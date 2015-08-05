package com.htyz.navy.sql;

import java.sql.*;
import java.util.*;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
//import com.navy.util.*;

/**
 * Individual pool associated with a JDBC datasource and username.  Each pool
 * is identified by an alias.  Use the alias name to acquire a ref to a
 * Connection from the pool.  See the ConnectionPoolManager for further
 * details on how to do this.
 *
 * @see ConnectionPoolManager
 * @author James Cooper
 * @version $Id: ConnectionPool.java,v 1.18 2001/07/26 16:28:32 pixel Exp $
 */
public class ConnectionPool {

    Vector connVector, closeVector;
    String url;
    private Queue waitingThreads;
    private String username, password, alias;
    private int maxConn, timeoutSeconds, checkoutSeconds, numCheckoutTimeout;
    private int numRequests, numWaits, maxCheckout;

    private boolean cacheStatements;

    /** Number of times connections within the pool have been found 
     *  to be closed 
     */
    int numConnectionFaults = 0;

    private boolean trace;
    private int prefetchSize = -1;

    /**
     * Creates a Connection pool with no maxCheckout parameter.
     *
     * @param alias Name of the pool
     * @param url JDBC URL to connect to
     * @param username JDBC username to connect as
     * @param password username's password in the database
     * @param maxConn Maximum number of connections to open; When this limit
     *                is reached, threads requesting a connection are queued
     *                until a connection becomes available
     * @param timeoutSeconds Maximum number of seconds a Connection can go
     *                       unused before it is closed
     * @param checkoutSeconds Maximum number of seconds a Thread can checkout a
     *                        Connection before it is closed and returned to the
     *                        pool.  This is a protection against the Thread
     *                        dying and leaving the Connection checked out
     *                        indefinately
     */
    public ConnectionPool(
        String alias,
        String url,
        String username,
        String password,
        int maxConn,
        int timeoutSeconds,
        int checkoutSeconds) {
        this(
            alias,
            url,
            username,
            password,
            maxConn,
            timeoutSeconds,
            checkoutSeconds,
            0);
    }
    /**
     * Creates a Connection pool
     *
     * @param alias Name of the pool
     * @param url JDBC URL to connect to
     * @param username JDBC username to connect as
     * @param password username's password in the database
     * @param maxConn Maximum number of connections to open; When this limit
     *                is reached, threads requesting a connection are queued
     *                until a connection becomes available
     * @param timeoutSeconds Maximum number of seconds a Connection can go
     *                       unused before it is closed
     * @param checkoutSeconds Maximum number of seconds a Thread can checkout a
     *                        Connection before it is closed and returned to the
     *                        pool.  This is a protection against the Thread
     *                        dying and leaving the Connection checked out
     *                        indefinately
     * @param maxCheckout If this is greater than 0, the number of times a
     *                    Connection may be checked out before it is closed.
     *                    Used as a safeguard against cursor leak, which occurs
     *                    if you don't call ResultSet.close() and
     *                    Statement.close()
     */
    public ConnectionPool(
        String alias,
        String url,
        String username,
        String password,
        int maxConn,
        int timeoutSeconds,
        int checkoutSeconds,
        int maxCheckout) {

        this.timeoutSeconds = timeoutSeconds;
        this.checkoutSeconds = checkoutSeconds;

        this.alias = alias;
        this.url = url;
        this.username = username;
        this.password = password;
        this.maxConn = maxConn;
        this.maxCheckout = maxCheckout;

        this.numRequests = 0;
        this.numWaits = 0;
        this.numCheckoutTimeout = 0;

        connVector = new Vector(maxConn);
        closeVector = new Vector(maxConn);
        waitingThreads = new Queue();

        trace = false;

        cacheStatements = true;
    }
    private void closeConnection(PooledConnection conn) {
        //
        // I've had problems with Oracle drivers that resulted in a 
        // deadlock when trying to close a connection.
        //
        // So we'll set a timeout of 10 seconds on this operation.  if the
        // close doesn't complete within this interval, we'll return 
        // without removing the connection.
        //
        // This will hopefully still allow us to trap cases where buggy
        // application code fails to return the Connection back to the 
        // pool, but won't stop a query that's in the middle of running on 
        // some drivers (Oracle).  With some MySQL drivers, this will 
        // interrupt a query in progress.
        //
        try {
            // remove the connection in all cases
            closeVector.removeElement(conn);

            JavaAlarm alarm = new JavaAlarm(conn, 10000);
        } catch (TimeoutException e) {
            // we were unable to close the connection within 10 seconds
            if (trace)
                e.printStackTrace();
            System.err.println("ConnectionPool: " + e);
        }
    }
    private void closeConnections() {
        Vector closeTmp = (Vector) closeVector.clone();
        for (int i = 0; i < closeTmp.size(); i++) {
            PooledConnection conn = (PooledConnection) closeTmp.elementAt(i);
            closeConnection(conn);
        }
    }
    /** Build a connection to the database using the appropriate database
     * driver.
     * @return a connection to the database
     */
    Connection createDriverConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
    /**
    * Dump some information about all connections
    *
    */
    public String dumpInfo() {
        String LS = System.getProperty("line.separator");
        String report = "Pool: " + this.toString() + LS;
        report += "\tAlias: " + this.getAlias() + LS;
        report += "\tMax connections: " + this.getMaxConn() + LS;
        report += "\tURL: " + this.url + LS;
        report += "\tCheckouts: " + this.getNumRequests() + LS;
        report += "\tThread waits: " + this.getNumWaits() + LS;
        report += "\tConnections found closed: " + numConnectionFaults + LS;
        report += "\tConnections reaped by timeout: "
            + this.getNumCheckoutTimeouts()
            + LS;
        report += "\tConnections currently in pool: " + this.size() + LS;
        Vector connectionVector = this.connVector;
        PooledConnection pc;
        for (int i = 0; i < connectionVector.size(); i++) {
            pc = (PooledConnection) connectionVector.elementAt(i);
            if (pc != null) {
                report += pc.dumpInfo();
            } // if
        } // for
        return report;
    }
    /**
     * Returns the alias for this pool.  This name is defined by the user in
     * the constructor
     */
    public String getAlias() {
        return alias;
    }
    /**
     * Returns whether connections in this pool will cache Statement and
     * PreparedStatement objects.
     */
    public boolean getCacheStatements() {
        return cacheStatements;
    }
    /**
     * Returns a connection for the pool.  There are three ways this can happen:
     *   <ol>
     *     <li>If a connection in the pool is free, it is locked and returned
     *         immediately
     *     <li>If no connections are free, but the pool is not full, a new
     *         Connection is opened and returned
     *     <li>If no connections are free, and the pool is full, the thread
     *         calls wait() and blocks until a connection is returned
     *   </ol>
     */
    public synchronized Connection getConnection() throws SQLException {
        if (trace)
            System.err.println("ConnectionPool: " + alias + " getConnection() called");
        numRequests++;
        while (true) {
            // Check if connection is free in pool
            PooledConnection p;
            for (int i = 0; i < connVector.size(); i++) {
                //
                // If a connection is free in the pool, then return it.
                //
                p = (PooledConnection) connVector.elementAt(i);
                if (p.getLock())
                    return releaseConnection(p); //return p;
            }

            //
            // If you got here, there were no connections free in the pool.
            //

            if (trace)
                System.err.println(
                    "ConnectionPool: "
                        + alias
                        + " all connections locked.  calling"
                        + " createConnection()");

            if (connVector.size() < maxConn) {
                //
                // If the number of connections in the pool is still less than the maximum number
                // of connections, then go through the work of initializing a new connection and
                // adding it to the pool.
                //
                if (trace)
                    System.err.println(
                        "ConnectionPool: "
                            + alias
                            + " opening new connection to database size="
                            + size());

                Connection conn = createDriverConnection();

                if (trace)
                    System.err.println(
                        "ConnectionPool: " + alias + " finished opening new connection");
                if (prefetchSize != -1) {
                    if (conn instanceof oracle.jdbc.driver.OracleConnection) {
                        ((oracle.jdbc.driver.OracleConnection) conn).setDefaultRowPrefetch(
                            prefetchSize);
                    }
                }
                p = new PooledConnection(conn, this);
                p.getLock();
                connVector.addElement(p);

                return releaseConnection(p); //p;
            }

            //
            // If you got here, then there are no free connections in the pool and the number
            // of connections in the pool is already at the maximum.  The only thing we can do
            // now is to wait until one is returned.
            //

            // Wait until a Connection is returned
            try {
                if (trace)
                    System.err.println(
                        "ConnectionPool: " + alias + " pool is full.  calling wait()");

                numWaits++;
                wait();
            } catch (InterruptedException e) {
            }

            if (trace)
                System.err.println(
                    "ConnectionPool: "
                        + alias
                        + " awoken from wait().  trying to grab"
                        + " an available connection");
        }
    }
    /**
     * Returns the maximum number of connections this pool can open
     */
    public int getMaxConn() {
        return maxConn;
    }
    /**
     * Returns the number of times a Connection has been closed by the
     * reapIdleConnections() method due to being checked out for longer than
     * the checkoutSeconds interval.
     * <p>
     * If this is greater than 0 it means that
     * you either have queries that take longer to execute than the
     * checkoutSeconds interval allows, or it means that you are forgetting to
     * call conn.close() somewhere in your application.  Both conditions are
     * undesirable, but they have different solutions.  In the latter case either
     * tune your query to execute more quickly, or increase the checkoutSeconds
     * parameter to the pool.  In the former case you simply need to find the
     * code that calls DriverManager.getConnection() but not conn.close()
     */
    public int getNumCheckoutTimeouts() {
        return numCheckoutTimeout;
    }
    /**
     * Returns the number of times a Connection has been checked out from the
     * pool
     */
    public int getNumRequests() {
        return numRequests;
    }
    /**
     * Returns the number of times a thread has had to block on wait() as a
     * result of all PooledConnections being in use.  Useful diagnostic tool to
     * see if your pool needs more nodes (which could require a database
     * license upgrade if you're running Oracle for instance)
     */
    public int getNumWaits() {
        return numWaits;
    }
    /** Get the current prefetch size
    */
    public int getPrefetchSize() {
        return prefetchSize;
    }
    /**
     * Check  all connections to make sure they haven't:
     *   1) gone idle for too long<br>
     *   2) been checked out by a thread for too long (cursor leak)
     */
    public void reapIdleConnections() {

        //
        // This section is synchronized because we're manipulating the state
        // of the connVector -- plus we're calling notifyAll(), which requires
        // that we have a sync lock
        // 
        synchronized (this) {
            if (trace)
                System.err.println(
                    "ConnectionPool: "
                        + alias
                        + " reapIdleConnections() starting,"
                        + " size="
                        + size());
            long now = System.currentTimeMillis();
            long idleTimeout = now - (timeoutSeconds * 1000);
            long checkoutTimeout = now - (checkoutSeconds * 1000);

            int i;
            for (i = 0; i < connVector.size();) {
                PooledConnection conn = (PooledConnection) connVector.elementAt(i);
                if (conn.isLocked() && (conn.getLastAccess() < checkoutTimeout)) {
                    numCheckoutTimeout++;
                    System.err.println(
                        "ConnectionPool: Warning: found " + "timed-out connection\n" + conn.dumpInfo());
                    removeConnection(conn);
                    notifyAll();
                } else
                    if ((conn.getLastAccess() < idleTimeout) && conn.getLock()) {
                        removeConnection(conn);
                        conn.releaseLock();
                        notifyAll();
                    } else {
                        // increment count, move to the next element
                        i++;
                    }
            }
        }

        //
        // Try to close the actual connections -- this part doesn't need to be
        // synchronized because we're only operating on the closeVector
        //
        closeConnections();

        if (trace)
            System.err.println(
                "ConnectionPool: " + alias + " reapIdleConnections() finished");
    }
    /**
     * This is a hook for doing things when you need to release a connection from
     * the pool.  (This might be a dumb idea, but it seemed better than cutting and
     * pasting the same code before each return statement in getConnection() --Orion).
     */
    private synchronized Connection releaseConnection(PooledConnection connection) {
        if (trace) {
            try {
                throw new RuntimeException();
            } catch (RuntimeException e) {
                connection.setTraceException(e);
            }
        }
        return connection;
    }
    /**
     * Removes all connections from the pool and calls close() on them.  It
     * squelches any SQLExceptions that might results from close().  That's
     * probably not ideal.
     */
    public synchronized void removeAllConnections() {
        // FIXME: what do we do when a connection is in progress.  Maybe we
        // pass a boolean to this method that tells us whether to shutdown
        // immediately, or to close connections as they are returned..
        // for now we'll just close 'em
        if (trace)
            System.err.println(
                "ConnectionPool: " + alias + " removeAllConnections() called");

        while (!connVector.isEmpty()) {
            PooledConnection conn = (PooledConnection) connVector.firstElement();
            removeConnection(conn);
        }
    }
    /**
     * Calls removeConnection(conn, false).  Won't close the connection
     * immediately.
     */
    private synchronized void removeConnection(PooledConnection conn) {
        removeConnection(conn, false);
    }
    /**
     * Removes the connection from the Vector of active connections.  If
     * closeConn is true, the actual JDBC Connection object is closed 
     * immediately.  Otherwise the Connection is placed on a Vector of
     * Connections to close later asynchronously by reapIdleConnections()
     */
    private synchronized void removeConnection(
        PooledConnection conn,
        boolean closeConn) {
        // remove connection from the vector of active connections and
        // place it on the vector of connections to close
        connVector.removeElement(conn);
        if (closeConn) {
            closeConnection(conn);
        } else {
            closeVector.addElement(conn);
        }
    }
    public synchronized void returnConnection(PooledConnection conn) {
        // remove connection from pool (close it) if we've used this connection
        // too many times
        if (maxCheckout > 0 && conn.getCheckoutCount() >= maxCheckout) {
            if (trace)
                System.err.println(
                    "ConnectionPool: "
                        + alias
                        + " connection checked out max # of times."
                        + " closing it.");

            removeConnection(conn, true);
        }

        // Wake up any waiting threads
        if (trace)
            System.err.println(
                "ConnectionPool: " + alias + " releasing lock and calling notifyAll()");

        conn.releaseLock();
        notifyAll();
    }
    /**
     * If set to true, connections in the pool will reuse Statements and
     * PreparedStatements.  If set to false, connections in the pool will
     * proxy calls to createStatement() and prepareStatement() straight through
     * to the driver.
     * <p>
     * Set this to false if you want to run multiple Statements over the same
     * Connection.
     * <p>
     * This is set to 'true' by default.
     */
    public void setCacheStatements(boolean cacheStmts) {
        this.cacheStatements = cacheStmts;
    }
    /** Change the number of rows prefetched for a result set, for those
    * drivers that support it.
    * @param newPrefetchSize the new prefetch size to use, -1 for the default
    */
    public void setPrefetchSize(int newPrefetchSize) {
        prefetchSize = newPrefetchSize;
    }
    /**
     * Turns tracing on or off.  If turned on, verbose messages about the pool
     * will be printed to STDERR
     */
    public void setTracing(boolean on) {
        trace = on;
    }
    /**
     * Returns the current number of Connections in the pool.
     */
    public int size() {
        return connVector.size();
    }
}

package com.htyz.navy.sql;



import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PooledConnection implements Connection, Runnable {

  private ConnectionPool pool;
  private Connection conn;
  private boolean locked;
  private long lastAccess;
  private long lastCheckin;
  private int checkoutCount;
  /** statement for the connection is cached here. */
  private PooledStatement theStatement = null;
  /** How many statements have been created on this connection */
  int totalStatements;
  /** How many prepared calls have been created on this connection */
  int preparedCalls;
  /** How many prepared statements have been found in the pool of PreparedStatements */
  int preparedStatementHits;
  /** How many prepared statements have been created for the pool of PreparedStatements */
  int preparedStatementMisses;

  /** How many prepared statements have been requested (if statement
   *  caching is turned off */
  int preparedStatements;

  /** 
   * Holds an exception which was thrown (and caught) when this connection pool was
   * checked out.  This will probably only be non-null when the connection pool is in
   * trace mode.
   */
  private Exception traceException;

  /** How many times to try re-opening a connection before giving up */
  private static final int closedConnMaxRetry = 5;
  /** How long to wait between re-opening retries, in milliseconds */
  private static final int closedConnRetryWait = 1000;


  private Map prepStmts;
  //private List callableStmts;

  public PooledConnection(Connection conn, ConnectionPool pool) {
    this.conn = conn;
    this.pool = pool;
    this.locked = false;
    this.lastAccess = 0;
    this.checkoutCount = 0;
    this.totalStatements = 0;

    prepStmts = new HashMap();

    //callableStmts = new LinkedList();
  }

    /**
     * This is used by ConnectionPool.removeConnection().  It closes all
     * statements and closes the actual JDBC Connection.  We implement this
     * in a runnable method so that we can run this in a separate Thread and
     * use a timeout mechanism to avoid deadlock (which was occurring when
     * trying to close a Connection that had a query in progress)
     */
    public void run() {
      try {
	  closeStatements();
      }
      catch(SQLException e) {
	  e.printStackTrace();
      }

      try {
	  getConnection().close();
      }
      catch(SQLException e) {
	  e.printStackTrace();
      }
    }

  /**
   * Closes all PreparedStatments and CallableStatements
   */
  public void closeStatements() throws SQLException {
    if (theStatement != null) {
      theStatement.getStatement().close();
    }

    //for prepared statements
    for (Iterator it = prepStmts.values().iterator(); it.hasNext();)
    {
      try
      {
        //for pooled prepared statements
        PooledPreparedStatement stmt =  (PooledPreparedStatement)it.next();
        stmt.getStatement().close();
      }
      catch (SQLException ex)
      {
        ex.printStackTrace();
      }
    }

    //for callable statements

    /**
     * commented out by James  May 2, 2001
     * didn't seem necessary, since we're not wrapping up CallableStatements,
     * and the application code should be calling close() on the statement
     * already.  If that's the case, then the code below would call close()
     * again, which could throw an Exception..
     *
    for (Iterator it = callableStmts.iterator(); it.hasNext();)
    {
      try
      {
        // for non-pooled
        ((CallableStatement)it.next()).close();
        //for pooled callable statements
        //PooledCallableStatement stmt = (PooledCallableStatement)e.nextElement();
        //stmt.getStatement().close();
      }
      catch (SQLException ex)
      {
          ex.printStackTrace();
      }
    }
    */

  }

  /**
   * Returns true if this Connection is not in use.  Returns false if it
   * is in use
   */
  public synchronized boolean getLock() {
    if(locked) return false;
    else {
      locked = true;
      checkoutCount++;

      // Touch our access time so that we don't get immediately reaped
      // by accident, which could happen if the idle timeout is close to the
      // max duration of the query
      lastAccess = System.currentTimeMillis();
      return true;
    }
  }

  public boolean isLocked() {
    return locked;
  }

  /**
   * Returns number of times this node has been checked out from the pool
   */
  public int getCheckoutCount() {
    return checkoutCount;
  }

  /**
   * Returns when this connection was last checked out; 0 if it has never
   * been used.
   */
  public long getLastAccess() {
    return lastAccess;
  }

  /**
   * Returns when this connection was last checked in (when it's lock was last released);
   * 0 if it has never been released.
   */
  public long getLastCheckin() {
    return lastCheckin;
  }

  /**
   * Doesn't actually close the connection, but instead returns itself
   * back to the pool to be re-used.  However, if you specified maxCheckouts
   * in the constructor, then this *will* close the JDBC Connection and re-open
   * it if the number of checkouts has been exceeded.
   */
  public void close() throws SQLException {
    lastAccess = System.currentTimeMillis();
    pool.returnConnection(this);
  }

  /**
   * called by ConnectionPool.returnConnection() right before it wakes up
   * the threads
   */
  protected void releaseLock() {
    lastCheckin = System.currentTimeMillis();
    locked = false;
  }

  protected Connection getConnection() {
    return conn;
  }

  public Connection getNativeConnection() {
    return conn;
  }

  /**
  * Dump some information about this connection and the statement
  *
  */
  public String dumpInfo() {
   String LS = System.getProperty("line.separator");
   String report = "\t\tConnection: " + this.toString()+ LS;
   report += "\t\t\tStatements Requested: " + this.totalStatements + LS;
   report += "\t\t\tPrepared Calls: " + this.preparedCalls + LS;
   if(pool.getCacheStatements()) {
     report += "\t\t\tPrepared Statements Hits: " + this.preparedStatementHits + LS;
     report += "\t\t\tPrepared Statements Misses: " + this.preparedStatementMisses + LS;
   }
   else {
     report += "\t\t\tPrepared Statements Requested: " + this.preparedStatements + LS;
   }
   report += "\t\t\tCheckout count: " + this.getCheckoutCount()+ LS;
   report += "\t\t\tLast Checkout: " + getLastAccess() + ": " + new java.util.Date(this.getLastAccess())+ LS;
   report += "\t\t\tLast Checkin : " + getLastCheckin() + ": " + new java.util.Date(getLastCheckin()) + LS;
   report += "\t\t\t" + (isLocked() ? "Connection IS checked out." : "Connection is NOT checked out.") + LS;
   report += "\t\t\tCheckout Stack Trace: ";
   if (traceException != null) {
     StringWriter sw = new StringWriter();
     PrintWriter pw = new PrintWriter(sw);
     traceException.printStackTrace(pw);
     report += sw.toString();
   }
   else {
     report += "null";
   }
   report += LS;

   if (theStatement!=null)
     report += theStatement.dumpInfo();
   return report;
  }

  /* Check to see if the connection has been closed (typically by the server)
   * and attempt to recover if it has.
   */
  public void guardConnection() {
    boolean badConnection;
    try {
      badConnection  = conn.isClosed();
    } catch (SQLException sqe) {
      badConnection = true;
    }
    if (badConnection) {
      pool.numConnectionFaults++;
      System.err.println("PooledConnection.guardConnection(): "+
           "found closed Connection. "+
           "Statement information follows. Attempting to recover.");
      if (theStatement!=null)
        System.err.println("PooledConnection.guardConnection(): "+
           theStatement.dumpInfo());
      else 
        System.err.println("PooledConnection.guardConnection: statement was null"); 
      this.theStatement = null;
      // Retry to open the connection, up to closedConnMaxRetry times.
      int retryCount = 0;
      for (retryCount = 0 ; retryCount < closedConnMaxRetry; retryCount++) {
        try {
          conn=pool.createDriverConnection();
        } catch (SQLException SQE) {
          System.err.println("PooledConnection.guardConnection(): "+
            "failed to create connection on try #"+retryCount);
          try {
            Thread.sleep(closedConnRetryWait);
          } catch (InterruptedException ie) {
            System.err.println("PooledConnection: "+ie);
	  }
          continue;         
	}
        System.err.println("PooledConnection.guardConnection(): "+
          "Recovered connection");
        return;      
      }
    }
  }

  public Statement createStatement() throws SQLException {
    // Following code is to test Connection recovery without
    // having to kill the database
    //java.util.Random r = new java.util.Random();
    //if (r.nextFloat()>0.75) {
    //  System.err.println("Poolconnection: simulating closed conn");
    //  conn.close();
    //}
    guardConnection();
    this.totalStatements++;
    if(pool.getCacheStatements()) {
	if (theStatement == null) {
	    theStatement = new PooledStatement(conn.createStatement());
	}
	return theStatement;
    }
    else {
	return conn.createStatement();
    }
  }



  /**************************************************************************
   *
   * Proxy all other JDBC calls to actual Connection object
   *
   **************************************************************************/
  public Map getTypeMap() throws SQLException {
		return conn.getTypeMap();
  }

  public PreparedStatement prepareStatement(String sql) throws SQLException {
    if(pool.getCacheStatements()) {
      PreparedStatement stmt = (PreparedStatement)prepStmts.get(sql);
      if (stmt == null) {
        // The prepared statement was not found in the prepared statement cache
        stmt = conn.prepareStatement(sql);
        stmt = new PooledPreparedStatement(stmt);
        synchronized (prepStmts) {
          prepStmts.put(sql, stmt);
        }
        preparedStatementMisses++;
      } else {
        preparedStatementHits++;
      }
      return stmt;
    }
    else {
      preparedStatements++;
      return conn.prepareStatement(sql);
    }
  }

  public PreparedStatement prepareStatement(String sql, int resultSetType,
                  int resultSetConcurrency) throws SQLException {
    if(pool.getCacheStatements()) {
      preparedStatementMisses++;
    }
    else {
      preparedStatements++;
    }
    PreparedStatement stmt = conn.prepareStatement(
        sql, resultSetType, resultSetConcurrency);
    //preparedStmts.add(stmt);
    return stmt;
  }

  public CallableStatement prepareCall(String sql) throws SQLException {
    preparedCalls++;
    CallableStatement stmt =  conn.prepareCall(sql);
    // removed by James on May 2, 2001
    //   uncomment when we actually cache this stuff
    //callableStmts.add(stmt);
    return stmt;
  }

  public CallableStatement prepareCall(String sql, int resultSetType,
	  int resultSetConcurrency) throws SQLException {
    preparedCalls++;          
    CallableStatement stmt =  
        conn.prepareCall(sql, resultSetType, resultSetConcurrency);
    // removed by James on May 2, 2001
    //   uncomment when we actually cache this stuff
    //callableStmts.add(stmt);
    return stmt;
  }

  public Statement createStatement(int resultSetType, int resultSetConcurrency)
      throws SQLException {
    this.totalStatements++;
    return conn.createStatement(resultSetType, resultSetConcurrency);
  }

  public String nativeSQL(String sql) throws SQLException {
    return conn.nativeSQL(sql);
  }

  public void setAutoCommit(boolean autoCommit) throws SQLException {
    conn.setAutoCommit(autoCommit);
  }

  public boolean getAutoCommit() throws SQLException {
    return conn.getAutoCommit();
  }

  public void commit() throws SQLException {
    conn.commit();
  }

  public void rollback() throws SQLException {
    conn.rollback();
  }

  public boolean isClosed() throws SQLException {
    return conn.isClosed();
  }

  public DatabaseMetaData getMetaData() throws SQLException {
    return conn.getMetaData();
  }

  public void setReadOnly(boolean readOnly) throws SQLException {
    conn.setReadOnly(readOnly);
  }

  public boolean isReadOnly() throws SQLException {
    return conn.isReadOnly();
  }

  public void setCatalog(String catalog) throws SQLException {
    conn.setCatalog(catalog);
  }

  public String getCatalog() throws SQLException {
    return conn.getCatalog();
  }

 public void setTypeMap(Map map) throws SQLException {
	 conn.setTypeMap(map);
 }

  public void setTransactionIsolation(int level) throws SQLException {
    conn.setTransactionIsolation(level);
  }

  public int getTransactionIsolation() throws SQLException {
    return conn.getTransactionIsolation();
  }

  public SQLWarning getWarnings() throws SQLException {
    return conn.getWarnings();
  }

  public void clearWarnings() throws SQLException {
    conn.clearWarnings();
  }

  protected void setTraceException(Exception e) {
    traceException = e;
  }

  /**
   * If the connection pool is in trace mode, you can examine the stack trace
   * in this exception to determine where it was last checked out.
   */
  public Exception getTraceException() { return traceException; }
}

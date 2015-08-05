/* JDBC Connection Pool
 * Copyright (C) 1998 James Cooper
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 *
 * For more information about this software, visit:
 * http://www.bitmechanic.com/projects/
 */

package com.htyz.navy.sql;

import java.sql.*;
import java.util.*;

/**
 * Implements java.sql.Driver and controls access to connections in the pool.
 * Here's how to use it:
 * <pre>
 *   // When your application starts, initialize the manager.  If you want
 *   // the pool to reap unused connections for you (recommended), then pass
 *   // an int to the constructor.  this tells the pool how many seconds to
 *   // wait between reaping connections in the pool.
 *   ConnectionPoolManager mgr = new ConnectionPoolManager(300);
 * 
 *   // Load the driver into the VM like you would do w/o the pool
 *   Class.forName("exgwe.sql.gweMysqlDriver").newInstance();
 * 
 *   // Add one alias to the pool for each JDBC datasource you wish to connect
 *   // to.  From this point forward any objects that need connection handles
 *   // do not need to know the url, username, or password of the databse.
 *   // They simply need the "alias" you named the pool with here
 *   mgr.addAlias("myalias", "exgwe.sql.gweMysqlDriver",
 *                "jdbc:mysql://localhost:3306/mydb",
 *                "username", "password",
 *                10,  // max connections to open
 *                300, // seconds a connection can be idle before it is closed
 *                120, // seconds a connection can be checked out by a thread
 *                     // before it is returned back to the pool
 *                30,  // number of times a connection can be re-used before 
 *                     // connection to database is closed and re-opened
 *                     // (optional parameter)
 *             false); // specifies whether to cache statements
 *                        (optional parameter.  set to 'true' by default.)
 *  
 *   // Later in your code, use the JDBC DriverManager to obtain a
 *   // connection manually
 *   Connection conn = DriverManager.getConnection(ConnectionPoolManager.URL_PREFIX +
 *                                   "myalias", null, null);
 *
 *   // Calling conn.close() returns the connection back to the pool
 *   conn.close();
 * </pre>
 * 
 * You can also call methods on the pool directly if you want to gather
 * statistics on the pool during runtime (to see if you need more connections
 * in the pool for example):
 * <pre>
 *   // First get a ref to the pool for the alias
 *   ConnectionPool pool = mgr.getPool("myalias");
 *
 *   // Then call methods on it
 *   System.out.println(pool.getNumWaits() + " threads have had to wait() for
 *                      connection handles.");
 *   System.out.println("Connections have been checked out from the pool " +
 *                       pool.getNumRequests() + " times.");
 * </pre>
 *
 * @see ConnectionPool
 * 
 * @author James Cooper
 * @version $Id: ConnectionPoolManager.java,v 1.8 2001/05/25 05:45:12 pixel Exp $
 */
public class ConnectionPoolManager implements Driver, Runnable {

  public static final String URL_PREFIX = "jdbc:bitmechanic:pool:";
  private static final int MAJOR_VERSION = 0;
  private static final int MINOR_VERSION = 92;

  private Hashtable aliasHash;
  private long sleepInterval;
  private boolean trace;

  /**
   * Creates a pool that doesn't monitor itself to check for idle/stale
   * connections.  Use this constructor only if you know what you're doing
   * and have a good reason to not use the monitor thread.
   */
  public ConnectionPoolManager() throws SQLException {
    aliasHash = new Hashtable();
    DriverManager.registerDriver(this);
    trace = false;
  }

  /**
   * Creates a pool with a monitoring thread that checks the pool to make
   * sure no connections are stale or idle.
   *
   * @param monitorInterval number of seconds between checks on the pool
   */
  public ConnectionPoolManager(int monitorInterval) throws SQLException {
    this();
    this.sleepInterval = monitorInterval * 1000;
    Thread t = new Thread(this);
    t.setDaemon(true);
    t.start();
  }

  /**
   * Adds an alias to the pool.  This <strong>does</strong> call 
   * Class.forName().newInstance() on the JDBC driver, so you don't have to
   * call that yourself.
   * <p>
   * idleTimeout and checkoutTimeout are expressed in seconds
   * <p>
   * maxCheckouts is unlimited
   * <p>
   * cacheStatements is set to 'true' by default
   *
   *
   * @param alias Name of the pool
   * @param driver Classname of JDBC driver to use
   * @param url JDBC URL to connect to
   * @param username JDBC username to connect as
   * @param password username's password in the database
   * @param maxConn Maximum number of connections to open; When this limit
   *                is reached, threads requesting a connection are queued
   *                until a connection becomes available
   * @param idleTimeout    Maximum number of seconds a Connection can go
   *                       unused before it is closed
   * @param checkoutTimeout Maximum number of seconds a Thread can checkout a
   *                        Connection before it is closed and returned to the
   *                        pool.  This is a protection against the Thread 
   *                        dying and leaving the Connection checked out
   *                        indefinately
   */
  public void addAlias(String alias, String driver, String url, 
		       String username, String password,
		       int maxConn, int idleTimeout, int checkoutTimeout) 
    throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    addAlias(alias, driver, url, username, password, maxConn, idleTimeout,
	     checkoutTimeout, 0);
  }

  /**
   * Adds an alias to the pool.  This <strong>does</strong> call 
   * Class.forName().newInstance() on the JDBC driver, so you don't have to
   * call that yourself.
   * <p>
   * idleTimeout and checkoutTimeout are expressed in seconds
   * <p>
   * cacheStatements is set to 'true' by default
   *
   * @param alias Name of the pool
   * @param driver Classname of JDBC driver to use
   * @param url JDBC URL to connect to
   * @param username JDBC username to connect as
   * @param password username's password in the database
   * @param maxConn Maximum number of connections to open; When this limit
   *                is reached, threads requesting a connection are queued
   *                until a connection becomes available
   * @param idleTimeout    Maximum number of seconds a Connection can go
   *                       unused before it is closed
   * @param checkoutTimeout Maximum number of seconds a Thread can checkout a
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
  public void addAlias(String alias, String driver, String url, 
		       String username, String password,
		       int maxConn, int idleTimeout, int checkoutTimeout,
		       int maxCheckout)
    throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      addAlias(alias, driver, url, username, password, maxConn, idleTimeout,
	       checkoutTimeout, maxCheckout, true);
  }

  /**
   * Adds an alias to the pool.  This <strong>does</strong> call 
   * Class.forName().newInstance() on the JDBC driver, so you don't have to
   * call that yourself.
   * <p>
   * idleTimeout and checkoutTimeout are expressed in seconds
   *
   * @param alias Name of the pool
   * @param driver Classname of JDBC driver to use
   * @param url JDBC URL to connect to
   * @param username JDBC username to connect as
   * @param password username's password in the database
   * @param maxConn Maximum number of connections to open; When this limit
   *                is reached, threads requesting a connection are queued
   *                until a connection becomes available
   * @param idleTimeout    Maximum number of seconds a Connection can go
   *                       unused before it is closed
   * @param checkoutTimeout Maximum number of seconds a Thread can checkout a
   *                        Connection before it is closed and returned to the
   *                        pool.  This is a protection against the Thread 
   *                        dying and leaving the Connection checked out
   *                        indefinately
   * @param maxCheckout If this is greater than 0, the number of times a
   *                    Connection may be checked out before it is closed. 
   *                    Used as a safeguard against cursor leak, which occurs
   *                    if you don't call ResultSet.close() and
   *                    Statement.close() 
   * @param cacheStatements If set to true, the PooledConnection will reuse
   *        the same Statement object when conn.createStatement() is called.
   *        It will also cache PreparedStatements.
   */
  public void addAlias(String alias, String driver, String url, 
		       String username, String password,
		       int maxConn, int idleTimeout, int checkoutTimeout,
		       int maxCheckout, boolean cacheStatements)
    throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    Class.forName(driver).newInstance();
    ConnectionPool pool = new ConnectionPool(alias, url, username, password, 
					     maxConn, idleTimeout,
					     checkoutTimeout, maxCheckout);
    pool.setCacheStatements(cacheStatements);
    addAlias(pool);
  }

  /**
   * Adds an alias to the pool.  This <strong>does</strong> call 
   * Class.forName().newInstance() on the JDBC driver, so you don't have to
   * call that yourself.
   * <p>
   * idleTimeout and checkoutTimeout are expressed in seconds
   * <p>
   * cacheStatements is set to 'true' by default
   *
   * @param alias Name of the pool
   * @param driver Classname of JDBC driver to use
   * @param url JDBC URL to connect to
   * @param username JDBC username to connect as
   * @param password username's password in the database
   * @param maxConn Maximum number of connections to open; When this limit
   *                is reached, threads requesting a connection are queued
   *                until a connection becomes available
   * @param idleTimeout    Maximum number of seconds a Connection can go
   *                       unused before it is closed
   * @param checkoutTimeout Maximum number of seconds a Thread can checkout a
   *                        Connection before it is closed and returned to the
   *                        pool.  This is a protection against the Thread 
   *                        dying and leaving the Connection checked out
   *                        indefinately
   * @param maxCheckout If this is greater than 0, the number of times a
   *                    Connection may be checked out before it is closed.
   *                    Used as a safeguard against cursor leak, which occurs
   *                    if you don't call ResultSet.close() and
   *                    Statement.close()
   * @param rowPrefetch If your db driver supports a row prefetch size (Oracle does)
   *                    use this value for row prefetch, -1 for the default
   */
  public void addAlias(String alias, String driver, String url, 
		       String username, String password,
		       int maxConn, int idleTimeout, int checkoutTimeout,
		       int maxCheckout, int rowPrefetch)
    throws ClassNotFoundException, InstantiationException, IllegalAccessException {
      addAlias(alias, driver, url, username, password, maxConn,
	       idleTimeout, checkoutTimeout, maxCheckout, rowPrefetch, true);
  }

  /**
   * Adds an alias to the pool.  This <strong>does</strong> call 
   * Class.forName().newInstance() on the JDBC driver, so you don't have to
   * call that yourself.
   * <p>
   * idleTimeout and checkoutTimeout are expressed in seconds
   *
   * @param alias Name of the pool
   * @param driver Classname of JDBC driver to use
   * @param url JDBC URL to connect to
   * @param username JDBC username to connect as
   * @param password username's password in the database
   * @param maxConn Maximum number of connections to open; When this limit
   *                is reached, threads requesting a connection are queued
   *                until a connection becomes available
   * @param idleTimeout    Maximum number of seconds a Connection can go
   *                       unused before it is closed
   * @param checkoutTimeout Maximum number of seconds a Thread can checkout a
   *                        Connection before it is closed and returned to the
   *                        pool.  This is a protection against the Thread 
   *                        dying and leaving the Connection checked out
   *                        indefinately
   * @param maxCheckout If this is greater than 0, the number of times a
   *                    Connection may be checked out before it is closed.
   *                    Used as a safeguard against cursor leak, which occurs
   *                    if you don't call ResultSet.close() and
   *                    Statement.close()
   * @param rowPrefetch If your db driver supports a row prefetch size (Oracle does)
   *                    use this value for row prefetch, -1 for the default
   * @param cacheStatements If set to true, the PooledConnection will reuse
   *        the same Statement object when conn.createStatement() is called.
   *        It will also cache PreparedStatements.
   */
  public void addAlias(String alias, String driver, String url, 
		       String username, String password,
		       int maxConn, int idleTimeout, int checkoutTimeout,
		       int maxCheckout, int rowPrefetch,
		       boolean cacheStatements)
    throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    Class.forName(driver).newInstance();
    ConnectionPool pool = new ConnectionPool(alias, url, username, password,
					     maxConn, idleTimeout,
					     checkoutTimeout, maxCheckout);
    pool.setCacheStatements(cacheStatements);
    pool.setPrefetchSize(rowPrefetch);
    addAlias(pool);
  }
  /**
   * Adds an alias to the pool using a supplied ConnectionPool object.
   * <p>
   * <strong>Beware!</strong> - this will not call Class.forName() on the
   * JDBC driver for you.  Make sure to call that before calling this method
   * or the driver will not work (getConnection() will fail)
   */
  public synchronized void addAlias(ConnectionPool pool) {
    aliasHash.put(pool.getAlias(), pool);
  }

  /**
   * Closes all Connections in the pool with the supplied alias
   *
   * @throws SQLException if no pool exists for that alias
   */
  public synchronized void removeAlias(String alias) throws SQLException {
    ConnectionPool p = getPool(alias);
    aliasHash.remove(alias);
    p.removeAllConnections();
  }

  /**
   * Returns an Enumeration of the ConnectionPool objects currently created
   */
  public Enumeration getPools() {
    return aliasHash.elements();
  }

  /**
   * Returns the pool with the supplied alias
   * 
   * @throws SQLException if no pool exists for that alias
   */
  public ConnectionPool getPool(String alias) throws SQLException {
    ConnectionPool p = (ConnectionPool)aliasHash.get(alias);
    if(p == null)
      throw new SQLException("No connection pool created for alias: " + alias);
    return p;
  }

  /**
   * Monitors each ConnectionPool and makes sure that no connection has gone
   * idle or has been checked out for too long.
   */
  public void run() {
    while(true) {
      try {
	Thread.currentThread().sleep(sleepInterval);
      }
      catch(InterruptedException e) { }

      for(Enumeration e = aliasHash.elements(); e.hasMoreElements();) {
	ConnectionPool pool = (ConnectionPool)e.nextElement();
	try {
	  pool.reapIdleConnections();
	}
	catch(Exception ex) {
	  ex.printStackTrace();
	}
      }
    }
  }

  /**
   * Turns tracing on or off.  It is off by default.  When you turn 
   * tracing on, the pool will print verbose messages about what it's
   * doing to STDERR.  If your application is hanging when it calls
   * DriverManager.getConnection() this may help diagnose where things go 
   * wrong.
   */
  public void setTracing(boolean on) {
    if(on != trace) {
      for(Enumeration e = getPools(); e.hasMoreElements();) {
	ConnectionPool p = (ConnectionPool)e.nextElement();
	p.setTracing(on);
      }

      String str = "off";
      if(on) str = "on";

      System.err.println("ConnectionPoolManager: Tracing turned " + str);

      trace = on;
    }
  }

  /**************************************************************************
   *
   * java.sql.Driver methods
   *
   **************************************************************************/

  /**
   * Returns a connection from pool
   *
   * @returns JDBC Connection from pool, or null if url does not start with
   *          URL_PREFIX
   * @throws SQLException if no alias is given in the URL or if no pool exists
   *         with that alias name
   */
  public Connection connect(String url, Properties props) throws SQLException {
    if(!url.startsWith(URL_PREFIX)) return null;

    if(url.length() <= URL_PREFIX.length())
      throw new SQLException("Invalid URL: " + url + " -- No alias given");

    String alias = url.substring(URL_PREFIX.length());

    if(trace) System.err.println("ConnectionPoolManager: connect() called for "
				 + alias + ".  calling pool.getConnection()");

    ConnectionPool p = getPool(alias);

    return p.getConnection();
  }

  /**
   * Returns true of url starts with URL_PREFIX
   */
  public boolean acceptsURL(String url) {
    return url.startsWith(URL_PREFIX);
  }

  public int getMajorVersion() {
    return MAJOR_VERSION;
  }

  public int getMinorVersion() {
    return MINOR_VERSION;
  }

  public DriverPropertyInfo[] getPropertyInfo(String str, Properties props) {
    return new DriverPropertyInfo[0];
  }

  /**
   * Always returns false since I haven't sent this code to Sun for approval
   */
  public boolean jdbcCompliant() {
    return false;
  }

  /**
  * Dump some information about all connections and pools into a String
  *
  */
  public String dumpInfo() {
    String LS = System.getProperty("line.separator");
    Enumeration allPools = getPools();
    ConnectionPool currentPool;
    String report = "";
    while (allPools.hasMoreElements()) {
      currentPool = (ConnectionPool)allPools.nextElement();
      report += currentPool.dumpInfo();
    } // while
    return report;
  } // dumpinfo
}

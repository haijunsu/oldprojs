///////////////////////////////////////////////////
//利用JDBC-ODBC桥和JNDI技术连接SQL_SERVER数据库
//
//适用服务器：Tomcat
//
// 苏海军
//
//  2002.1
///////////////////////////////////////////////////

/**
 * @(#)beanSQL.java
 *
 * 功能描述：通过DataSource联接数据库或连接池连接数据库
 *			并执行SQL查询、执行、事务等处理
 *
 * @version ver 1.1
 * @author name: 苏海军
 * @copyright 2002, 版权所有
 * 修改日期： 2002年 8月 3日
 */
package com.htyz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import sun.jdbc.rowset.CachedRowSet;

import com.htyz.system.SystemProperties;

//public class bean_SQL extends HttpServlet implements java.io.Serializable

public class beanSQL
{
	private static DataSource ds = null;
	private	Context initCtx = null;
	private	Context envCtx = null;
//	private static final String CONFIG_BUNDLE_NAME = "system.system";
	private String dbType = SystemProperties.getProperty("db.type");
	private boolean tracing = SystemProperties.getBooleanProperty("tracing");
	private String serverType = SystemProperties.getProperty("server.type");

	public beanSQL()
	{
		if (tracing)
			System.out.println ("init beanSQL ... database type is " + dbType);

			initContext();
	}

	private void initContext()
	{
	    if ( serverType.equalsIgnoreCase("tomcat") )
	    	initTomcat();
	    else if ( serverType.equalsIgnoreCase("webSphere") )
	    	initwebSphere();
	}

	/** ************************************************************
     * 功能描述：初始化Tomcat数据源
     *
     * @param
     *
     * @retruns
     *
     * @exception
     *
     * @author name: 苏海军
     * 创建日期：2002年 8月 3日
     */
	private void initTomcat () {
		//初始化Context
	    try
	    {
	        initCtx = new InitialContext();
	    }
	    catch (NamingException e)
	    {
	        System.err.println("beanSQL Couldn't build an Tomcat initial context : " + e);
	        if (tracing)
	        	e.printStackTrace();
	        return;
	    }

	    //取出环境参数
	    try
	    {
			envCtx = (Context) initCtx.lookup( SystemProperties.getProperty("tomcat.dataSource.env.lookup") );
	    }
	    catch (NamingException e)
	    {
	        System.err.println("beanSQL java:comp/env falsed : " + e);
	        if (tracing)
	        	e.printStackTrace();
	        return;
	    }

		// 取出data source
		try
		{
			ds = (DataSource)envCtx.lookup( SystemProperties.getProperty("tomcat.dataSource.ds.lookup") );
		}
		catch (NamingException e)
		{
		    System.err.println("beanSQL jdbc/beanSQL lookup failed : " + e);
	        if (tracing)
	        	e.printStackTrace();
	    }
	}

	/** ************************************************************
     * 功能描述：获取webSphere数据源的联接
     *
     * @param
     *
     * @retruns
     *
     * @exception
     *
     * @author name: 苏海军
     * 创建日期：2002年 8月 4日
     */
	private void initwebSphere() {

		try {
//			Hashtable parms = new Hashtable();
//			parms.put(Context.INITIAL_CONTEXT_FACTORY, SystemProperties.getProperty("webSphere.dataSource.factory"));
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup( SystemProperties.getProperty("webSphere.dataSource.ds.lookup") );
		}
		catch (Exception e) {
			System.out.println("Naming service exception: " + e.getMessage());
	        if (tracing)
	        	e.printStackTrace();
		}
	}


	private Connection getConn( ) throws Exception {
		Connection conn;
		if (tracing)
			System.out.println ("Starting connect...");
			conn = ds.getConnection();
			if (tracing)
				System.out.println ("dataSource connection OK.");
		return conn;

	}
	//added by liu_ag 2002.8.15
	public Connection getCon( ) throws Exception {
		Connection con;
		if (tracing)
			System.out.println ("=====================Starting connect...");
			con = ds.getConnection();
			if (tracing)
				System.out.println ("d================ataSource connection OK.");
		return con;

	}
	/** ************************************************************
     * 功能描述：执行SQL语句，返回影响的行数
     *
     * @param String sql
     *
     * @retruns int 影响的行数
     *
     * @exception Exception
     *
     * @author name: 苏海军
     * 修改日期：2002年 8月 5日
     */
	public int executeSQL(String sql) throws SQLException, Exception
	{
		Connection conn = null;
		Statement stmt = null;
		int i_rtn;
		try
		{
			conn = getConn();
			if(conn == null)
			{
				throw new Exception ( "Connect to database error!" );
			}

			if (tracing)
				System.out.println ( "execute SQL: " + sql );
			//执行SQL
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			//返回影响的行数
			i_rtn = stmt.getUpdateCount();
			//关闭连接
			return i_rtn;
		}
		catch ( SQLException e ) {
			if (tracing)
			{
				System.err.println ( "SQL ErrorCode: " + e.getErrorCode() );
				System.err.println ( "SQLState: " + e.getSQLState() );
				e.printStackTrace();
			}
			throw new SQLException( e.getMessage(), e.getSQLState(), e.getErrorCode() );
		}
		finally
		{
			//自动关闭连接
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
		}
	}

	/** ************************************************************
     * 功能描述：执行查询语句
     *
     * @param sql 查询的SQL语句
     *
     * @retruns CachedRowSet 结果集
     *
     * @exception Exception
     *
     * @author name: 苏海军
     * 创建日期：2002年 8月 5日
     */
	public CachedRowSet executeQuery(String sql) throws SQLException, Exception
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =null;
		CachedRowSet crs = null;
		try
		{
			//与数据库建立联接
			conn = getConn();
			if(conn == null)
			{
				throw new Exception ( "Connect to database error!" );
			}
			//执行查询
			if (tracing)
				System.out.println ( "Query SQL: " + sql );

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			//缓存执行结果
			crs = new CachedRowSet();
			crs.populate(rs);
			//关闭结果集和连接
			rs.close();
			return crs;
		}
		catch ( SQLException e ) {
			if (tracing)
			{
				System.err.println ( "SQL ErrorCode: " + e.getErrorCode() );
				System.err.println ( "SQLState: " + e.getSQLState() );
				e.printStackTrace();
			}
			throw new SQLException( e.getMessage(), e.getSQLState(), e.getErrorCode() );
		}

		finally
		{
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
		}
	}

	/** ************************************************************
     * 功能描述：执行SQL语句，返回影响的行数
     *
     * @param String sql
     *
     * @retruns int 影响的行数
     *
     * @exception Exception
     *
     * @author name: 苏海军
     * 修改日期：2002年 8月 5日
     */
	public int[] executeSQL(String[] sql) throws SQLException, Exception
	{
		Connection conn = null;
		Statement stmt = null;
		int i_rtn[];
		try
		{
			conn = getConn();
			if(conn == null)
			{
				throw new Exception ( "Connect to database error!" );
			}

			//执行SQL
			stmt = conn.createStatement();

			for (int i=0; i<sql.length; i++) {

				if (tracing)
					System.out.println ( "Adding SQL to Batch: " + sql[i] );

				stmt.addBatch( sql[i] );
			}

			i_rtn = stmt.executeBatch();

			//关闭连接
			return i_rtn;
		}
		catch ( SQLException e ) {
			if (tracing)
			{
				System.err.println ( "SQL ErrorCode: " + e.getErrorCode() );
				System.err.println ( "SQLState: " + e.getSQLState() );
				e.printStackTrace();
			}
			throw new SQLException( e.getMessage(), e.getSQLState(), e.getErrorCode() );
		}
		finally
		{
			//自动关闭连接
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
		}
	}
}




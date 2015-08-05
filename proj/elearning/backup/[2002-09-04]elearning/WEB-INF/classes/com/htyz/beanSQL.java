///////////////////////////////////////////////////
//����JDBC-ODBC�ź�JNDI��������SQL_SERVER���ݿ�
//
//���÷�������Tomcat
//
// �պ���
//
//  2002.1
///////////////////////////////////////////////////

/** 
 * @(#)beanSQL.java 
 *
 * ����������ͨ��DataSource�������ݿ�����ӳ��������ݿ�	
 *			��ִ��SQL��ѯ��ִ�С�����ȴ���
 *
 * @version ver 1.1
 * @author name: �պ���
 * @copyright 2002, ��Ȩ����
 * �޸����ڣ� 2002�� 8�� 3��
 */
package com.htyz;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import sun.jdbc.rowset.CachedRowSet;
import java.util.Hashtable;
import com.htyz.system.SystemProperties;
import com.htyz.navy.sql.ConnectionPoolManager;

//public class bean_SQL extends HttpServlet implements java.io.Serializable

public class beanSQL
{
	private static DataSource ds = null;
  	private ConnectionPoolManager pool = null;
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
			
		if (dbType.equalsIgnoreCase("pool"))
			initConnectPool();
		else
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
     * ������������ʼ��Tomcat����Դ
     * 
     * @param 
     * 
     * @retruns 
     * 
     * @exception 
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 3��
     */
	private void initTomcat () {
		//��ʼ��Context
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
				
	    //ȡ����������
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
			
		// ȡ��data source
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
     * ������������ȡwebSphere����Դ������
     * 
     * @param 
     * 
     * @retruns 
     * 
     * @exception 
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 4��
     */
	private void initwebSphere() {
	
		try {
			Hashtable parms = new Hashtable();
			parms.put(Context.INITIAL_CONTEXT_FACTORY, SystemProperties.getProperty("webSphere.dataSource.factory"));
			InitialContext ctx = new InitialContext(parms);
			ds = (DataSource)ctx.lookup( SystemProperties.getProperty("webSphere.dataSource.ds.lookup") );
		}
		catch (Exception e) {
			System.out.println("Naming service exception: " + e.getMessage());
	        if (tracing)
	        	e.printStackTrace();
		}
	}
	
	/** ************************************************************
     * ������������ȡ���ӻ����
     * 
     * @param 
     * 
     * @retruns 
     * 
     * @exception 
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 4��
     */
	private void initConnectPool() {
	    String[] dbinfo = new String[8];

		dbinfo[0] = SystemProperties.getProperty("pool.alias");
		dbinfo[1] = SystemProperties.getProperty("pool.driver");
		dbinfo[2] = SystemProperties.getProperty("pool.url");
		dbinfo[3] = SystemProperties.getProperty("pool.username");
		dbinfo[4] = SystemProperties.getProperty("pool.password");
		dbinfo[5] = SystemProperties.getProperty("pool.maxConn");
		dbinfo[6] = SystemProperties.getProperty("pool.timeoutSeconds");
		dbinfo[7] = SystemProperties.getProperty("pool.checkoutSeconds");
		
		try {
		    pool = new ConnectionPoolManager(120);
		    pool.addAlias( dbinfo[0], dbinfo[1], dbinfo[2], dbinfo[3], dbinfo[4],
		                  Integer.parseInt(dbinfo[5]), Integer.parseInt(dbinfo[6]),
		                  Integer.parseInt(dbinfo[7]));
		    pool.setTracing(tracing);
	    }
	    catch (Exception ex) {
	    	System.out.println(ex.getMessage());
	    	if (tracing)
	    		ex.printStackTrace();
	    }
	}
	
	
	private Connection getConn( ) throws Exception {
		Connection conn;
		if (tracing)
			System.out.println ("Starting connect...");
		if ( dbType.equalsIgnoreCase("pool") ) {
			String alias = SystemProperties.getProperty( "pool.alias" );
			conn = DriverManager.getConnection(ConnectionPoolManager.URL_PREFIX + alias, null, null);
			if (tracing)
				System.out.println ("pool connection OK.");
		}
		else 
		{
			conn = ds.getConnection();
			if (tracing)
				System.out.println ("dataSource connection OK.");
		}
		return conn;
		
	}
	//added by liu_ag 2002.8.15
	public Connection getCon( ) throws Exception {
		Connection con;
		if (tracing)
			System.out.println ("=====================Starting connect...");
		if ( dbType.equalsIgnoreCase("pool") ) {
			String alias = SystemProperties.getProperty( "pool.alias" );
			con = DriverManager.getConnection(ConnectionPoolManager.URL_PREFIX + alias, null, null);
			if (tracing)
				System.out.println ("==========pool connection OK.");
		}
		else 
		{
			con = ds.getConnection();
			if (tracing)
				System.out.println ("d================ataSource connection OK.");
		}
		return con;
		
	}
	/** ************************************************************
     * ����������ִ��SQL��䣬����Ӱ�������
     * 
     * @param String sql
     * 
     * @retruns int Ӱ�������
     * 
     * @exception Exception
     * 
     * @author name: �պ���
     * �޸����ڣ�2002�� 8�� 5��
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
			//ִ��SQL
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			//����Ӱ�������
			i_rtn = stmt.getUpdateCount();
			//�ر�����
			stmt.close();
			conn.close();
			return i_rtn;
		}
		catch ( SQLException e ) {
			if (tracing)
			{
				System.err.println ( "SQL ErrorCode: " + e.getErrorCode() );
				System.err.println ( "SQLState: " + e.getSQLState() );
				e.printStackTrace();
			}
			stmt.close();
			conn.close();
			throw new SQLException( e.getMessage(), e.getSQLState(), e.getErrorCode() );
		}
		finally
		{
			//�Զ��ر�����
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
     * ����������ִ�в�ѯ���
     * 
     * @param sql ��ѯ��SQL���
     * 
     * @retruns CachedRowSet �����
     * 
     * @exception Exception
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 5��
     */
	public CachedRowSet executeQuery(String sql) throws SQLException, Exception
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =null;
		CachedRowSet crs = null;
		try
		{
			//�����ݿ⽨������
			conn = getConn();
			if(conn == null)
			{
				throw new Exception ( "Connect to database error!" );
			}
			//ִ�в�ѯ			
			if (tracing)
				System.out.println ( "Query SQL: " + sql );

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			//����ִ�н��
			crs = new CachedRowSet();
			crs.populate(rs);
			//�رս����������
			rs.close();
			stmt.close();
			conn.close();
			return crs;
		}
		catch ( SQLException e ) {
			if (tracing)
			{
				System.err.println ( "SQL ErrorCode: " + e.getErrorCode() );
				System.err.println ( "SQLState: " + e.getSQLState() );
				e.printStackTrace();
			}
			rs.close();
			stmt.close();
			conn.close();
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
     * ����������ִ��SQL��䣬����Ӱ�������
     * 
     * @param String sql
     * 
     * @retruns int Ӱ�������
     * 
     * @exception Exception
     * 
     * @author name: �պ���
     * �޸����ڣ�2002�� 8�� 5��
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
			
			//ִ��SQL
			stmt = conn.createStatement();
			
			for (int i=0; i<sql.length; i++) {
				
				if (tracing)
					System.out.println ( "Adding SQL to Batch: " + sql[i] );
			
				stmt.addBatch( sql[i] );
			}

			i_rtn = stmt.executeBatch();

			//�ر�����
			stmt.close();
			conn.close();
			return i_rtn;
		}
		catch ( SQLException e ) {
			if (tracing)
			{
				System.err.println ( "SQL ErrorCode: " + e.getErrorCode() );
				System.err.println ( "SQLState: " + e.getSQLState() );
				e.printStackTrace();
			}
			stmt.close();
			conn.close();
			throw new SQLException( e.getMessage(), e.getSQLState(), e.getErrorCode() );
		}
		finally
		{
			//�Զ��ر�����
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
	
	
	
	
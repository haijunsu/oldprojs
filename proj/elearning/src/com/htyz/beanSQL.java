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




/**
 * @(#)SQLBean.java  2003-1-10
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.sql;

import java.io.Serializable;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import sun.jdbc.rowset.CachedRowSet;

import com.codestudio.util.JDBCPool;
import com.codestudio.util.JDBCPoolMetaData;
import com.codestudio.util.SQLManager;
import com.idn.property.PropertyManager;

/**
 * <P>
 * ��ѯDataSource���ƣ����������ݿ�����������ӣ�����ִ�о�̬SQL��ѯ�͸���
 * </P>
 * 
 * @version 0.4
 * @author �պ���
 *  
 */

public class SQLBean implements Serializable {
	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log = new com.idn.log.LogWrapper(
			SQLBean.class);

	/**
	 * �Ƿ��Ѿ���ʼ���ı�־����֤����ʼ��һ��
	 */
	private static boolean isInited = false;

	/**
	 * ��������Դ��ͨ��getDS()����ȡ
	 */
	private static DataSource ds = null;

	/**
	 * �����û����ݿ��������ݿ�ķ��� datasource/direct
	 */
	private static final String connectMethod = PropertyManager
			.getProperty("connect.method");

	/**
	 * ��ѯ����з��ص������������0����ʾû������
	 */
	private int m_returnMaxRows = 10000;

	/**
	 * ����SQLBean
	 */
	public SQLBean() {
		if (!isInited) {
			isInited = getDS();
		}
		initReturnMaxRows();
	}

	/**
	 * ����Ӧ�÷��������ͣ�����ȡ����Դ
	 */
	private static synchronized boolean getDS() {

		//����isInit���ԣ�����֤ds����ѯһ��
		try {
			log.debug("getDS() - init flag: " + String.valueOf(isInited));
			if (connectMethod.equalsIgnoreCase("direct"))
				return initDirectConnect();
			if (connectMethod.equalsIgnoreCase("datasource"))
				return initDS();
			if (connectMethod.equalsIgnoreCase("struts")) {
				ds = ActionDataSource.dataSource;
				if (ds == null) {
					log.error("getDS() - Get struts DataSource fail.");
					return false;
				}
				return true;
			}
			if (connectMethod.equalsIgnoreCase("poolman")) {
				return initPoolMan();
			}
		} catch (Exception e) {
			log.error("getDS() - can't init DataSource.", e);
		}
		return false;
	}

	/**
	 * ��ʼ��Tomcat����ȡ����Դ����Ҫ��ϵͳ�����ļ��ж������²����� <br>
	 * DataSource.ds.lookup
	 */
	private static synchronized boolean initDS() {
		//�����ʼ��������
		Context initCtx = null;
		//��ʼ��Context
		try {
			initCtx = new InitialContext();
			ds = (DataSource) initCtx.lookup(PropertyManager
					.getProperty("DataSource.ds.lookup"));
			if (ds == null) {
				log.debug("initDS() - Get DataSource fail. return is null.");
				return false;
			}
			log.info("initDS() - Get DataSource success.");
			return true;
		} catch (NamingException e) {
			log.debug("initDS() - NamingException error: " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * ��ʼ��ֱ�����ݿ�������Ҫ��ϵͳ�����ļ��ж������²����� <br>
	 * 
	 * <pre>
	 * database.driver
	 * </pre>
	 */
	private static synchronized boolean initDirectConnect() {
		log.debug("initDirectConnect() - Load JDBC driver ......");
		try {
			Class.forName(PropertyManager.getProperty("database.driver"))
					.newInstance();
		} catch (InstantiationException e) {
			log.error("initDirectConnect() - InstantiationException error: "
					+ e.getMessage(), e);
			return false;
		} catch (IllegalAccessException e) {
			log.error("initDirectConnect() - IllegalAccessException error: "
					+ e.getMessage(), e);
			return false;
		} catch (ClassNotFoundException e) {
			log.error("initDirectConnect() - ClassNotFoundException error: "
					+ e.getMessage(), e);
			return false;
		}
		log.debug("initDirectConnect() - success.");
		return true;
	}

	/**
	 * ��ʼ��PoolMan
	 * 
	 * @return �ɹ���־
	 */
	private static synchronized boolean initPoolMan() {
		log.debug("����PoolMan��");
		try {
			Class.forName("javax.transaction.xa.XAException");
		} catch (Exception e) {
			log.error("û���ҵ�javax.transaction.xa.XAException��"
					+ "����ʹ��PoolMan����ȷ��JDK�汾��1.4����");
			return false;
		}
		String connDriver = PropertyManager.getProperty("poolman.driver");
		String connUrl = PropertyManager.getProperty("poolman.url");
		String connUsername = PropertyManager.getProperty("poolman.username");
		String connPassword = PropertyManager.getProperty("poolman.password");
		String poolmanName = PropertyManager.getProperty("poolman.name");
		String poolnameLogfile = PropertyManager.getProperty("poolman.logFile");
		boolean isPoolnameDebug = PropertyManager
				.getBooleanProperty("poolman.debug");
		int nMinimumSize = PropertyManager
				.getIntProperty("poolman.minimumSize");
		int nMaximumSize = PropertyManager
				.getIntProperty("poolman.maximumSize");
		try {
			JDBCPoolMetaData meta = new JDBCPoolMetaData();

			meta.setDriver(connDriver);
			meta.setURL(connUrl);
			meta.setUserName(connUsername);
			meta.setPassword(connPassword);
			meta.setMinimumSize(nMinimumSize);
			meta.setMaximumSize(nMaximumSize);
			meta.setLogFile(poolnameLogfile);
			meta.setDebugging(isPoolnameDebug);
			JDBCPool newPool = new JDBCPool(meta);
			SQLManager.getInstance().addPool(poolmanName, newPool);

			log.debug("�ɹ�");
			return true;
		} catch (Exception e) {
			log.debug("ʧ�ܣ�", e);
			return false;
		}
	}

	/**
	 * �����ӳ��л�ȡ���ݿ�����
	 * 
	 * @exception SQLException
	 *                ����ʧ��ʱ�׳�SQLException
	 */
	public Connection getConn() throws SQLException {
		try {
			if (!isInited) {
				return null;
			}
			if (connectMethod.equalsIgnoreCase("direct")) {
				//log.debug("getConn() - Connect method is direct.");
				String connUrl = PropertyManager.getProperty("database.url");
				String connUsername = PropertyManager
						.getProperty("database.username");
				String connPassword = PropertyManager
						.getProperty("database.password");
				return DriverManager.getConnection(connUrl, connUsername,
						connPassword);
			}
			if (connectMethod.equalsIgnoreCase("poolman")) {
				log.debug("poolman");
				SQLManager manager = SQLManager.getInstance();
				return manager.requestConnection(PropertyManager
						.getProperty("poolman.name"));
			}
			//log.debug("getConn() - connect to DataSource ...");
			if (ds == null)
				isInited = getDS();
			return ds.getConnection();
		} catch (Exception sqle) {
			log
					.error("getConn() - Exception error: " + sqle.getMessage(),
							sqle);
			throw new SQLException(
					"getConn() - Navy throws this Execption from com.idn.sql.SQLBean.executQuery(String): "
							+ sqle.getMessage(), "execute sql error", -9001);

		}
	}

	/**
	 * ִ�о�̬SQL���
	 * 
	 * @param sql
	 *            Ҫִ�е�SQL���
	 * @return int SQL���Ӱ�������
	 * @exception SQLException
	 *                ����ʧ�ܡ�ִ�д���ʱ���׳��ô���
	 */
	public int executeSQL(String sql) throws SQLException {
		Connection _conn = null;
		Statement _stmt = null;
		boolean _isAutoCommit = true;
		int _iRtn;
		long _lltime = System.currentTimeMillis();
		String _strTime = Long.toString(_lltime);
		try {
			_conn = getConn();
			log.debug(_strTime
					+ " - executeSQL(String) - getConnect() time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_lltime = System.currentTimeMillis();
			_isAutoCommit = _conn.getAutoCommit();
			//log.debug("executeSQL(String) - AutoCommit is: " + isAutoCommit);
			//ִ��SQL
			_conn.setAutoCommit(false);
			_stmt = _conn.createStatement();
			log.debug(_strTime + " - executeSQL(String) - run sql statement: "
					+ sql);
			_stmt.executeUpdate(sql);
			//����Ӱ�������
			log.debug(_strTime
					+ " - executeSQL(String) - execut sql time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_iRtn = _stmt.getUpdateCount();
			_conn.commit();

			return _iRtn;
		} catch (SQLException sqle) {
			log.error(_strTime + " - executeSQL(String) - SQLExection error: "
					+ sqle.getMessage(), sqle);
			_conn.rollback();
			throw new SQLException(sqle.getMessage(), sqle.getSQLState(), sqle
					.getErrorCode());
		} catch (Exception sqle) {
			_conn.rollback();
			log.error(_strTime + " - executeSQL(String) - SQLExection error: "
					+ sqle.getMessage(), sqle);
			throw new SQLException(
					"Navy throws this Execption from com.idn.sql.SQLBean.executQuery(String): "
							+ sqle.getMessage(), "execute sql error", -9001);
		} finally {
			//�Զ��ر�����
			if (_stmt != null)
				_stmt.close();
			if (_conn != null) {
				_conn.setAutoCommit(_isAutoCommit);
				_conn.close();
			}
		}
	}

	/**
	 * ����ִ�о�̬SQL���
	 * 
	 * @param sql[]
	 *            Ҫִ�е�SQL�������ɵ�����
	 * @return int[] ÿ��SQL���Ӱ�����������ɵ�����
	 * @exception SQLException
	 *                ����ʧ�ܡ�ִ�д���ʱ���׳��ô���
	 * @deprecated
	 */
	public int[] executeSQL(String[] sql) throws BatchUpdateException,
			SQLException {
		Connection _conn = null;
		Statement _stmt = null;
		boolean _isAutoCommit = true;
		int _iRtn[];
		long _lltime = System.currentTimeMillis();
		String _strTime = Long.toString(_lltime);
		try {
			_conn = getConn();
			log.debug(_strTime
					+ " - executeSQL(String[]) - getConnect() time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_lltime = System.currentTimeMillis();
			_isAutoCommit = _conn.getAutoCommit();
			//log.debug("executeSQL(String[]) - AutoCommit is " +
			// isAutoCommit);
			//ִ��SQL
			_conn.setAutoCommit(false);
			_stmt = _conn.createStatement();
			for (int i = 0; i < sql.length; i++) {
				log.debug(_strTime
						+ " - executeSQL(String[]) - Adding SQL to Batch: "
						+ sql[i]);
				_stmt.addBatch(sql[i]);
			}
			_iRtn = _stmt.executeBatch();
			log.debug(_strTime
					+ " - executeSQL(String[]) - execut sqls time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_conn.commit();
			return _iRtn;
		} catch (BatchUpdateException be) {
			if (_conn != null)
				_conn.rollback();
			log.error(_strTime
					+ " - executeSQL(String[]) - BatchUpdateExecption error: "
					+ be.getMessage(), be);
			throw new BatchUpdateException(be.getMessage(), be.getSQLState(),
					be.getErrorCode(), be.getUpdateCounts());
		} catch (SQLException sqle) {
			log.error(_strTime
					+ " - executeSQL(String[]) - SQLException error: "
					+ sqle.getMessage(), sqle);
			_conn.rollback();
			throw new SQLException(sqle.getMessage(), sqle.getSQLState(), sqle
					.getErrorCode());
		} catch (Exception sqle) {
			log.error(_strTime
					+ " - executeSQL(String[]) - Exception error: "
					+ sqle.getMessage(), sqle);
			_conn.rollback();
			throw new SQLException(
					"Navy throws this Execption from com.idn.sql.SQLBean.executQuery(String): "
							+ sqle.getMessage(), "execute sql error", -9001);
		} finally {
			//�Զ��ر�����
			if (_stmt != null)
				_stmt.close();
			if (_conn != null) {
				_conn.setAutoCommit(_isAutoCommit);
				_conn.close();
			}
		}
	}

	/**
	 * ִ�о�̬SQL��ѯ���
	 * 
	 * @param sql
	 *            Ҫִ�еĲ�ѯSQL���
	 * @return CachedRowSet �Բ�ѯ������Ļ��棬���Զ����������ResultSet�Ĳ����� ֧�ֶԽ�����ĸ��¡��޸ĺ�ɾ��
	 * @exception SQLException
	 *                ����ʧ�ܡ�ִ�д���ʱ���׳��ô���
	 */
	public CachedRowSet executeQuery(String sql) throws SQLException {
		Connection _conn = null;
		Statement _stmt = null;
		ResultSet _rs = null;
		CachedRowSet _crs = null;
		boolean _isAutoCommit = true;
		long _lltime = System.currentTimeMillis();
		String _strTime = Long.toString(_lltime);
		try {
			_conn = getConn();
			log.debug(_strTime
					+ " - executeQuery(String) - getConnect() time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_lltime = System.currentTimeMillis();
			//ִ�в�ѯ
			_isAutoCommit = _conn.getAutoCommit();
			//log.debug("executeQuery(String) - AutoCommit is " +
			// isAutoCommit);
			//query doesn't need commit on AS400
			//conn.setAutoCommit(false);
			log.debug(_strTime
					+ " - executeQuery(String) - execute sql statement: " + sql);
			_stmt = _conn.createStatement();
			_stmt.setMaxRows(this.m_returnMaxRows);
			_rs = _stmt.executeQuery(sql);
			log.debug(_strTime
					+ " - executeQuery(String) - execute sql time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_lltime = System.currentTimeMillis();
			//�����ѯ�Ľ����
			_crs = new CachedRowSet();
			_crs.populate(_rs);
			log.debug(_strTime
					+ " - executeQuery(String) - cached all data time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_lltime = System.currentTimeMillis();
			//�رս����������
			//conn.commit();
			return _crs;
		} catch (SQLException sqle) {
			log.error(_strTime
					+ " - executeQuery(String) - SQLException error: "
					+ sqle.getMessage(), sqle);
			//conn.rollback();
			throw new SQLException(sqle.getMessage(), sqle.getSQLState(), sqle
					.getErrorCode());
		} catch (Exception sqle) {
			log.error(_strTime
					+ " - executeQuery(String) - Exception error: "
					+ sqle.getMessage(), sqle);
			//conn.rollback();
			throw new SQLException(
					"Navy throws this Execption from com.idn.sql.SQLBean.executQuery(String): "
							+ sqle.getMessage(), "execute sql error", -9001);
		} finally {
			//�ر�����
			if (_rs != null)
				_rs.close();
			if (_stmt != null)
				_stmt.close();
			if (_conn != null) {
				//conn.setAutoCommit(isAutoCommit);
				_conn.close();
			}
			initReturnMaxRows();
		}
	}

	/**
	 * �ͷ���Դ
	 */
	public void destory() {
		ds = null;
		log = null;
		isInited = false;
	}

	/**
	 * @return ��������Դ
	 */
	public static DataSource getDs() {
		return ds;
	}

	/**
	 * @return �����Ƿ��ʼ����־
	 */
	public static boolean isInited() {
		return isInited;
	}

	/**
	 * @param source
	 */
	public static void setDs(DataSource source) {
		ds = source;
	}

	/**
	 * @param b
	 */
	public static void setInited(boolean b) {
		isInited = b;
	}

	/**
	 * @return �������ݿ����ӷ�������
	 */
	public static String getConnectMethod() {
		return connectMethod;
	}

	/**
	 * @return
	 */
	public int getReturnMaxRows() {
		return this.m_returnMaxRows;
	}

	/**
	 * @param i
	 */
	public void setReturnMaxRows(int i) {
		this.m_returnMaxRows = i;
	}

	/**
	 * init return max rows form db
	 */
	private void initReturnMaxRows() {
		try {
			this.m_returnMaxRows = PropertyManager
					.getIntProperty("db.retrun.maxRows");
		} catch (Exception e) {
			log
					.warn("Can't find key 'db.retrun.maxRows'. Set default value is 10000. Error message: "
							+ e.getMessage());
			this.m_returnMaxRows = 10000;
		}
	}

}
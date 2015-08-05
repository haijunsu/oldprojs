/*
 * @(#)DynaSql.java  2003-3-31
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.sql;

import java.io.Serializable;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.idn.property.PropertyManager;

import sun.jdbc.rowset.CachedRowSet;

/**
 * <P>
 * 执行动态SQL语句
 * </P>
 * 
 * @version 0.1
 * @author 苏海军
 */
public class DynaSqlBean implements Serializable {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log = new com.idn.log.LogWrapper(
			DynaSqlBean.class);

	/**
	 * 要执行的SQL语句
	 */
	private String[] m_sql = null;

	/**
	 * SQL语句的参数值数组
	 */
	private String[] m_param = null;

	/**
	 * SQL语句的参数值数组
	 */
	private String[][] m_batchParam = null;

	/**
	 * 查询语句中返回的最大行数。“0”表示没有限制
	 */
	private int m_returnMaxRows = 10000;

	/**
	 * Constructor for DynaSql.
	 */
	public DynaSqlBean() {
		super();
		this.m_sql = null;
		this.m_param = null;
		initReturnMaxRows();

	}

	/**
	 * 通过SQLBean获取数据库连接
	 * 
	 * @return Connection
	 * @throws SQLException -
	 *             当取得数据连接失败时抛出
	 */
	private Connection getConn() throws SQLException {
		SQLBean _sqlbean = new SQLBean();
		return _sqlbean.getConn();

	}

	/**
	 * 执行动态查询，并将结果集用CachedRowSet缓存
	 * 
	 * @throws SQLException
	 *             当查询失败时抛出
	 * @return 返回查询的结果集
	 */
	public CachedRowSet executeQuery() throws SQLException {

		Connection _conn = null;
		PreparedStatement _pstmt = null;
		long _lltime = System.currentTimeMillis();
		String _strTime = Long.toString(_lltime);
		try {
			_conn = getConn();
			log.debug(_strTime + " - executeQuery() - "
					+ "getConnect() time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_lltime = System.currentTimeMillis();
			log.debug(_strTime + " - executeQuery() - execute sql statement: "
					+ this.m_sql[0]);
			_pstmt = _conn.prepareStatement(this.m_sql[0]);
			//给参数赋值
			if (this.m_param != null) {
				for (int i = 0; i < this.m_param.length; i++) {
					if (this.m_param[i] == null) {
						_pstmt.setNull(i + 1, Types.OTHER);
					} else {
						_pstmt.setObject(i + 1, this.m_param[i]);
					}
				}
			}
			CachedRowSet crs = new CachedRowSet();
			_pstmt.setMaxRows(this.m_returnMaxRows);
			_pstmt.executeQuery();
			log.debug(_strTime + " - executeQuery() - execute sql time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_lltime = System.currentTimeMillis();
			crs.populate(_pstmt.getResultSet());
			log.debug(_strTime
					+ " - executeQuery() - cached all data time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			return crs;
		} catch (SQLException sqle) {
			log.error(_strTime + " - executeQuery() - SQLException error: "
					+ sqle.getMessage(), sqle);
			throw new SQLException(sqle.getMessage(), sqle.getSQLState(), sqle
					.getErrorCode());
		} catch (Exception sqle) {
			log.error(_strTime + " - executeQuery() - Exception error: "
					+ sqle.getMessage(), sqle);
			throw new SQLException(
					"Navy throws exception when execute SQL statement."
							+ "mothed executeQuery(): " + sqle.getMessage(),
					"exceute sql", -9001);
		} finally {
			initReturnMaxRows();
			this.m_sql = null;
			this.m_param = null;
			try {
				if (_pstmt != null)
					_pstmt.close();
				if (_conn != null) {
					_conn.close();
				}
			} catch (SQLException e) {
				log.error(_strTime
						+ " - executeQuery() - Close database error!", e);
				if (_pstmt != null)
					_pstmt = null;
				if (_conn != null)
					_conn = null;
			}
		}
	}

	/**
	 * 执行动态SQL语名，成功返回影响的条数
	 * 
	 * @throws SQLException
	 *             当查询失败时抛出
	 * @return 影响的行数
	 */
	public int execute() throws SQLException {
		Connection _conn = null;
		PreparedStatement _pstmt = null;
		boolean _isAutoCommit = true;
		long _lltime = System.currentTimeMillis();
		String _strTime = Long.toString(_lltime);
		try {
			_conn = getConn();
			_isAutoCommit = _conn.getAutoCommit();
			log.debug(_strTime + "execute() - getConnect() time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_lltime = System.currentTimeMillis();
			_conn.setAutoCommit(false);
			log.debug(_strTime + "execute() - execute sql statement: "
					+ this.m_sql[0]);
			_pstmt = _conn.prepareStatement(this.m_sql[0]);
			int iRtn;
			//给参数赋值
			if (this.m_param != null) {
				for (int i = 0; i < this.m_param.length; i++) {
					if (this.m_param[i] == null) {
						_pstmt.setNull(i + 1, Types.OTHER);
					} else {
						_pstmt.setObject(i + 1, this.m_param[i]);
					}
				}
			}
			iRtn = _pstmt.executeUpdate();
			_conn.commit();
			log.debug(_strTime + "execute() - execute sql time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			return iRtn;
		} catch (SQLException sqle) {
			if (_conn != null)
				_conn.rollback();
			log.error(_strTime + "execute() - SQLExceition error: "
					+ sqle.getMessage(), sqle);
			throw new SQLException(sqle.getMessage(), sqle.getSQLState(), sqle
					.getErrorCode());
		} catch (Exception sqle) {
			if (_conn != null)
				_conn.rollback();
			log.error(_strTime + "execute() - Exception error: "
					+ sqle.getMessage(), sqle);
			throw new SQLException(
					"Navy throws exception when execute SQL statement."
							+ "mothed execute(): " + sqle.getMessage(),
					"exceute sql", 0);
		} finally {
			try {
				this.m_sql = null;
				this.m_param = null;
				if (_pstmt != null)
					_pstmt.close();
				if (_conn != null) {
					_conn.setAutoCommit(_isAutoCommit);
					_conn.close();
				}
			} catch (SQLException e) {
				log.error(_strTime + "execute() - Close database error!", e);
				if (_pstmt != null)
					_pstmt = null;
				if (_conn != null)
					_conn = null;
			}
		}
	}

	/**
	 * 执行动态SQL语名，成功返回true
	 * 
	 * @throws SQLException
	 *             当执行SQL失败时抛出
	 * @return 成功标志
	 */
	public boolean isExecuteSuccess() {
		try {
			execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * 执行批SQL语名，成功返回影响的条数的整型数组，如果 为多条不同的SQL，则batchParam必须为null，否则仅执行第一 条SQL语句
	 * <br>
	 * 
	 * 对于第一条SQL语句为影响的行数为0的SQL批处理，执行将失败但不抛出任何异常。
	 * 可以通过返回数组的长度来判断，如果为零，则失败。也可以用isExecuteBatchSuccess()
	 * 函数来替代此函数，根据返回的类型判断是否成功。 <br>
	 * 另一种替代方法是运行executeArrayBatch()，使其正常执行后面的语句，事务成功。
	 * 
	 * 
	 * @throws SQLException
	 *             当执行SQL失败时抛出
	 * @throws BatchUpdateException
	 *             批处理执行异常时抛出
	 * @return 影响的行数
	 */
	public int[] executeBatch() throws Exception {
		Connection _conn = null;
		PreparedStatement _pstmt = null;
		Statement _stmt = null;
		boolean _isAutoCommit = true;
		int[] _iRtn = null;
		long _lltime = System.currentTimeMillis();
		String _strTime = Long.toString(_lltime);
		try {
			_conn = getConn();
			_isAutoCommit = _conn.getAutoCommit();
			log.debug(_strTime + " - executeBatch() - getConnect() time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_lltime = System.currentTimeMillis();
			_conn.setAutoCommit(false);
			if (this.m_batchParam == null) {
				_stmt = _conn.createStatement();
				_iRtn = new int[this.m_sql.length];
				for (int i = 0; i < this.m_sql.length; i++) {
					log.debug(_strTime
							+ " - executeBatch() - execute sql statement[" + i
							+ "]: " + this.m_sql[i]);
					_iRtn[i] = _stmt.executeUpdate(this.m_sql[i]);
					log.debug(_strTime + " - executeBatch() - excecute sql ["
							+ i + "] time(ms): "
							+ (System.currentTimeMillis() - _lltime));
					_lltime = System.currentTimeMillis();
				}
				_stmt.close();
				//				stmt = conn.createStatement();
				//				for (int i = 0; i < sql.length; i++) {
				//					log.debug(sql[i]);
				//					stmt.addBatch(sql[i]);
				//				}
				//				iRtn = stmt.executeBatch();
				//				stmt.close();
			} else {
				log.debug(_strTime
						+ " - executeBatch() - execute sql statement: "
						+ this.m_sql[0]);
				_pstmt = _conn.prepareStatement(this.m_sql[0]);
				_iRtn = new int[this.m_batchParam.length];
				for (int i = 0; i < this.m_batchParam.length; i++) {
					for (int j = 0; j < this.m_batchParam[i].length; j++) {
						if (this.m_batchParam[i][j] == null) {
							_pstmt.setNull(j + 1, Types.OTHER);
						} else {
							_pstmt.setObject(j + 1, this.m_batchParam[i][j]);
						}
					}
					_iRtn[i] = _pstmt.executeUpdate();
				}
				_pstmt.close();
				log.debug(_strTime + " - executeBatch() - excecute total "
						+ this.m_batchParam.length + " sql(s) time(ms): "
						+ (System.currentTimeMillis() - _lltime));
				_lltime = System.currentTimeMillis();
			}
			_conn.commit();
			return _iRtn;
		} catch (BatchUpdateException be) {
			if (_conn != null)
				_conn.rollback();
			log.error(_strTime
					+ " - executeBatch() - BatchUpdateExecption error: "
					+ be.getMessage(), be);
			throw new BatchUpdateException(be.getMessage(), be.getSQLState(),
					be.getErrorCode(), be.getUpdateCounts());

		} catch (SQLException sqle) {
			if (_conn != null)
				_conn.rollback();
			log.error(_strTime + " - executeBatch() - SQLExecption error: "
					+ sqle.getMessage(), sqle);
			throw new SQLException(sqle.getMessage(), sqle.getSQLState(), sqle
					.getErrorCode());
		} catch (Exception ex) {
			if (_conn != null)
				_conn.rollback();
			log.error(_strTime + " - executeBatch() - Execption error: "
					+ ex.getMessage(), ex);
			throw new Exception(ex.getMessage());
		} finally {
			try {
				this.m_sql = null;
				this.m_batchParam = null;
				if (_stmt != null)
					_stmt.close();
				if (_pstmt != null)
					_pstmt.close();
				if (_conn != null) {
					_conn.setAutoCommit(_isAutoCommit);
					_conn.close();
				}
			} catch (SQLException e) {
				log.debug(_strTime
						+ " - executeBatch() - Close database error!", e);
				if (_stmt != null)
					_stmt = null;
				if (_pstmt != null)
					_pstmt = null;
				if (_conn != null)
					_conn = null;
			}
		}
	}

	/**
	 * 执行批SQL语句，成功返回true
	 * 
	 * @throws SQLException
	 *             当执行SQL失败时抛出
	 * @return 成功标志
	 */
	public boolean isExecuteBatchSuccess() {
		try {
			int[] nRtn = executeBatch();
			if (nRtn.length == 0) {
				log
						.warn("isExecuteBatchSuccess() - Warnning: return effect row is 0. Please check sql statement.");
				return false;
			}
			return true;
		} catch (BatchUpdateException e) {
			return false;
		} catch (SQLException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 执行批处理参数数组与SQL语句数组大小相同的批SQL，每条SQL语句的参数个数可以不同。 如果批处理参数为null，则按静态SQL数组处理
	 * 
	 * @return 执行成功后所影响的行数
	 * @throws SQLException
	 *             当执行错误时将抛出此异常
	 */
	public int[] executeArrayBatch() throws Exception {
		int[] _iRtn = new int[this.m_sql.length];
		Connection _conn = null;
		PreparedStatement[] _pstmt = null;
		boolean _isAutoCommit = true;
		long _lltime = System.currentTimeMillis();
		String _strTime = Long.toString(_lltime);
		try {
			_conn = getConn();
			_isAutoCommit = _conn.getAutoCommit();
			log.debug(_strTime
					+ " - executeArrayBatch() - getConnect() time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_lltime = System.currentTimeMillis();
			_conn.setAutoCommit(false);
			_pstmt = new PreparedStatement[this.m_sql.length];
			for (int i = 0; i < _pstmt.length; i++) {
				log.debug(_strTime
						+ " - executeArrayBatch() - run sql statement[" + i
						+ "]: " + this.m_sql[i]);
				_pstmt[i] = _conn.prepareStatement(this.m_sql[i]);
			}
			if (this.m_batchParam != null) {
				for (int i = 0; i < this.m_batchParam.length; i++) {
					for (int j = 0; j < this.m_batchParam[i].length; j++) {
						if (this.m_batchParam[i][j] == null) {
							break;
						}
						_pstmt[i].setObject(j + 1, this.m_batchParam[i][j]);
					}
				}
			}
			log.debug(_strTime
					+ " - executeArrayBatch() - prepare statement time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_lltime = System.currentTimeMillis();
			for (int i = 0; i < _pstmt.length; i++) {
				try {
					_iRtn[i] = _pstmt[i].executeUpdate();
					_pstmt[i].close();
				} catch (NullPointerException nulle) {
					_iRtn[i] = 0;
					if (_pstmt[i] != null)
						_pstmt[i].close();
				}
				log.debug(_strTime
						+ " - executeArrayBatch() - execute statement[" + i
						+ "] time(ms): "
						+ (System.currentTimeMillis() - _lltime)
						+ "return effect rows: " + _iRtn[i]);
				_lltime = System.currentTimeMillis();
			}
			_conn.commit();
			return _iRtn;
		} catch (SQLException sqle) {
			try {
				if (_conn != null)
					_conn.rollback();
			} catch (SQLException e) {
				log.error(_strTime
						+ " - executeArrayBatch() - SQLException sub error: "
						+ e.getMessage(), e);
			}
			log.error(_strTime
					+ " - executeArrayBatch() - SQLException error: "
					+ sqle.getMessage(), sqle);
			throw new SQLException(sqle.getMessage(), sqle.getSQLState(), sqle
					.getErrorCode());

		} catch (Exception sqle) {
			if (_conn != null)
				_conn.rollback();
			log.error(_strTime + " - executeArrayBatch() - Exception error: "
					+ sqle.getMessage(), sqle);
			throw new Exception(sqle.getMessage());

		} finally {
			try {
				this.m_sql = null;
				this.m_batchParam = null;
				if (_pstmt != null) {
					for (int i = 0; i < _pstmt.length; i++) {
						if (_pstmt[i] != null)
							_pstmt[i].close();
					}
				}
				_pstmt = null;
				if (_conn != null) {
					_conn.setAutoCommit(_isAutoCommit);
					_conn.close();
				}
			} catch (Exception e) {
				log.error(_strTime
						+ " - executeArrayBatch() - Close database error!", e);
				if (_pstmt != null)
					_pstmt = null;
				if (_conn != null)
					_conn = null;
			}
		}
	}

	/**
	 * 执行批处理参数数组与SQL语句数组大小相同的批SQL
	 * 
	 * @return 执行是否成功的标志
	 */
	public boolean isExecuteArrayBatchSuccess() {
		try {
			executeArrayBatch();
			return true;
		} catch (SQLException sqle) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Returns the sql.
	 * 
	 * @return String
	 */
	public String[] getSql() {
		return this.m_sql;
	}

	/**
	 * Sets the sql.
	 * 
	 * @param sql
	 *            The sql to set
	 */
	public void setSql(String sql) {
		String[] str = new String[] { sql };
		this.m_sql = str;
	}

	/**
	 * Sets the sql.
	 * 
	 * @param sql
	 *            The sql to set
	 */
	public void setSql(String[] sql) {
		this.m_sql = sql;
	}

	/**
	 * Returns the param.
	 * 
	 * @return String[][]
	 */
	public String[] getParam() {
		return this.m_param;
	}

	/**
	 * Sets the param.
	 * 
	 * @param param
	 *            The param to set
	 */
	public void setParam(String[] param) {
		this.m_param = param;
	}

	/**
	 * Sets the param.
	 * 
	 * @param param
	 *            The param to set
	 */
	public void setParam(String param) {
		String[] str = new String[] { param };
		this.m_param = str;
	}

	/**
	 * Returns the returnMaxRows.
	 * 
	 * @return int
	 */
	public int getReturnMaxRows() {
		return this.m_returnMaxRows;
	}

	/**
	 * Sets the returnMaxRows.
	 * 
	 * @param returnMaxRows
	 *            The returnMaxRows to set
	 */
	public void setReturnMaxRows(int returnMaxRows) {
		this.m_returnMaxRows = returnMaxRows;
	}

	/**
	 * @return 批SQL语名的参数
	 */
	public String[][] getBatchParam() {
		return this.m_batchParam;
	}

	/**
	 * @param strings
	 */
	public void setBatchParam(String[][] strings) {
		this.m_batchParam = strings;
	}

	/**
	 * 执行批SQL语名，成功返回影响的条数的整型数组，如果 为多条不同的SQL，则batchParam必须为null，否则仅执行第一 条SQL语句
	 * <br>
	 * 
	 * 对于第一条SQL语句为影响的行数为0的SQL批处理，执行将失败但不抛出任何异常。
	 * 可以通过返回数组的长度来判断，如果为零，则失败。也可以用isExecuteBatchSuccess()
	 * 函数来替代此函数，根据返回的类型判断是否成功。 <br>
	 * 另一种替代方法是运行executeArrayBatch()，使其正常执行后面的语句，事务成功。
	 * 
	 * 
	 * @throws SQLException
	 *             当执行SQL失败时抛出
	 * @throws BatchUpdateException
	 *             批处理执行异常时抛出
	 * @return 执行结果的描述
	 * @deprecated
	 */
	public String executeBatch(String[] seq) throws SQLException {
		Connection _conn = null;
		PreparedStatement _pstmt = null;
		Statement _stmt = null;
		int _iSeq = 0;
		boolean _isAutoCommit = true;
		long _lltime = System.currentTimeMillis();
		String _strTime = Long.toString(_lltime);
		try {
			_conn = getConn();
			log.debug(_strTime
					+ " - executeBatch(String[]) - getConnect() time(ms): "
					+ (System.currentTimeMillis() - _lltime));
			_lltime = System.currentTimeMillis();
			_isAutoCommit = _conn.getAutoCommit();
			_conn.setAutoCommit(false);
			_stmt = _conn.createStatement();
			for (int i = 0; i < seq.length; i++) {
				_iSeq++;
				log.debug(_strTime + " - executeBatch(String[]) - execut sql["
						+ i + "]: " + seq[i]);
				_stmt.executeUpdate(seq[i]);
				log.debug(_strTime + " - executeBatch(String[]) - execut sql["
						+ i + "] time(ms): "
						+ (System.currentTimeMillis() - _lltime));
				_lltime = System.currentTimeMillis();
			}
			_stmt.close();
			_conn.commit();
			return "Run sql success.";

		} catch (Exception sqle) {
			if (_conn != null)
				_conn.rollback();
			log.error(_strTime
					+ " - executeBatch(String[]) - Exception error: "
					+ sqle.getMessage());
			return String.valueOf(_iSeq + 1) + " sql statement is error: "
					+ sqle.getMessage();
		} finally {
			try {
				if (_stmt != null)
					_stmt.close();
				if (_pstmt != null)
					_pstmt.close();
				if (_conn != null) {
					_conn.setAutoCommit(_isAutoCommit);
					_conn.close();
				}
			} catch (SQLException e) {
				log.debug(_strTime
						+ " - executeBatch(String[]) - Close database error!",
						e);
				if (_stmt != null)
					_stmt = null;
				if (_pstmt != null)
					_pstmt = null;
				if (_conn != null)
					_conn = null;
			}
		}
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
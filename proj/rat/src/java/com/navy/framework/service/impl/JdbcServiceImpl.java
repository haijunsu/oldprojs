/*
 * @(#)JdbcServiceImpl.java  2005-1-20
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header$
 * $Log$
 */
package com.navy.framework.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.core.CollectionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterDisposer;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;

import com.navy.framework.exception.ServiceException;
import com.navy.framework.service.JdbcService;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * provide jdbc datasource
 * </p>
 *
 * $Revision$
 *
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class JdbcServiceImpl extends BaseServiceImpl implements JdbcService {

	private JdbcTemplate m_jdbcTemplate;

	protected JdbcTemplate getJdbcTemplate() {
		return this.m_jdbcTemplate;
	}

	public void setDataSource(DataSource dataSource) {
		this.m_jdbcTemplate = new MyJdbcTempleate(dataSource);
	}

	private static class MyJdbcTempleate extends JdbcTemplate {
		MyJdbcTempleate(DataSource dataSource) {
			super(dataSource);
		}

		public List queryForList(String sql) throws DataAccessException {
			return (List) query(sql, new MyListResultSetExtractor());
		}

		public List queryForList(String sql, final Object[] args)
				throws DataAccessException {
			return (List) query(sql, new MyArgPreparedStatementSetter(args),
					new MyListResultSetExtractor());
		}

	}

	/**
	 * ResultSetExtractor implementation that returns an ArrayList of HashMaps.
	 */
	private static class MyListResultSetExtractor implements ResultSetExtractor {

		public Object extractData(ResultSet rs) throws SQLException {
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			List listOfRows = new ArrayList();
			while (rs.next()) {
				Map mapOfColValues = CollectionFactory
						.createLinkedMapIfPossible(numberOfColumns);
				for (int i = 1; i <= numberOfColumns; i++) {
					Object o = getJdbcObject(rs, i);
					mapOfColValues.put(rsmd.getColumnName(i), o);
				}
				listOfRows.add(mapOfColValues);
			}
			return listOfRows;
		}
	}

	/**
	 * Retrieve a standard JDBC object from a ResultSet using the getObject
	 * method. This method includes a "hack" to get around Oracle returning a
	 * non standard object for their TIMESTAMP datatype.
	 *
	 * @param rs
	 *            is the ResultSet holding the data
	 * @param index
	 *            is the column index
	 * @return the Object returned
	 */
	private static Object getJdbcObject(ResultSet rs, int index)
			throws SQLException {
		try {
			Object obj = rs.getObject(index);
			if (obj != null
					&& obj.getClass().getName().startsWith(
							"oracle.sql.TIMESTAMP")) {
				obj = rs.getTimestamp(index);
			}
			return obj;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Simple adapter for PreparedStatementSetter that applies a given array of
	 * arguments.
	 */
	private static class MyArgPreparedStatementSetter implements
			PreparedStatementSetter, ParameterDisposer {

		private final Object[] args;

		public MyArgPreparedStatementSetter(Object[] args) {
			this.args = args;
		}

		public void setValues(PreparedStatement ps) throws SQLException {
			if (this.args != null) {
				for (int i = 0; i < this.args.length; i++) {
					StatementCreatorUtils.setParameterValue(ps, i + 1,
							SqlTypeValue.TYPE_UNKNOWN, null, this.args[i]);
				}
			}
		}

		public void cleanupParameters() {
			StatementCreatorUtils.cleanupParameters(this.args);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.service.JdbcService#execute(java.lang.String)
	 */
	public void execute(String sql) throws ServiceException {
		try {
			getJdbcTemplate().execute(sql);
		} catch (Exception e) {
			handleException(e, "9100", sql);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.service.JdbcService#query(java.lang.String)
	 */
	public List query(String sql) throws ServiceException {
		List _list = null;
		try {
			_list = getJdbcTemplate().queryForList(sql);
		} catch (Exception e) {
			handleException(e, "9200", sql);
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.service.JdbcService#query(java.lang.String, int)
	 */
	public List query(String sql, int maxRows) throws ServiceException {
		List _list = null;
		try {
			getJdbcTemplate().setMaxRows(maxRows);
			_list = getJdbcTemplate().queryForList(sql);
		} catch (Exception e) {
			handleException(e, "9300", new Object[] { sql, "" + maxRows });
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.service.JdbcService#queryForInt(java.lang.String)
	 */
	public int queryForInt(String sql) throws ServiceException {
		int _iValue = 0;
		try {
			_iValue = getJdbcTemplate().queryForInt(sql);
		} catch (Exception e) {
			handleException(e, "9200", sql);
		}
		return _iValue;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.service.JdbcService#queryForLong(java.lang.String)
	 */
	public long queryForLong(String sql) throws ServiceException {
		long _lValue = 0;
		try {
			_lValue = getJdbcTemplate().queryForLong(sql);
		} catch (Exception e) {
			handleException(e, "9200", sql);
		}
		return _lValue;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.service.JdbcService#queryForObject(java.lang.String,
	 *      java.lang.Class)
	 */
	public Object queryForObject(String sql, Class object)
			throws ServiceException {
		Object _obj = null;
		try {
			_obj = getJdbcTemplate().queryForObject(sql, object);
		} catch (Exception e) {
			handleException(e, "9200", sql);
		}
		return _obj;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.service.JdbcService#executeBatch(java.lang.String[])
	 */
	public int[] updateBatch(String[] sqls) throws ServiceException {
		int[] _iRtns = null;
		try {
			_iRtns = getJdbcTemplate().batchUpdate(sqls);
		} catch (Exception e) {
			handleException(e, "9500", Arrays.toString(sqls));
		}
		return _iRtns;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.service.JdbcService#executeUpdate(java.lang.String)
	 */
	public int update(String sql) throws ServiceException {
		int _iRtn = 0;
		try {
			_iRtn = getJdbcTemplate().update(sql);
		} catch (Exception e) {
			handleException(e, "9400", sql);
		}
		return _iRtn;
	}
}

/*
 * @(#)BaseDaoImpl.java  2005-1-5
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header: /navysu/Smirt18/src/framework/dao/impl/BaseDaoImpl.java,v 1.1 2005/01/20 03:14:28 navy Exp $
 * $Log: BaseDaoImpl.java,v $
 * Revision 1.1  2005/01/20 03:14:28  navy
 * Create SMiRT 18 project
 *
 */
package com.navy.framework.dao.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.navy.framework.dao.DbTimeParser;
import com.navy.framework.dao.IDao;
import com.navy.framework.exception.DaoException;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * Every Dao implement should extend this class. All base method has been
 * implemented in this class
 * </p>
 *
 * $Revision: 1.1 $
 *
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public abstract class BaseDaoImpl extends GeneralDaoImpl implements IDao {

	/**
	 * Logger for this class
	 */
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(BaseDaoImpl.class);

	protected abstract Class getEntitiy();

	private DbTimeParser timeParser;

	public void setDbTimeParser(DbTimeParser dbTimeParser) {
		this.timeParser = dbTimeParser;
	}

	/**
	 *
	 * @return Database time
	 */

	protected Date now() {
		return timeParser.getDbTime();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#count(java.lang.String)
	 */
	public int count(String whereCaluse) throws DaoException {
		String _strHql = "SELECT count(*) FROM " + getEntitiy().getName();
		if (!StringUtils.isBlank(whereCaluse)) {
			_strHql += " WHERE " + whereCaluse;
		}
		return countByHQL(_strHql);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#count(java.lang.String, Object[])
	 */
	public int count(String whereCaluse, Object[] values) throws DaoException {
		String _strHql = "SELECT count(*) FROM " + getEntitiy().getName();
		if (!StringUtils.isBlank(whereCaluse)) {
			_strHql += " WHERE " + whereCaluse;
		}
		return countByHQL(_strHql, values);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#find(java.lang.String)
	 */
	public List find(String whereClause) throws DaoException {
		return findWithOrder(whereClause, (Object[]) null, null, -1, -1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#find(java.lang.String)
	 */
	public List find(String whereClause, Object value) throws DaoException {
		return findWithOrder(whereClause, new Object[] { value }, null, -1, -1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#find(java.lang.String)
	 */
	public List find(String whereClause, Object[] values) throws DaoException {
		return findWithOrder(whereClause, values, null, -1, -1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#find(java.lang.String, int, int)
	 */
	public List find(String whereClause, Object[] values, int startRow,
			int maxRows) throws DaoException {
		return findWithOrder(whereClause, values, null, startRow, maxRows);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#find(java.lang.String, java.lang.String)
	 */
	public List findWithOrder(String whereClause, String orderby)
			throws DaoException {
		return findWithOrder(whereClause, (Object[]) null, orderby, -1, -1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#find(java.lang.String, java.lang.String)
	 */
	public List findWithOrder(String whereClause, Object value, String orderby)
			throws DaoException {
		return findWithOrder(whereClause, new Object[] { value }, orderby, -1,
				-1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#find(java.lang.String, java.lang.String)
	 */
	public List findWithOrder(String whereClause, Object[] values,
			String orderby) throws DaoException {
		return findWithOrder(whereClause, values, orderby, -1, -1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#find(java.lang.String, java.lang.String,
	 *      int, int)
	 */
	public List findWithOrder(String whereClause, Object[] values,
			String orderby, int startRow, int maxRows) throws DaoException {
		List _list = null;
		try {
			String _strHql = " FROM " + getEntitiy().getName();
			if (!StringUtils.isBlank(whereClause)) {
				_strHql += " WHERE " + whereClause;
			}
			if (!StringUtils.isBlank(orderby)) {
				_strHql += " ORDER BY " + orderby;
			}
			_list = findByHQL(_strHql, values, startRow, maxRows);
		} catch (Exception e) {
			handleException(e, "1000", Arrays.toString(new Object[] {
					whereClause, orderby, new Integer(startRow),
					new Integer(maxRows) }));
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#listAll()
	 */
	public List listAll() throws DaoException {
		String _strHql = "FROM " + getEntitiy().getName();
		List _list = null;
		try {
			_list = findByHQL(_strHql);
		} catch (Exception e) {
			handleException(e, "1000", _strHql);
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#delete(java.lang.String)
	 */
	public void deleteByWhereClause(String whereClause) throws DaoException {
		deleteByWhereClause(whereClause, (Object[]) null);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#delete(java.lang.String)
	 */
	public void deleteByWhereClause(String whereClause, Object value)
			throws DaoException {
		deleteByWhereClause(whereClause, new Object[] { value });
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#delete(java.lang.String)
	 */
	public void deleteByWhereClause(String whereClause, Object[] values)
			throws DaoException {
		try {
			// getTemplete().deleteAll(find(whereClause));
			String _strHql = " DELETE " + getEntitiy().getName();
			if (!StringUtils.isBlank(whereClause)) {
				_strHql += " WHERE " + whereClause;
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Delete HQL: " + _strHql);
			}
			int _iRows = bulkUpdate(_strHql, values);
			if (logger.isDebugEnabled()) {
				logger.debug("Delete " + _iRows + " rows.");
			}
		} catch (Exception e) {
			handleException(e, "3100", whereClause);
		}

	}

	protected Object getById(Long id) throws DaoException {
		Object _obj = null;
		_obj = getObjectById(getEntitiy(), id);
		return _obj;
	}

	protected Object loadById(Long id) throws DaoException {
		Object _obj = null;
		_obj = loadObjectById(getEntitiy(), id);
		return _obj;
	}

}
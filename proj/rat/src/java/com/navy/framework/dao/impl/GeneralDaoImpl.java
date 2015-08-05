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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.navy.framework.dao.GeneralDao;
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
public class GeneralDaoImpl extends HibernateDaoSupport implements GeneralDao {

	/**
	 * Logger for this class
	 */
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(GeneralDaoImpl.class);

	/**
	 * max return results per query.
	 */
	private static final int MAX_RESULTS = 100000;

	/**
	 * get HibernateTemplate with maxResults value to avoid too many result
	 * return by sql.
	 *
	 * @return
	 */
	private HibernateTemplate getTemplete() {
		HibernateTemplate _template = getHibernateTemplate();
		_template.setMaxResults(MAX_RESULTS);
		return _template;
	}

	/**
	 * Apply the given name parameter to the given Query object.
	 *
	 * @param queryObject
	 *            the Query object
	 * @param paramName
	 *            the name of the parameter
	 * @param value
	 *            the value of the parameter
	 * @throws HibernateException
	 *             if thrown by the Query object
	 */
	private void applyNamedParameterToQuery(Query queryObject,
			String paramName, Object value) throws HibernateException {

		if (value instanceof Collection) {
			queryObject.setParameterList(paramName, (Collection) value);
		} else if (value instanceof Object[]) {
			queryObject.setParameterList(paramName, (Object[]) value);
		} else {
			queryObject.setParameter(paramName, value);
		}
	}

	protected void handleException(Exception e, String errCode, Object[] params)
			throws DaoException {
		logger.error("Exception: " + e.getMessage(), e);
		throw new DaoException(errCode, params);
	}

	protected void handleException(Exception e, String errCode, String param)
			throws DaoException {
		handleException(e, errCode, new Object[] { param });
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#countByHQL(java.lang.String)
	 */
	public int countByHQL(String hql) throws DaoException {
		return countByHQL(hql, null);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#countByHQL(java.lang.String)
	 */
	public int countByHQL(String hql, Object value) throws DaoException {
		return countByHQL(hql, new Object[] { value });
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#countByHQL(java.lang.String)
	 */
	public int countByHQL(final String hql, final Object[] values)
			throws DaoException {
		int _iCount = 0;
		if (logger.isDebugEnabled()) {
			logger.debug("HQL: " + hql);
		}
		try {
			Object _countObj = getTemplete().execute(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					Query _queryObject = session.createQuery(hql);
					if (values != null) {
						for (int i = 0; i < values.length; i++) {
							_queryObject.setParameter(i, values[i]);
						}
					}
					return _queryObject.iterate().next();
				}
			}, true);

			_iCount = Integer.parseInt(_countObj.toString());
		} catch (Exception e) {
			handleException(e, "1000", hql);
		}
		return _iCount;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByHQL(java.lang.String, int, int)
	 */
	public List findByHQL(String hql, int startRow, int maxRows)
			throws DaoException {
		return findByHQL(hql, null, startRow, maxRows);
	}

	public Serializable save(Object entity) throws DaoException {
		Serializable _serializable = null;
		try {
			_serializable = getTemplete().save(entity);
		} catch (Exception e) {
			handleException(e, "5000", new Object[] { entity });
		}
		return _serializable;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#update(java.lang.Object)
	 */
	public void update(Object entity) throws DaoException {
		try {
			getTemplete().saveOrUpdate(entity);
		} catch (Exception e) {
			handleException(e, "4000", new Object[] { entity });
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#delete(java.lang.Object)
	 */
	public void delete(Object entity) throws DaoException {
		try {
			getTemplete().delete(entity);
		} catch (Exception e) {
			handleException(e, "3000", new Object[] { entity });
		}

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

	public Object getObjectById(Class clazz, Serializable id)
			throws DaoException {
		Object _obj = null;
		try {
			_obj = getTemplete().get(clazz, id);
		} catch (Exception e) {
			handleException(e, "2000", new Object[] { id });
		}
		return _obj;
	}

	public Object loadObjectById(Class clazz, Serializable id)
			throws DaoException {
		Object _obj = null;
		try {
			_obj = getTemplete().load(clazz, id);
		} catch (Exception e) {
			handleException(e, "2100", new Object[] { id });
		}
		return _obj;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByHQL(java.lang.String)
	 */
	public List findByHQL(String hql) throws DaoException {
		return findByHQL(hql, null, -1, -1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByHQL(java.lang.String,
	 *      java.lang.Object)
	 */
	public List findByHQL(String hql, Object value) throws DaoException {
		return findByHQL(hql, new Object[] { value }, -1, -1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByHQL(java.lang.String,
	 *      java.lang.Object[])
	 */
	public List findByHQL(String hql, Object[] values) throws DaoException {
		return findByHQL(hql, values, -1, -1);
	}

	public List findByHQL(final String hql, final Object[] values,
			final int startRow, final int maxRows) throws DaoException {
		List _list = new ArrayList();
		if (logger.isDebugEnabled()) {
			logger
					.debug("HQL: " + hql + ", params: "
							+ Arrays.toString(values));
		}
		final int maxResults = maxRows > 0 ? maxRows : MAX_RESULTS;
		try {
			_list = getTemplete().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					Query _queryObject = session.createQuery(hql);
					if (values != null) {
						for (int i = 0; i < values.length; i++) {
							_queryObject.setParameter(i, values[i]);
						}
					}
					if (startRow >= 0) {
						_queryObject.setFirstResult(startRow);
					}
					if (maxResults > 0) {
						_queryObject.setMaxResults(maxResults);
					}
					return _queryObject.list();
				}
			});
		} catch (Exception e) {
			handleException(e, "1000", hql + ", param: "
					+ Arrays.toString(values));
		}
		return _list;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByNamedParam(java.lang.String,
	 *      java.lang.String, java.lang.Object)
	 */
	public List findByNamedParam(String hql, String paramName, Object value) {
		List _list = new ArrayList();
		try {
			_list = getTemplete().findByNamedParam(hql, paramName, value);
		} catch (Exception e) {
			handleException(e, "1000", hql + ", paramName: " + paramName
					+ ", value: " + value);
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByNamedParam(java.lang.String,
	 *      java.lang.String[], java.lang.Object[])
	 */
	public List findByNamedParam(String hql, String[] paramNames,
			Object[] values) {
		List _list = new ArrayList();
		try {
			_list = getTemplete().findByNamedParam(hql, paramNames, values);
		} catch (Exception e) {
			handleException(e, "1000", hql + ", paramNames: "
					+ Arrays.toString(paramNames) + ", values: "
					+ Arrays.toString(values));
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByNamedParam(java.lang.String,
	 *      java.lang.String[], java.lang.Object[])
	 */
	public List findByNamedParam(final String hql, final String[] paramNames,
			final Object[] values, final int startRow, final int maxRows) {

		List _list = new ArrayList();
		if (logger.isDebugEnabled()) {
			logger.debug("HQL: " + hql + ", params: "
					+ Arrays.toString(paramNames) + ", values: "
					+ Arrays.toString(values));
		}
		final int maxResults = maxRows > 0 ? maxRows : MAX_RESULTS;
		try {
			if (paramNames.length != values.length) {
				throw new IllegalArgumentException(
						"Length of paramNames array must match length of values array");
			}
			_list = getTemplete().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					Query _queryObject = session.createQuery(hql);

					if (values != null) {
						for (int i = 0; i < values.length; i++) {
							applyNamedParameterToQuery(_queryObject,
									paramNames[i], values[i]);
						}
					}
					if (startRow >= 0) {
						_queryObject.setFirstResult(startRow);
					}
					if (maxResults > 0) {
						_queryObject.setMaxResults(maxResults);
					}
					return _queryObject.list();
				}
			});
		} catch (Exception e) {
			handleException(e, "1000", hql + ", paramNames: "
					+ Arrays.toString(paramNames) + ", values: "
					+ Arrays.toString(values));
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByNamedQuery(java.lang.String)
	 */
	public List findByNamedQuery(String queryName) throws DaoException {
		List _list = new ArrayList();
		try {
			_list = getTemplete().findByNamedQuery(queryName);
		} catch (Exception e) {
			handleException(e, "1000", "QueryName: " + queryName);
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByNamedQuery(java.lang.String,
	 *      java.lang.Object)
	 */
	public List findByNamedQuery(String queryName, Object value)
			throws DaoException {
		List _list = new ArrayList();
		try {
			_list = getTemplete().findByNamedQuery(queryName, value);
		} catch (Exception e) {
			handleException(e, "1000", "QueryName: " + queryName + ", value: "
					+ value);
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByNamedQuery(java.lang.String,
	 *      java.lang.Object[])
	 */
	public List findByNamedQuery(String queryName, Object[] values)
			throws DaoException {
		List _list = new ArrayList();
		try {
			_list = getTemplete().findByNamedQuery(queryName, values);
		} catch (Exception e) {
			handleException(e, "1000", "QueryName: " + queryName + ", values: "
					+ Arrays.toString(values));
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByNamedQuery(java.lang.String,
	 *      java.lang.Object[])
	 */
	public List findByNamedQuery(final String queryName, final Object[] values,
			final int startRow, final int maxRows) throws DaoException {
		List _list = new ArrayList();
		if (logger.isDebugEnabled()) {
			logger.debug("HQL query name: " + queryName + ", params: "
					+ Arrays.toString(values));
		}
		final int maxResults = maxRows > 0 ? maxRows : MAX_RESULTS;
		try {
			_list = getTemplete().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					Query _queryObject = session.getNamedQuery(queryName);
					if (values != null) {
						for (int i = 0; i < values.length; i++) {
							_queryObject.setParameter(i, values[i]);
						}
					}
					if (startRow >= 0) {
						_queryObject.setFirstResult(startRow);
					}
					if (maxResults > 0) {
						_queryObject.setMaxResults(maxResults);
					}
					return _queryObject.list();
				}
			});
		} catch (Exception e) {
			handleException(e, "1000", "HQL query name: " + queryName
					+ ", param: " + Arrays.toString(values));
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByNamedQueryAndNamedParam(java.lang.String,
	 *      java.lang.String, java.lang.Object)
	 */
	public List findByNamedQueryAndNamedParam(String queryName,
			String paramName, Object value) throws DaoException {
		List _list = new ArrayList();
		try {
			_list = getTemplete().findByNamedQueryAndNamedParam(queryName,
					paramName, value);
		} catch (Exception e) {
			handleException(e, "1000", "QueryName: " + queryName
					+ ", paramName: " + paramName + ", value: " + value);
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByNamedQueryAndNamedParam(java.lang.String,
	 *      java.lang.String[], java.lang.Object[])
	 */
	public List findByNamedQueryAndNamedParam(String queryName,
			String[] paramNames, Object[] values) throws DaoException {
		List _list = new ArrayList();
		try {
			_list = getTemplete().findByNamedQueryAndNamedParam(queryName,
					paramNames, values);
		} catch (Exception e) {
			handleException(e, "1000", "QueryName: " + queryName
					+ ", paramNames: " + Arrays.toString(paramNames)
					+ ", values: " + Arrays.toString(values));
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByNamedQueryAndNamedParam(java.lang.String,
	 *      java.lang.String[], java.lang.Object[])
	 */
	public List findByNamedQueryAndNamedParam(final String queryName,
			final String[] paramNames, final Object[] values,
			final int startRow, final int maxRows) throws DaoException {
		List _list = new ArrayList();
		if (logger.isDebugEnabled()) {
			logger.debug("HQL query name: " + queryName + ", params: "
					+ Arrays.toString(paramNames) + ", values: "
					+ Arrays.toString(values));
		}
		final int maxResults = maxRows > 0 ? maxRows : MAX_RESULTS;
		try {
			if (paramNames.length != values.length) {
				throw new IllegalArgumentException(
						"Length of paramNames array must match length of values array");
			}
			_list = getTemplete().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					Query _queryObject = session.getNamedQuery(queryName);

					if (values != null) {
						for (int i = 0; i < values.length; i++) {
							applyNamedParameterToQuery(_queryObject,
									paramNames[i], values[i]);
						}
					}
					if (startRow >= 0) {
						_queryObject.setFirstResult(startRow);
					}
					if (maxResults > 0) {
						_queryObject.setMaxResults(maxResults);
					}
					return _queryObject.list();
				}
			});
		} catch (Exception e) {
			handleException(e, "1000", "HQL query name: " + queryName
					+ ", paramNames: " + Arrays.toString(paramNames)
					+ ", values: " + Arrays.toString(values));
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#bulkUpdate(java.lang.String)
	 */
	public int bulkUpdate(String hql) throws DaoException {
		int _iCount = 0;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("HQL: " + hql);
			}
			_iCount = getTemplete().bulkUpdate(hql);
		} catch (Exception e) {
			handleException(e, "6000", "HQL: " + hql);
		}
		return _iCount;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#bulkUpdate(java.lang.String,
	 *      java.lang.Object)
	 */
	public int bulkUpdate(String hql, Object value) throws DaoException {
		int _iCount = 0;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("HQL: " + hql);
			}
			_iCount = getTemplete().bulkUpdate(hql, value);
		} catch (Exception e) {
			handleException(e, "6000", "HQL: " + hql + ", paraValue: " + value);
		}
		return _iCount;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#bulkUpdate(java.lang.String,
	 *      java.lang.Object[])
	 */
	public int bulkUpdate(String hql, Object[] values) throws DaoException {
		int _iCount = 0;
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("HQL: " + hql);
			}
			_iCount = getTemplete().bulkUpdate(hql, values);
		} catch (Exception e) {
			handleException(e, "6000", "HQL: " + hql + ", paraValues: "
					+ values);
		}
		return _iCount;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#execute(org.springframework.orm.hibernate3.HibernateCallback,
	 *      boolean)
	 */
	public Object execute(HibernateCallback action, boolean exposeNativeSession)
			throws DaoException {
		Object _obj = null;
		try {
			_obj = getTemplete().execute(action, exposeNativeSession);
		} catch (Exception e) {
			handleException(e, "6100", (Object[]) null);
		}
		return _obj;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#execute(org.springframework.orm.hibernate3.HibernateCallback)
	 */
	public Object execute(HibernateCallback action) throws DaoException {
		Object _obj = null;
		try {
			_obj = getTemplete().execute(action);
		} catch (Exception e) {
			handleException(e, "6100", (Object[]) null);
		}
		return _obj;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#executeFind(org.springframework.orm.hibernate3.HibernateCallback)
	 */
	public List executeFind(HibernateCallback action) throws DaoException {
		List _list = null;
		try {
			_list = getTemplete().executeFind(action);
		} catch (Exception e) {
			handleException(e, "6100", (Object[]) null);
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	public List findByCriteria(DetachedCriteria criteria) throws DaoException {
		List _list = null;
		try {
			_list = getTemplete().findByCriteria(criteria);
		} catch (Exception e) {
			handleException(e, "1000", "Criteria: " + criteria.toString());
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByCriteria(org.hibernate.criterion.DetachedCriteria,
	 *      int, int)
	 */
	public List findByCriteria(DetachedCriteria criteria, int startRow,
			int maxRows) throws DaoException {
		List _list = null;
		try {
			_list = getTemplete().findByCriteria(criteria, startRow, maxRows);
		} catch (Exception e) {
			handleException(e, "1000", "Criteria: " + criteria.toString());
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByExample(java.lang.Object)
	 */
	public List findByExample(Object exampleEntity) throws DaoException {
		List _list = null;
		try {
			_list = getTemplete().findByExample(exampleEntity);
		} catch (Exception e) {
			handleException(e, "1000", "ExampleEntity: "
					+ exampleEntity.toString());
		}
		return _list;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.IDao#findByExample(java.lang.Object, int,
	 *      int)
	 */
	public List findByExample(Object exampleEntity, int startRow, int maxRows)
			throws DaoException {
		List _list = null;
		try {
			_list = getTemplete().findByExample(exampleEntity, startRow,
					maxRows);
		} catch (Exception e) {
			handleException(e, "1000", "ExampleEntity: "
					+ exampleEntity.toString());
		}
		return _list;
	}

}
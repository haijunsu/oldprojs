/*
 * @(#)GeneralDao.java  2007-1-7
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.navy.framework.exception.DaoException;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 */
public interface GeneralDao {

	public Serializable save(Object entity) throws DaoException;

	public void update(Object entity) throws DaoException;

	/**
	 * Remove from database which accord with the condition.
	 *
	 * @param entity
	 *            For remove entity
	 */
	public void delete(Object entity) throws DaoException;

	public Object getObjectById(Class clazz, Serializable id)
			throws DaoException;

	public Object loadObjectById(Class clazz, Serializable id)
			throws DaoException;

	/**
	 * Count the query result before query.
	 *
	 * @param hql
	 * @return
	 * @throws DaoException
	 */
	public int countByHQL(String hql) throws DaoException;

	/**
	 * Count the query result before query.
	 *
	 * @param hql
	 * @return
	 * @throws DaoException
	 */
	public int countByHQL(String hql, Object value) throws DaoException;

	/**
	 * Count the query result before query.
	 *
	 * @param hql
	 * @return
	 * @throws DaoException
	 */
	public int countByHQL(String hql, Object[] values) throws DaoException;

	/**
	 * Execute an HQL query.
	 *
	 * @param hql
	 * @return
	 * @throws DaoException
	 */
	public List findByHQL(String hql) throws DaoException;

	/**
	 * Execute an HQL query, binding one value to a "?"
	 *
	 * @param hql
	 * @param value
	 * @return
	 * @throws DaoException
	 */
	public List findByHQL(String hql, Object value) throws DaoException;

	/**
	 * Execute an HQL query, binding a number of values to "?"
	 *
	 * @param hql
	 * @param values
	 * @return
	 * @throws DaoException
	 */
	public List findByHQL(String hql, Object[] values) throws DaoException;

	/**
	 * Execute an HQL query, binding a number of values to "?".
	 *
	 * @param hql
	 * @param values
	 * @return
	 * @throws DaoException
	 */
	public List findByHQL(final String hql, final Object[] values,
			int startRow, int maxRows) throws DaoException;

	/**
	 * Execute an HQL query, binding a number of values to ":" named parameters
	 * in the query string.
	 *
	 * @param hql
	 * @param paramName
	 * @param value
	 * @return
	 */
	public List findByNamedParam(String hql, String paramName, Object value);

	/**
	 * Execute an HQL query, binding a number of values to ":" named parameters
	 * in the query string.
	 *
	 * @param hql
	 * @param paramNames
	 * @param values
	 * @return
	 */
	public List findByNamedParam(String hql, String[] paramNames,
			Object[] values);

	/**
	 * Execute an HQL query, binding a number of values to ":" named parameters
	 * in the query string.
	 *
	 * @param hql
	 * @param paramNames
	 * @param values
	 * @return
	 */
	public List findByNamedParam(String hql, String[] paramNames,
			Object[] values, int startRow, int maxRows);

	/**
	 * Execute a named query.
	 *
	 * @param queryName
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQuery(String queryName) throws DaoException;

	/**
	 * Execute a named query, binding one value to a "?"
	 *
	 * @param queryName
	 * @param value
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQuery(String queryName, Object value)
			throws DaoException;

	/**
	 * Execute a named query binding a number of values to "?"
	 *
	 * @param queryName
	 * @param values
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQuery(String queryName, Object[] values)
			throws DaoException;

	/**
	 * Execute a named query binding a number of values to "?"
	 *
	 * @param queryName
	 * @param values
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQuery(String queryName, Object[] values,
			int startRow, int maxRows) throws DaoException;

	/**
	 * Execute a named query, binding one value to a ":" named parameter in the
	 * query string.
	 *
	 * @param queryName
	 * @param paramName
	 * @param value
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQueryAndNamedParam(String queryName,
			String paramName, Object value) throws DaoException;

	/**
	 * Execute a named query, binding a number of values to ":" named parameters
	 * in the query string.
	 *
	 * @param queryName
	 * @param paramNames
	 * @param values
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQueryAndNamedParam(String queryName,
			String[] paramNames, Object[] values) throws DaoException;

	/**
	 * Execute a named query, binding a number of values to ":" named parameters
	 * in the query string.
	 *
	 * @param queryName
	 * @param paramNames
	 * @param values
	 * @return
	 * @throws DaoException
	 */
	public List findByNamedQueryAndNamedParam(String queryName,
			String[] paramNames, Object[] values, int startRow, int maxRows)
			throws DaoException;

	/**
	 * Update/delete all objects according to the given query.
	 *
	 * @param hql
	 * @return
	 * @throws DaoException
	 */
	public int bulkUpdate(String hql) throws DaoException;

	/**
	 * Update/delete all objects according to the given query.
	 *
	 * @param hql
	 * @param value
	 * @return
	 * @throws DaoException
	 */
	public int bulkUpdate(String hql, Object value) throws DaoException;

	/**
	 * Update/delete all objects according to the given query.
	 *
	 * @param hql
	 * @param values
	 * @return
	 * @throws DaoException
	 */
	public int bulkUpdate(String hql, Object[] values) throws DaoException;

	/**
	 * Execute the action specified by the given action object within a Session.
	 *
	 * @param action
	 *            callback object that specifies the Hibernate action
	 * @param exposeNativeSession
	 *            whether to expose the native Hibernate Session to callback
	 *            code
	 * @return a result object returned by the action, or <code>null</code>
	 * @throws DaoException
	 *             in case of Hibernate errors
	 */
	public Object execute(HibernateCallback action, boolean exposeNativeSession)
			throws DaoException;

	public Object execute(HibernateCallback action) throws DaoException;

	public List executeFind(HibernateCallback action) throws DaoException;

	/**
	 * Execute a query based on a given Hibernate criteria object.
	 *
	 * @param criteria
	 * @return
	 * @throws DaoException
	 */
	public List findByCriteria(DetachedCriteria criteria) throws DaoException;

	/**
	 * Execute a query based on a given Hibernate criteria object.
	 *
	 * @param criteria
	 * @return
	 * @throws DaoException
	 */
	public List findByCriteria(DetachedCriteria criteria, int startRow,
			int maxRows) throws DaoException;

	/**
	 * Execute a query based on the given example entity object.
	 *
	 * @param exampleEntity
	 * @return
	 * @throws DaoException
	 */
	public List findByExample(Object exampleEntity) throws DaoException;

	/**
	 * Execute a query based on the given example entity object.
	 *
	 * @param exampleEntity
	 * @return
	 * @throws DaoException
	 */
	public List findByExample(Object exampleEntity, int startRow, int maxRows)
			throws DaoException;

}

/*
 * @(#)IDao.java  2005-1-5
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header: /navysu/Smirt18/src/framework/dao/IDao.java,v 1.1 2005/01/20 03:14:24 navy Exp $
 * $Log: IDao.java,v $
 * Revision 1.1  2005/01/20 03:14:24  navy
 * Create SMiRT 18 project
 *
 */
package com.navy.framework.dao;

import java.util.List;

import com.navy.framework.exception.DaoException;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * Base dao. All business dao should extend this interface.
 * </p>
 *
 * $Revision: 1.1 $
 *
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface IDao {

	public void update(Object entity) throws DaoException;

	/**
	 * Remove from database which accord with the condition.
	 *
	 * @param entity
	 *            For remove entity
	 */
	public void delete(Object entity) throws DaoException;

	/**
	 * Remove from database which accord with the condition.
	 *
	 * @param whereClause
	 *            For remove entities condition
	 */
	public void deleteByWhereClause(String whereClause) throws DaoException;

	/**
	 * Remove from database which accord with the condition. binding one value
	 * to a "?"
	 *
	 * @param whereClause
	 *            For remove entities condition
	 */
	public void deleteByWhereClause(String whereClause, Object value)
			throws DaoException;

	/**
	 * Remove from database which accord with the condition. binding a number of
	 * valses to "?"
	 *
	 * @param whereClause
	 *            For remove entities condition
	 */
	public void deleteByWhereClause(String whereClause, Object[] values)
			throws DaoException;

	/**
	 * Find entities from database which accord with the condition.
	 *
	 * @param whereClause
	 *            For look entities condition
	 * @return Collection of entities
	 */
	public List find(String whereClause) throws DaoException;

	/**
	 * Find entities from database which accord with the condition. binding one
	 * value to a "?"
	 *
	 * @param whereClause
	 *            For look entities condition
	 * @return Collection of entities
	 */
	public List find(String whereClause, Object value) throws DaoException;

	/**
	 * Find entities from database which accord with the condition. binding a
	 * number of valses to "?"
	 *
	 * @param whereClause
	 *            For look entities condition
	 * @return Collection of entities
	 */
	public List find(String whereClause, Object[] values) throws DaoException;

	/**
	 * Find entities from database which accord with the condition. binding a
	 * number of valses to "?"
	 *
	 * @param whereClause
	 *            For look entities condition
	 * @param startRow
	 *            The start index of reuslt
	 * @param maxRows
	 *            Can be return mas rows.
	 * @return Collection of entities
	 */

	public List find(String whereClause, Object[] values, int startRow,
			int maxRows) throws DaoException;

	/**
	 * Find entities from database which accord with the condition.
	 *
	 * @param whereClause
	 *            For look entities condition
	 * @param orderby
	 *            For sort result.
	 * @return Collection of entities
	 */
	public List findWithOrder(String whereClause, String orderby)
			throws DaoException;

	/**
	 * Find entities from database which accord with the condition. binding one
	 * value to a "?"
	 *
	 * @param whereClause
	 *            For look entities condition
	 * @return Collection of entities
	 */
	public List findWithOrder(String whereClause, Object value, String orderby)
			throws DaoException;

	/**
	 * Find entities from database which accord with the condition. binding a
	 * number of valses to "?"
	 *
	 * @param whereClause
	 *            For look entities condition
	 * @return Collection of entities
	 */
	public List findWithOrder(String whereClause, Object[] values,
			String orderby) throws DaoException;

	/**
	 * Find entities from database which accord with the condition. binding a
	 * number of valses to "?"
	 *
	 * @param whereClause
	 *            For look entities condition
	 * @param startRow
	 *            The start index of reuslt
	 * @param maxRows
	 *            Can be return mas rows.
	 * @return Collection of entities
	 */

	public List findWithOrder(String whereClause, Object[] values,
			String orderby, int startRow, int maxRows) throws DaoException;

	/**
	 * List all entities in the database.
	 *
	 * @return
	 * @throws DaoException
	 */

	public List listAll() throws DaoException;

	/**
	 * Count the query result before query.
	 *
	 * @param whereClause
	 * @return
	 * @throws DaoException
	 */
	public int count(String whereClause) throws DaoException;

	/**
	 * Count the query result before query.
	 *
	 * @param whereClause
	 * @return
	 * @throws DaoException
	 */
	public int count(String whereClause, Object[] values) throws DaoException;


}

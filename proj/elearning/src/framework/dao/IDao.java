/*
 * @(#)IDao.java  2005-1-5
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/framework/dao/IDao.java,v 1.1 2005/06/14 10:29:24 navysu Exp $
 * $Log: IDao.java,v $
 * Revision 1.1  2005/06/14 10:29:24  navysu
 * add application and framework etc.
 *
 * Revision 1.1  2005/01/20 03:14:24  navy
 * Create SMiRT 18 project
 *
 */
package framework.dao;

import java.util.List;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p>Base dao. All business dao should extend this interface.</p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface IDao {
    /**
     * Find entities from database which accord with the condition.
     * @param condition For look entities condition
     * @param entityType entity class
     * @return Collection of entities
     */
	public List find(String condition, Class entityType) throws DaoException;
	
	/**
	 * Remove from database which accord with the condition. 
	 * @param condition For remove entities condition 
	 * @param entityType entity class
	 */
	public void remove(String condition, Class entityType) throws DaoException;
}

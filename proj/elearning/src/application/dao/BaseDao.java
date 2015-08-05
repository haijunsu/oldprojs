/*
 * @(#)BaseDao.java  2005-2-17
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/dao/BaseDao.java,v 1.1 2005/06/14 10:29:21 navysu Exp $
 * $Log: BaseDao.java,v $
 * Revision 1.1  2005/06/14 10:29:21  navysu
 * add application and framework etc.
 *
 */
package application.dao;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface BaseDao {
    
//    public List findAll() throws DaoException;
//    
//    public List find(String condition) throws DaoException;
//    
//    public List find(String condition, String orderBy) throws DaoException;
//    
//    public List find(String condition, Object[] objParams) throws DaoException;
//
//    public List find(String condition, Object[] objParams, String orderBy) throws DaoException;
    
    public int remove(String condition) throws DaoException;
    
    public int remove(String condition, Object[] objParams) throws DaoException;
    
    public int count(String condition) throws DaoException;
    
    public Object listEntity(String condition) throws DaoException;
    
    public Object[] listEntities(String condition) throws DaoException;
    
    public Object[] listEntities(String condition, String orderBy) throws DaoException;

}

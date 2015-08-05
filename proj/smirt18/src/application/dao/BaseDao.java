/*
 * @(#)BaseDao.java  2005-2-17
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
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

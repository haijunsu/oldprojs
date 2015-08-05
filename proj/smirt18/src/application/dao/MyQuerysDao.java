/*
 * @(#)MyQueryDao.java  2005-4-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import framework.exception.DaoException;
import application.entity.MyQuerys;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface MyQuerysDao {
    
    public MyQuerys create(MyQuerys querysEntity) throws DaoException;
    
    public MyQuerys load(Long id) throws DaoException;
    
    public MyQuerys update(MyQuerys querysEntity) throws DaoException;
    
    public void remove(MyQuerys querysEntity) throws DaoException;
    
    public MyQuerys[] listAll() throws DaoException;

}

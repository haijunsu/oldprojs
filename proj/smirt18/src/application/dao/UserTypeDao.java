/*
 * @(#)UserTypeDao.java  2005-2-21
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;


import framework.exception.DaoException;
import application.entities.TuserType;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface UserTypeDao extends BaseDao {
    public TuserType findByTypeNo(String typeNo) throws DaoException;
    public TuserType[] findByUserType(String userType) throws DaoException;
    public TuserType[] listAll() throws DaoException;
}

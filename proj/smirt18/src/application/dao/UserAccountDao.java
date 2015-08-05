/*
 * @(#)UserAccountDao.java  2005-2-11
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.TuserAccount;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface UserAccountDao extends BaseDao {
    
    public TuserAccount findByUserid(Integer userid) throws DaoException;
    public TuserAccount[] findByUserAccount(String userAccount) throws DaoException;
    public TuserAccount[] findByFirstName(String firstName)  throws DaoException;
    public TuserAccount[] findByLastName(String lastName) throws DaoException;
    public TuserAccount[] findByEmail(String email) throws DaoException;
    public TuserAccount[] findByEmail(String email, boolean isExact) throws DaoException;
    public TuserAccount[] findByCountryNo(String countryNo) throws DaoException;
    public TuserAccount[] findByAffiliation(String affiliation) throws DaoException;
}
/*
 * @(#)UserAccountDao.java  2005-2-11
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.Tauthor;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface AuthorDao extends BaseDao {
    
    public Tauthor findByAuthorNo(String authorNo) throws DaoException;
    public Tauthor[] findByAuthorAccount(String authorAccount) throws DaoException;
    public Tauthor[] findByFirstName(String firstName)  throws DaoException;
    public Tauthor[] findByLastName(String lastName) throws DaoException;
    public Tauthor[] findByEmail(String email) throws DaoException;
    public Tauthor[] findByEmail(String email, boolean isExact) throws DaoException;
    public Tauthor[] findByCountryNo(String countryNo) throws DaoException;
    public Tauthor[] findByAffiliation(String affiliation) throws DaoException;
    public Tauthor[] findByPaperNumber(String paperNumber) throws DaoException;
}
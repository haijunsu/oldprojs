/*
 * @(#)CoauthorDao.java  2005-2-11
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.Tcoauthor;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface CoauthorDao extends BaseDao {
    
    public Tcoauthor findCoauthor(String paperNumber, String email) throws DaoException; 
    
    public Tcoauthor[] findByPaperNumber(String paperNumber) throws DaoException; 
    
    public Tcoauthor[] findByEmail(String email) throws DaoException; 
    
    public Tcoauthor[] findByEmail(String email, boolean isExact) throws DaoException; 
    
    public Tcoauthor[] findByFirstName(String firstName) throws DaoException; 
    
    public Tcoauthor[] findByLastName(String lastName) throws DaoException; 
    
    public Tcoauthor[] findByCountry(String countryNo) throws DaoException; 
    
    public Tcoauthor[] findByAffiliation(String affiliation) throws DaoException;
    
    public Integer findAuthorRankByPaperNumberAndEmail(String paperNumber, String email) throws DaoException;

}

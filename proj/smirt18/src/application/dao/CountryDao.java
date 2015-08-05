/*
 * @(#)CountryDao.java  2005-2-11
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.Tcountry;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface CountryDao extends BaseDao {

    public Tcountry findByCountryNo(String countryNo) throws DaoException;
    public Tcountry[] findByCountryName(String countryName) throws DaoException;
    public Tcountry[] listAll() throws DaoException;
    
}

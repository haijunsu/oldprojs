/*
 * @(#)DivisionDao.java  2005-2-21
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.Tdivision;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface DivisionDao extends BaseDao {
    public Tdivision findByDivisionNo(String divisionNo) throws DaoException;
    public Tdivision[] findByDivisionTitle(String divisionTitle) throws DaoException;
    public Tdivision[] listAll() throws DaoException;
 
}

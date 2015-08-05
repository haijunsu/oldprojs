/*
 * @(#)ExecuteSql.java  2005-5-7
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.jdbc;

import java.util.List;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author <a href=mailto:su_hj@126.com>Haijun Su</a>
 */
public interface ExecuteSql {
    
    public String execute(String strSql) throws DaoException;
    public String[] execute(String[] strSql) throws DaoException;
    public List query(String strSql) throws DaoException;

}

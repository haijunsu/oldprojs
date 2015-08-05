/*
 * @(#)ExecuteSqlHelper.java  2005-3-22
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.action.jdbc;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import framework.exception.DaoException;
import framework.service.impl.JdbcServiceImpl;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class ExecuteSqlHelper extends JdbcServiceImpl implements ExecuteSql {
    
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(ExecuteSqlHelper.class);
    
    public String execute(String strSql) throws DaoException {
        int _nRows = 0;
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("execute(String) - SQL: " + strSql);
            }
        _nRows = getJdbcTemplate().update(strSql);
        } catch (Exception e) {
            logger.error("execute(String) - exception", e);
            throw new DaoException(e.getMessage());
         }
        String _string = _nRows + " rows affected.";
        if (_nRows < 2) {
            _string = _nRows + " row affected.";
        }
        return _string;
    }
    
    public String[] execute(String[] strSql) throws DaoException {
        String[] _strings = new String[strSql.length];
        for (int i = 0; i < _strings.length; i++) {
            int _nRows = 0;
            try {
                if (logger.isDebugEnabled()) {
                    logger.debug("execute(String) - SQL: " + strSql[i]);
                }
            _nRows = getJdbcTemplate().update(strSql[i].trim());
            } catch (Exception e) {
                logger.error("execute(String) - exception", e);
                throw new DaoException(e.getMessage());
             }
            _strings[i] = _nRows + " rows affected.";
            if (_nRows < 2) {
                _strings[i] = _nRows + " row affected.";
            }
        }
        return _strings;
    }

    public List query(String strSql) throws DaoException {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("query(String) - SQL: " + strSql);
            }
            List _list = getJdbcTemplate().queryForList(strSql);
            return _list;
        } catch (Exception e) {
            logger.error("query(String) - exception", e);
            throw new DaoException(e.getMessage());
         }
    }
    
}

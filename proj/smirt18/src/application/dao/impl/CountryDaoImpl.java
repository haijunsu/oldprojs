/*
 * @(#)CountryDaoImpl.java  2005-2-18
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.CountryDao;
import application.entities.Tcountry;

import framework.exception.DaoException;
import framework.util.StringUtil;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision$
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class CountryDaoImpl extends BaseDaoJdbcImpl implements CountryDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(CountryDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.CountryDao#findByCountryNo(java.lang.String)
     */
    public Tcountry findByCountryNo(String countryNo) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("findByCountryNo(String) - start");
        }
        String _strCondition = "chrCountryNo = '" + StringUtil.escapeSql(countryNo) + "'";

        Tcountry _arrayCountry = (Tcountry)listEntity(_strCondition);
        
        if (logger.isDebugEnabled()) {
            logger.debug("findByCountryNo(String) - end");
        }
        return _arrayCountry;

    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.CountryDao#findByCountryName(java.lang.String)
     */
    public Tcountry[] findByCountryName(String countryName)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByCountryName(String) - start");
        }

        String _strCondition = "chvCountryName = '" + StringUtil.escapeSql(countryName) + "'";
        Tcountry[] _arrayCountry = (Tcountry[])listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByCountryName(String) - end");
        }
        return _arrayCountry;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.CountryDao#listAll()
     */
    public Tcountry[] listAll() throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("listAll() - start");
        }
        Tcountry[] _arrayCountry = (Tcountry[])listEntities(null);

        if (logger.isDebugEnabled()) {
            logger.debug("listAll() - end");
        }
        return _arrayCountry;
    }

    /* (non-Javadoc)
     * @see application.dao.CountryDao#listEntities(java.lang.String, java.lang.String)
     */
    public Object[] listEntities(String condition, String orderBy) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - start");
        }

        List _listRs = find(condition, orderBy);
        Tcountry[] _arrayCountry = new Tcountry[_listRs.size()];
        for (int i = 0; i < _arrayCountry.length; i++) {
            _arrayCountry[i] = new Tcountry();
        }
        SQLHelper.listToEntities(_listRs, _arrayCountry);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayCountry;
    }


}
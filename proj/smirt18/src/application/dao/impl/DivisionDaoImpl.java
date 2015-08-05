/*
 * @(#)DivisionDaoImpl.java  2005-2-21
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


import framework.exception.DaoException;
import framework.util.StringUtil;
import application.dao.DivisionDao;
import application.entities.Tdivision;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class DivisionDaoImpl extends BaseDaoJdbcImpl implements DivisionDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(DivisionDaoImpl.class);

    /* (non-Javadoc)
     * @see application.dao.DivisionDao#findByDivisionNo(java.lang.String)
     */
    public Tdivision findByDivisionNo(String divisionNo) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("findByDivisionNo(String) - start");
        }

        String _strCondition = "chrDivisionNo = '" + StringUtil.escapeSql(divisionNo) + "'";
        Tdivision _division = (Tdivision)listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByDivisionNo(String) - end");
        }
        return _division;
    }

    /* (non-Javadoc)
     * @see application.dao.DivisionDao#findByDivisionTitle(java.lang.String)
     */
    public Tdivision[] findByDivisionTitle(String divisionTitle)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByDivisionTitle(String) - start");
        }

        String _strCondition = "chvDivisionTitle like '%" + StringUtil.escapeSql(divisionTitle) + "%'";
        Tdivision[] _arrayDivision = (Tdivision[])listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByDivisionTitle(String) - end");
        }
        return _arrayDivision;
    }

    /* (non-Javadoc)
     * @see application.dao.DivisionDao#listAll()
     */
    public Tdivision[] listAll() throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("listAll() - start");
        }

        Tdivision[] _arrayDivision = (Tdivision[])listEntities(null);

        if (logger.isDebugEnabled()) {
            logger.debug("listAll() - end");
        }
        return _arrayDivision;
    }

    /* (non-Javadoc)
     * @see application.dao.BaseDao#listEntities(java.lang.String, java.lang.String)
     */
    public Object[] listEntities(String condition, String orderBy)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - start");
        }

        List _listRs = find(condition, orderBy);
        Tdivision[] _arrayDivision = new Tdivision[_listRs.size()];
        for (int i = 0; i < _arrayDivision.length; i++) {
            _arrayDivision[i] = new Tdivision();
        }
        SQLHelper.listToEntities(_listRs, _arrayDivision);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayDivision;
    }

}

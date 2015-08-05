/*
 * @(#)AffiliationTypeDaoImpl.java  2005-2-21
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
import application.dao.AffiliationTypeDao;
import application.entities.TaffiliationType;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class AffiliationTypeDaoImpl extends BaseDaoJdbcImpl implements
        AffiliationTypeDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(AffiliationTypeDaoImpl.class);

    /* (non-Javadoc)
     * @see application.dao.AffiliationTypeDao#findByAffiliationTypeNo(java.lang.String)
     */
    public TaffiliationType findByAffiliationTypeNo(String affiliationTypeNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByAffiliationTypeNo(String) - start");
        }

        String _strCondition = "chrAffiliationTypeNo = '" + StringUtil.escapeSql(affiliationTypeNo) + "'";
        TaffiliationType _affiliation = (TaffiliationType)listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByAffiliationTypeNo(String) - end");
        }
        return _affiliation;
    }

    /* (non-Javadoc)
     * @see application.dao.AffiliationTypeDao#findByAffiliationType(java.lang.String)
     */
    public TaffiliationType[] findByAffiliationType(String affiliationType)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByAffiliationType(String) - start");
        }

        String _strCondition = "chvAffiliationType like '%" + StringUtil.escapeSql(affiliationType) + "%'";
        TaffiliationType[] _arrayAffiliation = (TaffiliationType[])listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByAffiliationType(String) - end");
        }
        return _arrayAffiliation;
    }

    /* (non-Javadoc)
     * @see application.dao.AffiliationTypeDao#listAll()
     */
    public TaffiliationType[] listAll() throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("listAll() - start");
        }

        TaffiliationType[] _arrayAffiliation = (TaffiliationType[])listEntities(null);

        if (logger.isDebugEnabled()) {
            logger.debug("listAll() - end");
        }
        return _arrayAffiliation;
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
        TaffiliationType[] _arrayAffiliation = new TaffiliationType[_listRs.size()];
        for (int i = 0; i < _arrayAffiliation.length; i++) {
            _arrayAffiliation[i] = new TaffiliationType();
        }
        SQLHelper.listToEntities(_listRs, _arrayAffiliation);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayAffiliation;
    }

}

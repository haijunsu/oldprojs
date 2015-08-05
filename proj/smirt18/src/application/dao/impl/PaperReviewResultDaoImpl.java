/*
 * @(#)PaperReviewResultDaoImpl.java  2005-2-27
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
import application.dao.PaperReviewResultDao;
import application.entities.TpaperReviewResult;

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
public class PaperReviewResultDaoImpl extends BaseDaoJdbcImpl implements
        PaperReviewResultDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(PaperReviewResultDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperReviewResultDao#create(application.entities.TpaperReviewResult)
     */
    public TpaperReviewResult create(TpaperReviewResult paperReviewResultEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("create(TpaperReviewResult) - start");
        }

        insertEntity(paperReviewResultEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("create(TpaperReviewResult) - end");
        }
        return paperReviewResultEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperReviewResultDao#load(java.lang.String)
     */
    public TpaperReviewResult load(String paperNumber) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - start");
        }

        String _strCondition = "chvPaperNumber = '"
                + StringUtil.escapeSql(paperNumber) + "'";
        TpaperReviewResult _paperReviewResult = (TpaperReviewResult) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - end");
        }
        return _paperReviewResult;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperReviewResultDao#update(application.entities.TpaperReviewResult)
     */
    public int update(TpaperReviewResult paperReviewResultEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("update(TpaperReviewResult) - start");
        }

        int _nRtn = updateEntity(paperReviewResultEntity,
                new String[] { "chvPaperNumber" });

        if (logger.isDebugEnabled()) {
            logger.debug("update(TpaperReviewResult) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperReviewResultDao#remove(application.entities.TpaperReviewResult)
     */
    public int remove(TpaperReviewResult paperReviewResultEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove(TpaperReviewResult) - start");
        }

        int _nRtn = updateEntity(paperReviewResultEntity,
                new String[] { "chvPaperNumber" });

        if (logger.isDebugEnabled()) {
            logger.debug("remove(TpaperReviewResult) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperReviewResultDao#findByPaperNumber(java.lang.String)
     */
    public TpaperReviewResult findByPaperNumber(String paperNumber)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - start");
        }

        TpaperReviewResult _paperReviewResult = load(paperNumber);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - end");
        }
        return _paperReviewResult;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.BaseDao#listEntities(java.lang.String,
     *      java.lang.String)
     */
    public Object[] listEntities(String condition, String orderBy)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - start");
        }

        List _listRs = find(condition, orderBy);
        TpaperReviewResult[] _arrayPaperReviewResult = new TpaperReviewResult[_listRs
                .size()];
        for (int i = 0; i < _arrayPaperReviewResult.length; i++) {
            _arrayPaperReviewResult[i] = new TpaperReviewResult();
        }
        SQLHelper.listToEntities(_listRs, _arrayPaperReviewResult);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayPaperReviewResult;
    }

}
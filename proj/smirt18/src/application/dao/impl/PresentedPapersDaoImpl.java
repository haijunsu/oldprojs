/*
 * @(#)PresentedPapersDaoImpl.java  2005-2-27
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
import application.dao.PresentedPapersDao;
import application.entities.TpresentedPapers;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class PresentedPapersDaoImpl extends BaseDaoJdbcImpl implements
        PresentedPapersDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(PresentedPapersDaoImpl.class);

    /* (non-Javadoc)
     * @see application.dao.PresentedPapersDao#create(application.entities.TpresentedPapers)
     */
    public TpresentedPapers create(TpresentedPapers tpresentedPapersEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("create(TpresentedPapers) - start");
        }

        insertEntity(tpresentedPapersEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("create(TpresentedPapers) - end");
        }
        return tpresentedPapersEntity;
    }

    /* (non-Javadoc)
     * @see application.dao.PresentedPapersDao#load(java.lang.String)
     */
    public TpresentedPapers load(String paperNumber) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - start");
        }

        String _strCondition = "chvPaperNumber  = '" + StringUtil.escapeSql(paperNumber) + "'";
        TpresentedPapers _presentedPapers = (TpresentedPapers)listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - end");
        }
        return _presentedPapers;
    }

    /* (non-Javadoc)
     * @see application.dao.PresentedPapersDao#update(application.entities.TpresentedPapers)
     */
    public int update(TpresentedPapers tpresentedPapersEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("update(TpresentedPapers) - start");
        }

        int _nRtn = updateEntity(tpresentedPapersEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("update(TpresentedPapers) - end");
        }
        return _nRtn;
    }

    /* (non-Javadoc)
     * @see application.dao.PresentedPapersDao#remove(application.entities.TpresentedPapers)
     */
    public int remove(TpresentedPapers tpresentedPapersEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove(TpresentedPapers) - start");
        }

        int _nRtn = removeEntity(tpresentedPapersEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("remove(TpresentedPapers) - end");
        }
        return _nRtn;
    }

    /* (non-Javadoc)
     * @see application.dao.PresentedPapersDao#findByPaerNumber(java.lang.String)
     */
    public TpresentedPapers findByPaerNumber(String paperNumber)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaerNumber(String) - start");
        }

        TpresentedPapers _presentedPapers = load(paperNumber);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaerNumber(String) - end");
        }
        return _presentedPapers;
    }

    /* (non-Javadoc)
     * @see application.dao.PresentedPapersDao#findBySessionNo(java.lang.String)
     */
    public TpresentedPapers[] findBySessionNo(String sessionNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findBySessionNo(String) - start");
        }

        String _strCondition = "chrSessionNo = '" + StringUtil.escapeSql(sessionNo) + "'";
        TpresentedPapers[] _arrayPresentedPapers = (TpresentedPapers[])listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findBySessionNo(String) - end");
        }
        return _arrayPresentedPapers;
    }

    /* (non-Javadoc)
     * @see application.dao.PresentedPapersDao#findByPresentationNumber(java.lang.Integer)
     */
    public TpresentedPapers[] findByPresentationNumber(
            Integer presentationNumber) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPresentationNumber(Integer) - start");
        }

        String _strCondition = "intPresentationNumber = '" + presentationNumber;
        TpresentedPapers[] _arrayPresentedPapers = (TpresentedPapers[])listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPresentationNumber(Integer) - end");
        }
        return _arrayPresentedPapers;
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
        TpresentedPapers[] _presentedPapers = new TpresentedPapers[_listRs.size()];
        for (int i = 0; i < _presentedPapers.length; i++) {
            _presentedPapers[i] = new TpresentedPapers();
        }
        SQLHelper.listToEntities(_listRs, _presentedPapers);
        
        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _presentedPapers;
    }

}

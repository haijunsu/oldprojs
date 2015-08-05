/*
 * @(#)PaperPresentedDaoImpl.java  2005-2-27
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
import application.dao.PaperPresentedDao;
import application.entities.TpaperPresented;

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
public class PaperPresentedDaoImpl extends BaseDaoJdbcImpl implements
        PaperPresentedDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(PaperPresentedDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperPresentedDao#create(application.entities.TpaperPresented)
     */
    public TpaperPresented create(TpaperPresented paperPresentedEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("create(TpaperPresented) - start");
        }

        //paperPresentedEntity.setIntIndex(new Integer(genLongKey().intValue()));
        insertEntity(paperPresentedEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("create(TpaperPresented) - end");
        }
        return paperPresentedEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperPresentedDao#load(java.lang.Integer)
     */
    public TpaperPresented load(Integer index) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("load(Integer) - start");
        }

        String _strCondition = "intIndex = " + index;
        TpaperPresented _paperPresented = (TpaperPresented) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("load(Integer) - end");
        }
        return _paperPresented;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperPresentedDao#update(application.entities.TpaperPresented)
     */
    public int update(TpaperPresented paperPresentedEntity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("update(TpaperPresented) - start");
        }

        int _nRtn = updateEntity(paperPresentedEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("update(TpaperPresented) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperPresentedDao#remove(application.entities.TpaperPresented)
     */
    public int remove(TpaperPresented paperPresented) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove(TpaperPresented) - start");
        }

        int _nRtn = removeEntity(paperPresented);

        if (logger.isDebugEnabled()) {
            logger.debug("remove(TpaperPresented) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperPresentedDao#findByIndex(java.lang.Integer)
     */
    public TpaperPresented findByIndex(Integer index) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("findByIndex(Integer) - start");
        }

        TpaperPresented _paperPresented = load(index);

        if (logger.isDebugEnabled()) {
            logger.debug("findByIndex(Integer) - end");
        }
        return _paperPresented;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperPresentedDao#findByPaperNumber(java.lang.String)
     */
    public TpaperPresented findByPaperNumber(String paperNumber)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - start");
        }

        String _strCondition = "chvPaperNumber = '"
                + StringUtil.escapeSql(paperNumber) + "'";
        TpaperPresented _tpaperPresented = (TpaperPresented) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - end");
        }
        return _tpaperPresented;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperPresentedDao#findByPaperLNumber(java.lang.String)
     */
    public TpaperPresented findByPaperLNumber(String paperLNumber)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperLNumber(String) - start");
        }

        String _strCondition = "chvLPaperNumber = '"
                + StringUtil.escapeSql(paperLNumber) + "'";
        TpaperPresented _tpaperPresented = (TpaperPresented) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperLNumber(String) - end");
        }
        return _tpaperPresented;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperPresentedDao#findByPresenterPartiNo(java.lang.String)
     */
    public TpaperPresented[] findByPresenterPartiNo(String presenterPartiNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPresenterPartiNo(String) - start");
        }

        String _strCondition = "chrPresenterPartiNo = '"
                + StringUtil.escapeSql(presenterPartiNo) + "'";
        TpaperPresented[] _arrayPaperPresented = (TpaperPresented[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPresenterPartiNo(String) - end");
        }
        return _arrayPaperPresented;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperPresentedDao#findByFirstName(java.lang.String)
     */
    public TpaperPresented[] findByFirstName(String firstName)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByFirstName(String) - start");
        }

        String _strCondition = "chvPresenterFirstName like '%"
                + StringUtil.escapeSql(firstName) + "%'";
        TpaperPresented[] _arrayPaperPresented = (TpaperPresented[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByFirstName(String) - end");
        }
        return _arrayPaperPresented;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperPresentedDao#findByLastName(java.lang.String)
     */
    public TpaperPresented[] findByLastName(String lastName)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByLastName(String) - start");
        }

        String _strCondition = "chvPresenterLastName like '%"
                + StringUtil.escapeSql(lastName) + "%'";
        TpaperPresented[] _arrayPaperPresented = (TpaperPresented[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByLastName(String) - end");
        }
        return _arrayPaperPresented;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperPresentedDao#findByEquipmentNo(java.lang.String)
     */
    public TpaperPresented[] findByEquipmentNo(String equipmentNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByEquipmentNo(String) - start");
        }

        String _strCondition = "chrEquipmentNo = '"
                + StringUtil.escapeSql(equipmentNo) + "'";
        TpaperPresented[] _arrayPaperPresented = (TpaperPresented[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByEquipmentNo(String) - end");
        }
        return _arrayPaperPresented;
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
        TpaperPresented[] _arrayPaperPresented = new TpaperPresented[_listRs
                .size()];
        for (int i = 0; i < _arrayPaperPresented.length; i++) {
            _arrayPaperPresented[i] = new TpaperPresented();
        }
        SQLHelper.listToEntities(_listRs, _arrayPaperPresented);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayPaperPresented;
    }

}
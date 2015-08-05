/*
 * @(#)AccommodationDaoImpl.java  2005-2-26
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import application.dao.AccommodationDao;
import application.entities.Taccommodation;

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
public class AccommodationDaoImpl extends BaseDaoJdbcImpl implements
        AccommodationDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(AccommodationDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccommodationDao#create(application.entities.Taccommodation)
     */
    public Taccommodation create(Taccommodation taccommodationEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("create(Taccommodation) - start");
        }

        taccommodationEntity.setChrAccommodationNo(genStringKey());
        insertEntity(taccommodationEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("create(Taccommodation) - end");
        }
        return taccommodationEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccommodationDao#load(java.lang.String)
     */
    public Taccommodation load(String accommodationNo) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - start");
        }

        String _strCondition = "chrAccommodationNo = '"
                + StringUtil.escapeSql(accommodationNo) + "'";
        Taccommodation _accommodation = (Taccommodation) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - end");
        }
        return _accommodation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccommodationDao#update(application.entities.Taccommodation)
     */
    public int update(Taccommodation taccommodationEntity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("update(Taccommodation) - start");
        }

        int _nRtn = updateEntity(taccommodationEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("update(Taccommodation) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccommodationDao#remove(application.entities.Taccommodation)
     */
    public int remove(Taccommodation taccommodationEntity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove(Taccommodation) - start");
        }

        int _nRtn = remove(taccommodationEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("remove(Taccommodation) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccommodationDao#findByAccommodationNo(java.lang.String)
     */
    public Taccommodation findByAccommodationNo(String accommodationNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByAccommodationNo(String) - start");
        }

        Taccommodation _accommodation = load(accommodationNo);

        if (logger.isDebugEnabled()) {
            logger.debug("findByAccommodationNo(String) - end");
        }
        return _accommodation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccommodationDao#findByPartiNo(java.lang.String)
     */
    public Taccommodation[] findByPartiNo(String partiNo) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPartiNo(String) - start");
        }

        String _strCondition = "chrPartiNo = '" + StringUtil.escapeSql(partiNo)
                + "'";
        Taccommodation[] _arrayAccommodation = (Taccommodation[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPartiNo(String) - end");
        }
        return _arrayAccommodation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccommodationDao#findByHotelName(java.lang.String)
     */
    public Taccommodation[] findByHotelName(String hotelName)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByHotelName(String) - start");
        }

        String _strCondition = "chvHotalName like '%"
                + StringUtil.escapeSql(hotelName) + "%'";
        Taccommodation[] _arrayAccommodation = (Taccommodation[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByHotelName(String) - end");
        }
        return _arrayAccommodation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccommodationDao#findByRoomNumber(java.lang.String,
     *      java.lang.String)
     */
    public Taccommodation findByRoomNumber(String hotelName, String roomNumber)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByRoomNumber(String, String) - start");
        }

        String _strCondition = "chvHotalName = '"
                + StringUtil.escapeSql(hotelName) + "' AND chvRoomNumber = '"
                + StringUtil.escapeSql(roomNumber) + "'";
        Taccommodation _accommodation = (Taccommodation) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByRoomNumber(String, String) - end");
        }
        return _accommodation;
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
        Taccommodation[] _arrayAccommodation = new Taccommodation[_listRs.size()];
        for (int i = 0; i < _arrayAccommodation.length; i++) {
            _arrayAccommodation[i] = new Taccommodation();
        }
        SQLHelper.listToEntities(_listRs, _arrayAccommodation);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayAccommodation;
    }

}
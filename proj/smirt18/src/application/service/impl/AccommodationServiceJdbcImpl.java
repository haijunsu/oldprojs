/*
 * @(#)AccommodationServiceJdbcImpl.java  2005-3-10
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.AccommodationDao;
import application.entities.Taccommodation;
import application.exception.BusinessServiceException;
import application.service.AccommodationService;
import application.util.ServicesUtil;
import application.viewdata.AccommodationView;
import application.viewdata.SmallUserView;
import framework.exception.DaoException;
import framework.service.impl.JdbcServiceImpl;

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
public class AccommodationServiceJdbcImpl extends JdbcServiceImpl implements
        AccommodationService {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(AccommodationServiceJdbcImpl.class);

    private AccommodationDao m_accommodationDao;

    public AccommodationDao getAccommodationDao() {
        return this.m_accommodationDao;
    }

    public void setAccommodationDao(AccommodationDao accommodationDao) {
        this.m_accommodationDao = accommodationDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.AccommodationService#loadAccommodationByPartiNo(java.lang.String)
     */
    public AccommodationView[] loadAccommodationByPartiNo(String partiNo)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("loadAccommodationByPartiNo(String) - start");
        }

        try {
            Taccommodation[] _taccommodations = getAccommodationDao()
                    .findByPartiNo(partiNo);
            AccommodationView[] _accommodationViews = new AccommodationView[_taccommodations.length];
            for (int i = 0; i < _accommodationViews.length; i++) {
                _accommodationViews[i] = new AccommodationView();
                BeanUtils.copyProperties(_accommodationViews[i],
                        _taccommodations[i]);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("loadAccommodationByPartiNo(String) - end");
            }
            return _accommodationViews;
        } catch (DaoException e) {
            logger.error("loadAccommodationByPartiNo(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadAccommodationByPartiNo(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.AccommodationService#loadAccommodationByAccommodationNo(java.lang.String)
     */
    public AccommodationView loadAccommodationByAccommodationNo(
            String accommodationNo) throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("loadAccommodationByAccommodationNo(String) - start");
        }

        try {
            AccommodationView _accommodationView = new AccommodationView();
            Taccommodation _taccommodation = getAccommodationDao()
                    .findByAccommodationNo(accommodationNo);
            if (_taccommodation != null) {
                BeanUtils.copyProperties(_accommodationView, _taccommodation);
            }

            if (logger.isDebugEnabled()) {
                logger
                        .debug("loadAccommodationByAccommodationNo(String) - end");
            }
            return _accommodationView;
        } catch (DaoException e) {
            logger.error("loadAccommodationByAccommodationNo(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadAccommodationByAccommodationNo(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.AccommodationService#loadAccommodationByHotelName(java.lang.String)
     */
    public AccommodationView[] loadAccommodationByHotelName(String hotelName)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("loadAccommodationByHotelName(String) - start");
        }

        try {
            Taccommodation[] _taccommodations = getAccommodationDao()
                    .findByHotelName(hotelName);
            AccommodationView[] _accommodationViews = new AccommodationView[_taccommodations.length];
            for (int i = 0; i < _accommodationViews.length; i++) {
                _accommodationViews[i] = new AccommodationView();
                BeanUtils.copyProperties(_accommodationViews[i],
                        _taccommodations[i]);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("loadAccommodationByHotelName(String) - end");
            }
            return _accommodationViews;
        } catch (DaoException e) {
            logger.error("loadAccommodationByHotelName(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadAccommodationByHotelName(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.AccommodationService#loadAccommodationByHotelNameAndRoomNumber(java.lang.String,
     *      java.lang.String)
     */
    public AccommodationView loadAccommodationByHotelNameAndRoomNumber(
            String accommodationNo, String roomNumber)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("loadAccommodationByHotelNameAndRoomNumber(String, String) - start");
        }

        try {
            AccommodationView _accommodationView = new AccommodationView();
            Taccommodation _taccommodation = getAccommodationDao()
                    .findByRoomNumber(accommodationNo, roomNumber);
            if (_taccommodation != null) {
                BeanUtils.copyProperties(_accommodationView, _taccommodation);
            }

            if (logger.isDebugEnabled()) {
                logger
                        .debug("loadAccommodationByHotelNameAndRoomNumber(String, String) - end");
            }
            return _accommodationView;
        } catch (DaoException e) {
            logger
                    .error("loadAccommodationByHotelNameAndRoomNumber(String)",
                            e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger
                    .error("loadAccommodationByHotelNameAndRoomNumber(String)",
                            e);

            throw new BusinessServiceException(97, e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.AccommodationService#createAccommodation(application.entities.Taccommodation)
     */
    public void createAccommodation(Taccommodation accommodation)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("createAccommodation(Taccommodation) - start");
        }

        try {
            getAccommodationDao().create(accommodation);
        } catch (DaoException e) {
            logger.error("createAccommodation(Taccommodation)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("createAccommodation(Taccommodation)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("createAccommodation(Taccommodation) - end");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.AccommodationService#updateAccommodation(application.entities.Taccommodation)
     */
    public void updateAccommodation(Taccommodation accommodation)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("updateAccommodation(Taccommodation) - start");
        }

        try {
            getAccommodationDao().update(accommodation);
        } catch (DaoException e) {
            logger.error("updateAccommodation(Taccommodation)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("updateAccommodation(Taccommodation)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("updateAccommodation(Taccommodation) - end");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.AccommodationService#removeAccommodation(application.entities.Taccommodation)
     */
    public void removeAccommodation(Taccommodation accommodation)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("removeAccommodation(Taccommodation) - start");
        }

        try {
            getAccommodationDao().remove(accommodation);
        } catch (DaoException e) {
            logger.error("removeAccommodation(Taccommodation)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("removeAccommodation(Taccommodation)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("removeAccommodation(Taccommodation) - end");
        }
    }

    /* (non-Javadoc)
     * @see application.service.AccommodationService#loadOwnerByPartiNo(java.lang.String)
     */
    public SmallUserView loadOwnerByPartiNo(String partiNo) throws BusinessServiceException {
        return ServicesUtil.getSmallUserViewByPartiNo(partiNo);
    }
}
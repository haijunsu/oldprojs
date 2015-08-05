/*
 * @(#)AccompanyPersonServiceJdbcImpl.java  2005-3-8
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.AccompanyPersonDao;
import application.entities.TaccompanyPerson;
import application.exception.BusinessServiceException;
import application.service.AccompanyPersonService;
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
public class AccompanyPersonServiceJdbcImpl extends JdbcServiceImpl implements
        AccompanyPersonService {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(AccompanyPersonServiceJdbcImpl.class);

    private AccompanyPersonDao m_accompanyPersonDao;

    public AccompanyPersonDao getAccompanyPersonDao() {
        return this.m_accompanyPersonDao;
    }

    public void setAccompanyPersonDao(AccompanyPersonDao accompanyPersonDao) {
        this.m_accompanyPersonDao = accompanyPersonDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.AccompanyPersonService#loadAccompanyPersonByAccPersonNo(java.lang.String)
     */
    public TaccompanyPerson loadAccompanyPersonByAccPersonNo(String accPersonNo)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("loadAccompanyPersonByAccPersonNo(String) - start");
        }

        try {
            TaccompanyPerson _person = getAccompanyPersonDao()
                    .findByAccompanyPersonNo(accPersonNo);

            if (logger.isDebugEnabled()) {
                logger.debug("loadAccompanyPersonByAccPersonNo(String) - end");
            }
            return _person;
        } catch (DaoException e) {
            logger.error("loadAccompanyPersonByAccPersonNo(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadAccompanyPersonByAccPersonNo(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.AccompanyPersonService#createAccompanyPerson(application.entities.TaccompanyPerson)
     */
    public void createAccompanyPerson(TaccompanyPerson accompanyPerson)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("createAccompanyPerson(TaccompanyPerson) - start");
        }

        try {
            getAccompanyPersonDao().create(accompanyPerson);
        } catch (DaoException e) {
            logger.error("createAccompanyPerson(TaccompanyPerson)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("createAccompanyPerson(TaccompanyPerson)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("createAccompanyPerson(TaccompanyPerson) - end");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.AccompanyPersonService#updateAccompanyPerson(application.entities.TaccompanyPerson)
     */
    public void updateAccompanyPerson(TaccompanyPerson accompanyPerson)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("updateAccompanyPerson(TaccompanyPerson) - start");
        }

        try {
            getAccompanyPersonDao().update(accompanyPerson);
        } catch (DaoException e) {
            logger.error("updateAccompanyPerson(TaccompanyPerson)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("updateAccompanyPerson(TaccompanyPerson)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("updateAccompanyPerson(TaccompanyPerson) - end");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.AccompanyPersonService#removeAccompanyPerson(application.entities.TaccompanyPerson)
     */
    public void removeAccompanyPerson(TaccompanyPerson accompanyPerson)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("removeAccompanyPerson(TaccompanyPerson) - start");
        }

        try {
            getAccompanyPersonDao().remove(accompanyPerson);
        } catch (DaoException e) {
            logger.error("removeAccompanyPerson(TaccompanyPerson)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("removeAccompanyPerson(TaccompanyPerson)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("removeAccompanyPerson(TaccompanyPerson) - end");
        }
    }

}
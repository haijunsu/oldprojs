/*
 * @(#)PaperPresentedServiceJdbcImpl.java  2005-3-13
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.PaperPresentedDao;
import application.entities.TpaperPresented;
import application.exception.BusinessServiceException;
import application.service.PaperPresentedService;
import framework.exception.DaoException;
import framework.service.impl.JdbcServiceImpl;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class PaperPresentedServiceJdbcImpl extends JdbcServiceImpl implements
        PaperPresentedService {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(PaperPresentedServiceJdbcImpl.class);
    
    private PaperPresentedDao m_paperPresentedDao;
    
    

    public PaperPresentedDao getPaperPresentedDao() {
        return this.m_paperPresentedDao;
    }
    public void setPaperPresentedDao(PaperPresentedDao paperPresentedDao) {
        this.m_paperPresentedDao = paperPresentedDao;
    }
    /* (non-Javadoc)
     * @see application.service.PaperPresentedService#loadPaperPresentedByPaperNumber(java.lang.String)
     */
    public TpaperPresented loadPaperPresentedByPaperNumber(String paperNumber)
            throws BusinessServiceException {
        TpaperPresented _tpaperPresented = new TpaperPresented();
        try {
            _tpaperPresented = getPaperPresentedDao().findByPaperNumber(paperNumber);
            if (_tpaperPresented == null) {
                _tpaperPresented = new TpaperPresented();
            }
        } catch (DaoException e) {
            logger.error("loadPaperPresentedByPaperNumber(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadPaperPresentedByPaperNumber(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
        return _tpaperPresented;
    }

    /* (non-Javadoc)
     * @see application.service.PaperPresentedService#loadPaperPresentedByPaperLNumber(java.lang.String)
     */
    public TpaperPresented loadPaperPresentedByPaperLNumber(String paperLNumber)
            throws BusinessServiceException {
        TpaperPresented _tpaperPresented = new TpaperPresented();
        try {
            _tpaperPresented = getPaperPresentedDao().findByPaperLNumber(paperLNumber);
            if (_tpaperPresented == null) {
                _tpaperPresented = new TpaperPresented();
            }
        } catch (DaoException e) {
            logger.error("loadPaperPresentedByPaperLNumber(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadPaperPresentedByPaperLNumber(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
        return _tpaperPresented;
    }

    /* (non-Javadoc)
     * @see application.service.PaperPresentedService#loadPaperPresentedByPartiNo(java.lang.String)
     */
    public TpaperPresented[] loadPaperPresentedByPartiNo(String partiNo)
            throws BusinessServiceException {
        TpaperPresented[] _tpaperPresenteds = new TpaperPresented[0];
        try {
            _tpaperPresenteds = getPaperPresentedDao().findByPresenterPartiNo(partiNo);
            if (_tpaperPresenteds == null) {
                _tpaperPresenteds = new TpaperPresented[0];
            }
        } catch (DaoException e) {
            logger.error("loadPaperPresentedByPartiNo(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadPaperPresentedByPartiNo(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
        return _tpaperPresenteds;
    }

    /* (non-Javadoc)
     * @see application.service.PaperPresentedService#loadPaperPresentedByEquipmentNo(java.lang.String)
     */
    public TpaperPresented[] loadPaperPresentedByEquipmentNo(String equipmentNo)
            throws BusinessServiceException {
        TpaperPresented[] _tpaperPresenteds = new TpaperPresented[0];
        try {
            _tpaperPresenteds = getPaperPresentedDao().findByEquipmentNo(equipmentNo);
            if (_tpaperPresenteds == null) {
                _tpaperPresenteds = new TpaperPresented[0];
            }
        } catch (DaoException e) {
            logger.error("loadPaperPresentedByEquipmentNo(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadPaperPresentedByEquipmentNo(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
        return _tpaperPresenteds;
    }

    /* (non-Javadoc)
     * @see application.service.PaperPresentedService#createPaperPresented(application.entities.TpaperPresented)
     */
    public void createPaperPresented(TpaperPresented paperPresented)
            throws BusinessServiceException {
        try {
            getPaperPresentedDao().create(paperPresented);
        } catch (DaoException e) {
            logger.error("createPaperPresented(TpaperPresented)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("createPaperPresented(TpaperPresented)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
    }

    /* (non-Javadoc)
     * @see application.service.PaperPresentedService#updatePaperPresented(application.entities.TpaperPresented)
     */
    public void updatePaperPresented(TpaperPresented paperPresented)
            throws BusinessServiceException {
        try {
            getPaperPresentedDao().update(paperPresented);
        } catch (DaoException e) {
            logger.error("updatePaperPresented(TpaperPresented)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("updatePaperPresented(TpaperPresented)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

    }

    /* (non-Javadoc)
     * @see application.service.PaperPresentedService#removePaperPresented(application.entities.TpaperPresented)
     */
    public void removePaperPresented(TpaperPresented paperPresented)
            throws BusinessServiceException {
        try {
            getPaperPresentedDao().remove(paperPresented);
        } catch (DaoException e) {
            logger.error("removePaperPresented(TpaperPresented)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("removePaperPresented(TpaperPresented)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
    }

}

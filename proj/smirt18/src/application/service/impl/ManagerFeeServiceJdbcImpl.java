/*
 * @(#)ManagerFeeServiceJdbcImpl.java  2005-3-12
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.commons.beanutils.BeanUtils;

import application.dao.AccompanyPersonDao;
import application.dao.PaperDao;
import application.dao.PaperRegistrationDao;
import application.dao.RegistrationFeeDao;
import application.entities.Tpaper;
import application.entities.TpaperRegistation;
import application.entities.TregistrationFee;
import application.exception.BusinessServiceException;
import application.service.ManagerFeeService;
import application.viewdata.SmallPaperView;
import framework.exception.DaoException;
import framework.service.impl.JdbcServiceImpl;
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
public class ManagerFeeServiceJdbcImpl extends JdbcServiceImpl implements
        ManagerFeeService {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(ManagerFeeServiceJdbcImpl.class);

    PaperRegistrationDao m_paperRegistrationDao;

    RegistrationFeeDao m_registrationFeeDao;

    PaperDao m_paperDao;

    AccompanyPersonDao m_accompanyPersonDao;

    public AccompanyPersonDao getAccompanyPersonDao() {
        return this.m_accompanyPersonDao;
    }

    public void setAccompanyPersonDao(AccompanyPersonDao accompanyPersonDao) {
        this.m_accompanyPersonDao = accompanyPersonDao;
    }

    public PaperDao getPaperDao() {
        return this.m_paperDao;
    }

    public void setPaperDao(PaperDao paperDao) {
        this.m_paperDao = paperDao;
    }

    public PaperRegistrationDao getPaperRegistrationDao() {
        return this.m_paperRegistrationDao;
    }

    public void setPaperRegistrationDao(
            PaperRegistrationDao paperRegistrationDao) {
        this.m_paperRegistrationDao = paperRegistrationDao;
    }

    public RegistrationFeeDao getRegistrationFeeDao() {
        return this.m_registrationFeeDao;
    }

    public void setRegistrationFeeDao(RegistrationFeeDao registrationFeeDao) {
        this.m_registrationFeeDao = registrationFeeDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.ManagerFeeService#loadSmallPaperViewByPaperNumber(java.lang.String)
     */
    public SmallPaperView loadSmallPaperViewByPaperNumber(String paperNumber)
            throws BusinessServiceException {
        SmallPaperView _smallPaperView = new SmallPaperView();
        try {
            Tpaper _tpaper = getPaperDao().findByPaperNumber(paperNumber);
            BeanUtils.copyProperties(_smallPaperView, _tpaper);
        } catch (DaoException e) {
            logger.error("loadSmallPaperViewByPaperNumber(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadSmallPaperViewByPaperNumber(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
        return _smallPaperView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.ManagerFeeService#loadPaperRegistrationByPaperNumber(java.lang.String)
     */
    public TpaperRegistation loadPaperRegistrationByPaperNumber(
            String paperNumber) throws BusinessServiceException {
        TpaperRegistation _tpaperRegistation = new TpaperRegistation();
        try {
            _tpaperRegistation = getPaperRegistrationDao().findByPaperNumber(
                    paperNumber);
        } catch (DaoException e) {
            logger.error("loadPaperRegistrationByPaperNumber(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadPaperRegistrationByPaperNumber(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
        return _tpaperRegistation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.ManagerFeeService#loadPaperRegistrationByPaperLNumber(java.lang.String)
     */
    public TpaperRegistation loadPaperRegistrationByPaperLNumber(
            String paperLNumber) throws BusinessServiceException {
        TpaperRegistation _tpaperRegistation = new TpaperRegistation();
        try {
            _tpaperRegistation = getPaperRegistrationDao().findByPaperLNumber(
                    paperLNumber);
        } catch (DaoException e) {
            logger.error("loadPaperRegistrationByPaperLNumber(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadPaperRegistrationByPaperLNumber(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
        return _tpaperRegistation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.ManagerFeeService#loadPaperRegistrationByPaperRegNo(java.lang.String)
     */
    public TpaperRegistation loadPaperRegistrationByPaperRegNo(String paperRegNo)
            throws BusinessServiceException {
        TpaperRegistation _tpaperRegistation = new TpaperRegistation();
        try {
            _tpaperRegistation = getPaperRegistrationDao().findByPaperRegNo(
                    paperRegNo);
        } catch (DaoException e) {
            logger.error("loadPaperRegistrationByPaperRegNo(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadPaperRegistrationByPaperRegNo(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
        return _tpaperRegistation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.ManagerFeeService#createPaperRegistration(application.entities.TpaperRegistation)
     */
    public void createPaperRegistration(TpaperRegistation paperRegistration)
            throws BusinessServiceException {
        try {
            getPaperRegistrationDao().create(paperRegistration);
        } catch (DaoException e) {
            logger.error("createPaperRegistration(TpaperRegistation)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("createPaperRegistration(TpaperRegistation)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.ManagerFeeService#updatePaperRegistration(application.entities.TpaperRegistation)
     */
    public void updatePaperRegistration(TpaperRegistation paperRegistration)
            throws BusinessServiceException {
        try {
            getPaperRegistrationDao().update(paperRegistration);
            Tpaper _tpaper = new Tpaper();
            _tpaper.setChvPaperNumber(paperRegistration.getChvPaperNumber());
            _tpaper.setBitIsRegisted(paperRegistration.getBitIsAllPayed());
            if (StringUtil.isNotBlank(paperRegistration.getChrPaperRegTableNo())) {
                _tpaper.setBitIsMailPaperReg(new Boolean(true));
            } else {
                _tpaper.setBitIsMailPaperReg(new Boolean(false));
            }
            _tpaper.setBitIsJuniorAward(paperRegistration.getBitIsAppJuniorAward());
            _tpaper.setBitIsPaperPrize(paperRegistration.getBitIsAppJaegerPrize());
            getPaperDao().updateIsPaperRegisted(_tpaper);
        } catch (DaoException e) {
            logger.error("updatePaperRegistration(TpaperRegistation)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("updatePaperRegistration(TpaperRegistation)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.ManagerFeeService#removePaperRegistration(application.entities.TpaperRegistation)
     */
    public void removePaperRegistration(TpaperRegistation paperRegistration)
            throws BusinessServiceException {
        try {
            getPaperRegistrationDao().remove(paperRegistration);
        } catch (DaoException e) {
            logger.error("removePaperRegistration(TpaperRegistation)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("removePaperRegistration(TpaperRegistation)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.ManagerFeeService#loadRegistrationFeeByPartiNo(java.lang.String)
     */
    public TregistrationFee loadRegistrationFeeByPartiNo(String partiNo)
            throws BusinessServiceException {
        TregistrationFee _tregistrationFee = new TregistrationFee();
        try {
            _tregistrationFee = getRegistrationFeeDao().findByParticipantNo(
                    partiNo);
            if (_tregistrationFee == null) {
                _tregistrationFee = new TregistrationFee();
                _tregistrationFee.setChrPartiNo(partiNo);
                _tregistrationFee.setChrPaymentMethodNo("1");
                getRegistrationFeeDao().create(_tregistrationFee);
                _tregistrationFee = getRegistrationFeeDao().findByParticipantNo(
                        partiNo);
            }
            int _nAccompanyPersons = getAccompanyPersonDao()
                    .countByParticipantNo(partiNo);
            if (!_tregistrationFee.getBitIsRegFeeReceived().booleanValue()) {
                _tregistrationFee.setInyAccPerson(new Integer(
                        _nAccompanyPersons));
            }
        } catch (DaoException e) {
            logger.error("loadRegistrationFeeByPartiNo(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadRegistrationFeeByPartiNo(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
        return _tregistrationFee;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.ManagerFeeService#loadRegistrationFeeByPaperNumber(java.lang.String)
     */
    public TregistrationFee[] loadRegistrationFeeByPaperNumber(
            String paperNumber) throws BusinessServiceException {
        TregistrationFee[] _tregistrationFees = new TregistrationFee[0];
        try {
            _tregistrationFees = getRegistrationFeeDao().findByPaperNumber(
                    paperNumber);
        } catch (DaoException e) {
            logger.error("loadRegistrationFeeByPaperNumber(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadRegistrationFeeByPaperNumber(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
        return _tregistrationFees;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.ManagerFeeService#createRegistrationFee(application.entities.TregistrationFee)
     */
    public void createRegistrationFee(TregistrationFee tregistrationFee)
            throws BusinessServiceException {
        try {
            getRegistrationFeeDao().create(tregistrationFee);
        } catch (DaoException e) {
            logger.error("createRegistrationFee(TregistrationFee)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("createRegistrationFee(TregistrationFee)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.ManagerFeeService#updateRegistrationFee(application.entities.TregistrationFee)
     */
    public void updateRegistrationFee(TregistrationFee tregistrationFee)
            throws BusinessServiceException {
        try {
            getRegistrationFeeDao().update(tregistrationFee);
        } catch (DaoException e) {
            logger.error("updateRegistrationFee(TregistrationFee)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("updateRegistrationFee(TregistrationFee)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.ManagerFeeService#removeRegistrationFee(application.entities.TregistrationFee)
     */
    public void removeRegistrationFee(TregistrationFee tregistrationFee)
            throws BusinessServiceException {
        try {
            getRegistrationFeeDao().remove(tregistrationFee);
        } catch (DaoException e) {
            logger.error("removeRegistrationFee(TregistrationFee)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("removeRegistrationFee(TregistrationFee)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
    }

}
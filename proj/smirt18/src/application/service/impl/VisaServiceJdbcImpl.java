/*
 * @(#)VisaServiceJdbcImpl.java  2005-3-9
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
import application.dao.ParticipantDao;
import application.dao.VisaDao;
import application.entities.TaccompanyPerson;
import application.entities.Tparticipant;
import application.entities.Tvisa;
import application.exception.BusinessServiceException;
import application.service.VisaService;
import application.util.EntitiesTransUtil;
import application.viewdata.VisaView;
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
public class VisaServiceJdbcImpl extends JdbcServiceImpl implements VisaService {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(VisaServiceJdbcImpl.class);

    private ParticipantDao m_participantDao;

    private AccompanyPersonDao m_accompanyPersonDao;

    private VisaDao m_visaDao;

    public AccompanyPersonDao getAccompanyPersonDao() {
        return this.m_accompanyPersonDao;
    }

    public void setAccompanyPersonDao(AccompanyPersonDao accompanyPersonDao) {
        this.m_accompanyPersonDao = accompanyPersonDao;
    }

    public ParticipantDao getParticipantDao() {
        return this.m_participantDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.m_participantDao = participantDao;
    }

    public VisaDao getVisaDao() {
        return this.m_visaDao;
    }

    public void setVisaDao(VisaDao visaDao) {
        this.m_visaDao = visaDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.VisaService#loadVisaByVisaNo(java.lang.String)
     */
    public VisaView loadVisaByVisaNo(String visaNo)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("loadVisaByVisaNo(String) - start");
        }

        try {
            Tvisa _visa = getVisaDao().findByVisaNo(visaNo);
            VisaView _visaView = new VisaView();
            BeanUtils.copyProperties(_visaView, _visa);
            if (StringUtil.isBlank(_visaView.getChrAccPersonNo())) {
                Tparticipant _tparticipant = getParticipantDao()
                        .findByParticipantNo(_visa.getChrPartiNo());
                _visaView.setTitle(_tparticipant.getChvPartiTitle());
                _visaView.setFirstName(_tparticipant.getChvPartiFirstName());
                _visaView.setLastName(_tparticipant.getChvPartiLastName());
                _visaView.setMiddleName(_tparticipant.getChvPartiMiddleName());
                _visaView.setPassportNumber(_tparticipant
                        .getChvPassportNumber());
            } else {
                TaccompanyPerson _taccompanyPerson = getAccompanyPersonDao()
                        .findByAccompanyPersonNo(_visa.getChrAccPersonNo());
                _visaView.setTitle(_taccompanyPerson.getChvAccPersonTitle());
                _visaView.setFirstName(_taccompanyPerson
                        .getChvAccPersonFirstName());
                _visaView.setLastName(_taccompanyPerson
                        .getChvAccPersonLastName());
                _visaView.setMiddleName(_taccompanyPerson
                        .getChvAccPersonMiddleName());
                _visaView.setPassportNumber(_taccompanyPerson
                        .getChvPassportNumber());
            }

            if (logger.isDebugEnabled()) {
                logger.debug("loadVisaByVisaNo(String) - end");
            }
            return _visaView;
        } catch (DaoException e) {
            logger.error("loadVisaByVisaNo(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadVisaByVisaNo(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.VisaService#loadVisaByPartiNo(java.lang.String)
     */
    public VisaView[] loadVisaByPartiNo(String partiNo)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("loadVisaByPartiNo(String) - start");
        }

        try {
            Tparticipant _tparticipant = getParticipantDao()
                    .findByParticipantNo(partiNo);
            TaccompanyPerson[] _arrayAccompanyPerson = getAccompanyPersonDao()
                    .findByParticipantNo(partiNo);
            VisaView[] _arrayVisaView = new VisaView[1 + _arrayAccompanyPerson.length];
            Tvisa _tvisa = getVisaDao()
                    .findByPartiNoAndAccPersonNo(partiNo, "");
            int _nIndex = 0;
            _arrayVisaView[_nIndex] = new VisaView();
            if (_tvisa != null) {
                BeanUtils.copyProperties(_arrayVisaView[_nIndex], _tvisa);
            }
            _arrayVisaView[_nIndex].setChrPartiNo(partiNo);
            _arrayVisaView[_nIndex].setTitle(_tparticipant.getChvPartiTitle());
            _arrayVisaView[_nIndex].setFirstName(_tparticipant
                    .getChvPartiFirstName());
            _arrayVisaView[_nIndex].setLastName(_tparticipant
                    .getChvPartiLastName());
            _arrayVisaView[_nIndex].setMiddleName(_tparticipant
                    .getChvPartiMiddleName());
            _arrayVisaView[_nIndex].setPassportNumber(_tparticipant
                    .getChvPassportNumber());
            _arrayVisaView[_nIndex].setChrAccPersonNo("");
            if (StringUtil.isBlank(_arrayVisaView[_nIndex]
                    .getChvVisaNationality())) {
                _arrayVisaView[_nIndex].setChvVisaNationality(_tparticipant
                        .getChvPartiCountry());
            }
            _nIndex++;

            for (int i = 0; i < _arrayAccompanyPerson.length; i++) {
                _arrayVisaView[_nIndex] = new VisaView();
                _arrayVisaView[_nIndex].setChrPartiNo(partiNo);
                _tvisa = getVisaDao().findByPartiNoAndAccPersonNo(partiNo,
                        _arrayAccompanyPerson[i].getChrAccPersonNo());
                if (_tvisa != null) {
                    BeanUtils.copyProperties(_arrayVisaView[_nIndex], _tvisa);
                }
                _arrayVisaView[_nIndex]
                        .setChrAccPersonNo(_arrayAccompanyPerson[i]
                                .getChrAccPersonNo());
                _arrayVisaView[_nIndex].setTitle(_arrayAccompanyPerson[i]
                        .getChvAccPersonTitle());
                _arrayVisaView[_nIndex].setFirstName(_arrayAccompanyPerson[i]
                        .getChvAccPersonFirstName());
                _arrayVisaView[_nIndex].setLastName(_arrayAccompanyPerson[i]
                        .getChvAccPersonLastName());
                _arrayVisaView[_nIndex].setMiddleName(_arrayAccompanyPerson[i]
                        .getChvAccPersonMiddleName());
                _arrayVisaView[_nIndex]
                        .setPassportNumber(_arrayAccompanyPerson[i]
                                .getChvPassportNumber());
                if (StringUtil.isBlank(_arrayVisaView[_nIndex]
                        .getChvVisaNationality())) {
                    _arrayVisaView[_nIndex].setChvVisaNationality(_tparticipant
                            .getChvPartiCountry());
                }
                _nIndex++;
            }

            if (logger.isDebugEnabled()) {
                logger.debug("loadVisaByPartiNo() - visaViews length = "
                        + _arrayVisaView.length);
            }

            _arrayVisaView = EntitiesTransUtil
                    .removeDuplicateArray(_arrayVisaView);

            if (logger.isDebugEnabled()) {
                logger.debug("loadVisaByPartiNo() - visaViews length2 = "
                        + _arrayVisaView.length);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("loadVisaByPartiNo(String) - end");
            }
            return _arrayVisaView;
        } catch (DaoException e) {
            logger.error("loadVisaByPartiNo(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadVisaByPartiNo(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.VisaService#loadVisaByPartiNoAndAccPersonNo(java.lang.String,
     *      java.lang.String)
     */
    public VisaView loadVisaByPartiNoAndAccPersonNo(String partiNo,
            String accPersonNo) throws BusinessServiceException {
        try {
            VisaView _visaView = new VisaView();
            _visaView.setChrPartiNo(partiNo);
            Tvisa _tvisa = getVisaDao().findByPartiNoAndAccPersonNo(partiNo,
                    accPersonNo);
            if (_tvisa != null) {
                BeanUtils.copyProperties(_visaView, _tvisa);
            }
            Tparticipant _tparticipant = getParticipantDao()
                    .findByParticipantNo(partiNo);
            if (StringUtil.isBlank(accPersonNo)) {
                _visaView.setTitle(_tparticipant.getChvPartiTitle());
                _visaView.setFirstName(_tparticipant.getChvPartiFirstName());
                _visaView.setLastName(_tparticipant.getChvPartiLastName());
                _visaView.setMiddleName(_tparticipant.getChvPartiMiddleName());
                _visaView.setPassportNumber(_tparticipant
                        .getChvPassportNumber());
                _visaView.setChrAccPersonNo("");
                if (StringUtil.isBlank(_visaView.getChvVisaNationality())) {
                    _visaView.setChvVisaNationality(_tparticipant
                            .getChvPartiCountry());
                }
            } else {
                TaccompanyPerson _taccompanyPerson = getAccompanyPersonDao()
                        .findByAccompanyPersonNo(accPersonNo);
                _visaView.setChrAccPersonNo(_taccompanyPerson
                        .getChrAccPersonNo());
                _visaView.setTitle(_taccompanyPerson.getChvAccPersonTitle());
                _visaView.setFirstName(_taccompanyPerson
                        .getChvAccPersonFirstName());
                _visaView.setLastName(_taccompanyPerson
                        .getChvAccPersonLastName());
                _visaView.setMiddleName(_taccompanyPerson
                        .getChvAccPersonMiddleName());
                _visaView.setPassportNumber(_taccompanyPerson
                        .getChvPassportNumber());
                if (StringUtil.isBlank(_visaView.getChvVisaNationality())) {
                    _visaView.setChvVisaNationality(_tparticipant
                            .getChvPartiCountry());
                }

            }
            return _visaView;
        } catch (DaoException e) {
            logger.error("loadVisaByPartiNoAndAccPersonNo(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadVisaByPartiNoAndAccPersonNo(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.VisaService#createVisa(application.entities.Tvisa)
     */
    public void createVisa(Tvisa visa) throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("createVisa(Tvisa) - start");
        }

        try {
            getVisaDao().create(visa);
        } catch (DaoException e) {
            logger.error("createVisa(Tvisa)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("createVisa(Tvisa)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("createVisa(Tvisa) - end");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.VisaService#updateVisa(application.entities.Tvisa)
     */
    public void updateVisa(Tvisa visa) throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("updateVisa(Tvisa) - start");
        }

        try {
            getVisaDao().update(visa);
        } catch (DaoException e) {
            logger.error("updateVisa(Tvisa)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("updateVisa(Tvisa)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("updateVisa(Tvisa) - end");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.VisaService#removeVisa(application.entities.Tvisa)
     */
    public void removeVisa(Tvisa visa) throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("removeVisa(Tvisa) - start");
        }

        try {
            getVisaDao().remove(visa);
        } catch (DaoException e) {
            logger.error("removeVisa(Tvisa)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("removeVisa(Tvisa)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("removeVisa(Tvisa) - end");
        }
    }

}
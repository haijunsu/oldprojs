/*
 * @(#)ServicesUtil.java  2005-3-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.util;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

import application.dao.AccompanyPersonDao;
import application.dao.CountryDao;
import application.dao.EquipmentsDao;
import application.dao.PaperDao;
import application.dao.ParticipantDao;
import application.dao.PaymentMethodDao;
import application.dao.RegistrationFeeDao;
import application.dao.RelationBetweenParticipantAndPaperDao;
import application.entities.Tcountry;
import application.entities.Tequipments;
import application.entities.Tpaper;
import application.entities.Tparticipant;
import application.entities.TpaymentMethod;
import application.exception.BusinessServiceException;
import application.helper.BeansLocator;
import application.helper.ResourcesHelper;
import application.viewdata.SmallPaperView;
import application.viewdata.SmallUserView;
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
public class ServicesUtil {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(ServicesUtil.class);

    public static String getCountryName(String countryNo) {
        String _countryName = "";
        try {
            if (StringUtil.isNotBlank(countryNo)) {
                CountryDao _country = (CountryDao) BeansLocator
                        .getBeanResource("countryDao");
                _countryName = _country.findByCountryNo(countryNo)
                        .getChvCountryName();
            }
        } catch (Exception e) {
            logger.error("getCountryName(String)", e);

        }
        return _countryName;
    }

    public static Tcountry[] getAllCountry() {
        Tcountry[] _arrayCountry = new Tcountry[0];
        try {
            CountryDao _country = (CountryDao) BeansLocator
                    .getBeanResource("countryDao");
            _arrayCountry = (Tcountry[]) _country.listEntities(null,
                    "chvCountryName");

        } catch (Exception e) {
            logger.error("getAllCountry()", e);
        }

        return _arrayCountry;
    }

    public static Collection getCountryLabelValueBeans() {
        if (logger.isDebugEnabled()) {
            logger.debug("getCountries() - start");
        }
        try {
            Collection _countries = new ArrayList();
            Tcountry[] _arrayCountry = ServicesUtil.getAllCountry();
            for (int i = 0; i < _arrayCountry.length; i++) {
                _countries.add(new LabelValueBean(_arrayCountry[i]
                        .getChvCountryName(), _arrayCountry[i]
                        .getChrCountryNo()));
            }
            if (logger.isDebugEnabled()) {
                logger.debug("getCountries() - end");
            }
            return _countries;
        } catch (Exception e) {
            logger.error("getCountries()", e);
            return new ArrayList();
        }
    }

    public static String getEquipmentName(String equipmentNo) {
        String _strName = "";
        try {
            if (StringUtil.isNotBlank(equipmentNo)) {
                _strName = ResourcesHelper.getFormsResources().getMessage(
                        "form.equipment." + equipmentNo.trim());
            }
        } catch (Exception e) {
            logger.error("getEquipmentName(String)", e);
        }
        return _strName;
    }

    public static Tequipments[] getAllEquipment() {
        Tequipments[] _tequipments = new Tequipments[0];
        try {
            EquipmentsDao _dao = (EquipmentsDao) BeansLocator
                    .getBeanResource("equipmentsDao");
            _tequipments = (Tequipments[]) _dao.listEntities(null,
                    "chrEquipmentNo");
        } catch (Exception e) {
            logger.error("getAllEquipment()", e);
        }
        return _tequipments;
    }

    public static Collection getEquipmentLabelValueBeans() {
        try {
            Collection _equipments = new ArrayList();
            Tequipments[] _tequipments = getAllEquipment();
            for (int i = 0; i < _tequipments.length; i++) {
                _equipments.add(new LabelValueBean(
                        getEquipmentName(_tequipments[i].getChrEquipmentNo()),
                        _tequipments[i].getChrEquipmentNo()));
            }
            if (logger.isDebugEnabled()) {
                logger.debug("getEquipmentLabelValueBeans() - end");
            }
            return _equipments;
        } catch (Exception e) {
            logger.error("getEquipmentLabelValueBeans()", e);
            return new ArrayList();
        }

    }

    public static String getPaymentMethodName(String paymentMethodNo) {
        String _strName = "";
        try {
            if (StringUtil.isNotBlank(paymentMethodNo)) {
                _strName = ResourcesHelper.getFormsResources().getMessage(
                        "form.payment.method." + paymentMethodNo.trim());
            }
        } catch (Exception e) {
            logger.error("getPaymentMethodName(String)", e);
        }
        return _strName;
    }

    public static TpaymentMethod[] getAllPaymentMethod() {
        TpaymentMethod[] _tpaymentMethods = new TpaymentMethod[0];
        try {
            PaymentMethodDao _dao = (PaymentMethodDao) BeansLocator
                    .getBeanResource("paymentMethodDao");
            _tpaymentMethods = (TpaymentMethod[]) _dao.listEntities(null,
                    "chrPaymentMethodNo");
        } catch (Exception e) {
            logger.error("getAllPaymentMethod()", e);
        }
        return _tpaymentMethods;
    }

    public static Collection getPaymentMethodLabelValueBeans() {
        try {
            Collection _paymentMethods = new ArrayList();
            TpaymentMethod[] _tpaymentMethods = getAllPaymentMethod();
            for (int i = 0; i < _tpaymentMethods.length; i++) {
                _paymentMethods.add(new LabelValueBean(
                        getPaymentMethodName(_tpaymentMethods[i]
                                .getChrPaymentMethodNo()), _tpaymentMethods[i]
                                .getChrPaymentMethodNo()));
            }
            if (logger.isDebugEnabled()) {
                logger.debug("getPaymentMethodLabelValueBeans() - end");
            }
            return _paymentMethods;
        } catch (Exception e) {
            logger.error("getPaymentMethodLabelValueBeans()", e);
            return new ArrayList();
        }
    }
    
    private static final String FEE_TYPE_PREFIX = "form.parti.fee.";

    public static Collection getFeeTypeLabelValueBeans() {
        return getLabelValueBeansByPrefix(FEE_TYPE_PREFIX);
    }

    public static String getFeeValueByType(String feeTypeNo) {
        String _strName = "0";
        try {
            if (StringUtil.isNotBlank(feeTypeNo)) {
                _strName = ResourcesHelper.getFormsResources().getMessage(
                        FEE_TYPE_PREFIX + feeTypeNo.trim());
            }
        } catch (Exception e) {
            logger.error("getHotelRoomTypeName() - Exception", e);
        }
        if (StringUtil.isBlank(_strName)) {
            _strName = "0";
        }
        return _strName;
    }

    
    private static final String HOTEL_ROOM_TYPE_PREFIX = "form.hotel.room.type.";

    public static Collection getHotelRoomTypeLabelValueBeans() {
        return getLabelValueBeansByPrefix(HOTEL_ROOM_TYPE_PREFIX);
    }

    public static String getHotelRoomTypeName(String roomTypeNo) {
        String _strName = "";
        try {
            if (StringUtil.isNotBlank(roomTypeNo)) {
                _strName = ResourcesHelper.getFormsResources().getMessage(
                        HOTEL_ROOM_TYPE_PREFIX + roomTypeNo.trim());
            }
        } catch (Exception e) {
            logger.error("getHotelRoomTypeName() - Exception", e);
        }
        return _strName;
    }

    private static final String MONEY_TYPE_PREFIX = "form.money.type.";

    public static Collection getMoneyTypeLabelValueBeans() {
        return getLabelValueBeansByPrefix(MONEY_TYPE_PREFIX);
    }

    public static String getMoneyTypeName(String moneyTypeNo) {
        String _strName = "";
        try {
            if (StringUtil.isNotBlank(moneyTypeNo)) {
                _strName = ResourcesHelper.getFormsResources().getMessage(
                        MONEY_TYPE_PREFIX + moneyTypeNo.trim());
            }
        } catch (Exception e) {
            logger.error("getMoneyTypeLabelValueBeans() - Exception", e);
        }
        return _strName;
    }

    private static Collection getLabelValueBeansByPrefix(String prefix) {
        Collection _collection = new ArrayList();
        try {
            int _nTypesCount = Integer.parseInt(ResourcesHelper
                    .getFormsResources().getMessage(prefix + "count"));
            for (int i = 0; i < _nTypesCount; i++) {
                _collection.add(new LabelValueBean(ResourcesHelper
                        .getFormsResources().getMessage(prefix + i), String
                        .valueOf(i)));
            }
        } catch (Exception e) {
            logger.error("getLabelValueBeansByPrefix() - Exception", e);
        }
        return _collection;
    }

    public static SmallUserView getSmallUserViewByPartiNo(String partiNo)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("loadSmallUserViewByPartiNo(String) - start");
        }

        try {
            SmallUserView _smallUserView = new SmallUserView();
            ParticipantDao _participantDao = (ParticipantDao) BeansLocator
                    .getBeanResource("participantDao");
            Tparticipant _tparticipant = _participantDao
                    .findByParticipantNo(partiNo);
            if (_tparticipant != null) {
                BeanUtils.copyProperties(_smallUserView, _tparticipant);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("loadSmallUserViewByPartiNo(String) - end");
            }
            return _smallUserView;
        } catch (DaoException e) {
            logger.error("loadSmallUserViewByPartiNo(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("loadSmallUserViewByPartiNo(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

    }

    public static SmallPaperView getSmallPaperViewByPaperNumber(
            String paperNumber) throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("getSmallPaperViewByPaperNumber(String) - start");
        }

        try {
            SmallPaperView _smallPaperView = new SmallPaperView();
            PaperDao _paperDao = (PaperDao) BeansLocator
                    .getBeanResource("paperDao");
            Tpaper _tpaper = _paperDao.findByPaperNumber(paperNumber);
            if (_tpaper != null) {
                BeanUtils.copyProperties(_smallPaperView, _tpaper);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("getSmallPaperViewByPaperNumber(String) - end");
            }
            return _smallPaperView;
        } catch (DaoException e) {
            logger.error("getSmallPaperViewByPaperNumber(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("getSmallPaperViewByPaperNumber(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }

    }

    public static int getAccompanyPersonCount(String partiNo)
            throws BusinessServiceException {
        int _nCount = 0;
        try {
            AccompanyPersonDao _accompanyPersonDao = (AccompanyPersonDao) BeansLocator
                    .getBeanResource("accompanyPersonDao");
            _nCount = _accompanyPersonDao.countByParticipantNo(partiNo);
        } catch (DaoException e) {
            logger.error("getAccompanyPersonCount(String)", e);

            throw new BusinessServiceException(99, e.getMessage());
        } catch (Exception e) {
            logger.error("getAccompanyPersonCount(String)", e);

            throw new BusinessServiceException(97, e.getMessage());
        }
        return _nCount;
    }

    public static String getGrantPaperLNumberOfParticipant(String partiNo) {
        RegistrationFeeDao _registrationFeeDao = (RegistrationFeeDao) BeansLocator
                .getBeanResource("registrationFeeDao");
        return _registrationFeeDao
                .getGrantedPaperLNumberOfParticipantNo(partiNo);
    }

    public static boolean isRegFeeReceivedOfParticipant(String partiNo) {
        RegistrationFeeDao _registrationFeeDao = (RegistrationFeeDao) BeansLocator
                .getBeanResource("registrationFeeDao");
        return _registrationFeeDao.isRegFeeReceivedOfParticipantNo(partiNo);
    }

    public static Integer getIntUserIdByPartiNo(String partiNo, String paperNumber) {
        try {
            RelationBetweenParticipantAndPaperDao _dao = (RelationBetweenParticipantAndPaperDao) BeansLocator
                    .getBeanResource("relationBetweenParticipantAndPaperDao");
            return _dao.findUseridByPartiNo(partiNo, paperNumber);
        } catch (Exception e) {
            return null;
        }
    }

}
/*
 * @(#)PayMentMethodDaoImpl.java  2005-2-20
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.PaymentMethodDao;
import application.entities.TpaymentMethod;

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
public class PaymentMethodDaoImpl extends BaseDaoJdbcImpl implements
        PaymentMethodDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(PaymentMethodDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PayMentMethodDao#create(application.entities.TpayMentMethod)
     */
    public TpaymentMethod create(TpaymentMethod paymentMethodEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("create(TpaymentMethod) - start");
        }

        paymentMethodEntity.setChrPaymentMethodNo(genStringKey());
        insertEntity(paymentMethodEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("create(TpayMentMethod) - end");
        }
        return paymentMethodEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PayMentMethodDao#load(java.lang.String)
     */
    public TpaymentMethod load(String paymentMethodNo) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - start");
        }

        String _strCondition = "chrPaymentMethodNo = '"
                + StringUtil.escapeSql(paymentMethodNo) + "'";
        TpaymentMethod _payMentMethod = (TpaymentMethod)listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - end");
        }
        return _payMentMethod;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PayMentMethodDao#update(application.entities.TpayMentMethod)
     */
    public int update(TpaymentMethod paymentMethodEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("update(TpaymentMethod) - start");
        }

        int _nRtn = updateEntity(paymentMethodEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("update(TpaymentMethod) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PayMentMethodDao#remove(application.entities.TpayMentMethod)
     */
    public int remove(TpaymentMethod paymentMethodEntity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove(TpaymentMethod) - start");
        }

        int _nRtn = removeEntity(paymentMethodEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("remove(TpaymentMethod) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PayMentMethodDao#findByPaymentMethodNo(java.lang.String)
     */
    public TpaymentMethod findByPaymentMethodNo(String paymentMethodNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaymentMethodNo(String) - start");
        }

        TpaymentMethod _payMentMethod = load(paymentMethodNo);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaymentMethodNo(String) - end");
        }
        return _payMentMethod;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PayMentMethodDao#findByPaymentMethod(java.lang.String)
     */
    public TpaymentMethod[] findByPaymentMethod(String paymentMethod)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaymentMethod(String) - start");
        }

        String _strCondition = "chvPaymentMethod = '"
                + StringUtil.escapeSql(paymentMethod) + "'";
        TpaymentMethod[] _arrayPayMethod = (TpaymentMethod[])listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaymentMethod(String) - end");
        }
        return _arrayPayMethod;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PayMentMethodDao#listEntities(java.lang.String,
     *      java.lang.String)
     */
    public Object[] listEntities(String condition, String orderBy)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - start");
        }

        List _listRs = find(condition, orderBy);
        TpaymentMethod[] _arrayPayMethod = new TpaymentMethod[_listRs.size()];
        for (int i = 0; i < _arrayPayMethod.length; i++) {
            _arrayPayMethod[i] = new TpaymentMethod();
        }
        SQLHelper.listToEntities(_listRs, _arrayPayMethod);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayPayMethod;
    }

}
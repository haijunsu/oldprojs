/*
 * @(#)PayMentMethodDao.java  2005-2-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.TpaymentMethod;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface PaymentMethodDao extends BaseDao {
    
    public TpaymentMethod create(TpaymentMethod payMentMethodEntity) throws DaoException;
    
    public TpaymentMethod load(String paymentMethodNo) throws DaoException;
    
    public int update(TpaymentMethod payMentMethodEntity) throws DaoException;
    
    public int remove(TpaymentMethod payMentMethodEntity) throws DaoException;
    
    public TpaymentMethod findByPaymentMethodNo(String paymentMethodNo) throws DaoException;
    
    public TpaymentMethod[] findByPaymentMethod(String paymentMethod) throws DaoException;
}

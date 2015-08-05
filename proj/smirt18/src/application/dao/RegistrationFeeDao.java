/*
 * @(#)RegistrationFeeDao.java  2005-2-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.TregistrationFee;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface RegistrationFeeDao extends BaseDao {
    
    public TregistrationFee create(TregistrationFee registrationFeeEntity) throws DaoException;
    
    public TregistrationFee load(String registrationFeeNo) throws DaoException;
    
    public int update(TregistrationFee registrationFeeEntity) throws DaoException;
    
    public int remove(TregistrationFee registrationFeeEntity) throws DaoException;
    
    public TregistrationFee findByRegistrationFeeNo(String registrationFeeNo) throws DaoException;
    
    public TregistrationFee findByParticipantNo(String participantNo) throws DaoException;
    
    public TregistrationFee[] findByPaperNumber(String paperNumber) throws DaoException;
    
    public String getGrantedPaperLNumberOfParticipantNo(String participantNo) throws DaoException;
    
    public boolean isRegFeeReceivedOfParticipantNo(String participantNo) throws DaoException;
    
}

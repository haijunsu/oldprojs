/*
 * @(#)VisaDao.java  2005-2-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.Tvisa;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface VisaDao extends BaseDao {
    
    public Tvisa create(Tvisa visaEntity) throws DaoException;
    
    public Tvisa load(String visaNo) throws DaoException;
    
    public int update(Tvisa visaEntity) throws DaoException;
    
    public int remove(Tvisa visaEntity) throws DaoException;
    
    public Tvisa findByVisaNo(String visaNo) throws DaoException;

    public Tvisa findByPartiNoAndAccPersonNo(String participantNo, String accPersonNo) throws DaoException;

    public Tvisa[] findByParticipantNo(String participantNo) throws DaoException;
    
    public Tvisa[] findByAccPersonNo(String accPersonNo) throws DaoException;
    
    public Tvisa[] findByPassportNumber(String passportNumber) throws DaoException;
}

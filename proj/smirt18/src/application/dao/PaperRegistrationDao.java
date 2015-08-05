/*
 * @(#)PaperRegistrationDao.java  2005-2-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.TpaperRegistation;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface PaperRegistrationDao extends BaseDao {
    
    public TpaperRegistation create(TpaperRegistation paperRegistationEntity) throws DaoException;
    
    public TpaperRegistation load(String paperRegistrationNo) throws DaoException;
    
    public int update(TpaperRegistation paperRegistationEntity) throws DaoException;
    
    public int remove(TpaperRegistation paperRegistationEntity) throws DaoException;
    
    public TpaperRegistation findByPaperRegNo(String paperRegNo) throws DaoException;
    
    public TpaperRegistation findByPaperRegTableNo(String paperRegTableNo) throws DaoException;
    
    public TpaperRegistation findByPaperNumber(String paperNumber) throws DaoException;
    
    public TpaperRegistation findByPaperLNumber(String paperLNumber) throws DaoException;
    
    public TpaperRegistation[] findByPaperRegFirstName(String paperRegFirstName) throws DaoException;
    
    public TpaperRegistation[] findByPaperRegLastName(String paperRegLastName) throws DaoException;
    
    public TpaperRegistation[] findByPaperRegEmail(String paperRegEmail) throws DaoException;
    
    public TpaperRegistation[] findByPaperRegEmail(String paperRegEmail, boolean isExact) throws DaoException;

    public TpaperRegistation[] findByPaperRegBankName(String paperRegBankName) throws DaoException;

}

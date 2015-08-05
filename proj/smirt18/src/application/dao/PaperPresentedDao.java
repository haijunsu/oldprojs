/*
 * @(#)PaperPresentedDao.java  2005-2-26
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.TpaperPresented;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface PaperPresentedDao extends BaseDao {
    
    public TpaperPresented create(TpaperPresented paperPresentedEntity) throws DaoException;
    
    public TpaperPresented load(Integer index) throws DaoException;
    
    public int update(TpaperPresented paperPresentedEntity) throws DaoException;
    
    public int remove(TpaperPresented paperPresented) throws DaoException;
    
    public TpaperPresented findByIndex(Integer index) throws DaoException;
    
    public TpaperPresented findByPaperNumber(String paperNumber) throws DaoException;
    
    public TpaperPresented findByPaperLNumber(String paperLNumber) throws DaoException;
    
    public TpaperPresented[] findByPresenterPartiNo(String presenterPartiNo) throws DaoException;
    
    public TpaperPresented[] findByFirstName(String firstName) throws DaoException;
    
    public TpaperPresented[] findByLastName(String lastName) throws DaoException;
    
    public TpaperPresented[] findByEquipmentNo(String equipmentNo) throws DaoException;

}

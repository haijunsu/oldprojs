/*
 * @(#)PresentedPapersDao.java  2005-2-27
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;


import framework.exception.DaoException;
import application.entities.TpresentedPapers;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface PresentedPapersDao extends BaseDao {
    
    public TpresentedPapers create(TpresentedPapers tpresentedPapersEntity) throws DaoException;
    
    public TpresentedPapers load(String paperNumber) throws DaoException;
    
    public int update(TpresentedPapers tpresentedPapersEntity) throws DaoException;
    
    public int remove(TpresentedPapers tpresentedPapersEntity) throws DaoException;
    
    public TpresentedPapers findByPaerNumber(String paperNumber) throws DaoException;
    
    public TpresentedPapers[] findBySessionNo(String sessionNo) throws DaoException;
    
    public TpresentedPapers[] findByPresentationNumber(Integer presentationNumber) throws DaoException;
    
}

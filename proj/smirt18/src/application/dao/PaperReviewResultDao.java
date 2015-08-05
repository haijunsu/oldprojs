/*
 * @(#)PaperReviewResultDao.java  2005-2-26
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;


import framework.exception.DaoException;
import application.entities.TpaperReviewResult;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface PaperReviewResultDao extends BaseDao {
    
    public TpaperReviewResult create(TpaperReviewResult paperReviewResultEntity) throws DaoException;
    
    public TpaperReviewResult load(String paperNumber) throws DaoException;
    
    public int update(TpaperReviewResult paperReviewResultEntity) throws DaoException;
    
    public int remove(TpaperReviewResult paperReviewResultEntity) throws DaoException;
    
    public TpaperReviewResult findByPaperNumber(String paperNumber) throws DaoException;
    
}

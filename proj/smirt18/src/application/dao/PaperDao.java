/*
 * @(#)PaperDao.java  2005-2-11
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.Tpaper;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface PaperDao extends BaseDao {
    
    public Tpaper create(Tpaper paperEntity) throws DaoException;
    
    public Tpaper load(String id) throws DaoException;
    
    public int update(Tpaper paperEntity) throws DaoException;
    
    public int remove(Tpaper paperEntity) throws DaoException;
    
    public int updateIsPaperRegisted(Tpaper paperEntity) throws DaoException;

    public int updateIsPaperRecivedDetail(Tpaper paperEntity) throws DaoException;

    public Tpaper findByPaperLNumber(String paperLNumber) throws DaoException;
    
    public Tpaper[] findByPaperTitle(String paperTitle) throws DaoException;
    
    public Tpaper findByPaperNumber(String paperNumber) throws DaoException;
    
    public Tpaper[] findByUserid(Integer userid) throws DaoException;
    
    public Tpaper[] findByDivisionNo(String divisionNo) throws DaoException;
    
    public Tpaper[] findByKeyWords(String keyWords) throws DaoException;
    
    public Tpaper[] findByPartiNo(String PartiNo) throws DaoException;
    
    public Integer findAuthorRankByPaperNumberAndUserID(String paperNumber, Integer userid) throws DaoException;
    
    public Tpaper[] findAllPapersByUserid(Integer userid) throws DaoException;
    
    public Tpaper[] findAllPapersByEmail(String email) throws DaoException;
    
    public Tpaper[] findAllPapersByAuthorNo(String authorNo) throws DaoException;
    
}

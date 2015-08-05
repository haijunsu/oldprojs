/*
 * @(#)RelationBetweenParticipantAndPaperDao.java  2005-2-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.TrelationBetweenAuthorAndPaper;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface RelationBetweenAuthorAndPaperDao extends BaseDao {
    
    public TrelationBetweenAuthorAndPaper create(TrelationBetweenAuthorAndPaper RelationBetweenAuthorAndPaperEntity) throws DaoException;
    
    public TrelationBetweenAuthorAndPaper load(String authorNo, String paperNumber, Integer authorRank) throws DaoException;
    
    public int update(TrelationBetweenAuthorAndPaper relationBetweenAuthorAndPaperEntity) throws DaoException;
    
    public int remove(TrelationBetweenAuthorAndPaper relationBetweenAuthorAndPaperEntity) throws DaoException;
    
    public TrelationBetweenAuthorAndPaper[] findByAuthorNo(String authorNo) throws DaoException;
    
    public TrelationBetweenAuthorAndPaper[] findByPaperNumber(String paperNumber) throws DaoException;
    
    public TrelationBetweenAuthorAndPaper[] findByPaperLNumber(String paperLNumber) throws DaoException;
    
    public Integer findAuthorRankByAuthorNoAndPaperNumber(String authorno, String paperNumber) throws DaoException;
    
}

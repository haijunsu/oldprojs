/*
 * @(#)RelationBetweenParticipantAndPaperDao.java  2005-2-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.TrelationBetweenParticipantAndPaper;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface RelationBetweenParticipantAndPaperDao extends BaseDao {
    
    public TrelationBetweenParticipantAndPaper create(TrelationBetweenParticipantAndPaper relationBetweenParticipantAndPaperEntity) throws DaoException;
    
    public TrelationBetweenParticipantAndPaper load(String participantNo, String paperNumber, Integer authorRank) throws DaoException;
    
    public int update(TrelationBetweenParticipantAndPaper relationBetweenParticipantAndPaperEntity) throws DaoException;
    
    public int remove(TrelationBetweenParticipantAndPaper relationBetweenParticipantAndPaperEntity) throws DaoException;
    
    public TrelationBetweenParticipantAndPaper[] findByParticipantNo(String participantNo) throws DaoException;
    
    public TrelationBetweenParticipantAndPaper[] findByPaperNumber(String paperNumber) throws DaoException;
    
    public TrelationBetweenParticipantAndPaper[] findByUserID(Integer userID) throws DaoException;
    
    public Integer findAuthorRankByPartiNoAndPaperNumber(String paperNumber, String partiNo) throws DaoException;
    
    public Integer findUseridByPartiNo(String partiNo, String paperNumber) throws DaoException;

    public TrelationBetweenParticipantAndPaper findByPartiNoAndPaperNumber(String partiNo, String paperNumber) throws DaoException;
}

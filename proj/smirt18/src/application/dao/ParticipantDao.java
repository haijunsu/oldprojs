/*
 * @(#)ParticipantDao.java  2005-2-11
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.Tparticipant;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface ParticipantDao extends BaseDao {

    public Tparticipant create(Tparticipant participantEntity) throws DaoException;
    
    public Tparticipant load(String participantNo) throws DaoException;
    
    public Tparticipant findByEmailFirstNameLastName(String email, String firstName, String lastName) throws DaoException;
    
    public int update(Tparticipant participantEntity) throws DaoException;
    
    public int remove(Tparticipant participantEntity) throws DaoException;
    
    public Tparticipant findByParticipantNo(String participantNo) throws DaoException;
    
    public Tparticipant[] findByFirstName(String firstName) throws DaoException;
    
    public Tparticipant[] findByLastName(String lastName) throws DaoException;
    
    public Tparticipant[] findByEmail(String email) throws DaoException;
    
    public Tparticipant[] findByEmail(String email, boolean isExact) throws DaoException;

    public Tparticipant[] findByCountry(String country) throws DaoException;
    
    public Tparticipant[] findByPaperNumber(String paperNumber) throws DaoException;
    
    public Tparticipant[] findByAffiliation(String affiliation) throws DaoException;
    
}


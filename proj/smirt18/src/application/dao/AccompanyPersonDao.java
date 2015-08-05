/*
 * @(#)AccompanyPerson.java  2005-2-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.TaccompanyPerson;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface AccompanyPersonDao extends BaseDao {
    
    public TaccompanyPerson create(TaccompanyPerson accompanyPersonEntity) throws DaoException;
    
    public TaccompanyPerson load(String accompanyPersonNo) throws DaoException;
    
    public int update(TaccompanyPerson accompanyPersonEntity) throws DaoException;
    
    public int remove(TaccompanyPerson accompanyPersonEntity) throws DaoException;
    
    public TaccompanyPerson findByAccompanyPersonNo(String accompanyPersonNo) throws DaoException;
    
    public TaccompanyPerson findByPassportNumber(String passportNumber) throws DaoException;
    
    public TaccompanyPerson[] findByFirstName(String firstName) throws DaoException;
    
    public TaccompanyPerson[] findByLastName(String lastName) throws DaoException;
    
    public TaccompanyPerson[] findByParticipantNo(String participantNo) throws DaoException;
    
    public TaccompanyPerson[] listAll() throws DaoException;
    
    public int countByParticipantNo(String participantNo) throws DaoException;

}

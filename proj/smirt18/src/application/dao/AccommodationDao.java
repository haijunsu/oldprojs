/*
 * @(#)AccommodationDao.java  2005-2-26
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.Taccommodation;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface AccommodationDao extends BaseDao {
    
    public Taccommodation create(Taccommodation taccommodationEntity) throws DaoException;
    
    public Taccommodation load(String accommodationNo) throws DaoException;
    
    public int update(Taccommodation taccommodationEntity) throws DaoException;
    
    public int remove(Taccommodation taccommodationEntity) throws DaoException;
    
    public Taccommodation findByAccommodationNo(String accommodationNo) throws DaoException;
    
    public Taccommodation[] findByPartiNo(String partiNo) throws DaoException;
    
    public Taccommodation[] findByHotelName(String hotelName) throws DaoException;
    
    public Taccommodation findByRoomNumber(String hotelName, String roomNumber) throws DaoException;
    
    
    

}

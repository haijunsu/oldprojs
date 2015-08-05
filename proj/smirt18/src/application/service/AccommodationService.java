/*
 * @(#)AccommodationService.java  2005-3-4
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.service;

import application.entities.Taccommodation;
import application.exception.BusinessServiceException;
import application.viewdata.AccommodationView;
import application.viewdata.SmallUserView;
import framework.service.IService;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface AccommodationService extends IService {
    
    public SmallUserView loadOwnerByPartiNo(String partiNo) throws BusinessServiceException;
    
    public AccommodationView[] loadAccommodationByPartiNo(String partiNo) throws BusinessServiceException;
    
    public AccommodationView loadAccommodationByAccommodationNo(String accommodationNo) throws BusinessServiceException;
    
    public AccommodationView[] loadAccommodationByHotelName(String hotelName) throws BusinessServiceException;

    public AccommodationView loadAccommodationByHotelNameAndRoomNumber(String accommodationNo, String roomNumber) throws BusinessServiceException;
    
    public void createAccommodation(Taccommodation accommodation) throws BusinessServiceException;

    public void updateAccommodation(Taccommodation accommodation) throws BusinessServiceException;

    public void removeAccommodation(Taccommodation accommodation) throws BusinessServiceException;

}

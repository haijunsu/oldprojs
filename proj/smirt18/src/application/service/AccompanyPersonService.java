/*
 * @(#)AccompanyPersonService.java  2005-3-4
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.service;

import application.entities.TaccompanyPerson;
import application.exception.BusinessServiceException;
import framework.service.IService;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface AccompanyPersonService extends IService {
    
    public TaccompanyPerson loadAccompanyPersonByAccPersonNo(String accPersonNo) throws BusinessServiceException;
    
    public void createAccompanyPerson(TaccompanyPerson accompanyPerson) throws BusinessServiceException;

    public void updateAccompanyPerson(TaccompanyPerson accompanyPerson) throws BusinessServiceException;

    public void removeAccompanyPerson(TaccompanyPerson accompanyPerson) throws BusinessServiceException;
}

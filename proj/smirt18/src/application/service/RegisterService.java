/*
 * @(#)RegisterService.java  2005-2-21
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.service;

import application.entities.Tparticipant;
import application.exception.BusinessServiceException;
import application.viewdata.RegistrationView;
import framework.service.IService;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface RegisterService extends IService {
    
    public RegistrationView loadRegistrationByParticipantNo(String participantNo) throws BusinessServiceException;

    public RegistrationView loadRegistrationByAuthorNo(String authorNo) throws BusinessServiceException;

    public RegistrationView loadRegistrationByUserID(Integer userid) throws BusinessServiceException;
    
    public RegistrationView loadRegistrationByPaperNumberAndEmail(String paperNumber, String email) throws BusinessServiceException;
    
    public void updateParticipant(Tparticipant participant) throws BusinessServiceException;
    
    public void createParticipant(Tparticipant participant) throws BusinessServiceException;
    
    public void removeParticipant(Tparticipant participant) throws BusinessServiceException;

}

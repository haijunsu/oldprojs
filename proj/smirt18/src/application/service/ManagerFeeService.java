/*
 * @(#)ManagerFeeService.java  2005-3-4
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.service;

import application.entities.TpaperRegistation;
import application.entities.TregistrationFee;
import application.exception.BusinessServiceException;
import application.viewdata.SmallPaperView;
import framework.service.IService;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface ManagerFeeService extends IService {
    
    public SmallPaperView loadSmallPaperViewByPaperNumber(String paperNumber) throws BusinessServiceException;
    
    public TpaperRegistation loadPaperRegistrationByPaperNumber(String paperNumber) throws BusinessServiceException;
    
    public TpaperRegistation loadPaperRegistrationByPaperLNumber(String paperLNumber) throws BusinessServiceException;
    
    public TpaperRegistation loadPaperRegistrationByPaperRegNo(String paperRegNo) throws BusinessServiceException;
    
    public void createPaperRegistration(TpaperRegistation paperRegistration) throws BusinessServiceException;
    
    public void updatePaperRegistration(TpaperRegistation paperRegistration) throws BusinessServiceException;
    
    public void removePaperRegistration(TpaperRegistation paperRegistration) throws BusinessServiceException;
    
    public TregistrationFee loadRegistrationFeeByPartiNo(String partiNo) throws BusinessServiceException;
    
    public TregistrationFee[] loadRegistrationFeeByPaperNumber(String paperNumber) throws BusinessServiceException;
    
    public void createRegistrationFee(TregistrationFee tregistrationFee) throws BusinessServiceException;
    
    public void updateRegistrationFee(TregistrationFee tregistrationFee) throws BusinessServiceException;
    
    public void removeRegistrationFee(TregistrationFee tregistrationFee) throws BusinessServiceException;
}

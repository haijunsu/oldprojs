/*
 * @(#)VisaService.java  2005-3-4
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.service;

import application.entities.Tvisa;
import application.exception.BusinessServiceException;
import application.viewdata.VisaView;
import framework.service.IService;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface VisaService extends IService {
    
    public VisaView loadVisaByVisaNo(String visaNo) throws BusinessServiceException;
    
    public VisaView[] loadVisaByPartiNo(String partiNo) throws BusinessServiceException;
    
    public VisaView loadVisaByPartiNoAndAccPersonNo(String partiNo, String accPersonNo) throws BusinessServiceException;
    
    public void createVisa(Tvisa visa) throws BusinessServiceException;
    
    public void updateVisa(Tvisa visa) throws BusinessServiceException;
    
    public void removeVisa(Tvisa visa) throws BusinessServiceException;
}

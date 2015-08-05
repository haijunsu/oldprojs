/*
 * @(#)PaperPresentedService.java  2005-3-4
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.service;

import application.entities.TpaperPresented;
import application.exception.BusinessServiceException;
import framework.service.IService;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface PaperPresentedService extends IService {
    
    public TpaperPresented loadPaperPresentedByPaperNumber(String papaerNumber) throws BusinessServiceException;
    
    public TpaperPresented loadPaperPresentedByPaperLNumber(String paperLNumber) throws BusinessServiceException;
    
    public TpaperPresented[] loadPaperPresentedByPartiNo(String partiNo) throws BusinessServiceException;
    
    public TpaperPresented[] loadPaperPresentedByEquipmentNo(String equipmentNo) throws BusinessServiceException;
    
    public void createPaperPresented(TpaperPresented paperPresented) throws BusinessServiceException;
    
    public void updatePaperPresented(TpaperPresented paperPresented) throws BusinessServiceException;
    
    public void removePaperPresented(TpaperPresented paperPresented) throws BusinessServiceException;

}

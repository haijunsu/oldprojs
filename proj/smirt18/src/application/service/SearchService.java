/*
 * @(#)SearchService.java  2005-2-21
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header$
 * $Log$
 */
package application.service;

import application.exception.BusinessServiceException;
import application.viewdata.PaperView;
import application.viewdata.SmallPaperView;
import application.viewdata.SmallUserView;
import framework.service.IService;

/**
 * <p><b>Description</b></p>
 * <p></p>
 *
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface SearchService extends IService {

    public PaperView serachByPaperNumber(String paperNumber) throws BusinessServiceException;

    public PaperView serachByPaperLNumber(String paperLNumber) throws BusinessServiceException;

    public SmallPaperView[] searchByPaperTitle(String paperTitle) throws BusinessServiceException;

    public SmallUserView[] searchByUserAccount(String userAccount) throws BusinessServiceException;

    public SmallUserView[] searchByEmail(String email) throws BusinessServiceException;

    public SmallUserView[] searchByFirstName(String firstName) throws BusinessServiceException;

    public SmallUserView[] searchByLastName(String lastName) throws BusinessServiceException;

    public SmallUserView[] searchRegisterByEmail(String email) throws BusinessServiceException;

    public SmallUserView[] searchRegisterByFirstName(String firstName) throws BusinessServiceException;

    public SmallUserView[] searchRegisterByLastName(String lastName) throws BusinessServiceException;

    public SmallUserView[] searchByAffiliation(String affiliation) throws BusinessServiceException;

    public SmallUserView[] searchRegisterByAffiliation(String affiliation) throws BusinessServiceException;

}

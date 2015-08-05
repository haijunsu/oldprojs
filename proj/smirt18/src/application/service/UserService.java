/*
 * @(#)UserService.java  2005-1-10
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/application/service/UserService.java,v 1.1 2005/01/20 03:14:27 navy Exp $
 * $Log: UserService.java,v $
 * Revision 1.1  2005/01/20 03:14:27  navy
 * Create SMiRT 18 project
 *
 */
package application.service;

import framework.service.IService;
import application.entity.User;
import application.exception.BusinessServiceException;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * Business service for user action. All business motheds should be defined in
 * here.
 * </p>
 * 
 * $Revision: 1.1 $
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public interface UserService extends IService {
    public User registerNewUser(User userEntity)
            throws BusinessServiceException;

    public User userLogon(String userName, String password)
            throws BusinessServiceException;

    public User loadUserById(Long id) throws BusinessServiceException;

    public User updateUserInfo(User userEntity) throws BusinessServiceException;

    public User findUserByAccount(String account)
            throws BusinessServiceException;

    public User[] listAllUsers() throws BusinessServiceException;

    public void removeUser(Long id) throws BusinessServiceException;
}
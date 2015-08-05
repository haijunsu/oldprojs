/*
 * @(#)UserServiceImpl.java  2005-1-10
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/service/impl/UserServiceImpl.java,v 1.1 2005/06/14 10:29:24 navysu Exp $
 * $Log: UserServiceImpl.java,v $
 * Revision 1.1  2005/06/14 10:29:24  navysu
 * add application and framework etc.
 *
 * Revision 1.1  2005/01/20 03:14:20  navy
 * Create SMiRT 18 project
 *
 */
package application.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.UserDao;
import application.entity.User;
import application.exception.BusinessServiceException;
import application.service.UserService;
import framework.exception.DaoException;
import framework.service.impl.BaseServiceImpl;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * Implement user business action.
 * </p>
 * 
 * $Revision: 1.1 $
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

    private UserDao m_userDao;

    /*
     * (non-Javadoc)
     * 
     * @see application.service.UserService#registerNewUser(application.entity.User)
     */
    public User registerNewUser(User userEntity)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("registerNewUser(User) - start");
        }

        try {
            User _user = this.m_userDao.findByAccount(userEntity.getAccount());
            if (_user == null) {
                throw new BusinessServiceException(102,
                        "User account is existed. account = "
                                + userEntity.getAccount());
            } else {
                try {
                    this.m_userDao.create(userEntity);
                } catch (DaoException ex) {
                    logger.error("registerNewUser(User)", ex);

                    throw new BusinessServiceException(100,
                            "create new user fail.");
                }
            }
        } catch (DaoException e) {
            logger.error("registerNewUser(User)", e);

            throw new BusinessServiceException(99, "Access database error.");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("registerNewUser(User) - end");
        }
        return userEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.UserService#userLogon(java.lang.String,
     *      java.lang.String)
     */
    public User userLogon(String userName, String password)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("userLogon(String, String) - start");
        }

        User _user = null;
        try {
            _user = this.m_userDao.findByAccount(userName);
            if (_user == null) {
                throw new BusinessServiceException(101,
                        "Cannot find user. userName = " + userName);
            }
            if (!password.equals(_user.getPassword())) {
                throw new BusinessServiceException(103,
                        "Cannot match user's password. userName = " + userName);
            }
        } catch (DaoException e) {
            logger.error("userLogon(String, String)", e);
            throw new BusinessServiceException(99, "Access database error.");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("userLogon(String, String) - end");
        }
        return _user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.UserService#loadUserById(java.lang.Long)
     */
    public User loadUserById(Long id) throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("loadUserById(Long) - start");
        }

        User _user = null;
        try {
            _user = this.m_userDao.load(id);
            if (_user == null) {
            throw new BusinessServiceException(101,
                    "Cannot find user. user id = " + id);
            }
        } catch (DaoException e) {
            logger.error("loadUserById(Long)", e);

            throw new BusinessServiceException(99, "Access database error.");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("loadUserById(Long) - end");
        }
        return _user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.UserService#updateUserInfo(application.entity.User)
     */
    public User updateUserInfo(User userEntity) throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("updateUserInfo(User) - start");
        }

        try {
            this.m_userDao.update(userEntity);
        } catch (DaoException e) {
            logger.error("updateUserInfo(User)", e);

            throw new BusinessServiceException(99, "Access database error.");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("updateUserInfo(User) - end");
        }
        return userEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.UserService#findUserByAccount(java.lang.String)
     */
    public User findUserByAccount(String account)
            throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("findUserByAccount(String) - start");
        }

        User _user = null;
        try {
            _user = this.m_userDao.findByAccount(account);
            if (_user == null) {
            throw new BusinessServiceException(101,
                    "Cannot find user. userName = " + account);
            }
        } catch (DaoException e) {
            logger.error("findUserByAccount(String)", e);

            throw new BusinessServiceException(99, "Access database error.");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("findUserByAccount(String) - end");
        }
        return _user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.UserService#listAllUsers()
     */
    public User[] listAllUsers() throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("listAllUsers() - start");
        }

        User[] _user = null;
        try {
            _user = this.m_userDao.listAll();
            if (_user == null) {
            throw new BusinessServiceException(101, "User table is empty.");
            }

        } catch (DaoException e) {
            logger.error("listAllUsers()", e);

            throw new BusinessServiceException(99, "Access database error.");

        }

        if (logger.isDebugEnabled()) {
            logger.debug("listAllUsers() - end");
        }
        return _user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.service.UserService#removeUser(java.lang.Long)
     */
    public void removeUser(Long id) throws BusinessServiceException {
        if (logger.isDebugEnabled()) {
            logger.debug("removeUser(Long) - start");
        }

        try {
            User _user = this.m_userDao.load(id);
            this.m_userDao.remove(_user);
        } catch (DaoException e) {
            logger.error("removeUser(Long)", e);

            throw new BusinessServiceException(99, "Access database error.");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("removeUser(Long) - end");
        }
    }

    /**
     * @param userDao The userDao to set.
     */
    public void setUserDao(UserDao userDao) {
        this.m_userDao = userDao;
    }
}
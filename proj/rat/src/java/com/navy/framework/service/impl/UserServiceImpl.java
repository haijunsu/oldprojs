/*
 * @(#)UserServiceImpl.java  2006-12-14
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.service.impl;

import java.util.Date;

import com.navy.framework.dao.UserDao;
import com.navy.framework.exception.ServiceException;
import com.navy.framework.models.User;
import com.navy.framework.service.UserService;
import com.navy.framework.util.BeanUtilsExt;
import com.navy.framework.web.data.UserInfo;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 */
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	/**
	 * Logger for this class
	 */
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(UserServiceImpl.class);

	private UserDao m_userDao = null;

	/**
	 * @return the userDao
	 */
	private UserDao getUserDao() {
		return this.m_userDao;
	}

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		m_userDao = userDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.service.UserService#create(com.navy.app.rat.entities.User)
	 */
	public User create(User user) throws ServiceException {
		try {
			user = getUserDao().create(user);
		} catch (Exception e) {
			handleException(e, "1100", user.getUsername());
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.service.UserService#delete(com.navy.app.rat.entities.User)
	 */
	public void delete(User user) throws ServiceException {
		try {
			getUserDao().delete(user);
		} catch (Exception e) {
			handleException(e, "1101", user.getUsername());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.service.UserService#getUserById(java.lang.Long)
	 */
	public User getUserById(Long id) throws ServiceException {
		User _user = null;
		try {
			_user = getUserDao().get(id);
		} catch (Exception e) {
			handleException(e, "1102", id.toString());
		}
		return _user;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.service.UserService#getUserByUserid(java.lang.String)
	 */
	public User getUserByUserid(String userId) throws ServiceException {
		User _user = null;
		try {
			_user = getUserDao().get(userId);
		} catch (Exception e) {
			handleException(e, "1103", userId);
		}
		return _user;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.service.UserService#login(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public UserInfo login(String userId, String password, String userIP)
			throws ServiceException {
		User _user = getUserByUserid(userId);
		if (_user == null) {
			throw new ServiceException("1200", new Object[] { userId });
		}
		// verify password
		if (!password.equals(_user.getPassword())) {
			throw new ServiceException("1201");
		}
		// populate user info
		UserInfo _userInfo = new UserInfo();
		_userInfo.setCurrentIp(userIP);
		try {
			BeanUtilsExt.copyProperties(_userInfo, _user);
		} catch (Exception e) {
			logger.error(e);
		}

		// update lastAccessTime
		Date _date = new Date();
		getUserDao().update(_user);
		// create login log

		return _userInfo;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.service.UserService#update(com.navy.app.rat.entities.User)
	 */
	public void update(User user) throws ServiceException {
		try {
			getUserDao().update(user);
		} catch (Exception e) {
			handleException(e, "1104", user.getUsername());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.service.UserService#logout()
	 */
	public void logout(UserInfo userInfo) throws ServiceException {
		// create login log
		// AccessLog _accessLog = new AccessLog();
		// _accessLog.setUsername(userInfo.getUsername());
		// _accessLog.setUserFullname(userInfo.getUserFullname());
		// _accessLog.setIpAddress(userInfo.getCurrentIp());
		// // 1 login, 2 logout
		// _accessLog.setActionType(LoginActionEnum.LOGOUT.getIntValue());
		// getAccessLogDao().create(_accessLog);
	}
}

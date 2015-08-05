/*
 * @(#)UserService.java  2006-12-14
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.service;

import com.navy.framework.web.data.UserInfo;
import com.navy.framework.exception.ServiceException;
import com.navy.framework.models.User;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 */
public interface UserService {

	public User create(User user) throws ServiceException;

	public void update(User user) throws ServiceException;

	public void delete(User user) throws ServiceException;

	public User getUserById(Long id) throws ServiceException;

	public User getUserByUserid(String userId) throws ServiceException;

	public UserInfo login(String userId, String password, String userIP)
			throws ServiceException;

	public void logout(UserInfo userInfo) throws ServiceException;

}

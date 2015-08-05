/*
 * @(#)UserDao.java  2006-12-13
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.dao;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;

import com.navy.framework.exception.DaoException;
import com.navy.framework.models.User;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 */
public interface UserDao extends IDao, UserDetailsService {

	/**
	 * Persist User
	 *
	 * @param entity
	 * @return user object
	 * @throws DaoException
	 */
	public User create(User entity) throws DaoException;

	/**
	 * Return the persistent instance of the entity class with the given
	 * identifier, throwing an exception if not found.
	 *
	 * @param id
	 * @return user object
	 * @throws DaoException
	 */
	public User load(Long id) throws DaoException;

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or null if not found.
	 *
	 * @param id
	 * @return user object
	 * @throws DaoException
	 */
	public User get(Long id) throws DaoException;

	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or null if not found.
	 *
	 * @param username
	 * @return user object
	 * @throws DaoException
	 */
	public User get(String username) throws DaoException;

	/**
	 * Gets users information based on login name.
	 *
	 * @param username
	 *            the user's username
	 * @return userDetails populated userDetails object
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	public void lockedAccount(User user) throws DaoException;

	public void unLockedAccount(User user) throws DaoException;

	public void enableAccount(User user) throws DaoException;

	public void disableAccount(User user) throws DaoException;


}

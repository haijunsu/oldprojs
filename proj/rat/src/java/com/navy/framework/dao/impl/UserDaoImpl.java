/*
 * @(#)UserDaoImpl.java  2006-12-14
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.dao.impl;

import java.util.List;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;

import com.navy.framework.dao.UserDao;
import com.navy.framework.exception.DaoException;
import com.navy.framework.models.User;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 *
 * </p>
 *
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	/**
	 * Logger for UserDaoImpl.class
	 */

	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(UserDaoImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.impl.BaseDaoImpl#getEntitiy()
	 */
	protected Class getEntitiy() {
		return User.class;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.UserDao#create(com.navy.app.rat.entities.User)
	 */
	public User create(User entity) throws DaoException {
		entity.setAccountExpired(false);
		entity.setAccountLocked(false);
		entity.setEnabled(true);
		entity.setCredentialsExpired(false);
		entity.setJoinTime(now());
		Long _id = (Long) save(entity);
		return (User) getById(_id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.UserDao#get(java.lang.Long)
	 */
	public User get(Long id) throws DaoException {
		return (User) getById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.UserDao#load(java.lang.Long)
	 */
	public User load(Long id) throws DaoException {
		return (User) loadById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.UserDao#get(java.lang.String)
	 */
	public User get(String username) throws DaoException {
		String _strWhere = "username = ?";
		List _list = find(_strWhere, username);
		if (_list == null || _list.isEmpty()) {
			if (logger.isDebugEnabled()) {
				logger.debug("result is null");
			}
			return null;
		}
		if (_list.size() > 1) {
			handleException(new Exception("More than one rows return."),
					"1100", _strWhere);
		}
		return (User) _list.iterator().next();
	}

	/*
	 * (non-Javadoc)
	 *
	 *
	 * @see com.navy.framework.dao.UserDao#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails _user = null;
		try {
			_user = get(username);
			if (_user == null) {
				throw new UsernameNotFoundException("user '" + username
						+ "' not found...");
			}
		} catch (UsernameNotFoundException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw new UsernameNotFoundException("user '" + username
					+ "' not found...");
		}
		return _user;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.UserDao#disableAccount(com.navy.framework.models.User)
	 */
	public void disableAccount(User user) throws DaoException {
		User _user = get(user.getId());
		_user.setEnabled(false);
		update(user);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.UserDao#enableAccount(com.navy.framework.models.User)
	 */
	public void enableAccount(User user) throws DaoException {
		User _user = get(user.getId());
		_user.setEnabled(true);
		update(user);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.UserDao#lockedAccount(com.navy.framework.models.User)
	 */
	public void lockedAccount(User user) throws DaoException {
		User _user = get(user.getId());
		_user.setAccountLocked(true);
		update(user);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.UserDao#unLockedAccount(com.navy.framework.models.User)
	 */
	public void unLockedAccount(User user) throws DaoException {
		User _user = get(user.getId());
		_user.setAccountLocked(false);
		update(user);
	}

}

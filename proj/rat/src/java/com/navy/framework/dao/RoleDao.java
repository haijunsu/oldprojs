/*
 * @(#)RoleDao.java  2007-1-9
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.dao;

import com.navy.framework.exception.DaoException;
import com.navy.framework.models.Role;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 */
public interface RoleDao extends IDao {

	public Role create(Role entity) throws DaoException;

	public Role load(Long id) throws DaoException;

	public Role get(Long id) throws DaoException;

}

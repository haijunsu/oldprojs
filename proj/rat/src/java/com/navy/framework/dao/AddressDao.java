/*
 * @(#)RoleDao.java  2007-1-9
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.dao;

import com.navy.framework.exception.DaoException;
import com.navy.framework.models.Address;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 */
public interface AddressDao extends IDao {

	public Address create(Address entity) throws DaoException;

	public Address load(Long id) throws DaoException;

	public Address get(Long id) throws DaoException;

}

/*
 * @(#)LaptopDao.java  2007-1-15
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.dao;

import com.navy.app.rat.models.Laptop;
import com.navy.framework.dao.IDao;
import com.navy.framework.exception.DaoException;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 */
public interface LaptopDao extends IDao {
	public Laptop create(Laptop entity) throws DaoException;

	public Laptop load(Long id) throws DaoException;

	public Laptop get(Long id) throws DaoException;

}

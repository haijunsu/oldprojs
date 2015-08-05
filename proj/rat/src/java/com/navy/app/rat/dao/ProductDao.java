/*
 * @(#)ProductDao.java  2007-1-15
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.dao;

import com.navy.app.rat.models.Product;
import com.navy.framework.dao.IDao;
import com.navy.framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * @author Haijun Su
 */
public interface ProductDao extends IDao {
	public Product create(Product entity) throws DaoException;

	public Product load(Long id) throws DaoException;

	public Product get(Long id) throws DaoException;

}

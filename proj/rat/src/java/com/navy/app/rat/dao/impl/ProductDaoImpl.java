/*
 * @(#)ProductDaoImpl.java  2007-1-15
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.dao.impl;

import com.navy.app.rat.dao.ProductDao;
import com.navy.app.rat.models.Product;
import com.navy.framework.dao.impl.BaseDaoImpl;
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
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.impl.BaseDaoImpl#getEntitiy()
	 */
	protected Class getEntitiy() {
		return Product.class;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.ProductDao#create(com.navy.app.rat.models.Product)
	 */
	public Product create(Product entity) throws DaoException {
		Long id = (Long) save(entity);
		return (Product) getById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.ProductDao#get(java.lang.Long)
	 */
	public Product get(Long id) throws DaoException {
		return (Product) getById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.ProductDao#load(java.lang.Long)
	 */
	public Product load(Long id) throws DaoException {
		return (Product) loadById(id);
	}

}

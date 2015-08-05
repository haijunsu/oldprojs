/*
 * @(#)LaptopDaoImpl.java  2007-1-15
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.dao.impl;

import com.navy.app.rat.dao.LaptopDao;
import com.navy.app.rat.models.Laptop;
import com.navy.framework.dao.impl.BaseDaoImpl;
import com.navy.framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * @author Haijun Su
 */
public class LaptopDaoImpl extends BaseDaoImpl implements LaptopDao {

	/* (non-Javadoc)
	 * @see com.navy.framework.dao.impl.BaseDaoImpl#getEntitiy()
	 */
	protected Class getEntitiy() {
		return Laptop.class;
	}

	/* (non-Javadoc)
	 * @see com.navy.app.rat.dao.LaptopDao#create(com.navy.app.rat.models.Laptop)
	 */
	public Laptop create(Laptop entity) throws DaoException {
		Long id = (Long) save(entity);
		return (Laptop) getById(id);
	}

	/* (non-Javadoc)
	 * @see com.navy.app.rat.dao.LaptopDao#get(java.lang.Long)
	 */
	public Laptop get(Long id) throws DaoException {
		return (Laptop) getById(id);
	}

	/* (non-Javadoc)
	 * @see com.navy.app.rat.dao.LaptopDao#load(java.lang.Long)
	 */
	public Laptop load(Long id) throws DaoException {
		return (Laptop) loadById(id);
	}

}

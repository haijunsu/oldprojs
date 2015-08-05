/*
 * @(#)AddressDaoImpl.java  2007-1-15
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.dao.impl;

import com.navy.framework.dao.AddressDao;
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
public class AddressDaoImpl extends BaseDaoImpl implements AddressDao {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.impl.BaseDaoImpl#getEntitiy()
	 */
	protected Class getEntitiy() {
		return Address.class;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.AddressDao#create(com.navy.framework.models.Address)
	 */
	public Address create(Address entity) throws DaoException {
		Long id = (Long) save(entity);
		return (Address) getById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.AddressDao#get(java.lang.Long)
	 */
	public Address get(Long id) throws DaoException {
		return (Address) getById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.AddressDao#load(java.lang.Long)
	 */
	public Address load(Long id) throws DaoException {
		return (Address) loadById(id);
	}

}

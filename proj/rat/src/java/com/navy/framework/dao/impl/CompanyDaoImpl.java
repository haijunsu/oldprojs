/*
 * @(#)CompanyDaoImpl.java  2007-1-15
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.dao.impl;

import com.navy.framework.dao.CompanyDao;
import com.navy.framework.models.Company;
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
public class CompanyDaoImpl extends BaseDaoImpl implements CompanyDao {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.impl.BaseDaoImpl#getEntitiy()
	 */
	protected Class getEntitiy() {
		return Company.class;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.CompanyDao#create(com.navy.app.rat.models.Company)
	 */
	public Company create(Company entity) throws DaoException {
		Long id = (Long) save(entity);
		return (Company) getById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.CompanyDao#get(java.lang.Long)
	 */
	public Company get(Long id) throws DaoException {
		return (Company) getById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.CompanyDao#load(java.lang.Long)
	 */
	public Company load(Long id) throws DaoException {
		return (Company) loadById(id);
	}

}

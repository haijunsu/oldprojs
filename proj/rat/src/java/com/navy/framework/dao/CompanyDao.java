/*
 * @(#)Company.java  2007-1-15
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.dao;

import com.navy.framework.models.Company;
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
public interface CompanyDao extends IDao {
	public Company create(Company entity) throws DaoException;

	public Company load(Long id) throws DaoException;

	public Company get(Long id) throws DaoException;

}

/*
 * @(#)Category.java  2007-1-15
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.dao;

import java.util.List;

import com.navy.app.rat.models.Category;
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
public interface CategoryDao extends IDao {
	public Category create(Category entity) throws DaoException;

	public Category load(Long id) throws DaoException;

	public Category get(Long id) throws DaoException;

	public Category getByCode(String code) throws DaoException;

	public List getChildren(Category cate) throws DaoException;

	public Category getParent(Category cate) throws DaoException;

}

/*
 * @(#)CategoryDaoImpl.java  2007-1-15
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.navy.app.rat.dao.CategoryDao;
import com.navy.app.rat.models.Category;
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
public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.impl.BaseDaoImpl#getEntitiy()
	 */
	protected Class getEntitiy() {
		return Category.class;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.CategoryDao#create(com.navy.app.rat.models.Category)
	 */
	public Category create(Category entity) throws DaoException {
		if (StringUtils.isNotBlank(entity.getParent())) {
			Category _cate = getByCode(entity.getParent());
			_cate.setLeaf(false);
			update(_cate);
		}
		entity.setLeaf(true);
		Long id = (Long) save(entity);
		return get(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.CategoryDao#get(java.lang.Long)
	 */
	public Category get(Long id) throws DaoException {
		return (Category) getById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.CategoryDao#get(java.lang.Long)
	 */
	public Category getByCode(String code) throws DaoException {
		List _list = find("code = ?", code);
		return (Category) (_list == null ? null : _list.iterator().next());
	}

	public List getChildren(Category cate) throws DaoException {
		return find("parent = ?", cate.getCode());
	}

	public Category getParent(Category cate) throws DaoException {
		return getByCode(cate.getParent());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.app.rat.dao.CategoryDao#load(java.lang.Long)
	 */
	public Category load(Long id) throws DaoException {
		return (Category) loadById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.impl.GeneralDaoImpl#delete(java.lang.Object)
	 */
	public void delete(Object entity) throws DaoException {
		Category _cate = (Category) entity;
		_cate = get(_cate.getId());
		if (_cate.isLeaf()) {
			super.delete(_cate);
			Category _parent = getParent(_cate);
			List _list = getChildren(_parent);
			if (_list == null || _list.isEmpty()) {
				_parent.setLeaf(true);
				super.update(_parent);
			}
		} else {
			List _list = getChildren(_cate);
			Iterator _iter = _list.iterator();
			while (_iter.hasNext()) {
				delete(_iter.next());
			}
		}
	}
}

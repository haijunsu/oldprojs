/*
 * @(#)RoleDaoImpl.java  2007-1-9
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.dao.impl;

import com.navy.framework.dao.RoleDao;
import com.navy.framework.exception.DaoException;
import com.navy.framework.models.Role;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 */
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.impl.BaseDaoImpl#getEntitiy()
	 */
	protected Class getEntitiy() {
		return Role.class;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.RoleDao#create(com.navy.framework.models.Role)
	 */
	public Role create(Role entity) throws DaoException {
		Long _id = (Long) save(entity);
		return (Role) getById(_id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.RoleDao#get(java.lang.Long)
	 */
	public Role get(Long id) throws DaoException {
		return (Role) getById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.RoleDao#load(java.lang.Long)
	 */
	public Role load(Long id) throws DaoException {
		return (Role) loadById(id);
	}

}

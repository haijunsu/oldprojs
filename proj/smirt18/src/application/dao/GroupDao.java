/*
 * @(#)GroupDao.java  2005-1-16
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/application/dao/GroupDao.java,v 1.1 2005/01/20 03:14:23 navy Exp $
 * $Log: GroupDao.java,v $
 * Revision 1.1  2005/01/20 03:14:23  navy
 * Create SMiRT 18 project
 *
 */
package application.dao;

import application.entity.Group;
import application.entity.User;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface GroupDao {
	public Group create(User userEntity,Group GroupEntity) throws DaoException;
	public Group load(Long id) throws DaoException;
	public Group update(Group groupEntity) throws DaoException;
	public void remove(Group groupEntity) throws DaoException;
	public Group[] findByAccount(String account) throws DaoException;
	public Group[] findByUserKey(Long userkey) throws DaoException;
	public Group findByAccountAndGroup(String account, String group) throws DaoException;
	public Group[] listAll() throws DaoException;

}

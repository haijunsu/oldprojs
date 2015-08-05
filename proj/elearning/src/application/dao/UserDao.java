/*
 * @(#)UserDao.java  2005-1-16
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/dao/UserDao.java,v 1.1 2005/06/14 10:29:21 navysu Exp $
 * $Log: UserDao.java,v $
 * Revision 1.1  2005/06/14 10:29:21  navysu
 * add application and framework etc.
 *
 * Revision 1.1  2005/01/20 03:14:23  navy
 * Create SMiRT 18 project
 *
 */
package application.dao;

import application.entity.User;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface UserDao {
	public User create(User UserEntity) throws DaoException;
	public User load(Long id) throws DaoException;
	public User update(User UserEntity) throws DaoException;
	public void remove(User UserEntity) throws DaoException;
	public User findByAccount(String account) throws DaoException;
	public User findByAccountAndPass(String account, String password) throws DaoException;
	public User[] listAll() throws DaoException;

}

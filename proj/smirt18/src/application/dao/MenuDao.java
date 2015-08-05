/*
 * @(#)MenuDao.java  2005-1-16
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/application/dao/MenuDao.java,v 1.1 2005/01/20 03:14:23 navy Exp $
 * $Log: MenuDao.java,v $
 * Revision 1.1  2005/01/20 03:14:23  navy
 * Create SMiRT 18 project
 *
 */
package application.dao;

import application.entity.Menu;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface MenuDao {
	public Menu create(Menu MenuEntity) throws DaoException;
	public Menu load(Long id) throws DaoException;
	public Menu update(Menu MenuEntity) throws DaoException;
	public void remove(Menu MenuEntity) throws DaoException;
	public Menu findByMenuid(String menuid) throws DaoException;
	public Menu[] findByParentid(String parentid) throws DaoException;
	public Menu[] listAll() throws DaoException;

}

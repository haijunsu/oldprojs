/*
 * @(#)MenuDaoImpl.java  2005-1-16
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/application/dao/impl/MenuDaoImpl.java,v 1.1 2005/01/20 03:14:26 navy Exp $
 * $Log: MenuDaoImpl.java,v $
 * Revision 1.1  2005/01/20 03:14:26  navy
 * Create SMiRT 18 project
 *
 */
package application.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.ObjectNotFoundException;
import application.dao.MenuDao;
import application.entity.Menu;
import framework.dao.impl.BaseDaoImpl;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class MenuDaoImpl extends BaseDaoImpl implements MenuDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(MenuDaoImpl.class);

    /* (non-Javadoc)
     * @see application.dao.MenuDao#create(application.entity.Menu)
     */
    public Menu create(Menu MenuEntity) throws DaoException {
        saveEntity(MenuEntity);
        return MenuEntity;
    }

    /* (non-Javadoc)
     * @see application.dao.MenuDao#load(java.lang.Long)
     */
    public Menu load(Long id) throws DaoException {
        return (Menu)loadEntityByPrimaryKey(id);
    }

    /* (non-Javadoc)
     * @see application.dao.MenuDao#update(application.entity.Menu)
     */
    public Menu update(Menu MenuEntity) throws DaoException {
        updateEntity(MenuEntity);
        return MenuEntity;
    }

    /* (non-Javadoc)
     * @see application.dao.MenuDao#remove(application.entity.Menu)
     */
    public void remove(Menu MenuEntity) throws DaoException {
        removeEntity(MenuEntity);
    }

    /* (non-Javadoc)
     * @see application.dao.MenuDao#findByMenuid(java.lang.String)
     */
    public Menu findByMenuid(String menuid) throws DaoException {
		List _result = new ArrayList();
		try {
			_result = currentSession().find(
				"FROM Menu m WHERE m.menuid = ? order by m.menuid",	menuid, Hibernate.STRING);
		} catch (ObjectNotFoundException e) {
			logger.warn("Cannot find entity of menuid=" + menuid);
		} catch (HibernateException e) {
			throw new DaoException("Qurey from menu error.", e);
		}
		return (Menu) _result.get(0);
    }

    /* (non-Javadoc)
     * @see application.dao.MenuDao#findByParentid(java.lang.String)
     */
    public Menu[] findByParentid(String parentid) throws DaoException {
		List _result = new ArrayList();
		try {
//		    if (parentid == null) {
//				_result = currentSession().find(
//						"FROM Menu m WHERE m.parentid = '' or m.parentid is null order by m.parentid",	parentid, Hibernate.STRING);
//		    } else {
			_result = currentSession().find(
				"FROM Menu m WHERE m.parentid = ? order by m.parentid",	parentid, Hibernate.STRING);
//		    }
		} catch (ObjectNotFoundException e) {
			logger.warn("Cannot find entity of parentid=" + parentid);
		} catch (HibernateException e) {
			throw new DaoException("Qurey from menu error.", e);
		}
		Menu[] _menu = new Menu[_result.size()];
		_result.toArray(_menu);
		return _menu;
    }

    /* (non-Javadoc)
     * @see application.dao.MenuDao#listAll()
     */
    public Menu[] listAll() throws DaoException {
		List _result = new ArrayList();
		try {
			_result = currentSession().find(
				"FROM Menu m order by m.menuid");
		} catch (ObjectNotFoundException e) {
			logger.warn("Cannot find entity. Menus Table is empty.");
		} catch (HibernateException e) {
			throw new DaoException("Qurey from menu error.", e);
		}
		Menu[] _entities = new Menu[_result.size()];
		_result.toArray(_entities);
		return _entities;
    }


 
}

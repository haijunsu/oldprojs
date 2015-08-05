/*
 * @(#)UserDaoImpl.java  2005-1-7
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/dao/impl/UserDaoImpl.java,v 1.1 2005/06/14 10:29:23 navysu Exp $
 * $Log: UserDaoImpl.java,v $
 * Revision 1.1  2005/06/14 10:29:23  navysu
 * add application and framework etc.
 *
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
import net.sf.hibernate.type.Type;
import application.dao.UserDao;
import application.entity.User;
import framework.dao.impl.BaseDaoImpl;

import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(UserDaoImpl.class);
    
    /* (non-Javadoc)
     * @see application.dao.UserDao#load(java.lang.Long)
     */
    public User load(Long id) throws DaoException{
		List _result = new ArrayList();
		try {
			_result = currentSession().find(
				"FROM User u WHERE u.id = ?",	id, Hibernate.LONG);
		} catch (ObjectNotFoundException e) {
			logger.warn("Cannot find entity of id=" + id);
			return null;
		} catch (HibernateException e) {
			throw new DaoException("Qurey from user error.", e);
		}
		if (_result.size() == 0) {
		    return null;
		}
		return (User) _result.get(0);
    }

    /* (non-Javadoc)
     * @see application.dao.UserDao#findByAccount(java.lang.String)
     */
    public User findByAccount(String account) throws DaoException{
		List _result = new ArrayList();
		try {
			_result = currentSession().find(
				"FROM User u WHERE u.account = ?",	account, Hibernate.STRING);
		} catch (ObjectNotFoundException e) {
			logger.warn("Cannot find entity of account=" + account);
			return null;
		} catch (HibernateException e) {
			throw new DaoException("Qurey from user error.", e);
		}
		return (User) _result.get(0);
     }

    /* (non-Javadoc)
     * @see application.dao.UserDao#listAll()
     */
    public User[] listAll() throws DaoException {
		List _result = new ArrayList();
		try {
			_result = currentSession().find(
				"FROM User u order by u.id");
		} catch (ObjectNotFoundException e) {
			logger.warn("Cannot find entity. User Table is empty.");
			return null;
		} catch (HibernateException e) {
			throw new DaoException("Qurey from user error.", e);
		}
		User[] _entities = new User[_result.size()];
		_result.toArray(_entities);
		return _entities;
    }

    /* (non-Javadoc)
     * @see application.dao.UserDao#create(application.entity.User)
     */
    public User create(User userEntity) {
        saveEntity(userEntity);
        return userEntity;
    }

    /* (non-Javadoc)
     * @see application.dao.UserDao#update(application.entity.User)
     */
    public User update(User userEntity) {
        updateEntity(userEntity);
        return userEntity;
    }

    /* (non-Javadoc)
     * @see application.dao.UserDao#remove(application.entity.User)
     */
    public void remove(User userEntity) {
        removeEntity(userEntity);
     }

    /* (non-Javadoc)
     * @see application.dao.UserDao#findByAccountAndPass(java.lang.String, java.lang.String)
     */
    public User findByAccountAndPass(String account, String password) throws DaoException {
		List _result = new ArrayList();
		try {
		    String[] _params = new String[]{account, password};
		    Type[] _types = new Type[]{Hibernate.STRING, Hibernate.STRING};
			_result = currentSession().find(
				"FROM User u WHERE u.account = ? and u.pass = ?",	_params, _types);
		} catch (ObjectNotFoundException e) {
			logger.warn("Cannot find entity of account=" + account + ", password=" + password);
			return null;
		} catch (HibernateException e) {
			throw new DaoException("Qurey from user error.", e);
		}
		return (User) _result.get(0);
    }
}

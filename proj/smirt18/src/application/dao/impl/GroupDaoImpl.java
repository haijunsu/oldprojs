/*
 * @(#)GroupDaoImpl.java  2005-1-16
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/application/dao/impl/GroupDaoImpl.java,v 1.1 2005/01/20 03:14:26 navy Exp $
 * $Log: GroupDaoImpl.java,v $
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
import application.dao.GroupDao;
import application.dao.UserDao;
import application.entity.Group;
import application.entity.User;
import framework.dao.impl.BaseDaoImpl;

import framework.exception.DaoException;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision: 1.1 $
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class GroupDaoImpl extends BaseDaoImpl implements GroupDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(GroupDaoImpl.class);

    private UserDao m_userDao;

    /**
     * @param userDao The userDao to set.
     */
    public void setUserDao(UserDao userDao) {
        this.m_userDao = userDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.GroupDao#create(application.entity.Group)
     */
    public Group create(User userEntity, Group groupEntity) throws DaoException {
        saveEntity(groupEntity);
        return groupEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.GroupDao#load(java.lang.Long)
     */
    public Group load(Long id) throws DaoException {
        return (Group) loadEntityByPrimaryKey(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.GroupDao#update(application.entity.Group)
     */
    public Group update(Group groupEntity) throws DaoException {
        updateEntity(groupEntity);
        return groupEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.GroupDao#remove(application.entity.Group)
     */
    public void remove(Group groupEntity) throws DaoException {
        removeEntity(groupEntity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.GroupDao#findByAccount(java.lang.String)
     */
    public Group[] findByAccount(String account) throws DaoException {
        List _result = new ArrayList();
        try {
            _result = currentSession().find("FROM Group g WHERE g.account = ?",
                    account, Hibernate.STRING);
        } catch (ObjectNotFoundException e) {
            logger.warn("Cannot find entity. ");
            return null;
        } catch (HibernateException e) {
            throw new DaoException("Qurey from group error.", e);
        }
        Group[] _entities = new Group[_result.size()];
        _result.toArray(_entities);
        return _entities;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.GroupDao#listAll()
     */
    public Group[] listAll() throws DaoException {
        List _result = new ArrayList();
        try {
            _result = currentSession().find("FROM Group g order by g.id");
        } catch (ObjectNotFoundException e) {
            logger.warn("Cannot find entity. Group Table is empty.");
            return null;
        } catch (HibernateException e) {
            throw new DaoException("Qurey from user error.", e);
        }
        Group[] _entities = new Group[_result.size()];
        _result.toArray(_entities);
        return _entities;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.GroupDao#findByAccountAndGroup(java.lang.String,
     *      java.lang.String)
     */
    public Group findByAccountAndGroup(String account, String group)
            throws DaoException {
        List _result = new ArrayList();
        try {
            String[] _params = new String[] { account, group };
            Type[] _types = new Type[] { Hibernate.STRING, Hibernate.STRING };
            _result = currentSession().find(
                    "FROM Group g WHERE g.account = ? and g.group = ?",
                    _params, _types);
            if (_result.size() == 0) {
                return null;
            }
        } catch (ObjectNotFoundException e) {
            logger.warn("Cannot find entity. ");
            return null;
        } catch (HibernateException e) {
            throw new DaoException("Qurey from group error.", e);
        }
        Group[] _entities = new Group[_result.size()];
        _result.toArray(_entities);
        return _entities[0];
    }

    /* (non-Javadoc)
     * @see application.dao.GroupDao#findByUserKey(java.lang.Long)
     */
    public Group[] findByUserKey(Long userkey) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

}
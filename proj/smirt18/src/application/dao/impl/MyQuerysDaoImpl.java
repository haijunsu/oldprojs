/*
 * @(#)MyQuerysDaoImpl.java  2005-4-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.ObjectNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.MyQuerysDao;
import application.entity.MyQuerys;
import framework.dao.impl.BaseDaoImpl;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class MyQuerysDaoImpl extends BaseDaoImpl implements MyQuerysDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(MyQuerysDaoImpl.class);

    /* (non-Javadoc)
     * @see application.dao.MyQuerysDao#create(application.entity.MyQuerys)
     */
    public MyQuerys create(MyQuerys querysEntity) throws DaoException {
        saveEntity(querysEntity);
        return querysEntity;
    }

    /* (non-Javadoc)
     * @see application.dao.MyQuerysDao#load(java.lang.Long)
     */
    public MyQuerys load(Long id) throws DaoException {
        return (MyQuerys)loadEntityByPrimaryKey(id);
    }

    /* (non-Javadoc)
     * @see application.dao.MyQuerysDao#update(application.entity.MyQuerys)
     */
    public MyQuerys update(MyQuerys querysEntity) throws DaoException {
        updateEntity(querysEntity);
        return querysEntity;
    }

    /* (non-Javadoc)
     * @see application.dao.MyQuerysDao#remove(application.entity.MyQuerys)
     */
    public void remove(MyQuerys querysEntity) throws DaoException {
        removeEntity(querysEntity);
    }

    /* (non-Javadoc)
     * @see application.dao.MyQuerysDao#listAll()
     */
    public MyQuerys[] listAll() throws DaoException {
		List _result = new ArrayList();
		try {
			_result = currentSession().find(
				"FROM MyQuerys");
		} catch (ObjectNotFoundException e) {
			logger.warn("Cannot find entity. MyQuerys Table is empty.");
		} catch (HibernateException e) {
			throw new DaoException("Qurey from MyQuerys error.", e);
		}
		MyQuerys[] _querys = new MyQuerys[_result.size()];
		_result.toArray(_querys);
        return _querys;
    }

}

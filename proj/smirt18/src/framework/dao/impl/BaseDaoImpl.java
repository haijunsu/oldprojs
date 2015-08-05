/*
 * @(#)BaseDaoImpl.java  2005-1-5
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/framework/dao/impl/BaseDaoImpl.java,v 1.1 2005/01/20 03:14:28 navy Exp $
 * $Log: BaseDaoImpl.java,v $
 * Revision 1.1  2005/01/20 03:14:28  navy
 * Create SMiRT 18 project
 *
 */
package framework.dao.impl;

import java.io.Serializable;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.ObjectNotFoundException;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate.SessionFactoryUtils;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

import framework.dao.IDao;
import framework.exception.DaoException;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * Every Dao implement should extend this class. All base method has been
 * implemented in this class
 * </p>
 * 
 * $Revision: 1.1 $
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class BaseDaoImpl extends HibernateDaoSupport implements IDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(BaseDaoImpl.class);

    /**
     * Main Entity will be persisted
     */
    private Class m_EntityType;
    
    /**
     * Save appointed entity with appointed key
     * @param entity
     * @param key
     * @return Object
     * @throws DaoException
     */
    public Object saveEntity(Object entity, Serializable key) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEntity(Object entity = " + entity.getClass().getName()
                    + ", Serializable key = " + key + ") - start");
        }

        try {
            if (key == null) {
                if (logger.isDebugEnabled()) {
                    logger
                            .debug("saveEntity(Object, Serializable) - " +
                            		"Executing option when key is null.");
                }

                currentSession().save(entity);
            } else {
                if (logger.isDebugEnabled()) {
                    logger
                            .debug("saveEntity(Object, Serializable) - " +
                            		"Executing option when Serializable is '"
                                    + key + "'.");
                }

               currentSession().save(entity, key);
            }
        } catch (HibernateException e) {
            logger.error("saveEntity(Object, Serializable)", e);
            throw new DaoException("Error in BaseDaoImpl.saveEntity(Object, Serializable)", e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("saveEntity(Object, Serializable) - end");
        }
        return entity;
    }


    /**
     * Save appointed entity 
     * @param entity
     * @return Object
     * @throws DaoException
     */
    public Object saveEntity(Object entity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("saveEntity(Object) - start");
        }

        Object _returnObject = saveEntity(entity, null);
        if (logger.isDebugEnabled()) {
            logger.debug("saveEntity(Object) - end");
        }
        return _returnObject;
    }
    
    /**
     * Load appointed entity by key in database
     * 
     * @param key
     * @return Object
     * @throws DaoException
     * @throws CannotFoundRecordException
     */
    public Object loadEntityByPrimaryKey(Class clazz, Serializable key)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("loadEntityByPrimaryKey(Class clazz = " + clazz
                    + ", Serializable key = " + key + ") - Start");
        }

        try {
            Object _returnObject = currentSession().load(clazz, key);
            if (logger.isDebugEnabled()) {
                logger.debug("loadEntityByPrimaryKey(Class, Serializable) - end");
            }
            return _returnObject;
        } catch (ObjectNotFoundException e) {
            if (logger.isDebugEnabled()) {
                logger
                        .debug("loadEntityByPrimaryKey(Class, Serializable) - ObjectNotFoundException");
            }
            logger.warn("loadEntityByPrimaryKey(Class, Serializable) - Cannot found the record, type=" + clazz.getName()
                            + ", key=" + key, e);
            return null;

        } catch (HibernateException e) {
            logger.error("loadEntityByPrimaryKey(Class, Serializable)", e);

            throw new DaoException(
                    "Error in BaseDaoImpl.loadEntityByPrimaryKey(Class, Serializable)",
                    e);
        }
    }

    /**
     * Load entity by key in database
     * 
     * @param key
     * @return Object
     * @throws DaoException
     * @throws CannotFoundRecordException
     */
    public Object loadEntityByPrimaryKey(Serializable key) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("loadEntityByPrimaryKey(Serializable) - start");
        }

        Object _returnObject = loadEntityByPrimaryKey(this.m_EntityType, key);
        if (logger.isDebugEnabled()) {
            logger.debug("loadEntityByPrimaryKey(Serializable) - end");
        }
        return _returnObject;
    }

    /**
     * persist appointed entity and sub entity
     * 
     * @param entity
     * @return Object
     * @throws DAOException
     */
    public Object storeEntity(Object entity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("storeEntity(Object) - start");
        }

        try {
            Session _session = currentSession();
            _session.saveOrUpdate(entity);
            _session.flush();
        } catch (HibernateException e) {
            logger.error("storeEntity(Object)", e);

            throw new DaoException("Error in BaseDaoImpl.storeEntity(Object)",
                    e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("storeEntity(Object) - end");
        }
        return entity;
    }
    
    
    /**
     * Update appointed entity with appointed key
     * @param entity
     * @param key
     * @return Object
     * @throws DaoException
     */
    public Object updateEntity(Object entity, Serializable key) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("updateEntity(Object entity = " + entity
                    + ", Serializable key = " + key + ") - Start");
        }

        try {
            if (key == null) {
                if (logger.isDebugEnabled()) {
                    logger
                            .debug("updateEntity(Object, Serializable) - " +
                            		"Executing option when key is null.");
                }

                currentSession().update(entity);
            } else {
                if (logger.isDebugEnabled()) {
                    logger
                            .debug("updateEntity(Object, Serializable) - " +
                            		"Executing option when key is '" +
                            		key +"'.");
                }
                currentSession().update(entity, key);
            }
                
        } catch (HibernateException e) {
            logger.error("updateEntity(Object, Serializable)", e);

            throw new DaoException("Error in BaseDaoImpl.updateEntity(Object, Serializable)",
                    e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("updateEntity(Object, Serializable) - end");
        }
        
        return entity;
     }
    
    /**
     * Update appoint entity
     * @param entity
     * @return Object
     * @throws DaoException
     */
    public Object updateEntity(Object entity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("updateEntity(Object) - start");
        }

        Object _returnObject = updateEntity(entity, null);
        if (logger.isDebugEnabled()) {
            logger.debug("updateEntity(Object) - end");
        }
        return _returnObject;
    }
    
    /**
     * Delete appointed entity with appionted key
     * @param clazz
     * @param key
     * @throws DaoException
     */
    public void removeEntity(Class clazz, Serializable key) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("removeEntity(Class, Serializable) - start");
        }

        try {
            Session _session = currentSession();
            Object _obj = _session.load(clazz, key);
            _session.delete(_obj);
        } catch (HibernateException e) {
            logger.error("removeEntity(Class, Serializable)", e);

            throw new DaoException(
                    "Error in BaseDaoImpl.removeEntity(Class, Serializable)", e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("removeEntity(Class, Serializable) - end");
        }
    }

//    /**
//     * Delete entity with appionted key
//     * @param key
//     * @throws DaoException
//     */
//    public void removeEntity(Serializable key) throws DaoException {
//        if (logger.isDebugEnabled()) {
//            logger.debug("removeEntity(Serializable) - start");
//        }
//
//        removeEntity(this.m_EntityType, key);
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("removeEntity(Serializable) - end");
//        }
//    }

    /**
     * Delete appointed entity
     * @param entity
     * @throws DaoException
     */
    public void removeEntity(Object entity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("removeEntity(Object) - start");
        }

        try {
            currentSession().delete(entity);
        } catch (HibernateException e) {
            logger.error("removeEntity(Object)", e);

            throw new DaoException("Error in BaseDaoImpl.removeEntity(Object)",
                    e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("removeEntity(Object) - end");
        }
    }

    /**
     * Delete all recods from database 
     * @param clazz
     * @throws DaoException
     */
    public void removeAllEntities(Class clazz) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("removeAllEntities(Class) - start");
        }

        try {
            currentSession().delete("FROM " + clazz.getName());
        } catch (HibernateException e) {
            logger.error("removeAllEntities(Class)", e);

            throw new DaoException("Error in BaseDaoImpl.removeAllEntities(Class)",
                    e);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("removeAllEntities(Class) - end");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see framework.dao.IDao#find(java.lang.String, java.lang.Class)
     */
    public List find(String condition, Class entityType) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see framework.dao.IDao#remove(java.lang.String, java.lang.Class)
     */
    public void remove(String condition, Class entityType) throws DaoException {
        // TODO Auto-generated method stub

    }

    /**
     * @return Returns the entityType.
     */
    public Object getEntityType() {
        return this.m_EntityType;
    }

    /**
     * @param entityType
     *            The entityType to set.
     */
    public void setEntityType(Object entityType) {
        this.m_EntityType = entityType.getClass();
    }

    /**
     * Get Hibernate session from SessionFactory.
     * 
     * @return Hibernate session
     */
    protected Session currentSession() {
        return SessionFactoryUtils.getSession(getSessionFactory(), true);
    }
}
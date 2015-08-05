/*
 * @(#)EquipmentDaoImpl.java  2005-2-19
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.EquipmentsDao;
import application.entities.Tequipments;

import framework.exception.DaoException;
import framework.util.StringUtil;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision$
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class EquipmentsDaoImpl extends BaseDaoJdbcImpl implements EquipmentsDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(EquipmentsDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.EquipmentDao#create(application.entities.Tequipment)
     */
    public Tequipments create(Tequipments equipmentEntity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("create(Tequipment) - start");
        }

        equipmentEntity.setChrEquipmentNo(genStringKey());
        insertEntity(equipmentEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("create(Tequipment) - end");
        }
        return equipmentEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.EquipmentDao#load(java.lang.String)
     */
    public Tequipments load(String equipmentNo) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - start");
        }

        String _strCondition = "chrEquipmentNo = '"
                + StringUtil.escapeSql(equipmentNo) + "'";
        Tequipments _equipment = (Tequipments)listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - end");
        }
        return _equipment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.EquipmentDao#update(application.entities.Tequipment)
     */
    public int update(Tequipments equipmentEntity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("update(Tequipment) - start");
        }

        int _nRtn = updateEntity(equipmentEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("update(Tequipment) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.EquipmentDao#remove(application.entities.Tequipment)
     */
    public int remove(Tequipments equipmentEntity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove(Tequipment) - start");
        }

        int _nRtn = removeEntity(equipmentEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("remove(Tequipment) - end");
        }
        
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.EquipmentDao#findByEquipmentNo(java.lang.String)
     */
    public Tequipments findByEquipmentNo(String equipmentNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByEquipmentNo(String) - start");
        }

        Tequipments _equipment = load(equipmentNo);
        if (logger.isDebugEnabled()) {
            logger.debug("findByEquipmentNo(String) - end");
        }
        return _equipment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.EquipmentDao#findByEquipment(java.lang.String)
     */
    public Tequipments[] findByEquipmentName(String equipmentName) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("findByEquipmentName(String) - start");
        }
        String _strCondition = "chvEquipmentName like '%"
                + StringUtil.escapeSql(equipmentName) + "%'";
        Tequipments[] _arrayEquipment = (Tequipments[])listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByEquipmentName(String) - end");
        }
        return _arrayEquipment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.EquipmentDao#listEntities(java.lang.String,
     *      java.lang.String)
     */
    public Object[] listEntities(String condition, String orderBy)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - start");
        }

        List _listRs = find(condition, orderBy);
        Tequipments[] _arrayEquipment = new Tequipments[_listRs.size()];
        for (int i = 0; i < _arrayEquipment.length; i++) {
            _arrayEquipment[i] = new Tequipments();
        }
        SQLHelper.listToEntities(_listRs, _arrayEquipment);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayEquipment;
    }

}
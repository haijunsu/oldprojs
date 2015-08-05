/*
 * @(#)EquipmentDao.java  2005-2-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.Tequipments;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface EquipmentsDao extends BaseDao {
    public Tequipments create(Tequipments equipmentEntity) throws DaoException;
    public Tequipments load(String equipmentNo) throws DaoException;
    public int update(Tequipments equipmentEntity) throws DaoException;
    public int remove(Tequipments equipmentEntity) throws DaoException;
    public Tequipments findByEquipmentNo(String equipmentNo) throws DaoException;
    public Tequipments[] findByEquipmentName(String equipment) throws DaoException;
    
}

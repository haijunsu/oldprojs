/*
 * @(#)AffiliationTypeDao.java  2005-2-21
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao;

import application.entities.TaffiliationType;
import framework.exception.DaoException;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public interface AffiliationTypeDao extends BaseDao {
    
    public TaffiliationType findByAffiliationTypeNo(String affiliationTypeNo) throws DaoException;
    public TaffiliationType[] findByAffiliationType(String affiliationType) throws DaoException;
    public TaffiliationType[] listAll() throws DaoException;

}

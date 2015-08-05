/*
 * @(#)UserTypeDaoImpl.java  2005-2-21
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


import framework.exception.DaoException;
import framework.util.StringUtil;
import application.dao.UserTypeDao;
import application.entities.TuserType;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class UserTypeDaoImpl extends BaseDaoJdbcImpl implements UserTypeDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(UserTypeDaoImpl.class);

    /* (non-Javadoc)
     * @see application.dao.UserTypeDao#findByTypeNo(java.lang.String)
     */
    public TuserType findByTypeNo(String typeNo) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("findByTypeNo(String) - start");
        }

        String _strCondition = "chrTypeNo = '" + StringUtil.escapeSql(typeNo) + "'";
        TuserType _userType = (TuserType)listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByTypeNo(String) - end");
        }
        return _userType;
    }

    /* (non-Javadoc)
     * @see application.dao.UserTypeDao#findByUserType(java.lang.String)
     */
    public TuserType[] findByUserType(String userType) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("findByUserType(String) - start");
        }

        String _strCondition = "chvUserType like '%" + StringUtil.escapeSql(userType) + "%'";
        TuserType[] _arrayUserType = (TuserType[])listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByUserType(String) - end");
        }
        return _arrayUserType;
    }

    /* (non-Javadoc)
     * @see application.dao.UserTypeDao#listAll()
     */
    public TuserType[] listAll() throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("listAll() - start");
        }

        TuserType[] _arrayUserType = (TuserType[])listEntities(null);

        if (logger.isDebugEnabled()) {
            logger.debug("listAll() - end");
        }
        return _arrayUserType;
    }


    /* (non-Javadoc)
     * @see application.dao.BaseDao#listEntities(java.lang.String, java.lang.String)
     */
    public Object[] listEntities(String condition, String orderBy)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - start");
        }

        List _listRs = find(condition, orderBy);
        TuserType[] _arrayUserType = new TuserType[_listRs.size()];
        for (int i = 0; i < _arrayUserType.length; i++) {
            _arrayUserType[i] = new TuserType();
        }
        SQLHelper.listToEntities(_listRs, _arrayUserType);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayUserType;
    }

}

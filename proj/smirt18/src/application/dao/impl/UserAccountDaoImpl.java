/*
 * @(#)UserAccountDaoImpl.java  2005-2-20
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
import application.dao.UserAccountDao;
import application.entities.TuserAccount;

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
public class UserAccountDaoImpl extends BaseDaoJdbcImpl implements
        UserAccountDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(UserAccountDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.UserAccountDao#findByUserid(java.lang.Integer)
     */
    public TuserAccount findByUserid(Integer userid) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByUserid(Integer) - start");
        }

        String _strCondition = "intUserID = " + userid.toString();
        TuserAccount _user = (TuserAccount) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByUserid(Integer) - end");
        }
        return _user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.UserAccountDao#findByUserAccount(java.lang.String)
     */
    public TuserAccount[] findByUserAccount(String userAccount)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByUserAccount(String) - start");
        }

        TuserAccount[] _arrayUser = findByEmail(userAccount, true);

        if (logger.isDebugEnabled()) {
            logger.debug("findByUserAccount(String) - end");
        }
        return _arrayUser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.UserAccountDao#findByFirstName(java.lang.String)
     */
    public TuserAccount[] findByFirstName(String firstName) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByFirstName(String) - start");
        }

        String _strCondition = "chvFirstName like '%"
                + StringUtil.escapeSql(firstName) + "%'";
        TuserAccount[] _arrayUser = (TuserAccount[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByFirstName(String) - end");
        }
        return _arrayUser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.UserAccountDao#findByLastName(java.lang.String)
     */
    public TuserAccount[] findByLastName(String lastName) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByLastName(String) - start");
        }

        String _strCondition = "chvLastName like '%"
                + StringUtil.escapeSql(lastName) + "%'";
        TuserAccount[] _arrayUser = (TuserAccount[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByLastName(String) - end");
        }
        return _arrayUser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.UserAccountDao#findByEmail(java.lang.String)
     */
    public TuserAccount[] findByEmail(String email) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByEmail(String) - start");
        }

        TuserAccount[] _user = findByEmail(email, false);

        if (logger.isDebugEnabled()) {
            logger.debug("findByEmail(String) - end");
        }
        return _user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.UserAccountDao#listEntities(java.lang.String,
     *      java.lang.String)
     */
    public Object[] listEntities(String condition, String orderBy)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - start");
        }

        List _listRs = find(condition, orderBy);
        TuserAccount[] _arrayUser = new TuserAccount[_listRs.size()];
        for (int i = 0; i < _arrayUser.length; i++) {
            _arrayUser[i] = new TuserAccount();
        }
        SQLHelper.listToEntities(_listRs, _arrayUser);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayUser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.UserAccountDao#findByCountryNo(java.lang.String)
     */
    public TuserAccount[] findByCountryNo(String countryNo) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByCountryNo(String) - start");
        }

        String _strCondition = "chrCountryNo = '"
                + StringUtil.escapeSql(countryNo) + "'";
        TuserAccount[] _arrayUser = (TuserAccount[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByCountryNo(String) - end");
        }
        return _arrayUser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.UserAccountDao#findByEmail(java.lang.String,
     *      boolean)
     */
    public TuserAccount[] findByEmail(String email, boolean isExact)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByEmail(String, boolean) - start");
        }

        String _strCondition = "chvUserAccount like '%"
                + StringUtil.escapeSql(email) + "%'";
        if (isExact) {
            _strCondition = "chvUserAccount = '" + StringUtil.escapeSql(email)
                    + "'";
        }
        TuserAccount[] _arrayUser = (TuserAccount[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByEmail(String, boolean) - end");
        }
        return _arrayUser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.UserAccountDao#findByAffiliation(java.lang.String)
     */
    public TuserAccount[] findByAffiliation(String affiliation)
            throws DaoException {
        String _strCondition = "chvAffiliation like '%"
                + StringUtil.escapeSql(affiliation) + "%'";
        TuserAccount[] _arrayUser = (TuserAccount[]) listEntities(_strCondition);
        return _arrayUser;

    }

}
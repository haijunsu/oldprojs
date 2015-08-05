/*
 * @(#)CoauthorDaoImpl.java  2005-2-19
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.CoauthorDao;
import application.entities.Tcoauthor;

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
public class CoauthorDaoImpl extends BaseDaoJdbcImpl implements CoauthorDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(CoauthorDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.CoauthorDao#findCoauthor(java.lang.String,
     *      java.lang.String)
     */
    public Tcoauthor findCoauthor(String paperNumber, String email)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findCoauthor(String) - start");
        }

        String _strCondition = "chvPaperNumber = '"
                + StringUtil.escapeSql(paperNumber)
                + "' AND chvCoauthorEmail='" + StringUtil.escapeSql(email)
                + "'";
        Tcoauthor _coauthor = (Tcoauthor) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findCoauthor(String) - end");
        }
        return _coauthor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.CoauthorDao#findByEmail(java.lang.String)
     */
    public Tcoauthor[] findByEmail(String email) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByEmail(String) - start");
        }

        Tcoauthor[] _arrayCoauthor = (Tcoauthor[]) findByEmail(email, false);
        if (logger.isDebugEnabled()) {
            logger.debug("findByEmail(String) - end");
        }
        return _arrayCoauthor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.CoauthorDao#findByPaperNumber(java.lang.String)
     */
    public Tcoauthor[] findByPaperNumber(String paperNumber)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - start");
        }

        String _strCondition = "chvPaperNumber = '"
                + StringUtil.escapeSql(paperNumber) + "'";
        String _strOrderby = "chvPaperNumber, inyAuthorRank";
        Tcoauthor[] _arrayCoauthor = (Tcoauthor[]) listEntities(_strCondition,
                _strOrderby);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - end");
        }
        return _arrayCoauthor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.CoauthorDao#findByFirstName(java.lang.String)
     */
    public Tcoauthor[] findByFirstName(String firstName) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByFirstName(String) - start");
        }

        String _strCondition = "chvFirstName like '%"
                + StringUtil.escapeSql(firstName) + "%'";
        Tcoauthor[] _arrayCoauthor = (Tcoauthor[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByFirstName(String) - end");
        }
        return _arrayCoauthor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.CoauthorDao#findByLastName(java.lang.String)
     */
    public Tcoauthor[] findByLastName(String lastName) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByLastName(String) - start");
        }

        String _strCondition = "chvLastName like '%"
                + StringUtil.escapeSql(lastName) + "%'";
        Tcoauthor[] _arrayCoauthor = (Tcoauthor[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByLastName(String) - end");
        }
        return _arrayCoauthor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.CoauthorDao#findByCountry(java.lang.String)
     */
    public Tcoauthor[] findByCountry(String countryNo) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByCountry(String) - start");
        }

        String _strCondition = "chrCountryNo = '"
                + StringUtil.escapeSql(countryNo) + "'";
        Tcoauthor[] _arrayCoauthor = (Tcoauthor[]) listEntities(_strCondition);
        if (logger.isDebugEnabled()) {
            logger.debug("findByCountry(String) - end");
        }
        return _arrayCoauthor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.CoauthorDao#listEntities(java.lang.String,
     *      java.lang.String)
     */
    public Object[] listEntities(String condition, String orderBy)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - start");
        }

        List _listRs = find(condition, orderBy);
        Tcoauthor[] _arrayCoauthor = new Tcoauthor[_listRs.size()];
        for (int i = 0; i < _arrayCoauthor.length; i++) {
            _arrayCoauthor[i] = new Tcoauthor();
        }
        SQLHelper.listToEntities(_listRs, _arrayCoauthor);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayCoauthor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.CoauthorDao#findByEmail(java.lang.String, boolean)
     */
    public Tcoauthor[] findByEmail(String email, boolean isExact)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByEmail(String, boolean) - start");
        }

        String _strCondition = "chvCoauthorEmail like '%"
                + StringUtil.escapeSql(email) + "%'";
        if (isExact) {
            _strCondition = "chvCoauthorEmail = '"
                    + StringUtil.escapeSql(email) + "'";
        }
        Tcoauthor[] _arrayCoauthor = (Tcoauthor[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByEmail(String, boolean) - end");
        }
        return _arrayCoauthor;
    }

    /* (non-Javadoc)
     * @see application.dao.CoauthorDao#findAuthorRankByPaperNumberAndEmail(java.lang.String, java.lang.String)
     */
    public Integer findAuthorRankByPaperNumberAndEmail(String paperNumber, String email) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("findAuthorRankByPaperNumberAndEmail(String, String) - start");
        }

        try {
            StringBuffer _sbSQL = new StringBuffer();
            _sbSQL.append("SELECT inyAuthorRank FROM ");
            if (StringUtil.isNotBlank(getTableSchema())) {
                _sbSQL.append(getTableSchema()).append(".");
            }
            _sbSQL.append(getTableName())
            .append(" WHERE chvPaperNumber = '")
            .append(StringUtil.escapeSql(paperNumber))
            .append("' AND chvCoauthorEmail = '")
            .append(StringUtil.escapeSql(email))
            .append("'");
            if (logger.isDebugEnabled()) {
                logger
                        .debug("findAuthorRankByPaperNumberAndEmail(String, String) - SQL: " + _sbSQL.toString());
            }
            int _nRtn = getJdbcTemplate().queryForInt(_sbSQL.toString());

            if (logger.isDebugEnabled()) {
                logger
                        .debug("findAuthorRankByPaperNumberAndEmail(String, String) - end");
            }
            return new Integer(_nRtn);
        } catch (Exception e) {
            logger.error("findAuthorRankByPaperNumberAndEmail(String, String)",
                    e);

            throw new DaoException(e.getMessage());
        }
    }

    /* (non-Javadoc)
     * @see application.dao.CoauthorDao#findByAffiliation(java.lang.String)
     */
    public Tcoauthor[] findByAffiliation(String affiliation) throws DaoException {
        String _strCondition = "chvAffiliation like '%"
            + StringUtil.escapeSql(affiliation) + "%'";
        Tcoauthor[] _arrayCoauthor = (Tcoauthor[]) listEntities(_strCondition);
        return _arrayCoauthor;
    }

}
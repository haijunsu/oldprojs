/*
 * @(#)UserAccountDaoImpl.java  2005-2-20
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.AuthorDao;
import application.entities.Tauthor;
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
public class AuthorDaoImpl extends BaseDaoJdbcImpl implements AuthorDao {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(AuthorDaoImpl.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.AuthorDao#findByAuthorNo(java.lang.String)
	 */
	public Tauthor findByAuthorNo(String authorNo) throws DaoException {
		String _strCondition = "chrAuthorNo = '" + authorNo + "'";
		Tauthor _user = (Tauthor) listEntity(_strCondition);
		return _user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.UserAccountDao#findByUserAccount(java.lang.String)
	 */
	public Tauthor[] findByAuthorAccount(String authorAccount)
			throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByUserAccount(String) - start");
		}

		Tauthor[] _arrayUser = findByEmail(authorAccount, true);

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
	public Tauthor[] findByFirstName(String firstName) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByFirstName(String) - start");
		}

		String _strCondition = "chvFirstName like '%"
				+ StringUtil.escapeSql(firstName) + "%'";
		Tauthor[] _arrayUser = (Tauthor[]) listEntities(_strCondition);

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
	public Tauthor[] findByLastName(String lastName) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByLastName(String) - start");
		}

		String _strCondition = "chvLastName like '%"
				+ StringUtil.escapeSql(lastName) + "%'";
		Tauthor[] _arrayUser = (Tauthor[]) listEntities(_strCondition);

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
	public Tauthor[] findByEmail(String email) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByEmail(String) - start");
		}

		Tauthor[] _user = findByEmail(email, false);

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
		Tauthor[] _arrayUser = new Tauthor[_listRs.size()];
		for (int i = 0; i < _arrayUser.length; i++) {
			_arrayUser[i] = new Tauthor();
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
	public Tauthor[] findByCountryNo(String countryNo) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByCountryNo(String) - start");
		}

		String _strCondition = "chrCountryNo = '"
				+ StringUtil.escapeSql(countryNo) + "'";
		Tauthor[] _arrayUser = (Tauthor[]) listEntities(_strCondition);

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
	public Tauthor[] findByEmail(String email, boolean isExact)
			throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByEmail(String, boolean) - start");
		}

		String _strCondition = "chvAuthorAccount like '%"
				+ StringUtil.escapeSql(email) + "%'";
		if (isExact) {
			_strCondition = "chvAuthorAccount = '" + StringUtil.escapeSql(email)
					+ "'";
		}
		Tauthor[] _arrayUser = (Tauthor[]) listEntities(_strCondition);

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
	public Tauthor[] findByAffiliation(String affiliation) throws DaoException {
		String _strCondition = "chvAffiliation like '%"
				+ StringUtil.escapeSql(affiliation) + "%'";
		Tauthor[] _arrayUser = (Tauthor[]) listEntities(_strCondition);
		return _arrayUser;

	}

	/* (non-Javadoc)
	 * @see application.dao.AuthorDao#findByPaperNumber(java.lang.String)
	 */
	public Tauthor[] findByPaperNumber(String paperNumber) throws DaoException {
	       if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - start");
        }
        try {
            StringBuffer _sbSql = new StringBuffer();
            _sbSql.append(SQLHelper.getSelectStatement(getTableName(),
                    getTableSchema()));
            _sbSql.append(
                    ", T_RelationBetweenAuthorAndPaper B, T_Paper C ")
                    .append("WHERE ").append(getFullTableName()).append(
                            ".chrAuthorNo = B.chrAuthorNo AND ").append(
                            "B.chvPaperNumber = C.chvPaperNumber AND ").append(
                            "C.chvPaperNumber = '").append(
                            StringUtil.escapeSql(paperNumber)).append("'");
            if (logger.isDebugEnabled()) {
                logger.debug("findByPaperNumber(String) - SQL: "
                        + _sbSql.toString());
            }
            List _listRs = getJdbcTemplate().queryForList(_sbSql.toString());
            if (_listRs.isEmpty()) {
                if (logger.isDebugEnabled()) {
                    logger
                            .debug("findByPaperNumber(String) - result is empty and return null");
                }
                return null;
            }
            Tauthor[] _arrayAuthor = new Tauthor[_listRs.size()];
            for (int i = 0; i < _arrayAuthor.length; i++) {
                _arrayAuthor[i] = new Tauthor();
            }
            SQLHelper.listToEntities(_listRs, _arrayAuthor);

            if (logger.isDebugEnabled()) {
                logger.debug("findByPaperNumber(String) - end");
            }
            return _arrayAuthor;
        } catch (Exception e) {
            logger.error("findByPaperNumber(String)", e);

            throw new DaoException(e.getMessage());
        }
	}

}
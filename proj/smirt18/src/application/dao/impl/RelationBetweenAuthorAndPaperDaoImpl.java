/*
 * @(#)RelationBetweenauthorAndPaperDaoImpl.java  2005-2-20
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.RelationBetweenAuthorAndPaperDao;
import application.entities.TrelationBetweenAuthorAndPaper;
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
public class RelationBetweenAuthorAndPaperDaoImpl extends BaseDaoJdbcImpl
		implements RelationBetweenAuthorAndPaperDao {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(RelationBetweenAuthorAndPaperDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.RelationBetweenParticipantAndPaperDao#create(application.entities.TrelationBetweenAuthorAndPaper)
	 */
	public TrelationBetweenAuthorAndPaper create(
			TrelationBetweenAuthorAndPaper relationBetweenAuthroAndPaperEntity)
			throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("create(TrelationBetweenAuthorAndPaper) - start");
		}

		insertEntity(relationBetweenAuthroAndPaperEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("create(TrelationBetweenAuthorAndPaper) - end");
		}
		return relationBetweenAuthroAndPaperEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.RelationBetweenParticipantAndPaperDao#load(java.lang.String,
	 *      java.lang.String, java.lang.Integer)
	 */
	public TrelationBetweenAuthorAndPaper load(String authorNo,
			String paperNumber, Integer authorRank) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("load(String, String, Integer) - start");
		}

		String _strCondition = "chrAuthorNo = '"
				+ StringUtil.escapeSql(authorNo) + "' AND chvPaperNumber = '"
				+ StringUtil.escapeSql(paperNumber) + "' AND intAuthorRank = "
				+ authorRank.toString();
		TrelationBetweenAuthorAndPaper _rbpap = (TrelationBetweenAuthorAndPaper) listEntity(_strCondition);

		if (logger.isDebugEnabled()) {
			logger.debug("load(String, String, Integer) - end");
		}
		return _rbpap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.RelationBetweenParticipantAndPaperDao#update(application.entities.TrelationBetweenAuthorAndPaper)
	 */
	public int update(
			TrelationBetweenAuthorAndPaper relationBetweenAuthorAndPaperEntity)
			throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("update(TrelationBetweenAuthorAndPaper) - start");
		}

		String[] _arrayStrColumns = new String[] { "chrAuthorNo",
				"chvPaperNumber", "chvPaperLNumber", "intAuthorRank" };
		int _nRtn = updateEntity(relationBetweenAuthorAndPaperEntity,
				_arrayStrColumns);

		if (logger.isDebugEnabled()) {
			logger.debug("update(TrelationBetweenAuthorAndPaper) - end");
		}
		return _nRtn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.RelationBetweenParticipantAndPaperDao#remove(application.entities.TrelationBetweenAuthorAndPaper)
	 */
	public int remove(
			TrelationBetweenAuthorAndPaper relationBetweenAuthorAndPaperEntity)
			throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("remove(TrelationBetweenAuthorAndPaper) - start");
		}

		String[] _arrayStrColumns = new String[] { "chrAuthorNo",
				"chvPaperNumber", "intAuthorRank" };
		int _nRtn = removeEntity(relationBetweenAuthorAndPaperEntity,
				_arrayStrColumns);

		if (logger.isDebugEnabled()) {
			logger.debug("remove(TrelationBetweenAuthorAndPaper) - end");
		}
		return _nRtn;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.RelationBetweenParticipantAndPaperDao#findByParticipantNo(java.lang.String)
	 */
	public TrelationBetweenAuthorAndPaper[] findByAuthorNo(String authorNo)
			throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByauthorNo(String) - start");
		}

		String _strCondition = "chrAuthorNo = '"
				+ StringUtil.escapeSql(authorNo) + "'";
		TrelationBetweenAuthorAndPaper[] _arrayRbpap = (TrelationBetweenAuthorAndPaper[]) listEntities(_strCondition);

		if (logger.isDebugEnabled()) {
			logger.debug("findByauthorNo(String) - end");
		}
		return _arrayRbpap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.RelationBetweenauthorAndPaperDao#findByPaperNumber(java.lang.String)
	 */
	public TrelationBetweenAuthorAndPaper[] findByPaperNumber(String paperNumber)
			throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByPaperNumber(String) - start");
		}

		String _strCondition = "chvPaperNumber = '"
				+ StringUtil.escapeSql(paperNumber) + "'";
		TrelationBetweenAuthorAndPaper[] _arrayRbpap = (TrelationBetweenAuthorAndPaper[]) listEntities(_strCondition);

		if (logger.isDebugEnabled()) {
			logger.debug("findByPaperNumber(String) - end");
		}
		return _arrayRbpap;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.RelationBetweenParticipantAndPaperDao#listEntities(java.lang.String,
	 *      java.lang.String)
	 */
	public Object[] listEntities(String condition, String orderBy)
			throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("listEntities(String, String) - start");
		}

		List _listRs = find(condition, orderBy);
		TrelationBetweenAuthorAndPaper[] _arrayRbpap = new TrelationBetweenAuthorAndPaper[_listRs
				.size()];
		for (int i = 0; i < _arrayRbpap.length; i++) {
			_arrayRbpap[i] = new TrelationBetweenAuthorAndPaper();
		}
		SQLHelper.listToEntities(_listRs, _arrayRbpap);

		if (logger.isDebugEnabled()) {
			logger.debug("listEntities(String, String) - end");
		}
		return _arrayRbpap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.RelationBetweenauthorAndPaperDao#findAuthorRankByauthorNoAndPaperNumber(java.lang.String,
	 *      java.lang.String)
	 */
	public Integer findAuthorRankByAuthorNoAndPaperNumber(String authorNo,
			String paperNumber) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger
					.debug("findAuthorRankByauthorNoAndPaperNumber(String, String) - start");
		}

		try {
			StringBuffer _sbSQL = new StringBuffer();
			_sbSQL.append("SELECT intAuthorRank FROM ");
			if (StringUtil.isNotBlank(getTableSchema())) {
				_sbSQL.append(getTableSchema()).append(".");
			}
			_sbSQL.append(getTableName()).append(" WHERE chvPaperNumber = '")
					.append(StringUtil.escapeSql(paperNumber)).append(
							"' AND chrAuthorNo = '").append(
							StringUtil.escapeSql(authorNo)).append("'");
			if (logger.isDebugEnabled()) {
				logger
						.debug("findAuthorRankByAuthorNoAndPaperNumber(String, String) - SQL: "
								+ _sbSQL.toString());
			}
			int _nRtn = getJdbcTemplate().queryForInt(_sbSQL.toString());

			if (logger.isDebugEnabled()) {
				logger
						.debug("findAuthorRankByAuthorNoAndPaperNumber(String, String) - end");
			}
			return new Integer(_nRtn);
		} catch (Exception e) {
			logger
					.error(
							"findAuthorRankByauthorNoAndPaperNumber(String, String)",
							e);

			throw new DaoException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see application.dao.RelationBetweenAuthorAndPaperDao#findByPaperLNumber(java.lang.String)
	 */
	public TrelationBetweenAuthorAndPaper[] findByPaperLNumber(String paperLNumber) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByPaperNumber(String) - start");
		}

		String _strCondition = "chvPaperLNumber = '"
				+ StringUtil.escapeSql(paperLNumber) + "'";
		TrelationBetweenAuthorAndPaper[] _arrayRbpap = (TrelationBetweenAuthorAndPaper[]) listEntities(_strCondition);

		if (logger.isDebugEnabled()) {
			logger.debug("findByPaperNumber(String) - end");
		}
		return _arrayRbpap;
	}

}
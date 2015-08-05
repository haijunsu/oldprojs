/*
 * @(#)ParticipantDaoImpl.java  2005-2-19
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.ParticipantDao;
import application.entities.Tparticipant;
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
public class ParticipantDaoImpl extends BaseDaoJdbcImpl implements
		ParticipantDao {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory
			.getLog(ParticipantDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#create(application.entities.Tparticipant)
	 */
	public Tparticipant create(Tparticipant participantEntity)
			throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("create(Tparticipant) - start");
		}

		participantEntity.setChrPartiNo(genStringKey());
		insertEntity(participantEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("create(Tparticipant) - end");
		}
		return participantEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#load(java.lang.String)
	 */
	public Tparticipant load(String participantNo) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("load(String) - start");
		}

		String _strCondition = "chrPartiNo = '"
				+ StringUtil.escapeSql(participantNo) + "'";
		Tparticipant _participant = (Tparticipant) listEntity(_strCondition);

		if (logger.isDebugEnabled()) {
			logger.debug("load(String) - end");
		}
		return _participant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#update(application.entities.Tparticipant)
	 */
	public int update(Tparticipant participantEntity) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("update(Tparticipant) - start");
		}

		int _nRtn = updateEntity(participantEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("update(Tparticipant) - end");
		}
		return _nRtn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#remove(application.entities.Tparticipant)
	 */
	public int remove(Tparticipant participantEntity) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("remove(Tparticipant) - start");
		}

		int _nRtn = removeEntity(participantEntity);

		if (logger.isDebugEnabled()) {
			logger.debug("remove(Tparticipant) - end");
		}
		return _nRtn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#findByParticipantNo(java.lang.String)
	 */
	public Tparticipant findByParticipantNo(String participantNo)
			throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByParticipantNo(String) - start");
		}

		Tparticipant _participant = load(participantNo);

		if (logger.isDebugEnabled()) {
			logger.debug("findByParticipantNo(String) - end");
		}
		return _participant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#findByFirstName(java.lang.String)
	 */
	public Tparticipant[] findByFirstName(String firstName) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByFirstName(String) - start");
		}

		String _strCondition = "chvPartiFirstName like '%"
				+ StringUtil.escapeSql(firstName) + "%'";
		Tparticipant[] _arrayParticipant = (Tparticipant[]) listEntities(_strCondition);

		if (logger.isDebugEnabled()) {
			logger.debug("findByFirstName(String) - end");
		}
		return _arrayParticipant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#findByLastName(java.lang.String)
	 */
	public Tparticipant[] findByLastName(String lastName) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByLastName(String) - start");
		}

		String _strCondition = "chvPartiLastName like '%"
				+ StringUtil.escapeSql(lastName) + "%'";
		Tparticipant[] _arrayParticipant = (Tparticipant[]) listEntities(_strCondition);

		if (logger.isDebugEnabled()) {
			logger.debug("findByLastName(String) - end");
		}
		return _arrayParticipant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#findByEmail(java.lang.String)
	 */
	public Tparticipant[] findByEmail(String email) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByEmail(String) - start");
		}

		Tparticipant[] _arrayParticipant = findByEmail(email, false);

		if (logger.isDebugEnabled()) {
			logger.debug("findByEmail(String) - end");
		}
		return _arrayParticipant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#findByCountry(java.lang.String)
	 */
	public Tparticipant[] findByCountry(String country) throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByCountry(String) - start");
		}

		String _strCondition = "chvPartiCountry = '"
				+ StringUtil.escapeSql(country) + "'";
		Tparticipant[] _arrayParticipant = (Tparticipant[]) listEntities(_strCondition);

		if (logger.isDebugEnabled()) {
			logger.debug("findByCountry(String) - end");
		}
		return _arrayParticipant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#listEntities(java.lang.String,
	 *      java.lang.String)
	 */
	public Object[] listEntities(String condition, String orderBy)
			throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("listEntities(String, String) - start");
		}

		List _listRs = find(condition, orderBy);
		Tparticipant[] _arrayParticipant = new Tparticipant[_listRs.size()];
		for (int i = 0; i < _arrayParticipant.length; i++) {
			_arrayParticipant[i] = new Tparticipant();
		}
		SQLHelper.listToEntities(_listRs, _arrayParticipant);

		if (logger.isDebugEnabled()) {
			logger.debug("listEntities(String, String) - end");
		}
		return _arrayParticipant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#findByEmail(java.lang.String,
	 *      boolean)
	 */
	public Tparticipant[] findByEmail(String email, boolean isExact)
			throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByEmail(String, boolean) - start");
		}

		String _strCondition = "chvPartiEmail like '%"
				+ StringUtil.escapeSql(email) + "%'";
		if (isExact) {
			_strCondition = "chvPartiEmail = '" + StringUtil.escapeSql(email)
					+ "'";
		}
		Tparticipant[] _arrayParticipant = (Tparticipant[]) listEntities(_strCondition);

		if (logger.isDebugEnabled()) {
			logger.debug("findByEmail(String, boolean) - end");
		}
		return _arrayParticipant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#findByPaperNumber(java.lang.String)
	 */
	public Tparticipant[] findByPaperNumber(String paperNumber)
			throws DaoException {
		if (logger.isDebugEnabled()) {
			logger.debug("findByPaperNumber(String) - start");
		}
		try {
			StringBuffer _sbSql = new StringBuffer();
			_sbSql.append(SQLHelper.getSelectStatement(getTableName(),
					getTableSchema()));
			_sbSql.append(
					", T_RelationBetweenParticipantAndPaper B, T_Paper C ")
					.append("WHERE ").append(getFullTableName()).append(
							".chrPartiNo = B.chrPartiNo AND ").append(
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
			Tparticipant[] _arrayParticipant = new Tparticipant[_listRs.size()];
			for (int i = 0; i < _arrayParticipant.length; i++) {
				_arrayParticipant[i] = new Tparticipant();
			}
			SQLHelper.listToEntities(_listRs, _arrayParticipant);

			if (logger.isDebugEnabled()) {
				logger.debug("findByPaperNumber(String) - end");
			}
			return _arrayParticipant;
		} catch (Exception e) {
			logger.error("findByPaperNumber(String)", e);

			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#findByAffiliation(java.lang.String)
	 */
	public Tparticipant[] findByAffiliation(String affiliation)
			throws DaoException {
		String _strCondition = "chvPartiAffiliation like '%"
				+ StringUtil.escapeSql(affiliation) + "%'";
		Tparticipant[] _arrayParticipant = (Tparticipant[]) listEntities(_strCondition);
		return _arrayParticipant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.dao.ParticipantDao#findByEmailFirstNameLastName(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public Tparticipant findByEmailFirstNameLastName(String email,
			String firstName, String lastName) throws DaoException {
		String _strCondition = "chvPartiEmail = '"
				+ StringUtil.escapeSql(email) + "' AND "
				+ "chvPartiFirstName = '" + StringUtil.escapeSql(firstName)
				+ "' AND " + "chvPartiLastName = '"
				+ StringUtil.escapeSql(lastName) + "'";
		Tparticipant _participant = (Tparticipant) listEntity(_strCondition);

		return _participant;
	}

}
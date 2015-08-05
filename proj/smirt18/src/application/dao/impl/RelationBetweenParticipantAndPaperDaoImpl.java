/*
 * @(#)RelationBetweenParticipantAndPaperDaoImpl.java  2005-2-20
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import framework.exception.DaoException;
import framework.util.StringUtil;
import application.dao.RelationBetweenParticipantAndPaperDao;
import application.entities.TrelationBetweenParticipantAndPaper;

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
public class RelationBetweenParticipantAndPaperDaoImpl extends BaseDaoJdbcImpl
        implements RelationBetweenParticipantAndPaperDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(RelationBetweenParticipantAndPaperDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RelationBetweenParticipantAndPaperDao#create(application.entities.TrelationBetweenParticipantAndPaper)
     */
    public TrelationBetweenParticipantAndPaper create(
            TrelationBetweenParticipantAndPaper relationBetweenParticipantAndPaperEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("create(TrelationBetweenParticipantAndPaper) - start");
        }

        insertEntity(relationBetweenParticipantAndPaperEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("create(TrelationBetweenParticipantAndPaper) - end");
        }
        return relationBetweenParticipantAndPaperEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RelationBetweenParticipantAndPaperDao#load(java.lang.String,
     *      java.lang.String, java.lang.Integer)
     */
    public TrelationBetweenParticipantAndPaper load(String participantNo,
            String paperNumber, Integer authorRank) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("load(String, String, Integer) - start");
        }

        String _strCondition = "chrPartiNo = '"
                + StringUtil.escapeSql(participantNo)
                + "' AND chvPaperNumber = '"
                + StringUtil.escapeSql(paperNumber) + "' AND inyAuthorRank = "
                + authorRank.toString();
        TrelationBetweenParticipantAndPaper _rbpap = (TrelationBetweenParticipantAndPaper) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("load(String, String, Integer) - end");
        }
        return _rbpap;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RelationBetweenParticipantAndPaperDao#update(application.entities.TrelationBetweenParticipantAndPaper)
     */
    public int update(
            TrelationBetweenParticipantAndPaper relationBetweenParticipantAndPaperEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("update(TrelationBetweenParticipantAndPaper) - start");
        }

        String[] _arrayStrColumns = new String[] { "chrPartiNo",
                "chvPaperNumber", "inyAuthorRank" };
        int _nRtn = updateEntity(relationBetweenParticipantAndPaperEntity,
                _arrayStrColumns);

        if (logger.isDebugEnabled()) {
            logger.debug("update(TrelationBetweenParticipantAndPaper) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RelationBetweenParticipantAndPaperDao#remove(application.entities.TrelationBetweenParticipantAndPaper)
     */
    public int remove(
            TrelationBetweenParticipantAndPaper relationBetweenParticipantAndPaperEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove(TrelationBetweenParticipantAndPaper) - start");
        }

        String[] _arrayStrColumns = new String[] { "chrPartiNo",
                "chvPaperNumber", "inyAuthorRank" };
        int _nRtn = removeEntity(relationBetweenParticipantAndPaperEntity,
                _arrayStrColumns);

        if (logger.isDebugEnabled()) {
            logger.debug("remove(TrelationBetweenParticipantAndPaper) - end");
        }
        return _nRtn;

    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RelationBetweenParticipantAndPaperDao#findByParticipantNo(java.lang.String)
     */
    public TrelationBetweenParticipantAndPaper[] findByParticipantNo(
            String participantNo) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByParticipantNo(String) - start");
        }

        String _strCondition = "chrPartiNo = '"
                + StringUtil.escapeSql(participantNo) + "'";
        TrelationBetweenParticipantAndPaper[] _arrayRbpap = (TrelationBetweenParticipantAndPaper[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByParticipantNo(String) - end");
        }
        return _arrayRbpap;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RelationBetweenParticipantAndPaperDao#findByPaperNumber(java.lang.String)
     */
    public TrelationBetweenParticipantAndPaper[] findByPaperNumber(
            String paperNumber) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - start");
        }

        String _strCondition = "chvPaperNumber = '"
                + StringUtil.escapeSql(paperNumber) + "'";
        TrelationBetweenParticipantAndPaper[] _arrayRbpap = (TrelationBetweenParticipantAndPaper[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - end");
        }
        return _arrayRbpap;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RelationBetweenParticipantAndPaperDao#findByUserID(java.lang.Integer)
     */
    public TrelationBetweenParticipantAndPaper[] findByUserID(Integer userID)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByUserID(Integer) - start");
        }

        String _strCondition = "intUserID = " + userID.toString();
        TrelationBetweenParticipantAndPaper[] _arrayRbpap = (TrelationBetweenParticipantAndPaper[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByUserID(Integer) - end");
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
        TrelationBetweenParticipantAndPaper[] _arrayRbpap = new TrelationBetweenParticipantAndPaper[_listRs
                .size()];
        for (int i = 0; i < _arrayRbpap.length; i++) {
            _arrayRbpap[i] = new TrelationBetweenParticipantAndPaper();
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
     * @see application.dao.RelationBetweenParticipantAndPaperDao#findAuthorRankByPartiNoAndPaperNumber(java.lang.String,
     *      java.lang.String)
     */
    public Integer findAuthorRankByPartiNoAndPaperNumber(String paperNumber,
            String partiNo) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("findAuthorRankByPartiNoAndPaperNumber(String, String) - start");
        }

        try {
            StringBuffer _sbSQL = new StringBuffer();
            _sbSQL.append("SELECT inyAuthorRank FROM ");
            if (StringUtil.isNotBlank(getTableSchema())) {
                _sbSQL.append(getTableSchema()).append(".");
            }
            _sbSQL.append(getTableName()).append(" WHERE chvPaperNumber = '")
                    .append(StringUtil.escapeSql(paperNumber)).append(
                            "' AND chrPartiNo = '").append(
                            StringUtil.escapeSql(partiNo)).append("'");
            if (logger.isDebugEnabled()) {
                logger
                        .debug("findAuthorRankByPartiNoAndPaperNumber(String, String) - SQL: "
                                + _sbSQL.toString());
            }
            int _nRtn = getJdbcTemplate().queryForInt(_sbSQL.toString());

            if (logger.isDebugEnabled()) {
                logger
                        .debug("findAuthorRankByPartiNoAndPaperNumber(String, String) - end");
            }
            return new Integer(_nRtn);
        } catch (Exception e) {
            logger.error(
                    "findAuthorRankByPartiNoAndPaperNumber(String, String)", e);

            throw new DaoException(e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RelationBetweenParticipantAndPaperDao#findUseridByPartiNo(java.lang.String,
     *      java.lang.String)
     */
    public Integer findUseridByPartiNo(String partiNo, String paperNumber)
            throws DaoException {
        try {
        	if (StringUtil.isBlank(partiNo)) {
        		return null;
        	}
            StringBuffer _sbSQL = new StringBuffer();
            _sbSQL.append("SELECT DISTINCT intUserID FROM ");
            if (StringUtil.isNotBlank(getTableSchema())) {
                _sbSQL.append(getTableSchema()).append(".");
            }
            _sbSQL.append(getTableName()).append(" WHERE chrPartiNo = '")
                    .append(StringUtil.escapeSql(partiNo)).append("'");
            if (StringUtil.isNotBlank(paperNumber)) {
                _sbSQL.append(" AND chvPaperNumber = '").append(
                        StringUtil.escapeSql(paperNumber)).append("'");
            }
            if (logger.isDebugEnabled()) {
                logger.debug("findUseridByPartiNo(String, String) - SQL: "
                        + _sbSQL.toString());
            }
            List _list = getJdbcTemplate().queryForList(_sbSQL.toString());
            Iterator _iterator = _list.iterator();
            while (_iterator.hasNext()) {
                Map _map = (Map) _iterator.next();
                Integer _integer = (Integer) _map.get("intUserID");
                if (_integer.intValue() > 0) {
                    return _integer;
                }
            }
            return null;
        } catch (Exception e) {
            logger.error("findUseridByPartiNo(String, String)", e);

            return null;
        }
    }

	/* (non-Javadoc)
	 * @see application.dao.RelationBetweenParticipantAndPaperDao#findByPaperNumber(java.lang.String, java.lang.String)
	 */
	public TrelationBetweenParticipantAndPaper findByPartiNoAndPaperNumber(String partiNo, String paperNumber) throws DaoException {
        String _strCondition = "chrPartiNo = '" + partiNo + "' AND chvPaperNumber = '" + paperNumber + "'";
        TrelationBetweenParticipantAndPaper _arrayRbpap = (TrelationBetweenParticipantAndPaper) listEntity(_strCondition);
        return _arrayRbpap;
	}

}
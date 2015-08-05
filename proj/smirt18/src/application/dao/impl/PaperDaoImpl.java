/*
 * @(#)PaperDaoImpl.java  2005-2-19
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.PaperDao;
import application.entities.Tpaper;
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
public class PaperDaoImpl extends BaseDaoJdbcImpl implements PaperDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(PaperDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#create(application.entities.Tpaper)
     */
    public Tpaper create(Tpaper paperEntity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("create(Tpaper) - start");
        }

        paperEntity.setChvPaperNumber(genStringKey());
        insertEntity(paperEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("create(Tpaper) - end");
        }
        return paperEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#load(java.lang.String)
     */
    public Tpaper load(String id) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - start");
        }

        String _strCondition = "chvPaperNumber = '" + StringUtil.escapeSql(id)
                + "'";
        Tpaper _paper = (Tpaper) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - end");
        }
        return _paper;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#update(application.entities.Tpaper)
     */
    public int update(Tpaper paperEntity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("update(Tpaper) - start");
        }

        int _nRtn = updateEntity(paperEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("update(Tpaper) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#remove(application.entities.Tpaper)
     */
    public int remove(Tpaper paperEntity) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove(Tpaper) - start");
        }

        int _nRtn = removeEntity(paperEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("remove(Tpaper) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#findByPaperLNumber(java.lang.String)
     */
    public Tpaper findByPaperLNumber(String paperLNumber) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperLNumber(String) - start");
        }

        String _strCondition = "chvPaperLNumber = '"
                + StringUtil.escapeSql(paperLNumber) + "'";
        Tpaper _paper = (Tpaper) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperLNumber(String) - end");
        }
        return _paper;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#findByPaperTitle(java.lang.String)
     */
    public Tpaper[] findByPaperTitle(String paperTitle) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperTitle(String) - start");
        }

        String _strCondition = "chvPaperTitle like '%"
                + StringUtil.escapeSql(paperTitle) + "%'";
        Tpaper[] _arrayPaper = (Tpaper[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperTitle(String) - end");
        }
        return _arrayPaper;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#findByPaperNumber(java.lang.String)
     */
    public Tpaper findByPaperNumber(String paperNumber) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - start");
        }

        Tpaper _paper = load(paperNumber);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - end");
        }
        return _paper;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#findByUserid(java.lang.Integer)
     */
    public Tpaper[] findByUserid(Integer userid) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByUserid(String) - start");
        }

        String _strCondition = "intUserID = " + userid;
        Tpaper[] _arrayPaper = (Tpaper[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByUserid(String) - end");
        }
        return _arrayPaper;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#findByDivisionNo(java.lang.String)
     */
    public Tpaper[] findByDivisionNo(String divisionNo) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByDivisionNo(String) - start");
        }
        String _strCondition = "chrDivisionNo = '"
                + StringUtil.escapeSql(divisionNo) + "'";

        Tpaper[] _arrayPaper = (Tpaper[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByDivisionNo(String) - end");
        }
        return _arrayPaper;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#findByKeyWords(java.lang.String)
     */
    public Tpaper[] findByKeyWords(String keyWords) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByKeyWords(String) - start");
        }
        String _strCondition = "chvPaperKeyword like '%"
                + StringUtil.escapeSql(keyWords) + "%'";

        Tpaper[] _arrayPaper = (Tpaper[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByKeyWords(String) - end");
        }
        return _arrayPaper;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#listEntities(java.lang.String,
     *      java.lang.String)
     */
    public Object[] listEntities(String condition, String orderBy)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - start");
        }

        List _listRs = find(condition, orderBy);
        Tpaper[] _arrayPaper = new Tpaper[_listRs.size()];
        for (int i = 0; i < _arrayPaper.length; i++) {
            _arrayPaper[i] = new Tpaper();
        }
        SQLHelper.listToEntities(_listRs, _arrayPaper);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayPaper;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#findByPartiNo(java.lang.String)
     */
    public Tpaper[] findByPartiNo(String PartiNo) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPartiNo(String) - start");
        }
        try {
            StringBuffer _sbSql = new StringBuffer();
            _sbSql.append(SQLHelper.getSelectStatement(getTableName(),
                    getTableSchema()));
            _sbSql
                    .append(
                            ", T_RelationBetweenParticipantAndPaper B, T_Participant C ")
                    .append("WHERE ").append(getFullTableName()).append(
                            ".chvPaperNumber = B.chvPaperNumber AND ").append(
                            "B.chrPartiNo = C.chrPartiNo AND ").append(
                            "C.chrPartiNo = '").append(
                            StringUtil.escapeSql(PartiNo)).append("'");
            if (logger.isDebugEnabled()) {
                logger.debug("findByPartiNo(String) - SQL: "
                        + _sbSql.toString());
            }

            List _listRs = getJdbcTemplate().queryForList(_sbSql.toString());
            if (_listRs.isEmpty()) {
                if (logger.isDebugEnabled()) {
                    logger.debug("findByPartiNo(String) - result is empty.");
                }
                return new Tpaper[0];
            }
            Tpaper[] _arrayPaper = new Tpaper[_listRs.size()];
            for (int i = 0; i < _arrayPaper.length; i++) {
                _arrayPaper[i] = new Tpaper();
            }
            SQLHelper.listToEntities(_listRs, _arrayPaper);

            if (logger.isDebugEnabled()) {
                logger.debug("findByPartiNo(String) - end");
            }
            return _arrayPaper;
        } catch (Exception e) {
            logger.error("findByPartiNo(String) - Exception error. ", e);
            throw new DaoException(e.getMessage());

        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#findAuthorRankByPaperNumberAndUserID(java.lang.Integer)
     */
    public Integer findAuthorRankByPaperNumberAndUserID(String paperNumber,
            Integer userid) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("findAuthorRankByPaperNumberAndUserID(String, Integer) - start");
        }

        try {
            StringBuffer _sbSQL = new StringBuffer();
            _sbSQL.append("SELECT inyAuthorRank FROM ");
            if (StringUtil.isNotBlank(getTableSchema())) {
                _sbSQL.append(getTableSchema()).append(".");
            }
            _sbSQL.append(getTableName()).append(" WHERE intUserID = ").append(
                    userid).append(" AND chvPaperNumber = '").append(
                    StringUtil.escapeSql(paperNumber)).append("'");
            if (logger.isDebugEnabled()) {
                logger
                        .debug("findAuthorRankByPaperNumberAndUserID(String, Integer) - SQL: "
                                + _sbSQL.toString());
            }
            int _nRtn = getJdbcTemplate().queryForInt(_sbSQL.toString());

            if (logger.isDebugEnabled()) {
                logger
                        .debug("findAuthorRankByPaperNumberAndUserID(String, Integer) - end");
            }
            return new Integer(_nRtn);
        } catch (Exception e) {
            logger.error(
                    "findAuthorRankByPaperNumberAndUserID(String, Integer) - exception: "
                            + e.getMessage(), e);

            throw new DaoException(e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#findAllPapersByUserid(java.lang.Integer)
     */
    public Tpaper[] findAllPapersByUserid(Integer userid) throws DaoException {
        Tpaper[] _arrayPaper = new Tpaper[0];
        StringBuffer _sbSQL = new StringBuffer();
        StringBuffer _sbSQLSelect = new StringBuffer();
        StringBuffer _sbSQLWhere = new StringBuffer();
        _sbSQLSelect.append(SQLHelper.getSelectStatement(getTableName(),
                getTableSchema()));
        _sbSQLWhere.append(", dbo.T_UserAccount A WHERE ").append(
                getFullTableName()).append(
                ".intUserID = A.intUserID AND A.intUserID = ").append(
                userid.toString());
        _sbSQL.append(_sbSQLSelect.toString()).append(_sbSQLWhere.toString());
        if (logger.isDebugEnabled()) {
            logger.debug("findAllPapersByUserid() - SQL: " + _sbSQL.toString());
        }
        List _listRs = getJdbcTemplate().queryForList(_sbSQL.toString());
        Tpaper[] _arrayPaper1 = new Tpaper[_listRs.size()];
        for (int i = 0; i < _arrayPaper1.length; i++) {
            _arrayPaper1[i] = new Tpaper();
        }
        SQLHelper.listToEntities(_listRs, _arrayPaper1);
        _sbSQLWhere = new StringBuffer();
        _sbSQL = new StringBuffer();
        _sbSQLWhere.append(", dbo.T_UserAccount A, dbo.T_Coauthor B WHERE ")
                .append(getFullTableName()).append(
                        ".intUserID = A.intUserID AND A.intUserID = ").append(
                        userid.toString()).append(
                        " AND A.chvUserAccount = B.chvCoauthorEmail AND ")
                .append(getFullTableName()).append(
                        ".chvPaperNumber = B.chvPaperNumber");
        if (logger.isDebugEnabled()) {
            logger.debug("findAllPapersByUserid() - SQL: " + _sbSQL.toString());
        }
        _sbSQL.append(_sbSQLSelect.toString()).append(_sbSQLWhere.toString());
        _listRs = getJdbcTemplate().queryForList(_sbSQL.toString());
        Tpaper[] _arrayPaper2 = new Tpaper[_listRs.size()];
        for (int i = 0; i < _arrayPaper2.length; i++) {
            _arrayPaper2[i] = new Tpaper();
        }
        SQLHelper.listToEntities(_listRs, _arrayPaper2);

        _arrayPaper = new Tpaper[_arrayPaper1.length + _arrayPaper2.length];
        for (int i = 0; i < _arrayPaper1.length; i++) {
            _arrayPaper[i] = _arrayPaper1[i];
        }
        for (int i = _arrayPaper1.length; i < _arrayPaper.length; i++) {
            _arrayPaper[i] = _arrayPaper2[i - _arrayPaper1.length];
        }
        return _arrayPaper;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#findAllPapersByEmail(java.lang.String)
     */
    public Tpaper[] findAllPapersByEmail(String email) throws DaoException {
        Tpaper[] _arrayPaper = new Tpaper[0];
        StringBuffer _sbSQL = new StringBuffer();
        StringBuffer _sbSQLSelect = new StringBuffer();
        StringBuffer _sbSQLWhere = new StringBuffer();
        _sbSQLSelect.append(SQLHelper.getSelectStatement(getTableName(),
                getTableSchema()));
        _sbSQLWhere
                .append(", dbo.T_Coauthor B WHERE ")
                .append(getFullTableName())
                .append(
                        ".chvPaperNumber = B.chvPaperNumber AND B.chvCoauthorEmail = '")
                .append(email).append("'");
        _sbSQL.append(_sbSQLSelect.toString()).append(_sbSQLWhere.toString());

        if (logger.isDebugEnabled()) {
            logger.debug("findAllPapersByEmail() - SQL: " + _sbSQL.toString());
        }

        List _listRs = getJdbcTemplate().queryForList(_sbSQL.toString());
        Tpaper[] _arrayPaper1 = new Tpaper[_listRs.size()];
        for (int i = 0; i < _arrayPaper1.length; i++) {
            _arrayPaper1[i] = new Tpaper();
        }
        SQLHelper.listToEntities(_listRs, _arrayPaper1);
        _sbSQLWhere = new StringBuffer();
        _sbSQL = new StringBuffer();
        _sbSQLWhere.append(", dbo.T_UserAccount A, dbo.T_Coauthor B WHERE ")
                .append(getFullTableName()).append(
                        ".intUserID = A.intUserID AND B.chvCoauthorEmail = '")
                .append(email).append(
                        "' AND A.chvUserAccount = B.chvCoauthorEmail");
        _sbSQL.append(_sbSQLSelect.toString()).append(_sbSQLWhere.toString());
        if (logger.isDebugEnabled()) {
            logger.debug("findAllPapersByEmail() - SQL: " + _sbSQL.toString());
        }
        _listRs = getJdbcTemplate().queryForList(_sbSQL.toString());
        Tpaper[] _arrayPaper2 = new Tpaper[_listRs.size()];
        for (int i = 0; i < _arrayPaper2.length; i++) {
            _arrayPaper2[i] = new Tpaper();
        }
        SQLHelper.listToEntities(_listRs, _arrayPaper2);

        _arrayPaper = new Tpaper[_arrayPaper1.length + _arrayPaper2.length];
        for (int i = 0; i < _arrayPaper1.length; i++) {
            _arrayPaper[i] = _arrayPaper1[i];
        }
        for (int i = _arrayPaper1.length; i < _arrayPaper.length; i++) {
            _arrayPaper[i] = _arrayPaper2[i - _arrayPaper1.length];
        }
        return _arrayPaper;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#updateIsPaperRegisted(Tpaper)
     */
    public int updateIsPaperRegisted(Tpaper paperEntity) throws DaoException {
        int _nRtn = 0;
        try {
            StringBuffer _sbSQL = new StringBuffer();
            int _nIsRegisted = 0;
            int _nIsMailPaperReg = 0;
            int _nIsJuniorAward = 0;
            int _nIsPaperPrize = 0;
            if (paperEntity.getBitIsRegisted().booleanValue()) {
                _nIsRegisted = 1;
            }
            if (paperEntity.getBitIsMailPaperReg().booleanValue()) {
                _nIsMailPaperReg = 1;
            }
            if (paperEntity.getBitIsJuniorAward().booleanValue()) {
                _nIsJuniorAward = 1;
            }
            if (paperEntity.getBitIsPaperPrize().booleanValue()) {
                _nIsPaperPrize = 1;
            }
            _sbSQL.append("UPDATE ").append(getFullTableName()).append(" SET ")
                    .append(getFullTableName()).append(".bitIsRegisted = ")
                    .append(_nIsRegisted).append(", ").append(
                            getFullTableName()).append(".bitIsJuniorAward = ")
                    .append(_nIsJuniorAward).append(", ").append(
                            getFullTableName()).append(".bitIsPaperPrize = ")
                    .append(_nIsPaperPrize).append(", ").append(
                            getFullTableName()).append(".bitIsMailPaperReg = ")
                    .append(_nIsMailPaperReg).append(" WHERE ").append(
                            getFullTableName()).append(".chvPaperNumber = '")
                    .append(paperEntity.getChvPaperNumber()).append("'");

            if (logger.isDebugEnabled()) {
                logger.debug("updateIsPaperRegisted(Tpaper) - SQL: "
                        + _sbSQL.toString());
            }
            _nRtn = getJdbcTemplate().update(_sbSQL.toString());
        } catch (Exception e) {
            logger.error("updateIsPaperRegisted(Tpaper) - Exception", e);

            throw new DaoException(e.getMessage());
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperDao#updateIsPaperRecivedDetail(application.entities.Tpaper)
     */
    public int updateIsPaperRecivedDetail(Tpaper paperEntity)
            throws DaoException {
        int _nRtn = 0;
        try {
            StringBuffer _sbSQL = new StringBuffer();
            int _nIsMailCopyRight = 0;
            int _nIsMailOrgAbstract = 0;
            int _nIsMailOrgPaper = 0;
            if (paperEntity.getBitIsMailCopyright().booleanValue()) {
                _nIsMailCopyRight = 1;
            }
            if (paperEntity.getBitIsMailOrgAbstract().booleanValue()) {
                _nIsMailOrgAbstract = 1;
            }
            if (paperEntity.getBitIsMailOrgPaper().booleanValue()) {
                _nIsMailOrgPaper = 1;
            }
            _sbSQL.append("UPDATE ").append(getFullTableName()).append(" SET ")
                    .append(getFullTableName())
                    .append(".bitIsMailCopyright = ").append(_nIsMailCopyRight)
                    .append(", ").append(getFullTableName()).append(
                            ".bitIsMailOrgAbstract = ").append(
                            _nIsMailOrgAbstract).append(", ").append(
                            getFullTableName()).append(".bitIsMailOrgPaper = ")
                    .append(_nIsMailOrgPaper).append(" WHERE ").append(
                            getFullTableName()).append(".chvPaperNumber = '")
                    .append(paperEntity.getChvPaperNumber()).append("'");

            if (logger.isDebugEnabled()) {
                logger.debug("updateIsPaperRecivedDetail(Tpaper) - SQL: "
                        + _sbSQL.toString());
            }
            _nRtn = getJdbcTemplate().update(_sbSQL.toString());
        } catch (Exception e) {
            logger.error("updateIsPaperRecivedDetail(Tpaper) - Exception", e);

            throw new DaoException(e.getMessage());
        }
        return _nRtn;
    }

	/* (non-Javadoc)
	 * @see application.dao.PaperDao#findAllPapersByAuthorNo(java.lang.String)
	 */
	public Tpaper[] findAllPapersByAuthorNo(String authorNo) throws DaoException {
        StringBuffer _sbSQL = new StringBuffer();
        StringBuffer _sbSQLSelect = new StringBuffer();
        StringBuffer _sbSQLWhere = new StringBuffer();
        _sbSQLSelect.append(SQLHelper.getSelectStatement(getTableName(),
                getTableSchema()));
        _sbSQLWhere
                .append(", dbo.T_RelationBetweenAuthorAndPaper B WHERE ")
                .append(getFullTableName())
                .append(
                        ".chvPaperNumber = B.chvPaperNumber AND B.chrAuthorNo = '")
                .append(authorNo).append("'");
        _sbSQL.append(_sbSQLSelect.toString()).append(_sbSQLWhere.toString());

        if (logger.isDebugEnabled()) {
            logger.debug("findAllPapersByAuthorNo() - SQL: " + _sbSQL.toString());
        }

        List _listRs = getJdbcTemplate().queryForList(_sbSQL.toString());
        Tpaper[] _arrayPaper = new Tpaper[_listRs.size()];
        for (int i = 0; i < _arrayPaper.length; i++) {
            _arrayPaper[i] = new Tpaper();
        }
        SQLHelper.listToEntities(_listRs, _arrayPaper);
        return _arrayPaper;
	}

}
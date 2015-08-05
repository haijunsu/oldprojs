/*
 * @(#)RegistrationFeeDaoImpl.java  2005-2-20
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.RegistrationFeeDao;
import application.entities.TregistrationFee;

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
public class RegistrationFeeDaoImpl extends BaseDaoJdbcImpl implements
        RegistrationFeeDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(RegistrationFeeDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RegistrationFeeDao#create(application.entities.TregistrationFee)
     */
    public TregistrationFee create(TregistrationFee registrationFeeEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("create(TregistrationFee) - start");
        }

        registrationFeeEntity.setChrRegisFeeNo(genStringKey());
        insertEntity(registrationFeeEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("create(TregistrationFee) - end");
        }
        return registrationFeeEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RegistrationFeeDao#load(java.lang.String)
     */
    public TregistrationFee load(String registrationFeeNo) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - start");
        }

        String _strCondition = "chrRegistrationFeeNo = '"
                + StringUtil.escapeSql(registrationFeeNo) + "'";
        TregistrationFee _rfp = (TregistrationFee) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - end");
        }
        return _rfp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RegistrationFeeDao#update(application.entities.TregistrationFee)
     */
    public int update(TregistrationFee registrationFeeEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("update(TregistrationFee) - start");
        }

        int _nRtn = updateEntity(registrationFeeEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("update(TregistrationFee) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RegistrationFeeDao#remove(application.entities.TregistrationFee)
     */
    public int remove(TregistrationFee registrationFeeEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove(TregistrationFee) - start");
        }

        int _nRtn = removeEntity(registrationFeeEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("remove(TregistrationFee) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RegistrationFeeDao#findByRegistrationFeeNo(java.lang.String)
     */
    public TregistrationFee findByRegistrationFeeNo(String registrationFeeNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByRegistrationFeeNo(String) - start");
        }

        TregistrationFee _rfp = load(registrationFeeNo);

        if (logger.isDebugEnabled()) {
            logger.debug("findByRegistrationFeeNo(String) - end");
        }
        return _rfp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RegistrationFeeDao#findByParticipantNo(java.lang.String)
     */
    public TregistrationFee findByParticipantNo(String participantNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByParticipantNo(String) - start");
        }

        String _strCondition = "chrPartiNo = '"
                + StringUtil.escapeSql(participantNo) + "'";
        TregistrationFee _tregistrationFee = (TregistrationFee) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByParticipantNo(String) - end");
        }
        return _tregistrationFee;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RegistrationFeeDao#findByPaperNumber(java.lang.String)
     */
    public TregistrationFee[] findByPaperNumber(String paperNumber)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - start");
        }

        String _strCondition = "chvPaperNumber = '"
                + StringUtil.escapeSql(paperNumber) + "'";
        TregistrationFee[] _arrayRfp = (TregistrationFee[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - end");
        }
        return _arrayRfp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RegistrationFeeDao#listEntities(java.lang.String,
     *      java.lang.String)
     */
    public Object[] listEntities(String condition, String orderBy)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - start");
        }

        List _listRs = find(condition, orderBy);
        TregistrationFee[] _arrayRfp = new TregistrationFee[_listRs.size()];
        for (int i = 0; i < _arrayRfp.length; i++) {
            _arrayRfp[i] = new TregistrationFee();
        }
        SQLHelper.listToEntities(_listRs, _arrayRfp);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayRfp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RegistrationFeeDao#getGrantedPaperLNumberOfParticipantNo(java.lang.String)
     */
    public String getGrantedPaperLNumberOfParticipantNo(String participantNo)
            throws DaoException {
        String _strPaperLNumber = "";
        try {
            StringBuffer _sbSQL = new StringBuffer();
            _sbSQL.append("SELECT ").append(getFullTableName()).append(
                    ".chvPaperNumber AS paperNumber FROM ").append(
                    getFullTableName()).append(" WHERE ").append(
                    getFullTableName()).append(".chrPartiNo = '").append(
                    StringUtil.escapeSql(participantNo)).append("'");

            if (logger.isDebugEnabled()) {
                logger.debug("getGrantedPaperLNumberOfParticipantNo() - SQL: "
                        + _sbSQL.toString());
            }

            _strPaperLNumber = (String) getJdbcTemplate().queryForObject(
                    _sbSQL.toString(), String.class);
        } catch (Exception e) {
            logger
                    .error(
                            "getGrantedPaperLNumberOfParticipantNo(String) - Exception",
                            e);
        }
        return _strPaperLNumber;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.RegistrationFeeDao#isRegFeeReceivedOfParticipantNo(java.lang.String)
     */
    public boolean isRegFeeReceivedOfParticipantNo(String participantNo)
            throws DaoException {
        boolean _isRtn = false;
        try {
            StringBuffer _sbSQL = new StringBuffer();
            _sbSQL.append("SELECT ").append(getFullTableName()).append(
                    ".bitIsRegFeeReceived AS bitIsRegFeeReceived FROM ").append(
                    getFullTableName()).append(" WHERE ").append(
                    getFullTableName()).append(".chrPartiNo = '").append(
                    StringUtil.escapeSql(participantNo)).append("'");

            if (logger.isDebugEnabled()) {
                logger.debug("isRegFeeReceivedOfParticipantNo() - SQL: "
                        + _sbSQL.toString());
            }

            _isRtn = ((Boolean) getJdbcTemplate().queryForObject(
                    _sbSQL.toString(), Boolean.class)).booleanValue();
        } catch (Exception e) {
            logger
                    .error(
                            "isRegFeeReceivedOfParticipantNo(String) - Exception",
                            e);
        }
        return _isRtn;
    }

}
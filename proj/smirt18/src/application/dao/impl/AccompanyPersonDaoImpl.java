/*
 * @(#)AccompanyPersonDaoImpl.java  2005-2-16
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.AccompanyPersonDao;
import application.entities.TaccompanyPerson;

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
public class AccompanyPersonDaoImpl extends BaseDaoJdbcImpl implements
        AccompanyPersonDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(AccompanyPersonDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccompanyPersonDao#create(application.entities.TaccompanyPerson)
     */
    public TaccompanyPerson create(TaccompanyPerson accompanyPersonEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("create(TaccompanyPerson) - start");
        }

        accompanyPersonEntity.setChrAccPersonNo(genStringKey());
        insertEntity(accompanyPersonEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("create(TaccompanyPerson) - end");
        }
        return accompanyPersonEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccompanyPersonDao#load(java.lang.String)
     */
    public TaccompanyPerson load(String accompanyPersonNo) throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - start");
        }
        String _strCondition = "chrAccPersonNo = '"
                + StringUtil.escapeSql(accompanyPersonNo) + "'";
        TaccompanyPerson _returnTaccompanyPerson = (TaccompanyPerson)listEntity(_strCondition);
        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - end");
        }
        return _returnTaccompanyPerson;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccompanyPersonDao#update(application.entities.TaccompanyPerson)
     */
    public int update(TaccompanyPerson accompanyPersonEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("update(TaccompanyPerson) - start");
        }

        int _nRtn = updateEntity(accompanyPersonEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("update(TaccompanyPerson) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccompanyPersonDao#remove(application.entities.TaccompanyPerson)
     */
    public int remove(TaccompanyPerson accompanyPersonEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove(TaccompanyPerson) - start");
        }

        int _nRtn = removeEntity(accompanyPersonEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("remove(TaccompanyPerson) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccompanyPersonDao#findByaccompanyPersonNo(java.lang.String)
     */
    public TaccompanyPerson findByAccompanyPersonNo(String accompanyPersonNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByaccompanyPersonNo(String) - start");
        }

        TaccompanyPerson _accompanyPerson = load(accompanyPersonNo);

        if (logger.isDebugEnabled()) {
            logger.debug("findByaccompanyPersonNo(String) - end");
        }
        return _accompanyPerson;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccompanyPersonDao#findByFirstName(java.lang.String)
     */
    public TaccompanyPerson[] findByFirstName(String firstName)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByFirstName(String) - start");
        }

        String _strCondition = "chvAccPersonFirstName like '%"
                + StringUtil.escapeSql(firstName) + "%'";
        TaccompanyPerson[] _returnTaccompanyPersonArray = (TaccompanyPerson[])listEntities(_strCondition);
        if (logger.isDebugEnabled()) {
            logger.debug("findByFirstName(String) - end");
        }
        return _returnTaccompanyPersonArray;

    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccompanyPersonDao#findByLastName(java.lang.String)
     */
    public TaccompanyPerson[] findByLastName(String lastName)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByLastName(String) - start");
        }

        String _strCondition = "chvAccPersonLastName like '%"
                + StringUtil.escapeSql(lastName) + "%'";
        TaccompanyPerson[] _returnTaccompanyPersonArray = (TaccompanyPerson[])listEntities(_strCondition);
        if (logger.isDebugEnabled()) {
            logger.debug("findByLastName(String) - end");
        }
        return _returnTaccompanyPersonArray;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccompanyPersonDao#findByParticipantNo(java.lang.String)
     */
    public TaccompanyPerson[] findByParticipantNo(String participantNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByParticipantNo(String) - start");
        }

        String _strCondition = "chrPartiNo = '"
                + StringUtil.escapeSql(participantNo) + "'";
        TaccompanyPerson[] _returnTaccompanyPersonArray = (TaccompanyPerson[])listEntities(_strCondition);
        if (logger.isDebugEnabled()) {
            logger.debug("findByParticipantNo(String) - end");
        }
        return _returnTaccompanyPersonArray;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccompanyPersonDao#findByPassNumber(java.lang.String)
     */
    public TaccompanyPerson findByPassportNumber(String passportNumber)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPassportNumber(String) - start");
        }

        String _strCondition = "chvPassportNumber = '"
                + StringUtil.escapeSql(passportNumber) + "'";
        TaccompanyPerson _returnTaccompanyPerson = (TaccompanyPerson)listEntity(_strCondition);
        if (logger.isDebugEnabled()) {
            logger.debug("findByPassportNumber(String) - end");
        }
        return _returnTaccompanyPerson;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccompanyPersonDao#listAll()
     */
    public TaccompanyPerson[] listAll() throws DaoException
             {
        if (logger.isDebugEnabled()) {
            logger.debug("listAll() - start");
        }

        TaccompanyPerson[] _returnTaccompanyPersonArray = (TaccompanyPerson[])listEntities(null);
        if (logger.isDebugEnabled()) {
            logger.debug("listAll() - end");
        }
        return _returnTaccompanyPersonArray;

    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.AccompanyPersonDao#listEntities(java.lang.String,
     *      java.lang.String)
     */
    public Object[] listEntities(String condition, String orderBy)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - start");
        }

        List _listRs = find(condition, orderBy);
        TaccompanyPerson[] _arrayAccompanyPerson = new TaccompanyPerson[_listRs
                .size()];
        for (int i = 0; i < _arrayAccompanyPerson.length; i++) {
            _arrayAccompanyPerson[i] = new TaccompanyPerson();
        }
        SQLHelper.listToEntities(_listRs, _arrayAccompanyPerson);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayAccompanyPerson;
    }

    /* (non-Javadoc)
     * @see application.dao.AccompanyPersonDao#countByParticipantNo(java.lang.String)
     */
    public int countByParticipantNo(String participantNo) throws DaoException {
        int _nCount = 0;
        String _strCondition = "chrPartiNo = '" + StringUtil.escapeSql(participantNo) + "'";
        _nCount = count(_strCondition);
        return _nCount;
    }

}
/*
 * @(#)VisaDaoImpl.java  2005-2-20
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.util.List;

import framework.exception.DaoException;
import framework.util.StringUtil;
import application.dao.VisaDao;
import application.entities.Tvisa;

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
public class VisaDaoImpl extends BaseDaoJdbcImpl implements VisaDao {

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.VisaDao#create(application.entities.Tvisa)
     */
    public Tvisa create(Tvisa visaEntity) throws DaoException {
        visaEntity.setChrVisaNo(genStringKey());
        insertEntity(visaEntity);
        return visaEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.VisaDao#load(java.lang.String)
     */
    public Tvisa load(String visaNo) throws DaoException {
        String _strCondition = "chrVisaNo = '" + StringUtil.escapeSql(visaNo)
                + "'";
        Tvisa _visa = (Tvisa) listEntity(_strCondition);
        return _visa;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.VisaDao#update(application.entities.Tvisa)
     */
    public int update(Tvisa visaEntity) throws DaoException {
        int _nRtn = updateEntity(visaEntity);
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.VisaDao#remove(application.entities.Tvisa)
     */
    public int remove(Tvisa visaEntity) throws DaoException {
        int _nRtn = removeEntity(visaEntity);
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.VisaDao#findByVisaNo(java.lang.String)
     */
    public Tvisa findByVisaNo(String visaNo) throws DaoException {
        Tvisa _visa = load(visaNo);
        return _visa;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.VisaDao#findByParticipantNo(java.lang.String)
     */
    public Tvisa[] findByParticipantNo(String participantNo)
            throws DaoException {
        String _strCondition = "chrPartiNo = '"
                + StringUtil.escapeSql(participantNo) + "'";
        Tvisa[] _arrayVisa = (Tvisa[]) listEntities(_strCondition);
        return _arrayVisa;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.VisaDao#findByAccPersonNo(java.lang.String)
     */
    public Tvisa[] findByAccPersonNo(String accPersonNo) throws DaoException {
        String _strCondition = "chrAccPersonNo = '"
                + StringUtil.escapeSql(accPersonNo) + "'";
        Tvisa[] _arrayVisa = (Tvisa[]) listEntities(_strCondition);
        return _arrayVisa;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.VisaDao#findByPassportNumber(java.lang.String)
     */
    public Tvisa[] findByPassportNumber(String passportNumber)
            throws DaoException {
        String _strCondition = "chvPassportNumber = '"
                + StringUtil.escapeSql(passportNumber) + "'";
        Tvisa[] _arrayVisa = (Tvisa[]) listEntities(_strCondition);
        return _arrayVisa;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.VisaDao#listEntities(java.lang.String,
     *      java.lang.String)
     */
    public Object[] listEntities(String condition, String orderBy)
            throws DaoException {
        List _listRs = find(condition, orderBy);
        Tvisa[] _arrayVisa = new Tvisa[_listRs.size()];
        for (int i = 0; i < _arrayVisa.length; i++) {
            _arrayVisa[i] = new Tvisa();
        }
        SQLHelper.listToEntities(_listRs, _arrayVisa);
        return _arrayVisa;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.VisaDao#findByPartiNoAndAccPersonNo(java.lang.String,
     *      java.lang.String)
     */
    public Tvisa findByPartiNoAndAccPersonNo(String participantNo,
            String accPersonNo) throws DaoException {
        String _strCondition = "chrPartiNo = '"
                + StringUtil.escapeSql(participantNo)
                + "' AND chrAccPersonNo = '"
                + StringUtil.escapeSql(accPersonNo)
                + "'";
        Tvisa _visa = (Tvisa) listEntity(_strCondition);
        return _visa;
    }

}
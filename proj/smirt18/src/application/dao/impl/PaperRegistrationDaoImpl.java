/*
 * @(#)PaperRegistrationDaoImpl.java  2005-2-19
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import application.dao.PaperRegistrationDao;
import application.entities.TpaperRegistation;

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
public class PaperRegistrationDaoImpl extends BaseDaoJdbcImpl implements
        PaperRegistrationDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(PaperRegistrationDaoImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperRegistrationDao#create(application.entities.TpaperRegistation)
     */
    public TpaperRegistation create(TpaperRegistation paperRegistationEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("create(TpaperRegistation) - start");
        }

        paperRegistationEntity.setChrPaperRegNo(genStringKey());
        insertEntity(paperRegistationEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("create(TpaperRegistation) - end");
        }
        return paperRegistationEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperRegistrationDao#load(java.lang.String)
     */
    public TpaperRegistation load(String paperRegistrationNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - start");
        }

        String _strCondition = "chrPaperRegNo = '"
                + StringUtil.escapeSql(paperRegistrationNo) + "'";
        TpaperRegistation _paperRegistration = (TpaperRegistation) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("load(String) - end");
        }
        return _paperRegistration;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperRegistrationDao#update(application.entities.TpaperRegistation)
     */
    public int update(TpaperRegistation paperRegistationEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("update(TpaperRegistation) - start");
        }

        int _nRtn = updateEntity(paperRegistationEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("update(TpaperRegistation) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperRegistrationDao#remove(application.entities.TpaperRegistation)
     */
    public int remove(TpaperRegistation paperRegistationEntity)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove(TpaperRegistation) - start");
        }

        int _nRtn = removeEntity(paperRegistationEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("remove(TpaperRegistation) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperRegistrationDao#findByPaperRegNo(java.lang.String)
     */
    public TpaperRegistation findByPaperRegNo(String paperRegNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperRegNo(String) - start");
        }

        TpaperRegistation _paperRegistration = load(paperRegNo);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperRegNo(String) - end");
        }
        return _paperRegistration;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperRegistrationDao#findByPaperRegTableNo(java.lang.String)
     */
    public TpaperRegistation findByPaperRegTableNo(String paperRegTableNo)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperRegTableNo(String) - start");
        }

        String _strCondition = "chrPaperRegTableNo = '"
                + StringUtil.escapeSql(paperRegTableNo) + "'";
        TpaperRegistation _paperRegistration = (TpaperRegistation) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperRegTableNo(String) - end");
        }
        return _paperRegistration;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperRegistrationDao#findByPaperNumber(java.lang.String)
     */
    public TpaperRegistation findByPaperNumber(String paperNumber)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - start");
        }

        String _strCondition = "chvPaperNumber = '"
                + StringUtil.escapeSql(paperNumber) + "'";
        TpaperRegistation _paperRegistration = (TpaperRegistation) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperNumber(String) - end");
        }
        return _paperRegistration;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperRegistrationDao#findByPaperLNumber(java.lang.String)
     */
    public TpaperRegistation findByPaperLNumber(String paperLNumber)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperLNumber(String) - start");
        }

        String _strCondition = "chvPaperLNumber = '"
                + StringUtil.escapeSql(paperLNumber) + "'";
        TpaperRegistation _paperRegistration = (TpaperRegistation) listEntity(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperLNumber(String) - end");
        }
        return _paperRegistration;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperRegistrationDao#findByPaperRegFirstName(java.lang.String)
     */
    public TpaperRegistation[] findByPaperRegFirstName(String paperRegFirstName)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperRegFirstName(String) - start");
        }

        String _strCondition = "chvPaperRegFirstName like '%"
                + StringUtil.escapeSql(paperRegFirstName) + "%'";
        TpaperRegistation[] _arrayPaperRegistration = (TpaperRegistation[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperRegFirstName(String) - end");
        }
        return _arrayPaperRegistration;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperRegistrationDao#findByPaperRegLastName(java.lang.String)
     */
    public TpaperRegistation[] findByPaperRegLastName(String paperRegLastName)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperRegLastName(String) - start");
        }

        String _strCondition = "chvPaperRegLastName like '%"
                + StringUtil.escapeSql(paperRegLastName) + "%'";
        TpaperRegistation[] _arrayPaperRegistration = (TpaperRegistation[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperRegLastName(String) - end");
        }
        return _arrayPaperRegistration;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperRegistrationDao#findByPaperRegEmail(java.lang.String)
     */
    public TpaperRegistation[] findByPaperRegEmail(String paperRegEmail)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperRegEmail(String) - start");
        }

        TpaperRegistation[] _arrayPaperRegistration = (TpaperRegistation[]) findByPaperRegEmail(paperRegEmail, false);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperRegEmail(String) - end");
        }
        return _arrayPaperRegistration;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperRegistrationDao#findByPaperRegBankName(java.lang.String)
     */
    public TpaperRegistation[] findByPaperRegBankName(String paperRegBankName)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperRegBankName(String) - start");
        }

        String _strCondition = "chvPaperRegBankName like '%"
                + StringUtil.escapeSql(paperRegBankName) + "%'";
        TpaperRegistation[] _arrayPaperRegistration = (TpaperRegistation[]) listEntities(_strCondition);

        if (logger.isDebugEnabled()) {
            logger.debug("findByPaperRegBankName(String) - end");
        }
        return _arrayPaperRegistration;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.PaperRegistrationDao#listEntities(java.lang.String,
     *      java.lang.String)
     */
    public Object[] listEntities(String condition, String orderBy)
            throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - start");
        }

        List _listRs = find(condition, orderBy);
        TpaperRegistation[] _arrayPr = new TpaperRegistation[_listRs.size()];
        for (int i = 0; i < _arrayPr.length; i++) {
            _arrayPr[i] = new TpaperRegistation();
        }
        SQLHelper.listToEntities(_listRs, _arrayPr);

        if (logger.isDebugEnabled()) {
            logger.debug("listEntities(String, String) - end");
        }
        return _arrayPr;
    }

    /* (non-Javadoc)
     * @see application.dao.PaperRegistrationDao#findByPaperRegEmail(java.lang.String, boolean)
     */
    public TpaperRegistation[] findByPaperRegEmail(String paperRegEmail, boolean isExact) throws DaoException {
        String _strCondition = "chvPaperRegEmail like '%"
            + StringUtil.escapeSql(paperRegEmail) + "%'";
        if (isExact) {
            _strCondition = "chvPaperRegEmail = '"
                + StringUtil.escapeSql(paperRegEmail) + "'";
        }
        TpaperRegistation[] _arrayPaperRegistration = (TpaperRegistation[]) listEntities(_strCondition);

        return _arrayPaperRegistration;
    }

}
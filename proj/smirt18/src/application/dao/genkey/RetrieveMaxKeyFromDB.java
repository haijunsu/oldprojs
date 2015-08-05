/*
 * @(#)RetrieveMaxKeyFromDB.java  2005-2-15
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.genkey;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import framework.service.impl.JdbcServiceImpl;
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
public class RetrieveMaxKeyFromDB extends JdbcServiceImpl {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(RetrieveMaxKeyFromDB.class);

    public String getStringMaxKey(String tableName, String tableSchema,
            String keyVol) {
        if (logger.isDebugEnabled()) {
            logger.debug("getStringMaxKey(String, String) - start");
        }
        String _strMaxKey = "0";
        try {
            if (StringUtil.isNotBlank(tableSchema)) {
                tableName = tableSchema + "." + tableName;
            }
            String _strSql = "SELECT MAX(" + keyVol + ") FROM " + tableName;
            if (logger.isDebugEnabled()) {
                logger.debug("getStringMaxKey(String, String) - SQL: "
                        + _strSql);
            }
            _strMaxKey = (String) getJdbcTemplate().queryForObject(_strSql,
                    String.class);
            if (StringUtil.isBlank(_strMaxKey)) {
                _strMaxKey = "0";
            }
        } catch (NullPointerException e) {
            logger.error("getStringMaxKey(String, String)", e);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("getStringMaxKey(String, String) - return value = "
                    + _strMaxKey);
            logger.debug("getStringMaxKey(String, String) - end");
        }
        return _strMaxKey;
    }

    public Long getLongMaxKey(String tableName, String tableSchema,
            String keyVol) {
        if (logger.isDebugEnabled()) {
            logger.debug("getLongMaxKey(String, String) - start");
        }
        Long _llMaxKey = new Long(0);
        try {
            if (StringUtil.isNotBlank(tableSchema)) {
                tableName = tableSchema + "." + tableName;
            }
            String _strSql = "SELECT MAX(" + keyVol + ") FROM " + tableName;
            if (logger.isDebugEnabled()) {
                logger.debug("getLongMaxKey(String, String) - SQL: " + _strSql);
            }
            _llMaxKey = new Long(getJdbcTemplate().queryForLong(_strSql));
        } catch (NullPointerException e) {
            logger.error("getLongMaxKey(String, String)", e);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("getStringMaxKey(String, String) - return value = "
                    + _llMaxKey);
            logger.debug("getLongMaxKey(String, String) - end");
        }
        return _llMaxKey;
    }
}
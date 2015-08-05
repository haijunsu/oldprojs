/*
 * @(#)SQLHelper.java  2005-2-16
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;

import application.exception.BusinessServiceException;
import application.helper.ResourcesHelper;
import framework.util.ArrayUtil;
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
public class SQLHelper {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(SQLHelper.class);

    private static MessageResources m_sqlResources;

    static {
        try {
            m_sqlResources = ResourcesHelper.getDaoSQLStatements();
        } catch (Exception e) {

        }
    }

    public static String[] getColumns(String tableName) {
        if (logger.isDebugEnabled()) {
            logger.debug("getColumns(String) - start");
        }

        String[] _returnStringArray = getColumns(tableName, false);
        if (logger.isDebugEnabled()) {
            logger.debug("getColumns(String) - end");
        }
        return _returnStringArray;
    }

    public static String[] getKeyColumns(String tableName) {
        if (logger.isDebugEnabled()) {
            logger.debug("getKeyColumns(String) - start");
        }

        String[] _returnStringArray = StringUtil.split(m_sqlResources
                .getMessage(tableName + ".keys"), ",");
        if (logger.isDebugEnabled()) {
            logger.debug("getKeyColumns(String) - return value: "
                    + ArrayUtil.toString(_returnStringArray));
            logger.debug("getKeyColumns(String) - end");
        }
        return _returnStringArray;
    }
    
    public static String[] getKeyColumnsLength(String tableName) {
        if (logger.isDebugEnabled()) {
            logger.debug("getKeyColumnsLength(String) - start");
        }

        String[] _returnStringArray = StringUtil.split(m_sqlResources
                .getMessage(tableName + ".keys.length"), ",");
        if (logger.isDebugEnabled()) {
            logger.debug("getKeyColumnsLength(String) - return value: "
                    + ArrayUtil.toString(_returnStringArray));
            logger.debug("getKeyColumnsLength(String) - end");
        }
        return _returnStringArray;
    }
    

    public static String[] getColumns(String tableName, boolean withoutKey) {
        if (logger.isDebugEnabled()) {
            logger.debug("getColumns(String, boolean) - start");
        }

        String[] _arrayColumns = StringUtil.split(m_sqlResources
                .getMessage(tableName + ".columns"), ",");
        if (withoutKey) {
            String[] _arrayKeyColumns = getKeyColumns(tableName);
            String[] _arrayColumnsWithoutKey = new String[_arrayColumns.length
                    - _arrayKeyColumns.length];
            int _arrayIndex = 0;
            for (int i = 0; i < _arrayColumns.length; i++) {
                if (ArrayUtil.contains(_arrayKeyColumns, _arrayColumns[i])) {
                    continue;
                }
                _arrayColumnsWithoutKey[_arrayIndex] = _arrayColumns[i];
                _arrayIndex++;
            }

            if (logger.isDebugEnabled()) {
                logger.debug("getColumns(String, boolean) - return value withoutKey: "
                        + ArrayUtil.toString(_arrayColumnsWithoutKey));
                logger.debug("getColumns(String, boolean) - end");
            }
            return _arrayColumnsWithoutKey;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("getColumns(String, boolean) - return value: "
                        + ArrayUtil.toString(_arrayColumns));
                logger.debug("getColumns(String, boolean) - end");
            }
            return _arrayColumns;
        }
    }

    public static String getSelectStatement(String tableName) {
        if (logger.isDebugEnabled()) {
            logger.debug("getSelectStatement(String) - start");
        }

        String _returnString = getSelectStatement(tableName, null);
        if (logger.isDebugEnabled()) {
            logger.debug("getSelectStatement(String) - end");
        }
        return _returnString;
    }

    public static String getSelectStatement(String tableName, String schema) {
        if (logger.isDebugEnabled()) {
            logger.debug("getSelectStatement(String, String) - start");
        }

        StringBuffer _sbSQL = new StringBuffer();
        _sbSQL.append("SELECT ");
        String[] _arrayColumns = getColumns(tableName);
        for (int i = 0; i < _arrayColumns.length - 1; i++) {
            if (StringUtil.isNotBlank(schema)) {
                if (StringUtil.isNotBlank(schema)) {
                    _sbSQL.append(schema).append(".");
                }
                _sbSQL.append(tableName)
                	.append(".")
                	.append(_arrayColumns[i])
                	.append(" AS ")
                	.append(_arrayColumns[i])
                	.append(", ");
            }
        }
        if (StringUtil.isNotBlank(schema)) {
            if (StringUtil.isNotBlank(schema)) {
                _sbSQL.append(schema).append(".");
            }
            _sbSQL.append(tableName)
            	.append(".")
            	.append(_arrayColumns[_arrayColumns.length - 1])
            	.append(" AS ")
            	.append(_arrayColumns[_arrayColumns.length - 1])
            	.append(" ");
        }
       
//        _sbSQL.append(m_sqlResources.getMessage(tableName + ".columns"));
        _sbSQL.append(" FROM ");
        if (StringUtil.isNotBlank(schema)) {
            _sbSQL.append(schema).append(".");
        }
        _sbSQL.append(tableName);
        String _returnString = _sbSQL.toString();
        if (logger.isDebugEnabled()) {
            logger.debug("getSelectStatement(String, String) - return value: "
                    + _returnString);
            logger.debug("getSelectStatement(String, String) - end");
        }
        return _returnString;
    }

    public static String getUpdateStatement(String tableName) {
        if (logger.isDebugEnabled()) {
            logger.debug("getUpdateStatement(String) - start");
        }

        String _returnString = getUpdateStatement(tableName, null);
        if (logger.isDebugEnabled()) {
            logger.debug("getUpdateStatement(String) - end");
        }
        return _returnString;
    }

    public static String getUpdateStatement(String tableName, String schema) {
        if (logger.isDebugEnabled()) {
            logger.debug("getUpdateStatement(String, String) - start");
        }

        String _returnString = getUpdateStatement(tableName, schema, false);
        if (logger.isDebugEnabled()) {
            logger.debug("getUpdateStatement(String, String) - end");
        }
        return _returnString;
    }

    public static String getUpdateStatement(String tableName, String schema,
            boolean withoutKey) {
        if (logger.isDebugEnabled()) {
            logger.debug("getUpdateStatement(String, String, boolean) - start");
        }

        StringBuffer _sbSQL = new StringBuffer();
        String[] _strColumns = getColumns(tableName);
        String[] _strKeyColumns = getKeyColumns(tableName);
        _sbSQL.append("UPDATE ");
        if (StringUtil.isNotBlank(schema)) {
            _sbSQL.append(schema).append(".");
        }
        _sbSQL.append(tableName).append(" SET ");
        for (int i = 0; i < _strColumns.length; i++) {
            if (withoutKey) {
                if (ArrayUtil.contains(_strKeyColumns, _strColumns[i]))
                    continue;
            }
            _sbSQL.append(_strColumns[i]).append("=?, ");
        }
        String _str = _sbSQL.toString();
        if (_str.endsWith(", ")) {
            _str = StringUtil.left(_str, _str.length() - 2);
        }

        if (logger.isDebugEnabled()) {
            logger
                    .debug("getUpdateStatement(String, String, boolean) - return value: "
                            + _str);
            logger.debug("getUpdateStatement(String, String, boolean) - end");
        }
        return _str;
    }

    public static String getInsertStatement(String tableName) {
        if (logger.isDebugEnabled()) {
            logger.debug("getInsertStatement(String) - start");
        }

        String _returnString = getInsertStatement(tableName, null, false);
        if (logger.isDebugEnabled()) {
            logger.debug("getInsertStatement(String) - return value: "
                    + _returnString);
            logger.debug("getInsertStatement(String) - end");
        }
        return _returnString;
    }

    public static String getInsertStatement(String tableName, String schema) {
        if (logger.isDebugEnabled()) {
            logger.debug("getInsertStatement(String, String) - start");
        }

        String _returnString = getInsertStatement(tableName, schema, false);
        if (logger.isDebugEnabled()) {
            logger.debug("getInsertStatement(String, String) - return value: "
                    + _returnString);
            logger.debug("getInsertStatement(String, String) - end");
        }
        return _returnString;
    }

    public static String getInsertStatement(String tableName, String schema,
            boolean withoutKey) {
        if (logger.isDebugEnabled()) {
            logger.debug("getInsertStatement(String, String, boolean) - start");
        }

        StringBuffer _sbSQL = new StringBuffer();
        String[] _strColumns = getColumns(tableName);
        String[] _strKeyColumns = getKeyColumns(tableName);
        int _nParamsLen = 0;
        _sbSQL.append("INSERT INTO ");
        if (StringUtil.isNotBlank(schema)) {
            _sbSQL.append(schema).append(".");
        }
        _sbSQL.append(tableName).append(" (");
        for (int i = 0; i < _strColumns.length; i++) {
            if (withoutKey) {
                if (ArrayUtil.contains(_strKeyColumns, _strColumns[i]))
                    continue;
            }
            _sbSQL.append(_strColumns[i]).append(", ");
            _nParamsLen++;
        }
        String _str = _sbSQL.toString();
        if (_str.endsWith(", ")) {
            _str = StringUtil.left(_str, _str.length() - 2);
        }
        _sbSQL = new StringBuffer();
        _sbSQL.append(_str).append(") VALUES (");
        _sbSQL.append(StringUtil.repeat("?, ", _nParamsLen - 1)).append("?)");
        _str = _sbSQL.toString();

        if (logger.isDebugEnabled()) {
            logger
                    .debug("getInsertStatement(String, String, boolean) - return value: "
                            + _str);
            logger.debug("getInsertStatement(String, String, boolean) - end");
        }
        return _str;
    }

    public static String getDeleteStatement(String tableName) {
        if (logger.isDebugEnabled()) {
            logger.debug("getDeleteStatement(String) - start");
        }

        String _returnString = getDeleteStatement(tableName, null);
        if (logger.isDebugEnabled()) {
            logger.debug("getDeleteStatement(String) - return value: "
                    + _returnString);
            logger.debug("getDeleteStatement(String) - end");
        }
        return _returnString;
    }

    public static String getDeleteStatement(String tableName, String schema) {
        if (logger.isDebugEnabled()) {
            logger.debug("getDeleteStatement(String, String) - start");
        }

        StringBuffer _sbSQL = new StringBuffer();
        _sbSQL.append("DELETE FROM ");
        if (StringUtil.isNotBlank(schema)) {
            _sbSQL.append(schema).append(".");
        }
        _sbSQL.append(tableName);
        String _returnString = _sbSQL.toString();
        if (logger.isDebugEnabled()) {
            logger.debug("getDeleteStatement(String, String) - return value: "
                    + _returnString);
            logger.debug("getDeleteStatement(String, String) - end");
        }
        return _returnString;
    }

    public static void listToEntities(List listRs, Object[] obj) {
        if (logger.isDebugEnabled()) {
            logger.debug("listToEntities(List, Object[]) - start");
        }

        int _arrayIndex = 0;
        for (Iterator iter = listRs.iterator(); iter.hasNext();) {
            HashMap _hm = (HashMap) iter.next();
            try {
                BeanUtils.copyProperties(obj[_arrayIndex], _hm);
            } catch (IllegalAccessException e) {
                logger.error("listToEntities(List, Object[])", e);

                throw new BusinessServiceException(122,
                        "copy properties to paperInfo fail.");
            } catch (InvocationTargetException e) {
                logger.error("listToEntities(List, Object[])", e);

                throw new BusinessServiceException(122,
                        "copy properties to paperInfo fail.");
            }
            _arrayIndex++;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("listToEntities(List, Object[]) - end");
        }
    }

}
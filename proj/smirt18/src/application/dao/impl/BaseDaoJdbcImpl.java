/*
 * @(#)BaseDaoJdbcImpl.java  2005-2-17
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package application.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dao.BaseDao;
import application.dao.genkey.GenKeyService;

import framework.exception.DaoException;
import framework.service.impl.JdbcServiceImpl;
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
public abstract class BaseDaoJdbcImpl extends JdbcServiceImpl implements
        BaseDao {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory.getLog(BaseDaoJdbcImpl.class);

    private String m_tableName;

    private String m_tableSchema;

    private boolean m_withoutKeyColumns;

    protected String genStringKey() {
        String[] _strKeys = SQLHelper.getKeyColumns(this.m_tableName);
        String[] _strKeysLength = SQLHelper
                .getKeyColumnsLength(this.m_tableName);
        String _str = GenKeyService.genStringkey(this.m_tableName,
                this.m_tableSchema, _strKeys[0], Integer
                        .parseInt(_strKeysLength[0]));
        return _str;
    }

    protected Long genLongKey() {
        String[] _strKeys = SQLHelper.getKeyColumns(this.m_tableName);
        Long _llong = GenKeyService.genLongkey(this.m_tableName,
                this.m_tableSchema, _strKeys[0]);
        return _llong;
    }

    protected Object insertEntity(Object obj) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("insertEntity(Object) - start");
        }

        try {
            String _strInsertSQL = SQLHelper.getInsertStatement(
                    this.m_tableName, this.m_tableSchema,
                    this.m_withoutKeyColumns);
            String[] _strArrayColumnsName = SQLHelper.getColumns(
                    this.m_tableName, this.m_withoutKeyColumns);
            Object[] _oParams = new Object[_strArrayColumnsName.length];
            for (int i = 0; i < _oParams.length; i++) {
                _oParams[i] = BeanUtils.getSimpleProperty(obj,
                        _strArrayColumnsName[i].trim());
                if (_oParams[i] == null)
                    _oParams[i] = "";
                if (_strArrayColumnsName[i].trim().toLowerCase().startsWith(
                        "mny")) {
                    if (StringUtil.isNotBlank(_oParams[i].toString())) {
                        _oParams[i] = new BigDecimal(_oParams[i].toString());
                    } else {
                        _oParams[i] = new BigDecimal(0);
                    }
                }
                if (_strArrayColumnsName[i].trim().toLowerCase().startsWith(
                "bit")) {
                    
                    if (_oParams[i].equals("true")) {
                        _oParams[i] = "1";
                    } else {
                        _oParams[i] = "0";
                    }
                }
            }
            if (logger.isDebugEnabled()) {
                logger.debug("insertEntity(Object) - SQL: " + _strInsertSQL);
                logger.debug("insertEntity(Object) - parameters: "
                        + ArrayUtil.toString(_oParams));
            }

            getJdbcTemplate().update(_strInsertSQL, _oParams);

            if (logger.isDebugEnabled()) {
                logger.debug("insertEntity(Object) - end");
            }
            return obj;
        } catch (Exception e) {
            logger.error("insertEntity(Object)", e);

            throw new DaoException(e.getMessage());
        }
    }

    protected int updateEntity(Object obj) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("updateEntity(Object) - start");
        }

        int _nUpdateState = updateEntity(obj, null);

        if (logger.isDebugEnabled()) {
            logger.debug("updateEntity(Object) - end");
        }
        return _nUpdateState;

    }

    protected int updateEntity(Object obj, String[] conditionColumns) {
        if (logger.isDebugEnabled()) {
            logger.debug("updateEntity(Object, String[]) - start");
        }

        int _nUpdateState = 0;
        try {
            StringBuffer _sbSQL = new StringBuffer();

            String[] _strArrayColumnsName = SQLHelper.getColumns(
                    this.m_tableName, true);
            String[] _strArrayColumnsKey = conditionColumns;
            if (ArrayUtil.isEmpty(_strArrayColumnsKey)) {
                _strArrayColumnsKey = SQLHelper.getKeyColumns(this.m_tableName);
            }
            Object[] _oParams = new Object[_strArrayColumnsName.length
                    + _strArrayColumnsKey.length];

            _sbSQL.append(
                    SQLHelper.getUpdateStatement(this.m_tableName,
                            this.m_tableSchema, true)).append(" WHERE ");
            for (int i = 0; i < _strArrayColumnsKey.length; i++) {
                _sbSQL.append(_strArrayColumnsKey[i]).append(" = ?");
                if (i < _strArrayColumnsKey.length - 1) {
                    _sbSQL.append(" AND ");
                }
            }
            for (int i = 0; i < _strArrayColumnsName.length; i++) {
                _oParams[i] = BeanUtils.getSimpleProperty(obj,
                        _strArrayColumnsName[i].trim());
                if (_oParams[i] == null)
                    _oParams[i] = "";
                if (_strArrayColumnsName[i].trim().toLowerCase().startsWith(
                        "mny")) {
                    if (StringUtil.isNotBlank(_oParams[i].toString())) {
                        _oParams[i] = new BigDecimal(_oParams[i].toString());
                    } else {
                        _oParams[i] = new BigDecimal(0);
                    }
                }
                if (_strArrayColumnsName[i].trim().toLowerCase().startsWith(
                "bit")) {
                    
                    if (_oParams[i].equals("true")) {
                        _oParams[i] = "1";
                    } else {
                        _oParams[i] = "0";
                    }
                }
                
            }
            for (int i = _strArrayColumnsName.length; i < _oParams.length; i++) {
                _oParams[i] = BeanUtils.getSimpleProperty(obj,
                        _strArrayColumnsKey[i - _strArrayColumnsName.length]
                                .trim());
                if (_oParams[i] == null)
                    _oParams[i] = "";
                if (_strArrayColumnsKey[i - _strArrayColumnsName.length].trim()
                        .toLowerCase().startsWith("mny")) {
                    if (StringUtil.isNotBlank(_oParams[i].toString())) {
                        _oParams[i] = new BigDecimal(_oParams[i].toString());
                    } else {
                        _oParams[i] = new BigDecimal(0);
                    }
                }
            }
            if (logger.isDebugEnabled()) {
                logger.debug("updateEntity(Object, String[]) - SQL: "
                        + _sbSQL.toString());
                logger.debug("updateEntity(Object, String[]) - parameters: "
                        + ArrayUtil.toString(_oParams));
            }

            _nUpdateState = getJdbcTemplate().update(_sbSQL.toString(),
                    _oParams);
        } catch (Exception e) {
            logger.error("updateEntity(Object, String[])", e);

            throw new DaoException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("updateEntity(Object, String[]) - end");
        }
        return _nUpdateState;
    }

    protected int removeEntity(Object obj) {
        if (logger.isDebugEnabled()) {
            logger.debug("removeEntity(Object) - start");
        }

        int _nRtn = removeEntity(obj, null);

        if (logger.isDebugEnabled()) {
            logger.debug("removeEntity(Object) - end");
        }
        return _nRtn;
    }

    protected int removeEntity(Object obj, String[] conditionColumns) {
        if (logger.isDebugEnabled()) {
            logger.debug("removeEntity(Object, String[]) - start");
        }

        int _nRtn = 0;

        try {
            StringBuffer _sbCondition = new StringBuffer();
            String[] _strArrayKey = conditionColumns;
            if (ArrayUtil.isEmpty(_strArrayKey)) {
                _strArrayKey = SQLHelper.getKeyColumns(getTableName());
            }
            String[] _strArrayParams = new String[_strArrayKey.length];
            for (int i = 0; i < _strArrayKey.length; i++) {
                _sbCondition.append(_strArrayKey[i]).append(" = ?");
                if (i < _strArrayKey.length - 1) {
                    _sbCondition.append(" AND ");
                }
            }
            for (int i = 0; i < _strArrayParams.length; i++) {
                _strArrayParams[i] = BeanUtils.getSimpleProperty(obj,
                        _strArrayKey[i].trim());
                if (_strArrayParams[i] == null)
                    _strArrayParams[i] = "";
            }

            _nRtn = remove(_sbCondition.toString(), _strArrayParams);

        } catch (Exception e) {
            logger.error("removeEntity(Object, String[])", e);

            throw new DaoException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("removeEntity(Object, String[]) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.BaseDao#findAll()
     */
    protected List findAll() throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("findAll() - start");
        }

        List _rtnList = find(null);

        if (logger.isDebugEnabled()) {
            logger.debug("findAll() - end");
        }
        return _rtnList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.BaseDao#find(java.lang.String)
     */
    protected List find(String condition) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("find(String) - start");
        }

        List _rtnList = find(condition, null);

        if (logger.isDebugEnabled()) {
            logger.debug("find(String) - end");
        }
        return _rtnList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.BaseDao#remove(java.lang.String)
     */
    public int remove(String condition) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove(String) - start");
        }

        int _nRtn = remove(condition, null);

        if (logger.isDebugEnabled()) {
            logger.debug("remove(String) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.BaseDao#remove(java.lang.String, java.lang.Object[])
     */
    public int remove(String condition, Object[] objParams) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("remove(String, Object[]) - start");
        }

        int _nRtn = 0;

        try {
            StringBuffer _sbSQL = new StringBuffer();
            _sbSQL.append(SQLHelper.getDeleteStatement(this.m_tableName,
                    this.m_tableSchema));
            if (StringUtil.isNotBlank(condition)) {
                _sbSQL.append(" WHERE ").append(condition);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("remove(String, Object[]) - SQL: "
                        + _sbSQL.toString());
            }

            if (ArrayUtil.isEmpty(objParams)) {
                _nRtn = getJdbcTemplate().update(_sbSQL.toString());
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("remove(String, Object[]) - parameters: "
                            + ArrayUtil.toString(objParams));
                }
                _nRtn = getJdbcTemplate().update(_sbSQL.toString(), objParams);
            }

        } catch (Exception e) {
            logger.error("remove(String, Object[]) - Exception: "
                    + e.getMessage(), e);
            throw new DaoException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("remove(String, Object[]) - end");
        }
        return _nRtn;
    }

    public String getTableName() {
        return this.m_tableName;
    }

    public void setTableName(String tableName) {
        this.m_tableName = tableName;
    }

    public String getTableSchema() {
        return this.m_tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.m_tableSchema = tableSchema;
    }

    public boolean isWithoutKeyColumns() {
        return this.m_withoutKeyColumns;
    }

    public void setWithoutKeyColumns(boolean withoutKeyColumns) {
        this.m_withoutKeyColumns = withoutKeyColumns;
    }

    private String getDefaultOrderBy() {
        StringBuffer _sbOrderBy = new StringBuffer();
        String[] _arrayKeys = SQLHelper.getKeyColumns(getTableName());
        if (!ArrayUtil.isEmpty(_arrayKeys)) {
            _sbOrderBy.append(" ORDER BY ");
            for (int i = 0; i < _arrayKeys.length; i++) {
                _sbOrderBy.append(_arrayKeys[i]);
                if (i < _arrayKeys.length - 1) {
                    _sbOrderBy.append(", ");
                }
            }
        }

        return _sbOrderBy.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.BaseDao#find(java.lang.String, java.lang.String)
     */
    protected List find(String condition, String orderBy) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("find(String, String) - start");
        }

        try {
            StringBuffer _sbSQL = new StringBuffer();
            List _listResults;
            _sbSQL.append(SQLHelper.getSelectStatement(this.m_tableName,
                    this.m_tableSchema));
            if (StringUtil.isNotBlank(condition)) {
                _sbSQL.append(" WHERE ").append(condition);
            }
            if (StringUtil.isNotBlank(orderBy)) {
                _sbSQL.append(" ORDER BY ").append(orderBy);
            } else {
                _sbSQL.append(getDefaultOrderBy());
            }

            if (logger.isDebugEnabled()) {
                logger
                        .debug("find(String, String) - SQL: "
                                + _sbSQL.toString());
            }
            _listResults = getJdbcTemplate().queryForList(_sbSQL.toString());
            if (_listResults.isEmpty()) {
                if (logger.isDebugEnabled()) {
                    logger.debug("find() - result is empty.");
                }
            }

            if (logger.isDebugEnabled()) {
                logger.debug("find(String, String) - end");
            }
            return _listResults;
        } catch (Exception e) {
            logger.error("find(String, String) - Exception: " + e.getMessage(),
                    e);
            throw new DaoException(e.getMessage());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.BaseDao#count(java.lang.String)
     */
    public int count(String condition) throws DaoException {
        if (logger.isDebugEnabled()) {
            logger.debug("count(String) - start");
        }

        int _nRtn = 0;
        try {
            StringBuffer _sbSQL = new StringBuffer();
            _sbSQL.append("SELECT count(1) FROM ");
            if (StringUtil.isNotBlank(getTableSchema())) {
                _sbSQL.append(getTableSchema()).append(".");
            }
            _sbSQL.append(getTableName());
            if (StringUtil.isNotBlank(condition)) {
                _sbSQL.append(" WHERE ").append(condition);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("count(String) - SQL: " + _sbSQL.toString());
            }
            _nRtn = getJdbcTemplate().queryForInt(_sbSQL.toString());
        } catch (Exception e) {
            logger.error("count(String)", e);

            throw new DaoException(e.getMessage());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("count(String) - end");
        }
        return _nRtn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.BaseDao#listEntity(java.lang.String)
     */
    public Object listEntity(String condition) throws DaoException {
        Object[] _arrayObj = listEntities(condition);
        if (_arrayObj.length == 0) {
            return null;
        }
        Object _obj = _arrayObj[0];
        return _obj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see application.dao.BaseDao#listEntities(java.lang.String)
     */
    public Object[] listEntities(String condition) throws DaoException {
        Object[] _arrayObj = listEntities(condition, null);
        return _arrayObj;
    }
    
    protected String getFullTableName() {
        String _strRtn = getTableName();
        if (StringUtil.isNotBlank(getTableSchema())) {
            _strRtn = getTableSchema() + "." + _strRtn;
        }
        return _strRtn;
    }
}
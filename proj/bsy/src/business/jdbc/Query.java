/*
 * @(#)Query.java  2005-3-17
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package business.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import business.table.BaseTableModel;

import util.StringUtil;



/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class Query {

    private List m_resultList;
    
    private String[] m_columnNames;
    
    private String[] m_columnLabels;
    
    private String[] m_columnTypeNames;
    
    private boolean m_isShowSql; 
    
    public void execute(String strSql) {
        try {
            if (isShowSql()) {
                System.out.println("execute sql: " + strSql);
            }
            if (StringUtil.isBlankString(strSql)) {
                System.out.println("Sql statement is empty!");
                return;
            }
            Connection _connection = DBConnectionManager.getConnection();
            Statement _statement = _connection.createStatement();
            _statement.executeUpdate(strSql);
            _statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List executeQuery(String strSql) {
        try {
            if (isShowSql()) {
                System.out.println("query sql: " + strSql);
            }
            if (StringUtil.isBlankString(strSql)) {
                System.out.println("Query sql statement is empty!");
                return getResultList();
            }
            Connection _connection = DBConnectionManager.getConnection();
            Statement _statement = _connection.createStatement();
            ResultSet _rs = _statement.executeQuery(strSql);
            m_resultList = ResultSetUtil.getResultSetToList(_rs);
            m_columnLabels = ResultSetUtil.getResultSetColumnLabels(_rs);
            m_columnNames = ResultSetUtil.getResultSetColumnNames(_rs);
            m_columnTypeNames = ResultSetUtil.getResultSetColumnTypeNames(_rs);
            _rs.close();
            _statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m_resultList;
    }
    
    public JTable queryForTable(String strSql) {
        executeQuery(strSql);
        BaseTableModel _tableModel = new BaseTableModel();
        _tableModel.setColumnNames(m_columnNames);
        _tableModel.setColumnTypeNames(m_columnTypeNames);
        _tableModel.setData(m_resultList);
        JTable _table = new JTable(_tableModel);
        return _table;
    }
    
    public String[] getColumnLabels() {
        if (m_columnLabels == null) {
            m_columnLabels = new String[0];
        }
        return m_columnLabels;
    }
    public void setColumnLabels(String[] columnLabels) {
        m_columnLabels = columnLabels;
    }
    public List getResultList() {
        if (m_resultList == null) {
            m_resultList = new ArrayList();
        }
        return m_resultList;
    }
    public void setResultList(List resultList) {
        m_resultList = resultList;
    }
    public String[] getColumnNames() {
        if(m_columnNames == null) {
            m_columnNames = new String[0];
        }
        return m_columnNames;
    }
    public void setColumnNames(String[] columnNames) {
        m_columnNames = columnNames;
    }
    public boolean isShowSql() {
        return m_isShowSql;
    }
    public void setShowSql(boolean isShowSql) {
        m_isShowSql = isShowSql;
    }

    public String[] getColumnTypeNames() {
        if (m_columnTypeNames == null) {
            m_columnTypeNames = new String[m_columnNames.length];
        }
        return m_columnTypeNames;
    }
    public void setColumnTypeNames(String[] columnTypeNames) {
        m_columnTypeNames = columnTypeNames;
    }
}

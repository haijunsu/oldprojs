/*
 * @(#)BaseTable.java  2005-3-17
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package business.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

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
public class BaseTableModel extends AbstractTableModel {
    private String[] m_columnNames = null;
    
    private String[] m_columnTypeNames = null;

    private List m_data = null;

    public int getColumnCount() {
        return getColumnNames().length;
    }

    public int getRowCount() {
        return getData().size();
    }

    public String getColumnName(int col) {
        return getColumnNames()[col];
    }
    
    public Object getValueAt(int row, int col) {
        Object[] _objs = (Object[])getData().get(row);
        return _objs[col];
    }

    public String[] getColumnNames() {
        if (m_columnNames == null) {
            m_columnNames = new String[0];
        }
        return m_columnNames;
    }
    public void setColumnNames(String[] columnNames) {
        m_columnNames = columnNames;
    }
    public List getData() {
        if (m_data == null) {
            m_data = new ArrayList();
        }
        return m_data;
    }
    public void setData(List data) {
        m_data = data;
    }
    
    public Class getColumnClass(int c) {
        if (getValueAt(0, c) == null) {
            if (getColumnTypeNames()[c].toLowerCase().equals("bit")) {
                    return (new Boolean(false)).getClass();
            }
            return String.class.getClass();
        }
        Class _class = getValueAt(0, c).getClass();
        return _class;
    }

    public String[] getColumnTypeNames() {
        if (m_columnTypeNames == null) {
            m_columnTypeNames = new String[getColumnNames().length];
        }
        return m_columnTypeNames;
    }
    public void setColumnTypeNames(String[] columnNameTypes) {
        m_columnTypeNames = columnNameTypes;
    }
}
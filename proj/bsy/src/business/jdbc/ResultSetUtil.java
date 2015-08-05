/*
 * @(#)ResultSetUtil.java  2005-3-17
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package business.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class ResultSetUtil {

    public static String[] getResultSetColumnNames(ResultSet rs) {
        if (rs == null) {
            return new String[0];
        }
        try {
            int _nCount = rs.getMetaData().getColumnCount();
            String[] _strNames = new String[_nCount];
            for (int i = 0; i < _strNames.length; i++) {
                _strNames[i] = rs.getMetaData().getColumnName(i + 1);
            }
            return _strNames;
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0];
        } 
    }
    
    public static String[] getResultSetColumnLabels(ResultSet rs) {
        if (rs == null) {
            return new String[0];
        }
        try {
            int _nCount = rs.getMetaData().getColumnCount();
            String[] _strNames = new String[_nCount];
            for (int i = 0; i < _strNames.length; i++) {
                _strNames[i] = rs.getMetaData().getColumnLabel(i + 1);
            }
            return _strNames;
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0];
        } 
    }

    public static String[] getResultSetColumnTypeNames(ResultSet rs) {
        if (rs == null) {
            return new String[0];
        }
        try {
            int _nCount = rs.getMetaData().getColumnCount();
            String[] _strNames = new String[_nCount];
            for (int i = 0; i < _strNames.length; i++) {
                _strNames[i] = rs.getMetaData().getColumnTypeName(i + 1);
            }
            return _strNames;
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0];
        } 
    }

    public static List getResultSetToList(ResultSet rs) {
        List _list = new ArrayList();
        if (rs == null) {
            return _list;
        }
        try {
            int _nCount = rs.getMetaData().getColumnCount();
            Object[] _objects = null;
            while (rs.next()) {
                _objects = new Object[_nCount];
                for (int i = 0; i < _objects.length; i++) {
                    _objects[i] = rs.getObject(i + 1);
                }
                _list.add(_objects);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return _list;
    }

}

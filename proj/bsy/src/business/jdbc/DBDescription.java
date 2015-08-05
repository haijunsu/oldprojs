/*
 * @(#)DBDescription.java  2005-3-17
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package business.jdbc;


import java.util.ArrayList;
import java.util.Iterator;
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
public class DBDescription {
    
    public static JTable getDBInformationTable() {
        BaseTableModel _table = new BaseTableModel();
        String[] _strTitles = new String[]{"Property name","Property value"};
        _table.setColumnNames(_strTitles);
        _table.setData(getDBProperties());
        return new JTable(_table);
    }
    public static String getDBInformation() {
        StringBuffer _stringBuffer = new StringBuffer();
        try {
            Iterator _iterator = getDBProperties().iterator();
            while(_iterator.hasNext()) {
                String[] _strs = (String[])_iterator.next();
                for (int i = 0; i < _strs.length; i++) {
                    _stringBuffer.append(_strs[i]).append(": ");
                }
                _stringBuffer.append(StringUtil.getLineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _stringBuffer.toString();
    }
    
    public static List getDBProperties() {
        List _list = new ArrayList();
        try {
            String[] _properties = new String[2];
            _properties[0] = "Database product name";
            _properties[1] = DBConnectionManager.getConnection().getMetaData().getDatabaseProductName();
            _list.add(_properties);
            _properties = new String[2];
            _properties[0] = "Database product version";
            _properties[1] = DBConnectionManager.getConnection().getMetaData().getDatabaseProductVersion();
            _list.add(_properties);
            _properties = new String[2];
            _properties[0] = "Driver name";
            _properties[1] = DBConnectionManager.getConnection().getMetaData().getDriverName();
            _list.add(_properties);
            _properties = new String[2];
            _properties[0] = "Drive version";
            _properties[1] = DBConnectionManager.getConnection().getMetaData().getDriverVersion();
            _list.add(_properties);
            _properties = new String[2];
            _properties[0] = "Connecting URL";
            _properties[1] = DBConnectionManager.getConnection().getMetaData().getURL();
            _list.add(_properties);
            _properties = new String[2];
            _properties[0] = "Current catolog";
            _properties[1] = DBConnectionManager.getConnection().getCatalog();
            _list.add(_properties);
            _properties = new String[2];
            _properties[0] = "Current user name";
            _properties[1] = DBConnectionManager.getConnection().getMetaData().getUserName();
            _list.add(_properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return _list;
    }
}

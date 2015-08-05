/*
 * @(#)DatabaseTableTreeNode.java  2005-3-17
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package business.tree;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.DefaultMutableTreeNode;

import business.jdbc.DBConnectionManager;
import business.jdbc.ResultSetUtil;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class DatabaseTableTreeNode {

    private static DefaultMutableTreeNode m_node = null;
    
    public static DefaultMutableTreeNode getNode() {
        if (m_node == null) {
            initNode();
        }
        return m_node;
    }

    /**
     * 
     */
    private static void initNode() {
        try {
            Connection _connection = DBConnectionManager.getConnection();
            DatabaseMetaData _metaData = _connection.getMetaData();
            String _strCatalog = _connection.getCatalog();
            m_node = new DefaultMutableTreeNode(_strCatalog);
            DefaultMutableTreeNode _category = new DefaultMutableTreeNode("TABLE");
            m_node.add(_category);
            ResultSet _rs = _metaData.getTables(_strCatalog, null, null, new String[] {"TABLE"});
            String[] _strColumns = ResultSetUtil.getResultSetColumnNames(_rs);
            while(_rs.next()) {
                String _strTableSchema = _rs.getString("TABLE_SCHEM");
                String _strTableName = _rs.getString("TABLE_NAME");
                DefaultMutableTreeNode _tableName = new DefaultMutableTreeNode(_strTableSchema.trim() + "." + _strTableName.trim());
                _category.add(_tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

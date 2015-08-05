/*
 * @(#)DBConnectionManager.java  2005-3-17
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package business.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.StringUtil;


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
public class DBConnectionManager {
    
    private static String m_jdbcClassName = "sun.jdbc.odbc.JdbcOdbcDriver";
    private static String m_jdbcURL = "jdbc:odbc:Smirt";
    private static String m_jdbcUserName = "sa";
    private static String m_jdbcPasswrod = "12345";
    private static String m_oldJdbcClassName = "";
    private static Connection m_connection = null;
    
    private static boolean m_loadClassFlag;

    public static Connection getConnection() {
        if (m_connection != null) {
            return m_connection;
        }
        if (StringUtil.isBlankString(m_jdbcClassName)) {
            System.out.println("Jdbc Class name is empty!");
            return null;
        }
        if (!m_loadClassFlag) {
            try {
                Class.forName(m_jdbcClassName).newInstance();
                m_loadClassFlag = true;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        if (StringUtil.isBlankString(m_jdbcURL)) {
            System.out.println("Jdbc URL is empty!");
            return null;
        }
        
        if (StringUtil.isBlankString(m_jdbcUserName)) {
            System.out.println("Jdbc Username is empty!");
            return null;
        }
        
        try {
            m_connection = DriverManager.getConnection(m_jdbcURL, m_jdbcUserName, m_jdbcPasswrod);
        } catch (SQLException e) {
            m_connection = null;
            e.printStackTrace();
        }
        return m_connection;
    }
    
    
    public static String getJdbcClassName() {
        return m_jdbcClassName;
    }
    public static void setJdbcClassName(String jdbcClassName) {
        if (!m_oldJdbcClassName.equals(jdbcClassName)) {
            m_loadClassFlag = false;
        }
        m_jdbcClassName = jdbcClassName;
    }
    public static String getJdbcPasswrod() {
        return m_jdbcPasswrod;
    }
    public static void setJdbcPasswrod(String jdbcPasswrod) {
        m_jdbcPasswrod = jdbcPasswrod;
    }
    public static String getJdbcURL() {
        return m_jdbcURL;
    }
    public static void setJdbcURL(String jdbcURL) {
        m_jdbcURL = jdbcURL;
    }
    public static String getJdbcUserName() {
        return m_jdbcUserName;
    }
    public static void setJdbcUserName(String jdbcUserName) {
        m_jdbcUserName = jdbcUserName;
    }
    public static String getOldJdbcClassName() {
        return m_oldJdbcClassName;
    }
    public static void setOldJdbcClassName(String oldJdbcClassName) {
        m_oldJdbcClassName = oldJdbcClassName;
    }
}
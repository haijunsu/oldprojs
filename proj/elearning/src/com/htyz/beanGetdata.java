///////////////////////////////////////////////////
//用于处理CatchedRowSet中的数据；
//
//适用对象：针对没有Rusult Cache功能的数据库
//
//   苏海军
//
//     2002.1
///////////////////////////////////////////////////
/**
 * @(#)beanGetData.java
 * 
 * 功能描述：处理SQL查询、执行、事务等功能，对于Result使用了 CatchedRowSet技术来进行缓存数据
 * 
 * 
 * @version ver 1.1
 * @author name: 苏海军
 * @copyright 2002, 版权所有 修改日期： 2002年 8月 3日
 */
package com.htyz;

import sun.jdbc.rowset.CachedRowSet;
import java.sql.*;

import com.htyz.system.SystemProperties;
import com.htyz.util.BeanDateFormat;

public class beanGetdata {
    protected CachedRowSet crs = null;

    protected java.sql.ResultSetMetaData metaData = null;

    protected int RowCount = 0;

    private int realRow = 0;

    private int numRows = 0;

    private Object ob = null;

    private boolean tracing = SystemProperties.getBooleanProperty("tracing"); //是否显示调试信息

    public beanGetdata() {
        super();//加入实例化时的操作
    }

    /***************************************************************************
     * 功能描述：执行SQL语句，主要是UPDATE，DELETE，INSERT等操作
     * 
     * @param String sql 要执行的SQL语句
     * 
     * @retruns int 影响的行数
     * 
     * @exception Exception
     * 
     * @author name: 苏海军 创建日期：2002年 8月 5日
     */
    public int execute(String sql) {
        beanSQL beanSql = new beanSQL();
        try {
            return beanSql.executeSQL(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int[] execute(String[] sql) {
        beanSQL beanSql = new beanSQL();
        try {
            return beanSql.executeSQL(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[0];
    }

    /***************************************************************************
     * 功能描述：执行SQL语句，主要是SELECT操作
     * 
     * @param String sql 要执行的SQL语句
     * 
     * @retruns CachedRowSet 结果集
     * 
     * @exception Exception
     * 
     * @author name: 苏海军 创建日期：2002年 8月 5日
     */
    public CachedRowSet executeSelect(String s_sql) {
        beanSQL bean_Sql = new beanSQL();
        try {
            crs = bean_Sql.executeQuery(s_sql);
            this.metaData = crs.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crs;
    }

    /***************************************************************************
     * 功能描述：返回当前结果集总行数
     * 
     * @param
     * 
     * @retruns int
     * 
     * @exception Exception
     * 
     * @author name: 苏海军 创建日期：2002年 8月 5日
     */
    public int getRowCount() {
        if (crs == null)
            return 0;
        return crs.size();
    }

    /***************************************************************************
     * 功能描述：返回当前结果集列数
     * 
     * @param
     * 
     * @retruns int
     * 
     * @exception SQLException
     * 
     * @author name: 苏海军 创建日期：2002年 8月 5日
     */
    public int getColumnCount() {
        if (metaData == null)
            return 0;
        try {
            return metaData.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /***************************************************************************
     * 功能描述：返回当前结果集指定列名称
     * 
     * @param int
     * 
     * @retruns String
     * 
     * @exception SQLException
     * 
     * @author name: 苏海军 创建日期：2002年 8月 5日
     */
    public String getColumnName(int i) {
        try {
            return metaData.getColumnName(i);
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    /***************************************************************************
     * 功能描述：返回当前结果集指定列类型名称
     * 
     * @param int
     * 
     * @retruns String
     * 
     * @exception SQLException
     * 
     * @author name: 苏海军 创建日期：2002年 8月 5日
     */
    public String getColumnTypeName(int i) {
        try {
            return metaData.getColumnTypeName(i);
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    /***************************************************************************
     * 功能描述：返回当前结果集指定列类型名称
     * 
     * @param int
     * 
     * @retruns int
     * 
     * @exception SQLException
     * 
     * @author name: 苏海军 创建日期：2002年 8月 5日
     */
    public int getColumnType(int i) {
        try {
            return metaData.getColumnType(i);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /***************************************************************************
     * 功能描述：返回指定行、列的值
     * 
     * @param int col [(String) col], int row
     * 
     * @retruns String
     * 
     * @exception ArrayIndexOutOfBoundsException
     * 
     * @author name: 苏海军 创建日期：2002年 8月 5日
     */
    public String getFieldValue(int col, int row) {
        return valueAtColumnRow(col, row);
    }

    public String getFieldValue(String col, int row) {
        return valueAtColumnRow(col, row);
    }

    private String valueAtColumnRow(int col, int row) {
        realRow = row + 1;
        numRows = getRowCount();

        if (crs == null || numRows == 0) {
            if (tracing)
                System.err.println("结果集为空！");
            return "";
        }

        if (realRow > numRows) {
            if (tracing)
                System.err.println("共有" + numRows + "条记录，请求的第" + realRow
                        + "条记录超出结果集范围！");
            return "";
        }

        try {
            crs.absolute(realRow);

            ob = new Object();
            ob = crs.getObject(col);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (ob == null) {
            return "";

        }

        return SystemProperties.getBooleanProperty("processDBChinese") ? SystemProperties
                .getGBKString(ob.toString())
                : ob.toString();
    }

    private String valueAtColumnRow(String col, int row) {
        realRow = row + 1;
        numRows = getRowCount();

        if (crs == null || numRows == 0) {
            if (tracing)
                System.err.println("结果集为空！");
            return "";
        }

        if (realRow > numRows) {
            if (tracing)
                System.err.println("共有" + numRows + "条记录，请求的第" + realRow
                        + "条记录超出结果集范围！");
            return "";
        }

        try {
            crs.absolute(realRow);

            ob = new Object();
            ob = crs.getObject(col);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (ob == null) {
            return "";

        }

        return SystemProperties.getBooleanProperty("processDBChinese") ? SystemProperties
                .getGBKString(ob.toString())
                : ob.toString();
    }

    /***************************************************************************
     * 功能描述：返回数据库日期,目前是返回当前服务器的日期
     * 
     * @param String Year, Month, Day
     * 
     * @retruns String 15位毫秒
     * 
     * @exception
     * 
     * @author name: 苏海军 创建日期：2002年 8月 5日
     */
    public String getDbDate() {
        long ll = System.currentTimeMillis();
        String strRtn = "000000000000000" + Long.toString(ll);
        return strRtn.substring(strRtn.length() - 15, strRtn.length());
    }

    public String getDbDate(String year, String month, String day) {
        com.htyz.util.BeanDateFormat bdf = new com.htyz.util.BeanDateFormat();
        return bdf.parseDBDate(year, month, day);
    }

    public void destory() {
        this.crs = null;
        this.RowCount = 0;
        this.realRow = 0;
        this.numRows = 0;
        this.ob = null;
    }
}


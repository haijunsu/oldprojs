/**
 * @(#)DataBean.java  2003-1-14
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.sql;

import java.io.Serializable;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import sun.jdbc.rowset.CachedRowSet;

import com.idn.property.PropertyManager;
import com.idn.util.CommonTools;

/**
 * <P>主要是对静态SQL查询语句得到CachedRowSet结果集进行操作</P>
 * 
 * @version 0.2
 * @author 苏海军
 */
public class DataBean implements Serializable {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(DataBean.class);

	/**
	 * 用来缓存ResultSet结果集
	 */
	private CachedRowSet crs = null;

	/**
	 * ResultSet结果集中的元数据
	 */
	private ResultSetMetaData metaData = null;

	public DataBean() {
		super();
		this.crs = null;
		this.metaData = null;
	}

	/**
	 * 执行静态SQL查询语句，主要是SELECT操作
	 * 
	 * @param String sql 要执行的SQL语句
	 * @return CachedRowSet 结果集
	 * @exception Exception
	 */
	public CachedRowSet executeSelect(String s_sql) throws SQLException {
			SQLBean bean_Sql = new SQLBean();
			crs = bean_Sql.executeQuery(s_sql);
			metaData = crs.getMetaData();
			return crs;

	}

	/**
	 * 返回当前结果集总行数，如果结果集为空，返回0
	 * 
	 * @return int 总行数
	 */
	public int getRowCount() {
		if (crs == null)
			return 0;
		return crs.size();
	}

	/**
	 * 返回当前结果集列数，如果结果集为空，返回0
	 * 
	 * @return int 总列数，出错时返回-1
	 */
	public int getColumnCount() {
		if (crs == null)
			return 0;
		try {
			if (metaData == null)
				metaData = crs.getMetaData();
			return metaData.getColumnCount();
		} catch (java.sql.SQLException e) {
			log.error("getColumnCount() - SQLException Error Message: " + e.getMessage(), e);
			return 0;
		}
	}

	/**
	 * 返回当前结果集指定列名称
	 * @param int 要查询的列的编号，编号从1开始
	 * @return String 返回查询的结果，出错时返回""
	 */
	public String getColumnName(int i) {
		if (crs == null)
			return "";
		try {
			if (metaData == null)
				metaData = crs.getMetaData();
			return metaData.getColumnName(i + 1);
		} catch (java.sql.SQLException e) {
			log.error("getColumnName() - SQLException Error Message: " + e.getMessage(), e);
			return "";
		}
	}

	/**
	 * 返回当前结果集指定列类型描述
	 * @param int 要查询的列的编号，编号从1开始
	 * @return String 返回列类型描述，出错时返回""
	 */
	public String getColumnTypeName(int i) {
		try {
			return metaData.getColumnTypeName(i + 1);
		} catch (java.sql.SQLException e) {
			log.error("getColumnTypeName(int) - SQLException Error Message: " + e.getMessage(), e);
			return "";
		}
	}

	/**
	 * 返回当前结果集指定列类型名称
	 * @param int 要查询的列的编号，编号从1开始
	 * @return int 返回列的类型，出错时返回-1
	 */
	public int getColumnType(int i) {
		try {
			return metaData.getColumnType(i + 1);
		} catch (java.sql.SQLException e) {
			log.error("getColumnType(int) - SQLException Error Message: " + e.getMessage(), e);
			return -1;
		}
	}

	/**
	 * 返回当前结果集指定列长度
	 * @param int 要查询的列的编号，编号从1开始
	 * @return int 返回列的类型，出错时返回-1
	 */
	public int getColumnLength(int i) {
		try {
			return metaData.getColumnDisplaySize(i + 1);
		} catch (java.sql.SQLException e) {
			log.error("getColumnLength(int) - SQLException Error Message: " + e.getMessage(),e);
			return -1;
		}
	}

	/**
	 * 返回指定行、列的值
	 * @param int col 指定的列的编号
	 * @param int row 指定的行
	 * @return String 结果集中的结果，出错时返回""
	 * @deprecated 请参阅getElementValue;
	 */
	public String getFieldValue(int col, int row)
		throws ArrayIndexOutOfBoundsException {
		try {
			return valueAtColumnRow(row, col);
		} catch (java.sql.SQLException e) {
			log.error(
				"getFieldValue(int, int) - SQLException error: col/row = "
					+ String.valueOf(col)
					+ "/"
					+ String.valueOf(row) + ". Return value is \"\".",
				e);
			return "";
		} catch (ArrayIndexOutOfBoundsException ex) {
			log.warn(
				"getFieldValue(int, int) - ArrayIndexOutOfBoundsException error: col/row = "
					+ String.valueOf(col)
					+ "/"
					+ String.valueOf(row) + ". Return value is \"\".");
			return "";
		}
	}

	/**
	 * 返回指定行、列的值
	 * @param int col 指定的列的名称
	 * @param int row 指定的行
	 * @return String 结果集中的结果，出错时返回""
	 * @deprecated 请参阅getElementValue;
	 */
	public String getFieldValue(String col, int row) {
		try {
			return valueAtColumnRow(row, col);
		} catch (java.sql.SQLException e) {
			log.error(
				"getFieldValue(String, int) - SQLException error: col/row = "
					+ col
					+ "/"
					+ String.valueOf(row) + ". Return value is \"\".",
				e);
			return "";
		} catch (ArrayIndexOutOfBoundsException ex) {
			log.warn(
				"getFieldValue(String, int) - ArrayIndexOutOfBoundsException error: col/row = "
					+ col
					+ "/"
					+ String.valueOf(row) + ". Return value is \"\".");
			return "";
		}
	}


	/**
	 * 返回指定行、列的值
	 * @param int col 指定的列的编号
	 * @param int row 指定的行
	 * @return String 结果集中的结果，出错时返回""
	 */
	public String getElementValue(int row, int col)
		throws ArrayIndexOutOfBoundsException {
		try {
			return valueAtColumnRow(row, col);
		} catch (java.sql.SQLException e) {
			log.error(
				"getElementValue(int, int) - SQLException error: col/row = "
					+ String.valueOf(col)
					+ "/"
					+ String.valueOf(row) + ". Return value is \"\".",
				e);
			return "";
		} catch (ArrayIndexOutOfBoundsException ex) {
			log.warn(
				"getElementValue(int, int) - ArrayIndexOutOfBoundsException error: col/row = "
					+ String.valueOf(col)
					+ "/"
					+ String.valueOf(row) + ". Return value is \"\".");
			return "";
		}
	}

	/**
	 * 返回指定行、列的值
	 * @param int col 指定的列的名称
	 * @param int row 指定的行
	 * @return String 结果集中的结果，出错时返回""
	 */
	public String getElementValue(int row, String col) {
		try {
			return valueAtColumnRow(row, col);
		} catch (java.sql.SQLException e) {
			log.error(
				"getElementValue(int, String) - SQLException error: col/row = "
					+ col
					+ "/"
					+ String.valueOf(row) + ". Return value is \"\".",
				e);
			return "";
		} catch (ArrayIndexOutOfBoundsException ex) {
			log.warn(
				"getElementValue(int, String) - ArrayIndexOutOfBoundsException error: col/row = "
					+ col
					+ "/"
					+ String.valueOf(row) + ". Return value is \"\".");
			return "";
		}
	}

	/**
	 * 返回指定行、列的值
	 * @param int col 指定的列
	 * @param int row 指定的行
	 * @return String 结果集中的结果，出错时返回""
	 */
	private String valueAtColumnRow(int row, int col)
		throws ArrayIndexOutOfBoundsException, SQLException {
		int realRow = 0;
		int numRows = 0;
		int realCol = 0;
		int numCols = 0;
		Object ob = null;

		realCol = col + 1;
		numCols = getColumnCount();
		realRow = row + 1;
		numRows = getRowCount();

		if (crs == null || numRows == 0) {
			log.debug("valueAtColumnRow(int, int) - Result is null. Return value is \"\"");
			return "";
		}

		if (realRow > numRows) {
			log.debug("RowCount is " + numRows + ". Require is " + realRow + " row. Return value is \"\"");
			return "";
		}

		if (realCol > numCols) {
			log.debug("ColumnCount is " + numCols + ". Require is " + realCol + " column. Return value is \"\"");
			return "";
		}
		crs.absolute(realRow);

		ob = new Object();
		ob = crs.getObject(realCol);
		if (ob == null) {
			return "";

		}

		return PropertyManager.getBooleanProperty("processDBChinese")
			? CommonTools.getGBKString(ob.toString())
			: ob.toString();
	}

	/**
	 * 返回指定行、列的值
	 * @param int col 指定的列的名称
	 * @param int row 指定的行
	 * @return String 结果集中的结果，出错时返回""
	 */
	private String valueAtColumnRow(int row, String col)
		throws ArrayIndexOutOfBoundsException, SQLException {
		int realRow = 0;
		int numRows = 0;
		Object ob = null;

		realRow = row + 1;
		numRows = getRowCount();

		if (crs == null || numRows == 0) {
			log.debug("valueAtColumnRow(int, int) - Result is null. Return value is \"\"");
			return "";
		}

		if (realRow > numRows) {
			log.debug("RowCount is " + numRows + ". Require is " + realRow + " row. Return value is \"\"");
			return "";
		}

		crs.absolute(realRow);

		ob = new Object();
		ob = crs.getObject(col);
		if (ob == null) {
			return "";

		}

		return PropertyManager.getBooleanProperty("processDBChinese")
			? CommonTools.getGBKString(ob.toString())
			: ob.toString();
	}

	/**
	 * 释放资源
	 */
	public void release() {
		destory();
	}

	/**
	 * 回收资源
	 */
	public void destory() {
		this.crs = null;
		this.metaData = null;
	}
	/**
	 * Returns the crs.
	 * @return CachedRowSet
	 */
	public CachedRowSet getCrs() {
		return crs;
	}

	/**
	 * Returns the metaData.
	 * @return ResultSetMetaData
	 */
	public ResultSetMetaData getMetaData() {
		return metaData;
	}

	
	/**
	 * Sets the crs.
	 * @param crs The crs to set
	 */
	public void setCrs(CachedRowSet crs) {
		this.crs = crs;
		try {
			this.metaData = crs.getMetaData();
		} catch (SQLException e) {
			log.debug("setCrs(CachedRowSet) - SQLException error: set CachedRowSet fail.");
		}
	}

	/**
	 * Sets the metaData.
	 * @param metaData The metaData to set
	 */
	public void setMetaData(ResultSetMetaData metaData) {
		this.metaData = metaData;
	}

}

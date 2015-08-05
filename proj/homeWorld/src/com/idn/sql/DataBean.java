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
 * <P>��Ҫ�ǶԾ�̬SQL��ѯ���õ�CachedRowSet��������в���</P>
 * 
 * @version 0.2
 * @author �պ���
 */
public class DataBean implements Serializable {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(DataBean.class);

	/**
	 * ��������ResultSet�����
	 */
	private CachedRowSet crs = null;

	/**
	 * ResultSet������е�Ԫ����
	 */
	private ResultSetMetaData metaData = null;

	public DataBean() {
		super();
		this.crs = null;
		this.metaData = null;
	}

	/**
	 * ִ�о�̬SQL��ѯ��䣬��Ҫ��SELECT����
	 * 
	 * @param String sql Ҫִ�е�SQL���
	 * @return CachedRowSet �����
	 * @exception Exception
	 */
	public CachedRowSet executeSelect(String s_sql) throws SQLException {
			SQLBean bean_Sql = new SQLBean();
			crs = bean_Sql.executeQuery(s_sql);
			metaData = crs.getMetaData();
			return crs;

	}

	/**
	 * ���ص�ǰ���������������������Ϊ�գ�����0
	 * 
	 * @return int ������
	 */
	public int getRowCount() {
		if (crs == null)
			return 0;
		return crs.size();
	}

	/**
	 * ���ص�ǰ�������������������Ϊ�գ�����0
	 * 
	 * @return int ������������ʱ����-1
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
	 * ���ص�ǰ�����ָ��������
	 * @param int Ҫ��ѯ���еı�ţ���Ŵ�1��ʼ
	 * @return String ���ز�ѯ�Ľ��������ʱ����""
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
	 * ���ص�ǰ�����ָ������������
	 * @param int Ҫ��ѯ���еı�ţ���Ŵ�1��ʼ
	 * @return String ��������������������ʱ����""
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
	 * ���ص�ǰ�����ָ������������
	 * @param int Ҫ��ѯ���еı�ţ���Ŵ�1��ʼ
	 * @return int �����е����ͣ�����ʱ����-1
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
	 * ���ص�ǰ�����ָ���г���
	 * @param int Ҫ��ѯ���еı�ţ���Ŵ�1��ʼ
	 * @return int �����е����ͣ�����ʱ����-1
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
	 * ����ָ���С��е�ֵ
	 * @param int col ָ�����еı��
	 * @param int row ָ������
	 * @return String ������еĽ��������ʱ����""
	 * @deprecated �����getElementValue;
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
	 * ����ָ���С��е�ֵ
	 * @param int col ָ�����е�����
	 * @param int row ָ������
	 * @return String ������еĽ��������ʱ����""
	 * @deprecated �����getElementValue;
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
	 * ����ָ���С��е�ֵ
	 * @param int col ָ�����еı��
	 * @param int row ָ������
	 * @return String ������еĽ��������ʱ����""
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
	 * ����ָ���С��е�ֵ
	 * @param int col ָ�����е�����
	 * @param int row ָ������
	 * @return String ������еĽ��������ʱ����""
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
	 * ����ָ���С��е�ֵ
	 * @param int col ָ������
	 * @param int row ָ������
	 * @return String ������еĽ��������ʱ����""
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
	 * ����ָ���С��е�ֵ
	 * @param int col ָ�����е�����
	 * @param int row ָ������
	 * @return String ������еĽ��������ʱ����""
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
	 * �ͷ���Դ
	 */
	public void release() {
		destory();
	}

	/**
	 * ������Դ
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

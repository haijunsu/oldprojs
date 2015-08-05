///////////////////////////////////////////////////
//���ڴ���CatchedRowSet�е����ݣ�
//
//���ö������û��Rusult Cache���ܵ����ݿ�
//
//   �պ���
//
//     2002.1
///////////////////////////////////////////////////
/** 
 * @(#)beanGetData.java 
 *
 * ��������������SQL��ѯ��ִ�С�����ȹ��ܣ�����Resultʹ����
 *			CatchedRowSet���������л�������
 *			
 *
 * @version ver 1.1
 * @author name: �պ���
 * @copyright 2002, ��Ȩ����
 * �޸����ڣ� 2002�� 8�� 3��
 */
package com.htyz;

import sun.jdbc.rowset.CachedRowSet;
import java.sql.*;
import com.htyz.system.SystemProperties;
import com.htyz.navy.util.BeanDateFormat;

public class beanGetdata
{
	protected CachedRowSet crs = null;
	protected java.sql.ResultSetMetaData metaData = null;
	protected int RowCount = 0;
	private int realRow = 0;
	private int numRows = 0;
	private Object ob = null;
	private boolean tracing = SystemProperties.getBooleanProperty("tracing"); 	//�Ƿ���ʾ������Ϣ
	
	public beanGetdata()
	{
		super();//����ʵ����ʱ�Ĳ���
	}
	
	/** ************************************************************
     * ����������ִ��SQL��䣬��Ҫ��UPDATE��DELETE��INSERT�Ȳ���
     * 
     * @param String sql Ҫִ�е�SQL���
     * 
     * @retruns int Ӱ�������
     * 
     * @exception Exception
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 5��
     */
	public int execute(String sql)
	{
		try {
			beanSQL beanSql = new beanSQL();
			return beanSql.executeSQL(sql);
		} catch (java.sql.SQLException e) {
			System.err.println("ErrorCode: " + e.getErrorCode());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("Error Message: " + e.getMessage());
			e.printStackTrace();
			return -1;
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;	
		}
	}
	
	public int[] execute(String[] sql)
	{
		int[] nRtn = new int[sql.length];
		for (int i = 0; i<nRtn.length; i++) {
			nRtn[i] = -1;
	    }
	    try {
			beanSQL beanSql = new beanSQL();
			return beanSql.executeSQL(sql);
		} catch (java.sql.SQLException e) {
			System.err.println("ErrorCode: " + e.getErrorCode());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("Error Message: " + e.getMessage());
			e.printStackTrace();
			return nRtn;
		} catch (Exception ex) {
			ex.printStackTrace();
			return nRtn;	
		}
	}

	/** ************************************************************
     * ����������ִ��SQL��䣬��Ҫ��SELECT����
     * 
     * @param String sql Ҫִ�е�SQL���
     * 
     * @retruns CachedRowSet �����
     * 
     * @exception Exception
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 5��
     */
	public CachedRowSet executeSelect(String s_sql)
	{
		try {
			beanSQL bean_Sql = new beanSQL();
			crs = bean_Sql.executeQuery(s_sql);
			this.metaData = crs.getMetaData();
			return crs;
		} catch (java.sql.SQLException e) {
			System.err.println("ErrorCode: " + e.getErrorCode());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("Error Message: " + e.getMessage());
			e.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;	
		}
		
	}
	
	/** ************************************************************
     * �������������ص�ǰ�����������
     * 
     * @param 
     * 
     * @retruns int 
     * 
     * @exception Exception
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 5��
     */
	public int getRowCount()
	{
		if (crs == null)
			return 0;
		return crs.size();	
	}
	
	/** ************************************************************
     * �������������ص�ǰ���������
     * 
     * @param 
     * 
     * @retruns int 
     * 
     * @exception SQLException
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 5��
     */
	public int getColumnCount() throws SQLException {
		if (metaData == null)
			return 0;
		return metaData.getColumnCount();
	}
	
	/** ************************************************************
     * �������������ص�ǰ�����ָ��������
     * 
     * @param int
     * 
     * @retruns String 
     * 
     * @exception SQLException
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 5��
     */
	public String getColumnName(int i) throws SQLException {
		return metaData.getColumnName(i);
	}
	
	/** ************************************************************
     * �������������ص�ǰ�����ָ������������
     * 
     * @param int
     * 
     * @retruns String 
     * 
     * @exception SQLException
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 5��
     */
	public String getColumnTypeName(int i) throws SQLException {
		return metaData.getColumnTypeName(i);
	}
	
	/** ************************************************************
     * �������������ص�ǰ�����ָ������������
     * 
     * @param int
     * 
     * @retruns int 
     * 
     * @exception SQLException
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 5��
     */
	public int getColumnType(int i) throws SQLException {
		return metaData.getColumnType(i);
	}

	/** ************************************************************
     * ��������������ָ���С��е�ֵ
     * 
     * @param int col [(String) col], int row
     * 
     * @retruns String
     * 
     * @exception ArrayIndexOutOfBoundsException
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 5��
     */
	public String getFieldValue(int col, int row) 
		throws ArrayIndexOutOfBoundsException {
		try {
			return valueAtColumnRow(col, row);
		} catch (java.sql.SQLException e) {
			System.err.println("ErrorCode: " + e.getErrorCode());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("Error Message: " + e.getMessage());
			e.printStackTrace();
			return "";
		}
	}
	
	public String getFieldValue(String col, int row) 
		throws ArrayIndexOutOfBoundsException {
		try {
			return valueAtColumnRow(col, row);
		} catch (java.sql.SQLException e) {
			System.err.println("ErrorCode: " + e.getErrorCode());
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("Error Message: " + e.getMessage());
			e.printStackTrace();
			return "";
		}
	}
	
	private String valueAtColumnRow(int col, int row) throws ArrayIndexOutOfBoundsException, SQLException
	{
		realRow = row + 1;
		numRows = getRowCount();
		
		if (crs == null||numRows == 0)
		{
			if (tracing)
				System.err.println("�����Ϊ�գ�");
			return "";
		}
		
		if (realRow > numRows)
		{
			if (tracing)
				System.err.println("����" + numRows + "����¼������ĵ�" + realRow +"����¼�����������Χ��");
			return "";
		}
		
		crs.absolute(realRow);

		ob = new Object();
		ob = crs.getObject(col);
		if (ob == null)
		{
			return "";
			
		}

		return SystemProperties.getBooleanProperty("processDBChinese")? SystemProperties.getGBKString(ob.toString()) : ob.toString();
	}
		
	private String valueAtColumnRow(String col, int row) throws ArrayIndexOutOfBoundsException, SQLException
	{
		realRow = row + 1;
		numRows = getRowCount();
		
		if (crs == null||numRows == 0)
		{
			if (tracing)
				System.err.println("�����Ϊ�գ�");
			return "";
		}
		
		if (realRow > numRows)
		{
			if (tracing)
				System.err.println("����" + numRows + "����¼������ĵ�" + realRow +"����¼�����������Χ��");
			return "";
		}
		
		crs.absolute(realRow);

		ob = new Object();
		ob = crs.getObject(col);
		if (ob == null)
		{
			return "";
			
		}

		return SystemProperties.getBooleanProperty("processDBChinese")? SystemProperties.getGBKString(ob.toString()) : ob.toString();
	}
	
	
	/** ************************************************************
     * �����������������ݿ�����,Ŀǰ�Ƿ��ص�ǰ������������
     * 
     * @param String Year, Month, Day
     * 
     * @retruns String 15λ����
     * 
     * @exception 
     * 
     * @author name: �պ���
     * �������ڣ�2002�� 8�� 5��
     */
	public String getDbDate() {
		long ll = System.currentTimeMillis();
		String strRtn = "000000000000000" + Long.toString(ll);
		return strRtn.substring(strRtn.length() - 15, strRtn.length());
	}
	
	public String getDbDate(String year, String month, String day) {
		com.htyz.navy.util.BeanDateFormat bdf = new com.htyz.navy.util.BeanDateFormat();
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
		
			
	
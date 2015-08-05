/*
 * @(#)Column.java  2003-8-29
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.print;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * <P>��ӡ�����ж�Ӧ�е�����</P>
 * 
 * @version 0.1
 * @author navy
 */
public class Column {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(Column.class);

	/**
	 * �������ַ���������е�����
	 */
	private final String[] STRING_TYPES =
		new String[] {
			"CHAR",
			"VARCHAR",
			"DATE",
			"BOOLEAN",
			"BIT",
			"DISTINCT",
			"LONGVARCHAR",
			"TIME",
			"TIMESTAMP",
			"NULL",
			"OTHER" };

	/**
	 * ������������������е�����
	 */
	private final String[] INT_TYPES =
		new String[] { "BIGINT", "INTEGER", "SMALLINT", "TINYINT" };

	/**
	 * ������������������е�����
	 */
	private final String[] DECIMAL_TYPES =
		new String[] { "REAL", "FLOAT", "DOUBLE", "DECIMAL", "NUMERIC" };
		
	/**
	 * ���������
	 */
	private int m_index = 0;

	/**
	 * �ַ����ͼ���
	 */
	private HashSet m_stringSet = null;

	/**
	 * �������ͼ���
	 */
	private HashSet m_intSet = null;

	/**
	 * �������ͼ���
	 */
	private HashSet m_decimalSet = null;

	/**
	 * �е���ʾ���ƣ���ͷ��
	 */
	private String m_label = null;

	/**
	 * �е�����
	 */
	private String m_type = null;

	/**
	 * �еĳ���
	 */
	private int m_length = 0;

	/**
	 * �е�С����λ���������Ϊ�����ͣ�
	 */
	private int m_decimalDigits = 0;

	/**
	 * �е���ʾ��ţ��������е���ʾλ��
	 */
	private String m_seq = null;

	/**
	 * ���е���ʾ��־�����������Ƿ���ʾ
	 */
	private boolean isShow = false;
	
	/**
	 * �Ƿ���԰��ַ�����
	 */
	private boolean isString = false;
	
	/**
	 * �Ƿ���԰�Int�ʹ���
	 */
	private boolean isInt = false;
	
	/**
	 * �Ƿ���԰��ɱ��������ʹ���
	 */
	private boolean isDecimal = false;

	/**
	 * ʵ����
	 */
	public Column() {
		List listString = Arrays.asList(STRING_TYPES);
		List listInt = Arrays.asList(INT_TYPES);
		List listDecimal = Arrays.asList(DECIMAL_TYPES);
		m_stringSet = new HashSet(listString);
		m_intSet = new HashSet(listInt);
		m_decimalSet = new HashSet(listDecimal);
	}
	
	/**
	 * �����������͵���ʾ��ʽ�����ͷ��ء�#,##0�����ɱ������ͷ��ء�#,##0.�� + С��λ��������������֣��򷵻�nullֵ
	 * @return �������͵���ʾ��ʽ
	 */
	public String getNumberPattern () {
		
		if (isString())
			return null;
			
		if (isInt())
			return "#,##0";
			
		StringBuffer sb = new StringBuffer("#,##0.");
		for (int j = 0; j < m_decimalDigits; j++) {
			sb.append("0");
		}
		return sb.toString();
	}
	
	/**
	 * @return ��ʾ��־
	 */
	public boolean isShow() {
		return isShow;
	}

	/**
	 * @return С����λ��
	 */
	public int getDecimalDigits() {
		return m_decimalDigits;
	}

	/**
	 * @return ��ʾ����
	 */
	public String getLabel() {
		return m_label;
	}

	/**
	 * @return ����
	 */
	public int getLength() {
		return m_length;
	}

	/**
	 * @return ���
	 */
	public String getSeq() {
		return m_seq;
	}

	/**
	 * @return ����
	 */
	public String getType() {
		return m_type;
	}

	/**
	 * @param b - �����Ƿ���ʾ
	 */
	public void setShow(boolean b) {
		isShow = b;
	}

	/**
	 * @param i - ����С��λ��
	 */
	public void setDecimalDigits(int i) {
		m_decimalDigits = i;
	}

	/**
	 * @param string - ��������������ͷ��
	 */
	public void setLabel(String string) {
		m_label = string;
	}

	/**
	 * @param i - �����ֶγ���
	 */
	public void setLength(int i) {
		m_length = i;
	}

	/**
	 * @param string - ��ʾ��˳��
	 */
	public void setSeq(String string) {
		m_seq = string;
	}

	/**
	 * @param ����������
	 */
	public void setType(String string) {
		m_type = string;
	}

	/**
	 * @return �ɰ��ɱ����ִ���
	 */
	public boolean isDecimal() {
		return m_decimalSet.contains(m_type);
	}

	/**
	 * @return �ɰ����ʹ���
	 */
	public boolean isInt() {
		return m_intSet.contains(m_type);
	}

	/**
	 * @return �ɰ��ַ�����
	 */
	public boolean isString() {
		return m_stringSet.contains(m_type);
	}

	/**
	 * @return ����˳���
	 */
	public int getIndex() {
		return m_index;
	}

	/**
	 * @param i - ��������
	 */
	public void setIndex(int i) {
		m_index = i;
	}

}

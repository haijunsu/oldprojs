/*
 * @(#)Fields.java  2003-4-23
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

/**
 * <P> Ӱ��Fileds���ͱ�</P>
 * 
 * @version 0.1
 * @author navy
 */
public class Field {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(Field.class);

	/**
	 * �����ݿ��п��԰����ֵ����ͽ��д�����У�����
	 * SMALLINT, INTEGER, BIGINT, REAL, DOUBLE, DECIMAL

	 */
	private final String[] NUMBER_TYPE =
		new String[] {
			"SMALLINT",
			"INTEGER",
			"BIGINT",
			"REAL",
			"DOUBLE",
			"DECIMAL" };

	/**
	 * �ֶ�����ID�������ݿ��е�����
	 */
	private String columnId = null;

	/**
	 * �ֶ��������������ֶε�������
	 */
	private String name = null;

	/**
	 * �ֶ�����
	 */
	private String type = null;

	/**
	 * �ֶγ���
	 */
	private int length = 0;

	/**
	 * �ֶ�С��λ��
	 */
	private int decimalDigits = 0;

	/**
	 * �ֶ�С��λ��
	 */
	private String cclass = null;

	/**
	 * ���캯���������е����Ծ���Ϊnullֵ
	 */
	public Field() {
		columnId = null;
		name = null;
		type = null;
		length = 0;
		decimalDigits = 0;
		cclass = "no";
	}

	/**
	 * ���캯���������е����Ծ���Ϊָ����ֵ
	 * @param strColumnId �ֶ�����ID
	 * @param strName �ֶ���ʾ����
	 * @param strType �ֶ�����
	 * @param strLength �ֶγ���
	 * @param strDecimalDigits �ֶ�С�����ĳ���
	 * @param strCclass �ֶδ���
	 */
	public Field(
		String strColumnId,
		String strName,
		String strType,
		int nLength,
		int nDecimalDigits,
		String strCclass) {
		columnId = strColumnId;
		name = strName;
		type = strType;
		length = nLength;
		decimalDigits = nDecimalDigits;
		cclass = strCclass;
	}
	
	/**
	 * �����������͵���ʾ��ʽ
	 * @return �������͵���ʾ��ʽ������������֣��򷵻�nullֵ
	 */
	public String getNumberPattern() {
		try {
			for (int i = 0; i < NUMBER_TYPE.length; i++) {
				if (type.trim().equalsIgnoreCase(NUMBER_TYPE[i])) {
					int iDigits = decimalDigits;
					StringBuffer sb = new StringBuffer();
					sb.append("#,##0");
					if (iDigits > 0) {
						sb.append(".");
						for (int j = 0; j < iDigits; j++) {
							sb.append("0");
						}
					}
					return sb.toString();
				}
			}
			return null;
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * @return �������
	 */
	public String getCclass() {
		return cclass;
	}

	/**
	 * @return ����
	 */
	public String getColumnId() {
		return columnId;
	}

	/**
	 * @return С��λ��
	 */
	public int getDecimalDigits() {
		return decimalDigits;
	}

	/**
	 * @return �ֶγ���
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return �ֶ�����
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return �ֶ�����
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param string
	 */
	public void setCclass(String string) {
			cclass = string;
	}

	/**
	 * @param string
	 */
	public void setColumnId(String string) {
		columnId = string;
	}

	/**
	 * @param string
	 */
	public void setDecimalDigits(int n) {
		decimalDigits = n;
	}

	/**
	 * @param string
	 */
	public void setLength(int n) {
		length = n;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}

}

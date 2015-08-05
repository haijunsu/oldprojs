/*
 * @(#)Fields.java  2003-4-23
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

/**
 * <P> 影射Fileds类型表</P>
 * 
 * @version 0.1
 * @author navy
 */
public class Field {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(Field.class);

	/**
	 * 在数据库中可以按数字的类型进行处理的列，包括
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
	 * 字段列名ID，即数据库中的列名
	 */
	private String columnId = null;

	/**
	 * 字段名的描述，即字段的中文名
	 */
	private String name = null;

	/**
	 * 字段类型
	 */
	private String type = null;

	/**
	 * 字段长度
	 */
	private int length = 0;

	/**
	 * 字段小数位数
	 */
	private int decimalDigits = 0;

	/**
	 * 字段小数位数
	 */
	private String cclass = null;

	/**
	 * 构造函数，将所有的属性均置为null值
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
	 * 构造函数，将所有的属性均置为指定的值
	 * @param strColumnId 字段列名ID
	 * @param strName 字段显示名称
	 * @param strType 字段类型
	 * @param strLength 字段长度
	 * @param strDecimalDigits 字段小数点后的长度
	 * @param strCclass 字段代码
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
	 * 设置数字类型的显示格式
	 * @return 数字类型的显示格式，如果不是数字，则返回null值
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
	 * @return 代码分类
	 */
	public String getCclass() {
		return cclass;
	}

	/**
	 * @return 列名
	 */
	public String getColumnId() {
		return columnId;
	}

	/**
	 * @return 小数位数
	 */
	public int getDecimalDigits() {
		return decimalDigits;
	}

	/**
	 * @return 字段长度
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @return 字段名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return 字段类型
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

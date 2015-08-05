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
 * <P>打印数组中对应列的属性</P>
 * 
 * @version 0.1
 * @author navy
 */
public class Column {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(Column.class);

	/**
	 * 可以用字符来处理的列的类型
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
	 * 可以用整型来处理的列的类型
	 */
	private final String[] INT_TYPES =
		new String[] { "BIGINT", "INTEGER", "SMALLINT", "TINYINT" };

	/**
	 * 可以用数字来处理的列的类型
	 */
	private final String[] DECIMAL_TYPES =
		new String[] { "REAL", "FLOAT", "DOUBLE", "DECIMAL", "NUMERIC" };
		
	/**
	 * 数组列序号
	 */
	private int m_index = 0;

	/**
	 * 字符类型集合
	 */
	private HashSet m_stringSet = null;

	/**
	 * 整型类型集合
	 */
	private HashSet m_intSet = null;

	/**
	 * 数字类型集合
	 */
	private HashSet m_decimalSet = null;

	/**
	 * 列的显示名称（表头）
	 */
	private String m_label = null;

	/**
	 * 列的类型
	 */
	private String m_type = null;

	/**
	 * 列的长度
	 */
	private int m_length = 0;

	/**
	 * 列的小数点位数（如果列为数字型）
	 */
	private int m_decimalDigits = 0;

	/**
	 * 列的显示序号，决定该列的显示位置
	 */
	private String m_seq = null;

	/**
	 * 该列的显示标志，决定该列是否显示
	 */
	private boolean isShow = false;
	
	/**
	 * 是否可以按字符处理
	 */
	private boolean isString = false;
	
	/**
	 * 是否可以按Int型处理
	 */
	private boolean isInt = false;
	
	/**
	 * 是否可以按可变数字类型处理
	 */
	private boolean isDecimal = false;

	/**
	 * 实例化
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
	 * 设置数字类型的显示格式，整型返回“#,##0”，可变数字型返回“#,##0.” + 小数位数，如果不是数字，则返回null值
	 * @return 数字类型的显示格式
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
	 * @return 显示标志
	 */
	public boolean isShow() {
		return isShow;
	}

	/**
	 * @return 小数点位数
	 */
	public int getDecimalDigits() {
		return m_decimalDigits;
	}

	/**
	 * @return 显示名称
	 */
	public String getLabel() {
		return m_label;
	}

	/**
	 * @return 长度
	 */
	public int getLength() {
		return m_length;
	}

	/**
	 * @return 序号
	 */
	public String getSeq() {
		return m_seq;
	}

	/**
	 * @return 类型
	 */
	public String getType() {
		return m_type;
	}

	/**
	 * @param b - 设置是否显示
	 */
	public void setShow(boolean b) {
		isShow = b;
	}

	/**
	 * @param i - 设置小数位数
	 */
	public void setDecimalDigits(int i) {
		m_decimalDigits = i;
	}

	/**
	 * @param string - 设置列描述（表头）
	 */
	public void setLabel(String string) {
		m_label = string;
	}

	/**
	 * @param i - 设置字段长度
	 */
	public void setLength(int i) {
		m_length = i;
	}

	/**
	 * @param string - 显示的顺序
	 */
	public void setSeq(String string) {
		m_seq = string;
	}

	/**
	 * @param 设置列类型
	 */
	public void setType(String string) {
		m_type = string;
	}

	/**
	 * @return 可按可变数字处理
	 */
	public boolean isDecimal() {
		return m_decimalSet.contains(m_type);
	}

	/**
	 * @return 可按整型处理
	 */
	public boolean isInt() {
		return m_intSet.contains(m_type);
	}

	/**
	 * @return 可按字符处理
	 */
	public boolean isString() {
		return m_stringSet.contains(m_type);
	}

	/**
	 * @return 返回顺序号
	 */
	public int getIndex() {
		return m_index;
	}

	/**
	 * @param i - 数组的序号
	 */
	public void setIndex(int i) {
		m_index = i;
	}

}

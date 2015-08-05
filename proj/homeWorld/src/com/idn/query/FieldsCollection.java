/*
 * @(#)FieldsCollection.java  2003-4-27
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

import java.sql.SQLException;
import java.util.Hashtable;

import com.idn.sql.DataBean;

/**
 * <P>各个字段的属性 </P>
 * 
 * @version 0.1
 * @author 苏海军
 */
public class FieldsCollection {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(FieldsCollection.class);

	private static Hashtable hFields = null;
	/**
	 * 构造函数，从Fields中收集应用程序中所有的字段的属性
	 */
	public FieldsCollection() {
		init();
	}

	/**
	 * 初始化，要求SQL语句中的列必须按照如下顺序：<br>
	 * columnid, name, type, length, decimalDigits, cclass
	 */
	private static void init() {
		hFields = new Hashtable();
		String strSql = QueryProperties.getProperty("fields.sql");
		DataBean db = new DataBean();
		try {
			db.executeSelect(strSql);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		for (int i = 0; i < db.getRowCount(); i++) {
			Field field = new Field();
			field.setColumnId(db.getElementValue(i, 0).trim());
			field.setName(db.getElementValue(i, 1).trim());
			field.setType(db.getElementValue(i, 2).trim());
			try {
				field.setLength(Integer.parseInt(db.getElementValue(i, 3).trim()));
			} catch (NumberFormatException nfe) {
				log.warn("字段长度数字类型错误：" + db.getElementValue(i, 3).trim());
				field.setLength(0);
			}
			try {
				field.setDecimalDigits(Integer.parseInt(db.getElementValue(i, 4).trim()));
			} catch (NumberFormatException nfe) {
				log.warn("小数点位数数字类型错误：" + db.getElementValue(i, 4).trim());
				field.setDecimalDigits(0);
			}
			field.setCclass(db.getElementValue(i, 5).trim());
			addField(field);
		}
		db.release();
	}

	/**
	 * Field字段的个数，如果初始化失败，则返回0。
	 * @return 字段个数
	 */
	public static int size() {
		try {
			if (hFields == null)
				init();
			return hFields.size();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return 0;
		}
	}

	/**
	 * 在字段哈希表加入一个字段
	 * @param field 要加入的字段
	 */
	public static void addField(Field field) {
		if (hFields == null)
			init();
		hFields.put(field.getColumnId(), field);
	}

	/**
	 * 在字段哈希表删除指定的一个字段
	 * @param columnId 要删除字段的columnId
	 */
	public static void removeField(String columnId) {
		if (hFields == null)
			init();
		hFields.remove(columnId);
	}

	/**
	 * 在字段哈希表查找指定的一个字段
	 * @param columnId 要查找字段的columnId
	 * @return 查找到的字段
	 */
	public static Field getField(String columnId) {
		if (hFields == null)
			init();
		return (Field) hFields.get(columnId);
	}
}

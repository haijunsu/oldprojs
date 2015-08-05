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
 * <P>�����ֶε����� </P>
 * 
 * @version 0.1
 * @author �պ���
 */
public class FieldsCollection {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(FieldsCollection.class);

	private static Hashtable hFields = null;
	/**
	 * ���캯������Fields���ռ�Ӧ�ó��������е��ֶε�����
	 */
	public FieldsCollection() {
		init();
	}

	/**
	 * ��ʼ����Ҫ��SQL����е��б��밴������˳��<br>
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
				log.warn("�ֶγ����������ʹ���" + db.getElementValue(i, 3).trim());
				field.setLength(0);
			}
			try {
				field.setDecimalDigits(Integer.parseInt(db.getElementValue(i, 4).trim()));
			} catch (NumberFormatException nfe) {
				log.warn("С����λ���������ʹ���" + db.getElementValue(i, 4).trim());
				field.setDecimalDigits(0);
			}
			field.setCclass(db.getElementValue(i, 5).trim());
			addField(field);
		}
		db.release();
	}

	/**
	 * Field�ֶεĸ����������ʼ��ʧ�ܣ��򷵻�0��
	 * @return �ֶθ���
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
	 * ���ֶι�ϣ�����һ���ֶ�
	 * @param field Ҫ������ֶ�
	 */
	public static void addField(Field field) {
		if (hFields == null)
			init();
		hFields.put(field.getColumnId(), field);
	}

	/**
	 * ���ֶι�ϣ��ɾ��ָ����һ���ֶ�
	 * @param columnId Ҫɾ���ֶε�columnId
	 */
	public static void removeField(String columnId) {
		if (hFields == null)
			init();
		hFields.remove(columnId);
	}

	/**
	 * ���ֶι�ϣ�����ָ����һ���ֶ�
	 * @param columnId Ҫ�����ֶε�columnId
	 * @return ���ҵ����ֶ�
	 */
	public static Field getField(String columnId) {
		if (hFields == null)
			init();
		return (Field) hFields.get(columnId);
	}
}

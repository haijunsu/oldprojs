/*
 * @(#)QueryProperties.java  2003-4-27
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

import java.util.ResourceBundle;

/**
 * <P>��ȡ��ѯ��ص��Զ������� </P>
 * 
 * @version 0.1
 * @author navy
 */
public class QueryProperties {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(QueryProperties.class);
	
	/**
	 * ��ѯ���Զ���������ļ�
	 */
	private static final String PROPERTIES_FILE = "com.idn.query.query";

	/**
	 * �󶨵���Դ
	 */
	private static ResourceBundle rb = null;

	/**
	 * ���캯��
	 */
	public QueryProperties() {
		super();
	}

	/**
	 * ����Դ�ļ���ȡ��ָ��KEY��ֵ
	 * @param key ָ����KEY
	 * @return ����KEY��ֵ
	 */
	public static String getProperty(String key) {
		String strRtn = null;
		try {
			if (rb == null)
				rb = ResourceBundle.getBundle(PROPERTIES_FILE);
			strRtn = rb.getString(key);
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			strRtn = "{" + key + "} not found!";
		}
		return strRtn;
	}

}

/*
 * @(#)ActionDataSource.java  2003-7-15
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.sql;

import javax.sql.DataSource;

import com.idn.property.InitServletProperty;

/**
 * <P> ��TOMCAT��ʹ��Struts����Դ������moudle��ʹ��struts-config.xml�ж��������Դ </P>
 * 
 * @version 0.2
 * @author navy
 */

/**
 * <p>�����ˣ� navy<br>
 * ����ʱ�䣺2004-1-15<br>
 * �������ݣ�<br>
 *  <UL>
 *  	<LI>��ԭ������Servlet��ʼ������Ϊ���ü���ʼ��
 *  </UL>
 */
public class ActionDataSource {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(ActionDataSource.class);
	/**
	 * Strust����Դ
	 */
	public static DataSource dataSource = getDataSource();

	/**
	 * ��ȡStrust����Դ
	 * @return Strust����Դ
	 */
	private static DataSource getDataSource() {
		if (SQLBean.getConnectMethod().equalsIgnoreCase("struts")) {
			if (dataSource == null) {
				dataSource =
					(DataSource) InitServletProperty.context.getAttribute(
						"org.apache.struts.action.DATA_SOURCE");
				if (dataSource != null) {
					log.debug("���� struts ����Դ�ɹ�!");
				} else {
					log.debug("���� struts ����Դʧ��!");
				}
			}
			return dataSource;
		} else {
			return null;
		}
	}

}

/*
 * @(#)InitCodesProperties.java  2003-4-3
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.property;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

import com.idn.sql.DataBean;

/**
 * <P>��ʼ����������ݵ���ϣ���У���Ӧ�ó���ֱ��ʹ��</P>
 * @version 0.2
 * @author �պ���
 */

/**
 * <p>�����ˣ� navy<br>
 * ����ʱ�䣺2004-1-14<br>
 * �������ݣ�<br>
 *  <UL>
 *  	<LI>ȥ��HttpServlet�̳У�ɾ��init��������WebӦ���������ر�Ϊ���ü���
 *  </UL>
 */
public class InitCodesProperties {

	/**
	 * ���ڴ�Ŵ����Ĺ�ϣ��
	 */
	private static Hashtable hProperties = null;

	/**
	 * ���ڴ�Ŵ�����ָ���Ĵ���
	 */
	private static Properties pCodes = null;

	/**
	 * Constructor of the object.
	 */
	public InitCodesProperties() {
		super();
	}


	/**
	 * ���� commons-logging API ��������־��¼
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(InitCodesProperties.class);
		
	/**
	 * �ͷ��ڴ�ռ�. <br>
	 */
	public void destroy() {
		hProperties = null;
		pCodes = null;
	}

	/**
	 * ��ʼ�������
	 */
	public static void loadCodes() {
		log.info("Loading codes table ......");
		DataBean db = new DataBean();
		try {
			db.executeSelect(
				"SELECT CCLASS, CCODE, CSHOWC, CSHOWE FROM CODES ORDER BY CCLASS");
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		if (db.getRowCount() == 0) {
			log.warn("û��ȡ�����������ݻ����������Ϊ��!");
			return;
		}
		log.debug("ȡ���Ĵ�������Ϊ��" + String.valueOf(db.getRowCount()));
		//�����еĴ�������ϣ��
		hProperties = new Hashtable();
		pCodes = new Properties();
		String strOldCclass = db.getElementValue(0, "CCLASS").trim();
		for (int i = 0; i < db.getRowCount(); i++) {
			String strNewCclass = db.getElementValue(i, "CCLASS").trim();
			if (!strOldCclass.equals(strNewCclass)) {
				hProperties.put(strOldCclass, pCodes);
				strOldCclass = strNewCclass;
				pCodes = new Properties();
			}
			String strKey = db.getElementValue(i, "CCODE").trim();
			String strValue = db.getElementValue(i, "CSHOWC").trim();
			pCodes.put(strKey, strValue);
		}
		hProperties.put(strOldCclass, pCodes);

		log.info("Load codes table success.");
	}

	/**
	 * @return �����
	 */
	public static Hashtable getProperties() {
		if (hProperties == null || hProperties.isEmpty()) {
			loadCodes();
		}
		return hProperties;
	}

}

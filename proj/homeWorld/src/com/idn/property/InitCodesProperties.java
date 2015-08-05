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
 * <P>初始化代码库内容到哈希表中，供应用程序直接使用</P>
 * @version 0.2
 * @author 苏海军
 */

/**
 * <p>更改人： navy<br>
 * 更改时间：2004-1-14<br>
 * 更改内容：<br>
 *  <UL>
 *  	<LI>去掉HttpServlet继承，删除init方法，由Web应用启动加载变为即用即载
 *  </UL>
 */
public class InitCodesProperties {

	/**
	 * 用于存放代码表的哈希表
	 */
	private static Hashtable hProperties = null;

	/**
	 * 用于存放代码表的指定的代码
	 */
	private static Properties pCodes = null;

	/**
	 * Constructor of the object.
	 */
	public InitCodesProperties() {
		super();
	}


	/**
	 * 启用 commons-logging API 来进行日志记录
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(InitCodesProperties.class);
		
	/**
	 * 释放内存空间. <br>
	 */
	public void destroy() {
		hProperties = null;
		pCodes = null;
	}

	/**
	 * 初始化代码表
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
			log.warn("没有取到代码表的内容或代码表的内容为空!");
			return;
		}
		log.debug("取到的代码条数为：" + String.valueOf(db.getRowCount()));
		//将所有的代码放入哈希表
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
	 * @return 代码表
	 */
	public static Hashtable getProperties() {
		if (hProperties == null || hProperties.isEmpty()) {
			loadCodes();
		}
		return hProperties;
	}

}

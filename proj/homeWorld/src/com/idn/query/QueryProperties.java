/*
 * @(#)QueryProperties.java  2003-4-27
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

import java.util.ResourceBundle;

/**
 * <P>获取查询相关的自定义属性 </P>
 * 
 * @version 0.1
 * @author navy
 */
public class QueryProperties {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(QueryProperties.class);
	
	/**
	 * 查询的自定义的属性文件
	 */
	private static final String PROPERTIES_FILE = "com.idn.query.query";

	/**
	 * 绑定的资源
	 */
	private static ResourceBundle rb = null;

	/**
	 * 构造函数
	 */
	public QueryProperties() {
		super();
	}

	/**
	 * 从资源文件中取出指定KEY的值
	 * @param key 指定的KEY
	 * @return 返回KEY的值
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

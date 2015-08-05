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
 * <P> 在TOMCAT中使用Struts数据源，所有moudle都使用struts-config.xml中定义的数据源 </P>
 * 
 * @version 0.2
 * @author navy
 */

/**
 * <p>更改人： navy<br>
 * 更改时间：2004-1-15<br>
 * 更改内容：<br>
 *  <UL>
 *  	<LI>由原来的用Servlet初始化，改为即用即初始化
 *  </UL>
 */
public class ActionDataSource {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(ActionDataSource.class);
	/**
	 * Strust数据源
	 */
	public static DataSource dataSource = getDataSource();

	/**
	 * 获取Strust数据源
	 * @return Strust数据源
	 */
	private static DataSource getDataSource() {
		if (SQLBean.getConnectMethod().equalsIgnoreCase("struts")) {
			if (dataSource == null) {
				dataSource =
					(DataSource) InitServletProperty.context.getAttribute(
						"org.apache.struts.action.DATA_SOURCE");
				if (dataSource != null) {
					log.debug("创建 struts 数据源成功!");
				} else {
					log.debug("创建 struts 数据源失败!");
				}
			}
			return dataSource;
		} else {
			return null;
		}
	}

}

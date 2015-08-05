/*
 * @(#)MenusIndexByUrl.java  2004-1-17
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package com.idn.menu;

import java.util.Enumeration;
import java.util.Hashtable;

import com.idn.util.CommonTools;

/**
 * <P>MENUS根据URL键索引</P>
 * 
 * @version 0.1
 * @author navy
 */
public class MenusIndexByUrl {
	
	/**
	 * 启用 commons-logging API 来进行日志记录
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(MenusIndexByUrl.class);

	/**
	 * 索引时间，如果其小于菜单加载时间，则重新生成索引
	 */
	public static long indexTime = 0;

	/**
	 * 按URL进行索引，不包括初始化参数
	 */
	private static Hashtable htIndex = null;

	/**
	 * 构造函数
	 */
	public MenusIndexByUrl() {
		super();
	}

	/**
	 * 生成索引
	 */
	private static Hashtable getIndex() {
		if (htIndex == null
			|| htIndex.isEmpty()
			|| indexTime < MenusBean.loadMenuTime) {
			htIndex = new Hashtable();
			Enumeration enum = MenusBean.getHtIndexById().keys();
			String strMenuid, strUrl;
			int indexof = 0;
			while (enum.hasMoreElements()) {
				strMenuid = (String) enum.nextElement();
				strUrl = MenusUtil.getMenuItemByMenuId(strMenuid).getUrl();
				indexof = strUrl.indexOf("?");
				if (indexof > 0) {
					strUrl = strUrl.substring(0, indexof);
					indexof = 0;
				}
				if (htIndex.containsKey(strUrl)) {
					strMenuid =
						strMenuid + "," + (String) htIndex.get(strUrl);
				}
				if (log.isDebugEnabled()) {
					log.debug("索引内容：" + strUrl + "/" + strMenuid);
				}
				htIndex.put(strUrl, strMenuid);
			}
			indexTime = System.currentTimeMillis();
		}
		return htIndex;
	}

	/**
	 * 根据URL返回菜单项集合
	 * @param url 菜单URL，不包含初始化参数
	 * @return 菜单项集合
	 */
	public static MenuItem[] getMenuItemsByUrl(String url) {

		if (getIndex().get(url) == null)
			return null;
		if (log.isDebugEnabled()) {
			log.debug("要查询的URL: " + url);
		}
		String strMenuids = (String) getIndex().get(url);
		String[] ids = CommonTools.stringToArray(strMenuids, ",");
		if (log.isDebugEnabled()) {
			log.debug("找到URL节点的个数: " + ids.length);
		}
		MenuItem[] mi = new MenuItem[ids.length];
		for (int i = 0; i < mi.length; i++) {
			mi[i] = MenusUtil.getMenuItemByMenuId(ids[i]);
			if (log.isDebugEnabled()) {
				log.debug("节点的URL：" + mi[i].getUrl());
			}
		}
		return mi;
	}
}

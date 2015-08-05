/*
 * @(#)MenusUtil.java  2004-2-3
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package com.idn.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

import com.idn.util.CommonTools;

/**
 * <P>菜单程序用到的一些函数</P>
 * 
 * @version 0.1
 * @author navy
 */
public class MenusUtil {
	
	/**
	 * 启用 commons-logging API 来进行日志记录
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(MenusUtil.class);
		
	/**
	 * 构造函数
	 */
	public MenusUtil() {
		super();
	}
	
	/**
	 * 获取指定menuid的菜单项
	 * @param menuid 
	 * @return 菜单项
	 */
	public static MenuItem getMenuItemByMenuId(String menuid) {
		return getMenuItemByMenuId(menuid, null);
	}

	/**
	 * 从特定的菜单集合中中获取指定menuid菜单项
	 * @param menuid
	 * @param ht 菜单集合
	 * @return 菜单项
	 */
	public static MenuItem getMenuItemByMenuId(String menuid, Hashtable ht) {
		if (ht == null)
			ht = MenusBean.getHtMenus();

		if (ht.containsKey(menuid)) {
			return (MenuItem) ht.get(menuid);
		}
		if (MenusBean.getHtIndexById().containsKey(menuid)) {
			String mitn = (String) MenusBean.getHtIndexById().get(menuid);
			return (MenuItem) ((MenuNode) ht.get(mitn)).getHtMenu().get(menuid);
		}
		return null;
	}
	
	/**
	 * 返回菜单排序后的ID数组
	 * @param htMenus
	 * @return 排序后的ID数组
	 */
	public static String[] getSortedMenuids (Hashtable htMenus) {
		if (htMenus == null)
			return null;
		ArrayList al = new ArrayList();
		Enumeration enum = htMenus.keys();
		while (enum.hasMoreElements()) {
			String element = (String) enum.nextElement();
			al.add(element);
		}
		String[] sortArray = (String[])al.toArray(new String[al.size()]);
		Arrays.sort(sortArray);
		return sortArray;
	}
	
	/**
	 * 将字符串URL中的参数提取到哈希表
	 * @param strUrl
	 * @return 哈希表
	 */
	public static Hashtable parseStringUrlParamters(String strUrl) {
		Hashtable ht = new Hashtable();
		int nTemp = 0;
		nTemp = strUrl.indexOf("?");
		if (nTemp > -1) {
			String[] strTempArray;
			strUrl = strUrl.substring(nTemp + 1);
			if (log.isDebugEnabled()) {
				log.debug("参数：" + strUrl);
			}
			strTempArray = CommonTools.stringToArray(strUrl, "&");
			for (int i = 0; i < strTempArray.length; i++) {
				nTemp = strTempArray[i].indexOf("=");
				ht.put(
					strTempArray[i].substring(0, nTemp).trim(),
					strTempArray[i].substring(nTemp + 1).trim());
			}
		} else {
			return null;
		}
		return ht;
	}
	

}

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
 * <P>�˵������õ���һЩ����</P>
 * 
 * @version 0.1
 * @author navy
 */
public class MenusUtil {
	
	/**
	 * ���� commons-logging API ��������־��¼
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(MenusUtil.class);
		
	/**
	 * ���캯��
	 */
	public MenusUtil() {
		super();
	}
	
	/**
	 * ��ȡָ��menuid�Ĳ˵���
	 * @param menuid 
	 * @return �˵���
	 */
	public static MenuItem getMenuItemByMenuId(String menuid) {
		return getMenuItemByMenuId(menuid, null);
	}

	/**
	 * ���ض��Ĳ˵��������л�ȡָ��menuid�˵���
	 * @param menuid
	 * @param ht �˵�����
	 * @return �˵���
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
	 * ���ز˵�������ID����
	 * @param htMenus
	 * @return ������ID����
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
	 * ���ַ���URL�еĲ�����ȡ����ϣ��
	 * @param strUrl
	 * @return ��ϣ��
	 */
	public static Hashtable parseStringUrlParamters(String strUrl) {
		Hashtable ht = new Hashtable();
		int nTemp = 0;
		nTemp = strUrl.indexOf("?");
		if (nTemp > -1) {
			String[] strTempArray;
			strUrl = strUrl.substring(nTemp + 1);
			if (log.isDebugEnabled()) {
				log.debug("������" + strUrl);
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

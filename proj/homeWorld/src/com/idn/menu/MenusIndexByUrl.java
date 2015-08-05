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
 * <P>MENUS����URL������</P>
 * 
 * @version 0.1
 * @author navy
 */
public class MenusIndexByUrl {
	
	/**
	 * ���� commons-logging API ��������־��¼
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(MenusIndexByUrl.class);

	/**
	 * ����ʱ�䣬�����С�ڲ˵�����ʱ�䣬��������������
	 */
	public static long indexTime = 0;

	/**
	 * ��URL������������������ʼ������
	 */
	private static Hashtable htIndex = null;

	/**
	 * ���캯��
	 */
	public MenusIndexByUrl() {
		super();
	}

	/**
	 * ��������
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
					log.debug("�������ݣ�" + strUrl + "/" + strMenuid);
				}
				htIndex.put(strUrl, strMenuid);
			}
			indexTime = System.currentTimeMillis();
		}
		return htIndex;
	}

	/**
	 * ����URL���ز˵����
	 * @param url �˵�URL����������ʼ������
	 * @return �˵����
	 */
	public static MenuItem[] getMenuItemsByUrl(String url) {

		if (getIndex().get(url) == null)
			return null;
		if (log.isDebugEnabled()) {
			log.debug("Ҫ��ѯ��URL: " + url);
		}
		String strMenuids = (String) getIndex().get(url);
		String[] ids = CommonTools.stringToArray(strMenuids, ",");
		if (log.isDebugEnabled()) {
			log.debug("�ҵ�URL�ڵ�ĸ���: " + ids.length);
		}
		MenuItem[] mi = new MenuItem[ids.length];
		for (int i = 0; i < mi.length; i++) {
			mi[i] = MenusUtil.getMenuItemByMenuId(ids[i]);
			if (log.isDebugEnabled()) {
				log.debug("�ڵ��URL��" + mi[i].getUrl());
			}
		}
		return mi;
	}
}

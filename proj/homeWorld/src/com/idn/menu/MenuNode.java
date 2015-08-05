/*
 * @(#)MenuNode.java  2003-6-30
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * <P>���˵���</P>
 * 
 * @version 0.2
 * @author navy
 */
public class MenuNode extends MenuItem {

	/**
	 * ���ڴ���Ӳ˵���hash��
	 */
	private Hashtable htMenu = null;

	/**
	 * �Ӳ˵���ɵ�����
	 */
	private MenuItem[] menuItems = null;

	/**
	 * ����ID
	 */
	private String[] sortedMenuIds = null;
	
	/**
	 * �Ƿ���Ҫ��������
	 */
	private boolean isNeedResort = false;

	/**
	 * ���캯��
	 */
	public MenuNode() {
		this.htMenu = new Hashtable();
	}

	/**
	 * ���캯��
	 */
	public MenuNode(String strNodeName) {
		super.setMenuId(strNodeName);
		this.htMenu = new Hashtable();
	}

	/**
	 * ���캯��
	 */
	public MenuNode(String strNodeId, String strNodeName) {
		super.setMenuId(strNodeId);
		super.setChineseName(strNodeName);
		this.htMenu = new Hashtable();
	}

	/**
	 * �����Ӳ˵�����
	 * @return �Ӳ˵�����
	 */
	public int size() {
		return htMenu.size();
	}

	/**
	 * ��HashTable������Ӳ˵���
	 * @param menuItem �˵���
	 */
	public void addMenuItem(MenuItem menuItem) {
		htMenu.put(menuItem.getMenuId(), menuItem);
		if (!isNeedResort) isNeedResort = true;
	}

	/**
	 * ��HashTable����ɾ���˵���
	 * @param menuId �˵���ID
	 */
	public void removeMenuItem(String menuId) {
		htMenu.remove(menuId);
		if (!isNeedResort) isNeedResort = true;
	}

	/**
	 * @return
	 */
	public Hashtable getHtMenu() {
		return htMenu;
	}

	/**
	 * @return
	 */
	public String getNodeName() {
		return getMenuId();
	}

	/**
	 * @param hashtable
	 */
	public void setHtMenu(Hashtable hashtable) {
		htMenu = hashtable;
	}

	/**
	 * @param string
	 */
	public void setNodeName(String string) {
		setChineseName(string);
	}

	/**
	 * @return ���˵�ID
	 */
	public String getNodeId() {
		return getMenuId();
	}

	/**
	 * @param string
	 */
	public void setNodeId(String string) {
		setMenuId(string);
	}

	/**
	 * @return �˵�����
	 */
	public MenuItem[] getMenuItems() {
		ArrayList al = new ArrayList();
		Enumeration enum = htMenu.elements();
		while (enum.hasMoreElements()) {
			al.add((String) enum.nextElement());
		}
		String[] strTempArray = (String[]) al.toArray(new String[al.size()]);
		Arrays.sort(strTempArray);
		menuItems = new MenuItem[strTempArray.length];
		for (int i = 0; i < strTempArray.length; i++) {
			menuItems[i] = (MenuItem) htMenu.get(strTempArray[i]);
		}
		return menuItems;
	}

	/**
	 * @param items
	 */
	public void setMenuItems(MenuItem[] items) {
		menuItems = items;
	}

	/**
	 * @return ����˵�ID
	 */
	public String[] getSortedMenuIds() {
		if (sortedMenuIds == null || isNeedResort) {
			sortedMenuIds = MenusUtil.getSortedMenuids(getHtMenu());
		}
		return sortedMenuIds;
	}

	/**
	 * @param strings
	 */
	public void setSortedMenuIds(String[] strings) {
		sortedMenuIds = strings;
	}

	/**
	 * @return �Ƿ���Ҫ��������
	 */
	public boolean isNeedResort() {
		return isNeedResort;
	}

	/**
	 * @param b
	 */
	public void setNeedResort(boolean b) {
		isNeedResort = b;
	}

	/* ���� Javadoc��
	 * @see com.idn.menu.MenuItem#setLocaleCode(java.lang.String)
	 */
	public void setLocaleCode(String string) {
		super.setLocaleCode(string);
		for (int i = 0; i < getSortedMenuIds().length; i++) {
			MenuItem mi = (MenuItem)getHtMenu().get(getSortedMenuIds()[i]);
			mi.setLocaleCode(string);
			htMenu.put(mi.getMenuId(), mi);
		}
	}

}

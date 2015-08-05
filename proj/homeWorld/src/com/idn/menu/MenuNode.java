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
 * <P>主菜单项</P>
 * 
 * @version 0.2
 * @author navy
 */
public class MenuNode extends MenuItem {

	/**
	 * 用于存放子菜单的hash表
	 */
	private Hashtable htMenu = null;

	/**
	 * 子菜单组成的数组
	 */
	private MenuItem[] menuItems = null;

	/**
	 * 排序ID
	 */
	private String[] sortedMenuIds = null;
	
	/**
	 * 是否需要重新排序
	 */
	private boolean isNeedResort = false;

	/**
	 * 构造函数
	 */
	public MenuNode() {
		this.htMenu = new Hashtable();
	}

	/**
	 * 构造函数
	 */
	public MenuNode(String strNodeName) {
		super.setMenuId(strNodeName);
		this.htMenu = new Hashtable();
	}

	/**
	 * 构造函数
	 */
	public MenuNode(String strNodeId, String strNodeName) {
		super.setMenuId(strNodeId);
		super.setChineseName(strNodeName);
		this.htMenu = new Hashtable();
	}

	/**
	 * 返回子菜单个数
	 * @return 子菜单个数
	 */
	public int size() {
		return htMenu.size();
	}

	/**
	 * 向HashTable表中添加菜单项
	 * @param menuItem 菜单项
	 */
	public void addMenuItem(MenuItem menuItem) {
		htMenu.put(menuItem.getMenuId(), menuItem);
		if (!isNeedResort) isNeedResort = true;
	}

	/**
	 * 从HashTable表中删除菜单项
	 * @param menuId 菜单项ID
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
	 * @return 主菜单ID
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
	 * @return 菜单数组
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
	 * @return 排序菜单ID
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
	 * @return 是否需要重新排序
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

	/* （非 Javadoc）
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

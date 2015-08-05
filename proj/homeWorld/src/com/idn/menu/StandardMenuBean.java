/*
 * @(#)StandardMenuBean.java  2003-6-30
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.menu;

import java.util.Hashtable;

import com.idn.property.PropertyManager;
import com.idn.util.CommonTools;

/**
 * <P>生成标准菜单程序，菜单表中以M开头的均为模块，不在菜单中显示</P>
 * 
 * @version 0.1
 * @author navy
 */

public class StandardMenuBean {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(StandardMenuBean.class);

	/**
	 * 是否重载菜单
	 */
	protected boolean reload = false;
	
	/**
	 * 菜单是否加密
	 */
	protected boolean encryption = PropertyManager.getBooleanProperty("menu.encryption");
	
	/**
	 * 用户权限
	 */
	protected String purview = "0";

	/**
	 * 上下文路径
	 */
	protected String contextPath = null;

	/**
	 * 菜单语言标志，“1”为中文
	 */
	protected String localeCode = "1";

	/**
	 * SQL附加WHERE条件
	 */
	protected String sqlWhere = null;

	/**
	 * 超链接目标框架
	 */
	protected String urlTarget = "_self";

	/**
	 * 菜单数组
	 */
	protected MenuItem[] menuArray = null;

	/**
	 * 加载菜单时间
	 */
	protected long loadMenuTime = 0l;

	/**
	 * 生成排序菜单ID的时间
	 */
	protected long sortMenuNodeIdTime = 0l;

	/**
	 * 菜单集合
	 */
	protected Hashtable htMenus = null;

	/**
	 * 排序后的菜单ID
	 */
	protected String[] sortedMenuNodeIds = null;

	/**
	 * 菜单项个数
	 */
	protected int menuItemNumber = 0;

	/**
	 * 构造函数
	 */
	public StandardMenuBean() {
		super();
	}

	/**
	 * @return 排序后ID
	 */
	protected String[] getSortedMenuNodeIds() {
		if (sortedMenuNodeIds == null || sortMenuNodeIdTime < loadMenuTime || isReload()) {
			sortedMenuNodeIds = MenusUtil.getSortedMenuids(getHtMenus());
			sortMenuNodeIdTime = System.currentTimeMillis();
			log.debug("sort time:" + sortMenuNodeIdTime);
		}
		return sortedMenuNodeIds;
	}

	/**
	 * @return 菜单哈希表
	 */
	protected Hashtable getHtMenus() {
		if (htMenus == null
			|| isReload()
			|| htMenus.isEmpty()
			|| loadMenuTime < MenusBean.loadMenuTime
			|| loadMenuTime == 0) {
			initMenus();
		}
		return htMenus;
	}

	/**
	 * @return 主菜单个数
	 */
	public int size() {
		return htMenus.size();
	}

	/**
	 * 从Menus中获取有权限的菜单
	 */
	private void initMenus() {
		log.debug("初始化个性菜单");
		//初始化
		if (MenusBean.loadMenuTime == 0l || MenusBean.isReload()) {
			//初始化Menus菜单加载参数
			MenusBean.setSqlWhere(this.sqlWhere);
			MenusBean.setContextPath(this.contextPath);
			log.debug("第一次加载");
		}
		//子菜单计数器
		int n = 0;
		htMenus = new Hashtable();

		for (int i = 0; i < MenusBean.getSortedMenuNodeIds().length; i++) {
			MenuNode mn = (MenuNode) MenusBean.getHtMenus().get(MenusBean.getSortedMenuNodeIds()[i]);
			MenuNode mnTemp = new MenuNode();
			//是否模块
			if (mn.getNodeId().substring(0, 1).equalsIgnoreCase("M"))
				continue;
			//节点是否有权限
			log.debug(purview + "/" + mn.getPurview());
			if (CommonTools.isMenuShow(purview, mn.getPurview(), encryption)) {
				mnTemp.setNodeName(mn.getNodeName());
				mnTemp.setNodeId(mn.getNodeId());
				mnTemp.setChineseName(mn.getChineseName());
				mnTemp.setEnglishName(mn.getEnglishName());
				mnTemp.setMenuId(mn.getMenuId());
				mnTemp.setPurview(mn.getPurview());
				mnTemp.setUrl(mn.getUrl());
				//节点是否有子菜单
				if (mn.size() > 0) {
					for (int j = 0; j < mn.getSortedMenuIds().length; j++) {
						MenuItem mi =
							MenusUtil.getMenuItemByMenuId(
								mn.getSortedMenuIds()[j],
								mn.getHtMenu());
						log.debug(purview + "/" + mi.getPurview());
						if (CommonTools
							.isMenuShow(purview, mi.getPurview(),encryption)) {
							//有权限
							MenuItem miTemp = new MenuItem();
							miTemp.setChineseName(mi.getChineseName());
							miTemp.setEnglishName(mi.getEnglishName());
							miTemp.setMenuId(mi.getMenuId());
							miTemp.setPurview(mi.getPurview());
							miTemp.setUrl(mi.getUrl());
							mnTemp.addMenuItem(miTemp);
							n++;
						}
					}
				}
				if (mnTemp.size() == 0) {
					//没有子菜单，是否本身有URL
					if (mnTemp.getUrl() == null || mnTemp.getUrl().length() == 0)
						continue;
				}
				//将节点放入菜单中
				mnTemp.setLocaleCode(getLocaleCode());
				htMenus.put(mnTemp.getNodeId(), mnTemp);
			}
		}
		menuItemNumber = n + size();
		reload = false;
		loadMenuTime = System.currentTimeMillis();
		log.debug(menuItemNumber);
		log.debug("个性化菜单完成");
	}

	/**
	 * 释放菜单
	 */
	public void release() {
		htMenus.clear();
		reload = false;
		purview = "0";
	}

	/**
	 * @return 用户权限
	 */
	public String getPurview() {
		return purview;
	}

	/**
	 * @return 是否重载
	 */
	public boolean isReload() {
		return reload;
	}

	/**
	 * @param string
	 */
	public void setPurview(String string) {
		purview = string;
	}

	/**
	 * @param b
	 */
	public void setReload(boolean b) {
		reload = b;
	}

	/**
	 * @return 上下文路径
	 */
	public String getContextPath() {
		return contextPath;
	}

	/**
	 * @param string
	 */
	public void setContextPath(String string) {
		contextPath = string;
	}

	/**
	 * @return 是不是中文代码
	 */
	public String getLocaleCode() {
		return localeCode;
	}

	/**
	 * @param string
	 */
	public void setLocaleCode(String string) {
		localeCode = string;
	}

	/**
	 * @return 超链接目标框架
	 */
	public String getUrlTarget() {
		return urlTarget;
	}

	/**
	 * @param string
	 */
	public void setUrlTarget(String string) {
		urlTarget = string;
	}

	/**
	 * @return 菜单数组，适用于没有节点和菜单
	 */
	public MenuItem[] getMenuArray() {
		if (isReload() || menuArray == null ) {
			initMenus();
			log.debug(menuItemNumber);
			menuArray = new MenuItem[menuItemNumber];
			//下标
			int k = 0;
			try {
				for (int i = 0; i < getSortedMenuNodeIds().length; i++) {
					MenuNode mn = (MenuNode)getHtMenus().get(getSortedMenuNodeIds()[i]);
					menuArray[k++] = (MenuItem)mn;
					log.debug(mn.size() + mn.getName());
					for (int j = 0; j < mn.getSortedMenuIds().length; j++) {
						menuArray[k++] =
							MenusUtil.getMenuItemByMenuId(
								mn.getSortedMenuIds()[j],
								mn.getHtMenu());
						if (menuArray[k-1] == null)
							log.debug(mn.getSortedMenuIds()[j] + " is null!");
						else
							log.debug(menuArray[k-1].getName());
					}
				}
				log.debug("初始化菜单个数: " + k);
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(), e);
			}
			log.debug("生成菜单完成");
			log.debug(menuArray.length);
		}
		return menuArray;
	}

	/**
	 * @param items
	 */
	public void setMenuArray(MenuItem[] items) {
		menuArray = items;
	}

	/**
	 * @return SQL附加条件
	 */
	public String getSqlWhere() {
		return sqlWhere;
	}

	/**
	 * @param string
	 */
	public void setSqlWhere(String string) {
		sqlWhere = string;
	}
}

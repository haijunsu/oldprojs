/*
 * @(#)Menus.java  2004-1-17
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package com.idn.menu;

import java.sql.SQLException;
import java.util.Hashtable;

import com.idn.property.PropertyManager;
import com.idn.sql.DataBean;
import com.idn.sql.DynaSqlBean;
import com.idn.util.CommonTools;

/**
 * <P>
 * 菜单项
 * </P>
 * 
 * @version 0.1
 * @author navy
 */
public class MenusBean {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log = new com.idn.log.LogWrapper(
			MenusBean.class);

	/**
	 * 生成排序菜单ID的时间
	 */
	public static long sortMenuNodeIdTime = 0l;

	/**
	 * 加载菜单时间
	 */
	public static long loadMenuTime = 0l;

	/**
	 * 是否重载菜单
	 */
	private static boolean reload = false;

	/**
	 * SQL附加WHERE条件
	 */
	private static String sqlWhere = null;

	/**
	 * 上下文路径
	 */
	private static String contextPath = null;

	/**
	 * 菜单集合
	 */
	private static Hashtable htMenus = null;

	/**
	 * 按菜单ID索引
	 */
	private static Hashtable htIndexById = null;

	/**
	 * 排序后的菜单ID
	 */
	private static String[] sortedMenuNodeIds = null;

	/**
	 * 构造函数
	 */
	public MenusBean() {
		super();
	}

	/**
	 * @return 排序后的节点ID
	 */
	private static String[] getSortedId() {
		if (sortedMenuNodeIds == null || sortMenuNodeIdTime < loadMenuTime) {
			log.debug("startSorting");
			sortedMenuNodeIds = MenusUtil.getSortedMenuids(getHtMenus());
			sortMenuNodeIdTime = System.currentTimeMillis();
		}
		return sortedMenuNodeIds;
	}

	/**
	 * @return 菜单哈希表
	 */
	public static Hashtable getHtMenus() {
		if (htMenus == null || reload || htMenus.isEmpty()) {
			loadMenus();
		}
		return htMenus;
	}

	/**
	 * @return 主菜单个数
	 */
	public static int size() {
		return htMenus.size();
	}

	/**
	 * 加载菜单
	 */
	private static synchronized void loadMenus() {

		//初始化
		if (contextPath == null) {
			log.error("请先设置应用程序上下文!");
			return;
		}
		htMenus = new Hashtable();
		htIndexById = new Hashtable();
		StringBuffer sbQuerySql = new StringBuffer(
				"SELECT * FROM MENUALL WHERE (APPSYS = '99' OR APPSYS = '");
		sbQuerySql.append(PropertyManager.getProperty("appsys")).append("')");
		String strTempSqlwhere = PropertyManager
				.getProperty("menu.add.sqlwhere");
		if (sqlWhere == null || !sqlWhere.trim().equals("")) {
			if (strTempSqlwhere != null && !strTempSqlwhere.trim().equals(""))
				sqlWhere = strTempSqlwhere;
		} else {
			if (strTempSqlwhere != null && !strTempSqlwhere.trim().equals(""))
				sqlWhere = "(" + sqlWhere + ") AND (" + strTempSqlwhere + ")";
		}
		if (sqlWhere != null && sqlWhere.length() > 3) {
			log.debug("添加的Where条件：" + sqlWhere);
			sbQuerySql.append(" AND (").append(sqlWhere).append(") ");
		}
		sbQuerySql.append(" ORDER BY MENUID");
		DataBean db = new DataBean();
		DynaSqlBean dsb = new DynaSqlBean();
		dsb.setSql(sbQuerySql.toString());
		try {
			db.setCrs(dsb.executeQuery());
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			return;
		}
		log.debug("从数据库中取得菜单数：" + String.valueOf(db.getRowCount()));

		String strMenuNameEn;
		String strMenuNameCn;
		String strMenuId;
		String strUrl;
		String strPurview;

		//所有菜单不用经过权限的检验，统一放到哈希表中，其中判断节点和
		//菜单项的条件也改为仅与前一项的前两位字符相比较，如果不一样则
		//为新的节点
		for (int i = 0; i < db.getRowCount(); i++) {
			strMenuId = db.getElementValue(i, "MENUID").trim();
			log.debug(strMenuId);
			strPurview = db.getElementValue(i, "MPURVIEW").trim();
			strMenuNameCn = db.getElementValue(i, "MNAMEC").trim();
			if (strMenuNameCn.indexOf("(&") > 0)
				strMenuNameCn = strMenuNameCn.substring(0, strMenuNameCn
						.indexOf("(&"));
			MenuNode mn = new MenuNode(strMenuId, strMenuNameCn);

			strMenuNameEn = db.getElementValue(i, "MNAMEE").trim();
			strUrl = db.getElementValue(i, "MFILEE").trim();
			if (strUrl.length() > 0) {
				if (strUrl.startsWith("/"))
					strUrl = getContextPath() + strUrl;
				strUrl = CommonTools.encodeUrl(strUrl);
			}
			mn.setUrl(strUrl);
			mn.setEnglishName(strMenuNameEn);
			mn.setPurview(strPurview);

			htIndexById.put(strMenuId, mn.getNodeId());

			//填充主菜单
			while (i < db.getRowCount() - 1) {
				strMenuId = db.getElementValue(i + 1, "MENUID").trim();
				if (strMenuId.startsWith(mn.getNodeId().substring(0, 2))) {
					//子菜单
					i++;
					log.debug(strMenuId);
					strPurview = db.getElementValue(i, "MPURVIEW").trim();
					//						if (!CommonTools.isMenuShow(strPurview, purview))
					//							continue;
					strMenuNameCn = db.getElementValue(i, "MNAMEC").trim();
					if (strMenuNameCn.indexOf("(&") > 0)
						strMenuNameCn = strMenuNameCn.substring(0,
								strMenuNameCn.indexOf("(&"));
					strMenuNameEn = db.getElementValue(i, "MNAMEE").trim();
					strUrl = db.getElementValue(i, "MFILEE").trim();
					if (strUrl.length() > 0) {
						if (strUrl.startsWith("/"))
							strUrl = getContextPath() + strUrl;
						strUrl = CommonTools.encodeUrl(strUrl);
					}
					MenuItem mi = new MenuItem();
					mi.setMenuId(strMenuId);
					mi.setChineseName(strMenuNameCn);
					mi.setEnglishName(strMenuNameEn);
					mi.setUrl(strUrl);
					mi.setPurview(strPurview);
					mn.addMenuItem(mi);
					htIndexById.put(strMenuId, mn.getNodeId());
				} else {
					break;
				}
			}
			htMenus.put(mn.getNodeId(), mn);
		}

		//释放DATABEAN资源
		db.release();
		reload = false;
		loadMenuTime = System.currentTimeMillis();
		log.info(loadMenuTime);

	}

	/**
	 * 释放菜单
	 */
	public static void release() {
		htMenus.clear();
		reload = false;
	}

	/**
	 * @return 是否重载
	 */
	public static boolean isReload() {
		return reload;
	}

	/**
	 * @param b
	 */
	public static void setReload(boolean b) {
		reload = b;
	}

	/**
	 * @return SQL附加条件
	 */
	public static String getSqlWhere() {
		return sqlWhere;
	}

	/**
	 * @param string
	 */
	public static void setSqlWhere(String string) {
		sqlWhere = string;
	}

	/**
	 * @return 上下文路径，可以从request对象中得到
	 */
	public static String getContextPath() {
		return contextPath;
	}

	/**
	 * @param string
	 */
	public static void setContextPath(String string) {
		contextPath = string;
	}

	/**
	 * @return 排序ID
	 */
	public static String[] getSortedMenuNodeIds() {
		return getSortedId();
	}

	/**
	 * @return 索引ID
	 */
	public static Hashtable getHtIndexById() {
		if (htIndexById == null)
			loadMenus();
		return htIndexById;
	}

}
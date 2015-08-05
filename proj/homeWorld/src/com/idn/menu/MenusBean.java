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
 * �˵���
 * </P>
 * 
 * @version 0.1
 * @author navy
 */
public class MenusBean {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log = new com.idn.log.LogWrapper(
			MenusBean.class);

	/**
	 * ��������˵�ID��ʱ��
	 */
	public static long sortMenuNodeIdTime = 0l;

	/**
	 * ���ز˵�ʱ��
	 */
	public static long loadMenuTime = 0l;

	/**
	 * �Ƿ����ز˵�
	 */
	private static boolean reload = false;

	/**
	 * SQL����WHERE����
	 */
	private static String sqlWhere = null;

	/**
	 * ������·��
	 */
	private static String contextPath = null;

	/**
	 * �˵�����
	 */
	private static Hashtable htMenus = null;

	/**
	 * ���˵�ID����
	 */
	private static Hashtable htIndexById = null;

	/**
	 * �����Ĳ˵�ID
	 */
	private static String[] sortedMenuNodeIds = null;

	/**
	 * ���캯��
	 */
	public MenusBean() {
		super();
	}

	/**
	 * @return �����Ľڵ�ID
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
	 * @return �˵���ϣ��
	 */
	public static Hashtable getHtMenus() {
		if (htMenus == null || reload || htMenus.isEmpty()) {
			loadMenus();
		}
		return htMenus;
	}

	/**
	 * @return ���˵�����
	 */
	public static int size() {
		return htMenus.size();
	}

	/**
	 * ���ز˵�
	 */
	private static synchronized void loadMenus() {

		//��ʼ��
		if (contextPath == null) {
			log.error("��������Ӧ�ó���������!");
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
			log.debug("��ӵ�Where������" + sqlWhere);
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
		log.debug("�����ݿ���ȡ�ò˵�����" + String.valueOf(db.getRowCount()));

		String strMenuNameEn;
		String strMenuNameCn;
		String strMenuId;
		String strUrl;
		String strPurview;

		//���в˵����þ���Ȩ�޵ļ��飬ͳһ�ŵ���ϣ���У������жϽڵ��
		//�˵��������Ҳ��Ϊ����ǰһ���ǰ��λ�ַ���Ƚϣ������һ����
		//Ϊ�µĽڵ�
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

			//������˵�
			while (i < db.getRowCount() - 1) {
				strMenuId = db.getElementValue(i + 1, "MENUID").trim();
				if (strMenuId.startsWith(mn.getNodeId().substring(0, 2))) {
					//�Ӳ˵�
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

		//�ͷ�DATABEAN��Դ
		db.release();
		reload = false;
		loadMenuTime = System.currentTimeMillis();
		log.info(loadMenuTime);

	}

	/**
	 * �ͷŲ˵�
	 */
	public static void release() {
		htMenus.clear();
		reload = false;
	}

	/**
	 * @return �Ƿ�����
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
	 * @return SQL��������
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
	 * @return ������·�������Դ�request�����еõ�
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
	 * @return ����ID
	 */
	public static String[] getSortedMenuNodeIds() {
		return getSortedId();
	}

	/**
	 * @return ����ID
	 */
	public static Hashtable getHtIndexById() {
		if (htIndexById == null)
			loadMenus();
		return htIndexById;
	}

}
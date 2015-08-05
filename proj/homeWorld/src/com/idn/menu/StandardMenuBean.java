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
 * <P>���ɱ�׼�˵����򣬲˵�������M��ͷ�ľ�Ϊģ�飬���ڲ˵�����ʾ</P>
 * 
 * @version 0.1
 * @author navy
 */

public class StandardMenuBean {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(StandardMenuBean.class);

	/**
	 * �Ƿ����ز˵�
	 */
	protected boolean reload = false;
	
	/**
	 * �˵��Ƿ����
	 */
	protected boolean encryption = PropertyManager.getBooleanProperty("menu.encryption");
	
	/**
	 * �û�Ȩ��
	 */
	protected String purview = "0";

	/**
	 * ������·��
	 */
	protected String contextPath = null;

	/**
	 * �˵����Ա�־����1��Ϊ����
	 */
	protected String localeCode = "1";

	/**
	 * SQL����WHERE����
	 */
	protected String sqlWhere = null;

	/**
	 * ������Ŀ����
	 */
	protected String urlTarget = "_self";

	/**
	 * �˵�����
	 */
	protected MenuItem[] menuArray = null;

	/**
	 * ���ز˵�ʱ��
	 */
	protected long loadMenuTime = 0l;

	/**
	 * ��������˵�ID��ʱ��
	 */
	protected long sortMenuNodeIdTime = 0l;

	/**
	 * �˵�����
	 */
	protected Hashtable htMenus = null;

	/**
	 * �����Ĳ˵�ID
	 */
	protected String[] sortedMenuNodeIds = null;

	/**
	 * �˵������
	 */
	protected int menuItemNumber = 0;

	/**
	 * ���캯��
	 */
	public StandardMenuBean() {
		super();
	}

	/**
	 * @return �����ID
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
	 * @return �˵���ϣ��
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
	 * @return ���˵�����
	 */
	public int size() {
		return htMenus.size();
	}

	/**
	 * ��Menus�л�ȡ��Ȩ�޵Ĳ˵�
	 */
	private void initMenus() {
		log.debug("��ʼ�����Բ˵�");
		//��ʼ��
		if (MenusBean.loadMenuTime == 0l || MenusBean.isReload()) {
			//��ʼ��Menus�˵����ز���
			MenusBean.setSqlWhere(this.sqlWhere);
			MenusBean.setContextPath(this.contextPath);
			log.debug("��һ�μ���");
		}
		//�Ӳ˵�������
		int n = 0;
		htMenus = new Hashtable();

		for (int i = 0; i < MenusBean.getSortedMenuNodeIds().length; i++) {
			MenuNode mn = (MenuNode) MenusBean.getHtMenus().get(MenusBean.getSortedMenuNodeIds()[i]);
			MenuNode mnTemp = new MenuNode();
			//�Ƿ�ģ��
			if (mn.getNodeId().substring(0, 1).equalsIgnoreCase("M"))
				continue;
			//�ڵ��Ƿ���Ȩ��
			log.debug(purview + "/" + mn.getPurview());
			if (CommonTools.isMenuShow(purview, mn.getPurview(), encryption)) {
				mnTemp.setNodeName(mn.getNodeName());
				mnTemp.setNodeId(mn.getNodeId());
				mnTemp.setChineseName(mn.getChineseName());
				mnTemp.setEnglishName(mn.getEnglishName());
				mnTemp.setMenuId(mn.getMenuId());
				mnTemp.setPurview(mn.getPurview());
				mnTemp.setUrl(mn.getUrl());
				//�ڵ��Ƿ����Ӳ˵�
				if (mn.size() > 0) {
					for (int j = 0; j < mn.getSortedMenuIds().length; j++) {
						MenuItem mi =
							MenusUtil.getMenuItemByMenuId(
								mn.getSortedMenuIds()[j],
								mn.getHtMenu());
						log.debug(purview + "/" + mi.getPurview());
						if (CommonTools
							.isMenuShow(purview, mi.getPurview(),encryption)) {
							//��Ȩ��
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
					//û���Ӳ˵����Ƿ�����URL
					if (mnTemp.getUrl() == null || mnTemp.getUrl().length() == 0)
						continue;
				}
				//���ڵ����˵���
				mnTemp.setLocaleCode(getLocaleCode());
				htMenus.put(mnTemp.getNodeId(), mnTemp);
			}
		}
		menuItemNumber = n + size();
		reload = false;
		loadMenuTime = System.currentTimeMillis();
		log.debug(menuItemNumber);
		log.debug("���Ի��˵����");
	}

	/**
	 * �ͷŲ˵�
	 */
	public void release() {
		htMenus.clear();
		reload = false;
		purview = "0";
	}

	/**
	 * @return �û�Ȩ��
	 */
	public String getPurview() {
		return purview;
	}

	/**
	 * @return �Ƿ�����
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
	 * @return ������·��
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
	 * @return �ǲ������Ĵ���
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
	 * @return ������Ŀ����
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
	 * @return �˵����飬������û�нڵ�Ͳ˵�
	 */
	public MenuItem[] getMenuArray() {
		if (isReload() || menuArray == null ) {
			initMenus();
			log.debug(menuItemNumber);
			menuArray = new MenuItem[menuItemNumber];
			//�±�
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
				log.debug("��ʼ���˵�����: " + k);
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(), e);
			}
			log.debug("���ɲ˵����");
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
	 * @return SQL��������
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

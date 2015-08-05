/*
 * @(#)MenuItems.java  2003-6-30
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.menu;

/**
 * <P>�˵���</P>
 * 
 * @version 0.1
 * @author navy
 */
public class MenuItem {
	
	/**
	 * �˵�ID
	 */
	private String m_menuId = null;

	/**
	 * URL������
	 */
	private String m_url = null;
	
	/**
	 * ���÷������ԣ���1��Ϊ���ģ�����ΪӢ��
	 */
	private String m_localeCode = "1";

	/**
	 * �˵�Ȩ��
	 */
	private String m_purview = null;
	
	/**
	 * �˵�Ӣ������
	 */
	private String m_englishName = null;
	
	/**
	 * �˵���������
	 */
	private String m_chineseName = null;

	/**
	 * ���캯��
	 */
	public MenuItem() {
		super();
	}

	/**
	 * ���캯��
	 */
	public MenuItem(
		String strId,
		String strCnName,
		String strEnName,
		String strUrl,
		String strPurview) {
		this.m_menuId = strId;
		this.m_chineseName = strCnName;
		this.m_englishName = strEnName;
		this.m_url = strUrl;
		this.m_purview = strPurview;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb
			.append("\"")
			.append(this.m_menuId)
			.append("\", ")
			.append("\"")
			.append(getName())
			.append("\", ")
			.append("\"")
			.append(this.m_url)
			.append("\", ")
			.append("\"")
			.append(this.m_purview)
			.append("\"");
		return sb.toString();
	}

	/**
	 * @return �˵�����
	 */
	public String getName() {
		if (this.m_localeCode.equals("1"))
			return this.m_chineseName;
		return this.m_englishName;
	}


	/**
	 * @return �˵�������
	 */
	public String getUrl() {
		return this.m_url;
	}

	/**
	 * @param string
	 */
	public void setUrl(String string) {
		this.m_url = string;
	}

	/**
	 * @return �˵�ID
	 */
	public String getMenuId() {
		return this.m_menuId;
	}

	/**
	 * @param string
	 */
	public void setMenuId(String string) {
		this.m_menuId = string;
	}

	/**
	 * @return Ȩ��
	 */
	public String getPurview() {
		return this.m_purview;
	}

	/**
	 * @param string
	 */
	public void setPurview(String string) {
		this.m_purview = string;
	}

	/**
	 * @return ���Ĵ���
	 */
	public String getLocaleCode() {
		return this.m_localeCode;
	}

	/**
	 * @param string
	 */
	public void setLocaleCode(String string) {
		this.m_localeCode = string;
	}

	/**
	 * @param string
	 */
	public void setChineseName(String string) {
		this.m_chineseName = string;
	}

	/**
	 * @param string
	 */
	public void setEnglishName(String string) {
		this.m_englishName = string;
	}

	/**
	 * @return ��������
	 */
	public String getChineseName() {
		return this.m_chineseName;
	}

	/**
	 * @return Ӣ������
	 */
	public String getEnglishName() {
		return this.m_englishName;
	}

}

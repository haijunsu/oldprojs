/*
 * @(#)MenuItems.java  2003-6-30
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.menu;

/**
 * <P>菜单项</P>
 * 
 * @version 0.1
 * @author navy
 */
public class MenuItem {
	
	/**
	 * 菜单ID
	 */
	private String m_menuId = null;

	/**
	 * URL超链接
	 */
	private String m_url = null;
	
	/**
	 * 设置访问语言，“1”为中文，其它为英文
	 */
	private String m_localeCode = "1";

	/**
	 * 菜单权限
	 */
	private String m_purview = null;
	
	/**
	 * 菜单英文名称
	 */
	private String m_englishName = null;
	
	/**
	 * 菜单中文名称
	 */
	private String m_chineseName = null;

	/**
	 * 构造函数
	 */
	public MenuItem() {
		super();
	}

	/**
	 * 构造函数
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
	 * @return 菜单名称
	 */
	public String getName() {
		if (this.m_localeCode.equals("1"))
			return this.m_chineseName;
		return this.m_englishName;
	}


	/**
	 * @return 菜单超链接
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
	 * @return 菜单ID
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
	 * @return 权限
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
	 * @return 中文代码
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
	 * @return 中文名称
	 */
	public String getChineseName() {
		return this.m_chineseName;
	}

	/**
	 * @return 英文名称
	 */
	public String getEnglishName() {
		return this.m_englishName;
	}

}

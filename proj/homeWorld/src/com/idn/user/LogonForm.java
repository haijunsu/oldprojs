/**
 * @(#)LogonForm.java  2003-1-28
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * <P>用于处理登录Form</P>
 * <ul>
 * <li>userid - 用户ID
 * <li>username - 用户名称
 * <li>password - 登录口令
 * <li>newpass - 新口令
 * <li>repass - 校验口令
 * <li>localeCode - 菜单显示语言版本 1为中文，其它为英文
 * <li>purview - 用户权限码
 * <li>ignoreCase - 忽略用户名和口令大小写
 * <li>userType - 用户类型，分公司用户和合作伙伴
 * </ul>
 * 
 * @version 0.1
 * @author 苏海军
 */

public class LogonForm extends ActionForm {

	private String userid = null;
	private String username = null;
	private String password = null;
	private String repass = null;
	private String newpass = null;
	private String localeCode = "1";
	private String purview = "255";
	private boolean ignoreCase = false;
	private String userType = "2";
	
	//家世界特定字段
	private long liftBegin = 0;
	private long liftEnd  = 0;
	private String UseridGroup = null;
	private String beGroup = null;

	/**
	 * Get username
	 * @return java.lang.String
	 */
	public java.lang.String getUsername() {
		return username;
	}

	/**
	 * Set username
	 * @param <code>java.lang.String</code>
	 */
	public void setUsername(java.lang.String u) {
		username = u;
	}
	/**
	 * Get password
	 * @return java.lang.String
	 */
	public java.lang.String getPassword() {
		if (ignoreCase)
			password = password.toUpperCase();
		return password;
	}

	/**
	 * Set password
	 * @param <code>java.lang.String</code>
	 */
	public void setPassword(java.lang.String p) {
		password = p;
	}
	/**
	* Constructor
	*/
	public LogonForm() {

		super();

	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.
		userid = null;
		username = null;
		password = null;
		repass = null;
		newpass = null;
		localeCode = "1";
		purview = "255";
		ignoreCase = false;
		userType = "2";
		liftBegin = 0;
		String liftEnd  = null;
		String UseridGroup = null;
		String beGroup = null;
	}
	/**
	 * @return 菜单语言版本
	 */
	public String getLocaleCode() {
		return localeCode;
	}

	/**
	 * @return 新口令
	 */
	public String getNewpass() {
		if (ignoreCase && newpass != null)
			newpass = newpass.toUpperCase();
		return newpass;
	}

	/**
	 * @return 校验口令
	 */
	public String getRepass() {
		if (ignoreCase && repass != null)
			repass = repass.toUpperCase();
		return repass;
	}

	/**
	 * @param string 
	 */
	public void setLocaleCode(String string) {
		localeCode = string;
	}

	/**
	 * @param string
	 */
	public void setNewpass(String string) {
		newpass = string;
	}

	/**
	 * @param string
	 */
	public void setRepass(String string) {
		repass = string;
	}

	/**
	 * @return 用户ID
	 */
	public String getUserid() {
		if (ignoreCase)
			userid = userid.toUpperCase();
		return userid;
	}

	/**
	 * @param string
	 */
	public void setUserid(String string) {
		userid = string;
	}

	/**
	 * @return 用户权限
	 */
	public String getPurview() {
		return purview;
	}

	/**
	 * @param string
	 */
	public void setPurview(String string) {
		purview = string;
	}

	/**
	 * @return 是否儿忽略大小写
	 */
	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	/**
	 * @param b
	 */
	public void setIgnoreCase(boolean b) {
		ignoreCase = b;
	}
	/**
	 * @return 用户类型
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param string
	 */
	public void setUserType(String string) {
		userType = string;
	}

	/**
	 * @return bgGruop
	 */
	public String getBeGroup() {
		return beGroup;
	}

	/**
	 * @return 生命起始值
	 */
	public long getLiftBegin() {
		return liftBegin;
	}

	/**
	 * @return 生命结束值
	 */
	public long getLiftEnd() {
		return liftEnd;
	}

	/**
	 * @return 用户ID分组
	 */
	public String getUseridGroup() {
		return UseridGroup;
	}

	/**
	 * @param string
	 */
	public void setBeGroup(String string) {
		beGroup = string;
	}

	/**
	 * @param string
	 */
	public void setLiftBegin(long n) {
		liftBegin = n;
	}

	/**
	 * @param string
	 */
	public void setLiftEnd(long n) {
		liftEnd = n;
	}

	/**
	 * @param string
	 */
	public void setUseridGroup(String string) {
		UseridGroup = string;
	}

}

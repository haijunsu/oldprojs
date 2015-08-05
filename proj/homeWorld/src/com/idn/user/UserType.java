/*
 * @(#)UserType.java  2003-10-31
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.user;

/**
 * <P>用户类型，用来区分公司员工和客户</P>
 * 
 * @version 0.1
 * @author navy
 */
public class UserType {

	/**
	 * 公司员工
	 */
	public static final String EMPLOYEE = "1";
	
	/**
	 * 业务伙伴
	 */
	public static final String ASSOCIATOR = "2";
	
	/**
	 * 没有注册
	 */
	public static final String UNREGISTERED = "3";
	
	/**
	 * 服务提供商
	 */
	public static final String VENDOR = "4";

	
	/**
	 * 实例化
	 */
	public UserType() {
		super();
	}

}

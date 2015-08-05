/*
 * @(#)LogonException.java  2003-10-17
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.user;

/**
 * <P>登录时发生的错误</P>
 * 
 * @version 0.1
 * @author navy
 */
public class UserException extends Exception {

	/**
	 * 没有错误
	 */
	public static final int NO_ERROR = 0;
	
	/**
	 * 用户ID不存在
	 */
	public static final int USERID_NOT_EXISTED = 1;
	
	/**
	 * 口令错误
	 */
	public static final int WRONG_PASSWORD = 2;
	
	/**
	 * USERID为空
	 */
	public static final int USERID_EMPTY = 3;
	
	/**
	 * 口令为空
	 */
	public static final int PASSWORD_EMPTY = 4;
	
	/**
	 * 更新口令失败
	 */
	public static final int UPDATE_PASS_FAIL = 5;
	
	/**
	 * 查询用户信息出错
	 */
	public static final int QUERY_INFORMATION_FAIL = 6;
	
	/**
	 * 插入用户信息出错
	 */
	public static final int INSERT_INFORMATION_FAIL = 7;
	
	/**
	 * 更新用户信息出错
	 */
	public static final int UPDATE_INFORMATION_FAIL = 8;
	
	/**
	 * 用户已经存在
	 */
	public static final int USER_EXISTED = 9; 
	
	/**
	 * 没有登录
	 */
	public static final int NOT_LOGIN = 10;
	
	/**
	 * 没有使用此系统的权限
	 */
	public static final int NOT_PERMIT = 11;
	
	/**
	 * 需要使用指定的GROUP的USERID登录
	 */
	public static final int NEED_GROUP_USERID_LOGON = 12;
	
	/**
	 * 用户ID失效，超过生命周期
	 */
	public static final int LIFE_INACTIVE = 13;
	
	public static final int DUPLICATED_LOGON = 14;
	
	/**
	 * 系统错误
	 */
	public static final int SYSTEM_ERROR = 99;

	/**
	 * 错误代码<br>
	 * <pre>
	 *	0 - 无错误
	 *	1 - 用户名不存在
	 *	2 - 口令错误
	 *  3 - 用户名为空
	 *  4 - 口令为空 
	 *  5 - 更改口令失败，新旧口令不匹配
	 *  6 - 系统错误
	 * </pre>
	 */
	private int errCode = 0;
	
	/**
	 * 错误消息
	 */
	private String errMessage = null;
	/**
	 * 构造函数
	 * @param ncode
	 * @param strMsg
	 */
	public UserException(int ncode, String strMsg) {
		super(strMsg);
		this.errCode = ncode;
		this.errMessage = strMsg;
	}
	
	

	/**
	 * @return 错误代码
	 */
	public int getErrCode() {
		return errCode;
	}

	/**
	 * @return 错误消息
	 */
	public String getErrMessage() {
		return errMessage;
	}

	/**
	 * @param i
	 */
	public void setErrCode(int i) {
		errCode = i;
	}

	/**
	 * @param string
	 */
	public void setErrMessage(String string) {
		errMessage = string;
	}

}

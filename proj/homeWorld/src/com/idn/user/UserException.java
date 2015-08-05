/*
 * @(#)LogonException.java  2003-10-17
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.user;

/**
 * <P>��¼ʱ�����Ĵ���</P>
 * 
 * @version 0.1
 * @author navy
 */
public class UserException extends Exception {

	/**
	 * û�д���
	 */
	public static final int NO_ERROR = 0;
	
	/**
	 * �û�ID������
	 */
	public static final int USERID_NOT_EXISTED = 1;
	
	/**
	 * �������
	 */
	public static final int WRONG_PASSWORD = 2;
	
	/**
	 * USERIDΪ��
	 */
	public static final int USERID_EMPTY = 3;
	
	/**
	 * ����Ϊ��
	 */
	public static final int PASSWORD_EMPTY = 4;
	
	/**
	 * ���¿���ʧ��
	 */
	public static final int UPDATE_PASS_FAIL = 5;
	
	/**
	 * ��ѯ�û���Ϣ����
	 */
	public static final int QUERY_INFORMATION_FAIL = 6;
	
	/**
	 * �����û���Ϣ����
	 */
	public static final int INSERT_INFORMATION_FAIL = 7;
	
	/**
	 * �����û���Ϣ����
	 */
	public static final int UPDATE_INFORMATION_FAIL = 8;
	
	/**
	 * �û��Ѿ�����
	 */
	public static final int USER_EXISTED = 9; 
	
	/**
	 * û�е�¼
	 */
	public static final int NOT_LOGIN = 10;
	
	/**
	 * û��ʹ�ô�ϵͳ��Ȩ��
	 */
	public static final int NOT_PERMIT = 11;
	
	/**
	 * ��Ҫʹ��ָ����GROUP��USERID��¼
	 */
	public static final int NEED_GROUP_USERID_LOGON = 12;
	
	/**
	 * �û�IDʧЧ��������������
	 */
	public static final int LIFE_INACTIVE = 13;
	
	public static final int DUPLICATED_LOGON = 14;
	
	/**
	 * ϵͳ����
	 */
	public static final int SYSTEM_ERROR = 99;

	/**
	 * �������<br>
	 * <pre>
	 *	0 - �޴���
	 *	1 - �û���������
	 *	2 - �������
	 *  3 - �û���Ϊ��
	 *  4 - ����Ϊ�� 
	 *  5 - ���Ŀ���ʧ�ܣ��¾ɿ��ƥ��
	 *  6 - ϵͳ����
	 * </pre>
	 */
	private int errCode = 0;
	
	/**
	 * ������Ϣ
	 */
	private String errMessage = null;
	/**
	 * ���캯��
	 * @param ncode
	 * @param strMsg
	 */
	public UserException(int ncode, String strMsg) {
		super(strMsg);
		this.errCode = ncode;
		this.errMessage = strMsg;
	}
	
	

	/**
	 * @return �������
	 */
	public int getErrCode() {
		return errCode;
	}

	/**
	 * @return ������Ϣ
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

/*
 * @(#)QueryException.java  2003-4-22
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

/**
 * <P>��ѯʱ��������ʱ�׳����쳣 </P>
 * 
 * @version 0.1
 * @author navy
 */
public class QueryException extends Exception {

	/**
	 * @param arg0 �쳣��Ϣ
	 */
	public QueryException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0 �쳣
	 */
	public QueryException(Throwable arg0) {
		
		super(arg0.getLocalizedMessage());
	}

	/**
	 * @param arg0 �쳣��Ϣ
	 * @param arg1 �쳣
	 */
	public QueryException(String arg0, Throwable arg1) {
		super(arg0 + System.getProperty("line.separator") + arg1.getLocalizedMessage());
	}

}

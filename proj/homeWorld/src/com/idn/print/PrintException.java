/*
 * @(#)PrintException.java  2003-9-3
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.print;

/**
 * <P>��ӡʱ�����쳣</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PrintException extends Exception {

	/**
	 * �����쳣
	 * @param str �쳣��Ϣ
	 */
	public PrintException(String str) {
		super(str);
	}

}

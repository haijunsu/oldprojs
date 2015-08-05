/*
 * @(#)PrintException.java  2003-9-3
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.print;

/**
 * <P>打印时发生异常</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PrintException extends Exception {

	/**
	 * 构造异常
	 * @param str 异常信息
	 */
	public PrintException(String str) {
		super(str);
	}

}

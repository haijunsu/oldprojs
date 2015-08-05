/*
 * @(#)CodeNotFoundException.java  2004-1-14
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package com.idn.property;

/**
 * <P>代码查询异常</P>
 * 
 * @version 0.1
 * @author navy
 */
public class CodeNotFoundException extends Exception {

	/**
	 * 异常信息构造
	 * @param s
	 */
	public CodeNotFoundException(String s) {
		super(s);
	}

}

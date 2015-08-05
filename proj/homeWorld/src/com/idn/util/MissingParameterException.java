/*
 * @(#)MissingParameterException.java  2003-4-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.util;

/**
 * <P>查找参数异常 </P>
 * 
 * @version 0.1
 * @author xling
 */
public class MissingParameterException extends Exception {

	/**
	 * @param message
	 */
	public MissingParameterException(String message) {
		super(message);
	}
}

/**
 * @(#)ExpiredPasswordException.java  2003-1-28
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.user;

import org.apache.struts.util.ModuleException;

/**
 * <P>口令过期异常处理</P>
 * 
 * @version 0.1
 * @author 苏海军
 */
public class ExpiredPasswordException extends ModuleException {
	
	public ExpiredPasswordException(String username) {
		super("error.password.expired", username);
	}

}

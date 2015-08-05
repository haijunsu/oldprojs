/*
 * @(#)ServletTools.java  2003-4-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.util;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

/**
 * <P>Servlet的一些工具 </P>
 * 
 * @version 0.1
 * @author 苏海军
 */
public class ServletTools {

	/**
	 * 私有构造函数，该类不可以被实例化
	 */
	private ServletTools() {
	}

	/**
	 * 获取HttpServletRequest参数值
	 * @param req  HttpServletRequest
	 * @param param 要获取参数的名字
	 * @param isRequired 是不是为必需参数
	 * @param defaultValue 如果参数为可选参数，如果找不到，则返回此值
	 * @return 参数值
	 * @throws MissingParameterException 当参数为必需，但找不到时，抛出此错误
	 */
	public static String getParameter(
		HttpServletRequest req,
		String param,
		boolean isRequired,
		String defaultValue)
		throws MissingParameterException {
		String strParam = req.getParameter(param);
		if (strParam == null && isRequired) {
			throw new MissingParameterException("参数“" + param + "”没有找到！");
		}
		if (strParam == null)
			return defaultValue;
		return strParam;
	}

	/**
	 * 获取HttpServletRequest参数值
	 * @param req  HttpServletRequest
	 * @param param 要获取参数的名字
	 * @param isRequired 是不是为必需参数
	 * @param defaultValue 如果参数为可选参数，如果找不到，则返回此值
	 * @return 参数值
	 * @throws MissingParameterException 当参数为必需，但找不到时，抛出此错误
	 */
	public static String[] getParameterValues(
		HttpServletRequest req,
		String param,
		boolean isRequired,
		String[] defaultValue)
		throws MissingParameterException {
		String[] strParam = req.getParameterValues(param);
		if (strParam == null && isRequired) {
			throw new MissingParameterException("参数“" + param + "”没有找到！");
		}
		if (strParam == null)
			return defaultValue;
		return strParam;
	}

	/**
	 * 将HttpServletRequest中的参数放到哈希表内
	 * @param req HttpServletRequest
	 * @return 参数哈希表
	 * @throws MissingParameterException
	 */
	public static Hashtable RequestParametersToHashtable(HttpServletRequest req)
		throws MissingParameterException {
		Hashtable htParams = new Hashtable();
		Enumeration enumParamNames = req.getParameterNames();

		String strName = null;
		while (enumParamNames.hasMoreElements()) {
			strName = (String) enumParamNames.nextElement();
			htParams.put(
				strName,
				ServletTools.getParameter(req, strName, true, ""));
		}
		return htParams;
	}

}

/*
 * @(#)Request.java  2003-8-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.upload;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * <P>上载系统Request工具</P>
 * 
 * @version 0.1
 * @author navy
 */
public class Request {
	
	/**
	 * 参数集合
	 */
	private Hashtable m_parameters;
	
	/**
	 * 参数数目
	 */
	private int m_counter;
	
	/**
	 * 实例化
	 *
	 */
	Request() {
		m_parameters = new Hashtable();
		m_counter = 0;
	}
	
	/**
	 * 将参数放入Hashtable
	 * @param strName 参数名称
	 * @param strValue 参数值
	 */
	protected void putParameter(String strName, String strValue) {
		if (strName == null)
			throw new IllegalArgumentException("参数名称不能为空");
		if (m_parameters.containsKey(strName)) {
			Hashtable hashtable = (Hashtable) m_parameters.get(strName);
			hashtable.put(new Integer(hashtable.size()), strValue);
		} else {
			Hashtable hashtable1 = new Hashtable();
			hashtable1.put(new Integer(0), strValue);
			m_parameters.put(strName, hashtable1);
			m_counter++;
		}
	}
	
	/**
	 * 获取指定的参数值
	 * @param str 参数名称
	 * @return 参数值
	 */
	public String getParameter(String str) {
		if (str == null)
			throw new IllegalArgumentException("Form's name is invalid or does not exist (1305).");
		Hashtable hashtable = (Hashtable) m_parameters.get(str);
		if (hashtable == null)
			return null;
		else
			return (String) hashtable.get(new Integer(0));
	}
	
	/**
	 * 获取集合中所有参数的名称
	 * @return 所有参数的名称
	 */
	public Enumeration getParameterNames() {
		return m_parameters.keys();
	}
	
	/**
	 * 获取集合中数组参数的值
	 * @param str 数组参数的名称
	 * @return 参数值数组
	 */
	public String[] getParameterValues(String str) {
		if (str == null)
			throw new IllegalArgumentException("Form's name is invalid or does not exist (1305).");
		Hashtable hashtable = (Hashtable) m_parameters.get(str);
		if (hashtable == null)
			return null;
		String as[] = new String[hashtable.size()];
		for (int i = 0; i < hashtable.size(); i++)
			as[i] = (String) hashtable.get(new Integer(i));

		return as;
	}

}

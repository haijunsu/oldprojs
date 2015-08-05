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
 * <P>����ϵͳRequest����</P>
 * 
 * @version 0.1
 * @author navy
 */
public class Request {
	
	/**
	 * ��������
	 */
	private Hashtable m_parameters;
	
	/**
	 * ������Ŀ
	 */
	private int m_counter;
	
	/**
	 * ʵ����
	 *
	 */
	Request() {
		m_parameters = new Hashtable();
		m_counter = 0;
	}
	
	/**
	 * ����������Hashtable
	 * @param strName ��������
	 * @param strValue ����ֵ
	 */
	protected void putParameter(String strName, String strValue) {
		if (strName == null)
			throw new IllegalArgumentException("�������Ʋ���Ϊ��");
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
	 * ��ȡָ���Ĳ���ֵ
	 * @param str ��������
	 * @return ����ֵ
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
	 * ��ȡ���������в���������
	 * @return ���в���������
	 */
	public Enumeration getParameterNames() {
		return m_parameters.keys();
	}
	
	/**
	 * ��ȡ���������������ֵ
	 * @param str �������������
	 * @return ����ֵ����
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

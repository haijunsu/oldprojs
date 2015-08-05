/*
 * @(#)CodesManager.java  2003-4-7
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import org.apache.struts.util.LabelValueBean;

import com.idn.util.CommonTools;

/**
 * <P>��������</P>
 * 
 * @version 0.1
 * @author �պ���
 */

/**
 * <p>�����ˣ� navy<br>
 * ����ʱ�䣺2004-1-14<br>
 * �������ݣ�<br>
 *  <UL>
 *  	<LI>���CodeNotFoundException 
 *  </UL>
 */
public class CodesManager {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CodesManager.class);

	/**
	 * Constructor for CodesManager.
	 */
	public CodesManager() {
		super();
	}

	/**
	 * ��ѯָ��key��ֵ��keyΪcclass.ccode��ʽ��
	 * @param key
	 * @return String
	 */
	public static String getCodeValue(String key) throws CodeNotFoundException {
		String[] keys = CommonTools.stringToArray(key, ".");
		if (keys.length != 2)
			throw new CodeNotFoundException("Code's key {" + key + "} format error");
		Properties prop =
			(Properties) InitCodesProperties.getProperties().get(keys[0]);
		return prop.getProperty(keys[1]);
	}

	/**
	 * ��ѯָ���Ĵ���ֵ
	 * @param cclass �������
	 * @param ccode cclass�����еĴ���
	 * @return ����ֵ
	 */
	public static String getCodeValue(String cclass, String ccode) throws CodeNotFoundException {
		return getCodeValue(cclass.trim() + "." + ccode.trim());
	}

	/**
	 * ��ȡĳ������д���ֵ��keyΪcclass��ֵ
	 * @param key
	 * @return String[]
	 */
	public static String[] getCodeMembersKey(String key) throws CodeNotFoundException {
		Object o = InitCodesProperties.getProperties().get(key);
		if (o == null) {
			throw new CodeNotFoundException("Code's key {" + key + "} member not found!");
		}
		Properties prop = (Properties) o;
		Enumeration enum = prop.keys();
		ArrayList alkeys = new ArrayList();
		while (enum.hasMoreElements()) {
			alkeys.add(enum.nextElement());
		}
		String[] keys = (String[]) alkeys.toArray(new String[alkeys.size()]);
		Arrays.sort(keys);
		return keys;
	}

	/**
	 * ��ȡĳ������д���ֵ������������keyΪcclass��ֵ
	 * @param key
	 * @return String[]
	 */
	public static String[] getCodeMembersValue(String key) throws CodeNotFoundException {
		Properties prop = (Properties) InitCodesProperties.getProperties().get(key);
		String[] keys = getCodeMembersKey(key);
		String[] values = new String[keys.length];
		for (int i = 0; i < keys.length; i++) {
			values[i] = (String) prop.getProperty(keys[i]);
		}
		return values;
	}

	/**
	 * ������ת����FORMBEAN�е�multibox��select��ʾ����Ҫ�ļ��ϡ�
	 * ���Ҫ����00�����80���ϵ����ݣ�ʹ��"system"Ϊkey
	 * 
	 * @param key ����ֵ
	 * @return ת����ļ���
	 */
	public static Collection getCodeLabelValueBeanCollection(String key) throws CodeNotFoundException {
		return getCodeLabelValueBeanCollection(key, false);
	}

	/**
	 * ������ת����FORMBEAN�е�multibox��select��ʾ����Ҫ�ļ��ϡ�
	 * ���Ҫ����00�����80���ϵ����ݣ�ʹ��"system"Ϊkey
	 * 
	 * @param key ����ֵ
	 * @param isWithCode �Ƿ񽫴�����ڴ���ֵǰ�棬�ԡ����� - ����ֵ����ʽ��ʾ
	 * @return ת����ļ���
	 */
	public static Collection getCodeLabelValueBeanCollection(
		String key,
		boolean isWithCode) throws CodeNotFoundException {
		boolean isSystem = false;
		if (key.equals("system")) {
			isSystem = true;
			key = "00";
		}
		String[] strLabel = getCodeMembersValue(key);
		String[] strKeys = getCodeMembersKey(key);
		Vector entries = new Vector(strKeys.length);
		for (int i = 0; i < strKeys.length; i++) {
			if (isSystem) {
				if (Integer.parseInt(strKeys[i]) > 80) {
					if (isWithCode)
						entries.add(
							new LabelValueBean(
								strKeys[i] + "-" + strLabel[i],
								strKeys[i]));
					else
						entries.add(
							new LabelValueBean(strLabel[i], strKeys[i]));
				}
			} else {
				if (Integer.parseInt(key) == 0) {
					if (Integer.parseInt(strKeys[i]) <= 80) {
						if (isWithCode)
							entries.add(
								new LabelValueBean(
									strKeys[i] + "-" + strLabel[i],
									strKeys[i]));
						else
							entries.add(
								new LabelValueBean(strLabel[i], strKeys[i]));

					}
				} else {
					if (isWithCode)
						entries.add(
							new LabelValueBean(
								strKeys[i] + "-" + strLabel[i],
								strKeys[i]));
					else
						entries.add(
							new LabelValueBean(strLabel[i], strKeys[i]));

				}
			}
		}
		return entries;
	}
	
	/**
	 * ����޸��˴�������Ե��ô˺��������ݿ����޸ĺ�Ĵ����
	 */
	public static void retrieveCodes() {
		InitCodesProperties.loadCodes();
	}
}

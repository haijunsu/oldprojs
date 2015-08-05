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
 * <P>代码表管理</P>
 * 
 * @version 0.1
 * @author 苏海军
 */

/**
 * <p>更改人： navy<br>
 * 更改时间：2004-1-14<br>
 * 更改内容：<br>
 *  <UL>
 *  	<LI>添加CodeNotFoundException 
 *  </UL>
 */
public class CodesManager {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
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
	 * 查询指定key的值，key为cclass.ccode格式。
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
	 * 查询指定的代码值
	 * @param cclass 代码分类
	 * @param ccode cclass分类中的代码
	 * @return 代码值
	 */
	public static String getCodeValue(String cclass, String ccode) throws CodeNotFoundException {
		return getCodeValue(cclass.trim() + "." + ccode.trim());
	}

	/**
	 * 获取某类的所有代码值，key为cclass的值
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
	 * 获取某类的所有代码值的中文描术，key为cclass的值
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
	 * 将代码转换成FORMBEAN中的multibox和select显示所需要的集合。
	 * 如果要访问00代码的80以上的内容，使用"system"为key
	 * 
	 * @param key 代码值
	 * @return 转换后的集合
	 */
	public static Collection getCodeLabelValueBeanCollection(String key) throws CodeNotFoundException {
		return getCodeLabelValueBeanCollection(key, false);
	}

	/**
	 * 将代码转换成FORMBEAN中的multibox和select显示所需要的集合。
	 * 如果要访问00代码的80以上的内容，使用"system"为key
	 * 
	 * @param key 代码值
	 * @param isWithCode 是否将代码加在代码值前面，以“代码 - 代码值”格式显示
	 * @return 转换后的集合
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
	 * 如果修改了代码表，可以调用此函数从数据库获得修改后的代码表
	 */
	public static void retrieveCodes() {
		InitCodesProperties.loadCodes();
	}
}

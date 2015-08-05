/*
 * @(#)DecimalTools.java  2003-5-2
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DecimalFormat;

/**
 * <P>��ʽ������</P>
 * 
 * @version 0.1
 * @author xling
 */
public class DecimalTools {
	/**
	 * Commons Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(DecimalTools.class);
	
	/**
	 * 
	 */
	private DecimalTools() {

	}

	/**
	 * �������ַ�����ָ���ĸ�ʽ��ʾ
	 * @param str �����ַ���
	 * @param pattern ָ���ĸ�ʽ���硰0.00������####.##��
	 * @return ��ʽ������ִ�
	 */
	public static String format(String str, String pattern) {
		double d = 0.0;
		try {
			d = Double.parseDouble(str);
		} catch (Exception e) {
			d = 0.0;

			logger.warn(
				"format(String str = "
					+ str
					+ ", String pattern = "
					+ pattern
					+ ") - ת�����ִ��󣡽�str����Ϊ0");
		}
		int nTemp = pattern.length() - pattern.indexOf(".");
		String strToDouble = "0.";
		for (int i=0; i<nTemp - 1; i++)
			strToDouble += "0";
		strToDouble += "1";
		double dTemp = Double.parseDouble(strToDouble);
		d += dTemp;
		return format(d, pattern);
	}

	/**
	 * �����������ְ�ָ���ĸ�ʽ��ʾ
	 * @param d ����������
	 * @param pattern ָ���ĸ�ʽ���硰0.00������####.##��
	 * @return ��ʽ������ִ�
	 */
	public static String format(double d, String pattern) {
		return new DecimalFormat(pattern).format(d);
	}

	/**
	 * �����������ְ�ָ���ĸ�ʽ��ʾ
	 * @param f ����������
	 * @param pattern ָ���ĸ�ʽ���硰0.00������####.##��
	 * @return ��ʽ������ִ�
	 */
	public static String format(float f, String pattern) {
		return new DecimalFormat(pattern).format(f);
	}

}

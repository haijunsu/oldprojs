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
 * <P>格式化数字</P>
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
	 * 将数字字符串按指定的格式显示
	 * @param str 数字字符串
	 * @param pattern 指定的格式，如“0.00”、“####.##”
	 * @return 格式后的数字串
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
					+ ") - 转换数字错误！将str设置为0");
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
	 * 将精度型数字按指定的格式显示
	 * @param d 精度型数字
	 * @param pattern 指定的格式，如“0.00”、“####.##”
	 * @return 格式后的数字串
	 */
	public static String format(double d, String pattern) {
		return new DecimalFormat(pattern).format(d);
	}

	/**
	 * 将浮点型数字按指定的格式显示
	 * @param f 浮点型数字
	 * @param pattern 指定的格式，如“0.00”、“####.##”
	 * @return 格式后的数字串
	 */
	public static String format(float f, String pattern) {
		return new DecimalFormat(pattern).format(f);
	}

}

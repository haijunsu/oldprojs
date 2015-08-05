/**
 * @(#)FormatDate.java  2003-1-10
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * 用于将15位的数字字符串格式化日期显示，并当指定年月日的数字、
 * 字符串字符转换为15位的数字，方便数据库的日期处理
 * <PRE>
 *	    G  Era designator  				Text  AD  
 *    	y  Year  						Year  1996; 96  
 *    	M  Month in year  				Month  July; Jul; 07  
 *    	w  Week in year  				Number  27  
 *    	W  Week in month  				Number  2  
 *    	D  Day in year  				Number  189  
 *    	d  Day in month  				Number  10  
 *    	F  Day of week in month  		Number  2  
 *    	E  Day in week  				Text  Tuesday; Tue  
 *    	a  Am/pm marker  				Text  PM  
 *    	H  Hour in day (0-23)  			Number  0  
 *    	k  Hour in day (1-24)  			Number  24  
 *    	K  Hour in am/pm (0-11)  		Number  0  
 *    	h  Hour in am/pm (1-12)  		Number  12  
 *    	m  Minute in hour  				Number  30  
 *    	s  Second in minute  			Number  55  
 *    	S  Millisecond  				Number  978  
 *    	z  Time zone  General time zone  Pacific Standard Time; PST; GMT-08:00 
 *    	Z  Time zone  RFC 822 time zone  -0800  
 *</PRE>
 * @version ver 0.2
 * @author name: 苏海军
 */

public class FormatDate {

	/**
	 * 将日期格式化为：yyyy年MM月dd日 H:mm:ss.S 24小时制
	 */
	public static final int LONG_YYYY_MM_DD_HMSS = 0;

	/**
	 * 将日期格式化为：yyyy-MM-dd H:mm:ss.S 24小时制
	 */
	public static final int SHORT_YYYY_MM_DD_HMSS = 1;

	/**
	 * 将日期格式化为：yyyy年M月d日 H:mm:ss.S 24小时制
	 */
	public static final int LONG_YYYY_M_D_HMSS = 2;

	/**
	 * 将日期格式化为：yyyy-M-d H:mm:ss.S 24小时制
	 */
	public static final int SHORT_YYYY_M_D_HMSS = 3;

	/**
	 * 将日期格式化为：yyyy年MM月dd日 H:mm:ss 24小时制
	 */
	public static final int LONG_YYYY_MM_DD_HMS = 4;

	/**
	 * 将日期格式化为：yyyy-MM-dd H:mm:ss 24小时制
	 */
	public static final int SHORT_YYYY_MM_DD_HMS = 5;

	/**
	 * 将日期格式化为：yyyy年M月d日 H:mm:ss 24小时制
	 */
	public static final int LONG_YYYY_M_D_HMS = 6;

	/**
	 * 将日期格式化为：yyyy-M-d H:mm:ss 24小时制
	 */
	public static final int SHORT_YYYY_M_D_HMS = 7;

	/**
	 * 将日期格式化为：yyyy年MM月dd日 H:mm 24小时制
	 */
	public static final int LONG_YYYY_MM_DD_H_M = 8;

	/**
	 * 将日期格式化为：yyyy-MM-dd H:mm 24小时制
	 */
	public static final int SHORT_YYYY_MM_DD_H_M = 9;

	/**
	 * 将日期格式化为：yyyy年M月d日 H:mm 24小时制
	 */
	public static final int LONG_YYYY_M_D_H_M = 10;

	/**
	 * 将日期格式化为：yyyy-M-d H:mm 24小时制
	 */
	public static final int SHORT_YYYY_M_D_H_M = 11;

	/**
	 * 将日期格式化为：yyyy年MM月dd日 H:mm:ss.S 12小时制
	 */
	public static final int LONG_YYYY_MM_DD_HMSSA = 12;

	/**
	 * 将日期格式化为：yyyy-MM-dd H:mm:ss.S 12小时制
	 */
	public static final int SHORT_YYYY_MM_DD_HMSSA = 13;

	/**
	 * 将日期格式化为：yyyy年M月d日 H:mm:ss.S 12小时制
	 */
	public static final int LONG_YYYY_M_D_HMSSA = 14;

	/**
	 * 将日期格式化为：yyyy-M-d H:mm:ss.S 12小时制
	 */
	public static final int SHORT_YYYY_M_D_HMSSA = 15;

	/**
	 * 将日期格式化为：yyyy年MM月dd日 H:mm:ss 12小时制
	 */
	public static final int LONG_YYYY_MM_DD_HMSA = 16;

	/**
	 * 将日期格式化为：yyyy-MM-dd H:mm:ss a 12小时制
	 */
	public static final int SHORT_YYYY_MM_DD_HMSA = 17;

	/**
	 * 将日期格式化为：yyyy年M月d日 H:mm:ss a 12小时制
	 */
	public static final int LONG_YYYY_M_D_HMSA = 18;

	/**
	 * 将日期格式化为：yyyy-M-d H:mm:ss a 12小时制
	 */
	public static final int SHORT_YYYY_M_D_HMSA = 19;

	/**
	 * 将日期格式化为：yyyy年MM月dd日 H:mm a 12小时制
	 */
	public static final int LONG_YYYY_MM_DD_HMA = 20;

	/**
	 * 将日期格式化为：yyyy-MM-dd H:mm a 12小时制
	 */
	public static final int SHORT_YYYY_MM_DD_HMA = 21;

	/**
	 * 将日期格式化为：yyyy年M月d日 H:mm a 12小时制
	 */
	public static final int LONG_YYYY_M_D_HMA = 22;

	/**
	 * 将日期格式化为：yyyy-M-d H:mm a 12小时制
	 */
	public static final int SHORT_YYYY_M_D_HMA = 23;

	/**
	 * 将日期格式化为：yyyy年MM月dd日
	 */
	public static final int LONG_YYYY_MM_DD = 24;

	/**
	 * 将日期格式化为：yyyy-MM-dd
	 */
	public static final int SHORT_YYYY_MM_DD = 25;

	/**
	 * 将日期格式化为：yyyy年M月d日
	 */
	public static final int LONG_YYYY_M_D = 26;

	/**
	 * 将日期格式化为：yyyy-M-d
	 */
	public static final int SHORT_YYYY_M_D = 27;

	/**
	 * 将日期格式化为：H:mm:ss.S 24小时制
	 */
	public static final int LONG_HMSS = 28;
	/**
	 * 将日期格式化为：H:mm:ss.S 12小时制
	 */
	public static final int SHORT_HMSSA = 29;

	/**
	 * 将日期格式化为：H:mm:ss 24小时制
	 */
	public static final int LONG_H_M_S = 30;

	/**
	 * 将日期格式化为：H:mm:ss a 12小时制
	 */
	public static final int SHORT_H_M_S_A = 31;

	/**
	 * 将日期格式化为：H:mm 24小时制
	 */
	public static final int LONG_H_M = 32;

	/**
	 * 将日期格式化为：h:mm a 12小时制
	 */
	public static final int SHORT_H_M = 33;

	private static int m_nFormat = 0;
	private static String m_strFormat = "yyyy'年'MM'月'dd'日' H:mm:ss.S";

	/**
	 * 构造函数
	 */
	public FormatDate() {
		m_nFormat = 0;
		String m_strFormat = "yyyy'年'MM'月'dd'日' H:mm:ss.S";
	}

	/**
	 * 构造函数
	 * @param intFormat: 初始化format类型
	 */
	public FormatDate(int intFormat) {
		m_nFormat = intFormat;
	}

	/**
	 * 构造函数
	 * @param intFormat: 初始化format类型
	 */
	public FormatDate(String strFormat) {
		m_strFormat = strFormat;
	}

	/**
	 * 将日期格式为m_setFormat指定的格式
	 * @param date: 要格式化的日期 
	 * @return String 格式化后的日期
	 */
	public static String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(m_strFormat);
		return sdf.format(date);
	}

	/**
	 * 将指定的日期格式为指定的显示格式
	 * @param date: 要格式化的日期 
	 * @param intFormat: 格式化日期的类型 
	 * @return 格式化后的日期
	 */
	public static String format(Date date, int intFormat) {
		String strFormat;
		switch (intFormat) {
			case LONG_YYYY_MM_DD_HMSS :
				strFormat = "yyyy'年'MM'月'dd'日' H:mm:ss.S";
				break;
			case SHORT_YYYY_MM_DD_HMSS :
				strFormat = "yyyy'-'MM'-'dd H:mm:ss.S";
				break;
			case LONG_YYYY_M_D_HMSS :
				strFormat = "yyyy'年'M'月'd'日' H:mm:ss.S";
				break;
			case SHORT_YYYY_M_D_HMSS :
				strFormat = "yyyy'-'M'-'d H:mm:ss.S";
				break;
			case LONG_YYYY_MM_DD_HMS :
				strFormat = "yyyy'年'MM'月'dd'日' H:mm:ss";
				break;
			case SHORT_YYYY_MM_DD_HMS :
				strFormat = "yyyy'-'MM'-'dd H:mm:ss";
				break;
			case LONG_YYYY_M_D_HMS :
				strFormat = "yyyy'年'M'月'd'日' H:mm:ss";
				break;
			case SHORT_YYYY_M_D_HMS :
				strFormat = "yyyy'-'M'-'d H:mm:ss";
				break;
			case LONG_YYYY_MM_DD_H_M :
				strFormat = "yyyy'年'MM'月'dd'日' H:mm";
				break;
			case SHORT_YYYY_MM_DD_H_M :
				strFormat = "yyyy'-'MM'-'dd H:mm";
				break;
			case LONG_YYYY_M_D_H_M :
				strFormat = "yyyy'年'M'月'd'日' H:mm";
				break;
			case SHORT_YYYY_M_D_H_M :
				strFormat = "yyyy'-'M'-'d H:mm";
				break;
			case LONG_YYYY_MM_DD_HMSSA :
				strFormat = "yyyy'年'MM'月'dd'日' K:mm:ss.S a";
				break;
			case SHORT_YYYY_MM_DD_HMSSA :
				strFormat = "yyyy'-'MM'-'dd K:mm:ss.S a";
				break;
			case LONG_YYYY_M_D_HMSSA :
				strFormat = "yyyy'年'M'月'd'日' K:mm:ss.S a";
				break;
			case SHORT_YYYY_M_D_HMSSA :
				strFormat = "yyyy'-'M'-'d K:mm:ss.S a";
				break;
			case LONG_YYYY_MM_DD_HMSA :
				strFormat = "yyyy'年'MM'月'dd'日' K:mm:ss a";
				break;
			case SHORT_YYYY_MM_DD_HMSA :
				strFormat = "yyyy'-'MM'-'dd K:mm:ss a";
				break;
			case LONG_YYYY_M_D_HMSA :
				strFormat = "yyyy'年'M'月'd'日' K:mm:ss a";
				break;
			case SHORT_YYYY_M_D_HMSA :
				strFormat = "yyyy'-'M'-'d K:mm:ss a";
				break;
			case LONG_YYYY_MM_DD_HMA :
				strFormat = "yyyy'年'MM'月'dd'日' K:mm a";
				break;
			case SHORT_YYYY_MM_DD_HMA :
				strFormat = "yyyy'-'MM'-'dd K:mm a";
				break;
			case LONG_YYYY_M_D_HMA :
				strFormat = "yyyy'年'M'月'd'日' K:mm a";
				break;
			case SHORT_YYYY_M_D_HMA :
				strFormat = "yyyy'-'M'-'d K:mm a";
				break;
			case LONG_YYYY_MM_DD :
				strFormat = "yyyy'年'MM'月'dd'日'";
				break;
			case SHORT_YYYY_MM_DD :
				strFormat = "yyyy'-'MM'-'dd";
				break;
			case LONG_YYYY_M_D :
				strFormat = "yyyy'年'M'月'd'日'";
				break;
			case SHORT_YYYY_M_D :
				strFormat = "yyyy'-'M'-'d";
				break;
			case LONG_HMSS :
				strFormat = "H:mm:ss.S";
				break;
			case SHORT_HMSSA :
				strFormat = "K:mm:ss.S a";
				break;
			case LONG_H_M_S :
				strFormat = "H:mm:ss";
				break;
			case SHORT_H_M_S_A :
				strFormat = "K:mm:ss a";
				break;
			case LONG_H_M :
				strFormat = "H:mm";
				break;
			case SHORT_H_M :
				strFormat = "K:mm a";
				break;
			default :
				strFormat = "yyyy'年'MM'月'dd'日' H:mm:ss.S";
				break;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		return sdf.format(date);
	}

	/**
	 * 将指定的日期格式为指定的显示格式
	 * @param date: 要格式化的日期 
	 * @param strFormat: 格式化日期的类型 
	 * @return 格式化后的日期
	 */
	public static String format(Date date, String strFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		return sdf.format(date);
	}

	/**
	 * 将指定的日期格式为指定的显示格式
	 * @param strDate: 要格式化的日期，该是由数字组成的字符串
	 * @param strFormat: 格式化日期的类型 
	 * 
	 * @return String 格式化后的日期
	 * @exception NumberFormatException 如果给定的日期不是由数字组成的
	 * 字符串，将抛出NumberFormatException异常
	 */
	public static String format(String strDate, String strFormat)
		throws NumberFormatException {
		long longDate = Long.parseLong(strDate);
		Date date = new Date(longDate);
		return format(date, strFormat);
	}

	/**
	 * 将指定的日期格式为strFomat的显示格式
	 * @param strDate: 要格式化的日期，该是由数字组成的字符串
	 * @return String 格式化后的日期
	 * @exception NumberFormatException 如果给定的日期不是由数字组成的
	 * 字符串，将抛出NumberFormatException异常
	 */
	public static String format(String strDate) throws NumberFormatException {
		long longDate = Long.parseLong(strDate);
		Date date = new Date(longDate);
		return format(date);
	}

	/**
	 * 将指定的日期格式为strFomat的显示格式
	 * @param strDate: 要格式化的日期，该是由数字组成的字符串
	 * @param intFormat: 格式化日期的类型 
	 * @return String 格式化后的日期
	 * @exception NumberFormatException 如果给定的日期不是由数字组成的
	 * 字符串，将抛出NumberFormatException异常
	 */
	public static String format(String strDate, int intFormat)
		throws NumberFormatException {
		long longDate = Long.parseLong(strDate);
		Date date = new Date(longDate);
		return format(date, intFormat);
	}

	/**
	 * 将指定的日期格式为指定的显示格式
	 * @param llDate: 要格式化的日期，该是由数字组成
	 * @param strFormat: 格式化日期的类型 
	 * @return String 格式化后的日期
	 */
	public static String format(long llDate, String strFormat) {
		Date date = new Date(llDate);
		return format(date, strFormat);
	}

	/**
	 * 将指定的日期格式为strFormat指定的显示格式
	 * @param llDate: 要格式化的日期，该是由数字组成
	 * @return String 格式化后的日期
	 */
	public static String format(long llDate) {
		Date date = new Date(llDate);
		return format(date);
	}

	/**
	 * 将指定的日期格式为指定的显示格式
	 * @param llDate: 要格式化的日期，该是由数字组成
	 * @param intFormat: 格式化日期的类型 
	 * @return String 格式化后的日期
	 */
	public static String format(long llDate, int intFormat) {
		Date date = new Date(llDate);
		return format(date, intFormat);
	}

	public static int getIntFormat() {
		return m_nFormat;
	}

	public static void setIntFormat(int intFormat) {
		m_nFormat = intFormat;
	}

	public static String getStrFormat() {
		return m_strFormat;
	}

	public static void setStrFormat(String strFormat) {
		m_strFormat = strFormat;
	}

}
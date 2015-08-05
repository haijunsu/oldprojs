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
 * ���ڽ�15λ�������ַ�����ʽ��������ʾ������ָ�������յ����֡�
 * �ַ����ַ�ת��Ϊ15λ�����֣��������ݿ�����ڴ���
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
 * @author name: �պ���
 */

public class FormatDate {

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��MM��dd�� H:mm:ss.S 24Сʱ��
	 */
	public static final int LONG_YYYY_MM_DD_HMSS = 0;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-MM-dd H:mm:ss.S 24Сʱ��
	 */
	public static final int SHORT_YYYY_MM_DD_HMSS = 1;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��M��d�� H:mm:ss.S 24Сʱ��
	 */
	public static final int LONG_YYYY_M_D_HMSS = 2;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-M-d H:mm:ss.S 24Сʱ��
	 */
	public static final int SHORT_YYYY_M_D_HMSS = 3;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��MM��dd�� H:mm:ss 24Сʱ��
	 */
	public static final int LONG_YYYY_MM_DD_HMS = 4;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-MM-dd H:mm:ss 24Сʱ��
	 */
	public static final int SHORT_YYYY_MM_DD_HMS = 5;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��M��d�� H:mm:ss 24Сʱ��
	 */
	public static final int LONG_YYYY_M_D_HMS = 6;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-M-d H:mm:ss 24Сʱ��
	 */
	public static final int SHORT_YYYY_M_D_HMS = 7;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��MM��dd�� H:mm 24Сʱ��
	 */
	public static final int LONG_YYYY_MM_DD_H_M = 8;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-MM-dd H:mm 24Сʱ��
	 */
	public static final int SHORT_YYYY_MM_DD_H_M = 9;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��M��d�� H:mm 24Сʱ��
	 */
	public static final int LONG_YYYY_M_D_H_M = 10;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-M-d H:mm 24Сʱ��
	 */
	public static final int SHORT_YYYY_M_D_H_M = 11;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��MM��dd�� H:mm:ss.S 12Сʱ��
	 */
	public static final int LONG_YYYY_MM_DD_HMSSA = 12;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-MM-dd H:mm:ss.S 12Сʱ��
	 */
	public static final int SHORT_YYYY_MM_DD_HMSSA = 13;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��M��d�� H:mm:ss.S 12Сʱ��
	 */
	public static final int LONG_YYYY_M_D_HMSSA = 14;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-M-d H:mm:ss.S 12Сʱ��
	 */
	public static final int SHORT_YYYY_M_D_HMSSA = 15;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��MM��dd�� H:mm:ss 12Сʱ��
	 */
	public static final int LONG_YYYY_MM_DD_HMSA = 16;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-MM-dd H:mm:ss a 12Сʱ��
	 */
	public static final int SHORT_YYYY_MM_DD_HMSA = 17;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��M��d�� H:mm:ss a 12Сʱ��
	 */
	public static final int LONG_YYYY_M_D_HMSA = 18;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-M-d H:mm:ss a 12Сʱ��
	 */
	public static final int SHORT_YYYY_M_D_HMSA = 19;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��MM��dd�� H:mm a 12Сʱ��
	 */
	public static final int LONG_YYYY_MM_DD_HMA = 20;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-MM-dd H:mm a 12Сʱ��
	 */
	public static final int SHORT_YYYY_MM_DD_HMA = 21;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��M��d�� H:mm a 12Сʱ��
	 */
	public static final int LONG_YYYY_M_D_HMA = 22;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-M-d H:mm a 12Сʱ��
	 */
	public static final int SHORT_YYYY_M_D_HMA = 23;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��MM��dd��
	 */
	public static final int LONG_YYYY_MM_DD = 24;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-MM-dd
	 */
	public static final int SHORT_YYYY_MM_DD = 25;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy��M��d��
	 */
	public static final int LONG_YYYY_M_D = 26;

	/**
	 * �����ڸ�ʽ��Ϊ��yyyy-M-d
	 */
	public static final int SHORT_YYYY_M_D = 27;

	/**
	 * �����ڸ�ʽ��Ϊ��H:mm:ss.S 24Сʱ��
	 */
	public static final int LONG_HMSS = 28;
	/**
	 * �����ڸ�ʽ��Ϊ��H:mm:ss.S 12Сʱ��
	 */
	public static final int SHORT_HMSSA = 29;

	/**
	 * �����ڸ�ʽ��Ϊ��H:mm:ss 24Сʱ��
	 */
	public static final int LONG_H_M_S = 30;

	/**
	 * �����ڸ�ʽ��Ϊ��H:mm:ss a 12Сʱ��
	 */
	public static final int SHORT_H_M_S_A = 31;

	/**
	 * �����ڸ�ʽ��Ϊ��H:mm 24Сʱ��
	 */
	public static final int LONG_H_M = 32;

	/**
	 * �����ڸ�ʽ��Ϊ��h:mm a 12Сʱ��
	 */
	public static final int SHORT_H_M = 33;

	private static int m_nFormat = 0;
	private static String m_strFormat = "yyyy'��'MM'��'dd'��' H:mm:ss.S";

	/**
	 * ���캯��
	 */
	public FormatDate() {
		m_nFormat = 0;
		String m_strFormat = "yyyy'��'MM'��'dd'��' H:mm:ss.S";
	}

	/**
	 * ���캯��
	 * @param intFormat: ��ʼ��format����
	 */
	public FormatDate(int intFormat) {
		m_nFormat = intFormat;
	}

	/**
	 * ���캯��
	 * @param intFormat: ��ʼ��format����
	 */
	public FormatDate(String strFormat) {
		m_strFormat = strFormat;
	}

	/**
	 * �����ڸ�ʽΪm_setFormatָ���ĸ�ʽ
	 * @param date: Ҫ��ʽ�������� 
	 * @return String ��ʽ���������
	 */
	public static String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(m_strFormat);
		return sdf.format(date);
	}

	/**
	 * ��ָ�������ڸ�ʽΪָ������ʾ��ʽ
	 * @param date: Ҫ��ʽ�������� 
	 * @param intFormat: ��ʽ�����ڵ����� 
	 * @return ��ʽ���������
	 */
	public static String format(Date date, int intFormat) {
		String strFormat;
		switch (intFormat) {
			case LONG_YYYY_MM_DD_HMSS :
				strFormat = "yyyy'��'MM'��'dd'��' H:mm:ss.S";
				break;
			case SHORT_YYYY_MM_DD_HMSS :
				strFormat = "yyyy'-'MM'-'dd H:mm:ss.S";
				break;
			case LONG_YYYY_M_D_HMSS :
				strFormat = "yyyy'��'M'��'d'��' H:mm:ss.S";
				break;
			case SHORT_YYYY_M_D_HMSS :
				strFormat = "yyyy'-'M'-'d H:mm:ss.S";
				break;
			case LONG_YYYY_MM_DD_HMS :
				strFormat = "yyyy'��'MM'��'dd'��' H:mm:ss";
				break;
			case SHORT_YYYY_MM_DD_HMS :
				strFormat = "yyyy'-'MM'-'dd H:mm:ss";
				break;
			case LONG_YYYY_M_D_HMS :
				strFormat = "yyyy'��'M'��'d'��' H:mm:ss";
				break;
			case SHORT_YYYY_M_D_HMS :
				strFormat = "yyyy'-'M'-'d H:mm:ss";
				break;
			case LONG_YYYY_MM_DD_H_M :
				strFormat = "yyyy'��'MM'��'dd'��' H:mm";
				break;
			case SHORT_YYYY_MM_DD_H_M :
				strFormat = "yyyy'-'MM'-'dd H:mm";
				break;
			case LONG_YYYY_M_D_H_M :
				strFormat = "yyyy'��'M'��'d'��' H:mm";
				break;
			case SHORT_YYYY_M_D_H_M :
				strFormat = "yyyy'-'M'-'d H:mm";
				break;
			case LONG_YYYY_MM_DD_HMSSA :
				strFormat = "yyyy'��'MM'��'dd'��' K:mm:ss.S a";
				break;
			case SHORT_YYYY_MM_DD_HMSSA :
				strFormat = "yyyy'-'MM'-'dd K:mm:ss.S a";
				break;
			case LONG_YYYY_M_D_HMSSA :
				strFormat = "yyyy'��'M'��'d'��' K:mm:ss.S a";
				break;
			case SHORT_YYYY_M_D_HMSSA :
				strFormat = "yyyy'-'M'-'d K:mm:ss.S a";
				break;
			case LONG_YYYY_MM_DD_HMSA :
				strFormat = "yyyy'��'MM'��'dd'��' K:mm:ss a";
				break;
			case SHORT_YYYY_MM_DD_HMSA :
				strFormat = "yyyy'-'MM'-'dd K:mm:ss a";
				break;
			case LONG_YYYY_M_D_HMSA :
				strFormat = "yyyy'��'M'��'d'��' K:mm:ss a";
				break;
			case SHORT_YYYY_M_D_HMSA :
				strFormat = "yyyy'-'M'-'d K:mm:ss a";
				break;
			case LONG_YYYY_MM_DD_HMA :
				strFormat = "yyyy'��'MM'��'dd'��' K:mm a";
				break;
			case SHORT_YYYY_MM_DD_HMA :
				strFormat = "yyyy'-'MM'-'dd K:mm a";
				break;
			case LONG_YYYY_M_D_HMA :
				strFormat = "yyyy'��'M'��'d'��' K:mm a";
				break;
			case SHORT_YYYY_M_D_HMA :
				strFormat = "yyyy'-'M'-'d K:mm a";
				break;
			case LONG_YYYY_MM_DD :
				strFormat = "yyyy'��'MM'��'dd'��'";
				break;
			case SHORT_YYYY_MM_DD :
				strFormat = "yyyy'-'MM'-'dd";
				break;
			case LONG_YYYY_M_D :
				strFormat = "yyyy'��'M'��'d'��'";
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
				strFormat = "yyyy'��'MM'��'dd'��' H:mm:ss.S";
				break;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		return sdf.format(date);
	}

	/**
	 * ��ָ�������ڸ�ʽΪָ������ʾ��ʽ
	 * @param date: Ҫ��ʽ�������� 
	 * @param strFormat: ��ʽ�����ڵ����� 
	 * @return ��ʽ���������
	 */
	public static String format(Date date, String strFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		return sdf.format(date);
	}

	/**
	 * ��ָ�������ڸ�ʽΪָ������ʾ��ʽ
	 * @param strDate: Ҫ��ʽ�������ڣ�������������ɵ��ַ���
	 * @param strFormat: ��ʽ�����ڵ����� 
	 * 
	 * @return String ��ʽ���������
	 * @exception NumberFormatException ������������ڲ�����������ɵ�
	 * �ַ��������׳�NumberFormatException�쳣
	 */
	public static String format(String strDate, String strFormat)
		throws NumberFormatException {
		long longDate = Long.parseLong(strDate);
		Date date = new Date(longDate);
		return format(date, strFormat);
	}

	/**
	 * ��ָ�������ڸ�ʽΪstrFomat����ʾ��ʽ
	 * @param strDate: Ҫ��ʽ�������ڣ�������������ɵ��ַ���
	 * @return String ��ʽ���������
	 * @exception NumberFormatException ������������ڲ�����������ɵ�
	 * �ַ��������׳�NumberFormatException�쳣
	 */
	public static String format(String strDate) throws NumberFormatException {
		long longDate = Long.parseLong(strDate);
		Date date = new Date(longDate);
		return format(date);
	}

	/**
	 * ��ָ�������ڸ�ʽΪstrFomat����ʾ��ʽ
	 * @param strDate: Ҫ��ʽ�������ڣ�������������ɵ��ַ���
	 * @param intFormat: ��ʽ�����ڵ����� 
	 * @return String ��ʽ���������
	 * @exception NumberFormatException ������������ڲ�����������ɵ�
	 * �ַ��������׳�NumberFormatException�쳣
	 */
	public static String format(String strDate, int intFormat)
		throws NumberFormatException {
		long longDate = Long.parseLong(strDate);
		Date date = new Date(longDate);
		return format(date, intFormat);
	}

	/**
	 * ��ָ�������ڸ�ʽΪָ������ʾ��ʽ
	 * @param llDate: Ҫ��ʽ�������ڣ��������������
	 * @param strFormat: ��ʽ�����ڵ����� 
	 * @return String ��ʽ���������
	 */
	public static String format(long llDate, String strFormat) {
		Date date = new Date(llDate);
		return format(date, strFormat);
	}

	/**
	 * ��ָ�������ڸ�ʽΪstrFormatָ������ʾ��ʽ
	 * @param llDate: Ҫ��ʽ�������ڣ��������������
	 * @return String ��ʽ���������
	 */
	public static String format(long llDate) {
		Date date = new Date(llDate);
		return format(date);
	}

	/**
	 * ��ָ�������ڸ�ʽΪָ������ʾ��ʽ
	 * @param llDate: Ҫ��ʽ�������ڣ��������������
	 * @param intFormat: ��ʽ�����ڵ����� 
	 * @return String ��ʽ���������
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
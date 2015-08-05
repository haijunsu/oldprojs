/*
 * @(#)CommonTools.java  2003-1-14
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <P>���ù��߼��������Ա�ʵ����</P>
 * 
 * @version 0.3
 * @author �պ���
 */
public final class CommonTools {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommonTools.class);

	/**
	 * ���캯����ʵ����Ϊ����ʵ����
	 */
	private CommonTools() {

	}

	/**
	 * У���Ƿ���ʾ��MENU��userPriv���û���Ȩ�ޣ�menuPriv�ǲ˵���Ȩ�� 
	 * @param userPriv �û�Ȩ��
	 * @param menuPriv �˵�Ȩ��
	 * @return �Ƿ�Ӧ����ʾ
	 */
	public static boolean isMenuShow(String userPriv, String menuPriv) {
		return isMenuShow(userPriv, menuPriv, false);
	}

	/**
	 * У���Ƿ���ʾ��MENU��userPriv���û���Ȩ�ޣ�menuPriv�ǲ˵���Ȩ�� 
	 * @param userPriv �û�Ȩ��
	 * @param menuPriv �˵�Ȩ��
	 * @param isEncrypt �Ƿ��Ȩ�޽��м���
	 * @return �Ƿ�Ӧ����ʾ
	 */
	public static boolean isMenuShow(String userPriv, String menuPriv, boolean isEncrypt) {
		if ((parseRealPurview(userPriv, isEncrypt) & parseRealPurview(menuPriv, isEncrypt)) == 0)
			return false;
		return true;
	}

	/**
	 * ���û�����Ȩ�޻�ԭΪ����
	 * @param userPurview �û�Ȩ��
	 * @param itemPurview Ȩ����
	 * @return �û����е�Ȩ��
	 */
	public static String[] itemSelected(
		String userPurview,
		String[] itemPurview) {
		ArrayList al = new ArrayList();
		int nPurivew = parseRealPurview(userPurview);
		int nItemPurview = 0;
		for (int i = 0; i < itemPurview.length; i++) {
			nItemPurview =
				(int) java.lang.Math.pow(
					2,
					Integer.parseInt(itemPurview[i]) - 1);
			if ((nPurivew & nItemPurview) > 0) {
				al.add(itemPurview[i]);
			}
		}
		return (String[]) al.toArray(new String[al.size()]);
	}
	/**
	 * �����û��Ͳ˵�Ȩ��
	 * @param s_right �û�Ȩ��
	 * @return �û���˵���Ȩ��
	 */
	public static String parseRight(String[] s_right) {
		int i_Right, i_RightNum;
		String s_rtnRight;
		i_Right = 0;
		for (int i = 0; i < s_right.length; i++) {
			i_RightNum = Integer.parseInt(s_right[i]);
			if (i_RightNum != 0)
				i_Right = i_Right + (int) java.lang.Math.pow(2, i_RightNum - 1);
		}
		i_Right = mdRealPurview(i_Right);
		s_rtnRight = Integer.toString(i_Right);
		return s_rtnRight;
	}

	/**
	 * �����û��Ͳ˵�Ȩ��
	 * @param s_right �û�Ȩ��
	 * @return �û���˵���Ȩ��
	 */
	public static String parseRight(String s_right) {
		String[] s_rtnRight = new String[] { s_right };
		return parseRight(s_rtnRight);
	}

	/**
	 * �����û�Ȩ��
	 * @param purview �û�����ǰȨ��
	 */
	private static int mdRealPurview(int purview) {
		return mdRealPurview(purview, false);
	}

	/**
	 * �����û�Ȩ��
	 * @param purview �û�����ǰȨ��
	 * @param isEncrypt �Ƿ��Ȩ�޽��м���
	 */
	private static int mdRealPurview(int purview, boolean isEncrypt) {
		int iPurview = purview;
		if (isEncrypt)
			iPurview = purview * 7 + 177;
		return iPurview;
	}

	/**
	 * ��ԭ�û�/�˵�������Ȩ��
	 * @param purview Ȩ�޵��ַ��ͱ�ʾ
	 */
	private static int parseRealPurview(String purview) {
		return parseRealPurview(purview, false);
	}

	/**
	 * ��ԭ�û�/�˵�������Ȩ��
	 * @param purview Ȩ�޵��ַ��ͱ�ʾ
	 * @param isEncrypt �Ƿ��Ȩ�޽��м���
	 */
	private static int parseRealPurview(String purview, boolean isEncrypt) {
		int iPurview = Integer.parseInt(purview);
		if (isEncrypt)
			iPurview = (Integer.parseInt(purview) - 177) / 7;
		return iPurview;
	}

	/**
	 * ����ѡ��Ľ��Ϊ����ʵ�ʵ�ѡ��״̬������������ʾ
	 * @param strStatus ѡ��Ľ��
	 * @return �µ�״̬
	 */
	public static String parseStatus(String[] strStatus) {
		int iStatus, iStatusNum;
		iStatus = 0;
		for (int i = 0; i < strStatus.length; i++) {
			iStatusNum = Integer.parseInt(strStatus[i]);
			if (iStatusNum != 0)
				iStatus = iStatus + (int) java.lang.Math.pow(2, iStatusNum - 1);
		}
		return Integer.toString(iStatus);
	}

	/**
	 * ����ѡ��Ľ��Ϊ����ʵ�ʵ�ѡ��״̬������������ʾ
	 * @param strStatus ѡ��Ľ��
	 * @return �µ�״̬
	 */
	public static String parseStatus(String strStatus) {
		String[] str = new String[] { strStatus };
		return parseStatus(str);
	}

	/**
	 * ��λ�������м��㣬�ж�״̬�Ƿ�Ϊѡ���״̬���������ɶ�ѡ��
	 * @param originStatus ԭʼ״̬
	 * @param newStatus ��״̬
	 * @return �Ƿ�Ϊѡ��״̬
	 */
	public static boolean isSelected(String originStatus, String newStatus) {
		return isSelected(
			Integer.parseInt(originStatus),
			Integer.parseInt(newStatus));
	}

	/**
	 * ��λ�������м��㣬�ж�״̬�Ƿ�Ϊѡ���״̬���������ɶ�ѡ��
	 * @param originStatus ԭʼ״̬
	 * @param newStatus ��״̬
	 * @return �Ƿ�Ϊѡ��״̬
	 */
	public static boolean isSelected(String originStatus, int newStatus) {
		return isSelected(Integer.parseInt(originStatus), newStatus);
	}

	/**
	 * ��λ�������м��㣬�ж�״̬�Ƿ�Ϊѡ���״̬���������ɶ�ѡ��
	 * @param originStatus ԭʼ״̬
	 * @param newStatus ��״̬
	 * @return �Ƿ�Ϊѡ��״̬
	 */
	public static boolean isSelected(int originStatus, String newStatus) {
		return isSelected(originStatus, Integer.parseInt(newStatus));
	}

	/**
	 * ��λ�������м��㣬�ж�״̬�Ƿ�Ϊѡ���״̬���������ɶ�ѡ��
	 * @param originStatus ԭʼ״̬
	 * @param newStatus ��״̬
	 * @return �Ƿ�Ϊѡ��״̬
	 */
	public static boolean isSelected(int originStatus, int newStatus) {
		if ((originStatus & newStatus) == 0)
			return false;
		return true;
	}

	/**
	 * 	У��SQL����е������Ƿ�Ϸ�����ƴдSQL���ǰ�����ַ��������ж�
	 * 	<pre>���ڸ�ʽ��yyyy-mm-dd
	 * 	�����ţ�
	 * 	1: ok, ���ںϷ�
	 * -1�����ڸ�ʽ����
	 * -2�������ʾ����
	 * -3���·ݱ�ʾ����
	 * -4�����ڱ�ʾ����
	 * -5�����������Χ
	 * -6���·ݳ�����Χ
	 * -7�����ڳ�����Χ
	 * -8������ӦΪ30��
	 * -9������2�·ݵ�������29��
	 * -10��2�·ݵ�������28��
	 * </pre>
	 * @param date_str ҪУ�������
	 * @return ����У���Ĵ���
	 */

	public static int verifyDate(String date_str) {
		String num = "0123456789";
		String year, month, day;
		String temp_str;
		int i, i1, j, int_year, int_month, int_day;
		year = "";
		month = "";
		day = "";
		//У�����ڸ�ʽ�������������
		i = date_str.indexOf("-");
		if (i == -1) {
			return -1;
		} else {
			//ȡ�����
			year = date_str.substring(0, i);
			i1 = date_str.indexOf("-", i + 1);
			if (i1 == -1 || (i1 - i) == 1) {
				return -1;
			} else {
				//ȡ���·ݺ�����
				month = date_str.substring(i + 1, i1);
				day = date_str.substring(i1 + 1, date_str.length());
			}
		}
		//�ж�����ĳ��Ȳ�У���Ƿ�Ϊ����
		if (year.length() != 4) {
			return -2;
		} else {
			for (i = 0; i < 4; i++) {
				temp_str = year.substring(i, i + 1);
				i1 = num.indexOf(temp_str);
				if (i1 == -1) {
					return -2;
				}
			}
		}
		//�ж��·ݵĳ��Ȳ�У���Ƿ�Ϊ����
		if (month.length() > 2) {
			return -3;
		} else {
			for (i = 0; i < month.length(); i++) {
				temp_str = month.substring(i, i + 1);
				i1 = num.indexOf(temp_str);
				if (i1 == -1) {
					return -3;
				}
			}
		}
		//�ж����ڵĳ��Ȳ�У���Ƿ�Ϊ����
		if (day.length() > 2) {
			return -4;
		} else {
			for (i = 0; i < day.length(); i++) {
				temp_str = day.substring(i, i + 1);
				i1 = num.indexOf(temp_str);
				if (i1 == -1) {
					return -4;
				}
			}
		}
		//��������ת��Ϊ�������Է������
		int_year = Integer.valueOf(year).intValue();
		int_month = Integer.valueOf(month).intValue();
		int_day = Integer.valueOf(day).intValue();
		//�ж�����Ƿ񳬳���Χ����һλΪ��Ϊ0����Ч���Ϊ1753~9999
		if (int_year < 1753) {
			return -5;
		}
		//�ж��·��Ƿ񳬳���Χ
		if (int_month < 1 || int_month > 12) {
			return -6;
		}
		//�ж������Ƿ񳬳���Χ
		if (int_day < 1 || int_day > 31) {
			return -7;
		}
		//��һ����4��6��9��11�·ݽ������ڳ����ж�
		if (int_day == 31
			&& (int_month == 4
				|| int_month == 6
				|| int_month == 9
				|| int_month == 11)) {
			return -8;
		}
		//�ж��Ƿ�Ϊ������ȷ��2�·ݵ��������
		if (int_month == 2) {
			if (int_year % 4 == 0
				|| (int_year % 100 == 0 && int_year % 400 == 0)) {
				//����
				if (int_day > 29) {
					return -9;
				}
			} else {
				if (int_day > 28) {
					return -10;
				}
			}
		}
		//���ںϷ�
		return 1;
	}
	/**
	 * �滻SQL����еĵ����ţ���ƴдSQL���ǰ�����ַ������д���
	 * @param Ostr Ҫ���滻��SQL���
	 * @return ������SQL���
	 */
	public static String checkSQLQuote(String Ostr) {
		return stringReplace(Ostr, "'", "''");
	}
	/**
	 * �Զ����ų��򣬽��������ַ��������ַ�ʽ��1�����Զ�����ԭ���ĳ���
	 * @param strNum ����ת��Ϊ���ֵ��ַ���
	 * @return ���ŵĵ��ַ���
	 */
	public static String autoNum(String strNum) {
		String strZero = "";
		String rtnNum = "";
		int numLen = strNum.length();
		//����׼���Զ���ǰ���"0";
		if (numLen == 0) {
			return "0";
		} else {
			for (int i = 0; i < numLen; i++) {
				strZero = strZero + "0";
			}
			int numIntValue = Integer.valueOf(strNum).intValue();
			numIntValue = numIntValue + 1;
			rtnNum = strZero + Integer.toString(numIntValue);
			rtnNum =
				rtnNum.substring(rtnNum.length() - numLen, rtnNum.length());
			return rtnNum;
		}
	}

	/** 
	 * ���Ĵ������������������ʾ���ģ���ʹ�ô˷���ת������
	 * @param str �����������
	 * @return ����������
	 */
	public static String getGBKString(String str) {
		try {
			String str_temp = str;
			byte[] temp_t = str_temp.getBytes("ISO8859-1");
			str_temp = new String(temp_t);
			return str_temp;
		} catch (Exception e) {
			return str;
		}
	}

	/** 
	 * ���Ĵ������������������ʾ���ģ���ʹ�ô˷���ת������
	 * @param str ���������������
	 * @return ��������������
	 */
	public static String[] getGBKString(String[] str) {
		try {
			String[] str_temp = new String[str.length];

			for (int i = 0; i < str_temp.length; i++) {
				str_temp[i] = str[i];
				str_temp[i] = getGBKString(str_temp[i]);
			}

			for (int i = 0; i < str_temp.length; i++) {
				str[i] = str_temp[i];
			}
			return str;
		} catch (Exception e) {
			return str;
		}
	}
	/**
	 * ��չ�ַ����滻���ܣ����ַ����е�char�滻Ϊ�µ��ַ���
	 * @param str Ҫ���滻���ַ���
	 * @param c �滻ǰ��char
	 * @param s �滻����ַ�
	 * @return �滻��ɺ���ַ���
	 */
	public static String stringReplace(String str, char c, String s) {
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == c) {
				temp.append(str.substring(0, i)).append(s);
				str = str.substring(i + 1, str.length());
				i = 0;
			}
		}
		temp.append(str);
		return temp.toString();
	}
	/**
	 * ��չ�ַ����滻���ܣ����ַ����е�char�滻Ϊ�µ��ַ��� 
	 * @param fString Ҫ���滻���ַ���
	 * @param olds �滻ǰ���ַ� 
	 * @param news �滻����ַ�
	 * @return �滻��ɺ���ַ���
	 */
	//replace��������fString�е�olds������news
	public static String stringReplace(
		String fString,
		String olds,
		String news) {
		int Startwith = fString.indexOf(olds);
		while (Startwith != -1) {
			fString =
				fString.substring(0, Startwith)
					+ news
					+ fString.substring(Startwith + olds.length());
			Startwith = fString.indexOf(olds);
		}
		return fString;

	}

	/**
	 * ɾ���ַ��������еĿո񣬰����м�Ŀո�һ��ɾ��
	 * @param str Ҫɾ���ո���ַ���
	 * @return ɾ���ո����ַ���
	 * @since JDK 1.4
	 */
	public static String stringTrim(String str) {
		StringBuffer sb = new StringBuffer();
		log.debug(str);
		String[] strTmp = stringToArray(str);
		for (int i = 0; i < strTmp.length; i++) {
			sb.append(strTmp[i]);
		}
		return sb.toString();
	}

	/**
	 * ��ָ�����ַ���ת��Ϊ���飬�м���ָ�����ַ�delimiter�ָ�
	 * ���delimiterΪnull����delimiterΪ�ո�
	 *
	 * @param str ���ָ���ַ���
	 * @param delimiter �ָ���
	 *
	 * @return ���飬����Ԫ�ض��Ѿ�trim()
	 * @since JDK 1.4
	 */
	public static String[] stringToArray(String str, String delimiter) {
		String[] array = null;
		int elementCount = 0;

		String separator = " ";
		if (delimiter != null) {
			separator = delimiter;
		}

		//		if (separator.equals(" ")) {
		StringTokenizer tokens = new StringTokenizer(str.trim(), separator);
		elementCount = tokens.countTokens();

		if (elementCount != 0) {
			array = new String[elementCount];
			for (int i = 0; i < elementCount; i++) {
				array[i] = tokens.nextToken().trim();
			}
		}
		//		} else {
		//			str = " " + str + " ";
		//			array = str.split(separator);
		//			for (int i = 0; i < array.length; i++) {
		//				array[i] = array[i].trim();
		//			}
		//		}
		return array;
	}

	/**
	 * ��ָ�����ַ���ת��Ϊ���飬�м���ָ�����ַ��ո�ָ�
	 *
	 * @param str ���ָ���ַ���
	 *
	 * @return ���飬����Ԫ�ض��Ѿ�trim()
	 * @since JDK 1.4
	 */
	public static String[] stringToArray(String str) {
		return stringToArray(str, null);
	}

	/**
	 * ����ַ������Ƿ�������ģ����Ϊһ���ַ������ȷ�������ǲ�������
	 * @param chinese �������ַ�
	 * @return ������������򷵻���
	 */
	public static boolean isChinese(String chinese) {

		//		CharSet cnCharset = CharSet.forName("GB18030");
		//		CharsetEncoder encoder = cnCharset.newEncoder();
		//		CharBuffer charBuf = CharBuffer.wrap(chinese.toCharArray());
		//		ByteBuffer cnBytes = null;
		//		try {
		//			cnBytes = encoder.encode(charBuf);
		//		} catch (CharacterCodingException e) {
		//			log.debug(e);
		//			return false;
		//		}
		//		byte byCn[] = cnBytes.array();
		//		for (int i = 0, n = byCn.length; i < n; i++) {
		//			if (Byte.toString(byCn[i]).startsWith("-"))
		//				return true;
		//		}
		char[] ch = chinese.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if ((int) ch[i] > 255)
				return true;
		}
		return false;
	}

	/**
	 * ��ѯ��������ǰ���ڣ����ظ�ʽΪ��yyyy-MM-dd��
	 * @return ��ǰ����
	 */
	public static String getServerDate() {
		long llDate = System.currentTimeMillis();
		return FormatDate.format(llDate, FormatDate.SHORT_YYYY_MM_DD);
	}

	/**
	 * ��ѯ��������ǰ���ں�ʱ�䣬���ظ�ʽΪ��yyyy-MM-dd H:mm:ss.S��
	 * @return ��ǰ�����������ں�ʱ��
	 */
	public static String getServerDateTime() {
		long llDate = System.currentTimeMillis();
		return FormatDate.format(llDate, FormatDate.SHORT_YYYY_MM_DD_HMSS);
	}

	/**
	 * ��ѯ��������ǰʱ�䣬���ظ�ʽΪ��H:mm:ss.S��
	 * @return ��ǰ��������ʱ��
	 */
	public static String getServerTime() {
		long llDate = System.currentTimeMillis();
		return FormatDate.format(llDate, FormatDate.LONG_HMSS);
	}

	/**
	 * ���ַ�����URL����
	 * @param strUrl �������URL
	 * @return ������URL
	 */
	public static String encodeUrl(String strUrl) {
		int nTemp = 0;
		nTemp = strUrl.indexOf("?");
		if (nTemp > -1) {
			StringBuffer sb = new StringBuffer();
			String[] strTempArray;
			sb.append(strUrl.substring(0, nTemp + 1));
			strTempArray = CommonTools.stringToArray(strUrl, "&");
			strTempArray[0] = strTempArray[0].substring(nTemp + 1);
			for (int i = 0; i < strTempArray.length; i++) {
				nTemp = strTempArray[i].indexOf("=");
				strTempArray[i] =
					strTempArray[i].substring(0, nTemp + 1)
						+ URLEncoder.encode(strTempArray[i].substring(nTemp + 1));
				sb.append(strTempArray[i]);
				if (i < strTempArray.length - 1)
					sb.append("&");
			}
			strUrl = sb.toString();
		}

		return strUrl;
	}

	/**
	 * ����ָ���ַ����ĳ��ȣ�����ַ����������ģ������Ĳ��ְ������ַ�������
	 * @param str Ҫ���㳤�ȵ��ַ���
	 * @return �ַ����ĳ���
	 */
	public static int stringLength(String str) {
		int nTemp = 0;
		String strTempElement = "";
		for (int i = 0; i < str.trim().length(); i++) {
			strTempElement = str.substring(i, i + 1);
			if (CommonTools.isChinese(strTempElement)) {
				//				����ʵ�ʳ��ȣ����ְ������ֽ�������
				nTemp += 2;
			} else {
				nTemp++;
			}
		}
		return nTemp;
	}

}

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
 * <P>常用工具集，不可以被实例化</P>
 * 
 * @version 0.3
 * @author 苏海军
 */
public final class CommonTools {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommonTools.class);

	/**
	 * 构造函数，实际上为不可实例化
	 */
	private CommonTools() {

	}

	/**
	 * 校验是否显示该MENU，userPriv是用户的权限，menuPriv是菜单的权限 
	 * @param userPriv 用户权限
	 * @param menuPriv 菜单权限
	 * @return 是否应该显示
	 */
	public static boolean isMenuShow(String userPriv, String menuPriv) {
		return isMenuShow(userPriv, menuPriv, false);
	}

	/**
	 * 校验是否显示该MENU，userPriv是用户的权限，menuPriv是菜单的权限 
	 * @param userPriv 用户权限
	 * @param menuPriv 菜单权限
	 * @param isEncrypt 是否对权限进行加密
	 * @return 是否应该显示
	 */
	public static boolean isMenuShow(String userPriv, String menuPriv, boolean isEncrypt) {
		if ((parseRealPurview(userPriv, isEncrypt) & parseRealPurview(menuPriv, isEncrypt)) == 0)
			return false;
		return true;
	}

	/**
	 * 将用户已有权限还原为数组
	 * @param userPurview 用户权限
	 * @param itemPurview 权限项
	 * @return 用户已有的权限
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
	 * 计算用户和菜单权限
	 * @param s_right 用户权限
	 * @return 用户或菜单的权限
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
	 * 计算用户和菜单权限
	 * @param s_right 用户权限
	 * @return 用户或菜单的权限
	 */
	public static String parseRight(String s_right) {
		String[] s_rtnRight = new String[] { s_right };
		return parseRight(s_rtnRight);
	}

	/**
	 * 加密用户权限
	 * @param purview 用户加密前权限
	 */
	private static int mdRealPurview(int purview) {
		return mdRealPurview(purview, false);
	}

	/**
	 * 加密用户权限
	 * @param purview 用户加密前权限
	 * @param isEncrypt 是否对权限进行加密
	 */
	private static int mdRealPurview(int purview, boolean isEncrypt) {
		int iPurview = purview;
		if (isEncrypt)
			iPurview = purview * 7 + 177;
		return iPurview;
	}

	/**
	 * 还原用户/菜单真正的权限
	 * @param purview 权限的字符型表示
	 */
	private static int parseRealPurview(String purview) {
		return parseRealPurview(purview, false);
	}

	/**
	 * 还原用户/菜单真正的权限
	 * @param purview 权限的字符型表示
	 * @param isEncrypt 是否对权限进行加密
	 */
	private static int parseRealPurview(String purview, boolean isEncrypt) {
		int iPurview = Integer.parseInt(purview);
		if (isEncrypt)
			iPurview = (Integer.parseInt(purview) - 177) / 7;
		return iPurview;
	}

	/**
	 * 根据选择的结果为计算实际的选择状态，用整数来表示
	 * @param strStatus 选择的结果
	 * @return 新的状态
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
	 * 根据选择的结果为计算实际的选择状态，用整数来表示
	 * @param strStatus 选择的结果
	 * @return 新的状态
	 */
	public static String parseStatus(String strStatus) {
		String[] str = new String[] { strStatus };
		return parseStatus(str);
	}

	/**
	 * 按位与来进行计算，判断状态是否为选择的状态，用于生成多选框
	 * @param originStatus 原始状态
	 * @param newStatus 新状态
	 * @return 是否为选择状态
	 */
	public static boolean isSelected(String originStatus, String newStatus) {
		return isSelected(
			Integer.parseInt(originStatus),
			Integer.parseInt(newStatus));
	}

	/**
	 * 按位与来进行计算，判断状态是否为选择的状态，用于生成多选框
	 * @param originStatus 原始状态
	 * @param newStatus 新状态
	 * @return 是否为选择状态
	 */
	public static boolean isSelected(String originStatus, int newStatus) {
		return isSelected(Integer.parseInt(originStatus), newStatus);
	}

	/**
	 * 按位与来进行计算，判断状态是否为选择的状态，用于生成多选框
	 * @param originStatus 原始状态
	 * @param newStatus 新状态
	 * @return 是否为选择状态
	 */
	public static boolean isSelected(int originStatus, String newStatus) {
		return isSelected(originStatus, Integer.parseInt(newStatus));
	}

	/**
	 * 按位与来进行计算，判断状态是否为选择的状态，用于生成多选框
	 * @param originStatus 原始状态
	 * @param newStatus 新状态
	 * @return 是否为选择状态
	 */
	public static boolean isSelected(int originStatus, int newStatus) {
		if ((originStatus & newStatus) == 0)
			return false;
		return true;
	}

	/**
	 * 	校验SQL语句中的日期是否合法，在拼写SQL语句前，对字符串进行判断
	 * 	<pre>日期格式：yyyy-mm-dd
	 * 	错误编号：
	 * 	1: ok, 日期合法
	 * -1：日期格式错误
	 * -2：年代表示错误
	 * -3：月份表示错误
	 * -4：日期表示错误
	 * -5：年代超出范围
	 * -6：月份超出范围
	 * -7：日期超出范围
	 * -8：日期应为30天
	 * -9：闰年2月份的天数是29天
	 * -10：2月份的天数是28天
	 * </pre>
	 * @param date_str 要校验的日期
	 * @return 返回校验后的代码
	 */

	public static int verifyDate(String date_str) {
		String num = "0123456789";
		String year, month, day;
		String temp_str;
		int i, i1, j, int_year, int_month, int_day;
		year = "";
		month = "";
		day = "";
		//校验日期格式并分离出年月日
		i = date_str.indexOf("-");
		if (i == -1) {
			return -1;
		} else {
			//取出年代
			year = date_str.substring(0, i);
			i1 = date_str.indexOf("-", i + 1);
			if (i1 == -1 || (i1 - i) == 1) {
				return -1;
			} else {
				//取出月份和日期
				month = date_str.substring(i + 1, i1);
				day = date_str.substring(i1 + 1, date_str.length());
			}
		}
		//判断年代的长度并校验是否为数字
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
		//判断月份的长度并校验是否为数字
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
		//判断日期的长度并校验是否为数字
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
		//将年月日转换为数字型以方便计算
		int_year = Integer.valueOf(year).intValue();
		int_month = Integer.valueOf(month).intValue();
		int_day = Integer.valueOf(day).intValue();
		//判断年代是否超出范围，第一位为能为0，有效年代为1753~9999
		if (int_year < 1753) {
			return -5;
		}
		//判断月份是否超出范围
		if (int_month < 1 || int_month > 12) {
			return -6;
		}
		//判断日期是否超出范围
		if (int_day < 1 || int_day > 31) {
			return -7;
		}
		//进一步对4、6、9、11月份进行日期超界判断
		if (int_day == 31
			&& (int_month == 4
				|| int_month == 6
				|| int_month == 9
				|| int_month == 11)) {
			return -8;
		}
		//判断是否为闰年来确定2月份的最大天数
		if (int_month == 2) {
			if (int_year % 4 == 0
				|| (int_year % 100 == 0 && int_year % 400 == 0)) {
				//闰年
				if (int_day > 29) {
					return -9;
				}
			} else {
				if (int_day > 28) {
					return -10;
				}
			}
		}
		//日期合法
		return 1;
	}
	/**
	 * 替换SQL语句中的单引号，在拼写SQL语句前，对字符串进行处理
	 * @param Ostr 要被替换的SQL语句
	 * @return 处理后的SQL语句
	 */
	public static String checkSQLQuote(String Ostr) {
		return stringReplace(Ostr, "'", "''");
	}
	/**
	 * 自动发号程序，将送来的字符串按数字方式加1，并自动保持原来的长度
	 * @param strNum 可以转换为数字的字符串
	 * @return 发号的的字符串
	 */
	public static String autoNum(String strNum) {
		String strZero = "";
		String rtnNum = "";
		int numLen = strNum.length();
		//将来准备自动在前面加"0";
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
	 * 中文处理，如果程序不能正常显示中文，可使用此方法转换中文
	 * @param str 待处理的中文
	 * @return 处理后的中文
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
	 * 中文处理，如果程序不能正常显示中文，可使用此方法转换中文
	 * @param str 待处理的中文数组
	 * @return 处理后的中文数组
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
	 * 扩展字符串替换功能，将字符串中的char替换为新的字符串
	 * @param str 要被替换的字符串
	 * @param c 替换前的char
	 * @param s 替换后的字符
	 * @return 替换完成后的字符串
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
	 * 扩展字符串替换功能，将字符串中的char替换为新的字符串 
	 * @param fString 要被替换的字符串
	 * @param olds 替换前的字符 
	 * @param news 替换后的字符
	 * @return 替换完成后的字符串
	 */
	//replace函数，将fString中的olds串换成news
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
	 * 删除字符串中所有的空格，包括中间的空格一并删除
	 * @param str 要删除空格的字符串
	 * @return 删除空格后的字符串
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
	 * 将指定的字符串转换为数组，中间以指定的字符delimiter分隔
	 * 如果delimiter为null，则delimiter为空格。
	 *
	 * @param str 待分割的字符串
	 * @param delimiter 分隔符
	 *
	 * @return 数组，所有元素都已经trim()
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
	 * 将指定的字符串转换为数组，中间以指定的字符空格分隔
	 *
	 * @param str 待分割的字符串
	 *
	 * @return 数组，所有元素都已经trim()
	 * @since JDK 1.4
	 */
	public static String[] stringToArray(String str) {
		return stringToArray(str, null);
	}

	/**
	 * 检查字符串中是否包含中文，如果为一个字符，则可确定此字是不是中文
	 * @param chinese 待检查的字符
	 * @return 如果包含中文则返回真
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
	 * 查询服务器当前日期，返回格式为“yyyy-MM-dd”
	 * @return 当前日期
	 */
	public static String getServerDate() {
		long llDate = System.currentTimeMillis();
		return FormatDate.format(llDate, FormatDate.SHORT_YYYY_MM_DD);
	}

	/**
	 * 查询服务器当前日期和时间，返回格式为“yyyy-MM-dd H:mm:ss.S”
	 * @return 当前服务器的日期和时间
	 */
	public static String getServerDateTime() {
		long llDate = System.currentTimeMillis();
		return FormatDate.format(llDate, FormatDate.SHORT_YYYY_MM_DD_HMSS);
	}

	/**
	 * 查询服务器当前时间，返回格式为“H:mm:ss.S”
	 * @return 当前服务器的时间
	 */
	public static String getServerTime() {
		long llDate = System.currentTimeMillis();
		return FormatDate.format(llDate, FormatDate.LONG_HMSS);
	}

	/**
	 * 将字符进行URL编码
	 * @param strUrl 待编码的URL
	 * @return 编码后的URL
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
	 * 返回指定字符串的长度，如果字符串包含中文，则中文部分按两个字符来计算
	 * @param str 要计算长度的字符串
	 * @return 字符串的长度
	 */
	public static int stringLength(String str) {
		int nTemp = 0;
		String strTempElement = "";
		for (int i = 0; i < str.trim().length(); i++) {
			strTempElement = str.substring(i, i + 1);
			if (CommonTools.isChinese(strTempElement)) {
				//				计算实际长度，汉字按两个字节来计算
				nTemp += 2;
			} else {
				nTemp++;
			}
		}
		return nTemp;
	}

}

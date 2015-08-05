/*
 * @(#)DataUtil.java  2004-3-4
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package com.idn.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <P>用于日期计算</P>
 * 
 * @version 0.1
 * @author navy
 */
public class DateUtil {

	/**
	 * 把日期转换为长整型
	 * @param date
	 * @return 从1970年1月1日到指定日期的毫秒数
	 */
	public static long parseDateToLong(Date date) {
		return date.getTime();
	}

	/**
	 * 把指定的年月日转换为长整型
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return 从1970年1月1日到指定日期的毫秒数
	 */
	public static long parseDateToLong(int year, int month, int day) {
		return parseDateToLong(getDate(year, month, day));
	}

	/**
	 * 把指定的年月日转换为长整型
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return 从1970年1月1日到指定日期的毫秒数
	 */
	public static long parseDateToLong(String year, String month, String day) {
		return parseDateToLong(getDate(year, month, day));
	}

	/**
	 * 把指定的年月日时分转换为长整型
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 小时
	 * @param minute 分钟
	 * @return 从1970年1月1日到指定日期的毫秒数
	 */
	public static long parseDateToLong(
		int year,
		int month,
		int day,
		int hour,
		int minute) {
		return parseDateToLong(getDate(year, month, day, hour, minute));
	}

	/**
	 * 把指定的年月日时分转换为长整型
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 小时
	 * @param minute 分钟
	 * @return 从1970年1月1日到指定日期的毫秒数
	 */
	public static long parseDateToLong(
		String year,
		String month,
		String day,
		String hour,
		String minute) {
		return parseDateToLong(getDate(year, month, day, hour, minute));
	}

	/**
	 * 把指定的年月日时分秒转换为长整型
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 小时
	 * @param minute 分钟
	 * @param second 秒
	 * @return 从1970年1月1日到指定日期的毫秒数
	 */
	public static long parseDateToLong(
		int year,
		int month,
		int day,
		int hour,
		int minute,
		int second) {
		return parseDateToLong(getDate(year, month, day, hour, minute, second));
	}

	/**
	 * 把指定的年月日时分秒转换为长整型
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 小时
	 * @param minute 分钟
	 * @param second 秒
	 * @return 从1970年1月1日到指定日期的毫秒数
	 */
	public static long parseDateToLong(
		String year,
		String month,
		String day,
		String hour,
		String minute,
		String second) {
		return parseDateToLong(getDate(year, month, day, hour, minute, second));
	}

	/**
	 * 将长整型转换为日期型
	 * @param milliscond 从1970年1月1日到指定日期的毫秒数
	 * @return 转换后的日期
	 */
	public static Date getDate(long milliscond) {
		return new Date(milliscond);
	}

	/**
	 * 将长整型转换为日期型 GregorianCalendar
	 * @param milliscond 从1970年1月1日到指定日期的毫秒数
	 * @return 转换后的日期 GregorianCalendar
	 */
	public static GregorianCalendar getGregorianCalendar(long milliscond) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(getDate(milliscond));
		return gc;
	}

	/**
	 * 根据指定的年月日，得到Date日期型
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return 日期 Date型
	 */
	public static Date getDate(int year, int month, int day) {
		month -= 1;
		GregorianCalendar gc = new GregorianCalendar(year, month, day);
		return gc.getTime();
	}

	/**
	 * 根据指定的年月日，得到Date日期型
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return 日期 Date型
	 */
	public static Date getDate(String year, String month, String day) {
		return getDate(
			Integer.parseInt(year),
			Integer.parseInt(month),
			Integer.parseInt(day));
	}

	/**
	 * 根据指定的年月日时分，得到Date日期型
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 小时
	 * @param minute 分
	 * @return 日期 Date型
	 */
	public static Date getDate(
		int year,
		int month,
		int day,
		int hour,
		int minute) {
		month -= 1;
		GregorianCalendar gc =
			new GregorianCalendar(year, month, day, hour, minute);
		return gc.getTime();
	}

	/**
	 * 根据指定的年月日时分，得到Date日期型
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 小时
	 * @param minute 分
	 * @return 日期 Date型
	 */
	public static Date getDate(
		String year,
		String month,
		String day,
		String hour,
		String minute) {
		return getDate(
			Integer.parseInt(year),
			Integer.parseInt(month),
			Integer.parseInt(day),
			Integer.parseInt(hour),
			Integer.parseInt(minute));
	}

	/**
	 * 根据指定的年月日时分秒，得到Date日期型
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 小时
	 * @param minute 分
	 * @param second 秒
	 * @return 日期 Date型
	 */
	public static Date getDate(
		int year,
		int month,
		int day,
		int hour,
		int minute,
		int second) {
		month -= 1;
		GregorianCalendar gc =
			new GregorianCalendar(year, month, day, hour, minute, second);
		return gc.getTime();
	}

	/**
	 * 根据指定的年月日时分秒，得到Date日期型
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 小时
	 * @param minute 分
	 * @param second 秒
	 * @return 日期 Date型
	 */
	public static Date getDate(
		String year,
		String month,
		String day,
		String hour,
		String minute,
		String second) {
		return getDate(
			Integer.parseInt(year),
			Integer.parseInt(month),
			Integer.parseInt(day),
			Integer.parseInt(hour),
			Integer.parseInt(minute),
			Integer.parseInt(second));
	}

	/**
	 * 计算两个日期之间相差的毫秒数
	 * @param firstDate 第一个日期
	 * @param secondDate 第二个日期
	 * @return 相差的毫秒数
	 */
	public static long elapsedMilliseconds(Date firstDate, Date secondDate) {
		return secondDate.getTime() - firstDate.getTime();
	}

	/**
	 * 计算两个日期之间相差的秒数
	 * @param firstDate 第一个日期
	 * @param secondDate 第二个日期
	 * @return 相差的秒数
	 */
	public static long elapsedSeconds(Date firstDate, Date secondDate) {
		return elapsedMilliseconds(firstDate, secondDate) / 1000;
	}

	/**
	 * 计算两个日期之间相差多少分种
	 * @param firstDate 第一个日期
	 * @param secondDate 第二个日期
	 * @return 相差多少分钟
	 */
	public static long elapsedMinutes(Date firstDate, Date secondDate) {
		return elapsedSeconds(firstDate, secondDate) / 60;
	}

	/**
	 * 计算两个日期之间相差多少小时
	 * @param firstDate 第一个日期
	 * @param secondDate 第二个日期
	 * @return 相差多少小时
	 */
	public static long elapsedHours(Date firstDate, Date secondDate) {
		return elapsedMinutes(firstDate, secondDate) / 60;
	}

	/**
	 * 计算两个日期之间相差多少天，按自然天计算，
	 * 如2004年3月3日23：50与2004年3月4日0：00就会相差一天
	 * @param firstDate 第一个日期
	 * @param secondDate 第二个日期
	 * @return 相差多少天
	 */
	public static long elapsedDays(Date firstDate, Date secondDate) {
		return elapsedDays(firstDate, secondDate, false);
	}

	/**
	 * 计算两个日期之间相差多少天，如果精确到秒，则严格按每天24小时为一天计算，
	 * 如果相差不到24小时，则为同一天。如2004年3月3日23：50与2004年3月4日23：00
	 * 计算出的结果为0天
	 * @param firstDate 第一个日期
	 * @param secondDate 第二个日期
	 * @param isStrict 是否精确到秒
	 * @return 相差多少天
	 */
	public static long elapsedDays(
		Date firstDate,
		Date secondDate,
		boolean isStrict) {
		if (isStrict) {
			return elapsedHours(firstDate, secondDate) / 24;
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(firstDate);
		GregorianCalendar gcFirstDate =
			new GregorianCalendar(
				gc.get(Calendar.YEAR),
				gc.get(Calendar.MONTH),
				gc.get(Calendar.DAY_OF_MONTH));
		gc.setTime(secondDate);
		GregorianCalendar gcSecondDate =
			new GregorianCalendar(
				gc.get(Calendar.YEAR),
				gc.get(Calendar.MONTH),
				gc.get(Calendar.DAY_OF_MONTH));
		return elapsedHours(gcFirstDate.getTime(), gcSecondDate.getTime()) / 24;
	}

	/**
	 * 计算两个日期之间相差多少天，如果精确到秒，则严格按每天24小时为一天计算，
	 * 如果相差不到24小时，则为同一天。如2004年3月3日23：50与2004年3月4日23：00
	 * 计算出的结果为0天
	 * @param firstDate 第一个日期
	 * @param secondDate 第二个日期
	 * @param isStrict 是否精确到秒
	 * @return 相差多少天
	 */
	public static int elapsedMonths(Date firstDate, Date secondDate) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(firstDate);
		int nFirstYear = gc.get(Calendar.YEAR);
		int nFirstMonth = gc.get(Calendar.MONTH);
		gc.setTime(secondDate);
		int nSecondYear = gc.get(Calendar.YEAR);
		int nSecondMonth = gc.get(Calendar.MONTH);
		return (nSecondYear - nFirstYear) * 12 + nSecondMonth - nFirstMonth;
	}

	/**
	 * 计算两个日期之间相差多少年
	 * @param firstDate 第一个日期
	 * @param secondDate 第二个日期
	 * @param isStrict 是否精确到秒
	 * @return 相差多少年
	 */
	public static int elapsedYears(Date firstDate, Date secondDate) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(firstDate);
		int nFirstYear = gc.get(Calendar.YEAR);
		gc.setTime(secondDate);
		int nSecondYear = gc.get(Calendar.YEAR);
		return nSecondYear - nFirstYear;
	}

	/**
	 * 日期添加，按天数增加
	 * @param date 要添加的日期
	 * @param days 要增加的天数 
	 * @return 添加后的日期
	 */
	public static Date addDays(Date date, int days) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.DAY_OF_MONTH, days);
		return gc.getTime();
	}

	/**
	 * 日期添加，按月增加
	 * @param date 要添加的日期
	 * @param months 要增加的月数 
	 * @return 添加后的日期
	 */
	public static Date addMonths(Date date, int months) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.MONTH, months);
		return gc.getTime();
	}

	/**
	 * 日期添加，按年增加
	 * @param date 要添加的日期
	 * @param years 要增加的年数 
	 * @return 添加后的日期
	 */
	public static Date addYears(Date date, int years) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.YEAR, years);
		return gc.getTime();
	}

	/**
	 * 日期添加，按小时增加
	 * @param date 要添加的日期
	 * @param hours 要增加的小时数
	 * @return 添加后的日期
	 */
	public static Date addHours(Date date, int hours) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.HOUR, hours);
		return gc.getTime();
	}

	/**
	 * 日期添加，按分钟增加
	 * @param date 要添加的日期
	 * @param minutes 要增加的分钟数
	 * @return 添加后的日期
	 */
	public static Date addMinutes(Date date, int minutes) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.MINUTE, minutes);
		return gc.getTime();
	}

	/**
	 * 日期添加，按秒增加
	 * @param date 要添加的日期
	 * @param seconds 要增加的秒数
	 * @return 添加后的日期
	 */
	public static Date addSeconds(Date date, int seconds) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.SECOND, seconds);
		return gc.getTime();
	}

	/**
	 * 日期添加，按毫秒增加
	 * @param date 要添加的日期
	 * @param milliseconds 要增加的毫秒数
	 * @return 添加后的日期
	 */
	public static Date addMilliseconds(Date date, int milliseconds) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.MILLISECOND, milliseconds);
		return gc.getTime();
	}
}

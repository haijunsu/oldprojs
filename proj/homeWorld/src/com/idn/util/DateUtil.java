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
 * <P>�������ڼ���</P>
 * 
 * @version 0.1
 * @author navy
 */
public class DateUtil {

	/**
	 * ������ת��Ϊ������
	 * @param date
	 * @return ��1970��1��1�յ�ָ�����ڵĺ�����
	 */
	public static long parseDateToLong(Date date) {
		return date.getTime();
	}

	/**
	 * ��ָ����������ת��Ϊ������
	 * @param year ��
	 * @param month ��
	 * @param day ��
	 * @return ��1970��1��1�յ�ָ�����ڵĺ�����
	 */
	public static long parseDateToLong(int year, int month, int day) {
		return parseDateToLong(getDate(year, month, day));
	}

	/**
	 * ��ָ����������ת��Ϊ������
	 * @param year ��
	 * @param month ��
	 * @param day ��
	 * @return ��1970��1��1�յ�ָ�����ڵĺ�����
	 */
	public static long parseDateToLong(String year, String month, String day) {
		return parseDateToLong(getDate(year, month, day));
	}

	/**
	 * ��ָ����������ʱ��ת��Ϊ������
	 * @param year ��
	 * @param month ��
	 * @param day ��
	 * @param hour Сʱ
	 * @param minute ����
	 * @return ��1970��1��1�յ�ָ�����ڵĺ�����
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
	 * ��ָ����������ʱ��ת��Ϊ������
	 * @param year ��
	 * @param month ��
	 * @param day ��
	 * @param hour Сʱ
	 * @param minute ����
	 * @return ��1970��1��1�յ�ָ�����ڵĺ�����
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
	 * ��ָ����������ʱ����ת��Ϊ������
	 * @param year ��
	 * @param month ��
	 * @param day ��
	 * @param hour Сʱ
	 * @param minute ����
	 * @param second ��
	 * @return ��1970��1��1�յ�ָ�����ڵĺ�����
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
	 * ��ָ����������ʱ����ת��Ϊ������
	 * @param year ��
	 * @param month ��
	 * @param day ��
	 * @param hour Сʱ
	 * @param minute ����
	 * @param second ��
	 * @return ��1970��1��1�յ�ָ�����ڵĺ�����
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
	 * ��������ת��Ϊ������
	 * @param milliscond ��1970��1��1�յ�ָ�����ڵĺ�����
	 * @return ת���������
	 */
	public static Date getDate(long milliscond) {
		return new Date(milliscond);
	}

	/**
	 * ��������ת��Ϊ������ GregorianCalendar
	 * @param milliscond ��1970��1��1�յ�ָ�����ڵĺ�����
	 * @return ת��������� GregorianCalendar
	 */
	public static GregorianCalendar getGregorianCalendar(long milliscond) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(getDate(milliscond));
		return gc;
	}

	/**
	 * ����ָ���������գ��õ�Date������
	 * @param year ��
	 * @param month ��
	 * @param day ��
	 * @return ���� Date��
	 */
	public static Date getDate(int year, int month, int day) {
		month -= 1;
		GregorianCalendar gc = new GregorianCalendar(year, month, day);
		return gc.getTime();
	}

	/**
	 * ����ָ���������գ��õ�Date������
	 * @param year ��
	 * @param month ��
	 * @param day ��
	 * @return ���� Date��
	 */
	public static Date getDate(String year, String month, String day) {
		return getDate(
			Integer.parseInt(year),
			Integer.parseInt(month),
			Integer.parseInt(day));
	}

	/**
	 * ����ָ����������ʱ�֣��õ�Date������
	 * @param year ��
	 * @param month ��
	 * @param day ��
	 * @param hour Сʱ
	 * @param minute ��
	 * @return ���� Date��
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
	 * ����ָ����������ʱ�֣��õ�Date������
	 * @param year ��
	 * @param month ��
	 * @param day ��
	 * @param hour Сʱ
	 * @param minute ��
	 * @return ���� Date��
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
	 * ����ָ����������ʱ���룬�õ�Date������
	 * @param year ��
	 * @param month ��
	 * @param day ��
	 * @param hour Сʱ
	 * @param minute ��
	 * @param second ��
	 * @return ���� Date��
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
	 * ����ָ����������ʱ���룬�õ�Date������
	 * @param year ��
	 * @param month ��
	 * @param day ��
	 * @param hour Сʱ
	 * @param minute ��
	 * @param second ��
	 * @return ���� Date��
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
	 * ������������֮�����ĺ�����
	 * @param firstDate ��һ������
	 * @param secondDate �ڶ�������
	 * @return ���ĺ�����
	 */
	public static long elapsedMilliseconds(Date firstDate, Date secondDate) {
		return secondDate.getTime() - firstDate.getTime();
	}

	/**
	 * ������������֮����������
	 * @param firstDate ��һ������
	 * @param secondDate �ڶ�������
	 * @return ��������
	 */
	public static long elapsedSeconds(Date firstDate, Date secondDate) {
		return elapsedMilliseconds(firstDate, secondDate) / 1000;
	}

	/**
	 * ������������֮�������ٷ���
	 * @param firstDate ��һ������
	 * @param secondDate �ڶ�������
	 * @return �����ٷ���
	 */
	public static long elapsedMinutes(Date firstDate, Date secondDate) {
		return elapsedSeconds(firstDate, secondDate) / 60;
	}

	/**
	 * ������������֮��������Сʱ
	 * @param firstDate ��һ������
	 * @param secondDate �ڶ�������
	 * @return ������Сʱ
	 */
	public static long elapsedHours(Date firstDate, Date secondDate) {
		return elapsedMinutes(firstDate, secondDate) / 60;
	}

	/**
	 * ������������֮���������죬����Ȼ����㣬
	 * ��2004��3��3��23��50��2004��3��4��0��00�ͻ����һ��
	 * @param firstDate ��һ������
	 * @param secondDate �ڶ�������
	 * @return ��������
	 */
	public static long elapsedDays(Date firstDate, Date secondDate) {
		return elapsedDays(firstDate, secondDate, false);
	}

	/**
	 * ������������֮���������죬�����ȷ���룬���ϸ�ÿ��24СʱΪһ����㣬
	 * �������24Сʱ����Ϊͬһ�졣��2004��3��3��23��50��2004��3��4��23��00
	 * ������Ľ��Ϊ0��
	 * @param firstDate ��һ������
	 * @param secondDate �ڶ�������
	 * @param isStrict �Ƿ�ȷ����
	 * @return ��������
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
	 * ������������֮���������죬�����ȷ���룬���ϸ�ÿ��24СʱΪһ����㣬
	 * �������24Сʱ����Ϊͬһ�졣��2004��3��3��23��50��2004��3��4��23��00
	 * ������Ľ��Ϊ0��
	 * @param firstDate ��һ������
	 * @param secondDate �ڶ�������
	 * @param isStrict �Ƿ�ȷ����
	 * @return ��������
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
	 * ������������֮����������
	 * @param firstDate ��һ������
	 * @param secondDate �ڶ�������
	 * @param isStrict �Ƿ�ȷ����
	 * @return ��������
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
	 * ������ӣ�����������
	 * @param date Ҫ��ӵ�����
	 * @param days Ҫ���ӵ����� 
	 * @return ��Ӻ������
	 */
	public static Date addDays(Date date, int days) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.DAY_OF_MONTH, days);
		return gc.getTime();
	}

	/**
	 * ������ӣ���������
	 * @param date Ҫ��ӵ�����
	 * @param months Ҫ���ӵ����� 
	 * @return ��Ӻ������
	 */
	public static Date addMonths(Date date, int months) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.MONTH, months);
		return gc.getTime();
	}

	/**
	 * ������ӣ���������
	 * @param date Ҫ��ӵ�����
	 * @param years Ҫ���ӵ����� 
	 * @return ��Ӻ������
	 */
	public static Date addYears(Date date, int years) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.YEAR, years);
		return gc.getTime();
	}

	/**
	 * ������ӣ���Сʱ����
	 * @param date Ҫ��ӵ�����
	 * @param hours Ҫ���ӵ�Сʱ��
	 * @return ��Ӻ������
	 */
	public static Date addHours(Date date, int hours) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.HOUR, hours);
		return gc.getTime();
	}

	/**
	 * ������ӣ�����������
	 * @param date Ҫ��ӵ�����
	 * @param minutes Ҫ���ӵķ�����
	 * @return ��Ӻ������
	 */
	public static Date addMinutes(Date date, int minutes) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.MINUTE, minutes);
		return gc.getTime();
	}

	/**
	 * ������ӣ���������
	 * @param date Ҫ��ӵ�����
	 * @param seconds Ҫ���ӵ�����
	 * @return ��Ӻ������
	 */
	public static Date addSeconds(Date date, int seconds) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.SECOND, seconds);
		return gc.getTime();
	}

	/**
	 * ������ӣ�����������
	 * @param date Ҫ��ӵ�����
	 * @param milliseconds Ҫ���ӵĺ�����
	 * @return ��Ӻ������
	 */
	public static Date addMilliseconds(Date date, int milliseconds) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.MILLISECOND, milliseconds);
		return gc.getTime();
	}
}

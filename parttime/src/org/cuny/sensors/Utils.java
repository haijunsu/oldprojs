/*
 *
 */
package org.cuny.sensors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Haijun Su Created date: 2013-12-13
 */
public class Utils {

	public static final String DATE_PATTERN = "MM/dd/yyyy HH:mm:ss";

	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	public static long getTime(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		try {
			Date date = sdf.parse(strDate);
			return date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public static String getTimeString(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		Date date = new Date(time);
		return sdf.format(date);
	}

	public static Date getDate(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		return sdf.parse(strDate);
	}
}

/* 
 * Project: G-Roller
 * Created on 2003-12-16
 */
package groller.application.common;

import java.util.Calendar;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class StringUtil {
	public static String convertDateToString(Calendar day) {
		if(day == null) {
			return new String();
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(day.get(Calendar.YEAR));
		buffer.append("��");
		buffer.append(day.get(Calendar.MONTH) + 1);
		buffer.append("��");
		buffer.append(day.get(Calendar.DAY_OF_MONTH));
		buffer.append("��");
		return buffer.toString();
	}
}

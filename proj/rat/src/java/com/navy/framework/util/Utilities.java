package com.navy.framework.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * General purpose utilities, not for use in templates.
 */
public class Utilities {
	/** The <code>Log</code> instance for this class. */
	private static Log logger = LogFactory.getLog(Utilities.class);

	/**
	 * @param string
	 * @return
	 */
	public static int stringToInt(String string) {
		try {
			return Integer.valueOf(string).intValue();
		} catch (NumberFormatException e) {
			logger.debug("Invalid Integer:" + string);
		}
		return 0;
	}

	/**
	 * if both are null values, it will return true
	 *
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean objectEqulas(Object obj1, Object obj2) {
		return obj1 != null ? obj1.equals(obj2) : obj2 == null;
	}

	public static int buildHashCode(Object[] objs) {
		int hashCode = 0;
		for (int i = 0; i < objs.length; i++) {
			hashCode = 29 * hashCode
					+ (objs[i] != null ? objs[i].hashCode() : 0);

		}
		return hashCode;
	}

	public static int buildHashCode(Object obj) {

		return buildHashCode(new Object[] { obj });
	}
}

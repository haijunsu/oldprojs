/*
 * @(#)StringUtil.java  2005-3-17
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package util;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision$
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class StringUtil {

    public static boolean isBlankString(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
    }
    
    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }
    
    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }

}

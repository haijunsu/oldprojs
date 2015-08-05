/*
 * @(#)StringUtil.java  2005-1-4
 * 
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/framework/util/StringUtil.java,v 1.1 2005/01/20 03:14:25 navy Exp $
 * $Log: StringUtil.java,v $
 * Revision 1.1  2005/01/20 03:14:25  navy
 * Create SMiRT 18 project
 *
 */
package framework.util;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * String useful tools
 * </p>
 * 
 * $Revision: 1.1 $
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class StringUtil {

    public static String[] split(String str, String separatorChars, int max) {
        String[] _strArray = new String[0];
        try {
            _strArray = str.split(separatorChars, max);
        } catch (Exception e) {
            _strArray = StringUtils.split(str, separatorChars, max);
        }
        ArrayUtil.trim(_strArray);
        return _strArray;
    }

    public static String[] split(String str, String separatorChars) {
        return split(str, separatorChars, 0);
    }

    public static String[] split(String str) {
        return split(str, " ");
    }

    public static String[] split(String str, char separatorChars) {
        return split(str, String.valueOf(separatorChars));
    }

    public static String left(String str, int len) {
        return StringUtils.left(str, len);
    }

    public static String right(String str, int len) {
        return StringUtils.right(str, len);
    }

    public static String mid(String str, int pos, int len) {
        return StringUtils.mid(str, pos, len);
    }

    public static String leftPad(String str, int size, String padStr) {
        return StringUtils.leftPad(str, size, padStr);
    }

    public static String leftPad(String str, int size, char padChar) {
        return leftPad(str, size, String.valueOf(padChar));
    }

    public static String leftPad(String str, int size) {
        return leftPad(str, size, " ");
    }

    public static String rightPad(String str, int size, String padStr) {
        return StringUtils.rightPad(str, size, padStr);
    }

    public static String rightPad(String str, int size, char padChar) {
        return rightPad(str, size, String.valueOf(padChar));
    }

    public static String rightPad(String str, int size) {
        return rightPad(str, size, " ");
    }

    public static String center(String str, int size, String padStr) {
        return StringUtils.center(str, size, padStr);
    }

    public static String center(String str, int size, char padChar) {
        return center(str, size, String.valueOf(padChar));
    }

    public static String center(String str, int size) {
        return center(str, size, " ");
    }

    public static String repeat(String str, int repeat) {
        return StringUtils.repeat(str, repeat);
    }

    public static String reverse(String str) {
        return StringUtils.reverse(str);
    }

    public static String reverseDelimited(String str, char separatorChar) {
        return StringUtils.reverseDelimited(str, separatorChar);
    }

    public static String abbreviate(String str, int offset, int maxWidth) {
        return StringUtils.abbreviate(str, offset, maxWidth);
    }

    public static String abbreviate(String str, int maxWidth) {
        return StringUtils.abbreviate(str, 0, maxWidth);
    }

    public static String wrapWord(String str, int wrapLength) {
        return wrapWord(str, wrapLength, null, false);
    }

    public static String wrapWord(String str, int wrapLength,
            String newLineStr, boolean wrapLongWords) {
        return WordUtils.wrap(str, wrapLength, newLineStr, wrapLongWords);
    }

    public static String capitalize(String str) {
        return WordUtils.capitalize(str);
    }

    public static String capitalizeFully(String str) {
        return WordUtils.capitalizeFully(str);
    }

    public static String uncapitalize(String str) {
        return WordUtils.uncapitalize(str);
    }

    public static String swapCase(String str) {
        return WordUtils.swapCase(str);
    }

    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    public static boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str);
    }
    
    public static String escapeSql(String str) {
        return StringEscapeUtils.escapeSql(str);
    }
}
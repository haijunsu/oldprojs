/*
 * @(#)ArrayUtil.java  2005-2-16
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
 */
package framework.util;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision$
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class ArrayUtil {
    public static boolean contains(Object[] array, Object objectToFind) {
        return ArrayUtils.contains(array, objectToFind);
    }
    
    public static void reverse(Object[] array) {
        ArrayUtils.reverse(array);
    }
    
    public static boolean isEquals(Object array1,
            Object array2) {
        return ArrayUtils.isEquals(array1, array2);
    }
    
    public static String toString(Object[] array) {
        return ArrayUtils.toString(array);
    }
    
    public static void trim(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
    }
    
    public static boolean isEmpty(Object[] array) {
        if (array == null || array.length == 0) {
            return true;
        }
        return false;
    }
}
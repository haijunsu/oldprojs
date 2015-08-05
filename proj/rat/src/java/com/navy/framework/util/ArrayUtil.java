/*
 * @(#)ArrayUtil.java  2007-1-10
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.util;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 */
public class ArrayUtil {

	// PRIVATE //
	private static final String START_CHAR = "[";

	private static final String END_CHAR = "]";

	private static final String SEPARATOR = ", ";

	private static final String NULL = "null";

	/**
	 * <code>aArray</code> is a possibly-null array whose elements are
	 * primitives or objects; arrays of arrays are also valid, in which case
	 * <code>aArray</code> is rendered in a nested, recursive fashion.
	 */
	public static String arrayToString(Object aArray) {
		if (aArray == null)
			return NULL;
		checkObjectIsArray(aArray);

		StringBuffer result = new StringBuffer(START_CHAR);
		int length = Array.getLength(aArray);
		for (int idx = 0; idx < length; ++idx) {
			Object item = Array.get(aArray, idx);
			if (isNonNullArray(item)) {
				// recursive call!
				result.append(arrayToString(item));
			} else {
				result.append(item);
			}
			if (!isLastItem(idx, length)) {
				result.append(SEPARATOR);
			}
		}
		result.append(END_CHAR);
		return result.toString();
	}

	private static void checkObjectIsArray(Object aArray) {
		if (!aArray.getClass().isArray()) {
			throw new IllegalArgumentException("Object is not an array.");
		}
	}

	private static boolean isNonNullArray(Object aItem) {
		return aItem != null && aItem.getClass().isArray();
	}

	private static boolean isLastItem(int aIdx, int aLength) {
		return (aIdx == aLength - 1);
	}

	/** Convert string to integer array. */
	public static int[] stringToIntArray(String instr, String delim)
			throws NoSuchElementException, NumberFormatException {
		StringTokenizer toker = new StringTokenizer(instr, delim);
		int intArray[] = new int[toker.countTokens()];
		int i = 0;

		while (toker.hasMoreTokens()) {
			String sInt = toker.nextToken();
			int nInt = Integer.parseInt(sInt);
			intArray[i++] = new Integer(nInt).intValue();
		}
		return intArray;
	}

	/** Convert string to string array. */
	public static String[] stringToStringArray(String instr, String delim)
			throws NoSuchElementException, NumberFormatException {
		StringTokenizer toker = new StringTokenizer(instr, delim);
		String stringArray[] = new String[toker.countTokens()];
		int i = 0;

		while (toker.hasMoreTokens()) {
			stringArray[i++] = toker.nextToken();
		}
		return stringArray;
	}

}

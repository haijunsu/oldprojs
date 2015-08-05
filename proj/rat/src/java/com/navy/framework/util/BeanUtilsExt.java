/*
 * @(#)BeanUtilsExt.java  2006-12-4
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.navy.framework.util.converter.SqlDateConverter;
import com.navy.framework.util.converter.SqlTimestampConverter;
import com.navy.framework.util.converter.UtilDateConverter;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * To support data object is null.
 * </p>
 *
 */
public class BeanUtilsExt extends BeanUtils {

	static {
		ConvertUtils.register(new SqlTimestampConverter(),
				java.sql.Timestamp.class);
		ConvertUtils.register(new SqlDateConverter(), java.sql.Date.class);
		ConvertUtils.register(new UtilDateConverter(), java.util.Date.class);

	}

	public static void copyProperties(Object target, Object source)
			throws InvocationTargetException, IllegalAccessException {

		org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);

	}

}




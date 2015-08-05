/*
 * @(#)SqlTimestampConverter.java  2007-1-6
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.util.converter;

import java.sql.Timestamp;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 */
public class SqlTimestampConverter implements Converter {

	/**
	 * Create a {@link Converter} that will throw a {@link ConversionException}
	 * if a conversion error occurs.
	 */
	public SqlTimestampConverter() {

		this.defaultValue = null;
		this.useDefault = false;

	}

	/**
	 * Create a {@link Converter} that will return the specified default value
	 * if a conversion error occurs.
	 *
	 * @param defaultValue
	 *            The default value to be returned
	 */
	public SqlTimestampConverter(Object defaultValue) {

		this.defaultValue = defaultValue;
		this.useDefault = true;

	}

	/*
	 * The default value specified to our Constructor, if any.
	 */
	private Object defaultValue = null;

	/**
	 * Should we return the default value on conversion errors?
	 */
	private boolean useDefault = true;

	// --------------------------------------------------------- Public Methods

	/**
	 * Convert the specified input object into an output object of the specified
	 * type.
	 *
	 * @param type
	 *            Data type to which this value should be converted
	 * @param value
	 *            The input value to be converted
	 *
	 * @exception ConversionException
	 *                if conversion cannot be performed successfully
	 */
	public Object convert(Class type, Object value) {

		if (value == null) {
			if (useDefault) {
				return (defaultValue);
			} else {
				return null;
				// throw new ConversionException("No value specified");
			}
		}

		if (value instanceof Timestamp) {
			return (value);
		}

		try {
			return (Timestamp.valueOf(value.toString()));
		} catch (Exception e) {
			if (useDefault) {
				return (defaultValue);
			} else {
				throw new ConversionException(e);
			}
		}

	}

}

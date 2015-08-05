/*
 * @(#)DaoException.java  2005-1-6
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header: /navysu/Smirt18/src/framework/exception/DaoException.java,v 1.1 2005/01/20 03:14:22 navy Exp $
 * $Log: DaoException.java,v $
 * Revision 1.1  2005/01/20 03:14:22  navy
 * Create SMiRT 18 project
 *
 */
package com.navy.framework.exception;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * Access Dao throw this Exception.
 * </p>
 *
 * $Revision: 1.1 $
 *
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class DaoException extends BaseException {

	private final static String MESSAGE_BUNDLE = "properties.DaoExceptionMessages";

	private static ResourceBundle m_ResourceBundle = null;

	private static Map m_Bundles = new HashMap();

	public DaoException() {
		super();
	}

	public DaoException(String errCode) {
		super(errCode);
	}

	public DaoException(String errCode, Throwable cause) {
		super(errCode, cause);
	}

	public DaoException(String errCode, Object[] params) {
		super(errCode, params);
	}

	public DaoException(String errCode, Object[] params, Throwable cause) {
		super(errCode, params, cause);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.exception.BaseException#getMsgCodePrefix()
	 */
	protected String getMsgCodePrefix() {
		return "dao.err.";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.exception.BaseException#getResourceBundle(java.util.Locale)
	 */
	protected ResourceBundle getResourceBundle(Locale locale) {
		if (locale == null) {
			if (m_ResourceBundle == null) {
				m_ResourceBundle = ResourceBundle.getBundle(MESSAGE_BUNDLE);
			}
			return m_ResourceBundle;
		}
		ResourceBundle _rb = (ResourceBundle) m_Bundles.get(locale);
		if (_rb == null) {
			_rb = ResourceBundle.getBundle(MESSAGE_BUNDLE, locale);
			m_Bundles.put(locale, _rb);
		}
		return _rb;
	}

}

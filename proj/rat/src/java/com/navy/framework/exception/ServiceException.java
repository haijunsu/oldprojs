/*
 * @(#)ServiceException.java  2005-1-6
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header: /navysu/Smirt18/src/framework/exception/ServiceException.java,v 1.1 2005/01/20 03:14:21 navy Exp $
 * $Log: ServiceException.java,v $
 * Revision 1.1  2005/01/20 03:14:21  navy
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
 * Excute business service which extend framework.service.impl.BaseServiceImpl,
 * it will throw this exception when error occurred.
 * </p>
 *
 * $Revision: 1.1 $
 *
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class ServiceException extends BaseException {
	private final static String MESSAGE_BUNDLE = "properties.ServiceExceptionMessages";

	private static ResourceBundle m_ResourceBundle = null;

	private static Map m_Bundles = new HashMap();

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.exception.BaseException#getMsgCodePrefix()
	 */
	protected String getMsgCodePrefix() {
		return "service.err.";
	}

	public ServiceException() {
		super();
	}

	public ServiceException(String errCode) {
		super(errCode);
	}

	public ServiceException(String errCode, Throwable cause) {
		super(errCode, cause);
	}

	public ServiceException(String errCode, Object[] params) {
		super(errCode, params);
	}

	public ServiceException(String errCode, Object[] params, Throwable cause) {
		super(errCode, params, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
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

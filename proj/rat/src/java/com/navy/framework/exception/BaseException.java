/*
 * @(#)BaseException.java  2005-1-6
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header: /navysu/Smirt18/src/framework/exception/BaseException.java,v 1.1 2005/01/20 03:14:21 navy Exp $
 * $Log: BaseException.java,v $
 * Revision 1.1  2005/01/20 03:14:21  navy
 * Create SMiRT 18 project
 *
 */
package com.navy.framework.exception;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.SystemUtils;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * Bease Exception for framework while be dealt in further. All business
 * exception should extends this exception.
 * </p>
 *
 * $Revision: 1.1 $
 *
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public abstract class BaseException extends RuntimeException {

	/**
	 * Logger for this class
	 */
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(BaseException.class);

	private String m_errCode = null;

	private Object[] m_params = null;

	private Locale m_locale = new Locale(SystemUtils.USER_LANGUAGE,
			SystemUtils.USER_COUNTRY);

	protected abstract ResourceBundle getResourceBundle(Locale locale);

	protected abstract String getMsgCodePrefix();

	/**
	 * @param localeValue
	 *            the localeValue to set
	 */
	public void setLocaleValue(Locale locale) {
		this.m_locale = locale;
	}

	public BaseException() {
		super();
	}

	public BaseException(String code) {
		super("Error Code: " + code);
		this.m_errCode = code;
	}

	public BaseException(String code, Throwable cause) {
		super("Error Code: " + code, cause);
		this.m_errCode = code;
	}

	public BaseException(String code, Object[] params) {
		super("Error Code: " + code + ", Parameters: "
				+ Arrays.toString(params));
		this.m_errCode = code;
		this.m_params = params;
	}

	public BaseException(String code, Object[] params, Throwable cause) {
		super("Error Code: " + code + ", Parameters: "
				+ Arrays.toString(params), cause);
		this.m_errCode = code;
		this.m_params = params;
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	/**
	 * @return the errCode
	 */
	public String getErrCode() {
		return this.m_errCode;
	}

	/**
	 * @return the params
	 */
	public Object[] getParams() {
		return this.m_params;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return formatMsg();
	}

	private String formatMsg() {
		try {
			ResourceBundle _rb = getResourceBundle(m_locale);
			String _strMsg = _rb.getString(getMsgCodePrefix() + m_errCode);
			if (m_params != null) {
				_strMsg = MessageFormat.format(_strMsg, m_params);
			}
			return _strMsg;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return super.getMessage();
		}
	}

	/**
	 * @param errCode the errCode to set
	 */
	public void setErrCode(String errCode) {
		m_errCode = errCode;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Object[] params) {
		m_params = params;
	}

}

/*
 * @(#)SearchBean.java  Apr 17, 2006
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.web.data;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public abstract class BaseSearchBean implements Serializable {

	/**
	 * Logger for this class
	 */
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(BaseSearchBean.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -7894349526903865295L;

	protected final String SQL_AND = " and ";

	private int m_pageNo;

	private String m_orderby;

	private String m_orderAscending;

	/**
	 * @return Returns the orderby.
	 */
	public String getOrderby() {
		return this.m_orderby;
	}

	/**
	 * @param orderby
	 *            The orderby to set.
	 */
	public void setOrderby(String orderby) {
		m_orderby = orderby;
	}

	public int getPageNo() {
		return this.m_pageNo;
	}

	public void setPageNo(int pageNo) {
		m_pageNo = pageNo;
	}

	/**
	 * @return Returns the orderAscending.
	 */
	public String getOrderAscending() {
		return this.m_orderAscending;
	}

	/**
	 * @param orderAscending
	 *            The orderAscending to set.
	 */
	public void setOrderAscending(String orderAscending) {
		m_orderAscending = orderAscending;
	}

	public abstract int getObjectType();

	public abstract String getWhereClause();

	public String getOrderByClause() {
		if (StringUtils.isBlank(getOrderby())) {
			return null;
		}
		StringBuffer _buffer = new StringBuffer();
		_buffer.append(getOrderby()).append(" ").append(getOrderAscending());
		if (logger.isDebugEnabled()) {
			logger.debug("order by = " + _buffer.toString());
		}
		return _buffer.toString();
	}

	protected static String toSQLLegalString(String origin) {
		if (origin == null || origin.length() == 0) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < origin.length(); i++) {
			char aChar = origin.charAt(i);
			result.append(aChar);
			if (aChar == '\'')
				result.append(aChar);
		}
		return result.toString();
	}

}

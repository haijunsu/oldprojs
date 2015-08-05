/*
 * @(#)UserInfo.java  2006-12-14
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.web.data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.navy.framework.service.ServiceLocator;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 */
public class UserInfo implements Serializable, HttpSessionBindingListener {

	/**
	 * Logger for this class
	 */
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(UserInfo.class);

	private int m_preview = 0;

	private Locale m_language = null;

	private String m_username = null;

	private Long m_id = null;

	private String m_userFullname = null;

	private String m_lastAccessFrom = null;

	private Date m_lastAccessTime = null;

	private String m_currentIp = null;

	private int m_perview = 0;

	private Map m_userBag = new HashMap();

	public static UserInfo get(javax.servlet.http.HttpSession session) {
		if (session == null)
			return null;
		return (UserInfo) session.getAttribute(UserInfo.class.getName());
	}

	public static void put(javax.servlet.http.HttpSession session,
			UserInfo userInfo) {
		session.setAttribute(UserInfo.class.getName(), userInfo);
	}

	public static void remove(javax.servlet.http.HttpSession session) {
		session.removeAttribute(UserInfo.class.getName());
	}

	public void addProperty(String key, Object property) {
		m_userBag.put(key, property);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.m_id;
	}

	/**
	 * @return the lastAccessFrom
	 */
	public String getLastAccessFrom() {
		return this.m_lastAccessFrom;
	}

	/**
	 * @return the lastAccessTime
	 */
	public Date getLastAccessTime() {
		return this.m_lastAccessTime;
	}

	/**
	 * @return the perview
	 */
	public int getPerview() {
		return this.m_perview;
	}

	/**
	 * @return the preview
	 */
	public int getPreview() {
		return this.m_preview;
	}

	public Object getProperty(String key) {
		return m_userBag.get(key);
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		m_id = id;
	}

	/**
	 * @param lastAccessFrom
	 *            the lastAccessFrom to set
	 */
	public void setLastAccessFrom(String lastAccessFrom) {
		m_lastAccessFrom = lastAccessFrom;
	}

	/**
	 * @param lastAccessTime
	 *            the lastAccessTime to set
	 */
	public void setLastAccessTime(Date lastAccessTime) {
		this.m_lastAccessTime = lastAccessTime;
	}

	/**
	 * @param perview
	 *            the perview to set
	 */
	public void setPerview(int perview) {
		m_perview = perview;
	}

	/**
	 * @param preview
	 *            the preview to set
	 */
	public void setPreview(int preview) {
		m_preview = preview;
	}

	/**
	 * @return the userFullname
	 */
	public String getUserFullname() {
		return this.m_userFullname;
	}

	/**
	 * @param userFullname
	 *            the userFullname to set
	 */
	public void setUserFullname(String userFullname) {
		m_userFullname = userFullname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.m_username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		m_username = username;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueBound(HttpSessionBindingEvent arg0) {
		if (logger.isDebugEnabled()) {
			logger.debug("value bound");
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		if (logger.isDebugEnabled()) {
			logger.debug("value unbound");
		}
		try {
			ServiceLocator.getUserService().logout(this);
		} catch (Exception e) {
			logger.error(e);
		}

	}

	/**
	 * @return the language
	 */
	public Locale getLanguage() {
		return this.m_language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(Locale language) {
		m_language = language;
	}

	/**
	 * @return the currentIp
	 */
	public String getCurrentIp() {
		return this.m_currentIp;
	}

	/**
	 * @param currentIp
	 *            the currentIp to set
	 */
	public void setCurrentIp(String currentIp) {
		m_currentIp = currentIp;
	}
}

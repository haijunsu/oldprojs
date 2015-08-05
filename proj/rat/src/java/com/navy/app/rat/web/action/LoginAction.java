/*
 * @(#)LoginAction.java  2006-12-22
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.web.action;

import com.navy.app.rat.service.ServiceFactory;
import com.navy.framework.service.UserService;
import com.navy.framework.web.action.BaseAction;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 */
public class LoginAction extends BaseAction {

	/**
	 * Logger for this class
	 */
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(LoginAction.class);

	private UserService m_service;

	/**
	 * @return the service
	 */
	private UserService getService() {
		if (m_service == null) {
			m_service = ServiceFactory.getUserService();
		}
		return this.m_service;
	}

}

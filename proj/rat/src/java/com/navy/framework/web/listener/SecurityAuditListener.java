/*
 * @(#)SecurityAuditListener.java  2007-1-9
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.web.listener;

import org.acegisecurity.event.authentication.AuthenticationFailureBadCredentialsEvent;
import org.acegisecurity.event.authentication.LoggerListener;
import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 */
public class SecurityAuditListener extends LoggerListener {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.acegisecurity.event.authentication.LoggerListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	public void onApplicationEvent(ApplicationEvent event) {
		super.onApplicationEvent(event);

		if (event instanceof AuthenticationFailureBadCredentialsEvent) {

			// TODO logfail event
			AuthenticationFailureBadCredentialsEvent _authEvent = (AuthenticationFailureBadCredentialsEvent) event;
		}

	}

}

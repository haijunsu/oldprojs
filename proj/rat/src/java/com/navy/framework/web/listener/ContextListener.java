/*
 * @(#)ContextListener.java  2007-1-5
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.web.listener;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.providers.ProviderManager;
import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.acegisecurity.providers.rememberme.RememberMeAuthenticationProvider;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.navy.framework.factory.FrameworkResourcesFactory;
import com.navy.framework.web.Constants;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 */
public class ContextListener extends ContextLoaderListener implements
		ServletContextListener {
	/**
	 * Logger for ContextListener.class
	 */

	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(ContextListener.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.context.ContextLoaderListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		if (logger.isDebugEnabled()) {
			logger.debug("Shut down at " + new Date());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ServletContext context = event.getServletContext();

		// Orion starts Servlets before Listeners, so check if the config
		// object already exists
		Map config = (HashMap) context.getAttribute(Constants.CONFIG);

		if (config == null) {
			config = new HashMap();
		}

		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);
		// init beanFactory for services.
		FrameworkResourcesFactory.setApplicationContext(ctx);

		boolean encryptPassword = false;
		try {
			ProviderManager provider = (ProviderManager) ctx
					.getBean("authenticationManager");
			for (Iterator it = provider.getProviders().iterator(); it.hasNext();) {
				AuthenticationProvider p = (AuthenticationProvider) it.next();
				if (p instanceof RememberMeAuthenticationProvider) {
					config.put("rememberMeEnabled", Boolean.TRUE);
				}
			}

			if (ctx.containsBean("passwordEncoder")) {
				encryptPassword = true;
				config.put(Constants.ENCRYPT_PASSWORD, Boolean.TRUE);
				String algorithm = "SHA";
				if (ctx.getBean("passwordEncoder") instanceof Md5PasswordEncoder) {
					algorithm = "MD5";
				}
				config.put(Constants.ENC_ALGORITHM, algorithm);
			}
		} catch (NoSuchBeanDefinitionException n) {
			// ignore, should only happen when testing
		}

		context.setAttribute(Constants.CONFIG, config);

		// output the retrieved values for the Init and Context Parameters
		if (logger.isDebugEnabled()) {
			logger.debug("Remember Me Enabled? "
					+ config.get("rememberMeEnabled"));
			logger.debug("Encrypt Passwords? " + encryptPassword);
			if (encryptPassword) {
				logger.debug("Encryption Algorithm: "
						+ config.get(Constants.ENC_ALGORITHM));
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Startup at " + new Date());
		}
	}

}

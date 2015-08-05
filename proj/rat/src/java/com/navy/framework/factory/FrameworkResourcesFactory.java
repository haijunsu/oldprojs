/*
 * @(#)FrameworkResourcesFactory.java  2005-1-5
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header: /navysu/Smirt18/src/framework/factory/FrameworkResourcesFactory.java,v 1.1 2005/01/20 03:14:29 navy Exp $
 * $Log: FrameworkResourcesFactory.java,v $
 * Revision 1.1  2005/01/20 03:14:29  navy
 * Create SMiRT 18 project
 *
 */
package com.navy.framework.factory;

import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.springframework.context.ApplicationContext;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * Locator all application beans.
 * </p>
 *
 * $Revision: 1.1 $
 *
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class FrameworkResourcesFactory {

	/**
	 * Logger for FrameworkResourcesFactory.class
	 */

	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(FrameworkResourcesFactory.class);

	private static BeanFactoryReference resourcesFactory = null;

	private static ApplicationContext applicationContext = null;

	protected static Object getResource(String str) {
		if (applicationContext != null) {
			return applicationContext.getBean(str);
		}
		return getResourcesFactory().getFactory().getBean(str);
	}

	private static BeanFactoryReference getResourcesFactory() {
		if (resourcesFactory == null) {
			try {
				BeanFactoryLocator _bfl = SingletonBeanFactoryLocator
						.getInstance();
				resourcesFactory = _bfl
						.useBeanFactory("com.application.context");
			} catch (Exception e) {
				logger.error("Exception: " + e.getMessage(), e);
			}
		}
		return resourcesFactory;
	}

	/**
	 * @param applicationContext
	 *            the applicationContext to set
	 */
	public static void setApplicationContext(
			ApplicationContext applicationContext) {
		FrameworkResourcesFactory.applicationContext = applicationContext;
	}

}

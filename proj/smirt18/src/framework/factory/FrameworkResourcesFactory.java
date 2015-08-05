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
package framework.factory;

import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;

/**
 * <p><b>Description</b></p>
 * <p>Locator all application beans.</p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class FrameworkResourcesFactory{
 
    private static BeanFactoryReference m_ResourcesFactory;
    static {
		try {
			BeanFactoryLocator _bfl = SingletonBeanFactoryLocator.getInstance();
			m_ResourcesFactory = _bfl.useBeanFactory("com.application.context");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    protected static Object getResource(String str) {
        return m_ResourcesFactory.getFactory().getBean(str);
    }

}

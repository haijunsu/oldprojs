/*
 * @(#)BeansLocator.java  2005-1-20
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/helper/BeansLocator.java,v 1.1 2005/06/14 10:29:22 navysu Exp $
 * $Log: BeansLocator.java,v $
 * Revision 1.1  2005/06/14 10:29:22  navysu
 * add application and framework etc.
 *
 */
package application.helper;

import framework.factory.FrameworkResourcesFactory;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * <p>
 * </p>
 * 
 * $Revision: 1.1 $
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class BeansLocator extends FrameworkResourcesFactory {

     public static Object getBeanResource(String beanName) {
        return getResource(beanName);
    }
}
/*
 * @(#)BeansLocator.java  2005-1-20
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header$
 * $Log$
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
 * $Revision$
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class BeansLocator extends FrameworkResourcesFactory {

     public static Object getBeanResource(String beanName) {
        return getResource(beanName);
    }
}
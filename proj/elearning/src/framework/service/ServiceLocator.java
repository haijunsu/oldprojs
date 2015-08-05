/*
 * @(#)ServiceLocator.java  2005-1-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/framework/service/ServiceLocator.java,v 1.1 2005/06/14 10:29:21 navysu Exp $
 * $Log: ServiceLocator.java,v $
 * Revision 1.1  2005/06/14 10:29:21  navysu
 * add application and framework etc.
 *
 * Revision 1.1  2005/01/20 03:14:24  navy
 * Create SMiRT 18 project
 *
 */
package framework.service;

import framework.factory.FrameworkResourcesFactory;

/**
 * <p><b>Description</b></p>
 * <p></p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class ServiceLocator extends FrameworkResourcesFactory {
    protected static IService getService(String serviceName) {
        return (IService)getResource(serviceName);
    }
}

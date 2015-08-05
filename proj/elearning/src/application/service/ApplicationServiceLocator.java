/*
 * @(#)ApplicationServiceLocator.java  2005-1-6
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header: /project/Elearning/src/application/service/ApplicationServiceLocator.java,v 1.1 2005/06/14 10:29:22 navysu Exp $
 * $Log: ApplicationServiceLocator.java,v $
 * Revision 1.1  2005/06/14 10:29:22  navysu
 * add application and framework etc.
 *
 * Revision 1.1  2005/01/20 03:14:27  navy
 * Create SMiRT 18 project
 *
 */
package application.service;

import framework.service.ServiceLocator;

/**
 * <p><b>Description</b></p>
 * <p>Locator all business services which have defined in context.</p>
 *
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class ApplicationServiceLocator extends ServiceLocator {

    public static UserService getUserService() {
        return (UserService)getService("userService");
    }

    public static MenuService getMenuService() {
        return (MenuService)getService("menuService");
    }

}

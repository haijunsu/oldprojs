/*
 * @(#)ServiceException.java  2005-1-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/framework/exception/ServiceException.java,v 1.1 2005/06/14 10:29:23 navysu Exp $
 * $Log: ServiceException.java,v $
 * Revision 1.1  2005/06/14 10:29:23  navysu
 * add application and framework etc.
 *
 * Revision 1.1  2005/01/20 03:14:21  navy
 * Create SMiRT 18 project
 *
 */
package framework.exception;

/**
 * <p><b>Description</b></p>
 * <p>Excute business service which extend framework.service.impl.BaseServiceImpl,
 * it will throw this exception when error occurred.</p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class ServiceException extends BaseException {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ServiceException(Throwable cause) {
        super(cause);
    }

}

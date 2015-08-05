/*
 * @(#)BusinessServiceException.java  2005-1-10
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/application/exception/BusinessServiceException.java,v 1.1 2005/06/14 10:29:23 navysu Exp $
 * $Log: BusinessServiceException.java,v $
 * Revision 1.1  2005/06/14 10:29:23  navysu
 * add application and framework etc.
 *
 * Revision 1.1  2005/01/20 03:14:21  navy
 * Create SMiRT 18 project
 *
 */
package application.exception;

/**
 * <p><b>Description</b></p>
 * <p>All business Serivces will throw this Exception.
 * Error code will indicate error's type.
 * 1~99 System error.
 * 100~199 User action error.
 * </p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class BusinessServiceException extends BaseApplicationException {
    

    /**
     * 
     */
    public BusinessServiceException() {
        super();
    }
    /**
     * @param errcode
     */
    public BusinessServiceException(int errcode) {
        super(errcode);
    }
    /**
     * @param errcode
     * @param message
     */
    public BusinessServiceException(int errcode, String message) {
        super(errcode, message);
    }
    /**
     * @param errcode
     * @param message
     * @param cause
     */
    public BusinessServiceException(int errcode, String message, Throwable cause) {
        super(errcode, message, cause);
    }
    /**
     * @param errcode
     * @param cause
     */
    public BusinessServiceException(int errcode, Throwable cause) {
        super(errcode, cause);
    }
    /**
     * @param message
     */
    public BusinessServiceException(String message) {
        super(message);
    }
    /**
     * @param message
     * @param cause
     */
    public BusinessServiceException(String message, Throwable cause) {
        super(message, cause);
     }
    /**
     * @param cause
     */
    public BusinessServiceException(Throwable cause) {
        super(cause);
    }
}

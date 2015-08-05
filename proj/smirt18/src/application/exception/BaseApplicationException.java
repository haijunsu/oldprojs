/*
 * @(#)BaseApplicationException.java  2005-1-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/application/exception/BaseApplicationException.java,v 1.1 2005/01/20 03:14:21 navy Exp $
 * $Log: BaseApplicationException.java,v $
 * Revision 1.1  2005/01/20 03:14:21  navy
 * Create SMiRT 18 project
 *
 */
package application.exception;

import framework.exception.BaseException;

/**
 * <p><b>Description</b></p>
 * <p>Base application exception. It defines an <b>ErrCode</b> to deal with 
 * business exceptions. ErrCode default value is 0. If ErrCode greater 
 * than 900, it means a system error. If ErrCode less than 0,  it means 
 * an unknown error and should stop application.</p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class BaseApplicationException extends BaseException {
    
    private int m_ErrCode = 0;
    
    public BaseApplicationException() {
        super();
    }

    public BaseApplicationException(String message) {
        super(message);
    }
    
    public BaseApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BaseApplicationException(Throwable cause) {
        super(cause);
    }

    public BaseApplicationException(int errcode) {
        super();
        this.m_ErrCode = errcode;
    }

    public BaseApplicationException(int errcode, String message) {
        super(message);
        this.m_ErrCode = errcode;
   }
    
    public BaseApplicationException(int errcode, String message, Throwable cause) {
        super(message, cause);
        this.m_ErrCode = errcode;
    }
    
    public BaseApplicationException(int errcode, Throwable cause) {
        super(cause);
        this.m_ErrCode = errcode;
    }

    /**
     * @return Returns the errCode.
     */
    public int getErrCode() {
        return this.m_ErrCode;
    }
}

/*
 * @(#)BaseException.java  2005-1-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/framework/exception/BaseException.java,v 1.1 2005/01/20 03:14:21 navy Exp $
 * $Log: BaseException.java,v $
 * Revision 1.1  2005/01/20 03:14:21  navy
 * Create SMiRT 18 project
 *
 */
package framework.exception;

/**
 * <p><b>Description</b></p>
 * <p>Bease Exception for framework while be dealt in further.
 * All business exception should extends this exception.</p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class BaseException extends RuntimeException {
    
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }
    
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BaseException(Throwable cause) {
        super(cause);
    }
}

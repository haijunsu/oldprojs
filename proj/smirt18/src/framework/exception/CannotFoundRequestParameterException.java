/*
 * @(#)CannotFoundRequestParameterException.java  2005-1-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/framework/exception/CannotFoundRequestParameterException.java,v 1.1 2005/01/20 03:14:22 navy Exp $
 * $Log: CannotFoundRequestParameterException.java,v $
 * Revision 1.1  2005/01/20 03:14:22  navy
 * Create SMiRT 18 project
 *
 */
package framework.exception;

/**
 * <p><b>Description</b></p>
 * <p>When access request parameter by framework.util.http.RequestUtil required mothed,
 * it will throw this exception if the parameter can't be found. </p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class CannotFoundRequestParameterException extends BaseException {
    public CannotFoundRequestParameterException() {
        super();
    }

    public CannotFoundRequestParameterException(String message) {
        super(message);
    }
    
    public CannotFoundRequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CannotFoundRequestParameterException(Throwable cause) {
        super(cause);
    }

}

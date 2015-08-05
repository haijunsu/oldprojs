/*
 * @(#)DaoException.java  2005-1-6
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /project/Elearning/src/framework/exception/DaoException.java,v 1.1 2005/06/14 10:29:23 navysu Exp $
 * $Log: DaoException.java,v $
 * Revision 1.1  2005/06/14 10:29:23  navysu
 * add application and framework etc.
 *
 * Revision 1.1  2005/01/20 03:14:22  navy
 * Create SMiRT 18 project
 *
 */
package framework.exception;

/**
 * <p><b>Description</b></p>
 * <p>Access Dao throw this Exception.</p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class DaoException extends BaseException {
    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }
    
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DaoException(Throwable cause) {
        super(cause);
    }

}

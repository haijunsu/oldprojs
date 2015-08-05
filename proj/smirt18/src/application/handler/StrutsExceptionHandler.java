/*
 * @(#)StrutsExceptionHandler.java  2005-1-17
 * Copyright (c) 2005. All rights reserved. 
 * 
 * $Header: /navysu/Smirt18/src/application/handler/StrutsExceptionHandler.java,v 1.1 2005/01/20 03:14:28 navy Exp $
 * $Log: StrutsExceptionHandler.java,v $
 * Revision 1.1  2005/01/20 03:14:28  navy
 * Create SMiRT 18 project
 *
 */
package application.handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

/**
 * <p><b>Description</b></p>
 * <p>Handler all are uncatched exceptions in web application with struts. 
 * It will forword to an error page.
 * 
 * </p>
 * 
 * $Revision: 1.1 $
 * @author su_haijun  <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class StrutsExceptionHandler extends ExceptionHandler {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(StrutsExceptionHandler.class);

    /* (non-Javadoc)
     * @see org.apache.struts.action.ExceptionHandler#execute(java.lang.Exception, org.apache.struts.config.ExceptionConfig, org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(Exception exception, ExceptionConfig config,
            ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("execute(Exception, ExceptionConfig, ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - start");
        }

        logger.error("execute(Exception, ExceptionConfig, ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - uncatched exception.", exception);

        ActionForward returnActionForward = mapping.findForward("error");
        if (logger.isDebugEnabled()) {
            logger
                    .debug("execute(Exception, ExceptionConfig, ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse) - end");
        }
        return returnActionForward;
    }
}

/*
 * @(#)BaseDispachAction.java  2005-1-6
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header: /navysu/Smirt18/src/application/action/BaseDispachAction.java,v 1.1 2005/01/20 03:14:27 navy Exp $
 * $Log: BaseDispachAction.java,v $
 * Revision 1.1  2005/01/20 03:14:27  navy
 * Create SMiRT 18 project
 *
 */
package application.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import application.exception.BusinessServiceException;
import framework.util.http.RequestUtil;

/**
 * <p>
 * <b>Description </b>
 * </p>
 * Every business action must extend it.
 * <p>
 * </p>
 * 
 * $Revision: 1.1 $
 * 
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com </a>
 */
public class BaseDispachAction extends DispatchAction {
    /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(BaseDispachAction.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger
                    .debug("execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)"
                            + " - start");
        }
        ActionMessages _errors = new ActionMessages();
        try {
            //put some code here before business service
            // Click cancel button will release resource and return to home
            //saveHomeButton(request);
            ActionForward _returnActionForward = super.execute(mapping, form,
                    request, response);
            if (logger.isDebugEnabled()) {
                logger
                        .debug("execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)"
                                + " - end");
            }
            return _returnActionForward;
        } catch (BusinessServiceException e) {
            logger.error(RequestUtil.requestToString(request).toString());
            logger
                    .error(
                            "execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);
            _errors.add("Uncatched exception", new ActionMessage("errors.code."
                    + e.getErrCode()));
            saveErrors(request, _errors);
            return mapping.findForward("error");

        } catch (Exception e) {
            logger.error(RequestUtil.requestToString(request).toString());
            logger
                    .error(
                            "execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)",
                            e);
            _errors.add("Uncatched exception", new ActionMessage(
                    "errors.code.97"));
            saveErrors(request, _errors);
            return mapping.findForward("error");

        } finally {
            //write some code here after business service
        }
    }

    /**
     * Convenience method for removing the obsolete form bean.
     * 
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     */
    protected void removeFormBean(ActionMapping mapping,
            HttpServletRequest request) throws Exception {

        // Remove the obsolete form bean
        if (mapping.getAttribute() != null) {
            if ("request".equals(mapping.getScope())) {
                request.removeAttribute(mapping.getAttribute());
            } else {
                HttpSession _session = request.getSession();
                _session.removeAttribute(mapping.getAttribute());
            }
        }
    }
    
    protected void saveHomeButton(HttpServletRequest request, HttpServletResponse response) {
        SingleHtmlButton _btnHome = new SingleHtmlButton(request, response);
        _btnHome.setNameKey("form.button.home");
        _btnHome.setAction("/");
        request.setAttribute("backBtn", _btnHome);
    }
    
}
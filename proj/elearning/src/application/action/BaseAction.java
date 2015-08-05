/*
 * Created on 2005-5-29
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import application.exception.BusinessServiceException;
import framework.util.http.RequestUtil;

/**
 * @author xiongll
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseAction extends Action {

	   /**
     * Logger for this class
     */
    private static final Log logger = LogFactory
            .getLog(BaseAction.class);

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
            ActionForward _returnActionForward = performBusiness(mapping, form,
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

    protected abstract ActionForward performBusiness(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response);


}

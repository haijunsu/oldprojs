package com.navy.framework.web.action;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.navy.framework.exception.ServiceException;
import com.navy.framework.web.Constants;

public abstract class BaseAction extends DispatchAction {

	/**
	 * Logger for this class
	 */
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(BaseAction.class);

	protected static final String PAGE_MGMT = "mgmtPage";

	protected static final String PAGE_DETAIL = "detailPage";

	protected static final String PAGE_INPUT = "inputPage";

	protected static final String PAGE_ERROR = "error";

	protected static final String PAGE_LOGIN = "login";

	protected static final String PAGE_SUCCESS = "success";

	protected static final String PAGE_WARNING = "warn";

	protected static final String PAGE_WELCOME = "welcome";

	protected static final String IMAGE_DOWN = "/images/down.gif";

	protected static final String IMAGE_UP = "/images/up.gif";

	protected static final String CUSTOMIZE_MESSAGE = "customize.message";

	protected static final String EXCEPTION_INTERNAL_ERROR = "system.internal.err";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionForward _forward = mapping.findForward(PAGE_LOGIN);
		try {
			if (isCancelled(request)) {
				try {
					getMethod("cancel");
					return dispatchMethod(mapping, form, request, response,
							"cancel");
				} catch (NoSuchMethodException n) {
					logger.warn("No 'cancel' method found, returning null");
					return cancelled(mapping, form, request, response);
				}
			}
			// Check to see if methodName indicated by request parameter
			String actionMethod = getActionMethodWithMapping(request, mapping);

			if (actionMethod != null) {
				return dispatchMethod(mapping, form, request, response,
						actionMethod);
			} else {
				String[] rules = { "edit", "save", "search", "view" };
				for (int i = 0; i < rules.length; i++) {
					// apply the rules for automatically appending the method
					// name
					if (request.getServletPath().indexOf(rules[i]) > -1) {
						return dispatchMethod(mapping, form, request, response,
								rules[i]);
					}
				}
			}
			return super.execute(mapping, form, request, response);
		} catch (Exception e) {
			handleRequestException(request, e);
			_forward = mapping.findForward(PAGE_ERROR);
		} finally {
			if (form instanceof BaseForm) {
				BaseForm _baseForm = (BaseForm) form;
				saveOrderInfo(_baseForm, request);
			}
		}
		return _forward;
	}

	/**
	 * Convenience method to get the Configuration HashMap from the servlet
	 * context.
	 *
	 * @return the user's populated form from the session
	 */
	public Map getConfiguration() {
		Map config = (HashMap) getServlet().getServletContext().getAttribute(
				Constants.CONFIG);

		// so unit tests don't puke when nothing's been set
		if (config == null) {
			return new HashMap();
		}

		return config;
	}

	/**
	 * Gets the method name based on the mapping passed to it
	 */
	private String getActionMethodWithMapping(HttpServletRequest request,
			ActionMapping mapping) {
		return getActionMethod(request, mapping.getParameter());
	}

	/**
	 * Gets the method name based on the prepender passed to it.
	 */
	protected String getActionMethod(HttpServletRequest request, String prepend) {
		String name = null;

		// for backwards compatibility, try with no prepend first
		name = request.getParameter(prepend);
		if (name != null) {
			// trim any whitespace around - this might happen on buttons
			name = name.trim();
			// lowercase first letter
			return name.replace(name.charAt(0), Character.toLowerCase(name
					.charAt(0)));
		}

		Enumeration e = request.getParameterNames();

		while (e.hasMoreElements()) {
			String currentName = (String) e.nextElement();

			if (currentName.startsWith(prepend + ".")) {
				if (log.isDebugEnabled()) {
					log.debug("calling method: " + currentName);
				}

				String[] parameterMethodNameAndArgs = StringUtils.split(
						currentName, ".");
				name = parameterMethodNameAndArgs[1];
				break;
			}
		}

		return name;
	}

    /**
     * Convenience method for removing the obsolete form bean.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     */
    protected void removeFormBean(ActionMapping mapping, HttpServletRequest request) {
        // Remove the obsolete form bean
        if (mapping.getAttribute() != null) {
            if ("request".equals(mapping.getScope())) {
                request.removeAttribute(mapping.getAttribute());
            } else {
                HttpSession session = request.getSession();
                session.removeAttribute(mapping.getAttribute());
            }
        }
    }

    /**
     * Convenience method to update a formBean in it's scope
     *
     * @param mapping The ActionMapping used to select this instance
     * @param request The HTTP request we are processing
     * @param form    The ActionForm
     */
    protected void updateFormBean(ActionMapping mapping, HttpServletRequest request, ActionForm form) {
        // Remove the obsolete form bean
        if (mapping.getAttribute() != null) {
            if ("request".equals(mapping.getScope())) {
                request.setAttribute(mapping.getAttribute(), form);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute(mapping.getAttribute(), form);
            }
        }
    }

	protected String getMessageByKey(HttpServletRequest req, String key) {
		MessageResources _messageResources = getResources(req);
		return _messageResources.getMessage(getLocale(req), key);
	}

	protected Integer getIntegerByString(String str) {
		return isBlank(str) ? null : new Integer(str);
	}

	protected boolean isBlank(String str) {
		return StringUtils.isBlank(str);
	}

	/**
	 * If page need be sorted, please set orderby property/column in actionForm
	 * or give parameter in url request. Then you can access oldOrderby column
	 * and sorting image path.
	 *
	 * @param form
	 * @param req
	 */
	protected void saveOrderInfo(BaseForm form, HttpServletRequest req) {
		if (StringUtils.isBlank(form.getOrderby())) {
			return;
		}
		// save AscendingImage
		if (form.getIsOrderAscending()) {
			req.setAttribute("accendingImage", new StringBuffer(req
					.getContextPath()).append(IMAGE_UP).toString());
		} else {
			req.setAttribute("accendingImage", new StringBuffer(req
					.getContextPath()).append(IMAGE_DOWN).toString());
		}
		// save oldOrderby
		req.setAttribute("oldOrderby", form.getOrderby());
	}


	protected void handleRequestException(HttpServletRequest req, Exception e) {
		ActionMessages _messages = new ActionMessages();
		if (e instanceof ServiceException) {
			ServiceException _ex = (ServiceException) e;
			_ex.setLocaleValue(getLocale(req));
			_messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(CUSTOMIZE_MESSAGE, _ex.getMessage()));
		} else {
			logger.error("UnExpected Exceptin: " + e.getMessage(), e);
			_messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(EXCEPTION_INTERNAL_ERROR));
		}
		saveErrors(req, _messages);

	}

}

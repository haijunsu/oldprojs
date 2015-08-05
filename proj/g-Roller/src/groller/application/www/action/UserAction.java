/*
 * Created on 2003-10-31
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.www.action;

import groller.application.context.Global;
import groller.application.entity.User;
import groller.application.service.UserService;
import groller.application.www.helper.RequestResolver;
import groller.framework.exception.KnownException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class UserAction extends BaseDispatchAction {
	private UserService _service;
	
	public UserAction() {
		_service = Global.getGrollerServiceLocator().getUserService();
	}
	
	public ActionForward register(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception 
	{
		User dto = new User();
		RequestResolver.copyProperties(request, dto);
		dto = _service.registerNewUser(dto);
		return mapping.findForward("LOGON");
	}
	
	public ActionForward logon(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception 
	{
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		User dto = null;
		try {
			dto = _service.userLogon(account, password);
		} catch (KnownException e) {
			request.setAttribute("exception", e);
			return mapping.findForward("LOGON");
		}
		request.getSession().setAttribute(BaseDispatchAction.USER_IN_SESSION, dto);
		return mapping.findForward("INDEX");
	}
	

	public ActionForward logoff(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception 
	{
		request.getSession().removeAttribute(BaseDispatchAction.USER_IN_SESSION);
		return mapping.findForward("LOGON");
	}

	public ActionForward listAll(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception 
	{
		User[] dtos = _service.listAllUsers();
		request.setAttribute("dtos", dtos);
		return mapping.findForward("LIST");
	}
}

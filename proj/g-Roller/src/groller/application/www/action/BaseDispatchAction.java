/*
 * Created on 2003-11-1
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.www.action;

import groller.application.entity.User;
import groller.framework.exception.WebException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class BaseDispatchAction extends DispatchAction {
	private HttpServletRequest _request;

	public static final String USER_IN_SESSION = "user";
	
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception 
	{
		request.setCharacterEncoding("GB2312");
		_request = request;
		return super.execute(mapping, form, request, response);
	}
	
	protected User userAuthenticate() {
		User dto = (User) _request.getSession().getAttribute(BaseDispatchAction.USER_IN_SESSION);
		if(dto == null) {
			throw new WebException("ÓÃ»§Î´µÇÂ½£¡");
		}
		return dto;
	}
}

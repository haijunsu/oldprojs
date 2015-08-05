/*
 * Created on 2003-11-5
 */
package groller.application.rss;

import groller.application.context.Global;
import groller.application.entity.Post;
import groller.application.entity.User;
import groller.application.service.PostService;
import groller.application.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.RequestUtils;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class RssFeedAction extends Action {
	private PostService _pService;
	private UserService _uService;
	
	public RssFeedAction() {
		_pService = Global.getGrollerServiceLocator().getPostService();
		_uService = Global.getGrollerServiceLocator().getUserService();
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception 
	{
		String account = RequestUtils.getStringParameter(request, "account", "");
		User dto = _uService.findUserByAccount(account);
		request.setAttribute("dto", dto);

		Post[] dtos = _pService.getAllPostsOfUser(dto.getId());
		request.setAttribute("dtos", dtos);
		
		return mapping.findForward("FEED");
	}

}

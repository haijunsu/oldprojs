/*
 * Created on 2003-11-1
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.www.action;

import groller.application.context.Global;
import groller.application.entity.Post;
import groller.application.entity.Reply;
import groller.application.entity.User;
import groller.application.service.PostService;
import groller.application.www.helper.RequestResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.RequestUtils;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class PostAction extends BaseDispatchAction {
	private PostService _service;
	
	public PostAction() {
		_service = Global.getGrollerServiceLocator().getPostService();
	}
	
	public ActionForward list(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception 
	{
		Long userId = new Long(RequestUtils.getLongParameter(request, "id", -1));
		Post[] dtos = _service.getAllPostsOfUser(userId);
		request.setAttribute("dtos", dtos);
		return mapping.findForward("LIST");
	}
	
	public ActionForward view(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception 
	{
		Long postId = new Long(RequestUtils.getLongParameter(request, "id", -1));
		Post dto = _service.loadPostById(postId);
		request.setAttribute("dto", dto);
		Reply[] dtos = _service.getAllRepliesOfPost(postId);
		request.setAttribute("dtos", dtos);
		return mapping.findForward("VIEW");
	}

	public ActionForward addPost(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception 
	{
		userAuthenticate();
		return mapping.findForward("POST");
	}
	
	public ActionForward publish(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception 
	{
		Post dto = new Post();
		RequestResolver.copyProperties(request, dto);
		User user = userAuthenticate();
		dto = _service.publishNewPost(user.getId(), dto);
		request.setAttribute("dto", dto);
		request.setAttribute("dtos", new Reply[0]);
		return mapping.findForward("PUBLISH_SUCCESS");
	}	
	
	public ActionForward reply(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception 
	{
		Reply dto = new Reply();
		RequestResolver.copyProperties(request, dto);
		Long postId = new Long(RequestUtils.getLongParameter(request, "id", -1));
		_service.publishNewReply(postId, dto);
		return mapping.findForward("REPLY_SUCCESS");
	}
}

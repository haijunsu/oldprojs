/*
 * @(#)UserRegisterAction.java  2003-10-31
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.user;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

/**
 * <P>用户注册修改</P>
 * 
 * @version 0.1
 * @author navy
 */
public class UserRegisterAction extends Action {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(UserRegisterAction.class);

	/**
	 * 实例化
	 */
	public UserRegisterAction() {
		super();
	}

	/* （非 Javadoc）
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		ActionMessages messages = new ActionMessages();

		Locale locale = request.getLocale();
		MessageResources resources = getResources(request);

		UserForm userForm = (UserForm) form;
		UserRegisterBean urb = new UserRegisterBean();
		String strAction = userForm.getAction();

		try {
			if (strAction.equalsIgnoreCase("NEW")) {
				//新用户
				userForm.reset(mapping, request);
				userForm.setAction("CREATE");
				form = userForm;
				//根据USERTYPE来决定是注册员工或用户
				if (userForm.getUserType() != null
					&& userForm.getUserType().equalsIgnoreCase(
						UserType.EMPLOYEE)) {
					forward = mapping.findForward("registerEmployee");
				} else {
					forward = mapping.findForward("register");
				}
			}
			if (strAction.equalsIgnoreCase("CREATE")) {
				//创建用户
				if (userForm
					.getUserType()
					.equalsIgnoreCase(UserType.ASSOCIATOR)) {
					forward = mapping.findForward("register");
				} else {
					forward = mapping.findForward("registerEmployee");
				}
				urb.setForm(userForm);
				urb.insert();
				if (userForm
					.getUserType()
					.equalsIgnoreCase(UserType.ASSOCIATOR)) {
						messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.create.user"));
				} else {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.create.employee"));
				}
				forward = mapping.findForward("success");
			}
			if (strAction.equalsIgnoreCase("MODIFY")) {
				HttpSession session = request.getSession(false);
				//修改，根据USERID和USERTYPE来决定是显示修改内容
				if (userForm.getUserid() == null
					&& session.getAttribute("userid") == null)
					throw new UserException(UserException.NOT_LOGIN, "没有登录");
				if (userForm.getUserid() == null) {
					userForm.setUserid((String) session.getAttribute("userid"));
					userForm.setUserType(
						(String) session.getAttribute("userType"));
				}
				userForm.setAction("UPDATE");
				urb.setForm(userForm);
				urb.query();
				form = urb.getForm();
				if (userForm
					.getUserType()
					.equalsIgnoreCase(UserType.ASSOCIATOR)) {
					forward = mapping.findForward("register");
				} else {
					forward = mapping.findForward("registerEmployee");
				}
			}
			if (strAction.equalsIgnoreCase("UPDATE")) {
				//更新
				if (userForm
					.getUserType()
					.equalsIgnoreCase(UserType.ASSOCIATOR)) {
					forward = mapping.findForward("register");
				} else {
					forward = mapping.findForward("registerEmployee");
				}
				urb.setForm(userForm);
				urb.update();
				if (userForm
					.getUserType()
					.equalsIgnoreCase(UserType.ASSOCIATOR)) {
						messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.update.user"));
				} else {
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("success.update.employee"));
				}
				forward = mapping.findForward("success");
			}
		} catch (UserException ue) {
			log.debug(ue.getErrMessage(), ue);
			int errCode = ue.getErrCode();
			switch (errCode) {
				case UserException.USERID_EMPTY :
					errors.add(
						ActionErrors.GLOBAL_ERROR,
						new ActionError(
							"errors.required",
							resources.getMessage(
								locale,
								"form.prompt.username")));
					break;

				case UserException.USERID_NOT_EXISTED :
					errors.add(
						ActionErrors.GLOBAL_ERROR,
						new ActionError("error.username"));

					break;

				case UserException.PASSWORD_EMPTY :
					errors.add(
						ActionErrors.GLOBAL_ERROR,
						new ActionError(
							"errors.required",
							resources.getMessage(
								locale,
								"form.prompt.password")));
					break;

				case UserException.WRONG_PASSWORD :
					errors.add(
						ActionErrors.GLOBAL_ERROR,
						new ActionError("error.password"));

					break;

				case UserException.UPDATE_PASS_FAIL :
					errors.add(
						ActionErrors.GLOBAL_ERROR,
						new ActionError("error.changed.password"));

					break;
				case UserException.SYSTEM_ERROR :
					errors.add(
						ActionErrors.GLOBAL_ERROR,
						new ActionError("error.system"));
					break;

				case UserException.INSERT_INFORMATION_FAIL :
					errors.add(
						ActionErrors.GLOBAL_ERROR,
						new ActionError("error.insert"));
					break;

				case UserException.NOT_LOGIN :
					errors.add(
						ActionErrors.GLOBAL_ERROR,
						new ActionError("error.logonRequired"));
					break;

				case UserException.QUERY_INFORMATION_FAIL :
					errors.add(
						ActionErrors.GLOBAL_ERROR,
						new ActionError("error.query"));
					break;

				case UserException.UPDATE_INFORMATION_FAIL :
					errors.add(
						ActionErrors.GLOBAL_ERROR,
						new ActionError("error.update"));
					break;

				case UserException.USER_EXISTED :
					errors.add(
						ActionErrors.GLOBAL_ERROR,
						new ActionError("error.user.existed"));
					break;

				default :
					break;
			}

		}
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			if (!userForm.getAction().equalsIgnoreCase("UPDATE")
				&& !userForm.getAction().equalsIgnoreCase("CREATE")) {
				forward = mapping.findForward("failure");
			}
		}
		if (!messages.isEmpty()) {
			saveMessages(request, messages);
		}

		return forward;
	}

}

/*
 * @(#)LogonAction.java  2004-2-12
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package com.idn.user;

import org.apache.log4j.Logger;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.idn.menu.MenusBean;
import com.idn.util.FormatDate;

/**
 * <P>
 * 用户登录模块
 * </P>
 * 
 * @version 0.1
 * @author navy
 */
public class LogonAction extends Action {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LogonAction.class);

	/**
	 * Method execute
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//方法生命周期的标志
		long _llTime = System.currentTimeMillis();
		org.apache.struts.action.ActionErrors _errors = new org.apache.struts.action.ActionErrors();

		Locale _locale = request.getLocale();
		MessageResources _resources = getResources(request);

		LogonForm _logonForm = (LogonForm) form;
		LogonBean _logonBean = new LogonBean();
		HttpSession _session = null;
		String _strTemp = "";
		String _strLifeid = "";

		try {

			if (logger.isDebugEnabled()) {
				_strLifeid = Long.toString(_llTime)
						+ " - ";
				logger.debug(_strLifeid + "execute() - Begin logon ...");
			}

			try {
				if (logger.isDebugEnabled()) {
					logger.debug(_strLifeid
							+ "execute() - Checking MenusBean context ...");
				}
				//判断应用程序有没有初始化系统菜单
				if (MenusBean.getContextPath() == null) {
					//初始化菜单
					MenusBean.setContextPath(request.getContextPath());
					if (logger.isDebugEnabled()) {
						logger.debug(_strLifeid
								+ "execute() - Init MenuBean finished.");
					}
				}

				if (logger.isDebugEnabled()) {
					logger.debug(_strLifeid
							+ "execute() - Checking session ...");
				}
				//检查session，如果存在，则删除“userid”，否则创建session
				_session = request.getSession();

				if (logger.isDebugEnabled()) {
					logger.debug(_strLifeid + "execute() - session id: "
							+ _session.getId() + "/session create time: "
							+ _session.getCreationTime() + ". User ip: "
							+ request.getRemoteAddr() + "/"
							+ request.getRemoteHost() + "/request session id: "
							+ request.getRequestedSessionId());
				}

				//检查是不是已经使用别的用户名登录了？
				_strTemp = (String) _session.getAttribute("userid");
				
				while (_strTemp != null && !_strTemp.trim().equals("")
						&& !_strTemp.trim().equals(_logonForm.getUserid())) {

					logger.warn(_strLifeid
							+ "execute() - session existed and user's "
							+ "state is normal. userid is " + _strTemp
							+ ". Session will be destroy.");
					_session.invalidate();
					_session = request.getSession();
					if (logger.isDebugEnabled()) {
						logger.debug(_strLifeid
								+ "execute() - new session id: "
								+ _session.getId()
								+ "/new session create time: "
								+ _session.getCreationTime());
					}
					_strTemp = (String) _session.getAttribute("userid");
				}

				if (logger.isDebugEnabled()) {
					logger.debug(_strLifeid
							+ "execute() - checking user information ...");
				}
				if (_logonForm.getUserid() == null)
					throw new UserException(UserException.USERID_EMPTY,
							"Userid is empty!");
				if (_logonForm.getPassword() == null)
					throw new UserException(UserException.PASSWORD_EMPTY,
							"Password is empty!");
				if (logger.isDebugEnabled()) {
					logger
							.debug(_strLifeid
									+ "execute() - checking user infomation via database ...");
				}
				_logonBean.setForm(_logonForm);
				_logonBean.logon();
				_logonForm = _logonBean.getForm();
				if (logger.isDebugEnabled()) {
					logger
							.debug(_strLifeid
									+ "execute() - logon success. Put user info to session.");
				}
				_strTemp = _logonForm.getUserid();
				if (_strTemp != null)
					_session.setAttribute("userid", _strTemp);
				_strTemp = _logonForm.getUsername();
				if (_strTemp != null)
					_session.setAttribute("username", _strTemp);
				_strTemp = _logonForm.getPurview();
				if (_strTemp != null)
					_session.setAttribute("purview", _strTemp);
				_strTemp = _logonForm.getLocaleCode();
				if (_strTemp != null)
					_session.setAttribute("localeCode", _strTemp);
				_strTemp = _logonForm.getUserType();
				if (_strTemp != null)
					_session.setAttribute("userType", _strTemp);
				_strTemp = _logonForm.getUseridGroup();
				if (_strTemp != null)
					_session.setAttribute("begroup", _strTemp);
				_session.setAttribute("menuReload", "true");

				if (logger.isDebugEnabled()) {
					logger.debug(_strLifeid
							+ "execute() - put user info to session finished.");
				}
				if (logger.isInfoEnabled()) {
					logger.info(_strLifeid
							+ "execute() - "
							+ FormatDate.format(System.currentTimeMillis(),
									FormatDate.LONG_YYYY_M_D_HMSS) + ": "
							+ _logonForm.getUsername() + " from "
							+ request.getRemoteAddr() + " logon success.");
				}

				//移除没有用的FORMBEAN
				if (mapping.getAttribute() != null) {
					if (logger.isDebugEnabled()) {
						logger.debug(_strLifeid
								+ "execute() - clear nouse infomation.");
					}
					if ("request".equals(mapping.getScope())) {
						request.removeAttribute(mapping.getAttribute());

						if (logger.isDebugEnabled()) {
							logger
									.debug(_strLifeid
											+ "execute() - remove request attribute from mapping.");
						}
					} else {
						_session.removeAttribute(mapping.getAttribute());
						if (logger.isDebugEnabled()) {
							logger
									.debug(_strLifeid
											+ "execute() - remove session attribute from mapping.");
						}
					}
					if (logger.isDebugEnabled()) {
						logger.debug(_strLifeid
								+ "execute() - clear nouse infomation ok.");
					}
				}


				//由于使用框架，所以在JSP层处理失败联接
				/*
				 * if (logger.isDebugEnabled()) { logger.debug("检查有无旧的登录失败的链接
				 * ..."); } if (session.getAttribute("failUrl") != null) {
				 * String str = (String) session.getAttribute("failUrl");
				 * session.removeAttribute("failUrl"); if
				 * (logger.isDebugEnabled()) { logger.debug("导向旧的登录失败页面：" +
				 * str); } response.sendRedirect(str); return null; }
				 */
				//			if (logger.isDebugEnabled()) {
				//				logger.debug("没有登录失败的链接，设置当前位置为首页");
				//			}
				_session.setAttribute("urlname", "首页");
				request.setAttribute("lifecycle", "您生命周期为："
						+ FormatDate.format(_logonForm.getLiftBegin(),
								FormatDate.LONG_YYYY_MM_DD)
						+ " - "
						+ FormatDate.format(_logonForm.getLiftEnd(),
								FormatDate.LONG_YYYY_MM_DD));
				//_logonForm.reset(mapping, request);
				//			form = logonForm;

			} catch (UserException logonE) {
				if (logger.isDebugEnabled()) {
					logger.debug(_strLifeid + "Logon fail. "
							+ logonE.getErrMessage());
				}
				_logonForm.setPassword(null);
				_logonForm.setNewpass(null);
				_logonForm.setRepass(null);
				form = _logonForm;
				int errCode = logonE.getErrCode();
				switch (errCode) {
				case UserException.USERID_EMPTY:
					_errors.add("system.logon.error", new ActionError(
							"errors.required", _resources.getMessage(_locale,
									"form.prompt.username")));
					break;

				case UserException.USERID_NOT_EXISTED:
					_errors.add("system.logon.error", new ActionError(
							"error.username"));

					break;

				case UserException.PASSWORD_EMPTY:
					_errors.add("system.logon.error", new ActionError(
							"errors.required", _resources.getMessage(_locale,
									"form.prompt.password")));
					break;

				case UserException.WRONG_PASSWORD:
					_errors.add("system.logon.error", new ActionError(
							"error.password"));

					break;

				case UserException.UPDATE_PASS_FAIL:
					_errors.add("system.logon.error", new ActionError(
							"error.changed.password"));

					break;
				case UserException.NOT_PERMIT:
					_errors.add("system.logon.error", new ActionError(
							"error.not.permit"));

					break;
				case UserException.SYSTEM_ERROR:
					_errors.add("system.logon.error", new ActionError(
							"error.system"));
					break;

				case UserException.LIFE_INACTIVE:
					_errors.add("system.logon.error", new ActionError(
							"error.userid.inactive"));
					break;

				case UserException.NEED_GROUP_USERID_LOGON:
					_errors.add("system.logon.error", new ActionError(
							"error.need.userid.group.logon", _logonBean
									.getForm().getUseridGroup()));
					break;
				case UserException.DUPLICATED_LOGON:
					_errors.add("system.logon.error", new ActionError(
							"error.duplicated.logon"));
				default:
					break;
				}
			} catch (Exception e) {
				logger.error(_strLifeid + "fatal error", e);
				_errors.add("system.logon.error", new ActionError(
						"error.system"));
			}
			if (!_errors.isEmpty()) {
				saveErrors(request, _errors);
				return mapping.findForward("failure");
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			logger.fatal(_strLifeid + "execute() - Fatal error.", e);

			_errors.add("system.logon.error", new ActionError("error.system"));
			saveErrors(request, _errors);
			return mapping.findForward("failure");
		} finally {
			//release all variable
			_llTime = System.currentTimeMillis() - _llTime;

			if (logger.isDebugEnabled()) {
				logger.debug(_strLifeid + "execute() total time(ms)" + _llTime);
			}

			_errors = null;
			_locale = null;
			_resources = null;
			_logonForm = null;
			_logonBean = null;
			_session = null;
			_strTemp = "";
			_strLifeid = "";

		}
	}

}
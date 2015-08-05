/*
 * @(#)LogoffAction.java  2004-2-12
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package com.idn.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.idn.util.FormatDate;

/**
 * <P>用户注销模块</P>
 * 
 * @version 0.1
 * @author navy
 */
public class LogoffAction extends Action {

	/**
	 * 启用 commons-logging API 来进行日志记录
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(LogoffAction.class);

	/** 
	 * Method execute
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("开始注销登录状态 . . .");
		}

		javax.servlet.http.HttpSession session = request.getSession(false);
		if (session == null) {
			if (log.isDebugEnabled()) {
				log.debug("session 不存在，直接退出！");
			}
		} else {
			if (session.getAttribute("username") != null) {
				if (log.isInfoEnabled()) {
					log.info(
						FormatDate.format(
							System.currentTimeMillis(),
							FormatDate.LONG_YYYY_M_D_HMSS)
							+ ": "
							+ session.getAttribute("username")
							+ "从"
							+ request.getRemoteAddr()
							+ "IP地址退出登录！");
				}
			}
			if (log.isDebugEnabled()) {
				log.debug("session 存在，清除session ...");
			}
			session.invalidate();
			if (log.isDebugEnabled()) {
				log.debug("session清除成功，退出完成！");
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("导向跳转页面。");
		}
		return mapping.findForward("success");
	}

}

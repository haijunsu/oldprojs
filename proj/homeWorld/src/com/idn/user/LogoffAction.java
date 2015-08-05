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
 * <P>�û�ע��ģ��</P>
 * 
 * @version 0.1
 * @author navy
 */
public class LogoffAction extends Action {

	/**
	 * ���� commons-logging API ��������־��¼
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
			log.debug("��ʼע����¼״̬ . . .");
		}

		javax.servlet.http.HttpSession session = request.getSession(false);
		if (session == null) {
			if (log.isDebugEnabled()) {
				log.debug("session �����ڣ�ֱ���˳���");
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
							+ "��"
							+ request.getRemoteAddr()
							+ "IP��ַ�˳���¼��");
				}
			}
			if (log.isDebugEnabled()) {
				log.debug("session ���ڣ����session ...");
			}
			session.invalidate();
			if (log.isDebugEnabled()) {
				log.debug("session����ɹ����˳���ɣ�");
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("������תҳ�档");
		}
		return mapping.findForward("success");
	}

}

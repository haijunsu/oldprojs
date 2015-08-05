/*
 * @(#)QueryAction.java  2003-3-28
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.idn.util.MissingParameterException;
import com.idn.util.ServletTools;

/**
 * <P>ͨ�ò�ѯ����</P>
 * 
 * @version 0.1
 * @author �պ���
 */
public class QueryAction extends Action {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(QueryAction.class);

	/**
	 * ��ʾ��Ϣ
	 */
	private QueryContents qc = null;

	/**
	 * Constructor for QueryAction.
	 */
	public QueryAction() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		ActionForward forward = new ActionForward();
		ActionErrors errors = new ActionErrors();
		try {
			Locale locale = getLocale(request);
			log.debug(locale);
			MessageResources messages = getResources(request);

			//��RequestȡҪ��ʾҳ��
			String strPageName =
				ServletTools.getParameter(request, "pageName", false, null);
			setQc(mapping, form, request, response);

			request.setAttribute("qc", qc);
			if (strPageName == null)
				strPageName = qc.getFormula().getForwardPage().trim();
			if (strPageName.equals(""))
				strPageName = "success";
			log.debug(strPageName);
			if (strPageName.toUpperCase().endsWith(".JSP")) {
				forward.setContextRelative(true);
				if (!strPageName.startsWith("/")) {
					log.warn("ForwardҳΪJSP�ļ�����������/��ͷ�������Զ������˴���");
					strPageName = "/" + strPageName;
				}
				forward.setPath(strPageName);
			} else {
				forward = mapping.findForward(strPageName);
			}
		} catch (QueryException qe) {
			qc = null;
			log.debug("ͨ�ò�ѯ����" + qe.getMessage(), qe);
			errors.add("query.error", new ActionError("query.error"));
			forward = mapping.findForward("failure");
		} catch (Exception e) {
			qc = null;
			log.debug("error", e);
			errors.add(
				ActionErrors.GLOBAL_ERROR,
				new ActionError("system.error"));
			forward = mapping.findForward("failure");
		}
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			forward = mapping.findForward("failure");
		}
		return forward;
	}

	public void setQc(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws QueryException, MissingParameterException {
		Locale locale = getLocale(request);
		MessageResources messages = getResources(request);

		//��requestȡ�ò�ѯ��SQL������ǰ׺
		String strQueryPrefix =
			ServletTools.getParameter(request, "queryPrefix", true, "");

		//��request��ȡ��SQL���Ĳ���ֵ����������ֵ
		Hashtable hParam = new Hashtable();
		String strSqlParam[] =
			ServletTools.getParameterValues(request, "queryArg", false, null);
		Enumeration enumParamNames = request.getParameterNames();
		int iParam = 0;

		//����SQL���Ĳ�������
		String strName = null;
		while (enumParamNames.hasMoreElements()) {
			strName = (String) enumParamNames.nextElement();
			hParam.put(
				strName,
				ServletTools.getParameter(request, strName, true, ""));
			if (strName.startsWith("queryArg")) {
				iParam++;
			}
		}
		hParam.remove("nextPage");
		hParam.remove("reload");
		if (strSqlParam == null && iParam > 0) {
			strSqlParam = new String[iParam];
			for (int i = 0; i < strSqlParam.length; i++) {
				strSqlParam[i] =
					ServletTools.getParameter(
						request,
						"queryArg" + String.valueOf(i),
						true,
						"");
			}
		}

		//��ȡÿҳ��ʾ������
		int iRowsPerPage =
			Integer.parseInt(
				ServletTools.getParameter(request, "rowsPerPage", false, "0"));

		//��ȡҪ��ʾ������ҳ
		int iNextPage =
			Integer.parseInt(
				ServletTools.getParameter(request, "nextPage", false, "1"));

		//��ȡˢ�±�־
		boolean isReload =
			Boolean
				.valueOf(
					ServletTools.getParameter(
						request,
						"reload",
						false,
						"false"))
				.booleanValue();

		//�Ƿ�Ϊ��ӡҳ
		boolean isPrintPage =
			Boolean
				.valueOf(
					ServletTools.getParameter(
						request,
						"printPage",
						false,
						"false"))
				.booleanValue();

		//�Ƿ�Ϊ���浽Excel�ļ�
		boolean isSaveToExcel =
			Boolean
				.valueOf(
					ServletTools.getParameter(
						request,
						"saveToExcel",
						false,
						"false"))
				.booleanValue();
		//�������Ϊ�գ��������ϴβ�ѯ��ͬ
		if (qc != null && strQueryPrefix.equals(qc.getQueryPrefix())) {

			if (qc.getDb() == null)
				isReload = true;
			//�Ƿ�Ҫ�����ݿ���ˢ����ʾ
			if (isPrintPage) {
				log.debug("���ô�ӡҳ");
				iRowsPerPage = 0;
				qc.setPrintPage(isPrintPage);
			}
			if (isSaveToExcel) {
				log.debug("��浽Excel");
				iRowsPerPage = 0;
				qc.setSaveToExcel(isSaveToExcel);
			}

			if (iRowsPerPage != qc.getPagesCtrl().getRowsPerPage()) {
				log.debug("����ÿҳ��ʾ������");
				qc.getPagesCtrl().setRowsPerPage(iRowsPerPage);
				isReload = true;
			}

			if (strSqlParam == null && qc.getSqlParam() != null) {
				log.debug("����SQL����Ϊnull");
				qc.setSqlParam(strSqlParam);
				isReload = true;
			}

			if (strSqlParam != null) {
				if (qc.getSqlParam() != null) {
					if (strSqlParam.length == qc.getSqlParam().length) {
						for (int i = 0; i < strSqlParam.length; i++) {
							if (!strSqlParam[i].equals(qc.getSqlParam()[i])) {
								log.debug("����SQL����");
								qc.setSqlParam(strSqlParam);
								isReload = true;
								break;
							}
						}
					}
				} else {
					log.debug("����SQL����");
					qc.setSqlParam(strSqlParam);
					isReload = true;
				}
			}

			if (!hParam.isEmpty()) {
				if (!hParam.equals(qc.getFormula().getRequestParam())) {
					log.debug("����Request����");
					qc.getFormula().setRequestParam(hParam);
					qc.getPagesCtrl().setRequestParam(hParam);
					isReload = true;
				}
			}

			if (isReload) {
				qc.prepareQuery();
				qc.getPagesCtrl().setCurrentPage(iNextPage);
				qc.query();
			} else {
				if (qc.getPagesCtrl().getRowsPerPage() > 0) {
					int iCurrentRowsCount =
						qc.getPagesCtrl().getCurrentRowsCount();
					qc.getPagesCtrl().setCurrentPage(iNextPage);
					log.debug(
						Integer.toString(qc.getPagesCtrl().getMaxPages()));
					log.debug(
						Integer.toString(
							qc.getPagesCtrl().getCurrentRowsCount()));
					log.debug(
						Integer.toString(qc.getPagesCtrl().getRealRowsCount()));
					if (qc.getPagesCtrl().getCurrentRowsCount()
						> iCurrentRowsCount) {
						qc.prepareQuery();
						qc.getPagesCtrl().setCurrentPage(iNextPage);
						qc.query();
					}
				}
			}
		} else {
			qc = new QueryContents();
			qc.setQueryPrefix(strQueryPrefix);
			qc.setSqlParam(strSqlParam);
			qc.getPagesCtrl().setRowsPerPage(iRowsPerPage);
			qc.getPagesCtrl().setBaseUrl(request.getRequestURI());
			qc.getFormula().setRequestParam(hParam);
			qc.getPagesCtrl().setRequestParam(hParam);
			qc.setPrintPage(isPrintPage);
			qc.setSaveToExcel(isSaveToExcel);
			qc.prepareQuery();
			qc.getPagesCtrl().setCurrentPage(iNextPage);
			log.debug(
				Integer.toString(qc.getPagesCtrl().getCurrentRowsCount()));
			log.debug(Integer.toString(qc.getPagesCtrl().getRealRowsCount()));
			log.debug(Integer.toString(qc.getPagesCtrl().getMaxPages()));
			log.debug(Integer.toString(qc.getPagesCtrl().getCurrentPage()));
			qc.query();
		}
	}

	public QueryContents getQc() {
		return qc;
	}
}

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
 * <P>通用查询操作</P>
 * 
 * @version 0.1
 * @author 苏海军
 */
public class QueryAction extends Action {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(QueryAction.class);

	/**
	 * 显示信息
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

			//从Request取要显示页面
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
					log.warn("Forward页为JSP文件，但不是以/开头，程序将自动修正此错误");
					strPageName = "/" + strPageName;
				}
				forward.setPath(strPageName);
			} else {
				forward = mapping.findForward(strPageName);
			}
		} catch (QueryException qe) {
			qc = null;
			log.debug("通用查询错误：" + qe.getMessage(), qe);
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

		//从request取得查询的SQL语名的前缀
		String strQueryPrefix =
			ServletTools.getParameter(request, "queryPrefix", true, "");

		//从request中取得SQL语句的参数值和其它参数值
		Hashtable hParam = new Hashtable();
		String strSqlParam[] =
			ServletTools.getParameterValues(request, "queryArg", false, null);
		Enumeration enumParamNames = request.getParameterNames();
		int iParam = 0;

		//计算SQL语句的参数个数
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

		//获取每页显示的行数
		int iRowsPerPage =
			Integer.parseInt(
				ServletTools.getParameter(request, "rowsPerPage", false, "0"));

		//获取要显示的数据页
		int iNextPage =
			Integer.parseInt(
				ServletTools.getParameter(request, "nextPage", false, "1"));

		//获取刷新标志
		boolean isReload =
			Boolean
				.valueOf(
					ServletTools.getParameter(
						request,
						"reload",
						false,
						"false"))
				.booleanValue();

		//是否为打印页
		boolean isPrintPage =
			Boolean
				.valueOf(
					ServletTools.getParameter(
						request,
						"printPage",
						false,
						"false"))
				.booleanValue();

		//是否为保存到Excel文件
		boolean isSaveToExcel =
			Boolean
				.valueOf(
					ServletTools.getParameter(
						request,
						"saveToExcel",
						false,
						"false"))
				.booleanValue();
		//结果集不为空，并且与上次查询相同
		if (qc != null && strQueryPrefix.equals(qc.getQueryPrefix())) {

			if (qc.getDb() == null)
				isReload = true;
			//是否要从数据库中刷新显示
			if (isPrintPage) {
				log.debug("设置打印页");
				iRowsPerPage = 0;
				qc.setPrintPage(isPrintPage);
			}
			if (isSaveToExcel) {
				log.debug("另存到Excel");
				iRowsPerPage = 0;
				qc.setSaveToExcel(isSaveToExcel);
			}

			if (iRowsPerPage != qc.getPagesCtrl().getRowsPerPage()) {
				log.debug("设置每页显示的行数");
				qc.getPagesCtrl().setRowsPerPage(iRowsPerPage);
				isReload = true;
			}

			if (strSqlParam == null && qc.getSqlParam() != null) {
				log.debug("更新SQL参数为null");
				qc.setSqlParam(strSqlParam);
				isReload = true;
			}

			if (strSqlParam != null) {
				if (qc.getSqlParam() != null) {
					if (strSqlParam.length == qc.getSqlParam().length) {
						for (int i = 0; i < strSqlParam.length; i++) {
							if (!strSqlParam[i].equals(qc.getSqlParam()[i])) {
								log.debug("更新SQL参数");
								qc.setSqlParam(strSqlParam);
								isReload = true;
								break;
							}
						}
					}
				} else {
					log.debug("更新SQL参数");
					qc.setSqlParam(strSqlParam);
					isReload = true;
				}
			}

			if (!hParam.isEmpty()) {
				if (!hParam.equals(qc.getFormula().getRequestParam())) {
					log.debug("更新Request参数");
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

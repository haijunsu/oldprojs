/*
 * @(#)PrintAction.java  2003-9-8
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package printsystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.idn.print.PrintController;

/**
 * <P>打印/显示控制程序</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PrintAction extends Action {

	/**
	 * 构造函数
	 */
	public PrintAction() {
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
		PrintForm pf = (PrintForm) form;
		String strAction = pf.getAction();

		if (strAction.equalsIgnoreCase("save")) {
			DownloadExcel dlExcel = new DownloadExcel();
			return dlExcel.execute(mapping, form, request, response);
		}
		if (strAction.equalsIgnoreCase("view")) {
			String strQueryid = pf.getQueryid();
			String strSqlWhere = pf.getSqlwhere();
			PrintController pctrl = new PrintController(strQueryid, strSqlWhere);
			pctrl.getPrintHtmlView().setDisplayOnly(true);
			pctrl.getPrintHtmlView().setTableStyle("table2border");
			pctrl.getPrintHtmlView().setTableHeaderStyle("table2titletd");
			pctrl.getPrintHtmlView().setTdStyle("table2textlefttd");
			pf.setHtmlContents(pctrl.getPrintHtmlView().getHtmlContents());
		}
		if (strAction.equalsIgnoreCase("print")) {
			String strQueryid = pf.getQueryid();
			String strSqlWhere = pf.getSqlwhere();
			PrintController pctrl = new PrintController(strQueryid, strSqlWhere);
			pctrl.getPrintHtmlView().setDisplayOnly(false);
			pf.setHtmlContents(pctrl.getPrintHtmlView().getHtmlContents());
		}
		form = pf;
		//TODO 设置打印的控制文件
		return null;
	}

}

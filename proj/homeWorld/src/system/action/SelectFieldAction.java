/*
 * @(#)SelectFieldAction.java  2003-7-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import commsearch.*;
import system.fun.FunPurview;

/**
 * <P>菜单管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class SelectFieldAction extends Action {
	private int intTemp = 0;
	private String strSql;
	private String[] strTemp = null;
	ActionErrors errors = new ActionErrors();
	FunPurview funPurview=new FunPurview();
	/**
	 * 构造函数
	 */
	public SelectFieldAction() {
		super();
	}

	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,	HttpServletResponse response)throws Exception {

		errors.clear();
		errors.add("header",new ActionError("errors.header"));
		errors.add("footer",new ActionError("errors.footer"));

		String dwbh = ((String)request.getParameter("arg0")).trim();
		String uid = ((String)request.getParameter("arg1")).trim();
	/*	String num="1";
		if(dwbh.equals("")||dwbh==null){
			dwbh = ((String) PropertyUtils.getSimpleProperty(form, "page")).trim();
			uid = ((String) PropertyUtils.getSimpleProperty(form, "page")).trim();
			num="2";
		}
		PropertyUtils.setSimpleProperty(form, "dwbh",dwbh);
		PropertyUtils.setSimpleProperty(form, "uid",uid);
		PropertyUtils.setSimpleProperty(form, "num",num);*/
			
		dwbh=  com.idn.util.CommonTools.stringReplace(dwbh,"*","%") ;
		String[][] show=null;		
		String[] title=null;
		String[] hiddend=null;
		String[] size=null;
		String[] qtitle=null;
		
		CommActQuery caq = new CommActQuery(uid,dwbh);
		show = caq.getData();
		if(show==null){
			PropertyUtils.setSimpleProperty(form, "mark","1");
			errors.add("errormessage",new ActionError("SelectField.nofield"));
			saveErrors(request, errors);
			return(mapping.findForward("success"));
		}	
		title = caq.getFields("FHEADERC");
		hiddend = caq.getFields("QSATTRI");	
		size = caq.getFields("FLENGTH");
		qtitle = caq.getFields("QNAMEC");
		for(int i=0;i<size.length;i++){
			if(Integer.parseInt(size[i])>20)
				size[i]="20";
		}
		
		PropertyUtils.setSimpleProperty(form, "cellcount",String.valueOf(title.length));
		PropertyUtils.setSimpleProperty(form, "show",show);
		PropertyUtils.setSimpleProperty(form, "title",title);
		PropertyUtils.setSimpleProperty(form, "hiddend",hiddend);
		PropertyUtils.setSimpleProperty(form, "size",size);
		PropertyUtils.setSimpleProperty(form, "qtitle",qtitle[0]);
		return(mapping.findForward("success"));
	}

}

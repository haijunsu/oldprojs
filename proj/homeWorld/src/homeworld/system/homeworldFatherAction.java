/*
 * @(#)homewordFatherAction.java  2004-2-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.system;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author lyc
 */


import java.io.IOException;
//import java.util.Date;

//import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
//import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

//import com.idn.log.LogWrapper;
import com.idn.secure.SecureAction;
//import com.idn.sql.*;
//import com.idn.util.FormatDate;

/**
 * <P>置装费表管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class homeworldFatherAction extends SecureAction {
	private int intTemp = 0;
	private String strSql;
	private String[] strTemp = null;
	ActionErrors errors = new ActionErrors();
	ActionMessages messages = new ActionMessages();
	/**
	 * 构造函数
	 */
	public homeworldFatherAction() {
		super();
	}

	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeme(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,	HttpServletResponse response)throws IOException, ServletException {
		
		//初始化error对象		
		errors.clear();
		errors.add("header",new ActionError("errors.header"));
		errors.add("footer",new ActionError("errors.footer"));
			

	try{

		//添加标题	
		PropertyUtils.setSimpleProperty(form, "title","");
		PropertyUtils.setSimpleProperty(form, "id","");
		PropertyUtils.setSimpleProperty(form, "show","");
		//添加标题
		
		String temp="";
	
		//1 : 修改(默认)  2 ：查询
		String se_up=(String)request.getParameter("se_up");
		if(se_up==null){se_up=(String) PropertyUtils.getSimpleProperty(form, "se_up");}
		PropertyUtils.setSimpleProperty(form,"se_up",se_up);
		
		//取标志
		String flag=(String) PropertyUtils.getSimpleProperty(form, "flag");
		String page=(String) PropertyUtils.getSimpleProperty(form, "page");
		String where=(String) PropertyUtils.getSimpleProperty(form, "where");
		 
		//首次直接返回
		if (flag.equals("")){return mapping.findForward("success");}
		
		//在次返回
		if(flag.equals("selectexic")){
			temp=setFormbeen(form,request,where);
			if (temp.equals("0")){saveErrors(request, errors);}
			return mapping.findForward("success");
		}
		
		
		
		/*
		 *添加代码
		 */
		 
		 

		/*
		 *添加代码
		 */
		 

		//在次返回
		if(flag.equals("select")){
			temp=setFormbeen(form,request,where);
			if (temp.equals("0")){saveErrors(request, errors);}
			return mapping.findForward("success");
		}
		//向formbean中赋值
		
		return mapping.findForward("success");	
		
	} catch (Exception e){
		errors.add("errormessage",new ActionError("Database.formbean"));
		saveErrors(request, errors);
		return(mapping.findForward("success"));
	}

}

		/**
		 * 将指定类代码内容传入显示FormBeen(page=3)
		 * 
		 * @param ActionForm 显示FormBeen
		 * @param String 人员ID
		 * @return String 1、成功；0、不成功；
		 * @exception Exception
		 */
		public String setFormbeen(ActionForm form,HttpServletRequest request,String where) throws Exception {
			String[] seq=null; 
		
			/*
			 *添加代码
			 */
		 
		 

			/*
			 *添加代码
			 */
		 			
			PropertyUtils.setSimpleProperty(form, "page","2");
			return "1";
	}

	/* (non-Javadoc)
	 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 
		return  executeme( mapping,form,request,response);
	}

}

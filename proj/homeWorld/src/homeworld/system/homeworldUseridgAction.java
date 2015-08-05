/*
 * @(#)homewordUseridgAction.java  2004-2-13
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
import java.util.Vector;

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


//import com.idn.property.CodesManager;
import system.fun.FunPurview;
import com.idn.log.LogWrapper;
import com.idn.secure.SecureAction;
import com.idn.sql.DataBean;
import com.idn.sql.DynaSqlBean;
//import com.idn.util.CommonTools;

/**
 * <P>菜单管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class homeworldUseridgAction extends SecureAction {

	private static LogWrapper log = new LogWrapper("UserForm");

	ActionErrors errors = new ActionErrors();
	FunPurview funPurview=new FunPurview();
	/**
	 * 构造函数
	 */
	public homeworldUseridgAction() {
		super();
	}

	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeme(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,	HttpServletResponse response)throws IOException, ServletException{

		errors.clear();
		errors.add("header",new ActionError("errors.header"));
		errors.add("footer",new ActionError("errors.footer"));
		
		DynaSqlBean dybBean = new DynaSqlBean();
		Vector vecTemp=new Vector();
		String strFlag;

try{
	
			String currrowshow;
			String[] strSql=null;
			
			strFlag=((String) PropertyUtils.getSimpleProperty(form, "flag"));
			
			
			if (strFlag.equalsIgnoreCase("")){
				setFormbeen(form);
				return(mapping.findForward("success"));
			}
	
			
			String[] rowstate=((String[]) PropertyUtils.getProperty(form, "rowstate"));
			String[] userid=((String[]) PropertyUtils.getProperty(form, "userid"));
			String[] useridg=((String[]) PropertyUtils.getProperty(form, "useridgid"));
			
			for(int i=0 ;i<rowstate.length;i++){
				if(rowstate[i].equals("1")){
					vecTemp.addElement("update users set useridg='"+useridg[i]+"' where userid='"+userid[i]+"'");
				}	
				
			}
	
			strSql = (String[]) vecTemp.toArray(new String[0]);		
			//执行sql语句
			try{
				dybBean.setSql(strSql);
				dybBean.executeBatch();
			} catch (Exception e){
				errors.add("errormessage",new ActionError("Database.writedb"));
				saveErrors(request, errors);
			}
			
			//setFormbeen(form);
	
			return(mapping.findForward("success"));
		} catch (Exception e){
			errors.add("errormessage",new ActionError("Database.formbeen"));
			saveErrors(request, errors);
			return(mapping.findForward("success"));
		}
		
	}


	/**
	 * 将指定类代码内容传入显示FormBeen(小表格)
	 * 
	 * @param ActionForm 显示FormBeen
	 * @param String 工资卡号
	 * @return String 1、成功；0、不成功；
	 * @exception Exception
	 */
	public String setFormbeen(ActionForm form) throws Exception{
		
		String strSql,key;
		DataBean dbBean = new DataBean();
		strSql="select userid,lifebeg,lifeend,useridg,vndnam " +
			"from users,vndinfo where userid=vndnum order by userid";

		dbBean.executeSelect(strSql);
	
		int count;
		count=dbBean.getRowCount();
	if (count<2){
		 count=2;
	}

	String[] userid= new String[count];//用户id
	String[] usershow= new String[count];//用户姓名
	
	String[] lifebeg = new String[count];
	String[] lifeend = new String[count];
	
	String[] useridgshow = new String[count];
	String[] useridgid = new String[count];
	
		for(int i=0;i<count;i++){
			userid[i]=dbBean.getElementValue(i,0).trim() ;
			usershow[i]=dbBean.getElementValue(i,"vndnam").trim() ;

			lifebeg[i]=dbBean.getElementValue(i,1).trim() ;
			lifeend[i]=dbBean.getElementValue(i,2).trim() ;

			if (!lifeend[i].equals("")){
				lifebeg[i]=com.idn.util.FormatDate.format(Long.parseLong(lifebeg[i]),"yyyy-MM-dd");	
				lifeend[i]=com.idn.util.FormatDate.format(Long.parseLong(lifeend[i]),"yyyy-MM-dd");
			}
			
			useridgid[i]=dbBean.getElementValue(i,3).trim() ;
			useridgshow[i]=dbBean.getElementValue(i,4).trim();			
		}

		PropertyUtils.setSimpleProperty(form, "userid",userid);
		PropertyUtils.setSimpleProperty(form, "usershow",usershow);
		
		PropertyUtils.setSimpleProperty(form, "lifebeg",lifebeg);
		PropertyUtils.setSimpleProperty(form, "lifeend",lifeend);

		PropertyUtils.setSimpleProperty(form, "useridgid",useridgid);
		PropertyUtils.setSimpleProperty(form, "useridgshow",useridgshow);
		
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

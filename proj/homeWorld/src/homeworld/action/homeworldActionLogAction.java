/*
 * @(#)homewordOrderAction.java  2004-1-8
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.action;

import java.io.IOException;
import java.util.Date;
//import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
//import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//import com.idn.property.CodesManager;
import com.idn.secure.SecureAction;
import com.idn.sql.DataBean;
//import com.idn.sql.DynaSqlBean;
import com.idn.util.FormatDate;
//import com.idn.util.DecimalTools;


/**
 * <P>菜单管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class homeworldActionLogAction extends  SecureAction {
	ActionErrors errors = new ActionErrors();

	/**
	 * 构造函数
	 */
	public homeworldActionLogAction() {
		super();
	}

	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeme(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,	HttpServletResponse response)throws  IOException, ServletException {

			errors.clear();
			errors.add("header",new ActionError("errors.header"));
			errors.add("footer",new ActionError("errors.footer"));	
	
			HttpSession hs = request.getSession(false);
			String uname=(String)hs.getAttribute("userid");
			

						
			//TODO 临时用来监测用
			long l_begin,l_end;
			l_begin = System.currentTimeMillis();		
			commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"ActionLog","B","LYC0000000",0);
							
							
							
			if(uname==null){
				errors.add("errormessage",new ActionError("NoName"));
				saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"ActionLog","E","LYC5555555",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			if(uname.trim().equals("")){
				errors.add("errormessage",new ActionError("NoName"));
				saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"ActionLog","E","LYC6666666",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			
			
			try{		
			//page--> 0：录入 1：更改
			
			String queryid = (String)request.getParameter("queryid");
			String flag = ((String) PropertyUtils.getSimpleProperty(form, "flag")).trim();
			
			if (queryid==null){
				queryid="";
			}
			String title=((String) PropertyUtils.getSimpleProperty(form, "title")).trim();
			String qtable=((String) PropertyUtils.getSimpleProperty(form, "qtable")).trim();
					
			if(flag.equals("") ){
				DataBean dbBean = new DataBean();
				try{
					dbBean.executeSelect("select  qnamec,qtable from querys where queryid='"+queryid.toUpperCase().trim()+"'");
				} catch (Exception e) {
					e.printStackTrace();
					errors.add("errormessage",new ActionError("Datebase.readdb"));
				}
				PropertyUtils.setSimpleProperty(form, "title" ,dbBean.getElementValue(0,"qnamec").trim()+"日志");
				qtable=dbBean.getElementValue(0,"qtable").trim();
				PropertyUtils.setSimpleProperty(form, "qtable" ,qtable);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"ActionLog","E","LYC8888881",l_end - l_begin);
				return(mapping.findForward("success"));
			}

			if(setFormbeen(form,flag,qtable).equals("0")){
				saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"ActionLog","E","LYC8888882",l_end - l_begin);
				return(mapping.findForward("err"));
			}
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"ActionLog","E","LYC8888888",l_end - l_begin);
			return(mapping.findForward("success"));
			
		} catch (Exception e){
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Datebase.formbean"));
			saveErrors(request, errors);
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"ActionLog","C","LYC9999999",l_end - l_begin);
			return(mapping.findForward("err"));
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
	public String setFormbeen(ActionForm form,String where,String qtable) throws Exception{
				DataBean dbBean = new DataBean();
				
				String ss=where;

				Date today=new Date();		
		
				String hiddenshow="1";
				if (where.indexOf("act_datetime")==-1){
					ss=FormatDate.format(today,"yyyy-MM");
					where=where+"and act_datetime like '"+ss+"%'";
					hiddenshow="0";	
				}
				
				
				//VNDINFO.VNDNAM
		
				String strsql="select act_datetime,VNDNAM,act_memo,act_me from actionlog,VNDINFO where act_user=VNDNuM and act_table='"+qtable+"' and "+where+" order by act_datetime desc";   
				try{
					dbBean.executeSelect(strsql);
				} catch (Exception e) {
					e.printStackTrace();
					errors.add("errormessage",new ActionError("Datebase.readdb"));
					return "0";
				}
		int count=dbBean.getRowCount();
		
		String datetime[]=new String[count];
		String user[]=new String[count];
		String memo[]=new String[count];
		for(int i=0;i<count;i++){
			datetime[i]=dbBean.getElementValue(i,"act_datetime").trim() ;
			
			user[i]=dbBean.getElementValue(i,"VNDNAM").trim() ;
			//user[i]=getNamec(dbBean.getFieldValue("act_user",i).trim());
			
			memo[i]=dbBean.getElementValue(i,"act_memo").trim() +dbBean.getElementValue(i,"act_me").trim();
		}
		
		if(count!=0){
			if(hiddenshow=="0"){
				hiddenshow="1";
			}
		}
		
		//PropertyUtils.setSimpleProperty(form, "flag" ,"");
		
		PropertyUtils.setSimpleProperty(form, "hiddenshow" ,hiddenshow);
		
		String date=((String) PropertyUtils.getSimpleProperty(form, "date")).trim();
		if(date.equals("")){
			PropertyUtils.setSimpleProperty(form, "date" ,FormatDate.format(today,"yyyy"));
		}
		
		if (count==0){
			datetime=null;
		}
		PropertyUtils.setSimpleProperty(form, "datetime" ,datetime);
		PropertyUtils.setSimpleProperty(form, "user" ,user);
		PropertyUtils.setSimpleProperty(form, "memo" ,memo);

		return "1";
	}

	public void resetFormbeen(ActionForm form,HttpServletRequest request) throws Exception{
 
	}

	public String getNamec(String name) throws Exception{
		String sql="select namec from employee where userid=upper('"+name+"')";
		DataBean dbBean = new DataBean();	
		try{
		dbBean.executeSelect(sql);
		} catch (Exception e) {
			e.printStackTrace();
		errors.add("errormessage",new ActionError("Datebase.readdb"));
		return "";
		}
		return dbBean.getElementValue(0,0);
	}

	/* (non-Javadoc)
	 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		return  executeme( mapping,form,request,response);
	}
	
	
}

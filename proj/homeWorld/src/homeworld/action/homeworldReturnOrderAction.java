/*
 * @(#)homewordOrderAction.java  2004-1-8operator
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.idn.secure.SecureAction;
import com.idn.sql.DataBean;
import com.idn.sql.DynaSqlBean;

import com.idn.util.CommonTools;

import commsearch.util.CommDate;

import java.util.Date;


/**
 * <P>菜单管理</P>
 * 
 * @version 0.1
 * @author ht
 */
public class homeworldReturnOrderAction extends SecureAction {
	ActionErrors errors = new ActionErrors();

	/**
	 * 构造函数
	 */
	public homeworldReturnOrderAction() {
		super();
	}

	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeme(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,	HttpServletResponse response)throws IOException, ServletException {

			errors.clear();
			errors.add("header",new ActionError("errors.header"));
			errors.add("footer",new ActionError("errors.footer"));	
	
			HttpSession hs = request.getSession();
			String uname=(String)hs.getAttribute("userid");
			

						
			//TODO 临时用来监测用
			long l_begin,l_end;
			l_begin = System.currentTimeMillis();		
			commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"ReturnOrder","B","LYC0000000",0);
			
			
			if(uname==null){
				errors.add("errormessage",new ActionError("NoName"));
				saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"ReturnOrder","E","LYC5555555",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			if(uname.trim().equals("")){
				errors.add("errormessage",new ActionError("NoName"));
				saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"ReturnOrder","E","LYC6666666",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			
			
			try{
			PropertyUtils.setSimpleProperty(form, "message" ,"0");
			
			Date now=new Date();
			PropertyUtils.setSimpleProperty(form, "nowdate" ,com.idn.util.FormatDate.format(now,"yyyy-MM-dd"));
			
			
			String temp=request.getParameter("eponum");
			String queryid=request.getParameter("queryid");
			String selectwhere=request.getParameter("selectwhere");
			
			if(selectwhere==null){
				queryid=(String)PropertyUtils.getSimpleProperty(form,"queryid");
				selectwhere=(String)PropertyUtils.getSimpleProperty(form,"selectwhere");
			}
			String stemp=selectwhere;
			
			if(stemp==null)
				stemp="";
			if(stemp.equals("")){
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"ReturnOrder","E","LYC3333333",l_end - l_begin);
				return(mapping.findForward("err"));
			}
			
			PropertyUtils.setSimpleProperty(form,"queryid",queryid);
			PropertyUtils.setSimpleProperty(form,"selectwhere",selectwhere);

			String flag =((String)PropertyUtils.getSimpleProperty(form,"flag")).trim();
			
			if(flag.equals("")){
				setFormbeen(form,request,selectwhere,uname);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"ReturnOrder","E","LYC8888881",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			
			String epoflg=(String)PropertyUtils.getSimpleProperty(form,"epoflg");	
			String freeformstate=(String)PropertyUtils.getSimpleProperty(form,"freeformstate");

			String eponum=(String)PropertyUtils.getSimpleProperty(form,"eponum");
			String epostr=(String)PropertyUtils.getSimpleProperty(form,"epostr");
			String epostn=(String)PropertyUtils.getSimpleProperty(form,"epostn");
			String eposdt=(String)PropertyUtils.getSimpleProperty(form,"eposdt");
			String epordt=(String)PropertyUtils.getSimpleProperty(form,"epordt");
			
			eposdt=CommonTools.stringReplace(eposdt,"-","").trim() ;
			eposdt=eposdt.substring(2);
			epordt=CommonTools.stringReplace(epordt,"-","").trim() ;
			epordt=epordt.substring(2);
			
			String epordtm=(String)PropertyUtils.getSimpleProperty(form,"epordtmshow");
			String epordtm2=(String)PropertyUtils.getSimpleProperty(form,"epordtm2show");
			String operator=(String)PropertyUtils.getSimpleProperty(form,"operatorid");
			String operdate=(String)PropertyUtils.getSimpleProperty(form,"operdate");
			String opertime=(String)PropertyUtils.getSimpleProperty(form,"opertime");
			
			//SQLBean sis=new SQLBean();

			DataBean dbBean=new DataBean();
			String strsql1="select * from ORDERR where "+selectwhere+"";
			try{
				dbBean.executeSelect(strsql1);
			}catch(Exception e){
				e.printStackTrace();
				errors.add("errormessage",new ActionError("Datebase.readdb"));
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"ReturnOrder","E","LYC8888882",l_end - l_begin);
				return(mapping.findForward("success")); 
			}
					
			String strsql[]=new String[1];
			if(dbBean.getRowCount()==0){           
				strsql[0]="insert into ORDERR (eponum,epostr,epostn,eposdt,epordt,epordtm,epoflg,operator,operdate,opertime,epordtm2) values('"+eponum+"' ,'"+epostr+"' ,'"+epostn+"','"+eposdt+"','"+epordt+"','"+epordtm+"','0','"+operator+"','"+operdate+"','"+opertime+"','"+epordtm2+"')";
				 //第一次进入并且orderr中不存在记录
				//int retrunint1=sis.executeSQL(strsql);
				
				//写日志（XIAOAI处理）
				commsearch.util.CommActionLog  cal= new commsearch.util.CommActionLog();
			
				cal.setAct_user(uname);
				cal.setAct_from("homeworldReturnOrder");
				cal.setAct_do("INS");
				cal.setAct_key(temp);
				cal.setAct_table("ORDERR");
				cal.setAct_ip(request.getRemoteAddr());
				cal.setAct_memo("回复插入定单编号为" + temp);
				cal.setAct_me("");
				cal.setActionLog();	
			}else{				
				operator=uname;
				operdate=com.idn.util.FormatDate.format(now,"yyMMdd");
				opertime=com.idn.util.FormatDate.format(now,"hh:mm:ss");
				
				strsql[0]="update ORDERR set epotrf='', epordt='"+epordt+"',epordtm='"+epordtm+"',epordtm2='"+epordtm2+"', operdate='"+operdate+"',opertime='"+opertime+"',operator='"+operator+"' where eponum='"+eponum+"'";
						  
				//int returnint=sis.executeSQL(strsql);
				
				//写日志（XIAOAI处理）
				commsearch.util.CommActionLog  cal= new commsearch.util.CommActionLog();
			
				cal.setAct_user(uname);
				cal.setAct_from("homeworldReturnOrder");
				cal.setAct_do("UPD");
				cal.setAct_key(temp);
				cal.setAct_table("ORDERR");
				cal.setAct_ip(request.getRemoteAddr());
				cal.setAct_memo("回复修改定单编号为" + temp);
				cal.setAct_me("");
				cal.setActionLog();
				
				}
			DynaSqlBean dybBean = new DynaSqlBean();
			dybBean.setSql(strsql);
			dybBean.executeBatch();
			//setFormbeen(form,request,selectwhere,uname);
			
			PropertyUtils.setSimpleProperty(form, "freeformstate" ,"0");
			
			PropertyUtils.setSimpleProperty(form, "message" ,"1");

//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"ReturnOrder","E","LYC8888888",l_end - l_begin);
			return(mapping.findForward("success"));
			
			
		} catch (Exception e){
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Database.formbean"));
			saveErrors(request, errors);
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"ReturnOrder","C","LYC9999999",l_end - l_begin);
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
    
    public String setFormbeen(ActionForm form,HttpServletRequest request,String where,String uname) throws Exception{
			CommDate cd=new CommDate();
            DataBean dbBean=new DataBean();
            String strsql="select * from EPOHDR where "+where+"	and epovnd='"+uname+"'";
		try{
			dbBean.executeSelect(strsql);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Datebase.readdb"));
			return "0";
		}

		String eponum=dbBean.getElementValue(0,"eponum").trim() ;
		String epostr=dbBean.getElementValue(0,"epostr").trim() ;
		String epostn=dbBean.getElementValue(0,"epostn").trim() ;
		String eposdt=cd.dateFormat(dbBean.getElementValue(0,"eposdt").trim(),"L");
		String epoqdt=cd.dateFormat(dbBean.getElementValue(0,"epoqdt").trim(),"L");
		
		PropertyUtils.setSimpleProperty(form, "epoqdt" ,epoqdt ); 
		PropertyUtils.setSimpleProperty(form, "eponum" ,eponum ); 
		PropertyUtils.setSimpleProperty(form, "epostr" ,epostr ); 
		PropertyUtils.setSimpleProperty(form, "epostn" ,epostn ); 
		PropertyUtils.setSimpleProperty(form, "eposdt" ,eposdt );
		
		String strsql1="select * from ORDERR where "+where+" and operator='"+uname+"'";
		try{
		   dbBean.executeSelect(strsql1);
		}catch(Exception e){
			e.printStackTrace();
		   errors.add("errormessage",new ActionError("Datebase.readdb")); 
	    }
	    int count=dbBean.getRowCount();


	    String epordt=cd.dateFormat(dbBean.getElementValue(0,"epordt").trim(),"L");
	    
	    
		String epordtm=dbBean.getElementValue(0,"epordtm").trim() ;
		String epordtm2=dbBean.getElementValue(0,"epordtm2").trim();

		String epoflg=dbBean.getElementValue(0,"epoflg").trim() ;
		
		String operator=dbBean.getElementValue(0,"operator").trim() ;
		String operdate=dbBean.getElementValue(0,"operdate").trim() ;
		String opertime=dbBean.getElementValue(0,"opertime").trim() ;

		Date now=new Date();
		String freeformstate="0";
		
		if( count==0){
			epoflg="0";
			operator=uname;
			operdate=com.idn.util.FormatDate.format(now,"yyyy-MM-dd");
			opertime=com.idn.util.FormatDate.format(now,"hh:mm:ss");
			freeformstate="3";
		}

		PropertyUtils.setSimpleProperty(form, "freeformstate" ,freeformstate);
		PropertyUtils.setSimpleProperty(form, "epordt" ,epordt );
		PropertyUtils.setSimpleProperty(form, "epordtmshow" ,epordtm );
		PropertyUtils.setSimpleProperty(form, "epordtm2show" ,epordtm2 );
		PropertyUtils.setSimpleProperty(form, "epoflg" ,epoflg);

		PropertyUtils.setSimpleProperty(form, "operatorid" ,operator);
		PropertyUtils.setSimpleProperty(form, "operatorshow" ,operator);
	//	PropertyUtils.setSimpleProperty(form, "operatorshow" ,getNamec(operator));
		
		PropertyUtils.setSimpleProperty(form, "operdate" ,operdate );
		PropertyUtils.setSimpleProperty(form, "opertime" ,opertime );
		
   return "1";
    }

	public String getNamec(String name) throws Exception{
		String sql="select vndnam from vndinfo where vndnum=upper('"+name+"')";
		DataBean dbBean = new DataBean();	
		try{
		dbBean.executeSelect(sql);
		} catch (Exception e) {
		e.printStackTrace();
		errors.add("errormessage",new ActionError("Datebase.readdb"));
		return "";
		}
		String s_re=dbBean.getElementValue(0,0).trim();
		
		if (s_re.equals("")){
			s_re=name;
		}
		return  s_re;
	}
	
	
	/* (non-Javadoc)
	 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return executeme(mapping,form,request,response);
	}

	
	
	
}

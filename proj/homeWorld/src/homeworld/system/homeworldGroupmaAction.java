/*
 * @(#)homewordGroupmaAction.java  2004-2-11
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.system;

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

//import system.fun.FunPurview;

import java.io.IOException;
import java.util.*;
//import com.idn.log.LogWrapper;
import com.idn.secure.SecureAction;
import com.idn.sql.*;
import com.idn.util.FormatDate;

/**
 * <P>菜单管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class homeworldGroupmaAction  extends SecureAction{

	ActionErrors errors = new ActionErrors();


	/**
	 * 构造函数
	 */
	public homeworldGroupmaAction () {
		super();
	}

	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeme(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,	HttpServletResponse response)throws IOException, ServletException  {

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
				uname,hs.getId(),"Groupma","B","LYC0000000",0);
						
			if(uname==null){
				errors.add("errormessage",new ActionError("NoName"));
				saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Groupma","E","LYC5555555",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			if(uname.trim().equals("")){
				errors.add("errormessage",new ActionError("NoName"));
				saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Groupma","E","LYC6666666",l_end - l_begin);
				return(mapping.findForward("success"));
			}

	try{
						
			String stemp,flag;
		
			DynaSqlBean dybBean = new DynaSqlBean();
			Vector vecTemp=new Vector();
			String temp=null;
		
			//取标志
			flag=(String) PropertyUtils.getSimpleProperty(form, "flag");
		
			
			//不需要提交数据库的时候直接赋值
			if (flag.equalsIgnoreCase("")){
				temp=setFormbeen(form,request);
				if (temp.equals("0")){
					saveErrors(request, errors);
				}
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Groupma","E","LYC8888881",l_end - l_begin);
				return(mapping.findForward("success"));
			}

			if (flag.substring(flag.length()-2).equalsIgnoreCase("xx")){
				temp=setFormbeen(form,request);
				if (temp.equals("0")){
					saveErrors(request, errors);
				}
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Groupma","E","LYC8888882",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			
			
			String[] strsql=null;
			
			int currrow;
			String groupid="";
			String groupnam="";
			String groupdes="";
			String operator="";
			String operdate="";
			String rowid="";
			
			
			//分不同的情况取当前行的值
			if(flag.equalsIgnoreCase("commit"))
				currrow=Integer.parseInt((String) PropertyUtils.getSimpleProperty(form, "currrow"));
			else
				currrow=Integer.parseInt((String) PropertyUtils.getSimpleProperty(form, "currrowold"));
				
			//按当前行取值
			groupid=((String) PropertyUtils.getIndexedProperty(form,  "groupid",currrow)).trim();
			groupnam=((String) PropertyUtils.getIndexedProperty(form, "groupnam",currrow)).trim();
			groupdes=((String) PropertyUtils.getIndexedProperty(form, "groupdes",currrow)).trim();
			operator=((String) PropertyUtils.getIndexedProperty(form, "operatorid",currrow)).trim();
			operdate=((String) PropertyUtils.getIndexedProperty(form, "operdate",currrow)).trim();
			rowid=((String) PropertyUtils.getIndexedProperty(form, "rowid",currrow)).trim();


			//拼写提交数据库的代码
			String rowstate="";
			
			rowstate=((String) PropertyUtils.getIndexedProperty(form, "rowstate",currrow)).trim();

			if (flag.equalsIgnoreCase("del"))
				rowstate="2";
				
			if(rowstate.equalsIgnoreCase("1") || rowstate.equalsIgnoreCase("2"))
				vecTemp.addElement("DELETE FROM groupma where groupid='"+rowid+"'");
				
			if(rowstate.equalsIgnoreCase("1") || rowstate.equalsIgnoreCase("4"))
				vecTemp.addElement("insert into groupma(groupid,groupnam,groupdes,operator,operdate,director) values('"+groupid+"','"+groupnam+"','"+groupdes+"','"+operator+"','"+operdate+"','')");
				
			strsql = (String[]) vecTemp.toArray(new String[0]);
				
			//执行sql语句
			try{
				dybBean.setSql(strsql);
				dybBean.executeBatch();
			} catch (Exception e){
				e.printStackTrace();
				errors.add("errormessage",new ActionError("Database.writedb"));
				saveErrors(request, errors);
			}
			
			//刷新网页
			temp=setFormbeen(form,request);
			if (temp.equals("0")){
				saveErrors(request, errors);
			}

//		TODO 临时用来监测用
		l_end = System.currentTimeMillis();
		commsearch.util.CommActionLog.setTempLog(
			Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
			uname,hs.getId(),"Groupma","E","LYC8888888",l_end - l_begin);
			return(mapping.findForward("success"));
		} catch (Exception e){
		e.printStackTrace();
			errors.add("errormessage",new ActionError("Database.formbean"));
			saveErrors(request, errors);
//		TODO 临时用来监测用
		l_end = System.currentTimeMillis();
		commsearch.util.CommActionLog.setTempLog(
			Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
			uname,hs.getId(),"Groupma","C","LYC9999999",l_end - l_begin);
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
	public String setFormbeen(ActionForm form,HttpServletRequest request) throws Exception{
	
		int count;
		String strsql;
		
		String[] groupid  = null;//组ＩＤ
		String[] groupnam = null;//组名字
		String[] groupdes = null;//组描述
		String[] operatorid = null;//操作人
		String[] operatorshow = null;//操作人
		String[] operdate = null;//操作日期
		
		DataBean dbBean = new DataBean();
	
		//取数据		
		strsql="SELECT groupid,groupnam,groupdes,operator,operdate,VNDNAM FROM groupma,VNDINFO where vndnum=operator"; 
		
		try{
			dbBean.executeSelect(strsql);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Database.readdb"));
			return "0";
		}
	
		//不足两行则补足两行	
		count=dbBean.getRowCount();
		if (count<2)
			count=2;
			
		groupid  = new String[count]; 
		groupnam = new String[count];
		groupdes = new String[count];
		operatorid = new String[count];
		operatorshow = new String[count];
		operdate = new String[count];

		//赋值数组，并截去空格
		for(int i=0;i<count;i++){                                                  
			groupid[i]=dbBean.getElementValue(i,"groupid").trim();
			groupnam[i]=dbBean.getElementValue(i,"groupnam").trim();
			groupdes[i]=dbBean.getElementValue(i,"groupdes").trim();
			operatorid[i]=dbBean.getElementValue(i,"operator").trim();
			operatorshow[i]=dbBean.getElementValue(i,"vndNaM").trim();
			operdate[i]=dbBean.getElementValue(i,"operdate").trim();
		}
		
		//向formbean中赋值
                                                           
		PropertyUtils.setSimpleProperty(form, "groupid",groupid);
		PropertyUtils.setSimpleProperty(form, "rowid",groupid);
		PropertyUtils.setSimpleProperty(form, "groupnam",groupnam);
		PropertyUtils.setSimpleProperty(form, "groupdes",groupdes);
		PropertyUtils.setSimpleProperty(form, "operatorid",operatorid);
		PropertyUtils.setSimpleProperty(form, "operatorshow",operatorshow);
		PropertyUtils.setSimpleProperty(form, "operdate",operdate);

		HttpSession session = request.getSession();
		String temp=(String)session.getAttribute("userid");
		PropertyUtils.setSimpleProperty(form, "userid",temp);
		PropertyUtils.setSimpleProperty(form, "usershow",getNamec(temp.trim()));
		
		Date today=new Date();
		temp=FormatDate.format(today,"yyyy-MM-dd");
		PropertyUtils.setSimpleProperty(form, "datenow",temp);
		
		PropertyUtils.setSimpleProperty(form, "count",String.valueOf(count));
		
		return "1";
	}

	public String getNo() throws Exception{
	
		String s_now = "";
		
			Date today=new Date();
			s_now=FormatDate.format(today,"yyMMdd");
			String sql="select max(groupid) from groupma where groupid like '"+s_now+"%'";
			DataBean dbBean = new DataBean();	
			try{
				dbBean.executeSelect(sql);
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("errormessage",new ActionError("IdnkhTel.readdb"));
				return "";
			}
			
			if (dbBean.getElementValue(0,0).equals("")){
				 s_now =s_now+"0000";		
			}else{
				 s_now =dbBean.getElementValue(0,0);	
			}
		
		s_now=String.valueOf(Long.parseLong(s_now)+1);
		s_now="00"+s_now;
		s_now=s_now.substring(s_now.length()-10);
		return s_now;
		
	}
	

	
	public String getNamec(String name) throws Exception{
		String sql="select vndnam from VNDINFO where vndnum=upper('"+name+"')";
		DataBean dbBean = new DataBean();	
		try{
		dbBean.executeSelect(sql);
		} catch (Exception e) {
			e.printStackTrace();
		errors.add("errormessage",new ActionError("IdnkhInstall.readdb"));
		return "";
		}
		return dbBean.getElementValue(0,0);
	}

	/* (non-Javadoc)
	 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 
		return  executeme( mapping,form,request,response);
	}

}

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

import java.util.Vector;

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

import com.idn.property.CodesManager;
import com.idn.secure.SecureAction;
import com.idn.sql.*;
import system.fun.FunPurview;

/**
 * <P>置装费表管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class homeworldPurviewNewAction extends SecureAction {
	ActionErrors errors = new ActionErrors();
	
	//
	/**
	 * 构造函数
	 */
	public homeworldPurviewNewAction() {
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
			
		FunPurview funPurview=new FunPurview();
		
		HttpSession hs = request.getSession();
		String uname=(String)hs.getAttribute("userid");
			
		//TODO 临时用来监测用
		long l_begin,l_end;
		l_begin = System.currentTimeMillis();		
		commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
		commsearch.util.CommActionLog.setTempLog(
			Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
			uname,hs.getId(),"PurviewNEW","B","LYC0000000",0);
				
			
	try{

		//		添加标题		
		PropertyUtils.setSimpleProperty(form, "title","人员权限");
		PropertyUtils.setSimpleProperty(form, "id","userid");
		PropertyUtils.setSimpleProperty(form, "show","用户");
		//		添加标题	

		String temp="";
		
		//1 : 修改(默认)  2 ：查询
		String se_up=(String)request.getParameter("se_up");
		if(se_up==null){se_up=(String) PropertyUtils.getSimpleProperty(form, "se_up");}
		PropertyUtils.setSimpleProperty(form,"se_up",se_up);
		
		String disabled=(String)request.getParameter("disabled");
		if(disabled==null){disabled="1";}
		PropertyUtils.setSimpleProperty(form,"disabled",disabled);
		
		//取标志
		String strFlag=(String) PropertyUtils.getSimpleProperty(form, "flag");
		String page=(String) PropertyUtils.getSimpleProperty(form, "page");
		String where=(String) PropertyUtils.getSimpleProperty(form, "where");

		

		//首次直接返回
		if (strFlag.equals("")){
			
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"PurviewNEW","E","LYC8888881",l_end - l_begin);
				return mapping.findForward("success");}
		
		//在次返回
		if(strFlag.equals("selectexic")){
			temp=setFormbeen(form,request,where);
			if (temp.equals("0")){saveErrors(request, errors);}
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"PurviewNEW","E","LYC8888882",l_end - l_begin);
			return mapping.findForward("success");
		}
		

		if(strFlag.equals("hiddenexic")){
			temp=setFormbeen(form,request,where);
			if (temp.equals("0")){saveErrors(request, errors);}
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"PurviewNEW","E","LYC8888883",l_end - l_begin);
			return mapping.findForward("success");
		}
		
		/*
		 *添加代码
		 */
				DynaSqlBean dybBean = new DynaSqlBean();
				Vector vecTemp=new Vector();

				String currrowshow;
				String[] strSql=null;
		
				currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrowshow"));

				if (strFlag.equalsIgnoreCase("hiddenexit")){
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"PurviewNEW","E","LYC8888884",l_end - l_begin);
					return(mapping.findForward("success"));
				}
				//根据不同的系统显示不同的权限
				if (strFlag.equalsIgnoreCase("circle")){
					String temps;
					temps=((String) PropertyUtils.getIndexedProperty(form, "appsysid",Integer.parseInt(currrowshow))).trim();
					if(funPurview.decodePurview(0,temps)){
						PropertyUtils.setSimpleProperty(form, "mpurviewshow",funPurview.getCshowc());
						PropertyUtils.setSimpleProperty(form, "mpurviewid",funPurview.getPurview());
					}	
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"PurviewNEW","E","LYC8888885",l_end - l_begin);
					return(mapping.findForward("success"));
				}
				//根据不同的系统显示不同的权限
				if (strFlag.equalsIgnoreCase("changeexic")){
					if(!currrowshow.equals("-1")){
						String appsys,mpurview;
						String[] mpurviewid=new String[2];
						String[] mpurviewshow=new String[2];
				
						appsys=((String) PropertyUtils.getIndexedProperty(form, "appsysid",Integer.parseInt(currrowshow))).trim();
						mpurview=((String) PropertyUtils.getIndexedProperty(form, "mpurview",Integer.parseInt(currrowshow))).trim();
						//解析权限
						if(funPurview.decodePurview(Integer.parseInt(mpurview),appsys)){
							mpurviewid=funPurview.getPurview();
							mpurviewshow=funPurview.getCshowc();
						}
						PropertyUtils.setSimpleProperty(form, "mpurviewshow",mpurviewshow);
						PropertyUtils.setSimpleProperty(form, "mpurviewid",mpurviewid);
					}
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"PurviewNEW","E","LYC8888886",l_end - l_begin);
					return(mapping.findForward("success"));
				}

				//取当前行
				currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrowshowold"));
				if (strFlag.equalsIgnoreCase("commit") || strFlag.equalsIgnoreCase("select"))
					currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrow"));
		
				String rowstate=((String) PropertyUtils.getIndexedProperty(form, "rowstate",Integer.parseInt(currrowshow)));
			
				if (rowstate.equals("1")){
					//取系统
					String appsysid=((String) PropertyUtils.getIndexedProperty(form, "appsysid",Integer.parseInt(currrowshow)));
					String userid=((String) PropertyUtils.getIndexedProperty(form, "userid",Integer.parseInt(currrowshow)));
					//取权限
					String[] purviewid = (String[])request.getParameterValues("mpurviewid");
					String strTemp=String.valueOf(funPurview.codingPurview(purviewid));
		
					PropertyUtils.setIndexedProperty(form,"mpurview",Integer.parseInt(currrowshow),strTemp);
					PropertyUtils.setIndexedProperty(form,"rowstate",Integer.parseInt(currrowshow),"0");
		
					vecTemp.addElement("update users set appsys='"+appsysid+"',purview="+strTemp+" where userid='"+userid+"'");

					strSql = (String[]) vecTemp.toArray(new String[0]);		
					//执行sql语句
					try{
						dybBean.setSql(strSql);
						dybBean.executeBatch();
					} catch (Exception e){
						e.printStackTrace();
						errors.add("errormessage",new ActionError("Database.writedb"));
						saveErrors(request, errors);
					}
				}
		
		currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrow"));

		/*
		 *添加代码
		 */

		//在次返回
		if(strFlag.equals("select")){
			temp=setFormbeen(form,request,where);
			if (temp.equals("0")){saveErrors(request, errors);}
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"PurviewNEW","E","LYC8888887",l_end - l_begin);
			return mapping.findForward("success");
		}
		
		
		if(!currrowshow.equals("-1")){
			String appsys,mpurview;
			String[] mpurviewid=new String[2];
			String[] mpurviewshow=new String[2];
				
			appsys=((String) PropertyUtils.getIndexedProperty(form, "appsysid",Integer.parseInt(currrowshow))).trim();
			mpurview=((String) PropertyUtils.getIndexedProperty(form, "mpurview",Integer.parseInt(currrowshow))).trim();
			//解析权限
			if(funPurview.decodePurview(Integer.parseInt(mpurview),appsys)){
				mpurviewid=funPurview.getPurview();
				mpurviewshow=funPurview.getCshowc();
			}
			PropertyUtils.setSimpleProperty(form, "mpurviewshow",mpurviewshow);
			PropertyUtils.setSimpleProperty(form, "mpurviewid",mpurviewid);
		}
		
						
		//向formbean中赋值

//		TODO 临时用来监测用
		l_end = System.currentTimeMillis();
		commsearch.util.CommActionLog.setTempLog(
			Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
			uname,hs.getId(),"PurviewNEW","E","LYC8888888",l_end - l_begin);
		return mapping.findForward("success");	
		
	} catch (Exception e){
		e.printStackTrace();
		errors.add("errormessage",new ActionError("Database.formbean"));
		saveErrors(request, errors);
//		TODO 临时用来监测用
		l_end = System.currentTimeMillis();
		commsearch.util.CommActionLog.setTempLog(
			Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
			uname,hs.getId(),"PurviewNEW","C","LYC9999999",l_end - l_begin);
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
			

			/*
			 *添加代码
			 */
			String strSql,key,currrowshow;
		
			DataBean dbBean = new DataBean();
		
			strSql="select userid,appsys,purview,lifebeg,lifeend,useridg,vndnam  " +
				"from users,VNDINFO where userid=vndnum and ("+ where +") order by userid";

			dbBean.executeSelect(strSql);
	
			int count;
			count=dbBean.getRowCount();
		if (count<2){
			 count=2;
		}
		
		
		String[] mpurviewshow = new String[count];//权限显示
		String[] mpurviewid = new String[count];//权限id
		String[] mpurview = new String[count];//权限id
	
		String[] appsysshow =new String[count];//系统显示
		String[] appsysid = new String[count];//系统id

		String[] userid= new String[count];//用户id
		String[] usershow= new String[count];//用户姓名
	
		String[] lifebeg = new String[count];
		String[] lifeend = new String[count];
	
		String[] useridgshow = new String[count];
		String[] useridgid = new String[count];
		
			for(int i=0;i<count;i++){
				
				userid[i]=dbBean.getElementValue(i,0) ;
				usershow[i]=dbBean.getElementValue(i,"vndnam");
			
				appsysid[i]=dbBean.getElementValue(i,1);
				if(!appsysid[i].equals(""))
					appsysshow[i]=CodesManager.getCodeValue("00",appsysid[i]);
				//appsysshow[i]=appsysid[i];
			
				mpurview[i]=dbBean.getElementValue(i,2);
				lifebeg[i]=dbBean.getElementValue(i,3).trim()  ;
				lifeend[i]=dbBean.getElementValue(i,4).trim();

				if (!lifeend[i].equals("")){
					lifebeg[i]=com.idn.util.FormatDate.format(Long.parseLong(lifebeg[i]),"yyyy-MM-dd");	
					lifeend[i]=com.idn.util.FormatDate.format(Long.parseLong(lifeend[i]),"yyyy-MM-dd");
				}
				useridgid[i]=dbBean.getElementValue(i,5);
				useridgshow[i]=useridgid[i] ;			
			}

			PropertyUtils.setSimpleProperty(form, "userid",userid);
			PropertyUtils.setSimpleProperty(form, "usershow",usershow);

			PropertyUtils.setSimpleProperty(form, "appsysshow",appsysshow);
			PropertyUtils.setSimpleProperty(form, "appsysid",appsysid);
		
			PropertyUtils.setSimpleProperty(form, "mpurview",mpurview);
		
			PropertyUtils.setSimpleProperty(form, "lifebeg",lifebeg);
			PropertyUtils.setSimpleProperty(form, "lifeend",lifeend);

			PropertyUtils.setSimpleProperty(form, "useridgid",useridgid);
			PropertyUtils.setSimpleProperty(form, "useridgshow",useridgshow);
		
			

			PropertyUtils.setSimpleProperty(form, "currrow","-1"); 
			PropertyUtils.setSimpleProperty(form, "currrowshowold","-1");
			PropertyUtils.setSimpleProperty(form, "currrowshow","-1");

			/*
			 *添加代码
			 */

		
			PropertyUtils.setSimpleProperty(form, "count",String.valueOf(count));
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

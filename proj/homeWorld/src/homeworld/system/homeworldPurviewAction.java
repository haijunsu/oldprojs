/*
 * @(#)homewordPuriewAction.java  2004-2-12
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


import com.idn.property.CodesManager;
import system.fun.FunPurview;
//import com.idn.log.LogWrapper;
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
public class homeworldPurviewAction extends SecureAction {

	ActionErrors errors = new ActionErrors();

	/**
	 * 构造函数
	 */
	public homeworldPurviewAction() {
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
		
		FunPurview funPurview=new FunPurview();
		
		DynaSqlBean dybBean = new DynaSqlBean();
		Vector vecTemp=new Vector();
		String strFlag;


		String currrowshow;
		String[] strSql=null;
		
		try{
			
			strFlag=((String) PropertyUtils.getSimpleProperty(form, "flag"));
			
			currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrowshow"));
			
			if (strFlag.equalsIgnoreCase("")){
				setFormbeen(form);
				return(mapping.findForward("success"));
			}		
			if (strFlag.equalsIgnoreCase("hiddenexit")){
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
				return(mapping.findForward("success"));
			}
	
			//取当前行
			currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrowshowold"));
			if (strFlag.equalsIgnoreCase("commit") )
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
			setFormbeen(form);
			//根据不同的系统显示不同的权限
			//if (strFlag.equalsIgnoreCase("change")){
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
			return(mapping.findForward("success"));
		}catch (Exception e){
			e.printStackTrace();
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
		String strSql,key,currrowshow;
		
		DataBean dbBean = new DataBean();
		
		strSql="select userid,appsys,purview,lifebeg,lifeend,useridg,vndnam  " +
				"from users,VNDINFO where userid=vndnum  order by userid";

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
			userid[i]=dbBean.getElementValue(i,0).trim() ;
			usershow[i]=dbBean.getElementValue(i,"vndnam");
			
			appsysid[i]=dbBean.getElementValue(i,1).trim();
			appsysshow[i]=CodesManager.getCodeValue("00",appsysid[i]);
			//appsysshow[i]=appsysid[i];
			
			mpurview[i]=dbBean.getElementValue(i,2).trim();
			lifebeg[i]=dbBean.getElementValue(i,3).trim() ;
			lifeend[i]=dbBean.getElementValue(i,4).trim() ;

			if (!lifeend[i].equals("")){
				lifebeg[i]=com.idn.util.FormatDate.format(Long.parseLong(lifebeg[i]),"yyyy-MM-dd");	
				lifeend[i]=com.idn.util.FormatDate.format(Long.parseLong(lifeend[i]),"yyyy-MM-dd");
			}
			useridgid[i]=dbBean.getElementValue(i,5).trim() ;
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

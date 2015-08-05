/**
 * @(#)LogonAction.java  2003-1-28
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package systemmanager;

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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import com.idn.log.LogWrapper;
import com.idn.secure.SecureAction;
import com.idn.sql.*;

/**
 * <P>代码表管理</P>
 * 
 * @version 0.1
 * @author 刘坚
 */
public class CodesAction extends  SecureAction {
	private static LogWrapper log= new LogWrapper(CodesAction.class);
	private int intTemp = 0;
	private String strSql;
	private String[] strTemp = null;
	ActionErrors errors = new ActionErrors();

	/**
	 * 
	 */
	public CodesAction() {
		super();
	}

	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeme(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,	HttpServletResponse response)throws IOException, ServletException {

		String strFlag;
//		String strErr="1";
		String strCclass;
		String[] strRowstate = null;
		String[] strRowid = null;
		String[] strCcode = null;
		String[] strCshowc = null;
		Vector vecTemp = new Vector();
		DynaSqlBean dybBean = new DynaSqlBean();
		ActionForward forward = mapping.findForward("ArrayForm");
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
			uname,hs.getId(),"Codes","B","LYC0000000",0);
			
			
		try{
			
			strRowstate = (String[]) PropertyUtils.getSimpleProperty(form, "rowstate");
			strFlag = ((String) PropertyUtils.getSimpleProperty(form, "flag")).trim();
			strCclass = ((String) PropertyUtils.getSimpleProperty(form, "cclass")).trim();
			//初始化页面
			if (strRowstate == null) {
				strCclass="00";
				strFlag="esc";
			}
	
			//数据处理不处理只换页
			if(strFlag.equals("esc")){
				if (setFormbeen(form,strCclass).equals("0")){
					saveErrors(request, errors);
				}
						
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Codes","E","LYC8888881",l_end - l_begin);
				return forward;
			}
			
			//数据处理
			strRowid = (String[]) PropertyUtils.getSimpleProperty(form, "rowid");
			strCcode = (String[]) PropertyUtils.getSimpleProperty(form, "ccode");
			strCshowc = (String[]) PropertyUtils.getSimpleProperty(form, "cshowc");
			for (int intFor = 0;intFor<strRowstate.length;intFor++){
				//删除
				if(strRowstate[intFor].equals("2")||strRowstate[intFor].equals("1")){
					if(!strRowid[intFor].trim().equals("")){
						strSql="DELETE FROM CODES where cclass='"+strCclass+"' AND " +
							"ccode='"+strRowid[intFor].trim()+"'";
						vecTemp.addElement(strSql);
						System.out.println(strSql);
						if(strCclass.equals("00")){
							if(strRowstate[intFor].equals("1")){
								strSql="UPDATE CODES SET CCLASS='"+strCcode[intFor].trim()+"' " +
									"where cclass='"+strRowid[intFor].trim()+"'";
							} else{
								strSql="DELETE FROM CODES where cclass='"+strRowid[intFor].trim()+"'";
							}
							vecTemp.addElement(strSql);
							System.out.println(strSql);
						}
					}
				}
				//添加
				if(strRowstate[intFor].equals("4") || strRowstate[intFor].equals("1")){
					//代码项为空不添加
					if (!strCcode[intFor].trim().equals("")){
						strSql="INSERT INTO CODES (cclass,ccode,cshowc) VALUES " +
							"('"+strCclass+"','"+strCcode[intFor].trim()+"','"+strCshowc[intFor].trim()+"')";
						vecTemp.addElement(strSql);
						System.out.println(strSql);
					}
				}
			}
			if (vecTemp.size() > 0){
				strTemp = (String[]) vecTemp.toArray(new String[0]);
				dybBean.setSql(strTemp);
				try{
					dybBean.executeBatch();
				} catch (Exception e){
					errors.add("errormessage",new ActionError("CodesAction.writedb"));
					saveErrors(request, errors);
				}
			}
			
			//显示
			if(!strFlag.equals("commit")){
				strCclass=strFlag;
			}
			if (setFormbeen(form,strCclass).equals("0")){
				saveErrors(request, errors);
			}
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"Codes","E","LYC8888888",l_end - l_begin);
			return forward;
		} catch (Exception e){
			errors.add("errormessage",new ActionError("CodesAction.formbeen"));
			saveErrors(request, errors);
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"Codes","C","LYC9999999",l_end - l_begin);
			return forward;
		}
	}

	/**
	 * 将指定类代码内容传入显示FormBeen
	 * 
	 * @param ActionForm 显示FormBeen
	 * @param String 代码种类
	 * @return String 1、成功；-1、不成功；
	 * @exception Exception
	 */
	public String setFormbeen(ActionForm form,String strCclass) 
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{ 
		int intMaxrow;
		String strCclassshowc;
		DataBean dbBean = new DataBean();

		try {
			//取代码种类说明，并判断是否有效
			strCclassshowc="代码种类管理";
			if(!strCclass.equals("00")){
				strSql="select cshowc from codes where cclass='00' and ccode='"+strCclass+"'";
				dbBean.executeSelect(strSql);
				if (dbBean.getRowCount()==0){
					strCclass="00";
				} else {
					strCclassshowc=dbBean.getElementValue(0,0).trim();
				}
			}
			//查询代码内容
			strSql="select ccode,cshowc from codes where cclass='"+strCclass+"'";
			if (strCclass.equals("00")){
				strSql=strSql+" and ccode<'80'";
			}
			dbBean.executeSelect(strSql);
		} catch (Exception e) {
			errors.add("errormessage",new ActionError("CodesAction.readdb"));
			return "0";
		}
		
		intMaxrow = dbBean.getRowCount();
		//记录不足两行的补空行
		if (intMaxrow < 2) {
			intMaxrow=2;
		}

		String[] strCcode = new String[intMaxrow];
		String[] strCshowc = new String[intMaxrow];
		for (int intRow = 0; intRow < intMaxrow;intRow++){
			strCcode[intRow]=dbBean.getElementValue(intRow,"ccode").trim();
			
			strCshowc[intRow]=dbBean.getElementValue(intRow,"cshowc").trim();
		}
		PropertyUtils.setSimpleProperty(form, "ccode",strCcode);
		PropertyUtils.setSimpleProperty(form, "cshowc",strCshowc);
		PropertyUtils.setSimpleProperty(form, "cclass",strCclass);
		PropertyUtils.setSimpleProperty(form, "cclassshowc",strCclassshowc);
		PropertyUtils.setSimpleProperty(form, "currrow","0");
		
		return "1";
	}

	/* (non-Javadoc)
	 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return  executeme( mapping,form,request,response);
	}


}

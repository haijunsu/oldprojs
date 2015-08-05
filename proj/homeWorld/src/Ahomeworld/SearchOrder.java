/**
 * @(#)SearchOrder.java  2004-02-01
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package Ahomeworld;


import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;
import org.apache.commons.beanutils.PropertyUtils;

import com.idn.secure.SecureAction;

import system.action.ErrorValue;

import commsearch.*;
//import commsearch.util.CommUsers;

/**
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author 小艾
 */

public class SearchOrder extends SecureAction  {
	
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(SearchOrder.class);
	
	public SearchOrder() {
		super();
	}
	public ActionForward executeme(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException 
		{
		commsearch.CommTools ct = new commsearch.CommTools();    //公用查询工具类
		commsearch.CommSQL cs = new commsearch.CommSQL();    //执行SQL语句 
		commsearch.util.CommString cstr = new commsearch.util.CommString();
		Locale locale = null;             
		MessageResources messages = null; 
		String username = "",purview = "";

		String s_flag,s_page,s_queryid;
		String s_nowpage,s_nowoncepage;
		String s_where,s_actiondo;
		String s_ghs= "";
		//
		String s_no="";
		String s_checkthing="";
		String[] s_checkthings;
		String s_temp;
		String s_superuser="";
		String s_superuserthing="";
		String s_order="";
		String s_divs[] = null;
		String s_userwhere = "";
		String[] s_fields=null,s_fieldsch=null,s_fieldskey=null,s_fieldstype=null,s_title=null;
		String[] s_deleteflag=null;
		
		CommQuery cq = null;
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		username = (String)session.getAttribute("userid");
		purview  = (String)session.getAttribute("purview");
		if (username == null) username="";
		if (purview==null) purview = "177";
		s_queryid = ct.getParameter(request,"queryid");
		long l_begin,l_end;
		l_begin = System.currentTimeMillis();		
		commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
		commsearch.util.CommActionLog.setTempLog(
			Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
			username,session.getId(),"SearchOrder","B",s_queryid + "0001",0);
		s_page = ct.getParameter(request,"page");
		if (s_page.trim().equals("")) s_page = "1";
		s_flag = ct.getParameter(request,"flag");
		s_nowpage = ct.getParameter(request,"nowpage");
		if (s_nowpage.equals("")) s_nowpage = "1";
		s_nowoncepage = ct.getParameter(request,"nowoncepage");
		if (s_nowoncepage.equals("")) s_nowoncepage = "1";
		if (s_nowoncepage.equals("0")) s_nowoncepage = "1";

		
		locale = getLocale(request);
		messages = getResources(request);
		try {
			if (s_queryid.equals("")){
				ErrorValue ev = new system.action.ErrorValue();
				ev.setError("SearchOrder","execute","定单查询的主画面:可能由于网络传输问题");
				request.setAttribute("siev",ev);
				forward = mapping.findForward("failure");
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					username,session.getId(),"SearchOrder","C",s_queryid + "0800",l_end - l_begin);
				return forward;			
			}
			if (Integer.parseInt(s_nowpage)<0){
				ErrorValue ev = new system.action.ErrorValue();
				ev.setError("SearchOrder","execute","定单查询的主画面:可能由于网络传输问题");
				request.setAttribute("siev",ev);
				forward = mapping.findForward("failure");
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					username,session.getId(),"SearchOrder","C",s_queryid + "0801",l_end - l_begin);
				return forward;			
			}
			if (Integer.parseInt(s_nowoncepage)<0){
				ErrorValue ev = new system.action.ErrorValue();
				ev.setError("SearchOrder","execute","定单查询的主画面:可能由于网络传输问题");
				request.setAttribute("siev",ev);
				forward = mapping.findForward("failure");
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					username,session.getId(),"SearchOrder","C",s_queryid + "0802",l_end - l_begin);
				return forward;			
			}
	
			s_actiondo = ct.getParameter(request,"actiondo");

			s_checkthing=ct.getParameter(request,"checkthing");
			s_checkthings = null;

			s_order = ct.getParameter(request,"order");
			
			if (s_flag.equals("DELETE")){
				
				s_deleteflag = request.getParameterValues("deleteflag");
				if (s_deleteflag==null){
				} else {
					commsearch.util.CommActionLog  cal = new commsearch.util.CommActionLog();
					String[][] s_deletesql=null;
					for (int i_fori=0;i_fori<s_deleteflag.length;i_fori++){
						s_deleteflag[i_fori] = cstr.replaceAll(s_deleteflag[i_fori],"&#39;","'");
					}

					s_deletesql = new String[s_deleteflag.length ][4];
					if (s_queryid.equals("DQ_ERTHDR")){
						for (int i_fori=0;i_fori<s_deleteflag.length;i_fori++){
							s_deletesql[i_fori][0] = "INSERT INTO BKERTHDR SELECT * FROM ERTHDR WHERE " + s_deleteflag[i_fori];
							s_deletesql[i_fori][1] = "INSERT INTO BKERTDTL SELECT * FROM ERTDTL WHERE " + s_deleteflag[i_fori];
							s_deletesql[i_fori][2] = "DELETE FROM ERTHDR WHERE " + s_deleteflag[i_fori];
							s_deletesql[i_fori][3] = "DELETE FROM ERTDTL WHERE " + s_deleteflag[i_fori];
							s_no=s_deleteflag[i_fori];
							s_no = s_no.substring(s_no.indexOf("'") + 1,s_no.indexOf("'",s_no.indexOf("'") + 1));
							cal.setAll();
							cal.setAct_user(username);
							cal.setAct_from("SearchOrder");
							cal.setAct_do("DEL");
							cal.setAct_key(s_no);
							cal.setAct_table("ERTHDR");
							cal.setAct_ip(request.getRemoteAddr());
							cal.setAct_memo("删除返厂单编号为" + s_no);
							cal.setAct_me("");
							cal.setActionLog();
						} 
					}
					if (s_queryid.equals("DQ_ERVHDR")){
						for (int i_fori=0;i_fori<s_deleteflag.length;i_fori++){
							s_deletesql[i_fori][0] = "INSERT INTO BKERVHDR SELECT * FROM ERVHDR WHERE " + s_deleteflag[i_fori];
							s_deletesql[i_fori][1] = "INSERT INTO BKERVDTL SELECT * FROM ERVDTL WHERE " + s_deleteflag[i_fori];
							s_deletesql[i_fori][2] = "DELETE FROM ERVHDR WHERE " + s_deleteflag[i_fori];
							s_deletesql[i_fori][3] = "DELETE FROM ERVDTL WHERE " + s_deleteflag[i_fori];
							s_no=s_deleteflag[i_fori];
							s_no = s_no.substring(s_no.indexOf("'") + 1,s_no.indexOf("'",s_no.indexOf("'") + 1));
							cal.setAll();
							cal.setAct_user(username);
							cal.setAct_from("SearchOrder");
							cal.setAct_do("DEL");
							cal.setAct_key(s_no);
							cal.setAct_table("ERVHDR");
							cal.setAct_ip(request.getRemoteAddr());
							cal.setAct_memo("删除收货单编号为" + s_no);
							cal.setAct_me("");
							cal.setActionLog();
						} 
						
					}
					if (s_queryid.equals("DQ_ERJHDR")){
						for (int i_fori=0;i_fori<s_deleteflag.length;i_fori++){
							s_deletesql[i_fori][0] = "INSERT INTO BKERJHDR SELECT * FROM ERJHDR WHERE " + s_deleteflag[i_fori];
							s_deletesql[i_fori][1] = "INSERT INTO BKERJDTL SELECT * FROM ERJDTL WHERE " + s_deleteflag[i_fori];
							s_deletesql[i_fori][2] = "DELETE FROM ERJHDR WHERE " + s_deleteflag[i_fori];
							s_deletesql[i_fori][3] = "DELETE FROM ERJDTL WHERE " + s_deleteflag[i_fori];

							s_no=s_deleteflag[i_fori];
							s_no = s_no.substring(s_no.indexOf("'") + 1,s_no.indexOf("'",s_no.indexOf("'") + 1));
							cal.setAll();
							cal.setAct_user(username);
							cal.setAct_from("SearchOrder");
							cal.setAct_do("DEL");
							cal.setAct_key(s_no);
							cal.setAct_table("ERJHDR");
							cal.setAct_ip(request.getRemoteAddr());
							cal.setAct_memo("删除收货调整单编号为" + s_no);
							cal.setAct_me("");
						 	cal.setActionLog();
						} 
					}	
					for (int i_fori=0;i_fori<s_deleteflag.length;i_fori++){
						for (int i_forj=0;i_forj<4;i_forj++){
							cs.executeBatchSQL(s_deletesql[i_fori][i_forj]);
						}
					}
					commsearch.page.PageForm pf = new commsearch.page.PageForm(); 
					if (s_deleteflag.length==(pf.getOnePage())){
						s_nowpage = "1";
						s_nowoncepage = "1";	
					}
					cal = null;
					pf = null;
					s_deletesql = null;
				}
			}

			if (s_queryid.equals("DQ_EPOHDR")){
				s_ghs = "EPOVND";
			}
			if (s_queryid.equals("DQ_ERTHDR")){
				s_ghs = "ERTVND";
			}
			if (s_queryid.equals("DQ_ERVHDR")){
				s_ghs = "ERVVND";
			}
			if (s_queryid.equals("DQ_ERJHDR")){
				s_ghs = "ERJVND";
			}
			if (s_queryid.equals("DQ_EIPDTL")){
				s_ghs = "EIPVDR";
			}
			if (s_queryid.equals("DQ_EPYHDR")){
				s_ghs = "EPYVDR";
			}
			if (s_queryid.equals("DQ_EPYRJT")){
				s_ghs = "ERJVDR";
			}
			if (s_queryid.equals("DQ_EIVDTL")){
				s_ghs = "EIVVDR";				
			}

			s_where = "";
			if (com.idn.util.CommonTools.isMenuShow(purview,"401",true)){
				s_superuser=s_ghs;
				s_superuserthing = ct.getParameter(request,"superuserthing");
				if (s_superuserthing.equals("")){
					s_userwhere = " 1=0 ";
				} else {
					s_userwhere = s_ghs + "='" + s_superuserthing + "'";
				}
				PropertyUtils.setSimpleProperty(form, "superuser",s_superuser);
			} else {
				s_where = getWhere(s_queryid,form,request,locale,messages);			
				commsearch.homeworld.CommUsers cu= new commsearch.homeworld.CommUsers(); 
				s_temp = cu.getSameUserString(username);
				s_divs = cstr.DivString(s_temp,",");
				for (int i_fori=0;i_fori<s_divs.length;i_fori++){
					if (s_userwhere.equals("")){
						s_userwhere = " (" + s_ghs +" ='" +s_divs[i_fori] +"') ";
					} else {
						s_userwhere = s_userwhere + " OR " + " (" + s_ghs+ " ='" +s_divs[i_fori] +"') ";
					}
				}
			}
			
			if (s_where.equals("")){
				s_where = " (" + s_userwhere + ") " ;
			} else {
				s_where = " (" + s_where + " AND " + " (" + s_userwhere + ")" + ")"  ;
			}
			if (s_queryid.equals("DQ_EPOHDR")){
				if (s_where.equals("")){
					s_where = " (" + "EPOFLG<>'9'" + ") " ;
				} else {
					s_where = " (" + s_where + " AND " + " (" + "EPOFLG<>'9'" + ")" + ")"  ;
				}				
			}
			if (s_queryid.equals("DQ_ERTHDR")){
				if (s_where.equals("")){
					s_where = " (" + "ERTSTS<>'9'" + ") " ;
				} else {
					s_where = " (" + s_where + " AND " + " (" + "ERTSTS<>'9'" + ")" + ")"  ;
				}				
			}
			if (s_queryid.equals("DQ_ERVHDR")){
				if (s_where.equals("")){
					s_where = " (" + "ERVSTS<>'9'" + ") " ;
				} else {
					s_where = " (" + s_where + " AND " + " (" + "ERVSTS<>'9'" + ")" + ")"  ;
				}				
			}
			if (s_queryid.equals("DQ_ERJHDR")){
			}
			if (s_queryid.equals("DQ_EIPDTL")){
				if (s_where.equals("")){
					s_where = " (" + "EIPFLG='1'" + ") " ;
				} else {
					s_where = " (" + s_where + " AND " + " (" + "EIPFLG='1'" + ")" + ")"  ;
				}				
			}
			if (s_queryid.equals("DQ_EPYHDR")){
			}
			if (s_queryid.equals("DQ_EIVDTL")){
			}
			
			if (!s_checkthing.equals("")) {
				s_checkthings = cstr.DivString(s_checkthing,"`");
			
				PropertyUtils.setSimpleProperty(form, "checkthing",s_checkthing);
				PropertyUtils.setSimpleProperty(form, "checkthings",s_checkthings);
			}
			
			
			
			cq = new CommQuery(s_queryid);
			if (cq.init().equals("-1")){
			}
			s_fields = cq.getFields();
			PropertyUtils.setSimpleProperty(form, "fields",s_fields);
			
			s_fieldskey = cq.getFieldskey();
			s_title = cq.getFieldscol("QNAMEC");
			
		
			if (s_page.equals("1")){
				setFormBeanListNew(form,s_page,s_where,cq,s_queryid,s_actiondo,"","",Integer.parseInt(s_nowpage),Integer.parseInt(s_nowoncepage),s_queryid,s_order,username);
			}
			if (s_page.equals("2")){
				
			}

			PropertyUtils.setSimpleProperty(form, "deleteflag",null);
			
			forward = mapping.findForward("success");
			l_end = System.currentTimeMillis();

			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"SearchOrder","E",s_queryid + "0999",l_end - l_begin);

		} catch (Exception e) {
			e.printStackTrace();
			ErrorValue ev = new system.action.ErrorValue();
			ev.setError("SearchOrder","execute","定单查询的主画面出现异常错误:" + e.getMessage());
			request.setAttribute("siev",ev);
			forward = mapping.findForward("failure");

			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"SearchOrder","C",s_queryid + "0900",l_end - l_begin);
		} finally {
			cq.setClear();
			cq = null;
			cstr = null;
			s_checkthings = null;
			s_divs = null;
			s_fields=null;
			s_fieldsch=null;
			s_fieldskey=null;
			s_fieldstype=null;
			s_title=null;
			s_deleteflag=null;
		}
		return forward;
	}

	public String[] getReturnin(String s_queryid,String[][] s_showdata,String user_name){
		int i_num=0,i_flag;
		commsearch.CommSQL cs = new commsearch.CommSQL();    //执行SQL语句 
		
		String s_num="",s_tablename="";
		String s_where = "",s_select = "";
		String[][] s_return = null;
		String[] s_lookin = null;
		if (s_queryid.equals("DQ_EPOHDR")) {
			i_num = 2;
			s_num = "EPONUM";
	
			s_where = "( ";
			for (int i_fori=0;i_fori<s_showdata.length;i_fori++){
				s_where = s_where + "'" + s_showdata[i_fori][i_num] + "'";
				if (i_fori!=(s_showdata.length - 1) ) s_where =  s_where + ",";
			}
			s_where = s_where + " )";
	
			s_select = "SELECT " + "EPONUM" + " FROM " + "ORDERR" + " WHERE operator = '"+ user_name +"' AND " + s_num + " in "+  s_where;
			s_return = cs.executeSelect(s_select);
			s_lookin = new String[s_showdata.length];
			if (s_return == null) {
				for (int i_fori=0;i_fori<s_showdata.length;i_fori++){
					s_lookin[i_fori]="0";
				}
				return s_lookin;
			}
			
			for (int i_fori=0;i_fori<s_showdata.length;i_fori++){
				i_flag = 0;
				for (int i_forj=0;i_forj<s_return.length;i_forj++){
					if (s_showdata[i_fori][i_num].trim().equals(s_return[i_forj][0])){
						i_flag = 1;
						break;
					}
				}
				if (i_flag==0) {
					s_lookin[i_fori] = "0";
				} else {
					s_lookin[i_fori] = "1";
				}
			}
		}	
		if (s_queryid.equals("DQ_EPYHDR")) {
			s_lookin = new String[s_showdata.length];			
			for (int i_fori=0;i_fori<s_showdata.length;i_fori++){
				s_where = "( ";
				s_where = s_where +     " EPJVDR = '"+s_showdata[i_fori][0] + "'";
				s_where = s_where + " AND EPJSTR = '"+s_showdata[i_fori][1] + "'";
				s_where = s_where + " AND EPJRTD = '"+s_showdata[i_fori][2].substring(2,4)+s_showdata[i_fori][2].substring(5,7)+s_showdata[i_fori][2].substring(8,10) + "'";
				s_where = s_where + " )";
				s_select = "SELECT " + "EPJVDR,EPJSTR,EPJRTD" + " FROM " + "EPYFEE" + " WHERE " + s_where;
				s_return = cs.executeSelect(s_select);
				if (s_return==null) {
					s_lookin[i_fori]="0";
				} else {
					s_lookin[i_fori]="1";
				}
			}
		}	
		return s_lookin;
	}

	public String[] getLookin(String s_queryid,String[][] s_showdata,String s_oldwhere,String user_name){
		int i_num=0,i_flag;
		commsearch.CommTools ct = new commsearch.CommTools();
		commsearch.CommSQL cs = new commsearch.CommSQL();  
		String s_num="",s_tablename="";
		String s_where = "",s_select = "";
		String[][] s_return = null;
		String[] s_lookin = null;
		if (s_queryid.equals("DQ_EPOHDR")){
			i_num = 2;
			s_num = "EPONUM";
			s_tablename = "EPOHDR";			
		}
		if (s_queryid.equals("DQ_ERTHDR")){
			i_num = 1;
			s_num = "ERTNUM";
			s_tablename = "ERTHDR";			
		}
		if (s_queryid.equals("DQ_ERVHDR")){
			i_num = 1;
			s_num = "ERVNUM";
			s_tablename = "ERVHDR";			
		}
		if (s_queryid.equals("DQ_ERJHDR")){
			i_num = 2;
			s_num = "ERJNUM";
			s_tablename = "ERJHDR";			
		}
		
		
		if (!s_oldwhere.equals("")){
			String s_temp="";
			String[][] s_look = null;
			s_temp = "select count(distinct ACTIONLOG.ACT_KEY) FROM " + s_tablename;
			s_temp = s_temp + ",ACTIONLOG WHERE " + s_tablename + "." + s_num;
			s_temp = s_temp + " = ACTIONLOG.ACT_KEY AND ACTIONLOG.ACT_TABLE = '";
			s_temp = s_temp + s_tablename +"'";
			s_temp = s_temp + " AND " + s_oldwhere;
			s_look = cs.executeSelect(s_temp);
			if (!s_look[0][0].equals("0")){
				s_lookin = new String [Integer.parseInt(s_look[0][0])];
			} else {
			}
			
			return s_lookin;
		}
		
		if (s_queryid.equals("DQ_EIPDTL")){
			return null;
		}
		if (s_queryid.equals("DQ_EPYRJT")){
			return null;
		}
		if (s_queryid.equals("DQ_EIVDTL")){
			return null;
		}
		
		if (s_queryid.equals("DQ_EPYHDR")){
			i_num = 2;
			s_num = "EPYVDR/EPYSTR/EPYRDT";
			s_tablename = "EPYHDR";	
			s_lookin = new String[s_showdata.length];
			for (int i_fori=0;i_fori<s_showdata.length;i_fori++){
				s_where = "ACT_KEY = '"+s_showdata[i_fori][0]+"/"+s_showdata[i_fori][1]+"/" +s_showdata[i_fori][2].substring(2,4)+s_showdata[i_fori][2].substring(5,7)+s_showdata[i_fori][2].substring(8,10) +"'";
				s_select = "SELECT DISTINCT " + "ACT_FROM" + " FROM " + "ACTIONLOG" + " WHERE " +  s_where;
				s_return = cs.executeSelect(s_select);
				if (s_return == null){
					s_lookin[i_fori] = "0";					
				} else {
					s_lookin[i_fori] = Integer.toString(s_return.length);
				}
			}
			return s_lookin;		
		}
		s_where = "( ";
		for (int i_fori=0;i_fori<s_showdata.length;i_fori++){

			s_where = s_where + "'" + s_showdata[i_fori][i_num] + "'";
			if (i_fori!=(s_showdata.length - 1) ) s_where =  s_where + ",";
		}
		s_where = s_where + " )";
		s_where = "(" + "ACT_KEY" + " IN " + s_where +")";
		s_select = "SELECT DISTINCT " + "ACT_KEY" + " FROM " + "ACTIONLOG" + " WHERE " ; 
		s_select = s_select + " act_user = '"+user_name +"' AND ACTIONLOG.ACT_TABLE = '";
		s_select = s_select + s_tablename +"' AND ";
		s_select = s_select +  s_where;
		s_return = cs.executeSelect(s_select);
		s_lookin = new String[s_showdata.length];
		if (s_return == null) {
			for (int i_fori=0;i_fori<s_showdata.length;i_fori++){
				s_lookin[i_fori]="0";
			}
			return s_lookin;
		}
		
		for (int i_fori=0;i_fori<s_showdata.length;i_fori++){
			i_flag = 0;
			for (int i_forj=0;i_forj<s_return.length;i_forj++){
				if (s_showdata[i_fori][i_num].trim().equals(s_return[i_forj][0])){
					i_flag = 1;
					break;
				}
			}
			if (i_flag==0) {
				s_lookin[i_fori] = "0";
			} else {
				s_lookin[i_fori] = "1";
			}
		}
			
			
		return s_lookin;
	}

	
	public String getWhere(String s_queryid,ActionForm form,HttpServletRequest request,
	                        Locale locale,MessageResources messages){
		String s_where = "",s_flag="",s_temp="";
		String s_searchkey="",s_searchtitle="",s_searchthing="";
		String[] s_tempkey=null,s_temptitle=null;
		commsearch.util.CommString cst = new commsearch.util.CommString(); 
		commsearch.CommTools ct = new commsearch.CommTools();
		commsearch.CommSQL cs = new commsearch.CommSQL();     
		try {
			s_flag = ct.getParameter(request,"searchkey1");
			
			if (!s_flag.equals("")){
				for (int i_fori = 1;i_fori <= 5;i_fori++){
					s_searchkey = ct.getParameter(request,"searchkey" + Integer.toString(i_fori));
					if (!s_searchkey.equals("")){
						s_searchtitle = ct.getParameter(request,"searchtitle" + Integer.toString(i_fori));
						s_searchthing = ct.getParameter(request,"searchthing" + Integer.toString(i_fori));
						s_searchthing = s_searchthing.trim();
						
						if (!s_searchthing.equals("")){
							if (s_where.equals("")){
								if (i_fori==2 && s_queryid.equals("DQ_EIPDTL")){
									s_where = s_searchkey + " <= " + "'" + s_searchthing + "'";
								} else {
									s_where = s_searchkey + " = " + "'" + s_searchthing + "'";
								}
							} else {
								if (i_fori==2 && s_queryid.equals("DQ_EIPDTL")){
								s_where = s_where + " AND " + s_searchkey + " <= " + "'" + s_searchthing + "'";
								} else {
									s_where = s_where + " AND " + s_searchkey + " = " + "'" + s_searchthing + "'";
								}
							}
						}

						PropertyUtils.setSimpleProperty(form, "searchkey"    + Integer.toString(i_fori),s_searchkey);
						PropertyUtils.setSimpleProperty(form, "searchtitle" + Integer.toString(i_fori),s_searchtitle);
						PropertyUtils.setSimpleProperty(form, "searchthing" + Integer.toString(i_fori),s_searchthing);
					}
				}
				
			} else {
				s_temp = messages.getMessage(locale,"search.searchkey_" + s_queryid);
				if (s_temp==null) s_temp = "";
				s_tempkey = cst.DivString(s_temp,",");
				s_temp = messages.getMessage(locale,"search.searchtitle_" + s_queryid);
				if (s_temp==null) s_temp = "";
				s_temptitle = cst.DivString(s_temp,",");
				for (int i_fori = 1;i_fori <= s_tempkey.length;i_fori++){
					s_searchkey = s_tempkey[i_fori - 1].trim();
					if (!s_searchkey.equals("")) {
						s_searchtitle = s_temptitle[i_fori - 1];
						s_searchthing = ct.getParameter(request,"searchthing" + Integer.toString(i_fori));
						
						
						if (!s_searchthing.equals("")){
							if (s_where.equals("")){
								s_where = s_searchkey + " = " + "'" + s_searchthing + "'";
							} else {
								s_where = s_where + " AND " + s_searchkey + " = " + "'" + s_searchthing + "'";
							}
						}
	
						PropertyUtils.setSimpleProperty(form, "searchkey"    + Integer.toString(i_fori),s_searchkey);
						PropertyUtils.setSimpleProperty(form, "searchtitle" + Integer.toString(i_fori),s_searchtitle);
						PropertyUtils.setSimpleProperty(form, "searchthing" + Integer.toString(i_fori),s_searchthing);
					}
				}
			}
			if (!s_where.equals("")){
				s_where = "(" + s_where +")";
			}
		} catch (Exception e) {
			e.printStackTrace();
			s_where="";
		}
		
		return s_where;
	}


	public String setFormBeanListNew(
	ActionForm form,String s_page,
	String s_where,CommQuery cq,
	String s_queryid,String s_actiondo,
	String s_begintitle,String s_endtitle,
	int i_nowpage,int i_nowoncepage,
	String s_queryiddis,String s_order,String user_name){
		commsearch.util.CommString cstr = new commsearch.util.CommString();
		int i_count = 0;              
		String s_return = "-1";
		int i_lookincount=0;          
		String[][] s_showall = null;
		String[] s_keyall  = null;
		String[][] s_datashow=null;
		String[] s_datakey=null,s_datatitle=null;
		String[] s_lookin=null,s_returnin = null;
		String s_prewOncePage=null;    
		String s_nextOncePage=null;    

		CommQueryData cqd = new CommQueryData(cq);
		CommNewTitle cnt = new CommNewTitle();
		commsearch.page.PageList[] pagelist = null;
		cqd.setActiondo(s_actiondo);
		commsearch.util.CommSearchWhere csw = new commsearch.util.CommSearchWhere(); 
		s_where = csw.whereReplace(s_where);

			i_count = cqd.makeSelectCount(s_where);
			if (i_count < 0 ) i_count = 0;
			commsearch.page.PageForm pf = null;
			commsearch.page.Page p = new commsearch.page.Page("",i_nowpage,i_nowoncepage,0,0,i_count);
			p.init();
			pf = p.getPf();
			pagelist = new commsearch.page.PageList[pf.getEndPage() - pf.getBeginPage() + 1];
			for (int i_fori=0;i_fori<pagelist.length;i_fori++){
				pagelist[i_fori] = new commsearch.page.PageList();
				pagelist[i_fori].list = Integer.toString(pf.getBeginPage() + i_fori);
				pagelist[i_fori].number = Integer.toString(pf.getBeginPage() + i_fori); 
			}
			if (pf.getPrewOncePage()!=null){
				s_prewOncePage = Integer.toString((Integer.parseInt(pf.getPrewOncePage()) - 1 ) * pf.getOncePage() + 1 );
			}
			if (pf.getNextOncePage()!=null){			
				s_nextOncePage = Integer.toString((Integer.parseInt(pf.getNextOncePage()) - 1 ) * pf.getOncePage() + 1) ;
			}
			if (s_order.equals("")){
				
			} else {
				s_order = s_order + " DESC";
			}
			
			cqd.init(s_where,s_order,"");
		
			s_datatitle = cqd.getDatatitle();
			s_showall = cqd.getDatashow();
		if (s_queryid.equals("DQ_EIVDTL")){
		}

			if (s_showall !=null){
				if (s_queryid.equals("DQ_EPOHDR") ||
					s_queryid.equals("DQ_ERVHDR") ||
					s_queryid.equals("DQ_ERJHDR") ||
					s_queryid.equals("DQ_ERTHDR") ){
					String[] s_temp = null;
					s_temp = getLookin(s_queryid,s_showall,s_where,user_name);
					if (s_temp==null) {
						i_lookincount = s_showall.length;
					} else {
						i_lookincount = s_showall.length - s_temp.length;
						if (i_lookincount < 0 ) i_lookincount = 0; 
					}
										
				}
			}
			s_keyall = cqd.getDatakey();
			if (s_showall!= null)	{		
				s_datashow = new String[pf.getNowPageEnd() - pf.getNowPageBegin() + 1 ][s_showall[0].length];
				s_datakey  = new String[pf.getNowPageEnd() - pf.getNowPageBegin() + 1 ];
				for (int i_fori=pf.getNowPageBegin() ;i_fori<=pf.getNowPageEnd()  ;i_fori++){
					for (int i_forj=0;i_forj<s_showall[0].length;i_forj++){
						s_datashow[i_fori - pf.getNowPageBegin()][i_forj] = s_showall[i_fori - 1][i_forj];
					}
					s_datakey [i_fori - pf.getNowPageBegin()] = s_keyall [i_fori -1 ];
					s_datakey [i_fori - pf.getNowPageBegin()] = cstr.replaceAll(s_datakey [i_fori - pf.getNowPageBegin()],s_actiondo +"?queryid=" + s_queryid+"&selectwhere=","");
			
				}
		
				if (s_datashow != null){
					if  (s_datashow.length!=0){
						s_lookin = getLookin(s_queryid,s_datashow,"",user_name);
						s_returnin = getReturnin(s_queryid,s_datashow,user_name);
					}
				}
				if (s_queryid.equals("DQ_EPYHDR")){
					for (int i_fori=0;i_fori<s_lookin.length;i_fori++){
						if (s_lookin[i_fori].equals("2")){
							s_lookin[i_fori] = "1";
						} else {
							if (s_lookin[i_fori].equals("1")){
								if (s_returnin[i_fori].equals("1")){
									s_lookin[i_fori] = "0";
								} else {
									
								}
							}
						}
					}
					
				}
			}		
		try{
			PropertyUtils.setSimpleProperty(form, "prewOncePage",s_prewOncePage);
			PropertyUtils.setSimpleProperty(form, "nextOncePage",s_nextOncePage);
			PropertyUtils.setSimpleProperty(form, "pagelist",pagelist);
			PropertyUtils.setSimpleProperty(form, "page",s_page);
			PropertyUtils.setSimpleProperty(form, "data",s_datashow);
			PropertyUtils.setSimpleProperty(form, "datatitle",s_datatitle);
			PropertyUtils.setSimpleProperty(form, "datakey",s_datakey);
			PropertyUtils.setSimpleProperty(form, "nowpage",Integer.toString(i_nowpage));
			PropertyUtils.setSimpleProperty(form, "nowoncepage",Integer.toString(i_nowoncepage));			
			PropertyUtils.setSimpleProperty(form, "oncepage",Integer.toString(pf.getOncePage()));
			PropertyUtils.setSimpleProperty(form, "lookin" , s_lookin);
			PropertyUtils.setSimpleProperty(form, "returnin" , s_returnin);
			PropertyUtils.setSimpleProperty(form, "selectwhere" , "");
			if (i_lookincount>0){
				String s_lookincount="";
				if (s_queryid.equals("DQ_EPOHDR") ){
					s_lookincount = "有" + i_lookincount + "个订单未阅";
				}
				if (s_queryid.equals("DQ_ERVHDR") ){
					s_lookincount = "有" + i_lookincount + "个收货未阅";
				}
				if (s_queryid.equals("DQ_ERJHDR") ){
					s_lookincount = "有" + i_lookincount + "个收货调整未阅";
				}
				if (s_queryid.equals("DQ_ERTHDR") ){
					s_lookincount = "有" + i_lookincount + "个返厂未阅";
				}
				PropertyUtils.setSimpleProperty(form, "lookincount",s_lookincount);
			}

			if (s_datashow != null){
				if (s_begintitle.trim().equals("")){
					s_begintitle = "当前共有" + Integer.toString(s_datashow.length) + "条记录" ;
				} else {
					
				}
			}
			s_return = "1";		
		} catch (Exception e){
			e.printStackTrace();
		}

		return s_return;
	}
	public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 
		return executeme(mapping,form,request,response);

	}
	
}

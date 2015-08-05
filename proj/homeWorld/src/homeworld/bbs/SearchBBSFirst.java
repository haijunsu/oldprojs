/**
 * @(#)SearchOrder.java  2004-02-01
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package homeworld.bbs;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;
import org.apache.commons.beanutils.PropertyUtils;

import system.action.ErrorValue;

import commsearch.*;
//import commsearch.util.CommUsers;


public class SearchBBSFirst extends Action {
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(SearchBBSFirst.class);
	
	
	public SearchBBSFirst() {
		super();
	}
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException 
		{
		commsearch.CommTools ct = new commsearch.CommTools();
		commsearch.CommSQL cs = new commsearch.CommSQL();     
		Locale locale = null;            
		MessageResources messages = null;

		String username = null;          
		CommQuery cq = null;             
		String s_flag,s_page,s_queryid;
		String s_nowpage,s_nowoncepage;
		String s_where,s_actiondo;
		String s_ghs= "";
		String s_temp;
		String s_ip;
		String[] s_fields=null,s_fieldsch=null,s_fieldskey=null,s_fieldstype=null,s_title=null;

		ActionForward forward = new ActionForward();

		s_ip = request.getRemoteAddr();
		
		HttpSession session = request.getSession(false);
		if (session == null ){
		}
		username = (String)session.getAttribute("userid");
		if (username == null) username="";
		String s_begroup;

		s_queryid = ct.getParameter(request,"queryid");
		if (s_queryid.equals("")){
			s_queryid = "DQ_TOPIC";
		}
		long l_begin,l_end;
		l_begin = System.currentTimeMillis();		
		commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
		commsearch.util.CommActionLog.setTempLog(
			Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
			username,session.getId(),"SearchBBSFirst","B",s_queryid + "0001",0);
		
		if (username.equals("")){
			s_begroup = "00000000000000000000000000000001";
		} else {
			s_begroup = (String)session.getAttribute("begroup");
		}
		s_begroup = "00000000000000000000000000000001";
		try {
			
			s_queryid = ct.getParameter(request,"queryid");
			s_page = ct.getParameter(request,"page");
			if (s_page.equals("")) s_page = "1";
			s_flag = ct.getParameter(request,"flag");
			s_nowpage = ct.getParameter(request,"nowpage");
			if (s_nowpage.equals("")) s_nowpage = "1";
			s_nowoncepage = ct.getParameter(request,"nowoncepage");
			if (s_nowoncepage.equals("")) s_nowoncepage = "1";
			if (s_nowoncepage.equals("0")) s_nowoncepage = "1";
			s_actiondo = ct.getParameter(request,"actiondo");
			s_where = "";
			s_where = getWhere(form,request,username,s_begroup,s_page);


			cq = new CommQuery(s_queryid);
			cq.init();
			s_fieldskey = cq.getFieldskey();
			s_title = cq.getFieldscol("QNAMEC");

			setFormBeanListNew(form,s_page,s_where,cq,s_queryid,s_actiondo,"","",Integer.parseInt(s_nowpage),Integer.parseInt(s_nowoncepage),s_queryid,s_ip,username);

			forward = mapping.findForward("success");
			
			l_end = System.currentTimeMillis();

			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"SearchBBSFirst","E",s_queryid + "0999",l_end - l_begin);
			
		} catch (Exception e) {
			//异常错误
			e.printStackTrace();
			ErrorValue ev = new system.action.ErrorValue();
			ev.setError("SearchOrder","execute","通告一览的主画面出现异常错误:" + e.getMessage());
			request.setAttribute("siev",ev);
			forward = mapping.findForward("failure");
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"SearchBBSFirst","C",s_queryid + "0900",l_end - l_begin);


			return forward;
		}
		return forward;
	}
	public String[] getLookin(String s_queryid,String[][] s_showdata,String s_ip){
		int i_num=0,i_flag;
		String s_num="",s_tablename="";
		String s_where = "",s_select = "",s_user="";
		String[][] s_return = null;
		String[] s_lookin = null;
		commsearch.CommSQL cs = new commsearch.CommSQL();   
			i_num = 0;
			s_num = "TOPIC";
			s_tablename = "TOPICLOG";			
		s_user =  " ACT_IP = '" + s_ip+"'";
		s_where = "( ";
		for (int i_fori=0;i_fori<s_showdata.length;i_fori++){
			s_where = s_where + "'" + s_showdata[i_fori][i_num] + "'";
			if (i_fori!=(s_showdata.length - 1) ) s_where =  s_where + ",";
		}
		s_where = s_where + " )";
		s_where = "(" + "TOPID" + " IN " + s_where +")";
		s_select = "SELECT DISTINCT " + "TOPID" + " FROM " + " TOPICLOG " + " WHERE TOPFLAG='1' AND " + s_user+ " AND "  +  s_where;
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
	
	public String getWhere(ActionForm form,HttpServletRequest request,String username,String s_begroup,String s_page){
		int i_len=0;
		String s_where = "";
		String s_onlinebegin="substr(bbsgroup,";
		String s_onlineend  =",1)='1'"; 
		String s_online="";
		
		try {
			if (s_page.equals("1")){
				if (s_begroup.equals("")) return "( 1 = 0)";
				i_len = s_begroup.length();
				char[] c_begroup=new char[i_len];
				
				s_begroup.getChars(0,i_len,c_begroup,0);
				
				for (int i_fori = 0;i_fori<i_len;i_fori++){
					if (c_begroup[i_fori]=='1'){
						if (s_online.equals("")){
							s_online = "(" + s_onlinebegin + Integer.toString(i_fori + 1) + s_onlineend +")";
						} else {
							s_online = s_online + " OR " +  "(" + s_onlinebegin + Integer.toString(i_fori + 1) + s_onlineend +")";
						}
					}
				}
				if (s_online.equals("")) return "( 1 = 0)";
				s_where = "((" + s_online + ") AND (current date>=effbeg and current date <=effend))";
			} 
			if (s_page.equals("2")){
				s_where="operator='" + username + "'";
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
	String s_queryiddis,String s_ip,String username){
		int i_count = 0;            
		String s_return = "-1";
		
		String[][] s_showall = null,s_showalltemp = null;
		String[] s_keyall  = null;
		String[][] s_datashow=null;
		String[] s_datakey=null,s_datatitle=null;
		String[] s_lookin = null;
		String s_prewOncePage=null;   
		String s_nextOncePage=null;   


		CommQueryData cqd = new CommQueryData(cq);
		CommNewTitle cnt = new CommNewTitle();
		commsearch.page.PageList[] pagelist = null;
		cqd.setActiondo(s_actiondo);
		commsearch.util.CommSearchWhere csw = new commsearch.util.CommSearchWhere(); 
		s_where = csw.whereReplace(s_where);

			i_count = cqd.makeSelectCount(s_where);
			
			if (i_count<0 ) i_count=0;
			
			commsearch.page.PageForm pf = null;
			commsearch.page.Page p = new commsearch.page.Page("",i_nowpage,i_nowoncepage,6,0,i_count);
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
			cqd.init(s_where,"","");	
		
			s_datatitle = cqd.getDatatitle();
			s_showalltemp = cqd.getDatashow();
			s_keyall = cqd.getDatakey();
			if (s_showalltemp!= null)	{
				s_showall = 	s_showalltemp;	
				if (s_showall!= null)	{		
							s_datashow = new String[pf.getNowPageEnd() - pf.getNowPageBegin() + 1 ][s_showall[0].length];
							s_datakey  = new String[pf.getNowPageEnd() - pf.getNowPageBegin() + 1 ];
							for (int i_fori=pf.getNowPageBegin() ;i_fori<=pf.getNowPageEnd()  ;i_fori++){
								for (int i_forj=0;i_forj<s_showall[0].length;i_forj++){
									s_datashow[i_fori - pf.getNowPageBegin()][i_forj] = s_showall[i_fori - 1][i_forj];
								}
								s_datakey [i_fori - pf.getNowPageBegin()] = s_keyall [i_fori -1 ];
							}
				}		
			}
			
			if (s_datashow!=null){
				s_lookin = getLookin(s_queryid,s_datashow,s_ip);
			}
			if (s_datakey!=null){
				for(int i_fori=0;i_fori<s_datakey.length;i_fori++){
					s_datakey[i_fori] = s_datakey[i_fori] + "&nowpage=" + Integer.toString(i_nowpage);
					s_datakey[i_fori] = s_datakey[i_fori] + "&nowoncepage=" + Integer.toString(i_nowoncepage);
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
			PropertyUtils.setSimpleProperty(form, "lookin",s_lookin);
			if (s_datashow != null){
				if (s_begintitle.trim().equals("")){
					s_begintitle = "SearchBBSFirst:Now records is :" + Integer.toString(s_datashow.length) + "条记录" ;
				} else {
					
				}
			}
			s_return = "1";		
		} catch (Exception e){
			e.printStackTrace();

		}

		return s_return;
	}

	
}

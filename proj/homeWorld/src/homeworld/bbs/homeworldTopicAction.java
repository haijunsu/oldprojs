/*
 * @(#)homeworldTopicAction.java  2004-2-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.bbs;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author lyc
 */


import java.util.Date;
import java.io.IOException;
import java.util.Vector;

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
import com.idn.sql.DynaSqlBean;
//import com.idn.util.FormatDate;

//import homeworld.fun.systemfun;

/**
 * <P>菜单管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class homeworldTopicAction extends  SecureAction {
	ActionErrors errors = new ActionErrors();

	/**
	 * 构造函数
	 */
	public homeworldTopicAction() {
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
				uname,hs.getId(),"Topic","B","LYC0000000",0);
			
		try{
			Vector vecTemp=new Vector();
			//page--> 0：录入 1：更改
			String page=((String) PropertyUtils.getSimpleProperty(form, "page")).trim();
			String temp = (String)request.getParameter("selectwhere");
			String flag=((String) PropertyUtils.getSimpleProperty(form, "flag")).trim();
			
			String nowpage =((String) PropertyUtils.getSimpleProperty(form,"nowpage")).trim() ;
			String nowoncepage =((String) PropertyUtils.getSimpleProperty(form,"nowoncepage")).trim() ;
			if(nowpage.equals("")){
				nowpage = (String)request.getParameter("nowpage");
				nowoncepage= (String)request.getParameter("nowoncepage");
				if (nowpage==null) nowpage="1";
				if (nowoncepage==null) nowoncepage="1";
				PropertyUtils.setSimpleProperty(form, "nowpage"  ,nowpage);
				PropertyUtils.setSimpleProperty(form, "nowoncepage"  ,nowoncepage);
			}
			
			if (temp==null && flag.equals("")){
				resetFormbeen(form,request); 
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Topic","E","LYC8888881",l_end - l_begin);  
				return(mapping.findForward("success"));         
			}
			if (temp!=null && flag.equals("")){
				page = "1";
				temp=temp.trim();
				setFormbeen(form,temp);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Topic","E","LYC8888882",l_end - l_begin);
				return(mapping.findForward("success"));
			}

			//不保存
			if(flag.equals("") || flag.equals("lrexic") ){
				if (page.equals("0")){
					resetFormbeen(form,request);
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Topic","E","LYC8888883",l_end - l_begin);
					return(mapping.findForward("success"));
					}
				if(setFormbeen(form,temp).equals("0")){
					saveErrors(request, errors);
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Topic","E","LYC8888884",l_end - l_begin);
					return(mapping.findForward("success"));
				}	
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Topic","E","LYC8888885",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			
			//提交数据库
			String sql="";
			String freeformstate=(String) PropertyUtils.getSimpleProperty(form,"freeformstate");
			
			String topid=getNo();
			String seq="0";
			if(page.equals("1")){
				topid=((String) PropertyUtils.getSimpleProperty(form,"topid")).trim();
				vecTemp.addElement("delete from topic where topid='"+topid+"' and seq="+seq);
			}
			PropertyUtils.setSimpleProperty(form,"topid",topid);
			
			if(!flag.equals("del")){		
				if(freeformstate.equals("1") || freeformstate.equals("4")){		
					sql="insert into topic (TOPID,SEQ,USERID,TOPICC,BROWTIMS,TOPTIME,CONTENTC,BBSSTATE,BBSKIND,EFFBEG,EFFEND,OPERATOR,OPERDATE,DIRECTOR,CANCELER,BBSGROUP) values(";
					//通告号
					sql=sql+"'"+topid+"',";
					//序号
					sql=sql+"0,";
					//用户ID
					sql=sql+"'',";
					//标题
					temp=((String) PropertyUtils.getSimpleProperty(form,"topicc")).trim();
					sql=sql+"'"+temp+"',";
					//浏览次数
					sql=sql+"0,";
					//发表时间
					Date dd=new Date();
					temp=com.idn.util.FormatDate.format(dd, "yyyy-MM-dd HH:mm:ss");
					sql=sql+"'"+temp+"',";
					//内容
					temp=((String) PropertyUtils.getSimpleProperty(form,"contentc")).trim();
					if(temp.length()>512) temp=temp.substring(0,512);
					sql=sql+"'"+temp+"',";            
					//通告状态
					sql=sql+"'01',";
					//通告类型         
					temp=((String) PropertyUtils.getSimpleProperty(form,"bbskind")).trim();
					sql=sql+"'"+temp+"',";
					//生效始日期
					temp=((String) PropertyUtils.getSimpleProperty(form,"effbeg")).trim();
					sql=sql+"'"+temp+"',";
					//生效止日期        
					temp=((String) PropertyUtils.getSimpleProperty(form,"effend")).trim();
					sql=sql+"'"+temp+"',";
					//操作人
					//HttpSession hs = request.getSession();
					temp=(String)hs.getAttribute("userid");
					sql=sql+"'"+temp+"',";
					//操作日期
					temp=com.idn.util.FormatDate.format(dd, "yyyy-MM-dd");
					//temp=((String) PropertyUtils.getSimpleProperty(form,"operdate")).trim();
					sql=sql+"'"+temp+"',";
					//审核人
					temp=(String)hs.getAttribute("userid");
					sql=sql+"'"+temp+"',";
					//sql=sql+"'',";
					
					//取消人
					sql=sql+"'',";
					//通告所属组
					String[] bbsgroup=null;  //通告所属组
					bbsgroup=(String[]) PropertyUtils.getSimpleProperty(form,"bbsgroup");
					temp=homeworld.fun.systemfun.coding(bbsgroup);
					sql=sql+"'"+temp+"')";
					
					vecTemp.addElement(sql);
				}
			}
			String[] strsql = (String[]) vecTemp.toArray(new String[0]); 
			try{
				DynaSqlBean dybBean = new DynaSqlBean();
				dybBean.setSql(strsql);
				dybBean.executeBatch();
				if(page.equals("1")){
					BbsFun bf = new BbsFun();
					bf.clearTopicLog(topid);
				}
			} catch (Exception e){

				e.printStackTrace();
				setFormbeen(form,"topid='"+topid+"' and seq=0");
				/*
				12月2日修改
				锁死错误
				*/
				PropertyUtils.setSimpleProperty(form, "flag"  ,"");
				errors.add("errormessage",new ActionError("Database.wrong"));
				saveErrors(request, errors);
				//return(mapping.findForward("success"));
				
//				普通错误
//				errors.add("errormessage",new ActionError("Database.writedb"));
//				saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Topic","C","LYC9999991",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			

			//为录入时提交
			if(flag.equals("del")){
				setFormbeen(form,"topid='"+topid+"' and seq=0");
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Topic","E","LYC8888886",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			
			if(flag.equals("xg")){
				setFormbeen(form,"topid='"+topid+"' and seq=0");
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Topic","E","LYC8888887",l_end - l_begin);
				return(mapping.findForward("select"));
			}
			if(flag.equals("lr")){
				resetFormbeen(form,request);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Topic","E","LYC8888888",l_end - l_begin);
				return(mapping.findForward("select"));
			}
			
			//为录入时提交
			if(flag.equals("commit")){
				setFormbeen(form,"topid='"+topid+"' and seq=0");
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Topic","E","LYC8888889",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			
			resetFormbeen(form,request);

//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"Topic","E","LYC8888890",l_end - l_begin);
						
			return(mapping.findForward("success"));
			
		} catch (Exception e){
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Database.formbean"));
			saveErrors(request, errors);
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"Topic","C","LYC9999999",l_end - l_begin);
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
	public String setFormbeen(ActionForm form,String where) throws Exception{
		
		PropertyUtils.setSimpleProperty(form, "page","1");

		DataBean dbBean = new DataBean();
		String strsql="select * from topic where "+where+"";
		try{
			dbBean.executeSelect(strsql);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Database.readdb"));
			return "0";
		}	
		
		String temp      =dbBean.getElementValue(0,"bbsgroup").trim();  
		
		String topid     =dbBean.getElementValue(0,"topid").trim();  //标题
		PropertyUtils.setSimpleProperty(form, "topid"  ,topid );
		
		String seq       =dbBean.getElementValue(0,"seq").trim();   //内容
		PropertyUtils.setSimpleProperty(form, "seq"  ,seq );

		String topicc    =dbBean.getElementValue(0,"topicc").trim();    //标题
		PropertyUtils.setSimpleProperty(form, "topicc"  ,topicc);
		
		String contentc  =dbBean.getElementValue(0,"contentc").trim();   //内容
		PropertyUtils.setSimpleProperty(form, "contentc"  ,contentc );
	
		String bbskind   =dbBean.getElementValue(0,"bbskind").trim();    //通告类型
		PropertyUtils.setSimpleProperty(form, "bbskind"  ,bbskind);
	
		String effbeg    =dbBean.getElementValue(0,"effbeg").trim();    //生效始日期
		PropertyUtils.setSimpleProperty(form, "effbeg"  ,effbeg);
		
		String effend    =dbBean.getElementValue(0,"effend").trim();  //生效止日期
		PropertyUtils.setSimpleProperty(form, "effend"  ,effend );

		String operatorshow=homeworld.fun.systemfun.getNamec(dbBean.getElementValue(0,"operator").trim());  //操作人
		PropertyUtils.setSimpleProperty(form, "operatorshow"  ,operatorshow );
		
		String operatorid  =dbBean.getElementValue(0,"operator").trim(); //操作人
		PropertyUtils.setSimpleProperty(form, "operatorid"  ,operatorid);

		String operdate  =dbBean.getElementValue(0,"operdate").trim(); //操作日期
		PropertyUtils.setSimpleProperty(form, "operdate"  ,operdate);


		String[] readioshow =null;//通告显示		
		strsql="select * from codes where cclass='21' order by ccode";
		try{
			dbBean.executeSelect(strsql);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Database.readdb"));
		}
		int count =dbBean.getRowCount();
		readioshow=new String[count];
		for(int i=0;i<count;i++){
			readioshow[i]=dbBean.getElementValue(i,"cshowc").trim() ;
		}
		PropertyUtils.setSimpleProperty(form, "readioshow",readioshow);

		String[] bbsgroup=null;  //通告所属组
		String[] bbsgroupshow=null;  //通告所属组
		strsql="select * from codes where cclass='25' order by ccode";
		try{
			dbBean.executeSelect(strsql);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Datebase.readdb"));
		}
		count =dbBean.getRowCount();
		bbsgroupshow=new String[count];
		for(int i=0;i<count;i++){
			bbsgroupshow[i]=dbBean.getElementValue(i,"cshowc").trim() ;
		}
		bbsgroup=homeworld.fun.systemfun.decode(temp);
		PropertyUtils.setSimpleProperty(form, "bbsgroupshow",bbsgroupshow);
		PropertyUtils.setSimpleProperty(form, "bbsgroup"  ,bbsgroup);


		Date dd=new Date();
		 temp=com.idn.util.FormatDate.format(dd, "yyyy-MM-dd");
		PropertyUtils.setSimpleProperty(form, "nowdate"      ,temp);
		return "1";
	}

	public void resetFormbeen(ActionForm form,HttpServletRequest request) throws Exception{
		
		PropertyUtils.setSimpleProperty(form, "page","0");
		
		String[] readioshow=null; 

		DataBean dbBean = new DataBean();
		String strsql="select * from codes where cclass='21' order by ccode";
		try{
			dbBean.executeSelect(strsql);
		} catch (Exception e) {
			errors.add("errormessage",new ActionError("Database.readdb"));
		}
		int count =dbBean.getRowCount();
		readioshow=new String[count];
		
		for(int i=0;i<count;i++){
				readioshow[i]=dbBean.getElementValue(i,"cshowc").trim() ;
		}
		PropertyUtils.setSimpleProperty(form, "bbskind"     ,dbBean.getElementValue(count-1,"ccode").trim());
		PropertyUtils.setSimpleProperty(form, "readioshow"  ,readioshow);
		
		PropertyUtils.setSimpleProperty(form, "topicc"      ,"");
		PropertyUtils.setSimpleProperty(form, "contentc"    ,"");

		Date dd=new Date();
		String temp=com.idn.util.FormatDate.format(dd, "yyyy-MM-dd");
		PropertyUtils.setSimpleProperty(form, "nowdate"      ,temp);
		
		PropertyUtils.setSimpleProperty(form, "effbeg"      ,temp);
		PropertyUtils.setSimpleProperty(form, "effend"      ,temp);
		
		PropertyUtils.setSimpleProperty(form, "bbsgroup"    ,readioshow);
		PropertyUtils.setSimpleProperty(form, "bbsgroupshow",readioshow);
		
		
		HttpSession hs = request.getSession();
		String uname=(String)hs.getAttribute("userid");
		PropertyUtils.setSimpleProperty(form, "operatorshow",homeworld.fun.systemfun.getNamec(uname).trim() );
		PropertyUtils.setSimpleProperty(form, "operatorid"  ,uname.trim() );
		
		
		PropertyUtils.setSimpleProperty(form, "operdate",temp.trim() );
		
		String[] bbsgroup=new String[0];  //通告所属组
		String[] bbsgroupshow=null;  //通告所属组

		
		strsql="select * from codes where cclass='25' order by ccode";
		try{
			dbBean.executeSelect(strsql);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Database.readdb"));
		}
		count =dbBean.getRowCount();
		bbsgroupshow=new String[count];
		for(int i=0;i<count;i++){
			bbsgroupshow[i]=dbBean.getElementValue(i,"cshowc").trim() ;
		}
		PropertyUtils.setSimpleProperty(form, "bbsgroupshow",bbsgroupshow);
		PropertyUtils.setSimpleProperty(form, "bbsgroup"  ,bbsgroup);
		
		PropertyUtils.setSimpleProperty(form, "topid","");
		PropertyUtils.setSimpleProperty(form, "seq"  ,"0");
		
		
	}
		

				
		public String getNo() throws Exception{
			
			Date dd=new Date();
			String temp=com.idn.util.FormatDate.format(dd, "yyMMdd");
			
			String sql="select max(topid) from topic where topid like '"+temp+"%'";
			DataBean dbBean = new DataBean();	
			try{
			dbBean.executeSelect(sql);
			} catch (Exception e) {
				e.printStackTrace();
			errors.add("errormessage",new ActionError("Database.readdb"));
			return "";
			}
			if(! dbBean.getElementValue(0,0).equals("")){
				temp=dbBean.getElementValue(0,0).trim();
				temp=String.valueOf(Long.parseLong(temp)+1);
				temp="0000000000"+temp;
				temp=temp.substring(temp.length()-10);
			}else{		
				temp=temp+"0001";
			}
		
			return temp;
		}

	/* (non-Javadoc)
	 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return  executeme( mapping,form,request,response);
	}

	
}

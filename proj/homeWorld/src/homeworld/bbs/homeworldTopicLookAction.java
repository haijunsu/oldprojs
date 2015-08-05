/*
 * @(#)homeworldTopicLookAction.java  2004-2-26
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


//	import java.util.Date;
	import java.io.IOException;
	import java.util.Vector;

	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
//	import javax.servlet.http.HttpSession;
	import org.apache.commons.beanutils.PropertyUtils;
//	import org.apache.struts.action.Action;
import org.apache.struts.action.Action;
	import org.apache.struts.action.ActionError;
	import org.apache.struts.action.ActionErrors;
	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionForward;
	import org.apache.struts.action.ActionMapping;

	import com.idn.sql.DataBean;
import javax.servlet.http.*;	
import com.idn.sql.DynaSqlBean;
//	import com.idn.sql.DynaSqlBean;
//	import com.idn.util.FormatDate;



	/**
	 * <P>菜单管理</P>
	 * 
	 * @version 0.1
	 * @author 李永初
	 */
	public class homeworldTopicLookAction extends  Action {
		ActionErrors errors = new ActionErrors();

		/**
		 * 构造函数
		 */
		public homeworldTopicLookAction() {
			super();
		}

		/** (non-Javadoc)
		 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 */
		public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,	HttpServletResponse response)throws IOException, ServletException {

				errors.clear();
				errors.add("header",new ActionError("errors.header"));
				errors.add("footer",new ActionError("errors.footer"));	
			
				Vector vecTemp=new Vector();
				String temp = (String)request.getParameter("selectwhere");

				HttpSession session = request.getSession();
				String username = (String)session.getAttribute("userid");
				if (username==null) username="";

				HttpSession hs = request.getSession();
				String uname=(String)hs.getAttribute("userid");

				//TODO 临时用来监测用
				long l_begin,l_end;
				l_begin = System.currentTimeMillis();		
				commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"TopicLook","B","LYC0000000",0);
			
				try{
				String nowpage = (String)request.getParameter("nowpage");
				String nowoncepage = (String)request.getParameter("nowoncepage");
				String first = request.getParameter("first");
				if (first==null) first="0";
				if (first.equals("")) first="0";
				PropertyUtils.setSimpleProperty(form, "nowpage"  ,nowpage);
				PropertyUtils.setSimpleProperty(form, "nowoncepage",nowoncepage);
				PropertyUtils.setSimpleProperty(form, "first",first);
				
				temp=temp.trim();
				setFormbeen(form,temp,request.getRemoteAddr(),username);

//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"TopicLook","E","LYC8888881",l_end - l_begin); 
				return(mapping.findForward("success"));
			} catch (Exception e){
				e.printStackTrace();
				errors.add("errormessage",new ActionError("Database.formbean"));
				saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"TopicLook","C","LYC9999999",l_end - l_begin); 
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
		public String setFormbeen(ActionForm form,String where,
								   String act_ip,String userid) throws Exception{
			
			
			String sql="update  topic set browtims = browtims + 1  where "+where;
	
			DynaSqlBean dybBean = new DynaSqlBean();
			dybBean.setSql(sql);
			try{
				dybBean.executeBatch();
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("errormessage",new ActionError("Database.readdb"));
				return "0";
			}	

						
			DataBean dbBean = new DataBean();
			String strsql="select * from topic where "+where+"";
			try{
				dbBean.executeSelect(strsql);
			} catch (Exception e) {
				e.printStackTrace();
				errors.add("errormessage",new ActionError("Database.readdb"));
				return "0";
			}	

			String topicc    =dbBean.getElementValue(0,"topicc").trim();    //标题
			PropertyUtils.setSimpleProperty(form, "topicc"  ,topicc);
		
			String contentc  =dbBean.getElementValue(0,"contentc").trim();   //内容
			PropertyUtils.setSimpleProperty(form, "contentc"  ,contentc );
	
			String bbskind   =dbBean.getElementValue(0,"bbskind").trim();    //通告类型
			PropertyUtils.setSimpleProperty(form, "bbskind"  ,bbskind);
	
			String operatorshow=getNamec(dbBean.getElementValue(0,"operator").trim());  //操作人
			PropertyUtils.setSimpleProperty(form, "operatorshow"  ,operatorshow );
		
			String operatorid  =dbBean.getElementValue(0,"operator").trim(); //操作人
			PropertyUtils.setSimpleProperty(form, "operatorid"  ,operatorid);

			String operdate  =dbBean.getElementValue(0,"operdate").trim(); //操作日期
			PropertyUtils.setSimpleProperty(form, "operdate"  ,operdate);
			
			//写通告日志
			BbsFun bf = new BbsFun();
			bf.addTopicLog(dbBean.getElementValue(0,"topid").trim(),act_ip,userid);			
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
	}

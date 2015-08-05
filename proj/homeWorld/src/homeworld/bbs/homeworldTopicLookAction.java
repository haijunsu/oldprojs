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
	 * <P>�˵�����</P>
	 * 
	 * @version 0.1
	 * @author ������
	 */
	public class homeworldTopicLookAction extends  Action {
		ActionErrors errors = new ActionErrors();

		/**
		 * ���캯��
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

				//TODO ��ʱ���������
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

//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"TopicLook","E","LYC8888881",l_end - l_begin); 
				return(mapping.findForward("success"));
			} catch (Exception e){
				e.printStackTrace();
				errors.add("errormessage",new ActionError("Database.formbean"));
				saveErrors(request, errors);
//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"TopicLook","C","LYC9999999",l_end - l_begin); 
				return(mapping.findForward("success"));
			}

	}

		/**
		 * ��ָ����������ݴ�����ʾFormBeen(С���)
		 * 
		 * @param ActionForm ��ʾFormBeen
		 * @param String ���ʿ���
		 * @return String 1���ɹ���0�����ɹ���
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

			String topicc    =dbBean.getElementValue(0,"topicc").trim();    //����
			PropertyUtils.setSimpleProperty(form, "topicc"  ,topicc);
		
			String contentc  =dbBean.getElementValue(0,"contentc").trim();   //����
			PropertyUtils.setSimpleProperty(form, "contentc"  ,contentc );
	
			String bbskind   =dbBean.getElementValue(0,"bbskind").trim();    //ͨ������
			PropertyUtils.setSimpleProperty(form, "bbskind"  ,bbskind);
	
			String operatorshow=getNamec(dbBean.getElementValue(0,"operator").trim());  //������
			PropertyUtils.setSimpleProperty(form, "operatorshow"  ,operatorshow );
		
			String operatorid  =dbBean.getElementValue(0,"operator").trim(); //������
			PropertyUtils.setSimpleProperty(form, "operatorid"  ,operatorid);

			String operdate  =dbBean.getElementValue(0,"operdate").trim(); //��������
			PropertyUtils.setSimpleProperty(form, "operdate"  ,operdate);
			
			//дͨ����־
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

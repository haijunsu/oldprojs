/*
 * @(#)homeworldTestAction.java  2004-3-18
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.bbs;


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
		import com.idn.sql.DynaSqlBean;

		/**
		 * <P>菜单管理</P>
		 * 
		 * @version 0.1
		 * @author 李永初
		 */
		public class homeworldTestAction extends  SecureAction {
			ActionErrors errors = new ActionErrors();

			/**
			 * 构造函数
			 */
			public homeworldTestAction() {
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
						uname,hs.getId(),"Test","B","LYC0000000",0);
						
						
					if(!uname.trim().equals("ADMINB")){
						errors.add("errormessage",new ActionError("NAME.ERROR"));
						saveErrors(request, errors);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Test","E","LYC555555",l_end - l_begin);
						return(mapping.findForward("success"));
					}
					
			try{		
					String returns="";
					String contentc=((String) PropertyUtils.getSimpleProperty(form, "contentc")).trim();
					String[] strsql=setFormbeen(contentc,";");
					
					DynaSqlBean dybBean = new DynaSqlBean();
					if(contentc ==""){

//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Test","E","LYC8888881",l_end - l_begin);
						
						return(mapping.findForward("success"));
					}
					dybBean.setSql(strsql);
					//returns= 
					dybBean.executeBatch();
					contentc="";
			
					for(int i=0;i<strsql.length;i++){
						contentc=contentc+strsql[i]+";\r\n";
					}
						
					PropertyUtils.setSimpleProperty(form, "contentc",contentc);
					
					PropertyUtils.setSimpleProperty(form, "returns",returns);				


//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Test","E","LYC8888888",l_end - l_begin);
						
					return(mapping.findForward("success"));
				} catch (Exception e){
					e.printStackTrace();
					errors.add("errormessage",new ActionError("Database.formbean"));
					saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Test","C","LYC9999999",l_end - l_begin);
					return(mapping.findForward("success"));
				}

		}
				/**
				 * 将指定的字符串替换回车后转换为数组，中间以指定的字符delimiter分隔
				 * 如果delimiter为null，则delimiter为空格。
				 *
				 * @param str 待分割的字符串
				 * @param delimiter 分隔符
				 *
				 * @return 数组，所有元素都已经trim()
				 * @since JDK 1.4
				 */
			public String[] setFormbeen(String str,String delimiter) throws Exception{
				String ss=com.idn.util.CommonTools.stringReplace(str,"\r\n"," ");
				String[] re=com.idn.util.CommonTools.stringToArray(ss,delimiter);
				return re;
			}
			/* (non-Javadoc)
			 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
			 */
			public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
				return  executeme( mapping,form,request,response);
			}

		}

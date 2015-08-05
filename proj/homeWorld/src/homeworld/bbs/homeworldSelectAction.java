/*
 * @(#)homeworldSelectAction.java  2004-4-9
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
			import com.idn.sql.DataBean;

			/**
			 * <P>菜单管理</P>
			 * 
			 * @version 0.1
			 * @author 李永初
			 */
			public class homeworldSelectAction  extends  SecureAction {
				ActionErrors errors = new ActionErrors();

				/**
				 * 构造函数
				 */
				public homeworldSelectAction () {
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
//			
						
			//TODO 临时用来监测用
			long l_begin,l_end;
			l_begin = System.currentTimeMillis();		
			commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"Select","B","LYC0000000",0);
			
//						if(!uname.trim().equals("ADMINB")){
//							errors.add("errormessage",new ActionError("NAME.ERROR"));
//							saveErrors(request, errors);
//							return(mapping.findForward("success"));
//						}
						
				try{			
						String[][] show=null;
						String contentc=((String) PropertyUtils.getSimpleProperty(form, "contentc")).trim();
						
						if(contentc.equals("")){
//							TODO 临时用来监测用
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"Select","E","LYC8888881",l_end - l_begin);
							return(mapping.findForward("success"));
						}
						
						
						DataBean dbBean = new DataBean();
						try{
							dbBean.executeSelect(contentc);
						} catch (Exception e) {
							e.printStackTrace();
							errors.add("errormessage",new ActionError("Database.readdb"));
							saveErrors(request, errors);
//							TODO 临时用来监测用
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"Select","C","LYC9999991",l_end - l_begin);
							return(mapping.findForward("success"));
						}
						
						show=new String [dbBean.getRowCount()][dbBean.getColumnCount()];
						String[] column=new String[dbBean.getColumnCount()];
						
						
						for(int i=0;i<dbBean.getRowCount();i++){
							for(int j=0;j<dbBean.getColumnCount();j++){
									show[i][j]=dbBean.getElementValue(i,j).trim();
									if(show[i][j].equals("")) {show[i][j]="--";} 
							}
						}


						for(int j=0;j<dbBean.getColumnCount();j++){
								column[j]=dbBean.getColumnName(j).trim();
						}
						

						PropertyUtils.setSimpleProperty(form, "columnname",column);
						PropertyUtils.setSimpleProperty(form, "show",show);
						PropertyUtils.setSimpleProperty(form, "contentc",contentc);
						

//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Select","E","LYC8888888",l_end - l_begin);
						
						return(mapping.findForward("success"));
					} catch (Exception e){
						e.printStackTrace();
						errors.add("errormessage",new ActionError("Database.formbean"));
						saveErrors(request, errors);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Select","C","LYC9999999",l_end - l_begin);
						return(mapping.findForward("success"));
					}

			}

				/* (non-Javadoc)
				 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
				 */
				public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

					return  executeme( mapping,form,request,response);
				}

			}

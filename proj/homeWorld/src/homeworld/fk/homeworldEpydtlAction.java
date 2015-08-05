/*
 * @(#)homeworldEpydtlAction.java  2004-7-1
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.fk;

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
			import com.idn.util.CommonTools;
			import commsearch.util.CommActionLog;
			import commsearch.util.CommDate;


			/**
			 * <P>菜单管理</P>
			 * 
			 * @version 0.1
			 * @author 李永初
			 */
			public class homeworldEpydtlAction  extends SecureAction {
				//private static LogWrapper log= new LogWrapper(SalaryCardCtrl.class);

				ActionErrors errors = new ActionErrors();

				/**
				 * 构造函数
				 */
				public homeworldEpydtlAction  () {
					super();
				}

				/** (non-Javadoc)
				 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
				 */
				public ActionForward executeme(ActionMapping mapping,ActionForm form,
					HttpServletRequest request,	HttpServletResponse response)throws  IOException, ServletException {

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
							uname,hs.getId(),"Epydtl","B","LYC0000000",0);
						
						
						if(uname==null){
							errors.add("errormessage",new ActionError("NoName"));
							saveErrors(request, errors);
//							TODO 临时用来监测用
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"Epydtl","E","LYC5555555",l_end - l_begin);
							return(mapping.findForward("success"));
						}
						if(uname.trim().equals("")){
							errors.add("errormessage",new ActionError("NoName"));
							saveErrors(request, errors);
//							TODO 临时用来监测用
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"Epydtl","E","LYC6666666",l_end - l_begin);
							return(mapping.findForward("success"));
						}

			try{
						String selectwhere= (String)request.getParameter("selectwhere");
						
						selectwhere=CommonTools.stringReplace(selectwhere,"EPY","EPD");

						String stemp=selectwhere;
						
						if(stemp==null)
							stemp="";
						if(stemp.equals("")){
//							TODO 临时用来监测用
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"epydtl","E","LYC3333333",l_end - l_begin);
							return(mapping.findForward("err"));
						}
						
						PropertyUtils.setSimpleProperty(form,"selectwhere",selectwhere);
			
						setFormbeen(form,request,selectwhere,uname);

//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Epydtl","E","LYC8888888",l_end - l_begin);
						return(mapping.findForward("success"));
					} catch (Exception e){
						e.printStackTrace();
						errors.add("errormessage",new ActionError("Datebase.formbean"));
						saveErrors(request, errors);
//				TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Epydtl","C","LYC9999999",l_end - l_begin);
						return(mapping.findForward("err"));
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
				public String setFormbeen(ActionForm form,HttpServletRequest request,String where,String uname) throws Exception{

					int count =0;

					DataBean dbBean = new DataBean();
					String strsql="select * from epydtl where "+where+" ";
					//strsql="select * from epydtl  ";
//					try{
						dbBean.executeSelect(strsql);
//					} catch (Exception e) {
//						e.printStackTrace();
//						errors.add("errormessage",new ActionError("Datebase.readdb"));
//						return "0";
//					}
					count=dbBean.getRowCount();
					
					if(count==0){
						return "1";
					}
						
					String[] epdvdr=new String[count];//供货商号
					String[] epdstr=new String[count];//商店号
					String[] epdrdt=new String[count];//付日期
			
					String[] epdtrk=new String[count];//交易类型
					String[] epdivn=new String[count];//交易日期
					String[] epdivd=new String[count];//金额
					
					CommDate cd=new CommDate();		
												
					for(int i=0;i<count;i++){
						epdvdr[i]=dbBean.getElementValue(i,"epdvdr").trim() ;        
						epdstr[i]=dbBean.getElementValue(i,"epdstr").trim() ;        
						epdrdt[i]=cd.dateFormat(dbBean.getElementValue(i,"epdrdt").trim(),"L");		  
                                                               
						epdtrk[i]=dbBean.getElementValue(i,"epdtrk").trim() ;        
						epdivn[i]=dbBean.getElementValue(i,"epdivn").trim() ;        
						epdivd[i]=cd.dateFormat(dbBean.getElementValue(i,"epdivd").trim(),"L");		
						
						//eipyta[i]=DecimalTools.format(dbBean.getElementValue(i,"eipyta").trim(),"L");		
						
					}

					//写日志
					CommActionLog  cal= new CommActionLog();
					 String temp=epdvdr[0]+"/"+epdstr[0]+"/"+dbBean.getElementValue(0,"epdrdt").trim();		 
					
					cal.setAct_user(uname);
					cal.setAct_from("homeworldEpydtl");
					cal.setAct_do("SEA");
					cal.setAct_key(temp);
					cal.setAct_table("EPYHDR");
					cal.setAct_ip(request.getRemoteAddr());
					cal.setAct_memo("查询已付款明细信息：" + temp);
					cal.setAct_me("");
					cal.setActionLog();
					

					PropertyUtils.setSimpleProperty(form, "epdvdr"   ,epdvdr );
					PropertyUtils.setSimpleProperty(form, "epdstr"   ,epdstr );
					PropertyUtils.setSimpleProperty(form, "epdrdt"   ,epdrdt );
			                                                                        
					PropertyUtils.setSimpleProperty(form, "epdtrk"   ,epdtrk );
					PropertyUtils.setSimpleProperty(form, "epdivn"   ,epdivn );
					PropertyUtils.setSimpleProperty(form, "epdivd"   ,epdivd );


					return "1";
				}

				/* (non-Javadoc)
				 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
				 */
				public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
					return  executeme( mapping,form,request,response);
				}
	
	
	
			}

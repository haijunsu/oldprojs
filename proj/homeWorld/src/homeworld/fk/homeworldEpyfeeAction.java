/*
 * @(#)homeworldEpyfeeAction.java  2004-7-1
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
				import com.idn.util.DecimalTools;

				import commsearch.util.CommActionLog;
				import commsearch.util.CommDate;


				/**
				 * <P>菜单管理</P>
				 * 
				 * @version 0.1
				 * @author 李永初
				 */
				public class homeworldEpyfeeAction   extends SecureAction {
					//private static LogWrapper log= new LogWrapper(SalaryCardCtrl.class);

					ActionErrors errors = new ActionErrors();

					/**
					 * 构造函数
					 */
					public homeworldEpyfeeAction () {
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
								uname,hs.getId(),"Epyfee","B","LYC0000000",0);
						
							if(uname==null){
								errors.add("errormessage",new ActionError("NoName"));
								saveErrors(request, errors);
//								TODO 临时用来监测用
								l_end = System.currentTimeMillis();
								commsearch.util.CommActionLog.setTempLog(
									Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
									uname,hs.getId(),"Epyfee","E","LYC5555555",l_end - l_begin);
								return(mapping.findForward("success"));
							}
							if(uname.trim().equals("")){
								errors.add("errormessage",new ActionError("NoName"));
								saveErrors(request, errors);
//								TODO 临时用来监测用
								l_end = System.currentTimeMillis();
								commsearch.util.CommActionLog.setTempLog(
									Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
									uname,hs.getId(),"Epyfee","E","LYC6666666",l_end - l_begin);
								return(mapping.findForward("success"));
							}

					try{
							String selectwhere= (String)request.getParameter("selectwhere");

							String stemp=selectwhere;
							
							if(stemp==null)
								stemp="";
							if(stemp.equals("")){
//								TODO 临时用来监测用
								l_end = System.currentTimeMillis();
								commsearch.util.CommActionLog.setTempLog(
									Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
									uname,hs.getId(),"Epyfee","E","LYC3333333",l_end - l_begin);
								return(mapping.findForward("err"));
							}
							
							selectwhere=CommonTools.stringReplace(selectwhere,"RDT","RTD");
							selectwhere=CommonTools.stringReplace(selectwhere,"EPY","EPJ");
							
							PropertyUtils.setSimpleProperty(form,"selectwhere",selectwhere);
			
							setFormbeen(form,request,selectwhere,uname);

//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Epyfee","E","LYC8888888",l_end - l_begin);
							return(mapping.findForward("success"));
						} catch (Exception e){
							e.printStackTrace();
							errors.add("errormessage",new ActionError("Datebase.formbean"));
							saveErrors(request, errors);
//						TODO 临时用来监测用
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Epyfee","C","LYC9999999",l_end - l_begin);
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
						String strsql="select * from epyfee where "+where+" ";
						//strsql="select * from epyfee  ";
//						try{
							dbBean.executeSelect(strsql);
//						} catch (Exception e) {
//							e.printStackTrace();
//							errors.add("errormessage",new ActionError("Datebase.readdb"));
//							return "0";
//						}
						count=dbBean.getRowCount();

						if(count==0){
							return "1";
						}
	
						String[] epjvdr=new String[count+1];      //供货商号
						String[] epjstr=new String[count+1];      //商店号
						String[] epjrtd=new String[count+1];      //付款日期
						String[] epjpst=new String[count+1];      //协议对应的商店
						String[] epjspn=new String[count+1];      //系统协议号
						String[] epjhpn=new String[count+1];      //手签协议号
						String[] epjpnm=new String[count+1];      //项目名称
						String[] epjfee=new String[count+1];      //费用金额
					
						CommDate cd=new CommDate();		
						double dd=0;				
							
						for(int i=0;i<count;i++){
								epjvdr[i]=dbBean.getElementValue(i,"epjvdr").trim() ;                
								epjstr[i]=dbBean.getElementValue(i,"epjstr").trim() ;                
								epjrtd[i]=cd.dateFormat(dbBean.getElementValue(i,"epjrtd").trim(),"L");		              
								epjpst[i]=dbBean.getElementValue(i,"epjpst").trim() ;                
								epjspn[i]=dbBean.getElementValue(i,"epjspn").trim() ;                
								epjhpn[i]=dbBean.getElementValue(i,"epjhpn").trim() ;                
								epjpnm[i]=dbBean.getElementValue(i,"epjpnm").trim() ;                
								epjfee[i]=dbBean.getElementValue(i,"epjfee").trim() ; 
							//eipyta[i]=DecimalTools.format(dbBean.getElementValue(i,"eipyta").trim(),"L");	

							if(!epjfee[i].trim().equals(""))
								dd =dd + Double.parseDouble(epjfee[i]);
						
						}
						
						String sumfee=DecimalTools.format(String.valueOf(dd),"###,##0.00"); 

						epjvdr[count]="sumhj";      //供货商号
						epjstr[count]="";      //商店号
						epjrtd[count]="";      //付款日期
						epjpst[count]="";      //协议对应的商店
						epjspn[count]="";      //系统协议号
						epjhpn[count]="";      //手签协议号
						epjpnm[count]="";      //项目名称
						epjfee[count]=sumfee;      //费用金额
						
						//写日志
						CommActionLog  cal= new CommActionLog();
						 String temp=epjvdr[0]+"/"+epjstr[0]+"/"+dbBean.getElementValue(0,"epjrtd").trim();		 
					
						cal.setAct_user(uname);
						cal.setAct_from("homeworldEpyfee");
						cal.setAct_do("SEA");
						cal.setAct_key(temp);
						cal.setAct_table("EPYHDR");
						cal.setAct_ip(request.getRemoteAddr());
						cal.setAct_memo("查询已付款费用信息：" + temp);
						cal.setAct_me("");
						cal.setActionLog();

						//PropertyUtils.setSimpleProperty(form, "sumfee"   ,sumfee );
						PropertyUtils.setSimpleProperty(form, "epjvdr"   ,epjvdr );
						PropertyUtils.setSimpleProperty(form, "epjstr"   ,epjstr );
						PropertyUtils.setSimpleProperty(form, "epjrtd"   ,epjrtd );
						PropertyUtils.setSimpleProperty(form, "epjpst"   ,epjpst );
						PropertyUtils.setSimpleProperty(form, "epjspn"   ,epjspn );
						PropertyUtils.setSimpleProperty(form, "epjhpn"   ,epjhpn );
						PropertyUtils.setSimpleProperty(form, "epjpnm"   ,epjpnm );
						PropertyUtils.setSimpleProperty(form, "epjfee"   ,epjfee );



						return "1";
					}

					/* (non-Javadoc)
					 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
					 */
					public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
						return  executeme( mapping,form,request,response);
					}
	
	
	
				}

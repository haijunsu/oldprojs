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
			 * <P>�˵�����</P>
			 * 
			 * @version 0.1
			 * @author ������
			 */
			public class homeworldEpydtlAction  extends SecureAction {
				//private static LogWrapper log= new LogWrapper(SalaryCardCtrl.class);

				ActionErrors errors = new ActionErrors();

				/**
				 * ���캯��
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

						//TODO ��ʱ���������
						long l_begin,l_end;
						l_begin = System.currentTimeMillis();		
						commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Epydtl","B","LYC0000000",0);
						
						
						if(uname==null){
							errors.add("errormessage",new ActionError("NoName"));
							saveErrors(request, errors);
//							TODO ��ʱ���������
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"Epydtl","E","LYC5555555",l_end - l_begin);
							return(mapping.findForward("success"));
						}
						if(uname.trim().equals("")){
							errors.add("errormessage",new ActionError("NoName"));
							saveErrors(request, errors);
//							TODO ��ʱ���������
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
//							TODO ��ʱ���������
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"epydtl","E","LYC3333333",l_end - l_begin);
							return(mapping.findForward("err"));
						}
						
						PropertyUtils.setSimpleProperty(form,"selectwhere",selectwhere);
			
						setFormbeen(form,request,selectwhere,uname);

//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Epydtl","E","LYC8888888",l_end - l_begin);
						return(mapping.findForward("success"));
					} catch (Exception e){
						e.printStackTrace();
						errors.add("errormessage",new ActionError("Datebase.formbean"));
						saveErrors(request, errors);
//				TODO ��ʱ���������
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Epydtl","C","LYC9999999",l_end - l_begin);
						return(mapping.findForward("err"));
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
						
					String[] epdvdr=new String[count];//�����̺�
					String[] epdstr=new String[count];//�̵��
					String[] epdrdt=new String[count];//������
			
					String[] epdtrk=new String[count];//��������
					String[] epdivn=new String[count];//��������
					String[] epdivd=new String[count];//���
					
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

					//д��־
					CommActionLog  cal= new CommActionLog();
					 String temp=epdvdr[0]+"/"+epdstr[0]+"/"+dbBean.getElementValue(0,"epdrdt").trim();		 
					
					cal.setAct_user(uname);
					cal.setAct_from("homeworldEpydtl");
					cal.setAct_do("SEA");
					cal.setAct_key(temp);
					cal.setAct_table("EPYHDR");
					cal.setAct_ip(request.getRemoteAddr());
					cal.setAct_memo("��ѯ�Ѹ�����ϸ��Ϣ��" + temp);
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

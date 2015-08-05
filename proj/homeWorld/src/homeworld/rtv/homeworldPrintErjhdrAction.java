/*
 * @(#)homeworldPrintErjhdrAction.java  2004-5-26
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.rtv;

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
			import com.idn.util.DecimalTools;


			import commsearch.util.CommDate;


			/**
			 * <P>菜单管理</P>
			 * 
			 * @version 0.1
			 * @author 李永初
			 */
			public class homeworldPrintErjhdrAction  extends SecureAction {

				ActionErrors errors = new ActionErrors();

				/**
				 * 构造函数
				 */
				public homeworldPrintErjhdrAction  () {
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
							uname,hs.getId(),"PrintErjhdr","B","LYC0000000",0);
						
						if(uname==null){
							errors.add("errormessage",new ActionError("NoName"));
							saveErrors(request, errors);
//							TODO 临时用来监测用
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"PrintErjhdr","E","LYC5555555",l_end - l_begin);
							return(mapping.findForward("success"));
						}
						if(uname.trim().equals("")){
							errors.add("errormessage",new ActionError("NoName"));
							saveErrors(request, errors);
//							TODO 临时用来监测用
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"PrintErjhdr","E","LYC6666666",l_end - l_begin);
							return(mapping.findForward("success"));
						}

				try{

						String selectwhere= (String)request.getParameter("selectwhere");

						String stemp=selectwhere;
						
						if(stemp==null)
							stemp="";
						if(stemp.equals("")){
//							TODO 临时用来监测用
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"PrintErjhdr","E","LYC3333333",l_end - l_begin);
							return(mapping.findForward("err"));
						}
						
						String queryid= (String)request.getParameter("queryid");
			
						PropertyUtils.setSimpleProperty(form,"queryid",queryid);
						PropertyUtils.setSimpleProperty(form,"selectwhere",selectwhere);
					
						PropertyUtils.setSimpleProperty(form, "pagerow" ,"20");
					
						setFormbeen(form,request,selectwhere,uname);

//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"PrintErjhdr","E","LYC8888888",l_end - l_begin);
						return(mapping.findForward("success"));
					} catch (Exception e){
						e.printStackTrace();
						errors.add("errormessage",new ActionError("Datebase.formbean"));
						saveErrors(request, errors);
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"PrintErjhdr","C","LYC9999999",l_end - l_begin);
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
				public String setFormbeen(ActionForm form,HttpServletRequest request,String where,String uname) throws Exception{
						DataBean dbBean = new DataBean();
					
					String strsql="select * from Erjhdr where "+where+" ";   
					try{
						dbBean.executeSelect(strsql);
					} catch (Exception e) {
						e.printStackTrace();
						errors.add("errormessage",new ActionError("Datebase.readdb"));
						return "0";
					}
					
					CommDate cd=new CommDate();
			
					String erjrnm=dbBean.getElementValue(0,"erjrnm"  ).trim() ;	//收货单号
					String erjnum=dbBean.getElementValue(0,"erjnum"  ).trim() ;	//收货调整单号
					String erjcdt=cd.dateFormat(dbBean.getElementValue(0,"erjcdt"  ).trim(),"L");//生成日期
					String erjctm=cd.timeFormat(dbBean.getElementValue(0,"erjctm"  ).trim(),"L");//生成时间
					String erjstr=dbBean.getElementValue(0,"erjstr"  ).trim() ;	//商店
					String erjstn=dbBean.getElementValue(0,"erjstn"  ).trim() ; 	//商店名
					String erjvnd=dbBean.getElementValue(0,"erjvnd"  ).trim() ;	//供货商
					String erjvdn=dbBean.getElementValue(0,"erjvdn"  ).trim() ;	//供货商名
					String erjjdt=cd.dateFormat(dbBean.getElementValue(0,"erjjdt"  ).trim(),"L");//调整日期
							
					PropertyUtils.setSimpleProperty(form, "erjrnm"   ,erjrnm	  );
					PropertyUtils.setSimpleProperty(form, "erjnum"   ,erjnum	  );
					PropertyUtils.setSimpleProperty(form, "erjcdt"   ,erjcdt	  ); 
					PropertyUtils.setSimpleProperty(form, "erjctm"   ,erjctm	  );
					PropertyUtils.setSimpleProperty(form, "erjstr"   ,erjstr	  );
					PropertyUtils.setSimpleProperty(form, "erjstn"   ,erjstn	  );
					PropertyUtils.setSimpleProperty(form, "erjvnd"   ,erjvnd	  );
					PropertyUtils.setSimpleProperty(form, "erjvdn"   ,erjvdn	  );
					PropertyUtils.setSimpleProperty(form, "erjjdt"   ,erjjdt	  );
			

					int count =0;

					strsql="select * from Erjdtl where "+where+" order by erjssq";   
					try{
						dbBean.executeSelect(strsql);
					} catch (Exception e) {
						e.printStackTrace();
						errors.add("errormessage",new ActionError("Datebase.readdb"));
						return "0";
					}

					count=dbBean.getRowCount();
					int pagerow=Integer.parseInt((String)PropertyUtils.getSimpleProperty(form,"pagerow"));

					//计算分页显示的最大页数
					int maxpage=0;
					String endnew="flase";
					for(int i=1; i<50; i++){
						maxpage=i;
						if((count+i)<=(i* pagerow)){
							break;
						}
					}

					int countend=0;
					countend=count+maxpage-((maxpage-1)*pagerow);
			
					if(pagerow-countend<0){
						endnew="true";
					}

					int pagecount=0;
			
					if(endnew.equals("true")){
						pagecount=maxpage+1;
					}else{
						pagecount=maxpage;
					}
				

					String[]  erjseq=new String[count+maxpage];	//序号
					String[]erjstrmx=new String[count+maxpage];	//商店      
					String[]erjvndmx=new String[count+maxpage];	//供货商    
					String[]  erjssq=new String[count+maxpage];	//sku序号   
					String[]  erjsku=new String[count+maxpage];	//sku       
					String[]  erjskd=new String[count+maxpage];	//sku描述   
					String[]  erjvds=new String[count+maxpage];	//供货商型号
					String[]  erjmgn=new String[count+maxpage];	//规格      
					String[]  erjrqy=new String[count+maxpage];	//数量      
					String[]  erjret=new String[count+maxpage];	//售价      
					String[]  erjcst=new String[count+maxpage];	//成本      
					String[]  erjtrt=new String[count+maxpage];	//售价金额  
					String[]  erjtct=new String[count+maxpage];	//成本金额   


					double countrqy	=0;  
					double counttrt	=0;  
					double counttct	=0;  
					
					double sumrqy=0;
					double sumtrt=0;
					double sumtct=0;
					
					int jk=1;
					int j=0;

					for(int i=0;i<count+maxpage;i++){
						if((i==jk*pagerow-1)||(i==count+maxpage-1)){
							//epostr[i]="小计--        "; 

							erjseq[i]="";
						  erjstrmx[i]="";
						  erjvndmx[i]="";
							erjssq[i]="";
							erjsku[i]="minsum";
							erjskd[i]="";
							erjvds[i]="";
							erjmgn[i]="";
					
							erjret[i]="";
							erjcst[i]="";
							
							erjrqy[i]=DecimalTools.format(String.valueOf(sumrqy),"###,##0.00");
							erjtrt[i]=DecimalTools.format(String.valueOf(sumtrt),"###,##0.000");
							erjtct[i]=DecimalTools.format(String.valueOf(sumtct),"###,##0.0000");
		
							sumrqy=0;
							sumtrt=0;
							sumtct=0;
							
							jk++;					
						}else{

							erjseq[i]=dbBean.getElementValue(j,"erjseq");
						  erjstrmx[i]=dbBean.getElementValue(j,"erjstr");
						  erjvndmx[i]=dbBean.getElementValue(j,"erjvnd");
							erjssq[i]=dbBean.getElementValue(j,"erjssq");
							erjsku[i]=dbBean.getElementValue(j,"erjsku");
							erjskd[i]=dbBean.getElementValue(j,"erjskd");
							erjvds[i]=dbBean.getElementValue(j,"erjvds");
							erjmgn[i]=dbBean.getElementValue(j,"erjmgn");
							erjrqy[i]=dbBean.getElementValue(j,"erjrqy");
							erjret[i]=dbBean.getElementValue(j,"erjret");
							erjcst[i]=dbBean.getElementValue(j,"erjcst");
							erjtrt[i]=dbBean.getElementValue(j,"erjtrt");
							erjtct[i]=dbBean.getElementValue(j,"erjtct");
							
							countrqy=countrqy+Double.parseDouble(erjrqy[i]);
							counttrt=counttrt+Double.parseDouble(erjtrt[i]);
							counttct=counttct+Double.parseDouble(erjtct[i]);
							
							
							sumrqy=sumrqy+Double.parseDouble(erjrqy[i]);
							sumtrt=sumtrt+Double.parseDouble(erjtrt[i]);
							sumtct=sumtct+Double.parseDouble(erjtct[i]);
						
							erjrqy[i]=DecimalTools.format(erjrqy[i],"###,##0.00");
							erjret[i]=DecimalTools.format(erjret[i],"###,##0.000");
							erjcst[i]=DecimalTools.format(erjcst[i],"###,##0.0000");
							erjtrt[i]=DecimalTools.format(erjtrt[i],"###,##0.000");
							erjtct[i]=DecimalTools.format(erjtct[i],"###,##0.0000");

							j++;
						}
					}

					   
					PropertyUtils.setSimpleProperty(form, "countrqy" ,DecimalTools.format(countrqy,"###,##0.00"));
					PropertyUtils.setSimpleProperty(form, "counttrt" ,DecimalTools.format(counttrt,"###,##0.000"));   
					PropertyUtils.setSimpleProperty(form, "counttct" ,DecimalTools.format(counttct,"###,##0.0000"));   
				
					PropertyUtils.setSimpleProperty(form, "endnew" ,endnew);   
				
					PropertyUtils.setSimpleProperty(form, "erjseq"  ,  erjseq);
					PropertyUtils.setSimpleProperty(form, "erjstrmx",erjstrmx);
					PropertyUtils.setSimpleProperty(form, "erjvndmx",erjvndmx);
					PropertyUtils.setSimpleProperty(form, "erjssq"  ,  erjssq);
					PropertyUtils.setSimpleProperty(form, "erjsku"  ,  erjsku);
					PropertyUtils.setSimpleProperty(form, "erjskd"  ,  erjskd);
					PropertyUtils.setSimpleProperty(form, "erjvds"  ,  erjvds);
					PropertyUtils.setSimpleProperty(form, "erjmgn"  ,  erjmgn);
					PropertyUtils.setSimpleProperty(form, "erjrqy"  ,  erjrqy);
					PropertyUtils.setSimpleProperty(form, "erjret"  ,  erjret);
					PropertyUtils.setSimpleProperty(form, "erjcst"  ,  erjcst);
					PropertyUtils.setSimpleProperty(form, "erjtrt"  ,  erjtrt);
					PropertyUtils.setSimpleProperty(form, "erjtct"  ,  erjtct);

					return "1";
				}

				/* (non-Javadoc)
				 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
				 */
				public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

					return  executeme( mapping,form,request,response);
				}
	
	
	
			}


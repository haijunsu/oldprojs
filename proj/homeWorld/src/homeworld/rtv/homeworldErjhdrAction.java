/*
 * @(#)homeworldErjhdrAction.java  2004-5-26
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

//		  import commsearch.CommTools;
		import commsearch.util.CommActionLog;
		import commsearch.util.CommDate;


		/**
		 * <P>菜单管理</P>
		 * 
		 * @version 0.1
		 * @author 李永初
		 */
		public class homeworldErjhdrAction   extends SecureAction {
			//private static LogWrapper log= new LogWrapper(SalaryCardCtrl.class);
			ActionErrors errors = new ActionErrors();

			/**
			 * 构造函数
			 */
			public homeworldErjhdrAction() {
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
						uname,hs.getId(),"Erjhdr","B","LYC0000000",0);
						
					if(uname==null){
						errors.add("errormessage",new ActionError("NoName"));
						saveErrors(request, errors);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Erjhdr","E","LYC5555555",l_end - l_begin);
						return(mapping.findForward("success"));
					}
					if(uname.trim().equals("")){
						errors.add("errormessage",new ActionError("NoName"));
						saveErrors(request, errors);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Erjhdr","E","LYC6666666",l_end - l_begin);
						return(mapping.findForward("success"));
					}
			try{

					String selectwhere= (String)request.getParameter("selectwhere");
					String queryid= (String)request.getParameter("queryid");
			

				if(selectwhere==null)
					selectwhere="";
				if(selectwhere.equals("")){
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Erjhdr","E","LYC3333333",l_end - l_begin);
					return(mapping.findForward("err"));
				}
				
				
					PropertyUtils.setSimpleProperty(form,"queryid",queryid);
					PropertyUtils.setSimpleProperty(form,"selectwhere",selectwhere);
				//PropertyUtils.setSimpleProperty(form,"selectwhere","");
			
					setFormbeen(form,request,selectwhere,uname);

//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Erjhdr","E","LYC8888888",l_end - l_begin);
					return(mapping.findForward("success"));
				} catch (Exception e){
					e.printStackTrace();
					errors.add("errormessage",new ActionError("Datebase.formbean"));
					saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Erjhdr","C","LYC999999",l_end - l_begin);
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
						DataBean dbBean = new DataBean();
				
						String strsql="select * from Erjhdr where "+where+" and erjvnd='"+uname+"'";
//						try{
							dbBean.executeSelect(strsql);
//						} catch (Exception e) {
//							e.printStackTrace();
//							errors.add("errormessage",new ActionError("Datebase.readdb"));
//							return "0";
//						}
				
				CommDate cd=new CommDate();

				//String ertsdt=cd.dateFormat(dbBean.getElementValue(0,"ertsdt"  ),"L");
				//String ertctm=cd.timeFormat(dbBean.getElementValue(0,"ertctm").trim() ,"L");

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
				
		   //写日志
		   CommActionLog  cal= new CommActionLog();
			
		   cal.setAct_user(uname);
		   cal.setAct_from("homeworldErjhdr");
		   cal.setAct_do("SEA");
		   cal.setAct_key(erjnum);
		   cal.setAct_table("ERJHDR");
		   cal.setAct_ip(request.getRemoteAddr());
		   cal.setAct_memo("查询收货调整单编号为" + erjnum);
		   cal.setAct_me("");
		cal.setActionLog();

				int count =0;

				strsql="select * from Erjdtl where "+where+" and erjvnd='"+uname+"' order by erjssq";   
//				try{
					dbBean.executeSelect(strsql);
//				} catch (Exception e) {
//					e.printStackTrace();
//					errors.add("errormessage",new ActionError("Datebase.readdb"));
//					return "0";
//				}
				count=dbBean.getRowCount()+1;

				String[]  erjseq=new String[count];	//序号
				String[]erjstrmx=new String[count];	//商店      
				String[]erjvndmx=new String[count];	//供货商    
				String[]  erjssq=new String[count];	//sku序号   
				String[]  erjsku=new String[count];	//sku       
				String[]  erjskd=new String[count];	//sku描述   
				String[]  erjvds=new String[count];	//供货商型号
				String[]  erjmgn=new String[count];	//规格      
				String[]  erjrqy=new String[count];	//数量      
				String[]  erjret=new String[count];	//售价      
				String[]  erjcst=new String[count];	//成本      
				String[]  erjtrt=new String[count];	//售价金额  
				String[]  erjtct=new String[count];	//成本金额   


				double countrqy	=0;  
				double counttrt	=0;  
				double counttct	=0;  


				for(int i=0;i<count-1;i++){

					erjseq[i]=dbBean.getElementValue(i,"erjseq");
				  erjstrmx[i]=dbBean.getElementValue(i,"erjstr");
				  erjvndmx[i]=dbBean.getElementValue(i,"erjvnd");
					erjssq[i]=dbBean.getElementValue(i,"erjssq");
					erjsku[i]=dbBean.getElementValue(i,"erjsku");
					erjskd[i]=dbBean.getElementValue(i,"erjskd");
					erjvds[i]=dbBean.getElementValue(i,"erjvds");
					erjmgn[i]=dbBean.getElementValue(i,"erjmgn");
					erjrqy[i]=dbBean.getElementValue(i,"erjrqy");
					erjret[i]=dbBean.getElementValue(i,"erjret");
					erjcst[i]=dbBean.getElementValue(i,"erjcst");
					erjtrt[i]=dbBean.getElementValue(i,"erjtrt");
					erjtct[i]=dbBean.getElementValue(i,"erjtct");

					countrqy	=countrqy+Double.parseDouble(erjrqy[i]);  
					counttrt	=counttrt+Double.parseDouble(erjtrt[i]);  
					counttct	=counttct+Double.parseDouble(erjtct[i]);  

					erjrqy[i]=DecimalTools.format(erjrqy[i],"###,##0.00");
					erjret[i]=DecimalTools.format(erjret[i],"###,##0.000");
					erjcst[i]=DecimalTools.format(erjcst[i],"###,##0.0000");
					erjtrt[i]=DecimalTools.format(erjtrt[i],"###,##0.000");
					erjtct[i]=DecimalTools.format(erjtct[i],"###,##0.0000");
 
				}

				erjrqy[count-1]=DecimalTools.format(countrqy,"###,##0.00");
				erjtrt[count-1]=DecimalTools.format(counttrt,"###,##0.000");
				erjtct[count-1]=DecimalTools.format(counttct,"###,##0.0000");
			
				erjsku[count-1]="sumcount";

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

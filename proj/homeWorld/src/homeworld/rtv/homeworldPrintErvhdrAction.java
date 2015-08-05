/*
 * @(#)homeworldPrintErvhdrAction.java  2004-4-26
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
		public class homeworldPrintErvhdrAction extends SecureAction {

			ActionErrors errors = new ActionErrors();

			/**
			 * 构造函数
			 */
			public homeworldPrintErvhdrAction () {
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
						uname,hs.getId(),"PrintErvhdr","B","LYC0000000",0);
						
					if(uname==null){
						errors.add("errormessage",new ActionError("NoName"));
						saveErrors(request, errors);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintErvhdr","E","LYC5555555",l_end - l_begin);
						return(mapping.findForward("success"));
					}
					if(uname.trim().equals("")){
						errors.add("errormessage",new ActionError("NoName"));
						saveErrors(request, errors);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintErvhdr","E","LYC6666666",l_end - l_begin);
						return(mapping.findForward("success"));
					}

			try{
					String selectwhere= (String)request.getParameter("selectwhere");

					String stemp=selectwhere;
					
					if(stemp==null)
						stemp="";
					if(stemp.equals("")){
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintErvhdr","E","LYC3333333",l_end - l_begin);
						return(mapping.findForward("err"));
					}
					
					String queryid= (String)request.getParameter("queryid");
			
					PropertyUtils.setSimpleProperty(form,"queryid",queryid);
					PropertyUtils.setSimpleProperty(form,"selectwhere",selectwhere);
					
					PropertyUtils.setSimpleProperty(form, "pagerow" ,"22");
					
					setFormbeen(form,request,selectwhere,uname);

//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"PrintErvhdr","E","LYC8888888",l_end - l_begin);
					return(mapping.findForward("success"));
				} catch (Exception e){
					e.printStackTrace();
					errors.add("errormessage",new ActionError("Datebase.formbean"));
					saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"PrintErvhdr","C","LYC9999999",l_end - l_begin);
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
				
						String strsql="select * from Ervhdr where "+where+" ";   
						try{
							dbBean.executeSelect(strsql);
						} catch (Exception e) {
							e.printStackTrace();
							errors.add("errormessage",new ActionError("Datebase.readdb"));
							return "0";
						}
				
				CommDate cd=new CommDate();

				//String ertsdt=cd.dateFormat(dbBean.getElementValue(0,"ertsdt"  ),"L");
				//String ertctm=cd.timeFormat(dbBean.getElementValue(0,"ertctm").trim() ,"L");
				String ervnum	=dbBean.getElementValue(0,"ervnum"  ).trim() ;
				String ervcdt	=cd.dateFormat(dbBean.getElementValue(0,"ervcdt"  ).trim() ,"L");
				String ervctm	=cd.timeFormat(dbBean.getElementValue(0,"ervctm"  ).trim(),"L");
				String ervpno	=dbBean.getElementValue(0,"ervpno"  ).trim() ;
				String ervstr	=dbBean.getElementValue(0,"ervstr"  ).trim() ;
				String ervstn 	=dbBean.getElementValue(0,"ervstn"  ).trim() ;
				String ervdpt	=dbBean.getElementValue(0,"ervdpt"  ).trim() ;
				String ervdpn	=dbBean.getElementValue(0,"ervdpn"  ).trim() ;
				String ervsdpt	=dbBean.getElementValue(0,"ervsdpt" ).trim() ;
				String ervsdptn =dbBean.getElementValue(0,"ervsdptn").trim() ;
				String ervbyr	=dbBean.getElementValue(0,"ervbyr"  ).trim() ;
				String ervbyn	=dbBean.getElementValue(0,"ervbyn"  ).trim() ;
				String ervvnd	=dbBean.getElementValue(0,"ervvnd"  ).trim() ;
				String ervvdn	=dbBean.getElementValue(0,"ervvdn"  ).trim() ;
				String ervedt	=cd.dateFormat(dbBean.getElementValue(0,"ervedt"  ).trim() ,"L");
				String ervva1	=dbBean.getElementValue(0,"ervva1"  ).trim() ;
				String ervva2	=dbBean.getElementValue(0,"ervva2"  ).trim() ;
				String ervva3	=dbBean.getElementValue(0,"ervva3"  ).trim() ;
				String ervvcy	=dbBean.getElementValue(0,"ervvcy"  ).trim() ;
				String ervvzp	=dbBean.getElementValue(0,"ervvzp"  ).trim() ;
				String ervvcn	=dbBean.getElementValue(0,"ervvcn"  ).trim() ;
				String ervtrm	=dbBean.getElementValue(0,"ervtrm"  ).trim() ;
				String ervtmn	=dbBean.getElementValue(0,"ervtmn"  ).trim() ;
				String ervfrc	=dbBean.getElementValue(0,"ervfrc"  ).trim() ;
				String ervfrn	=dbBean.getElementValue(0,"ervfrn"  ).trim() ;
				String ervrdt	=cd.dateFormat(dbBean.getElementValue(0,"ervrdt"  ).trim(),"L");
				String ervvct	=dbBean.getElementValue(0,"ervvct"  ).trim() ;
				String ervsts	=dbBean.getElementValue(0,"ervsts"  ).trim() ;
				String ervnot1	=dbBean.getElementValue(0,"ervnot1" ).trim() ;
				String ervnot2	=dbBean.getElementValue(0,"ervnot2" ).trim() ;
				String ervnot3	=dbBean.getElementValue(0,"ervnot3" ).trim() ;
				String ervfob	=dbBean.getElementValue(0,"ervfob"  ).trim() ;
				String ervsp1	=dbBean.getElementValue(0,"ervsp1"  ).trim() ;
				String ervsp2	=dbBean.getElementValue(0,"ervsp2"  ).trim() ;
				String ervspp	=dbBean.getElementValue(0,"ervspp"  ).trim() ;
				String ervspc	=dbBean.getElementValue(0,"ervspc"  ).trim() ;
				String ervsdt	=dbBean.getElementValue(0,"ervsdt"  ).trim() ;
				String ervvdt	=dbBean.getElementValue(0,"ervvdt"  ).trim() ;
				String ervseq	=dbBean.getElementValue(0,"ervseq"  ).trim() ;



				String ervtrt	=dbBean.getElementValue(0,"ervtrt"  ).trim();
				String ervtct	=dbBean.getElementValue(0,"ervtct"  ).trim();
			
				if(!ervtrt.equals(""))
					ervtrt=DecimalTools.format(ervtrt ,"###,##0.0000");
				if(!ervtct.equals(""))
					ervtct=DecimalTools.format(ervtct ,"###,##0.0000");
				if(!ervsdt.equals(""))
					ervsdt=DecimalTools.format(ervsdt ,"###,##0.0000");
				if(!ervvdt.equals(""))
					ervvdt=DecimalTools.format(ervvdt ,"###,##0.0000");
			
				PropertyUtils.setSimpleProperty(form, "ervtrt"   ,ervtrt	  );   
				PropertyUtils.setSimpleProperty(form, "ervtct"   ,ervtct	  );   
			
			
				PropertyUtils.setSimpleProperty(form, "ervnum"   ,ervnum	  );   
				PropertyUtils.setSimpleProperty(form, "ervcdt"   ,ervcdt	  );   
				PropertyUtils.setSimpleProperty(form, "ervctm"   ,ervctm	  );   
				PropertyUtils.setSimpleProperty(form, "ervpno"   ,ervpno	  );   
				PropertyUtils.setSimpleProperty(form, "ervstr"   ,ervstr	  );   
				PropertyUtils.setSimpleProperty(form, "ervstn"   ,ervstn 	  );   
				PropertyUtils.setSimpleProperty(form, "ervdpt"   ,ervdpt	  );   
				PropertyUtils.setSimpleProperty(form, "ervdpn"   ,ervdpn	  );   
				PropertyUtils.setSimpleProperty(form, "ervsdpt"  ,ervsdpt	  );   
				PropertyUtils.setSimpleProperty(form, "ervsdptn" ,ervsdptn    );   
				PropertyUtils.setSimpleProperty(form, "ervbyr"   ,ervbyr	  );   
				PropertyUtils.setSimpleProperty(form, "ervbyn"   ,ervbyn	  );   
				PropertyUtils.setSimpleProperty(form, "ervvnd"   ,ervvnd	  );   
				PropertyUtils.setSimpleProperty(form, "ervvdn"   ,ervvdn	  );   
				PropertyUtils.setSimpleProperty(form, "ervedt"   ,ervedt	  );   
				PropertyUtils.setSimpleProperty(form, "ervva1"   ,ervva1	  );   
				PropertyUtils.setSimpleProperty(form, "ervva2"   ,ervva2	  );   
				PropertyUtils.setSimpleProperty(form, "ervva3"   ,ervva3	  );   
				PropertyUtils.setSimpleProperty(form, "ervvcy"   ,ervvcy	  );   
				PropertyUtils.setSimpleProperty(form, "ervvzp"   ,ervvzp	  );   
				PropertyUtils.setSimpleProperty(form, "ervvcn"   ,ervvcn	  );   
				PropertyUtils.setSimpleProperty(form, "ervtrm"   ,ervtrm	  );   
				PropertyUtils.setSimpleProperty(form, "ervtmn"   ,ervtmn	  );   
				PropertyUtils.setSimpleProperty(form, "ervfrc"   ,ervfrc	  );   
				PropertyUtils.setSimpleProperty(form, "ervfrn"   ,ervfrn	  );   
				PropertyUtils.setSimpleProperty(form, "ervrdt"   ,ervrdt	  );   
				PropertyUtils.setSimpleProperty(form, "ervvct"   ,ervvct	  );   
				PropertyUtils.setSimpleProperty(form, "ervsts"   ,ervsts	  );   
				PropertyUtils.setSimpleProperty(form, "ervnot1"  ,ervnot1	  );   
				PropertyUtils.setSimpleProperty(form, "ervnot2"  ,ervnot2	  );   
				PropertyUtils.setSimpleProperty(form, "ervnot3"  ,ervnot3	  );   
				PropertyUtils.setSimpleProperty(form, "ervfob"   ,ervfob	  );
				PropertyUtils.setSimpleProperty(form, "ervsp1"   ,ervsp1	  );
				PropertyUtils.setSimpleProperty(form, "ervsp2"   ,ervsp2	  );
				PropertyUtils.setSimpleProperty(form, "ervspp"   ,ervspp	  );
				PropertyUtils.setSimpleProperty(form, "ervspc"   ,ervspc	  );
				PropertyUtils.setSimpleProperty(form, "ervsdt"   ,ervsdt	  );
				PropertyUtils.setSimpleProperty(form, "ervvdt"   ,ervvdt	  );
				PropertyUtils.setSimpleProperty(form, "ervseq"   ,ervseq	  );
			
		   
		   //写日志
//		   CommActionLog  cal= new CommActionLog();
//			
//		   cal.setAct_user(uname);
//		   cal.setAct_from("homeworldErvthdr");
//		   cal.setAct_do("SEA");
//		   cal.setAct_key(ervnum);
//		   cal.setAct_table("ERVHDR");
//		   cal.setAct_ip(request.getRemoteAddr());
//		   cal.setAct_memo("查询返厂单编号为" + ervnum);
//		   cal.setAct_me("");
//		   cal.setActionLog();
 

			String[] ervseqmx=null; //序号       
			String[] ervstrmx=null; //商店      
			String[] ervvndmx=null; //供货商    
			String[] ervssq	 =null; //sku序号   
			String[] ervsku	 =null; //sku       
			String[] ervskd	 =null; //sku描述   
			String[] ervvds	 =null; //供货商货号
			String[] ervsum	 =null; //销售单位  
			String[] ervbum	 =null; //采购单位  
			String[] ervupc	 =null; //upc       
			String[] ervcas  =null; //订货数_包 
			String[] ervqty	 =null; //订货数_件 
			String[] ervmgn	 =null; //规格      
			String[] ervsks	 =null; //折扣标志  
			String[] ervrqy	 =null; //收货数    
			String[] ervnqy	 =null; //欠收数   

				int count =0;

				strsql="select * from ervdtl where "+where+" ";   
				try{
					dbBean.executeSelect(strsql);
				} catch (Exception e) {
					e.printStackTrace();
					errors.add("errormessage",new ActionError("Datebase.readdb"));
					return "0";
				}
				count=dbBean.getRowCount()+1;

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
			
				if(pagerow-countend<11){
					endnew="true";
				}

				int pagecount=0;
			
				if(endnew.equals("true")){
					pagecount=maxpage+1;
				}else{
					pagecount=maxpage;
				}
				
								
				ervseqmx=new String[count+maxpage]; 
				ervstrmx=new String[count+maxpage]; 
				ervvndmx=new String[count+maxpage]; 
				ervssq	=new String[count+maxpage]; 
				ervsku	=new String[count+maxpage]; 
				ervskd	=new String[count+maxpage]; 
				ervvds	=new String[count+maxpage]; 
				ervsum	=new String[count+maxpage]; 
				ervbum	=new String[count+maxpage]; 
				ervupc	=new String[count+maxpage]; 
				ervcas  =new String[count+maxpage]; 
				ervqty	=new String[count+maxpage]; 
				ervmgn	=new String[count+maxpage]; 
				ervsks	=new String[count+maxpage]; 
				ervrqy	=new String[count+maxpage]; 
				ervnqy	=new String[count+maxpage]; 
		
				double countcas=0;
				double countqty=0;
				double countrqy=0;
				double countnqy=0;
				
				double sumcas=0;
				double sumqty=0;
				double sumrqy=0;
				double sumnqy=0;

				int jk=1;
				int j=0;

				for(int i=0;i<count+maxpage;i++){
					if((i==jk*pagerow-1)||(i==count+maxpage-1)){
						//epostr[i]="小计--        "; 

						ervseqmx[i]="";
						ervstrmx[i]="";
						ervvndmx[i]="";
						ervssq	[i]="";
						ervsku	[i]="minsum";
						ervskd	[i]="";
						ervvds	[i]="";
						ervsum	[i]="";
						ervbum	[i]="";
						ervupc	[i]="";
						ervcas  [i]="";
						ervqty	[i]="";
						ervmgn	[i]="";
						ervsks	[i]="";
						ervrqy	[i]="";
						ervnqy	[i]="";
		
						ervcas[i]=DecimalTools.format(String.valueOf(sumcas),"###,##0.00");
						ervqty[i]=DecimalTools.format(String.valueOf(sumqty),"###,##0.00");
						ervrqy[i]=DecimalTools.format(String.valueOf(sumrqy),"###,##0.00");
						ervnqy[i]=DecimalTools.format(String.valueOf(sumnqy),"###,##0.00");
						
						sumcas=0;
						sumqty=0;
						sumrqy=0;
						sumnqy=0;
						jk++;					
					}else{
						ervseqmx[i]=dbBean.getElementValue(j,"ervseq");
						ervstrmx[i]=dbBean.getElementValue(j,"ervstr");
						ervvndmx[i]=dbBean.getElementValue(j,"ervvnd");
						ervssq	[i]=dbBean.getElementValue(j,"ervssq"  );
						ervsku	[i]=dbBean.getElementValue(j,"ervsku"  );
						ervskd	[i]=dbBean.getElementValue(j,"ervskd"  );
						ervvds	[i]=dbBean.getElementValue(j,"ervvds"  );
						ervsum	[i]=dbBean.getElementValue(j,"ervsum"  );
						ervbum	[i]=dbBean.getElementValue(j,"ervbum"  );
						ervupc	[i]=dbBean.getElementValue(j,"ervupc"  );
						ervcas  [i]=dbBean.getElementValue(j,"ervcas"  );
						ervqty	[i]=dbBean.getElementValue(j,"ervqty"  );
						ervmgn	[i]=dbBean.getElementValue(j,"ervmgn"  );
						ervsks	[i]=dbBean.getElementValue(j,"ervsks"  );
						ervrqy	[i]=dbBean.getElementValue(j,"ervrqy"  );
						ervnqy	[i]=dbBean.getElementValue(j,"ervnqy"  );

						countcas=countcas+Double.parseDouble(ervcas[i]);
						countqty=countqty+Double.parseDouble(ervqty[i]);
						countrqy=countrqy+Double.parseDouble(ervrqy[i]);
						countnqy=countnqy+Double.parseDouble(ervnqy[i]);
	
						sumcas=sumcas+Double.parseDouble(ervcas[i]);
						sumqty=sumqty+Double.parseDouble(ervqty[i]);
						sumrqy=sumrqy+Double.parseDouble(ervrqy[i]);
						sumnqy=sumnqy+Double.parseDouble(ervnqy[i]);
						
						ervcas[i]=DecimalTools.format(ervcas[i],"###,##0.00");
						ervqty[i]=DecimalTools.format(ervqty[i],"###,##0.00");
						ervrqy[i]=DecimalTools.format(ervrqy[i],"###,##0.00");
						ervnqy[i]=DecimalTools.format(ervnqy[i],"###,##0.00"); 

						j++;
					}
				}

				PropertyUtils.setSimpleProperty(form, "countcas" ,DecimalTools.format(countcas,"###,##0.00"));   
				PropertyUtils.setSimpleProperty(form, "countqty" ,DecimalTools.format(countqty,"###,##0.00"));   
				PropertyUtils.setSimpleProperty(form, "countrqy" ,DecimalTools.format(countrqy,"###,##0.00"));   
				PropertyUtils.setSimpleProperty(form, "countnqy" ,DecimalTools.format(countnqy,"###,##0.00"));   
				
				PropertyUtils.setSimpleProperty(form, "endnew" ,endnew);   
				
				
				PropertyUtils.setSimpleProperty(form, "ervseqmx" ,ervseqmx);
				PropertyUtils.setSimpleProperty(form, "ervstrmx" ,ervstrmx);
				PropertyUtils.setSimpleProperty(form, "ervvndmx" ,ervvndmx);
				PropertyUtils.setSimpleProperty(form, "ervssq"   ,ervssq  );
				PropertyUtils.setSimpleProperty(form, "ervsku"   ,ervsku  );
				PropertyUtils.setSimpleProperty(form, "ervskd"   ,ervskd  );
				PropertyUtils.setSimpleProperty(form, "ervvds"   ,ervvds  );
				PropertyUtils.setSimpleProperty(form, "ervsum"   ,ervsum  );
				PropertyUtils.setSimpleProperty(form, "ervbum"   ,ervbum  );
				PropertyUtils.setSimpleProperty(form, "ervupc"   ,ervupc  );
				PropertyUtils.setSimpleProperty(form, "ervcas"   ,ervcas  );
				PropertyUtils.setSimpleProperty(form, "ervqty"   ,ervqty  );
				PropertyUtils.setSimpleProperty(form, "ervmgn"   ,ervmgn  );
				PropertyUtils.setSimpleProperty(form, "ervsks"   ,ervsks  );
				PropertyUtils.setSimpleProperty(form, "ervrqy"   ,ervrqy  );
				PropertyUtils.setSimpleProperty(form, "ervnqy"   ,ervnqy  ); 

				return "1";
			}

			/* (non-Javadoc)
			 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
			 */
			public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
				// 
				return  executeme( mapping,form,request,response);
			}
	
	
	
		}

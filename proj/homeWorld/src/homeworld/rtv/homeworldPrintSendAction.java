/*
 * @(#)homeworldPrintSendAction.java  2004-5-14
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
		import com.idn.util.CommonTools;
		import commsearch.util.CommDate;


		/**
		 * <P>菜单管理</P>
		 * 
		 * @version 0.1
		 * @author 李永初
		 */
		public class homeworldPrintSendAction  extends SecureAction {

			ActionErrors errors = new ActionErrors();
		
			/**
			 * 构造函数
			 */
			public homeworldPrintSendAction  () {
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
						uname,hs.getId(),"PrintSend","B","LYC0000000",0);
						
					if(uname==null){
						errors.add("errormessage",new ActionError("NoName"));
						saveErrors(request, errors);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintSend","E","LYC5555555",l_end - l_begin);
						return(mapping.findForward("success"));
					}
					if(uname.trim().equals("")){
						errors.add("errormessage",new ActionError("NoName"));
						saveErrors(request, errors);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintSend","E","LYC6666666",l_end - l_begin);
						return(mapping.findForward("success"));
					}

			try{
					PropertyUtils.setSimpleProperty(form, "pagerow" ,"13");
					PropertyUtils.setSimpleProperty(form, "addrow" ,"0");
				
					String temp = (String)request.getParameter("selectwhere");

					String stemp=temp;
					
					if(stemp==null)
						stemp="";
					if(stemp.equals("")){
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintSend","E","LYC3333333",l_end - l_begin);
						return(mapping.findForward("err"));
					}
					
					temp=CommonTools.stringReplace(temp,"&#39;","'");
				
					PropertyUtils.setSimpleProperty(form, "selectwhere" ,temp );
					CommDate cd=new CommDate();			
					DataBean dbBean = new DataBean();
					String strsql="select epordt,epordtm,epordtm2 from orderr where "+temp;
					dbBean.executeSelect(strsql);
					PropertyUtils.setSimpleProperty(form, "senddatetime" ,cd.dateFormat(dbBean.getElementValue(0,0).trim(),"L")+" "+dbBean.getElementValue(0,1).trim()+"-"+dbBean.getElementValue(0,2).trim() );
					 
					

					String key=CommonTools.stringReplace(temp,"EPONUM","edcpon");
					
					strsql="select * from edchdr where "+key;//+" and edcvdr='"+uname+"'";
					dbBean.executeSelect(strsql);
					
					if(dbBean.getRowCount()==0){
						if(setFormbeen(form,request,temp,uname).equals("0")){
							saveErrors(request, errors);
//							TODO 临时用来监测用
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"PrintSend","E","LYC8888881",l_end - l_begin);
							return(mapping.findForward("success"));
						}
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintSend","E","LYC8888882",l_end - l_begin);
						return(mapping.findForward("success"));
					}else{
						if(setFormbeenII(form,request,key,uname).equals("0")){
							saveErrors(request, errors);
//							TODO 临时用来监测用
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"PrintSend","E","LYC8888883",l_end - l_begin);
							return(mapping.findForward("sendII"));
						}
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintSend","E","LYC8888884",l_end - l_begin);
						return(mapping.findForward("sendII"));
					}

				} catch (Exception e){
					e.printStackTrace();
					errors.add("errormessage",new ActionError("OrderIn.formbean"));
					saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"PrintSend","C","LYC9999999",l_end - l_begin);
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
			public String setFormbeenII(ActionForm form,HttpServletRequest request,String where,String uname) throws Exception{
				
					DataBean dbBean = new DataBean();
					CommDate cd=new CommDate();   
					String strsql="select * from edchdr where "+where+" and edcvdr='"+uname+"'";
					try{
						dbBean.executeSelect(strsql);
					} catch (Exception e) {
						e.printStackTrace();
						errors.add("errormessage",new ActionError("OrderIn.readdb"));
						return "0";
					}

				String edcpon=dbBean.getElementValue(0,"edcpon"  ).trim();
				String edcvdr=dbBean.getElementValue(0,"edcvdr"  ).trim();
				String edcvdn=dbBean.getElementValue(0,"edcvdn"  ).trim();
				String edcstr=dbBean.getElementValue(0,"edcstr"  ).trim();
				String edcnum=dbBean.getElementValue(0,"edcnum"  ).trim();
				String edcstn=dbBean.getElementValue(0,"edcstn"  ).trim();
				String edcdat=cd.dateFormat(dbBean.getElementValue(0,"edcdat"  ).trim(),"L");		
				String edctim=cd.timeFormat(dbBean.getElementValue(0,"edctim"  ).trim(),"L");	
				String edcrnm=dbBean.getElementValue(0,"edcrnm"  ).trim();
				String edcshp=dbBean.getElementValue(0,"edcshp"  ).trim();

				PropertyUtils.setSimpleProperty(form, "edcpon" ,edcpon );
				PropertyUtils.setSimpleProperty(form, "edcvdr" ,edcvdr );
				PropertyUtils.setSimpleProperty(form, "edcvdn" ,edcvdn );
				PropertyUtils.setSimpleProperty(form, "edcstr" ,edcstr );
				PropertyUtils.setSimpleProperty(form, "edcnum" ,edcnum );
				PropertyUtils.setSimpleProperty(form, "edcstn" ,edcstn );
				PropertyUtils.setSimpleProperty(form, "edcdat" ,edcdat );
				PropertyUtils.setSimpleProperty(form, "edctim" ,edctim );
				PropertyUtils.setSimpleProperty(form, "edcrnm" ,edcrnm );
				PropertyUtils.setSimpleProperty(form, "edcshp" ,edcshp );
				
				
				String key=CommonTools.stringReplace(where,"edcpon","EPONUM");
	
				strsql="select * from epohdr where "+key+" ";   
				try{
					dbBean.executeSelect(strsql);
				} catch (Exception e) {
					errors.add("errormessage",new ActionError("OrderIn.readdb"));
					return "0";
				}
				String eposp1  =dbBean.getElementValue(0,"eposp1"  );
				String epodpt  =dbBean.getElementValue(0,"epodpt"  );
				String epodpn  =dbBean.getElementValue(0,"epodpn"  );
				String epocdt = cd.dateFormat(dbBean.getElementValue(0,"epocdt"),"L");	
				String epoqdt = cd.dateFormat(dbBean.getElementValue(0,"epoqdt"),"L");	
				String eponot1 =dbBean.getElementValue(0,"eponot1" );
				String eponot2 =dbBean.getElementValue(0,"eponot2" );
				String eponot3 =dbBean.getElementValue(0,"eponot3" );
				
				PropertyUtils.setSimpleProperty(form, "eposp1" ,eposp1 );
				PropertyUtils.setSimpleProperty(form, "epodpt" ,epodpt );
				PropertyUtils.setSimpleProperty(form, "epodpn" ,epodpn );
				PropertyUtils.setSimpleProperty(form, "epocdt" ,epocdt );
				PropertyUtils.setSimpleProperty(form, "epoqdt" ,epoqdt );
				PropertyUtils.setSimpleProperty(form, "eponot1" ,eponot1 );
				PropertyUtils.setSimpleProperty(form, "eponot2" ,eponot2 );
				PropertyUtils.setSimpleProperty(form, "eponot3" ,eponot3 );
				
		   //写日志
	/*
		   CommActionLog  cal= new CommActionLog();
				
		   cal.setAct_user(uname);
		   cal.setAct_from("homeworldOrder");
		   cal.setAct_do("SEA");
		   cal.setAct_table("EPOHDR");
		   cal.setAct_ip(request.getRemoteAddr());
		   cal.setAct_memo("查询定单编号为" + eponum);
		   cal.setAct_me("");
		   cal.setActionLog();
	*/

				int count =0;
				strsql="select * from edcdtl,epodtl where edcpon=eponum and edcsku=eposku  and "+where+" order by edcssq";//"epossq";
				try{
					dbBean.executeSelect(strsql);
				} catch (Exception e) {
					errors.add("errormessage",new ActionError("OrderIn.readdb"));
					return "0";
				}
				count=dbBean.getRowCount();
				int pagerow=Integer.parseInt((String)PropertyUtils.getSimpleProperty(form,"pagerow"));
				int addrow=Integer.parseInt((String)PropertyUtils.getSimpleProperty(form,"addrow"))-1;
				
				//计算分页显示的最大页数
				int maxpage=0;
				String endnew="flase";
				
				for(int i=1; i<50; i++){
					maxpage=i;
					//if((count+i)<=(i* pagerow)){
					if((count+i)<=(i* (pagerow+addrow) - addrow )){
						break;
					}
				}


				int countend=0;
				countend=count+maxpage-((maxpage-1)*(pagerow+addrow)-addrow);
				int ss=pagerow;
				
				if(ss!=1){
					ss=pagerow+addrow;
				}
				
				if(ss-countend<addrow+7){
					endnew="true";
				}
			
				int pagecount=0;
			
				if(endnew.equals("true")){
					pagecount=maxpage+1;
				}else{
					pagecount=maxpage;
				}

				String[] edcssq =new String[count+maxpage]; //序号	       
				String[] edcsku =new String[count+maxpage]; //sku编号	       
				String[] edcskd =new String[count+maxpage]; //说明	       
				String[] edcsum =new String[count+maxpage]; //销售单位	       
				String[] edcupc =new String[count+maxpage]; //upc	       
				String[] edccas =new String[count+maxpage]; //订货箱数	       
				String[] edcqty =new String[count+maxpage]; //订货数_件	       
				String[] edcmgn =new String[count+maxpage]; //规格	       
				String[] edcdpn =new String[count+maxpage]; //Part. No 	       
				String[] edcbum =new String[count+maxpage]; //采购单位	       
				String[] edcwgh =new String[count+maxpage]; //箱包重量	       
				String[] edccub =new String[count+maxpage]; //箱包体积	       
				String[] edcadt =new String[count+maxpage]; //DC允收期	       
				String[] edcplr =new String[count+maxpage]; //码放规则	       
				String[] edcddd =new String[count+maxpage]; //码放规则	  
				
				String[] epovds =new String[count+maxpage]; //码放规则	  
				String[] eponct =new String[count+maxpage]; //码放规则	  
	
				double sumperpagecas=0; 
				double sumperpageqty=0;
				double sumperpagewgh=0;
				double sumperpagecub=0;
	
				double sumcas =0;
				double sumqty =0;
				double sumwgh =0;
				double sumcub =0;
				
				int jk=1;
				double temp=0;
				
				int j=0;
				int i=0;
				
				for(;i<count+maxpage;i++){
					if((i==jk*pagerow+(jk-1)*addrow-1)||(i==count+maxpage-1)){

						edcssq[i]="count";
						edcsku[i]="";
						edcskd[i]="";
						edcsum[i]="";
						edcupc[i]="";
						edccas[i]="";
						edcqty[i]="";
						edcmgn[i]="";
						edcdpn[i]="";
						edcbum[i]="";
						edcwgh[i]="";
						edccub[i]="";
						edcadt[i]="";
						edcplr[i]="";
						edcddd[i]="";

						epovds[i]="";
						eponct[i]="";
						
						edccas[i]=DecimalTools.format(String.valueOf(sumperpagecas),"###,##0.00");
						edcqty[i]=DecimalTools.format(String.valueOf(sumperpageqty),"###,##0.00");
						edcwgh[i]=DecimalTools.format(String.valueOf(sumperpagewgh),"###,##0.00");
						edccub[i]=DecimalTools.format(String.valueOf(sumperpagecub),"###,##0.00");
						
						sumperpagecas=0;
						sumperpageqty=0;
						sumperpagewgh=0;
						sumperpagecub=0;
						jk++;					
					}else{
						edcssq[i]=dbBean.getElementValue(j,"edcssq"  ).trim();
						edcsku[i]=dbBean.getElementValue(j,"edcsku"  ).trim();
						edcskd[i]=dbBean.getElementValue(j,"edcskd"  ).trim();
						edcsum[i]=dbBean.getElementValue(j,"edcsum"  ).trim();
						edcupc[i]=dbBean.getElementValue(j,"edcupc"  ).trim();
						edccas[i]=dbBean.getElementValue(j,"edccas"  ).trim();
						edcqty[i]=dbBean.getElementValue(j,"edcqty"  ).trim();
						edcmgn[i]=dbBean.getElementValue(j,"edcmgn"  ).trim();
						edcdpn[i]=dbBean.getElementValue(j,"edcdpn"  ).trim();
						edcbum[i]=dbBean.getElementValue(j,"edcbum"  ).trim();
						edcwgh[i]=dbBean.getElementValue(j,"edcwgh"  ).trim();
						edccub[i]=dbBean.getElementValue(j,"edccub"  ).trim();
						edcadt[i]=cd.dateFormat(dbBean.getElementValue(j,"edcadt"  ).trim(),"L");		
						edcplr[i]=dbBean.getElementValue(j,"edcplr"  ).trim();
						edcddd[i]=dbBean.getElementValue(j,"edcatr"  ).trim();

						epovds[i]=dbBean.getElementValue(j,"epovds"  ).trim();
						eponct[i]=DecimalTools.format(dbBean.getElementValue(j,"eponct"  ).trim(),"###,##0.0000");
						
						
						temp=Double.parseDouble(edccas[i]);
						sumperpagecas=sumperpagecas+temp;
						
						temp=Double.parseDouble(edcqty[i]);
						sumperpageqty=sumperpageqty+temp;
						
						temp=Double.parseDouble(edcwgh[i]);
						sumperpagewgh=sumperpagewgh+temp;

						temp=Double.parseDouble(edccub[i]);
						sumperpagecub=sumperpagecub+temp;
						
						sumcas =sumcas +Double.parseDouble(edccas[i]);
						sumqty =sumqty +Double.parseDouble(edcqty[i]);
						sumwgh =sumwgh +Double.parseDouble(edcwgh[i]);
						sumcub =sumcub +Double.parseDouble(edccub[i]);
						
						edccas[i]=DecimalTools.format(edccas[i],"###,##0.00");
						edcqty[i]=DecimalTools.format(edcqty[i],"###,##0.00");
						edcwgh[i]=DecimalTools.format(edcwgh[i],"###,##0.00");
	
	
						j++;
					}
				}
	
				PropertyUtils.setSimpleProperty(form, "sumcas" ,DecimalTools.format(String.valueOf(sumcas),"###,##0.00"));
				PropertyUtils.setSimpleProperty(form, "sumqty" ,DecimalTools.format(String.valueOf(sumqty),"###,##0.00"));
				PropertyUtils.setSimpleProperty(form, "sumwgh" ,DecimalTools.format(String.valueOf(sumwgh),"###,##0.00"));
				PropertyUtils.setSimpleProperty(form, "sumcub" ,DecimalTools.format(String.valueOf(sumcub),"###,##0.00"));
					    
				PropertyUtils.setSimpleProperty(form, "edcpon" ,edcpon);
				PropertyUtils.setSimpleProperty(form, "edcssq" ,edcssq);
				PropertyUtils.setSimpleProperty(form, "edcsku" ,edcsku);
				PropertyUtils.setSimpleProperty(form, "edcskd" ,edcskd);
				PropertyUtils.setSimpleProperty(form, "edcsum" ,edcsum);
				PropertyUtils.setSimpleProperty(form, "edcupc" ,edcupc);
				PropertyUtils.setSimpleProperty(form, "edccas" ,edccas);
				PropertyUtils.setSimpleProperty(form, "edcqty" ,edcqty);
				PropertyUtils.setSimpleProperty(form, "edcmgn" ,edcmgn);
				PropertyUtils.setSimpleProperty(form, "edcdpn" ,edcdpn);
				PropertyUtils.setSimpleProperty(form, "edcbum" ,edcbum);
				PropertyUtils.setSimpleProperty(form, "edcwgh" ,edcwgh);
				PropertyUtils.setSimpleProperty(form, "edccub" ,edccub);
				PropertyUtils.setSimpleProperty(form, "edcadt" ,edcadt);
				PropertyUtils.setSimpleProperty(form, "edcplr" ,edcplr);
				PropertyUtils.setSimpleProperty(form, "edcddd" ,edcddd);

				PropertyUtils.setSimpleProperty(form, "epovds" ,epovds);
				PropertyUtils.setSimpleProperty(form, "eponct" ,eponct);
				
				PropertyUtils.setSimpleProperty(form, "endnew" ,endnew);

				return "1";
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
						CommDate cd=new CommDate();				
						String strsql="select * from epohdr where "+where+" ";   
						try{
							dbBean.executeSelect(strsql);
						} catch (Exception e) {
							e.printStackTrace();
							errors.add("errormessage",new ActionError("OrderIn.readdb"));
							return "0";
						}
			
				String epocdt = cd.dateFormat(dbBean.getElementValue(0,"epocdt"),"L");					
//				String epocdt  =dbBean.getElementValue(0,"epocdt"  );
				String epousr  =dbBean.getElementValue(0,"epousr"  );
				String eponum  =dbBean.getElementValue(0,"eponum"  );
				String epoctm  =cd.timeFormat(dbBean.getElementValue(0,"epoctm").trim() ,"L");
				//String epoctm  =dbBean.getElementValue(0,"epoctm"  );
				String epostrz =dbBean.getElementValue(0,"epostr"  );
				String epostn  =dbBean.getElementValue(0,"epostn"  );
				String epovndz =dbBean.getElementValue(0,"epovnd"  );
				String eposa1  =dbBean.getElementValue(0,"eposa1"  );
				String eposa2  =dbBean.getElementValue(0,"eposa2"  );
				String eposa3  =dbBean.getElementValue(0,"eposa3"  );
				String eposcy  =dbBean.getElementValue(0,"eposcy"  );
				String eposzp  =dbBean.getElementValue(0,"eposzp"  );
				String eposcn  =dbBean.getElementValue(0,"eposcn"  );
				String epovdn  =dbBean.getElementValue(0,"epovdn"  );
				String epova1  =dbBean.getElementValue(0,"epova1"  );
				String epova2  =dbBean.getElementValue(0,"epova2"  );
				String epova3  =dbBean.getElementValue(0,"epova3"  );
				String epovcy  =dbBean.getElementValue(0,"epovcy"  );
				String epovzp  =dbBean.getElementValue(0,"epovzp"  );
				String epovcn  =dbBean.getElementValue(0,"epovcn"  );
				String epobyr  =dbBean.getElementValue(0,"epobyr"  );
				String epobyn  =dbBean.getElementValue(0,"epobyn"  );
				String epodpt  =dbBean.getElementValue(0,"epodpt"  );
				String epodpn  =dbBean.getElementValue(0,"epodpn"  );
				String eposdpt =dbBean.getElementValue(0,"eposdpt" );
				String eposdptn=dbBean.getElementValue(0,"eposdptn");
				String epotrm  =dbBean.getElementValue(0,"epotrm"  );
				String epotmn  =dbBean.getElementValue(0,"epotmn"  );
				String epofrc  =dbBean.getElementValue(0,"epofrc"  );
				String epofrn  =dbBean.getElementValue(0,"epofrn"  );
				String epoedt  =cd.dateFormat(dbBean.getElementValue(0,"epoedt").trim() ,"L");
				String eposdt  =cd.dateFormat(dbBean.getElementValue(0,"eposdt").trim(),"L");
				String epoqdt  =cd.dateFormat(dbBean.getElementValue(0,"epoqdt").trim(),"L");
				String epordt  =cd.dateFormat(dbBean.getElementValue(0,"epordt").trim(),"L");

			
	/*			String epoedt  =dbBean.getElementValue(0,"epoedt"  );
				String eposdt  =dbBean.getElementValue(0,"eposdt"  );
				String epoqdt  =dbBean.getElementValue(0,"epoqdt"  );
				String epordt  =dbBean.getElementValue(0,"epordt"  );
	*/
				String eposts  =dbBean.getElementValue(0,"eposts"  );
				String epostd  =dbBean.getElementValue(0,"epostd"  );
				String epovct  =dbBean.getElementValue(0,"epovct"  );
				String epospn  =dbBean.getElementValue(0,"epospn"  );
				String eposfx  =dbBean.getElementValue(0,"eposfx"  );
				String epovpn  =dbBean.getElementValue(0,"epovpn"  );
				String epovfx  =dbBean.getElementValue(0,"epovfx"  );
				String epocur  =dbBean.getElementValue(0,"epocur"  );
				String epocrd  =dbBean.getElementValue(0,"epocrd"  );
				String epoflg  =dbBean.getElementValue(0,"epoflg"  );
				String eposeq  =dbBean.getElementValue(0,"eposeq"  );
				String eponot1 =dbBean.getElementValue(0,"eponot1" );
				String eponot2 =dbBean.getElementValue(0,"eponot2" );
				String eponot3 =dbBean.getElementValue(0,"eponot3" );
				String epofob  =dbBean.getElementValue(0,"epofob"  );
				String eposp1  =dbBean.getElementValue(0,"eposp1"  );
				String eposp2  =dbBean.getElementValue(0,"eposp2"  );
				String epospp  =dbBean.getElementValue(0,"epospp"  );
				String epospc  =dbBean.getElementValue(0,"epospc"  );
				String epotdt  =dbBean.getElementValue(0,"epotdt"  );
				String epoodt  =dbBean.getElementValue(0,"epoodt"  );


		   PropertyUtils.setSimpleProperty(form, "epocdt" ,epocdt );
		   PropertyUtils.setSimpleProperty(form, "epousr"  ,epousr  );
		   PropertyUtils.setSimpleProperty(form, "eponum"  ,eponum  );
		   PropertyUtils.setSimpleProperty(form, "epoctm"  ,epoctm  );
		   PropertyUtils.setSimpleProperty(form, "epostrz" ,epostrz );
		   PropertyUtils.setSimpleProperty(form, "epostn"  ,epostn  );
		   PropertyUtils.setSimpleProperty(form, "epovndz" ,epovndz );
		   PropertyUtils.setSimpleProperty(form, "eposa1"  ,eposa1  );
		   PropertyUtils.setSimpleProperty(form, "eposa2"  ,eposa2  );
		   PropertyUtils.setSimpleProperty(form, "eposa3"  ,eposa3  );
		   PropertyUtils.setSimpleProperty(form, "eposcy"  ,eposcy  );
		   PropertyUtils.setSimpleProperty(form, "eposzp"  ,eposzp  );
		   PropertyUtils.setSimpleProperty(form, "eposcn"  ,eposcn  );
		   PropertyUtils.setSimpleProperty(form, "epovdn"  ,epovdn  );
		   PropertyUtils.setSimpleProperty(form, "epova1"  ,epova1  );
		   PropertyUtils.setSimpleProperty(form, "epova2"  ,epova2  );
		   PropertyUtils.setSimpleProperty(form, "epova3"  ,epova3  );
		   PropertyUtils.setSimpleProperty(form, "epovcy"  ,epovcy  );
		   PropertyUtils.setSimpleProperty(form, "epovzp"  ,epovzp  );
		   PropertyUtils.setSimpleProperty(form, "epovcn"  ,epovcn  );
		   PropertyUtils.setSimpleProperty(form, "epobyr"  ,epobyr  );
		   PropertyUtils.setSimpleProperty(form, "epobyn"  ,epobyn  );
		   PropertyUtils.setSimpleProperty(form, "epodpt"  ,epodpt  );
		   PropertyUtils.setSimpleProperty(form, "epodpn"  ,epodpn  );
		   PropertyUtils.setSimpleProperty(form, "eposdpt" ,eposdpt );
		   PropertyUtils.setSimpleProperty(form, "eposdptn",eposdptn);
		   PropertyUtils.setSimpleProperty(form, "epotrm"  ,epotrm  );
		   PropertyUtils.setSimpleProperty(form, "epotmn"  ,epotmn  );
		   PropertyUtils.setSimpleProperty(form, "epofrc"  ,epofrc  );
		   PropertyUtils.setSimpleProperty(form, "epofrn"  ,epofrn  );
		   PropertyUtils.setSimpleProperty(form, "epoedt"  ,epoedt  );
		   PropertyUtils.setSimpleProperty(form, "eposdt"  ,eposdt );
		   PropertyUtils.setSimpleProperty(form, "epoqdt"  ,epoqdt );
		   PropertyUtils.setSimpleProperty(form, "epordt"  ,epordt  );
		   PropertyUtils.setSimpleProperty(form, "eposts"  ,eposts  );
		   PropertyUtils.setSimpleProperty(form, "epostd"  ,epostd  );
		   PropertyUtils.setSimpleProperty(form, "epovct"  ,epovct  );
		   PropertyUtils.setSimpleProperty(form, "epospn"  ,epospn  );
		   PropertyUtils.setSimpleProperty(form, "eposfx"  ,eposfx  );
		   PropertyUtils.setSimpleProperty(form, "epovpn"  ,epovpn  );
		   PropertyUtils.setSimpleProperty(form, "epovfx"  ,epovfx  );
		   PropertyUtils.setSimpleProperty(form, "epocur"  ,epocur  );
		   PropertyUtils.setSimpleProperty(form, "epocrd"  ,epocrd  );
		   PropertyUtils.setSimpleProperty(form, "epoflg"  ,epoflg  );
		   PropertyUtils.setSimpleProperty(form, "eposeq"  ,eposeq  );
		   PropertyUtils.setSimpleProperty(form, "eponot1" ,eponot1 );
		   PropertyUtils.setSimpleProperty(form, "eponot2" ,eponot2 );
		   PropertyUtils.setSimpleProperty(form, "eponot3" ,eponot3 );
		   PropertyUtils.setSimpleProperty(form, "epofob"  ,epofob  );
		   PropertyUtils.setSimpleProperty(form, "eposp1"  ,eposp1  );
		   PropertyUtils.setSimpleProperty(form, "eposp2"  ,eposp2  );
		   PropertyUtils.setSimpleProperty(form, "epospp"  ,epospp  );
		   PropertyUtils.setSimpleProperty(form, "epospc"  ,epospc  );
		   PropertyUtils.setSimpleProperty(form, "epotdt"  ,epotdt );
		   PropertyUtils.setSimpleProperty(form, "epoodt"  ,epoodt  );
		   //写日志
	/*
		   CommActionLog  cal= new CommActionLog();
			
		   cal.setAct_user(uname);
		   cal.setAct_from("homeworldOrder");
		   cal.setAct_do("SEA");
		   cal.setAct_table("EPOHDR");
		   cal.setAct_ip(request.getRemoteAddr());
		   cal.setAct_memo("查询定单编号为" + eponum);
		   cal.setAct_me("");
		   cal.setActionLog();
	*/
                                                  
				String[] epostr =null;  //商店             
				String[] epovnd =null;  //供货商           
				String[] epossq =null;  //sku序号          
				String[] eposku =null;  //sku              
				String[] eposkd =null;  //sku描述          
				String[] epovds =null;  //供货商货号       
				String[] eposum =null;  //销售单位         
				String[] epobum =null;  //采购单位         
				String[] epoupc =null;  //upc              
				String[] epocas =null;  //订货数_包        
				String[] epoqty =null;  //订货数_件        
				String[] epomgn =null;  //规格             
				String[] epobts =null;  //epobts           
				String[] eponct =null;  //净成本           
				String[] eposks =null;  //折扣标志
				String[] epoddd =null;  //折扣标志
		
				int count =0;
				strsql="select * from epodtl where "+where+" order by eponum,eposeq";//"epossq";   
				try{
					dbBean.executeSelect(strsql);
				} catch (Exception e) {
					e.printStackTrace();
					errors.add("errormessage",new ActionError("OrderIn.readdb"));
					return "0";
				}
				count=dbBean.getRowCount();
			
				int pagerow=Integer.parseInt((String)PropertyUtils.getSimpleProperty(form,"pagerow"));
				int addrow=Integer.parseInt((String)PropertyUtils.getSimpleProperty(form,"addrow"));
			
				//计算分页显示的最大页数
				int maxpage=0;
				String endnew="flase";
				
				for(int i=1; i<50; i++){
					maxpage=i;
					//if((count+i+2)<=(i* pagerow)){
					if((count+i)<=(i* (pagerow+addrow) - addrow )){
						break;
					}
				}

				int countend=0;
				countend=count+maxpage-((maxpage-1)*(pagerow+addrow)-addrow);
				int ss=pagerow;
				
				if(ss!=1){
					ss=pagerow+addrow;
				}
				
				if(ss-countend<addrow+5){
					endnew="true";
				}
			
				int pagecount=0;
			
				if(endnew.equals("true")){
					pagecount=maxpage+1;
				}else{
					pagecount=maxpage;
				}

				epostr=new String[count+maxpage];
				epovnd=new String[count+maxpage];
				epossq=new String[count+maxpage];
				eposku=new String[count+maxpage];
				eposkd=new String[count+maxpage];
				epovds=new String[count+maxpage];
				eposum=new String[count+maxpage];
				epobum=new String[count+maxpage];
				epoupc=new String[count+maxpage];
				epocas=new String[count+maxpage];
				epoqty=new String[count+maxpage];
				epomgn=new String[count+maxpage];
				epobts=new String[count+maxpage];
				eponct=new String[count+maxpage];
				eposks=new String[count+maxpage];
				epoddd=new String[count+maxpage];
				double sumperpagecas=0; 
				double sumperpageqty=0;
				double sumperpagenct=0;

				double sumcas =0;
				double sumqty =0;
				double sumnct =0;
				int jk=1;
				double temp=0;
			
				int j=0;

				int i=0;
			
				for(;i<count+maxpage;i++){
					
					if((i==jk*pagerow+(jk-1)*addrow-1)||(i==count+maxpage-1)){
						epostr[i]="小计--        "; 
						epovnd[i]=""; 
						epossq[i]=""; 
						eposku[i]=""; 
						eposkd[i]=""; 
						epovds[i]=""; 
						eposum[i]=""; 
						epobum[i]=""; 
						epoupc[i]=""; 
						epocas[i]=""; 
						epoqty[i]=""; 
						epomgn[i]=""; 
						epobts[i]=""; 
						eponct[i]=""; 
						eposks[i]="";
						epoddd[i]="";
						epocas[i]=DecimalTools.format(String.valueOf(sumperpagecas),"###,##0.00");
						epoqty[i]=DecimalTools.format(String.valueOf(sumperpageqty),"###,##0.00");
						eponct[i]=DecimalTools.format(String.valueOf(sumperpagenct),"###,##0.0000");
						sumperpagecas=0;
						sumperpageqty=0;
						sumperpagenct=0;
//						if(jk==1){
//							pagerow=pagerow+addrow/2;
//						}
						jk++;					
					}else{
						epostr[i]=dbBean.getElementValue(j,"epostr"  ); 
						epovnd[i]=dbBean.getElementValue(j,"epovnd"  ); 
						epossq[i]=dbBean.getElementValue(j,"epossq"  ); 
						eposku[i]=dbBean.getElementValue(j,"eposku"  ); 
						eposkd[i]=dbBean.getElementValue(j,"eposkd"  ); 
						epovds[i]=dbBean.getElementValue(j,"epovds"  ); 
						eposum[i]=dbBean.getElementValue(j,"eposum"  ); 
						epobum[i]=dbBean.getElementValue(j,"epobum"  ); 
						epoupc[i]=dbBean.getElementValue(j,"epoupc"  ); 
						epocas[i]=dbBean.getElementValue(j,"epocas"  ); 
						epoqty[i]=dbBean.getElementValue(j,"epoqty"  ); 
						epomgn[i]=dbBean.getElementValue(j,"epomgn"  ); 
						epobts[i]=dbBean.getElementValue(j,"epobts"  ); 
						
						eponct[i] = dbBean.getElementValue(j,"eponct"  );
						eposks[i]=dbBean.getElementValue(j,"eposks"  );
						//质地
						epoddd[i]=dbBean.getElementValue(j,"epoatr"  );
	
						temp=Double.parseDouble(epocas[i]);
						sumperpagecas=sumperpagecas+temp;
					
						temp=Double.parseDouble(epoqty[i]);
						sumperpageqty=sumperpageqty+temp;
					
						temp=Double.parseDouble(dbBean.getElementValue(j,"eponct"  ));
						sumperpagenct=sumperpagenct+(temp*Double.parseDouble(epoqty[i]));
					
					
						sumcas =sumcas +Double.parseDouble(epocas[i]);
						sumqty =sumqty +Double.parseDouble(epoqty[i]);
						sumnct =sumnct +(Double.parseDouble(eponct[i])*Double.parseDouble(epoqty[i]));

						epocas[i]=DecimalTools.format(epocas[i],"###,##0.00");
						epoqty[i]=DecimalTools.format(epoqty[i],"###,##0.00");
//						XIAOAI
						eponct[i]=DecimalTools.format(eponct[i],"###,##0.0000");


						j++;
					}
				}

				PropertyUtils.setSimpleProperty(form, "sumcas" ,DecimalTools.format(String.valueOf(sumcas),"###,##0.00"));
				PropertyUtils.setSimpleProperty(form, "sumqty" ,DecimalTools.format(String.valueOf(sumqty),"###,##0.00"));
				PropertyUtils.setSimpleProperty(form, "sumnct" ,DecimalTools.format(String.valueOf(sumnct),"###,##0.0000"));
			    
				PropertyUtils.setSimpleProperty(form, "epostr" ,epostr);
				PropertyUtils.setSimpleProperty(form, "epovnd" ,epovnd);
				PropertyUtils.setSimpleProperty(form, "epossq" ,epossq);
				PropertyUtils.setSimpleProperty(form, "eposku" ,eposku);
				PropertyUtils.setSimpleProperty(form, "eposkd" ,eposkd);
				PropertyUtils.setSimpleProperty(form, "epovds" ,epovds);
				PropertyUtils.setSimpleProperty(form, "eposum" ,eposum);
				PropertyUtils.setSimpleProperty(form, "epobum" ,epobum);
				PropertyUtils.setSimpleProperty(form, "epoupc" ,epoupc);
				PropertyUtils.setSimpleProperty(form, "epocas" ,epocas);
				PropertyUtils.setSimpleProperty(form, "epoqty" ,epoqty);
				PropertyUtils.setSimpleProperty(form, "epomgn" ,epomgn);
				PropertyUtils.setSimpleProperty(form, "epobts" ,epobts);
				PropertyUtils.setSimpleProperty(form, "eponct" ,eponct);
				PropertyUtils.setSimpleProperty(form, "eposks" ,eposks);

				PropertyUtils.setSimpleProperty(form, "epoddd" ,epoddd);
				PropertyUtils.setSimpleProperty(form, "endnew" ,endnew);
			
			
				return "1";
			}


			/* （非 Javadoc）
			 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
			 */
			public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
				return executeme(mapping,form,request,response);
			}
	
	
	
		}


/*
 * @(#)homeworldPrintOrderNEWAction.java  2004-4-12
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.print;

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
	public class homeworldPrintOrderNEWAction extends SecureAction {

		ActionErrors errors = new ActionErrors();
		
		/**
		 * 构造函数
		 */
		public homeworldPrintOrderNEWAction () {
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
					uname,hs.getId(),"PrintOrderNEW","B","LYC0000000",0);
			
				if(uname==null){
					errors.add("errormessage",new ActionError("NoName"));
					saveErrors(request, errors);
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"PrintOrderNEW","E","LYC5555555",l_end - l_begin); 
					return(mapping.findForward("success"));
				}
				if(uname.trim().equals("")){
					errors.add("errormessage",new ActionError("NoName"));
					saveErrors(request, errors);
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"PrintOrderNEW","E","LYC6666666",l_end - l_begin); 
					return(mapping.findForward("success"));
				}
			
		try{		
				PropertyUtils.setSimpleProperty(form, "pagerow" ,"22");
				
				String temp = (String)request.getParameter("selectwhere");

				String stemp=temp;
				
				if(stemp==null)
					stemp="";
				if(stemp.equals("")){
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"PrintOrderNEW","E","LYC3333333",l_end - l_begin);
					return(mapping.findForward("err"));
				}
				
				temp=CommonTools.stringReplace(temp,"&#39;","'");
				
				PropertyUtils.setSimpleProperty(form, "selectwhere" ,temp );
				
				if(setFormbeen(form,request,temp,uname).equals("0")){
					saveErrors(request, errors);
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"PrintOrderNEW","E","LYC8888881",l_end - l_begin); 
					return(mapping.findForward("success"));
				}
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"PrintOrderNEW","E","LYC8888888",l_end - l_begin); 
				return(mapping.findForward("success"));
					

			} catch (Exception e){
				e.printStackTrace();
				errors.add("errormessage",new ActionError("OrderIn.formbean"));
				saveErrors(request, errors);
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"PrintOrderNEW","C","LYC9999999",l_end - l_begin); 
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
//			String epocdt  =dbBean.getElementValue(0,"epocdt"  );
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
			//XIAOAI修改
			String epotdt  =DecimalTools.format(dbBean.getElementValue(0,"epotdt"),"##,###,##0.000");
			String epoodt  =DecimalTools.format(dbBean.getElementValue(0,"epoodt"),"##,###,##0.000");


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
			 
			String[] values =null;  //跳转页值
			String[] labels =null;  //跳转页显示
			
			String[] length =null;

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
			
			if(pagerow-countend<8){
				endnew="true";
			}
			
			int pagecount=0;
			
			if(endnew.equals("true")){
				pagecount=maxpage+1;
			}else{
				pagecount=maxpage;
			}
			
			values=new String[pagecount];  
			labels=new String[pagecount];
			
			for(int i=0;i<pagecount;i++){
				 labels[i]=String.valueOf(i+1);
				 values[i]=String.valueOf(i+1);
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

			double sumperpagecas=0; 
			double sumperpageqty=0;
			double sumperpagenct=0;

			double sumcas =0;
			double sumqty =0;
			double sumnct =0;
			int jk=1;
			double temp=0;
			
			int j=0;
			
			for(int i=0;i<count+maxpage;i++){
				if((i==jk*pagerow-1)||(i==count+maxpage-1)){
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
					
					epocas[i]=DecimalTools.format(String.valueOf(sumperpagecas),"###,##0.00");
					epoqty[i]=DecimalTools.format(String.valueOf(sumperpageqty),"###,##0.00");
					eponct[i]=DecimalTools.format(String.valueOf(sumperpagenct),"###,##0.0000");
					sumperpagecas=0;
					sumperpageqty=0;
					sumperpagenct=0;
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
					eponct[i]=dbBean.getElementValue(j,"eponct"  ); 
					eposks[i]=dbBean.getElementValue(j,"eposks"  );
	
	
					temp=Double.parseDouble(epocas[i]);
					sumperpagecas=sumperpagecas+temp;
					
					temp=Double.parseDouble(epoqty[i]);
					sumperpageqty=sumperpageqty+temp;
					
					temp=Double.parseDouble(eponct[i]);
					sumperpagenct=sumperpagenct+(temp*Double.parseDouble(epoqty[i]));
					
					
					sumcas =sumcas +Double.parseDouble(epocas[i]);
					sumqty =sumqty +Double.parseDouble(epoqty[i]);
					sumnct =sumnct +(Double.parseDouble(eponct[i])*Double.parseDouble(epoqty[i]));

					epocas[i]=DecimalTools.format(epocas[i],"###,##0.00");
					epoqty[i]=DecimalTools.format(epoqty[i],"###,##0.00");
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


			PropertyUtils.setSimpleProperty(form, "endnew" ,endnew);
			
			PropertyUtils.setSimpleProperty(form, "values" ,values);
			PropertyUtils.setSimpleProperty(form, "labels" ,labels);
			
			return "1";
		}


		/* （非 Javadoc）
		 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 */
		public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			return executeme(mapping,form,request,response);
		}
	
	
	
	}


/*
 * @(#)homewordOrderAction.java  2004-1-8
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.action;

import java.io.IOException;
//import java.util.Date;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
//import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//import com.idn.property.CodesManager;
import com.idn.secure.SecureAction;
import com.idn.sql.DataBean;
//import com.idn.sql.DynaSqlBean;
//import com.idn.util.FormatDate;
import com.idn.util.DecimalTools;
import commsearch.util.CommActionLog;
import commsearch.util.CommDate;


/**
 * <P>菜单管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class homeworldOrderAction extends SecureAction {
	ActionErrors errors = new ActionErrors();

	/**
	 * 构造函数
	 */
	public homeworldOrderAction() {
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
				uname,hs.getId(),"Order","B","LYC0000000",0);
				
				
			try{					
					
			String temp="";
			Vector vecTemp=new Vector();
			
			//page--> 0：录入 1：更改
			String page=((String) PropertyUtils.getSimpleProperty(form, "page")).trim();
			temp = (String)request.getParameter("selectwhere");
			if(temp==null)
				temp="";
			if(temp.equals("")){
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Order","E","LYC3333333",l_end - l_begin);
				return(mapping.findForward("err"));
			}
			
			String queryid=(String)request.getParameter("queryid");
			PropertyUtils.setSimpleProperty(form,"queryid",queryid);
			PropertyUtils.setSimpleProperty(form,"selectwhere",temp);

			PropertyUtils.setSimpleProperty(form,"url",temp);

				if(setFormbeen(form,request,temp,uname).equals("0")){
					saveErrors(request, errors);
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Order","E","LYC8888881",l_end - l_begin);
					return(mapping.findForward("err"));
				}
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Order","E","LYC8888888",l_end - l_begin);
			
			return(mapping.findForward("success"));
		} catch (Exception e){
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Datebase.formbean"));
			saveErrors(request, errors);
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"Order","C","LYC9999999",l_end - l_begin);
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
				CommDate cd=new CommDate();
		
				//String strsql="select * from epohdr where "+where+" ";   
				String strsql="select * from epohdrl2 where "+where+" and epovnd='"+uname+"'";
				try{
					dbBean.executeSelect(strsql);
				} catch (Exception e) {
					e.printStackTrace();
					errors.add("errormessage",new ActionError("Datebase.readdb"));
					return "0";
				}


		String epocdt = cd.dateFormat(dbBean.getElementValue(0,"epocdt"),"L");
//		String epocdt = dbBean.getElementValue(0,"epocdt");
		String epousr  =dbBean.getElementValue(0,"epousr");
		String eponum  =dbBean.getElementValue(0,"eponum");
		String epoctm  =cd.timeFormat(dbBean.getElementValue(0,"epoctm").trim() ,"L");
//		String epoctm  =dbBean.getElementValue(0,"epoctm").trim();
		String epostrz =dbBean.getElementValue(0,"epostr");
		String epostn  =dbBean.getElementValue(0,"epostn");
		String epovndz =dbBean.getElementValue(0,"epovnd");
		String eposa1  =dbBean.getElementValue(0,"eposa1");
		String eposa2  =dbBean.getElementValue(0,"eposa2");
		String eposa3  =dbBean.getElementValue(0,"eposa3");
		String eposcy  =dbBean.getElementValue(0,"eposcy");
		String eposzp  =dbBean.getElementValue(0,"eposzp");
		String eposcn  =dbBean.getElementValue(0,"eposcn");
		String epovdn  =dbBean.getElementValue(0,"epovdn");
		String epova1  =dbBean.getElementValue(0,"epova1");
		String epova2  =dbBean.getElementValue(0,"epova2");
		String epova3  =dbBean.getElementValue(0,"epova3");
		String epovcy  =dbBean.getElementValue(0,"epovcy");
		String epovzp  =dbBean.getElementValue(0,"epovzp");
		String epovcn  =dbBean.getElementValue(0,"epovcn");
		String epobyr  =dbBean.getElementValue(0,"epobyr");
		String epobyn  =dbBean.getElementValue(0,"epobyn");
		String epodpt  =dbBean.getElementValue(0,"epodpt");
		String epodpn  =dbBean.getElementValue(0,"epodpn");
		String eposdpt =dbBean.getElementValue(0,"eposdpt");
		String eposdptn=dbBean.getElementValue(0,"eposdptn");
		String epotrm  =dbBean.getElementValue(0,"epotrm");
		String epotmn  =dbBean.getElementValue(0,"epotmn");
		String epofrc  =dbBean.getElementValue(0,"epofrc");
		String epofrn  =dbBean.getElementValue(0,"epofrn");
		String epotyp  =dbBean.getElementValue(0,"epotyp");
		
		String epoedt  =cd.dateFormat(dbBean.getElementValue(0,"epoedt").trim() ,"L");
		String eposdt  =cd.dateFormat(dbBean.getElementValue(0,"eposdt").trim(),"L");
		String epoqdt  =cd.dateFormat(dbBean.getElementValue(0,"epoqdt").trim(),"L");
		String epordt  =cd.dateFormat(dbBean.getElementValue(0,"epordt").trim(),"L");

//		String epoedt  =dbBean.getElementValue(0,"epoedt");
//		String eposdt  =dbBean.getElementValue(0,"eposdt");
//		String epoqdt  =dbBean.getElementValue(0,"epoqdt");
//		String epordt  =dbBean.getElementValue(0,"epordt");
		String eposts  =dbBean.getElementValue(0,"eposts");
		String epostd  =dbBean.getElementValue(0,"epostd");
		String epovct  =dbBean.getElementValue(0,"epovct");
		String epospn  =dbBean.getElementValue(0,"epospn");
		String eposfx  =dbBean.getElementValue(0,"eposfx");
		String epovpn  =dbBean.getElementValue(0,"epovpn");
		String epovfx  =dbBean.getElementValue(0,"epovfx");
		String epocur  =dbBean.getElementValue(0,"epocur");
		String epocrd  =dbBean.getElementValue(0,"epocrd");
		String epoflg  =dbBean.getElementValue(0,"epoflg");
		String eposeq  =dbBean.getElementValue(0,"eposeq");
		String eponot1 =dbBean.getElementValue(0,"eponot1");
		String eponot2 =dbBean.getElementValue(0,"eponot2");
		String eponot3 =dbBean.getElementValue(0,"eponot3");
		String epofob  =dbBean.getElementValue(0,"epofob");
		String eposp1  =dbBean.getElementValue(0,"eposp1");
		String eposp2  =dbBean.getElementValue(0,"eposp2");
		String epospp  =dbBean.getElementValue(0,"epospp");
		String epospc  =dbBean.getElementValue(0,"epospc");
		//XIAOAI修改
		String epotdt  =DecimalTools.format(dbBean.getElementValue(0,"epotdt"),"##,###,##0.0000");
		String epoodt  =DecimalTools.format(dbBean.getElementValue(0,"epoodt"),"##,###,##0.0000");

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
   PropertyUtils.setSimpleProperty(form, "epotyp"  ,epotyp.toUpperCase()  );
   
   
   //写日志
   CommActionLog  cal= new CommActionLog();
			
   cal.setAct_user(uname);
   cal.setAct_from("homeworldOrder");
   cal.setAct_do("SEA");
   cal.setAct_key(eponum);
   cal.setAct_table("EPOHDR");
   cal.setAct_ip(request.getRemoteAddr());
   cal.setAct_memo("查询的订单编号为" + eponum);
   cal.setAct_me("");
	cal.setActionLog();
 
                                                  
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
		String countcas1="";
		String countqty1="";
		String countnct1="";

		int count =0;

		//strsql="select * from epodtl where "+where+"  order by eponum,eposeq";
		strsql="select * from epodtll1 where "+where+" and epovnd='"+uname+"' order by eponum,eposeq";//"epossq";
		try{
			dbBean.executeSelect(strsql);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Datebase.readdb"));
			return "0";
		}
		count=dbBean.getRowCount();

		epostr=new String[count];
		epovnd=new String[count];
		epossq=new String[count];
		eposku=new String[count];
		eposkd=new String[count];
		epovds=new String[count];
		eposum=new String[count];
		epobum=new String[count];
		epoupc=new String[count];
		epocas=new String[count];
		epoqty=new String[count];
		epomgn=new String[count];
		epobts=new String[count];
		eponct=new String[count];
		eposks=new String[count];
		
		double countcas=0;
		double countqty=0;
		double countnct=0;

		 
		for(int i=0;i<count;i++){
                   
				epostr[i]=dbBean.getElementValue(i,"epostr"); 
				epovnd[i]=dbBean.getElementValue(i,"epovnd"); 
				epossq[i]=dbBean.getElementValue(i,"epossq"); 
				eposku[i]=dbBean.getElementValue(i,"eposku"); 
				eposkd[i]=dbBean.getElementValue(i,"eposkd"); 
				epovds[i]=dbBean.getElementValue(i,"epovds"); 
				eposum[i]=dbBean.getElementValue(i,"eposum"); 
				epobum[i]=dbBean.getElementValue(i,"epobum"); 
				epoupc[i]=dbBean.getElementValue(i,"epoupc"); 
				epocas[i]=dbBean.getElementValue(i,"epocas"); 
				epoqty[i]=dbBean.getElementValue(i,"epoqty"); 
				epomgn[i]=dbBean.getElementValue(i,"epomgn"); 
				epobts[i]=dbBean.getElementValue(i,"epobts"); 
				eponct[i]=dbBean.getElementValue(i,"eponct"); 
				eposks[i]=dbBean.getElementValue(i,"eposks"); 
				
				if (epostr[i]==null){epostr[i]="";}
				if (epovnd[i]==null){epovnd[i]="";}
				if (epossq[i]==null){epossq[i]="";}
				if (eposku[i]==null){eposku[i]="";}
				if (eposkd[i]==null){eposkd[i]="";}
				if (epovds[i]==null){epovds[i]="";}
				if (eposum[i]==null){eposum[i]="";}
				if (epobum[i]==null){epobum[i]="";}
				if (epoupc[i]==null){epoupc[i]="";}
				if (epocas[i]==null){epocas[i]="";}
				if (epoqty[i]==null){epoqty[i]="";}
				if (epomgn[i]==null){epomgn[i]="";}
				if (epobts[i]==null){epobts[i]="";}
				if (eponct[i]==null){eponct[i]="";}
				if (eposks[i]==null){eposks[i]="";}

			countcas=countcas+Double.parseDouble(epocas[i]);
			countqty=countqty+Double.parseDouble(epoqty[i]);
			countnct=countnct+(Double.parseDouble(eponct[i])*Double.parseDouble(epoqty[i]));


			epocas[i]=DecimalTools.format(epocas[i],"###,##0.00");
			epoqty[i]=DecimalTools.format(epoqty[i],"###,##0.00");
			eponct[i]=DecimalTools.format(eponct[i],"##,###,##0.0000");
 
			
			
			
//			epostr[i]=String.valueOf(i);
//			epovnd[i]=String.valueOf(i);
//			epossq[i]=String.valueOf(i);
//			eposku[i]=String.valueOf(i);
//			eposkd[i]=String.valueOf(i);
//			epovds[i]=String.valueOf(i);
//			eposum[i]=String.valueOf(i);
//			epobum[i]=String.valueOf(i);
//			epoupc[i]=String.valueOf(i);
//			epocas[i]=String.valueOf(i);
//			epoqty[i]=String.valueOf(i);
//			epomgn[i]=String.valueOf(i);
//			epobts[i]=String.valueOf(i);
//			eponct[i]=String.valueOf(i);
//			eposks[i]=String.valueOf(i);
		}

		countcas1=DecimalTools.format(countcas,"###,##0.00");
		countqty1=DecimalTools.format(countqty,"###,##0.00");
		countnct1=DecimalTools.format(countnct,"###,##0.0000");
		
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
		PropertyUtils.setSimpleProperty(form, "countcas1" ,countcas1);
		PropertyUtils.setSimpleProperty(form, "countqty1" ,countqty1);
		PropertyUtils.setSimpleProperty(form, "countnct1" ,countnct1);




		strsql="select * from ORDERR where eponum='"+eponum+"'  ";//"epossq";   
		try{
			dbBean.executeSelect(strsql);
		} catch (Exception e) {
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Datebase.readdb"));
			return "0";
		}
		
		PropertyUtils.setSimpleProperty(form, "send" ,String.valueOf(dbBean.getRowCount()));
		return "1";
	}

	public void resetFormbeen(ActionForm form,HttpServletRequest request) throws Exception{
 
	}

	/* (non-Javadoc)
	 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return executeme(mapping,form,request,response);
	}

	
	
	
}

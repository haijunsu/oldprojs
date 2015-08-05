/*
 * @(#)homewordErthdrAction.java  2004-2-17
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.rtv ;
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
import com.idn.sql.DynaSqlBean;
import com.idn.sql.SQLBean;
import com.idn.util.DecimalTools;

//import commsearch.CommTools;
import commsearch.util.CommActionLog;
import commsearch.util.CommDate;


/**
 * <P>菜单管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class homeworldErthdrAction extends SecureAction {

	ActionErrors errors = new ActionErrors();

	/**
	 * 构造函数
	 */
	public homeworldErthdrAction() {
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
				uname,hs.getId(),"Erthdr","B","LYC0000000",0);

			if(uname==null){
				errors.add("errormessage",new ActionError("NoName"));
				saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Erthdr","E","LYC5555555",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			if(uname.trim().equals("")){
				errors.add("errormessage",new ActionError("NoName"));
				saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Erthdr","E","LYC6666666",l_end - l_begin);
				return(mapping.findForward("success"));
			}

			try{
			String selectwhere= (String)request.getParameter("selectwhere");
			String queryid= (String)request.getParameter("queryid");

			if(selectwhere==null)
				selectwhere="";
			if(selectwhere.equals("")){
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Erthdr","E","LYC3333333",l_end - l_begin);
				return(mapping.findForward("err"));
			}
			
			PropertyUtils.setSimpleProperty(form,"queryid",queryid);
			PropertyUtils.setSimpleProperty(form,"selectwhere",selectwhere);
		//	PropertyUtils.setSimpleProperty(form,"selectwhere","");
			
			String flag=(String)PropertyUtils.getSimpleProperty(form,"flag");

			SQLBean sis=new SQLBean();
			
			if(flag.equals("change")){
				selectwhere=(String)PropertyUtils.getSimpleProperty(form,"selectwhere");

				String strsql[]=new String[1];
				
				strsql[0]="update erthdr set ertsts='Z' where "+ selectwhere;
				//int retrunint1=sis.executeSQL(strsql1);

				DynaSqlBean dybBean = new DynaSqlBean();
				dybBean.setSql(strsql);
				dybBean.executeBatch();
				
				PropertyUtils.setSimpleProperty(form,"flag","");
			}
			
			setFormbeen(form,request,selectwhere,uname);
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"Erthdr","E","LYC8888888",l_end - l_begin);
			return(mapping.findForward("success"));
		} catch (Exception e){
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Datebase.formbean"));
			saveErrors(request, errors);
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"Erthdr","C","LYC9999999",l_end - l_begin);
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
				
				String strsql="select * from ERTHDR where "+where+"  and ertvnd='"+uname+"'";
//				try{
					dbBean.executeSelect(strsql);
//				} catch (Exception e) {
//					e.printStackTrace();
//					errors.add("errormessage",new ActionError("Datebase.readdb"));
//					return "0";
//				}
				
		CommDate cd=new CommDate();
		
		String ertsct=dbBean.getElementValue(0,"ertsct"  ).trim() ;
		String ertvct=dbBean.getElementValue(0,"ertvct"  ).trim() ;
		String ertvzp=dbBean.getElementValue(0,"ertvzp"  ).trim() ;
		
		String ertstr=dbBean.getElementValue(0,"ertstr"  ).trim() ;
		String ertvnd=dbBean.getElementValue(0,"ertvnd"  ).trim() ;
		String ertnum=dbBean.getElementValue(0,"ertnum"  ).trim() ;
		String ertstn=dbBean.getElementValue(0,"ertstn"  ).trim() ;
		String ertnam=dbBean.getElementValue(0,"ertnam"  ).trim() ;
		String ertvra=dbBean.getElementValue(0,"ertvra"  ).trim() ;
		String ertsa1=dbBean.getElementValue(0,"ertsa1"  ).trim() ;
		String ertad1=dbBean.getElementValue(0,"ertad1"  ).trim() ;
		String ertaut=dbBean.getElementValue(0,"ertaut"  ).trim() ;
		String ertsa2=dbBean.getElementValue(0,"ertsa2"  ).trim() ;
		String ertad2=dbBean.getElementValue(0,"ertad2"  ).trim() ;
		String ertvwd=dbBean.getElementValue(0,"ertvwd"  ).trim() ;
		String ertsa3=dbBean.getElementValue(0,"ertsa3"  ).trim() ;
		String ertad3=dbBean.getElementValue(0,"ertad3"  ).trim() ;
		String ertsdt=cd.dateFormat(dbBean.getElementValue(0,"ertsdt"  ),"L");
		String ertszp=dbBean.getElementValue(0,"ertszp"  ).trim() ;
		String ertcnt=dbBean.getElementValue(0,"ertcnt"  ).trim() ;
		String ertscn=dbBean.getElementValue(0,"ertscn"  ).trim() ;
		String ertsmg=dbBean.getElementValue(0,"ertsmg"  ).trim() ;
		String ertacn=dbBean.getElementValue(0,"ertacn"  ).trim() ;
		String ertsph=dbBean.getElementValue(0,"ertsph"  ).trim() ;
		String ertphn=dbBean.getElementValue(0,"ertphn"  ).trim() ;
		String ertins=dbBean.getElementValue(0,"ertins"  ).trim() ;
		String ertrsn=dbBean.getElementValue(0,"ertrsn"  ).trim() ;
		String ertshb=dbBean.getElementValue(0,"ertshb"  ).trim() ;
		String ertcar=dbBean.getElementValue(0,"ertcar"  ).trim() ;
		String ertcom=dbBean.getElementValue(0,"ertcom"  ).trim() ;
		String ertreq=dbBean.getElementValue(0,"ertreq"  ).trim() ;
		String ertcdt=cd.dateFormat(dbBean.getElementValue(0,"ertcdt"  ),"L");
		//String ertctm=timeFormat(dbBean.getElementValue(0,"ertctm").trim(),"L");
		String ertctm=cd.timeFormat(dbBean.getElementValue(0,"ertctm").trim() ,"L");
		
		String ertseq=dbBean.getElementValue(0,"ertseq"  ).trim() ;
		String ertsts=dbBean.getElementValue(0,"ertsts"  ).trim() ;

		String ertfax=dbBean.getElementValue(0,"ertfax"  ).trim() ;
		String ertsfx=dbBean.getElementValue(0,"ertsfx"  ).trim() ;
		
		PropertyUtils.setSimpleProperty(form, "ertsct" ,ertsct );   
		PropertyUtils.setSimpleProperty(form, "ertvct" ,ertvct );   
		PropertyUtils.setSimpleProperty(form, "ertvzp" ,ertvzp ); 
		
		PropertyUtils.setSimpleProperty(form, "ertstr" ,ertstr );   
		PropertyUtils.setSimpleProperty(form, "ertvnd" ,ertvnd );   
		PropertyUtils.setSimpleProperty(form, "ertnum" ,ertnum );   
		PropertyUtils.setSimpleProperty(form, "ertstn" ,ertstn );   
		PropertyUtils.setSimpleProperty(form, "ertnam" ,ertnam );   
		PropertyUtils.setSimpleProperty(form, "ertvra" ,ertvra );   
		PropertyUtils.setSimpleProperty(form, "ertsa1" ,ertsa1 );   
		PropertyUtils.setSimpleProperty(form, "ertad1" ,ertad1 );   
		PropertyUtils.setSimpleProperty(form, "ertaut" ,ertaut );   
		PropertyUtils.setSimpleProperty(form, "ertsa2" ,ertsa2 );   
		PropertyUtils.setSimpleProperty(form, "ertad2" ,ertad2 );   
		PropertyUtils.setSimpleProperty(form, "ertvwd" ,ertvwd );   
		PropertyUtils.setSimpleProperty(form, "ertsa3" ,ertsa3 );   
		PropertyUtils.setSimpleProperty(form, "ertad3" ,ertad3 );   
		PropertyUtils.setSimpleProperty(form, "ertsdt" ,ertsdt );   
		PropertyUtils.setSimpleProperty(form, "ertszp" ,ertszp );   
		PropertyUtils.setSimpleProperty(form, "ertcnt" ,ertcnt );   
		PropertyUtils.setSimpleProperty(form, "ertscn" ,ertscn );   
		PropertyUtils.setSimpleProperty(form, "ertsmg" ,ertsmg );   
		PropertyUtils.setSimpleProperty(form, "ertacn" ,ertacn );   
		PropertyUtils.setSimpleProperty(form, "ertsph" ,ertsph );   
		PropertyUtils.setSimpleProperty(form, "ertphn" ,ertphn );   
		PropertyUtils.setSimpleProperty(form, "ertins" ,ertins );   
		PropertyUtils.setSimpleProperty(form, "ertrsn" ,ertrsn );   
		PropertyUtils.setSimpleProperty(form, "ertshb" ,ertshb );   
		PropertyUtils.setSimpleProperty(form, "ertcar" ,ertcar );   
		PropertyUtils.setSimpleProperty(form, "ertcom" ,ertcom );   
		PropertyUtils.setSimpleProperty(form, "ertreq" ,ertreq );   
		PropertyUtils.setSimpleProperty(form, "ertcdt" ,ertcdt );   
		PropertyUtils.setSimpleProperty(form, "ertctm" ,ertctm );   
		PropertyUtils.setSimpleProperty(form, "ertseq" ,ertseq );   
		PropertyUtils.setSimpleProperty(form, "ertsts" ,ertsts ); 
  
		PropertyUtils.setSimpleProperty(form, "ertfax" ,ertfax );   
		PropertyUtils.setSimpleProperty(form, "ertsfx" ,ertsfx ); 
   //写日志
   CommActionLog  cal= new CommActionLog();
			
   cal.setAct_user(uname);
   cal.setAct_from("homeworldErthdr");
   cal.setAct_do("SEA");
   cal.setAct_key(ertnum);
   cal.setAct_table("ERTHDR");
   cal.setAct_ip(request.getRemoteAddr());
   cal.setAct_memo("查询返厂单编号为" + ertnum);
   cal.setAct_me("");
	cal.setActionLog();
 

	String[] ertstsmx =null;	
	String[] ertsku =null;	
	String[] ertskd =null;	
	String[] ertupc =null;	
	String[] ertmfg =null;	
	String[] ertbum =null;	
	String[] ertsum =null;	
	String[] ertcst =null;	
	String[] ertshq =null;	

		int count =0;

		strsql="select * from ERTDTL where "+where+"  ";//and ertvnd='"+uname+"'";
//		try{
			dbBean.executeSelect(strsql);
//		} catch (Exception e) {
//			e.printStackTrace();
//			errors.add("errormessage",new ActionError("Datebase.readdb"));
//			return "0";
//		}
		count=dbBean.getRowCount()+1;

		ertstsmx=new String[count];
		ertsku=new String[count];
		ertskd=new String[count];
		ertupc=new String[count];
		ertmfg=new String[count];
		ertbum=new String[count];
		ertsum=new String[count];
		ertcst=new String[count];
		ertshq=new String[count];
		String[] ertvdn =new String[count];
		
		double countcst=0;
		double countshq=0;
		 
		for(int i=0;i<count-1;i++){

			ertstsmx[i]=dbBean.getElementValue(i,"ertsts"  );
			ertsku[i]=dbBean.getElementValue(i,"ertsku"  );
			ertskd[i]=dbBean.getElementValue(i,"ertskd"  );
			ertupc[i]=dbBean.getElementValue(i,"ertupc"  );
			ertmfg[i]=dbBean.getElementValue(i,"ertmfg"  );
			ertbum[i]=dbBean.getElementValue(i,"ertbum"  );
			ertsum[i]=dbBean.getElementValue(i,"ertsum"  );
			ertcst[i]=dbBean.getElementValue(i,"ertcst"  );
			ertshq[i]=dbBean.getElementValue(i,"ertshq"  );
			ertvdn[i]=dbBean.getElementValue(i,"ertvdn"  );
			//ertvdn[i]=dbBean.getElementValue(i,"" );
			countcst=countcst+(Double.parseDouble(ertcst[i])*Double.parseDouble(ertshq[i]));
			countshq=countshq+Double.parseDouble(ertshq[i]);


			ertcst[i]=DecimalTools.format(ertcst[i],"###,##0.0000");
			ertshq[i]=DecimalTools.format(ertshq[i],"###,##0.00");
 
		}

		ertcst[count-1]=DecimalTools.format(countcst,"###,##0.0000");
		ertshq[count-1]=DecimalTools.format(countshq,"###,##0.00");
		ertsku[count-1]="sumcount";

		
		PropertyUtils.setSimpleProperty(form, "ertvdn" ,ertvdn );   
		PropertyUtils.setSimpleProperty(form, "ertstsmx" ,ertstsmx );   
		PropertyUtils.setSimpleProperty(form, "ertsku"   ,ertsku );   
		PropertyUtils.setSimpleProperty(form, "ertskd"   ,ertskd );   
		PropertyUtils.setSimpleProperty(form, "ertupc"   ,ertupc );   
		PropertyUtils.setSimpleProperty(form, "ertmfg"   ,ertmfg );   
		PropertyUtils.setSimpleProperty(form, "ertbum"   ,ertbum );   
		PropertyUtils.setSimpleProperty(form, "ertsum"   ,ertsum );   
		PropertyUtils.setSimpleProperty(form, "ertcst"   ,ertcst );   
		PropertyUtils.setSimpleProperty(form, "ertshq"   ,ertshq );  

		return "1";
	}

	public void resetFormbeen(ActionForm form,HttpServletRequest request) throws Exception{
 
	}

	/* (non-Javadoc)
	 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		return  executeme( mapping,form,request,response);
	}
	
	
	
}

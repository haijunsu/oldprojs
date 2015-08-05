/*
 * @(#)homeworldPrintErthdrAction.java  2004-4-23
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
	public class homeworldPrintErthdrAction extends SecureAction {

		ActionErrors errors = new ActionErrors();

		/**
		 * 构造函数
		 */
		public homeworldPrintErthdrAction() {
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
					uname,hs.getId(),"PrintErthdr","B","LYC0000000",0);
						
				if(uname==null){
					errors.add("errormessage",new ActionError("NoName"));
					saveErrors(request, errors);
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"PrintErthdr","E","LYC5555555",l_end - l_begin);
					return(mapping.findForward("success"));
				}
				if(uname.trim().equals("")){
					errors.add("errormessage",new ActionError("NoName"));
					saveErrors(request, errors);
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"PrintErthdr","E","LYC6666666",l_end - l_begin);
					return(mapping.findForward("success"));
				}

		try{

				String selectwhere= (String)request.getParameter("selectwhere");
				String stemp=selectwhere;
				
				if(stemp==null)
					stemp="";
				if(stemp.equals("")){
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"PrintErthdr","E","LYC3333333",l_end - l_begin);
					return(mapping.findForward("err"));
				}
				
				String queryid= (String)request.getParameter("queryid");
			
				PropertyUtils.setSimpleProperty(form,"queryid",queryid);
				PropertyUtils.setSimpleProperty(form,"selectwhere",selectwhere);

				PropertyUtils.setSimpleProperty(form, "pagerow" ,"40");
				PropertyUtils.setSimpleProperty(form, "onerow" ,"22");
				
				setFormbeen(form,request,selectwhere,uname);

//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"PrintErthdr","E","LYC8888888",l_end - l_begin);
				return(mapping.findForward("success"));
			} catch (Exception e){
				e.printStackTrace();
				errors.add("errormessage",new ActionError("Datebase.formbean"));
				saveErrors(request, errors);
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"PrintErthdr","C","LYC9999999",l_end - l_begin);
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
			
					String strsql="select * from ERTHDR where "+where+" ";   
					try{
						dbBean.executeSelect(strsql);
					} catch (Exception e) {
						e.printStackTrace();
						errors.add("errormessage",new ActionError("Datebase.readdb"));
						return "0";
					}
				
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
			String ertctm=cd.timeFormat(dbBean.getElementValue(0,"ertctm").trim() ,"L");
			String ertseq=dbBean.getElementValue(0,"ertseq"  ).trim() ;
			String ertsts=dbBean.getElementValue(0,"ertsts"  ).trim() ;

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
	   //写日志
/*
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
 */

		String[] ertstsmx =null;	
		String[] ertsku =null;	
		String[] ertskd =null;	
		String[] ertupc =null;	
		String[] ertmfg =null;	
		String[] ertbum =null;	
		String[] ertsum =null;	
		String[] ertcst =null;	
		String[] ertshq =null;	
		String[] ertvdn =null;
		
			int count =0;

			strsql="select * from ERTDTL where "+where+" ";   
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
			String endnew="flase";
			/*
			 改多页 
			 */
			String[] oertstsmx =null;	
			String[] oertsku =null;	
			String[] oertskd =null;	
			String[] oertupc =null;	
			String[] oertmfg =null;	
			String[] oertbum =null;	
			String[] oertsum =null;	
			String[] oertcst =null;	
			String[] oertshq =null;	
			String[] oertvdn =null;
			int onerow=Integer.parseInt((String)PropertyUtils.getSimpleProperty(form,"onerow"));
			
			String onlyone="flase";
			
			//全部都只有一页
			if(count <= onerow - 5 ){
				onlyone="true";
				endnew="flase";
			}
			
//			只有尾部在第二页
			if((count > onerow - 5) && ( count <= onerow)){
				onlyone="true";
				endnew="true";
			}

			double countcst=0;
			double countshq=0;
			double sumcst=0;
			double sumshq=0;
			
			
//			多页的情况
			if(count > onerow ){
				count=count-onerow+1;
				onlyone="flase";

				oertstsmx =new String[onerow];	
				oertsku =new String[onerow];	
				oertskd =new String[onerow];	
				oertupc =new String[onerow];
				oertmfg =new String[onerow];
				oertbum =new String[onerow];
				oertsum =new String[onerow];
				oertcst =new String[onerow];
				oertshq =new String[onerow];
				oertvdn =new String[onerow];
				
				for(int i=0;i<onerow-1;i++){			
					oertstsmx[i]=dbBean.getElementValue(i,"ertsts"  );
					oertsku[i]=dbBean.getElementValue(i,"ertsku"  );
					oertskd[i]=dbBean.getElementValue(i,"ertskd"  );
					oertupc[i]=dbBean.getElementValue(i,"ertupc"  );
					oertmfg[i]=dbBean.getElementValue(i,"ertmfg"  );
					oertbum[i]=dbBean.getElementValue(i,"ertbum"  );
					oertsum[i]=dbBean.getElementValue(i,"ertsum"  );
					oertcst[i]=dbBean.getElementValue(i,"ertcst"  );
					oertshq[i]=dbBean.getElementValue(i,"ertshq"  );
					oertvdn[i]=dbBean.getElementValue(i,"ertvdn"  );
					
					countcst=countcst+(Double.parseDouble(oertcst[i])*Double.parseDouble(oertshq[i]));
					countshq=countshq+Double.parseDouble(oertshq[i]);


					sumcst=sumcst+(Double.parseDouble(oertcst[i])*Double.parseDouble(oertshq[i]));
					sumshq=sumshq+Double.parseDouble(oertshq[i]);

					oertcst[i]=DecimalTools.format(oertcst[i],"###,##0.0000");
					oertshq[i]=DecimalTools.format(oertshq[i],"###,##0.00");
				}
					
				oertstsmx[onerow-1]="";
				oertsku[onerow-1]="minsum";
				oertskd[onerow-1]="";
				oertupc[onerow-1]="";
				oertmfg[onerow-1]="";
				oertbum[onerow-1]="";
				oertsum[onerow-1]="";
				oertvdn[onerow-1]="";
					
				oertcst[onerow-1]=DecimalTools.format(String.valueOf(sumcst),"###,##0.0000");
				oertshq[onerow-1]=DecimalTools.format(String.valueOf(sumshq),"###,##0.00");
					
				sumcst=0;
				sumshq=0;
				  
				PropertyUtils.setSimpleProperty(form, "oertvdn"   ,oertvdn );   
				PropertyUtils.setSimpleProperty(form, "oertstsmx" ,oertstsmx );   
				PropertyUtils.setSimpleProperty(form, "oertsku"   ,oertsku );   
				PropertyUtils.setSimpleProperty(form, "oertskd"   ,oertskd );   
				PropertyUtils.setSimpleProperty(form, "oertupc"   ,oertupc );   
				PropertyUtils.setSimpleProperty(form, "oertmfg"   ,oertmfg );   
				PropertyUtils.setSimpleProperty(form, "oertbum"   ,oertbum );   
				PropertyUtils.setSimpleProperty(form, "oertsum"   ,oertsum );   
				PropertyUtils.setSimpleProperty(form, "oertcst"   ,oertcst );   
				PropertyUtils.setSimpleProperty(form, "oertshq"   ,oertshq );
			}
			
			 
			/*
			 改多页 
			 */
			 
			 

			//计算分页显示的最大页数
			int maxpage=0;
			
			for(int i=1; i<50; i++){
				maxpage=i;
				if((count+i)<=(i* pagerow)){
					break;
				}
			}

			int countend=0;
			countend=count+maxpage-((maxpage-1)*pagerow);
			
			if(!onlyone.equals("true")){
				if(pagerow-countend<5){
					endnew="true";
				}
			}

			int pagecount=0;
			
			if(endnew.equals("true")){
				pagecount=maxpage+1;
			}else{
				pagecount=maxpage;
			}
			
			
			
			
			
			ertstsmx=new String[count+maxpage];
			ertsku=new String[count+maxpage];
			ertskd=new String[count+maxpage];
			ertupc=new String[count+maxpage];
			ertmfg=new String[count+maxpage];
			ertbum=new String[count+maxpage];
			ertsum=new String[count+maxpage];
			ertcst=new String[count+maxpage];
			ertshq=new String[count+maxpage];
			ertvdn=new String[count+maxpage];
			
		 
			int jk=1;
			int j=0;
			if(onlyone.equals("flase")){
				j=onerow-1;
			}

			for(int i=0;i<count+maxpage;i++){
				if((i==jk*pagerow-1)||(i==count+maxpage-1)){
					//epostr[i]="小计--        "; 
					
					ertstsmx[i]="";
					ertsku[i]="minsum";
					ertskd[i]="";
					ertupc[i]="";
					ertmfg[i]="";
					ertbum[i]="";
					ertsum[i]="";
					ertvdn[i]="";
					
					ertcst[i]=DecimalTools.format(String.valueOf(sumcst),"###,##0.00");
					ertshq[i]=DecimalTools.format(String.valueOf(sumshq),"###,##0.00");
					
					sumcst=0;
					sumshq=0;
					jk++;					
				}else{
					
					ertstsmx[i]=dbBean.getElementValue(j,"ertsts"  );
					ertsku[i]=dbBean.getElementValue(j,"ertsku"  );
					ertskd[i]=dbBean.getElementValue(j,"ertskd"  );
					ertupc[i]=dbBean.getElementValue(j,"ertupc"  );
					ertmfg[i]=dbBean.getElementValue(j,"ertmfg"  );
					ertbum[i]=dbBean.getElementValue(j,"ertbum"  );
					ertsum[i]=dbBean.getElementValue(j,"ertsum"  );
					ertcst[i]=dbBean.getElementValue(j,"ertcst"  );
					ertshq[i]=dbBean.getElementValue(j,"ertshq"  );
					ertvdn[i]=dbBean.getElementValue(j,"ertvdn"  );
					
					countcst=countcst+(Double.parseDouble(ertcst[i])*Double.parseDouble(ertshq[i]));
					countshq=countshq+Double.parseDouble(ertshq[i]);


					sumcst=sumcst+(Double.parseDouble(ertcst[i])*Double.parseDouble(ertshq[i]));
					sumshq=sumshq+Double.parseDouble(ertshq[i]);

					ertcst[i]=DecimalTools.format(ertcst[i],"###,##0.00");
					ertshq[i]=DecimalTools.format(ertshq[i],"###,##0.00");

					j++;
				}
			}

			PropertyUtils.setSimpleProperty(form, "endnew" ,endnew);   
			
			PropertyUtils.setSimpleProperty(form, "sumcst" ,DecimalTools.format(String.valueOf(countcst),"###,###,##0.00"));   
			PropertyUtils.setSimpleProperty(form, "sumshq" ,DecimalTools.format(String.valueOf(countshq),"###,###,##0.00"));
			   
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
			
			PropertyUtils.setSimpleProperty(form, "onlyone"   ,onlyone ); 

			return "1";
		}

		/* (non-Javadoc)
		 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 */
		public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			return  executeme( mapping,form,request,response);
		}
	
	
	
	}

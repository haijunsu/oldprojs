/*
 * @(#)homeworldEipdtlAction.java  2004-6-29
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
//import com.idn.log.LogWrapper;
import com.idn.property.CodesManager;
		import java.io.IOException;
import java.util.Date;
		import java.util.Vector;

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
import com.idn.util.CommonTools;
		import com.idn.util.DecimalTools;
import com.idn.util.FormatDate;

//		  import commsearch.CommTools;
//		import commsearch.util.CommActionLog;
		import commsearch.util.CommDate;
		
import java.util.*;

		/**
		 * <P>菜单管理</P>
		 * 
		 * @version 0.1
		 * @author 李永初
		 */
		public class homeworldEipdtlAction extends SecureAction {
			ActionErrors errors = new ActionErrors();

			/**
			 * 构造函数
			 */
			public homeworldEipdtlAction () {
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

					//private static LogWrapper log= new LogWrapper(homeworldEipdtlAction.class);
					
					HttpSession hs = request.getSession();
					String uname=(String)hs.getAttribute("userid");
					uname = uname.trim();

					//TODO 临时用来监测用
					long l_begin,l_end;
					l_begin = System.currentTimeMillis();		
					commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Eipdtl","B","LYC0000000",0);
						
					if (uname==null){
						errors.add("errormessage",new ActionError("NoName"));
						saveErrors(request, errors);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Eipdtl","E","LYC5555555",l_end - l_begin);
						return(mapping.findForward("success"));
					}
					if(uname.trim().equals("")){
						errors.add("errormessage",new ActionError("NoName"));
						saveErrors(request, errors);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Eipdtl","E","LYC6666666",l_end - l_begin);
						return(mapping.findForward("success"));
					}
					
				try{		
					String selectwhere= (String)request.getParameter("selectwhere");

					if(selectwhere==null)
						selectwhere="";
					if(selectwhere.equals("")){	
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Eipdtl","E","LYC3333333",l_end - l_begin);
						return(mapping.findForward("err"));
					}
					
					PropertyUtils.setSimpleProperty(form,"selectwhere",selectwhere);
					PropertyUtils.setSimpleProperty(form, "msgbox"   ,"0"  );
					
					String flag= (String) PropertyUtils.getSimpleProperty(form,"flag");
					if(!flag.equals("commit")){
						setFormbeen(form,request,selectwhere,uname);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Eipdtl","E","LYC8888881",l_end - l_begin);
						return(mapping.findForward("success"));	
					}
					
					String[] chack=(String[]) PropertyUtils.getSimpleProperty(form,"chacked");
					
					String[] eipstr=(String[]) PropertyUtils.getSimpleProperty(form,"eipstr");
					String[] eipnum=(String[]) PropertyUtils.getSimpleProperty(form,"eipnum");
					String[] eiptyp=(String[]) PropertyUtils.getSimpleProperty(form,"eiptyp");
					String[] eipflg=(String[]) PropertyUtils.getSimpleProperty(form,"eipflg");
					
					String[] eipflgxx=new String[eiptyp.length];
					
					//String[] eipflgxx=n;
						
					System.arraycopy(eipflg,0,eipflgxx,0,eipflg.length);
					
					String[] eivtrk=(String[]) PropertyUtils.getSimpleProperty(form,"eivtrk");
					String[] eivivn=(String[]) PropertyUtils.getSimpleProperty(form,"eivivn");
					String[] eivamt=(String[]) PropertyUtils.getSimpleProperty(form,"eivamt");
					String[] eivtax=(String[]) PropertyUtils.getSimpleProperty(form,"eivtax");
					String[] eivdat=(String[]) PropertyUtils.getSimpleProperty(form,"eivdat");
					
					
					
					String[] state=(String[]) PropertyUtils.getSimpleProperty(form,"rowstate");
					
					Vector vecTemp=new Vector();
					String temp="";
					if(chack==null){
					
						errors.add("errormessage",new ActionError("Database.error"));
						saveErrors(request, errors);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Eipdtl","E","LYC8888882",l_end - l_begin);
						return(mapping.findForward("success"));
					}

					String strsql="";
					String ser=getNo();

					ser=String.valueOf(Long.parseLong(ser)+1);
					ser="0000000000"+ser;
					ser=ser.substring(ser.length()-10);
					Date dd=new Date();
					String sql="";
					String now=FormatDate.format(dd,"yyMMdd");
					int mak=0;
					for(int i=0;i<chack.length;i++){
						temp="delete from eivdtl where eivstr='"+eipstr[Integer.parseInt(chack[i])]+"' and eivnum='"+eipnum[Integer.parseInt(chack[i])]+"' and eivtyp='"+eiptyp[Integer.parseInt(chack[i])]+"'";
						vecTemp.addElement(temp);	
			
						for(int j=0;j<state.length;j++){
							if(state[j].equals("4")){
								if(j==0){
									eivamt[j]=CommonTools.stringReplace(eivamt[j],",","");
									eivamt[j]=DecimalTools.format(eivamt[j],"#####0.00");  
									temp="insert into eivdtl (eivtrk,eivivn,eividt,eivstr,eivnum,eivamt,eivdat,eivtyp,eivtax,eivser,eivvdr) values('"+eivtrk[j]+"','"+eivivn[j]+"','"+now+"','"+eipstr[Integer.parseInt(chack[i])]+"','"+eipnum[Integer.parseInt(chack[i])]+"','"+eivamt[j]+"','"+now+"','"+eiptyp[Integer.parseInt(chack[i])]+"','"+eivtax[j]+"','"+ser+"','"+uname+"')";
									vecTemp.addElement(temp);
								}
							}
						}
						eipflgxx[Integer.parseInt(chack[i])]="2";
						temp="update eipdtl set eipflg='2' where eipstr='"+eipstr[Integer.parseInt(chack[i])]+"' and eipnum='"+eipnum[Integer.parseInt(chack[i])]+"' and eiptyp='"+eiptyp[Integer.parseInt(chack[i])]+"'";
						vecTemp.addElement(temp);
					}

					
					DataBean db = new DataBean();
			

					chack=new String[0];
					PropertyUtils.setSimpleProperty(form, "chacked"   ,chack  );
					String eiv2bkdate="";
					eiv2bkdate = FormatDate.format(dd,"yyyy-MM-dd");
					for(int j=0;j<state.length;j++){
						if(state[j].equals("4")){
							mak=1;
							eivamt[j]=CommonTools.stringReplace(eivamt[j],",","");
							eivdat[j]=CommonTools.stringReplace(eivdat[j],"-","").trim();
							if(!eivdat[j].equals(""))
								eivdat[j]=eivdat[j].substring(2);
							else
								eivdat[j]=FormatDate.format(dd,"yyMMdd");
							sql="select * from eivdtl2 where eivtrk='"+eivtrk[j]+"' and eivivn='"+eivivn[j]+"'";
							db.executeSelect(sql);
							if(db.getRowCount()!=0){
								errors.add("errormessage",new ActionError("eivdtl2.repeat"));
								saveErrors(request, errors);
								return(mapping.findForward("success"));
							}
							eivamt[j]=DecimalTools.format(eivamt[j],"#####0.00");
							temp="insert into eivdtl2 (eivtrk,eivivn,eivseq,eivamt,eivtax,eivser,eivdat,eivvdr) values('"+eivtrk[j]+"','"+eivivn[j]+"','"+String.valueOf(j+1)+"','"+eivamt[j]+"','"+eivtax[j]+"','"+ser+"','"+eivdat[j]+"','"+uname+"')";
							vecTemp.addElement(temp);
							temp="insert into eivdtl3 (eivtrk,eivivn,eivser,eivdat,eivvdr) values("+eivtrk[j]+","+eivivn[j]+",'"+ser+"','"+eivdat[j]+"','"+uname+"')";
							vecTemp.addElement(temp);

						}
					}
		

					if(mak==0){
						errors.add("errormessage",new ActionError("Database.error"));
						saveErrors(request, errors);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Eipdtl","E","LYC8888883",l_end - l_begin);
						return(mapping.findForward("success"));
					}
					String[] strTemp = (String[]) vecTemp.toArray(new String[0]);
					DynaSqlBean dbBean = new DynaSqlBean();
					
					dbBean.setSql(strTemp);				   
					dbBean.executeBatch();
		
					PropertyUtils.setSimpleProperty(form, "eipflg"   ,eipflgxx  );
					PropertyUtils.setSimpleProperty(form, "msgbox"   ,"1"  );
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Eipdtl","E","LYC8888888",l_end - l_begin);
					return(mapping.findForward("success"));
				} catch (Exception e){
					e.printStackTrace();
					errors.add("errormessage",new ActionError("Datebase.formbean"));
					saveErrors(request, errors);
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Eipdtl","C","LYC9999999",l_end - l_begin);
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
		
		   //写日志
//		   CommActionLog  cal= new CommActionLog();
//			
//		   cal.setAct_user(uname);
//		   cal.setAct_from("homeworldErvthdr");
//		   cal.setAct_do("SEA");
//		   cal.setAct_key(ervnum);
//		   cal.setAct_table("ERVHDR");
//		   cal.setAct_ip(request.getRemoteAddr());
//		   cal.setAct_memo("查询收货单编号为" + ervnum);
//		   cal.setAct_me("");
//		cal.setActionLog();
				String[] seq=null;		//序号	
				String[] eipstr=null;	//商店号	
				String[] eipnum=null;	//单号	
				String[] eipamt=null;	//金额	
				String[] eipdta=null;	//交易日期
				String[] eiptyp=null;	//交易类型
				String[] eipvdr=null;	//供货商号
				String[] eipyta=null;	//应付日期
				String[] eipflg=null;	//标志	
				String[] show=null;
		
				int count =0;

				DataBean dbBean = new DataBean();
				String strsql="select * from eipdtl where eipvdr='"+uname+"' order by EIPSTR,EIPNUM,EIPTYP";
				
				String showhide=(String) PropertyUtils.getSimpleProperty(form,"showhide");
				if (showhide.equals("0")){
					strsql="select * from eipdtl where eipflg='1' and  eipvdr='"+uname+"' order by EIPSTR,EIPNUM,EIPTYP";
					PropertyUtils.setSimpleProperty(form, "showhide"   ,"1");
				}
				
				//strsql="select * from eipdtl  ";
//				try{
					dbBean.executeSelect(strsql);
//				} catch (Exception e) {
//					e.printStackTrace();
//					errors.add("errormessage",new ActionError("Datebase.readdb"));
//					return "0";
//				}
				
				String temp="";
				String[] db=new String[dbBean.getRowCount()];
				for(int j=0;j<dbBean.getRowCount();j++){
					temp=dbBean.getElementValue(j,"EIPSTR").trim();
					temp=temp+dbBean.getElementValue(j,"EIPNUM").trim();
					temp=temp+dbBean.getElementValue(j,"EIPTYP").trim();
					db[j]=temp;
				}
				
				String[] row=getArray(where, db);
				
				//count=dbBean.getRowCount();
				count=row.length;
				
				seq=new String[count];
				eipstr=new String[count];	//商店号	
				eipnum=new String[count];	//单号	
				eipamt=new String[count];	//金额	
				eipdta=new String[count];	//交易日期
				eiptyp=new String[count];	//交易类型
				eipvdr=new String[count];	//供货商号
				eipyta=new String[count];	//应付日期
				eipflg=new String[count];	//标志
				show=new String[count];	
				CommDate cd=new CommDate();
						
				String selectwhere= "";
				int i=0;
								
				for(int j=0;j<count;j++){
					seq[j]=String.valueOf(j+1);
					i=Integer.parseInt(row[j]);
					
					eipstr[j]=dbBean.getElementValue(i,"eipstr").trim() ;
					eipnum[j]=dbBean.getElementValue(i,"eipnum").trim() ;
					eipamt[j]=DecimalTools.format(dbBean.getElementValue(i,"eipamt").trim() ,"#####0.00");
					//eipamt[i]=dbBean.getElementValue(i,"eipamt").trim();
					eipdta[j]=cd.dateFormat(dbBean.getElementValue(i,"eipdta").trim(),"L");		
					eiptyp[j]=dbBean.getElementValue(i,"eiptyp").trim() ;
					
					if(!eiptyp[j].equals(""))
						show[j]=CodesManager.getCodeValue("20",eiptyp[j]);
					
					eipvdr[j]=dbBean.getElementValue(i,"eipvdr").trim() ;
					eipyta[j]=cd.dateFormat(dbBean.getElementValue(i,"eipyta").trim(),"L");		
					eipflg[j]=dbBean.getElementValue(i,"eipflg").trim() ;
					 
					selectwhere=selectwhere+"OR (EIPSTR='"+dbBean.getElementValue(i,"eipstr").trim()+"'";
					selectwhere=selectwhere+" AND EIPNUM='"+dbBean.getElementValue(i,"eipnum").trim()+"'";
					selectwhere=selectwhere+" AND EIPTYP='"+dbBean.getElementValue(i,"eiptyp").trim()+"') ";
				}
				
				Date dd =new Date();
					
				if(!selectwhere.equals("")){
					selectwhere=selectwhere.substring(2);
				}
				
				PropertyUtils.setSimpleProperty(form,"selectwhere",selectwhere);
				
				PropertyUtils.setSimpleProperty(form, "nowdate"   ,FormatDate.format(dd,"yyyy-MM-dd")  );
				
				PropertyUtils.setSimpleProperty(form, "show"   ,show  );
				PropertyUtils.setSimpleProperty(form, "seq"   ,seq  );
				PropertyUtils.setSimpleProperty(form, "eipstr"   ,eipstr  );
				PropertyUtils.setSimpleProperty(form, "eipnum"   ,eipnum  );
				PropertyUtils.setSimpleProperty(form, "eipamt"   ,eipamt  );
				PropertyUtils.setSimpleProperty(form, "eipdta"   ,eipdta  );
				PropertyUtils.setSimpleProperty(form, "eiptyp"   ,eiptyp  );
				PropertyUtils.setSimpleProperty(form, "eipvdr"   ,eipvdr  );
				PropertyUtils.setSimpleProperty(form, "eipyta"   ,eipyta  );
				PropertyUtils.setSimpleProperty(form, "eipflg"   ,eipflg  );


				return "1";
			}

			/* (non-Javadoc)
			 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
			 */
			public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
				return  executeme( mapping,form,request,response);
			}
	

			public String[] getArray(String where,String[] db) throws Exception{
				String ss=where;
				String[] min=null;
				
				ss=com.idn.util.CommonTools.stringReplace(ss,"EIPSTR=","");
				ss=com.idn.util.CommonTools.stringReplace(ss,"adsfs=","");
				ss=com.idn.util.CommonTools.stringReplace(ss,"EIPNUM=","");
				ss=com.idn.util.CommonTools.stringReplace(ss,"EIPTYP=","");
				ss=com.idn.util.CommonTools.stringReplace(ss,"AND","");
				ss=com.idn.util.CommonTools.stringReplace(ss,'(',"");
				ss=com.idn.util.CommonTools.stringReplace(ss,')',"");
				ss=com.idn.util.CommonTools.stringReplace(ss,"'","");
				ss=com.idn.util.CommonTools.stringReplace(ss," ","");
				ss=com.idn.util.CommonTools.stringReplace(ss,"OR","&");
				 min=com.idn.util.CommonTools.stringToArray(ss,"&");
				

				
				 
				Vector tv=new Vector();
				Arrays.sort(min);
				//int k=0;
				int j=0;
				int mark=0;
				 for(int i=0;i<min.length ;i++){
					for(;j<db.length;j++){		
						if(min[i].compareTo(db[j])<0){
							break;	
						}
						if(min[i].equals(db[j])){
							tv.addElement(String.valueOf(j));
							j++;
							break;	
						}
					}
					if(j==db.length) break;
				 }
				String[] returns=(String[]) tv.toArray(new String[0]);
				return returns;
			}	

			public String getNo() throws Exception{
				String sql="select max(eivser)	from eivdtl";
				DataBean dbBean = new DataBean();	
				try{
				dbBean.executeSelect(sql);
				} catch (Exception e) {
				errors.add("errormessage",new ActionError("OrderIn.readdb"));
				return "";
				}
				
				String no=dbBean.getElementValue(0,0).trim();
				if (no.equals("")){
					no="0000000000";
				}
				
				sql="select max(eivser)	from eivdtl2";
				dbBean = new DataBean();	
				try{
				dbBean.executeSelect(sql);
				} catch (Exception e) {
				errors.add("errormessage",new ActionError("OrderIn.readdb"));
				return "";
				}
				String temp=dbBean.getElementValue(0,0);

				if (temp.equals("")){
					temp="0000000000";
				}
				if(no.compareTo(temp)>0){
					temp=no;		
				}
		
				return temp;
			}
	
		}

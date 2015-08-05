/*
 * @(#)homeworldPrintEipdtlAction.java  2004-6-30
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
import java.util.Arrays;
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

			import com.idn.log.LogWrapper;
			import com.idn.property.CodesManager;
			import com.idn.secure.SecureAction;
			import com.idn.sql.DataBean;
			import com.idn.util.DecimalTools;
			import commsearch.util.CommDate;


			/**
			 * <P>�˵�����</P>
			 * 
			 * @version 0.1
			 * @author ������
			 */
			public class homeworldPrintEipdtlAction extends SecureAction {
				private static LogWrapper log= new LogWrapper(homeworldPrintEipdtlAction.class);
				//private int intTemp = 0;
				//private String strSql;
				//private String[] strTemp = null;
				ActionErrors errors = new ActionErrors();
		
				/**
				 * ���캯��
				 */
				public homeworldPrintEipdtlAction () {
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

						
						//TODO ��ʱ���������
						long l_begin,l_end;
						l_begin = System.currentTimeMillis();		
						commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintEipdtl","B","LYC0000000",0);
							
							
						if(uname==null){
							errors.add("errormessage",new ActionError("NoName"));
							saveErrors(request, errors);
//							TODO ��ʱ���������
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"PrintEipdtl","E","LYC5555555",l_end - l_begin);
							return(mapping.findForward("success"));
						}
						if(uname.trim().equals("")){
							errors.add("errormessage",new ActionError("NoName"));
							saveErrors(request, errors);
//							TODO ��ʱ���������
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"PrintEipdtl","E","LYC6666666",l_end - l_begin);
							return(mapping.findForward("success"));
						}
	
						try{
			
						PropertyUtils.setSimpleProperty(form, "pagerow" ,"32");
				
						String temp = (String)request.getParameter("selectwhere");

						String stemp=temp;
						
						if(stemp==null)
							stemp="";
						if(stemp.equals("")){
//							TODO ��ʱ���������
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"PrintEipdtl","E","LYC3333333",l_end - l_begin);
							return(mapping.findForward("err"));
						}
						
						//temp=CommonTools.stringReplace(temp,"&#39;","'");
						DataBean dbBean = new DataBean();
						String ss="";
						if(temp.indexOf("EIVIVN")!=-1){
							try{
								dbBean.executeSelect("select eivstr,eivnum,eivtyp from eivdtl where "+ temp);
								} catch (Exception e) {
								errors.add("errormessage",new ActionError("Datebase.readdb"));
								saveErrors(request, errors);
//								TODO ��ʱ���������
								l_end = System.currentTimeMillis();
								commsearch.util.CommActionLog.setTempLog(
									Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
									uname,hs.getId(),"PrintEipdtl","C","LYC9999991",l_end - l_begin);
								return(mapping.findForward("success"));
							}
							for(int i=0;i<dbBean.getRowCount();i++){
								ss=ss+"OR (EIPSTR='"+dbBean.getElementValue(i,"eivstr").trim()+"' AND " ;
								ss=ss+" EIPNUM='"+dbBean.getElementValue(i,"eivnum").trim()+"' AND " ;
								ss=ss+" EIPTYP='"+dbBean.getElementValue(i,"eivtyp").trim()+"')" ;
							}
							ss=ss.substring(2);
							temp=ss;
						}
				
						PropertyUtils.setSimpleProperty(form, "selectwhere" ,temp );
				
						if(setFormbeen(form,request,temp,uname).equals("0")){
							saveErrors(request, errors);
//							TODO ��ʱ���������
							l_end = System.currentTimeMillis();
							commsearch.util.CommActionLog.setTempLog(
								Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
								uname,hs.getId(),"PrintEipdtl","E","LYC8888881",l_end - l_begin);
							return(mapping.findForward("success"));
						}
						log.debug("�ɹ�ˢ��ҳ��");
						
//						TODO ��ʱ���������
						l_end = System.currentTimeMillis();

						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintEipdtl","E","LYC8888882",l_end - l_begin);
						return(mapping.findForward("success"));
					

					} catch (Exception e){
						e.printStackTrace();
//						TODO ��ʱ���������
						l_end = System.currentTimeMillis();

						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintEipdtl","C","LYC9999999",l_end - l_begin);
						errors.add("errormessage",new ActionError("OrderIn.formbean"));
						saveErrors(request, errors);
						return(mapping.findForward("success"));
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
							
					CommDate cd=new CommDate();
					DataBean dbBean = new DataBean();
					
					String strsql="";
					
					int maxpage=0;
					int count =0;
					strsql="SELECT * FROM eipdtl LEFT OUTER JOIN eivdtl ON (eipstr = eivstr and eipnum=eivnum and eiptyp=eivtyp ) where eipflg='2' and  eipvdr='"+uname+"' order by EIPSTR,EIPNUM,EIPTYP";
					try{
						dbBean.executeSelect(strsql);
					} catch (Exception e) {
						e.printStackTrace();
						errors.add("errormessage",new ActionError("Datebase.readdb"));
						return "0";
					}
					log.debug("ִ��sql���");


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
				
					//count=dbBean.getRowCount();
					int pagerow=Integer.parseInt((String)PropertyUtils.getSimpleProperty(form,"pagerow"));
			
					//�����ҳ��ʾ�����ҳ��
					log.debug("�����С"+String.valueOf(count+maxpage));
					String[] seq=new String[count+maxpage];		//���	
					String[] eipstr=new String[count+maxpage];	//�̵��	
					String[] eipnum=new String[count+maxpage];	//����	
					String[] eipamt=new String[count+maxpage];	//���	
					String[] eipdta=new String[count+maxpage];	//��������
					String[] eiptyp=new String[count+maxpage];	//��������
					String[] eipvdr=new String[count+maxpage];	//�����̺�
					String[] eipyta=new String[count+maxpage];	//Ӧ������
					String[] eipflg=new String[count+maxpage];	//��־	
		
					String[] eivtrk=new String[count+maxpage]; //	��Ʊ�ֹ�
					String[] eivivn=new String[count+maxpage]; //	��Ʊ��
					String[] eivamt=new String[count+maxpage]; //	��Ʊ����
					
					String[] eivtax=new String[count+maxpage];	//Ӧ������
				
					
					int i=0;
					int j=0;
					
					String key=""; 
					for(;i<count+maxpage;i++){
						log.debug("����ѭ��"+String.valueOf(i));
						j=Integer.parseInt(row[i]);
						//if((i==jk*pagerow-1)||(i==count+maxpage-1)){
//						if(!( tempstr.equals(dbBean.getElementValue(j,"eipstr"  ).trim()) && tempnum.equals(dbBean.getElementValue(j,"eipnum"  ).trim()) && temptyp.equals(dbBean.getElementValue(j,"eiptyp"  ).trim()))){
//							seq[i]="";	
//							eipstr[i]="";	
//							eipnum[i]="";	
//							eipamt[i]="";	
//							eipdta[i]="";
//							eiptyp[i]="";
//							eipvdr[i]="";
//							eipyta[i]="";
//							eipflg[i]="";	
//				
//							eivtrk[i]="";
//							eivivn[i]="";
//							eivamt[i]="";
//							eivtax[i]="";
//							
//							tempstr=dbBean.getElementValue(j,"eipstr"  ).trim();
//							tempnum=dbBean.getElementValue(j,"eipnum"  ).trim();
//							temptyp=dbBean.getElementValue(j,"eiptyp"  ).trim();
//							
//							i++;
//							if(i >=count+maxpage){
//								break;
//							}
//						}
							seq[i]=String.valueOf(i+1);	
							eipstr[i]=dbBean.getElementValue(j,"eipstr"  ).trim() ; 
							eipnum[i]=dbBean.getElementValue(j,"eipnum"  ).trim(); 
							eipamt[i]=DecimalTools.format(dbBean.getElementValue(j,"eipamt"  ).trim(),"###,##0.00"); 
							eipdta[i]=cd.dateFormat(dbBean.getElementValue(j,"eipdta"  ).trim(),"L");		
							eiptyp[i]=dbBean.getElementValue(j,"eiptyp"  ).trim(); 
							if(!eiptyp[i].equals(""))
								eiptyp[i]=CodesManager.getCodeValue("20",eiptyp[i]);
													
							eipvdr[i]=dbBean.getElementValue(j,"eipvdr"  ).trim(); 
							eipyta[i]=cd.dateFormat(dbBean.getElementValue(j,"eipyta"  ).trim(),"L");		
							eipflg[i]=dbBean.getElementValue(j,"eipflg"  ).trim(); 
				
							eivtrk[i]=dbBean.getElementValue(j,"eivtrk"  ).trim(); 
							eivivn[i]=dbBean.getElementValue(j,"eivivn"  ).trim();
							eivtax[i]=dbBean.getElementValue(j,"eivtax"  ).trim();
							key=dbBean.getElementValue(j,"eivser"  ).trim();
							if(!dbBean.getElementValue(j,"eivamt"  ).trim().equals(""))
								eivamt[i]=DecimalTools.format(dbBean.getElementValue(j,"eivamt"  ).trim(),"###,##0.00");  
						
					}

					log.debug("�������");
					PropertyUtils.setSimpleProperty(form, "seq" ,seq   );
					PropertyUtils.setSimpleProperty(form, "eipstr" ,eipstr);
					PropertyUtils.setSimpleProperty(form, "eipnum" ,eipnum);
					PropertyUtils.setSimpleProperty(form, "eipamt" ,eipamt);
					PropertyUtils.setSimpleProperty(form, "eipdta" ,eipdta);
					PropertyUtils.setSimpleProperty(form, "eiptyp" ,eiptyp);
					PropertyUtils.setSimpleProperty(form, "eipvdr" ,eipvdr);
					PropertyUtils.setSimpleProperty(form, "eipyta" ,eipyta);
					
					PropertyUtils.setSimpleProperty(form, "eivtrk" ,eivtrk);
					PropertyUtils.setSimpleProperty(form, "eivivn" ,eivivn);
					PropertyUtils.setSimpleProperty(form, "eivamt" ,eivamt);
					PropertyUtils.setSimpleProperty(form, "eivtax" ,eivtax);
					
					
					
					strsql="SELECT distinct eivser FROM eipdtl LEFT OUTER JOIN ( select eivstr,eivnum,eivtyp,eivdtl2.eivtrk,eivdtl2.eivivn,eivseq,eivdtl2.eivamt,eivdtl2.eivtax,eivdtl2.eivser,eivdtl2.eivdat  from eivdtl,eivdtl2 where eivdtl.eivser=eivdtl2.eivser) as a ON (eipstr = eivstr and eipnum=eivnum and eiptyp=eivtyp) where eipflg='2' and eivser='"+key+"' and  eipvdr='"+uname+"' ";
					try{
						dbBean.executeSelect(strsql);
					} catch (Exception e) {
						e.printStackTrace();
						errors.add("errormessage",new ActionError("Datebase.readdb"));
						return "0";
					}
					maxpage=dbBean.getRowCount();
					
					strsql="SELECT distinct eivtrk,eivivn,eivseq,eivamt,eivtax,eivser,a.eivdat FROM eipdtl LEFT OUTER JOIN ( select eivstr,eivnum,eivtyp,eivdtl2.eivtrk,eivdtl2.eivivn,eivseq,eivdtl2.eivamt,eivdtl2.eivtax,eivdtl2.eivser ,eivdtl2.eivdat from eivdtl,eivdtl2 where eivdtl.eivser=eivdtl2.eivser) as a ON (eipstr = eivstr and eipnum=eivnum and eiptyp=eivtyp) where eipflg='2' and eivser='"+key+"' and eipvdr='"+uname+"' order by eivser,eivseq";
					try{
						dbBean.executeSelect(strsql);
					} catch (Exception e) {
						e.printStackTrace();
						errors.add("errormessage",new ActionError("Datebase.readdb"));
						return "0";
					}
					count=dbBean.getRowCount();
					String[] seqmx=new String[count+maxpage];	//Ӧ������
					String[] eivtrkmx=new String[count+maxpage]; //	��Ʊ�ֹ�
					String[] eivivnmx=new String[count+maxpage]; //	��Ʊ��
					String[] eivamtmx=new String[count+maxpage]; //	��Ʊ����
					String[] eivtaxmx=new String[count+maxpage];	//Ӧ������
					String[] eivdat=new String[count+maxpage];	//Ӧ������
					
					String tempser=dbBean.getElementValue(0,"eivser"  ).trim() ;

					j=0;
					i=0;
					int k=0;
					for(;i<count+maxpage;i++){
						log.debug("����ѭ��"+String.valueOf(i));
						if(!(tempser.equals(dbBean.getElementValue(j,"eivser"  ).trim()))){
							seqmx[i]="";	
							eivtrkmx[i]="";	
							eivivnmx[i]="";	
							eivamtmx[i]="";	
							eivtaxmx[i]="";
							eivdat[i]="";
											
							tempser=dbBean.getElementValue(j,"eivser"  ).trim() ;
							i++;
							if(i >=count+maxpage){
								break;
							}
							k=0;
						}
							seqmx[i]=String.valueOf(k+1);	

							eivtrkmx[i]=dbBean.getElementValue(j,"eivtrk"  ).trim() ;	
							eivivnmx[i]=dbBean.getElementValue(j,"eivivn"  ).trim() ;	
							if(!dbBean.getElementValue(j,"eivamt"  ).trim().equals(""))
								eivamtmx[i]=DecimalTools.format(dbBean.getElementValue(j,"eivamt"  ).trim(),"###,##0.00");  
							eivtaxmx[i]=dbBean.getElementValue(j,"eivtax"  ).trim() ;
							eivdat[i]=cd.dateFormat(dbBean.getElementValue(j,"eivdat"  ).trim(),"L");	
						 	j++;
						 	k++;
					}


					PropertyUtils.setSimpleProperty(form, "seqmx" ,seqmx);
					PropertyUtils.setSimpleProperty(form, "eivtrkmx" ,eivtrkmx);
					PropertyUtils.setSimpleProperty(form, "eivivnmx" ,eivivnmx);
					PropertyUtils.setSimpleProperty(form, "eivamtmx" ,eivamtmx);
					PropertyUtils.setSimpleProperty(form, "eivtaxmx" ,eivtaxmx);
					PropertyUtils.setSimpleProperty(form, "eivdat" ,eivdat);
					
					log.debug("���formbeen");
					return "1";
				}

				/* ���� Javadoc��
				 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
				 */
				public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
					return executeme(mapping,form,request,response);
				}

				public String[] getArray(String where,String[] db) throws Exception{
					String ss=where;
					ss=com.idn.util.CommonTools.stringReplace(ss,"EIPSTR=","");
					ss=com.idn.util.CommonTools.stringReplace(ss,"EIPNUM=","");
					ss=com.idn.util.CommonTools.stringReplace(ss,"EIPTYP=","");
					ss=com.idn.util.CommonTools.stringReplace(ss,"AND","");
					ss=com.idn.util.CommonTools.stringReplace(ss,'(',"");
					ss=com.idn.util.CommonTools.stringReplace(ss,')',"");
					ss=com.idn.util.CommonTools.stringReplace(ss,"'","");
					ss=com.idn.util.CommonTools.stringReplace(ss," ","");
					ss=com.idn.util.CommonTools.stringReplace(ss,"OR","&");
				
					String[] min=com.idn.util.CommonTools.stringToArray(ss,"&");
				
					Vector tv=new Vector();
					Arrays.sort(min);
					int k=0;
				
					 for(int i=0;i<min.length ;i++){
						for(int j=0;j<db.length;j++){
							if(min[i].equals(db[j])){
								tv.addElement(String.valueOf(j));
								break;	
							}
						}
					 }	
					String[] returns=(String[]) tv.toArray(new String[0]);
					return returns;
				}	
	
	
			}


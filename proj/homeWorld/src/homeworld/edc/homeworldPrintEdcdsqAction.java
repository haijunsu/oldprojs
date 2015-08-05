/*
 * @(#)homeworldPrintEdcdsqAction.java  2004-10-18
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.edc;

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
import com.idn.util.CommonTools;
		import com.idn.util.DecimalTools;

//		  import commsearch.CommTools;

		import commsearch.util.CommDate;


		/**
		 * <P>�˵�����</P>
		 * 
		 * @version 0.1
		 * @author ������
		 */
		public class homeworldPrintEdcdsqAction  extends SecureAction {
			ActionErrors errors = new ActionErrors();

			/**
			 * ���캯��
			 */
			public homeworldPrintEdcdsqAction () {
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

					//TODO ��ʱ���������
					long l_begin,l_end;
					l_begin = System.currentTimeMillis();		
					commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"PrintEdcdsq","B","LYC0000000",0);
						
					if(uname==null){
						errors.add("errormessage",new ActionError("NoName"));
						saveErrors(request, errors);
//						TODO ��ʱ���������
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintEdcdsq","E","LYC5555555",l_end - l_begin); 
						return(mapping.findForward("success"));
					}
					if(uname.trim().equals("")){
						errors.add("errormessage",new ActionError("NoName"));
						saveErrors(request, errors);
//						TODO ��ʱ���������
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintEdcdsq","E","LYC6666666",l_end - l_begin); 
						return(mapping.findForward("success"));
					}

			try{
					String selectwhere= (String)request.getParameter("selectwhere");
					String queryid= (String)request.getParameter("queryid");
					

					String stemp=selectwhere;
					
					if(stemp==null)
						stemp="";
					if(stemp.equals("")){
//						TODO ��ʱ���������
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"PrintEdcdsq","E","LYC3333333",l_end - l_begin);
						return(mapping.findForward("err"));
					}
					
					selectwhere=CommonTools.stringReplace(selectwhere,"EPONUM","dqponm");
					
					PropertyUtils.setSimpleProperty(form,"queryid",queryid);
					PropertyUtils.setSimpleProperty(form,"selectwhere",selectwhere);

					PropertyUtils.setSimpleProperty(form, "pagerow" ,"22");
				
					setFormbeen(form,request,selectwhere,uname);

//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"PrintEdcdsq","E","LYC8888888",l_end - l_begin);
					return(mapping.findForward("success"));
				} catch (Exception e){
					e.printStackTrace();
					errors.add("errormessage",new ActionError("Datebase.formbean"));
					saveErrors(request, errors);
//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"PrintEdcdsq","C","LYC9999999",l_end - l_begin);
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
			DataBean dbBean = new DataBean();
			CommDate cd=new CommDate();

		 String[] dqponm =null;       //������    
		 String[] dqcdat =null;       //��������  
		 String[] dqctim =null;       //����ʱ��  
		 String[] dqpdat =null;       //��������  
		 String[] dqpstr =null;       //�����ص�  
		 String[] dqvend =null;       //�����̺�  
		 String[] dqseq  =null;       //���      
		 String[] dqdstr =null;       //�̵�      
		 String[] dqsku  =null;       //sku       
		 String[] dqskud =null;       //Ʒ��      
		 String[] dqvndn =null;       //����      
		 String[] dqdqty =null;       //��������  
		 String[] dqdcas =null;       //����    
		
				int count =0;

				String strsql="select * from edcdsq where "+where+" order by dqseq";   
				try{
					dbBean.executeSelect(strsql);
				} catch (Exception e) {
					e.printStackTrace();
					errors.add("errormessage",new ActionError("Datebase.readdb"));
					return "0";
				}

				count=dbBean.getRowCount();
				PropertyUtils.setSimpleProperty(form, "endnew"   ,String.valueOf(count) );  
			
				int pagerow=Integer.parseInt((String)PropertyUtils.getSimpleProperty(form,"pagerow"));

				//�����ҳ��ʾ�����ҳ��
				int maxpage=0;
				
				for(int i=1; i<50; i++){
					maxpage=i-1;
					if(count<=(i* pagerow)){
						break;
					}
				}
				

				dqponm =new String[count+maxpage];       //������    
				dqcdat =new String[count+maxpage];       //��������  
				dqctim =new String[count+maxpage];       //����ʱ��  
				dqpdat =new String[count+maxpage];       //��������  
				dqpstr =new String[count+maxpage];       //�����ص�  
				dqvend =new String[count+maxpage];       //�����̺�  
				dqseq  =new String[count+maxpage];       //���      
				dqdstr =new String[count+maxpage];       //�̵�      
				dqsku  =new String[count+maxpage];       //sku       
				dqskud =new String[count+maxpage];       //Ʒ��      
				dqvndn =new String[count+maxpage];       //����      
				dqdqty =new String[count+maxpage];       //��������  
				dqdcas =new String[count+maxpage];       //����   

		 
				int jk=1;
				int j=0;

				for(int i=0;i<count+maxpage;i++){
					if((i==jk*pagerow-1)){
						                                             
						dqponm[i]="";
						dqcdat[i]="";
						dqctim[i]="";
						dqpdat[i]="";
						dqpstr[i]="";
						dqvend[i]="";
						dqseq[i] ="";
						dqdstr[i]="pagenew";
						dqsku[i] ="pagenew";
						dqskud[i]="";
						dqvndn[i]="";
						dqdqty[i]="";
						dqdcas[i]="";

						jk++;					
					}else{
						dqponm[i]=dbBean.getElementValue(j,"dqponm");
						dqcdat[i]=cd.dateFormat(dbBean.getElementValue(j,"dqcdat").trim() ,"L");
						dqctim[i]=cd.timeFormat(dbBean.getElementValue(j,"dqctim").trim() ,"L");
						dqpdat[i]=cd.dateFormat(dbBean.getElementValue(j,"dqpdat").trim() ,"L");
						dqpstr[i]=dbBean.getElementValue(j,"dqpstr");
						dqvend[i]=dbBean.getElementValue(j,"dqvend");
						dqseq[i] =dbBean.getElementValue(j,"dqseq" );
						dqdstr[i]=dbBean.getElementValue(j,"dqdstr");
						dqsku[i] =dbBean.getElementValue(j,"dqsku" );
						dqskud[i]=dbBean.getElementValue(j,"dqskud");
						dqvndn[i]=dbBean.getElementValue(j,"dqvndn");
						dqdqty[i]=DecimalTools.format(dbBean.getElementValue(j,"dqdqty"),"##,###,##0.00");
						dqdcas[i]=DecimalTools.format(dbBean.getElementValue(j,"dqdcas"),"##,###,##0.00");

						j++;
					}
				}

			
				PropertyUtils.setSimpleProperty(form, "dqponm"   ,dqponm );  
				PropertyUtils.setSimpleProperty(form, "dqcdat"   ,dqcdat );
				PropertyUtils.setSimpleProperty(form, "dqctim"   ,dqctim );  
				PropertyUtils.setSimpleProperty(form, "dqpdat"   ,dqpdat );  
				PropertyUtils.setSimpleProperty(form, "dqpstr"   ,dqpstr );  
				PropertyUtils.setSimpleProperty(form, "dqvend"   ,dqvend );  
				PropertyUtils.setSimpleProperty(form, "dqseq"    ,dqseq  );  
				PropertyUtils.setSimpleProperty(form, "dqdstr"   ,dqdstr );  
				PropertyUtils.setSimpleProperty(form, "dqsku"    ,dqsku  );  
				PropertyUtils.setSimpleProperty(form, "dqskud"   ,dqskud );  
				PropertyUtils.setSimpleProperty(form, "dqvndn"   ,dqvndn );  
				PropertyUtils.setSimpleProperty(form, "dqdqty"   ,dqdqty );  
				PropertyUtils.setSimpleProperty(form, "dqdcas"   ,dqdcas );  
			
						
				return "1";
			}

			/* (non-Javadoc)
			 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
			 */
			public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
				return  executeme( mapping,form,request,response);
			}
	
	
	
		}

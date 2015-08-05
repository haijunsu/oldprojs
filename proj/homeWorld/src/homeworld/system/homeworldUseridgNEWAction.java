/*
 * @(#)homeworldUseridgNEWAction.java  2004-4-5
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.system;
	/**
	 * <P> </P>
	 * 
	 * @version 0.1
	 * @author lyc
	 */


	import java.io.IOException;
import java.util.Vector;
//	  import java.util.Date;

//	  import java.util.Vector;

	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//	  import javax.servlet.http.HttpSession;

	import org.apache.commons.beanutils.PropertyUtils;
//	  import org.apache.struts.action.Action;
	import org.apache.struts.action.ActionError;
	import org.apache.struts.action.ActionErrors;
	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionForward;
	import org.apache.struts.action.ActionMapping;
	//import org.apache.struts.action.ActionMessages;

//	  import com.idn.log.LogWrapper;
	import com.idn.secure.SecureAction;
import com.idn.sql.DataBean;
import com.idn.sql.DynaSqlBean;
//	  import com.idn.sql.*;
//	  import com.idn.util.FormatDate;

	/**
	 * <P>��װ�ѱ����</P>
	 * 
	 * @version 0.1
	 * @author ������
	 */
	public class homeworldUseridgNEWAction extends SecureAction {
		
		ActionErrors errors = new ActionErrors();
		
		/**
		 * ���캯��
		 */
		public homeworldUseridgNEWAction() {
			super();
		}

		/** (non-Javadoc)
		 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 */
		public ActionForward executeme(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,	HttpServletResponse response)throws IOException, ServletException {
		
			//��ʼ��error����		
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
				uname,hs.getId(),"UseridgNEW","B","LYC0000000",0);
			
//			System.out.println("����ʼ");
		try{

			//��ӱ���	
			PropertyUtils.setSimpleProperty(form, "title","��Ա������");
			PropertyUtils.setSimpleProperty(form, "id","userid");
			PropertyUtils.setSimpleProperty(form, "show","��Ա����");
			//��ӱ���
		
			String temp="";
	
			//1 : �޸�(Ĭ��)  2 ����ѯ
			String se_up=(String)request.getParameter("se_up");
			if(se_up==null){se_up=(String) PropertyUtils.getSimpleProperty(form, "se_up");}
			PropertyUtils.setSimpleProperty(form,"se_up",se_up);
		
			//ȡ��־
			String flag=(String) PropertyUtils.getSimpleProperty(form, "flag");
			String page=(String) PropertyUtils.getSimpleProperty(form, "page");
			String where=(String) PropertyUtils.getSimpleProperty(form, "where");
		 
			//String liketext=(String) PropertyUtils.getSimpleProperty(form, "liketext");
			
			//�״�ֱ�ӷ���
			if (flag.equals("")){
//				System.out.println(page);
//				System.out.println("�״�ֱ�ӷ���");
//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"UseridgNEW","E","LYC8888881",l_end - l_begin);
				return mapping.findForward("success");
			}
		
			//�ڴη���
			if(flag.equals("selectexic")){
				temp=setFormbeen(form,request,where);
				if (temp.equals("0")){saveErrors(request, errors);}
//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"UseridgNEW","E","LYC8888882",l_end - l_begin);
				return mapping.findForward("success");
			}
		
			/*
			 *��Ӵ���
			 */
			String currrowshow;
			String[] strSql=null;
			Vector vecTemp=new Vector();
			DynaSqlBean dybBean = new DynaSqlBean();
			
				String strFlag=((String) PropertyUtils.getSimpleProperty(form, "flag"));
			
				String[] rowstate=((String[]) PropertyUtils.getProperty(form, "rowstate"));
				String[] userid=((String[]) PropertyUtils.getProperty(form, "userid"));
				String[] useridg=((String[]) PropertyUtils.getProperty(form, "useridgid"));

				DataBean dbBean = new DataBean();
				for(int i=0 ;i<rowstate.length;i++){
					if(rowstate[i].equals("1")){
						dbBean.executeSelect("select count(*) from users where userid='"+useridg[i]+"'");
						if(Integer.parseInt(dbBean.getElementValue(0,0))==0){
							errors.add("errormessage",new ActionError("Useridg.error"));
							saveErrors(request, errors);
							return(mapping.findForward("success"));
						}
						vecTemp.addElement("update users set useridg='"+useridg[i]+"' where userid='"+userid[i]+"'");
					}	
				
				}
	
				strSql = (String[]) vecTemp.toArray(new String[0]);		
				//ִ��sql���
				try{
					dybBean.setSql(strSql);
					dybBean.executeBatch();
				} catch (Exception e){
					e.printStackTrace();
					errors.add("errormessage",new ActionError("Database.writedb"));
					saveErrors(request, errors);
				}
		 

			/*
			 *��Ӵ���
			 */
		 
			 for(int i=0; i<rowstate.length;i++){
			 	rowstate[i]="0";
			 }
			PropertyUtils.setSimpleProperty(form, "rowstate",rowstate);
//			System.out.println("���̽���");
			//�ڴη���
			if(flag.equals("select")){
				temp=setFormbeen(form,request,where);
				if (temp.equals("0")){saveErrors(request, errors);}
//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"UseridgNEW","E","LYC8888883",l_end - l_begin);
				return mapping.findForward("success");
			}
			//��formbean�и�ֵ
			//System.out.println("�������");
			//temp=setFormbeen(form,request,where);
			
//			TODO ��ʱ���������
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"UseridgNEW","E","LYC8888884",l_end - l_begin);
			return mapping.findForward("success");	
		
		} catch (Exception e){
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Database.formbean"));
			saveErrors(request, errors);
//			TODO ��ʱ���������
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"UseridgNEW","C","LYC9999999",l_end - l_begin);
			return(mapping.findForward("success"));
		}

	}

			/**
			 * ��ָ����������ݴ�����ʾFormBeen(page=3)
			 * 
			 * @param ActionForm ��ʾFormBeen
			 * @param String ��ԱID
			 * @return String 1���ɹ���0�����ɹ���
			 * @exception Exception
			 */
			public String setFormbeen(ActionForm form,HttpServletRequest request,String where) throws Exception {
				String[] seq=null; 
		
				/*
				 *��Ӵ���
				 */

				String strSql,key;
				DataBean dbBean = new DataBean();
				strSql="select userid,lifebeg,lifeend,useridg,vndnam " +
					"from users,vndinfo where "+where+" and userid=vndnum order by userid";

				dbBean.executeSelect(strSql);
	
				int count;
				count=dbBean.getRowCount();
			if (count<2){
				 count=2;
			}

			String[] userid= new String[count];//�û�id
			String[] usershow= new String[count];//�û�����
	
			String[] lifebeg = new String[count];
			String[] lifeend = new String[count];
	
			String[] useridgshow = new String[count];
			String[] useridgid = new String[count];
			String[] rowstate = new String[count];
			seq = new String[count];
	
				for(int i=0;i<count;i++){
					seq[i]=String.valueOf(i);
					userid[i]=dbBean.getElementValue(i,0).trim() ;
					usershow[i]=dbBean.getElementValue(i,"vndnam").trim() ;

					lifebeg[i]=dbBean.getElementValue(i,1).trim() ;
					lifeend[i]=dbBean.getElementValue(i,2).trim() ;
					rowstate[i]="0";

					if (!lifeend[i].equals("")){
						lifebeg[i]=com.idn.util.FormatDate.format(Long.parseLong(lifebeg[i]),"yyyy-MM-dd");	
						lifeend[i]=com.idn.util.FormatDate.format(Long.parseLong(lifeend[i]),"yyyy-MM-dd");
					}
			
					useridgid[i]=dbBean.getElementValue(i,3).trim() ;
					useridgshow[i]=dbBean.getElementValue(i,4).trim();			
				}

				PropertyUtils.setSimpleProperty(form, "rowstate",rowstate);
				PropertyUtils.setSimpleProperty(form, "seq",seq);
				PropertyUtils.setSimpleProperty(form, "userid",userid);
				PropertyUtils.setSimpleProperty(form, "usershow",usershow);
		
				PropertyUtils.setSimpleProperty(form, "lifebeg",lifebeg);
				PropertyUtils.setSimpleProperty(form, "lifeend",lifeend);

				PropertyUtils.setSimpleProperty(form, "useridgid",useridgid);
				PropertyUtils.setSimpleProperty(form, "useridgshow",useridgshow);
		
		 

				/*
				 *��Ӵ���
				 */
		 			
				PropertyUtils.setSimpleProperty(form, "page","2");
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

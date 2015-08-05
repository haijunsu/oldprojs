/*
 * @(#)homeworldUserGroupAction.java  2004-2-27
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
//	import java.util.Date;

	import java.util.Vector;

	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//	import javax.servlet.http.HttpSession;

	import org.apache.commons.beanutils.PropertyUtils;
//	import org.apache.struts.action.Action;
	import org.apache.struts.action.ActionError;
	import org.apache.struts.action.ActionErrors;
	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionForward;
	import org.apache.struts.action.ActionMapping;
//	import org.apache.struts.action.ActionMessages;

//	import com.idn.log.LogWrapper;
//	import com.idn.property.CodesManager;
	import com.idn.secure.SecureAction;
	import com.idn.sql.*;
//	import com.idn.util.FormatDate;
	//import system.fun.FunPurview;
//	import homeworld.fun.systemfun;

	/**
	 * <P>��װ�ѱ����</P>
	 * 
	 * @version 0.1
	 * @author ������
	 */
	public class homeworldUserGroupAction extends SecureAction {
		ActionErrors errors = new ActionErrors();
	
		//FunPurview funPurview=new FunPurview();
		/**
		 * ���캯��
		 */
		public homeworldUserGroupAction() {
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
				uname,hs.getId(),"UserGroup","B","LYC0000000",0);
						

		try{

			//		��ӱ���		
			PropertyUtils.setSimpleProperty(form, "title","��Աͨ��Ȩ��");
			PropertyUtils.setSimpleProperty(form, "id","userid");
			PropertyUtils.setSimpleProperty(form, "show","�û�");
			//		��ӱ���	

			String temp="";
	
		
			//1 : �޸�(Ĭ��)  2 ����ѯ
			String se_up=(String)request.getParameter("se_up");
			if(se_up==null){se_up=(String) PropertyUtils.getSimpleProperty(form, "se_up");}
			PropertyUtils.setSimpleProperty(form,"se_up",se_up);
		
			//ȡ��־
			String strFlag=(String) PropertyUtils.getSimpleProperty(form, "flag");
			String page=(String) PropertyUtils.getSimpleProperty(form, "page");
			String where=(String) PropertyUtils.getSimpleProperty(form, "where");

		

			//�״�ֱ�ӷ���
			if (strFlag.equals("")){
				
//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"UserGroup","E","LYC8888881",l_end - l_begin);
					return mapping.findForward("success");}
		
			//�ڴη���
			if(strFlag.equals("selectexic")){
				temp=setFormbeen(form,request,where);
				if (temp.equals("0")){saveErrors(request, errors);}
//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"UserGroup","E","LYC8888882",l_end - l_begin);
				return mapping.findForward("success");
			}


			if(strFlag.equals("hiddenexic")){
//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"UserGroup","E","LYC8888883",l_end - l_begin);
				return mapping.findForward("success");
			}
		
		
			/*
			 *��Ӵ���
			 */
					DynaSqlBean dybBean = new DynaSqlBean();
					Vector vecTemp=new Vector();

					String currrowshow;
					String[] strSql=null;
		
					currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrowshow"));

					if (strFlag.equalsIgnoreCase("hiddenexit")){
//						TODO ��ʱ���������
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"UserGroup","E","LYC8888884",l_end - l_begin);
						return(mapping.findForward("success"));
					}



					//���ݲ�ͬ��ϵͳ��ʾ��ͬ��Ȩ��
					if (strFlag.equalsIgnoreCase("changeexic")){
						if(!currrowshow.equals("-1")){
							String mpurview;
							String[] mpurviewid=null;
							
							mpurview=((String) PropertyUtils.getIndexedProperty(form, "mpurview",Integer.parseInt(currrowshow))).trim();
							//����Ȩ��
							mpurviewid=homeworld.fun.systemfun.decode(mpurview);
							PropertyUtils.setSimpleProperty(form, "mpurviewid",mpurviewid);
						}
//						TODO ��ʱ���������
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"UserGroup","E","LYC8888886",l_end - l_begin);
						return(mapping.findForward("success"));
					}

					//ȡ��ǰ��
					currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrowshowold"));
					if (strFlag.equalsIgnoreCase("commit") || strFlag.equalsIgnoreCase("select"))
						currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrow"));
		
					String rowstate=((String) PropertyUtils.getIndexedProperty(form, "rowstate",Integer.parseInt(currrowshow)));
			
					if (rowstate.equals("1")){
						//ȡϵͳ
						String userid=((String) PropertyUtils.getIndexedProperty(form, "userid",Integer.parseInt(currrowshow)));
						//ȡȨ��
						String[] purviewid = (String[])request.getParameterValues("mpurviewid");
						
						String strTemp=homeworld.fun.systemfun.coding(purviewid);
		
						PropertyUtils.setIndexedProperty(form,"mpurview",Integer.parseInt(currrowshow),strTemp);
						PropertyUtils.setIndexedProperty(form,"rowstate",Integer.parseInt(currrowshow),"0");
		
						vecTemp.addElement("update users set begroup='"+strTemp+"' where userid='"+userid+"'");

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
					}
		
			currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrow"));

			/*
			 *��Ӵ���
			 */

			//�ڴη���
			if(strFlag.equals("select")){
				temp=setFormbeen(form,request,where);
				if (temp.equals("0")){saveErrors(request, errors);}
//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"UserGroup","E","LYC8888885",l_end - l_begin);
				return mapping.findForward("success");
			}
			if(!currrowshow.equals("-1")){
				String mpurview;
				String[] mpurviewid=null;
				mpurview=((String) PropertyUtils.getIndexedProperty(form, "mpurview",Integer.parseInt(currrowshow))).trim();
				//����Ȩ��
				mpurviewid=homeworld.fun.systemfun.decode(mpurview);
				PropertyUtils.setSimpleProperty(form, "mpurviewid",mpurviewid);
			}
		
						
			//��formbean�и�ֵ

//			TODO ��ʱ���������
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"UserGroup","E","LYC8888888",l_end - l_begin);
			return mapping.findForward("success");	
		
		} catch (Exception e){
			e.printStackTrace();
			errors.add("errormessage",new ActionError("Database.formbean"));
			saveErrors(request, errors);
//			TODO ��ʱ���������
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"UserGroup","C","LYC9999999",l_end - l_begin);
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
			

				/*
				 *��Ӵ���
				 */
				String strSql,key,currrowshow;
		
				DataBean dbBean = new DataBean();
		
				strSql="select userid,appsys,purview,lifebeg,lifeend,useridg,vndnam,begroup  " +
					"from users,VNDINFO where userid=vndnum and ("+ where +") order by userid";

				dbBean.executeSelect(strSql);
	
				int count;
				count=dbBean.getRowCount();
			if (count<2){
				 count=2;
			}
		
			String[] mpurview = new String[count];//Ȩ��id

			String[] userid= new String[count];//�û�id
			String[] usershow= new String[count];//�û�����
	
			String[] lifebeg = new String[count];
			String[] lifeend = new String[count];
	
			String[] useridgshow = new String[count];
			String[] useridgid = new String[count];
	
				for(int i=0;i<count;i++){
				
					userid[i]=dbBean.getElementValue(i,0) ;
					usershow[i]=dbBean.getElementValue(i,"vndnam");
			
					mpurview[i]=dbBean.getElementValue(i,"begroup");
					lifebeg[i]=dbBean.getElementValue(i,3).trim() ;
					lifeend[i]=dbBean.getElementValue(i,4).trim();

					if (!lifeend[i].equals("")){
						lifebeg[i]=com.idn.util.FormatDate.format(Long.parseLong(lifebeg[i]),"yyyy-MM-dd");	
						lifeend[i]=com.idn.util.FormatDate.format(Long.parseLong(lifeend[i]),"yyyy-MM-dd");
					}
					
					useridgid[i]=dbBean.getElementValue(i,5);
					useridgshow[i]=useridgid[i] ;			
				}

				PropertyUtils.setSimpleProperty(form, "userid",userid);
				PropertyUtils.setSimpleProperty(form, "usershow",usershow);
		
				PropertyUtils.setSimpleProperty(form, "mpurview",mpurview);
		
				PropertyUtils.setSimpleProperty(form, "lifebeg",lifebeg);
				PropertyUtils.setSimpleProperty(form, "lifeend",lifeend);

				PropertyUtils.setSimpleProperty(form, "useridgid",useridgid);
				PropertyUtils.setSimpleProperty(form, "useridgshow",useridgshow);
		
			

				PropertyUtils.setSimpleProperty(form, "currrow","-1"); 
				PropertyUtils.setSimpleProperty(form, "currrowshowold","-1");
				PropertyUtils.setSimpleProperty(form, "currrowshow","-1");


				String[] mpurviewshow = new String[count];//Ȩ����ʾ
			
				strSql="select * from codes where cclass='25' order by ccode";
				try{
					dbBean.executeSelect(strSql);
				} catch (Exception e) {
					e.printStackTrace();
					errors.add("errormessage",new ActionError("Database.readdb"));
				}
				count =dbBean.getRowCount();
				mpurviewshow=new String[count];
				for(int i=0;i<count;i++){
					mpurviewshow[i]=dbBean.getElementValue(i,"cshowc").trim() ;
				}
				PropertyUtils.setSimpleProperty(form, "mpurviewshow",mpurviewshow);
				/*
				 *��Ӵ���
				 */

		
				PropertyUtils.setSimpleProperty(form, "count",String.valueOf(count));
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


/*
 * @(#)SalaryInputAllCtrl.java  2003-05-08
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.action;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;

//import com.idn.util.*;
//import com.idn.property.*;

//import salaryin.SalaryInputTools;
//import com.idn.property.PropertyManager;
/**
 * <P>����¼���ѯ����</P>
 * 
 * @version 1.0
 * @author XIAOAI
 */
public class ChangePasswordSave extends Action {

	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(ChangePasswordCtrl.class);
	private Locale locale = null;   //ȡ�����ļ���
	private MessageResources messages = null; //ȡ�����ļ���
	private String username = null;  //��¼�û���
	private ErrorValue siev =null;  //����Bean
//	private SalaryInputTools sit= null;     //����
	private commsearch.CommSQL sis = null;      //SQL���
	/**
	 * Constructor for QueryAction.
	 */		
	public ChangePasswordSave() {
		super();
	}
	/**
	 * �û��������EXECUTE����
	 * 
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @exception IOException, ServletException 
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException 
		{
		int i_fori,i_forj,i_return;
		int i_count,i_rowcount,i_colcount;
		String passwordold;        //�µĿ���
		String passwordnew;        //�µĿ���
		String passwordagain;      //��һ���µĿ���

		siev = new ErrorValue(); //Error ValueBean
//		sit = new SalaryInputTools();    //����Bean
		ActionForward forward = new ActionForward();

		log.info("�û�������ı���:��ʼִ���û�������ı�����Ƴ���Execute");
		HttpSession session = request.getSession();
		username = (String)session.getAttribute("userid");
		username = username.trim();
		log.info("��¼�û���"+username);

		//TODO ��ʱ���������
		long l_begin,l_end;
		l_begin = System.currentTimeMillis();		
		commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
		commsearch.util.CommActionLog.setTempLog(
			Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
			username,session.getId(),"ChangePassworldSave","B","cpw0001",0);



		if (username==null){
			//sessionʧЧ��
			siev.setError("SalaryInputSearch","execute","ʱ��̫��,session�Ѿ�ʧЧ��;�����µ�¼!");					
			request.setAttribute("siev",siev);
			forward = mapping.findForward("failure");
			log.info("�û�������ı���:�û�������ı�����Ƴ���Execute����ʧ�ܻ���");

//			TODO ��ʱ���������
			commsearch.util.CommDate cd = new commsearch.util.CommDate();
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"ChangePassworldSave","E","cpw0997",l_end - l_begin);

			return forward;
		}
		locale = getLocale(request);
		messages = getResources(request);
		sis = new commsearch.CommSQL();
		try {
			passwordold = request.getParameter("passwordold");
			passwordnew = request.getParameter("passwordnew");
			passwordagain = request.getParameter("passwordagain");
			passwordold=passwordold.toUpperCase().trim();
			sis = new commsearch.CommSQL();
			String[][] passwordoldcount=null;
			passwordoldcount = sis.executeSelect("select * from users where userid='"+username.trim()+"' AND upwd='" + passwordold +"'");
			if (passwordoldcount == null){
			//ʧ��ת��ҳ��
				  siev.setError("SalaryChangePasswordSave","execute","�ɵĿ����������");
				  siev.setFlag("0");					
				  request.setAttribute("siev",siev);
				  forward = mapping.findForward("failure");
//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					username,session.getId(),"ChangePassworldSave","B","cpw0903",l_end - l_begin);
				  
				  return forward;
			}

			if (!passwordnew.equals(passwordagain)){
				//ʧ��ת��ҳ��
				siev.setError("SalaryChangePasswordSave","execute","��������Ŀ��һ��");
				siev.setFlag("0");					
				request.setAttribute("siev",siev);
				forward = mapping.findForward("failure");
				
//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					username,session.getId(),"ChangePassworldSave","B","cpw0904",l_end - l_begin);
				
				return forward;				
			}
			if (passwordnew.equals("")){
				//ʧ��ת��ҳ��
				siev.setError("SalaryChangePasswordSave","execute","�����Ϊ��");
				siev.setFlag("0");					
				request.setAttribute("siev",siev);
				forward = mapping.findForward("failure");

//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					username,session.getId(),"ChangePassworldSave","B","cpw0905",l_end - l_begin);

				return forward;				
			}
			
			
			
			
			
			passwordnew = passwordnew.toUpperCase();
			if (sis.executeBatchSQL("UPDATE USERS SET UPWD='"+passwordnew+"' WHERE USERID='"+username+"'")==-1){
				//ʧ��ת��ҳ��
				siev.setError("SalaryChangePasswordSave","execute","�û�����������ݿⷢ������");
				siev.setFlag("0");					
				request.setAttribute("siev",siev);
				forward = mapping.findForward("failure");

//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					username,session.getId(),"ChangePassworldSave","C","cpw0906",l_end - l_begin);
				
				return forward;				
			}
			//�ɹ�ת��ҳ��
			siev.setError("","","�û������޸ĳɹ�");
			siev.setFlag("1");					
			request.setAttribute("siev",siev);
			forward = mapping.findForward("success");
			
//			TODO ��ʱ���������
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"ChangePassworldSave","E","cpw0999",l_end - l_begin);
			
			
			log.info("�û�������ı���:�û�������ı�����Ƴ���Execute����ɹ�����");
		} catch (Exception e) {
			log.error("�û�������ı���:δ֪�쳣����");
			e.printStackTrace();
			siev.setError("SalaryChangePasswordSave","execute","�쳣δ֪����");					
			request.setAttribute("siev",siev);
			forward = mapping.findForward("failure");

//			TODO ��ʱ���������
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"ChangePassworldSave","E","cpw0900",l_end - l_begin);
			
			
			log.info("�û�������ı���:�û�������ı�����Ƴ���Execute����ʧ�ܻ���");
		}
		return forward;
	}	
}
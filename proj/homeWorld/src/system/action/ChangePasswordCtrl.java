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
import org.apache.commons.beanutils.PropertyUtils;

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
public class ChangePasswordCtrl extends Action {

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
	public ChangePasswordCtrl() {
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
		siev = new ErrorValue(); //Error ValueBean
//		sit = new SalaryInputTools();    //����Bean
		ActionForward forward = new ActionForward();

		log.info("�û��������:��ʼִ���û�������Ŀ��Ƴ���Execute");
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
			username,session.getId(),"ChangePassworldCtrl","B","cpw0001",0);


		if (username==null){
			//sessionʧЧ��
			siev.setError("ChangePasswordCtrl","execute","ʱ��̫��,session�Ѿ�ʧЧ��;�����µ�¼!");					
			request.setAttribute("siev",siev);
			forward = mapping.findForward("failure");
			log.info("�û��������:�û�������Ŀ��Ƴ���Execute����ʧ�ܻ���");

//			TODO ��ʱ���������
			commsearch.util.CommDate cd = new commsearch.util.CommDate();
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"ChangePassworldCtrl","E","cpw0997",l_end - l_begin);
			return forward;
		}



		locale = getLocale(request);
		messages = getResources(request);
		sis = new commsearch.CommSQL();
		try {
			String[][] s_temp;
			String s_namec = "";
			String a;
			a="select VNDNAM from VNDINFO WHERE VNDNUM='"+username.toUpperCase()+"'";
			s_temp= sis.executeSelect(a);
			if (!(s_temp==null)){
				s_namec = s_temp[0][0];
			}
			PropertyUtils.setSimpleProperty(form,"usernameid",username);
			PropertyUtils.setSimpleProperty(form,"usernamec",s_namec);
			//�ɹ�ת��ҳ��
			forward = mapping.findForward("success");
//			siev.setError("SalaryChangePasswrodCtrl","execute","������");					
//			request.setAttribute("siev",siev);
//			forward = mapping.findForward("failure");
//			TODO ��ʱ���������
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"ChangePassworldCtrl","E","cpw0999",l_end - l_begin);

			log.info("�û��������:�û�������Ŀ��Ƴ���Execute����ɹ�����");
		} catch (Exception e) {
			log.error("�û��������:δ֪�쳣����");
			e.printStackTrace();
			siev.setError("SalaryChangePasswordCtrl","execute","�쳣δ֪����");					
			request.setAttribute("siev",siev);
			forward = mapping.findForward("failure");

//			TODO ��ʱ���������
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"ChangePassworldCtrl","E","cpw0900",l_end - l_begin);


			log.info("�û��������:�û�������Ŀ��Ƴ���Execute����ʧ�ܻ���");
		}
		return forward;
	}	
}
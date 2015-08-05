/*
 * @(#)CommSearch.java  2003-07-10
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;

//import com.idn.util.*;
//import com.idn.property.*;
//import com.idn.property.PropertyManager;
//import com.idn.sql.*;

/**
 * <P>���ò�ѯ����</P>
 * 
 * @version 1.0
 * @author XIAOAI
 
 */
public class CommSearchResult extends Action {

	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommSearchResult.class);
	
	/**
	 * Constructor for QueryAction.
	 */		
	public CommSearchResult() {
		super();
	}
	/**
	 * ���ò�ѯEXECUTE����
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
		Locale locale = null;             //ȡ�����ļ���
		MessageResources messages = null; //ȡ�����ļ���
		String username = null;            //��¼�û���
		CommTools ct = new CommTools();    //���ò�ѯ������
		CommQuery cq = null;               //���ò�ѯ������

		int i_fori,i_forj,i_return;
		String s_statu = "",s_flag,s_page,s_queryid,s_actiondo;

		ActionForward forward = new ActionForward();

		log.info("���ò�ѯ:��ʼִ�й��ò�ѯ���Ƴ���Execute");
		HttpSession session = request.getSession();
		username = (String)session.getAttribute("userid");

		locale = getLocale(request);
		messages = getResources(request);
		try {
			//ȡ�������
			//1:��ѯ����;2:��ѯ���б�ҳ��
			s_page = ct.getParameter(request,"page");
			if (s_page ==null ) s_page = "1";
			if (s_page.equals("")) s_page = "1";
			s_flag = ct.getParameter(request,"flag");
			s_actiondo = ct.getParameter(request,"actiondo");
			if (s_flag.equals("search")) {
				s_page = "2";
			}
			//���ű��queryid			
			s_queryid = ct.getParameter(request,"queryid");

			//�ɹ�ת��ҳ��
			forward = mapping.findForward("success");
			log.info("���ò�ѯ:���ò�ѯ���Ƴ���Execute����ɹ�����");
		} catch (Exception e) {
			//�쳣����
			log.error("���ò�ѯ:δ֪�쳣����");
			e.printStackTrace();
			system.action.ErrorValue ev = new system.action.ErrorValue(); 
			ev.setError("CommSearchResult","execute","���ò�ѯ�����쳣����:" + e.getMessage());
			request.setAttribute("siev",ev);
			forward = mapping.findForward("failure");
			log.info("���ò�ѯ:���ò�ѯ���Ƴ���Execute����ʧ�ܻ���");
			return forward;
		}
		return forward;
	}

}
package chatroom;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.htyz.*;

public class BeanChat extends HttpServlet implements java.io.Serializable
{
	
	BeanChatRoom chatroom = new BeanChatRoom();
	BeanChatDetail chatDetail = new BeanChatDetail();
	//��ʼ��Servlet
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
	}
	
    public void doGet(HttpServletRequest req,
                      HttpServletResponse res)
        throws IOException, ServletException
	{
		doProcess(req, res);
	}

    public void doPost(HttpServletRequest req,
                      HttpServletResponse res)
        throws IOException, ServletException
	{
		doProcess(req, res);
	}
	
	//��ȡ����ֵ����
	//����˵��:
	//HttpServletRequest req: ServletRequest����
	//String parameterName: ��������
	//boolean checkRequestParameters: �Ƿ��ServletRequest��ȡ
	//boolean checkInitParameters���Ƿ��Servlet��ʼ�������л�ȡ
	//boolean isParameterRequired���ñ����ǲ��Ǳ��������Ǳ�������û���ҵ������׳��쳣
	//String defaultValue��û���ҵ�����ʱ����Ĭ��ֵ
	public java.lang.String getParameter(javax.servlet.http.HttpServletRequest request, java.lang.String parameterName, boolean checkRequestParameters, boolean checkInitParameters, boolean isParameterRequired, java.lang.String defaultValue) throws  java.lang.Exception
	{
		beanElearnTools ets = new beanElearnTools();
		java.lang.String[] parameterValues = null;
		java.lang.String paramValue = null;
		java.lang.String paramValue_GBK = null;

		//��request�л�ȡ����
		if (checkRequestParameters)
		{
			parameterValues = request.getParameterValues(parameterName);

			if (parameterValues != null)
				paramValue = parameterValues[0];
		}

		//ת��ΪGBK
		if (paramValue!=null)
		{
			paramValue_GBK = ets.getGBKString(paramValue);
			if (paramValue_GBK == null)
			{
				throw new Exception(parameterName + " ����ת��ΪGBK�����飡");
			}
			paramValue = paramValue_GBK;
		}
		
		//���request�в���Ϊnull,��servlet��ʼ�������л�ȡ
		if ( (checkInitParameters) && (paramValue == null) )
			paramValue = getServletConfig().getInitParameter(parameterName);

		//�������Ϊ���룬��û�л�ȡ�����׳��쳣
		if ( (isParameterRequired) && (paramValue == null) )
			throw new Exception(parameterName + " ����Ϊ���룬��û���ҵ������飡");

		//�������û���ҵ����򷵻�Ĭ��ֵ
		if (paramValue == null)
			paramValue = defaultValue;

		return paramValue;
	}

	//Servlet��ִ������	
	public void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
		String strLoginPage, strOkPage, strErrPage;
		try
		{
			strLoginPage = getParameter(req, "loginPage", false, true, true, "");
			strOkPage = getParameter(req, "okPage", false, true, true, "");
			strErrPage = getParameter(req, "errPage", false, true, true, "");
    		String s_userId, s_action, s_page, s_room;
    		beanElearnTools ets = new beanElearnTools(); 
    		beanGetdata bgd = new beanGetdata(); 
			
			boolean create = false; //�����Ƿ񴴽�session��ֻ����Login�в�Ϊtrue
			HttpSession session = req.getSession(create);

			//�ж�session�Ƿ����
			if(session == null)
			{
				req.setAttribute("message", "��û�е�¼��");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			//�ж��û��Ƿ��¼
			s_userId = (String)session.getAttribute("userid");
			if(s_userId == null)
			{
				req.setAttribute("message", "��û�е�¼��");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			s_action = getParameter(req, "action", true, false, true, "");
			s_room = getParameter(req, "room", true, false, true, "");
			s_page = getParameter(req, "showPage", false, true, true, "");
			
			//��¼
			if (s_action.equals("login"))
			{
				chatroom.setChatroom(s_room);
				chatroom.setUserId(s_userId);
				chatroom.addUser();
				chatDetail.setChatroom(s_room);
				String strLastTime = bgd.getDbDate();
				session.setAttribute(s_userId + "_chatLastTime_" + s_room, strLastTime);
				chatDetail.setLastTime(strLastTime);
				chatDetail.setUserId("ϵͳ����Ա");
				chatDetail.setContent(s_userId + "���������ң�");
				chatDetail.insert();
				chatDetail.setUserId(s_userId);
				s_page = getParameter(req, "loginRoom", false, true, true, "");
			}
			
			//����
			if (s_action.equals("speak"))
			{
				chatDetail.setChatroom(s_room);
				chatDetail.setUserId(s_userId);
				chatDetail.setContent(getParameter(req, "contentvalue", true, false, false, ""));
				chatDetail.setTalkto(getParameter(req, "talkto", true, false, false, ""));
				chatDetail.setStealthy(getParameter(req, "stealthy", true, false, false, "0"));
				chatDetail.insert();
				String strLastTime = (String)session.getAttribute(s_userId + "_chatLastTime_" + s_room);
				chatDetail.setLastTime(strLastTime);
				session.setAttribute(s_userId + "_chatLastTime_" + s_room, bgd.getDbDate());
//				req.setAttribute("message", "���Գɹ���");
//				s_page = getParameter(req, "okPage", false, true, true, "");

			}
			//��ʾ�����б�
			if (s_action.equals("onlineList"))
			{
				chatroom.setChatroom(s_room);
				chatroom.setUserId(s_userId);
				chatroom.updateLife();
				s_page = getParameter(req, "onlieListPage", false, true, true, "");
			}
			
			//��ʾ̸������
			if (s_action.equals("show"))
			{
				chatDetail.setChatroom(s_room);
				chatDetail.setUserId(s_userId);
				String strLastTime = (String)session.getAttribute(s_userId + "_chatLastTime_" + s_room);
				chatDetail.setLastTime(strLastTime);
				session.setAttribute(s_userId + "_chatLastTime_" + s_room, bgd.getDbDate());
			}
			
			//�˳�������
			if (s_action.equals("logout"))
			{
				chatroom.setChatroom(s_room);
				chatroom.setUserId(s_userId);
				chatroom.removeUser();
				chatDetail.setChatroom(s_room);
				chatDetail.setLastTime(bgd.getDbDate());
				chatDetail.setUserId("ϵͳ����Ա");
				chatDetail.setContent(s_userId + "�뿪�������ң�");
				chatDetail.insert();
				s_page = getParameter(req, "logoutPage", false, true, true, "");
			}
			req.setAttribute("room", s_room);
			req.setAttribute("chatDetail", chatDetail);
			req.setAttribute("chatroom", chatroom);
			getServletConfig().getServletContext().getRequestDispatcher(s_page).forward(req, res);
			return;
		}
		catch(Exception ex)
		{
			try
			{
				ex.printStackTrace();
				strErrPage = getParameter(req, "errPage", false, true, true, "");
				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception e)
			{
				System.err.println("BeanChat���ô���ҳ��ʱ����");
				e.printStackTrace();
			}
		}
    }
}
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
	//初始化Servlet
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
	
	//获取参数值函数
	//参数说明:
	//HttpServletRequest req: ServletRequest对象
	//String parameterName: 参数名称
	//boolean checkRequestParameters: 是否从ServletRequest获取
	//boolean checkInitParameters：是否从Servlet初始化变量中获取
	//boolean isParameterRequired：该变量是不是必须项，如果是必须项且没有找到，则抛出异常
	//String defaultValue；没有找到变量时，赋默认值
	public java.lang.String getParameter(javax.servlet.http.HttpServletRequest request, java.lang.String parameterName, boolean checkRequestParameters, boolean checkInitParameters, boolean isParameterRequired, java.lang.String defaultValue) throws  java.lang.Exception
	{
		beanElearnTools ets = new beanElearnTools();
		java.lang.String[] parameterValues = null;
		java.lang.String paramValue = null;
		java.lang.String paramValue_GBK = null;

		//从request中获取参数
		if (checkRequestParameters)
		{
			parameterValues = request.getParameterValues(parameterName);

			if (parameterValues != null)
				paramValue = parameterValues[0];
		}

		//转换为GBK
		if (paramValue!=null)
		{
			paramValue_GBK = ets.getGBKString(paramValue);
			if (paramValue_GBK == null)
			{
				throw new Exception(parameterName + " 不能转换为GBK，请检查！");
			}
			paramValue = paramValue_GBK;
		}
		
		//如果request中参数为null,从servlet初始化参数中获取
		if ( (checkInitParameters) && (paramValue == null) )
			paramValue = getServletConfig().getInitParameter(parameterName);

		//如果参数为必须，且没有获取，则抛出异常
		if ( (isParameterRequired) && (paramValue == null) )
			throw new Exception(parameterName + " 参数为必须，但没有找到！请检查！");

		//如果参数没有找到，则返回默认值
		if (paramValue == null)
			paramValue = defaultValue;

		return paramValue;
	}

	//Servlet的执行内容	
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
			
			boolean create = false; //决定是否创建session，只有在Login中才为true
			HttpSession session = req.getSession(create);

			//判断session是否存在
			if(session == null)
			{
				req.setAttribute("message", "您没有登录！");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			//判断用户是否登录
			s_userId = (String)session.getAttribute("userid");
			if(s_userId == null)
			{
				req.setAttribute("message", "您没有登录！");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			s_action = getParameter(req, "action", true, false, true, "");
			s_room = getParameter(req, "room", true, false, true, "");
			s_page = getParameter(req, "showPage", false, true, true, "");
			
			//登录
			if (s_action.equals("login"))
			{
				chatroom.setChatroom(s_room);
				chatroom.setUserId(s_userId);
				chatroom.addUser();
				chatDetail.setChatroom(s_room);
				String strLastTime = bgd.getDbDate();
				session.setAttribute(s_userId + "_chatLastTime_" + s_room, strLastTime);
				chatDetail.setLastTime(strLastTime);
				chatDetail.setUserId("系统公告员");
				chatDetail.setContent(s_userId + "进入聊天室！");
				chatDetail.insert();
				chatDetail.setUserId(s_userId);
				s_page = getParameter(req, "loginRoom", false, true, true, "");
			}
			
			//发言
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
//				req.setAttribute("message", "发言成功！");
//				s_page = getParameter(req, "okPage", false, true, true, "");

			}
			//显示在线列表
			if (s_action.equals("onlineList"))
			{
				chatroom.setChatroom(s_room);
				chatroom.setUserId(s_userId);
				chatroom.updateLife();
				s_page = getParameter(req, "onlieListPage", false, true, true, "");
			}
			
			//显示谈话内容
			if (s_action.equals("show"))
			{
				chatDetail.setChatroom(s_room);
				chatDetail.setUserId(s_userId);
				String strLastTime = (String)session.getAttribute(s_userId + "_chatLastTime_" + s_room);
				chatDetail.setLastTime(strLastTime);
				session.setAttribute(s_userId + "_chatLastTime_" + s_room, bgd.getDbDate());
			}
			
			//退出聊天室
			if (s_action.equals("logout"))
			{
				chatroom.setChatroom(s_room);
				chatroom.setUserId(s_userId);
				chatroom.removeUser();
				chatDetail.setChatroom(s_room);
				chatDetail.setLastTime(bgd.getDbDate());
				chatDetail.setUserId("系统公告员");
				chatDetail.setContent(s_userId + "离开了聊天室！");
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
				System.err.println("BeanChat调用错误页面时出错！");
				e.printStackTrace();
			}
		}
    }
}
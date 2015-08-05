package system;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.htyz.*;

public class beanUserList extends HttpServlet implements java.io.Serializable
{

   	private	beanGetdata bgd = new beanGetdata();
	private boolean reload = false;
	
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

		//如果request中参数为null,从servlet初始化参数中获取
		if ( (checkInitParameters) && (paramValue == null) )
			paramValue = getServletConfig().getInitParameter(parameterName);

		//如果参数为必须，且没有获取，则抛出异常
		if ( (isParameterRequired) && (paramValue == null) )
			throw new Exception(parameterName + " 参数为必须，但没有找到！请检查！");

		//如果参数没有找到，则返回默认值
		if (paramValue == null)
			paramValue = defaultValue;

		//转换为GBK
		paramValue_GBK = ets.getGBKString(paramValue);
		if (paramValue_GBK == null)
		{
			throw new Exception(parameterName + " 不能转换为GBK，请检查！");
		}
		paramValue = paramValue_GBK;

		return paramValue;
	}

	//Servlet的执行内容	
	public void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
		String strLoginPage, strPage, strErrPage;
		try
		{
			strPage = getParameter(req, "page", false, true, true, "");
			strLoginPage = getParameter(req, "loginPage", false, true, true, "");
			String s_userId,
				   s_pageNo, 
				   s_queryId, 
				   s_queryColoum,
				   s_reload,
				   s_sql;
    		beanElearnTools ets = new beanElearnTools(); 
			
			boolean create = false; //决定是否创建session，只有在Login中才为true
			HttpSession session = req.getSession(create);
			s_userId = (String)session.getAttribute("userid");
			
			//判断session是否存在
			if(session == null||s_userId == null)
			{
				req.setAttribute("message", "您没有登录！");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			
			//取得参数
			s_pageNo = getParameter(req, "pageno", true, true, false, "1");
			s_reload = getParameter(req, "reload", true, true, false, "false");
			s_queryId = getParameter(req, "queryid", true, true, false, "");
			
			reload = s_reload.equals("true");
			
			if (s_pageNo.equals("1")||reload)
			{
				if (s_queryId.equals(""))
				{
					s_sql = "SELECT * FROM t_user where user_status = '1' order by user_id";
					if (ets.isAdmin(s_userId))
						s_sql = "SELECT * FROM t_user order by user_id";
				}
				else
				{
					s_queryColoum = getParameter(req, "querycoloum", true, true, false, "");
					if (s_queryColoum.equals("group_id"))
					{
						s_sql = "SELECT * FROM t_user u, t_code o";
						s_sql = s_sql + " where o.code_namec like '%" + s_queryId + "%' ";
						s_sql = s_sql + " and u.group_id = o.code_value and o.code_id = '0002'";
						if (ets.isAdmin(s_userId))
						{
							s_sql = s_sql + " order by u.user_id";
						}
						else
						{
							s_sql = s_sql + " and u.user_status = '1' order by u.user_id";
						}
					}
					else
					{
						s_sql = "SELECT * FROM t_user WHERE " + s_queryColoum + " like '%" + s_queryId + "%' ";
						if (ets.isAdmin(s_userId))
						{
							s_sql = s_sql + " order by user_id";
						}
						else
						{
							s_sql = s_sql + " and user_status = '1' order by user_id";
						}
					}
				}
				bgd.executeSelect(s_sql);
			}
			req.setAttribute("bgd", bgd);
			req.setAttribute("pageno", s_pageNo);
			getServletConfig().getServletContext().getRequestDispatcher(strPage).forward(req, res);
			return;
		}
		catch(Exception ex)
		{
			try
			{
				strErrPage = getParameter(req, "errPage", false, true, true, "");
				System.err.println("beanUserList error: " + ex);
				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception e)
			{
				System.err.println("beanUserList调用错误页面时出错！");
				e.printStackTrace();
			}
		}
    }
    
}
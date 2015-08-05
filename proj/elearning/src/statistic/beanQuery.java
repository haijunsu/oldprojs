
package statistic;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.htyz.*;

public class beanQuery extends HttpServlet implements java.io.Serializable
{
	private beanGetdata bgd = new beanGetdata();
	boolean reload = false;
	
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
		try
		{
			String s_userId,
				   s_pageNo,
				   s_reload,
				   s_open,
				   s_action,
				   s_queryItem,
				   s_filter,
				   s_order,
				   s_keywords,
				   s_message,
				   s_navigation,
				   s_catalog,
				   s_page,
				   s_listPage,
				   s_countInPage,
				   s_sql;
			int i_count, i_countInPage;
	   		beanElearnTools ets = new beanElearnTools(); 
	   		beanQueryCodes bqc = new beanQueryCodes();
			
			boolean create = false; //决定是否创建session，只有在Login中才为true
			HttpSession session = req.getSession(create);

			//判断session是否存在
			if(session == null)
			{
				req.setAttribute("message", "您没有登录！");
				getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
				return;
			}
			
			//判断用户是否登录
			s_userId = (String)session.getAttribute("userid");
			if(s_userId == null)
			{
				req.setAttribute("message", "您没有登录！");
				getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
				return;
			}
			
			//取得参数
			s_action = getParameter(req, "action", true, false, false, "query");
			s_open = getParameter(req, "open", true, false, false, "0");
			s_queryItem = getParameter(req, "queryItem", true, false, false, "");
			s_filter =  getParameter(req, "filter", true, false, false, "");
			
			req.setAttribute("queryItem", s_queryItem);
			s_navigation = "<A href=\"" + req.getContextPath() + "/\" target=\"_top\">首页</A>"
							+ "&gt;<A href=\"query?queryItem=" + s_queryItem + "\" target=\"_self\">";
			
				
			if (s_action.equalsIgnoreCase("showOk"))
			{
				s_message = getParameter(req, "message", true, false, false, "");
				s_message = s_message + "操作成功！";
				req.setAttribute("message", s_message);
				getServletConfig().getServletContext().getRequestDispatcher("/Ok.jsp").forward(req, res);
				return;
			}
			
			if (s_action.equalsIgnoreCase("showErr"))
			{
				s_message = getParameter(req, "message", true, false, false, "");
				s_message = s_message + "操作失败！";
				req.setAttribute("errmsg", s_message);
				getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
				return;
			}
			
			if (s_action.equalsIgnoreCase("query"))
			{
				if (s_queryItem.equals("log"))
				{
					s_navigation = s_navigation + "登录查询</A>";
				s_order = getParameter(req, "order", true, false, false, "login_time");
				s_pageNo = getParameter(req, "pageno", true, false, false, "");
				s_reload = getParameter(req, "reload", true, false, false, "");
				String s_orderBy="";
				String s_whereClause="";
				if (s_pageNo.equals("")) s_pageNo = "1";
				
				reload = s_reload.equals("true");
				
				if(ets.isAdmin(s_userId))
				{
					if(s_filter==""||s_filter.equals(null))
					{
						s_whereClause="";
						s_catalog = "所有用户";
						s_navigation = s_navigation + "&gt;<a href=\"query?queryItem=" + s_queryItem + "\" target=\"_self\">所有用户的登录记录</a>";
					}else{
						s_whereClause=" Where user_id like'%"+s_filter+"%'";
						s_catalog = s_filter;
						s_navigation = s_navigation +  "&gt;<a href=\"query?queryItem=" + s_queryItem + "&filter="+s_filter+"\" target=\"_self\">用户"+s_filter+"的登录记录</a>";
					}

				}
				else{
					 s_whereClause=" Where user_id='"+s_userId+"'";
					 s_catalog = s_userId;
				}
				if(s_order==""){
					s_orderBy=" ";
				} else{
					s_orderBy=" ORDER BY "+s_order;
				}
				
				s_sql = "SELECT * FROM t_user_log "+s_whereClause+s_orderBy;
				if (s_pageNo.equals("1")||reload)
				{
	//				System.out.println("刷新数据");
					bgd.executeSelect(s_sql);
					if (reload) reload = false;
				}
				req.setAttribute("bgd", bgd);
				req.setAttribute("navigation", s_navigation);
				req.setAttribute("catalog", s_catalog);
				req.setAttribute("queryItem", s_queryItem);
				req.setAttribute("filter", s_filter);
				req.setAttribute("order", s_order);
				req.setAttribute("pageno", s_pageNo);
				req.setAttribute("action", s_action);
				s_listPage = getParameter(req, "listPage", false, true, false, "/statistic/logHistory.jsp");
				getServletConfig().getServletContext().getRequestDispatcher(s_listPage).forward(req, res);
				return;
			}
//以下为通讯录列表
				if (s_queryItem.equals("address"))
				{
					s_navigation = s_navigation + "地址簿</A>";
					s_order = getParameter(req, "order", true, false, false, "addr_id");
					s_pageNo = getParameter(req, "pageno", true, false, false, "");
					s_reload = getParameter(req, "reload", true, false, false, "");
					String s_orderBy="";
					String s_whereClause="";
					if (s_pageNo.equals("")) s_pageNo = "1";
				
					reload = s_reload.equals("true");
				
					if(ets.isAdmin(s_userId))
					{
						if(s_filter==""||s_filter.equals(null))
						{
							s_whereClause="";
							s_catalog = "所有用户";
							s_navigation = s_navigation + "&gt;<a href=\"query?queryItem=" + s_queryItem + "\" target=\"_self\">所有用户的地址簿</a>";
						}else{
							s_whereClause=" Where owner ='"+s_userId+"'";
							s_catalog = s_filter;
							s_navigation = s_navigation +  "&gt;<a href=\"query?queryItem=" + s_queryItem + "&filter="+s_filter+"\" target=\"_self\">用户"+s_filter+"的登录记录</a>";
						}

					}
					else{
						s_whereClause=" Where owner='"+s_userId+"'";
						s_catalog = s_userId;
					}
					if(s_order==""){
						s_orderBy=" ";
					} else{
						s_orderBy=" ORDER BY "+s_order;
					}
					s_sql = "SELECT * FROM t_addr_book "+s_whereClause+s_orderBy;
//				System.out.println(s_sql);
					if (s_pageNo.equals("1")||reload)
					{
	//				System.out.println("刷新数据");
						bgd.executeSelect(s_sql);
						if (reload) reload = false;
					}
					req.setAttribute("bgd", bgd);
					req.setAttribute("navigation", s_navigation);
					req.setAttribute("catalog", s_catalog);
					req.setAttribute("queryItem", s_queryItem);
					req.setAttribute("filter", s_filter);
					req.setAttribute("order", s_order);
					req.setAttribute("pageno", s_pageNo);
					req.setAttribute("action", s_action);
					s_listPage = getParameter(req, "listPage", false, true, false, "/mail/webmail/folder/address.jsp");
					getServletConfig().getServletContext().getRequestDispatcher(s_listPage).forward(req, res);
					return;
				}
			}
			
			if (s_action.equals("list")) s_action = "showDetail";
			req.setAttribute("action", s_action);
			req.setAttribute("flag", s_open);
			s_page = getParameter(req, "page", false, true, true, "/lesson/lessonApplyTree.jsp");
			
			getServletConfig().getServletContext().getRequestDispatcher(s_page).forward(req, res);
			return;
		}
		catch(Exception ex)
		{
			try
			{
				System.err.println("beanQuery error: ");
				ex.printStackTrace();
				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
				return;
			}
			catch(Exception e)
			{
				System.err.println("beanQuery调用错误页面时出错！");
				e.printStackTrace();
			}
		}
    }
}
package system;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.htyz.*;

public class beanMenuList extends HttpServlet implements java.io.Serializable
{
	private beanGetdata bgd = new beanGetdata();
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
		String strPage, strLoginPage, strOkPage, strErrPage;
		try
		{
			String s_userId,
				   s_pageNo, 
				   s_queryId, 
				   s_reload,
				   s_action,
				   s_menuId,
				   s_oldMenuId,
				   s_menuNamec,
				   s_menuNamee,
				   s_menuUrl,
				   s_menuRight,
				   s_rightCount,
				   s_right[],
				   s_message,
				   s_sql;
			int i_count;
    		beanElearnTools ets = new beanElearnTools(); 
			beanMenuDetail bmd = new beanMenuDetail();
			
			strLoginPage = getParameter(req, "loginPage", false, true, true, "");
			strOkPage = getParameter(req, "okPage", false, true, true, "");
			strErrPage = getParameter(req, "errPage", false, true, true, "");
			strPage = getParameter(req, "page", false, true, true, "");
			
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
			
			if (!ets.isAdmin(s_userId))
				throw new Exception("你没有执行此操作的权限！");
			
			//取得参数
			s_action = getParameter(req, "action", true, false, false, "list");
			s_pageNo = getParameter(req, "pageno", true, false, false, "1");
			s_reload = getParameter(req, "reload", true, false, false, "false");
			s_queryId = getParameter(req, "queryid", true, false, false, "");
			
			s_message = "";
			
			if (s_pageNo == null)
				s_pageNo = "1";
				
			if (s_action.equalsIgnoreCase("showOk"))
			{
				s_message = getParameter(req, "message", true, false, false, "");
				s_message = s_message + "操作成功！";
				req.setAttribute("message", s_message);
				getServletConfig().getServletContext().getRequestDispatcher(strOkPage).forward(req, res);
				return;
			}
			
			if (s_action.equalsIgnoreCase("showErr"))
			{
				s_message = getParameter(req, "message", true, false, false, "");
				s_message = s_message + "操作失败！";
				req.setAttribute("errmsg", s_message);
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}

			if ( s_action.equalsIgnoreCase("showDetail"))
			{
				s_menuId = getParameter(req, "menuid", true, false, true, "");
				for (int i=0; i<bgd.getRowCount(); i++)
				{
					if (s_menuId.equals(bgd.getFieldValue("menu_id", i)))
					{
						bmd.setMenuId(bgd.getFieldValue("menu_id", i));
						bmd.setMenuNamec(bgd.getFieldValue("menu_namec", i));
						bmd.setMenuNamee(bgd.getFieldValue("menu_namee", i));
						bmd.setMenuUrl(bgd.getFieldValue("menu_url", i));
						bmd.setMenuRight(bgd.getFieldValue("menu_right", i));
						break;
					}
				}
				req.setAttribute("action", "");
				req.setAttribute("bmd", bmd);
				req.setAttribute("pageno", s_pageNo);
				strPage = getParameter(req, "detailPage", false, true, true, "");
				getServletConfig().getServletContext().getRequestDispatcher(strPage).forward(req, res);
				return;
			}
			if (s_action.equalsIgnoreCase("new"))
			{
				req.setAttribute("action", "add");
				req.setAttribute("bmd", bmd);
				req.setAttribute("pageno", s_pageNo);
				strPage = getParameter(req, "detailPage", false, true, true, "");
				getServletConfig().getServletContext().getRequestDispatcher(strPage).forward(req, res);
				return;
			}
			if (s_action.equalsIgnoreCase("delete"))
			{
				s_oldMenuId = getParameter(req, "oldmenuid", true, false, true, "");
				s_sql = "DELETE FROM t_menu WHERE menu_id = '" + s_oldMenuId + "'";
				s_action = "showOk";
				s_message = "删除";
					
				if (bgd.execute(s_sql) == -1)
				{
					s_action = "showErr";
					s_message = "删除";
				}
				req.setAttribute("message", s_message);
			}
			if (s_action.equalsIgnoreCase("add")||s_action.equalsIgnoreCase("update"))
			{
				reload = true;
				s_menuId = getParameter(req, "menuid", true, false, true, "");
				s_menuNamec = getParameter(req, "menunamec", true, false, false, "");
				s_menuNamee = getParameter(req, "menunamee", true, false, false, "");
				s_menuUrl = getParameter(req, "menuurl", true, false, false, "");
				s_rightCount = getParameter(req, "rightcount", true, false, true, "");
				i_count = Integer.parseInt(s_rightCount);
				s_right = new String[i_count];
                for (int k = 0; k < i_count; k++)
                    s_right[k] = getParameter(req, "menuright" + Integer.toString(k), true, false, false, "0");

				s_menuRight = ets.parseRight(s_right);
				
				if (s_menuNamec.equals("")) 
					s_menuNamec = "空";
				bmd.setMenuId(s_menuId);
				bmd.setMenuNamec(s_menuNamec);
				bmd.setMenuNamee(s_menuNamee);
				bmd.setMenuUrl(s_menuUrl);
				bmd.setMenuRight(s_menuRight);
				
				if (s_action.equalsIgnoreCase("add"))
				{
					s_sql = "SELECT * FROM t_menu WHERE menu_id = '" + s_menuId + "'";
					bgd.executeSelect(s_sql);
					if (bgd.getRowCount()>0)
					{
						s_action = "showErr";
						s_message = "菜单编号" + s_menuId + "已存在! 添加";
					}
					else
					{
						s_message = "添加";
						s_sql = "INSERT t_menu VALUES ('"
								+ s_menuId + "', '"
								+ s_menuNamec + "', '"
								+ s_menuNamee + "', '"
								+ s_menuUrl + "', "
								+ s_menuRight + ")";
						if (bgd.execute(s_sql) == -1)
							s_action = "showErr";
						else
							s_action = "showOk";
					}
				}
				else
				{
					s_oldMenuId = getParameter(req, "oldmenuid", true, false, true, "");
					s_sql = "UPDATE t_menu SET "
					        + "menu_id = '" + s_menuId + "', "
					        + "menu_namec = '" + s_menuNamec + "', "
					        + "menu_namee = '" + s_menuNamee + "', "
					        + "menu_url = '" + s_menuUrl + "', "
					        + "menu_right = " + s_menuRight + " "
					        + " WHERE menu_id = '" + s_oldMenuId + "'";
					s_message = "更新";
					if (bgd.execute(s_sql) == -1)
						s_action = "showErr";
					else
						s_action = "showOk";
				}
				req.setAttribute("menuid", s_menuId);
				req.setAttribute("message", s_message);
			}
			
			reload = s_reload.equals("true");
			
			if (s_pageNo.equals("1")||reload)
			{
				if (s_queryId.equals(""))
				{
					s_sql = "SELECT * FROM t_menu ORDER BY menu_id";
				}
				else
				{
					s_sql = "SELECT * FROM t_menu WHERE menu_namec LIKE '%" + s_queryId + "%' ORDER BY menu_id";
				}
				bgd.executeSelect(s_sql);
				reload = false;
			}
			if(s_action.equals("list")) s_action = "showDetail";
			req.setAttribute("action", s_action);
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
				System.err.println("beanMenuList error: " + ex);
				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception e)
			{
				System.err.println("beanMenuList调用错误页面时出错！");
				e.printStackTrace();
			}
		}
    }
}
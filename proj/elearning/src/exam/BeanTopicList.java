
package exam;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.htyz.*;
import com.htyz.system.SystemProperties;

public class BeanTopicList extends HttpServlet implements java.io.Serializable
{
	private BeanTopicQuestion btq = new BeanTopicQuestion();
	private	beanGetdata bgd = new beanGetdata();
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
		beanElearnTools ets = new beanElearnTools();
		String strLoginPage, strOkPage, strErrPage;
		try
		{
			strLoginPage = getParameter(req, "loginPage", false, true, true, "");
			strOkPage = getParameter(req, "okPage", false, true, true, "");
			strErrPage = getParameter(req, "errPage", false, true, true, "");
			String s_userId, s_action, s_whereType;

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
			
			//判断用户是不是课程管理员
			if (!ets.isRole(s_userId, "3"))
				throw new Exception("你没有执行此操作的权限！");
				
    		beanQueryCodes bqcTypes = new beanQueryCodes();
    		beanQueryCodes bqcStatus = new beanQueryCodes();
			bqcTypes.QueryCode("0004");
			bqcStatus.QueryCode("0005");
			req.setAttribute("bqcTypes", bqcTypes);
			req.setAttribute("bqcStatus", bqcStatus);
			
			s_whereType = "";

			//取得参数
			s_action = getParameter(req, "action", true, false, false, "list");
			
			//显示TREE
			if (s_action.equalsIgnoreCase("list")) {
				String strAddsign = SystemProperties.getProperty("db.addsign");
				String s_open =  getParameter(req, "open", true, false, false, "0");
				String s_sql = "SELECT code_id, code_value, code_namec, code_namee, 'topicList?action=showDetail&codeid='" + strAddsign + "rtrim(code_id)" + strAddsign + "'&codevalue='" + strAddsign + "code_value AS url FROM t_code WHERE code_id = '0013' OR code_id in (SELECT code_value FROM t_code WHERE code_id = '0013')";
				
				req.setAttribute("flag", s_open);
				req.setAttribute("codeid", "0013");
				req.setAttribute("codevalue", "9000");
				req.setAttribute("action", "showDetail");				
				req.setAttribute("treename", "topicList");
				req.setAttribute("treepage", "topicList");
				req.setAttribute("sql", s_sql);
				req.setAttribute("treeroot", "0013");
				String s_display = getParameter(req, "treePage", false, true, true, "");
				getServletConfig().getServletContext().getRequestDispatcher(s_display).forward(req, res);
				return;
			}
			
			
			//显示课程明细
			if ( s_action.equalsIgnoreCase("showDetail"))
			{
				String s_display, s_codeId, s_codeValue, s_pageNo, s_sql;
				s_codeId = getParameter(req, "codeid", true, false, true, "");
				s_codeValue = getParameter(req, "codevalue", true, false, true, "");
				s_pageNo = getParameter(req, "pageno", true, false, false, "");
				
				if (s_pageNo.equals("")) s_pageNo = "1";
				
				
				if (s_codeId.equals("0013"))
				{
					s_sql = "SELECT * FROM t_code WHERE code_id = '" + s_codeValue + "'";
					s_display = getParameter(req, "typePage", false, true, true, "/exam/topicType.jsp");
					bgd.executeSelect(s_sql);
				}
				else
				{
					s_sql = "SELECT * FROM t_class WHERE " + SystemProperties.getProperty("db.substr") + "(class_id, 1, 8) = '" + s_codeId + s_codeValue + "' " + s_whereType
							+ " ORDER BY class_id";
					s_display = getParameter(req, "listPage", false, true, true, "/exam/topicList.jsp");
					if (s_pageNo.equals("1")||bgd.getRowCount()==0)
					{
						System.out.println("刷新数据");
						bgd.executeSelect(s_sql);
					}
				}
				req.setAttribute("bgd", bgd);
				req.setAttribute("navigation", getClassNavigation(s_codeId, s_codeValue, req.getContextPath()));
				req.setAttribute("codeid", s_codeId);
				req.setAttribute("codevalue", s_codeValue);
				req.setAttribute("pageno", s_pageNo);
				req.setAttribute("action", s_action);
				
				getServletConfig().getServletContext().getRequestDispatcher(s_display).forward(req, res);
				return;
			}

			//查询
			if (s_action.equalsIgnoreCase("query"))
			{
				String s_display, s_codeId, s_codeValue, s_pageNo;
				String s_queryId, s_queryColoum, s_sql, s_navigation;
				s_queryId = getParameter(req, "queryid", true, false, false, "");
				s_queryColoum = getParameter(req, "querycoloum", true, false, true, "");
				
				s_codeId = getParameter(req, "codeid", true, false, false, "");
				s_codeValue = getParameter(req, "codevalue", true, false, false, "");
				s_pageNo = getParameter(req, "pageno", true, false, false, "");
				s_navigation = getClassNavigation(req.getContextPath()) + ">搜索结果";
				
				if (s_pageNo.equals("")) s_pageNo = "1";
				
				
				s_sql = "SELECT * FROM t_class WHERE " + s_queryColoum + " like '%" + s_queryId + "%' "
							+ " AND t_class.class_id not in (select class_id from t_user_class uc, t_user u where uc.user_id = u.group_id and u.user_id = '" + s_userId + "') "
							+ s_whereType +" ORDER BY t_class.class_id";
				if (s_queryColoum.equals("class_type"))
				{
					s_sql = "SELECT t_class.* FROM t_class, t_code WHERE t_class.class_type = t_code.code_value AND "
							+ " (t_class.class_type like '%" + s_queryId + "%' OR "
							+ " (t_code.code_id = '0004' AND t_code.code_namec like '%" + s_queryId + "%')) "
							+ s_whereType +" ORDER BY t_class.class_id";
				}
				bgd.executeSelect(s_sql);
				if (s_pageNo.equals("1")||bgd.getRowCount()==0)
				{
					System.out.println("刷新数据");
					bgd.executeSelect(s_sql);
				}
				s_codeValue = s_codeValue + "&queryid=" + s_queryId + "&querycoloum=" + s_queryColoum;//增加查询参数
				req.setAttribute("bgd", bgd);
				req.setAttribute("navigation", s_navigation);
				req.setAttribute("codeid", s_codeId);
				req.setAttribute("codevalue", s_codeValue);
				req.setAttribute("pageno", s_pageNo);
				req.setAttribute("action", s_action);
				s_display = getParameter(req, "listPage", false, true, true, "/exam/topicList.jsp");
				
				getServletConfig().getServletContext().getRequestDispatcher(s_display).forward(req, res);
				return;
			}
			
		}
		catch(Exception ex)
		{
			try
			{
				System.err.println("BeanTopicList error: ");
				ex.printStackTrace();
				strErrPage = getParameter(req, "errPage", false, true, true, "");
				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception e)
			{
				System.err.println("BeanTopicList调用错误页面时出错！");
				e.printStackTrace();
			}
		}
    }

    //课程管理导航
	private String getClassNavigation(String contextPath)
	{
		String s_navigation = "<A href=\"" + contextPath + "/\" target=\"_top\">首页</A>"
						+ "&gt;<A href=\"topicManager\" target=\"_parent\">试题管理</A>";
		return s_navigation;
	}
		
    	 
	private String getClassNavigation(String codeId, String codeValue, String contextPath)
	{
		beanQueryCodes bqc = new beanQueryCodes();
		
		String s_navigation = getClassNavigation(contextPath);
		
		if (!codeId.equals("0013"))
		{
			bqc.QueryCode("0013", codeId);
			s_navigation = s_navigation + "&gt;<A href=\"topicList?action=showDetail&codeid=0013&codevalue=" + codeId + "\">" + bqc.getCodeValue("code_namec", 0) + "</A>";
		}
		
		bqc.QueryCode(codeId, codeValue);
		s_navigation = s_navigation + "&gt;" + bqc.getCodeValue("code_namec", 0);
		
		return s_navigation; 
	}
	
}
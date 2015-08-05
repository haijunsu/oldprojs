package system;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.htyz.*;

public class beanCodeList extends HttpServlet implements java.io.Serializable
{
	private beanGetdata bgd = new beanGetdata();
	private String root = "0000";
	
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
		String strLoginPage, strPage, strDetailPage, strOkPage, strErrPage;
		try
		{
			String s_userId,
				   s_open,
				   s_action,
				   s_codeId,
				   s_codeValue,
				   s_oldCodeId,
				   s_oldCodeValue,
				   s_codeNamec,
				   s_codeNamee,
				   s_message,
				   s_treeRoot,
				   s_sql;
			int i_count;
    		beanElearnTools ets = new beanElearnTools(); 
    		beanQueryCodes bqc = new beanQueryCodes();
			
			strLoginPage = getParameter(req, "loginPage", false, true, true, "");
			strPage = getParameter(req, "page", false, true, true, "");
			strDetailPage = getParameter(req, "detailPage", false, true, true, "");
			strOkPage = getParameter(req, "okPage", false, true, true, "");
			strErrPage = getParameter(req, "errPage", false, true, true, "");

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
			
			//判断用户是不是管理员
			if (!ets.isAdmin(s_userId))
				throw new Exception("你没有执行此操作的权限！");
				
			
			//取得参数
			s_action = getParameter(req, "action", true, false, false, "list");
			s_open = getParameter(req, "open", true, false, false, "0");
			s_treeRoot = getParameter(req, "root", true, false, false, "");
	//		System.out.println(s_treeRoot);
			
			if (!s_treeRoot.equals("")) this.root = s_treeRoot;
	//		System.out.println(this.root);
			
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
				beanCodeDetail bcd = new beanCodeDetail();
				s_codeId = getParameter(req, "codeid", true, false, true, "");
				s_codeValue = getParameter(req, "codevalue", true, false, true, "");
				bqc.QueryCode(s_codeId, s_codeValue);
				
				bcd.setCodeId(bqc.getCodeValue("code_id", 0));
				bcd.setCodeValue(bqc.getCodeValue("code_value", 0));
				bcd.setCodeNamec(bqc.getCodeValue("code_namec", 0));
				bcd.setCodeNamee(bqc.getCodeValue("code_namee", 0));
				
				req.setAttribute("action", "");
				req.setAttribute("bcd", bcd);
				getServletConfig().getServletContext().getRequestDispatcher(strDetailPage).forward(req, res);
				return;
			}
			if (s_action.equalsIgnoreCase("new"))
			{
				beanCodeDetail bcd = new beanCodeDetail();
				req.setAttribute("action", "add");
				req.setAttribute("bcd", bcd);
				getServletConfig().getServletContext().getRequestDispatcher(strDetailPage).forward(req, res);
				return;
			}
			if (s_action.equalsIgnoreCase("delete"))
			{
				s_oldCodeId = getParameter(req, "oldcodeid", true, false, true, "");
				s_oldCodeValue = getParameter(req, "oldcodevalue", true, false, true, "");
				s_sql = "DELETE FROM t_code  WHERE code_id = '" + s_oldCodeId + "'"
						+ " AND code_value = '" + s_oldCodeValue + "' ";
				if (s_oldCodeId.equals("0000"))
					s_sql = s_sql + " OR code_id = '" + s_oldCodeValue + "'";
				
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
				beanCodeDetail bcd = new beanCodeDetail();
				s_codeId = getParameter(req, "codeid", true, false, true, "");
				s_codeValue = getParameter(req, "codevalue", true, false, true, "");
				s_codeNamec = getParameter(req, "codenamec", true, false, false, "");
				s_codeNamee = getParameter(req, "codenamee", true, false, false, "");
				
				if (s_codeNamec.equals("")) s_codeNamec = "空";
				
				bcd.setCodeId(s_codeId);
				bcd.setCodeValue(s_codeValue);
				bcd.setCodeNamec(s_codeNamec);
				bcd.setCodeNamee(s_codeNamee);
								
				if (s_action.equalsIgnoreCase("add"))
				{
					s_sql = "SELECT * FROM t_code WHERE code_id = '" + s_codeId + "'"
							+ " AND code_value = '" + s_codeValue + "'";
					bgd.executeSelect(s_sql);
					if (bgd.getRowCount()>0)
					{
						s_action = "showErr";
						s_message = "节点" + s_codeId + "已经存在" + s_codeValue + "代码值!添加";
					}
					else
					{
						s_message = "添加";
						s_sql = "INSERT INTO t_code VALUES ('"
								+ s_codeId + "', '"
								+ s_codeValue + "', '"
								+ s_codeNamec + "', '"
								+ s_codeNamee + "' )";
						if (bgd.execute(s_sql) == -1)
							s_action = "showErr";
						else
							s_action = "showOk";
					}
				}
				else
				{
					s_oldCodeId = getParameter(req, "oldcodeid", true, false, true, "");
					s_oldCodeValue = getParameter(req, "oldcodevalue", true, false, true, "");
					s_sql = "UPDATE t_code SET "
					        + "code_id = '" + s_codeId + "', "
					        + "code_value = '" + s_codeValue + "', "
					        + "code_namec = '" + s_codeNamec + "', "
					        + "code_namee = '" + s_codeNamee + "' "
					        + " WHERE code_id = '" + s_oldCodeId + "'"
					        + " AND code_value = '" + s_oldCodeValue + "'";
					s_message = "更新";
					if (bgd.execute(s_sql) == -1)
						s_action = "showErr";
					else
						s_action = "showOk";
				}
				req.setAttribute("message", s_message);
			}
			
			if (s_action.equals("list")) s_action = "showDetail";
			req.setAttribute("action", s_action);
			req.setAttribute("flag", s_open);
			req.setAttribute("treeroot", this.root);
			
	//		System.out.println(root);
			
			getServletConfig().getServletContext().getRequestDispatcher(strPage).forward(req, res);
			return;
		}
		catch(Exception ex)
		{
			try
			{
				strErrPage = getParameter(req, "errPage", false, true, true, "");
				System.err.println("beanCodeList error: " + ex);
				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception e)
			{
				System.err.println("beanCodeList调用错误页面时出错！");
				e.printStackTrace();
			}
		}
    }
}
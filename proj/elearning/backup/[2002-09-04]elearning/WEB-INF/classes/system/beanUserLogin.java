package system;

import javax.servlet.*;
import javax.servlet.http.*;
import com.htyz.*;

public class beanUserLogin extends HttpServlet
{

	protected beanElearnTools ets = new beanElearnTools();

	//初始化Servlet
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
	}

	//执行Post方法
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
		doProcess(req, res);
	}

	//执行Get方法
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException
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
		java.lang.String[] parameterValues = null;
		java.lang.String paramValue = null;
		java.lang.String paramValue_GBK = null;

		//从request中获取参数
		if (checkRequestParameters)
		{
			parameterValues = request.getParameterValues(parameterName);

			if (parameterValues != null)
				paramValue = parameterValues[0];
                                if (checkRequestParameters)
		{
			parameterValues = request.getParameterValues(parameterName);

			if (parameterValues != null)
				paramValue = parameterValues[0];
		}

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
		//定义变量
		String s_UserId, s_Pass, s_Ver, s_IpAddress,s_Right,s_Group;
		String s_Sql, s_ConditionsSql, s_QuerySql, s_ExeSql;
		String strPage, strLoginPage, strErrPage;
		int i_rtn;
		s_Sql = "SELECT * FROM t_user ";
		s_ConditionsSql = "";
		s_QuerySql = "";
		s_ExeSql = "";

		try
		{
			beanGetdata bgd = new beanGetdata();
			beanGetdata bgdmenu = new beanGetdata();
			req.setAttribute("beanGetdataUser", bgd);
			req.setAttribute("beanGetdataMenu", bgdmenu);
			req.setAttribute("beanElearnTools", ets);

			//取得参数
			strLoginPage = getParameter(req, "loginPage", false, true, true, "");
			strPage = getParameter(req, "page", false, true, true, "");
			strErrPage = getParameter(req, "errPage", false, true, true, "");
			s_UserId = getParameter(req, "userid", true, false, false, "");
			s_Pass = getParameter(req, "pass", true, false, false, "");
			//s_Ver = getParameter(req, "ver", true, false, false, "1");

			//校验是否输入用户名
			s_UserId = s_UserId.trim();
			if(s_UserId.equals(""))
			{
				req.setAttribute("message", "用户名不能为空");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}

			//检查用户名是否存在
			s_ConditionsSql = " WHERE user_id = '" + s_UserId + "'";
			s_QuerySql = s_Sql + s_ConditionsSql;

			bgd.executeSelect(s_QuerySql);
			s_Right=bgd.getFieldValue("user_right",0);
			s_Group=bgd.getFieldValue("group_id",0);

			if(bgd.getRowCount() == 0)
			{
				req.setAttribute("message", "用户名不存在！");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}

			//校验用户名和口令
			s_ConditionsSql = " WHERE user_id = '" + s_UserId + "' AND user_pass = '" + s_Pass + "'";
			s_QuerySql = s_Sql + s_ConditionsSql;

			bgd.executeSelect(s_QuerySql);

			if(bgd.getRowCount() == 0)
			{
				req.setAttribute("message", "口令不正确！");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
///////////////////////////////////////////////////////
//                                                   //
//对测试用户设置登录200次的限制                        //
//                                                   //
///////////////////////////////////////////////////////
			bgdmenu.executeSelect("select * from t_user_log");
			if(bgdmenu.getRowCount() > (50+150+20+30))
			{
				req.setAttribute("message","对不起,您所使用的测试版的测试次数已到,如果您要继续使用,请您与我们联系!电话:010-82031610");
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}

			//登录成功，创建session
			boolean create = true;
			HttpSession session = req.getSession(create);
			//向Session中赋值
			session.setAttribute("userid", s_UserId);
			session.setAttribute("right", s_Right);
			session.setAttribute("groupid", s_Group);
			
		//	session.setAttribute("ver", s_Ver);

			//向日志表填记录
			s_IpAddress = req.getRemoteAddr();
			s_ExeSql = "INSERT INTO t_user_log values ('" + s_UserId + "','"+bgdmenu.getDbDate()+"', '" + s_IpAddress + "')";
			i_rtn = bgdmenu.execute(s_ExeSql);
			if(i_rtn == -1)
			{
				System.err.println("BeanUserLogin执行SQL出错：" + s_ExeSql);
				req.setAttribute("errmsg", "登录失败！");
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
			}

			s_QuerySql = "SELECT * FROM t_menu";
			bgdmenu.executeSelect(s_QuerySql);
//		req.setAttribute("message", "登录成功！");
			getServletConfig().getServletContext().getRequestDispatcher(strPage).forward(req, res);
			return;
		}
		catch(Exception e)
		{
			try
			{
				strErrPage = getParameter(req, "errPage", false, true, true, "");
				System.err.println("beanUserLogin error: " + e.getMessage());
				req.setAttribute("errmsg", e.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception ex)
			{
				System.err.println("beanUserLogin调用错误页面时出错！");
				ex.printStackTrace();
			}

		}
	}
}


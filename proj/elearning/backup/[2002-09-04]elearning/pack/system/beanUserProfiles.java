package system;

import javax.servlet.*;
import javax.servlet.http.*;
import com.htyz.*;

public class beanUserProfiles extends HttpServlet
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
		String s_Action, s_Msg, s_Title;
		String s_UserId, s_UserName, s_Pass, s_rePass, s_Birthday, s_Sex;
		String s_Email, s_Phone, s_Company, s_Area, s_Signature;
		String s_UserStatus, s_Groupid, s_UserRight;
		String s_Sql, s_temp, s_oldpass;
		int i_rtn;
		
		beanGetdata bgd = new beanGetdata();
		req.setAttribute("beanGetdata", bgd);
		beanQueryCodes bqc = new beanQueryCodes();
		req.setAttribute("beanQueryCodes", bqc);
		beanUserInfo userinfo = new beanUserInfo();
		req.setAttribute("beanUserInfo", userinfo);		
		req.setAttribute("beanElearnTools", ets);

		try
		{
			//取得参数
			s_Action = getParameter(req, "action", true, false, false, "apply");
			s_Title = "用户申请";
			s_Sql = "";
			s_Msg = "";
			s_UserId = "";
		
			
			//申请
			if(s_Action.equalsIgnoreCase("apply"))
			{
				//userinfo.UserInit();
				req.setAttribute("title", s_Title);
				req.setAttribute("action", "add");
				getServletConfig().getServletContext().getRequestDispatcher("/UserProfiles.jsp").forward(req, res);
				return;
			}

			//修改
			if(s_Action.equalsIgnoreCase("modify"))
			{
				boolean create = false; //决定是否创建session，只有在Login中才为true
				HttpSession session = req.getSession(create);
				
					
				s_UserId = (String)session.getAttribute("userid");
				
				//判断session是否存在
				if(session == null||s_UserId == null)
				{
					req.setAttribute("message", "您没有登录！");
					getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
					return;
				}
				
				//从Session中取值
				s_UserId = s_UserId.trim();	 
				
				//从Session中取值
				s_UserId = session.getAttribute("userid").toString().trim();	 

				s_Sql = "SELECT * FROM t_user WHERE user_id = '" + s_UserId + "'";
			//	System.out.println("asfdasdf="+s_Sql);
				bgd.executeSelect(s_Sql);
				//用户信息缓冲
				
				
				userinfo.setUserid(bgd.getFieldValue("user_id", 0).trim());
				userinfo.setUserName(bgd.getFieldValue("user_name", 0).trim());
				userinfo.setPassword(bgd.getFieldValue("user_pass", 0).trim());
				userinfo.setBirthday(bgd.getFieldValue("birthday", 0).trim());
				userinfo.setSex(bgd.getFieldValue("sex", 0).trim());
				userinfo.setEmail(bgd.getFieldValue("email", 0).trim());
				userinfo.setPhone(bgd.getFieldValue("phone", 0).trim());
				userinfo.setCompany(bgd.getFieldValue("company", 0).trim());
				userinfo.setArea(bgd.getFieldValue("area", 0).trim());
				userinfo.setUserRight(bgd.getFieldValue("user_right", 0).trim());
				userinfo.setSignature(bgd.getFieldValue("signature", 0).trim());
				userinfo.setUserStatus(bgd.getFieldValue("user_status", 0).trim());
				userinfo.setGroupid(bgd.getFieldValue("group_id", 0).trim());
				userinfo.setRegisterTime(bgd.getFieldValue("register_time", 0).trim());
			
				s_Title = "用户修改";
				req.setAttribute("title", s_Title);
				req.setAttribute("action", "update");
				getServletConfig().getServletContext().getRequestDispatcher("/UserProfiles.jsp").forward(req, res);
				return;
			}
			//修改用户口令
			if(s_Action.length()>4 && s_Action.substring(s_Action.length()-4, s_Action.length()).equalsIgnoreCase("pass"))
			{
				boolean create = false; //决定是否创建session，只有在Login中才为true
				HttpSession session = req.getSession(create);
					
				s_UserId = (String)session.getAttribute("userid");
				
				//判断session是否存在
				if(session == null||s_UserId == null)
				{
					req.setAttribute("message", "您没有登录！");
					getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
					return;
				}
				
				//从Session中取值
				s_UserId = s_UserId.trim();
				
				if(s_Action.equalsIgnoreCase("changepass"))
				{
					req.setAttribute("action", "updatepass");
					req.setAttribute("title", "口令修改");
					getServletConfig().getServletContext().getRequestDispatcher("/UserProfiles.jsp").forward(req, res);
					return;
				}
				s_Sql = "SELECT user_pass FROM t_user WHERE user_id = '" + s_UserId + "'";
				bgd.executeSelect(s_Sql);
				userinfo.setPassword(bgd.getFieldValue("user_pass", 0).trim());
				s_Pass = getParameter(req, "pass", true, false, true, "");
				s_rePass = getParameter(req, "repass", true, false, true, "");
				if(!(s_Pass.equals(s_rePass)))
				{
					//口令不匹配
					s_Msg = "口令不匹配";
					//保留用户信息
	
					req.setAttribute("message", s_Msg);	
					req.setAttribute("title", s_Title);
					req.setAttribute("action", s_Action);
					getServletConfig().getServletContext().getRequestDispatcher("/UserProfiles.jsp").forward(req, res);
					return;
				}
				s_oldpass = getParameter(req, "oldpass", true, false, false, "").trim();
				
				if(!(s_oldpass.equals(userinfo.getPassword().trim())))
				{
					//用户已经存在
					s_Msg = "旧口令不正确";
					//保留用户信息
	
					req.setAttribute("message", s_Msg);	
					req.setAttribute("title", s_Title);
					req.setAttribute("action", s_Action);
					getServletConfig().getServletContext().getRequestDispatcher("/UserProfiles.jsp").forward(req, res);
					return;
				}

				s_Msg = "修改口令";
				s_Sql = "UPDATE t_user SET ";
				s_Sql = s_Sql + "user_pass = '" + s_Pass + "' ";
				s_Sql = s_Sql + "WHERE user_id = '" + s_UserId + "'";
				i_rtn = bgd.execute(s_Sql);
				if(i_rtn < 0)
				{
					s_Msg = s_Msg + s_UserId + "时发生错误";
					req.setAttribute("message", s_Msg);
					getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
					return;
				}
				s_Msg = "用户" + s_Msg + "成功";
				req.setAttribute("message", s_Msg);
				//释放bean_UserInfo资源
				userinfo.UserInit();
				getServletConfig().getServletContext().getRequestDispatcher("/Ok.jsp").forward(req, res);
				return;
			}
			s_UserId =getParameter(req, "userid", true, false, true, "");
			s_UserName = getParameter(req, "username", true, false, true, "");
			s_Pass = getParameter(req, "pass", true, false, false, "");
			s_rePass = getParameter(req, "repass", true, false, false, "");
			s_Birthday = getParameter(req, "birthday", true, false, true, "");
			s_Sex = getParameter(req, "sex", true, false, true, "");
			s_Email = getParameter(req, "email", true, false, true, "");
			s_Phone = getParameter(req, "phone", true, false, false, "");
			s_Company = getParameter(req, "company", true, false, false, "");
			s_Area = getParameter(req, "area", true, true, false, "");
			s_Signature = getParameter(req, "signature", true, false, false, "");

			userinfo.setUserid(s_UserId);
			userinfo.setUserName(s_UserName);
			userinfo.setBirthday(s_Birthday);
			userinfo.setSex(s_Sex);
			userinfo.setEmail(s_Email);
			userinfo.setPhone(s_Phone);
			userinfo.setCompany(s_Company);
			userinfo.setArea(s_Area);
			userinfo.setSignature(s_Signature);
			userinfo.setPassword(s_Pass);
			if(!(s_Pass.equals(s_rePass)))
			{
				//用户已经存在
				s_Msg = "口令不匹配";
				//保留用户信息

				req.setAttribute("message", s_Msg);	
				req.setAttribute("title", s_Title);
				req.setAttribute("action", s_Action);
				getServletConfig().getServletContext().getRequestDispatcher("/UserProfiles.jsp").forward(req, res);
				return;
			}

			//添加用户
			if(s_Action.equalsIgnoreCase("add"))
			{
				//校验用户是否存在
				s_temp = ets.check_quote(s_UserId);
				s_Sql = "SELECT * FROM t_user WHERE user_id = '" + s_temp + "'";
				bgd.executeSelect(s_Sql);
				if(bgd.getRowCount() > 0)
				{
					//用户已经存在
					s_Msg = "用户名已经存在";
					//保留用户信息

					req.setAttribute("message", s_Msg);	
					req.setAttribute("title", s_Title);
					req.setAttribute("action", "add");
					getServletConfig().getServletContext().getRequestDispatcher("/UserProfiles.jsp").forward(req, res);
					return;
				}
				s_UserRight = userinfo.getUserRight();
				s_UserStatus = userinfo.getUserStatus();
				s_Groupid = userinfo.getGroupid();
				s_Msg = "添加";
				s_Sql = "INSERT INTO t_user ";
				s_Sql = s_Sql + "(user_id, user_name, user_pass, birthday, sex, email, phone, company, area, signature, user_right, group_id, user_status) ";
				s_Sql = s_Sql + " VALUES ('" + s_UserId + "', ";
				s_Sql = s_Sql + "'" + s_UserName + "', ";
				s_Sql = s_Sql + "'" + s_Pass + "', ";
				s_Sql = s_Sql + "'" + s_Birthday + "', ";
				s_Sql = s_Sql + "'" + s_Sex + "', ";
				s_Sql = s_Sql + "'" + s_Email + "', ";
				s_Sql = s_Sql + "'" + s_Phone + "', ";
				s_Sql = s_Sql + "'" + s_Company + "', ";
				s_Sql = s_Sql + "'" + s_Area + "', ";
				s_Sql = s_Sql + "'" + s_Signature + "', ";
				s_Sql = s_Sql + "" + s_UserRight + ", ";
				s_Sql = s_Sql + "'" + s_Groupid + "', ";
				s_Sql = s_Sql + "'" + s_UserStatus + "') ";
			//	System.out.println(s_Sql);
			}

			//更新用户信息
			if(s_Action.equalsIgnoreCase("update"))
			{
				boolean create = false; //决定是否创建session，只有在Login中才为true
				HttpSession session = req.getSession(create);
					
				s_UserId = (String)session.getAttribute("userid");
				
				//判断session是否存在
				if(session == null||s_UserId == null)
				{
					req.setAttribute("message", "您没有登录！");
					getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
					return;
				}
				
				//从Session中取值
				s_UserId = s_UserId.trim();	 

				s_Msg = "修改用户信息";
				s_Sql = "UPDATE t_user SET user_name = '" + s_UserName + "', ";
				s_Sql = s_Sql + "birthday = '" + s_Birthday + "', ";
				s_Sql = s_Sql + "sex = '" + s_Sex + "', ";
				s_Sql = s_Sql + "email = '" + s_Email + "', ";
				s_Sql = s_Sql + "phone = '" + s_Phone + "', ";
				s_Sql = s_Sql + "company = '" + s_Company + "', ";
				s_Sql = s_Sql + "area = '" + s_Area + "', ";
				s_Sql = s_Sql + "signature = '" + s_Signature + "' ";
				s_Sql = s_Sql + "WHERE user_id = '" + s_UserId + "'";
			}
			
//			System.out.println(s_Sql);
			i_rtn = bgd.execute(s_Sql);
			if(i_rtn < 0)
			{
				s_Msg = s_Msg + s_UserId + "时发生错误";
				req.setAttribute("message", s_Msg);
				getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
				return;
			}
			s_Msg = "用户" + s_Msg + "成功";
			req.setAttribute("message", s_Msg);
			//释放bean_UserInfo资源
			userinfo.UserInit();
			getServletConfig().getServletContext().getRequestDispatcher("/Ok.jsp").forward(req, res);
			return;
		}
		catch(Exception e)
		{
			try
			{
				System.err.println("bean_UserProfiles error: " + e.getMessage());
				req.setAttribute("errmsg", e.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
				return;
			}
			catch(Exception ex)
			{
				System.err.println("beanUserProfiles调用错误页面时出错！");
			}
			
		}
	}
}


package system;

import javax.servlet.*;
import javax.servlet.http.*;
import com.htyz.*;
import com.htyz.notice.*;
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
		String s_UserId, s_UserName;
		String s_Sql, s_temp, s_oldpass;
		int i_rtn;
		noticeBean notebean = new noticeBean();
		beanGetdata bgd = new beanGetdata();
		req.setAttribute("beanGetdata", bgd);
		beanQueryCodes bqc = new beanQueryCodes();
		req.setAttribute("beanQueryCodes", bqc);
		beanUserInfo userinfo = new beanUserInfo();
		req.setAttribute("beanUserInfo", userinfo);		

		try
		{
			//取得参数
			s_Action = getParameter(req, "action", true, false, false, "apply");
			s_Title = "用户注册";
			s_Sql = "";
			s_Msg = "";
			s_UserId = "";
		
			//申请
			if(s_Action.equalsIgnoreCase("apply"))
			{
				userinfo.UserInit();
				req.setAttribute("title", s_Title);
				req.setAttribute("action", "add");
				getServletConfig().getServletContext().getRequestDispatcher("/register.jsp").forward(req, res);
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
				
				//用户信息缓冲
				userinfo.setUserInfo(s_UserId);

				
				s_Title = "修改注册信息";
				req.setAttribute("title", s_Title);
				req.setAttribute("action", "update");
				getServletConfig().getServletContext().getRequestDispatcher("/register.jsp").forward(req, res);
				return;
			}

			//管理员修改
			if(s_Action.equalsIgnoreCase("admin"))
			{
				boolean create = false; //决定是否创建session，只有在Login中才为true
				HttpSession session = req.getSession(create);
				
					
				s_UserId = (String)session.getAttribute("userid");
				
				if(!ets.isAdmin(s_UserId)) {
					req.setAttribute("errmsg", "您不是管理员,无此权限!");
					getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
					return;
				}
					
				
				//判断session是否存在
				if(session == null||s_UserId == null)
				{
					req.setAttribute("message", "您没有登录！");
					getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
					return;
				}
				
				//从Session中取值
				s_UserId = getParameter(req, "userid", true, false, false, "");
				if(s_UserId.equals("")||s_UserId == null)
				{
					req.setAttribute("errmsg", "缺少参数,您没指明要修改哪一位用户的信息?");
					getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
					return;
				}
				
				
				//用户信息缓冲
				userinfo.setUserInfo(s_UserId);

				
				s_Title = "管理员修改注册信息";
				req.setAttribute("title", s_Title);
				req.setAttribute("action", "update");
				getServletConfig().getServletContext().getRequestDispatcher("/register.jsp").forward(req, res);
				return;
			}
			
			//添加用户
			if(s_Action.equalsIgnoreCase("add")||s_Action.equalsIgnoreCase("update"))
			{
				userinfo.setUserid(getParameter(req, "userid", true, false, false, ""));
				userinfo.setUserName(getParameter(req, "username", true, false, false, ""));
				userinfo.setPassword(getParameter(req, "pass", true, false, false, ""));
				userinfo.setRepass(getParameter(req, "repass", true, false, false, ""));
				userinfo.setEmail(getParameter(req, "email", true, false, false, ""));
				userinfo.setCompany(getParameter(req, "company", true, false, false, ""));
				userinfo.setDepartment(getParameter(req, "department", true, false, false, ""));
				userinfo.setPhone(getParameter(req, "phone", true, false, false, ""));
				userinfo.setContract(getParameter(req, "contract", true, false, false, ""));
				userinfo.setSex(getParameter(req, "sex", true, false, false, ""));
				userinfo.setBirthday(getParameter(req, "year", true, false, false, "").trim()+"-"+getParameter(req, "month", true, false, false, "").trim()+"-"+getParameter(req, "day", true, false, false, "").trim());
				userinfo.setShengxiao(getParameter(req, "shengxiao", true, false, false, ""));
				userinfo.setStars(getParameter(req, "userxz", true, false, false, ""));
				userinfo.setWebaddr(getParameter(req, "webaddr", true, false, false, ""));
				userinfo.setOicq(getParameter(req, "oicq", true, false, false, ""));
				userinfo.setMsn(getParameter(req, "msn", true, false, false, ""));
				userinfo.setCountry(getParameter(req, "userflag", true, false, false, ""));
				userinfo.setPravency(getParameter(req, "pravency", true, false, false, ""));
				userinfo.setCity(getParameter(req, "city", true, false, false, ""));
				userinfo.setResume(getParameter(req, "resume", true, false, false, ""));
				userinfo.setSignature(getParameter(req, "signature", true, false, false, ""));
				userinfo.setUserimg(getParameter(req, "useravatar", true, false, false, ""));
				userinfo.setSelfimg(getParameter(req, "personalavatar", true, false, false, ""));
				userinfo.setSelfwidth(getParameter(req, "personalwidth", true, false, false, "0").equals("")?"0":getParameter(req, "personalwidth", true, false, false, "0"));
				userinfo.setSelfheight(getParameter(req, "personalheight", true, true, false, "0").equals("")?"0":getParameter(req, "personalheight", true, true, false, "0"));

                userinfo.setGroupid(getParameter(req, "group_id", true, false, false, ""));
                userinfo.setUserStatus(getParameter(req, "user_status", true, false, false, ""));
                String s_rightCount = getParameter(req, "rightcount", true, false, false, "");
                int i = Integer.parseInt(s_rightCount);
                String right[] = new String[i];
                for(int k = 0; k < i; k++)
                   right[k] = getParameter(req, "userright" + Integer.toString(k), true, false, false, "0");

                userinfo.setUserRight(ets.parseRight(right));

				if(!(userinfo.getPassword().equals(userinfo.getRepass())))
				{
					s_Msg = "口令不匹配";
					//保留用户信息
	
					req.setAttribute("errmsg", s_Msg);	
					req.setAttribute("title", s_Title);
					req.setAttribute("action", s_Action);
					getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
					return;
				}
				
			}
			
			if(s_Action.equalsIgnoreCase("add")) {
				if(userinfo.getUserid().trim().equals(""))
				{
					s_Msg = "用户名不能为空";
					//保留用户信息
	
					req.setAttribute("errmsg", s_Msg);	
					req.setAttribute("title", s_Title);
					req.setAttribute("action", s_Action);
					getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
					return;
				}

				//校验用户是否存在
				s_temp = ets.check_quote(userinfo.getUserid());
				s_Sql = "SELECT * FROM t_user WHERE user_id = '" + s_temp + "'";
				bgd.executeSelect(s_Sql);
				if(bgd.getRowCount() > 0)
				{
					//用户已经存在
					s_Msg = "用户名已经存在";
					//保留用户信息

					req.setAttribute("errmsg", s_Msg);	
					req.setAttribute("title", s_Title);
					req.setAttribute("action", "add");
					getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
					return;
				}
				String s_regtime=notebean.getSysDate();
				s_Msg = "添加";
				s_Sql = "INSERT INTO t_user ";
				s_Sql = s_Sql + " ";
				s_Sql = s_Sql + " VALUES ('" + userinfo.getUserid() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getUserName() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getPassword() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getEmail() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getCompany() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getDepartment() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getPhone() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getGroupid() + "', ";
				s_Sql = s_Sql + "" + userinfo.getUserRight() + ", ";
				s_Sql = s_Sql + "'" + userinfo.getUserStatus() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getRegisterTime() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getContract() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getSex() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getBirthday() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getShengxiao() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getStars() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getWebaddr() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getOicq() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getMsn() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getCountry() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getPravency() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getCity() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getResume() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getSignature() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getUserimg() + "', ";
				s_Sql = s_Sql + "'" + userinfo.getSelfimg() + "', ";
				s_Sql = s_Sql + "" + userinfo.getSelfwidth() + ", ";
				s_Sql = s_Sql + "" + userinfo.getSelfheight() + ") ";
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
				
				s_Sql = "UPDATE t_user SET user_name = '" + userinfo.getUserName() + "', ";
				s_Sql = s_Sql + "user_pass = '" + userinfo.getPassword() + "', ";
				s_Sql = s_Sql + "email = '" + userinfo.getEmail() + "', ";
				s_Sql = s_Sql + "company = '" + userinfo.getCompany() + "', ";
				s_Sql = s_Sql + "department = '" + userinfo.getDepartment() + "', ";
				s_Sql = s_Sql + "phone = '" + userinfo.getPhone() + "', ";
				s_Sql = s_Sql + "group_id = '" + userinfo.getGroupid() + "', ";
				s_Sql = s_Sql + "user_right = " + userinfo.getUserRight() + ", ";
				s_Sql = s_Sql + "user_status = '" + userinfo.getUserStatus() + "', ";
				s_Sql = s_Sql + "contract = '" + userinfo.getContract() + "', ";
				s_Sql = s_Sql + "sex = '" + userinfo.getSex() + "', ";
				s_Sql = s_Sql + "birthday = '" + userinfo.getBirthday() + "', ";
				s_Sql = s_Sql + "shengxiao = '" + userinfo.getShengxiao() + "', ";
				s_Sql = s_Sql + "stars = '" + userinfo.getStars() + "', ";
				s_Sql = s_Sql + "webaddr = '" + userinfo.getWebaddr() + "', ";
				s_Sql = s_Sql + "oicq = '" + userinfo.getOicq() + "', ";
				s_Sql = s_Sql + "msn = '" + userinfo.getMsn() + "', ";
				s_Sql = s_Sql + "country = '" + userinfo.getCountry() + "', ";
				s_Sql = s_Sql + "pravency = '" + userinfo.getPravency() + "', ";
				s_Sql = s_Sql + "city = '" + userinfo.getCity() + "', ";
				s_Sql = s_Sql + "resume = '" + userinfo.getResume() + "', ";
				s_Sql = s_Sql + "signature = '" + userinfo.getSignature() + "', ";
				s_Sql = s_Sql + "user_img = '" + userinfo.getUserimg() + "', ";
				s_Sql = s_Sql + "self_img = '" + userinfo.getSelfimg() + "', ";
				s_Sql = s_Sql + "self_width = " + userinfo.getSelfwidth() + ", ";
				s_Sql = s_Sql + "self_height = " + userinfo.getSelfheight() + " ";
				s_Sql = s_Sql + "WHERE user_id = '" + userinfo.getUserid() + "'";
			}
			
//	System.out.println(s_Sql);
			i_rtn = bgd.execute(s_Sql);
			if(i_rtn < 0)
			{
				s_Msg = s_Msg + s_UserId + "时发生错误";
				req.setAttribute("errmsg", s_Msg);
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


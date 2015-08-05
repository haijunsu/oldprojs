package system;

import javax.servlet.*;
import javax.servlet.http.*;
import com.htyz.*;
import com.htyz.notice.*;
public class beanUserProfiles extends HttpServlet
{
	
	protected beanElearnTools ets = new beanElearnTools();

	//��ʼ��Servlet
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
	}
	
	//ִ��Post����
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
		doProcess(req, res);
	}

	//ִ��Get����
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException
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

		//���request�в���Ϊnull,��servlet��ʼ�������л�ȡ
		if ( (checkInitParameters) && (paramValue == null) )
			paramValue = getServletConfig().getInitParameter(parameterName);

		//�������Ϊ���룬��û�л�ȡ�����׳��쳣
		if ( (isParameterRequired) && (paramValue == null) )
			throw new Exception(parameterName + " ����Ϊ���룬��û���ҵ������飡");

		//�������û���ҵ����򷵻�Ĭ��ֵ
		if (paramValue == null)
			paramValue = defaultValue;

		//ת��ΪGBK
		paramValue_GBK = ets.getGBKString(paramValue);
		if (paramValue_GBK == null)
		{
			throw new Exception(parameterName + " ����ת��ΪGBK�����飡");
		}
		paramValue = paramValue_GBK;

		return paramValue;
	}
	
	//Servlet��ִ������	
	public void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
		//�������
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
			//ȡ�ò���
			s_Action = getParameter(req, "action", true, false, false, "apply");
			s_Title = "�û�ע��";
			s_Sql = "";
			s_Msg = "";
			s_UserId = "";
		
			//����
			if(s_Action.equalsIgnoreCase("apply"))
			{
				userinfo.UserInit();
				req.setAttribute("title", s_Title);
				req.setAttribute("action", "add");
				getServletConfig().getServletContext().getRequestDispatcher("/register.jsp").forward(req, res);
				return;
			}

			//�޸�
			if(s_Action.equalsIgnoreCase("modify"))
			{
				boolean create = false; //�����Ƿ񴴽�session��ֻ����Login�в�Ϊtrue
				HttpSession session = req.getSession(create);
				
					
				s_UserId = (String)session.getAttribute("userid");
				
				//�ж�session�Ƿ����
				if(session == null||s_UserId == null)
				{
					req.setAttribute("message", "��û�е�¼��");
					getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
					return;
				}
				
				//��Session��ȡֵ
				s_UserId = s_UserId.trim();	 
				
				//�û���Ϣ����
				userinfo.setUserInfo(s_UserId);

				
				s_Title = "�޸�ע����Ϣ";
				req.setAttribute("title", s_Title);
				req.setAttribute("action", "update");
				getServletConfig().getServletContext().getRequestDispatcher("/register.jsp").forward(req, res);
				return;
			}

			//����Ա�޸�
			if(s_Action.equalsIgnoreCase("admin"))
			{
				boolean create = false; //�����Ƿ񴴽�session��ֻ����Login�в�Ϊtrue
				HttpSession session = req.getSession(create);
				
					
				s_UserId = (String)session.getAttribute("userid");
				
				if(!ets.isAdmin(s_UserId)) {
					req.setAttribute("errmsg", "�����ǹ���Ա,�޴�Ȩ��!");
					getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
					return;
				}
					
				
				//�ж�session�Ƿ����
				if(session == null||s_UserId == null)
				{
					req.setAttribute("message", "��û�е�¼��");
					getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
					return;
				}
				
				//��Session��ȡֵ
				s_UserId = getParameter(req, "userid", true, false, false, "");
				if(s_UserId.equals("")||s_UserId == null)
				{
					req.setAttribute("errmsg", "ȱ�ٲ���,��ûָ��Ҫ�޸���һλ�û�����Ϣ?");
					getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
					return;
				}
				
				
				//�û���Ϣ����
				userinfo.setUserInfo(s_UserId);

				
				s_Title = "����Ա�޸�ע����Ϣ";
				req.setAttribute("title", s_Title);
				req.setAttribute("action", "update");
				getServletConfig().getServletContext().getRequestDispatcher("/register.jsp").forward(req, res);
				return;
			}
			
			//����û�
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
					s_Msg = "���ƥ��";
					//�����û���Ϣ
	
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
					s_Msg = "�û�������Ϊ��";
					//�����û���Ϣ
	
					req.setAttribute("errmsg", s_Msg);	
					req.setAttribute("title", s_Title);
					req.setAttribute("action", s_Action);
					getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
					return;
				}

				//У���û��Ƿ����
				s_temp = ets.check_quote(userinfo.getUserid());
				s_Sql = "SELECT * FROM t_user WHERE user_id = '" + s_temp + "'";
				bgd.executeSelect(s_Sql);
				if(bgd.getRowCount() > 0)
				{
					//�û��Ѿ�����
					s_Msg = "�û����Ѿ�����";
					//�����û���Ϣ

					req.setAttribute("errmsg", s_Msg);	
					req.setAttribute("title", s_Title);
					req.setAttribute("action", "add");
					getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
					return;
				}
				String s_regtime=notebean.getSysDate();
				s_Msg = "���";
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

			//�����û���Ϣ
			if(s_Action.equalsIgnoreCase("update"))
			{
				boolean create = false; //�����Ƿ񴴽�session��ֻ����Login�в�Ϊtrue
				HttpSession session = req.getSession(create);
					
				s_UserId = (String)session.getAttribute("userid");
				
				//�ж�session�Ƿ����
				if(session == null||s_UserId == null)
				{
					req.setAttribute("message", "��û�е�¼��");
					getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
					return;
				}
				
				//��Session��ȡֵ
				s_UserId = s_UserId.trim();	 

				s_Msg = "�޸��û���Ϣ";
				
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
				s_Msg = s_Msg + s_UserId + "ʱ��������";
				req.setAttribute("errmsg", s_Msg);
				getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
				return;
			}
			s_Msg = "�û�" + s_Msg + "�ɹ�";
			req.setAttribute("message", s_Msg);
			
			//�ͷ�bean_UserInfo��Դ
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
				System.err.println("beanUserProfiles���ô���ҳ��ʱ����");
			}
			
		}
	}
}


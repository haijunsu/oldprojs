package system;

import javax.servlet.*;
import javax.servlet.http.*;
import com.htyz.*;

public class beanUserLogin extends HttpServlet
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
                                if (checkRequestParameters)
		{
			parameterValues = request.getParameterValues(parameterName);

			if (parameterValues != null)
				paramValue = parameterValues[0];
		}

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

			//ȡ�ò���
			strLoginPage = getParameter(req, "loginPage", false, true, true, "");
			strPage = getParameter(req, "page", false, true, true, "");
			strErrPage = getParameter(req, "errPage", false, true, true, "");
			s_UserId = getParameter(req, "userid", true, false, false, "");
			s_Pass = getParameter(req, "pass", true, false, false, "");
			//s_Ver = getParameter(req, "ver", true, false, false, "1");

			//У���Ƿ������û���
			s_UserId = s_UserId.trim();
			if(s_UserId.equals(""))
			{
				req.setAttribute("message", "�û�������Ϊ��");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}

			//����û����Ƿ����
			s_ConditionsSql = " WHERE user_id = '" + s_UserId + "'";
			s_QuerySql = s_Sql + s_ConditionsSql;

			bgd.executeSelect(s_QuerySql);
			s_Right=bgd.getFieldValue("user_right",0);
			s_Group=bgd.getFieldValue("group_id",0);

			if(bgd.getRowCount() == 0)
			{
				req.setAttribute("message", "�û��������ڣ�");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}

			//У���û����Ϳ���
			s_ConditionsSql = " WHERE user_id = '" + s_UserId + "' AND user_pass = '" + s_Pass + "'";
			s_QuerySql = s_Sql + s_ConditionsSql;

			bgd.executeSelect(s_QuerySql);

			if(bgd.getRowCount() == 0)
			{
				req.setAttribute("message", "�����ȷ��");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
///////////////////////////////////////////////////////
//                                                   //
//�Բ����û����õ�¼200�ε�����                        //
//                                                   //
///////////////////////////////////////////////////////
			bgdmenu.executeSelect("select * from t_user_log");
			if(bgdmenu.getRowCount() > (50+150+20+30))
			{
				req.setAttribute("message","�Բ���,����ʹ�õĲ��԰�Ĳ��Դ����ѵ�,�����Ҫ����ʹ��,������������ϵ!�绰:010-82031610");
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}

			//��¼�ɹ�������session
			boolean create = true;
			HttpSession session = req.getSession(create);
			//��Session�и�ֵ
			session.setAttribute("userid", s_UserId);
			session.setAttribute("right", s_Right);
			session.setAttribute("groupid", s_Group);
			
		//	session.setAttribute("ver", s_Ver);

			//����־�����¼
			s_IpAddress = req.getRemoteAddr();
			s_ExeSql = "INSERT INTO t_user_log values ('" + s_UserId + "','"+bgdmenu.getDbDate()+"', '" + s_IpAddress + "')";
			i_rtn = bgdmenu.execute(s_ExeSql);
			if(i_rtn == -1)
			{
				System.err.println("BeanUserLoginִ��SQL����" + s_ExeSql);
				req.setAttribute("errmsg", "��¼ʧ�ܣ�");
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
			}

			s_QuerySql = "SELECT * FROM t_menu";
			bgdmenu.executeSelect(s_QuerySql);
//		req.setAttribute("message", "��¼�ɹ���");
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
				System.err.println("beanUserLogin���ô���ҳ��ʱ����");
				ex.printStackTrace();
			}

		}
	}
}


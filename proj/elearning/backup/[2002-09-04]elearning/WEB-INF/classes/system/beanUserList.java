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
	
	//��ʼ��Servlet
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
		beanElearnTools ets = new beanElearnTools();
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
			
			boolean create = false; //�����Ƿ񴴽�session��ֻ����Login�в�Ϊtrue
			HttpSession session = req.getSession(create);
			s_userId = (String)session.getAttribute("userid");
			
			//�ж�session�Ƿ����
			if(session == null||s_userId == null)
			{
				req.setAttribute("message", "��û�е�¼��");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			
			//ȡ�ò���
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
				System.err.println("beanUserList���ô���ҳ��ʱ����");
				e.printStackTrace();
			}
		}
    }
    
}
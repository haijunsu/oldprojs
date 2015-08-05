
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
			
			boolean create = false; //�����Ƿ񴴽�session��ֻ����Login�в�Ϊtrue
			HttpSession session = req.getSession(create);

			//�ж�session�Ƿ����
			if(session == null)
			{
				req.setAttribute("message", "��û�е�¼��");
				getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
				return;
			}
			
			//�ж��û��Ƿ��¼
			s_userId = (String)session.getAttribute("userid");
			if(s_userId == null)
			{
				req.setAttribute("message", "��û�е�¼��");
				getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
				return;
			}
			
			//ȡ�ò���
			s_action = getParameter(req, "action", true, false, false, "query");
			s_open = getParameter(req, "open", true, false, false, "0");
			s_queryItem = getParameter(req, "queryItem", true, false, false, "");
			s_filter =  getParameter(req, "filter", true, false, false, "");
			
			req.setAttribute("queryItem", s_queryItem);
			s_navigation = "<A href=\"" + req.getContextPath() + "/\" target=\"_top\">��ҳ</A>"
							+ "&gt;<A href=\"query?queryItem=" + s_queryItem + "\" target=\"_self\">";
			
				
			if (s_action.equalsIgnoreCase("showOk"))
			{
				s_message = getParameter(req, "message", true, false, false, "");
				s_message = s_message + "�����ɹ���";
				req.setAttribute("message", s_message);
				getServletConfig().getServletContext().getRequestDispatcher("/Ok.jsp").forward(req, res);
				return;
			}
			
			if (s_action.equalsIgnoreCase("showErr"))
			{
				s_message = getParameter(req, "message", true, false, false, "");
				s_message = s_message + "����ʧ�ܣ�";
				req.setAttribute("errmsg", s_message);
				getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
				return;
			}
			
			if (s_action.equalsIgnoreCase("query"))
			{
				if (s_queryItem.equals("log"))
				{
					s_navigation = s_navigation + "��¼��ѯ</A>";
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
						s_catalog = "�����û�";
						s_navigation = s_navigation + "&gt;<a href=\"query?queryItem=" + s_queryItem + "\" target=\"_self\">�����û��ĵ�¼��¼</a>";
					}else{
						s_whereClause=" Where user_id like'%"+s_filter+"%'";
						s_catalog = s_filter;
						s_navigation = s_navigation +  "&gt;<a href=\"query?queryItem=" + s_queryItem + "&filter="+s_filter+"\" target=\"_self\">�û�"+s_filter+"�ĵ�¼��¼</a>";
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
	//				System.out.println("ˢ������");
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
//����ΪͨѶ¼�б�
				if (s_queryItem.equals("address"))
				{
					s_navigation = s_navigation + "��ַ��</A>";
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
							s_catalog = "�����û�";
							s_navigation = s_navigation + "&gt;<a href=\"query?queryItem=" + s_queryItem + "\" target=\"_self\">�����û��ĵ�ַ��</a>";
						}else{
							s_whereClause=" Where owner ='"+s_userId+"'";
							s_catalog = s_filter;
							s_navigation = s_navigation +  "&gt;<a href=\"query?queryItem=" + s_queryItem + "&filter="+s_filter+"\" target=\"_self\">�û�"+s_filter+"�ĵ�¼��¼</a>";
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
	//				System.out.println("ˢ������");
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
				System.err.println("beanQuery���ô���ҳ��ʱ����");
				e.printStackTrace();
			}
		}
    }
}
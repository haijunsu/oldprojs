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

			boolean create = false; //�����Ƿ񴴽�session��ֻ����Login�в�Ϊtrue
			HttpSession session = req.getSession(create);

			//�ж�session�Ƿ����
			if(session == null)
			{
				req.setAttribute("message", "��û�е�¼��");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			//�ж��û��Ƿ��¼
			s_userId = (String)session.getAttribute("userid");
			if(s_userId == null)
			{
				req.setAttribute("message", "��û�е�¼��");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			//�ж��û��ǲ��ǹ���Ա
			if (!ets.isAdmin(s_userId))
				throw new Exception("��û��ִ�д˲�����Ȩ�ޣ�");
				
			
			//ȡ�ò���
			s_action = getParameter(req, "action", true, false, false, "list");
			s_open = getParameter(req, "open", true, false, false, "0");
			s_treeRoot = getParameter(req, "root", true, false, false, "");
	//		System.out.println(s_treeRoot);
			
			if (!s_treeRoot.equals("")) this.root = s_treeRoot;
	//		System.out.println(this.root);
			
			if (s_action.equalsIgnoreCase("showOk"))
			{
				s_message = getParameter(req, "message", true, false, false, "");
				s_message = s_message + "�����ɹ���";
				req.setAttribute("message", s_message);
				getServletConfig().getServletContext().getRequestDispatcher(strOkPage).forward(req, res);
				return;
			}
			
			if (s_action.equalsIgnoreCase("showErr"))
			{
				s_message = getParameter(req, "message", true, false, false, "");
				s_message = s_message + "����ʧ�ܣ�";
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
				s_message = "ɾ��";
					
				if (bgd.execute(s_sql) == -1)
				{
					s_action = "showErr";
					s_message = "ɾ��";
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
				
				if (s_codeNamec.equals("")) s_codeNamec = "��";
				
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
						s_message = "�ڵ�" + s_codeId + "�Ѿ�����" + s_codeValue + "����ֵ!���";
					}
					else
					{
						s_message = "���";
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
					s_message = "����";
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
				System.err.println("beanCodeList���ô���ҳ��ʱ����");
				e.printStackTrace();
			}
		}
    }
}
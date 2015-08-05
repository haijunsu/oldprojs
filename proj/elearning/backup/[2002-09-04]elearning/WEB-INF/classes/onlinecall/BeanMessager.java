//////////////////////////////////////////////
//
// ���Ź���
//
//     �պ���     2002.1
//
//////////////////////////////////////////////

package onlinecall;

import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.htyz.*;

public class BeanMessager extends HttpServlet
{
	protected String Str_Sql = "";
	protected String Select_Sql = "SELECT * FROM t_onlinecall ";
	protected String Order_Sql = " ORDER BY oc_id DESC";
	protected String Conditions_Sql = "";
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

			paramValue = request.getParameter(parameterName);
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
		String s_action, s_NewId, s_MsgId, s_MsgTitle, s_MsgContent, s_SenderId, s_ReceiptId, s_MsgStatus;
		String s_msg, s_Group, s_UserId, s_FileName;
		String s_pageNo;
		int i_rtn;
		Conditions_Sql = "";
		s_msg = "";
		beanGetdata bgd = new beanGetdata();
		req.setAttribute("beanGetdata", bgd);
		req.setAttribute("beanElearnTools", ets);
		String strLoginPage, strOkPage, strErrPage;
		try
		{
			strLoginPage = getParameter(req, "loginPage", false, true, true, "");
			strOkPage = getParameter(req, "okPage", false, true, true, "");
			strErrPage = getParameter(req, "errPage", false, true, true, "");
			//ȡ�ò���
			s_action = getParameter(req, "action", true, true, false, "list");
			//s_Group����Ҫȡ��Ϣ��״̬
			s_Group = getParameter(req, "group", true, true, false, "inbox");
			//ִ����������
			boolean create = false; //�����Ƿ񴴽�session��ֻ����Login�в�Ϊtrue
			HttpSession session = req.getSession(create);

			//�ж�session�Ƿ����
			if(session == null)
			{
				req.setAttribute("message", "��û�е�¼��");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			
			s_UserId = (String)session.getAttribute("userid");
			
			//�ж�userid�Ƿ����
			if(s_UserId == null)
			{
				req.setAttribute("message", "��û�е�¼��");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			s_pageNo = getParameter(req, "pageno", true, false, false, "1");
			s_UserId = session.getAttribute("userid").toString().trim();
			s_Group = s_Group.trim();
			req.setAttribute("pageno", s_pageNo);
			req.setAttribute("group", s_Group);
			//�ռ���
			if(s_Group.equals("inbox"))
			{
				s_msg = "�ռ���";
				Conditions_Sql = " where oc_obj = '" + s_UserId + "' AND oc_status in ('1', '2')";
			}
			//�ݸ���
			if(s_Group.equals("draftbox"))
			{
				s_msg = "�ݸ���";
				Conditions_Sql = " where oc_name = '" + s_UserId + "' AND oc_status = '3'";
			}
			//������
			if(s_Group.equals("outbox"))
			{
				s_msg = "������";
				Conditions_Sql = " where oc_name = '" + s_UserId + "' AND  oc_status = '4'";
			}

			s_FileName = getParameter(req, "listPage", false, true, true, "");
			//�޸Ĳݸ�
			if(s_action.equalsIgnoreCase("modify"))
			{
				s_FileName = getParameter(req, "modifyPage", false, true, true, "");
				req.setAttribute("action", "send");
				s_MsgId = getParameter(req, "msgid", true, false, true, "");
				Conditions_Sql = " where oc_id = '" + s_MsgId + "' ";
			}
			//�ظ��ʼ�
			if(s_action.equalsIgnoreCase("reply"))
			{
				s_FileName = getParameter(req, "modifyPage", false, true, true, "");
				req.setAttribute("action", "reply");
				s_MsgId = getParameter(req, "msgid", true, false, true, "");
				Conditions_Sql = " where oc_id = '" + s_MsgId + "' ";
			}
			if(s_action.equalsIgnoreCase("forward"))
			{
				s_FileName = getParameter(req, "modifyPage", false, true, true, "");
				req.setAttribute("action", "forward");
				s_MsgId = getParameter(req, "msgid", true, false, true, "");
				Conditions_Sql = " where oc_id = '" + s_MsgId + "' ";
			}
			//���ʼ�
			if(s_action.equalsIgnoreCase("read"))
			{
				s_FileName = getParameter(req, "modifyPage", false, true, true, "");
				req.setAttribute("action", "read");
				s_MsgId = getParameter(req, "msgid", true, false, true, "");
				Conditions_Sql = " where oc_id = '" + s_MsgId + "' ";
				if(s_Group.equals("inbox"))
				{
					Str_Sql = "UPDATE t_onlinecall SET oc_status = '2'  where oc_id = '" + s_MsgId + "' ";
					i_rtn = bgd.execute(Str_Sql);
					if(i_rtn == -1)
					{
						throw new Exception("���ʼ������ʱʧ�ܣ�");
					}
				}
			}
			//�����ʼ�
			if(s_action.equalsIgnoreCase("new"))
			{
				s_FileName = getParameter(req, "modifyPage", false, true, true, "");
				req.setAttribute("action", "send");
				Conditions_Sql = " where oc_id is null ";
			}
			//ɾ���ʼ�
			if(s_action.equalsIgnoreCase("delete"))
			{
				String s_countPara = getParameter(req, "countInPage", true, false, true, "");
				int i_count = Integer.parseInt(s_countPara);
				s_MsgId = "";
				for (int i=0; i<i_count; i++)
				{
					String s_selectedItem = getParameter(req, "select" + Integer.toString(i), true, false, false, "");
					if (s_selectedItem.equals(""))
						continue;
					else
						s_MsgId = s_MsgId + ",'" + s_selectedItem + "'";
				}
				if (!s_MsgId.equals(""))
				{
					s_MsgId = s_MsgId.substring(1, s_MsgId.length());
					System.out.println(s_MsgId);
					Str_Sql = "UPDATE t_onlinecall SET oc_status = '9'  where oc_id in (" + s_MsgId + ")";
					i_rtn = bgd.execute(Str_Sql);
					if(i_rtn == -1)
					{
						throw new Exception("ɾ���ʼ�ʧ��!");
					}
				}
			}
			
			//actionΪ"send/save"ʱ
			
			if(s_action.equalsIgnoreCase("send")||s_action.equalsIgnoreCase("save"))
			{
				//��ȡ���²���
				s_FileName = strOkPage;
				s_MsgId = getParameter(req, "msgid", true, false, true, "");
				s_MsgTitle = getParameter(req, "msgtitle", true, false, false, "");
				s_MsgContent = getParameter(req, "msgcontent", true, false, false, "");
				s_ReceiptId = getParameter(req, "receiptid", true, false, false, "");
				//s_MsgStatus = getParameter(req, "status", true, false, false, "");
				
				s_MsgTitle = ets.check_quote(s_MsgTitle).trim();
				s_MsgContent = ets.check_quote(s_MsgContent).trim();
				
				s_msg = "����";
				s_MsgStatus = "3";
				if(s_action.equalsIgnoreCase("send"))
				{
					s_msg = "����";
					s_MsgStatus = "4";
					//��ӷ��ͼ�¼
					Str_Sql = "SELECT MAX(oc_id) AS maxid FROM t_onlinecall ";
					bgd.executeSelect(Str_Sql);
					s_NewId = bgd.getFieldValue("maxid", 0);
					if(s_NewId.equals(""))
						s_NewId = "00000000000000000000";
					
					s_NewId = ets.AutoNum(s_NewId);
					
					Str_Sql = "INSERT INTO t_onlinecall (oc_id, oc_title, oc_content, oc_name, oc_obj, oc_datetime, oc_status) VALUES (";
					Str_Sql = Str_Sql + "'" + s_NewId + "', ";
					Str_Sql = Str_Sql + "'" + s_MsgTitle + "', ";
					Str_Sql = Str_Sql + "'" + s_MsgContent + "', ";
					Str_Sql = Str_Sql + "'" + s_UserId + "', ";
					Str_Sql = Str_Sql + "'" + s_ReceiptId + "', ";
					Str_Sql = Str_Sql + "'" + bgd.getDbDate() + "', ";
					Str_Sql = Str_Sql + "'1') ";
					i_rtn = bgd.execute(Str_Sql);
					if(i_rtn == -1)
					{
						throw new Exception(s_msg + "�ʼ�ʧ��A!");
					}
				}
				if(s_MsgId.equals("")) 
				{
					//�Զ�����
					Str_Sql = "SELECT MAX(oc_id) AS maxid FROM t_onlinecall ";
					bgd.executeSelect(Str_Sql);
					s_MsgId = bgd.getFieldValue("maxid", 0);
					if(s_MsgId.equals(""))
						s_MsgId = "00000000000000000000";
						
					s_MsgId = ets.AutoNum(s_MsgId);
					Str_Sql = "INSERT INTO t_onlinecall (oc_id, oc_title, oc_content, oc_name, oc_obj, oc_datetime, oc_status) VALUES (";
					Str_Sql = Str_Sql + "'" + s_MsgId + "', ";
					Str_Sql = Str_Sql + "'" + s_MsgTitle + "', ";
					Str_Sql = Str_Sql + "'" + s_MsgContent + "', ";
					Str_Sql = Str_Sql + "'" + s_UserId + "', ";
					Str_Sql = Str_Sql + "'" + s_ReceiptId + "', ";
					Str_Sql = Str_Sql + "'" + bgd.getDbDate() + "', ";
					Str_Sql = Str_Sql + "'" + s_MsgStatus + "') ";
				}
				else
				{
					//ƴд���µ�SQL���
					Str_Sql = "UPDATE t_onlinecall SET oc_title = '" + s_MsgTitle + "', ";
					Str_Sql = Str_Sql + " oc_content = '" + s_MsgContent + "', ";
					Str_Sql = Str_Sql + " oc_status = '" + s_MsgStatus + "', ";
					Str_Sql = Str_Sql + " oc_datetime = '" + bgd.getDbDate() + "', ";
					Str_Sql = Str_Sql + " oc_obj = '" + s_ReceiptId + "' ";
					Str_Sql = Str_Sql + " WHERE oc_id = '" + s_MsgId + "'";
				}
				
				//ִ�и���
				
				i_rtn = bgd.execute(Str_Sql);
				if(i_rtn == -1)
				{
					throw new Exception(s_msg + "�ʼ�ʧ��!");
				}
				s_msg = s_msg + "�ʼ��ɹ���";
				req.setAttribute("message", s_msg);
				getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);
				return;
			}
			
			req.setAttribute("message", s_msg);
			Str_Sql = Select_Sql + Conditions_Sql + Order_Sql;
			bgd.executeSelect(Str_Sql);
			getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);
			return;
		}
		catch(Exception e)
		{
			try
			{
				strErrPage = getParameter(req, "errPage", false, true, true, "");
				System.err.println("BeanMessager error: " + e);
				req.setAttribute("errmsg", e.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception ex)
			{
				System.err.println("BeanMessager���ô���ҳ��ʱ����");
			}
			
		}
	}
	
	//����Str_Sql
	public void setStr_Sql(String strSql)
	{
		Str_Sql = strSql;
	}
	
	//��ȡStr_Sql
	public String getStr_Sql()
	{
		return Str_Sql;
	}
	
	//����Order_Sql
	public void setOrder_Sql(String OrderSql)
	{
		Order_Sql = OrderSql;
	}
	
	//��ȡOrder_Sql
	public String getOrder_Sql()
	{
		return Order_Sql;
	}

	//����Conditions_Sql
	public void setConditions_Sql(String ConditionsSql)
	{
		Conditions_Sql = ConditionsSql;
	}
	
	//��ȡConditions_Sql
	public String getConditions_Sql()
	{
		return Conditions_Sql;
	}

	//����Select_Sql
	public void setSelect_Sql(String SelectSql)
	{
		Select_Sql = SelectSql;
	}
	
	//��ȡSelect_Sql
	public String getSelect_Sql()
	{
		return Select_Sql;
	}

}


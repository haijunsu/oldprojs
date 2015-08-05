//////////////////////////////////////////////
//
// 短信管理
//
//     苏海军     2002.1
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

			paramValue = request.getParameter(parameterName);
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
			//取得参数
			s_action = getParameter(req, "action", true, true, false, "list");
			//s_Group代表要取短息的状态
			s_Group = getParameter(req, "group", true, true, false, "inbox");
			//执行主体内容
			boolean create = false; //决定是否创建session，只有在Login中才为true
			HttpSession session = req.getSession(create);

			//判断session是否存在
			if(session == null)
			{
				req.setAttribute("message", "您没有登录！");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			
			s_UserId = (String)session.getAttribute("userid");
			
			//判断userid是否存在
			if(s_UserId == null)
			{
				req.setAttribute("message", "您没有登录！");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			s_pageNo = getParameter(req, "pageno", true, false, false, "1");
			s_UserId = session.getAttribute("userid").toString().trim();
			s_Group = s_Group.trim();
			req.setAttribute("pageno", s_pageNo);
			req.setAttribute("group", s_Group);
			//收件箱
			if(s_Group.equals("inbox"))
			{
				s_msg = "收件箱";
				Conditions_Sql = " where oc_obj = '" + s_UserId + "' AND oc_status in ('1', '2')";
			}
			//草稿箱
			if(s_Group.equals("draftbox"))
			{
				s_msg = "草稿箱";
				Conditions_Sql = " where oc_name = '" + s_UserId + "' AND oc_status = '3'";
			}
			//发件箱
			if(s_Group.equals("outbox"))
			{
				s_msg = "发件箱";
				Conditions_Sql = " where oc_name = '" + s_UserId + "' AND  oc_status = '4'";
			}

			s_FileName = getParameter(req, "listPage", false, true, true, "");
			//修改草稿
			if(s_action.equalsIgnoreCase("modify"))
			{
				s_FileName = getParameter(req, "modifyPage", false, true, true, "");
				req.setAttribute("action", "send");
				s_MsgId = getParameter(req, "msgid", true, false, true, "");
				Conditions_Sql = " where oc_id = '" + s_MsgId + "' ";
			}
			//回复邮件
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
			//读邮件
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
						throw new Exception("置邮件读标记时失败！");
					}
				}
			}
			//定新邮件
			if(s_action.equalsIgnoreCase("new"))
			{
				s_FileName = getParameter(req, "modifyPage", false, true, true, "");
				req.setAttribute("action", "send");
				Conditions_Sql = " where oc_id is null ";
			}
			//删除邮件
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
						throw new Exception("删除邮件失败!");
					}
				}
			}
			
			//action为"send/save"时
			
			if(s_action.equalsIgnoreCase("send")||s_action.equalsIgnoreCase("save"))
			{
				//获取更新参数
				s_FileName = strOkPage;
				s_MsgId = getParameter(req, "msgid", true, false, true, "");
				s_MsgTitle = getParameter(req, "msgtitle", true, false, false, "");
				s_MsgContent = getParameter(req, "msgcontent", true, false, false, "");
				s_ReceiptId = getParameter(req, "receiptid", true, false, false, "");
				//s_MsgStatus = getParameter(req, "status", true, false, false, "");
				
				s_MsgTitle = ets.check_quote(s_MsgTitle).trim();
				s_MsgContent = ets.check_quote(s_MsgContent).trim();
				
				s_msg = "保存";
				s_MsgStatus = "3";
				if(s_action.equalsIgnoreCase("send"))
				{
					s_msg = "发送";
					s_MsgStatus = "4";
					//添加发送记录
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
						throw new Exception(s_msg + "邮件失败A!");
					}
				}
				if(s_MsgId.equals("")) 
				{
					//自动发号
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
					//拼写更新的SQL语句
					Str_Sql = "UPDATE t_onlinecall SET oc_title = '" + s_MsgTitle + "', ";
					Str_Sql = Str_Sql + " oc_content = '" + s_MsgContent + "', ";
					Str_Sql = Str_Sql + " oc_status = '" + s_MsgStatus + "', ";
					Str_Sql = Str_Sql + " oc_datetime = '" + bgd.getDbDate() + "', ";
					Str_Sql = Str_Sql + " oc_obj = '" + s_ReceiptId + "' ";
					Str_Sql = Str_Sql + " WHERE oc_id = '" + s_MsgId + "'";
				}
				
				//执行更新
				
				i_rtn = bgd.execute(Str_Sql);
				if(i_rtn == -1)
				{
					throw new Exception(s_msg + "邮件失败!");
				}
				s_msg = s_msg + "邮件成功！";
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
				System.err.println("BeanMessager调用错误页面时出错！");
			}
			
		}
	}
	
	//设置Str_Sql
	public void setStr_Sql(String strSql)
	{
		Str_Sql = strSql;
	}
	
	//获取Str_Sql
	public String getStr_Sql()
	{
		return Str_Sql;
	}
	
	//设置Order_Sql
	public void setOrder_Sql(String OrderSql)
	{
		Order_Sql = OrderSql;
	}
	
	//获取Order_Sql
	public String getOrder_Sql()
	{
		return Order_Sql;
	}

	//设置Conditions_Sql
	public void setConditions_Sql(String ConditionsSql)
	{
		Conditions_Sql = ConditionsSql;
	}
	
	//获取Conditions_Sql
	public String getConditions_Sql()
	{
		return Conditions_Sql;
	}

	//设置Select_Sql
	public void setSelect_Sql(String SelectSql)
	{
		Select_Sql = SelectSql;
	}
	
	//获取Select_Sql
	public String getSelect_Sql()
	{
		return Select_Sql;
	}

}


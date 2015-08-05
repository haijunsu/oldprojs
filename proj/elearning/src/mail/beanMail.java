//////////////////////////////////////////////
//
// 邮件管理
//
//     柳林     2002.8
//
//////////////////////////////////////////////

package mail;

import java.text.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.*;
import com.htyz.*;

public class beanMail extends HttpServlet
{
	protected String Str_Sql = "";
	protected String Select_Sql = "SELECT * FROM t_mail ";
	protected String Order_Sql = " ORDER BY user_id,rcv_date DESC";
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
		String s_action, s_MailId,s_Subject,s_Content,s_Tomail,s_Ccmail,s_Bccmail,s_Frommail,s_Attachment,s_MailTime;
		String s_msg, s_Folder, s_UserId, s_FileName, s_pageNo;
		int i_rtn;
		Conditions_Sql = "";
		s_msg = "";
		beanGetdata bgd = new beanGetdata();
		req.setAttribute("beanGetdata", bgd);
		req.setAttribute("beanElearnTools", ets);
		try
		{
			//取得参数
			s_action = getParameter(req, "action", true, true, false, "list");
			//s_Folder代表要取短息的状态
			s_Folder = getParameter(req, "folder", true, true, false, "inbox");
			//执行主体内容
			boolean create = false; //决定是否创建session，只有在Login中才为true
			HttpSession session = req.getSession(create);

			//判断session是否存在
			if(session == null)
			{
				req.setAttribute("message", "您没有登录！");
				s_FileName=getParameter(req, "action", false, true, true, "");
				getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);
				return;
			}
			
			
			s_UserId = (String)session.getAttribute("userid");
			
			//判断userid是否存在
			if(s_UserId == null)
			{
				req.setAttribute("message", "您没有登录！");
				s_FileName=getParameter(req, "action", false, true, true, "");
				getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);
				return;
			}
			
			s_pageNo = getParameter(req, "pageno", true, false, false, "1");
			s_UserId = session.getAttribute("userid").toString().trim();
			s_Folder = s_Folder.trim();
			s_FileName = getParameter(req, "listPage", false, true, true, "");
			req.setAttribute("pageno", s_pageNo);
			req.setAttribute("folder", s_Folder);
			//收件箱
			if(s_Folder.equalsIgnoreCase("inbox"))
			{
				s_msg = "收件箱";
				Conditions_Sql = " where folder = '0001'";
				//if(s_action.equalsIgnoreCase("delete") || s_action.equalsIgnoreCase("move"))
			//		s_FileName="/mail/webmail/folder/inbox.htm";
			}
			//草稿箱
			if(s_Folder.equalsIgnoreCase("draftbox"))
			{
				s_msg = "草稿箱";
				Conditions_Sql = " where folder = '0003'";
			//	if(s_action.equalsIgnoreCase("delete") || s_action.equalsIgnoreCase("move"))
			//		s_FileName="/mail/webmail/folder/draft.htm";
			}
			//发件箱
			if(s_Folder.equalsIgnoreCase("outbox"))
			{
				s_msg = "发件箱";
				Conditions_Sql = " where folder = '0002'";
			//	if(s_action.equalsIgnoreCase("delete") || s_action.equalsIgnoreCase("move"))
			//		s_FileName="/mail/webmail/folder/outbox.htm";
			}
			//垃圾箱
			if(s_Folder.equalsIgnoreCase("garbagebox"))
			{
				s_msg = "垃圾箱";
				Conditions_Sql = " where folder = '0000'";
			//	if(s_action.equalsIgnoreCase("delete") || s_action.equalsIgnoreCase("move"))
		//			s_FileName="/mail/webmail/folder/garbage.htm";
			}
			//已发送邮件箱
			if(s_Folder.equalsIgnoreCase("sendbox"))
			{
				s_msg = "垃圾箱";
				Conditions_Sql = " where folder = '0009'";
		//		if(s_action.equalsIgnoreCase("delete") || s_action.equalsIgnoreCase("move"))
		//			s_FileName="/mail/webmail/folder/outmail.htm";
			}

			//修改草稿
			if(s_action.equalsIgnoreCase("modify"))
			{
				s_FileName = getParameter(req, "modifyPage", false, true, true, "");
				req.setAttribute("action", "send");
				s_MailId = getParameter(req, "mailid", true, false, true, "");
				Conditions_Sql = " where oc_id = '" + s_MailId + "' ";
			}
			//回复邮件//转发邮件
			if(s_action.equalsIgnoreCase("reply")||s_action.equalsIgnoreCase("forward"))
			{
				s_FileName = getParameter(req, "modifyPage", false, true, true, "");
				req.setAttribute("action", s_action);
				String s_countPara = getParameter(req, "countInPage", true, false, true, "");
				int i_count = Integer.parseInt(s_countPara);
				s_MailId = "";
				for (int i=0; i<i_count; i++)
				{
					String s_selectedItem = getParameter(req, "select" + Integer.toString(i), true, false, false, "");
					if (s_selectedItem.equals(""))
						continue;
					else
						s_MailId =  s_selectedItem ;
				}
				if (s_MailId.equals(""))
					req.setAttribute("action", "new");
				else	
					Conditions_Sql = " where mail_id = '" + s_MailId + "' ";
			}
			

			//读邮件
			if(s_action.equalsIgnoreCase("read"))
			{
				s_FileName = getParameter(req, "readPage", false, true, true, "");
				req.setAttribute("action", "read");
				s_MailId = getParameter(req, "mailid", true, false, true, "");
				Conditions_Sql = " where mail_id = '" + s_MailId + "' ";
				if(s_Folder.equals("inbox"))
				{
					Str_Sql = "UPDATE t_mail SET status = '2'  where mail_id = '" + s_MailId + "' ";
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
				req.setAttribute("action", "new");
				Conditions_Sql = " where mail_id is null ";
			}
			//删除邮件
			if(s_action.equalsIgnoreCase("delete"))
			{
				String s_countPara = getParameter(req, "countInPage", true, false, true, "");
				int i_count = Integer.parseInt(s_countPara);
				s_MailId = "";
				for (int i=0; i<i_count; i++)
				{
					String s_selectedItem = getParameter(req, "select" + Integer.toString(i), true, false, false, "");
					if (s_selectedItem.equals(""))
						continue;
					else
						s_MailId = s_MailId + ",'" + s_selectedItem + "'";
				}
				if (!s_MailId.equals(""))
				{
					s_MailId = s_MailId.substring(1, s_MailId.length());
					if(s_Folder.equalsIgnoreCase("garbagebox"))
						Str_Sql="Delete t_mail WHERE folder='0000' and mail_id in (" + s_MailId + ")";
					else
						Str_Sql = "UPDATE t_mail SET folder = '0000'  where mail_id in (" + s_MailId + ")";
//			System.out.println(Str_Sql);
					i_rtn = bgd.execute(Str_Sql);
					if(i_rtn == -1)
					{
						throw new Exception("删除邮件失败!");
					}
				}
			}
			//移动邮件到收件箱
			if(s_action.equalsIgnoreCase("move"))
			{
				String s_countPara = getParameter(req, "countInPage", true, false, true, "");
				int i_count = Integer.parseInt(s_countPara);
				s_MailId = "";
				for (int i=0; i<i_count; i++)
				{
					String s_selectedItem = getParameter(req, "select" + Integer.toString(i), true, false, false, "");
					if (s_selectedItem.equals(""))
						continue;
					else
						s_MailId = s_MailId + ",'" + s_selectedItem + "'";
				}
				if (!s_MailId.equals(""))
				{
					s_MailId = s_MailId.substring(1, s_MailId.length());
//			System.out.println(s_MailId);
					Str_Sql = "UPDATE t_mail SET folder = '0002'  where mail_id in (" + s_MailId + ")";
					i_rtn = bgd.execute(Str_Sql);
					if(i_rtn == -1)
					{
						throw new Exception("移动邮件失败!");
					}
				}
			}
			
			//action为"send/save"时
			
			if(s_action.equalsIgnoreCase("send")||s_action.equalsIgnoreCase("save"))
			{
				//获取更新参数
				s_FileName = getParameter(req, "okPage", false, true, true, "");
				s_MailId = getParameter(req, "mailid", true, false, true, "");
				s_Subject = getParameter(req, "subject", true, false, false, "");
				s_Content = getParameter(req, "content", true, false, false, "");
				s_Tomail = getParameter(req, "tomail", true, false, false, "");
				s_Ccmail = getParameter(req, "ccmail", true, false, false, "");
				s_Bccmail = getParameter(req, "bccmail", true, false, false, "");
				s_Frommail = getParameter(req, "frommail", true, false, false, "");
				s_Attachment = getParameter(req, "attachment", true, false, false, "");
				s_MailTime = bgd.getDbDate();
				//s_MsgStatus = getParameter(req, "status", true, false, false, "");
				
				s_Subject = ets.check_quote(s_Subject).trim();
				s_Content = ets.check_quote(s_Content).trim();
				
				s_msg = "保存";
				if(s_action.equalsIgnoreCase("save"))
				{
					//添加发送记录
					Str_Sql = "SELECT MAX(mail_id) AS maxid FROM t_mail ";
					bgd.executeSelect(Str_Sql);
					String s_NewId = bgd.getFieldValue("maxid", 0);
					if(s_NewId.equals(""))
						s_NewId = "00000000000000000000";
					
					s_NewId = ets.AutoNum(s_NewId);
					
					Str_Sql = "INSERT INTO t_mail (mail_id, user_id ,tomail,ccmail,bccmail,subject,frommail,content,attachment,mailtime,folder,status) VALUES (";
					Str_Sql = Str_Sql + "'" + s_NewId + "', ";
					Str_Sql = Str_Sql + "'" + s_UserId + "', ";
					Str_Sql = Str_Sql + "'" + s_Tomail + "', ";
					Str_Sql = Str_Sql + "'" + s_Ccmail + "', ";
					Str_Sql = Str_Sql + "'" + s_Bccmail + "', ";
					Str_Sql = Str_Sql + "'" + s_Subject + "', ";
					Str_Sql = Str_Sql + "'" + s_Frommail + "', ";
					Str_Sql = Str_Sql + "'" + s_Content + "', ";
					Str_Sql = Str_Sql + "'" + s_Attachment + "', ";
					Str_Sql = Str_Sql + "'" + s_MailTime + "', ";
					Str_Sql = Str_Sql + "'0003','1') ";
System.out.println("SQL="+Str_Sql);					
					i_rtn = bgd.execute(Str_Sql);
					if(i_rtn == -1)
					{
						throw new Exception(s_msg + "邮件失败A!");
					}
				}
				if(s_MailId.equals("")) 
				{
					//自动发号
					Str_Sql = "SELECT MAX(oc_id) AS maxid FROM t_onlinecall ";
					bgd.executeSelect(Str_Sql);
					s_MailId = bgd.getFieldValue("maxid", 0);
					if(s_MailId.equals(""))
						s_MailId = "00000000000000000000";
						
					s_MailId = ets.AutoNum(s_MailId);
				//	Str_Sql = "INSERT INTO t_onlinecall (oc_id, oc_title, oc_content, oc_name, oc_obj, oc_datetime, oc_status) VALUES (";
				//	Str_Sql = Str_Sql + "'" + s_MailId + "', ";
				//	Str_Sql = Str_Sql + "'" + s_Subject + "', ";
				//	Str_Sql = Str_Sql + "'" + s_Content + "', ";
				//	Str_Sql = Str_Sql + "'" + s_UserId + "', ";
				//	Str_Sql = Str_Sql + "'" + s_ReceiptId + "', ";
				//	Str_Sql = Str_Sql + "'" + bgd.getDbDate() + "', ";
				//	Str_Sql = Str_Sql + "'" + s_MsgStatus + "') ";
				}
				else
				{
					//拼写更新的SQL语句
				//	Str_Sql = "UPDATE t_onlinecall SET oc_title = '" + s_MsgTitle + "', ";
				//	Str_Sql = Str_Sql + " oc_content = '" + s_MsgContent + "', ";
				//	Str_Sql = Str_Sql + " oc_status = '" + s_MsgStatus + "', ";
				//	Str_Sql = Str_Sql + " oc_datetime = '" + bgd.getDbDate() + "', ";
				//	Str_Sql = Str_Sql + " oc_obj = '" + s_ReceiptId + "' ";
				//	Str_Sql = Str_Sql + " WHERE oc_id = '" + s_MailId + "'";
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
	System.out.println("================================");
	System.out.println(s_FileName);
	System.out.println("=================================");
	System.out.println(s_Folder);
			getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);
			return;
		}
		catch(Exception e)
		{
			try
			{
				System.err.println("BeanMessager error: " + e);
				s_FileName=getParameter(req, "errPage", false, true, true, "");
				req.setAttribute("errmsg", e.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);
				return;
			}
			catch(Exception ex)
			{
				System.err.println("BeanMessager调用错误页面时出错！");
			}
			
		}
	}
    /**
     * 获得并处理消息
     */
    public int receive(String popServer, String popUser, String popPassword) {
        
        Store store=null;
        Folder folder=null;
        int num = 0;
        
        try {
            // 得到缺省的session
            Properties props = System.getProperties();
            Session session = Session.getDefaultInstance(props, null);
            
            // 得到POP3 message store, 并连接
            store = session.getStore("pop3");
            store.connect(popServer, popUser, popPassword);
            
            // 得到缺省的Folder对象
            folder = store.getDefaultFolder();
            if (folder == null) throw new Exception("No default folder");
            
            // 得到Folder对象的INBOX
            folder = folder.getFolder("INBOX");
            if (folder == null) throw new Exception("No POP3 INBOX");
            
            // 以只读的方式打开文件夹
            folder.open(Folder.READ_ONLY);
            
            // 获得消息并显示
            Message[] msgs = folder.getMessages();
            num = msgs.length;
            for (int msgNum = 0; msgNum < msgs.length; msgNum++) {
                printMessage(msgs[msgNum]);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            //关闭对象
            try {
                if (folder!=null) folder.close(false);
                if (store!=null) store.close();
            }
            catch (Exception ex2) {ex2.printStackTrace();}
        }
        return num;
    }
    
    /**
     * 输出邮件的内容
     */
    public static void printMessage(Message message) {
        try {
            // 得到邮件头的信息
            String from=((InternetAddress)message.getFrom()[0]).getPersonal();
            if (from==null) from=((InternetAddress)message.getFrom()[0]).getAddress();
            System.out.println("FROM: "+from);
            
            String subject=message.getSubject();
            System.out.println("SUBJECT: "+subject);
            
            // 得到邮件的体
            Part messagePart=message;
            Object content=messagePart.getContent();
            
            // 如果是multipart消息
            if (content instanceof Multipart) {
                messagePart=((Multipart)content).getBodyPart(0);
                System.out.println("[ Multipart Message ]");
            }
            
            // 得到contentType
            String contentType=messagePart.getContentType();
            
            // 如果邮件的内容是简单无格式的文本，则输出到屏幕
            System.out.println("CONTENT:"+contentType);
            
            if (contentType.startsWith("text/plain")
            || contentType.startsWith("text/html")) {
                InputStream is = messagePart.getInputStream();
                
                BufferedReader reader
                =new BufferedReader(new InputStreamReader(is));
                String thisLine=reader.readLine();
                
                while (thisLine!=null) {
                    System.out.println(thisLine);
                    thisLine=reader.readLine();
                }
            }
            
            System.out.println("-----------------------------");
        }
        catch (Exception ex) {
            ex.printStackTrace();
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


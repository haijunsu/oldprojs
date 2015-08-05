//////////////////////////////////////////////
//
// �ʼ�����
//
//     ����     2002.8
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
			//ȡ�ò���
			s_action = getParameter(req, "action", true, true, false, "list");
			//s_Folder����Ҫȡ��Ϣ��״̬
			s_Folder = getParameter(req, "folder", true, true, false, "inbox");
			//ִ����������
			boolean create = false; //�����Ƿ񴴽�session��ֻ����Login�в�Ϊtrue
			HttpSession session = req.getSession(create);

			//�ж�session�Ƿ����
			if(session == null)
			{
				req.setAttribute("message", "��û�е�¼��");
				s_FileName=getParameter(req, "action", false, true, true, "");
				getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);
				return;
			}
			
			
			s_UserId = (String)session.getAttribute("userid");
			
			//�ж�userid�Ƿ����
			if(s_UserId == null)
			{
				req.setAttribute("message", "��û�е�¼��");
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
			//�ռ���
			if(s_Folder.equalsIgnoreCase("inbox"))
			{
				s_msg = "�ռ���";
				Conditions_Sql = " where folder = '0001'";
				//if(s_action.equalsIgnoreCase("delete") || s_action.equalsIgnoreCase("move"))
			//		s_FileName="/mail/webmail/folder/inbox.htm";
			}
			//�ݸ���
			if(s_Folder.equalsIgnoreCase("draftbox"))
			{
				s_msg = "�ݸ���";
				Conditions_Sql = " where folder = '0003'";
			//	if(s_action.equalsIgnoreCase("delete") || s_action.equalsIgnoreCase("move"))
			//		s_FileName="/mail/webmail/folder/draft.htm";
			}
			//������
			if(s_Folder.equalsIgnoreCase("outbox"))
			{
				s_msg = "������";
				Conditions_Sql = " where folder = '0002'";
			//	if(s_action.equalsIgnoreCase("delete") || s_action.equalsIgnoreCase("move"))
			//		s_FileName="/mail/webmail/folder/outbox.htm";
			}
			//������
			if(s_Folder.equalsIgnoreCase("garbagebox"))
			{
				s_msg = "������";
				Conditions_Sql = " where folder = '0000'";
			//	if(s_action.equalsIgnoreCase("delete") || s_action.equalsIgnoreCase("move"))
		//			s_FileName="/mail/webmail/folder/garbage.htm";
			}
			//�ѷ����ʼ���
			if(s_Folder.equalsIgnoreCase("sendbox"))
			{
				s_msg = "������";
				Conditions_Sql = " where folder = '0009'";
		//		if(s_action.equalsIgnoreCase("delete") || s_action.equalsIgnoreCase("move"))
		//			s_FileName="/mail/webmail/folder/outmail.htm";
			}

			//�޸Ĳݸ�
			if(s_action.equalsIgnoreCase("modify"))
			{
				s_FileName = getParameter(req, "modifyPage", false, true, true, "");
				req.setAttribute("action", "send");
				s_MailId = getParameter(req, "mailid", true, false, true, "");
				Conditions_Sql = " where oc_id = '" + s_MailId + "' ";
			}
			//�ظ��ʼ�//ת���ʼ�
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
			

			//���ʼ�
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
						throw new Exception("���ʼ������ʱʧ�ܣ�");
					}
				}
			}
			//�����ʼ�
			if(s_action.equalsIgnoreCase("new"))
			{
				s_FileName = getParameter(req, "modifyPage", false, true, true, "");
				req.setAttribute("action", "new");
				Conditions_Sql = " where mail_id is null ";
			}
			//ɾ���ʼ�
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
						throw new Exception("ɾ���ʼ�ʧ��!");
					}
				}
			}
			//�ƶ��ʼ����ռ���
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
						throw new Exception("�ƶ��ʼ�ʧ��!");
					}
				}
			}
			
			//actionΪ"send/save"ʱ
			
			if(s_action.equalsIgnoreCase("send")||s_action.equalsIgnoreCase("save"))
			{
				//��ȡ���²���
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
				
				s_msg = "����";
				if(s_action.equalsIgnoreCase("save"))
				{
					//��ӷ��ͼ�¼
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
						throw new Exception(s_msg + "�ʼ�ʧ��A!");
					}
				}
				if(s_MailId.equals("")) 
				{
					//�Զ�����
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
					//ƴд���µ�SQL���
				//	Str_Sql = "UPDATE t_onlinecall SET oc_title = '" + s_MsgTitle + "', ";
				//	Str_Sql = Str_Sql + " oc_content = '" + s_MsgContent + "', ";
				//	Str_Sql = Str_Sql + " oc_status = '" + s_MsgStatus + "', ";
				//	Str_Sql = Str_Sql + " oc_datetime = '" + bgd.getDbDate() + "', ";
				//	Str_Sql = Str_Sql + " oc_obj = '" + s_ReceiptId + "' ";
				//	Str_Sql = Str_Sql + " WHERE oc_id = '" + s_MailId + "'";
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
				System.err.println("BeanMessager���ô���ҳ��ʱ����");
			}
			
		}
	}
    /**
     * ��ò�������Ϣ
     */
    public int receive(String popServer, String popUser, String popPassword) {
        
        Store store=null;
        Folder folder=null;
        int num = 0;
        
        try {
            // �õ�ȱʡ��session
            Properties props = System.getProperties();
            Session session = Session.getDefaultInstance(props, null);
            
            // �õ�POP3 message store, ������
            store = session.getStore("pop3");
            store.connect(popServer, popUser, popPassword);
            
            // �õ�ȱʡ��Folder����
            folder = store.getDefaultFolder();
            if (folder == null) throw new Exception("No default folder");
            
            // �õ�Folder�����INBOX
            folder = folder.getFolder("INBOX");
            if (folder == null) throw new Exception("No POP3 INBOX");
            
            // ��ֻ���ķ�ʽ���ļ���
            folder.open(Folder.READ_ONLY);
            
            // �����Ϣ����ʾ
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
            //�رն���
            try {
                if (folder!=null) folder.close(false);
                if (store!=null) store.close();
            }
            catch (Exception ex2) {ex2.printStackTrace();}
        }
        return num;
    }
    
    /**
     * ����ʼ�������
     */
    public static void printMessage(Message message) {
        try {
            // �õ��ʼ�ͷ����Ϣ
            String from=((InternetAddress)message.getFrom()[0]).getPersonal();
            if (from==null) from=((InternetAddress)message.getFrom()[0]).getAddress();
            System.out.println("FROM: "+from);
            
            String subject=message.getSubject();
            System.out.println("SUBJECT: "+subject);
            
            // �õ��ʼ�����
            Part messagePart=message;
            Object content=messagePart.getContent();
            
            // �����multipart��Ϣ
            if (content instanceof Multipart) {
                messagePart=((Multipart)content).getBodyPart(0);
                System.out.println("[ Multipart Message ]");
            }
            
            // �õ�contentType
            String contentType=messagePart.getContentType();
            
            // ����ʼ��������Ǽ��޸�ʽ���ı������������Ļ
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


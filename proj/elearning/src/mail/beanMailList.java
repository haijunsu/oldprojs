
package mail;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.htyz.*;

public class beanMailList extends HttpServlet implements java.io.Serializable
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
				   s_folder,
				   s_codeValue,
				   s_classId,
				   s_className,
				   s_classType,
				   s_keywords,
				   s_description,
				   s_classTime,
				   s_passStander,
				   s_classStatus,
				   s_message,
				   s_navigation,
				   s_queryId,
				   s_queryColoum,
				   s_catalog,
				   s_page,
				   s_typePage,
				   s_listPage,
				   s_countInPage,
				   s_selectedItem,
				   s_removeItems,
				   s_applyType,
				   s_sql;
			int i_count, i_countInPage;
			Vector v_selectedItems = new Vector(); //ѡ����
			Vector v_oldSelectedItems = new Vector(); //ѡ����
			Vector v_reason = new Vector(); //��������
			Vector v_approve = new Vector(); //�Ѿ���׼���շѿγ�
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
			s_action = getParameter(req, "action", true, false, false, "list");
			s_open = getParameter(req, "open", true, false, false, "0");
			
			s_navigation = "<A href=\"" + req.getContextPath() + "/\" target=\"_top\">��ҳ</A>"+ "&gt;<A href=\"lessonApplyManager?applyType=\" target=\"_top\">";

			s_navigation = s_navigation + "����ѡ�޿�</A>";

			
			String s_whereType =  " AND status <> '0' ";
				
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
			
			if ( s_action.equalsIgnoreCase("showDetail"))
			{
				String s_display;
				s_folder = getParameter(req, "codeid", true, false, true, "");
				s_codeValue = getParameter(req, "codevalue", true, false, true, "");
				s_pageNo = getParameter(req, "pageno", true, false, false, "");
				s_reload = getParameter(req, "reload", true, false, false, "");
				
				if (s_pageNo.equals("")) s_pageNo = "1";
				
				reload = s_reload.equals("true");
				
				if (s_folder.equals("0013"))
				{
					s_sql = "SELECT * FROM t_code WHERE code_id = '" + s_codeValue + "'";
					s_display = getParameter(req, "typePage", false, true, true, "/lesson/lessonApplyTypeList.jsp");
					bqc.QueryCode("0013", s_codeValue);
					s_catalog = bqc.getCodeValue("code_namec", 0);
					s_navigation = s_navigation + "&gt;" + s_catalog;
					bgd.executeSelect(s_sql);
				}
				else
				{
					s_display = getParameter(req, "listPage", false, true, false, "/lesson/lessonApplyList.jsp");
					bqc.QueryCode(s_folder, s_codeValue);
					s_catalog = bqc.getCodeValue("code_namec", 0);
					s_navigation = s_navigation + "&gt;" + s_catalog;


					if (v_selectedItems==null) //��ʼ�����飬�����û��Ѿ�ѡ��Ŀγ�
					{
						v_selectedItems = new Vector(); //ѡ����
						v_oldSelectedItems = new Vector(); //ѡ����
						v_reason = new Vector(); //��������
						v_approve = new Vector(); //�Ѿ���׼���շѿγ�
						s_sql = "SELECT uc.class_id AS class_id, t.class_name AS class_name FROM t_user_class uc, t_class t WHERE uc.user_id = '" + s_userId + "' AND t.class_id = uc.class_id AND uc.status <> '9'" + s_whereType;
						bgd.executeSelect(s_sql);
						//System.out.println(s_sql);
						for (int i=0; i<bgd.getRowCount(); i++)
						{
							v_selectedItems.addElement(bgd.getFieldValue("class_id", i) + "," + bgd.getFieldValue("class_name", i));
							v_oldSelectedItems.addElement(bgd.getFieldValue("class_id", i) + "," + bgd.getFieldValue("class_name", i));
						}
						s_sql = "SELECT uc.class_id AS class_id, t.class_name AS class_name, uc.apply_reason AS apply_reason, uc.approval_id AS approval_id FROM t_user_apply uc, t_class t WHERE uc.user_id = '" + s_userId + "' AND t.class_id = uc.class_id " + s_whereType;
						bgd.executeSelect(s_sql);
						//System.out.println(s_sql);
						for (int i=0; i<bgd.getRowCount(); i++)
						{
							//v_oldSelectedItems.addElement(bgd.getFieldValue("class_id", i) + "," + bgd.getFieldValue("class_name", i));
							v_reason.addElement(bgd.getFieldValue("class_id", i) + "," + bgd.getFieldValue("class_name", i) + "," + bgd.getFieldValue("apply_reason", i));
							if ( bgd.getFieldValue("approval_id", i).trim().equals(""))
								v_selectedItems.addElement(bgd.getFieldValue("class_id", i) + "," + bgd.getFieldValue("class_name", i));
							else
								v_approve.addElement(bgd.getFieldValue("class_id", i));
						}
					}

					//����γ�����
					s_countInPage = getParameter(req, "countInPage", true, false, false, "0");
					i_countInPage = Integer.parseInt(s_countInPage);

					//ȡ�õ�ǰѡ��Ŀγ̣�����ԭ�е������������
					for (int i=0; i<i_countInPage; i++)
					{
						s_selectedItem = getParameter(req, "selectItem" + Integer.toString(i), true, false, false, "");
						if (s_selectedItem.equals(""))
							continue;
						if (v_selectedItems.isEmpty())
						{
							v_selectedItems.addElement(s_selectedItem);
							s_selectedItem = s_selectedItem + "," + getParameter(req, "reason" + Integer.toString(i), true, false, false, "");
							v_reason.addElement(s_selectedItem);
						}
						else
						{
							if (!v_selectedItems.contains(s_selectedItem))
								v_selectedItems.addElement(s_selectedItem);
							if (!v_reason.isEmpty())
							{
								for (int j=0; j<v_reason.size(); j++)
								{
									String temp = (String)v_reason.elementAt(j);
									if (temp.startsWith(s_selectedItem))
										v_reason.remove(j);
										break;
								}
							}
							s_selectedItem = s_selectedItem + "," + getParameter(req, "reason" + Integer.toString(i), true, false, false, "");
							v_reason.addElement(s_selectedItem);
						}
					}
					//System.out.println("2: " + v_selectedItems.toString());
					
					//ȥ���ѱ�ȡ���Ŀγ�
					s_removeItems = getParameter(req, "removeItems", true, false, false, "");
					StringTokenizer tokens = new StringTokenizer(s_removeItems, "|");
					while(tokens.hasMoreTokens())
					{
						s_classId = tokens.nextToken().trim();
						if (v_selectedItems.contains(s_classId))
							v_selectedItems.remove(s_classId);
					}

					//��ѡ�������ݵ�JSP

					
					s_sql = "SELECT * FROM t_class WHERE substring(class_id, 1, 8) = '" + s_folder + s_codeValue + "'  AND class_status = '1' " + s_whereType
							+ " AND class_id not in (select class_id from t_user_class uc, t_user u where uc.user_id = u.group_id and u.user_id = '" + s_userId + "') " 
							+ " ORDER BY class_id";
					if (s_pageNo.equals("1")||reload||bgd.getRowCount()==0)
					{
						//System.out.println("ˢ������");
						bgd.executeSelect(s_sql);
						if (reload) reload = false;
					}
					if (s_pageNo.equals("0")) //���½��
					{
						s_display = "/Ok.jsp";
						//����ԭ�еĿγ̱�
						for (int i=0; i<v_oldSelectedItems.size(); i++)
						{
							s_classId = (String)v_oldSelectedItems.elementAt(i);
							if (v_selectedItems.contains(s_classId)) //��ԭ�еĿγ̼���
							{
								s_classId = s_classId.substring(0, 14);
								s_sql = "UPDATE t_user_class SET status = '1' WHERE user_id = '" + s_userId + "' AND class_id = '" + s_classId + "'"; 
								bgd.execute(s_sql);
							}
							else //ɾ��ԭ�еĿγ�
							{
								s_classId = s_classId.substring(0, 14);
								s_sql = "UPDATE t_user_class SET status = '9' WHERE user_id = '" + s_userId + "' AND class_id = '" + s_classId + "'"; 
								bgd.execute(s_sql);
							}
						}
						if (s_userId.equals("free"))  //����ʱ��s_ApplyType��Ϊs_userId
						{
							for (int j=0; j<v_selectedItems.size(); j++)
							{
								s_classId = (String)v_selectedItems.elementAt(j);
								s_classId = s_classId.substring(0, 14);
								//ɾ���ɼ�¼
								s_sql = "DELETE FROM t_user_class WHERE user_id = '" + s_userId + "' AND class_id = '" + s_classId + "'";
								//System.out.println("Delete SQL: " + s_sql);
								bgd.execute(s_sql);
								
								//����¼�¼
								s_sql = "INSERT INTO t_user_class "
										+ "(user_id, class_id, start_time, status) VALUES " 
										+ "('" + s_userId + "', "
										+ "'" + s_classId + "', "
										+ "getdate(), "
										+ "'1')";
								//System.out.println("Insert SQL: " + s_sql);
								if(bgd.execute(s_sql) == -1)
									throw new Exception("������ѿγ̳���");
									
								//�ɹ���session�е��������
//								session.removeAttribute(s_applyType + ".selectedItems");
//								session.removeAttribute(s_applyType + ".oldSelectedItems");
//								session.removeAttribute(s_applyType + ".reason");
//								session.removeAttribute(s_applyType + ".approve");
								req.setAttribute("message", "������ѿγ̳ɹ���");
							}
						}
						else
						{
							s_sql = "DELETE FROM t_user_apply WHERE user_id = '" + s_userId + "' and (approval_id is NULL or approval_id = '')"; 
							bgd.execute(s_sql);
							for (int i=0; i<v_selectedItems.size(); i++)
							{
								String s_reason = "";
								s_classId = (String)v_selectedItems.elementAt(i);
								if (!v_oldSelectedItems.contains(s_classId))
								{
								//����������¼
									for (int j=0; j<v_reason.size(); j++)
									{
										s_reason = (String) v_reason.elementAt(j);
										if (s_reason.startsWith(s_classId))
										{
											if (s_reason.length()>s_classId.length() + 1)
												s_reason = s_reason.substring(s_classId.length() + 1, s_reason.length());
											else
												s_reason = "";
											break;
										}
									}
									s_classId = s_classId.substring(0, 14);
									if ( v_approve.contains(s_classId))
									{
										s_sql = "DELETE FROM t_user_class WHERE user_id = '" + s_userId + "' AND class_id = '" + s_classId + "'";
										//System.out.println("Delete SQL: " + s_sql);
										bgd.execute(s_sql);
										
										//����¼�¼
										s_sql = "INSERT INTO t_user_class "
												+ "(user_id, class_id, start_time, status) VALUES " 
												+ "('" + s_userId + "', "
												+ "'" + s_classId + "', "
												+ "getdate(), "
												+ "'1')";
										//System.out.println("Insert SQL: " + s_sql);
										if(bgd.execute(s_sql) == -1)
											throw new Exception("����ѡ�޿γ���");
									}
									else
									{
										s_sql = "INSERT INTO t_user_apply (apply_id,user_id, class_id, apply_time, apply_reason, apply_status) "
												+ "VALUES ('"+s_userId+s_classId+"','" + s_userId + "', "
												+ "'" + s_classId + "', "
												+ "getdate(), "
												+ "'" + s_reason + "', "
												+ "'0') ";
										//System.out.println(s_sql);
										bgd.execute(s_sql);
									}
								}
							}
//							session.removeAttribute(s_applyType + ".selectedItems");
//							session.removeAttribute(s_applyType + ".oldSelectedItems");
//							session.removeAttribute(s_applyType + ".reason");
//							session.removeAttribute(s_applyType + ".approve");
							s_display = "/Ok.jsp";
							req.setAttribute("message", "����ѡ�޿γɹ�����ȴ��γ̹���Ա��ˡ�");
						}
					}
				}
				req.setAttribute("bgd", bgd);
				req.setAttribute("navigation", s_navigation);
				req.setAttribute("catalog", s_catalog);
				req.setAttribute("folder", s_folder);
				req.setAttribute("codevalue", s_codeValue);
				req.setAttribute("pageno", s_pageNo);
				req.setAttribute("action", s_action);
				
				getServletConfig().getServletContext().getRequestDispatcher(s_display).forward(req, res);
				return;
			}


			if (s_action.equalsIgnoreCase("query"))
			{
				s_queryId = getParameter(req, "queryid", true, false, false, "");
				s_queryColoum = getParameter(req, "querycoloum", true, false, true, "");
				
				s_folder = getParameter(req, "folder", true, false, false, "");
				s_codeValue = getParameter(req, "codevalue", true, false, false, "");
				s_pageNo = getParameter(req, "pageno", true, false, false, "");
				s_reload = getParameter(req, "reload", true, false, false, "");
				
				if (s_pageNo.equals("")) s_pageNo = "1";
				
				reload = s_reload.equals("true");
				
				s_sql = "SELECT * FROM t_class WHERE " + s_queryColoum + " like '%" + s_queryId + "%' "
							+ " AND t_class.class_id not in (select class_id from t_user_class uc, t_user u where uc.user_id = u.group_id and u.user_id = '" + s_userId + "') "
							+ s_whereType +" ORDER BY t_class.class_id";
				if (s_queryColoum.equals("class_type"))
				{
					s_sql = "SELECT t_class.* FROM t_class, t_code WHERE t_class.class_type = t_code.code_value AND "
							+ " t_code.code_id = '0004' AND (t_code.code_namec like '%" + s_queryId + "%' OR t_code.code_value like '%" + s_queryId + "%') "
							+ " AND t_class.class_id not in (select class_id from t_user_class uc, t_user u where uc.user_id = u.group_id and u.user_id = '" + s_userId + "') "
							+ s_whereType +" ORDER BY t_class.class_id";
				}
				if (s_pageNo.equals("1")||reload)
				{
					System.out.println("ˢ������");
					bgd.executeSelect(s_sql);
					if (reload) reload = false;
				}
				s_catalog = "����";
				s_codeValue = s_codeValue + "&queryid=" + s_queryId + "&querycoloum=" + s_queryColoum;//���Ӳ�ѯ����
				req.setAttribute("bgd", bgd);
				req.setAttribute("navigation", s_navigation);
				req.setAttribute("catalog", s_catalog);
				req.setAttribute("folder", s_folder);
				req.setAttribute("codevalue", s_codeValue);
				req.setAttribute("pageno", s_pageNo);
				req.setAttribute("action", s_action);
				s_listPage = getParameter(req, "listPage", false, true, false, "/lesson/lessonApplyList.jsp");
				
				getServletConfig().getServletContext().getRequestDispatcher(s_listPage).forward(req, res);
				return;
			}
			
			if (s_action.equals("list"))
			{
				
				s_folder = getParameter(req, "folder", true, false, false, "00001");
				s_pageNo = getParameter(req, "pageno", true, false, false, "");
				s_reload = getParameter(req, "reload", true, false, false, "");
				
				if (s_pageNo.equals("")) s_pageNo = "1";
				
				reload = s_reload.equals("true");
				
				s_sql = "SELECT * FROM t_mail WHERE user_id='" + s_userId + "' AND folder='"+ s_folder + "' ORDER BY mailtime";
				if (s_pageNo.equals("1")||reload)
				{
			System.out.println(s_sql);
					bgd.executeSelect(s_sql);
					if (reload) reload = false;
				}
				s_catalog = "aaa";
				req.setAttribute("bgd", bgd);
				req.setAttribute("navigation", s_navigation);
				req.setAttribute("catalog", s_catalog);
				req.setAttribute("folder", s_folder);
				req.setAttribute("pageno", s_pageNo);
				req.setAttribute("action", s_action);
				s_listPage = getParameter(req, "listPage", false, true, false, "/mail/webmail/folder/main/mailList.jsp");
				
				getServletConfig().getServletContext().getRequestDispatcher(s_listPage).forward(req, res);
				return;
			}
			
			
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
				System.err.println("beanLessonApplyList error: ");
				ex.printStackTrace();
				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
				return;
			}
			catch(Exception e)
			{
				System.err.println("beanLessonApplyList���ô���ҳ��ʱ����");
				e.printStackTrace();
			}
		}
    }
}
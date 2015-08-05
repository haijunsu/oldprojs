
package lessadmin;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.htyz.*;
import com.htyz.system.SystemProperties;

public class beanLessonList extends HttpServlet implements java.io.Serializable
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
		String strLoginPage, strOkPage, strErrPage, strSubString;
		try
		{
			strLoginPage = getParameter(req, "loginPage", false, true, true, "");
			strOkPage = getParameter(req, "okPage", false, true, true, "");
			strErrPage = getParameter(req, "errPage", false, true, true, "");
			strSubString = SystemProperties.getProperty("db.substr");
			String s_userId,
				   s_pageNo,
				   s_reload,
				   s_open,
				   s_action,
				   s_codeId,
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
				   s_sql;
			int i_count, i_countInPage;
			Vector v_selectedItems = new Vector();
			Vector v_selectedGroups = new Vector();
    		beanElearnTools ets = new beanElearnTools(); 
    		beanQueryCodes bqc = new beanQueryCodes();
    		beanQueryCodes bqcTypes = new beanQueryCodes();
    		beanQueryCodes bqcStatus = new beanQueryCodes();
			
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
			
			//�ж��û��ǲ��ǿγ̹���Ա
			if (!ets.isRole(s_userId, "3"))
				throw new Exception("��û��ִ�д˲�����Ȩ�ޣ�");
				
			bqcTypes.QueryCode("0004");
			bqcStatus.QueryCode("0005");
			req.setAttribute("bqcTypes", bqcTypes);
			req.setAttribute("bqcStatus", bqcStatus);
			
			s_navigation = ets.getClassNavigation();
			//ȡ�ò���
			s_action = getParameter(req, "action", true, false, false, "list");
			s_open = getParameter(req, "open", true, false, false, "0");
			
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
				String s_display;
				s_codeId = getParameter(req, "codeid", true, false, true, "");
				s_codeValue = getParameter(req, "codevalue", true, false, true, "");
				s_pageNo = getParameter(req, "pageno", true, false, false, "");
				s_reload = getParameter(req, "reload", true, false, false, "");
				
				if (s_pageNo.equals("")) s_pageNo = "1";
				
				reload = s_reload.equals("true");
				if (s_codeId.equals("0013"))
				{
					s_sql = "SELECT * FROM t_code WHERE code_id = '" + s_codeValue + "'";
					s_display = getParameter(req, "typePage", false, true, true, "/lessadmin/lessonTypeList.jsp");
					bqc.QueryCode("0013", s_codeValue);
					s_catalog = bqc.getCodeValue("code_namec", 0);
					s_navigation = s_navigation + "&gt;" + s_catalog;
					bgd.executeSelect(s_sql);
				}
				else
				{
					s_sql = "SELECT * FROM t_class WHERE " + strSubString + "(class_id, 1, 8) = '" + s_codeId + s_codeValue + "' ORDER BY class_id";
					s_display = getParameter(req, "listPage", false, true, true, "/lessadmin/lessonList.jsp");
					bqc.QueryCode(s_codeId, s_codeValue);
					s_catalog = bqc.getCodeValue("code_namec", 0);
					s_navigation = ets.getClassNavigation(s_codeId) + "&gt;" + s_catalog;
					
					if (s_display.indexOf("Req") > -1)
					{
						//����γ�����
						
						s_sql = "SELECT * FROM t_class WHERE " + strSubString + "(class_id, 1, 8) = '" + s_codeId + s_codeValue + "' AND class_status = '1' ORDER BY class_id";
						if (session.getAttribute("selectedItems")!=null)
							v_selectedItems = (Vector)session.getAttribute("selectedItems");
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
							}
							else
							{
								if (!v_selectedItems.contains(s_selectedItem))
									v_selectedItems.addElement(s_selectedItem);
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
						//System.out.println("3: " + v_selectedItems.toString());

						//��ѡ�������ݵ�JSP
						session.setAttribute("selectedItems", v_selectedItems);
					}
					
					if (s_pageNo.equals("0")) //��ʾ�û���
					{
						if (session.getAttribute("selectedGroups")!=null)
							v_selectedGroups = (Vector)session.getAttribute("selectedGroups");
						s_display = getParameter(req, "groupPage", false, true, false, "/error.jsp");
						s_sql = "SELECT * FROM t_code WHERE code_id = '0002'";
						bgd.executeSelect(s_sql);
						s_pageNo = "1";
						s_action = "ShowGroup";
						session.setAttribute("selectedGroups", v_selectedGroups);
					}
					if (s_pageNo.equals("1")||reload||bgd.getRowCount()==0)
					{
			//			System.out.println("ˢ������");
						bgd.executeSelect(s_sql);
						if (reload) reload = false;
					}
				}
				req.setAttribute("bgd", bgd);
				req.setAttribute("navigation", s_navigation);
				req.setAttribute("catalog", s_catalog);
				req.setAttribute("codeid", s_codeId);
				req.setAttribute("codevalue", s_codeValue);
				req.setAttribute("pageno", s_pageNo);
				req.setAttribute("action", s_action);
				
				getServletConfig().getServletContext().getRequestDispatcher(s_display).forward(req, res);
				return;
			}

			if ( s_action.equalsIgnoreCase("ShowGroup"))
			{
				String s_display;
				s_pageNo = getParameter(req, "pageno", true, false, false, "");
				s_reload = getParameter(req, "reload", true, false, false, "");
				
				if (s_pageNo.equals("")) s_pageNo = "1";
				
				reload = s_reload.equals("true");
				
				s_sql = "SELECT * FROM t_code WHERE code_id = '0002'";

				s_display = getParameter(req, "groupPage", false, true, true, "/error.jsp");
				if (session.getAttribute("selectedItems")==null)
					throw new Exception("��������ѡ��γ̣�");
				else
					v_selectedItems = (Vector)session.getAttribute("selectedItems");
					
				if (session.getAttribute("selectedGroups")!=null)
					v_selectedGroups = (Vector)session.getAttribute("selectedGroups");
				s_countInPage = getParameter(req, "countInPage", true, false, false, "0");
				i_countInPage = Integer.parseInt(s_countInPage);

				//ȡ�õ�ǰѡ����û��飬����ԭ�е������������
				for (int i=0; i<i_countInPage; i++)
				{
					s_selectedItem = getParameter(req, "selectGroup" + Integer.toString(i), true, false, false, "");
					if (s_selectedItem.equals(""))
						continue;
					if (v_selectedGroups.isEmpty())
					{
						v_selectedGroups.addElement(s_selectedItem);
					}
					else
					{
						if (!v_selectedGroups.contains(s_selectedItem))
							v_selectedGroups.addElement(s_selectedItem);
					}
				}
				//System.out.println("2: " + v_selectedItems.toString());
				
				//ȥ���ѱ�ȡ�����û���
				s_removeItems = getParameter(req, "removeItems", true, false, false, "");
				StringTokenizer tokens = new StringTokenizer(s_removeItems, "|");
				while(tokens.hasMoreTokens())
				{
					s_classId = tokens.nextToken().trim();
					if (v_selectedGroups.contains(s_classId))
						v_selectedGroups.remove(s_classId);
				}
				//System.out.println("3: " + v_selectedItems.toString());

				//��ѡ�������ݵ�JSP
				session.setAttribute("selectedGroups", v_selectedGroups);
					
				if (s_pageNo.equals("1")||reload||bgd.getRowCount()==0)
				{
//					System.out.println("ˢ������");
					bgd.executeSelect(s_sql);
					if (reload) reload = false;
				}

				if (s_pageNo.equals("0")) //���½��
				{
					s_display = strOkPage;
					if (v_selectedGroups.isEmpty())
						throw new Exception("��û��ѡ���û��飡");
					if (v_selectedItems.isEmpty())
						throw new Exception("��û��ѡ��γ̣�");
					
					for (int i=0; i<v_selectedGroups.size(); i++)
					{
						s_codeId = (String)v_selectedGroups.elementAt(i);
						s_codeId = s_codeId.substring(0, s_codeId.indexOf(","));
						for (int j=0; j<v_selectedItems.size(); j++)
						{
							s_classId = (String)v_selectedItems.elementAt(j);
							s_classId = s_classId.substring(0, 14);
							//ɾ���ɼ�¼
							s_sql = "DELETE FROM t_user_class WHERE (user_id = '" + s_codeId + "' OR user_id IN (SELECT user_id FROM t_user WHERE group_id ='" + s_codeId + "')) AND class_id = '" + s_classId + "'";
		//	System.out.println("Delete SQL: " + s_sql);
							bgd.execute(s_sql);
							
							//����¼�¼
							s_sql = "INSERT INTO t_user_class "
									+ "(user_id, class_id, start_time, status) VALUES " 
									+ "('" + s_codeId + "', "
									+ "'" + s_classId + "', "
									+ "'"+bgd.getDbDate()+"', "
									+ "'1')";
		//	System.out.println("Insert SQL: " + s_sql);
							if(bgd.execute(s_sql) == -1)
								throw new Exception("���¾ɼ�¼ʱʧ�ܣ�");
								
							//�ɹ���session�е��������
							session.removeAttribute("selectedItems");
							session.removeAttribute("selectedGroups");
							req.setAttribute("message", "���޿�ָ���ɹ���");
							
						}
					}
				}
				req.setAttribute("bgd", bgd);
				req.setAttribute("pageno", s_pageNo);
				req.setAttribute("action", s_action);
				
				getServletConfig().getServletContext().getRequestDispatcher(s_display).forward(req, res);
				return;
			}

/*			if (s_action.equalsIgnoreCase("new"))
			{
				req.setAttribute("action", "add");
				getServletConfig().getServletContext().getRequestDispatcher("/admin/codeDetail.jsp").forward(req, res);
				return;
			}
*/
			if (s_action.equalsIgnoreCase("delete"))
			{
				s_classId = getParameter(req, "classid", true, false, true, "");
				//ɾ�����Ӧ���½�
				s_sql = "DELETE FROM t_lesson  WHERE class_id = '" + s_classId + "'";
					
				if (bgd.execute(s_sql) == -1)
				{
					s_action = "showErr";
					s_message = "ɾ���γ̵��½�ʱ";
				}
				//ɾ���γ�
				s_sql = "DELETE FROM t_class  WHERE class_id = '" + s_classId + "'";

				s_action = "showOk";
				s_message = "ɾ��";
					
				if (bgd.execute(s_sql) == -1)
				{
					s_action = "showErr";
					s_message = "ɾ���γ�";
				}
				req.setAttribute("message", s_message);

			}
			
			if (s_action.equalsIgnoreCase("add")||s_action.equalsIgnoreCase("update"))
			{
				s_className = getParameter(req, "classname", true, false, false, "");
				s_classType = getParameter(req, "classtype", true, false, false, "");
				s_keywords = getParameter(req, "keywords", true, false, false, "");
				s_description = getParameter(req, "description", true, false, false, "");
				s_classTime = getParameter(req, "classtime", true, false, false, "0");
				s_passStander = getParameter(req, "passstander", true, false, false, "0");
				s_classStatus = getParameter(req, "classstatus", true, false, false, "0");
				
				if (s_classTime.equals("")) s_classTime = "0";
				if (s_passStander.equals("")) s_passStander = "0";
				if (s_classStatus.equals("")) s_classStatus = "0";
								
				beanLessonDetail bld = new beanLessonDetail();
				bld.setClassName(s_className);
				bld.setClassType(s_classType);
				bld.setKeywords(s_keywords);
				bld.setDescription(s_description);
				bld.setClassTime(s_classTime);
				bld.setPassStander(s_passStander);
				bld.setClassStatus(s_classStatus);
				
				req.setAttribute("bld", bld);

				if (s_action.equalsIgnoreCase("add"))
				{
					s_message = "��ӿγ̳ɹ���";
					s_codeId = getParameter(req, "codeid", true, false, true, "");
					s_codeValue = getParameter(req, "codevalue", true, false, true, "");
					s_classId = s_codeId + s_codeValue;
					
					//�Զ�����
					s_sql = "SELECT max(class_id) FROM t_class WHERE " + strSubString + "(class_id, 1, 8) = '" + s_classId + "'";
					bgd.executeSelect(s_sql);
					String s_temp = bgd.getFieldValue(1, 0);
					if (s_temp.equals(""))
						s_temp = "000000";
					else
						s_temp = s_temp.substring(8, 14);
					s_classId = s_classId + ets.AutoNum(s_temp);
					
					s_sql = "INSERT INTO t_class "
							+ " (class_id, " 
							+ " class_name, " 
							+ " class_type, " 
							+ " keywords, " 
							+ " description, " 
							+ " class_time, " 
							+ " pass_stander, " 
							+ " set_time, " 
							+ " peoples, " 
							+ " pass_count, " 
							+ " class_status " 
							+ ") VALUES ("
							+ "'" + s_classId + "', "
							+ "'" + s_className + "', "
							+ "'" + s_classType + "', "
							+ "'" + s_keywords + "', "
							+ "'" + s_description + "', "
							+ "" + s_classTime + ", "
							+ "" + s_passStander + ", "
							+ "'"+bgd.getDbDate()+"', "
							+ "0, "
							+ "0, "
							+ "'0') ";
					if (bgd.execute(s_sql) == -1)
						throw new Exception("��ӿγ�ʱʧ�ܣ�");
					req.setAttribute("message", s_message);
					getServletConfig().getServletContext().getRequestDispatcher(strOkPage).forward(req, res);
					return;
				}
				else
				{
					s_message = "���¿γ̳ɹ���";
					s_classId = getParameter(req, "classid", true, false, true, "");
					bld.setClassId(s_classId);
					s_sql = "UPDATE t_class SET "
					        + "class_id = '" + s_classId + "', "
					        + "class_type = '" + s_classType + "', "
					        + "keywords = '" + s_keywords + "', "
					        + "description = '" + s_description + "', "
					        + "class_time = '" + s_classTime + "', "
					        + "pass_stander = '" + s_passStander + "', "
					        + "class_status = '" + s_classStatus + "' "
					        + " WHERE class_id = '" + s_classId + "'";
					if (bgd.execute(s_sql) == -1)
						throw new Exception("���¿γ�ʧ��!");
					req.setAttribute("message", s_message);
					getServletConfig().getServletContext().getRequestDispatcher(strOkPage).forward(req, res);
					return;
 				}
			}
			
			if (s_action.equalsIgnoreCase("query"))
			{
				s_queryId = getParameter(req, "queryid", true, false, false, "");
				s_queryColoum = getParameter(req, "querycoloum", true, false, true, "");
				
				s_codeId = getParameter(req, "codeid", true, false, false, "");
				s_codeValue = getParameter(req, "codevalue", true, false, false, "");
				s_pageNo = getParameter(req, "pageno", true, false, false, "");
				s_reload = getParameter(req, "reload", true, false, false, "");
				
				if (s_pageNo.equals("")) s_pageNo = "1";
				
				reload = s_reload.equals("true");
				
				s_sql = "SELECT * FROM t_class WHERE " + s_queryColoum + " like '%" + s_queryId + "%' ORDER BY class_id";
				if (s_queryColoum.equals("class_type"))
				{
					s_sql = "SELECT t_class.* FROM t_class, t_code WHERE t_class.class_type = t_code.code_value AND "
							+ " t_code.code_id = '0004' AND (t_code.code_namec like '%" + s_queryId + "%' OR t_code.code_value like '%" + s_queryId + "%') ORDER BY t_class.class_id";
				}
				if (s_queryColoum.equals("class_status"))
				{
					s_sql = "SELECT t_class.* FROM t_class, t_code WHERE t_class.class_status = t_code.code_value AND "
							+ " t_code.code_id = '0005' AND (t_code.code_namec like '%" + s_queryId + "%' OR t_code.code_value like '%" + s_queryId + "%') ORDER BY t_class.class_id";
				}
				//System.out.println(s_sql);
				//bgd.executeSelect(s_sql);
				if (s_pageNo.equals("1")||reload)
				{
//					System.out.println("ˢ������");
					bgd.executeSelect(s_sql);
					if (reload) reload = false;
				}
				s_catalog = "����";
				s_codeValue = s_codeValue + "&queryid=" + s_queryId + "&querycoloum=" + s_queryColoum;//���Ӳ�ѯ����
				req.setAttribute("bgd", bgd);
				req.setAttribute("navigation", s_navigation);
				req.setAttribute("catalog", s_catalog);
				req.setAttribute("codeid", s_codeId);
				req.setAttribute("codevalue", s_codeValue);
				req.setAttribute("pageno", s_pageNo);
				req.setAttribute("action", s_action);
				s_listPage = getParameter(req, "listPage", false, true, true, "/lessadmin/lessonList.jsp");
				
				getServletConfig().getServletContext().getRequestDispatcher(s_listPage).forward(req, res);
				return;
			}
			
			if (s_action.equals("list")) s_action = "showDetail";
			req.setAttribute("action", s_action);
			req.setAttribute("flag", s_open);
			s_page = getParameter(req, "page", false, true, true, "/lessadmin/lessonTree.jsp");
			
			getServletConfig().getServletContext().getRequestDispatcher(s_page).forward(req, res);
			return;
		}
		catch(Exception ex)
		{
			try
			{
				strErrPage = getParameter(req, "errPage", false, true, true, "");
				System.err.println("beanLessonList error: " + ex);
				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception e)
			{
				System.err.println("beanLessonList���ô���ҳ��ʱ����");
				e.printStackTrace();
			}
		}
    }
}
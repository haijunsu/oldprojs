
package lesson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.htyz.*;

public class beanLessonApplyList extends HttpServlet implements java.io.Serializable
{
	private beanGetdata bgd = new beanGetdata();
	boolean reload = false;
	
	//初始化Servlet
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
		beanElearnTools ets = new beanElearnTools();
		java.lang.String[] parameterValues = null;
		java.lang.String paramValue = null;
		java.lang.String paramValue_GBK = null;

		//从request中获取参数
		if (checkRequestParameters)
		{
			parameterValues = request.getParameterValues(parameterName);

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
		String strLoginPage, strOkPage, strErrPage;
		try
		{
			strLoginPage = getParameter(req, "loginPage", false, true, true, "");
			strOkPage = getParameter(req, "okPage", false, true, true, "");
			strErrPage = getParameter(req, "errPage", false, true, true, "");
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
				   s_applyType,
				   s_sql;
			int i_count, i_countInPage;
			Vector v_selectedItems = new Vector(); //选择项
			Vector v_oldSelectedItems = new Vector(); //选择项
			Vector v_reason = new Vector(); //申请理由
			Vector v_approve = new Vector(); //已经批准的收费课程
	   		beanElearnTools ets = new beanElearnTools(); 
	   		beanQueryCodes bqc = new beanQueryCodes();
			
			boolean create = false; //决定是否创建session，只有在Login中才为true
			HttpSession session = req.getSession(create);

			//判断session是否存在
			if(session == null)
			{
				req.setAttribute("message", "您没有登录！");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			//判断用户是否登录
			s_userId = (String)session.getAttribute("userid");
			if(s_userId == null)
			{
				req.setAttribute("message", "您没有登录！");
				getServletConfig().getServletContext().getRequestDispatcher(strLoginPage).forward(req, res);
				return;
			}
			
			//取得参数
			s_action = getParameter(req, "action", true, false, false, "list");
			s_open = getParameter(req, "open", true, false, false, "0");
			s_applyType = getParameter(req, "applyType", true, false, false, "");
			
			req.setAttribute("applyType", s_applyType);
//			if (s_applyType.equals(""))
//				s_applyType = (String) session.getAttribute("applyType");
//			
//			if (s_applyType == null)
//				throw new Exception("没有指定申请类型！");
				
				
//			session.setAttribute("applyType", s_applyType);
			
				s_navigation = "<A href=\"/elearning/\" target=\"_top\">首页</A>"
							+ "&gt;<A href=\"lessonApplyManager?applyType=" + s_applyType + "\" target=\"_parent\">";
			if (s_applyType.equals("free"))
			{
				s_navigation = s_navigation + "选择免费课程</A>";
			}
			else
			{
				s_navigation = s_navigation + "申请选修课</A>";
			}
			
			String s_whereType =  " AND class_type <> '0' ";
			if (s_applyType.equals("free"))
				s_whereType = " AND class_type = '0' ";
				
			if (s_action.equalsIgnoreCase("showOk"))
			{
				s_message = getParameter(req, "message", true, false, false, "");
				s_message = s_message + "操作成功！";
				req.setAttribute("message", s_message);
				getServletConfig().getServletContext().getRequestDispatcher(strOkPage).forward(req, res);
				return;
			}
			
			if (s_action.equalsIgnoreCase("showErr"))
			{
				s_message = getParameter(req, "message", true, false, false, "");
				s_message = s_message + "操作失败！";
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
					s_display = getParameter(req, "typePage", false, true, true, "/lesson/lessonApplyTypeList.jsp");
					bqc.QueryCode("0013", s_codeValue);
					s_catalog = bqc.getCodeValue("code_namec", 0);
					s_navigation = s_navigation + "&gt;" + s_catalog;
					bgd.executeSelect(s_sql);
				}
				else
				{
					s_display = getParameter(req, "listPage", false, true, true, "/lesson/lessonApplyList.jsp");
					bqc.QueryCode(s_codeId, s_codeValue);
					s_catalog = bqc.getCodeValue("code_namec", 0);
					s_navigation = s_navigation + "&gt;" + s_catalog;

//					if (session.getAttribute(s_applyType + ".selectedItems")!=null)
						v_selectedItems = (Vector)session.getAttribute(s_applyType + ".selectedItems");
//					if (session.getAttribute(s_applyType + ".oldSelectedItems")!=null)
						v_oldSelectedItems = (Vector)session.getAttribute(s_applyType + ".oldSelectedItems");
//					if (session.getAttribute(s_applyType + ".reason")!=null)
						v_reason = (Vector)session.getAttribute(s_applyType + ".reason");
					
						v_approve = (Vector)session.getAttribute(s_applyType + ".approve");

					if (v_selectedItems==null) //初始化数组，存贮用户已经选择的课程
					{
						v_selectedItems = new Vector(); //选择项
						v_oldSelectedItems = new Vector(); //选择项
						v_reason = new Vector(); //申请理由
						v_approve = new Vector(); //已经批准的收费课程
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

					//处理课程申请
					s_countInPage = getParameter(req, "countInPage", true, false, false, "0");
					i_countInPage = Integer.parseInt(s_countInPage);

					//取得当前选择的课程，并对原有的数组进行整理
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
					
					//去除已被取消的课程
					s_removeItems = getParameter(req, "removeItems", true, false, false, "");
					StringTokenizer tokens = new StringTokenizer(s_removeItems, "|");
					while(tokens.hasMoreTokens())
					{
						s_classId = tokens.nextToken().trim();
						if (v_selectedItems.contains(s_classId))
							v_selectedItems.remove(s_classId);
					}

					//将选择结果传递到JSP
					session.setAttribute(s_applyType + ".selectedItems", v_selectedItems);
					session.setAttribute(s_applyType + ".oldSelectedItems", v_oldSelectedItems);
					session.setAttribute(s_applyType + ".reason", v_reason);
					session.setAttribute(s_applyType + ".approve", v_approve);
					String strSubstr = com.htyz.system.SystemProperties.getProperty("db.substr");
					s_sql = "SELECT * FROM t_class WHERE " + strSubstr + "(class_id, 1, 8) = '" + s_codeId + s_codeValue + "'  AND class_status = '1' " + s_whereType
							+ " AND class_id not in (select class_id from t_user_class uc, t_user u where uc.user_id = u.group_id and u.user_id = '" + s_userId + "') " 
							+ " ORDER BY class_id";
					if (s_pageNo.equals("1")||reload||bgd.getRowCount()==0)
					{
						//System.out.println("刷新数据");
						bgd.executeSelect(s_sql);
						if (reload) reload = false;
					}
					if (s_pageNo.equals("0")) //更新结果
					{
						s_display = strOkPage;
						//整理原有的课程表
						for (int i=0; i<v_oldSelectedItems.size(); i++)
						{
							s_classId = (String)v_oldSelectedItems.elementAt(i);
							if (v_selectedItems.contains(s_classId)) //将原有的课程激活
							{
								s_classId = s_classId.substring(0, 14);
								s_sql = "UPDATE t_user_class SET status = '1' WHERE user_id = '" + s_userId + "' AND class_id = '" + s_classId + "'"; 
								bgd.execute(s_sql);
							}
							else //删除原有的课程
							{
								s_classId = s_classId.substring(0, 14);
								s_sql = "UPDATE t_user_class SET status = '9' WHERE user_id = '" + s_userId + "' AND class_id = '" + s_classId + "'"; 
								bgd.execute(s_sql);
							}
						}
						if (s_applyType.equals("free"))
						{
							for (int j=0; j<v_selectedItems.size(); j++)
							{
								s_classId = (String)v_selectedItems.elementAt(j);
								s_classId = s_classId.substring(0, 14);
								//删除旧记录
								s_sql = "DELETE FROM t_user_class WHERE user_id = '" + s_userId + "' AND class_id = '" + s_classId + "'";
								//System.out.println("Delete SQL: " + s_sql);
								bgd.execute(s_sql);
								
								//添加新记录
								s_sql = "INSERT INTO t_user_class "
										+ "(user_id, class_id, start_time, status) VALUES " 
										+ "('" + s_userId + "', "
										+ "'" + s_classId + "', "
										+ "'" + bgd.getDbDate() + "', "
										+ "'1')";
								//System.out.println("Insert SQL: " + s_sql);
								if(bgd.execute(s_sql) == -1)
									throw new Exception("申请免费课程出错！");
									
								//成功后将session中的数组清除
								session.removeAttribute(s_applyType + ".selectedItems");
								session.removeAttribute(s_applyType + ".oldSelectedItems");
								session.removeAttribute(s_applyType + ".reason");
								session.removeAttribute(s_applyType + ".approve");
								req.setAttribute("message", "申请免费课程成功！");
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
								//给申请表填记录
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
										
										//添加新记录
										s_sql = "INSERT INTO t_user_class "
												+ "(user_id, class_id, start_time, status) VALUES " 
												+ "('" + s_userId + "', "
												+ "'" + s_classId + "', "
												+ "'" + bgd.getDbDate() + "', "
												+ "'1')";
										//System.out.println("Insert SQL: " + s_sql);
										if(bgd.execute(s_sql) == -1)
											throw new Exception("申请选修课出错！");
									}
									else
									{
										s_sql = "INSERT INTO t_user_apply (apply_id,user_id, class_id, apply_time, apply_reason, apply_status) "
												+ "VALUES ('"+s_userId+s_classId+"','" + s_userId + "', "
												+ "'" + s_classId + "', "
												+ "'" + bgd.getDbDate() + "', "
												+ "'" + s_reason + "', "
												+ "'0') ";
							//System.out.println(s_sql);
										bgd.execute(s_sql);
									}
								}
							}
							session.removeAttribute(s_applyType + ".selectedItems");
							session.removeAttribute(s_applyType + ".oldSelectedItems");
							session.removeAttribute(s_applyType + ".reason");
							session.removeAttribute(s_applyType + ".approve");
							s_display = strOkPage;
							req.setAttribute("message", "申请选修课成功，请等待课程管理员审核。");
						}
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
//					System.out.println("刷新数据");
					bgd.executeSelect(s_sql);
					if (reload) reload = false;
				}
				s_catalog = "搜索";
				s_codeValue = s_codeValue + "&queryid=" + s_queryId + "&querycoloum=" + s_queryColoum;//增加查询参数
				req.setAttribute("bgd", bgd);
				req.setAttribute("navigation", s_navigation);
				req.setAttribute("catalog", s_catalog);
				req.setAttribute("codeid", s_codeId);
				req.setAttribute("codevalue", s_codeValue);
				req.setAttribute("pageno", s_pageNo);
				req.setAttribute("action", s_action);
				s_listPage = getParameter(req, "listPage", false, true, true, "/lesson/lessonApplyList.jsp");
				
				getServletConfig().getServletContext().getRequestDispatcher(s_listPage).forward(req, res);
				return;
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
				System.err.println("beanLessonApplyList error: ");
				strErrPage = getParameter(req, "errPage", false, true, true, "");
				ex.printStackTrace();
				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception e)
			{
				System.err.println("beanLessonApplyList调用错误页面时出错！");
				e.printStackTrace();
			}
		}
    }
}
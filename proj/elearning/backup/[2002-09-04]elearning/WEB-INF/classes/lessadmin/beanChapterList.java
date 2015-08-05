package lessadmin;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.htyz.*;

public class beanChapterList extends HttpServlet implements java.io.Serializable
{
	private beanGetdata bgd = new beanGetdata();
	private static final String CONFIG_BUNDLE_NAME = "system.system";
	private String workingDir = ""; 
	
	//初始化Servlet
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		ResourceBundle configFile = ResourceBundle.getBundle(CONFIG_BUNDLE_NAME);
		workingDir = (String) configFile.getString("lessondir");
		workingDir = workingDir.replace('\\', '/');
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
				   s_open,
				   s_action,
				   s_addType,
				   s_classId,
				   s_lessonId,
				   s_lessonName,
				   s_lessonUrl,
				   s_message,
				   s_navigation,
				   s_showPage,
				   s_temp,
				   s_lessonDir,
				   s_sql;
			int i_count;
    		beanElearnTools ets = new beanElearnTools(); 
    		beanQueryCodes bqc = new beanQueryCodes();
    		beanQueryCodes bqcTypes = new beanQueryCodes();
    		beanQueryCodes bqcStatus = new beanQueryCodes();
			
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
			
			//判断用户是不是管理员
			if (!ets.isAdmin(s_userId))
				throw new Exception("你没有执行此操作的权限！");
				
			bqcTypes.QueryCode("0004");
			bqcStatus.QueryCode("0005");
			req.setAttribute("bqcTypes", bqcTypes);
			req.setAttribute("bqcStatus", bqcStatus);
			
					
			//取得参数
			s_action = getParameter(req, "action", true, false, false, "list");
			s_open = getParameter(req, "open", true, false, false, "0");
			s_showPage = getParameter(req, "chTreePage", false, true, true, "");
			
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
			
			if (s_action.equalsIgnoreCase("query"))
			{
				String s_queryid = getParameter(req, "queryid", true, false, false, "");
				String s_querycoloum = getParameter(req, "querycoloum", true, false, false, "");
				res.sendRedirect("lessonList?action=query&queryid=" + s_queryid +"&querycoloum=" + s_querycoloum);
				return;
			}
			
			if ( s_action.equalsIgnoreCase("showDetail"))
			{
				s_classId = getParameter(req, "classid", true, false, true, "");
				s_lessonId = getParameter(req, "lessonid", true, false, false, "");
				
				if (s_lessonId.equals("")) //显示课程的详细内容
				{
					beanLessonDetail bld = new beanLessonDetail();
					s_showPage = getParameter(req, "lessDetailPage", false, true, true, "");
					s_sql = "SELECT * FROM t_class WHERE class_id = '" + s_classId + "'";
					bgd.executeSelect(s_sql);
					
					bld.setClassId(s_classId);
					bld.setClassName(bgd.getFieldValue("class_name", 0));
					bld.setClassStatus(bgd.getFieldValue("class_status", 0));
					bld.setClassTime(bgd.getFieldValue("class_time", 0));
					bld.setClassType(bgd.getFieldValue("class_type", 0));
					bld.setDescription(bgd.getFieldValue("description", 0));
					bld.setKeywords(bgd.getFieldValue("keywords", 0));
					bld.setPassCount(bgd.getFieldValue("pass_count", 0));
					bld.setPassStander(bgd.getFieldValue("pass_stander", 0));
					bld.setPeoples(bgd.getFieldValue("peoples", 0));
					bld.setSetTime(bgd.getFieldValue("set_time", 0));
					
					s_navigation = ets.getClassNavigation(s_classId) + "&gt;" + bgd.getFieldValue("class_name", 0);
					req.setAttribute("navigation", s_navigation);
					req.setAttribute("bld", bld);
					
				}
				else //显示章/节的内容
				{
					Vector fileList = new Vector();
					BeanFileManager bfm = new BeanFileManager();
					beanChapterDetail bcd = new beanChapterDetail();
					s_showPage = getParameter(req, "lessChPage", false, true, true, "");
					s_sql = "SELECT * FROM t_lesson WHERE lesson_id = '" + s_lessonId + "' AND class_id = '" + s_classId + "'"; 
					bgd.executeSelect(s_sql);
					
					bcd.setClassId(s_classId);
					bcd.setLessonId(s_lessonId);
					bcd.setLessonName(bgd.getFieldValue("lesson_name", 0));
					bcd.setLessonUrl(bgd.getFieldValue("lesson_url", 0));
					
					String s_option = getParameter(req, "option", true, false, false, "");
					
					if (s_option.equals("deletefiles"))//删除文件
					{
						Enumeration names = req.getParameterNames();
						while (names.hasMoreElements()) 
						{
							String name = (String) names.nextElement();
							if (name.startsWith("select")) 
							{
								String filename = getParameter(req, name, true, false, false, "");
								File file = new File(getLessonDir(s_classId, s_lessonId) + filename);
								file.delete();
							}
						}
					}
					
					if (s_option.equals("upload")) //上载文件
					{
						bfm.uploadFile(req.getInputStream(), req.getContentLength(), getLessonDir(s_classId, s_lessonId));
					}
					
					fileList = bfm.displayFiles(getLessonDir(s_classId, s_lessonId));
					if (bcd.getLessonUrl().equals("")&&!fileList.isEmpty())
					{
						File file = (File) fileList.elementAt(0);
						String filename = file.getName();
						s_sql = "UPDATE t_lesson SET lesson_url = '" + getPrefixUrl(s_classId, s_lessonId) + filename + "' WHERE class_id = '" + s_classId + "' AND lesson_id = '" + s_lessonId + "'";
						bgd.execute(s_sql);
						bcd.setLessonUrl(getPrefixUrl(s_classId, s_lessonId) + filename);
					}
					if (!bcd.getLessonUrl().equals("")&&fileList.isEmpty())
					{
						s_sql = "UPDATE t_lesson SET lesson_url = '' WHERE class_id = '" + s_classId + "' AND lesson_id = '" + s_lessonId + "'";
						bgd.execute(s_sql);
						bcd.setLessonUrl("");               
					}
					s_navigation = ets.getClassNavigation(s_classId, s_lessonId) + "&gt;" + bcd.getLessonName();
					req.setAttribute("fileList", fileList);
					req.setAttribute("prefixUrl", getPrefixUrl(s_classId, s_lessonId));
					req.setAttribute("action", "update");
					req.setAttribute("addtype", "section");
					req.setAttribute("classid", s_classId);
					req.setAttribute("lessonid", s_lessonId);
					req.setAttribute("navigation", s_navigation);
					req.setAttribute("bcd", bcd);
				}
				getServletConfig().getServletContext().getRequestDispatcher(s_showPage).forward(req, res);
				return;
			}
			if (s_action.equalsIgnoreCase("new"))
			{
				beanChapterDetail bcd = new beanChapterDetail();
				s_classId = getParameter(req, "classid", true, false, true, "");
				s_lessonId = getParameter(req, "lessonid", true, false, false, "");
				
				s_addType = getParameter(req, "addtype", true, false, true, "");
				if (s_addType.equals(""))
					throw new Exception ("请指定要添加的类型");
				
				if (s_addType.equals("section")&&s_lessonId.equals(""))
					throw new Exception ("添加节错误，没有获取章信息！");
				
					
				if (s_lessonId.equals(""))
					s_navigation = ets.getClassNavigation(s_classId) + "&gt;" + getClassName(s_classId);
				else
					s_navigation = ets.getClassNavigation(s_classId, s_lessonId) + "&gt;" + getChapterName(s_classId, s_lessonId);
						
				s_showPage = getParameter(req, "lessChPage", false, true, true, "");
				req.setAttribute("action", "add");
				req.setAttribute("classid", s_classId);
				req.setAttribute("lessonid", s_lessonId);
				req.setAttribute("addtype", s_addType);
				req.setAttribute("navigation", s_navigation);
				req.setAttribute("bcd", bcd);
				getServletConfig().getServletContext().getRequestDispatcher(s_showPage).forward(req, res);
				return;
			}
			if (s_action.equalsIgnoreCase("delete"))
			{
				s_temp = getParameter(req, "object", true, false, false, "chapter");
				
				if (s_temp.equals("class"))
				{
					
					s_classId = getParameter(req, "classid", true, false, true, "");
					deleteClass(s_classId);
					s_showPage = getParameter(req, "lessTreePage", false, true, true, "");
		
					req.setAttribute("action", "showOk");
					req.setAttribute("flag", "0");
					req.setAttribute("message", "删除课程成功");
					getServletConfig().getServletContext().getRequestDispatcher(s_showPage).forward(req, res);
					return;
				}
				else
				{
					
					//删除章节
					s_classId = getParameter(req, "classid", true, false, true, "");
					s_lessonId = getParameter(req, "lessonid", true, false, true, "");
					
					deleteFiles(s_classId, s_lessonId);
										
					s_action = "showOk";
					s_message = "删除";
						
					req.setAttribute("message", s_message);
					s_showPage = getParameter(req, "chTreePage", false, true, true, "");
				}
			}
			if (s_action.equalsIgnoreCase("add")||s_action.equalsIgnoreCase("update"))
			{
				s_temp = getParameter(req, "object", true, false, false, "chapter");
				
				if (s_temp.equals("class"))
				{
					String s_className = getParameter(req, "classname", true, false, false, "");
					String s_classType = getParameter(req, "classtype", true, false, false, "");
					String s_keywords = getParameter(req, "keywords", true, false, false, "");
					String s_description = getParameter(req, "description", true, false, false, "");
					String s_classTime = getParameter(req, "classtime", true, false, false, "0");
					String s_passStander = getParameter(req, "passstander", true, false, false, "0");
					String s_classStatus = getParameter(req, "classstatus", true, false, false, "0");
					
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

					s_message = "更新课程";
					s_classId = getParameter(req, "classid", true, false, true, "");
					bld.setClassId(s_classId);
					s_sql = "UPDATE t_class SET "
					        + "class_name = '" + s_className + "', "
					        + "class_type = '" + s_classType + "', "
					        + "keywords = '" + s_keywords + "', "
					        + "description = '" + s_description + "', "
					        + "class_time = " + s_classTime + ", "
					        + "pass_stander = " + s_passStander + ", "
					        + "class_status = '" + s_classStatus + "' "
					        + " WHERE class_id = '" + s_classId + "'";
					if (bgd.execute(s_sql) == -1)
						s_action = "showErr";
					else
						s_action = "showOk";
					req.setAttribute("message", s_message);
					s_showPage = "/lessadmin/chapterTree.jsp";
					s_showPage = getParameter(req, "chTreePage", false, true, true, "");
				}
				else
				{
					//更新章节
					beanChapterDetail bcd = new beanChapterDetail();
					String s_chapterNo;
					s_classId = getParameter(req, "classid", true, false, true, "");
					s_chapterNo = getParameter(req, "chapterno", true, false, true, "");
					s_lessonId = getParameter(req, "lessonid", true, false, false, "");
					s_lessonName = getParameter(req, "lessonname", true, false, false, "empty");
					s_lessonUrl = getParameter(req, "lessonurl", true, false, false, "");
	
					bcd.setClassId(s_classId);
					bcd.setLessonName(s_lessonName);
					bcd.setLessonUrl(s_lessonUrl);
					
					if (s_action.equalsIgnoreCase("add"))
					{
						s_addType = getParameter(req, "addtype", true, false, true, "");
						
						if (s_addType.equals("chapter"))
						{
							s_message = "第" + s_chapterNo + "章";
							s_lessonId = "000" + s_chapterNo;
							s_lessonId = s_lessonId.substring(s_lessonId.length() - 3, s_lessonId.length()) + "000";
							bcd.setLessonId(s_lessonId);
						}
						else
						{
							s_message = "第" + s_chapterNo + "节";
							s_chapterNo = "000" + s_chapterNo;
							s_chapterNo = s_chapterNo.substring(s_chapterNo.length() - 3, s_chapterNo.length());
							s_lessonId = s_lessonId.substring(0, 3) + s_chapterNo;
							bcd.setLessonId(s_lessonId);
						}
						s_sql = "SELECT * FROM t_lesson WHERE class_id = '" + s_classId + "'"
								+ " AND lesson_id = '" + s_lessonId + "'";
						bgd.executeSelect(s_sql);
						if (bgd.getRowCount()>0)
						{
							s_action = "showErr";
							s_message = s_message + "已经存在!添加";
						}
						else
						{
							s_message = "添加";
							s_sql = "INSERT t_lesson VALUES ('"
									+ s_classId + "', '"
									+ s_lessonId + "', '"
									+ s_lessonName + "', '"
									+ s_lessonUrl + "' )";
							if (bgd.execute(s_sql) == -1)
								s_action = "showErr";
							else
								s_action = "showOk";
						}
					}
					else
					{
						if (s_lessonId.endsWith("000"))
						{
							s_message = "第" + s_chapterNo + "章";
							s_chapterNo = "000" + s_chapterNo;
							s_chapterNo = s_chapterNo.substring(s_chapterNo.length() - 3, s_chapterNo.length()) + "000";
							bcd.setLessonId(s_chapterNo);
						}
						else
						{
							s_message = "第" + s_chapterNo + "节";
							s_chapterNo = "000" + s_chapterNo;
							s_chapterNo = s_chapterNo.substring(s_chapterNo.length() - 3, s_chapterNo.length());
							s_chapterNo = s_lessonId.substring(0, 3) + s_chapterNo;
							bcd.setLessonId(s_chapterNo);
						}
							s_sql = "UPDATE t_lesson SET "
						        + "lesson_id = '" + s_chapterNo + "', "
						        + "lesson_name = '" + s_lessonName + "', "
						        + "lesson_url = '" + s_lessonUrl + "' "
						        + " WHERE class_id = '" + s_classId + "'"
						        + " AND lesson_id = '" + s_lessonId + "'";
						s_message = "更新";
						if (bgd.execute(s_sql) == -1)
							s_action = "showErr";
						else
						{
							s_action = "showOk";
							if (!s_lessonId.equals(s_chapterNo))
								renameTree(s_classId, s_lessonId, s_chapterNo);
						}
					}
					req.setAttribute("message", s_message);
					s_showPage = "/lessadmin/chapterTree.jsp";
					s_showPage = getParameter(req, "chTreePage", false, true, true, "");
				}
			}
			
			if (s_action.equals("list")) s_action="showDetail";
			if (s_open.equals("0"))
			{
				s_classId =  getParameter(req, "classid", true, false, true, "");
				req.setAttribute("classid", s_classId);
				req.setAttribute("classname", getClassName(s_classId));
				session.setAttribute("lessadmin.classid", s_classId);
			}
			else
			{
				s_classId = (String)session.getAttribute("lessadmin.classid");
				req.setAttribute("classid", s_classId);
				req.setAttribute("classname", getClassName(s_classId));
			}
			req.setAttribute("action", s_action);
			req.setAttribute("flag", s_open);
			
			getServletConfig().getServletContext().getRequestDispatcher(s_showPage).forward(req, res);
			return;
		}
		catch(Exception ex)
		{
			try
			{
				strErrPage = getParameter(req, "errPage", false, true, true, "");
				ex.printStackTrace();
				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.err.println("beanChapterList调用错误页面时出错！");
			}
		}
    }
    
    private String getClassName(String classid) throws Exception
    {
    	beanGetdata bgdName = new beanGetdata();
    	String sql = "SELECT class_name FROM t_class WHERE class_id = '" + classid + "'";
    	bgdName.executeSelect(sql);
    	return bgdName.getFieldValue("class_name", 0);
	}
	
	private String getChapterName(String classid, String lessonid) throws Exception
	{
    	beanGetdata bgdName = new beanGetdata();
    	String sql = "SELECT lesson_name FROM t_lesson WHERE class_id = '" + classid + "' AND lesson_id = '" + lessonid + "'";
    	bgdName.executeSelect(sql);
    	return bgdName.getFieldValue("lesson_name", 0);
    }
    
    //返回课程的URL前缀
    private String getPrefixUrl(String classid, String lessonid)
    {
    	String s_temp;
    	s_temp = workingDir.substring(workingDir.indexOf("elearning") - 1, workingDir.length());
    	if (!s_temp.endsWith("/"))
    		s_temp = s_temp + "/";
    	s_temp = s_temp + classid.substring(0, 8) + "/" + classid.substring(8, classid.length()) + "/" + lessonid + "/";
    	return s_temp;
    }
    
    //返回工作目录
    private String getLessonDir(String classid, String lessonid)
    {
    	String s_temp = "";
    	s_temp = classid.substring(0, 8) + "/" + classid.substring(8, classid.length()) + "/" + lessonid + "/";
    	if (!workingDir.endsWith("/"))
    		workingDir = workingDir + "/";
    	return workingDir + s_temp;
    }
    
    //删除课程及目录
    
    private void deleteClass(String classid) throws Exception
    {
    	String s_dir = "";
   		try
   		{
	   		beanGetdata bgd = new beanGetdata();
	   		String s_sql = "SELECT lesson_id FROM t_lesson WHERE class_id = '" + classid + "'";
			bgd.executeSelect(s_sql);
			for (int i=0; i<bgd.getRowCount(); i++)
			{
				String temp = bgd.getFieldValue("lesson_id", i);
				s_dir = getLessonDir(classid, temp);
				deleteTree(s_dir);
			}
			s_dir = getLessonDir(classid, "");
			deleteTree(s_dir);
			bgd.execute("DELETE FROM t_lesson WHERE class_id = '" + classid + "'");
			bgd.execute("DELETE FROM t_class WHERE class_id = '" + classid + "'");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception("删除课程时出错");
		}
    	
    }
    //删除章节文件及目录
    private void deleteFiles(String classid, String lessonid) throws Exception
    {
    	String s_dir = "";
      	beanGetdata bgd = new beanGetdata();
	  	
    	if (lessonid.endsWith("000")) //删除章
    	{
    		try
    		{
    			String s_sql = "SELECT lesson_id FROM t_lesson WHERE class_id = '" + classid + "' AND  substring(lesson_id, 1, 3) = '" + lessonid.substring(0, 3) + "'";
	    		bgd.executeSelect(s_sql);
	    		for (int i=0; i<bgd.getRowCount(); i++)
	    		{
	    			String temp = bgd.getFieldValue("lesson_id", i);
	    			s_dir = getLessonDir(classid, temp);
	    			deleteTree(s_dir);
	    		}
	    		bgd.execute("DELETE FROM t_lesson WHERE class_id = '" + classid + "' AND substring(lesson_id, 1, 3) = '" + lessonid.substring(0, 3) + "'");
	    	}
	    	catch (Exception ex)
	    	{
	    		ex.printStackTrace();
	    		throw new Exception("删除章节时出错！");
	    	}
    	}
    	else
    	{
    		try
    		{
	    		s_dir = getLessonDir(classid, lessonid);
	    		deleteTree(s_dir);
	    		bgd.execute("DELETE FROM t_lesson WHERE class_id = '" + classid + "' AND lesson_id = '" + lessonid + "'");
    		}
    		catch (Exception ex)
    		{
    			ex.printStackTrace();
    			throw new Exception("删除节时出错");
    		}
    	}
    	
    }
    
    //删除目录
    private void deleteTree(String tree) throws Exception
    {
    	BeanFileManager bfm = new BeanFileManager();
    	Vector fileList = new Vector();
    	File file;
    	try
    	{
	    	fileList = bfm.displayFiles(tree);
	     	for (int i=0; i<fileList.size(); i++)
	    	{
	    		file = (File) fileList.elementAt(i);
	    		file.delete();
	    	}
	    	file = new File(tree);
	    	file.delete();
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    		throw new Exception("删除文件时出错");
    	}
    }
    
    //给目录更名
    private void renameTree(String classid, String oldTree, String newTree) throws Exception
    {
    	String s_old = getLessonDir(classid, oldTree);
    	String s_new = getLessonDir(classid, newTree);
    	File file = new File(s_old);
    	if (!file.renameTo(new File(s_new)))
    		throw new Exception("目录更名时失败");
    }
}
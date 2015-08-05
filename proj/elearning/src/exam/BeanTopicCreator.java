//////////////////////////////////
//
//   BeanTopicAnswer 问题答案维护
//
//             苏海军
//
//////////////////////////////////



package exam;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.htyz.*;

public class BeanTopicCreator extends HttpServlet implements java.io.Serializable
{
	private beanGetdata bgd = new beanGetdata();
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

		//转换为GBK
		if (paramValue!=null)
		{
			paramValue_GBK = ets.getGBKString(paramValue);
			if (paramValue_GBK == null)
			{
				throw new Exception(parameterName + " 不能转换为GBK，请检查！");
			}
			paramValue = paramValue_GBK;
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
    			   s_action,
    			   s_message,
    			   s_classId;
    		beanElearnTools ets = new beanElearnTools(); 
			
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
			
			//判断用户是不是课程管理员
			if (!ets.isRole(s_userId, "3"))
				throw new Exception("你没有执行此操作的权限！");
			
			s_action = getParameter(req, "action", true, false, false, "list");
			s_classId = getParameter(req, "classid", true, false, true, "");

			//列出课程测试题目
			if (s_action.equalsIgnoreCase("list")||s_action.equals("setup")) {
				String s_pageNo, s_sql, s_page;
				s_page = getParameter(req, "listPage", false, true, true, "");
				s_pageNo = getParameter(req, "pageno", true, false, false, "1");
				if (s_pageNo.equals("")) s_pageNo = "1";

				BeanExamProfile bep = new BeanExamProfile(s_classId);
				if ( s_action.equals("setup")) {
					bep.update(req);
				}
				
				
				s_sql = "SELECT * FROM t_test WHERE class_id = '" + s_classId + "'";

				if (s_pageNo.equals("1")||bgd.getRowCount()==0)
					bgd.executeSelect(s_sql);
				
				req.setAttribute("bep", bep);
				req.setAttribute("bgd", bgd);
				req.setAttribute("message", "");
				req.setAttribute("pageno", s_pageNo);
				req.setAttribute("className", getLessonName(s_classId));
				req.setAttribute("classId", s_classId);
				getServletConfig().getServletContext().getRequestDispatcher(s_page).forward(req, res);
				return;
				
			}


			//显示课程测试题目
			if (s_action.equalsIgnoreCase("showQuestion")) {
				req.setAttribute("message", "");
				showQuestion(req, res);
				return;
			}
			
			


			//添加课程测试题目
			if (s_action.equalsIgnoreCase("newQuestion")) {
	    		BeanTopicQuestion btq = new BeanTopicQuestion();
				beanGetdata bgdQ = new beanGetdata();
				String s_pageNo = getParameter(req, "pageno", true, false, false, "1");
				String s_page = getParameter(req, "listPage", false, true, true, "");
				String sql = "SELECT max(topic_id) FROM t_test";
				bgdQ.executeSelect(sql);
				String s_topicId = bgdQ.getFieldValue(1,0).equals("")?"00000000000000000000":bgdQ.getFieldValue(1, 0);
				s_topicId = ets.AutoNum(s_topicId);
				btq.setClassId(s_classId);
				btq.setTopicDescription(getParameter(req, "topicdescription", true, false, false, ""));
				btq.setTopicId(s_topicId);
				btq.setTopicMark(getParameter(req, "topicmark", true, false, false, ""));
				btq.setTopicStatus(getParameter(req, "topicstatus", true, false, false, ""));
				btq.setTopicType(getParameter(req, "topictype", true, false, false, ""));
				btq.setUserId(s_userId);
	    		req.setAttribute("message", "添加题目成功！");
	    		try
	    		{
		    		BeanTopic bt = new BeanTopic(btq);
					bt.insert();
				}
				catch (Exception e)
				{
					e.printStackTrace();
					req.setAttribute("message", "添加题目失败！");
				}
					
				sql = "SELECT * FROM t_test WHERE class_id = '" + s_classId + "'";
				bgd.executeSelect(sql);
				if (s_pageNo.equals("")||s_pageNo.equals("0")) s_pageNo = "1";
				BeanExamProfile bep = new BeanExamProfile(s_classId);
				req.setAttribute("bep", bep);
				req.setAttribute("bgd", bgd);
				req.setAttribute("pageno", s_pageNo);
				req.setAttribute("className", getLessonName(s_classId));
				req.setAttribute("classId", s_classId);
				getServletConfig().getServletContext().getRequestDispatcher(s_page).forward(req, res);
				return;
			}

			//删除课程测试题目
			if (s_action.equalsIgnoreCase("deleteQuestion")) {
	    		BeanTopicQuestion btq = new BeanTopicQuestion();
				String s_countInPage = getParameter(req, "countInPage", true, false, false, "0");
				String s_pageNo = getParameter(req, "pageno", true, false, false, "1");
				String s_page = getParameter(req, "listPage", false, true, true, "");
				if (s_countInPage.equals("")) s_countInPage = "0";

				int i_count = Integer.parseInt(s_countInPage);
				try
				{
					for (int i=0; i<i_count; i++)
					{
						btq.setTopicId(getParameter(req, "selectItem" + Integer.toString(i), true, false, false, ""));
						if (!btq.getTopicId().equals(""))
						{
							btq.setClassId(s_classId);
							BeanTopic bt = new BeanTopic(btq);
							bt.delete();
						}
					}
					
					if (i_count == 0)
					{
						btq.setTopicId(getParameter(req, "topicid", true, false, false, ""));
						btq.setClassId(s_classId);
						BeanTopic bt = new BeanTopic(btq);
						bt.delete();
					}
					req.setAttribute("message", "删除题目成功！");
				}
				catch (Exception e)
				{
					e.printStackTrace();
					req.setAttribute("message", "删除题目失败！");
				}
				String sql = "SELECT * FROM t_test WHERE class_id = '" + s_classId + "'";
				bgd.executeSelect(sql);
				if (s_pageNo.equals("")||s_pageNo.equals("0")) s_pageNo = "1";
				BeanExamProfile bep = new BeanExamProfile(s_classId);
				req.setAttribute("bep", bep);
				req.setAttribute("bgd", bgd);
				req.setAttribute("pageno", s_pageNo);
				req.setAttribute("className", getLessonName(s_classId));
				req.setAttribute("classId", s_classId);
				getServletConfig().getServletContext().getRequestDispatcher(s_page).forward(req, res);
				return;
				
			}

			//添加课程测试题目答案
			if (s_action.equalsIgnoreCase("newAnswer")) {
				BeanTopicAnswer bta = new BeanTopicAnswer();
				bta.setTopicId(getParameter(req, "topicid", true, false, true, ""));
				bta.setTopicAnswer(getParameter(req, "topicanswer", true, false, false, ""));
				bta.setAnswerDescription(getParameter(req, "answerdescription", true, false, false, ""));
				bta.setIsAnswer(getParameter(req, "isanswer", true, false, false, ""));
				try {
					BeanTopic bt = new BeanTopic(bta);
					bt.insertAnswer();
					req.setAttribute("message", "添加答案成功");
				}
				catch (Exception e)
				{
					e.printStackTrace();
					req.setAttribute("message", "添加答案失败！");
				}
				showQuestion(req, res);
				return;
			}
			
			//删除课程测试题目答案
			if (s_action.equalsIgnoreCase("deleteAnswer")) {
				try 
				{
					BeanTopicAnswer bta = new BeanTopicAnswer();
					bta.setTopicId(getParameter(req, "topicid", true, false, true, ""));
					Enumeration names = req.getParameterNames();
					while (names.hasMoreElements()) 
					{
						String name = (String) names.nextElement();
						if (name.startsWith("selectItem")) 
						{
							bta.setTopicAnswer(getParameter(req, name, true, false, true, ""));
							BeanTopic bt = new BeanTopic(bta);
							bt.deleteAnswer();
						}
					}
					req.setAttribute("message", "删除答案成功");
				}
				catch (Exception e)
				{
					e.printStackTrace();
					req.setAttribute("message", "删除答案失败！");
				}
				showQuestion(req, res);
				return;
			}
			
			//修改课程测试题目答案
			if (s_action.equalsIgnoreCase("modify")) {
				BeanTopicQuestion btq = new BeanTopicQuestion();
				btq.setClassId(getParameter(req, "classid", true, false, true, ""));
				btq.setTopicId(getParameter(req, "topicid", true, false, true, ""));
				btq.setTopicDescription(getParameter(req, "topicdescription", true, false, false, ""));
				btq.setTopicMark(getParameter(req, "topicmark", true, false, false, ""));
				btq.setTopicStatus(getParameter(req, "topicstatus", true, false, false, ""));
				btq.setTopicType(getParameter(req, "topictype", true, false, false, ""));
				btq.setUserId(s_userId);
				
				String s_size = getParameter(req, "answersize", true, false, false, "0");
				int i_size = Integer.parseInt(s_size);
				for (int i=0; i<i_size; i++)
				{
					BeanTopicAnswer bta = new BeanTopicAnswer();
					bta.setTopicId(getParameter(req, "topicid", true, false, true, ""));
					bta.setTopicAnswer(getParameter(req, "topicanswer" + Integer.toString(i), true, false, false, ""));
					bta.setAnswerDescription(getParameter(req, "answerdescription" + Integer.toString(i), true, false, false, ""));
					bta.setIsAnswer(getParameter(req, "isanswer" + Integer.toString(i), true, false, false, ""));
					btq.addAnswer(bta);
	    		}
	    		req.setAttribute("message", "修改题目成功！");
	    		try
	    		{
		    		BeanTopic bt = new BeanTopic(btq);
					bt.update();
				}
				catch (Exception e)
				{
					e.printStackTrace();
					req.setAttribute("message", "修改题目失败！");
				}
				showQuestion(req, res);
				return;
			}
						
		}
		catch(Exception ex)
		{
			try
			{
				System.err.println("BeanTopicCreator error: " + ex);
				strErrPage = getParameter(req, "errPage", false, true, true, "");
				ex.printStackTrace();
				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception e)
			{
				System.err.println("BeanTopicCreator调用错误页面时出错！");
				e.printStackTrace();
			}
		}
    }
    private String getLessonName(String classid) {
    	beanGetdata gt = new beanGetdata();
    	gt.executeSelect("SELECT class_name FROM  t_class WHERE class_id = '" + classid + "'");
    	return gt.getFieldValue("class_name", 0);
    }
    
    private void showQuestion(HttpServletRequest req, HttpServletResponse res) throws Exception
    {
		BeanTopicQuestion btq = new BeanTopicQuestion();
		String s_classId = getParameter(req, "classid", true, false, true, "");
		beanGetdata bgdQ = new beanGetdata();
		String s_topicId = getParameter(req, "topicid", true, false, true, "");
		String sql = "SELECT * FROM t_test WHERE class_id = '" + s_classId + "' AND topic_id = '" + s_topicId + "'";
		String s_page = getParameter(req, "questionPage", false, true, true, "");
		
		bgdQ.executeSelect(sql);
		btq.setClassId(s_classId);
		btq.setTopicDate(bgdQ.getFieldValue("topic_date", 0));
		btq.setTopicDescription(bgdQ.getFieldValue("topic_description", 0));
		btq.setTopicId(s_topicId);
		btq.setTopicMark(bgdQ.getFieldValue("topic_mark", 0));
		btq.setTopicStatus(bgdQ.getFieldValue("topic_status", 0));
		btq.setTopicType(bgdQ.getFieldValue("topic_type", 0));
		btq.setUserId(bgdQ.getFieldValue("user_id", 0));
		
		sql = "SELECT * FROM t_testlib WHERE topic_id = '" + s_topicId + "'";
		bgdQ.executeSelect(sql);
		
		for (int i=0; i<bgdQ.getRowCount(); i++) {
			BeanTopicAnswer bta = new BeanTopicAnswer();
			bta.setTopicId(s_topicId);
			bta.setTopicAnswer(bgdQ.getFieldValue("topic_answer", i));
			bta.setAnswerDescription(bgdQ.getFieldValue("answer_description", i));
			bta.setIsAnswer(bgdQ.getFieldValue("is_answer", i));
			btq.addAnswer(bta);
		}
		
		req.setAttribute("btq", btq);
		req.setAttribute("className", getLessonName(s_classId));
		req.setAttribute("classId", s_classId);
		getServletConfig().getServletContext().getRequestDispatcher(s_page).forward(req, res);
		return;
    }
}
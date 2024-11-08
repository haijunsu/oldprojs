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

public class BeanTopicExam extends HttpServlet implements java.io.Serializable
{
	private Vector questions = new Vector();
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
			
			s_action = getParameter(req, "action", true, false, false, "list");
			s_classId = getParameter(req, "classid", true, false, true, "");
			
			if (s_classId.equals("")&&session.getAttribute("currentExam")==null)
				throw new Exception("没有选择课程ID！");
			
			if (s_classId.equals(""))
				s_classId = (String) session.getAttribute("currentExam");
				
			/**
		     * 判断有没有正在考试的课程
		     */

			if (session.getAttribute("currentExam") != null)
			{
				if (!s_classId.equals((String)session.getAttribute("currentExam")))
					throw new Exception ("你目前正在测验" + this.getLessonName((String) session.getAttribute("currentExam")) + "课程，如果已经关闭考试页面，请从课程表点击“测试”继续考试！");
			}
			//列出课程测试题目
			if (s_action.equalsIgnoreCase("list")) {
				beanGetdata bgd = new beanGetdata();
				String start = (String) session.getAttribute(s_classId + ".startTime");;
				//进入考试
				if (start == null)
				{
					//检查用户的考试次数
					String sql = "SELECT * FROM t_result WHERE user_id = '" + s_userId + "' AND class_id = '" + s_classId + "' AND test_mark = -1";
					bgd.executeSelect(sql);
					if (bgd.getRowCount()>0)
					{
						if (bgd.getRowCount()>2)
						{
							String errmsg = "你已经考试过3次了，不能再参加考试了！";
							req.setAttribute("errmsg", errmsg);
							getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
							return;
						}
						
						
						//检查用户是否已经通过考试
						sql = "SELECT pass_stander, max(test_mark) AS test_mark FROM t_result t, t_class c WHERE t.user_id = '" + s_userId + "' AND t.class_id = '" + s_classId + "' AND t.class_id = c.class_id GROUP BY pass_stander";
						bgd.executeSelect(sql);
						int passStander = bgd.getFieldValue("pass_stander", 0).equals("")?0:Integer.parseInt(bgd.getFieldValue("pass_stander", 0));
						int testMark = bgd.getFieldValue("test_mark", 0).equals("")?0:Integer.parseInt(bgd.getFieldValue("test_mark", 0));
						if (passStander <= testMark)
						{
							String errmsg = "你已经通过了这门课程的考试了，不能再参加考试了！";
							req.setAttribute("errmsg", errmsg);
							getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
							return;
						}
					}
					questions.clear();
					BeanExamProfile bep = new BeanExamProfile(s_classId);
					String[] mark = new String[bep.getTypesCount()];
					int[] topicnum = new int[bep.getTypesCount()];
					for (int i=0; i<bep.getTypesCount() - 1; i++) {
						mark[i] = bep.getMark(i);
						topicnum[i] = Integer.parseInt(bep.getExamNum(i));
					}
					
					//选择考试题目
					for (int i=0; i<mark.length; i++)
					{
						Vector qv = new Vector();
						sql = "SELECT * FROM t_test WHERE class_id = '" + s_classId + "' AND topic_mark = " + mark[i] + " AND topic_status = '1'";
						bgd.executeSelect(sql);
						if (bgd.getRowCount() == 0) continue;
						for (int j=0; j<bgd.getRowCount(); j++)
						{
							BeanTopicQuestion btq = new BeanTopicQuestion();
							btq.setClassId(s_classId);
							btq.setTopicId(bgd.getFieldValue("topic_id", j));
							btq.setTopicDescription(bgd.getFieldValue("topic_description", j));
							btq.setTopicMark(mark[i]);
							btq.setTopicType(bgd.getFieldValue("topic_type", j));
							qv.addElement(btq);
						}
						createExamQuestions(qv, topicnum[i]);
					}
					if (questions.isEmpty())
						throw new Exception("试卷生成错误：没有正确设置试题库，请与管理员联系！");
						

					//插入用户考试记录
					sql = "INSERT INTO t_result (user_id, class_id, test_date, test_mark) VALUES ("
						+ "'" + s_userId + "', "
						+ "'" + s_classId + "', "
						+ "'" + bgd.getDbDate() + "', "
						+ "-1) ";
					bgd.execute(sql);					
					Date date = new Date();
					long startTime = date.getTime();
					start = Long.toString(startTime);
					session.setAttribute(s_classId + ".totalTime", bep.getExamNum(bep.getTypesCount() - 1));
					session.setAttribute(s_classId + ".startTime", start);
					session.setAttribute(s_classId + ".passTime", "0");
					session.setAttribute("currentExam", s_classId);
				}
				else
				{
					long startTime = Long.parseLong(start);
					Date date = new Date();
					long passTime = date.getTime();
					passTime = passTime - startTime;
					session.setAttribute(s_classId + ".passTime", Long.toString(passTime));
				}	
				String s_page = getParameter(req, "examPage", false, true, true, "");
				req.setAttribute("questions", questions);
				req.setAttribute("classId", session.getAttribute("currentExam"));
				req.setAttribute("className", getLessonName((String)session.getAttribute("currentExam")));
				getServletConfig().getServletContext().getRequestDispatcher(s_page).forward(req, res);
				return;
			}


			//显示课程测试题目
			if (s_action.equalsIgnoreCase("submit")) {
				//数组大小根据题目中的最高分来定
				beanGetdata bgd = new beanGetdata();
				String sql = "SELECT max(topic_mark) AS maxArray FROM t_test WHERE class_id = '" + s_classId + "'";
				bgd.executeSelect(sql);
				int maxArray = Integer.parseInt(bgd.getFieldValue("maxArray", 0));
				int[] rightNum = new int[maxArray];
				int[] typeNum = new int[maxArray];
				int grade = 0;
				int count = 0;
				String percent = "";
				if (questions.isEmpty())
					throw new Exception("严重错误，试题集已经为空！");
					
				for (int i=0; i<questions.size(); i++)
				{
					BeanTopicQuestion btq = (BeanTopicQuestion)questions.elementAt(i);
					if (btq.getTopicType().equals("0")) //单选题
					{
						int typeNo = Integer.parseInt(btq.getTopicMark()) - 1;
						typeNum[typeNo] = typeNum[typeNo] + 1;
						count = typeNo + count + 1;
						for (int j=0; j<btq.getAnswerCount(); j++)
						{
							if (btq.getAnswer(j).getIsAnswer().equals("1"))
							{
								String userAnswer = getParameter(req, "select" + btq.getTopicId(), true, false, false, "");
								if (userAnswer.equals(btq.getAnswer(j).getIsAnswer()))
								{
									grade = grade + Integer.parseInt(btq.getTopicMark());
									rightNum[typeNo] = rightNum[typeNo] + 1;
									break;
								}
							}
						}
					}
					else
					{
						int typeNo = Integer.parseInt(btq.getTopicMark()) - 1;
						count = typeNo + count + 1;
						typeNum[typeNo] = typeNum[typeNo] + 1;
						boolean right = false;
						for (int j=0; j<btq.getAnswerCount(); j++)
						{
							String userAnswer = getParameter(req, "select" + btq.getTopicId() + btq.getAnswer(j).getTopicAnswer(), true, false, false, "");
							if (userAnswer.equals(btq.getAnswer(j).getIsAnswer().trim()))
							{
								right = true;
							}
							else
							{
								right = false;
								break;
							}
						}
						if (right)
						{
							grade = grade + Integer.parseInt(btq.getTopicMark());
							rightNum[typeNo] = rightNum[typeNo] + 1;
						}
					}
				}
				//将成绩放入result表
				sql = "INSERT INTO t_result (user_id, class_id, test_date, test_mark) VALUES ("
					+ "'" + s_userId + "', "
					+ "'" + s_classId + "', "
					+ "'" + bgd.getDbDate() + "', " 
					+ Integer.toString(grade) + ")";
				bgd.execute(sql);
				
				//判断是否及格
				sql = "SELECT pass_stander FROM t_class WHERE class_id = '" + s_classId + "'";
				bgd.executeSelect(sql);
				int passStander = Integer.parseInt(bgd.getFieldValue("pass_stander", 0));
				if (passStander <= grade)
					req.setAttribute("pass", "true");
				else
					req.setAttribute("pass", "false");
				
				double passPer = grade*100.00/count;	
				req.setAttribute("grade", Integer.toString(grade));
				req.setAttribute("stander", Integer.toString(passStander));
				req.setAttribute("passPercent", new java.text.DecimalFormat("0").format(passPer));
				passPer = passStander*100.00/count;
				req.setAttribute("standerPercent", new java.text.DecimalFormat("0").format(passPer));
				
				//计算通过率
				for (int i=0; i<typeNum.length; i++)
				{
					if (typeNum[i] == 0) continue;
					double per = rightNum[i]*100.00/typeNum[i];
					percent = percent + Integer.toString(i + 1) + "分题通过百分比：" + new java.text.DecimalFormat("0").format(per) + "%<BR>";
				}
				
				req.setAttribute("percent", percent);
				req.setAttribute("className", getLessonName((String)session.getAttribute("currentExam")));
				String s_page = getParameter(req, "resultPage", false, true, true, "");
				session.removeAttribute(s_classId + ".totalTime");
				session.removeAttribute(s_classId + ".startTime");
				session.removeAttribute(s_classId + ".passTime");
				session.removeAttribute("currentExam");
				questions.clear();
				getServletConfig().getServletContext().getRequestDispatcher(s_page).forward(req, res);
				return;
			}
		}
		catch(Exception ex)
		{
			try
			{
				//System.err.println("BeanTopicExam error: " + ex);
				//ex.printStackTrace();
				strErrPage = getParameter(req, "errPage", false, true, true, "");
				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception e)
			{
				System.err.println("BeanTopicExam调用错误页面时出错！");
				e.printStackTrace();
			}
		}
    }
    private String getLessonName(String classid) throws Exception {
    	beanGetdata gt = new beanGetdata();
    	gt.executeSelect("SELECT class_name FROM  t_class WHERE class_id = '" + classid + "'");
    	return gt.getFieldValue("class_name", 0);
    }
    
    //产生试题集
    private void createExamQuestions(Vector qv, int num) throws Exception
    {
    	num = Math.min(num, qv.size());
		for (int i=0; i<num; i++)
		{
			int j = (int)(Math.random()*qv.size());
			BeanTopicQuestion btq = new BeanTopicQuestion();
			beanGetdata bgd = new beanGetdata();
			btq = (BeanTopicQuestion) qv.elementAt(j);
			String sql = "SELECT * FROM t_testlib WHERE topic_id = '" + btq.getTopicId() + "'";
			bgd.executeSelect(sql);
			for (int k=0; k<bgd.getRowCount(); k++)
			{
				BeanTopicAnswer bta = new BeanTopicAnswer();
				bta.setTopicId(bgd.getFieldValue("topic_id", k));
				bta.setTopicAnswer(bgd.getFieldValue("topic_answer", k));
				bta.setAnswerDescription(bgd.getFieldValue("answer_description", k));
				bta.setIsAnswer(bgd.getFieldValue("is_answer", k));
				btq.addAnswer(bta);
			}
			questions.addElement(btq);
			qv.remove(j);
		}
	    qv.clear();
    }
    
}
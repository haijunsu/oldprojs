package system;

import com.htyz.beanElearnTools;
import com.htyz.beanGetdata;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class beanUserDetail extends HttpServlet implements Serializable
{
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
    public void doProcess(HttpServletRequest req, HttpServletResponse res)
        throws ServletException
    {
        String strLoginPage, strPage, strErrPage, strOkPage;
        try
        {
            String s_userId,
            	   s_queryId,
            	   s_action,
            	   s_sql;
            beanElearnTools ets = new beanElearnTools();
            beanGetdata bgd = new beanGetdata();
            
			boolean create = false; //决定是否创建session，只有在Login中才为true
			HttpSession session = req.getSession(create);
			s_userId = (String)session.getAttribute("userid");
			
			//判断session是否存在
			if(session == null||s_userId == null)
			{
				req.setAttribute("message", "您没有登录！");
				getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
				return;
			}
            strPage = getParameter(req, "page", false, true, true, "");
            strLoginPage = getParameter(req, "loginPage", false, true, true, "");
            strOkPage = getParameter(req, "okPage", false, true, true, "");
            s_queryId = getParameter(req, "userid", true, false, true, "");
            s_action = getParameter(req, "action", true, false, false, "show");
            if(s_queryId.equals(""))
                throw new Exception("USER_ID不能为空!");
            if(s_action.equals("show"))
            {
                s_sql = "SELECT * FROM t_user WHERE user_id = '" + s_queryId + "'";
                bgd.executeSelect(s_sql);
                req.setAttribute("bgd", bgd);
                getServletConfig().getServletContext().getRequestDispatcher(strPage).forward(req, res);
                return;
            }
            if(ets.isAdmin(s_userId))
            {
                 if(s_action.equalsIgnoreCase("update"))
                {
                    String s_groupId = getParameter(req, "group_id", true, false, true, "");
                    String s_userStatus = getParameter(req, "user_status", true, false, true, "");
                    String s_rightCount = getParameter(req, "rightcount", true, false, true, "");
                    int i = Integer.parseInt(s_rightCount);
                    String right[] = new String[i];
                    for(int k = 0; k < i; k++)
                        right[k] = getParameter(req, "userright" + Integer.toString(k), true, false, false, "0");

                    String s_userRight = ets.parseRight(right);
                    s_sql = "UPDATE t_user SET group_id = '" + s_groupId + "', " + " user_status = '" + s_userStatus + "', " + " user_right = '" + s_userRight + "' " + " WHERE user_id = '" + s_queryId + "'";
                }
                else if(s_action.equalsIgnoreCase("resetpass"))
                    s_sql = "UPDATE t_user SET user_pass = '" + s_queryId + "'" + " WHERE user_id = '" + s_queryId + "'";
                else
                    throw new Exception("没有这个功能: " + s_action);
                int j = bgd.execute(s_sql);
                if(j == -1)
                {
                    throw new Exception("操作失败！");
                }
                else
                {
                    req.setAttribute("message", "操作成功");
                    getServletConfig().getServletContext().getRequestDispatcher(strOkPage).forward(req, res);
                    return;
                }
            }
            else
            {
                throw new Exception("只有管理员才可能执行此操作！");
            }
        }
        catch(Exception ex)
        {
            try
            {
    	        strErrPage = getParameter(req, "errPage", false, true, true, "");
                System.err.println("beanUserDetail error: " + ex);
 				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception e)
			{
				System.err.println("beanUserDetail调用错误页面时出错！");
				e.printStackTrace();
			}
        }
    }
}

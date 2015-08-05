package system;

import com.htyz.beanElearnTools;
import com.htyz.beanGetdata;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class beanUserDetail extends HttpServlet implements Serializable
{
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
            
			boolean create = false; //�����Ƿ񴴽�session��ֻ����Login�в�Ϊtrue
			HttpSession session = req.getSession(create);
			s_userId = (String)session.getAttribute("userid");
			
			//�ж�session�Ƿ����
			if(session == null||s_userId == null)
			{
				req.setAttribute("message", "��û�е�¼��");
				getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(req, res);
				return;
			}
            strPage = getParameter(req, "page", false, true, true, "");
            strLoginPage = getParameter(req, "loginPage", false, true, true, "");
            strOkPage = getParameter(req, "okPage", false, true, true, "");
            s_queryId = getParameter(req, "userid", true, false, true, "");
            s_action = getParameter(req, "action", true, false, false, "show");
            if(s_queryId.equals(""))
                throw new Exception("USER_ID����Ϊ��!");
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
                    throw new Exception("û���������: " + s_action);
                int j = bgd.execute(s_sql);
                if(j == -1)
                {
                    throw new Exception("����ʧ�ܣ�");
                }
                else
                {
                    req.setAttribute("message", "�����ɹ�");
                    getServletConfig().getServletContext().getRequestDispatcher(strOkPage).forward(req, res);
                    return;
                }
            }
            else
            {
                throw new Exception("ֻ�й���Ա�ſ���ִ�д˲�����");
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
				System.err.println("beanUserDetail���ô���ҳ��ʱ����");
				e.printStackTrace();
			}
        }
    }
}

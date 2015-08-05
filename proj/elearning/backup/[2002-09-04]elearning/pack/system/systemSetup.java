package system;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.text.*;
import com.htyz.beanElearnTools;

public class systemSetup extends HttpServlet implements java.io.Serializable
{
	private static final String CONFIG_BUNDLE_NAME = "system.system";
	
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
		String strPage, strOkPage, strErrPage, strLoginPage;
		try
		{
			String action, password, rePassword, customDriver, lineSeparator, key;
			action = getParameter(req, "action", true, false, false, "show");
			strPage = getParameter(req, "page", false, true, true, "");
			strOkPage = getParameter(req, "okPage", false, true, true, "");
			
			Vector driverVector = new Vector();
			ResourceBundle configFile = ResourceBundle.getBundle(CONFIG_BUNDLE_NAME);
			systemProfile spf = new systemProfile();
	        Enumeration driverKeys = configFile.getKeys();
	        Enumeration ctx = getServletContext().getAttributeNames();

	        do
	        {
	        	key = (String)driverKeys.nextElement();
	        	if ((key.length() > 6) && (key.substring(0, 6).equals("driver")))
	        	{
	        		driverVector.addElement((String)configFile.getString(key));
	        	}
	        }while(driverKeys.hasMoreElements());
	        
	        if(action.equals("update"))
	        {
				customDriver = getParameter(req, "customdriver", true, false, false, "");
				if (!customDriver.equals(""))
				{
					spf.setDriver(customDriver);
				}
				else
				{
					spf.setDriver(getParameter(req, "driver", true, false, true, ""));
				}
				password = getParameter(req, "password", true, false, true, "password");
				rePassword = getParameter(req, "repassword", true, false, true, "repassword");
				if (!password.equals(rePassword))
				{
					throw new Exception("��������Ŀ��ͬ��");
				}
				spf.setPassword(password);
				spf.setDatabase(getParameter(req, "database", true, false, true, ""));
				spf.setDatasource(getParameter(req, "datasource", true, false, true, ""));
				spf.setDatasourceurl(getParameter(req, "datasourceurl", true, false, true, ""));
				spf.setOwner(getParameter(req, "owner", true, false, true, ""));
				spf.setUserid(getParameter(req, "userid", true, false, true, ""));
				spf.setLessonDir(getParameter(req, "lessondir", true, false, true, ""));
				
		        while(ctx.hasMoreElements())
		        {
		        	key = (String)ctx.nextElement();
		        	if (key.endsWith("classpath"))
		        	{
		        		key = (String)getServletContext().getAttribute(key);
		        		break;
		        	}
		        }
		        key = key.substring(0, key.indexOf(System.getProperties().getProperty("path.separator")));
		        StringTokenizer tokens = new StringTokenizer(key, "\\");
		        key = "";
				while(tokens.hasMoreTokens())
				{
					key = key + tokens.nextToken().trim() + "/";
				}
				FileWriter fw = new FileWriter(key + "system/system.properties", false);
				PrintWriter pw = new PrintWriter(fw);
				
				lineSeparator = System.getProperties().getProperty("line.separator");
				pw.write(spf.toString());
				pw.write(lineSeparator);
				
				for (int i=0; i<driverVector.size(); i++)
				{
					pw.write("driver" + Integer.toString(i) + " = " + driverVector.elementAt(i) + lineSeparator);
				}
				pw.close();
				fw.close();

				req.setAttribute("message", "�ɹ�����ϵͳ���ã�");
				getServletConfig().getServletContext().getRequestDispatcher(strOkPage).forward(req, res);
			}
			else
			{
				spf.setDatabase((String)configFile.getString("database"));
				spf.setDatasource((String)configFile.getString("datasource"));
				spf.setDatasourceurl((String)configFile.getString("datasourceurl"));
				spf.setDriver((String)configFile.getString("driver"));
				spf.setOwner((String)configFile.getString("owner"));
				spf.setPassword((String)configFile.getString("password"));
				spf.setUserid((String)configFile.getString("userid"));
				spf.setLessonDir((String)configFile.getString("lessondir"));
				req.setAttribute("driverVector", driverVector);
				req.setAttribute("profile", spf);
				getServletConfig().getServletContext().getRequestDispatcher(strPage).forward(req, res);
			}	
			return;
		}
		catch(Exception ex)
		{
			try
			{
				strErrPage = getParameter(req, "errPage", false, true, true, "");
				System.err.println("systemSetup error: " );
				ex.printStackTrace();
				req.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(strErrPage).forward(req, res);
				return;
			}
			catch(Exception e)
			{
				System.err.println("systemSetup���ô���ҳ��ʱ����");
				e.printStackTrace();
			}
		}
    }
}
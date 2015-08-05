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
					throw new Exception("两次输入的口令不同！");
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

				req.setAttribute("message", "成功更新系统设置！");
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
				System.err.println("systemSetup调用错误页面时出错！");
				e.printStackTrace();
			}
		}
    }
}
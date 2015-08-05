package lessadmin;

/**/
/*                                                                           */
/*                        IBM Confidential                                   */
/*                                                                           */
/*               Copyright (c) IBM Corporation 1998                          */
/*                       All Rights Reserved                                 */
/*                                                                           */
/**/

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.htyz.BeanFileManager;


public class BeanUpFile extends HttpServlet implements java.io.Serializable
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
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		ResourceBundle configFile = ResourceBundle.getBundle(CONFIG_BUNDLE_NAME);
		String lessonDir = (String) configFile.getString("lessondir");
		try
		{
		BeanFileManager dataParser = new BeanFileManager();
		dataParser.uploadFile(request.getInputStream(), request.getContentLength(), lessonDir);
		Vector fileList = new Vector();
		fileList = dataParser.displayFiles(lessonDir);
		request.setAttribute("fileList", fileList);
		request.setAttribute("message", "上传成功");
		getServletConfig().getServletContext().getRequestDispatcher("/upload.jsp").forward(request, response);
		}
		catch(Exception ex)
		{
			try
			{
				System.err.println("beanChapterList error: ");
				ex.printStackTrace();
				request.setAttribute("errmsg", ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
				return;
			}
			catch(Exception e)
			{
				System.err.println("beanChapterList调用错误页面时出错！");
			}
		}
	}
}

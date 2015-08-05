/*
 * Created on 2003-11-6
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.xmlrpc;

import groller.application.context.Global;
import groller.application.www.helper.RequestResolver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class XmlRpcServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
		throws ServletException, IOException 
	{
		doGet(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1)
		throws ServletException, IOException 
	{
		initServerPath(arg0);
		XmlRpcFacade facade = (XmlRpcFacade) Global.getBeanFactory().getBean("xml-rpc");
		facade.execute(arg0, arg1);
	}

	private void initServerPath(HttpServletRequest request) {
		if(Global.getSharedObject("serverPath") == null) {
			String serverPath = RequestResolver.getServerPath(request);
			Global.shareObject("serverPath", serverPath);
		}
	}
}

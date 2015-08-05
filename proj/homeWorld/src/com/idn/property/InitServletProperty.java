/*
 * @(#)InitServletProperty.java  2003-7-15
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.property;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * <P> 收集Web应用程序的上下文信息和安装的绝对路径，在应用程序加载时第一个加载 </P>
 * 
 * @version 0.2
 * @author navy
 */
public class InitServletProperty extends HttpServlet {

	/**
	 * 应用程序上下文名称
	 */
	public static String contextName = null;
	
	/**
	 * 应用程序上下文
	 */
	public static ServletContext context = null;

	/**
	 * 应用程序安装的绝对路径
	 */
	public static String realPath = null;

	/* (non-Javadoc)
	 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		context = config.getServletContext();
		realPath = context.getRealPath("/");
		contextName = context.getServletContextName();
		com.idn.log.LogWrapper log =
			new com.idn.log.LogWrapper(InitServletProperty.class);
		log.debug("InstallPath: " + realPath);	
		log.debug("ServletContextName in web.xml: " + contextName);	
/*		
		try {
			commsearch.init.InitMAX.getInstance().init();
		} catch (Exception e){
			log.debug("可控制的问题");
			log.debug(e);
		}
		//别人
		int i=commsearch.init.InitMAX.getInstance().getNextKey();
		log.debug("小初的数1:"+i);
		i=commsearch.init.InitMAX.getInstance().getNextKey();
		log.debug("小初的数2:"+i);
*/		
	}

	/**
	 * @return 上下文
	 */
	public static String getContextName() {
		return contextName;
	}

	/**
	 * @return 应用程序绝对路径
	 */
	public static String getRealPath() {
		return realPath;
	}

	/**
	 * @param string
	 */
	public static void setContextName(String string) {
		contextName = string;
	}

	/**
	 * @param string
	 */
	public static void setRealPath(String string) {
		realPath = string;
	}

	/**
	 * @return 应用程序上下文
	 */
	public static ServletContext getContext() {
		return context;
	}

	/**
	 * @param context
	 */
	public static void setContext(ServletContext context) {
		InitServletProperty.context = context;
	}

}

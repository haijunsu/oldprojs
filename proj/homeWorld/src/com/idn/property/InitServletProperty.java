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
 * <P> �ռ�WebӦ�ó������������Ϣ�Ͱ�װ�ľ���·������Ӧ�ó������ʱ��һ������ </P>
 * 
 * @version 0.2
 * @author navy
 */
public class InitServletProperty extends HttpServlet {

	/**
	 * Ӧ�ó�������������
	 */
	public static String contextName = null;
	
	/**
	 * Ӧ�ó���������
	 */
	public static ServletContext context = null;

	/**
	 * Ӧ�ó���װ�ľ���·��
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
			log.debug("�ɿ��Ƶ�����");
			log.debug(e);
		}
		//����
		int i=commsearch.init.InitMAX.getInstance().getNextKey();
		log.debug("С������1:"+i);
		i=commsearch.init.InitMAX.getInstance().getNextKey();
		log.debug("С������2:"+i);
*/		
	}

	/**
	 * @return ������
	 */
	public static String getContextName() {
		return contextName;
	}

	/**
	 * @return Ӧ�ó������·��
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
	 * @return Ӧ�ó���������
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

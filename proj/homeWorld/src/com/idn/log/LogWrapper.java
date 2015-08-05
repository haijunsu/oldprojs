/**
 * @(#)LogWrapper.java 2003-1-10
 *
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.log;

import org.apache.log4j.Category;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import com.idn.property.PropertyManager;
import com.idn.property.SystemProperties;

/**
 * <P>包装log4j信息，使应用程序可以利用log4j的功能来记录
 * 程序运行情况。配置文件可以为XML和properties类型文件
 * 应用程序运行时，仅初始化一次。<BR>
 * 详细内容请参考：<A HREF=http://jakarta.apache.org/log4j/docs/api/index.html>log4j文档</A>
 * </P>
 * @version 0.1
 * @author 苏海军
 *
 */

public class LogWrapper {

	/**
	 * 决定是否重新初始化配置标志，true需要配置，false不需要配置
	 */
	private static boolean m_isInitLog = true;

	/**
	 * 定义NavyLogger实例变量
	 */
	private Category m_cat = null;

	/**
	 * 为记录组件(类)构造LogWrapper实例
	 */
	public LogWrapper(Class classname) {
		super();

		//初始化log4j系统
//		if (isInitLog) {
//			initLog();
//		}

		//在Log4j系统中注册记录组件
		this.m_cat = NavyLogger.getInstance(classname);
	}

	/**
	 * 为记录组件(类名称)构造LogWrapper实例
	 */
	public LogWrapper(String classname) {
		super();

		//初始化log4j系统
//		if (isInitLog) {
//			initLog();
//		}

		//在Log4j系统中注册记录组件
		this.m_cat = NavyLogger.getInstance(classname);
	}

	/**
	 *  初始化log4j功能,详细内容请参考：<A HREF=http://jakarta.apache.org/log4j/docs/api/index.html>log4j文档</A>
	 */
	private static synchronized void initLog() {
		if (m_isInitLog) {
			String strInitFile = PropertyManager.InitlogFilename;
			if (strInitFile.toLowerCase().endsWith("xml")) {
				DOMConfigurator.configure(strInitFile);
			} else {
				if ((strInitFile.indexOf(SystemProperties.FILE_SEPARATOR)
					== -1)
					&& strInitFile.endsWith("properties")) {
					PropertyConfigurator.configure(
						PropertyManager.loadProperties(strInitFile));
				} else {
					PropertyConfigurator.configure(strInitFile);
				}
			}
			m_isInitLog = false;
		}
	}

	/**
	 * 记录调试（informational）级别信息
	 * @param obj 需要记录的信息
	 */
	public void info(byte obj) {
		if (isInfoEnabled())
			this.m_cat.info(Byte.toString(obj));
	}
	/**
	 * 记录调试（informational）级别信息
	 * @param obj 需要记录的信息
	 */
	public void info(short obj) {
		if (isInfoEnabled())
			this.m_cat.info(Short.toString(obj));
	}
	/**
	 * 记录调试（informational）级别信息
	 * @param obj 需要记录的信息
	 */
	public void info(char obj) {
		if (isInfoEnabled())
			this.m_cat.info(String.valueOf(obj));
	}
	/**
	 * 记录调试（informational）级别信息
	 * @param obj 需要记录的信息
	 */
	public void info(int obj) {
		if (isInfoEnabled())
			this.m_cat.info(Integer.toString(obj));
	}
	/**
	 * 记录调试（informational）级别信息
	 * @param obj 需要记录的信息
	 */
	public void info(long obj) {
		if (isInfoEnabled())
			this.m_cat.info(Long.toString(obj));
	}
	/**
	 * 记录调试（informational）级别信息
	 * @param obj 需要记录的信息
	 */
	public void info(float obj) {
		if (isInfoEnabled())
			this.m_cat.info(Float.toString(obj));
	}
	/**
	 * 记录调试（informational）级别信息
	 * @param obj 需要记录的信息
	 */
	public void info(double obj) {
		if (isInfoEnabled())
			this.m_cat.info(Double.toString(obj));
	}
	/**
	 * 记录调试（informational）级别信息
	 * @param obj 需要记录的信息
	 */
	public void info(boolean obj) {
		if (isInfoEnabled())
			this.m_cat.info(String.valueOf(obj));
	}
	/**
	 * 记录信息（informational）级别信息
	 * @param obj 需要记录的信息
	 */
	public void info(Object obj) {
		if (isInfoEnabled())
			this.m_cat.info(obj);
	}

	/**
	 * 记录信息（informational）级别信息，包括Java抛出异常的堆栈信息
	 * @param obj 需要记录的信息
	 * @param t Java抛出的异常
	 */
	public void info(Object obj, Throwable t) {
		if (isInfoEnabled())
			this.m_cat.info(obj, t);
	}

	/**
	 * 记录调试（debug）级别信息
	 * @param obj 需要记录的信息
	 */
	public void debug(byte obj) {
		if (isDebugEnabled())
			this.m_cat.debug(Byte.toString(obj));
	}
	/**
	 * 记录调试（debug）级别信息
	 * @param obj 需要记录的信息
	 */
	public void debug(short obj) {
		if (isDebugEnabled())
			this.m_cat.debug(Short.toString(obj));
	}
	/**
	 * 记录调试（debug）级别信息
	 * @param obj 需要记录的信息
	 */
	public void debug(char obj) {
		if (isDebugEnabled())
			this.m_cat.debug(String.valueOf(obj));
	}
	/**
	 * 记录调试（debug）级别信息
	 * @param obj 需要记录的信息
	 */
	public void debug(int obj) {
		if (isDebugEnabled())
			this.m_cat.debug(Integer.toString(obj));
	}
	/**
	 * 记录调试（debug）级别信息
	 * @param obj 需要记录的信息
	 */
	public void debug(long obj) {
		if (isDebugEnabled())
			this.m_cat.debug(Long.toString(obj));
	}
	/**
	 * 记录调试（debug）级别信息
	 * @param obj 需要记录的信息
	 */
	public void debug(float obj) {
		if (isDebugEnabled())
			this.m_cat.debug(Float.toString(obj));
	}
	/**
	 * 记录调试（debug）级别信息
	 * @param obj 需要记录的信息
	 */
	public void debug(double obj) {
		if (isDebugEnabled())
			this.m_cat.debug(Double.toString(obj));
	}
	/**
	 * 记录调试（debug）级别信息
	 * @param obj 需要记录的信息
	 */
	public void debug(boolean obj) {
		if (isDebugEnabled())
			this.m_cat.debug(String.valueOf(obj));
	}

	/**
	 * 记录调试（debug）级别信息
	 * @param obj 需要记录的信息
	 */
	public void debug(Object obj) {
		if (isDebugEnabled())
			this.m_cat.debug(obj);
	}

	/**
	 * 记录调试（debug）级别信息，包括Java抛出异常的堆栈信息
	 * @param obj 需要记录的信息
	 * @param t Java抛出的异常
	 */
	public void debug(Object obj, Throwable t) {
		if (isDebugEnabled())
			this.m_cat.debug(obj, t);
	}

	/**
	 * 记录调试（warning）级别信息
	 * @param obj 需要记录的信息
	 */
	public void warn(byte obj) {
		this.m_cat.warn(Byte.toString(obj));
	}
	/**
	 * 记录调试（warning）级别信息
	 * @param obj 需要记录的信息
	 */
	public void warn(short obj) {
		this.m_cat.warn(Short.toString(obj));
	}
	/**
	 * 记录调试（warning）级别信息
	 * @param obj 需要记录的信息
	 */
	public void warn(char obj) {
		this.m_cat.warn(String.valueOf(obj));
	}
	/**
	 * 记录调试（warning）级别信息
	 * @param obj 需要记录的信息
	 */
	public void warn(int obj) {
		this.m_cat.warn(Integer.toString(obj));
	}
	/**
	 * 记录调试（warning）级别信息
	 * @param obj 需要记录的信息
	 */
	public void warn(long obj) {
		this.m_cat.warn(Long.toString(obj));
	}
	/**
	 * 记录调试（warning）级别信息
	 * @param obj 需要记录的信息
	 */
	public void warn(float obj) {
		this.m_cat.warn(Float.toString(obj));
	}
	/**
	 * 记录调试（warning）级别信息
	 * @param obj 需要记录的信息
	 */
	public void warn(double obj) {
		this.m_cat.warn(Double.toString(obj));
	}
	/**
	 * 记录调试（warning）级别信息
	 * @param obj 需要记录的信息
	 */
	public void warn(boolean obj) {
		this.m_cat.warn(String.valueOf(obj));
	}
	/**
	 * 记录警告（warning）级别信息
	 * @param obj 需要记录的信息
	 */
	public void warn(Object obj) {
		this.m_cat.warn(obj);
	}

	/**
	 * 记录警告（warning）级别信息，包括Java抛出异常的堆栈信息
	 * @param obj 需要记录的信息
	 * @param t Java抛出的异常
	 */
	public void warn(Object obj, Throwable t) {
		this.m_cat.warn(obj, t);
	}

	/**
	 * 记录调试（error）级别信息
	 * @param obj 需要记录的信息
	 */
	public void error(byte obj) {
		this.m_cat.error(Byte.toString(obj));
	}
	/**
	 * 记录调试（error）级别信息
	 * @param obj 需要记录的信息
	 */
	public void error(short obj) {
		this.m_cat.error(Short.toString(obj));
	}
	/**
	 * 记录调试（error）级别信息
	 * @param obj 需要记录的信息
	 */
	public void error(char obj) {
		this.m_cat.error(String.valueOf(obj));
	}
	/**
	 * 记录调试（error）级别信息
	 * @param obj 需要记录的信息
	 */
	public void error(int obj) {
		this.m_cat.error(Integer.toString(obj));
	}
	/**
	 * 记录调试（error）级别信息
	 * @param obj 需要记录的信息
	 */
	public void error(long obj) {
		this.m_cat.error(Long.toString(obj));
	}
	/**
	 * 记录调试（error）级别信息
	 * @param obj 需要记录的信息
	 */
	public void error(float obj) {
		this.m_cat.error(Float.toString(obj));
	}
	/**
	 * 记录调试（error）级别信息
	 * @param obj 需要记录的信息
	 */
	public void error(double obj) {
		this.m_cat.error(Double.toString(obj));
	}
	/**
	 * 记录调试（error）级别信息
	 * @param obj 需要记录的信息
	 */
	public void error(boolean obj) {
		this.m_cat.error(String.valueOf(obj));
	}
	/**
	 * 记录错误（error）级别信息
	 * @param obj 需要记录的信息
	 */
	public void error(Object obj) {
		this.m_cat.error(obj);
	}

	/**
	 * 记录错误（error）级别信息，包括Java抛出异常的堆栈信息
	 * @param obj 需要记录的信息
	 * @param t Java抛出的异常
	 */
	public void error(Object obj, Throwable t) {
		this.m_cat.error(obj, t);
	}

	/**
	 * 记录调试（fatal）级别信息
	 * @param obj 需要记录的信息
	 */
	public void fatal(byte obj) {
		this.m_cat.fatal(Byte.toString(obj));
	}
	/**
	 * 记录调试（fatal）级别信息
	 * @param obj 需要记录的信息
	 */
	public void fatal(short obj) {
		this.m_cat.fatal(Short.toString(obj));
	}
	/**
	 * 记录调试（fatal）级别信息
	 * @param obj 需要记录的信息
	 */
	public void fatal(char obj) {
		this.m_cat.fatal(String.valueOf(obj));
	}
	/**
	 * 记录调试（fatal）级别信息
	 * @param obj 需要记录的信息
	 */
	public void fatal(int obj) {
		this.m_cat.fatal(Integer.toString(obj));
	}
	/**
	 * 记录调试（fatal）级别信息
	 * @param obj 需要记录的信息
	 */
	public void fatal(long obj) {
		this.m_cat.fatal(Long.toString(obj));
	}
	/**
	 * 记录调试（fatal）级别信息
	 * @param obj 需要记录的信息
	 */
	public void fatal(float obj) {
		this.m_cat.fatal(Float.toString(obj));
	}
	/**
	 * 记录调试（fatal）级别信息
	 * @param obj 需要记录的信息
	 */
	public void fatal(double obj) {
		this.m_cat.fatal(Double.toString(obj));
	}
	/**
	 * 记录调试（fatal）级别信息
	 * @param obj 需要记录的信息
	 */
	public void fatal(boolean obj) {
		this.m_cat.fatal(String.valueOf(obj));
	}
	/**
	 * 记录严重错误（fatal）级别信息
	 * @param obj 需要记录的信息
	 */
	public void fatal(Object obj) {
		this.m_cat.fatal(obj);
	}

	/**
	 * 记录严重错误（fatal）级别信息，包括Java抛出异常的堆栈信息
	 * @param obj 需要记录的信息
	 * @param t Java抛出的异常
	 */
	public void fatal(Object obj, Throwable t) {
		this.m_cat.fatal(obj, t);
	}

	/**
	 * 该记录组件是否具有debug级别
	 * @return true 表示具有debug级别
	 */
	public boolean isDebugEnabled() {
		return this.m_cat.isDebugEnabled();
	}

	/**
	 * 该记录组件是否具有Info级别
	 * @return true 表示具有Info级别
	 */
	public boolean isInfoEnabled() {
		return this.m_cat.isInfoEnabled();
	}

	/**
	 * 该记录组件的记录级别
	 * @return 该组件的记录级别
	 */
	public String getStringLevel() {
		return this.m_cat.getLevel().toString();
	}

	/**
	 * 该记录组件的记录级别
	 * @return 该组件的记录级别整数表示
	 */
	public int getIntLevel() {
		return this.m_cat.getLevel().toInt();
	}

}

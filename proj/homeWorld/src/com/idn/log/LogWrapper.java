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
 * <P>��װlog4j��Ϣ��ʹӦ�ó����������log4j�Ĺ�������¼
 * ������������������ļ�����ΪXML��properties�����ļ�
 * Ӧ�ó�������ʱ������ʼ��һ�Ρ�<BR>
 * ��ϸ������ο���<A HREF=http://jakarta.apache.org/log4j/docs/api/index.html>log4j�ĵ�</A>
 * </P>
 * @version 0.1
 * @author �պ���
 *
 */

public class LogWrapper {

	/**
	 * �����Ƿ����³�ʼ�����ñ�־��true��Ҫ���ã�false����Ҫ����
	 */
	private static boolean m_isInitLog = true;

	/**
	 * ����NavyLoggerʵ������
	 */
	private Category m_cat = null;

	/**
	 * Ϊ��¼���(��)����LogWrapperʵ��
	 */
	public LogWrapper(Class classname) {
		super();

		//��ʼ��log4jϵͳ
//		if (isInitLog) {
//			initLog();
//		}

		//��Log4jϵͳ��ע���¼���
		this.m_cat = NavyLogger.getInstance(classname);
	}

	/**
	 * Ϊ��¼���(������)����LogWrapperʵ��
	 */
	public LogWrapper(String classname) {
		super();

		//��ʼ��log4jϵͳ
//		if (isInitLog) {
//			initLog();
//		}

		//��Log4jϵͳ��ע���¼���
		this.m_cat = NavyLogger.getInstance(classname);
	}

	/**
	 *  ��ʼ��log4j����,��ϸ������ο���<A HREF=http://jakarta.apache.org/log4j/docs/api/index.html>log4j�ĵ�</A>
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
	 * ��¼���ԣ�informational��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void info(byte obj) {
		if (isInfoEnabled())
			this.m_cat.info(Byte.toString(obj));
	}
	/**
	 * ��¼���ԣ�informational��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void info(short obj) {
		if (isInfoEnabled())
			this.m_cat.info(Short.toString(obj));
	}
	/**
	 * ��¼���ԣ�informational��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void info(char obj) {
		if (isInfoEnabled())
			this.m_cat.info(String.valueOf(obj));
	}
	/**
	 * ��¼���ԣ�informational��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void info(int obj) {
		if (isInfoEnabled())
			this.m_cat.info(Integer.toString(obj));
	}
	/**
	 * ��¼���ԣ�informational��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void info(long obj) {
		if (isInfoEnabled())
			this.m_cat.info(Long.toString(obj));
	}
	/**
	 * ��¼���ԣ�informational��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void info(float obj) {
		if (isInfoEnabled())
			this.m_cat.info(Float.toString(obj));
	}
	/**
	 * ��¼���ԣ�informational��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void info(double obj) {
		if (isInfoEnabled())
			this.m_cat.info(Double.toString(obj));
	}
	/**
	 * ��¼���ԣ�informational��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void info(boolean obj) {
		if (isInfoEnabled())
			this.m_cat.info(String.valueOf(obj));
	}
	/**
	 * ��¼��Ϣ��informational��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void info(Object obj) {
		if (isInfoEnabled())
			this.m_cat.info(obj);
	}

	/**
	 * ��¼��Ϣ��informational��������Ϣ������Java�׳��쳣�Ķ�ջ��Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 * @param t Java�׳����쳣
	 */
	public void info(Object obj, Throwable t) {
		if (isInfoEnabled())
			this.m_cat.info(obj, t);
	}

	/**
	 * ��¼���ԣ�debug��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void debug(byte obj) {
		if (isDebugEnabled())
			this.m_cat.debug(Byte.toString(obj));
	}
	/**
	 * ��¼���ԣ�debug��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void debug(short obj) {
		if (isDebugEnabled())
			this.m_cat.debug(Short.toString(obj));
	}
	/**
	 * ��¼���ԣ�debug��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void debug(char obj) {
		if (isDebugEnabled())
			this.m_cat.debug(String.valueOf(obj));
	}
	/**
	 * ��¼���ԣ�debug��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void debug(int obj) {
		if (isDebugEnabled())
			this.m_cat.debug(Integer.toString(obj));
	}
	/**
	 * ��¼���ԣ�debug��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void debug(long obj) {
		if (isDebugEnabled())
			this.m_cat.debug(Long.toString(obj));
	}
	/**
	 * ��¼���ԣ�debug��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void debug(float obj) {
		if (isDebugEnabled())
			this.m_cat.debug(Float.toString(obj));
	}
	/**
	 * ��¼���ԣ�debug��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void debug(double obj) {
		if (isDebugEnabled())
			this.m_cat.debug(Double.toString(obj));
	}
	/**
	 * ��¼���ԣ�debug��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void debug(boolean obj) {
		if (isDebugEnabled())
			this.m_cat.debug(String.valueOf(obj));
	}

	/**
	 * ��¼���ԣ�debug��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void debug(Object obj) {
		if (isDebugEnabled())
			this.m_cat.debug(obj);
	}

	/**
	 * ��¼���ԣ�debug��������Ϣ������Java�׳��쳣�Ķ�ջ��Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 * @param t Java�׳����쳣
	 */
	public void debug(Object obj, Throwable t) {
		if (isDebugEnabled())
			this.m_cat.debug(obj, t);
	}

	/**
	 * ��¼���ԣ�warning��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void warn(byte obj) {
		this.m_cat.warn(Byte.toString(obj));
	}
	/**
	 * ��¼���ԣ�warning��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void warn(short obj) {
		this.m_cat.warn(Short.toString(obj));
	}
	/**
	 * ��¼���ԣ�warning��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void warn(char obj) {
		this.m_cat.warn(String.valueOf(obj));
	}
	/**
	 * ��¼���ԣ�warning��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void warn(int obj) {
		this.m_cat.warn(Integer.toString(obj));
	}
	/**
	 * ��¼���ԣ�warning��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void warn(long obj) {
		this.m_cat.warn(Long.toString(obj));
	}
	/**
	 * ��¼���ԣ�warning��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void warn(float obj) {
		this.m_cat.warn(Float.toString(obj));
	}
	/**
	 * ��¼���ԣ�warning��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void warn(double obj) {
		this.m_cat.warn(Double.toString(obj));
	}
	/**
	 * ��¼���ԣ�warning��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void warn(boolean obj) {
		this.m_cat.warn(String.valueOf(obj));
	}
	/**
	 * ��¼���棨warning��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void warn(Object obj) {
		this.m_cat.warn(obj);
	}

	/**
	 * ��¼���棨warning��������Ϣ������Java�׳��쳣�Ķ�ջ��Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 * @param t Java�׳����쳣
	 */
	public void warn(Object obj, Throwable t) {
		this.m_cat.warn(obj, t);
	}

	/**
	 * ��¼���ԣ�error��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void error(byte obj) {
		this.m_cat.error(Byte.toString(obj));
	}
	/**
	 * ��¼���ԣ�error��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void error(short obj) {
		this.m_cat.error(Short.toString(obj));
	}
	/**
	 * ��¼���ԣ�error��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void error(char obj) {
		this.m_cat.error(String.valueOf(obj));
	}
	/**
	 * ��¼���ԣ�error��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void error(int obj) {
		this.m_cat.error(Integer.toString(obj));
	}
	/**
	 * ��¼���ԣ�error��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void error(long obj) {
		this.m_cat.error(Long.toString(obj));
	}
	/**
	 * ��¼���ԣ�error��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void error(float obj) {
		this.m_cat.error(Float.toString(obj));
	}
	/**
	 * ��¼���ԣ�error��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void error(double obj) {
		this.m_cat.error(Double.toString(obj));
	}
	/**
	 * ��¼���ԣ�error��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void error(boolean obj) {
		this.m_cat.error(String.valueOf(obj));
	}
	/**
	 * ��¼����error��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void error(Object obj) {
		this.m_cat.error(obj);
	}

	/**
	 * ��¼����error��������Ϣ������Java�׳��쳣�Ķ�ջ��Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 * @param t Java�׳����쳣
	 */
	public void error(Object obj, Throwable t) {
		this.m_cat.error(obj, t);
	}

	/**
	 * ��¼���ԣ�fatal��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void fatal(byte obj) {
		this.m_cat.fatal(Byte.toString(obj));
	}
	/**
	 * ��¼���ԣ�fatal��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void fatal(short obj) {
		this.m_cat.fatal(Short.toString(obj));
	}
	/**
	 * ��¼���ԣ�fatal��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void fatal(char obj) {
		this.m_cat.fatal(String.valueOf(obj));
	}
	/**
	 * ��¼���ԣ�fatal��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void fatal(int obj) {
		this.m_cat.fatal(Integer.toString(obj));
	}
	/**
	 * ��¼���ԣ�fatal��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void fatal(long obj) {
		this.m_cat.fatal(Long.toString(obj));
	}
	/**
	 * ��¼���ԣ�fatal��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void fatal(float obj) {
		this.m_cat.fatal(Float.toString(obj));
	}
	/**
	 * ��¼���ԣ�fatal��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void fatal(double obj) {
		this.m_cat.fatal(Double.toString(obj));
	}
	/**
	 * ��¼���ԣ�fatal��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void fatal(boolean obj) {
		this.m_cat.fatal(String.valueOf(obj));
	}
	/**
	 * ��¼���ش���fatal��������Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 */
	public void fatal(Object obj) {
		this.m_cat.fatal(obj);
	}

	/**
	 * ��¼���ش���fatal��������Ϣ������Java�׳��쳣�Ķ�ջ��Ϣ
	 * @param obj ��Ҫ��¼����Ϣ
	 * @param t Java�׳����쳣
	 */
	public void fatal(Object obj, Throwable t) {
		this.m_cat.fatal(obj, t);
	}

	/**
	 * �ü�¼����Ƿ����debug����
	 * @return true ��ʾ����debug����
	 */
	public boolean isDebugEnabled() {
		return this.m_cat.isDebugEnabled();
	}

	/**
	 * �ü�¼����Ƿ����Info����
	 * @return true ��ʾ����Info����
	 */
	public boolean isInfoEnabled() {
		return this.m_cat.isInfoEnabled();
	}

	/**
	 * �ü�¼����ļ�¼����
	 * @return ������ļ�¼����
	 */
	public String getStringLevel() {
		return this.m_cat.getLevel().toString();
	}

	/**
	 * �ü�¼����ļ�¼����
	 * @return ������ļ�¼����������ʾ
	 */
	public int getIntLevel() {
		return this.m_cat.getLevel().toInt();
	}

}

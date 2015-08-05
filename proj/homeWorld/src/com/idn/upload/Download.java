/*
 * @(#)Download.java  2004-3-31
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package com.idn.upload;

import java.io.IOException;
import java.util.Locale;

/**
 * <P>�ṩ���صĺ��������������Ķ���������</P>
 * 
 * @version 0.1
 * @author navy
 */
public class Download {
	/**
	 * ���� commons-logging API ��������־��¼
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(Download.class);
	
	/**
	 * ֱ�ӽ�org.jdom.Document����������������XML�������ı��뷽ʽ
	 * @param response HttpServletResponse����
	 * @param document org.jdom.Document����
	 * @param fileName xml�ļ���
	 * @throws IOException
	 */
	public static void downloadXML(javax.servlet.http.HttpServletResponse response, org.jdom.Document document, String fileName) throws IOException {
		org.jdom.output.XMLOutputter out = new org.jdom.output.XMLOutputter();
		
		//org.jdom.output.Format format = org.jdom.output.Format.getPrettyFormat();
		if (fileName == null)
			throw new IOException("û��ָ���ļ�����");
		if (!fileName.toUpperCase().endsWith(".XML"))
			fileName += ".XML";
		//format.setEncoding("GB2312");
		//out.setFormat(format);
		out.setEncoding("GB2312");
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setLocale(Locale.CHINA);
		out.output(document, response.getWriter());
	}
}

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
 * <P>提供下载的函数程序，针对特殊的对象来创建</P>
 * 
 * @version 0.1
 * @author navy
 */
public class Download {
	/**
	 * 启用 commons-logging API 来进行日志记录
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(Download.class);
	
	/**
	 * 直接将org.jdom.Document对象输出到浏览器，XML采用中文编码方式
	 * @param response HttpServletResponse对象
	 * @param document org.jdom.Document对象
	 * @param fileName xml文件名
	 * @throws IOException
	 */
	public static void downloadXML(javax.servlet.http.HttpServletResponse response, org.jdom.Document document, String fileName) throws IOException {
		org.jdom.output.XMLOutputter out = new org.jdom.output.XMLOutputter();
		
		//org.jdom.output.Format format = org.jdom.output.Format.getPrettyFormat();
		if (fileName == null)
			throw new IOException("没有指定文件名称");
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

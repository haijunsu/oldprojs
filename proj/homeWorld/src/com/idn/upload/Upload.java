/*
 * @(#)Upload.java  2003-8-8
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

/**
 * <P>文件上传/下载程序，可以上传到目录、数据库，也可以从目录和数据库下载。最大可以上传的文件为50MB</P>
 * 
 * @version 0.1
 * @author navy
 */
public class Upload {

	/**
	 * 上载的字节内容
	 */
	protected byte[] m_binArray;

	/**
	 * Request请求
	 */
	protected HttpServletRequest m_request;

	/**
	 * Response响应
	 */
	protected HttpServletResponse m_response;

	/**
	 * Application对象
	 */
	protected ServletContext m_application;

	/**
	 * 上载文件的总的大小
	 */
	private int m_totalBytes;

	/**
	 * 当前的索引
	 */
	private int m_currentIndex;

	/**
	 * 开始
	 */
	private int m_startData;

	/**
	 * 结束
	 */
	private int m_endData;

	/**
	 * 数据分界线
	 */
	private String m_boundary;

	/**
	 * 最大的文件总和值
	 */
	private long m_totalMaxFileSize;

	/**
	 * 单文件的最大值
	 */
	private long m_maxFileSize;

	/**
	 * 不允许上载的文件
	 */
	private Vector m_deniedFilesList;

	/**
	 * 允许上载的文件
	 */
	private Vector m_allowedFilesList;

	/**
	 * 不能访问的物理路径
	 */
	private boolean m_denyPhysicalPath;

	/**
	 * 强制物理路径
	 */
	private boolean m_forcePhysicalPath;

	/**
	 * 文件属性信息
	 */
	private String m_contentDisposition;

	/**
	 * 自动判断，如果相对路径不存在，则查找物理路径
	 */
	public static final int SAVE_AUTO = 0;

	/**
	 * 按相对目录存文件
	 */
	public static final int SAVE_VIRTUAL = 1;

	/**
	 * 按物理目录存储文件
	 */
	public static final int SAVE_PHYSICAL = 2;

	/**
	 * UploadFiles对象
	 */
	private UploadFiles m_files;

	/**
	 * Request对象
	 */
	private Request m_formRequest;

	/**
	 * 每次从结果集中读取blob类型时为100k字节
	 */
	private int MAX_READ_SIZE = 102400;

	/**
	 * 实例化
	 */
	public Upload() {
		m_totalBytes = 0;
		m_currentIndex = 0;
		m_startData = 0;
		m_endData = 0;
		m_boundary = "";
		m_totalMaxFileSize = 0L;
		m_maxFileSize = 0L;
		m_deniedFilesList = new Vector();
		m_allowedFilesList = new Vector();
		m_denyPhysicalPath = false;
		m_forcePhysicalPath = false;
		m_contentDisposition = "";
		m_files = new UploadFiles();
		m_formRequest = new Request();
	}

	/**
	 * 初始化应用程序
	 * @param servletconfig
	 * @throws ServletException
	 */
	public final void init(ServletConfig servletconfig)
		throws ServletException {
		m_application = servletconfig.getServletContext();
	}

	/**
	 * 服务函数，初始化response、request对象
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void service(
		HttpServletRequest httpservletrequest,
		HttpServletResponse httpservletresponse)
		throws ServletException, IOException {
		m_request = httpservletrequest;
		m_response = httpservletresponse;
	}

	/**
	 * 初始化应用程序、request对象、response对象
	 * @param servletconfig
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @throws ServletException
	 */
	public final void initialize(
		ServletConfig servletconfig,
		HttpServletRequest httpservletrequest,
		HttpServletResponse httpservletresponse)
		throws ServletException {
		m_application = servletconfig.getServletContext();
		m_request = httpservletrequest;
		m_response = httpservletresponse;
	}

	/**
	 * 根据页面上下文，初始化应用程序、request对象、response对象
	 * @param pagecontext
	 * @throws ServletException
	 */
	public final void initialize(PageContext pagecontext)
		throws ServletException {
		m_application = pagecontext.getServletContext();
		m_request = (HttpServletRequest) pagecontext.getRequest();
		m_response = (HttpServletResponse) pagecontext.getResponse();
	}

	/**
	 * 初始化应用程序、request对象、response对象
	 * @param servletcontext
	 * @param httpsession
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @param jspwriter
	 * @throws ServletException
	 */
	public final void initialize(
		ServletContext servletcontext,
		HttpSession httpsession,
		HttpServletRequest httpservletrequest,
		HttpServletResponse httpservletresponse,
		JspWriter jspwriter)
		throws ServletException {
		m_application = servletcontext;
		m_request = httpservletrequest;
		m_response = httpservletresponse;
	}

	/**
	 * 上载文件，将所有的文件通过UploadsFiles缓存起来，并等待存储到硬盘或数据库 
	 * @throws ServletException
	 * @throws IOException
	 * @throws UploadException
	 */
	public void upload()
		throws ServletException, IOException, UploadException {

		long l = 0L;
		boolean isReturn = false;

		String strFileName = "";
		String strFileExt = "";
		String strDataFieldValue = "";
		String strContentType = "";
		String strContentDisp = "";
		String strTypeMIME = "";
		String strSubTypeMIME = "";

		m_totalBytes = m_request.getContentLength();
		m_binArray = new byte[m_totalBytes];
		int j;
		for (int i = 0; i < m_totalBytes; i += j)
			try {
				//				m_request.getInputStream();
				j =
					m_request.getInputStream().read(
						m_binArray,
						i,
						m_totalBytes - i);
			} catch (Exception exception) {
				throw new UploadException("不能够上载");
			}

		//分析第一行
		for (; !isReturn && m_currentIndex < m_totalBytes; m_currentIndex++)
			if (m_binArray[m_currentIndex] == 13)
				isReturn = true;
			else
				m_boundary = m_boundary + (char) m_binArray[m_currentIndex];

		if (m_currentIndex == 1)
			return;
		for (m_currentIndex++;
			m_currentIndex < m_totalBytes;
			m_currentIndex = m_currentIndex + 2) {
			String strDataHeader = getDataHeader();
			m_currentIndex = m_currentIndex + 2;
			boolean isContainFileName = strDataHeader.indexOf("filename") > 0;
			String strFieldName = getDataFieldValue(strDataHeader, "name");
			if (isContainFileName) {
				strDataFieldValue =
					getDataFieldValue(strDataHeader, "filename");
				strFileName = getFileName(strDataFieldValue);
				strFileExt = getFileExt(strFileName);
				strContentType = getContentType(strDataHeader);
				strContentDisp = getContentDisp(strDataHeader);
				strTypeMIME = getTypeMIME(strContentType);
				strSubTypeMIME = getSubTypeMIME(strContentType);
			}
			getDataSection();
			if (isContainFileName && strFileName.length() > 0) {
				if (m_deniedFilesList.contains(strFileExt))
					throw new SecurityException("指定的文件名禁止上载");
				if (!m_allowedFilesList.isEmpty()
					&& !m_allowedFilesList.contains(strFileExt))
					throw new SecurityException("指定的文件名不允许上载");
				if (m_maxFileSize > 0L
					&& (long) ((m_endData - m_startData) + 1) > m_maxFileSize)
					throw new SecurityException(
						"上载的文件太大了 : "
							+ strFileName);
				l += (m_endData - m_startData) + 1;
				if (m_totalMaxFileSize > 0L && l > m_totalMaxFileSize)
					throw new SecurityException("文件的大小合计超出了可以上载的范围");
			}
			if (isContainFileName) {
				com.idn.upload.UploadFile file =
					new com.idn.upload.UploadFile();
				file.setParent(this);
				file.setFieldName(strFieldName);
				file.setFileName(strFileName);
				file.setFileExt(strFileExt);
				file.setFilePathName(strDataFieldValue);
				file.setIsMissing(strDataFieldValue.length() == 0);
				file.setContentType(strContentType);
				file.setContentDisp(strContentDisp);
				file.setTypeMIME(strTypeMIME);
				file.setSubTypeMIME(strSubTypeMIME);
				if (strContentType.indexOf("application/x-macbinary") > 0)
					m_startData = m_startData + 128;
				file.setSize((m_endData - m_startData) + 1);
				file.setStartData(m_startData);
				file.setEndData(m_endData);
				m_files.addFile(file);
			} else {
				String strFieldContent =
					new String(
						m_binArray,
						m_startData,
						(m_endData - m_startData) + 1);
				m_formRequest.putParameter(strFieldName, strFieldContent);
			}
			if ((char) m_binArray[m_currentIndex + 1] == '-')
				break;
		}

	}

	/**
	 * 
	 * @param str 上载路径
	 * @return 上载文件的个数
	 * @throws ServletException
	 * @throws IOException
	 * @throws UploadException
	 */
	public int save(String str)
		throws ServletException, IOException, UploadException {
		return save(str, 0);
	}

	/**
	 * 
	 * @param str 上载路径
	 * @param i 是否按物理或相对路径上载
	 * @return 上载文件的个数
	 * @throws ServletException
	 * @throws IOException
	 * @throws UploadException
	 */
	public int save(String str, int i)
		throws ServletException, IOException, UploadException {
		int j = 0;
		if (str == null)
			str = m_application.getRealPath("/");
		if (str.indexOf("/") != -1) {
			if (str.charAt(str.length() - 1) != '/')
				str = str + "/";
		} else if (str.charAt(str.length() - 1) != '\\')
			str = str + "\\";
		for (int k = 0; k < m_files.getCount(); k++)
			if (!m_files.getFile(k).isMissing()) {
				m_files.getFile(k).saveAs(
					str + m_files.getFile(k).getFileName(),
					i);
				j++;
			}

		return j;
	}

	/**
	 * 所有文件的总合 
	 * @return 所有文件的总大小
	 */
	public int getSize() {
		return m_totalBytes;
	}

	/**
	 * 获取指定位置的字节
	 * @param i 指定的位置
	 * @return 字节
	 */
	public byte getBinaryData(int i) {
		byte byte0;
		try {
			byte0 = m_binArray[i];
		} catch (Exception exception) {
			throw new ArrayIndexOutOfBoundsException("位置超界");
		}
		return byte0;
	}

	/**
	 * 获取UploadFiles对象
	 * @returnUploadFiles对象
	 */
	public UploadFiles getFiles() {
		return m_files;
	}

	/**
	 * 获取Request对象
	 * @return Request对象
	 */
	public Request getRequest() {
		return m_formRequest;
	}

	/**
	 * 下载文件
	 * @param strFileName 要下载的文件名
	 * @throws ServletException
	 * @throws IOException
	 * @throws UploadException
	 */
	public void downloadFile(String strFileName)
		throws ServletException, IOException, UploadException {
		downloadFile(strFileName, null, null);
	}

	/**
	 * 下载文件
	 * @param strFileName 要下载的文件名
	 * @param strDestName 目标文件名
	 * @throws ServletException
	 * @throws IOException
	 * @throws UploadException
	 */
	public void downloadFile(String strFileName, String strDestName)
		throws ServletException, IOException, UploadException {
		downloadFile(strFileName, strDestName, null);
	}

	/**
	 * 下载文件
	 * @param strFileName 要下载的文件名
	 * @param strContentType response类型
	 * @param strDestName 目标文件名
	 * @throws ServletException
	 * @throws IOException
	 * @throws UploadException
	 */
	public void downloadFile(
		String strFileName,
		String strDestName,
	String strContentType)
		throws ServletException, IOException, UploadException {
		downloadFile(strFileName, strDestName, strContentType, 65000);
	}

	/**
	 * 下载文件
	 * @param strFileName 要下载的文件名
	 * @param strContentType response类型
	 * @param strDestName 目标文件名
	 * @param nStepLength 每次读取的字节数
	 * @throws ServletException
	 * @throws IOException
	 * @throws UploadException
	 */
	public void downloadFile(
		String strFileName,
		String strDestName,
		String strContentType,
		int nStepLength)
		throws ServletException, IOException, UploadException {
		if (strFileName == null)
			throw new IllegalArgumentException(
				"没有找到文件 '" + strFileName + "'");
		if (strFileName.equals(""))
			throw new IllegalArgumentException(
				"没有找到文件 '" + strFileName + "'");
		if (!isVirtual(strFileName) && m_denyPhysicalPath)
			throw new SecurityException("物理路径禁止访问");
		if (isVirtual(strFileName))
			strFileName = m_application.getRealPath(strFileName);
		File file = new File(strFileName);
		FileInputStream fileinputstream = new FileInputStream(file);
		long l = file.length();
		boolean flag = false;
		int k = 0;
		byte abyte0[] = new byte[nStepLength];
		if (strContentType == null)
			m_response.setContentType("application/x-msdownload");
		else if (strContentType.length() == 0)
			m_response.setContentType("application/x-msdownload");
		else
			m_response.setContentType(strContentType);
		m_response.setContentLength((int) l);
		m_contentDisposition =
			m_contentDisposition != null ? m_contentDisposition : "attachment;";
		if (strDestName == null)
			m_response.setHeader(
				"Content-Disposition",
				m_contentDisposition + " filename=" + strDestName);
		else if (strDestName.length() == 0)
			m_response.setHeader("Content-Disposition", m_contentDisposition);
		else
			m_response.setHeader(
				"Content-Disposition",
				m_contentDisposition + " filename=" + getFileName(strFileName));
		int j = 0;
		while ((long) k < l) {
			j = fileinputstream.read(abyte0, 0, nStepLength);
			k += j;
			m_response.getOutputStream().write(abyte0, 0, j);
		}
		fileinputstream.close();
	}

	/**
	 * 从数据库中下载/读取文件
	 * @param resultset 含有要下载文件的结果集
	 * @param binaryFieldName 存储下载文件的列名
	 * @param contentType 文件下载方式，默认为“application/x-msdownload”
	 * @param fileName 保存到客户端的文件名
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void downloadField(
		ResultSet resultset,
		String binaryFieldName,
		String contentType,
		String fileName)
		throws ServletException, IOException, SQLException {
		downloadField(resultset, binaryFieldName, contentType, fileName, 0);
	}

	/**
	 * 从数据库中下载/读取文件
	 * @param resultset 含有要下载文件的结果集
	 * @param binaryFieldName 存储下载文件的列名
	 * @param contentType 文件下载方式，默认为“application/x-msdownload”
	 * @param fileName 保存到客户端的文件名
	 * @param fileSize 文件的实际大小
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void downloadField(
		ResultSet resultset,
		String binaryFieldName,
		String contentType,
		String fileName,
		int fileSize)
		throws ServletException, IOException, SQLException {
		byte[] byteFileContent = null;
		if (resultset == null)
			throw new IllegalArgumentException("结果集不能为null");
		if (binaryFieldName == null)
			throw new IllegalArgumentException("列名不能为null");
		if (binaryFieldName.length() == 0)
			throw new IllegalArgumentException("列名不能为空");
		if (fileSize < MAX_READ_SIZE)
			byteFileContent = resultset.getBytes(binaryFieldName);
		if (contentType == null)
			m_response.setContentType("application/x-msdownload");
		else if (contentType.length() == 0)
			m_response.setContentType("application/x-msdownload");
		else
			m_response.setContentType(contentType);
		if (fileSize < MAX_READ_SIZE) {
			m_response.setContentLength(byteFileContent.length);
		} else {
			m_response.setContentLength(fileSize);
		}
		if (fileName == null)
			m_response.setHeader("Content-Disposition", "attachment;");
		else if (fileName.length() == 0)
			m_response.setHeader("Content-Disposition", "attachment;");
		else
			m_response.setHeader(
				"Content-Disposition",
				"attachment; filename=" + fileName);
		if (fileSize < MAX_READ_SIZE) {
			m_response.getOutputStream().write(
				byteFileContent,
				0,
				byteFileContent.length);
		} else {
			InputStream inputstream =
				resultset.getBinaryStream(binaryFieldName);
			byte[] byteContent = new byte[fileSize];
			byteFileContent = new byte[MAX_READ_SIZE];
			int nLoop = fileSize / MAX_READ_SIZE;
			for (int i = 0; i < nLoop; i++) {
				inputstream.read(byteFileContent);
				m_response.getOutputStream().write(
					byteFileContent,
					0,
					byteFileContent.length);
			}
			byteFileContent = new byte[fileSize - nLoop * MAX_READ_SIZE];
			inputstream.read(byteFileContent);
			m_response.getOutputStream().write(
				byteFileContent,
				0,
				byteFileContent.length);
			
		}
	}

	/**
	 * 将结果集的blob字段存储到文件
	 * @param resultset 带有blob字段的结果集
	 * @param binaryFieldName blob字段名称
	 * @param filename 要存储的文件名
	 * @throws ServletException
	 * @throws IOException
	 * @throws UploadException
	 * @throws SQLException
	 */
	public void fieldToFile(
		ResultSet resultset,
		String binaryFieldName,
		String filename)
		throws ServletException, IOException, UploadException, SQLException {
		fieldToFile(resultset, binaryFieldName, filename, 0);
	}

	/**
	 * 将结果集的blob字段存储到文件
	 * @param resultset 带有blob字段的结果集
	 * @param binaryFieldName blob字段名称
	 * @param filename 要存储的文件名
	 * @param fileSize 要存储的文件的实际大小
	 * @throws ServletException
	 * @throws IOException
	 * @throws UploadException
	 * @throws SQLException
	 */
	public void fieldToFile(
		ResultSet resultset,
		String binaryFieldName,
		String filename,
		int fileSize)
		throws ServletException, IOException, UploadException, SQLException {
		try {
			if (m_application.getRealPath(filename) != null)
				filename = m_application.getRealPath(filename);
			FileOutputStream fileoutputstream = new FileOutputStream(filename);
			if (fileSize < MAX_READ_SIZE) {
				byte byteFileContent[] = resultset.getBytes(binaryFieldName);
				fileoutputstream.write(byteFileContent);
			} else {
				InputStream inputstream =
					resultset.getBinaryStream(binaryFieldName);
				byte[] byteRead = new byte[MAX_READ_SIZE];
				int nLoop = fileSize / MAX_READ_SIZE;
				for (int i = 0; i < nLoop; i++) {
					inputstream.read(byteRead);
					fileoutputstream.write(byteRead);
				}
				byteRead = new byte[fileSize - nLoop * MAX_READ_SIZE];
				inputstream.read(byteRead);
				fileoutputstream.write(byteRead);
			}
			//			byte[] binByte = new byte[102400];
			//			inputstream.read(binByte);
			//			int i;
			//			while ((i = inputstream.read()) != -1)
			//				fileoutputstream.write(i);
			//			fileoutputstream.write(binByte);
			fileoutputstream.close();
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			throw new UploadException("不能从数据库中保存文件");
		}
	}

	/**
	 * 查询字符串是是否包含指定的字段值，如果包含则取出。例：<br>
	 * test aaa="bbb" 则包含字段名为“aaa”，其值为“bbb”
	 * @param str 字符串
	 * @param strFieldName 字段名称
	 * @return 字段值
	 */
	private String getDataFieldValue(String str, String strFieldName) {
		String strTemp = "";
		int i = 0;
		strTemp = strFieldName + "=" + '"';
		i = str.indexOf(strTemp);
		if (i > 0) {
			int nStart = i + strTemp.length();
			strTemp = "\"";
			int nEnd = str.indexOf(strTemp, nStart);
			if (nStart > 0 && nEnd > 0)
				strTemp = str.substring(nStart, nEnd);
		}
		return strTemp;
	}

	/**
	 * 获取文件的扩展名
	 * @param str 文件名
	 * @return 文件扩展名
	 */
	private String getFileExt(String str) {
		String strTemp = "";
		int i = 0;
		int j = 0;
		if (str == null)
			return null;
		i = str.lastIndexOf(46) + 1;
		j = str.length();
		strTemp = str.substring(i, j);
		if (str.lastIndexOf(46) > 0)
			return strTemp;
		else
			return "";
	}

	/**
	 * 获取ContentType值
	 * @param str 字符串
	 * @return ContentTpye值
	 */
	private String getContentType(String str) {
		String strTemp = "";
		int nStart = 0;
		strTemp = "Content-Type:";
		nStart = str.indexOf(strTemp) + strTemp.length();
		if (nStart != -1) {
			int nEnd = str.length();
			strTemp = str.substring(nStart, nEnd);
		}
		return strTemp;
	}

	/**
	 * 获取Mime类型
	 * @param str 包含Mime类型信息的字符串
	 * @return Mime类型
	 */
	private String getTypeMIME(String str) {
		int i = 0;
		i = str.indexOf("/");
		if (i != -1)
			return str.substring(1, i);
		else
			return str;
	}

	/**
	 * 获取Mime子类型
	 * @param str 包含Mime类型信息的字符串
	 * @return Mime子类型
	 */
	private String getSubTypeMIME(String str) {
		int i = 0;
		i = str.indexOf("/") + 1;
		if (i != -1) {
			return str.substring(i, str.length());
		} else {
			return str;
		}
	}

	/**
	 * 获取上载信息
	 * @param str 包含上载信息的字符串
	 * @return 上载信息
	 */
	private String getContentDisp(String str) {
		String strTemp = "";
		int i = 0;
		int j = 0;
		i = str.indexOf(":") + 1;
		j = str.indexOf(";");
		strTemp = str.substring(i, j);
		return strTemp;
	}

	/**
	 * 获取上载文件的数据范围
	 */
	private void getDataSection() {
		int i = m_currentIndex;
		int j = 0;
		int k = m_boundary.length();
		m_startData = m_currentIndex;
		m_endData = 0;
		while (i < m_totalBytes)
			if (m_binArray[i] == (byte) m_boundary.charAt(j)) {
				if (j == k - 1) {
					m_endData = ((i - k) + 1) - 3;
					break;
				}
				i++;
				j++;
			} else {
				i++;
				j = 0;
			}
		m_currentIndex = m_endData + k + 3;
	}

	/**
	 * 获取数据头
	 * @return 数据头
	 */
	private String getDataHeader() {
		int i = m_currentIndex;
		int j = 0;
		boolean flag = false;
		for (flag = false; !flag;)
			if (m_binArray[m_currentIndex] == 13
				&& m_binArray[m_currentIndex + 2] == 13) {
				flag = true;
				j = m_currentIndex - 1;
				m_currentIndex = m_currentIndex + 2;
			} else {
				m_currentIndex++;
			}

		String str = new String(m_binArray, i, (j - i) + 1);
		return str;
	}

	/**
	 * 获取文件名称，不包括路径
	 * @param str 包含文件名称的字符串
	 * @return 文件名称
	 */
	private String getFileName(String str) {

		int i = 0;
		i = str.lastIndexOf(47);
		if (i != -1)
			return str.substring(i + 1, str.length());
		i = str.lastIndexOf(92);
		if (i != -1)
			return str.substring(i + 1, str.length());
		else
			return str;
	}

	/**
	 * 设置禁止上载的文件列表，中间用“,”分隔
	 * @param str 由禁止上载文件列表组成的字符串
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void setDeniedFilesList(String str)
		throws ServletException, IOException, SQLException {
		if (str != null) {
			String strTemp = "";
			for (int i = 0; i < str.length(); i++)
				if (str.charAt(i) == ',') {
					if (!m_deniedFilesList.contains(strTemp))
						m_deniedFilesList.addElement(strTemp);
					strTemp = "";
				} else {
					strTemp = strTemp + str.charAt(i);
				}

			if (strTemp != "")
				m_deniedFilesList.addElement(strTemp);
		} else {
			m_deniedFilesList = null;
		}
	}

	/**
	 * 设置允许上载的文件列表，中间用“,”分隔
	 * @param str 由允许上载文件列表组成的字符串
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void setAllowedFilesList(String str) {
		if (str != null) {
			String strTemp = "";
			for (int i = 0; i < str.length(); i++)
				if (str.charAt(i) == ',') {
					if (!m_allowedFilesList.contains(strTemp))
						m_allowedFilesList.addElement(strTemp);
					strTemp = "";
				} else {
					strTemp = strTemp + str.charAt(i);
				}

			if (strTemp != "")
				m_allowedFilesList.addElement(strTemp);
		} else {
			m_allowedFilesList = null;
		}
	}

	/**
	 * 设置禁止访问物理路径
	 * @param flag
	 */
	public void setDenyPhysicalPath(boolean flag) {
		m_denyPhysicalPath = flag;
	}

	/**
	 * 设置强制访问的物理路径
	 * @param flag
	 */
	public void setForcePhysicalPath(boolean flag) {
		m_forcePhysicalPath = flag;
	}

	/**
	 * 设置上传文件信息
	 * @param str
	 */
	public void setContentDisposition(String str) {
		m_contentDisposition = str;
	}

	/**
	 * 上传文件的最大合计值
	 * @param l
	 */
	public void setTotalMaxFileSize(long l) {
		m_totalMaxFileSize = l;
	}

	/**
	 * 上传单个文件的最大值
	 * @param l
	 */
	public void setMaxFileSize(long l) {
		m_maxFileSize = l;
	}

	/**
	 * 获取指定的路径
	 * @param str 文件名称
	 * @param nChoice 自动/相对/物理路径
	 * @return 返回在本地的完整路径
	 * @throws IOException
	 */
	protected String getPhysicalPath(String str, int nChoice) throws IOException {
		String strPath = "";
		String strFileName = "";
		String strFileSeparator = "";
		boolean flag = false;
		strFileSeparator = System.getProperty("file.separator");
		if (str == null)
			throw new IllegalArgumentException("没有指定目标文件");
		if (str.equals(""))
			throw new IllegalArgumentException("没有指定目标文件");
		if (str.lastIndexOf("\\") >= 0) {
			strPath = str.substring(0, str.lastIndexOf("\\"));
			strFileName = str.substring(str.lastIndexOf("\\") + 1);
		}
		if (str.lastIndexOf("/") >= 0) {
			strPath = str.substring(0, str.lastIndexOf("/"));
			strFileName = str.substring(str.lastIndexOf("/") + 1);
		}
		strPath = strPath.length() != 0 ? strPath : "/";
		File file = new File(strPath);
		if (file.exists())
			flag = true;
		if (nChoice == 0) {
			if (isVirtual(strPath)) {
				strPath = m_application.getRealPath(strPath);
				if (strPath.endsWith(strFileSeparator))
					strPath = strPath + strFileName;
				else
					strPath = strPath + strFileSeparator + strFileName;
				return strPath;
			}
			if (flag) {
				if (m_denyPhysicalPath)
					throw new IllegalArgumentException("物理路径禁止访问");
				else
					return str;
			} else {
				throw new IllegalArgumentException("路径不存在");
			}
		}
		if (nChoice == 1) {
			if (isVirtual(strPath)) {
				strPath = m_application.getRealPath(strPath);
				if (strPath.endsWith(strFileSeparator))
					strPath = strPath + strFileName;
				else
					strPath = strPath + strFileSeparator + strFileName;
				return strPath;
			}
			if (flag)
				throw new IllegalArgumentException("这个路径不是相对路径");
			else
				throw new IllegalArgumentException("路径不存在");
		}
		if (nChoice == 2) {
			if (flag)
				if (m_denyPhysicalPath)
					throw new IllegalArgumentException("物理路径禁止访问");
				else
					return str;
			if (isVirtual(strPath))
				throw new IllegalArgumentException("这个路径不是物理路径");
			else
				throw new IllegalArgumentException("路径不存在");
		} else {
			return null;
		}
	}
	
	/**
	 * 将FORM内容上载到指定的文件
	 * @param str 文件名称
	 * @throws IOException
	 * @throws UploadException
	 */
	public void uploadInFile(String str) throws IOException, UploadException {
		int i = 0;
		boolean flag = false;
		if (str == null)
			throw new IllegalArgumentException("指定的文件不存在");
		if (str.length() == 0)
			throw new IllegalArgumentException("指定的文件不存在");
		if (!isVirtual(str) && m_denyPhysicalPath)
			throw new SecurityException("物理路径不存在");
		i = m_request.getContentLength();
		m_binArray = new byte[i];
		int k;
		for (int j=0; j < i; j += k)
			try {
				k = m_request.getInputStream().read(m_binArray, j, i - j);
			} catch (Exception exception) {
				throw new UploadException("不能被上载");
			}

		if (isVirtual(str))
			str = m_application.getRealPath(str);
		try {
			File file = new File(str);
			FileOutputStream fileoutputstream = new FileOutputStream(file);
			fileoutputstream.write(m_binArray);
			fileoutputstream.close();
		} catch (Exception exception1) {
			throw new UploadException("Form内容不能上载到指定的文件");
		}
	}

	/**
	 * 判断相对目录是否存在
	 * @param str 相对应用程序安装目录的目录名称
	 * @return 若存在则返回真
	 */
	private boolean isVirtual(String str) {
		if (m_application.getRealPath(str) != null) {
			File file = new File(m_application.getRealPath(str));
			return file.exists();
		} else {
			return false;
		}
	}

}

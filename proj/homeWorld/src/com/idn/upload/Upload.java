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
 * <P>�ļ��ϴ�/���س��򣬿����ϴ���Ŀ¼�����ݿ⣬Ҳ���Դ�Ŀ¼�����ݿ����ء��������ϴ����ļ�Ϊ50MB</P>
 * 
 * @version 0.1
 * @author navy
 */
public class Upload {

	/**
	 * ���ص��ֽ�����
	 */
	protected byte[] m_binArray;

	/**
	 * Request����
	 */
	protected HttpServletRequest m_request;

	/**
	 * Response��Ӧ
	 */
	protected HttpServletResponse m_response;

	/**
	 * Application����
	 */
	protected ServletContext m_application;

	/**
	 * �����ļ����ܵĴ�С
	 */
	private int m_totalBytes;

	/**
	 * ��ǰ������
	 */
	private int m_currentIndex;

	/**
	 * ��ʼ
	 */
	private int m_startData;

	/**
	 * ����
	 */
	private int m_endData;

	/**
	 * ���ݷֽ���
	 */
	private String m_boundary;

	/**
	 * �����ļ��ܺ�ֵ
	 */
	private long m_totalMaxFileSize;

	/**
	 * ���ļ������ֵ
	 */
	private long m_maxFileSize;

	/**
	 * ���������ص��ļ�
	 */
	private Vector m_deniedFilesList;

	/**
	 * �������ص��ļ�
	 */
	private Vector m_allowedFilesList;

	/**
	 * ���ܷ��ʵ�����·��
	 */
	private boolean m_denyPhysicalPath;

	/**
	 * ǿ������·��
	 */
	private boolean m_forcePhysicalPath;

	/**
	 * �ļ�������Ϣ
	 */
	private String m_contentDisposition;

	/**
	 * �Զ��жϣ�������·�������ڣ����������·��
	 */
	public static final int SAVE_AUTO = 0;

	/**
	 * �����Ŀ¼���ļ�
	 */
	public static final int SAVE_VIRTUAL = 1;

	/**
	 * ������Ŀ¼�洢�ļ�
	 */
	public static final int SAVE_PHYSICAL = 2;

	/**
	 * UploadFiles����
	 */
	private UploadFiles m_files;

	/**
	 * Request����
	 */
	private Request m_formRequest;

	/**
	 * ÿ�δӽ�����ж�ȡblob����ʱΪ100k�ֽ�
	 */
	private int MAX_READ_SIZE = 102400;

	/**
	 * ʵ����
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
	 * ��ʼ��Ӧ�ó���
	 * @param servletconfig
	 * @throws ServletException
	 */
	public final void init(ServletConfig servletconfig)
		throws ServletException {
		m_application = servletconfig.getServletContext();
	}

	/**
	 * ����������ʼ��response��request����
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
	 * ��ʼ��Ӧ�ó���request����response����
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
	 * ����ҳ�������ģ���ʼ��Ӧ�ó���request����response����
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
	 * ��ʼ��Ӧ�ó���request����response����
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
	 * �����ļ��������е��ļ�ͨ��UploadsFiles�������������ȴ��洢��Ӳ�̻����ݿ� 
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
				throw new UploadException("���ܹ�����");
			}

		//������һ��
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
					throw new SecurityException("ָ�����ļ�����ֹ����");
				if (!m_allowedFilesList.isEmpty()
					&& !m_allowedFilesList.contains(strFileExt))
					throw new SecurityException("ָ�����ļ�������������");
				if (m_maxFileSize > 0L
					&& (long) ((m_endData - m_startData) + 1) > m_maxFileSize)
					throw new SecurityException(
						"���ص��ļ�̫���� : "
							+ strFileName);
				l += (m_endData - m_startData) + 1;
				if (m_totalMaxFileSize > 0L && l > m_totalMaxFileSize)
					throw new SecurityException("�ļ��Ĵ�С�ϼƳ����˿������صķ�Χ");
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
	 * @param str ����·��
	 * @return �����ļ��ĸ���
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
	 * @param str ����·��
	 * @param i �Ƿ���������·������
	 * @return �����ļ��ĸ���
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
	 * �����ļ����ܺ� 
	 * @return �����ļ����ܴ�С
	 */
	public int getSize() {
		return m_totalBytes;
	}

	/**
	 * ��ȡָ��λ�õ��ֽ�
	 * @param i ָ����λ��
	 * @return �ֽ�
	 */
	public byte getBinaryData(int i) {
		byte byte0;
		try {
			byte0 = m_binArray[i];
		} catch (Exception exception) {
			throw new ArrayIndexOutOfBoundsException("λ�ó���");
		}
		return byte0;
	}

	/**
	 * ��ȡUploadFiles����
	 * @returnUploadFiles����
	 */
	public UploadFiles getFiles() {
		return m_files;
	}

	/**
	 * ��ȡRequest����
	 * @return Request����
	 */
	public Request getRequest() {
		return m_formRequest;
	}

	/**
	 * �����ļ�
	 * @param strFileName Ҫ���ص��ļ���
	 * @throws ServletException
	 * @throws IOException
	 * @throws UploadException
	 */
	public void downloadFile(String strFileName)
		throws ServletException, IOException, UploadException {
		downloadFile(strFileName, null, null);
	}

	/**
	 * �����ļ�
	 * @param strFileName Ҫ���ص��ļ���
	 * @param strDestName Ŀ���ļ���
	 * @throws ServletException
	 * @throws IOException
	 * @throws UploadException
	 */
	public void downloadFile(String strFileName, String strDestName)
		throws ServletException, IOException, UploadException {
		downloadFile(strFileName, strDestName, null);
	}

	/**
	 * �����ļ�
	 * @param strFileName Ҫ���ص��ļ���
	 * @param strContentType response����
	 * @param strDestName Ŀ���ļ���
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
	 * �����ļ�
	 * @param strFileName Ҫ���ص��ļ���
	 * @param strContentType response����
	 * @param strDestName Ŀ���ļ���
	 * @param nStepLength ÿ�ζ�ȡ���ֽ���
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
				"û���ҵ��ļ� '" + strFileName + "'");
		if (strFileName.equals(""))
			throw new IllegalArgumentException(
				"û���ҵ��ļ� '" + strFileName + "'");
		if (!isVirtual(strFileName) && m_denyPhysicalPath)
			throw new SecurityException("����·����ֹ����");
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
	 * �����ݿ�������/��ȡ�ļ�
	 * @param resultset ����Ҫ�����ļ��Ľ����
	 * @param binaryFieldName �洢�����ļ�������
	 * @param contentType �ļ����ط�ʽ��Ĭ��Ϊ��application/x-msdownload��
	 * @param fileName ���浽�ͻ��˵��ļ���
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
	 * �����ݿ�������/��ȡ�ļ�
	 * @param resultset ����Ҫ�����ļ��Ľ����
	 * @param binaryFieldName �洢�����ļ�������
	 * @param contentType �ļ����ط�ʽ��Ĭ��Ϊ��application/x-msdownload��
	 * @param fileName ���浽�ͻ��˵��ļ���
	 * @param fileSize �ļ���ʵ�ʴ�С
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
			throw new IllegalArgumentException("���������Ϊnull");
		if (binaryFieldName == null)
			throw new IllegalArgumentException("��������Ϊnull");
		if (binaryFieldName.length() == 0)
			throw new IllegalArgumentException("��������Ϊ��");
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
	 * ���������blob�ֶδ洢���ļ�
	 * @param resultset ����blob�ֶεĽ����
	 * @param binaryFieldName blob�ֶ�����
	 * @param filename Ҫ�洢���ļ���
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
	 * ���������blob�ֶδ洢���ļ�
	 * @param resultset ����blob�ֶεĽ����
	 * @param binaryFieldName blob�ֶ�����
	 * @param filename Ҫ�洢���ļ���
	 * @param fileSize Ҫ�洢���ļ���ʵ�ʴ�С
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
			throw new UploadException("���ܴ����ݿ��б����ļ�");
		}
	}

	/**
	 * ��ѯ�ַ������Ƿ����ָ�����ֶ�ֵ�����������ȡ��������<br>
	 * test aaa="bbb" ������ֶ���Ϊ��aaa������ֵΪ��bbb��
	 * @param str �ַ���
	 * @param strFieldName �ֶ�����
	 * @return �ֶ�ֵ
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
	 * ��ȡ�ļ�����չ��
	 * @param str �ļ���
	 * @return �ļ���չ��
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
	 * ��ȡContentTypeֵ
	 * @param str �ַ���
	 * @return ContentTpyeֵ
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
	 * ��ȡMime����
	 * @param str ����Mime������Ϣ���ַ���
	 * @return Mime����
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
	 * ��ȡMime������
	 * @param str ����Mime������Ϣ���ַ���
	 * @return Mime������
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
	 * ��ȡ������Ϣ
	 * @param str ����������Ϣ���ַ���
	 * @return ������Ϣ
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
	 * ��ȡ�����ļ������ݷ�Χ
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
	 * ��ȡ����ͷ
	 * @return ����ͷ
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
	 * ��ȡ�ļ����ƣ�������·��
	 * @param str �����ļ����Ƶ��ַ���
	 * @return �ļ�����
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
	 * ���ý�ֹ���ص��ļ��б��м��á�,���ָ�
	 * @param str �ɽ�ֹ�����ļ��б���ɵ��ַ���
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
	 * �����������ص��ļ��б��м��á�,���ָ�
	 * @param str �����������ļ��б���ɵ��ַ���
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
	 * ���ý�ֹ��������·��
	 * @param flag
	 */
	public void setDenyPhysicalPath(boolean flag) {
		m_denyPhysicalPath = flag;
	}

	/**
	 * ����ǿ�Ʒ��ʵ�����·��
	 * @param flag
	 */
	public void setForcePhysicalPath(boolean flag) {
		m_forcePhysicalPath = flag;
	}

	/**
	 * �����ϴ��ļ���Ϣ
	 * @param str
	 */
	public void setContentDisposition(String str) {
		m_contentDisposition = str;
	}

	/**
	 * �ϴ��ļ������ϼ�ֵ
	 * @param l
	 */
	public void setTotalMaxFileSize(long l) {
		m_totalMaxFileSize = l;
	}

	/**
	 * �ϴ������ļ������ֵ
	 * @param l
	 */
	public void setMaxFileSize(long l) {
		m_maxFileSize = l;
	}

	/**
	 * ��ȡָ����·��
	 * @param str �ļ�����
	 * @param nChoice �Զ�/���/����·��
	 * @return �����ڱ��ص�����·��
	 * @throws IOException
	 */
	protected String getPhysicalPath(String str, int nChoice) throws IOException {
		String strPath = "";
		String strFileName = "";
		String strFileSeparator = "";
		boolean flag = false;
		strFileSeparator = System.getProperty("file.separator");
		if (str == null)
			throw new IllegalArgumentException("û��ָ��Ŀ���ļ�");
		if (str.equals(""))
			throw new IllegalArgumentException("û��ָ��Ŀ���ļ�");
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
					throw new IllegalArgumentException("����·����ֹ����");
				else
					return str;
			} else {
				throw new IllegalArgumentException("·��������");
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
				throw new IllegalArgumentException("���·���������·��");
			else
				throw new IllegalArgumentException("·��������");
		}
		if (nChoice == 2) {
			if (flag)
				if (m_denyPhysicalPath)
					throw new IllegalArgumentException("����·����ֹ����");
				else
					return str;
			if (isVirtual(strPath))
				throw new IllegalArgumentException("���·����������·��");
			else
				throw new IllegalArgumentException("·��������");
		} else {
			return null;
		}
	}
	
	/**
	 * ��FORM�������ص�ָ�����ļ�
	 * @param str �ļ�����
	 * @throws IOException
	 * @throws UploadException
	 */
	public void uploadInFile(String str) throws IOException, UploadException {
		int i = 0;
		boolean flag = false;
		if (str == null)
			throw new IllegalArgumentException("ָ�����ļ�������");
		if (str.length() == 0)
			throw new IllegalArgumentException("ָ�����ļ�������");
		if (!isVirtual(str) && m_denyPhysicalPath)
			throw new SecurityException("����·��������");
		i = m_request.getContentLength();
		m_binArray = new byte[i];
		int k;
		for (int j=0; j < i; j += k)
			try {
				k = m_request.getInputStream().read(m_binArray, j, i - j);
			} catch (Exception exception) {
				throw new UploadException("���ܱ�����");
			}

		if (isVirtual(str))
			str = m_application.getRealPath(str);
		try {
			File file = new File(str);
			FileOutputStream fileoutputstream = new FileOutputStream(file);
			fileoutputstream.write(m_binArray);
			fileoutputstream.close();
		} catch (Exception exception1) {
			throw new UploadException("Form���ݲ������ص�ָ�����ļ�");
		}
	}

	/**
	 * �ж����Ŀ¼�Ƿ����
	 * @param str ���Ӧ�ó���װĿ¼��Ŀ¼����
	 * @return �������򷵻���
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

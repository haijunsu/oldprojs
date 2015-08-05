/*
 * @(#)UploadFile.java  2003-8-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.upload;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;

/**
 * <P>�����ļ�������</P>
 * 
 * @version 0.1
 * @author navy
 */

public class UploadFile {

	/**
	 * Upload ʵ��
	 */
	private Upload m_parent;

	/**
	 * �ļ���ʼ��
	 */
	private int m_startData;

	/**
	 * �ļ�������
	 */
	private int m_endData;

	/**
	 * �ļ���С
	 */
	private int m_size;

	/**
	 * ���ص��ֶ�����
	 */
	private String m_fieldname;

	/**
	 * �ļ�����
	 */
	private String m_filename;

	/**
	 * �ļ���չ��
	 */
	private String m_fileExt;

	/**
	 * ����ʱ���ļ�·��
	 */
	private String m_filePathName;

	/**
	 * �ļ��������ͣ�ʾ����application/x-zip-compressed��
	 */
	private String m_contentType;

	/**
	 * �ļ���Ϣ��һ��Ϊ��form-data��
	 */
	private String m_contentDisp;

	/**
	 * �ļ�Mime���ͣ�һ��Ϊ��application��
	 */
	private String m_typeMime;

	/**
	 * �ļ�Mime�����ͣ�ʾ����x-zip-compressed��
	 */
	private String m_subTypeMime;

	/**
	 * �ļ������ַ���
	 */
	private String m_contentString;

	/**
	 * �����ļ��Ƿ���ڵı�־
	 */
	private boolean m_isMissing;

	/**
	 * �Զ�ģʽ���Ȱ����Ŀ¼�ң�����ɹ���洢��������ɹ���������Ŀ¼
	 */
	public static final int SAVEAS_AUTO = 0;

	/**
	 * �����Ŀ¼����
	 */
	public static final int SAVEAS_VIRTUAL = 1;

	/**
	 * ������·������
	 */
	public static final int SAVEAS_PHYSICAL = 2;

	/**
	 * ʵ����
	 *
	 */
	UploadFile() {
		m_startData = 0;
		m_endData = 0;
		m_size = 0;
		m_fieldname = new String();
		m_filename = new String();
		m_fileExt = new String();
		m_filePathName = new String();
		m_contentType = new String();
		m_contentDisp = new String();
		m_typeMime = new String();
		m_subTypeMime = new String();
		m_contentString = new String();
		m_isMissing = true;
	}

	/**
	 * ���ļ��洢��Ӳ��
	 * @param str ָ�����ļ���
	 * @throws IOException
	 * @throws UploadException
	 */
	public void saveAs(String str) throws IOException, UploadException {
		saveAs(str, 0);
	}
	
	/**
	 * ���ļ��洢��Ӳ��
	 * @param str ָ�����ļ���
	 * @param i
	 * @throws IOException
	 * @throws UploadException
	 */
	public void saveAs(String str, int i) throws IOException, UploadException {
		String strPhysicalPath = new String();
		strPhysicalPath = m_parent.getPhysicalPath(str, i);
		if (strPhysicalPath == null)
			throw new IllegalArgumentException("û��ָ��Ŀ���ļ�");
		try {
			java.io.File file = new java.io.File(strPhysicalPath);
			FileOutputStream fileoutputstream = new FileOutputStream(file);
			fileoutputstream.write(m_parent.m_binArray, m_startData, m_size);
			fileoutputstream.close();
		} catch (IOException ioexception) {
			throw new UploadException("�ļ��洢ʧ��");
		}
	}
	
	/**
	 * ��ȡ�ļ���������
	 * @return �ļ���������
	 */
	public ByteArrayInputStream getByteArrayInputStream() {
		return new ByteArrayInputStream(
			m_parent.m_binArray,
			m_startData,
			m_size);
	}
	
	/**
	 * ���ļ���ֵ��������е�blob��
	 * @param resultset �����
	 * @param str ����
	 * @throws ServletException
	 * @throws IOException
	 * @throws UploadException
	 * @throws SQLException
	 */
	public void fileToField(ResultSet resultset, String str)
		throws ServletException, IOException, UploadException, SQLException {
		long l = 0L;
		int i = 0x10000;
		int j = 0;
		int k = m_startData;
		if (resultset == null)
			throw new IllegalArgumentException("RecordSet����Ϊ��");
		if (str == null)
			throw new IllegalArgumentException("��������Ϊ��");
		if (str.length() == 0)
			throw new IllegalArgumentException("�������Ȳ���Ϊ��");
		l =
			BigInteger
				.valueOf(m_size)
				.divide(BigInteger.valueOf(i))
				.longValue();
		j = BigInteger.valueOf(m_size).mod(BigInteger.valueOf(i)).intValue();
		try {
			for (int i1 = 1;(long) i1 < l; i1++) {
				resultset.updateBinaryStream(
					str,
					new ByteArrayInputStream(m_parent.m_binArray, k, i),
					i);
				k = k != 0 ? k : 1;
				k = i1 * i + m_startData;
			}

			if (j > 0)
				resultset.updateBinaryStream(
					str,
					new ByteArrayInputStream(m_parent.m_binArray, k, j),
					j);
		} catch (SQLException sqlexception) {
			byte abyte0[] = new byte[m_size];
			System.arraycopy(
				m_parent.m_binArray,
				m_startData,
				abyte0,
				0,
				m_size);
			resultset.updateBytes(str, abyte0);
		} catch (Exception exception) {
			throw new UploadException("���ļ��洢�����ݿ�ʱʧ��");
		}
	}
	
	/**
	 * �Ҳ�����־
	 * @return �Ҳ�����־
	 */
	public boolean isMissing() {
		return m_isMissing;
	}
	
	/**
	 * ����ʱ���ֶε�����
	 * @return �ֶε�����
	 */
	public String getFieldName() {
		return m_fieldname;
	}
	
	/**
	 * �ļ�����
	 * @return �ļ�����
	 */
	public String getFileName() {
		return m_filename;
	}
	
	/**
	 * ����ʱ��·��
	 * @return ����ʱ��·��
	 */
	public String getFilePathName() {
		return m_filePathName;
	}
	
	/**
	 * �ļ���չ��
	 * @return �ļ���չ��
	 */
	public String getFileExt() {
		return m_fileExt;
	}

	/**
	 * �ļ���������
	 * @return �ļ���������
	 */
	public String getContentType() {
		return m_contentType;
	}
	
	/**
	 * �ļ���Ϣ
	 * @return �ļ���Ϣ
	 */
	public String getContentDisp() {
		return m_contentDisp;
	}
	
	/**
	 * �ļ������ַ���
	 * @return �ļ������ַ���
	 */
	public String getContentString() {
		String str = new String(m_parent.m_binArray, m_startData, m_size);
		return str;
	}
	
	/**
	 * �ļ�MIME����
	 * @return �ļ�MIME����
	 * @throws IOException
	 */
	public String getTypeMIME() throws IOException {
		return m_typeMime;
	}
	
	/**
	 * �ļ�MIME������
	 * @return �ļ�MIME������
	 */
	public String getSubTypeMIME() {
		return m_subTypeMime;
	}
	
	/**
	 * �ļ���С
	 * @return �ļ���С
	 */
	public int getSize() {
		return m_size;
	}
	
	/**
	 * �ļ�������ʼλ��
	 * @return �ļ�������ʼλ��
	 */
	protected int getStartData() {
		return m_startData;
	}
	
	/**
	 * �ļ����ݽ���λ��
	 * @return �ļ����ݽ���λ��
	 */
	protected int getEndData() {
		return m_endData;
	}
	
	/**
	 * ����Upload����
	 * @param upload Upload����
	 */
	protected void setParent(Upload upload) {
		m_parent = upload;
	}
	
	/**
	 * �����ļ���ʼ��
	 * @param i
	 */
	protected void setStartData(int i) {
		m_startData = i;
	}
	
	/**
	 * �����ļ�������
	 * @param i
	 */
	protected void setEndData(int i) {
		m_endData = i;
	}
	
	/**
	 * �����ļ���С
	 * @param i
	 */
	protected void setSize(int i) {
		m_size = i;
	}
	
	/**
	 * �ļ��Ƿ����
	 * @param flag
	 */
	protected void setIsMissing(boolean flag) {
		m_isMissing = flag;
	}

	/**
	 * ��������ʱ�ֶ���
	 * @param str
	 */
	protected void setFieldName(String str) {
		m_fieldname = str;
	}

	/**
	 * �����ļ�����
	 * @param str
	 */
	protected void setFileName(String str) {
		m_filename = str;
	}

	/**
	 * �����ļ�·��
	 * @param str
	 */
	protected void setFilePathName(String str) {
		m_filePathName = str;
	}

	/**
	 * �����ļ���չ��
	 * @param str
	 */
	protected void setFileExt(String str) {
		m_fileExt = str;
	}

	/**
	 * ������������
	 * @param str
	 */
	protected void setContentType(String str) {
		m_contentType = str;
	}
	
	/**
	 * ����������Ϣ
	 * @param str
	 */
	protected void setContentDisp(String str) {
		m_contentDisp = str;
	}

	/**
	 * ����MIME����
	 * @param str
	 */
	protected void setTypeMIME(String str) {
		m_typeMime = str;
	}

	/**
	 * ����MIME������
	 * @param str
	 */
	protected void setSubTypeMIME(String str) {
		m_subTypeMime = str;
	}

	/**
	 * ��ȡ�ļ���ָ�ֵ��ֽ�
	 * @param i �ֽڵ�λ��
	 * @return �ļ���ָ�ֵ��ֽ�
	 */
	public byte getBinaryData(int i) {
		if (m_startData + i > m_endData)
			throw new ArrayIndexOutOfBoundsException("ָ�����ֽ�λ�ó���");
		if (m_startData + i <= m_endData)
			return m_parent.m_binArray[m_startData + i];
		else
			return 0;
	}

}

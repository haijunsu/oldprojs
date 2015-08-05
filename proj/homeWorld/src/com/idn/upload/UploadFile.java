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
 * <P>上载文件的属性</P>
 * 
 * @version 0.1
 * @author navy
 */

public class UploadFile {

	/**
	 * Upload 实例
	 */
	private Upload m_parent;

	/**
	 * 文件起始点
	 */
	private int m_startData;

	/**
	 * 文件结束点
	 */
	private int m_endData;

	/**
	 * 文件大小
	 */
	private int m_size;

	/**
	 * 上载的字段名称
	 */
	private String m_fieldname;

	/**
	 * 文件名称
	 */
	private String m_filename;

	/**
	 * 文件扩展名
	 */
	private String m_fileExt;

	/**
	 * 上载时的文件路径
	 */
	private String m_filePathName;

	/**
	 * 文件内容类型，示例“application/x-zip-compressed”
	 */
	private String m_contentType;

	/**
	 * 文件信息，一般为“form-data”
	 */
	private String m_contentDisp;

	/**
	 * 文件Mime类型，一般为“application”
	 */
	private String m_typeMime;

	/**
	 * 文件Mime子类型，示例“x-zip-compressed”
	 */
	private String m_subTypeMime;

	/**
	 * 文件内容字符串
	 */
	private String m_contentString;

	/**
	 * 本地文件是否存在的标志
	 */
	private boolean m_isMissing;

	/**
	 * 自动模式，先按相对目录找，如果成功则存储，如果不成功则找物理目录
	 */
	public static final int SAVEAS_AUTO = 0;

	/**
	 * 按相对目录保存
	 */
	public static final int SAVEAS_VIRTUAL = 1;

	/**
	 * 按物理路径保存
	 */
	public static final int SAVEAS_PHYSICAL = 2;

	/**
	 * 实例化
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
	 * 将文件存储到硬盘
	 * @param str 指定的文件名
	 * @throws IOException
	 * @throws UploadException
	 */
	public void saveAs(String str) throws IOException, UploadException {
		saveAs(str, 0);
	}
	
	/**
	 * 将文件存储到硬盘
	 * @param str 指定的文件名
	 * @param i
	 * @throws IOException
	 * @throws UploadException
	 */
	public void saveAs(String str, int i) throws IOException, UploadException {
		String strPhysicalPath = new String();
		strPhysicalPath = m_parent.getPhysicalPath(str, i);
		if (strPhysicalPath == null)
			throw new IllegalArgumentException("没有指定目标文件");
		try {
			java.io.File file = new java.io.File(strPhysicalPath);
			FileOutputStream fileoutputstream = new FileOutputStream(file);
			fileoutputstream.write(m_parent.m_binArray, m_startData, m_size);
			fileoutputstream.close();
		} catch (IOException ioexception) {
			throw new UploadException("文件存储失败");
		}
	}
	
	/**
	 * 获取文件的数据流
	 * @return 文件的数据流
	 */
	public ByteArrayInputStream getByteArrayInputStream() {
		return new ByteArrayInputStream(
			m_parent.m_binArray,
			m_startData,
			m_size);
	}
	
	/**
	 * 将文件赋值给结果集中的blob列
	 * @param resultset 结果集
	 * @param str 列名
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
			throw new IllegalArgumentException("RecordSet不能为空");
		if (str == null)
			throw new IllegalArgumentException("列名不能为空");
		if (str.length() == 0)
			throw new IllegalArgumentException("列名长度不能为零");
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
			throw new UploadException("将文件存储到数据库时失败");
		}
	}
	
	/**
	 * 找不到标志
	 * @return 找不到标志
	 */
	public boolean isMissing() {
		return m_isMissing;
	}
	
	/**
	 * 上载时的字段的名称
	 * @return 字段的名称
	 */
	public String getFieldName() {
		return m_fieldname;
	}
	
	/**
	 * 文件名称
	 * @return 文件名称
	 */
	public String getFileName() {
		return m_filename;
	}
	
	/**
	 * 上载时的路径
	 * @return 上载时的路径
	 */
	public String getFilePathName() {
		return m_filePathName;
	}
	
	/**
	 * 文件扩展名
	 * @return 文件扩展名
	 */
	public String getFileExt() {
		return m_fileExt;
	}

	/**
	 * 文件内容类型
	 * @return 文件内容类型
	 */
	public String getContentType() {
		return m_contentType;
	}
	
	/**
	 * 文件信息
	 * @return 文件信息
	 */
	public String getContentDisp() {
		return m_contentDisp;
	}
	
	/**
	 * 文件内容字符串
	 * @return 文件内容字符串
	 */
	public String getContentString() {
		String str = new String(m_parent.m_binArray, m_startData, m_size);
		return str;
	}
	
	/**
	 * 文件MIME类型
	 * @return 文件MIME类型
	 * @throws IOException
	 */
	public String getTypeMIME() throws IOException {
		return m_typeMime;
	}
	
	/**
	 * 文件MIME子类型
	 * @return 文件MIME子类型
	 */
	public String getSubTypeMIME() {
		return m_subTypeMime;
	}
	
	/**
	 * 文件大小
	 * @return 文件大小
	 */
	public int getSize() {
		return m_size;
	}
	
	/**
	 * 文件内容起始位置
	 * @return 文件内容起始位置
	 */
	protected int getStartData() {
		return m_startData;
	}
	
	/**
	 * 文件内容结束位置
	 * @return 文件内容结束位置
	 */
	protected int getEndData() {
		return m_endData;
	}
	
	/**
	 * 设置Upload对象
	 * @param upload Upload对象
	 */
	protected void setParent(Upload upload) {
		m_parent = upload;
	}
	
	/**
	 * 设置文件起始点
	 * @param i
	 */
	protected void setStartData(int i) {
		m_startData = i;
	}
	
	/**
	 * 设置文件结束点
	 * @param i
	 */
	protected void setEndData(int i) {
		m_endData = i;
	}
	
	/**
	 * 设置文件大小
	 * @param i
	 */
	protected void setSize(int i) {
		m_size = i;
	}
	
	/**
	 * 文件是否存在
	 * @param flag
	 */
	protected void setIsMissing(boolean flag) {
		m_isMissing = flag;
	}

	/**
	 * 设置上载时字段名
	 * @param str
	 */
	protected void setFieldName(String str) {
		m_fieldname = str;
	}

	/**
	 * 设置文件名称
	 * @param str
	 */
	protected void setFileName(String str) {
		m_filename = str;
	}

	/**
	 * 设置文件路径
	 * @param str
	 */
	protected void setFilePathName(String str) {
		m_filePathName = str;
	}

	/**
	 * 设置文件扩展名
	 * @param str
	 */
	protected void setFileExt(String str) {
		m_fileExt = str;
	}

	/**
	 * 设置内容类型
	 * @param str
	 */
	protected void setContentType(String str) {
		m_contentType = str;
	}
	
	/**
	 * 设置内容信息
	 * @param str
	 */
	protected void setContentDisp(String str) {
		m_contentDisp = str;
	}

	/**
	 * 设置MIME类型
	 * @param str
	 */
	protected void setTypeMIME(String str) {
		m_typeMime = str;
	}

	/**
	 * 设置MIME子类型
	 * @param str
	 */
	protected void setSubTypeMIME(String str) {
		m_subTypeMime = str;
	}

	/**
	 * 获取文件中指字的字节
	 * @param i 字节的位置
	 * @return 文件中指字的字节
	 */
	public byte getBinaryData(int i) {
		if (m_startData + i > m_endData)
			throw new ArrayIndexOutOfBoundsException("指定的字节位置超界");
		if (m_startData + i <= m_endData)
			return m_parent.m_binArray[m_startData + i];
		else
			return 0;
	}

}

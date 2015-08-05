/*
 * @(#)UploadFiles.java  2003-8-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.upload;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * <P>�����ļ�����</P>
 * 
 * @version 0.1
 * @author navy
 */
public class UploadFiles {
	/**
	 * Upload ʵ��
	 */
	private Upload m_parent;

	/**
	 * �洢�����ļ��Ĺ�ϣ��
	 */
	private Hashtable m_files;

	/**
	 * �����ļ��ĸ���
	 */
	private int m_counter;

	/**
	 * ʵ����
	 */
	UploadFiles() {
		m_files = new Hashtable();
		m_counter = 0;
	}

	/**
	 * ������ļ�
	 * @param file UploadFile
	 */
	protected void addFile(UploadFile file) {
		if (file == null) {
			throw new IllegalArgumentException("�������ؿ��ļ�");
		} else {
			m_files.put(new Integer(m_counter), file);
			m_counter++;
			return;
		}
	}

	/**
	 * ��Hashtable�����л�ȡUploadFile����
	 * @param i �ļ��ڼ����е�λ��
	 * @return UploadFile����
	 */
	public UploadFile getFile(int i) {
		if (i < 0)
			throw new IllegalArgumentException("�ļ�λ�ò���С��0");
		UploadFile file = (UploadFile) m_files.get(new Integer(i));
		if (file == null)
			throw new IllegalArgumentException("�ļ�������");
		else
			return file;
	}

	/**
	 * ��ȡ�����ļ��ĸ���
	 * @return
	 */
	public int getCount() {
		return m_counter;
	}

	/**
	 * �����ļ��Ĵ�С
	 * @return ���ص������ļ���С���ܺ�
	 * @throws IOException ��ȡ�ļ���Сʧ��ʱ�׳����쳣
	 */
	public long getSize() throws IOException {
		long l = 0L;
		for (int i = 0; i < m_counter; i++)
			l += getFile(i).getSize();

		return l;
	}

	/**
	 * ��ȡ�����е�Ԫ��
	 * @return �����еĸ�Ԫ�ص�ֵ
	 */
	public Collection getCollection() {
		return m_files.values();
	}

	/**
	 * ��ȡ�����е�Ԫ��
	 * @return �����еĸ�Ԫ�ص�ֵ
	 */
	public Enumeration getEnumeration() {
		return m_files.elements();
	}

}

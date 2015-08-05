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
 * <P>上载文件集合</P>
 * 
 * @version 0.1
 * @author navy
 */
public class UploadFiles {
	/**
	 * Upload 实例
	 */
	private Upload m_parent;

	/**
	 * 存储在载文件的哈希表
	 */
	private Hashtable m_files;

	/**
	 * 上载文件的个数
	 */
	private int m_counter;

	/**
	 * 实例化
	 */
	UploadFiles() {
		m_files = new Hashtable();
		m_counter = 0;
	}

	/**
	 * 添加新文件
	 * @param file UploadFile
	 */
	protected void addFile(UploadFile file) {
		if (file == null) {
			throw new IllegalArgumentException("不能上载空文件");
		} else {
			m_files.put(new Integer(m_counter), file);
			m_counter++;
			return;
		}
	}

	/**
	 * 从Hashtable集合中获取UploadFile对象
	 * @param i 文件在集合中的位置
	 * @return UploadFile对象
	 */
	public UploadFile getFile(int i) {
		if (i < 0)
			throw new IllegalArgumentException("文件位置不能小于0");
		UploadFile file = (UploadFile) m_files.get(new Integer(i));
		if (file == null)
			throw new IllegalArgumentException("文件不存在");
		else
			return file;
	}

	/**
	 * 获取上载文件的个数
	 * @return
	 */
	public int getCount() {
		return m_counter;
	}

	/**
	 * 上载文件的大小
	 * @return 上载的所有文件大小的总和
	 * @throws IOException 获取文件大小失败时抛出该异常
	 */
	public long getSize() throws IOException {
		long l = 0L;
		for (int i = 0; i < m_counter; i++)
			l += getFile(i).getSize();

		return l;
	}

	/**
	 * 获取集合中的元素
	 * @return 集合中的各元素的值
	 */
	public Collection getCollection() {
		return m_files.values();
	}

	/**
	 * 获取集合中的元素
	 * @return 集合中的各元素的值
	 */
	public Enumeration getEnumeration() {
		return m_files.elements();
	}

}

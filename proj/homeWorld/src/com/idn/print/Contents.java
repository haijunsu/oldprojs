/*
 * @(#)Contents.java  2003-9-1
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * <P>打印内容</P>
 * 
 * @version 0.1
 * @author navy
 */
public class Contents {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(Contents.class);

	/**
	 * 要打印内容的数组
	 */
	private String[][] m_arrayContents = null;

	/**
	 * 要打印内容的数组的表头
	 */
	private String m_title = null;

	/**
	 * 存放每一列属性的集合
	 */
	private Hashtable m_hashColumns = null;

	/**
	 * 未排序的显示顺序集合
	 */
	private ArrayList m_listSequence = null;
	
	/**
	 * 排序后的显示顺序集合
	 */
	private Integer[] m_sequence = null;

	/**
	 * 实例化
	 */
	public Contents() {
		m_hashColumns = new Hashtable();
		m_listSequence = new ArrayList();
	}

	/**
	 * 返回要打印列数总合
	 * @return 要打印列数总合
	 */
	public int getColumnCount() {
		if (m_hashColumns == null)
			return 0;
		return m_hashColumns.size();
	}
	
	/**
	 * 获取指定下标的元素的内容
	 * @param col 列
	 * @param row 行
	 * @return 指定下标的元素的内容
	 */
	public String getElement(int col, int row) {
		return m_arrayContents[col][row];
	}
	/**
	 * 要打印的行数
	 * @return 要打印的行数
	 */
	public int getRowCount() {
		if (m_arrayContents == null)
			return 0;
		return m_arrayContents.length;
	}

	/**
	 * 在集合中添加列
	 * @param column 列对象
	 */
	public void addColumn(Column column) {
		Integer nSeq = new Integer(column.getSeq());
		m_hashColumns.put(nSeq, column);
		m_listSequence.add(nSeq);
	}

	/**
	 * 获取指定的顺序列
	 * @param i 顺序号
	 * @return 顺序列
	 */
	public Column getColumn(int i) {
		if (m_sequence == null)
			m_sequence = getSequence();
		return getColumn(m_sequence[i]);
	}

	/**
	 * 获取指定的顺序列
	 * @param str 顺序号
	 * @return 顺序列
	 */
	private Column getColumn(String str) {
		return getColumn(new Integer(str));
	}

	/**
	 * 获取指定的顺序列
	 * @param i 顺序号
	 * @return 顺序列
	 */
	private Column getColumn(Integer i) {
		return (Column) m_hashColumns.get(i);
	}

	/**
	 * 获取列的顺序号的整型集合
	 * @return 列的顺序号的整型集合
	 */
	private Integer[] getSequence() {
		Integer[] nTemp =
			(Integer[]) m_listSequence.toArray(
				new Integer[m_listSequence.size()]);
		Arrays.sort (nTemp);
		return nTemp;
	}
	/**
	 * @return 输出内容
	 */
	public String[][] getArrayContents() {
		return m_arrayContents;
	}

	/**
	 * @return 输出的标题
	 */
	public String getTitle() {
		return m_title;
	}

	/**
	 * @param strings 输出内容
	 */
	public void setArrayContents(String[][] strings) {
		m_arrayContents = strings;
	}

	/**
	 * @param string 输出的标题
	 */
	public void setTitle(String string) {
		m_title = string;
	}

}

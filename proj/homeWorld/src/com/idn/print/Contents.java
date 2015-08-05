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
 * <P>��ӡ����</P>
 * 
 * @version 0.1
 * @author navy
 */
public class Contents {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(Contents.class);

	/**
	 * Ҫ��ӡ���ݵ�����
	 */
	private String[][] m_arrayContents = null;

	/**
	 * Ҫ��ӡ���ݵ�����ı�ͷ
	 */
	private String m_title = null;

	/**
	 * ���ÿһ�����Եļ���
	 */
	private Hashtable m_hashColumns = null;

	/**
	 * δ�������ʾ˳�򼯺�
	 */
	private ArrayList m_listSequence = null;
	
	/**
	 * ��������ʾ˳�򼯺�
	 */
	private Integer[] m_sequence = null;

	/**
	 * ʵ����
	 */
	public Contents() {
		m_hashColumns = new Hashtable();
		m_listSequence = new ArrayList();
	}

	/**
	 * ����Ҫ��ӡ�����ܺ�
	 * @return Ҫ��ӡ�����ܺ�
	 */
	public int getColumnCount() {
		if (m_hashColumns == null)
			return 0;
		return m_hashColumns.size();
	}
	
	/**
	 * ��ȡָ���±��Ԫ�ص�����
	 * @param col ��
	 * @param row ��
	 * @return ָ���±��Ԫ�ص�����
	 */
	public String getElement(int col, int row) {
		return m_arrayContents[col][row];
	}
	/**
	 * Ҫ��ӡ������
	 * @return Ҫ��ӡ������
	 */
	public int getRowCount() {
		if (m_arrayContents == null)
			return 0;
		return m_arrayContents.length;
	}

	/**
	 * �ڼ����������
	 * @param column �ж���
	 */
	public void addColumn(Column column) {
		Integer nSeq = new Integer(column.getSeq());
		m_hashColumns.put(nSeq, column);
		m_listSequence.add(nSeq);
	}

	/**
	 * ��ȡָ����˳����
	 * @param i ˳���
	 * @return ˳����
	 */
	public Column getColumn(int i) {
		if (m_sequence == null)
			m_sequence = getSequence();
		return getColumn(m_sequence[i]);
	}

	/**
	 * ��ȡָ����˳����
	 * @param str ˳���
	 * @return ˳����
	 */
	private Column getColumn(String str) {
		return getColumn(new Integer(str));
	}

	/**
	 * ��ȡָ����˳����
	 * @param i ˳���
	 * @return ˳����
	 */
	private Column getColumn(Integer i) {
		return (Column) m_hashColumns.get(i);
	}

	/**
	 * ��ȡ�е�˳��ŵ����ͼ���
	 * @return �е�˳��ŵ����ͼ���
	 */
	private Integer[] getSequence() {
		Integer[] nTemp =
			(Integer[]) m_listSequence.toArray(
				new Integer[m_listSequence.size()]);
		Arrays.sort (nTemp);
		return nTemp;
	}
	/**
	 * @return �������
	 */
	public String[][] getArrayContents() {
		return m_arrayContents;
	}

	/**
	 * @return ����ı���
	 */
	public String getTitle() {
		return m_title;
	}

	/**
	 * @param strings �������
	 */
	public void setArrayContents(String[][] strings) {
		m_arrayContents = strings;
	}

	/**
	 * @param string ����ı���
	 */
	public void setTitle(String string) {
		m_title = string;
	}

}

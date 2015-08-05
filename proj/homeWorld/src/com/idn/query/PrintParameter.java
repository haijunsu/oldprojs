/*
 * @(#)PrintParameter.java  2003-6-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

/**
 * <P>��ӡҳ��������</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PrintParameter {
	/**
	 * ÿ������
	 */
	private int lineWordCount = 0;
	
	/**
	 * ��һҳ����
	 */
	private int firstPageRows = 0;
	
	/**
	 * �ڶ�ҳ����
	 */
	private int otherPageRows = 0;
	/**
	 * @return ��һҳ����
	 */
	public int getFirstPageRows() {
		return firstPageRows;
	}

	/**
	 * @return ÿ�е�����
	 */
	public int getLineWordCount() {
		return lineWordCount;
	}

	/**
	 * @return ����ҳ������
	 */
	public int getOtherPageRows() {
		return otherPageRows;
	}

	/**
	 * @param i
	 */
	public void setFirstPageRows(int i) {
		firstPageRows = i;
	}

	/**
	 * @param i
	 */
	public void setLineWordCount(int i) {
		lineWordCount = i;
	}

	/**
	 * @param i
	 */
	public void setOtherPageRows(int i) {
		otherPageRows = i;
	}

}

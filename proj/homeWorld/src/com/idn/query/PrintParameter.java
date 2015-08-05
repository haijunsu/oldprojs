/*
 * @(#)PrintParameter.java  2003-6-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

/**
 * <P>打印页参数设置</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PrintParameter {
	/**
	 * 每行字数
	 */
	private int lineWordCount = 0;
	
	/**
	 * 第一页行数
	 */
	private int firstPageRows = 0;
	
	/**
	 * 第二页行数
	 */
	private int otherPageRows = 0;
	/**
	 * @return 第一页行数
	 */
	public int getFirstPageRows() {
		return firstPageRows;
	}

	/**
	 * @return 每行的字数
	 */
	public int getLineWordCount() {
		return lineWordCount;
	}

	/**
	 * @return 其它页的行数
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

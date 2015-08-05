/*
 * @(#)PageSize.java  2003-9-5
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.print;

/**
 * <P>定义纸的大小</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PageSize {
	
	/**
	 * 定义纸张大小的索引，参见PageTypes中的定义
	 */
	private int index = 0;
	
	/**
	 * 纸张的宽度
	 */
	private int width = 0;
	
	/**
	 * 纸张的高度
	 */
	private int height = 0;
	
	/**
	 * 初始化
	 */
	public PageSize() {
		super();
	}
	
	
	
	/**
	 * @return 纸张高度
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return 纸张编号
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return 纸张高度
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param i
	 */
	public void setHeight(int i) {
		height = i;
	}

	/**
	 * @param i
	 */
	public void setIndex(int i) {
		index = i;
	}

	/**
	 * @param i
	 */
	public void setWidth(int i) {
		width = i;
	}

}

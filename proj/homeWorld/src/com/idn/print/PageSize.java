/*
 * @(#)PageSize.java  2003-9-5
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.print;

/**
 * <P>����ֽ�Ĵ�С</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PageSize {
	
	/**
	 * ����ֽ�Ŵ�С���������μ�PageTypes�еĶ���
	 */
	private int index = 0;
	
	/**
	 * ֽ�ŵĿ��
	 */
	private int width = 0;
	
	/**
	 * ֽ�ŵĸ߶�
	 */
	private int height = 0;
	
	/**
	 * ��ʼ��
	 */
	public PageSize() {
		super();
	}
	
	
	
	/**
	 * @return ֽ�Ÿ߶�
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return ֽ�ű��
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return ֽ�Ÿ߶�
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

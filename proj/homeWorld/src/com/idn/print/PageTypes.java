/*
 * @(#)PageTypes.java  2003-9-5
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.print;

import java.util.Hashtable;

/**
 * <P>����ֽ������</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PageTypes {

	/**
	 * ��ֹ��ʼ��
	 */
	private PageTypes() {
		super();
	}
	
	/**
	 * ֽ�Ŵ�С�ļ���
	 */
	private static Hashtable hashPageSize = null;
	
	/**
	 * ��ʼ��ֽ�Ŵ�С
	 */
	private static void initPageSize() {
		hashPageSize = new Hashtable();
		PageSize psA4 = new PageSize();
		psA4.setIndex(PAGE_A4);
		psA4.setHeight(970);
		psA4.setWidth(640);
		hashPageSize.put(Integer.toString(PAGE_A4), psA4);
		
		PageSize psA4H = new PageSize();
		psA4H.setIndex(PAGE_A4_HORIZONTAL);
		psA4H.setHeight(640);
		psA4H.setWidth(970);
		hashPageSize.put(Integer.toString(PAGE_A4_HORIZONTAL), psA4H);

		PageSize psA3 = new PageSize();
		psA3.setIndex(PAGE_A3);
		psA3.setHeight(1440);
		psA3.setWidth(970);
		hashPageSize.put(Integer.toString(PAGE_A3), psA3);

		PageSize psA3H = new PageSize();
		psA3H.setIndex(PAGE_A3_HORIZONTAL);
		psA3H.setHeight(970);
		psA3H.setWidth(1440);
		hashPageSize.put(Integer.toString(PAGE_A3_HORIZONTAL), psA3H);
		
	}
	
	/**
	 * �Զ�ѡ��ֽ������
	 */
	public static int PAGE_AUTO = 0;
	
	/**
	 * A4ֽ
	 */
	public static int PAGE_A4 = 1;
	
	/**
	 * A4ֽ����
	 */
	public static int PAGE_A4_HORIZONTAL = 2;
	
	/**
	 * A3ֽ
	 */
	public static int PAGE_A3 = 3;
	
	/**
	 * A3ֽ����
	 */
	public static int PAGE_A3_HORIZONTAL = 4;
	
	/**
	 * ����ֽ�Ŵ�С����
	 * @param n ֽ�Ŵ�С��������
	 * @return ֽ�Ŵ�С����
	 */
	public static PageSize getPageSize(int n) {
		if (hashPageSize == null)
			initPageSize();
		return (PageSize)hashPageSize.get(Integer.toString(n));
	}
	
	/**
	 * ����ֽ�ſ��
	 * @param n ֽ�Ŵ�С��������
	 * @return ֽ�ſ��
	 */
	public static int getPageWidth(int n) {
		return getPageSize(n).getWidth();
	}
	
	/**
	 * ����ֽ�Ÿ߶�
	 * @param n ֽ�Ŵ�С��������
	 * @return ֽ�Ÿ߶�
	 */
	public static int getPageHeight(int n) {
		return getPageSize(n).getHeight();
	}
}

/*
 * @(#)PageTypes.java  2003-9-5
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.print;

import java.util.Hashtable;

/**
 * <P>定义纸张类型</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PageTypes {

	/**
	 * 禁止初始化
	 */
	private PageTypes() {
		super();
	}
	
	/**
	 * 纸张大小的集合
	 */
	private static Hashtable hashPageSize = null;
	
	/**
	 * 初始化纸张大小
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
	 * 自动选择纸张类型
	 */
	public static int PAGE_AUTO = 0;
	
	/**
	 * A4纸
	 */
	public static int PAGE_A4 = 1;
	
	/**
	 * A4纸横向
	 */
	public static int PAGE_A4_HORIZONTAL = 2;
	
	/**
	 * A3纸
	 */
	public static int PAGE_A3 = 3;
	
	/**
	 * A3纸横向
	 */
	public static int PAGE_A3_HORIZONTAL = 4;
	
	/**
	 * 返回纸张大小对象
	 * @param n 纸张大小的索引号
	 * @return 纸张大小定义
	 */
	public static PageSize getPageSize(int n) {
		if (hashPageSize == null)
			initPageSize();
		return (PageSize)hashPageSize.get(Integer.toString(n));
	}
	
	/**
	 * 返回纸张宽度
	 * @param n 纸张大小的索引号
	 * @return 纸张宽度
	 */
	public static int getPageWidth(int n) {
		return getPageSize(n).getWidth();
	}
	
	/**
	 * 返回纸张高度
	 * @param n 纸张大小的索引号
	 * @return 纸张高度
	 */
	public static int getPageHeight(int n) {
		return getPageSize(n).getHeight();
	}
}

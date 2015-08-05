/*
 * @(#)PageSize.java  2003-6-12
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

import java.sql.SQLException;
import java.util.Hashtable;

import com.idn.sql.DataBean;
import com.idn.sql.DynaSqlBean;

/**
 * <P> 设置纸张的属性 </P>
 * @deprecated 此类已经被PrintPageSetup, PrintParameter取代
 * @version 0.1
 * @author navy
 */
public class PageSize {
	
	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(PageSize.class);
	
	/**
	 * pageSize类的代码，默认为"34"
	 */
	private String pageSizeCode = null;
	
	/**
	 * page的类型，在代码"33"中定义
	 */
	private String pageType = null;
	
	/**
	 * 页大小，分为第一页和其它页的行数
	 */
	private int[] pageSize = null;
	
	/**
	 * 所有类型页的大小的集合
	 */
	private Hashtable htPageSize = null;
	
	/**
	 * 第一页显示的行数
	 */
	private int firstPageRows = 0;
	
	/**
	 * 第二页显示的行数
	 */
	private int otherPageRows = 0;
	
	/**
	 * 构造pageSize
	 */
	public PageSize() {
		pageSizeCode = "34";
		pageSize = null;
		firstPageRows = 0;
		otherPageRows = 0;
		pageType = null;
		htPageSize = null;
		initPage();
	}
	
	/**
	 * 构造pageSize,提供了指定的pageSize代码
	 */
	public PageSize(String strPageSizeCode) {
		pageSizeCode = strPageSizeCode;
		pageSize = null;
		firstPageRows = 0;
		otherPageRows = 0;
		pageType = null;
		htPageSize = null;
		initPage();
	}
	
	/**
	 * 构造pageSize,提供了指定的pageSize代码和纸张大小的代码
	 */
	public PageSize(String strPageSizeCode, String strPageType) {
		pageSizeCode = strPageSizeCode;
		pageSize = null;
		pageType = strPageType;
		firstPageRows = 0;
		otherPageRows = 0;
		htPageSize = null;
		initPage();
	}

	/**
	 * 初始化页属性
	 */
	private void initPage() {
		if (htPageSize != null && !log.isDebugEnabled())
			return;
		log.debug("init Hashtable");
		DynaSqlBean dsb = new DynaSqlBean();
		DataBean db = new DataBean();
		String strSql = "SELECT CCODE, CSHOWC, CSHOWE FROM CODES WHERE CCLASS = '" + pageSizeCode.trim() + "'";
		dsb.setSql(strSql);
		htPageSize = new Hashtable();
		try {
			db.setCrs(dsb.executeQuery());
			for (int i = 0; i < db.getRowCount(); i++) {
				pageSize = new int[2];
				pageSize[0] = Integer.parseInt(db.getFieldValue("CSHOWC", i).trim());
				pageSize[1] = Integer.parseInt(db.getFieldValue("CSHOWE", i).trim());
				htPageSize.put(db.getFieldValue("CCODE", i).trim(), pageSize);
			}
			if (pageType != null) {
				initPageRows();
			}
				
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			htPageSize = null;
		} catch (NumberFormatException ex) {
			log.error(ex.getMessage(), ex);
			htPageSize = null;
		}
	}
	
	private void initPageRows() {
		pageSize = (int[])htPageSize.get(pageType.trim());
		firstPageRows = pageSize[0];
		otherPageRows = pageSize[1];
	}

	/**
	 * @return 第一页显示的行数
	 */
	public int getFirstPageRows() {
		return firstPageRows;
	}

	/**
	 * @return 其它页显示的行数
	 */
	public int getOtherPageRows() {
		return otherPageRows;
	}

	/**
	 * @return 页的大小
	 */ 
	public int[] getPageSize() {
		return pageSize;
	}

	/**
	 * @return 页面大小的代码
	 */
	public String getPageSizeCode() {
		return pageSizeCode;
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
	public void setOtherPageRows(int i) {
		otherPageRows = i;
	}

	/**
	 * @param string
	 */
	public void setPageSize(int[] i) {
		pageSize = i;
	}

	/**
	 * @param string
	 */
	public void setPageSizeCode(String string) {
		pageSizeCode = string;
	}

	/**
	 * @return 页面大小的集合
	 */
	public Hashtable getHtPageSize() {
		return htPageSize;
	}

	/**
	 * @return 页面的类型
	 */
	public String getPageType() {
		return pageType;
	}

	/**
	 * @param hashtable
	 */
	public void setHtPageSize(Hashtable hashtable) {
		htPageSize = hashtable;
	}

	/**
	 * @param string
	 */
	public void setPageType(String string) {
		pageType = string;
		initPageRows();
	}

}

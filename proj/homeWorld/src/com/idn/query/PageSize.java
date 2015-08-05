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
 * <P> ����ֽ�ŵ����� </P>
 * @deprecated �����Ѿ���PrintPageSetup, PrintParameterȡ��
 * @version 0.1
 * @author navy
 */
public class PageSize {
	
	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(PageSize.class);
	
	/**
	 * pageSize��Ĵ��룬Ĭ��Ϊ"34"
	 */
	private String pageSizeCode = null;
	
	/**
	 * page�����ͣ��ڴ���"33"�ж���
	 */
	private String pageType = null;
	
	/**
	 * ҳ��С����Ϊ��һҳ������ҳ������
	 */
	private int[] pageSize = null;
	
	/**
	 * ��������ҳ�Ĵ�С�ļ���
	 */
	private Hashtable htPageSize = null;
	
	/**
	 * ��һҳ��ʾ������
	 */
	private int firstPageRows = 0;
	
	/**
	 * �ڶ�ҳ��ʾ������
	 */
	private int otherPageRows = 0;
	
	/**
	 * ����pageSize
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
	 * ����pageSize,�ṩ��ָ����pageSize����
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
	 * ����pageSize,�ṩ��ָ����pageSize�����ֽ�Ŵ�С�Ĵ���
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
	 * ��ʼ��ҳ����
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
	 * @return ��һҳ��ʾ������
	 */
	public int getFirstPageRows() {
		return firstPageRows;
	}

	/**
	 * @return ����ҳ��ʾ������
	 */
	public int getOtherPageRows() {
		return otherPageRows;
	}

	/**
	 * @return ҳ�Ĵ�С
	 */ 
	public int[] getPageSize() {
		return pageSize;
	}

	/**
	 * @return ҳ���С�Ĵ���
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
	 * @return ҳ���С�ļ���
	 */
	public Hashtable getHtPageSize() {
		return htPageSize;
	}

	/**
	 * @return ҳ�������
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

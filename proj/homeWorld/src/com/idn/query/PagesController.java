/*
 * @(#)ControlPages.java  2003-4-14
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package com.idn.query;

import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * <P>�������Ʒ�ҳ��ʾ�ĸ�������</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PagesController {
	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(PagesController.class);

	/**
	 * ��ѯ��URL
	 */
	private String baseUrl = null;

	/**
	 * ��ѯ��ǰ׺
	 */
	private String queryPrefix = null;

	/**
	 * Request����Ĳ��������ɷ�ҳ���ƴ���ʱ�Զ��ӵ�URL����
	 */
	private Hashtable requestParam = null;
	/**
	 * ��һ�κ�ÿ�����ӵķ���ҳ����������ʼֵΪ10
	 */
	private int pagesInterval = 10;

	/**
	 * ��ǰ��ʾ��ҳ�棬Ĭ��Ϊ1
	 */
	private int currentPage = 1;

	/**
	 * ��ǰ��ѯ������еĿɷֵ����ҳ��Ĭ��ֵΪ1
	 */
	private int maxPages = 1;

	/**
	 * ��ǰ�ѵõ��Ľ�����п�����ʾ�����ҳ��Ĭ��ֵΪ10
	 */
	private int currentMaxPages = 10;

	/**
	 * ÿҳ��ʾ��������Ĭ��ֵΪ0����ʾ��ʾ����ҳ
	 */
	private int rowsPerPage = 0;

	/**
	 * ��ǰ������������к�
	 */
	private int currentRowsCount = 0;

	/**
	 * ��ѯ������е�������
	 */
	private int realRowsCount = 0;

	/**
	 * �Ƿ񰴷�������ѯ
	 */
	private boolean isGroupCompute = false;

	/**
	 * �������������µķ�ҳ����
	 */
	private int[] pageBreak = null;

	/**
	 * ���� ControlPages����ʼ����������
	 */
	public PagesController() {
		currentPage = 1;
		maxPages = 1;
		currentMaxPages = 1;
		rowsPerPage = 0;
		currentRowsCount = 0;
		realRowsCount = 0;
		baseUrl = null;
		queryPrefix = null;
	}

	/**
	 * ����SELECT���������������
	 * @param rowsCount ��֪����������
	 */
	public void createPagesController(String rowsCount) throws QueryException {
		createPagesController(Integer.parseInt(rowsCount));
	}

	/**
	 * ������֪������������������
	 * @param rowsCount ��֪����������
	 */
	public void createPagesController(int rowsCount) {
		realRowsCount = rowsCount;
		this.maxPages =
			(int) java.lang.Math.ceil((double) realRowsCount / rowsPerPage);
		setCurrentPage(currentPage);
	}

	/**
	 * @return ���ص�ǰҳ����ʼ��
	 */
	public int getStartRow() {
		if (isGroupCompute() && rowsPerPage != 0)
			return pageBreak[currentPage - 1];
		return (currentPage - 1) * rowsPerPage;
	}

	/**
	 * @return ���ص�ǰҳ�Ľ������к�
	 */
	public int getEndRow() {
		if (rowsPerPage == 0)
			return realRowsCount;
		if (isGroupCompute())
			return pageBreak[currentPage];
		int iCount = getStartRow() + rowsPerPage;
		if (iCount > realRowsCount)
			return realRowsCount;
		return iCount;
	}

	/**
	 * ����ҳ���ϵķ�ҳ���ƴ���
	 * @return ��ҳ���ƴ���
	 */
	public String getControlPageCodes() {
		StringBuffer sb = new StringBuffer();
		sb.append("<form>").append("��&nbsp;").append(maxPages).append(
			"&nbsp;ҳ&nbsp;&nbsp;");
		if (currentPage > 1) {
			sb.append(createHref(currentPage - 1, "��һҳ"));
		}
		sb.append("��ת&nbsp;<select size=\"1\"").append(
			" name=\"jumpOption\" ").append(
			"onChange=\"window.location=jumpOption.value\">");
		for (int i = 1; i <= maxPages; i++) {
			if (i == currentPage)
				sb.append("<option selected value=\"");
			else
				sb.append("<option value=\"");
			sb
				.append(baseUrl)
				.append("?")
				.append(parseQueryParam())
				.append("nextPage=")
				.append(i)
				.append("\">")
				.append(i)
				.append("</option>");
		}
		sb.append("</select>&nbsp;&nbsp;");
		if (currentPage < maxPages) {
			sb.append(createHref(currentPage + 1, "��һҳ"));
		}
		sb.append("</form>");
		return sb.toString();
	}

	private String createHref(int i, String title) {
		StringBuffer sb = new StringBuffer();
		sb
			.append("<A href=\"")
			.append(baseUrl)
			.append("?")
			.append(parseQueryParam())
			.append("nextPage=")
			.append(i)
			.append("\" title=\"")
			.append(title)
			.append("\">")
			.append(title)
			.append("</A>&nbsp;&nbsp;");
		return sb.toString();
	}

	/**
	 * ���ز�ѯ�����в�����ɵ�URL����ƴ����Ϊ�����ӵ�URL����
	 * @return ��ѯ�����в�����ɵ�URL
	 */
	public String getQueryParametersURL() {
		return parseQueryParam();
	}
	/**
	 * ����URL�еĲ���
	 * @return URL�еĲ���
	 */
	private String parseQueryParam() {
		StringBuffer sb = new StringBuffer();
		Enumeration enum = requestParam.keys();
		while (enum.hasMoreElements()) {
			String key = (String) enum.nextElement();
			sb
				.append(key)
				.append("=")
				.append(URLEncoder.encode((String) requestParam.get(key)))
				.append("&");
		}
		return sb.toString();
	}

	/**
	 * ���ز���
	 * @return ��ѯ����
	 */
	public String getQueryParam() {
		return parseQueryParam();
	}
	/**
	 * @return ���ص�ǰ�ѵõ��Ľ�����п�����ʾ�����ҳ
	 */
	public int getCurrentMaxPages() {
		return currentMaxPages;
	}

	/**
	 * @return ���ص�ǰ��ʾ��ҳ��
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return ���ص�ǰ��ѯ������еĿɷֵ����ҳ
	 */
	public int getMaxPages() {
		return maxPages;
	}

	/**
	 * @return ����ÿҳ��ʾ������
	 */
	public int getRowsPerPage() {
		return rowsPerPage;
	}

	/**
	 * @param i ���õ�ǰ�ѵõ��Ľ�����п�����ʾ�����ҳ
	 */
	public void setCurrentMaxPages(int i) {
		currentMaxPages = i;
		if (currentMaxPages > maxPages)
			currentMaxPages = maxPages;
		int rowsCount = currentMaxPages * rowsPerPage;
		if (rowsCount > realRowsCount)
			setCurrentRowsCount(realRowsCount);
		else
			setCurrentRowsCount(rowsCount);
	}

	/**
	 * @param i ���õ�ǰ��ʾ��ҳ��
	 */
	public void setCurrentPage(int i) {
		if (i > maxPages)
			i = maxPages;
		currentPage = i;
		if (i <= pagesInterval && currentMaxPages <= pagesInterval) {
			setCurrentMaxPages(pagesInterval);
		} else {
			if (i > currentMaxPages)
				setCurrentMaxPages(i + pagesInterval);
		}
	}

	/**
	 * @param i ���õ�ǰ��ѯ������еĿɷֵ����ҳ
	 */
	public void setMaxPages(int i) {
		maxPages = i;
	}

	/**
	 * @param i ����ÿҳ��ʾ������
	 */
	public void setRowsPerPage(int i) {
		if (i < 0)
			i = 0;
		rowsPerPage = i;
	}

	/**
	 * @return ��ǰ������Ĵ�С
	 */
	public int getCurrentRowsCount() {
		return currentRowsCount;
	}

	/**
	 * @return ��ѯ������Ĵ�С
	 */
	public int getRealRowsCount() {
		return realRowsCount;
	}

	/**
	 * @param i
	 */
	public void setCurrentRowsCount(int i) {
		currentRowsCount = i;
	}

	/**
	 * @param i
	 */
	public void setRealRowsCount(int i) {
		realRowsCount = i;
	}

	/**
	 * @return ��ѯ��URL
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @return ��ѯ����
	 */
	public String getQueryPrefix() {
		return queryPrefix;
	}

	/**
	 * @param string
	 */
	public void setBaseUrl(String string) {
		baseUrl = string;
	}

	/**
	 * @param string
	 */
	public void setQueryPrefix(String string) {
		queryPrefix = string;
	}

	/**
	 * @return ÿ�η��ص�ҳ��������
	 */
	public int getPagesInterval() {
		return pagesInterval;
	}

	/**
	 * @param i
	 */
	public void setPagesInterval(int i) {
		pagesInterval = i;
		if (currentPage == 1)
			currentMaxPages = i;
	}

	/**
	 * @return �����Ĺ�ϣ��
	 */
	public Hashtable getRequestParam() {
		return requestParam;
	}

	/**
	 * @param hashtable
	 */
	public void setRequestParam(Hashtable hashtable) {
		requestParam = hashtable;
	}

	/**
	 * @return �Ƿ�Ϊ��������ѯ
	 */
	public boolean isGroupCompute() {
		return isGroupCompute;
	}

	/**
	 * @return ��������ѯ��ҳ��
	 */
	public int[] getPageBreak() {
		return pageBreak;
	}

	/**
	 * @param b
	 */
	public void setGroupCompute(boolean b) {
		isGroupCompute = b;
	}

	/**
	 * @param is
	 */
	public void setPageBreak(int[] is) {
		pageBreak = is;
	}

}

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
 * <P>用来控制分页显示的各种属性</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PagesController {
	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(PagesController.class);

	/**
	 * 查询的URL
	 */
	private String baseUrl = null;

	/**
	 * 查询的前缀
	 */
	private String queryPrefix = null;

	/**
	 * Request请求的参数，生成分页控制代码时自动加到URL后面
	 */
	private Hashtable requestParam = null;
	/**
	 * 第一次和每次增加的返回页的数量，初始值为10
	 */
	private int pagesInterval = 10;

	/**
	 * 当前显示的页面，默认为1
	 */
	private int currentPage = 1;

	/**
	 * 当前查询结果集中的可分的最大页，默认值为1
	 */
	private int maxPages = 1;

	/**
	 * 当前已得到的结果集中可以显示的最大页，默认值为10
	 */
	private int currentMaxPages = 10;

	/**
	 * 每页显示的条数，默认值为0，表示显示不分页
	 */
	private int rowsPerPage = 0;

	/**
	 * 当前结果集中最大的行号
	 */
	private int currentRowsCount = 0;

	/**
	 * 查询结果集中的总条数
	 */
	private int realRowsCount = 0;

	/**
	 * 是否按分组计算查询
	 */
	private boolean isGroupCompute = false;

	/**
	 * 分组计算后生成新的分页数组
	 */
	private int[] pageBreak = null;

	/**
	 * 构造 ControlPages，初始化所有属性
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
	 * 根据SELECT语句来创建控制器
	 * @param rowsCount 已知的最大的行数
	 */
	public void createPagesController(String rowsCount) throws QueryException {
		createPagesController(Integer.parseInt(rowsCount));
	}

	/**
	 * 根据已知的行数来建立控制器
	 * @param rowsCount 已知的最大的行数
	 */
	public void createPagesController(int rowsCount) {
		realRowsCount = rowsCount;
		this.maxPages =
			(int) java.lang.Math.ceil((double) realRowsCount / rowsPerPage);
		setCurrentPage(currentPage);
	}

	/**
	 * @return 返回当前页的起始行
	 */
	public int getStartRow() {
		if (isGroupCompute() && rowsPerPage != 0)
			return pageBreak[currentPage - 1];
		return (currentPage - 1) * rowsPerPage;
	}

	/**
	 * @return 返回当前页的结束的行号
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
	 * 生成页面上的分页控制代码
	 * @return 分页控制代码
	 */
	public String getControlPageCodes() {
		StringBuffer sb = new StringBuffer();
		sb.append("<form>").append("共&nbsp;").append(maxPages).append(
			"&nbsp;页&nbsp;&nbsp;");
		if (currentPage > 1) {
			sb.append(createHref(currentPage - 1, "上一页"));
		}
		sb.append("跳转&nbsp;<select size=\"1\"").append(
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
			sb.append(createHref(currentPage + 1, "下一页"));
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
	 * 返回查询中所有参数组成的URL，可拼接做为新连接的URL参数
	 * @return 查询中所有参数组成的URL
	 */
	public String getQueryParametersURL() {
		return parseQueryParam();
	}
	/**
	 * 分析URL中的参数
	 * @return URL中的参数
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
	 * 返回参数
	 * @return 查询参数
	 */
	public String getQueryParam() {
		return parseQueryParam();
	}
	/**
	 * @return 返回当前已得到的结果集中可以显示的最大页
	 */
	public int getCurrentMaxPages() {
		return currentMaxPages;
	}

	/**
	 * @return 返回当前显示的页面
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return 返回当前查询结果集中的可分的最大页
	 */
	public int getMaxPages() {
		return maxPages;
	}

	/**
	 * @return 返回每页显示的条数
	 */
	public int getRowsPerPage() {
		return rowsPerPage;
	}

	/**
	 * @param i 设置当前已得到的结果集中可以显示的最大页
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
	 * @param i 设置当前显示的页面
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
	 * @param i 设置当前查询结果集中的可分的最大页
	 */
	public void setMaxPages(int i) {
		maxPages = i;
	}

	/**
	 * @param i 设置每页显示的条数
	 */
	public void setRowsPerPage(int i) {
		if (i < 0)
			i = 0;
		rowsPerPage = i;
	}

	/**
	 * @return 当前结果集的大小
	 */
	public int getCurrentRowsCount() {
		return currentRowsCount;
	}

	/**
	 * @return 查询结果集的大小
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
	 * @return 查询的URL
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @return 查询名称
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
	 * @return 每次返回的页数的增量
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
	 * @return 参数的哈希表
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
	 * @return 是否为分组计算查询
	 */
	public boolean isGroupCompute() {
		return isGroupCompute;
	}

	/**
	 * @return 分组计算查询分页器
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

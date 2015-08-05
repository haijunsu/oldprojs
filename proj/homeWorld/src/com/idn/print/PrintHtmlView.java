/*
 * @(#)PrintView.java  2003-9-1
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.print;

import com.idn.property.SystemProperties;

/**
 * <P>生成可打印页面</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PrintHtmlView {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(PrintHtmlView.class);

	/**
	 * 表格通用属性
	 */
	private final String TABLE_COMMON_STYLE =
		"cellSpacing=\"0\" cellPadding=\"0\" border=\"1\"";

	/**
	 * 打印分页标记
	 */
	private String PAGE_BREAK =
		"<br clear=all style='page-break-before:always'>";

	/**
	 * 可供打印的字体大小
	 */
	private int[] FONT_SIZES = new int[] { 9, 12, 14, 16, 18, 24 };

	/**
	 * 表格格式类
	 */
	private String m_tableStyle = "printTable";

	/**
	 * 表格标题格式类
	 */
	private String m_tableHeaderStyle = "printTableHeaderStyle";

	/**
	 * 表格标题格式类
	 */
	private String m_tdStyle = "printTd";

	/**
	 * 要存入Excel的内容
	 */
	private Contents m_contents = null;

	/**
	 * 生成的HTML显示内容
	 */
	private String m_htmlContents = null;

	/**
	 * 各列的最长值
	 */
	private int[] m_columnLength = null;

	/**
	 * 打印纸类型
	 */
	private int m_pageType = 0;

	/**
	 * 字体大小，单位为px
	 */
	private int m_fontSize = 14;

	/**
	 * 单行字数
	 */
	private int m_lineWordCount = 0;

	/**
	 * 是否仅供显示
	 */
	private boolean isDisplayOnly = false;

	/**
	 * 实例化
	 * @param contents
	 */
	public PrintHtmlView(Contents contents) {
		m_contents = contents;
		m_htmlContents = null;
	}

	/**
	 * 将原始内容生成网页代码
	 * @exception 生成HTML代码出错时抛出该异常
	 */
	public void renderHtml() throws PrintException {
		renderHtml(m_contents);
	}

	/**
	 * 将原始内容生成网页代码
	 * @param m_contents 内容
	 * @exception 生成HTML代码出错时抛出该异常
	 */
	private void renderHtml(Contents contents) throws PrintException {
		if (contents == null) {
			log.error("没有内容可以供显示！请先初始化m_contents.");
			throw new PrintException("没有内容可供显示！");
		}

		//初始化每列的长度
		initColumnLength();

		//选择纸张
		selectPage();

		//选择字体大小
		selectFontSize();

		//计算页数
		int nRowHeight = m_fontSize + 4;
		//首页行数
		int nFirstRowsCount =
			(int) Math.floor(
				(PageTypes.getPageHeight(m_pageType) - 75.0 - nRowHeight)
					/ nRowHeight);
		int nOtherRowsCount =
			(int) Math.floor(
				(PageTypes.getPageHeight(m_pageType) - nRowHeight)
					/ nRowHeight);

		int nPageNumber = 1;
		if (contents.getRowCount() > nFirstRowsCount) {
			nPageNumber
				+= (int) Math.ceil(
					(double) (contents.getRowCount() - nFirstRowsCount)
						/ nOtherRowsCount);
		}

		//生成HTML代码
		StringBuffer sb = new StringBuffer();

		//开始空无边界表格
		sb.append("<div align=\"center\">").append(
			SystemProperties.LINE_SEPARATOR);
		//		if (isDisplayOnly()) {
		sb.append("<H2>").append(contents.getTitle()).append("</H2>");
		//		} else {
		//			sb
		//				.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"")
		//				.append("style=\"border-collapse: collapse\" width=\"")
		//				.append(PageTypes.getPageWidth(m_pageType))
		//				.append("\">")
		//				.append(SystemProperties.LINE_SEPARATOR)
		//				.append("	<tr>")
		//				.append(SystemProperties.LINE_SEPARATOR)
		//				.append("		<td><H2>")
		//				.append(contents.getTitle())
		//				.append("</H2>")
		//				.append(SystemProperties.LINE_SEPARATOR)
		//				.append("		</td>")
		//				.append(SystemProperties.LINE_SEPARATOR)
		//				.append("	</tr>")
		//				.append(SystemProperties.LINE_SEPARATOR)
		//				.append("	<tr>")
		//				.append(SystemProperties.LINE_SEPARATOR)
		//				.append("		<td>");
		//		}
		//
		//写数据表格头
		sb
			.append("<table ")
			.append(TABLE_COMMON_STYLE)
			.append(" class=\"")
			.append(m_tableStyle)
			.append("\" ");
		if (isDisplayOnly()) {
			sb.append("width=\"100%\">");
		} else {
			sb.append("width=\"").append(
				PageTypes.getPageWidth(m_pageType) - 2).append(
				"\">");
		}
		writeTableHeader(contents);
		for (int i = 0; i < contents.getRowCount(); i++) {
			//写数据表格表体
			
			sb.append("<tr>").append(SystemProperties.LINE_SEPARATOR);
			for (int j = 0; j < m_columnLength.length; j++) {
				if (contents.getColumn(j).isShow()) {
					sb
						.append("<td nowrap ");
					if (contents.getColumn(j).isInt() || contents.getColumn(j).isDecimal())
						sb.append("align=\"right\" ");
						
					sb
						.append("width=\"")
						.append(m_columnLength[j] * m_fontSize)
						.append("\" height=\"")
						.append(m_fontSize + 4)
						.append("\" class=\"")
						.append(m_tdStyle)
						.append("\"><p class=\"p")
						.append(m_fontSize)
						.append("\">")
						.append(contents.getElement(i, contents.getColumn(j).getIndex()))
						.append("<td>")
						.append(SystemProperties.LINE_SEPARATOR);
				}
			}
			sb.append("</tr>").append(SystemProperties.LINE_SEPARATOR);
			

			if ((i == nFirstRowsCount
				|| (((i - nFirstRowsCount) % nOtherRowsCount == 0)
					&& i > nFirstRowsCount))
				&& !isDisplayOnly()
				&& (i != contents.getRowCount() - 1)) {
				//结束表格
				sb.append("</table>").append(SystemProperties.LINE_SEPARATOR);

				//添加分页符
				sb.append(PAGE_BREAK).append(SystemProperties.LINE_SEPARATOR);
				
				//开始新的表格
				sb
					.append("<table ")
					.append(TABLE_COMMON_STYLE)
					.append(" class=\"")
					.append(m_tableStyle)
					.append("\" ");
				if (isDisplayOnly()) {
					sb.append("width=\"100%\">");
				} else {
					sb.append("width=\"").append(
						PageTypes.getPageWidth(m_pageType) - 2).append(
						"\">");
				}
				writeTableHeader(contents);
			}
		}
		sb.append("</table>").append(SystemProperties.LINE_SEPARATOR);
		sb.append("<div>").append(SystemProperties.LINE_SEPARATOR);
		m_htmlContents = sb.toString();
	}

	private String writeTableHeader(Contents contents) {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>").append(SystemProperties.LINE_SEPARATOR);
		for (int i = 0; i < m_columnLength.length; i++) {
			if (contents.getColumn(i).isShow()) {
				sb
					.append("<td nowrap ")
					.append("width=\"")
					.append(m_columnLength[i] * m_fontSize)
					.append("\" height=\"")
					.append(m_fontSize + 4)
					.append("\" class=\"")
					.append(m_tableHeaderStyle)
					.append("\"><p class=\"p")
					.append(m_fontSize)
					.append("\">")
					.append(contents.getColumn(i).getLabel())
					.append("<td>")
					.append(SystemProperties.LINE_SEPARATOR);
			}
		}
		sb.append("</tr>").append(SystemProperties.LINE_SEPARATOR);
		return sb.toString();
	}

	/**
	 * 初始化数据中每一列的长度
	 */
	private void initColumnLength() {
		m_columnLength = new int[m_contents.getColumnCount()];
		for (int i = 0; i < m_columnLength.length; i++) {
			m_columnLength[i] =
				m_contents.getColumn(i).getLabel().getBytes().length;
		}

		int nTemp = 0;
		int k = 0;

		//查找每一列的最大长度
		for (int i = 0; i < m_columnLength.length; i++) {
			for (int j = 0; j < m_contents.getColumnCount(); j++) {
				k = m_contents.getColumn(j).getIndex();
				nTemp = m_contents.getElement(i, k).getBytes().length;
				if (m_columnLength[j] < nTemp)
					m_columnLength[j] = nTemp;
			}
		}
	}

	/**
	 * 选择纸张大小
	 */
	private void selectPage() {
		//仅显示，不用考虑大小
		if (isDisplayOnly)
			return;

		//用户已经指定大小
		if (m_pageType != 0)
			return;

		//没有指定大小
		//计算每行的字数
		for (int i = 0; i < m_columnLength.length; i++) {
			m_lineWordCount = m_columnLength[i];
		}

		//首先从A4开始查找
		m_pageType = 1;
		//每一行要占用的px值
		int nTempLength =
			m_lineWordCount * m_fontSize + m_contents.getColumnCount() + 4;
		//纸张可容纳的最大宽度px	
		int nTempPageWidth = PageTypes.getPageWidth(m_pageType);
		//找纸类型
		if (nTempLength > nTempPageWidth) {
			m_pageType++;
			while (m_pageType < 5) {
				nTempPageWidth = PageTypes.getPageWidth(m_pageType);
				if (nTempLength < nTempPageWidth) {
					return;
				}
				m_pageType++;
			}
		}

	}

	/**
	 * 根据纸张大小，选择适合的字体，字体最小为9px
	 */
	private void selectFontSize() {
		//如果字体大小合适，则不再选择
		int nTempLength =
			m_lineWordCount * m_fontSize + m_contents.getColumnCount() + 4;
		int nTempPageWidth = PageTypes.getPageWidth(m_pageType);
		if (nTempLength > nTempPageWidth) {
			for (int i = 0; i < FONT_SIZES.length; i++) {
				m_fontSize = FONT_SIZES[i];
				nTempLength =
					m_lineWordCount * m_fontSize
						+ m_contents.getColumnCount()
						+ 4;
				if (nTempLength > nTempPageWidth) {
					if (i > 0)
						m_fontSize = FONT_SIZES[i - 1];
				}
			}
		}
	}

	/**
	 * @return HTML表示的内容
	 */
	public String getHtmlContents() {
		if (m_htmlContents == null) {
			try {
				renderHtml();
			} catch (PrintException e) {
				log.error(e.getMessage(), e);
			}
		}
		return m_htmlContents;
	}

	/**
	 * @param contents 内容
	 */
	public void setContents(Contents contents) {
		m_contents = contents;
	}

	/**
	 * @return 是否公供显示
	 */
	public boolean isDisplayOnly() {
		return isDisplayOnly;
	}

	/**
	 * @return 各列的长度
	 */
	public int[] getColumnLength() {
		return m_columnLength;
	}

	/**
	 * @return 纸张类型
	 */
	public int getPageType() {
		return m_pageType;
	}

	/**
	 * @return 表头类型
	 */
	public String getTableHeaderStyle() {
		return m_tableHeaderStyle;
	}

	/**
	 * @return 表格类型
	 */
	public String getTableStyle() {
		return m_tableStyle;
	}

	/**
	 * @return 表格单元类型
	 */
	public String getTdStyle() {
		return m_tdStyle;
	}

	/**
	 * @param b
	 */
	public void setDisplayOnly(boolean b) {
		isDisplayOnly = b;
	}

	/**
	 * @param is
	 */
	public void setColumnLength(int[] is) {
		m_columnLength = is;
	}

	/**
	 * @param i
	 */
	public void setPageType(int i) {
		m_pageType = i;
	}

	/**
	 * @param string
	 */
	public void setTableHeaderStyle(String string) {
		m_tableHeaderStyle = string;
	}

	/**
	 * @param string
	 */
	public void setTableStyle(String string) {
		m_tableStyle = string;
	}

	/**
	 * @param string
	 */
	public void setTdStyle(String string) {
		m_tdStyle = string;
	}

}

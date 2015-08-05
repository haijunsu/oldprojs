/*
 * @(#)PrintView.java  2003-9-1
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.print;

import com.idn.property.SystemProperties;

/**
 * <P>���ɿɴ�ӡҳ��</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PrintHtmlView {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(PrintHtmlView.class);

	/**
	 * ���ͨ������
	 */
	private final String TABLE_COMMON_STYLE =
		"cellSpacing=\"0\" cellPadding=\"0\" border=\"1\"";

	/**
	 * ��ӡ��ҳ���
	 */
	private String PAGE_BREAK =
		"<br clear=all style='page-break-before:always'>";

	/**
	 * �ɹ���ӡ�������С
	 */
	private int[] FONT_SIZES = new int[] { 9, 12, 14, 16, 18, 24 };

	/**
	 * ����ʽ��
	 */
	private String m_tableStyle = "printTable";

	/**
	 * �������ʽ��
	 */
	private String m_tableHeaderStyle = "printTableHeaderStyle";

	/**
	 * �������ʽ��
	 */
	private String m_tdStyle = "printTd";

	/**
	 * Ҫ����Excel������
	 */
	private Contents m_contents = null;

	/**
	 * ���ɵ�HTML��ʾ����
	 */
	private String m_htmlContents = null;

	/**
	 * ���е��ֵ
	 */
	private int[] m_columnLength = null;

	/**
	 * ��ӡֽ����
	 */
	private int m_pageType = 0;

	/**
	 * �����С����λΪpx
	 */
	private int m_fontSize = 14;

	/**
	 * ��������
	 */
	private int m_lineWordCount = 0;

	/**
	 * �Ƿ������ʾ
	 */
	private boolean isDisplayOnly = false;

	/**
	 * ʵ����
	 * @param contents
	 */
	public PrintHtmlView(Contents contents) {
		m_contents = contents;
		m_htmlContents = null;
	}

	/**
	 * ��ԭʼ����������ҳ����
	 * @exception ����HTML�������ʱ�׳����쳣
	 */
	public void renderHtml() throws PrintException {
		renderHtml(m_contents);
	}

	/**
	 * ��ԭʼ����������ҳ����
	 * @param m_contents ����
	 * @exception ����HTML�������ʱ�׳����쳣
	 */
	private void renderHtml(Contents contents) throws PrintException {
		if (contents == null) {
			log.error("û�����ݿ��Թ���ʾ�����ȳ�ʼ��m_contents.");
			throw new PrintException("û�����ݿɹ���ʾ��");
		}

		//��ʼ��ÿ�еĳ���
		initColumnLength();

		//ѡ��ֽ��
		selectPage();

		//ѡ�������С
		selectFontSize();

		//����ҳ��
		int nRowHeight = m_fontSize + 4;
		//��ҳ����
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

		//����HTML����
		StringBuffer sb = new StringBuffer();

		//��ʼ���ޱ߽���
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
		//д���ݱ��ͷ
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
			//д���ݱ�����
			
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
				//�������
				sb.append("</table>").append(SystemProperties.LINE_SEPARATOR);

				//��ӷ�ҳ��
				sb.append(PAGE_BREAK).append(SystemProperties.LINE_SEPARATOR);
				
				//��ʼ�µı��
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
	 * ��ʼ��������ÿһ�еĳ���
	 */
	private void initColumnLength() {
		m_columnLength = new int[m_contents.getColumnCount()];
		for (int i = 0; i < m_columnLength.length; i++) {
			m_columnLength[i] =
				m_contents.getColumn(i).getLabel().getBytes().length;
		}

		int nTemp = 0;
		int k = 0;

		//����ÿһ�е���󳤶�
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
	 * ѡ��ֽ�Ŵ�С
	 */
	private void selectPage() {
		//����ʾ�����ÿ��Ǵ�С
		if (isDisplayOnly)
			return;

		//�û��Ѿ�ָ����С
		if (m_pageType != 0)
			return;

		//û��ָ����С
		//����ÿ�е�����
		for (int i = 0; i < m_columnLength.length; i++) {
			m_lineWordCount = m_columnLength[i];
		}

		//���ȴ�A4��ʼ����
		m_pageType = 1;
		//ÿһ��Ҫռ�õ�pxֵ
		int nTempLength =
			m_lineWordCount * m_fontSize + m_contents.getColumnCount() + 4;
		//ֽ�ſ����ɵ������px	
		int nTempPageWidth = PageTypes.getPageWidth(m_pageType);
		//��ֽ����
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
	 * ����ֽ�Ŵ�С��ѡ���ʺϵ����壬������СΪ9px
	 */
	private void selectFontSize() {
		//��������С���ʣ�����ѡ��
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
	 * @return HTML��ʾ������
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
	 * @param contents ����
	 */
	public void setContents(Contents contents) {
		m_contents = contents;
	}

	/**
	 * @return �Ƿ񹫹���ʾ
	 */
	public boolean isDisplayOnly() {
		return isDisplayOnly;
	}

	/**
	 * @return ���еĳ���
	 */
	public int[] getColumnLength() {
		return m_columnLength;
	}

	/**
	 * @return ֽ������
	 */
	public int getPageType() {
		return m_pageType;
	}

	/**
	 * @return ��ͷ����
	 */
	public String getTableHeaderStyle() {
		return m_tableHeaderStyle;
	}

	/**
	 * @return �������
	 */
	public String getTableStyle() {
		return m_tableStyle;
	}

	/**
	 * @return ���Ԫ����
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

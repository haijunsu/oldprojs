/*
 * @(#)QueryContents.java  2003-4-8
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import jxl.format.Border;
import jxl.format.BorderLineStyle;

import com.idn.property.PropertyManager;
import com.idn.sql.DataBean;
import com.idn.sql.DynaSqlBean;
import com.idn.util.CommonTools;
import com.idn.util.DecimalTools;
import com.idn.util.FormatDate;
import com.idn.util.MissingParameterException;

/**
 * <P>�����ѯ��ʾ����</P>
 * 
 * @version 0.1
 * @author �պ���
 */
public class QueryContents {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(QueryContents.class);

	/**
	 * ҳ����ʾ�Ļ�������
	 * <pre>
	 * 		GRID - ��Grid�ķ�ʽ�����ѯ����
	 * 		FREEFORM - ��FreeForm�ķ�ʽ�����ѯ����
	 * 		ROLL - �Թ������ķ�ʽ�����ѯ����
	 * </pre>
	 */
	private final String[] displayTypes =
		new String[] { "GRID", "FREEFORM", "ROLL" };

	/**
	 * ��ʾ������
	 */
	private String resultHtml = null;

	/**
	 * ��ӡҳ�������Ϣ
	 */
	private PrintPageSetup printPageSetup = null;

	/**
	 * �ɴ�ӡ����Ĵ�С
	 */
	private String printAbleFontSize = null;

	/**
	 * ÿ��Ҫ��ʾ�Ŀ��
	 */
	private int[] columnDisplaySize = null;

	/**
	 * �����ֶΣ���FORMBEAN��ֵʱ�����ı�������ͬ�������ڲ�ѯʱ����ʾ����
	 */
	private final int FORM_HIDDEN = 0;

	/**
	 * �ı��ֶΣ�����TEXT��TEXTAREA��PASSWORD�����ַ���FORMBEAN��ֵ
	 */
	private final int FORM_TEXT = 1;

	/**
	 * ��ѡ��ť�ֶΣ�����RADIO��CHECKBOX����FORMBEAN
	 * ��ֵʱ������SELECT��LABEL��VALUES�������ԣ�SELECT��LBAEL��VALUES
	 * Ϊһά���飬LBAEL��VALUES����ֵһ�Σ�SELECTΪ��ѯ����
	 */
	private final int FORM_SINGLE_CHECKBOX = 2;

	/**
	 * ��ѡ��ť�ֶΣ�����CHECKBOX��MULTIBOX�����ֶα���Ϊ���룬��FORMBEAN
	 * ��ֵʱ������SELECT��LABEL��VALUES�������ԣ�����SELECTΪ��ά���飬
	 * �ϱ�Ϊ�кţ��±�Ϊʵ�ʵ�ֵ��ͨ����λ������ó���LBAEL��VALUES
	 * Ϊһά���飬����ֵһ�Σ�SELECTΪ��ѯ����
	 */
	private final int FORM_MULTIBOX = 3;

	/**
	 * ��ѡ�򣬰���OPTION��OPTIONCOLLECTION��SELECT����FORMBEAN
	 * ��ֵʱ������SELECT��COLLECTION�������ԣ�COLLECTION������ֵһ�Σ�SELECTΪ��ѯ����
	 */
	private final int FORM_SINGLE_SELECT = 4;

	/**
	 * ��ѡ�򣬰���OPTION��OPTIONS��OPTIONCOLLECTION��SELECT��
	 * ���ֶα���Ϊ���룬��FORMBEAN��ֵʱ��
	 * ����SELECT��COLLECTION�������ԣ�����SELECTΪ��ά���飬
	 * �ϱ�Ϊ�кţ��±�Ϊʵ�ʵ�ֵ��ͨ����λ������ó���
	 * COLLECTION����ֵһ�Σ�SELECTΪ��ѯ����
	 * 
	 */
	private final int FORM_SELECT = 5;

	/**
	 * ����Bean����Ҫ�������������е�����
	 */
	private DataBean db = null;

	/**
	 * �����ֶε���ʾ��ǩ��������
	 */
	private String[] fieldLabel = null;

	/**
	 * HTMLҳ��ҳü����
	 */
	private String htmlTitle = null;

	/**
	 * HTMLҳ��ҳ����
	 */
	private String htmlHeader = null;

	/**
	 * ���ͨ������
	 */
	private String tableCommStyle =
		"cellSpacing=\"0\" cellPadding=\"0\" border=\"1\"";

	/**
	 * ���ͨ������
	 */
	private String tablePrintCommStyle =
		"cellPadding=\"2\" border=\"1\" style=\"border-collapse: collapse\" bordercolor=\"#111111\"";

	/**
	 * ��ӡ��ҳ���
	 */
	private String pageBreak =
		"<br clear=all style='page-break-before:always'>";

	/**
	 * ����ʽ��
	 */
	private String tableStyle = null;

	/**
	 * �������ʽ��
	 */
	private String tableHeaderStyle = null;

	/**
	 * �����������Ԫ���ʽ��
	 */
	private String groupTdStyle = null;

	/**
	 * ������������ֵ�Ԫ���ʽ��
	 */
	private String groupDigitTdStyle = null;

	/**
	 * ��ϼƼ�������Ԫ���ʽ��
	 */
	private String sumTdStyle = null;

	/**
	 * ��ϼƼ��������ֵ�Ԫ���ʽ��
	 */
	private String sumDigitTdStyle = null;

	/**
	 * ��Ԫ���ʽ��
	 */
	private String tdStyle = null;

	/**
	 * ���ֵ�Ԫ���ʽ��
	 */
	private String digitTdStyle = null;

	/**
	 * FreeForm��ÿ������ʾ������
	 */
	private int colsPerRow = 0;

	/**
	 * ��Ԫ�����Ƿ�Ҫ�Զ�����
	 */
	private boolean isAutoWrap = false;

	/**
	 * �Ƿ�Ϊ��ӡҳ
	 */
	private boolean isPrintPage = false;

	/**
	 * �Ƿ�Ҫ���浽Excel�ļ�
	 */
	private boolean isSaveToExcel = false;

	/**
	 * ��FreeForm��ʾ��ѯ���
	 */
	private String freeFormHtml = null;

	/**
	 * ��Grid��ʾ��ѯ���
	 */
	private String gridHtml = null;

	/**
	 * �ù�������ʽ��ʾ��ѯ���
	 */
	private String salaryRollHtml = null;

	/**
	 * ��ҳ����
	 */
	private PagesController pagesCtrl = null;

	/**
	 * ��ѯ���������Ϣ
	 */
	private QueryFormula formula = null;

	/**
	 * ��ѯ��SQL�����Ĳ���ֵ
	 */
	private String[] sqlParam = null;

	/**
	 * ��ѯ��SQL���
	 */
	private String querySql = null;

	/**
	 * ��ѯ����
	 */
	private String queryPrefix = null;

	/**
	 * ÿһ�еļ�����ȣ�����Ǽ����������Ϊ0
	 */
	private int[] computeDepths = null;

	/**
	 * ���캯��
	 */
	public QueryContents() {
		formula = new QueryFormula();
		pagesCtrl = new PagesController();
		printPageSetup = new PrintPageSetup();
	}

	/**
	 * ���в�ѯǰ��׼��������������ѯ���򼰷��������
	 * @throws QueryException ��ѯ�쳣
	 * @throws MissingParameterException ȡ����ʱ�����쳣
	 */
	public void prepareQuery()
		throws QueryException, MissingParameterException {
		if (queryPrefix == null)
			throw new QueryException("QueryContents�е� queryPrefix Ϊ��");

		//��ʼ����ѯ����
		formula.initContent(queryPrefix);
		printPageSetup.setPageType(formula.getPageType());
		querySql = formula.getSql();
		//����ҳ���ҳ������
		pagesCtrl.setQueryPrefix(queryPrefix);
		if (pagesCtrl.getRowsPerPage() > 0 && !formula.isNeedGroupBy()) {
			int iFrom = querySql.toUpperCase().indexOf("FROM");
			int iOrder = querySql.toUpperCase().indexOf("ORDER BY");
			String strSql = "SELECT COUNT(*) ";
			if (iOrder > -1)
				strSql = strSql + querySql.substring(iFrom, iOrder);
			else
				strSql = strSql + querySql.substring(iFrom);
			log.debug(strSql);
			db = new DataBean();
			DynaSqlBean dsb = new DynaSqlBean();
			dsb.setSql(strSql);
			dsb.setParam(sqlParam);
			try {
				db.setCrs(dsb.executeQuery());
				pagesCtrl.createPagesController(
					Integer.parseInt(db.getElementValue(0, 0)));
				db.release();
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
				throw new QueryException("׼������ҳ������ʱ��ִ��SQL������");
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new QueryException("׼������ҳ������ʱ��������δ��������쳣");
			}
		}
	}

	/**
	 * ��ѯ��������оۺϵ�SQL��ѯ�����ڴ˺����й����ҳ������
	 * @throws QueryException ��ѯ�쳣
	 */
	public void query() throws QueryException {
		db = new DataBean();
		DynaSqlBean dsb = new DynaSqlBean();
		dsb.setSql(querySql);
		dsb.setParam(sqlParam);
		if (pagesCtrl.getRowsPerPage() > 0 && !formula.isNeedGroupBy())
			dsb.setReturnMaxRows(pagesCtrl.getCurrentRowsCount());
		try {
			db.setCrs(dsb.executeQuery());
			//�����Ҫ��ҳ����SQL�к���Group by�����ڴ˽��г�ʼ����ҳ������
			if (formula.isNeedGroupBy() || pagesCtrl.getRowsPerPage() == 0) {
				log.debug("������ҳ������");
				pagesCtrl.createPagesController(db.getRowCount());
			}

			//�����Ҫ������㣬���������������
			if (formula.isGroupByCompute()) {
				computeDepths = parseComputeRows();
				pagesCtrl.setGroupCompute(true);
				//�������ɷ�ҳ��
				if (pagesCtrl.getRowsPerPage() != 0) {
					int nRow = 0;
					int nPage = 0;
					int nIgnore = 0;
					int[] nArray = new int[pagesCtrl.getMaxPages() + 1];
					for (int i = 0; i < computeDepths.length; i++) {
						if (isIgnoreRow(i)) {
							nIgnore++;
							continue;
						}
						if (nRow % pagesCtrl.getRowsPerPage() == 0) {
							nArray[nPage] = i;
							nPage++;
						}
						nRow++;
					}
					nArray[nPage] = computeDepths.length;
					int[] nPageBreak = new int[nPage + 1];
					for (int i = 0; i <= nPage; i++) {
						nPageBreak[i] = nArray[i];
					}
					log.debug("Ignore rows:" + nIgnore);
					log.debug("total rows:" + computeDepths.length);
					pagesCtrl.setPageBreak(nPageBreak);
					pagesCtrl.setMaxPages(nPage);
				}
			}

			log.debug(
				"�����������"
					+ db.getRowCount()
					+ "/��ҳ�������������"
					+ pagesCtrl.getRealRowsCount());
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new QueryException("ִ�в�ѯ����");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new QueryException("ִ�в�ѯ������δ�ܲ�׽���Ĵ���");
		}
	}

	/**
	 * ���ص�ǰ������е�����
	 * @return ��ǰ������е�����
	 */
	public int getRowCount() {
		return db.getRowCount();
	}

	/**
	 * ���ص�ǰ��ѯ���еĸ���������з������������������еĲ�����
	 * ��Ϊgrouping����ϳ�����URL���н�����ͳ��
	 * @return ��column���ж������
	 */
	public int getSequenceColumnCount() {
		return formula.size();
	}

	/**
	 * ���ص�ǰ��ѯ���еĸ���������з������������������еĲ�����
	 * ��Ϊgrouping����ϳ�����URL���н�����ͳ��
	 * @return ��column���ж������
	 */
	public int getSequenceDisplayColumnCount() {
		return formula.getDisplayColumnCount();
	}

	/**
	 * ���ص�ǰ��ѯ�еĸ���
	 * @return ��ѯSQL����ж�����и���
	 */
	public int getColumnCount() {
		return db.getColumnCount();
	}

	/**
	 * ��ʾ�е�˵����Ϣ����GRID��ѯ��Ϊ��ͷ����
	 * @param sequence Ҫ��ʾ�е���ţ���0��ʼ
	 * @return �е�˵����Ϣ
	 */
	public String getSequenceColumnLabel(int sequence) {
		return formula.getColumn(sequence).getDisplayName();
	}

	/**
	 * ��ʾ�е�˵����Ϣ����GRID��ѯ��Ϊ��ͷ����
	 * @param sequence Ҫ��ʾ�е�ʵ�����
	 * @return �е�˵����Ϣ
	 */
	public String getSequenceColumnLabel(String sequence) {
		return formula.getColumn(sequence).getDisplayName();
	}

	/**
	 * ��ȡָ�����е��е�HTML���ݣ����Ϊ���룬����ʾ����ֵ�����ΪURL����ת����URL����
	 * @param sequence Ҫ��ʾ���еı��
	 * @param row �������ڵ��к�
	 * @return ת������е�ֵ
	 */
	public String getSequenceColumnHtmlCode(int sequence, int row) {
		String strName = null;
		String strUrlName = null;
		String strColValue = null;
		String strPattern = null;
		StringBuffer sbHtml = new StringBuffer();
		strName = formula.getColumn(sequence).getQueryColumnName();
		strColValue = getSequenceColumnDisplayCode(sequence, row);
		if (strColValue.trim().length() == 0) {
			if (formula.getColumn(sequence).getColumnNumberPattern() != null)
				strColValue = "0";
			else
				strColValue = "&nbsp;";
		}
		//�ǲ�������Ҫ��ʽ������
		strPattern = formula.getColumn(sequence).getColumnNumberPattern();
		if (strPattern != null) {
			strColValue = DecimalTools.format(strColValue, strPattern);
		}
		if (formula.getColumn(sequence).isUrlColumn()) {
			strUrlName = strName + "_URL";
			sbHtml
				.append("<a href=")
				.append(
					CommonTools.stringTrim(
						db.getElementValue(row, strUrlName)))
				.append(">")
				.append(strColValue)
				.append("</a>");
		} else {
			sbHtml.append(strColValue);
		}
		return sbHtml.toString();
	}

	/**
	 * ��ȡָ�����е��е�HTML���ݣ����Ϊ���룬����ʾ����ֵ�����ΪURL����ת����URL����
	 * @param sequence Ҫ��ʾ���еı��
	 * @param row �������ڵ��к�
	 * @return ת������е�ֵ
	 */
	public String getSequenceColumnHtmlCode(String sequence, int row) {
		int i = Integer.parseInt(sequence) - 1;
		return getSequenceColumnHtmlCode(i, row);
	}

	/**
	 * ��ȡָ�����е��е���ʾ���ݣ����Ϊ���룬��Ϊ����ֵ
	 * @param sequence Ҫ��ʾ���еı��
	 * @param row �������ڵ��к�
	 * @return ת������е�ֵ
	 */
	public String getSequenceColumnDisplayCode(int sequence, int row) {
		String strName = null;
		String strColValue = null;
		String strCclass = null;
		strName = formula.getColumn(sequence).getQueryColumnName();
		strColValue = db.getElementValue(row, strName);
		//�ǲ��Ǵ���
		if (!strColValue.equals("")) {
			strCclass = formula.getColumn(sequence).getColumnCodeKey();
			if (formula.getColumn(sequence).isCodeColumn()) {
				strColValue =
					formula.getColumn(sequence).getColumnCodeValue(
						strCclass,
						strColValue);
			}
		}
		return strColValue;
	}

	/**
	 * ��ȡָ�����е��е���ʾ���ݣ����Ϊ���룬��Ϊ����ֵ
	 * @param sequence Ҫ��ʾ���еı��
	 * @param row �������ڵ��к�
	 * @return ת������е�ֵ
	 */
	public String getSequenceColumnDisplayCode(String sequence, int row) {
		int i = Integer.parseInt(sequence) - 1;
		return getSequenceColumnDisplayCode(i, row);
	}
	/**
	 * �ӽ������ȡ����Ӧ��ʾ��ŵ��е�����
	 * @param sequence ��ʾ�����
	 * @param row ָ������
	 * @return ������ָ���е�ֵ
	 */
	public String getSequenceVaulue(String sequence, int row) {
		String strColumnName = formula.getColumn(sequence).getQueryColumnName();
		return db.getElementValue(row, strColumnName).trim();
	}

	/**
	 * �ӽ������ȡ����Ӧ��ʾ��ŵ��е�����
	 * @param sequence ��ʾ�����
	 * @param row ָ������
	 * @return ������ָ���е�ֵ
	 */
	public String getSequenceVaulue(int sequence, int row) {
		String strColumnName = formula.getColumn(sequence).getQueryColumnName();
		return db.getElementValue(row, strColumnName).trim();
	}

	/**
	 * ����ָ���С��е�ֵ
	 * @param col ָ������
	 * @param row ָ������
	 * @return ������ָ���е�ֵ
	 */
	public String getFieldValue(String col, int row) {
		return db.getElementValue(row, col);
	}

	/**
	 * ��ʾ���͵����ֱ�ʾ
	 * @return ��ʾ���͵����ֱ�ʾ
	 */
	private int getNumDisplayType() {
		for (int i = 0; i < displayTypes.length; i++) {
			if (formula
				.getForwardPage()
				.trim()
				.toUpperCase()
				.equals(displayTypes[i]))
				return i;
		}
		log.warn("Ҫ��ʾ����Ϊ��" + formula.getForwardPage() + "����ϵͳ��֧�֣�����GRID��ʽ����ʾ");
		return 0;
	}
	/**
	 * ����ָ���С��е�ֵ
	 * @param col ָ������
	 * @param row ָ������
	 * @return ������ָ���е�ֵ
	 */
	public String getFieldValue(int col, int row) {
		return db.getElementValue(row, col);
	}

	/**
	 * ����ѯ����Թ���������ʽ���أ������ʾ������tableStyle��tdStyle����
	 * @return ���ɺ��HTML����
	 */
	public String renderSalaryRollHtml() {
		try {
			if (getRowCount() == 0)
				return "��ѯ������";

			//�����EXCEL�ļ�
			if (isSaveToExcel) {
				if (writeToExcel())
					return "����ɹ�";
				return "<Font color = red>����ʧ��</Font>";
			}

			//���������ʾ��񣬶Կյ����Ը�Ĭ��ֵ
			if (tableStyle == null)
				tableStyle = "salaryRollTable";
			if (tableHeaderStyle == null)
				tableHeaderStyle = "salaryRollTdHeader";
			if (tdStyle == null)
				tdStyle = "salaryRollTd";
			if (digitTdStyle == null)
				digitTdStyle = "salaryRollDigitTd";
			if (groupTdStyle == null)
				groupTdStyle = "salaryRollGroupTd";
			if (groupDigitTdStyle == null)
				groupDigitTdStyle = "salaryRollGroupDigitTd";
			if (sumTdStyle == null)
				sumTdStyle = "salaryRollSumTd";
			if (sumDigitTdStyle == null)
				sumDigitTdStyle = "salaryRollDigitSumTd";
			if (isPrintPage && printPageSetup.size() == 0) {
				isPrintPage = false;
				return "û�����ô�ӡ�������������ɴ�ӡҳ";
			}
			//����Ĭ��ֵ����
			if (isPrintPage) {
				tableStyle += "Print";
				tableHeaderStyle += "Print";
				tdStyle += "Print";
				digitTdStyle += "Print";
				groupTdStyle += "Print";
				groupDigitTdStyle += "Print";
				sumTdStyle += "Print";
				sumDigitTdStyle += "Print";
			}

			StringBuffer sbHtml = new StringBuffer();
			if (tableHeaderStyle == null)
				tableHeaderStyle = tdStyle;
			if (groupTdStyle == null)
				groupTdStyle = tdStyle;
			int l = 0; //ʵ�����������
			if (isPrintPage && printAbleFontSize == null)
				printAbleFontSize = getPrintAbleFontSize();
			for (int i = pagesCtrl.getStartRow();
				i < pagesCtrl.getEndRow();
				i++) {
				if (isPrintPage()
					&& printPageSetup
						.getPrintParameter(printAbleFontSize)
						.getOtherPageRows()
						> 0
					&& l > 0
					&& (l
						- printPageSetup
							.getPrintParameter(printAbleFontSize)
							.getFirstPageRows())
						% printPageSetup
							.getPrintParameter(printAbleFontSize)
							.getOtherPageRows()
						== 0) {
					//д��ҳ��
					log.debug(
						printPageSetup
							.getPrintParameter(printAbleFontSize)
							.getFirstPageRows()
							+ "/"
							+ printPageSetup
								.getPrintParameter(printAbleFontSize)
								.getOtherPageRows());
					sbHtml.append(pageBreak);
				}
				l++;
				//д��ͷ
				//����ͷ	
				if (tableStyle != null)
					sbHtml
						.append("<table class=\"")
						.append(tableStyle)
						.append("\" ")
						.append(tableCommStyle)
						.append("><tbody>");
				else
					sbHtml.append("<table  ").append(tableCommStyle).append(
						"><tbody>");

				sbHtml.append("<tr>");
				for (int k = 0; k < getSequenceColumnCount(); k++) {
					if (formula.getColumn(k).isDisplay()) {
						sbHtml.append("<td");
						if (tableHeaderStyle != null)
							sbHtml
								.append(" class=\"")
								.append(tableHeaderStyle)
								.append("\" width=\"")
								.append(
									getColumnDisplaySize()[k]
										* getDisplayScale())
								.append("\" ");
						if (!isAutoWrap)
							sbHtml.append(" nowrap align=center");
						sbHtml.append(">");
						if (isPrintPage)
							sbHtml.append("<P class=\"p").append(
								getPrintAbleFontSize()).append(
								"\">");
						sbHtml.append(getSequenceColumnLabel(k)).append(
							"</td>");
					}
				}
				sbHtml.append("</tr>");
				//д����
				sbHtml.append("<tr>");
				boolean isWriteTotal = false;
				for (int j = 0; j < getSequenceColumnCount(); j++) {
					if (formula.getColumn(j).isDisplay()) {
						sbHtml.append("<td");
						//�ǲ��Ǽ�����
						if (formula.isGroupByCompute() && isComputeRow(i)) {
							//��ϼ���
							if (getComputeRowDepth(i) == 1) {
								if (formula
									.getColumn(j)
									.getColumnNumberPattern()
									== null) {
									if (sumTdStyle != null)
										sbHtml.append(" class=\"").append(
											sumTdStyle).append(
											"\" ");
								} else {
									if (sumDigitTdStyle != null)
										sbHtml.append(" class=\"").append(
											sumDigitTdStyle).append(
											"\" ");
								}
							} else {
								//С����
								if (formula
									.getColumn(j)
									.getColumnNumberPattern()
									== null) {
									if (groupTdStyle != null)
										sbHtml.append(" class=\"").append(
											groupTdStyle).append(
											"\" ");
								} else {
									if (groupDigitTdStyle != null)
										sbHtml.append(" class=\"").append(
											groupDigitTdStyle).append(
											"\" ");
								}
							}
							if (j < formula.getSqlGroupByColumns().length)
								sbHtml.append(" colspan=\"").append(
									parseDisplayGroupByNumber()).append(
									"\"");
							if (!isAutoWrap)
								sbHtml.append(" nowrap");
							sbHtml.append(">");
							if (isPrintPage)
								sbHtml.append("<P class=\"p").append(
									getPrintAbleFontSize()).append(
									"\">");

							int k = getComputeRowDepth(i);
							if ((i == getRowCount() - 1)
								&& j <= parseDisplayGroupByNumber()
								&& !isWriteTotal) {
								sbHtml.append("�ϼ�");
								isWriteTotal = true;
							}
							if (j < k) {
								if (k > 1)
									sbHtml.append(
										getSequenceColumnHtmlCode(k - 2, i)
											.trim())
											.append(
										"С��");
								j = formula.getSqlGroupByColumns().length - 1;
							} else {
								sbHtml.append(
									getSequenceColumnHtmlCode(j, i).trim());
							}
							sbHtml.append("</td>");
						} else {
							if (formula.getColumn(j).getColumnNumberPattern()
								== null) {
								if (tdStyle != null)
									sbHtml.append(" class=\"").append(
										tdStyle).append(
										"\" ");
							} else {
								if (digitTdStyle != null)
									sbHtml.append(" class=\"").append(
										digitTdStyle).append(
										"\" ");
							}
							if (!isAutoWrap)
								sbHtml.append(" nowrap");
							sbHtml.append(">");
							if (isPrintPage)
								sbHtml.append("<P class=\"p").append(
									getPrintAbleFontSize()).append(
									"\">");

							sbHtml.append(getSequenceColumnHtmlCode(j, i));
							sbHtml.append("</td>");
						}
					}
				}
				sbHtml.append("</tr>");
				//������
				sbHtml.append("</tbody></table>");
				//�ӿձ��
				sbHtml.append("<table border=0><tr><td></td></tr></table>");
			}
			//��ӷ�ҳ����
			if (getPagesCtrl().getRowsPerPage() > 0)
				sbHtml.append(getPagesCtrl().getControlPageCodes());
			if (!isPrintPage) {
				if (formula.isNeedPrint())
					sbHtml
						.append("<A href=\"")
						.append(getPagesCtrl().getBaseUrl())
						.append("?printPage=true&pageName=%2Fquery%2FsalaryRollPrint.jsp&")
						.append(getPagesCtrl().getQueryParametersURL())
						.append("\" target=\"_printPage\">��ʾ�ɴ�ӡҳ��</A>&nbsp;&nbsp;&nbsp;&nbsp;");
				//������ΪEXCEL�ļ�
				sbHtml
					.append("<A href=\"")
					.append(getPagesCtrl().getBaseUrl())
					.append("?saveToExcel=true&")
					.append(getPagesCtrl().getQueryParametersURL())
					.append("\">���ΪEXCEL</A>");
			}
			return sbHtml.toString();
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			db = null;
			return "�����д���";
		} finally {
			if (isPrintPage) {
				//��ԭ�������
				tableStyle = tableStyle.substring(0, tableStyle.length() - 5);
				tableHeaderStyle =
					tableHeaderStyle.substring(
						0,
						tableHeaderStyle.length() - 5);
				tdStyle = tdStyle.substring(0, tdStyle.length() - 5);
				digitTdStyle =
					digitTdStyle.substring(0, digitTdStyle.length() - 5);
				groupTdStyle =
					groupTdStyle.substring(0, groupTdStyle.length() - 5);
				groupDigitTdStyle =
					groupDigitTdStyle.substring(
						0,
						groupDigitTdStyle.length() - 5);
				sumTdStyle = sumTdStyle.substring(0, sumTdStyle.length() - 5);
				sumDigitTdStyle =
					sumDigitTdStyle.substring(0, sumDigitTdStyle.length() - 5);
				isPrintPage = false;
			}
			if (isSaveToExcel)
				isSaveToExcel = false;
		}
	}

	/**
	 * ����ѯ�����Grid�ı����ʽ���أ������ʾ������tableStyle��tdStyle����
	 * @return ���ɺ��HTML����
	 */
	public String renderGridHtml() {
		try {
			if (getRowCount() == 0)
				return "��ѯ������";
			//�����EXCEL�ļ�
			if (isSaveToExcel) {
				if (writeToExcel())
					return "����ɹ�";
				return "<Font color = red>����ʧ��</Font>";
			}
			StringBuffer sbHtml = new StringBuffer();

			//���������ʾ��񣬶Կյ����Ը�Ĭ��ֵ
			if (tableStyle == null)
				tableStyle = "table2border";
			if (tableHeaderStyle == null)
				tableHeaderStyle = "table2titletd";
			if (tdStyle == null)
				tdStyle = "table2textlefttd";
			if (digitTdStyle == null)
				digitTdStyle = "table2textrighttd";
			if (groupTdStyle == null)
				groupTdStyle = "table2groupcentertd";
			if (groupDigitTdStyle == null)
				groupDigitTdStyle = "table2grouprighttd";
			if (sumTdStyle == null)
				sumTdStyle = "table2sumcentertd";
			if (sumDigitTdStyle == null)
				sumDigitTdStyle = "table2sumrighttd";
			//����Ĭ��ֵ����

			if (tableHeaderStyle == null)
				tableHeaderStyle = tdStyle;
			if (groupTdStyle == null)
				groupTdStyle = tdStyle;
			String strTempTableStyle = tableCommStyle;
			if (isPrintPage && printPageSetup.size() == 0) {
				isPrintPage = false;
				return "û�����ô�ӡ�������������ɴ�ӡҳ";
			}
			if (isPrintPage) {
				tableStyle += "Print";
				tableHeaderStyle += "Print";
				tdStyle += "Print";
				digitTdStyle += "Print";
				groupTdStyle += "Print";
				groupDigitTdStyle += "Print";
				sumTdStyle += "Print";
				sumDigitTdStyle += "Print";
				strTempTableStyle = tablePrintCommStyle;
			}
			if (tableStyle != null)
				sbHtml
					.append("<table class=\"")
					.append(tableStyle)
					.append("\" ")
					.append(strTempTableStyle)
					.append("><tbody>");
			else
				sbHtml.append("<table  ").append(tableCommStyle).append(
					"><tbody>");
			log.debug(pagesCtrl.getStartRow() + "/" + pagesCtrl.getEndRow());
			//д��ͷ
			sbHtml.append("<tr>");
			for (int k = 0; k < getSequenceColumnCount(); k++) {
				if (formula.getColumn(k).isDisplay()) {
					sbHtml.append("<td");
					if (tableHeaderStyle != null)
						sbHtml
							.append(" class=\"")
							.append(tableHeaderStyle)
							.append("\" width=\"")
							.append(
								getColumnDisplaySize()[k] * getDisplayScale())
							.append("\" ");
					if (!isAutoWrap)
						sbHtml.append(" nowrap ");
					sbHtml.append(">");
					if (isPrintPage)
						sbHtml.append("<P class=\"p").append(
							getPrintAbleFontSize()).append(
							"\">");
					sbHtml.append(getSequenceColumnLabel(k)).append("</td>");
				}
			}
			sbHtml.append("</tr>");
			int l = 0; //ʵ�����������
			if (isPrintPage && printAbleFontSize == null)
				printAbleFontSize = getPrintAbleFontSize();
			//д����
			for (int i = pagesCtrl.getStartRow();
				i < pagesCtrl.getEndRow();
				i++) {
				//�Ƿ�Ϊ������
				if (formula.isGroupByCompute() && formula.isGroupByCompute()) {
					if (isIgnoreRow(i)) {
						log.debug("ignore row: " + i);
						continue;
					}
				}

				if (isPrintPage()
					&& printPageSetup
						.getPrintParameter(printAbleFontSize)
						.getOtherPageRows()
						> 0
					&& l > 0
					&& (l
						- printPageSetup
							.getPrintParameter(printAbleFontSize)
							.getFirstPageRows())
						% printPageSetup
							.getPrintParameter(printAbleFontSize)
							.getOtherPageRows()
						== 0) {
					//�������
					sbHtml.append("</tbody></table>");

					//д��ҳ��
					sbHtml.append(pageBreak);
					//����ͷ	
					if (tableStyle != null)
						sbHtml
							.append("<table class=\"")
							.append(tableStyle)
							.append("\" ")
							.append(strTempTableStyle)
							.append("><tbody>");
					else
						sbHtml.append("<table  ").append(
							tableCommStyle).append(
							"><tbody>");
					//д��ӡ��ͷ
					sbHtml.append("<tr>");
					for (int k = 0; k < getSequenceColumnCount(); k++) {
						if (formula.getColumn(k).isDisplay()) {
							sbHtml.append("<td");
							if (tableHeaderStyle != null)
								sbHtml
									.append(" class=\"")
									.append(tableHeaderStyle)
									.append("\" width=\"")
									.append(
										getColumnDisplaySize()[k]
											* getDisplayScale())
									.append("\" ");
							if (!isAutoWrap)
								sbHtml.append(" nowrap ");
							sbHtml.append(">");
							if (isPrintPage)
								sbHtml.append("<P class=\"p").append(
									getPrintAbleFontSize()).append(
									"\">");
							sbHtml.append(getSequenceColumnLabel(k)).append(
								"</td>");
						}
					}
					sbHtml.append("</tr>");
				}
				l++;
				sbHtml.append("<tr>");
				boolean isWriteTotal = false;
				for (int j = 0; j < getSequenceColumnCount(); j++) {
					if (formula.getColumn(j).isDisplay()) {
						sbHtml.append("<td");
						//�ǲ��Ǽ�����
						if (formula.isGroupByCompute() && isComputeRow(i)) {
							//��ϼ���
							if (getComputeRowDepth(i) == 1) {
								if (formula
									.getColumn(j)
									.getColumnNumberPattern()
									== null) {
									if (sumTdStyle != null)
										sbHtml.append(" class=\"").append(
											sumTdStyle).append(
											"\" ");
								} else {
									if (sumDigitTdStyle != null)
										sbHtml.append(" class=\"").append(
											sumDigitTdStyle).append(
											"\" ");
								}
							} else {
								//С����
								if (formula
									.getColumn(j)
									.getColumnNumberPattern()
									== null) {
									if (groupTdStyle != null)
										sbHtml.append(" class=\"").append(
											groupTdStyle).append(
											"\" ");
								} else {
									if (groupDigitTdStyle != null)
										sbHtml.append(" class=\"").append(
											groupDigitTdStyle).append(
											"\" ");
								}
							}
							if (j < formula.getSqlGroupByColumns().length)
								sbHtml.append(" colspan=\"").append(
									parseDisplayGroupByNumber()).append(
									"\"");
							if (!isAutoWrap)
								sbHtml.append(" nowrap");

							sbHtml.append(">");
							if (isPrintPage)
								sbHtml.append("<P class=\"p").append(
									getPrintAbleFontSize()).append(
									"\">");
							int n = getComputeRowDepth(i);
							if ((i == getRowCount() - 1)
								&& j <= parseDisplayGroupByNumber()
								&& !isWriteTotal) {
								sbHtml.append("�ϼ�");
								isWriteTotal = true;
							}
							if (j < n) {
								if (n > 1)
									sbHtml.append(
										getSequenceColumnHtmlCode(n - 2, i)
											.trim())
											.append(
										"С��");
								j = formula.getSqlGroupByColumns().length - 1;
							} else {
								sbHtml.append(
									getSequenceColumnHtmlCode(j, i).trim());
							}
							sbHtml.append("</td>");
						} else {
							if (formula.getColumn(j).getColumnNumberPattern()
								== null) {
								if (tdStyle != null)
									sbHtml.append(" class=\"").append(
										tdStyle).append(
										"\" ");
							} else {
								if (digitTdStyle != null)
									sbHtml.append(" class=\"").append(
										digitTdStyle).append(
										"\" ");
							}
							if (!isAutoWrap)
								sbHtml.append(" nowrap");
							sbHtml.append(">");
							if (isPrintPage)
								sbHtml.append("<P class=\"p").append(
									getPrintAbleFontSize()).append(
									"\">");
							sbHtml.append(getSequenceColumnHtmlCode(j, i));
							sbHtml.append("</td>");
						}
					}
				}
				sbHtml.append("</tr>");
			}
			//������
			sbHtml.append("</tbody></table>");
			//��ӷ�ҳ����
			if (getPagesCtrl().getRowsPerPage() > 0)
				sbHtml.append(getPagesCtrl().getControlPageCodes());
			if (!isPrintPage) {
				if (formula.isNeedPrint())
					sbHtml
						.append("<A href=\"")
						.append(getPagesCtrl().getBaseUrl())
						.append("?printPage=true&pageName=%2Fquery%2FgridPrint.jsp&")
						.append(getPagesCtrl().getQueryParametersURL())
						.append("\" target=\"_printPage\">��ʾ�ɴ�ӡҳ��</A>&nbsp;&nbsp;&nbsp;&nbsp;");
				//������ΪEXCEL�ļ�
				sbHtml
					.append("<A href=\"")
					.append(getPagesCtrl().getBaseUrl())
					.append("?saveToExcel=true&")
					.append(getPagesCtrl().getQueryParametersURL())
					.append("\">���ΪEXCEL</A>");
			}
			return sbHtml.toString();
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			db = null;
			return "�����д���";
		} finally {
			if (isPrintPage) {
				//��ԭ�������
				tableStyle = tableStyle.substring(0, tableStyle.length() - 5);
				tableHeaderStyle =
					tableHeaderStyle.substring(
						0,
						tableHeaderStyle.length() - 5);
				tdStyle = tdStyle.substring(0, tdStyle.length() - 5);
				digitTdStyle =
					digitTdStyle.substring(0, digitTdStyle.length() - 5);
				groupTdStyle =
					groupTdStyle.substring(0, groupTdStyle.length() - 5);
				groupDigitTdStyle =
					groupDigitTdStyle.substring(
						0,
						groupDigitTdStyle.length() - 5);
				sumTdStyle = sumTdStyle.substring(0, sumTdStyle.length() - 5);
				sumDigitTdStyle =
					sumDigitTdStyle.substring(0, sumDigitTdStyle.length() - 5);
				isPrintPage = false;
			}
			if (isSaveToExcel)
				isSaveToExcel = false;

		}
	}

	/**
	 * �ж��Ƿ�Ϊ�����������
	 * @param row �������ָ������
	 * @return ����������У��򷵻�true
	 */
	public boolean isComputeRow(int row) {
		return (computeDepths[row] > 0);
	}

	/**
	 * ��������
	 * @param row �������ָ������
	 * @return ��������
	 */
	public int getComputeRowDepth(int row) {
		return computeDepths[row];
	}

	/**
	 * ��������
	 * @param row �������ָ������
	 * @return ��������
	 */
	public boolean isIgnoreRow(int row) {
		return computeDepths[row] > formula.getGroupByComputeDepth();
	}

	/**
	 * ����������
	 * @return ÿһ�еļ������
	 */
	private int[] parseComputeRows() {
		int[] nComputeRows = new int[db.getRowCount()];
		String[] strGroupingName = formula.getSqlGroupByColumns();
		for (int i = 0; i < nComputeRows.length; i++) {
			nComputeRows[i] = 0;
			for (int j = 0; j < strGroupingName.length; j++) {
				if (getFieldValue("GROUPING_" + strGroupingName[j], i)
					.trim()
					.equals("1")) {
					nComputeRows[i] = j + 1;
					break;
				}
			}
		}
		return nComputeRows;
	}

	/**
	 * ����GROUPBY���п���ʾ������
	 * @return GROUPBY���п���ʾ������
	 */
	private int parseDisplayGroupByNumber() {
		int j = 0;
		String[] strGroupingName = formula.getSqlGroupByColumns();
		for (int i = 0; i < strGroupingName.length; i++) {
			if (formula.getColumn(i).isDisplay())
				j++;
		}
		return j;
	}

	/**
	 * ����ѯ�����FreeForm�ı����ʽ���أ������ʾ������tableStyle��tdStyle����
	 * ���������colPerRow����
	 * @return ���ɺ��HTML����
	 */
	public String renderFreeFormHtml() {
		try {
			if (getRowCount() == 0)
				return "��ѯ������";

			//���������ʾ��񣬶Կյ����Ը�Ĭ��ֵ
			if (tableStyle == null)
				tableStyle = "table1border";
			if (tableHeaderStyle == null)
				tableHeaderStyle = "table1title";
			if (tdStyle == null)
				tdStyle = "table1text";
			if (digitTdStyle == null)
				digitTdStyle = tdStyle;
			if (groupTdStyle == null)
				groupTdStyle = tdStyle;
			if (groupDigitTdStyle == null)
				groupDigitTdStyle = tdStyle;
			if (sumTdStyle == null)
				sumTdStyle = tdStyle;
			if (sumDigitTdStyle == null)
				sumDigitTdStyle = tdStyle;
			//����Ĭ��ֵ����

			StringBuffer sbHtml = new StringBuffer();
			if (tableHeaderStyle == null)
				tableHeaderStyle = tdStyle;
			if (groupTdStyle == null)
				groupTdStyle = tdStyle;
			if (colsPerRow == 0)
				colsPerRow = 1;
			if (colsPerRow > getSequenceDisplayColumnCount())
				colsPerRow = getSequenceDisplayColumnCount();
			for (int i = pagesCtrl.getStartRow();
				i < pagesCtrl.getEndRow();
				i++) {
				if (tableStyle != null)
					sbHtml
						.append("<table class=\"")
						.append(tableStyle)
						.append("\" ")
						.append(tableCommStyle)
						.append("><tbody><tr>");
				else
					sbHtml.append("<table  ").append(tableCommStyle).append(
						"><tbody><tr>");
				//д��ʾ�е���Ŀ
				int k = 0;
				for (int j = 0; j < getSequenceColumnCount(); j++) {
					if (formula.getColumn(j).isDisplay()) {
						k = k + 1;
						sbHtml.append("<td");
						if (tableHeaderStyle != null)
							sbHtml.append(" class=\"").append(
								tableHeaderStyle).append(
								"\" ");
						if (!isAutoWrap)
							sbHtml.append(" nowrap");
						sbHtml.append("><b>").append(
							getSequenceColumnLabel(j)).append(
							"</b></td>");
						sbHtml.append("<td");
						if (formula.isGroupByCompute() && isComputeRow(i)) {
							if (groupTdStyle != null)
								sbHtml.append(" class=\"").append(
									groupTdStyle).append(
									"\" ");
						} else {
							if (tdStyle != null)
								sbHtml.append(" class=\"").append(
									tdStyle).append(
									"\" ");
						}
						if (!isAutoWrap)
							sbHtml.append(" nowrap");
						sbHtml.append(">");
						sbHtml.append(getSequenceColumnHtmlCode(j, i));
						sbHtml.append("</td>");
						//�Ƿ���
						if (k % colsPerRow == 0
							&& k >= colsPerRow
							&& j < getSequenceDisplayColumnCount() - 1) {
							sbHtml.append("</tr><tr>");
						}
					}

				}
				//���䵥Ԫ��
				if (k % colsPerRow != 0) {
					for (int j = 0; j < (colsPerRow - k % colsPerRow); j++) {
						sbHtml.append("<td");
						if (tdStyle != null)
							sbHtml.append(" class=\"").append(tdStyle).append(
								"\" ");
						if (!isAutoWrap)
							sbHtml.append(" nowrap align=right");
						sbHtml.append(">&nbsp;</td>");
						sbHtml.append("<td");
						if (tdStyle != null)
							sbHtml.append(" class=\"").append(tdStyle).append(
								"\" ");
						if (!isAutoWrap)
							sbHtml.append(" nowrap");
						sbHtml.append(">&nbsp;");
						sbHtml.append("</td>");
					}
				}
				sbHtml.append("</tr>");
				//������
				sbHtml.append("</tbody></table><br>");
			}
			//��ӷ�ҳ����
			if (getPagesCtrl().getRowsPerPage() > 0)
				sbHtml.append(getPagesCtrl().getControlPageCodes());
			if (!isPrintPage) {
				if (formula.isNeedPrint())
					sbHtml
						.append("<A href=\"")
						.append(getPagesCtrl().getBaseUrl())
						.append("?printPage=true&pageName=printPage&")
						.append(getPagesCtrl().getQueryParametersURL())
						.append("\">��ʾ�ɴ�ӡҳ��</A>&nbsp;&nbsp;&nbsp;&nbsp;");
				//������ΪEXCEL�ļ�
				sbHtml
					.append("<A href=\"")
					.append(getPagesCtrl().getBaseUrl())
					.append("?saveToExcel=true&")
					.append(getPagesCtrl().getQueryParametersURL())
					.append("\">���ΪEXCEL</A>");
			}
			return sbHtml.toString();
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			db = null;
			return "�����д���";
		}
	}

	/**
	 * @return DataBean ����
	 */
	public DataBean getDb() {
		return db;
	}

	/**
	 * @return PagesController ����
	 */
	public PagesController getPagesCtrl() {
		return pagesCtrl;
	}

	/**
	 * @return ��ѯ����
	 */
	public String getQueryPrefix() {
		return queryPrefix;
	}

	/**
	 * @return ��ѯ��SQL���
	 */
	public String getQuerySql() {
		return querySql;
	}

	/**
	 * @return SQL���Ĳ�������
	 */
	public String[] getSqlParam() {
		return sqlParam;
	}

	/**
	 * @param bean
	 */
	public void setDb(DataBean bean) {
		db = bean;
	}

	/**
	 * @param controller
	 */
	public void setPagesCtrl(PagesController controller) {
		pagesCtrl = controller;
	}

	/**
	 * @param string
	 */
	public void setQueryPrefix(String string) {
		queryPrefix = string;
	}

	/**
	 * @param string
	 */
	public void setQuerySql(String string) {
		querySql = string;
	}

	/**
	 * @param strings
	 */
	public void setSqlParam(String[] strings) {
		sqlParam = strings;
	}

	/**
	 * @return QueryFormula ����
	 */
	public QueryFormula getFormula() {
		return formula;
	}

	/**
	 * @param formula
	 */
	public void setFormula(QueryFormula formula) {
		this.formula = formula;
	}

	/**
	 * @return HTMLҳHEADER
	 */
	public String getHtmlHeader() {
		if (htmlHeader == null)
			htmlHeader = formula.getHeader().trim();
		return htmlHeader;
	}

	/**
	 * @return HTMLҳ����
	 */
	public String getHtmlTitle() {
		if (htmlTitle == null)
			htmlTitle = formula.getTitle().trim();
		return htmlTitle;
	}

	/**
	 * @param string
	 */
	public void setHtmlHeader(String string) {
		htmlHeader = string;
	}

	/**
	 * @param string
	 */
	public void setHtmlTitle(String string) {
		htmlTitle = string;
	}
	/**
	 * @return ����freeFormHtml����
	 */
	public String getFreeFormHtml() {
		freeFormHtml = renderFreeFormHtml();
		return freeFormHtml;
	}

	/**
	 * @return ����gridHtml���� 
	 */
	public String getGridHtml() {
		gridHtml = renderGridHtml();
		return gridHtml;
	}

	/**
	 * @return �Ƿ�����
	 */
	public boolean isAutoWrap() {
		return isAutoWrap;
	}

	/**
	 * @return ����������
	 */
	public String getSalaryRollHtml() {
		salaryRollHtml = renderSalaryRollHtml();
		return salaryRollHtml;
	}

	/**
	 * @return ����ʽ��
	 */
	public String getTableStyle() {
		return tableStyle;
	}

	/**
	 * @return ��Ԫ���ʽ��
	 */
	public String getTdStyle() {
		return tdStyle;
	}

	/**
	 * @param b
	 */
	public void setAutoWrap(boolean b) {
		isAutoWrap = b;
	}

	/**
	 * @param string
	 */
	public void setTableStyle(String string) {
		tableStyle = string;
	}

	/**
	 * @param string
	 */
	public void setTdStyle(String string) {
		tdStyle = string;
	}

	/**
	 * @return ÿ���ж�����
	 */
	public int getColsPerRow() {
		return colsPerRow;
	}

	/**
	 * @param i
	 */
	public void setColsPerRow(int i) {
		colsPerRow = i;
	}

	/**
	 * @return ���ظ���ѯ�еı�ע
	 */
	public String[] getFieldLabel() {
		fieldLabel = new String[getSequenceColumnCount()];
		for (int i = 0; i < fieldLabel.length; i++) {
			fieldLabel[i] = getSequenceColumnLabel(i);
		}
		return fieldLabel;
	}

	/**
	 * @return ������
	 */
	public String getTableHeaderStyle() {
		return tableHeaderStyle;
	}

	/**
	 * @param string
	 */
	public void setTableHeaderStyle(String string) {
		tableHeaderStyle = string;
	}

	/**
	 * @return �����������Ԫ���ʽ��
	 */
	public String getGroupTdStyle() {
		return groupTdStyle;
	}

	/**
	 * @param string
	 */
	public void setGroupTdStyle(String string) {
		groupTdStyle = string;
	}

	/**
	 * @return ���ֵ�Ԫ���ʽ��
	 */
	public String getDigitTdStyle() {
		return digitTdStyle;
	}

	/**
	 * @return ����������ֵ�Ԫ���ʽ��
	 */
	public String getGroupDigitTdStyle() {
		return groupDigitTdStyle;
	}

	/**
	 * @return ��ϼ����ָ�ʽ��
	 */
	public String getSumDigitTdStyle() {
		return sumDigitTdStyle;
	}

	/**
	 * @return ��ϼƸ�ʽ��
	 */
	public String getSumTdStyle() {
		return sumTdStyle;
	}

	/**
	 * @param string
	 */
	public void setDigitTdStyle(String string) {
		digitTdStyle = string;
	}

	/**
	 * @param string
	 */
	public void setGroupDigitTdStyle(String string) {
		groupDigitTdStyle = string;
	}

	/**
	 * @param string
	 */
	public void setSumDigitTdStyle(String string) {
		sumDigitTdStyle = string;
	}

	/**
	 * @param string
	 */
	public void setSumTdStyle(String string) {
		sumTdStyle = string;
	}

	/**
	 * @return ���ͨ������
	 */
	public String getTableCommStyle() {
		return tableCommStyle;
	}

	/**
	 * @param string
	 */
	public void setTableCommStyle(String string) {
		tableCommStyle = string;
	}

	/**
	 * @return ��ҳ�����
	 */
	public String getPageBreak() {
		return pageBreak;
	}

	/**
	 * @param strings
	 */
	public void setFieldLabel(String[] strings) {
		fieldLabel = strings;
	}

	/**
	 * @param string
	 */
	public void setPageBreak(String string) {
		pageBreak = string;
	}

	/**
	 * @return �Ƿ�Ϊ��ӡҳ
	 */
	public boolean isPrintPage() {
		return isPrintPage;
	}

	/**
	 * @param b
	 */
	public void setPrintPage(boolean b) {
		isPrintPage = b;
	}

	/**
	 * @return ��ӡҳ�������Ϣ
	 */
	public PrintPageSetup getPrintPageSetup() {
		return printPageSetup;
	}

	/**
	 * @param size
	 */
	public void setPageSize(PrintPageSetup pps) {
		printPageSetup = pps;
	}

	/**
	 * @return ��ѯ�����HTML�����ʾ
	 */
	public String getResultHtml() {
		int nTemp = getNumDisplayType();
		switch (nTemp) {
			case 0 :
				resultHtml = getGridHtml();
				break;

			case 1 :
				resultHtml = getFreeFormHtml();
				break;

			case 2 :
				resultHtml = getSalaryRollHtml();
				break;

			default :
				resultHtml = getGridHtml();
				break;
		}
		return resultHtml;
	}

	/**
	 * ����ÿ����ʾ������
	 * @return ÿ����ʾ�ɰ������������
	 */
	private int statisticLineLength() {
		int countColumn = 1;
		for (int i = 0; i < formula.size(); i++) {
			if (formula.getColumn(i).isDisplay()) {
				countColumn = countColumn + getColumnDisplaySize()[i];
			}
		}
		log.debug("ÿ�е�������" + countColumn);
		return countColumn + getColumnDisplaySize().length;
	}

	/**
	 * ��ʼ��ÿ����ʾ�Ĵ�С 
	 */
	private void initColumnDisplaySize() {
		columnDisplaySize = new int[formula.size()];
		DataBean databean = new DataBean();
		DynaSqlBean dynasql = new DynaSqlBean();
		String strColValue;
		String strPattern;
		if (!formula.isNeedGroupBy()) {
			//����Ҫ������㣬������������ֵ
			StringBuffer sbSql = new StringBuffer();
			String strColumn;
			sbSql.append("SELECT ");
			for (int i = 0; i < formula.size(); i++) {
				strColumn = formula.getColumn(i).getRealColumn();
				if (strColumn != null) {
					sbSql.append("MAX(");
					if (strColumn.toUpperCase().indexOf(" AS") > 0)
						strColumn =
							strColumn.substring(
								0,
								strColumn.toUpperCase().indexOf(" AS"));
					sbSql.append(formula.getColumn(i).getRealColumn()).append(
						") AS compute_");
					sbSql.append(i);
				}
				if (i < formula.size() - 1) {
					sbSql.append(",");
				} else {
					sbSql.append(" ");
				}
			}
			sbSql.append("FROM ").append(formula.getSqlTables());
			String paramWhere = (String) formula.getParamWhere();
			String sqlWhere = formula.getSqlWhere();
			if (paramWhere != null)
				sbSql.append(" WHERE ").append(paramWhere).append(" ");

			if (sqlWhere != null && !sqlWhere.trim().equals("")) {
				if (paramWhere == null)
					sbSql.append(" WHERE ").append(sqlWhere).append(" ");
				else
					sbSql.append(" AND ").append(sqlWhere).append(" ");
			}
			dynasql.setSql(sbSql.toString());
			dynasql.setParam(sqlParam);
			try {
				databean.setCrs(dynasql.executeQuery());
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
				databean = null;
			}
		}
		for (int i = 0; i < columnDisplaySize.length; i++) {
			//�ַ����ͣ���ֱ��ȡ�䳤��
			if (formula.getColumn(i).getColumnNumberPattern() == null) {
				columnDisplaySize[i] = formula.getColumn(i).getDisplaySize();
			} else {
				if (formula.isNeedGroupBy()) {
					if (formula.isGroupByCompute()) {
						//���Ϊ����ֵ�������һ�г���ֵ����ʾ
						if (formula.getColumn(i).isUrlColumn())
							columnDisplaySize[i] =
								getSequenceVaulue(i, getRowCount() - 1)
									.trim()
									.length();
						else
							columnDisplaySize[i] =
								getSequenceColumnHtmlCode(i, getRowCount() - 1)
									.trim()
									.length();
					} else {
						//�����ѯ 
						columnDisplaySize[i] =
							formula.getColumn(i).getDisplaySize();
					}
				} else {
					if (databean == null) {
						columnDisplaySize[i] =
							formula.getColumn(i).getDisplaySize();
					} else {
						strColValue =
							databean
								.getElementValue(
									0,
									"compute_" + Integer.toString(i))
								.trim();
						strPattern =
							formula.getColumn(i).getColumnNumberPattern();
						if (strPattern != null) {
							strColValue =
								DecimalTools.format(strColValue, strPattern);
						}

						columnDisplaySize[i] = strColValue.length();
					}
				}
			}
			if (columnDisplaySize[i]
				< formula.getColumn(i).getLabelDisplaySize()) {
				//���ʵ�ʳ���С�ڱ�ͷ������ȡ��ͷ����
				columnDisplaySize[i] =
					formula.getColumn(i).getLabelDisplaySize();
			}
		}
	}

	/**
	 * @return �ɴ�ӡ����Ĵ�С
	 */
	private String getPrintAbleFontSize() {
		if (printAbleFontSize == null) {
			int nPosition = printPageSetup.getCurrentPosition();
			printAbleFontSize = printPageSetup.getDefaluFontSize();
			int nLength = statisticLineLength();
			try {
				if (printPageSetup
					.getPrintParameter(nPosition)
					.getLineWordCount()
					< nLength) {
					log.debug("Ĭ�����岻�������ӡ�����ҿɴ�ӡ����");
					int nTemp = 0;
					int nComp = 0;
					//��������ʵ�����
					boolean isFind = false;
					for (int i = 0;
						i < printPageSetup.getFontSize().length;
						i++) {
						nTemp =
							printPageSetup
								.getPrintParameter(i)
								.getLineWordCount();
						if (nTemp > nLength) {
							//�ɽ��ܵ�����
							if (nComp == 0) {
								isFind = true;
								nComp = nTemp;
								printAbleFontSize =
									printPageSetup.getFontSize()[i];
							} else if (nTemp < nComp) {
								//��ӽ�������
								nComp = nTemp;
								printAbleFontSize =
									printPageSetup.getFontSize()[i];
							}
						}
					}
					if (!isFind) {
						log.warn("û���ҵ��ɴ�ӡ�����С");
						printAbleFontSize = "9";
					}
					log.debug("ѡ��Ĵ�ӡ����Ϊ��" + printAbleFontSize);
				}
			} catch (QueryException e) {
				log.error(e.getMessage(), e);
			}
		}
		return printAbleFontSize;
	}

	/**
	 * ���������С��������ʾ��Ԫ�����طŴ����
	 * @return ��Ԫ�����طŴ����
	 */
	private int getDisplayScale() {
		if (isPrintPage) {
			int nSwitch = Integer.parseInt(getPrintAbleFontSize());
			switch (nSwitch) {
				case 9 :
					return 5;

				case 10 :
					return 6;

				case 12 :
					return 7;

				case 14 :
					return 8;

				default :
					break;
			}
			return 9;
		} else {
			return 9;
		}
	}

	/**
	 * ��ǰҳ�Ĳ�ѯ���������
	 * @return ��ǰҳ�Ĳ�ѯ���������
	 */
	public String[][] getColumnsHtmlCode() {
		int nTemp = pagesCtrl.getEndRow() - pagesCtrl.getStartRow();
		String[][] strHtmlCode = new String[nTemp][getSequenceColumnCount()];
		for (int i = pagesCtrl.getStartRow(); i < pagesCtrl.getEndRow(); i++) {
			for (int j = 0; j < getSequenceColumnCount(); j++) {
				strHtmlCode[i - pagesCtrl.getStartRow()][j] =
					getSequenceColumnHtmlCode(j, i);
			}
		}
		return strHtmlCode;
	}

	/**
	 * �������д�뵽EXCEL�ļ�
	 * @return �ɹ���־
	 */
	public boolean writeToExcel() {
		File file = null;
		jxl.write.WritableWorkbook wwb = null;
		try {
			String strSavePath =
				PropertyManager.getProperty("query.save.path").trim();
			if (!strSavePath.equals("")
				&& !strSavePath.endsWith(File.separator))
				strSavePath += File.separator;
			log.debug(strSavePath);
			String strTimeStamp =
				FormatDate.format(
					System.currentTimeMillis(),
					"yyyy_MM_dd_HH_mm_ss_S");
			file =
				new File(
					strSavePath + htmlHeader + "_" + strTimeStamp + ".xls");
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			if (file.exists()) {
				log.warn("�ļ��Ѿ����ڣ�");
				File fDest = new File(strSavePath + file.getName() + ".bak");
				if (fDest.exists())
					fDest.delete();
				file.renameTo(fDest);
				file =
					new File(
						strSavePath + htmlHeader + "_" + strTimeStamp + ".xls");
			}
			wwb = jxl.Workbook.createWorkbook(file);
			jxl.write.WritableSheet ws = wwb.createSheet(htmlHeader, 0);
			int nCol = 0;
			int nRow = 0;
			jxl.write.Label labelC = null;
			jxl.write.Number labelN = null;
			jxl.write.NumberFormat[] nf =
				new jxl.write.NumberFormat[getSequenceColumnCount()];
			jxl.write.WritableFont wf = null;
			jxl.write.WritableCellFormat wcf = null;
			String strTemp = "";
			double ddTemp = 0.0;

			wf =
				new jxl.write.WritableFont(
					jxl.write.WritableFont.ARIAL,
					18,
					jxl.write.WritableFont.BOLD,
					true);
			wcf = new jxl.write.WritableCellFormat(wf);
			wcf.setAlignment(jxl.format.Alignment.CENTRE);
			labelC = new jxl.write.Label(nCol, nRow, htmlHeader, wcf);
			ws.addCell(labelC);
			nRow++;

			wf =
				new jxl.write.WritableFont(
					jxl.write.WritableFont.ARIAL,
					12,
					jxl.write.WritableFont.NO_BOLD,
					false);
			wcf = new jxl.write.WritableCellFormat(wf);
			wcf.setBackground(jxl.format.Colour.GREY_25_PERCENT);
			wcf.setAlignment(jxl.format.Alignment.CENTRE);
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
			//д��ͷ
			for (int k = 0; k < getSequenceColumnCount(); k++) {
				if (formula.getColumn(k).isDisplay()) {
					labelC =
						new jxl.write.Label(
							nCol,
							nRow,
							getSequenceColumnLabel(k),
							wcf);
					ws.addCell(labelC);
					nCol++;
				}
			}
			//�ϲ�������
			ws.mergeCells(0, 0, nCol - 1, 0);

			//���еĸ�ʽ
			for (int i = 0; i < nf.length; i++) {
				strTemp = formula.getColumn(i).getColumnNumberPattern();
				if (strTemp != null)
					nf[i] = new jxl.write.NumberFormat(strTemp);
			}
			//д����
			for (int i = pagesCtrl.getStartRow();
				i < pagesCtrl.getEndRow();
				i++) {
				//�Ƿ�Ϊ������
				if (formula.isGroupByCompute() && formula.isGroupByCompute()) {
					if (isIgnoreRow(i)) {
						log.debug("ignore row: " + i);
						continue;
					}
				}

				nRow++;
				nCol = 0;
				boolean isWriteTotal = false;
				for (int j = 0; j < getSequenceColumnCount(); j++) {
					if (formula.getColumn(j).isDisplay()) {
						//�ǲ��Ǽ�����
						if (formula.isGroupByCompute() && isComputeRow(i)) {
							if (formula.getColumn(j).isNumberColumn()) {
								//����
								strTemp = getSequenceColumnDisplayCode(j, i);
								try {
									ddTemp = Double.parseDouble(strTemp);
								} catch (NumberFormatException e) {
									log.error(j + "/" + i + "���ִ���", e);
									ddTemp = 0.0;
								}
								wcf =
									new jxl.write.WritableCellFormat(wf, nf[j]);
								if (getComputeRowDepth(i) == 1) {
									//��ϼ���
									wcf.setBackground(
										jxl.format.Colour.GRAY_50);
								} else {
									//С�ϼ���
									wcf.setBackground(
										jxl.format.Colour.GRAY_25);
								}
								wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
								labelN =
									new jxl.write.Number(
										nCol,
										nRow,
										ddTemp,
										wcf);
								ws.addCell(labelN);
							} else {
								//�ַ�
								wcf = new jxl.write.WritableCellFormat(wf);
								if (getComputeRowDepth(i) == 1) {
									//��ϼ���
									wcf.setBackground(
										jxl.format.Colour.GRAY_50);
								} else {
									//С�ϼ���
									wcf.setBackground(
										jxl.format.Colour.GRAY_25);
								}
								wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
								int n = getComputeRowDepth(i);
								if ((i == getRowCount() - 1)
									&& j <= parseDisplayGroupByNumber()
									&& !isWriteTotal) {
									//sbHtml.append("�ϼ�");
									wcf.setAlignment(
										jxl.format.Alignment.CENTRE);
									labelC =
										new jxl.write.Label(
											nCol,
											nRow,
											"�ϼ�",
											wcf);
									isWriteTotal = true;
								}
								if (j < n) {
									if (n > 1) {
										wcf.setAlignment(
											jxl.format.Alignment.CENTRE);
										labelC =
											new jxl.write.Label(
												nCol,
												nRow,
												getSequenceColumnDisplayCode(
													n - 2,
													i)
													+ "С��",
												wcf);
									}
									ws.addCell(labelC);
									if (j
										< formula
											.getSqlGroupByColumns()
											.length) {
										//�ϲ���Ԫ��
										ws.mergeCells(
											0,
											nRow,
											parseDisplayGroupByNumber() - 1,
											nRow);
										nCol += parseDisplayGroupByNumber() - 1;
									}
									j =
										formula.getSqlGroupByColumns().length
											- 1;
								} else {
									//								sbHtml.append(
									//									getSequenceColumnHtmlCode(j, i).trim());
									labelC =
										new jxl.write.Label(
											nCol,
											nRow,
											getSequenceColumnDisplayCode(j, i),
											wcf);
									ws.addCell(labelC);
								}
							}
						} else {
							//�Ǻϼ���
							if (formula.getColumn(j).isNumberColumn()) {
								//����getSequenceColumnHtmlCode(j, i)
								strTemp = getSequenceColumnDisplayCode(j, i);
								try {
									ddTemp = Double.parseDouble(strTemp);
								} catch (NumberFormatException e) {
									log.error(j + "/" + i + "���ִ���", e);
									ddTemp = 0.0;
								}
								wcf =
									new jxl.write.WritableCellFormat(wf, nf[j]);
								wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
								labelN =
									new jxl.write.Number(
										nCol,
										nRow,
										ddTemp,
										wcf);
								ws.addCell(labelN);
							} else {
								//�ַ�
								wcf = new jxl.write.WritableCellFormat(wf);
								wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
								labelC =
									new jxl.write.Label(
										nCol,
										nRow,
										getSequenceColumnDisplayCode(j, i),
										wcf);
								ws.addCell(labelC);
							}
						}
						nCol++;
					}
				}
			}

			wwb.write();
			wwb.close();
			wwb = null;

		} catch (IOException e) {
			log.error("����Excel�ļ�����", e);
			return false;
		} catch (jxl.write.biff.RowsExceededException e) {
			log.error("����Excel�ļ�����", e);
			return false;
		} catch (jxl.write.WriteException e) {
			log.error("����Excel�ļ�����", e);
			return false;
		} finally {
			if (wwb != null)
				try {
					wwb.close();
				} catch (IOException ioe) {
					log.error("�ر�Excel�ļ�����", ioe);
				}
		}
		return true;
	}
	/**
	 * @return ����ÿ����ʾ�Ŀ��
	 */
	public int[] getColumnDisplaySize() {
		if (columnDisplaySize == null)
			initColumnDisplaySize();
		return columnDisplaySize;
	}

	/**
	 * @return ����ÿ�м������
	 */
	public int[] getComputeDepths() {
		return computeDepths;
	}

	/**
	 * @return ������ʾ����
	 */
	public String[] getDisplayTypes() {
		return displayTypes;
	}

	/**
	 * @param is
	 */
	public void setColumnDisplaySize(int[] is) {
		columnDisplaySize = is;
	}

	/**
	 * @param is
	 */
	public void setComputeDepths(int[] is) {
		computeDepths = is;
	}

	/**
	 * @return �Ƿ�Ҫ���浽Excel�ļ�
	 */
	public boolean isSaveToExcel() {
		return isSaveToExcel;
	}

	/**
	 * @param b
	 */
	public void setSaveToExcel(boolean b) {
		isSaveToExcel = b;
	}

}

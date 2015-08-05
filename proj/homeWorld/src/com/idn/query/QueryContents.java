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
 * <P>定义查询显示内容</P>
 * 
 * @version 0.1
 * @author 苏海军
 */
public class QueryContents {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(QueryContents.class);

	/**
	 * 页面显示的基本属性
	 * <pre>
	 * 		GRID - 以Grid的方式输出查询内容
	 * 		FREEFORM - 以FreeForm的方式输出查询内容
	 * 		ROLL - 以工资条的方式输出查询内容
	 * </pre>
	 */
	private final String[] displayTypes =
		new String[] { "GRID", "FREEFORM", "ROLL" };

	/**
	 * 显示的内容
	 */
	private String resultHtml = null;

	/**
	 * 打印页的相关信息
	 */
	private PrintPageSetup printPageSetup = null;

	/**
	 * 可打印字体的大小
	 */
	private String printAbleFontSize = null;

	/**
	 * 每列要显示的宽度
	 */
	private int[] columnDisplaySize = null;

	/**
	 * 隐藏字段，给FORMBEAN赋值时，与文本属性相同，但用于查询时不显示的列
	 */
	private final int FORM_HIDDEN = 0;

	/**
	 * 文本字段，包括TEXT、TEXTAREA、PASSWORD，按字符给FORMBEAN符值
	 */
	private final int FORM_TEXT = 1;

	/**
	 * 单选按钮字段，包括RADIO、CHECKBOX，给FORMBEAN
	 * 赋值时，包括SELECT、LABEL、VALUES三种属性，SELECT、LBAEL、VALUES
	 * 为一维数组，LBAEL、VALUES仅赋值一次，SELECT为查询列名
	 */
	private final int FORM_SINGLE_CHECKBOX = 2;

	/**
	 * 多选按钮字段，包括CHECKBOX、MULTIBOX，该字段必须为代码，给FORMBEAN
	 * 赋值时，包括SELECT、LABEL、VALUES三种属性，其中SELECT为二维数组，
	 * 上标为行号，下标为实际的值，通过按位与运算得出。LBAEL、VALUES
	 * 为一维数组，仅赋值一次，SELECT为查询列名
	 */
	private final int FORM_MULTIBOX = 3;

	/**
	 * 单选框，包括OPTION、OPTIONCOLLECTION、SELECT，给FORMBEAN
	 * 赋值时，包括SELECT、COLLECTION两种属性，COLLECTION仅仅赋值一次，SELECT为查询列名
	 */
	private final int FORM_SINGLE_SELECT = 4;

	/**
	 * 多选框，包括OPTION、OPTIONS、OPTIONCOLLECTION、SELECT，
	 * 该字段必须为代码，给FORMBEAN赋值时，
	 * 包括SELECT、COLLECTION两种属性，其中SELECT为二维数组，
	 * 上标为行号，下标为实际的值，通过按位与运算得出，
	 * COLLECTION仅赋值一次，SELECT为查询列名
	 * 
	 */
	private final int FORM_SELECT = 5;

	/**
	 * 数据Bean，主要用来处理结果集中的内容
	 */
	private DataBean db = null;

	/**
	 * 各个字段的显示标签，即描述
	 */
	private String[] fieldLabel = null;

	/**
	 * HTML页中页眉标题
	 */
	private String htmlTitle = null;

	/**
	 * HTML页中页标题
	 */
	private String htmlHeader = null;

	/**
	 * 表格通用属性
	 */
	private String tableCommStyle =
		"cellSpacing=\"0\" cellPadding=\"0\" border=\"1\"";

	/**
	 * 表格通用属性
	 */
	private String tablePrintCommStyle =
		"cellPadding=\"2\" border=\"1\" style=\"border-collapse: collapse\" bordercolor=\"#111111\"";

	/**
	 * 打印分页标记
	 */
	private String pageBreak =
		"<br clear=all style='page-break-before:always'>";

	/**
	 * 表格格式类
	 */
	private String tableStyle = null;

	/**
	 * 表格标题格式类
	 */
	private String tableHeaderStyle = null;

	/**
	 * 分组计算结果单元格格式类
	 */
	private String groupTdStyle = null;

	/**
	 * 分组计算结果数字单元格格式类
	 */
	private String groupDigitTdStyle = null;

	/**
	 * 大合计计算结果单元格格式类
	 */
	private String sumTdStyle = null;

	/**
	 * 大合计计算结果数字单元格格式类
	 */
	private String sumDigitTdStyle = null;

	/**
	 * 单元格格式类
	 */
	private String tdStyle = null;

	/**
	 * 数字单元格格式类
	 */
	private String digitTdStyle = null;

	/**
	 * FreeForm中每行所显示的列数
	 */
	private int colsPerRow = 0;

	/**
	 * 单元格中是否要自动换行
	 */
	private boolean isAutoWrap = false;

	/**
	 * 是否为打印页
	 */
	private boolean isPrintPage = false;

	/**
	 * 是否要保存到Excel文件
	 */
	private boolean isSaveToExcel = false;

	/**
	 * 用FreeForm显示查询结果
	 */
	private String freeFormHtml = null;

	/**
	 * 用Grid显示查询结果
	 */
	private String gridHtml = null;

	/**
	 * 用工资条形式显示查询结果
	 */
	private String salaryRollHtml = null;

	/**
	 * 分页控制
	 */
	private PagesController pagesCtrl = null;

	/**
	 * 查询规则及相关信息
	 */
	private QueryFormula formula = null;

	/**
	 * 查询的SQL语名的参数值
	 */
	private String[] sqlParam = null;

	/**
	 * 查询的SQL语句
	 */
	private String querySql = null;

	/**
	 * 查询名称
	 */
	private String queryPrefix = null;

	/**
	 * 每一行的计算深度，如果非计算行则深度为0
	 */
	private int[] computeDepths = null;

	/**
	 * 构造函数
	 */
	public QueryContents() {
		formula = new QueryFormula();
		pagesCtrl = new PagesController();
		printPageSetup = new PrintPageSetup();
	}

	/**
	 * 进行查询前的准备，包括建立查询规则及分面控制器
	 * @throws QueryException 查询异常
	 * @throws MissingParameterException 取参数时出错异常
	 */
	public void prepareQuery()
		throws QueryException, MissingParameterException {
		if (queryPrefix == null)
			throw new QueryException("QueryContents中的 queryPrefix 为空");

		//初始化查询规则
		formula.initContent(queryPrefix);
		printPageSetup.setPageType(formula.getPageType());
		querySql = formula.getSql();
		//生成页面分页控制器
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
				throw new QueryException("准备创建页控制器时，执行SQL语句出错");
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new QueryException("准备创建页控制器时，出现了未被处理的异常");
			}
		}
	}

	/**
	 * 查询，如果带有聚合的SQL查询，则在此函数中构造分页控制器
	 * @throws QueryException 查询异常
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
			//如果需要分页，但SQL中含有Group by，则在此进行初始化分页控制器
			if (formula.isNeedGroupBy() || pagesCtrl.getRowsPerPage() == 0) {
				log.debug("创建分页控制器");
				pagesCtrl.createPagesController(db.getRowCount());
			}

			//如果需要分组计算，则分析分组计算情况
			if (formula.isGroupByCompute()) {
				computeDepths = parseComputeRows();
				pagesCtrl.setGroupCompute(true);
				//重新生成分页器
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
				"结果集行数："
					+ db.getRowCount()
					+ "/分页控制最大行数："
					+ pagesCtrl.getRealRowsCount());
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new QueryException("执行查询出错");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new QueryException("执行查询出现了未能捕捉到的错误");
		}
	}

	/**
	 * 返回当前结果集中的行数
	 * @return 当前结果集中的行数
	 */
	public int getRowCount() {
		return db.getRowCount();
	}

	/**
	 * 返回当前查询的列的个数，如果有分组求合则可能与结果集中的不符，
	 * 因为grouping和组合出来的URL的列将不被统计
	 * @return 在column表中定义的列
	 */
	public int getSequenceColumnCount() {
		return formula.size();
	}

	/**
	 * 返回当前查询的列的个数，如果有分组求合则可能与结果集中的不符，
	 * 因为grouping和组合出来的URL的列将不被统计
	 * @return 在column表中定义的列
	 */
	public int getSequenceDisplayColumnCount() {
		return formula.getDisplayColumnCount();
	}

	/**
	 * 返回当前查询列的个数
	 * @return 查询SQL语句中定义的列个数
	 */
	public int getColumnCount() {
		return db.getColumnCount();
	}

	/**
	 * 显示列的说明信息，在GRID查询中为表头内容
	 * @param sequence 要显示列的序号，从0开始
	 * @return 列的说明信息
	 */
	public String getSequenceColumnLabel(int sequence) {
		return formula.getColumn(sequence).getDisplayName();
	}

	/**
	 * 显示列的说明信息，在GRID查询中为表头内容
	 * @param sequence 要显示列的实际序号
	 * @return 列的说明信息
	 */
	public String getSequenceColumnLabel(String sequence) {
		return formula.getColumn(sequence).getDisplayName();
	}

	/**
	 * 获取指定序列的列的HTML内容，如果为代码，则显示代码值，如果为URL，则转换成URL代码
	 * @param sequence 要显示的列的编号
	 * @param row 该列所在的行号
	 * @return 转换后的列的值
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
		//是不是数需要格式的数字
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
	 * 获取指定序列的列的HTML内容，如果为代码，则显示代码值，如果为URL，则转换成URL代码
	 * @param sequence 要显示的列的编号
	 * @param row 该列所在的行号
	 * @return 转换后的列的值
	 */
	public String getSequenceColumnHtmlCode(String sequence, int row) {
		int i = Integer.parseInt(sequence) - 1;
		return getSequenceColumnHtmlCode(i, row);
	}

	/**
	 * 获取指定序列的列的显示内容，如果为代码，则为代码值
	 * @param sequence 要显示的列的编号
	 * @param row 该列所在的行号
	 * @return 转换后的列的值
	 */
	public String getSequenceColumnDisplayCode(int sequence, int row) {
		String strName = null;
		String strColValue = null;
		String strCclass = null;
		strName = formula.getColumn(sequence).getQueryColumnName();
		strColValue = db.getElementValue(row, strName);
		//是不是代码
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
	 * 获取指定序列的列的显示内容，如果为代码，则为代码值
	 * @param sequence 要显示的列的编号
	 * @param row 该列所在的行号
	 * @return 转换后的列的值
	 */
	public String getSequenceColumnDisplayCode(String sequence, int row) {
		int i = Integer.parseInt(sequence) - 1;
		return getSequenceColumnDisplayCode(i, row);
	}
	/**
	 * 从结果集中取出对应显示序号的列的内容
	 * @param sequence 显示的序号
	 * @param row 指定的行
	 * @return 该列在指定行的值
	 */
	public String getSequenceVaulue(String sequence, int row) {
		String strColumnName = formula.getColumn(sequence).getQueryColumnName();
		return db.getElementValue(row, strColumnName).trim();
	}

	/**
	 * 从结果集中取出对应显示序号的列的内容
	 * @param sequence 显示的序号
	 * @param row 指定的行
	 * @return 该列在指定行的值
	 */
	public String getSequenceVaulue(int sequence, int row) {
		String strColumnName = formula.getColumn(sequence).getQueryColumnName();
		return db.getElementValue(row, strColumnName).trim();
	}

	/**
	 * 返回指定列、行的值
	 * @param col 指定的列
	 * @param row 指定的行
	 * @return 该列在指定行的值
	 */
	public String getFieldValue(String col, int row) {
		return db.getElementValue(row, col);
	}

	/**
	 * 显示类型的数字表示
	 * @return 显示类型的数字表示
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
		log.warn("要显示类型为：" + formula.getForwardPage() + "，但系统不支持，将以GRID方式来显示");
		return 0;
	}
	/**
	 * 返回指定列、行的值
	 * @param col 指定的列
	 * @param row 指定的行
	 * @return 该列在指定行的值
	 */
	public String getFieldValue(int col, int row) {
		return db.getElementValue(row, col);
	}

	/**
	 * 将查询结果以工资条的形式返回，表格显示属性由tableStyle和tdStyle决定
	 * @return 生成后的HTML代码
	 */
	public String renderSalaryRollHtml() {
		try {
			if (getRowCount() == 0)
				return "查询无内容";

			//输出到EXCEL文件
			if (isSaveToExcel) {
				if (writeToExcel())
					return "保存成功";
				return "<Font color = red>保存失败</Font>";
			}

			//处理表格的显示风格，对空的属性赋默认值
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
				return "没有设置打印参数，不能生成打印页";
			}
			//处理默认值结束
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
			int l = 0; //实际输出的行数
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
					//写分页符
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
				//写表头
				//定表头	
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
				//写表体
				sbHtml.append("<tr>");
				boolean isWriteTotal = false;
				for (int j = 0; j < getSequenceColumnCount(); j++) {
					if (formula.getColumn(j).isDisplay()) {
						sbHtml.append("<td");
						//是不是计算行
						if (formula.isGroupByCompute() && isComputeRow(i)) {
							//大合计行
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
								//小计行
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
								sbHtml.append("合计");
								isWriteTotal = true;
							}
							if (j < k) {
								if (k > 1)
									sbHtml.append(
										getSequenceColumnHtmlCode(k - 2, i)
											.trim())
											.append(
										"小计");
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
				//表格结束
				sbHtml.append("</tbody></table>");
				//加空表格
				sbHtml.append("<table border=0><tr><td></td></tr></table>");
			}
			//添加分页代码
			if (getPagesCtrl().getRowsPerPage() > 0)
				sbHtml.append(getPagesCtrl().getControlPageCodes());
			if (!isPrintPage) {
				if (formula.isNeedPrint())
					sbHtml
						.append("<A href=\"")
						.append(getPagesCtrl().getBaseUrl())
						.append("?printPage=true&pageName=%2Fquery%2FsalaryRollPrint.jsp&")
						.append(getPagesCtrl().getQueryParametersURL())
						.append("\" target=\"_printPage\">显示可打印页面</A>&nbsp;&nbsp;&nbsp;&nbsp;");
				//添加另存为EXCEL文件
				sbHtml
					.append("<A href=\"")
					.append(getPagesCtrl().getBaseUrl())
					.append("?saveToExcel=true&")
					.append(getPagesCtrl().getQueryParametersURL())
					.append("\">另存为EXCEL</A>");
			}
			return sbHtml.toString();
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			db = null;
			return "内容有错误！";
		} finally {
			if (isPrintPage) {
				//还原表格属性
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
	 * 将查询结果以Grid的表格形式返回，表格显示属性由tableStyle和tdStyle决定
	 * @return 生成后的HTML代码
	 */
	public String renderGridHtml() {
		try {
			if (getRowCount() == 0)
				return "查询无内容";
			//输出到EXCEL文件
			if (isSaveToExcel) {
				if (writeToExcel())
					return "保存成功";
				return "<Font color = red>保存失败</Font>";
			}
			StringBuffer sbHtml = new StringBuffer();

			//处理表格的显示风格，对空的属性赋默认值
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
			//处理默认值结束

			if (tableHeaderStyle == null)
				tableHeaderStyle = tdStyle;
			if (groupTdStyle == null)
				groupTdStyle = tdStyle;
			String strTempTableStyle = tableCommStyle;
			if (isPrintPage && printPageSetup.size() == 0) {
				isPrintPage = false;
				return "没有设置打印参数，不能生成打印页";
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
			//写表头
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
			int l = 0; //实际输出的行数
			if (isPrintPage && printAbleFontSize == null)
				printAbleFontSize = getPrintAbleFontSize();
			//写表体
			for (int i = pagesCtrl.getStartRow();
				i < pagesCtrl.getEndRow();
				i++) {
				//是否为忽略行
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
					//结束表格
					sbHtml.append("</tbody></table>");

					//写分页符
					sbHtml.append(pageBreak);
					//定表头	
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
					//写打印表头
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
						//是不是计算行
						if (formula.isGroupByCompute() && isComputeRow(i)) {
							//大合计行
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
								//小计行
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
								sbHtml.append("合计");
								isWriteTotal = true;
							}
							if (j < n) {
								if (n > 1)
									sbHtml.append(
										getSequenceColumnHtmlCode(n - 2, i)
											.trim())
											.append(
										"小计");
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
			//表格结束
			sbHtml.append("</tbody></table>");
			//添加分页代码
			if (getPagesCtrl().getRowsPerPage() > 0)
				sbHtml.append(getPagesCtrl().getControlPageCodes());
			if (!isPrintPage) {
				if (formula.isNeedPrint())
					sbHtml
						.append("<A href=\"")
						.append(getPagesCtrl().getBaseUrl())
						.append("?printPage=true&pageName=%2Fquery%2FgridPrint.jsp&")
						.append(getPagesCtrl().getQueryParametersURL())
						.append("\" target=\"_printPage\">显示可打印页面</A>&nbsp;&nbsp;&nbsp;&nbsp;");
				//添加另存为EXCEL文件
				sbHtml
					.append("<A href=\"")
					.append(getPagesCtrl().getBaseUrl())
					.append("?saveToExcel=true&")
					.append(getPagesCtrl().getQueryParametersURL())
					.append("\">另存为EXCEL</A>");
			}
			return sbHtml.toString();
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			db = null;
			return "内容有错误！";
		} finally {
			if (isPrintPage) {
				//还原表格属性
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
	 * 判断是否为计算出来的行
	 * @param row 结果集中指定的行
	 * @return 计算出来的行，则返回true
	 */
	public boolean isComputeRow(int row) {
		return (computeDepths[row] > 0);
	}

	/**
	 * 计算的深度
	 * @param row 结果集中指定的行
	 * @return 计算的深度
	 */
	public int getComputeRowDepth(int row) {
		return computeDepths[row];
	}

	/**
	 * 计算的深度
	 * @param row 结果集中指定的行
	 * @return 计算的深度
	 */
	public boolean isIgnoreRow(int row) {
		return computeDepths[row] > formula.getGroupByComputeDepth();
	}

	/**
	 * 分析计算行
	 * @return 每一行的计算深度
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
	 * 分析GROUPBY列中可显示的列数
	 * @return GROUPBY列中可显示的列数
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
	 * 将查询结果以FreeForm的表格形式返回，表格显示属性由tableStyle和tdStyle决定
	 * 表格列数由colPerRow决定
	 * @return 生成后的HTML代码
	 */
	public String renderFreeFormHtml() {
		try {
			if (getRowCount() == 0)
				return "查询无内容";

			//处理表格的显示风格，对空的属性赋默认值
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
			//处理默认值结束

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
				//写显示列的数目
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
						//是否换行
						if (k % colsPerRow == 0
							&& k >= colsPerRow
							&& j < getSequenceDisplayColumnCount() - 1) {
							sbHtml.append("</tr><tr>");
						}
					}

				}
				//补充单元格
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
				//表格结束
				sbHtml.append("</tbody></table><br>");
			}
			//添加分页代码
			if (getPagesCtrl().getRowsPerPage() > 0)
				sbHtml.append(getPagesCtrl().getControlPageCodes());
			if (!isPrintPage) {
				if (formula.isNeedPrint())
					sbHtml
						.append("<A href=\"")
						.append(getPagesCtrl().getBaseUrl())
						.append("?printPage=true&pageName=printPage&")
						.append(getPagesCtrl().getQueryParametersURL())
						.append("\">显示可打印页面</A>&nbsp;&nbsp;&nbsp;&nbsp;");
				//添加另存为EXCEL文件
				sbHtml
					.append("<A href=\"")
					.append(getPagesCtrl().getBaseUrl())
					.append("?saveToExcel=true&")
					.append(getPagesCtrl().getQueryParametersURL())
					.append("\">另存为EXCEL</A>");
			}
			return sbHtml.toString();
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			db = null;
			return "内容有错误！";
		}
	}

	/**
	 * @return DataBean 对象
	 */
	public DataBean getDb() {
		return db;
	}

	/**
	 * @return PagesController 对象
	 */
	public PagesController getPagesCtrl() {
		return pagesCtrl;
	}

	/**
	 * @return 查询名称
	 */
	public String getQueryPrefix() {
		return queryPrefix;
	}

	/**
	 * @return 查询的SQL语句
	 */
	public String getQuerySql() {
		return querySql;
	}

	/**
	 * @return SQL语句的参数数组
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
	 * @return QueryFormula 规则
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
	 * @return HTML页HEADER
	 */
	public String getHtmlHeader() {
		if (htmlHeader == null)
			htmlHeader = formula.getHeader().trim();
		return htmlHeader;
	}

	/**
	 * @return HTML页标题
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
	 * @return 返回freeFormHtml代码
	 */
	public String getFreeFormHtml() {
		freeFormHtml = renderFreeFormHtml();
		return freeFormHtml;
	}

	/**
	 * @return 返回gridHtml代码 
	 */
	public String getGridHtml() {
		gridHtml = renderGridHtml();
		return gridHtml;
	}

	/**
	 * @return 是否折行
	 */
	public boolean isAutoWrap() {
		return isAutoWrap;
	}

	/**
	 * @return 工资条代码
	 */
	public String getSalaryRollHtml() {
		salaryRollHtml = renderSalaryRollHtml();
		return salaryRollHtml;
	}

	/**
	 * @return 表格格式类
	 */
	public String getTableStyle() {
		return tableStyle;
	}

	/**
	 * @return 单元格格式类
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
	 * @return 每行有多少列
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
	 * @return 返回各查询列的标注
	 */
	public String[] getFieldLabel() {
		fieldLabel = new String[getSequenceColumnCount()];
		for (int i = 0; i < fieldLabel.length; i++) {
			fieldLabel[i] = getSequenceColumnLabel(i);
		}
		return fieldLabel;
	}

	/**
	 * @return 表格标题
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
	 * @return 分组计算结果单元格格式类
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
	 * @return 数字单元格格式类
	 */
	public String getDigitTdStyle() {
		return digitTdStyle;
	}

	/**
	 * @return 分组计算数字单元格格式类
	 */
	public String getGroupDigitTdStyle() {
		return groupDigitTdStyle;
	}

	/**
	 * @return 大合计数字格式类
	 */
	public String getSumDigitTdStyle() {
		return sumDigitTdStyle;
	}

	/**
	 * @return 大合计格式类
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
	 * @return 表格通用属性
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
	 * @return 分页符标记
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
	 * @return 是否为打印页
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
	 * @return 打印页的相关信息
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
	 * @return 查询结果的HTML表格显示
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
	 * 计算每行显示的字数
	 * @return 每行显示可包含的最大字数
	 */
	private int statisticLineLength() {
		int countColumn = 1;
		for (int i = 0; i < formula.size(); i++) {
			if (formula.getColumn(i).isDisplay()) {
				countColumn = countColumn + getColumnDisplaySize()[i];
			}
		}
		log.debug("每行的字数：" + countColumn);
		return countColumn + getColumnDisplaySize().length;
	}

	/**
	 * 初始化每列显示的大小 
	 */
	private void initColumnDisplaySize() {
		columnDisplaySize = new int[formula.size()];
		DataBean databean = new DataBean();
		DynaSqlBean dynasql = new DynaSqlBean();
		String strColValue;
		String strPattern;
		if (!formula.isNeedGroupBy()) {
			//不需要分组计算，则查找最大数字值
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
			//字符类型，则直接取其长度
			if (formula.getColumn(i).getColumnNumberPattern() == null) {
				columnDisplaySize[i] = formula.getColumn(i).getDisplaySize();
			} else {
				if (formula.isNeedGroupBy()) {
					if (formula.isGroupByCompute()) {
						//如果为计算值，则按最后一行长度值来显示
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
						//分组查询 
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
				//如果实际长度小于表头长度则取表头长度
				columnDisplaySize[i] =
					formula.getColumn(i).getLabelDisplaySize();
			}
		}
	}

	/**
	 * @return 可打印字体的大小
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
					log.debug("默认字体不能满足打印，查找可打印字体");
					int nTemp = 0;
					int nComp = 0;
					//查找最合适的字体
					boolean isFind = false;
					for (int i = 0;
						i < printPageSetup.getFontSize().length;
						i++) {
						nTemp =
							printPageSetup
								.getPrintParameter(i)
								.getLineWordCount();
						if (nTemp > nLength) {
							//可接受的字体
							if (nComp == 0) {
								isFind = true;
								nComp = nTemp;
								printAbleFontSize =
									printPageSetup.getFontSize()[i];
							} else if (nTemp < nComp) {
								//最接近的字体
								nComp = nTemp;
								printAbleFontSize =
									printPageSetup.getFontSize()[i];
							}
						}
					}
					if (!isFind) {
						log.warn("没有找到可打印字体大小");
						printAbleFontSize = "9";
					}
					log.debug("选择的打印字体为：" + printAbleFontSize);
				}
			} catch (QueryException e) {
				log.error(e.getMessage(), e);
			}
		}
		return printAbleFontSize;
	}

	/**
	 * 根据字体大小，决定显示单元格像素放大比例
	 * @return 单元格像素放大比例
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
	 * 当前页的查询结果集内容
	 * @return 当前页的查询结果集内容
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
	 * 将结果集写入到EXCEL文件
	 * @return 成功标志
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
				log.warn("文件已经存在！");
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
			//写表头
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
			//合并标题行
			ws.mergeCells(0, 0, nCol - 1, 0);

			//各列的格式
			for (int i = 0; i < nf.length; i++) {
				strTemp = formula.getColumn(i).getColumnNumberPattern();
				if (strTemp != null)
					nf[i] = new jxl.write.NumberFormat(strTemp);
			}
			//写表体
			for (int i = pagesCtrl.getStartRow();
				i < pagesCtrl.getEndRow();
				i++) {
				//是否为忽略行
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
						//是不是计算行
						if (formula.isGroupByCompute() && isComputeRow(i)) {
							if (formula.getColumn(j).isNumberColumn()) {
								//数字
								strTemp = getSequenceColumnDisplayCode(j, i);
								try {
									ddTemp = Double.parseDouble(strTemp);
								} catch (NumberFormatException e) {
									log.error(j + "/" + i + "数字错误", e);
									ddTemp = 0.0;
								}
								wcf =
									new jxl.write.WritableCellFormat(wf, nf[j]);
								if (getComputeRowDepth(i) == 1) {
									//大合计行
									wcf.setBackground(
										jxl.format.Colour.GRAY_50);
								} else {
									//小合计行
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
								//字符
								wcf = new jxl.write.WritableCellFormat(wf);
								if (getComputeRowDepth(i) == 1) {
									//大合计行
									wcf.setBackground(
										jxl.format.Colour.GRAY_50);
								} else {
									//小合计行
									wcf.setBackground(
										jxl.format.Colour.GRAY_25);
								}
								wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
								int n = getComputeRowDepth(i);
								if ((i == getRowCount() - 1)
									&& j <= parseDisplayGroupByNumber()
									&& !isWriteTotal) {
									//sbHtml.append("合计");
									wcf.setAlignment(
										jxl.format.Alignment.CENTRE);
									labelC =
										new jxl.write.Label(
											nCol,
											nRow,
											"合计",
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
													+ "小计",
												wcf);
									}
									ws.addCell(labelC);
									if (j
										< formula
											.getSqlGroupByColumns()
											.length) {
										//合并单元格
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
							//非合计行
							if (formula.getColumn(j).isNumberColumn()) {
								//数字getSequenceColumnHtmlCode(j, i)
								strTemp = getSequenceColumnDisplayCode(j, i);
								try {
									ddTemp = Double.parseDouble(strTemp);
								} catch (NumberFormatException e) {
									log.error(j + "/" + i + "数字错误", e);
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
								//字符
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
			log.error("生成Excel文件出错", e);
			return false;
		} catch (jxl.write.biff.RowsExceededException e) {
			log.error("生成Excel文件出错", e);
			return false;
		} catch (jxl.write.WriteException e) {
			log.error("生成Excel文件出错", e);
			return false;
		} finally {
			if (wwb != null)
				try {
					wwb.close();
				} catch (IOException ioe) {
					log.error("关闭Excel文件出错", ioe);
				}
		}
		return true;
	}
	/**
	 * @return 返回每列显示的宽度
	 */
	public int[] getColumnDisplaySize() {
		if (columnDisplaySize == null)
			initColumnDisplaySize();
		return columnDisplaySize;
	}

	/**
	 * @return 返回每行计算深度
	 */
	public int[] getComputeDepths() {
		return computeDepths;
	}

	/**
	 * @return 返回显示类型
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
	 * @return 是否要保存到Excel文件
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

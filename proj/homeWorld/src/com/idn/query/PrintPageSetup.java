/*
 * @(#)PrintPageSetup.java  2003-6-19
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
 * <P> 打印查询结果的参数设置 </P>
 * 
 * @version 0.1
 * @author navy
 */
public class PrintPageSetup {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(PrintPageSetup.class);

	/**
	 * 默认的字体大小
	 */
	private String defaluFontSize = "14";

	/**
	 * 设置页面打印属性集合，key为fontSize
	 */
	private Hashtable hsPrintParameters = null;

	/**
	 * 页面打印属性集合中所有字体大小的信息
	 */
	private String[] fontSize = null;

	/**
	 * 查询选择的打印纸类型
	 */
	private String pageType = null;

	/**
	 * 构造函数，没有对pageType设置
	 */
	public PrintPageSetup() {
		this.hsPrintParameters = new Hashtable();
	}

	/**
	 * 构造函数，对pageType设置，并初始化打印页面属性集合
	 * @param pageType 打印纸类型
	 */
	public PrintPageSetup(String pageType) {
		this.pageType = pageType;
		this.hsPrintParameters = new Hashtable();
		initPrintParameter();
	}

	/**
	 * 初始化打印的参数
	 */
	private void initPrintParameter() {
		DataBean db = new DataBean();
		DynaSqlBean dsb = new DynaSqlBean();

		//SALAPAR: 已经存的任意KEY
		//SALADIV: 已经存的任意KEY
		//SALAVAL1: 纸张类型
		//SALAVAL2: 参数值
		//SALADATE: 编号 0000字号01-每行字数
		//				 0000字号02-首页行数
		//				 0000字号03-其它页行数
		//				 0000字号04-字体大小
		String strSql =
			"SELECT SALAVAL1, "
				+ "SALAVAL2, "
				+ "SALADATE "
				+ "FROM SALARYPA "
				+ "WHERE SALADATE<'00009000' AND SALAVAL1=? "
				+ "ORDER BY SALAVAL1, SALADATE";
		dsb.setSql(strSql);
		dsb.setParam(pageType);
		try {
			db.setCrs(dsb.executeQuery());
			log.debug("在参数表中取出纸张参数记录条数：" + db.getRowCount());
			//给参数赋值
			fontSize = new String[db.getRowCount() / 4];
			for (int i = 0; i < db.getRowCount(); i += 4) {
				PrintParameter printParameter = new PrintParameter();
				try {
					log.debug(
						pageType
							+ "类纸"
							+ db.getElementValue(i + 3, "SALAVAL2").trim()
							+ "号字每行可打印字数："
							+ db.getElementValue(i, "SALAVAL2").trim());
					printParameter.setLineWordCount(
						Integer.parseInt(
							db.getElementValue(i, "SALAVAL2").trim()));
				} catch (NumberFormatException nfe) {
					printParameter.setLineWordCount(80);
					log.warn("每行的字数错误，设置为80");
					log.warn(db.getElementValue(i, "SALAVAL2").trim(), nfe);
				}

				try {
					log.debug(
						pageType
							+ "类纸"
							+ db.getElementValue(i + 3, "SALAVAL2").trim()
							+ "号字首页的行数："
							+ db.getElementValue(i + 1, "SALAVAL2").trim());
					printParameter.setFirstPageRows(
						Integer.parseInt(
							db.getElementValue(i + 1, "SALAVAL2").trim()));
				} catch (NumberFormatException nfe) {
					printParameter.setFirstPageRows(30);
					log.warn("首页的行数错误，设置为30");
					log.warn(db.getElementValue(i + 1, "SALAVAL2").trim(), nfe);
				}

				try {
					log.debug(
						pageType
							+ "类纸"
							+ db.getElementValue(i + 3, "SALAVAL2").trim()
							+ "号字其它页的行数："
							+ db.getElementValue(i + 2, "SALAVAL2").trim());
					printParameter.setOtherPageRows(
						Integer.parseInt(
							db.getElementValue(i + 2, "SALAVAL2").trim()));
				} catch (NumberFormatException nfe) {
					printParameter.setOtherPageRows(30);
					log.warn("每页的行数错误，设置为30");
					log.warn(db.getElementValue(i + 2, "SALAVAL2").trim(), nfe);
				}
				fontSize[i / 4] = db.getElementValue(i + 3, "SALAVAL2").trim();
				log.debug(fontSize[i / 4]);
				hsPrintParameters.put(
					db.getElementValue(i + 3, "SALAVAL2").trim(),
					printParameter);
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

	}

	/**
	 * 返回多少种字体可供选择
	 * @return 可代选择的字体
	 */
	public int size() {
		return hsPrintParameters.size();
	}

	/**
	 * 返回默认字体在属性集合中的位置
	 */
	public int getCurrentPosition() {
		for (int i = 0; i < size(); i++) {
			if (fontSize[i].equals(defaluFontSize))
				return i;
		}
		return 0;
	}

	/**
	 * 返回指定的打印参数类
	 * @param i 指定的位置
	 * @return 打印参数类
	 */
	public PrintParameter getPrintParameter(int i) throws QueryException {
		PrintParameter pp = (PrintParameter) hsPrintParameters.get(fontSize[i]);
		if (pp == null) {
			throw new QueryException("纸张参数集合为空！不能进行打印");
		}
		return pp;
	}

	/**
	 * 返回指定的打印参数类
	 * @param strFontSize 指定的字体大小
	 * @return 打印参数类
	 */
	public PrintParameter getPrintParameter(String strFontSize)
		throws QueryException {
		PrintParameter pp = (PrintParameter) hsPrintParameters.get(strFontSize);
		if (pp == null) {
			throw new QueryException("纸张参数集合为空！不能进行打印");
		}
		return pp;
	}

	/**
	 * @return 默认字体大小
	 */
	public String getDefaluFontSize() {
		return defaluFontSize;
	}

	/**
	 * @return 字体大小的集合
	 */
	public String[] getFontSize() {
		return fontSize;
	}

	/**
	 * @return 字体参数集合
	 */
	public Hashtable getHsPrintParameters() {
		if (hsPrintParameters == null || hsPrintParameters.size() == 0)
			log.error("纸张参数集合为空！不能进行打印");
		return hsPrintParameters;
	}

	/**
	 * @return 打印页的类型
	 */
	public String getPageType() {
		return pageType;
	}

	/**
	 * @param string
	 */
	public void setDefaluFontSize(String string) {
		defaluFontSize = string;
	}

	/**
	 * @param strings
	 */
	public void setFontSize(String[] strings) {
		fontSize = strings;
	}

	/**
	 * @param hashtable
	 */
	public void setHsPrintParameters(Hashtable hashtable) {
		hsPrintParameters = hashtable;
	}

	/**
	 * @param string
	 */
	public void setPageType(String string) {
		pageType = string;
		initPrintParameter();
	}

}

/*
 * @(#)PrintController.java  2003-9-1
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.print;

import com.idn.property.SystemProperties;

//import commsearch.CommActQuery;

/**
 * <P>打印逻辑程序，负责生成可打印页或Excel文件</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PrintController {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(PrintController.class);

	/**
	 * 查询ID
	 */
	private String m_queryId = null;

	/**
	 * 查询的SQL语句
	 */
	private String m_sqlWhere = null;

	/**
	 * 文件保存路径
	 */
	private String m_savePath = null;

	/**
	 * 文件名称，默认为查询ID + 系统时间（毫秒）
	 */
	private String m_fileName = null;

	/**
	 * HTML内容显示
	 */
	private PrintHtmlView m_printHtmlView = null;

	/**
	 * 查询的结果
	 */
	private Contents m_contents = null;

	/**
	 * 实例化
	 * @param strQueryId 查询ID
	 * @param strSqlWhere 查询的附加Where条件
	 */
	public PrintController(String strQueryId, String strSqlWhere) {
		m_queryId = strQueryId;
		m_sqlWhere = strSqlWhere;
		init();
	}

	/**
	 * 实例化
	 * @param strQueryId 查询ID
	 * @param strSqlWhere 查询的附加Where条件
	 */
	public PrintController(
		String strQueryId,
		String strSqlWhere,
		String strSavePath) {
		m_queryId = strQueryId;
		m_sqlWhere = strSqlWhere;
		m_savePath = strSavePath;
		init();
	}

	/**
	 * 初始化打印内容
	 */
	private void init() {
//		CommActQuery cq = new CommActQuery(m_queryId, m_sqlWhere);
//		
//		m_contents = new Contents();
//		m_contents.setArrayContents(cq.getData());
//		m_contents.setTitle(cq.getTablename());
//
//		for (int i = 0; i < cq.getFieldsch().length; i++) {
//			Column column = new Column();
//			column.setIndex(i);
//			column.setLabel(cq.getFieldsch()[i]);
//			column.setType(cq.getFieldstype()[i]);
//			column.setLength(Integer.parseInt(cq.getFieldslen()[i]));
//			column.setDecimalDigits(Integer.parseInt(cq.getFieldsdigits()[i]));
//			column.setSeq(cq.getFieldsseq()[i]);
//			column.setShow(cq.getFieldsqsattri()[i].trim().equals("1"));
//			m_contents.addColumn(column);
//		}

	}
	
	/**
	 * 保存到Excel文件
	 * @return 保存是否成功
	 */
	public void saveExcel() throws PrintException {
		if (m_fileName == null) {
			m_fileName = m_queryId + System.currentTimeMillis() + ".XLS";
		}
		
		if (!m_fileName.toUpperCase().endsWith(".XLS"))
			m_fileName += ".XLS";
		if (m_savePath == null)
			m_savePath = SystemProperties.FILE_SEPARATOR;
		m_savePath = m_savePath.trim();
		if (m_savePath.endsWith(SystemProperties.FILE_SEPARATOR))
			m_savePath += SystemProperties.FILE_SEPARATOR;
		String strFile = m_savePath + m_fileName;
		SaveToExcel ste = new SaveToExcel(m_contents, strFile);
		ste.save();
	}

	/**
	 * @return 返回文件名称
	 */
	public String getFileName() {
		return m_fileName;
	}

	/**
	 * @return 返回查询ID
	 */
	public String getQueryId() {
		return m_queryId;
	}

	/**
	 * @return 返回保存路径
	 */
	public String getSavePath() {
		return m_savePath;
	}

	/**
	 * @return 返回SQL语句附加WHERE语句
	 */
	public String getSqlWhere() {
		return m_sqlWhere;
	}

	/**
	 * @param string
	 */
	public void setFileName(String string) {
		m_fileName = string;
	}

	/**
	 * @param string
	 */
	public void setQueryId(String string) {
		m_queryId = string;
	}

	/**
	 * @param string
	 */
	public void setSavePath(String string) {
		m_savePath = string;
	}

	/**
	 * @param string
	 */
	public void setSqlWhere(String string) {
		m_sqlWhere = string;
	}

	/**
	 * @return PrintHtmlView
	 */
	public PrintHtmlView getPrintHtmlView() {
		return m_printHtmlView;
	}

	/**
	 * @param view
	 */
	public void setPrintHtmlView(PrintHtmlView view) {
		m_printHtmlView = view;
	}

}

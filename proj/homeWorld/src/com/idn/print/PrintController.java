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
 * <P>��ӡ�߼����򣬸������ɿɴ�ӡҳ��Excel�ļ�</P>
 * 
 * @version 0.1
 * @author navy
 */
public class PrintController {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(PrintController.class);

	/**
	 * ��ѯID
	 */
	private String m_queryId = null;

	/**
	 * ��ѯ��SQL���
	 */
	private String m_sqlWhere = null;

	/**
	 * �ļ�����·��
	 */
	private String m_savePath = null;

	/**
	 * �ļ����ƣ�Ĭ��Ϊ��ѯID + ϵͳʱ�䣨���룩
	 */
	private String m_fileName = null;

	/**
	 * HTML������ʾ
	 */
	private PrintHtmlView m_printHtmlView = null;

	/**
	 * ��ѯ�Ľ��
	 */
	private Contents m_contents = null;

	/**
	 * ʵ����
	 * @param strQueryId ��ѯID
	 * @param strSqlWhere ��ѯ�ĸ���Where����
	 */
	public PrintController(String strQueryId, String strSqlWhere) {
		m_queryId = strQueryId;
		m_sqlWhere = strSqlWhere;
		init();
	}

	/**
	 * ʵ����
	 * @param strQueryId ��ѯID
	 * @param strSqlWhere ��ѯ�ĸ���Where����
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
	 * ��ʼ����ӡ����
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
	 * ���浽Excel�ļ�
	 * @return �����Ƿ�ɹ�
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
	 * @return �����ļ�����
	 */
	public String getFileName() {
		return m_fileName;
	}

	/**
	 * @return ���ز�ѯID
	 */
	public String getQueryId() {
		return m_queryId;
	}

	/**
	 * @return ���ر���·��
	 */
	public String getSavePath() {
		return m_savePath;
	}

	/**
	 * @return ����SQL��丽��WHERE���
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

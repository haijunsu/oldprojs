/*
 * @(#)PrintForm.java  2003-9-8
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package printsystem;

import org.apache.struts.action.ActionForm;

/**
 * <P> ��ӡFORM </P>
 * 
 * @version 0.1
 * @author navy
 */
public class PrintForm extends ActionForm {

	/**
	 * �������ͣ�<br>
	 *   save -- ����ΪExcel�ļ�<br>
	 *   print -- ��ӡ��ѯ���<br>
	 *   view -- ��ʾ��ѯ���<br>
	 */
	private String m_action = null;

	/**
	 * ��ѯID
	 */
	private String m_queryid = null;

	/**
	 * SQL���ĸ�������
	 */
	private String m_sqlwhere = null;

	/**
	 * ���ɺ��HTML����
	 */
	private String m_htmlContents = null;
	
	/**
	 * ��ҳ����
	 */
	private String m_htmlTitle = null;

	/**
	 * ���캯��
	 */
	public PrintForm() {
		super();
	}

	/**
	 * @return HTML����
	 */
	public String getHtmlContents() {
		return m_htmlContents;
	}

	/**
	 * @return ��ѯID
	 */
	public String getQueryid() {
		return m_queryid;
	}

	/**
	 * @return SQL���Ӳ�ѯ����
	 */
	public String getSqlwhere() {
		return m_sqlwhere;
	}

	/**
	 * @param string
	 */
	public void setHtmlContents(String string) {
		m_htmlContents = string;
	}

	/**
	 * @param string
	 */
	public void setQueryid(String string) {
		m_queryid = string;
	}

	/**
	 * @param string
	 */
	public void setSqlwhere(String string) {
		m_sqlwhere = string;
	}

	/**
	 * @return ��������
	 */
	public String getAction() {
		return m_action;
	}

	/**
	 * @param string
	 */
	public void setAction(String string) {
		m_action = string;
	}

	/**
	 * @return ��ҳ����
	 */
	public String getHtmlTitle() {
		return m_htmlTitle;
	}

	/**
	 * @param string
	 */
	public void setHtmlTitle(String string) {
		m_htmlTitle = string;
	}

}

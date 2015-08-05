/*
 * @(#)PrintForm.java  2003-9-8
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package printsystem;

import org.apache.struts.action.ActionForm;

/**
 * <P> 打印FORM </P>
 * 
 * @version 0.1
 * @author navy
 */
public class PrintForm extends ActionForm {

	/**
	 * 操作类型：<br>
	 *   save -- 保存为Excel文件<br>
	 *   print -- 打印查询结果<br>
	 *   view -- 显示查询结果<br>
	 */
	private String m_action = null;

	/**
	 * 查询ID
	 */
	private String m_queryid = null;

	/**
	 * SQL语句的附加条件
	 */
	private String m_sqlwhere = null;

	/**
	 * 生成后的HTML代码
	 */
	private String m_htmlContents = null;
	
	/**
	 * 网页标题
	 */
	private String m_htmlTitle = null;

	/**
	 * 构造函数
	 */
	public PrintForm() {
		super();
	}

	/**
	 * @return HTML内容
	 */
	public String getHtmlContents() {
		return m_htmlContents;
	}

	/**
	 * @return 查询ID
	 */
	public String getQueryid() {
		return m_queryid;
	}

	/**
	 * @return SQL附加查询条件
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
	 * @return 操作类型
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
	 * @return 网页标题
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

/*
 * @(#)PageNavigationBean.java  May 27, 2006
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.web.Pagination;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.MessageResources;

import com.navy.framework.web.action.BaseForm;
import com.navy.framework.web.data.LabelValueBean;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author: tt
 * @version: $Revision$
 */
public class PageNavigationBean {

	/**
	 * Logger for this class
	 */
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(PageNavigationBean.class);

	public static final String BUTTON_ALIGN_RIGHT = "right";

	public static final String BUTTON_ALIGN_LEFT = "left";

	public static final String BUTTON_ALIGN_CENTER = "center";

	public static final String METHOD_GET = "get";

	public static final String METHOD_POST = "post";

	private static final int MAX_PAGE_SELECTED = 100;

	private static final String LABEL_QUERY_TOTAL_RECORDS = "label.query.total.records";

	private static final String LABEL_QUERY_TOTAL_PAGE = "label.query.total.page";

	private static final String LABEL_QUERY_SKIP = "label.query.skip";

	private static final String LABEL_QUERY_PAGE = "label.query.page";

	private static final String BUTTON_PREVIOUS_PAGE = "button.previouspage";

	private static final String BUTTON_NEXT_PAGE = "button.nextpage";

	private static final String HTML_BLANK = "&nbsp;";

	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	private String m_buttonAlign = BUTTON_ALIGN_RIGHT;

	private String m_formAction;

	private String m_actionType;

	private String m_formName = "pageNavForm";

	private String m_formMethod = METHOD_GET;

	private String m_target;

	private List m_properties = null;

	private HttpServletRequest m_request;

	private BaseForm m_form;

	private PageListBean m_listBean;

	private MessageResources m_resources;

	private Locale m_locale;

	public PageNavigationBean(HttpServletRequest req, BaseForm form,
			PageListBean listBean, MessageResources resources, Locale locale) {
		this.m_request = req;
		this.m_form = form;
		this.m_listBean = listBean;
		this.m_properties = new ArrayList();
		this.m_actionType = form.getActionType();
		this.m_formAction = req.getRequestURI();
		this.m_resources = resources;
		this.m_locale = locale;
	}

	private String getMessageByKey(String key) {
		return m_resources.getMessage(m_locale, key);
	}

	private String createFromProperty(String name, String value) {
		StringBuffer _buffer = new StringBuffer(
				"<input type=\"hidden\" name=\"");
		_buffer.append(name.trim()).append("\" value=\"").append(
				StringUtils.isBlank(value) ? "" : value).append("\">").append(
				LINE_SEPARATOR);
		return _buffer.toString();
	}

	/**
	 * use getgetNavigationFormFirst, getPageInfoWithButtons,
	 * getNavigationFormLast at same time.
	 *
	 * @return
	 */
	public String getNavigationFormFirst() {
		StringBuffer _stringBuffer = new StringBuffer();
		try {
			_stringBuffer.append(getFormFirstPart()).append(LINE_SEPARATOR)
					.append(getFormProperties()).append(LINE_SEPARATOR);

		} catch (Exception e) {
			logger.error(e);
		}
		return _stringBuffer.toString();
	}

	public String getPageInfoWithButtons() {
		StringBuffer _stringBuffer = new StringBuffer();
		try {
			_stringBuffer.append(getPageInfo());
			if (this.m_listBean.getTotalRecords() <= m_listBean
					.getRowsPerPage()) {
				_stringBuffer.append(getFormWithoutButton()).append(
						LINE_SEPARATOR);
			} else {
				_stringBuffer.append(HTML_BLANK).append(HTML_BLANK).append(
						HTML_BLANK).append(getFormButtons()).append(
						LINE_SEPARATOR);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return _stringBuffer.toString();
	}

	public String getNavigationFormLast() {
		StringBuffer _stringBuffer = new StringBuffer();
		try {
			_stringBuffer.append(getFormLastPart()).append(LINE_SEPARATOR)
					.append(getScript());
		} catch (Exception e) {
			logger.error(e);
		}
		return _stringBuffer.toString();
	}

	public String getButtonNavigation() {
		StringBuffer _stringBuffer = new StringBuffer();
		try {
			_stringBuffer.append(getFormFirstPart()).append(LINE_SEPARATOR)
					.append(getFormProperties()).append(LINE_SEPARATOR);
			if (this.m_listBean.getTotalRecords() <= m_listBean
					.getRowsPerPage()) {
				_stringBuffer.append(getFormWithoutButton()).append(
						LINE_SEPARATOR);
			} else {
				_stringBuffer.append(getFormButtons()).append(LINE_SEPARATOR);
			}
			_stringBuffer.append(getFormLastPart()).append(LINE_SEPARATOR)
					.append(getScript());
		} catch (Exception e) {
			logger.error(e);
		}
		return _stringBuffer.toString();
	}

	public String getLinkNavigation() {
		StringBuffer _stringBuffer = new StringBuffer();
		try {
			_stringBuffer.append(getFormFirstPart()).append(LINE_SEPARATOR)
					.append(getFormProperties()).append(LINE_SEPARATOR);
			if (this.m_listBean.getTotalRecords() <= m_listBean
					.getRowsPerPage()) {
				_stringBuffer.append(getFormWithoutButton()).append(
						LINE_SEPARATOR);
			} else {
				_stringBuffer.append(getFormLinks()).append(LINE_SEPARATOR);
			}
			_stringBuffer.append(getFormLastPart()).append(LINE_SEPARATOR)
					.append(getScript());
		} catch (Exception e) {
			logger.error(e);
		}
		return _stringBuffer.toString();
	}

	/**
	 * gen Total: xx Page:xx/xx
	 *
	 * @return
	 */
	public String getPageInfo() {
		if (this.m_listBean.getTotalRecords() == 0) {
			return "";
		}
		StringBuffer _stringBuffer = new StringBuffer();
		_stringBuffer.append(getMessageByKey(LABEL_QUERY_TOTAL_RECORDS))
				.append(":").append(HTML_BLANK).append(
						m_listBean.getTotalRecords());
		for (int i = 0; i < 4; i++) {
			_stringBuffer.append(HTML_BLANK);
		}
		_stringBuffer.append(getMessageByKey(LABEL_QUERY_TOTAL_PAGE)).append(
				":").append(HTML_BLANK).append(m_listBean.getCurrentPage())
				.append("/").append(m_listBean.getTotalPages());
		return _stringBuffer.toString();

	}

	private String getFormFirstPart() {
		StringBuffer _stringBuffer = new StringBuffer();
		_stringBuffer.append("<form name=\"").append(this.m_formName).append(
				"\"");
		if (StringUtils.isNotBlank(this.m_formAction)) {

			_stringBuffer.append(" action=\"");
			_stringBuffer.append(this.m_formAction).append("\"");
		}
		if (StringUtils.isNotBlank(this.m_formMethod)) {
			_stringBuffer.append(" method=\"").append(this.m_formMethod)
					.append("\"");
		}
		if (StringUtils.isNotBlank(this.m_target)) {
			_stringBuffer.append(" target=\"").append(this.m_target).append(
					"\"");
		}
		_stringBuffer.append(" onsubmit=\"return checkButtonClick(this)\">")
				.append(LINE_SEPARATOR);
		_stringBuffer.append(
				"<table width=\"100%\" border=\"0\"><tr><td align=\"").append(
				this.m_buttonAlign).append("\">").append(LINE_SEPARATOR);

		_stringBuffer
				.append(createFromProperty("actionType", this.m_actionType));
		_stringBuffer.append(createFromProperty("orderby", null == this.m_form
				.getOrderby() ? "" : this.m_form.getOrderby()));
		_stringBuffer.append(createFromProperty("oldOrderby", ""));
		_stringBuffer.append(createFromProperty("orderAscending",
				null == this.m_form.getOrderAscending() ? "" : this.m_form
						.getOrderAscending()));

		_stringBuffer.append(createFromProperty("oldPageNo", new Integer(
				this.m_listBean.getCurrentPage()).toString()));

		return _stringBuffer.toString();

	}

	private String getFormLastPart() {
		StringBuffer _stringBuffer = new StringBuffer();
		_stringBuffer.append("</td></tr></table>").append(LINE_SEPARATOR)
				.append("</form>");
		return _stringBuffer.toString();
	}

	private String getFormWithoutButton() {
		StringBuffer _stringBuffer = new StringBuffer();
		_stringBuffer.append("<input type='hidden' name='pageNo' value='")
				.append(m_listBean.getCurrentPage()).append("'>");
		return _stringBuffer.toString();
	}

	private String getFormButtons() {
		StringBuffer _stringBuffer = new StringBuffer();
		if (m_listBean.getShowPreButton()) {
			_stringBuffer
					.append(
							"<input type=\"button\" name=\"previousButton\" onClick=\"prePage()\" value=\"")
					.append(getMessageByKey(BUTTON_PREVIOUS_PAGE)).append(
							"\"/>").append(LINE_SEPARATOR);
		}
		if (m_listBean.getShowNextButton()) {
			_stringBuffer
					.append(HTML_BLANK)
					.append(HTML_BLANK)
					.append(
							"<input type=\"button\" name=\"nextButton\" onClick=\"nextPage()\" value=\"")
					.append(getMessageByKey(BUTTON_NEXT_PAGE)).append("\"/>");
		}
		_stringBuffer.append(getPageSelect());
		return _stringBuffer.toString();
	}

	private String getFormLinks() {
		StringBuffer _stringBuffer = new StringBuffer();
		if (m_listBean.getShowPreButton()) {
			_stringBuffer.append("<a href=\"javascript:prePage()\">").append(
					getMessageByKey(BUTTON_PREVIOUS_PAGE)).append("\"</a>")
					.append(LINE_SEPARATOR);
		}
		if (m_listBean.getShowNextButton()) {
			_stringBuffer.append(HTML_BLANK).append(HTML_BLANK).append(
					"<a href=\"javascript:nextPage()\"").append(
					getMessageByKey(BUTTON_NEXT_PAGE)).append("\"</a>");
		}
		_stringBuffer.append(getPageSelect());
		return _stringBuffer.toString();
	}

	private String getPageSelect() {
		StringBuffer _stringBuffer = new StringBuffer();

		_stringBuffer.append(HTML_BLANK).append(HTML_BLANK);
		if (m_listBean.getTotalPages() > MAX_PAGE_SELECTED) {
			_stringBuffer
					.append(
							"<input type=\"button\" name=\"previousButton\" onClick=\"skipto()\" value=\"")
					.append(getMessageByKey(LABEL_QUERY_SKIP)).append("\"/>")
					.append(LINE_SEPARATOR);
			_stringBuffer
					.append("<input type=\"text\" name=\"pageNo\" style=\"width: 30\"></input>");
			_stringBuffer.append(getMessageByKey(LABEL_QUERY_PAGE)).append(
					LINE_SEPARATOR);

		} else {
			_stringBuffer
					.append(getMessageByKey(LABEL_QUERY_SKIP))
					.append(
							"<select name=\"pageNo\" size=\"1\" onChange=\"skipto()\">")
					.append(LINE_SEPARATOR);
			for (int i = 0; i < m_listBean.getTotalPages(); i++) {
				_stringBuffer.append("<option value=\"").append(i + 1).append(
						"\">").append(i + 1).append("</option>").append(
						LINE_SEPARATOR);
			}
			_stringBuffer.append("</select>").append(
					getMessageByKey(LABEL_QUERY_PAGE)).append(LINE_SEPARATOR);
		}
		return _stringBuffer.toString();

	}

	private String getFormProperties() {
		StringBuffer _stringBuffer = new StringBuffer();
		for (Iterator _iter = m_properties.iterator(); _iter.hasNext();) {
			LabelValueBean _labelValueBean = (LabelValueBean) _iter.next();
			_stringBuffer.append(
					createFromProperty(_labelValueBean.getLabel().trim(),
							_labelValueBean.getValue())).append(LINE_SEPARATOR);
		}
		return _stringBuffer.toString();
	}

	private String getSetHtmlFormStringValue(String propertyName, String value) {
		StringBuffer _strBuffer = new StringBuffer("document.").append(
				m_formName).append(".").append(propertyName).append(
				".value = \"").append(value).append("\";").append(
				LINE_SEPARATOR);
		return _strBuffer.toString();
	}

	private String getSetHtmlFormPrimaryValue(String propertyName, String value) {
		StringBuffer _strBuffer = new StringBuffer("document.").append(
				m_formName).append(".").append(propertyName)
				.append(".value = ").append(value).append(";").append(
						LINE_SEPARATOR);
		return _strBuffer.toString();
	}

	private String getScript() {
		// formsubmit
		StringBuffer _strBufferSubmit = new StringBuffer("document.");
		_strBufferSubmit.append(m_formName).append(".submit();");

		// script
		StringBuffer _stringBuffer = new StringBuffer();
		_stringBuffer.append("<script language=\"javascript\">").append(
				LINE_SEPARATOR);
		// vars
		_stringBuffer.append("var pageno = ").append(
				m_listBean.getCurrentPage()).append(";").append(LINE_SEPARATOR);
		_stringBuffer.append(getSetHtmlFormPrimaryValue("pageNo", "pageno"));

		// skipto()
		_stringBuffer.append("function skipto() { ").append(LINE_SEPARATOR)
				.append(_strBufferSubmit).append(LINE_SEPARATOR).append(" }")
				.append(LINE_SEPARATOR);

		// nextPage()
		_stringBuffer.append("function nextPage() {").append(LINE_SEPARATOR)
				.append(getSetHtmlFormPrimaryValue("pageNo", "1 + pageno;"))
				.append(LINE_SEPARATOR).append(_strBufferSubmit).append(
						LINE_SEPARATOR).append("}").append(LINE_SEPARATOR);

		// prePage()
		_stringBuffer.append("function prePage() {").append(LINE_SEPARATOR)
				.append(getSetHtmlFormPrimaryValue("pageNo", "pageno - 1;"))
				.append(_strBufferSubmit).append(LINE_SEPARATOR).append("}")
				.append(LINE_SEPARATOR);

		// changeOrderby
		_stringBuffer.append("function changeOrderby(orderCol) {").append(
				LINE_SEPARATOR).append(
				getSetHtmlFormStringValue("oldOrderby", null == this.m_form
						.getOrderby() ? "" : this.m_form.getOrderby())).append(
				getSetHtmlFormPrimaryValue("orderby", "orderCol;")).append(
				_strBufferSubmit).append(LINE_SEPARATOR).append("}").append(
				LINE_SEPARATOR);

		_stringBuffer.append("</script>");
		return _stringBuffer.toString();

	}

	public void addProperty(String name, String value) {
		if (StringUtils.isBlank(value))
			value = "";
		LabelValueBean _labelValueBean = new LabelValueBean(name, value);
		this.m_properties.add(_labelValueBean);
	}

	/**
	 * @param buttonAlign
	 *            The buttonAlign to set.
	 */
	public void setButtonAlign(String buttonAlign) {
		m_buttonAlign = buttonAlign;
	}

	/**
	 * @param formMethod
	 *            The formMethod to set.
	 */
	public void setFormMethod(String formMethod) {
		m_formMethod = formMethod;
	}

	/**
	 * @param formName
	 *            The formName to set.
	 */
	public void setFormName(String formName) {
		m_formName = formName;
	}

	/**
	 * @param target
	 *            The target to set.
	 */
	public void setTarget(String target) {
		m_target = target;
	}

	/**
	 * @return Returns the listBean.
	 */
	public Collection getRecords() {
		return this.m_listBean.getRecords();
	}

	public int getStartIndex() {
		return this.m_listBean.getStartIndex();
	}

}

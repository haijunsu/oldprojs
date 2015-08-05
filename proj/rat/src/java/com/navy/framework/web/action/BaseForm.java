package com.navy.framework.web.action;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class BaseForm extends ValidatorForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6952156613170298818L;

	protected String actionType = null;

	protected String mode = null;

	private boolean m_forceAction = false;

	private String m_orderby = null;

	private String m_oldOrderby = null;

	private int m_oldPageNo = 0;

	private String m_orderAscending = null;

	private int m_pageNo = 0;

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getActionType() {
		return this.actionType;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return this.mode;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);

		this.mode = null;
		this.actionType = null;
		this.m_forceAction = false;
		this.m_oldOrderby = null;
		this.m_orderby = null;
		this.m_orderAscending = null;
		this.m_pageNo = 0;
	}

	/**
	 * @return Returns the forceAction.
	 */
	public boolean getForceAction() {
		return this.m_forceAction;
	}

	/**
	 * @param forceAction
	 *            The forceAction to set.
	 */
	public void setForceAction(boolean forceAction) {
		m_forceAction = forceAction;
	}

	public boolean getIsOrderAscending() {
		return (getOrderAscending().equalsIgnoreCase("ASC")) ? true : false;
	}

	public String getOrderAscending() {
		if (this.m_orderAscending == null) {
			this.m_orderAscending = "DESC";
		}
		// if orderby equals oldorderby, then reverse.
		if (StringUtils.isBlank(getOldOrderby()) || getOldPageNo() == 0) {
			return this.m_orderAscending;
		} else if (getOldOrderby().equals(getOrderby())
				&& getOldPageNo() == getPageNo()) {
			// reverse
			if (this.m_orderAscending.equalsIgnoreCase("DESC")) {
				this.m_orderAscending = "ASC";
			} else {
				this.m_orderAscending = "DESC";
			}
			this.m_oldOrderby = null;
		}
		return this.m_orderAscending;
	}

	public void setOrderAscending(String sortAscending) {
		this.m_orderAscending = sortAscending;
	}

	public String getOldOrderby() {
		return this.m_oldOrderby;
	}

	public void setOldOrderby(String oldOrderby) {
		this.m_oldOrderby = oldOrderby;
	}

	/**
	 * @return Returns the orderby.
	 */
	public String getOrderby() {
		return this.m_orderby;
	}

	/**
	 * @param orderby
	 *            The orderby to set.
	 */
	public void setOrderby(String orderby) {
		this.m_orderby = orderby;
	}

	public int getPageNo() {
		return m_pageNo;
	}

	public void setPageNo(int pageNo) {
		m_pageNo = pageNo;
	}

	public int getOldPageNo() {
		return m_oldPageNo;
	}

	public void setOldPageNo(int oldPageNo) {
		m_oldPageNo = oldPageNo;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}

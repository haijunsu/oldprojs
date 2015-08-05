/*
 * @(#)Bargain.java  2007-1-15
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.models;

import java.math.BigDecimal;
import java.util.Date;

import com.navy.framework.models.BaseObject;
import com.navy.framework.models.Company;
import com.navy.framework.models.User;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 * @hibernate.class table="rat_quotation"
 * @struts.form include-all="true"
 *              extends="com.navy.framework.web.action.BaseForm"
 */
public class Quotation extends BaseObject {

	private Long id;

	private User user;

	private Company company;

	private BigDecimal price;

	private String currency;

	private Date createDate;

	/**
	 * 供应商报价，买方价格调整，供应商价格调整
	 */
	private Integer quoteType;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.models.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.models.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.models.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @hibernate.property
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * @hibernate.property length="32"
	 * @return the currency
	 */
	public String getCurrency() {
		return this.currency;
	}

	/**
	 * @hibernate.id column="id" generator-class="native" unsaved-value="null"
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @hibernate.property sql-type="NUMERIC(12, 2)"
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * @hibernate.many-to-one lazy="false"
	 *
	 * @return the company
	 */
	public Company getCompany() {
		return this.company;
	}

	/**
	 * @hibernate.property
	 * @return the quoteType
	 */
	public Integer getQuoteType() {
		return this.quoteType;
	}

	/**
	 * @hibernate.many-to-one lazy="false"
	 * @return the user
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * @param quoteType
	 *            the quoteType to set
	 */
	public void setQuoteType(Integer quoteType) {
		this.quoteType = quoteType;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}

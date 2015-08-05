/*
 * @(#)Order.java  2007-1-15
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.navy.framework.models.BaseObject;
import com.navy.framework.models.User;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 * @hibernate.class table="rat_order"
 * @struts.form include-all="true"
 *              extends="com.navy.framework.web.action.BaseForm"
 */
public class Order extends BaseObject {

	private Long id;

	private User user;

	private String code;

	private Set products = new HashSet();

	private Set quotations = new HashSet();

	private BigDecimal price;

	private String currency;

	private Date createDate;

	private Date lastChgDate;

	private Integer state;

	private Date bargainDate;

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
	 * 成交日期
	 *
	 * @hibernate.property
	 * @return the bargainDate
	 */
	public Date getBargainDate() {
		return this.bargainDate;
	}

	/**
	 * order code: summary of product hashcode + datetime
	 *
	 * @hibernate.property length="64" not-null="true" unique="true"
	 *                     index="OrderCodeIndex"
	 * @return the code
	 */
	public String getCode() {
		return this.code;
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
	 * @hibernate.property
	 * @return the lastChgDate
	 */
	public Date getLastChgDate() {
		return this.lastChgDate;
	}

	/**
	 * @hibernate.property sql-type="NUMERIC(12, 2)"
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * @hibernate.set table="rat_order_prod" cascade="all" lazy="false"
	 * @hibernate.collection-key column="order_id"
	 * @hibernate.collection-many-to-many class="com.navy.app.rat.models.Product"
	 *                                    column="prod_id"
	 * @return the products
	 */
	public Set getProducts() {
		return this.products;
	}

	/**
	 * @hibernate.property length="32"
	 * @return the state
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 * @hibernate.many-to-one lazy="false"
	 * @return the user
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * @hibernate.set lazy="false"
	 * @hibernate.collection-key column="order_id"
	 * @hibernate.collection-one-to-many class="com.navy.app.rat.models.Quotation"
	 * @return the quotations
	 */
	public Set getQuotations() {
		return this.quotations;
	}

	/**
	 * @param bargainDate
	 *            the bargainDate to set
	 */
	public void setBargainDate(Date bargainDate) {
		this.bargainDate = bargainDate;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @param lastChgDate
	 *            the lastChgDate to set
	 */
	public void setLastChgDate(Date lastChgDate) {
		this.lastChgDate = lastChgDate;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(Set products) {
		this.products = products;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @param quotations
	 *            the quotations to set
	 */
	public void setQuotations(Set quotations) {
		this.quotations = quotations;
	}

}

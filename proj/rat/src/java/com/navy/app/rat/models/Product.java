/*
 * @(#)Product.java  2007-1-14
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.navy.framework.models.BaseObject;
import com.navy.framework.util.Utilities;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 * @hibernate.class table="rat_product"
 * @struts.form include-all="true"
 *              extends="com.navy.framework.web.action.BaseForm"
 */
public class Product extends BaseObject {

	private Long id;

	private String code;

	private String name;

	private String notes;

	private String keyword;

	private String manufacturer;

	private String logo;

	private Date createTime;

	private Set categories = new HashSet();

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.models.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Product))
			return false;

		final Product product = (Product) o;
		if (!Utilities.objectEqulas(id, product.getId())) {
			return false;
		}
		if (!Utilities.objectEqulas(code, product.getCode())) {
			return false;
		}
		return true;
	}

	/**
	 * @hibernate.set table="rat_prod_cate" cascade="all" lazy="false"
	 * @hibernate.collection-key column="product_id"
	 * @hibernate.collection-many-to-many class="com.navy.app.rat.models.Category"
	 *                                    column="category_id"
	 */
	public Set getCategories() {
		return this.categories;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="true" unique="true"
	 *                     index="productCodeIndex"
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @hibernate.property
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * @hibernate.id column="id" generator-class="native" unsaved-value="null"
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @hibernate.property length="255" index="productKeywordIndex"
	 * @return the keyword
	 */
	public String getKeyword() {
		return this.keyword;
	}

	/**
	 * @hibernate.property length="255"
	 * @return the logo
	 */
	public String getLogo() {
		return this.logo;
	}

	/**
	 * @hibernate.property length="255"
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}

	/**
	 * @hibernate.property length="255"
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @hibernate.property length="1024"
	 * @return the notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.models.BaseObject#hashCode()
	 */
	public int hashCode() {
		return Utilities.buildHashCode(new Object[] { id, code });
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(Set categories) {
		this.categories = categories;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @param logo
	 *            the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.models.BaseObject#toString()
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("code", this.code).append(
				"name", this.name).append("notes", this.notes);

		return sb.toString();
	}

}

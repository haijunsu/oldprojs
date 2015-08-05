/*
 * @(#)Category.java  2007-1-14
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.models;

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
 * @hibernate.class table="rat_category"
 * @struts.form include-all="true"
 *              extends="com.navy.framework.web.action.BaseForm"
 *
 * @author Haijun Su
 */
public class Category extends BaseObject {

	private Long id;

	private String code;

	private String name;

	private String notes;

	private String parent;

	private boolean leaf;

	private Set products = new HashSet();

	/**
	 * @hibernate.set table="rat_prod_cate" cascade="all" lazy="true"
	 * @hibernate.collection-key column="category_id"
	 * @hibernate.collection-many-to-many class="com.navy.app.rat.models.Product"
	 *                                    column="product_id"
	 */
	public Set getProducts() {
		return this.products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(Set products) {
		this.products = products;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="true" unique="true"
	 *                     index="categoryCodeIndex"
	 * @return the code
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @hibernate.id column="id" generator-class="native" unsaved-value="null"
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="true" unique="false"
	 *                     index="categoryNameIndex"
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @hibernate.property length="256"
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * @hibernate.property length="32"
	 */
	public String getParent() {
		return this.parent;
	}

	/**
	 * @hibernate.property type="boolean"
	 * @return the leaf
	 */
	public boolean isLeaf() {
		return this.leaf;
	}

	/**
	 * @param leaf
	 *            the leaf to set
	 */
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.models.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Category))
			return false;

		final Category category = (Category) o;

		if (!Utilities.objectEqulas(id, category.getId())) {
			return false;
		}

		if (!Utilities.objectEqulas(code, category.getCode())) {
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.models.BaseObject#hashCode()
	 */
	public int hashCode() {
		return Utilities.buildHashCode(new Object[] { id, code });
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.models.BaseObject#toString()
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("code", this.code).append(
				"name", this.name).append("notes", this.notes).append("parent",
				this.parent);

		return sb.toString();
	}

}

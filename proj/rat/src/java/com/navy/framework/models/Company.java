/*
 * @(#)Provider.java  2007-1-13
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.models;

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
 * @hibernate.class table="rat_company"
 * @struts.form include-all="true"
 *              extends="com.navy.framework.web.action.BaseForm"
 */
public class Company extends BaseObject {

	private Long id;

	private String code;

	private String name;

	private String legalPerson;

	private String linkman;

	private String tel1;

	private String tel2;

	private String fax;

	private String email;

	private Integer type;

	private Set addresses = new HashSet();

	private Set users = new HashSet();

	/**
	 * @hibernate.property
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @hibernate.set cascade="all"
	 * @hibernate.collection-key column="company"
	 * @hibernate.one-to-many class="com.navy.framework.models.Address"
	 */
	public Set getAddresses() {
		return this.addresses;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="64" not-null="true" unique="true"
	 *                     index="companyCodeIndex"
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * @hibernate.property length="128"
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @hibernate.property length="64"
	 */
	public String getFax() {
		return this.fax;
	}

	/**
	 * @hibernate.id column="id" generator-class="native" unsaved-value="null"
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @hibernate.property length="128"
	 */
	public String getLegalPerson() {
		return this.legalPerson;
	}

	/**
	 * @hibernate.property length="128"
	 */
	public String getLinkman() {
		return this.linkman;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="128"
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="64"
	 */
	public String getTel1() {
		return this.tel1;
	}

	/**
	 * @hibernate.property length="64"
	 */
	public String getTel2() {
		return this.tel2;
	}

	/**
	 * @hibernate.set cascade="all"
	 * @hibernate.collection-key column="company"
	 * @hibernate.one-to-many class="com.navy.framework.models.User"
	 */
	public Set getUsers() {
		return this.users;
	}

	/**
	 * @param addresses
	 *            the addresses to set
	 */
	public void setAddresses(Set addresses) {
		this.addresses = addresses;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param legalPerson
	 *            the legalPerson to set
	 */
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	/**
	 * @param linkman
	 *            the linkman to set
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param tel1
	 *            the tel1 to set
	 */
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	/**
	 * @param tel2
	 *            the tel2 to set
	 */
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(Set users) {
		this.users = users;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.models.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Company))
			return false;

		final Company company = (Company) o;

		if (!Utilities.objectEqulas(id, company.getId())) {
			return false;
		}
		return Utilities.objectEqulas(code, company.getCode());
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
				"name", this.name).append("tel1", this.tel1).append("email",
				this.email).append("legalPersion", this.legalPerson).append(
				"linkman", this.linkman);

		return sb.toString();
	}

}

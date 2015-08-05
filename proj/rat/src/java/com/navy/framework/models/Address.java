package com.navy.framework.models;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.navy.framework.util.Utilities;

/**
 * This class is used to represent an address.
 * </p>
 *
 * <p>
 * <a href="Address.java.html"><i>View Source</i></a>
 * </p>
 *
 * @hibernate.class table="rat_address"
 * @struts.form include-all="true"
 *              extends="com.navy.framework.web.action.BaseForm"
 */
public class Address extends BaseObject implements Serializable {
	private static final long serialVersionUID = 3617859655330969141L;

	private Long id;

	private String address;

	private String city;

	private String province;

	private String country;

	private String postalCode;

	private User user;

	/**
	 * @hibernate.id column="id" generator-class="native" unsaved-value="null"
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="address" not-null="false" length="150"
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property column="city" not-null="true" length="50"
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property column="province" length="100"
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property column="country" length="100"
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @struts.validator type="required"
	 * @struts.validator type="mask" msgkey="errors.zip"
	 * @struts.validator-var name="mask" value="${zip}"
	 * @hibernate.property column="postal_code" not-null="true" length="15"
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @hibernate.many-to-one cascade="all"
	 */
	public User getUser() {
		return this.user;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Address))
			return false;

		final Address address1 = (Address) o;

		if (!Utilities.objectEqulas(id, address1.getId())) {
			return false;
		}
		if (!Utilities.objectEqulas(user, address1.getUser())) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return Utilities.buildHashCode(new Object[] { id, user });
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", this.id).append("country", this.country).append(
						"user", this.user).append("country", this.country)
				.append("address", this.address).append("province",
						this.province).append("postalCode", this.postalCode)
				.append("city", this.city).toString();
	}

}

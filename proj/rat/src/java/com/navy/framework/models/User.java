package com.navy.framework.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.navy.framework.util.ArrayUtil;
import com.navy.framework.util.Utilities;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="User.java.html"><i>View Source</i></a>
 *
 *
 * @struts.form include-all="true"
 *              extends="com.navy.framework.web.action.BaseForm"
 *
 * @hibernate.class table="rat_user"
 */
public class User extends BaseObject implements Serializable, UserDetails {
	private static final long serialVersionUID = 3832626162173359411L;

	private Long id;

	private String username; // required

	private String password; // required

	private String confirmPassword;

	private String passwordHint;

	private String firstName;

	private String lastName;

	private String email; // unique

	private String mobileNumber; // unique

	private String phoneNumber;

	private String website;

	// private Address address = new Address();

	private Integer version;

	private Set roles = new HashSet();

	private boolean enabled;

	private boolean accountExpired;

	private boolean accountLocked;

	private boolean credentialsExpired;

	private Date joinTime;

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	/**
	 * @return the joinTime
	 */
	public Date getJoinTime() {
		return this.joinTime;
	}

	/**
	 * @hibernate.property column="joinTime"
	 */
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	/**
	 *
	 * @hibernate.id column="id" generator-class="native" unsaved-value="null"
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="true" unique="true"
	 *                     index="userNameIndex"
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property column="password" not-null="true"
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @struts.validator type="required"
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property column="password_hint" not-null="false"
	 */
	public String getPasswordHint() {
		return passwordHint;
	}

	/**
	 * @hibernate.property column="first_name" not-null="false" length="50"
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @hibernate.property column="last_name" not-null="true" length="50"
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @struts.validator type="email"
	 * @hibernate.property name="email" not-null="false" unique="true"
	 *                     index="userEmailIndex"
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @struts.validator type="mask" msgkey="errors.phone"
	 * @struts.validator-var name="mask" value="${phone}"
	 * @hibernate.property column="phone_number" not-null="false"
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @struts.validator type="mask" msgkey="errors.phone"
	 * @struts.validator-var name="mask" value="${phone}"
	 * @hibernate.property column="mobile_number" not-null="false" unique="true"
	 *                     index="userMobileIndex"
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property column="website" not-null="false"
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * Returns the full name.
	 */
	public String getFullName() {
		return firstName + ' ' + lastName;
	}

	// /**
	// * @hibernate.component
	// */
	// public Address getAddress() {
	// return address;
	// }

	/**
	 * @hibernate.set table="rat_user_role" cascade="save-update" lazy="false"
	 * @hibernate.collection-key column="user_id"
	 * @hibernate.collection-many-to-many class="com.navy.framework.models.Role"
	 *                                    column="role_id"
	 */
	public Set getRoles() {
		return roles;
	}

	/**
	 * Adds a role for the user
	 *
	 * @param role
	 */
	public void addRole(Role role) {
		getRoles().add(role);
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#getAuthorities()
	 */
	public GrantedAuthority[] getAuthorities() {
		return (GrantedAuthority[]) roles.toArray(new GrantedAuthority[0]);
	}

	/**
	 * @hibernate.version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @hibernate.property column="account_enabled" type="yes_no"
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @hibernate.property column="account_expired" not-null="true"
	 *                     type="yes_no"
	 */
	public boolean isAccountExpired() {
		return accountExpired;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isAccountNonExpired()
	 */
	public boolean isAccountNonExpired() {
		return !isAccountExpired();
	}

	/**
	 * @hibernate.property column="account_locked" not-null="true" type="yes_no"
	 */
	public boolean isAccountLocked() {
		return accountLocked;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isAccountNonLocked()
	 */
	public boolean isAccountNonLocked() {
		return !isAccountLocked();
	}

	/**
	 * @hibernate.property column="credentials_expired" not-null="true"
	 *                     type="yes_no"
	 */
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	// public void setAddress(Address address) {
	// this.address = address;
	// }

	public void setRoles(Set roles) {
		this.roles = roles;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;

		final User user = (User) o;

		if (!Utilities.objectEqulas(username, user.getUsername())) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		return Utilities.buildHashCode(username);
	}

	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("username", this.username)
				.append("enabled", this.enabled).append("accountExpired",
						this.accountExpired).append("credentialsExpired",
						this.credentialsExpired).append("accountLocked",
						this.accountLocked);

		GrantedAuthority[] auths = this.getAuthorities();
		if (auths != null) {
			sb.append("Granted Authorities: ");
			sb.append(ArrayUtil.arrayToString(auths));
		} else {
			sb.append("No Granted Authorities");
		}
		return sb.toString();
	}
}

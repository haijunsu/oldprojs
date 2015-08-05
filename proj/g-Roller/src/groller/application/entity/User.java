/*
 * Created on 2003-10-16
 */
package groller.application.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 * @hibernate.class
 */
public class User {
	private Long _id;
	private String _account;
	private String _password;
	private String _mail;
	private String _name;
	private Set _posts;
	
	public User() {
		_posts = new HashSet();
	}
	
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof User)) {
			return false;
		}
		return ((User)obj).getId().equals(getId());
	}
	
	/**
	 * @hibernate.id
	 *  generator-class="native"
	 */ 
	public Long getId() {
		return _id;
	}

	/**
	 * @hibernate.set
	 *  lazy="true"
	 *  order-by="postTime"
	 * 	cascade="all"
	 * @hibernate.collection-key
	 *  column="owner"
	 * @hibernate.collection-one-to-many
	 * 	class="groller.application.entity.Post"
	 */
	public Set getPosts() {
		return _posts;
	}

	/**
	 * @hibernate.property
	 *  not-null="true"
	 *  unique="true"
	 */
	public String getAccount() {
		return _account;
	}

	/**
	 * @hibernate.property
	 */
	public String getMail() {
		return _mail;
	}

	/**
	 * @hibernate.property
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @hibernate.property
	 */
	public String getPassword() {
		return _password;
	}

	public void setAccount(String string) {
		_account = string;
	}

	public void setId(Long long1) {
		_id = long1;
	}

	public void setMail(String string) {
		_mail = string;
	}

	public void setName(String string) {
		_name = string;
	}

	public void setPassword(String string) {
		_password = string;
	}

	private void setPosts(Set set) {
		_posts = set;
	}
}

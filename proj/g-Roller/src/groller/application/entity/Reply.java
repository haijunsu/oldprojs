/*
 * Created on 2003-10-28
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.entity;

import groller.application.common.StringUtil;

import java.util.Calendar;

/**
 * @author gigix
 *	xiongjie@csdn.net
 * @hibernate.class
 */
public class Reply {
	private Long _id;
	private Calendar _postTime;
	private Post _owner;
	
	private String _author;
	private String _mail;
	private String _content;
	
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Reply)) {
			return false;
		}
		return ((Reply)obj).getId().equals(getId());
	}
	
	/**
	 * @hibernate.id
	 *  generator-class="native"
	 */
	public Long getId() {
		return _id;
	}

	/**
	 * @hibernate.many-to-one
	 */
	public Post getOwner() {
		return _owner;
	}

	/**
	 * @hibernate.property
	 */
	public String getAuthor() {
		return _author;
	}

	/**
	 * @hibernate.property
	 */
	public String getContent() {
		return _content;
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
	public Calendar getPostTime() {
		return _postTime;
	}

	public String getPostTimeInString() {
		return StringUtil.convertDateToString(_postTime);
	}

	public void setAuthor(String string) {
		_author = string;
	}

	public void setContent(String string) {
		_content = string;
	}

	public void setId(Long long1) {
		_id = long1;
	}

	public void setMail(String string) {
		_mail = string;
	}

	public void setOwner(Post post) {
		_owner = post;
	}

	public void setPostTime(Calendar calendar) {
		_postTime = calendar;
	}

}

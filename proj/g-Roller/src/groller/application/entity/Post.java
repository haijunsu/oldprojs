/*
 * Created on 2003-7-9
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.entity;

import groller.application.common.StringUtil;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 * @hibernate.class
 */
public class Post {
	private Long _id;
	private User _owner;
	
	private String _title;
	private String _link;
	private String _description;
	
	private Calendar _postTime;
	private Calendar _updateTime; 
	private Set _replies;

	public Post() {
		_replies = new HashSet();
	}

	public boolean equals(Object obj) {
		if(!(obj instanceof Post))
			return false;
		return ((Post)obj).getId().equals(_id);
	}
	
	/**
	 * @hibernate.id
	 *  generator-class="native"
	 */
	public Long getId() {
		return _id;
	}

	/**
	 * @hibernate.property
	 */
	public String getTitle() {
		return _title;
	}

	/**
	 * @hibernate.property
	 */
	public String getDescription() {
		return _description;
	}
	
	/**
	 * @hibernate.property
	 */
	public String getLink() {
		return _link;
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

	/**
	 * @hibernate.property
	 */
	public Calendar getUpdateTime() {
		return _updateTime;
	}

	public String getUpdateTimeInString() {
		return StringUtil.convertDateToString(_updateTime);
	}

	/**
	 * @hibernate.many-to-one
	 * 	lazy="true"
	 */
	public User getOwner() {
		return _owner;
	}

	/**
	 * @hibernate.set
	 *  lazy="true"
	 *  order-by="postTIme"
	 * 	cascade="all"
	 * @hibernate.collection-key
	 *  column="owner"
	 * @hibernate.collection-one-to-many
	 * 	class="groller.application.entity.Reply"
	 */
	public Set getReplies() {
		return _replies;
	}

	public void setId(Long long1) {
		_id = long1;
	}

	public void setTitle(String string) {
		_title = string;
	}
	
	public void setDescription(String description) {
		_description = description;
	}

	public void setLink(String string) {
		_link = string;
	}

	public void setOwner(User user) {
		_owner = user;
	}
	public void setPostTime(Calendar calendar) {
		_postTime = calendar;
	}

	public void setUpdateTime(Calendar calendar) {
		_updateTime = calendar;
	}

	public void setReplies(Set set) {
		_replies = set;
	}

}

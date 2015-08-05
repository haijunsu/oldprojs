/*
 * Created on 2004-2-27
 */
package groller.framework.observer.entity;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @hibernate.class
 * 	table="tab_message"
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class Message implements Serializable {
	private Long _id;
	private String _operateName;
	private Long _operateeId;
	
	public Message() {
	}
	
	public Message(String operateName, Object operatee) {
		_operateName = operateName;
		try {
			Method getter = operatee.getClass().getMethod("getId", new Class[0]);
			_operateeId = (Long) getter.invoke(operatee, new Object[0]);
		} catch (Exception e) {
			if(operatee instanceof Number){
				_operateeId = new Long(((Number)operatee).longValue()); 
			} else {
				_operateeId = new Long(operatee.hashCode());
			} 
		}
	}
	
	/**
	 * @hibernate.id 
	 * 	generator-class="native" 
	 */
	public Long getId() {
		return _id;
	}

	/**
	 * @hibernate.property
	 */
	public String getOperateName() {
		return _operateName;
	}

	/**
	 * @hibernate.property
	 */
	public Long getOperateeId() {
		return _operateeId;
	}

	public void setOperateName(String string) {
		_operateName = string;
	}

	public void setOperateeId(Long long1) {
		_operateeId = long1;
	}

	public void setId(Long long1) {
		_id = long1;
	}

}

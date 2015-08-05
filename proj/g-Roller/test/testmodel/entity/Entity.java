/*
 * Created on 2003-9-18
 */
package testmodel.entity;

/**
 * @author xiongj
 * @hibernate.class
 */
public class Entity {
	private Long _id;
	
	/**
	 * @hibernate.id
	 *  generator-class="native"
	 */ 
	public Long getId() {
		return _id;
	}

	public void setId(Long long1) {
		_id = long1;
	}

}

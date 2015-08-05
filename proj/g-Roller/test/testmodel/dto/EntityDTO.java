/*
 * Created on 2003-10-16
 */
package testmodel.dto;

import java.io.Serializable;

import testmodel.entity.Entity;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class EntityDTO implements Serializable {
	private Long _id;
	
	public EntityDTO() {
	}
	
	public EntityDTO(Entity entity) {
		_id = entity.getId();
	}
	
	public Long getId() {
		return _id;
	}

	public void setId(Long long1) {
		_id = long1;
	}

}

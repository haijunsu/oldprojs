/*
 * Created on 2003-10-15
 */
package groller.framework.dao;

import java.util.List;

/**
 * A marker interface for all Data Access Objects(DAOs).
 * Every DAO interface should extend IDAO, e.g. :
 * <code>public interface UserDAO extends IDAO {...}</code>
 * so that every concrete DAO class will implement IDAO.
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public interface IDAO {
    /**
     * Find entities from database which accord with the condition.
     * @param condition For look entities condition
     * @param entityType entity class
     * @return Collection of entities
     */
	public List find(String condition, Class entityType);
	
	/**
	 * Remove from database which accord with the condition. 
	 * @param condition For remove entities condition 
	 * @param entityType entity class
	 */
	public void remove(String condition, Class entityType);
}

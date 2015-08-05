/*
 * Created on 2003-10-16
 */
package testmodel.dao.hibernate;

import groller.framework.dao.hibernate.BaseHibernateDAO;
import groller.framework.exception.DAOException;
import testmodel.dao.IEntityDAO;
import testmodel.entity.Entity;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class HibernateEntityDAO
	extends BaseHibernateDAO
	implements IEntityDAO {

	/* (non-Javadoc)
	 * @see test.dao.IEntityDAO#create()
	 */
	public Entity create() throws DAOException {
		Entity entity = new Entity();
		saveEntity(entity);
		return entity;
	}

}

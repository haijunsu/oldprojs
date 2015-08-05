/*
 * Created on 2003-10-16
 */
package testmodel.dao;

import groller.framework.dao.IDAO;
import groller.framework.exception.DAOException;
import testmodel.entity.Entity;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public interface IEntityDAO extends IDAO {
	public Entity create() throws DAOException;
}

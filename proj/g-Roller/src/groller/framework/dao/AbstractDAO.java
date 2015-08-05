/*
 * Created on 2003-10-15
 */
package groller.framework.dao;


/**
 * The base class of all Data Access Object(DAO) base classes.
 * Every base-DAO should extend this class, e.g. :
 * <code>public class BaseHibernateDAO extends AbstractDAO</code>
 * This class defined "entityType" property for every DAO component,
 * so that in the configuration, DAOs will need "entityType" injection.
 * Base-DAOs can facility "entityType" infomation to implement some basic operations, 
 * such as CRUD operations or some kind of find methods.
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public abstract class AbstractDAO implements IDAO {
	protected Class _entityType;
	
	/**
	 * Setter dependency injection (type 2 IoC).
	 * DAO will defaultly deal with the entity of "typeName".
	 * @param typeName The default entity type
	 */
	public void setEntityType(String typeName) throws ClassNotFoundException {
		Class clazz = getClass().getClassLoader().loadClass(typeName);
		_entityType = clazz;
	}
}

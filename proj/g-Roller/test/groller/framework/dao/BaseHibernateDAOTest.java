/*
 * Created on 2003-10-15
 */
package groller.framework.dao;

import groller.framework.common.BeanFactoryWrapper;
import groller.framework.dao.hibernate.BaseHibernateDAO;
import groller.framework.exception.DAOException;
import junit.framework.TestCase;
import testmodel.DBUtil;
import testmodel.entity.Entity;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class BaseHibernateDAOTest extends TestCase {
	private BaseHibernateDAO _dao;

	/**
	 * Constructor for BaseHibernateDAOTest.
	 * @param arg0
	 */
	public BaseHibernateDAOTest(String arg0) {
		super(arg0);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		_dao = (BaseHibernateDAO) BeanFactoryWrapper.getBean("application", "baseHibernateDAO");
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		DBUtil.removeAllInstance(Entity.class);
	}

	public void testSaveAndLoadEntity() throws Exception {
		Entity entity1 = new Entity();
		_dao.saveEntity(entity1);
		assertNotNull(entity1.getId());
		
		Entity entity2 = (Entity) _dao.loadEntityById(entity1.getId());
		assertEquals(entity1.getId(), entity2.getId());
	}

	public void testUpdateEntity() throws Exception {
		Entity entity1 = new Entity();
		_dao.saveEntity(entity1);
		Long id = entity1.getId();
		_dao.updateEntity(entity1);
	}

	public void testRemoveEntity() throws Exception {
		Entity entity1 = new Entity();
		_dao.saveEntity(entity1);
		_dao.removeEntity(entity1);
		
		try {
			_dao.loadEntityById(entity1.getId());
			fail();
		} catch (DAOException e) {
			assertTrue(true);
		}
	}

	public void testLoadFail() throws Exception {
		try {
			_dao.loadEntityById(new Long(1));
			fail();
		} catch (DAOException e) {
			assertTrue(true);
		}
	}
}

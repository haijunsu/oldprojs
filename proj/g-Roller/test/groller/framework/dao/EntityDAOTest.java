/*
 * Created on 2003-10-16
 */
package groller.framework.dao;

import groller.framework.common.BeanFactoryWrapper;
import junit.framework.TestCase;
import testmodel.DBUtil;
import testmodel.dao.IEntityDAO;
import testmodel.entity.Entity;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class EntityDAOTest extends TestCase {
	IEntityDAO _dao;

	/**
	 * Constructor for EntityDAOTest.
	 * @param arg0
	 */
	public EntityDAOTest(String arg0) {
		super(arg0);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		_dao = (IEntityDAO) BeanFactoryWrapper.getBean("application", "entityDAO");
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		DBUtil.removeAllInstance(Entity.class);
	}

	public void testCreate() {
		Entity entity = _dao.create();
		assertNotNull(entity);
		assertTrue(entity.getId().longValue() > 0);
	}

}

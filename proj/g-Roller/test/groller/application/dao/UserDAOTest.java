/*
 * Created on 2003-10-16
 */
package groller.application.dao;

import groller.application.entity.User;
import groller.application.context.Global;
import groller.framework.exception.DAOException;
import junit.framework.TestCase;
import testmodel.DBUtil;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class UserDAOTest extends TestCase {
	private UserDAO _dao;

	/**
	 * Constructor for UserDAOTest.
	 * @param arg0
	 */
	public UserDAOTest(String arg0) {
		super(arg0);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		_dao = (UserDAO) Global.getBeanFactory().getBean("userDAO");
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		DBUtil.removeAllInstance(User.class);
	}

	public void testCreate() {
		User entity = _dao.create(new User());
		assertNotNull(entity);
		assertNotNull(entity.getId());
	}

	public void testLoad() {
		User entity1 = _dao.create(new User());
		User entity2 = _dao.load(entity1.getId());
		assertNotNull(entity2);
		assertEquals(entity1, entity2);
	}

	public void testUpdate() {
		User entity = new User();
		entity.setAccount("Gigix");
		User entity1 = _dao.create(entity);
		
		User entity2 = _dao.load(entity1.getId());
		assertEquals("Gigix", entity2.getAccount());
	}

	public void testRemove() {
		User entity = _dao.create(new User());
		_dao.remove(entity);
		try {
			_dao.load(entity.getId());
			fail();
		} catch (DAOException e) {
			assertTrue(true);
		}
	}

	public void testFindByAccount() {
		try {
			_dao.findByAccount("Gigix");
			fail();
		} catch (DAOException e) {
			assertTrue(true);
		}
		
		User entity = new User();
		entity.setAccount("Gigix");
		User entity1 = _dao.create(entity);
		_dao.create(new User());
		
		User entity2 = _dao.findByAccount("Gigix");
		assertEquals(entity1, entity2);
	}

	public void testListAll() {
		for(int i = 0; i < 5; i++) {
			User entity = _dao.create(new User());
		}
		User[] all = _dao.listAll();
		assertEquals(5, all.length);
	}
}

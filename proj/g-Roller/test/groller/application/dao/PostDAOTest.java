/*
 * Created on 2003-10-27
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.dao;

import groller.application.entity.Post;
import groller.application.entity.User;
import groller.application.context.Global;
import groller.framework.exception.DAOException;
import junit.framework.TestCase;
import testmodel.DBUtil;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class PostDAOTest extends TestCase {
	private PostDAO _pDao;
	private UserDAO _uDao;
	private User _owner; 

	/**
	 * Constructor for PostDAOTest.
	 * @param arg0
	 */
	public PostDAOTest(String arg0) {
		super(arg0);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		_uDao = (UserDAO) Global.getBeanFactory().getBean("userDAO");
		_pDao = (PostDAO) Global.getBeanFactory().getBean("postDAO");
		_owner = _uDao.create(new User()); 
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		DBUtil.removeAllInstance(User.class);
		DBUtil.removeAllInstance(Post.class);
	}

	public void testCreate() {
		Post entity = _pDao.create(_owner, new Post());
		assertNotNull(entity);
		assertNotNull(entity.getId());
	}

	public void testLoad() {
		Post entity1 = _pDao.create(_owner, new Post());
		Post entity2 = _pDao.load(entity1.getId());
		assertEquals(entity1, entity2);
	}

	public void testUpdate() {
		Post entity = new Post();
		entity.setTitle("test");
		Post entity1 = _pDao.create(_owner, entity);
		
		Post entity2 = _pDao.load(entity1.getId());
		assertEquals("test", entity2.getTitle());		
	}

	public void testRemove() {
		Post entity = _pDao.create(_owner, new Post());
		_pDao.remove(entity);
		
		try {
			_pDao.load(entity.getId());
			fail();
		} catch(DAOException e) {
			assertTrue(true);
		}
	}

	public void testFindByTitle() {
		assertEquals(0, _pDao.findByTitle("es").length);
		
		for(int i = 0; i < 5; i++) {
			Post entity = new Post();
			entity.setTitle("test");
			Post entity1 = _pDao.create(_owner, entity);
		}
		
		Post[] entities = _pDao.findByTitle("es");
		assertEquals(5, entities.length);
	}
}

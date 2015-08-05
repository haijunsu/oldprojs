/*
 * Created on 2003-10-28
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.dao;

import groller.application.entity.Post;
import groller.application.entity.Reply;
import groller.application.entity.User;
import groller.application.context.Global;
import junit.framework.TestCase;
import testmodel.DBUtil;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class ReplyDAOTest extends TestCase {
	private ReplyDAO _dao;
	private User _user;
	private Post _post;

	/**
	 * Constructor for ReplyDAOTest.
	 * @param arg0
	 */
	public ReplyDAOTest(String arg0) {
		super(arg0);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		UserDAO uDao = (UserDAO) Global.getBeanFactory().getBean("userDAO");
		_user = uDao.create(new User());
		PostDAO pDao = (PostDAO) Global.getBeanFactory().getBean("postDAO");
		_post = pDao.create(_user, new Post());
		_dao = (ReplyDAO) Global.getBeanFactory().getBean("replyDAO");
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		DBUtil.removeAllInstance(User.class);
		DBUtil.removeAllInstance(Post.class);
		DBUtil.removeAllInstance(Reply.class);
	}

	public void testCreate() {
		Reply entity = _dao.create(_post, new Reply());
		assertNotNull(entity);
		assertNotNull(entity.getId());
	}

	public void testUpdate() {
		Reply entity = new Reply();
		entity.setAuthor("gigix");
		Reply entity1 = _dao.create(_post, entity);
		
		UserDAO dao = (UserDAO) Global.getBeanFactory().getBean("userDAO");
		User user = dao.load(_user.getId());
		Post post = (Post) user.getPosts().iterator().next();
		Reply entity2 = (Reply) post.getReplies().iterator().next();
		assertEquals(entity1, entity2);
		assertEquals("gigix", entity2.getAuthor());
	}

	public void testRemove() {
		Reply entity = _dao.create(_post, new Reply());
		_dao.remove(entity);
		
		UserDAO dao = (UserDAO) Global.getBeanFactory().getBean("userDAO");
		User user = dao.load(_user.getId());
		Post post = (Post) user.getPosts().iterator().next();
		assertEquals(0, post.getReplies().size());
	}

}

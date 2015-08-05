/*
 * Created on 2003-10-28
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.service;

import groller.application.context.Global;
import groller.application.dao.PostDAO;
import groller.application.entity.Post;
import groller.application.entity.Reply;
import groller.application.entity.User;
import junit.framework.TestCase;
import testmodel.DBUtil;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class PostServiceTest extends TestCase {
	private User _user;
	private PostService _service;

	/**
	 * Constructor for PostServiceTest.
	 * @param arg0
	 */
	public PostServiceTest(String arg0) {
		super(arg0);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		
		User user = new User();
		user.setAccount("gigix");
		UserService uService = (UserService) Global.getGrollerServiceLocator().getUserService();
		_user = uService.registerNewUser(user);
		
		_service = (PostService) Global.getBeanFactory().getBean("postService");
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		DBUtil.removeAllInstance(User.class);
		DBUtil.removeAllInstance(Post.class);
	}

	public void testPublishNewPost() {
		Post dto = _service.publishNewPost(_user.getId(), new Post());
		assertNotNull(dto);
		assertNotNull(dto.getId());
		assertEquals(_user.getId(), dto.getOwner().getId());
		
		dto = new Post();
		dto.setTitle("test");
		dto = _service.publishNewPost(_user.getId(), dto);
		
		PostDAO dao = (PostDAO) Global.getBeanFactory().getBean("postDAO");
		Post entity = dao.load(dto.getId());
		assertEquals("test", entity.getTitle());
	}

	public void testModifyPost() {
		Post dto1 = _service.publishNewPost(_user.getId(), new Post());
		dto1.setTitle("test");
		_service.modifyPost(dto1);
		
		Post dto2 = _service.getAllPostsOfUser(_user.getId())[0];
		assertEquals("test", dto2.getTitle());
	}

	public void testRemovePost() {
		Post dto = _service.publishNewPost(_user.getId(), new Post());
		_service.removePostById(dto.getId());
		assertEquals(0, _service.getAllPostsOfUser(_user.getId()).length);
	}

	public void testGetAllPostsOfUser() {
		for(int i = 0; i < 5; i++) {
			_service.publishNewPost(_user.getId(), new Post());
		}
		assertEquals(5, _service.getAllPostsOfUser(_user.getId()).length);
	}

	public void testFindPostsByTitle() {
		for(int i = 0; i < 5; i++) {
			Post dto = new Post();
			dto.setTitle("test");
			_service.publishNewPost(_user.getId(), dto);
		}
		assertEquals(5, _service.findPostsByTitle("es").length);
	}

	public void testPublishNewReply() {
		Post post = _service.publishNewPost(_user.getId(), new Post());
		Reply dto = _service.publishNewReply(post.getId(), new Reply());
		assertNotNull(dto);
		assertNotNull(dto.getId());
		assertEquals(post.getId(), dto.getOwner().getId());	
	}
	
	public void testGetAllRepliesOfPost() {
		Post post = _service.publishNewPost(_user.getId(), new Post());
		for(int i = 0; i < 5; i++) {
			_service.publishNewReply(post.getId(), new Reply());		
		}
		assertEquals(5, _service.getAllRepliesOfPost(post.getId()).length);
	}
}

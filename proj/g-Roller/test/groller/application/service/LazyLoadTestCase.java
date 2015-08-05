/*
 * Created on 2004-5-23
 */
package groller.application.service;

import groller.application.context.Global;
import groller.application.entity.Post;
import groller.application.entity.User;
import junit.framework.TestCase;
import net.sf.hibernate.LazyInitializationException;
import testmodel.DBUtil;

/**
 * @author Gigix
 * xiongjie@csdn.net
 */
public class LazyLoadTestCase extends TestCase {
	private UserService _uService;
	private PostService _pService;
	
	public void setUp() throws Exception {
		_uService = Global.getGrollerServiceLocator().getUserService();
		_pService = Global.getGrollerServiceLocator().getPostService();
	}
	
	public void tearDown() throws Exception {
		DBUtil.removeAllInstance(User.class);
	}
	
	public void testLazyLoad() throws Exception {
		User user = new User();
		user.setAccount("gigix");
		user.setPassword("password");
		user = _uService.registerNewUser(user);
		
		Post post = new Post();
		post.setDescription("test");
		post = _pService.publishNewPost(user.getId(), post);
		
		User user1 = _uService.findUserByAccount("gigix");
		try {
			Post post1 = (Post) user1.getPosts().iterator().next();
			fail();
		} catch (LazyInitializationException e) {
		}
		
		Post post1 = _pService.loadPostById(post.getId());
		User user2 = post.getOwner();
		assertEquals(user1, user2);
	}
}

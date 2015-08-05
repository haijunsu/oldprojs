/*
 * Created on 2003-10-28
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.service.pojo;

import groller.application.dao.PostDAO;
import groller.application.dao.ReplyDAO;
import groller.application.dao.UserDAO;
import groller.application.entity.Post;
import groller.application.entity.Reply;
import groller.application.entity.User;
import groller.application.service.PostService;
import groller.framework.common.IdComparator;
import groller.framework.service.pojo.BaseService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class POJOPostService extends BaseService implements PostService {
	private PostDAO _postDao;
	private UserDAO _userDao;
	private ReplyDAO _replyDao;
	
	public void setPostDao(PostDAO dao) {
		_postDao = dao;
	}
	
	public void setUserDao(UserDAO dao) {
		_userDao = dao;
	}
	
	public void setReplyDao(ReplyDAO dao) {
		_replyDao = dao;
	}

	/* (non-Javadoc)
	 * @see groller.application.service.PostService#publishNewPost(groller.application.dto.PostDTO)
	 */
	public Post publishNewPost(Long userId, Post dto) {
		User owner = _userDao.load(userId);
		dto.setPostTime(Calendar.getInstance());
		return _postDao.create(owner, dto);
	}

	/* (non-Javadoc)
	 * @see groller.application.service.PostService#modifyPost(groller.application.dto.PostDTO)
	 */
	public Post modifyPost(Post dto) {
		return _postDao.update(dto);
	}

	/* (non-Javadoc)
	 * @see groller.application.service.PostService#removePost(groller.application.dto.PostDTO)
	 */
	public void removePostById(Long postId) {
		Post entity = _postDao.load(postId);
		_postDao.remove(entity);
	}

	/* (non-Javadoc)
	 * @see groller.application.service.PostService#getAllPostsOfUser(java.lang.Long)
	 */
	public Post[] getAllPostsOfUser(Long userId) {
		User user = _userDao.load(userId);
		Set posts = user.getPosts();
		List temp = new ArrayList(posts);
		Collections.sort(temp, new IdComparator());

		Post[] result = new Post[posts.size()];		
		posts.toArray(result);
		return result;
	}

	/* (non-Javadoc)
	 * @see groller.application.service.PostService#findPostsByTitle(java.lang.String)
	 */
	public Post[] findPostsByTitle(String title) {
		return _postDao.findByTitle(title);
	}

	/* (non-Javadoc)
	 * @see groller.application.service.PostService#publishNewReply(java.lang.Long, groller.application.dto.ReplyDTO)
	 */
	public Reply publishNewReply(Long postId, Reply dto) {
		Post owner = _postDao.load(postId);
		dto.setPostTime(Calendar.getInstance());
		return _replyDao.create(owner, dto);
	}

	/* (non-Javadoc)
	 * @see groller.application.service.PostService#getAllRepliesOfPost(java.lang.Long)
	 */
	public Reply[] getAllRepliesOfPost(Long postId) {
		Post owner = _postDao.load(postId);
		Set replies = owner.getReplies();
		
		List temp = new ArrayList(replies);
		Collections.sort(temp, new IdComparator());
		
		Reply[] result = new Reply[replies.size()];
		temp.toArray(result);
		return result;
	}

	/* (non-Javadoc)
	 * @see groller.application.service.PostService#loadPostById(java.lang.Long)
	 */
	public Post loadPostById(Long id) {
		return _postDao.load(id);
	}

}

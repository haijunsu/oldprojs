/*
 * Created on 2003-10-28
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.service;

import groller.application.entity.Post;
import groller.application.entity.Reply;
import groller.framework.service.IService;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public interface PostService extends IService {
	public Post publishNewPost(Long userId, Post dto);
	public Post loadPostById(Long id);
	public Post modifyPost(Post dto);
	public void removePostById(Long postId);
	public Post[] getAllPostsOfUser(Long userId);
	public Post[] findPostsByTitle(String title);
	public Reply publishNewReply(Long postId, Reply dto);
	public Reply[] getAllRepliesOfPost(Long postId);
}

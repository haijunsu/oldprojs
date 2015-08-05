/*
 * Created on 2003-10-27
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.dao;

import groller.application.entity.Post;
import groller.application.entity.User;
import groller.framework.dao.IDAO;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public interface PostDAO extends IDAO {
	public Post create(User owner, Post entity);
	public Post load(Long id);
	public Post update(Post entity);
	public void remove(Post entity);
	public Post[] findByTitle(String title);
}

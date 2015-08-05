/*
 * Created on 2003-10-28
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.dao;

import groller.application.entity.Post;
import groller.application.entity.Reply;
import groller.framework.dao.IDAO;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public interface ReplyDAO extends IDAO {
	public Reply create(Post owner, Reply entity);
	public Reply update(Reply entity);
	public void remove(Reply entity);
}

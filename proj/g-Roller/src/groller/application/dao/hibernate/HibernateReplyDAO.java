/*
 * Created on 2003-10-28
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.dao.hibernate;

import groller.application.dao.PostDAO;
import groller.application.dao.ReplyDAO;
import groller.application.entity.Post;
import groller.application.entity.Reply;
import groller.framework.dao.hibernate.BaseHibernateDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class HibernateReplyDAO extends BaseHibernateDAO implements ReplyDAO {
	private PostDAO _dao;
	private Log _log = LogFactory.getLog(HibernateReplyDAO.class);

	public void setPostDao(PostDAO dao) {
		_dao = dao;
	}

	/* (non-Javadoc)
	 * @see groller.application.dao.ReplyDAO#create(groller.application.entity.Post)
	 */
	public Reply create(Post owner, Reply entity) {
		saveEntity(entity);
		owner.getReplies().add(entity);
		entity.setOwner(owner);
		_dao.update(owner);
		
		return entity;
	}

	/* (non-Javadoc)
	 * @see groller.application.dao.ReplyDAO#remove(groller.application.entity.Reply)
	 */
	public void remove(Reply entity) {
		if(entity.getOwner() != null) {
			Post owner = entity.getOwner();
			owner.getReplies().remove(entity);
			entity.setOwner(null);
			_dao.update(owner);
		}
		removeEntity(entity);
	}

	/* (non-Javadoc)
	 * @see groller.application.dao.ReplyDAO#update(groller.application.entity.Reply)
	 */
	public Reply update(Reply entity) {
		return (Reply) updateEntity(entity);
	}

}

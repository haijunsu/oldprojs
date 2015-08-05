/*
 * Created on 2003-10-27
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.dao.hibernate;

import groller.application.dao.PostDAO;
import groller.application.dao.UserDAO;
import groller.application.entity.Post;
import groller.application.entity.User;
import groller.framework.dao.hibernate.BaseHibernateDAO;
import groller.framework.exception.DAOException;

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class HibernatePostDAO extends BaseHibernateDAO implements PostDAO {
	private UserDAO _uDao;
	private Log _log = LogFactory.getLog(HibernatePostDAO.class);

	public void setUserDao(UserDAO dao) {
		_uDao = dao;
	}

	/* (non-Javadoc)
	 * @see groller.application.dao.PostDAO#create()
	 */
	public Post create(User owner, Post entity) {
		saveEntity(entity);
		entity.setOwner(owner);
		owner.getPosts().add(entity);
		_uDao.update(owner);
		return entity;
	}

	/* (non-Javadoc)
	 * @see groller.application.dao.PostDAO#load(java.lang.Long)
	 */
	public Post load(Long id) {
		return (Post) loadEntityById(id);
	}

	/* (non-Javadoc)
	 * @see groller.application.dao.PostDAO#update(groller.application.entity.Post)
	 */
	public Post update(Post entity) {
		return (Post) updateEntity(entity);
	}

	/* (non-Javadoc)
	 * @see groller.application.dao.PostDAO#remove(groller.application.entity.Post)
	 */
	public void remove(Post entity) {
		if(entity.getOwner() != null) {
			User owner = entity.getOwner();
			owner.getPosts().remove(entity);
			entity.setOwner(null);
			_uDao.update(owner);
		}
		removeEntity(entity);
	}

	/* (non-Javadoc)
	 * @see groller.application.dao.PostDAO#findByTitle(java.lang.String)
	 */
	public Post[] findByTitle(String title) {
		Session session = currentSession();
		List entities;
		try {
			String query = "FROM Post post WHERE post.title like '%" + title + "%' ORDER BY post.id";
			entities = session.find(query);
		} catch (HibernateException e) {
			throw new DAOException("Error in HibernatePostDAO.findByTitle", e);
		}
		Post[] result = new Post[entities.size()];
		entities.toArray(result);
		return result;
	}

}

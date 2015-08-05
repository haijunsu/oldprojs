/*
 * Created on 2003-10-16
 */
package groller.application.dao.hibernate;

import groller.application.dao.UserDAO;
import groller.application.entity.User;
import groller.framework.dao.hibernate.BaseHibernateDAO;
import groller.framework.exception.DAOException;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class HibernateUserDAO extends BaseHibernateDAO implements UserDAO {

	/* (non-Javadoc)
	 * @see groller.application.dao.UserDAO#create()
	 */
	public User create(User entity) {
		saveEntity(entity);
		return entity;
	}

	/* (non-Javadoc)
	 * @see groller.application.dao.UserDAO#load(long)
	 */
	public User load(Long id) {
		return (User) loadEntityById(id);
	}

	/* (non-Javadoc)
	 * @see groller.application.dao.UserDAO#update(groller.application.entity.User)
	 */
	public User update(User entity) {
		return (User) updateEntity(entity);
	}

	/* (non-Javadoc)
	 * @see groller.application.dao.UserDAO#remove(groller.application.entity.User)
	 */
	public void remove(User entity) {
		removeEntity(entity);
	}

	/* (non-Javadoc)
	 * @see groller.application.dao.UserDAO#findByAccount(java.lang.String)
	 */
	public User findByAccount(String account) {
		Session session = currentSession();
		List result = new ArrayList();
		try {
			result = session.find(
				"FROM User u WHERE u.account = ?",	account, Hibernate.STRING);
		} catch (HibernateException e) {
			throw new DAOException("执行查询出错", e);
		}
		if(result.size() < 1) {
			throw new DAOException("不存在此User实体：account=" + account);
		}
		return (User) result.get(0);
	}

	/* (non-Javadoc)
	 * @see groller.application.dao.UserDAO#listAll()
	 */
	public User[] listAll() {
		Session session = currentSession();
		List result = new ArrayList();
		try {
			result = session.find("FROM User user ORDER BY user.id");
		} catch (HibernateException e) {
			throw new DAOException("执行查询出错", e);
		}
		
		User[] entities = new User[result.size()];
		result.toArray(entities);
		return entities;
	}

}

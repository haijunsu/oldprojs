/*
 * Created on 2003-10-16
 */
package groller.application.service.pojo;

import groller.application.dao.UserDAO;
import groller.application.entity.User;
import groller.application.service.UserService;
import groller.framework.exception.DAOException;
import groller.framework.exception.ServiceException;
import groller.framework.service.pojo.BaseService;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class POJOUserService extends BaseService implements UserService {
	private UserDAO _dao;
	
	public void setDao(UserDAO dao) {
		_dao = dao;
	}

	/* (non-Javadoc)
	 * @see groller.application.service.UserService#registerNewUser(groller.application.dto.UserDTO)
	 */
	public User registerNewUser(User dto) {
		try {
			_dao.findByAccount(dto.getAccount());
			throw new ServiceException("已经存在此用户。用户名=" + dto.getAccount());
		} catch (DAOException e) {
			// do nothing.
		}
		
		return _dao.create(dto);
	}

	/* (non-Javadoc)
	 * @see groller.application.service.UserService#loadUserById(java.lang.Long)
	 */
	public User loadUserById(Long id) {
		return _dao.load(id);
	}

	/* (non-Javadoc)
	 * @see groller.application.service.UserService#updateUserInfo(groller.application.dto.UserDTO)
	 */
	public User updateUserInfo(User dto) {
		return _dao.update(dto);
	}

	/* (non-Javadoc)
	 * @see groller.application.service.UserService#findUserByAccount(java.lang.String)
	 */
	public User findUserByAccount(String account) {
		return _dao.findByAccount(account);
	}

	/* (non-Javadoc)
	 * @see groller.application.service.UserService#listAllUsers()
	 */
	public User[] listAllUsers() {
		return _dao.listAll();
	}

	/* (non-Javadoc)
	 * @see groller.application.service.UserService#removeUser(java.lang.Long)
	 */
	public void removeUser(Long id) {
		User entity = _dao.load(id);
		_dao.remove(entity);
	}

	/* (non-Javadoc)
	 * @see groller.application.service.UserService#userLogon(java.lang.String, java.lang.String)
	 */
	public User userLogon(String userName, String password) {
		User entity = _dao.findByAccount(userName);
		if(password.equals(entity.getPassword())) {
			return entity;
		} 
		throw new ServiceException("用户名与密码不符：userName=" + userName);
	}

}

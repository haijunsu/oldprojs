/*
 * Created on 2003-10-16
 */
package groller.application.dao;

import groller.application.entity.User;
import groller.framework.dao.IDAO;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public interface UserDAO extends IDAO {
	public User create(User entity);
	public User load(Long id);
	public User update(User entity);
	public void remove(User entity);
	public User findByAccount(String account);
	public User[] listAll();
}

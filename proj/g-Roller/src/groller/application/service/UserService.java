/*
 * Created on 2003-10-16
 */
package groller.application.service;

import groller.application.entity.User;
import groller.framework.service.IService;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public interface UserService extends IService {
	public User registerNewUser(User dto);
	public User userLogon(String userName, String password);
	public User loadUserById(Long id);
	public User updateUserInfo(User dto);
	public User findUserByAccount(String account);
	public User[] listAllUsers();
	public void removeUser(Long id);
}

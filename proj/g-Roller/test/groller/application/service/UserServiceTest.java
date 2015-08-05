/*
 * Created on 2003-10-16
 */
package groller.application.service;

import groller.application.context.Global;
import groller.application.entity.User;
import groller.framework.dao.CannotFoundException;
import groller.framework.exception.ServiceException;
import junit.framework.TestCase;
import testmodel.DBUtil;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class UserServiceTest extends TestCase {
	private UserService _service;

	/**
	 * Constructor for UserServiceTest.
	 * @param arg0
	 */
	public UserServiceTest(String arg0) {
		super(arg0);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		_service = (UserService) Global.getGrollerServiceLocator().getUserService();
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		DBUtil.removeAllInstance(User.class);
	}

	public void testRegisterNewUser() {
		User dto = _service.registerNewUser(new User());
		assertNotNull(dto);
		assertNotNull(dto.getId());
		
		dto = new User();
		dto.setAccount("gigix");
		_service.registerNewUser(dto);
		try {
			_service.registerNewUser(dto);
			fail();
		} catch (ServiceException e) {
			assertTrue(true);
		}
	}

	public void testLoadUserById() {
		User dto1 = _service.registerNewUser(new User());
		User dto2 = _service.loadUserById(dto1.getId());
		assertNotNull(dto2);
		assertEquals(dto1, dto2);
	}

	public void testUpdateUserInfo() {
		User dto1 = _service.registerNewUser(new User());
		dto1.setAccount("Gigix");
		_service.updateUserInfo(dto1);
		
		User dto2 = _service.loadUserById(dto1.getId());
		assertEquals("Gigix", dto2.getAccount());
	}

	public void testFindUserByAccount() {
		User dto1 = new User();
		dto1.setAccount("Gigix");
		_service.registerNewUser(dto1);
		
		User dto2 = _service.findUserByAccount("Gigix");
		assertNotNull(dto2);
		assertEquals(dto1, dto2);
	}

	public void testListAllUsers() {
		for(int i = 0; i < 6; i++) {
			_service.registerNewUser(new User());
		}
		User[] all = _service.listAllUsers();
		assertEquals(6, all.length);
	}

	public void testRemoveUser() {
		User dto1 = _service.registerNewUser(new User());
		_service.removeUser(dto1.getId());
		assertEquals(0, _service.listAllUsers().length);
		try {
			_service.loadUserById(dto1.getId());
			fail();
		} catch(CannotFoundException e) {
			assertTrue(true);
		}
	}

	public void testLogon() {
		User dto = new User();
		dto.setAccount("gigix");
		dto.setPassword("password");
		_service.registerNewUser(dto);
		assertEquals(dto, _service.userLogon("gigix", "password"));
		try {
			_service.userLogon("gigix", "wrongPassword");
			fail();
		} catch (ServiceException e) {
		}
	}
}

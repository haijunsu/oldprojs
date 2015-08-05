/*
 * Created on 2003-10-16
 */
package groller.application.service;

import groller.framework.service.ServiceLocator;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class GrollerServiceLocator extends ServiceLocator {
	/*=====================================
	 * �ڴ�ע����Ҫʹ�õ�Service
	 * ����application.bean.xml����ϸ����
	 ====================================*/
	
	public UserService getUserService() {
		return (UserService) getService("userService", UserService.class);
	}
	
	public PostService getPostService() {
		return (PostService) getService("postService", PostService.class);
	}
}

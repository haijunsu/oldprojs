/*
 * Created on 2003-11-6
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.xmlrpc.impl;

import groller.application.context.Global;
import groller.application.entity.User;
import groller.application.service.UserService;
import groller.application.xmlrpc.IBlogger;

import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class Blogger implements IBlogger {
	private UserService _uService = Global.getGrollerServiceLocator().getUserService();
	private Log _log = LogFactory.getLog(getClass());
	
	private String _appKey;
	
	//TODO: App-Key是weblog的唯一标志符，应该在方法中加入App-Key的验证。
	public void setAppKey(String appKey) {
		_appKey = appKey;
	}

	public Hashtable[] getUsersBlogs(String appKey, String userName, String password) {
		try {
			User dto = _uService.userLogon(userName, password);
			Hashtable struct = new Hashtable();
			String serverPath = (String) Global.getSharedObject("serverPath");
			struct.put("url", serverPath + "user.do?id=" + dto.getId());
			struct.put("blogid", dto.getId().toString());
			struct.put("blogName", dto.getName());
			Hashtable[] result = new Hashtable[1];
			result[0] = struct;
			return result;
		} catch (Exception e) {
			_log.error(e);
			Hashtable fault = new Hashtable();
			fault.put("faultString", e.getMessage());
			return new Hashtable[]{fault};
		}
	}

	public String newPost(String appKey, String blogId, String userName, String password, String content, boolean publish) {
		return "un-implemented method : newPost";
	}
	
	public String editPost(String appKey, String postId, String userName, String password, String content, boolean publish) {
		return "un-implemented method : editPost";
	}
	
	public Hashtable getUserInfo(String appKey, String userName, String password) {
		return new Hashtable();
	}
	
	public String getTemplate(String appKey, String blogId, String userName, String password, String templateType) {
		return "un-implemented method : getTemplate";
	}
	
	public boolean setTemplate(String appKey, String blogId, String userName, String password, String template, String templateType) {
		return false;
	}
}

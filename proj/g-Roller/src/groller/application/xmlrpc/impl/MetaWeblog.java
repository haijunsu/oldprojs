/*
 * Created on 2003-11-6
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.xmlrpc.impl;

import groller.application.context.Global;
import groller.application.entity.Post;
import groller.application.entity.User;
import groller.application.service.PostService;
import groller.application.service.UserService;
import groller.application.xmlrpc.IMetaWeblog;

import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class MetaWeblog implements IMetaWeblog {
	private UserService _uService = Global.getGrollerServiceLocator().getUserService();
	private PostService _pService = Global.getGrollerServiceLocator().getPostService();
	private Log _log = LogFactory.getLog(getClass());

	private String _appKey;
	
	//TODO: App-Key是weblog的唯一标志符，应该在方法中加入App-Key的验证。
	public void setAppKey(String appKey) {
		_appKey = appKey;
	}

	/* (non-Javadoc)
	 * @see groller.application.xmlrpc.IMetaWeblog#newPost(java.lang.String, java.lang.String, java.lang.String, java.util.Hashtable, boolean)
	 */
	public String newPost(
		String blogId,
		String userName,
		String password,
		Hashtable struct,
		boolean publish) 
	{
		try {
			User user = _uService.userLogon(userName, password);
			Post post = getPostFromStruct(struct);
			post = _pService.publishNewPost(user.getId(), post);
//			setupWebAccessLink(post);
			return post.getId().toString();
		} catch (Exception e) {
			_log.error(e);
			return e.getMessage();		
		}
	}

	/* (non-Javadoc)
	 * @see groller.application.xmlrpc.IMetaWeblog#editPost(java.lang.String, java.lang.String, java.lang.String, java.util.Hashtable, boolean)
	 */
	public boolean editPost(
		String postId,
		String userName,
		String password,
		Hashtable struct,
		boolean publish) 
	{
		try {
			User user = _uService.userLogon(userName, password);
			Post post = getPostFromStruct(struct);
			post = _pService.modifyPost(post);
			setupWebAccessLink(post);
		} catch (Exception e) {
			_log.error(e);
			return false;	
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see groller.application.xmlrpc.IMetaWeblog#getPost(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Hashtable getPost(String postId, String userName, String password) {
		try {
			User user = _uService.userLogon(userName, password);
			Post[] posts = _pService.getAllPostsOfUser(user.getId());
			for(int i = 0; i < posts.length; i++) {
				if(posts[i].getId().toString().equals(postId)) {
					Hashtable struct = new Hashtable();
					insertEntry(struct, "title", posts[i].getTitle());
					insertEntry(struct, "url", posts[i].getLink());
					insertEntry(struct, "description", posts[i].getDescription());
					insertEntry(struct, "author", user.getMail());
					return struct;
				}
			}
		} catch (Exception e) {
			_log.error(e);
			Hashtable fault = new Hashtable();
			fault.put("faultString", e.getMessage());
			return fault;
		}
		Hashtable fault = new Hashtable();
		fault.put("faultString", "Cannot find post: ID=" + postId);
		return fault;
	}

	private void insertEntry(Hashtable struct, String key, String value) {
		if(value == null) {
			struct.put(key, "");
			return;
		}
		struct.put(key, value);
	}

	private Post getPostFromStruct(Hashtable struct) {
		Post post = new Post();
		post.setTitle((String) struct.get("title"));
		post.setDescription((String) struct.get("description"));	
		return post;
	}

	private void setupWebAccessLink(Post post) {
		String serverPath = (String) Global.getSharedObject("serverPath");
		String link = serverPath + "post.do?method=view&id=" + post.getId();
		post.setLink(link);
		_pService.modifyPost(post);
	}
}

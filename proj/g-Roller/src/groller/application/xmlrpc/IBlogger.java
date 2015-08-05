/*
 * Created on 2003-11-6
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.xmlrpc;

import java.util.Hashtable;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public interface IBlogger {
	public abstract Hashtable[] getUsersBlogs(
		String appKey,
		String userName,
		String password);
	public abstract String newPost(
		String appKey,
		String blogId,
		String userName,
		String password,
		String content,
		boolean publish);
	public abstract String editPost(
		String appKey,
		String postId,
		String userName,
		String password,
		String content,
		boolean publish);
	public abstract Hashtable getUserInfo(
		String appKey,
		String userName,
		String password);
	public abstract String getTemplate(
		String appKey,
		String blogId,
		String userName,
		String password,
		String templateType);
	public abstract boolean setTemplate(
		String appKey,
		String blogId,
		String userName,
		String password,
		String template,
		String templateType);
}
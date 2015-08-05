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
public interface IMetaWeblog {
	public String newPost(String blogId, String userName, String password, Hashtable struct, boolean publish); 
	public boolean editPost(String postId, String userName, String password, Hashtable struct, boolean publish);
	public Hashtable getPost(String postId, String userName, String password);
}

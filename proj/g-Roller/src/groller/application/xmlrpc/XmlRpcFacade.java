/*
 * Created on 2003-11-6
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.xmlrpc;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.XmlRpcServer;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class XmlRpcFacade {
	private IBlogger _blogger;
	private IMetaWeblog _metaWeblog;
	
	private Log _log = LogFactory.getLog(getClass());
	
	public void setBlogger(IBlogger blogger) {
		_blogger = blogger;
	}
	
	public void setMetaWeblog(IMetaWeblog metaWeblog) {
		_metaWeblog = metaWeblog;
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response) 
		throws IOException 
	{	
		XmlRpcServer xmlrpc = new XmlRpcServer();
		xmlrpc.addHandler("blogger", _blogger);
		xmlrpc.addHandler("metaWeblog", _metaWeblog);

		byte[] result = xmlrpc.execute(request.getInputStream());
		_log.info(new String(result));
		
		response.setContentType("text/xml; charset=GB2312");
		response.setContentLength(result.length);
		OutputStream out = response.getOutputStream();
		out.write(result);		
		out.flush();		
		out.close();
	}
}

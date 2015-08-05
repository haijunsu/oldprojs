/*
 * Created on 2003-10-16
 */
package groller.framework.session;

import groller.framework.common.BeanFactoryWrapper;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class SessionLocator {
	private static final Log _log = LogFactory.getLog(SessionLocator.class);
	private static HibernateSessionAdapter _adapter = (HibernateSessionAdapter) BeanFactoryWrapper.getBean("application", "sessionAdapter");
	
	public static Session getSession() {
		return _adapter.getSession();
	}
	
	public static void releaseSession() {
		_adapter.close();
	}
}

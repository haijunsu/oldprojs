/*
 * Created on 2003-10-15
 */
package groller.framework.session;

import junit.framework.TestCase;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class SessionAdapterTest extends TestCase {
	public void testGetSession() throws HibernateException {
		Session session = SessionLocator.getSession();
		assertNotNull(session);
		assertTrue(session.isConnected());
		assertTrue(session.isOpen());
		SessionLocator.releaseSession();
	}
}

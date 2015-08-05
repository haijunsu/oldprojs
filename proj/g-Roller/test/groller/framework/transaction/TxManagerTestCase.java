/*
 * Created on 2004-2-18
 */
package groller.framework.transaction;

import groller.framework.common.BeanFactoryWrapper;
import groller.framework.session.HibernateSessionAdapter;
import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import testmodel.DBUtil;
import testmodel.entity.Entity;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class TxManagerTestCase extends TestCase {
	private static final Log _log = LogFactory.getLog(TxManagerTestCase.class);
	
	protected void tearDown() throws Exception {
		DBUtil.removeAllInstance(Entity.class);
	}

	public void testEmbededTransaction() throws Exception {
		HibernateTxManager outer = (HibernateTxManager) BeanFactoryWrapper.getBean("application", "transactionManager");
		outer.beginTransaction();

		HibernateTxManager inner = (HibernateTxManager) BeanFactoryWrapper.getBean("application", "transactionManager");
		inner.beginTransaction();
		inner.rollback();

		HibernateSessionAdapter session = (HibernateSessionAdapter) BeanFactoryWrapper.getBean("application", "sessionAdapter");
		assertFalse("Session should not be closed here.", session.closed());

		outer.commit();
	}
}

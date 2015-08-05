/*
 * Created on 2003-10-16
 */
package testmodel;

import groller.framework.common.BeanFactoryWrapper;
import groller.framework.session.SessionLocator;
import groller.framework.transaction.ITxManager;

import java.io.Serializable;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class DBUtil {
	public static void removeAllInstance(Class clazz) throws HibernateException {
		ITxManager manager = (ITxManager) BeanFactoryWrapper.getBean("application", "transactionManager");
		manager.beginTransaction();
		try {
			String queryString = "from u in class " + clazz;
			getSession().delete(queryString);			
			getSession().flush();
			manager.commit();
		} catch (Exception e) {
			manager.rollback();
		}
	}
	
	private static void removeInstance(Class clazz, Serializable id) throws HibernateException {
		ITxManager manager = (ITxManager) BeanFactoryWrapper.getBean("application", "transactionManager");
		manager.beginTransaction();
		try {
			Object obj = getSession().load(clazz, id);
			getSession().delete(obj);			
			getSession().flush();
			manager.commit();
		} catch (Exception e) {
			manager.rollback();
			throw new RuntimeException("已经不存在此条记录，class = " + clazz.getName() + "，id = " + id.toString());
		}
	}

	private static Session getSession() {
		return SessionLocator.getSession();
	}
}

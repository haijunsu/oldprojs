/*
 * Created on 2003-10-15
 */
package groller.framework.dao;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for gigix.dao");
		//$JUnit-BEGIN$
		suite.addTest(new TestSuite(BaseHibernateDAOTest.class));
		suite.addTest(new TestSuite(EntityDAOTest.class));
		//$JUnit-END$
		return suite;
	}
}

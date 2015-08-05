/*
 * Created on 2003-10-15
 */
package groller.framework;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for gigix");
		//$JUnit-BEGIN$
		suite.addTest(groller.framework.service.AllTests.suite());
		suite.addTest(groller.framework.session.AllTests.suite());
		suite.addTest(groller.framework.dao.AllTests.suite());
		suite.addTest(groller.framework.transaction.AllTests.suite());
		suite.addTest(groller.framework.observer.AllTests.suite());
		//$JUnit-END$
		return suite;
	}
}

/*
 * Created on 2003-10-16
 */
package groller.application;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for groller.application");
		//$JUnit-BEGIN$
		suite.addTest(groller.application.dao.AllTests.suite());
		suite.addTest(groller.application.service.AllTests.suite());
		//$JUnit-END$
		return suite;
	}
}

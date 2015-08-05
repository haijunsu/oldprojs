/*
 * Created on 2003-10-16
 */
package groller.application.service;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for groller.application.service");
		//$JUnit-BEGIN$
		suite.addTest(new TestSuite(UserServiceTest.class));
		suite.addTest(new TestSuite(PostServiceTest.class));
		suite.addTest(new TestSuite(LazyLoadTestCase.class));
		//$JUnit-END$
		return suite;
	}
}

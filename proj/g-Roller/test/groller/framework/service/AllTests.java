/*
 * Created on 2003-10-16
 */
package groller.framework.service;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for gigix.service");
		//$JUnit-BEGIN$
		suite.addTest(new TestSuite(ServiceLocatorTest.class));
		//$JUnit-END$
		return suite;
	}
}

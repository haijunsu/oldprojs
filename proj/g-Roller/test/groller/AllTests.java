/*
 * Created on 2003-10-16
 */
package groller;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for groller");
		//$JUnit-BEGIN$
		suite.addTest(groller.application.AllTests.suite());
		suite.addTest(groller.framework.AllTests.suite());
		//$JUnit-END$
		return suite;
	}
}

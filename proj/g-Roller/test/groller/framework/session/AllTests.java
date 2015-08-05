/*
 * Created on 2003-10-15
 */
package groller.framework.session;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for gigix.session");
		//$JUnit-BEGIN$
		suite.addTest(new TestSuite(SessionAdapterTest.class));
		//$JUnit-END$
		return suite;
	}
}

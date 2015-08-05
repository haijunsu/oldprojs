/*
 * Created on 2004-2-18
 */
package groller.framework.transaction;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite =
			new TestSuite("Test for groller.framework.transaction");
		//$JUnit-BEGIN$
		suite.addTest(new TestSuite(TxManagerTestCase.class));
		//$JUnit-END$
		return suite;
	}
}

/* 
 * Project: G-Roller
 * Created on 2004-2-27
 */
package groller.framework.observer;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for groller.framework.observer");
		//$JUnit-BEGIN$
		suite.addTest(new TestSuite(ObservserTestCase.class));
		//$JUnit-END$
		return suite;
	}
}

/*
 * Created on 2003-10-16
 */
package groller.application.dao;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for groller.application.dao");
		//$JUnit-BEGIN$
		suite.addTest(new TestSuite(UserDAOTest.class));
		suite.addTest(new TestSuite(PostDAOTest.class));
		suite.addTest(new TestSuite(ReplyDAOTest.class));
		//$JUnit-END$
		return suite;
	}
}

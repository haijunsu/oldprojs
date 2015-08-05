package groller;
import groller.framework.AllTests;
import junit.framework.TestResult;
import junit.textui.TestRunner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
 * Created on 2004-2-20
 */

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class MultiThreadRunner extends Thread {
	private static final Log _log = LogFactory.getLog(MultiThreadRunner.class);
	private int _number;
	
	public MultiThreadRunner(int number) {
		_number = number;
		start();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		TestResult result = TestRunner.run(AllTests.suite());
		if(result.errorCount() + result.failureCount() > 0) {
			throw new RuntimeException("Thread " + _number + " ERROR!");
		}
	}

	public static void main(String[] args) {
		_log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++");
		_log.info("++++++++++ Begin Multi-Thread Execution +++++++++++");
		_log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		for(int i = 0; i < 20; i++) {
			MultiThreadRunner runner = new MultiThreadRunner(i);
		}
	}
}

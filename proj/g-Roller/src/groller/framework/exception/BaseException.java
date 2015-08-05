/*
 * Created on 2003-10-15
 */
package groller.framework.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author xiongj
 */
public class BaseException extends RuntimeException {
	private Log _log = LogFactory.getLog(getClass());
	private Throwable _rootCause;

	/**
	 * 
	 */
	public BaseException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param s
	 */
	public BaseException(String s) {
		this(s, null);
		_rootCause = this;
	}

	public BaseException(String s, Throwable e) {
		super(s);
		if(e instanceof BaseException) {
			_rootCause = ((BaseException)e)._rootCause;
		} else {
			_rootCause = e;
		}
		_log.debug(s, e);
	}
	
	public BaseException(Throwable e) {
		this("", e);
	}
	
	/**
	 * @return
	 */
	public Throwable getRootCause() {
		return _rootCause;
	}
}

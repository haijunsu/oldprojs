/*
 * Created on 2004-2-24
 */
package groller.framework.exception;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class ServiceException extends BaseException {

	/**
	 * 
	 */
	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param s
	 */
	public ServiceException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param s
	 * @param e
	 */
	public ServiceException(String s, Throwable e) {
		super(s, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param e
	 */
	public ServiceException(Throwable e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

}

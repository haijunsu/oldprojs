/*
 * Created on 2004-2-24
 */
package groller.framework.dao;

import groller.framework.exception.DAOException;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class CannotFoundException extends DAOException {

	/**
	 * 
	 */
	public CannotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public CannotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CannotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public CannotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

/*
 * Created on 2004-4-22
 */
package groller.framework.interceptor;

import groller.framework.exception.BaseException;
import groller.framework.exception.UnknownException;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Gigix
 * xiongjie@csdn.net
 */
public class ExceptionInterceptor implements MethodInterceptor {
	private static final Log _log = LogFactory.getLog(ExceptionInterceptor.class);

	/* (non-Javadoc)
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result;
		try {
			result = invocation.proceed();
		} catch (Throwable e) {
			if(e instanceof BaseException) {
				log(((BaseException)e).getRootCause(), invocation);
				throw e;
			} else {
				log(e, invocation);
				throw new UnknownException(e);
			}
		}
		return result;
	}

	private void log(Throwable e, MethodInvocation invocation) {
		String message = 
			"================ ExceptionInterceptor Report ====================\n" +			"Intercepted an exception : \n" +			"Message = " + e.getMessage() + "\n" +
			"Location = " + invocation.getThis().getClass().getName() + "[" + invocation.getMethod().getName() + "]";
		_log.info(message);
	}

}

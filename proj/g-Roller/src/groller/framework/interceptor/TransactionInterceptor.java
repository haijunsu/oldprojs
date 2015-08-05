/* 
 * Project: G-Roller
 * Created on 2004-3-16
 */
package groller.framework.interceptor;

import groller.framework.service.pojo.BaseService;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class TransactionInterceptor implements MethodInterceptor {
	private static final Log _log = LogFactory.getLog(TransactionInterceptor.class);

	/* (non-Javadoc)
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	public Object invoke(MethodInvocation invocation) throws Throwable {
		BaseService service = (BaseService) invocation.getThis();
		Method method = invocation.getMethod();
		Object result;
		try {
			service.beginTransaction();
			result = invocation.proceed();
			service.commitTransaction();
		} catch (Throwable e) {
			service.rollbackTransaction();
			throw e;
		} finally {
			service.releaseResource();
			_log.info("navy print...");
		}
		return result;
	}

}

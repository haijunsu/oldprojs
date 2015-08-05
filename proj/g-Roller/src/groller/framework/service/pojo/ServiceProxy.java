/*
 * Created on 2003-9-18
 */
package groller.framework.service.pojo;


import groller.framework.exception.BaseException;
import groller.framework.exception.KnownException;
import groller.framework.exception.UnknownException;
import groller.framework.observer.entity.Message;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author xiongj
 */
public class ServiceProxy implements InvocationHandler {
	private static final Log _log = LogFactory.getLog(ServiceProxy.class);
	private BaseService _service;
	private Class _interfaceType;
	
	private ServiceProxy(BaseService service, Class intf) {
		_service = service;
		_interfaceType = intf;
	}
	
	public static Object newInstance(Class intf, BaseService service) {
		return Proxy.newProxyInstance(
			service.getClass().getClassLoader(), 
			new Class[]{intf}, 
			new ServiceProxy(service, intf)
		);
	}

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		if(methodName.endsWith("$R")) {
			return invokeWithoutTransaction(method, args);
		}
		return invokeWithTransaction(method, args);
	}

	private Object invokeWithoutTransaction(Method method, Object[] args) throws IllegalArgumentException, IllegalAccessException {
		try {
			Object result = actualInvoke(method, args);
			return result;
		} catch (InvocationTargetException e) {
			if(e.getTargetException() instanceof BaseException) {
				throw new KnownException(e.getTargetException());
			} else {
				_log.error(e);
				throw new UnknownException(e);
			}
		} finally {
			_service.releaseResource();
		}
	}

	private Object invokeWithTransaction(Method method, Object[] args) throws IllegalAccessException {
		_service.beginTransaction();	//开启一次事务
		Object result = null;
		try {
			result = actualInvoke(method, args);		//实际调用service方法
			_service.commitTransaction();		//如果成功，提交事务
		} catch(InvocationTargetException e) {
			_service.rollbackTransaction();
			if(e.getTargetException() instanceof BaseException) {
				_log.info(((BaseException)e.getTargetException()).getRootCause());
				throw new KnownException(e.getTargetException());
			} else {
				_log.error(e.getTargetException());
				throw new UnknownException(e.getTargetException());
			}
		} finally {
			_service.releaseResource();
		}
		return result;
	}

	private Object actualInvoke(Method method, Object[] args)
		throws IllegalAccessException, InvocationTargetException 
	{
		Object result = method.invoke(_service, args);
		
		List observedMethodNames = _service.getObservedMethodNames();
		if(observedMethodNames.contains(method.getName())) {
			Message message = new Message(method.getName(), result);
			_service.publish(message);
		}
		
		return result;
	}

}
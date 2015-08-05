/*
 * Created on 2003-10-16
 */
package groller.application.context;

import groller.application.service.GrollerServiceLocator;
import groller.framework.common.BeanFactoryWrapper;
import groller.framework.exception.BaseException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class Global {
	private static ClassPathXmlApplicationContext _ctx;
	private static final Map _share = new HashMap();
	
	static {
		try {
			_ctx = new ClassPathXmlApplicationContext("/conf/context.xml");
		} catch (Exception e) {
			throw new ContextException(e);
		}
	}
	
	public static GrollerServiceLocator getGrollerServiceLocator() {
		return (GrollerServiceLocator) _ctx.getBean("grollerServiceLocator");
	}
	
	public static BeanFactoryWrapper getBeanFactory() {
		return (BeanFactoryWrapper) _ctx.getBean("beanFactoryWrapper");
	}
	
	public static void shareObject(String key, Object value) {
		_share.put(key, value);
	}
	
	public static Object getSharedObject(String key) {
		return _share.get(key);
	}
}

class ContextException extends BaseException {
	public ContextException() {
		super();
	}

	public ContextException(String s) {
		super(s);
	}

	public ContextException(String s, Throwable e) {
		super(s, e);
	}

	public ContextException(Throwable e) {
		super(e);
	}
}
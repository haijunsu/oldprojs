/*
 * Created on 2003-10-31
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.application.www.helper;

import groller.framework.exception.BeanException;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class RequestResolver {
	public static void copyProperties(HttpServletRequest request, Object bean) {
		Map paramMap = request.getParameterMap();
		Set params = paramMap.entrySet();
		for(Iterator it = params.iterator(); it.hasNext(); ) {
			Entry param = (Entry) it.next();
			String name = (String) param.getKey();
			String value = ((String[]) param.getValue())[0];
			try {
				setBeanProperty(bean, name, value);
			} catch (BeanException e) {
				// 无此属性，do nothing
			}
		}
	}
		
	public static String getServerPath(HttpServletRequest request) {
		String path = "http://";
		path += request.getServerName() + ":" + request.getServerPort();
		path += request.getContextPath() + "/";
		return path;
	}
		
	private static void setBeanProperty(Object bean, String propertyName, String propertyValue) {
		String errorInfo = "class=" + bean.getClass().getName() + "，name=" + propertyName;
		
		PropertyDescriptor descriptor;
		try {
			descriptor = new PropertyDescriptor(propertyName, bean.getClass());
		} catch (IntrospectionException e) {
			throw new BeanException("没有此Bean属性：" + errorInfo, e);
		}
		
		Method setter = descriptor.getWriteMethod();
		Class paramClazz = setter.getParameterTypes()[0];
		Object paramValue;
		try {
			Constructor paramCtor = paramClazz.getConstructor(new Class[]{String.class});
			paramValue = paramCtor.newInstance(new Object[]{propertyValue});
		} catch (Exception e) {
			throw new BeanException("无法将String转换成Bean属性类型：" + errorInfo, e);
		}
		
		try {
			setter.invoke(bean, new Object[]{paramValue});
		} catch (Exception e) {
			throw new BeanException("无法将属性值赋入Bean：" + errorInfo, e);
		}
	}
}

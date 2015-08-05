/*
 * Created on 2003-11-1
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package groller.framework.common;

import groller.framework.exception.BeanException;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class IdComparator implements Comparator {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		if(!(o1.getClass().equals(o2.getClass()))) {
			throw new ClassCastException(
				"Cannot compare two objects" +				"Fist class: " + o1.getClass().getName() +
				"Second class: " + o2.getClass().getName());
		}
		Long id1 = getId(o1);
		Long id2 = getId(o2);
		if(id1.longValue() < id2.longValue()) {
			return -1;
		} else if(id1.longValue() > id2.longValue()) {
			return 1;
		} else {
			return 0;
		}
	}

	private Long getId(Object obj) {
		PropertyDescriptor descriptor;
		try {
			descriptor = new PropertyDescriptor("id", obj.getClass());
		} catch (IntrospectionException e) {
			throw new BeanException("对象没有id属性：class=" + obj.getClass().getName(), e);
		}
		Method getter = descriptor.getReadMethod();
		try {
			return (Long) getter.invoke(obj, new Object[0]);
		} catch (Exception e) {
			throw new BeanException("调用getId()方法出错：class=" + obj.getClass().getName(), e);
		}
	}

}

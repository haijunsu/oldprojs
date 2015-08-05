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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * @author purewater
 *	dongfang_xiao@hotmail.com
 *
 *
 */
public class SequenceComparator implements Comparator {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		if(!(o1.getClass().equals(o2.getClass()))) {
			throw new ClassCastException(
				"Cannot compare two objects" +				"Fist class: " + o1.getClass().getName() +
				"Second class: " + o2.getClass().getName());
		}
		Long id1 = getSequence(o1);
		Long id2 = getSequence(o2);
		if(id1.longValue() < id2.longValue()) {
			return -1;
		} else if(id1.longValue() > id2.longValue()) {
			return 1;
		} else {
			return 0;
		}
	}

	private Long getSequence(Object obj) {
		PropertyDescriptor descriptor;
		try {
			descriptor = new PropertyDescriptor("sequenceNo", obj.getClass());
			Method getter = descriptor.getReadMethod();
			return (Long) getter.invoke(obj, new Object[0]);
		} catch (IllegalArgumentException e1) {
			throw new BeanException("调用getSequenceNo()方法出错：class=" + obj.getClass().getName(), e1);
		} catch (IllegalAccessException e1) {
			throw new BeanException("调用getSequenceNo()方法出错：class=" + obj.getClass().getName(), e1);
		} catch (InvocationTargetException e1) {
			throw new BeanException("调用getSequenceNo()方法出错：class=" + obj.getClass().getName(), e1);
		} catch (IntrospectionException e) {
			throw new BeanException("对象没有sequence属性：class=" + obj.getClass().getName(), e);
		}
	}

}

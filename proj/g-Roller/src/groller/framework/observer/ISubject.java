/*
 * Created on 2004-2-26
 */
package groller.framework.observer;

import groller.framework.observer.entity.Message;

import java.util.List;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public interface ISubject {
	public void attach(IObserver listener);
	public void detach(IObserver listener);
	public void publish(Message message);
	public List getObservedMethodNames();
}

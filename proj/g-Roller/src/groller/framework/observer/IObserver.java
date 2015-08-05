/*
 * Created on 2004-2-26
 */
package groller.framework.observer;

import groller.framework.observer.entity.Message;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public interface IObserver {
	public void deal(Message message);
}

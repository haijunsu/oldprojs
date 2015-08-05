/*
 * Created on 2003-9-18
 */
package groller.framework.service.pojo;

import groller.framework.exception.ServiceException;
import groller.framework.observer.IObserver;
import groller.framework.observer.ISubject;
import groller.framework.observer.entity.Message;
import groller.framework.service.IService;
import groller.framework.transaction.ITxManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * The common base class of all concrete POJO service component.
 * Every concrete service class must extend this class 
 * and implement its own business interface, e.g.:<br>
 * <code>public class POJOSomeService extends BaseService implements ISomeService</code><br>
 * BaseService delegates all transaction and resource related operations to TxManager, 
 * so every concrete service component will has a dependency to TxManager in its configuration, 
 * although it typically will never use TxManager explicitly.
 * @author xiongj
 */
public abstract class BaseService implements IService, ISubject {
	private ITxManager _txManager;
	private Set _observers = new HashSet();
	private List _observedMethodNames;

	/**
	 * Setter injection of transaction manager(TxManager).
	 * @param manager Transaction manager this component will use
	 */
	public void setTxManager(ITxManager manager) {
		_txManager = manager;
	}

	public void setObservers(List classNames) {
		if(classNames == null) {
			return;
		}
		for(int i = 0; i < classNames.size(); i++) {
			try {
				Class clazz = Class.forName((String) classNames.get(i));
				IObserver observer = (IObserver) clazz.newInstance();
				attach(observer);
			} catch (Exception e) {
				throw new ServiceException(
					"Error in create Observer " + classNames.get(i) + 
					" : " + e.getMessage(), e);
			}
		}
	}
	
	public void setObservedMethodNames(List methodNames) {
		_observedMethodNames = methodNames;
	}

	public void beginTransaction() {
		_txManager.beginTransaction();
	}

	public void commitTransaction() {
		_txManager.commit();
	}

	public void rollbackTransaction() {
		_txManager.rollback();
	}
	
	public void releaseResource() {
		_txManager.releaseResource();
	}
	
	public void attach(IObserver listener) {
		_observers.add(listener);
	}

	public void detach(IObserver listener) {
		_observers.remove(listener);
	}

	public void publish(Message message) {
		for(Iterator it = _observers.iterator(); it.hasNext(); ) {
			IObserver observer = (IObserver) it.next();
			observer.deal(message);
		}
	}

	/* (non-Javadoc)
	 * @see groller.framework.observer.ISubject#getObservedMethodNames()
	 */
	public List getObservedMethodNames() {
		if(_observedMethodNames == null) {
			_observedMethodNames = new ArrayList();
		}
		return _observedMethodNames;
	}

}

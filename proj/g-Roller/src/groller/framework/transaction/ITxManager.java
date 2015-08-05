/*
 * Created on 2003-9-18
 */
package groller.framework.transaction;

/**
 * @author xiongj
 */
public interface ITxManager {
	public void beginTransaction();
	public void commit();
	public void rollback();
	public void releaseResource();
}

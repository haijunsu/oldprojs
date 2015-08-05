/*
 * Created on 2003-9-18
 */
package groller.framework.transaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author xiongj
 */
public class MockTxManager implements ITxManager {
	private Log _log = LogFactory.getLog(MockTxManager.class);

	/* (non-Javadoc)
	 * @see gigix.transaction.TxManager#beginTransaction()
	 */
	public void beginTransaction() {
		_log.info("Transaction Begined");
	}

	/* (non-Javadoc)
	 * @see gigix.transaction.TxManager#commit()
	 */
	public void commit() {
		_log.info("Transaction Commited");
	}

	/* (non-Javadoc)
	 * @see gigix.transaction.TxManager#rollback()
	 */
	public void rollback() {
		_log.info("Transaction Rolled Back");
	}

	/* (non-Javadoc)
	 * @see groller.framework.transaction.ITxManager#releaseResource()
	 */
	public void releaseResource() {
		_log.info("All Resource Released");
	}

}

/* 
 * Project: G-Roller
 * Created on 2004-2-27
 */
package testmodel.observer.impl;

import groller.framework.observer.IObserver;
import groller.framework.observer.entity.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class MockObserver implements IObserver {

	/* (non-Javadoc)
	 * @see groller.framework.observer.IObserver#deal(groller.framework.observer.entity.Message)
	 */
	public void deal(Message message) {
		File file = new File("C:\\mock.observer");
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(message);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

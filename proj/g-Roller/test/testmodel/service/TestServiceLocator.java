/* 
 * Project: G-Roller
 * Created on 2004-2-27
 */
package testmodel.service;

import groller.framework.service.ServiceLocator;

/**
 * @author gigix
 *	xiongjie@csdn.net
 */
public class TestServiceLocator extends ServiceLocator {
	public TestServiceLocator() {
		setConfigName("application");
	}
	
	public IEntityService getEntityService() {
		return (IEntityService) getService("entityService", IEntityService.class);
	}
}

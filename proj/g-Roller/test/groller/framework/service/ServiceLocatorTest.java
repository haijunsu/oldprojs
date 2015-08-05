/*
 * Created on 2003-10-16
 */
package groller.framework.service;

import junit.framework.TestCase;
import testmodel.DBUtil;
import testmodel.entity.Entity;
import testmodel.service.IEntityService;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class ServiceLocatorTest extends TestCase {
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		DBUtil.removeAllInstance(Entity.class);
	}

	public void testGetService() {
		ServiceLocator locator = new ServiceLocator();
		locator.setConfigName("application");
		IEntityService service = (IEntityService) locator.getService("entityService", IEntityService.class);
		assertNotNull(service);
		service.createNewEntity();
	}

}

/*
 * Created on 2003-10-16
 */
package testmodel.service;

import groller.framework.service.IService;
import testmodel.dto.EntityDTO;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public interface IEntityService extends IService {
	public EntityDTO createNewEntity();
}

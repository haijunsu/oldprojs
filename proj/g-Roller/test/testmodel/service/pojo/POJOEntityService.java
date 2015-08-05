/*
 * Created on 2003-10-16
 */
package testmodel.service.pojo;

import groller.framework.service.pojo.BaseService;
import testmodel.dao.IEntityDAO;
import testmodel.dto.EntityDTO;
import testmodel.service.IEntityService;

/**
 * @author xiongj
 * 	xiongj@hzjbbis.com.cn
 */
public class POJOEntityService extends BaseService implements IEntityService {
	private static int _count = 1;
	private IEntityDAO _dao;
	
	public void setDao(IEntityDAO dao) {
		_dao = dao;
	}

	/* (non-Javadoc)
	 * @see test.service.IEntityService#createNewEntity()
	 */
	public EntityDTO createNewEntity() {
		EntityDTO dto = new EntityDTO();
		dto.setId(new Long(_count++));
		return dto;
	}

}

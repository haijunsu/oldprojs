/*
 * @(#)BaseServiceImpl.java  2005-1-6
 * Copyright (c) 2005. All rights reserved.
 *
 * $Header$
 * $Log: BaseServiceImpl.java,v $
 * Revision 1.1  2005/01/20 03:14:25  navy
 * Create SMiRT 18 project
 *
 */
package com.navy.framework.service.impl;

import com.navy.framework.dao.GeneralDao;
import com.navy.framework.exception.ServiceException;
import com.navy.framework.service.IService;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * Base Service implement. Every business service should extend it.
 * </p>
 *
 * $Revision: 1.1 $
 *
 * @author su_haijun <a href=mailto:su_hj@126.com>su_hj@126.com</a>
 */
public class BaseServiceImpl implements IService {
	/**
	 * Logger for this class
	 */
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(BaseServiceImpl.class);

	protected GeneralDao generalDao = null;

	public void setGeneralDao(GeneralDao dao) {
		generalDao = dao;
	}

	protected void handleException(Exception e, String errCode, Object[] params)
			throws ServiceException {
		logger.error("Exception: " + e.getMessage(), e);
		throw new ServiceException(errCode, params);
	}

	protected void handleException(Exception e, String errCode, String param)
			throws ServiceException {
		handleException(e, errCode, new Object[] { param });
	}

	protected void handleException(Exception e, String errCode)
			throws ServiceException {
		handleException(e, errCode, new Object[] { null });
	}

}

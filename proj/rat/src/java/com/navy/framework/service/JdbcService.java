/*
 * @(#)JdbcService.java  2006-12-14
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.service;

import java.util.List;

import com.navy.framework.exception.ServiceException;
import com.navy.framework.service.IService;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * Run native sqls
 * </p>
 *
 */
public interface JdbcService extends IService {

	public int update(String sql) throws ServiceException;

	public int[] updateBatch(String[] sqls) throws ServiceException;

	public void execute(String sql) throws ServiceException;

	public List query(String sql) throws ServiceException;

	public List query(String sql, int maxRows) throws ServiceException;

	public int queryForInt(String sql) throws ServiceException;

	public long queryForLong(String sql) throws ServiceException;

	public Object queryForObject(String sql, Class object)
			throws ServiceException;

}

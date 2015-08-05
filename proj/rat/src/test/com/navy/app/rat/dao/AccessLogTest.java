/*
 * @(#)AccessLogTest.java  2006-12-24
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.dao;

import junit.framework.TestCase;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 */
public class AccessLogTest extends TestCase {

	private static AccessLogDao dao = null;

	/**
	 * @throws java.lang.Exception
	 */
	protected void setUp() throws Exception {
		dao = DaoFactory.getAccessLogDao();
	}

	/**
	 * @throws java.lang.Exception
	 */
	protected void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.navy.app.rat.dao.impl.AccessLogDaoImpl#create(com.navy.app.rat.entities.AccessLog)}.
	 */
	public final void testCreate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.navy.app.rat.dao.impl.AccessLogDaoImpl#get(java.lang.Long)}.
	 */
	public final void testGet() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.navy.app.rat.dao.impl.AccessLogDaoImpl#load(java.lang.Long)}.
	 */
	public final void testLoad() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.navy.framework.dao.impl.BaseDaoImpl#count(java.lang.String)}.
	 */
	public final void testCount() {
		int _i = dao.count(null);
		assertEquals(_i, 8);
	}

	/**
	 * Test method for
	 * {@link com.navy.framework.dao.impl.BaseDaoImpl#find(java.lang.String)}.
	 */
	public final void testFindString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.navy.framework.dao.impl.BaseDaoImpl#deleteByWhereClause(java.lang.String)}.
	 */
	public final void testDeleteByWhereClause() {
		dao.deleteByWhereClause(null);
		assertEquals(dao.count(null), 0);
	}

}

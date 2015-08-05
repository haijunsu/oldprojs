/*
 * @(#)UserDaoTest.java  2006-12-29
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import junit.framework.TestCase;

import com.navy.framework.dao.UserDao;
import com.navy.framework.models.User;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 */
public class UserDaoTest extends TestCase {

	/**
	 * Logger for this class
	 */
	private static final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory
			.getLog(UserDaoTest.class);

	UserDao dao = null;

	/*
	 * (non-Javadoc)
	 *
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		dao = DaoFactory.getUserDao();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link com.navy.app.rat.dao.impl.UserDaoImpl#create(com.navy.app.rat.entities.User)}.
	 */
	public final void testCreate() {
		// dao.deleteByWhereClause(null);

		if (logger.isDebugEnabled()) {
			logger.debug("row count = " + dao.count(null));
		}

		assertEquals(4, dao.count(null));
	}

	/**
	 * Test method for
	 * {@link com.navy.app.rat.dao.impl.UserDaoImpl#get(java.lang.Long)}.
	 */
	public final void testGetLong() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.navy.app.rat.dao.impl.UserDaoImpl#load(java.lang.Long)}.
	 */
	public final void testLoad() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link com.navy.app.rat.dao.impl.UserDaoImpl#get(java.lang.String)}.
	 */
	public final void testGetString() {
		User _user = dao.get("tomcat");
		logger.debug(_user.toString());
	}

	public final void testQuery() {
		// String _strWhereClause = "registerDate > '" + "2006-12-29" + "'";
		String _strHql = "from User where registerDate between ? and ?";

		// List _list = dao.findByHQL(_strHql, new Object[] { new Date(),
		// new Timestamp(System.currentTimeMillis()) });
		// for (Iterator iter = _list.iterator(); iter.hasNext();) {
		// User element = (User) iter.next();
		// if (logger.isDebugEnabled()) {
		// logger.debug(element.getUserName());
		// }
		//
		// }
		// _list = dao.find(null, null, 2, 2);
		//
		// for (Iterator iter = _list.iterator(); iter.hasNext();) {
		// User element = (User) iter.next();
		// if (logger.isDebugEnabled()) {
		// logger.debug(element.getUserName());
		// }
		//
		// }
	}
}

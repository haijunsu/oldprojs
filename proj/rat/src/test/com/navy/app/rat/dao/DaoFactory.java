/*
 * @(#)DaoFactory.java  2006-12-24
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.dao;

import com.navy.framework.dao.UserDao;
import com.navy.framework.factory.FrameworkResourcesFactory;

/**
 * <p><b>Description</b></p>
 * <p></p>
 *
 */
public class DaoFactory extends FrameworkResourcesFactory {

	public static UserDao getUserDao() {
		return (UserDao)getResource("userDao");
	}

}

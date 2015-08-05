/*
 * @(#)ServerDbTimeParserImpl.java  2007-1-12
 * Copyright (c) 2007. All rights reserved.
 *
 * $Header$
 */

package com.navy.framework.dao.impl;

import java.util.Date;

import com.navy.framework.dao.DbTimeParser;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 * @author Haijun Su
 */
public class ServerDbTimeParserImpl implements DbTimeParser {

	/*
	 * (non-Javadoc)
	 *
	 * @see com.navy.framework.dao.DbTimeParser#getDbTime()
	 */
	public Date getDbTime() {
		return new Date();
	}

}

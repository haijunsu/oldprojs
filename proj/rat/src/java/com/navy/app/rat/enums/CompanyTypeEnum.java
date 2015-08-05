/*
 * @(#)LoginActionEnum.java  2006-12-24
 * Copyright (c) TrueTel Communications 2006. All rights reserved.
 *
 * $Header$
 */

package com.navy.app.rat.enums;

import com.navy.framework.enums.BaseEnum;

/**
 * <p>
 * <b>Description</b>
 * </p>
 * <p>
 * </p>
 *
 */
public class CompanyTypeEnum extends BaseEnum {

	public static CompanyTypeEnum ENTERPRISE = new CompanyTypeEnum(1);

	public static CompanyTypeEnum UNIVERSITY = new CompanyTypeEnum(2);

	protected CompanyTypeEnum(int integer) {
		super(integer);
	}

}

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
public class LoginActionEnum extends BaseEnum {

	public static LoginActionEnum LOGIN = new LoginActionEnum(1);

	public static LoginActionEnum LOGOUT = new LoginActionEnum(2);

	protected LoginActionEnum(int integer) {
		super(integer);
	}

}

/*
 * @(#)UploadException.java  2003-8-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.upload;

/**
 * <P>上载/下载文件时抛出的异常 </P>
 * 
 * @version 0.1
 * @author navy
 */
public class UploadException extends Exception {

	/**
	 * 构造异常
	 * @param str 异常信息
	 */
	UploadException(String str) {
		super(str);
	}
}

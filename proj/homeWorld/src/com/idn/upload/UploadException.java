/*
 * @(#)UploadException.java  2003-8-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.upload;

/**
 * <P>����/�����ļ�ʱ�׳����쳣 </P>
 * 
 * @version 0.1
 * @author navy
 */
public class UploadException extends Exception {

	/**
	 * �����쳣
	 * @param str �쳣��Ϣ
	 */
	UploadException(String str) {
		super(str);
	}
}

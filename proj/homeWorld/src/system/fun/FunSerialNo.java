/*
 * @(#)FunSerialNo.java  2003-06-30
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.fun;
/**
 * <P>与自动发号相关函数</P>
 * 
 * @version 1.0
 * @author IDN
 */
public class FunSerialNo  {
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(FunSerialNo.class);
	//	自动发号标志 ("0":新号在最后;"1":新号先补以前的空号)
	private String autoNoFlag;   
	public FunSerialNo() {
		super();
		autoNoFlag = "0";
	}

}


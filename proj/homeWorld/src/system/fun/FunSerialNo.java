/*
 * @(#)FunSerialNo.java  2003-06-30
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.fun;
/**
 * <P>���Զ�������غ���</P>
 * 
 * @version 1.0
 * @author IDN
 */
public class FunSerialNo  {
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(FunSerialNo.class);
	//	�Զ����ű�־ ("0":�º������;"1":�º��Ȳ���ǰ�Ŀպ�)
	private String autoNoFlag;   
	public FunSerialNo() {
		super();
		autoNoFlag = "0";
	}

}


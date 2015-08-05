/*
 * @(#)SalaryInputAllCtrl.java  2003-05-15
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.action;
/**
 * <P>工资录入中使用的错误VALUE BEAN</P>
 * <ul>
 * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class ErrorValue {
	String classname;
	String funname;
	String errordetail;
	String flag;
	public ErrorValue(){
		classname = null;
		funname = null;
		errordetail = null;
		flag = "0";
	}
	
	public void setError(String string1,String string2,String string3) {
		classname = string1;
		funname = string2;
		errordetail = string3;
	}
	/**
	 * @return
	 */
	public String getClassName() {
		return classname;
	}

	/**
	 * @return
	 */
	public String getErrorDetail() {
		return errordetail;
	}

	/**
	 * @return
	 */
	public String getFunName() {
		return funname;
	}

	/**
	 * @param string
	 */
	public void setClassName(String string) {
		classname = string;
	}

	/**
	 * @param string
	 */
	public void setErrorDetail(String string) {
		errordetail = string;
	}

	/**
	 * @param string
	 */
	public void setFunName(String string) {
		funname = string;
	}

	/**
	 * @return
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param string
	 */
	public void setFlag(String string) {
		flag = string;
	}

}

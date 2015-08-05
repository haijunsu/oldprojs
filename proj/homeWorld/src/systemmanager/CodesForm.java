/**
 * @(#)LogonAction.java  2003-1-28
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package systemmanager;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author 刘坚
 */
public class CodesForm extends ActionForm {
	
	private String flag = "";
	private String currrow = "";
	private String currcolumn = "";
	private String[] rowstate = null;
	private String[] rowid = null;
	private String cclass = "";
	private String cclassshowc = "";
	private String[] ccode = null;
	private String[] cshowc = null;

	/**
	 * 
	 */
	public CodesForm() {
		super();
		flag = "";
		currrow = "-1";
		currcolumn = "";
		cclass = "";
	}
	
	/**
 	 * <P>变量输入函数</P>
	 * @return
	 */
	public String getFlag() {
		return flag;
	}
	
	public String getCurrrow() {
		return currrow;
	}

	public String getCurrcolumn() {
		return currcolumn;
	}
	
	public String[] getRowstate() {
		return rowstate;
	}

	public String[] getRowid() {
		return rowid;
	}
	
	public String getCclass() {
		return cclass;
	}

	public String getCclassshowc() {
		return cclassshowc;
	}

	public String[] getCcode() {
		return ccode;
	}

	public String[] getCshowc() {
		return cshowc;
	}

	/**
 	 * <P>变量输出函数</P>
	 * @param string
	 */
	public void setFlag(String string) {
		flag = string;
	}

	public void setCurrrow(String string) {
		currrow = string;
	}

	public void setCurrcolumn(String string) {
		currcolumn = string;
	}

	public void setRowid(String[] string) {
		rowid = string;
	}

	public void setRowstate(String[] string) {
		rowstate = string;
	}

	public void setCclass(String string) {
		cclass = string;
	}

	public void setCclassshowc(String string) {
		cclassshowc = string;
	}

	public void setCcode(String[] string) {
		ccode = string;
	}

	public void setCshowc(String[] string) {
		cshowc = string;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		flag = "";
		currrow = "-1";
		currcolumn = "";
		cclass = "";
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
//		String sTemp = "";
//		sTemp=pay;
//		if (flag.equals("salary")){
//			sTemp=salary;
//		}
//		try {
//			float f = Float.parseFloat(sTemp);
//		} catch (Exception e) {
//			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.double"));
//		}
		return errors;
	}
}

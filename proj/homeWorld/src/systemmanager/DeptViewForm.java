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
 * @author 李永初
 */
public class DeptViewForm extends ActionForm {
	
	private String program="";	
	private String key = "";
	private String focusnow = "";
	private String visib = "";
	
	private String[] columnname = null;
	private String[] rowno= null;
	private String[] columncount= null;
	/**
	 * 
	 */
	public DeptViewForm() {
		super();
		key = "";
	}
	
	/**
	 * <P>变量输入函数</P>
	 * @return
	 */
	public String getVisib() {
		return visib;
	}
	public String getKey() {
		return key;
	}
	public String getFocusnow() {
		return focusnow;
	}
	public String getprogram() {
		return program;
	}

	public String[] getColumnname() {
		return columnname;
	}

	public String[] getColumncount() {
		return columncount;
	}

	public String[] getRowno() {
		return rowno;
	}
	/**
	 * <P>变量输出函数</P>
	 * @param string
	 */
	public void setVisib(String string) {
		visib= string;
	}
	public void setKey(String string) {
		key= string;
	}
	public void setFocusnow(String string) {
		focusnow= string;
	}
	public void setprogram(String string) {
		program= string;
	}
	public void setColumnname(String[] string) {
		columnname = string;
	}

	public void setColumncount(String[] string) {
		columncount = string;
	}
		
	public void setRowno(String[] string) {
		rowno = string;
	}
		
	

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		key = "";
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		return errors;
	}
}

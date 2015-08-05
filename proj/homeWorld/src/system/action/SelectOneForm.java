/*
 * @(#)SalaryParametForm.java  2003-6-13
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
 
package system.action;



import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author lyc
 */
public class SelectOneForm extends ActionForm {


	private String[] rowid = null;
	private String[] rowshow = null;
	private String cshowc = null;

	private String[][] row = null;
	
	
	public SelectOneForm (){
		super();	
	}

	/**
	 * <P>变量输入函数</P>
	 * @return
	 */

	public String getCshowc() {
			return cshowc;
	}
	public String[] getRowid() {
		return rowid;
	}

	public String[] getRowshow() {
		return rowshow;
	}
	
		/**
	 * <P>变量输出函数</P>
	 * @param string
	 */	


	public void setCshowc(String string) {
		cshowc = string;
	}
	public void setRowid(String[] string) {
		rowid = string;
	}
	
	public void setRowshow(String[] string) {
		rowshow = string;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		return errors;
	}


	/**
	 * @return
	 */
	public String[][] getRow() {
		return row;
	}

	/**
	 * @param strings
	 */
	public void setRow(String[][] strings) {
		row = strings;
	}

}

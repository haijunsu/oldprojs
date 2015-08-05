/**
 * @(#)LogonAction.java  2003-1-28
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package system.action;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.idn.log.LogWrapper;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author ¿Ó”¿≥ı
 */
public class SelectFieldForm extends ActionForm {
	
	LogWrapper log = new LogWrapper("SalaryUserForm");
		
	private String[][] show = null;//œ‘ æ¡–
	private String[] title = null;
	private String[] hiddend = null;
	private String[] size = null;
	private String qtitle = "";
	private String cellcount="";
	private String mark = "0";
	/**
	 * 
	 */
	public SelectFieldForm() {
		super();
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
	public String[][] getShow() {
		return show;
	}

	/**
	 * @param strings
	 */
	public void setShow(String[][] strings) {
		show = strings;
	}

	/**
	 * @return
	 */
	public String getCellcount() {
		return cellcount;
	}

	/**
	 * @param string
	 */
	public void setCellcount(String string) {
		cellcount = string;
	}

	/**
	 * @return
	 */
	public String[] getTitle() {
		return title;
	}

	/**
	 * @param strings
	 */
	public void setTitle(String[] strings) {
		title = strings;
	}


	/**
	 * @return
	 */
	public String[] getHiddend() {
		return hiddend;
	}

	/**
	 * @param strings
	 */
	public void setHiddend(String[] strings) {
		hiddend = strings;
	}

	/**
	 * @return
	 */
	public String[] getSize() {
		return size;
	}

	/**
	 * @param strings
	 */
	public void setSize(String[] strings) {
		size = strings;
	}

	/**
	 * @return
	 */
	public String getQtitle() {
		return qtitle;
	}

	/**
	 * @param string
	 */
	public void setQtitle(String string) {
		qtitle = string;
	}

	/**
	 * @return
	 */
	public String getMark() {
		return mark;
	}

	/**
	 * @param string
	 */
	public void setMark(String string) {
		mark = string;
	}


}

/*
 * @(#)SalaryParametForm.java  2003-6-13
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
 * @author lyc
 */
public class ParametForm extends ActionForm {

	private String nowdate;
	private String page = "1";
	private String flag = "";
	private String currrow = "";
	private String currcolumn = "";
	

	private String[] policyshow = null;
	private String[] rowlistold = null;
	private String[] rowstate = null;
	private String[] rowid = null;
	
	private String[] policyid = null;
	private String[] subsect = null;
	private String[] paramet = null;
	private String[] effedate = null;
	private String[] paramet2 = null;
	
	public ParametForm (){
		super();
		flag="";
		page = "1";
		nowdate="";
	}

	
	/**
	 * <P>变量输入函数</P>
	 * @return
	 */

	
	public String getNowdate() {
		return nowdate;
	}
	
	public String getFlag() {
		return flag;
	}
	
	public String getPage() {
		return page;
	}
	                 
	public String getCurrrow() {
		return currrow;
	}
	
	public String getCurrcolumn() {
		return currcolumn;
	}

	public String[] getPolicyshow() {
		return policyshow;
	}

	public String[] getRowlistold() {
		return rowlistold;
	}

	public String[] getRowid() {
		return rowid;
	}

	public String[] getRowstate() {
		return rowstate;
	}

	public String[] getPolicyid() {
		return policyid;
	}

	public String[] getSubsect() {
		return subsect;
	}

	public String[] getParamet() {
		return paramet;
	}

	public String[] getEffedate() {
		return effedate;
	}

	public String[] getParamet2() {
		return paramet2;
	}
	/**
	 * <P>变量输出函数</P>
	 * @param string
	 */	

	public void setNowdate(String string) {
		nowdate = string;
	}
	
	public void setFlag(String string) {
		flag = string;
	}
	
	public void setPage(String string) {
		page = string;
	}

	public void setCurrrow(String string) {
		currrow = string;
	}

	public void setCurrcolumn(String string) {
		currcolumn = string;
	}
	
	public void setPolicyshow(String[] string) {
		policyshow = string;
	}
		
	public void setRowlistold(String[] string) {
		rowlistold = string;
	}
	
	public void setRowid(String[] string) {
		rowid = string;
	}
		
	public void setRowstate(String[] string) {
		rowstate = string;
	}
	
	public void setPolicyid(String[] string) {
		policyid = string;
	}
		
	public void setSubsect(String[] string) {
		subsect = string;
	}
	
	public void setParamet(String[] string) {
		paramet = string;
	}
		
	public void setEffedate(String[] string) {
		effedate = string;
	}
	
	public void setParamet2(String[] string) {
		paramet2 = string;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		page="1";
		flag = "";
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		return errors;
	}


}

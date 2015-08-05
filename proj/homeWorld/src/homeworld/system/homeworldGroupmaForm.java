/*
 * @(#)homewordGroupmaForm.java  2004-2-11
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.system;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author lyc
 */

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.idn.log.LogWrapper;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class homeworldGroupmaForm  extends ActionForm {
	
	LogWrapper log = new LogWrapper("MenuAllForm");
	
	private String key="";
	
	private String flag = "";
	
	private String count = "";
	
	private String usershow = "";
	private String userid = "";
	private String datenow = "";
	
	private String currrowold = "-1";
	private String currrowshow = "-1";
	private String currrow = "-1";
	private String currcolumn = "";
	
	private String[] rowstate = null;
	
	private String[] rowid = null;
	
	private String[] groupid  = null;//组ＩＤ
	private String[] groupnam = null;//组名字
	private String[] groupdes = null;//组描述
	private String[] operatorid = null;//操作人
	private String[] operatorshow = null;//操作人
	private String[] operdate = null;//操作日期
//	private String[] director = null;//审核人
//	private String[] diredate = null;//审核日期

	/**
	 * 
	 */
	public homeworldGroupmaForm () {
		super();
		key="00";
		flag = "";
		currrow = "-1";
		currcolumn = "";
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
	public String getCurrcolumn() {
		return currcolumn;
	}

	/**
	 * @return
	 */
	public String getCurrrow() {
		return currrow;
	}

	/**
	 * @return
	 */
	public String getCurrrowold() {
		return currrowold;
	}

	/**
	 * @return
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @return
	 */
	public String[] getGroupdes() {
		return groupdes;
	}

	/**
	 * @return
	 */
	public String[] getGroupid() {
		return groupid;
	}

	/**
	 * @return
	 */
	public String[] getGroupnam() {
		return groupnam;
	}

	/**
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return
	 */
	public LogWrapper getLog() {
		return log;
	}

	/**
	 * @return
	 */
	public String[] getOperdate() {
		return operdate;
	}

	/**
	 * @return
	 */
	public String[] getRowstate() {
		return rowstate;
	}

	/**
	 * @param string
	 */
	public void setCurrcolumn(String string) {
		currcolumn = string;
	}

	/**
	 * @param string
	 */
	public void setCurrrow(String string) {
		currrow = string;
	}

	/**
	 * @param string
	 */
	public void setCurrrowold(String string) {
		currrowold = string;
	}

	/**
	 * @param string
	 */
	public void setFlag(String string) {
		flag = string;
	}

	/**
	 * @param strings
	 */
	public void setGroupdes(String[] strings) {
		groupdes = strings;
	}

	/**
	 * @param strings
	 */
	public void setGroupid(String[] strings) {
		groupid = strings;
	}

	/**
	 * @param strings
	 */
	public void setGroupnam(String[] strings) {
		groupnam = strings;
	}

	/**
	 * @param string
	 */
	public void setKey(String string) {
		key = string;
	}

	/**
	 * @param wrapper
	 */
	public void setLog(LogWrapper wrapper) {
		log = wrapper;
	}


	/**
	 * @param strings
	 */
	public void setOperdate(String[] strings) {
		operdate = strings;
	}

	/**
	 * @param strings
	 */
	public void setRowstate(String[] strings) {
		rowstate = strings;
	}

	/**
	 * @return
	 */
	public String getDatenow() {
		return datenow;
	}

	/**
	 * @return
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @return
	 */
	public String getUsershow() {
		return usershow;
	}

	/**
	 * @param string
	 */
	public void setDatenow(String string) {
		datenow = string;
	}

	/**
	 * @param string
	 */
	public void setUserid(String string) {
		userid = string;
	}

	/**
	 * @param string
	 */
	public void setUsershow(String string) {
		usershow = string;
	}

	/**
	 * @return
	 */
	public String[] getOperatorid() {
		return operatorid;
	}

	/**
	 * @return
	 */
	public String[] getOperatorshow() {
		return operatorshow;
	}

	/**
	 * @param strings
	 */
	public void setOperatorid(String[] strings) {
		operatorid = strings;
	}

	/**
	 * @param strings
	 */
	public void setOperatorshow(String[] strings) {
		operatorshow = strings;
	}

	/**
	 * @return
	 */
	public String getCount() {
		return count;
	}

	/**
	 * @param string
	 */
	public void setCount(String string) {
		count = string;
	}

	/**
	 * @return
	 */
	public String getCurrrowshow() {
		return currrowshow;
	}

	/**
	 * @param string
	 */
	public void setCurrrowshow(String string) {
		currrowshow = string;
	}

	/**
	 * @return
	 */
	public String[] getRowid() {
		return rowid;
	}

	/**
	 * @param strings
	 */
	public void setRowid(String[] strings) {
		rowid = strings;
	}

}

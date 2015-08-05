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

import com.idn.log.LogWrapper;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class MenuAllForm extends ActionForm {
	
	LogWrapper log = new LogWrapper("MenuAllForm");
	
	private String currrowshow="";
	private String currrowshowold="";
	private String key="";
	
	private String count = "";
	private String flag = "";
	private String currrow = "";
	private String currcolumn = "";
	private String page = "";
	
	private String id = "";
	
	private String[] mpurviewshow = null;
	private String[] mpurviewid = null;
	
	
	private String[] rowstate = null;

	
	private String[] menuid = null;
	private String[] mnamec= null;
	private String[] mnamee= null;
	private String[] mfile = null;
	private String[] mfilee = null;
	private String[] appsysshow = null;
	private String[] appsysid = null;
	/**
	 * 
	 */
	public MenuAllForm() {
		super();
		key="00";
		flag = "";
		currrow = "-1";
		currcolumn = "";
		page = "1";
		currrowshow="-1";
		currrowshowold="-1";
	}
	
	/**
	 * <P>变量输入函数</P>
	 * @return
	 */

	public String getKey() {
		return key;
	}
	public String getCount() {
		return count;
	}
	public String getId() {
		return id;
	}
	
	public String getPage() {
		return page;
	}
	
	public String getFlag() {
		return flag;
	}

	public String getCurrcolumn() {
		return currcolumn;
	}
	
	public String getCurrrowshowold() {
		return currrowshowold;
	}
	
	public String getCurrrowshow() {
		return currrowshow;
	}
	
	public String getCurrrow() {
		return currrow;
	}
	
	public String[] getMenuid() {
		return menuid;
	}

	public String[] getMnamec() {
		return mnamec;
	}

	public String[] getMnamee() {
		return mnamee;
	}

	public String[] getMfile() {
		return mfile;
	}

	public String[] getMfilee() {
		return mfilee;
	}

	public String[] getAppsysid() {
		return appsysid;
	}

	public String[] getAppsysshow() {
		return appsysshow;
	}

	public String[] getMpurviewshow() {
		return mpurviewshow;
	}
	
	public String[] getMpurviewid() {
		if (mpurviewid != null) {
			for (int i = 0; i < mpurviewid.length; i++) {
				log.debug(mpurviewid[i]);
			}
		}
		return mpurviewid;
	}
	public String[] getRowstate() {
		return rowstate;
	}


	/**
	 * <P>变量输出函数</P>
	 * @param string
	 */

	public void setKey(String string) {
		key = string;
	}

	public void setCount(String string) {
		count = string;
	}
	
	public void setId(String string) {
		id = string;
	}
	
	public void setFlag(String string) {
		flag = string;
	}

	public void setCurrrowshow(String string) {
		currrowshow = string;
	}

	public void setCurrrowshowold(String string) {
		currrowshowold = string;
	}

	public void setCurrrow(String string) {
		currrow = string;
	}

	public void setCurrcolumn(String string) {
		currcolumn = string;
	}
	
	public void setPage(String string) {
		page = string;
	}

	public void setMenuid(String[] string) {
		menuid = string;
	}

	public void setMnamec(String[] string) {
		mnamec = string;
	}

	public void setMnamee(String[] string) {
		mnamee = string;
	}

	public void setMfile(String[] string) {
		mfile = string;
	}
		
	public void setMfilee(String[] string) {
		mfilee = string;
	}

	public void setAppsysshow(String[] string) {
		appsysshow = string;
	}

	public void setAppsysid(String[] string) {
		appsysid = string;
	}
		
	public void setMpurviewshow(String[] string) {
		mpurviewshow = string;
	}
		
	public void setMpurviewid(String[] string) {
		mpurviewid =null;
		mpurviewid = string;
	}
				
	public void setRowstate(String[] string) {
		rowstate = string;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		flag = "";
		currrow = "0";
		currcolumn = "";
		page = "1";
		currrowshow="-1";
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		return errors;
	}
}

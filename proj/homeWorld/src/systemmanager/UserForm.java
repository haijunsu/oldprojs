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
public class UserForm extends ActionForm {
	private String count = "";//提交的分支标志
	private String flag = "";//提交的分支标志
	private String page = "";//画面分支标志
	
	private String nowdeptid = "";//当前部门代码
	private String nowdeptshow = "";//当前部门中文名

	private String currrowshowold="";//上一显示行
	private String currrowshow="";//当前显示行
	private String currrow = "";//当前行
	
	private String returnurl="";
	
	private String[] rowstate  = null;//行状态
	
	private String[] mpurviewshow = null;//权限显示
	private String[] mpurviewid = null;//权限id

	private String appsysshow = null;//系统显示
	private String appsysid = null;//系统id

	private String[] userid= null;//用户id
	private String[] namec= null;//用户姓名
	
	private String[] gendershow = null;//性别中文
	private String[] genderid = null;//性别代码
	
	private String[] jobshow = null;//职务中文
	private String[] jobid = null;//职务代码
	
	private String[] deptshow = null;//单位中文
	private String[] deptid = null;//单位代码

	private String[] birthday = null;//单位中文
	private String[] address = null;//单位代码

	private String[] tel = null;//电话
	private String[] bp = null;//bp
	private String[] handset = null;//手机

	private String[] levelshow = null;//级别
	private String[] levelid = null;//级别
	
	private String[] ustateshow = null;//用户状态中文
	private String[] ustateid = null;//用户代码
	
	/**
	 * 
	 */
	public UserForm() {
		super();
		flag = "";
		currrow = "-1";
		
		currrowshow="-1";
		
	}
	
	/**
	 * <P>变量输入函数</P>
	 * @return
	 */

	public String getFlag() {
		return flag;
	}
	public String getPage() {
		return page;
	}

	public String getNowdeptid() {
		return nowdeptid;
	}
	public String getNowdeptshow() {
		return nowdeptshow;
	}

	public String getCurrrowshow() {
		return currrowshow;
	}
	public String getCurrrow() {
		return currrow;
	}
	




	
	public String[] getMpurviewshow() {
		return mpurviewshow;
	}
	public String[] getMpurviewid() {
		return mpurviewid;
	}

	
	public String getAppsysid() {
		return appsysid;
	}
	public String getAppsysshow() {
		return appsysshow;
	}
	public String[] getUserid() {
		return userid;
	}
	public String[] getNamec() {
		return namec;
	}

	public String[] getDeptshow() {
		return deptshow;
	}
	public String[] getDeptid() {
		return deptid;
	}


	public String[] getUstateshow() {
		return ustateshow;
	}
	public String[] getUstateid() {
		return ustateid;
	}


	

	/**
	 * <P>变量输出函数</P>
	 * @param string
	 */


	
	public void setFlag(String string) {
		flag = string;
	}
	public void setPage(String string) {
		page = string;
	}

	public void setNowdeptshow(String string) {
		nowdeptshow = string;
	}
	public void setNowdeptid(String string) {
		nowdeptid = string;
	}
	
	public void setCurrrowshow(String string) {
		currrowshow = string;
	}
	public void setCurrrow(String string) {
		currrow = string;
	}
		
	
	public void setMpurviewshow(String[] string) {
		mpurviewshow = string;
	}	
	public void setMpurviewid(String[] string) {
		mpurviewid = string;
	}
	
	public void setAppsysshow(String string) {
		appsysshow = string;
	}
	public void setAppsysid(String string) {
		appsysid = string;
	}
	
	
	public void setUserid(String[] string) {
		userid = string;
	}
	public void setNamec(String[] string) {
		namec = string;
	}

	public void setDeptshow(String[] string) {
		deptshow = string;
	}
	public void setDeptid(String[] string) {
		deptid = string;
	}
	

	public void setUstateshow(String[] string) {
		ustateshow = string;
	}
	public void setUstateid(String[] string) {
		ustateid = string;
	}
	

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		flag = "";
		currrow = "-1";
		returnurl="";
		currrowshow="-1";
		page="";
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
	public String getCurrrowshowold() {
		return currrowshowold;
	}

	/**
	 * @param string
	 */
	public void setCurrrowshowold(String string) {
		currrowshowold = string;
	}	

	/**
	 * @return
	 */
	public String[] getAddress() {
		return address;
	}

	/**
	 * @return
	 */
	public String[] getBirthday() {
		return birthday;
	}

	/**
	 * @return
	 */
	public String[] getBp() {
		return bp;
	}


	/**
	 * @return
	 */
	public String[] getLevelid() {
		return levelid;
	}

	/**
	 * @return
	 */
	public String[] getLevelshow() {
		return levelshow;
	}

	/**
	 * @return
	 */
	public String[] getTel() {
		return tel;
	}

	/**
	 * @param strings
	 */
	public void setAddress(String[] strings) {
		address = strings;
	}

	/**
	 * @param strings
	 */
	public void setBirthday(String[] strings) {
		birthday = strings;
	}

	/**
	 * @param strings
	 */
	public void setBp(String[] strings) {
		bp = strings;
	}

	/**
	 * @param strings
	 */
	public void setLevelid(String[] strings) {
		levelid = strings;
	}

	/**
	 * @param strings
	 */
	public void setLevelshow(String[] strings) {
		levelshow = strings;
	}

	/**
	 * @param strings
	 */
	public void setTel(String[] strings) {
		tel = strings;
	}


	/**
	 * @return
	 */
	public String[] getHandset() {
		return handset;
	}

	/**
	 * @param strings
	 */
	public void setHandset(String[] strings) {
		handset = strings;
	}

	/**
	 * @return
	 */
	public String[] getGenderid() {
		return genderid;
	}

	/**
	 * @return
	 */
	public String[] getGendershow() {
		return gendershow;
	}

	/**
	 * @return
	 */
	public String[] getJobid() {
		return jobid;
	}

	/**
	 * @return
	 */
	public String[] getJobshow() {
		return jobshow;
	}

	/**
	 * @return
	 */
	public String getReturnurl() {
		return returnurl;
	}

	/**
	 * @param strings
	 */
	public void setGenderid(String[] strings) {
		genderid = strings;
	}

	/**
	 * @param strings
	 */
	public void setGendershow(String[] strings) {
		gendershow = strings;
	}

	/**
	 * @param strings
	 */
	public void setJobid(String[] strings) {
		jobid = strings;
	}

	/**
	 * @param strings
	 */
	public void setJobshow(String[] strings) {
		jobshow = strings;
	}

	/**
	 * @param string
	 */
	public void setReturnurl(String string) {
		returnurl = string;
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
	public String[] getRowstate() {
		return rowstate;
	}

	/**
	 * @param strings
	 */
	public void setRowstate(String[] strings) {
		rowstate = strings;
	}

}

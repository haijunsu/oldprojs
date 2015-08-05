/*
 * @(#)homewordUseridgForm.java  2004-2-13
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

import org.apache.struts.action.ActionForm;



/**
 * <P> </P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class homeworldUseridgForm extends ActionForm {
	
	private String flag = "";//提交的分支标志
	
	private String currrow = "-1";//当前行
	private String currcolumn = "-1";//当前行
	
	private String[] rowstate  = null;//行状态

	private String[] userid= null;//用户id
	private String[] usershow= null;//用户姓名
	
	private String[] lifebeg = null;//生命周期起
	private String[] lifeend = null;//生命周期止
	
	private String[] useridgshow = null;//共用名中文
	private String[] useridgid = null;//共用名id

	/**
	 * 
	 */
	public homeworldUseridgForm() {
		super();
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
	public String getFlag() {
		return flag;
	}

	/**
	 * @return
	 */
	public String[] getLifebeg() {
		return lifebeg;
	}

	/**
	 * @return
	 */
	public String[] getLifeend() {
		return lifeend;
	}

	/**
	 * @return
	 */
	public String[] getRowstate() {
		return rowstate;
	}

	/**
	 * @return
	 */
	public String[] getUserid() {
		return userid;
	}

	/**
	 * @return
	 */
	public String[] getUseridgid() {
		return useridgid;
	}

	/**
	 * @return
	 */
	public String[] getUseridgshow() {
		return useridgshow;
	}

	/**
	 * @return
	 */
	public String[] getUsershow() {
		return usershow;
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
	public void setFlag(String string) {
		flag = string;
	}

	/**
	 * @param strings
	 */
	public void setLifebeg(String[] strings) {
		lifebeg = strings;
	}

	/**
	 * @param strings
	 */
	public void setLifeend(String[] strings) {
		lifeend = strings;
	}

	/**
	 * @param strings
	 */
	public void setRowstate(String[] strings) {
		rowstate = strings;
	}

	/**
	 * @param strings
	 */
	public void setUserid(String[] strings) {
		userid = strings;
	}

	/**
	 * @param strings
	 */
	public void setUseridgid(String[] strings) {
		useridgid = strings;
	}

	/**
	 * @param strings
	 */
	public void setUseridgshow(String[] strings) {
		useridgshow = strings;
	}

	/**
	 * @param strings
	 */
	public void setUsershow(String[] strings) {
		usershow = strings;
	}

	/**
	 * @return
	 */
	public String getCurrcolumn() {
		return currcolumn;
	}

	/**
	 * @param string
	 */
	public void setCurrcolumn(String string) {
		currcolumn = string;
	}

}

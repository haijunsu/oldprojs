/*
 * @(#)FieldsForm.java  2003-8-11
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.action;

import org.apache.struts.action.ActionForm;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author haizhou
 */
public class FieldsForm extends ActionForm {
	
	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(FieldsForm.class);
	
	private String currrowshow="";
	private String currrowshowold="";
	private String key="";
	private String count = "";
	private String flag = "";
	private String currrow = "";
	private String currcolumn = "";
	private String page = "1";
	private String id = "";
	private String[] fieldid = null;
	private String[] ftypeeshow = null;
	private String[] flength = null;
	private String[] fdigits = null;
	private String[] fheaderc = null;
	private String[] cclass = null;
	private String[] ftypeshow = null;
	private String[] ftypeeid= null;
	private String[] ftypeid = null;
	private String[] fheadere = null;
	private String[] rowstate = null;
	private String[] fieldidkey = null;
	                 
	/**
	 * 
	 */
	public FieldsForm() {
		super();
		flag = "";
		currrow = "-1";
		currcolumn = "";
		page = "1";
		currrowshow="-1";
		currrowshowold="-1";
	}

	/**
	 * @return
	 */
	public static com.idn.log.LogWrapper getLog() {
		return log;
	}

	/**
	 * @return
	 */
	public String[] getCclass() {
		return cclass;
	}

	/**
	 * @return
	 */
	public String getCount() {
		return count;
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
	public String getCurrrowshow() {
		return currrowshow;
	}

	/**
	 * @return
	 */
	public String getCurrrowshowold() {
		return currrowshowold;
	}

	/**
	 * @return
	 */
	public String[] getFdigits() {
		return fdigits;
	}

	/**
	 * @return
	 */
	public String[] getFheaderc() {
		return fheaderc;
	}

	/**
	 * @return
	 */
	public String[] getFieldid() {
		return fieldid;
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
	public String[] getFlength() {
		return flength;
	}

	/**
	 * @return
	 */
	public String[] getFtypeeid() {
		return ftypeeid;
	}

	/**
	 * @return
	 */
	public String[] getFtypeeshow() {
		return ftypeeshow;
	}

	/**
	 * @return
	 */
	public String[] getFtypeid() {
		return ftypeid;
	}

	/**
	 * @return
	 */
	public String[] getFtypeshow() {
		return ftypeshow;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
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
	public String getPage() {
		return page;
	}

	/**
	 * @param wrapper
	 */
	public static void setLog(com.idn.log.LogWrapper wrapper) {
		log = wrapper;
	}

	/**
	 * @param strings
	 */
	public void setCclass(String[] strings) {
		cclass = strings;
	}

	/**
	 * @param string
	 */
	public void setCount(String string) {
		count = string;
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
	public void setCurrrowshow(String string) {
		currrowshow = string;
	}

	/**
	 * @param string
	 */
	public void setCurrrowshowold(String string) {
		currrowshowold = string;
	}

	/**
	 * @param strings
	 */
	public void setFdigits(String[] strings) {
		fdigits = strings;
	}

	/**
	 * @param strings
	 */
	public void setFheaderc(String[] strings) {
		fheaderc = strings;
	}

	/**
	 * @param strings
	 */
	public void setFieldid(String[] strings) {
		fieldid = strings;
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
	public void setFlength(String[] strings) {
		flength = strings;
	}

	/**
	 * @param strings
	 */
	public void setFtypeeid(String[] strings) {
		ftypeeid = strings;
	}

	/**
	 * @param strings
	 */
	public void setFtypeeshow(String[] strings) {
		ftypeeshow = strings;
	}

	/**
	 * @param strings
	 */
	public void setFtypeid(String[] strings) {
		ftypeid = strings;
	}

	/**
	 * @param strings
	 */
	public void setFtypeshow(String[] strings) {
		ftypeshow = strings;
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

	/**
	 * @param string
	 */
	public void setKey(String string) {
		key = string;
	}

	/**
	 * @param string
	 */
	public void setPage(String string) {
		page = string;
	}

	/**
	 * @return
	 */
	public String[] getFheadere() {
		return fheadere;
	}

	/**
	 * @param strings
	 */
	public void setFheadere(String[] strings) {
		fheadere = strings;
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

	/**
	 * @return
	 */
	public String[] getFieldidkey() {
		return fieldidkey;
	}

	/**
	 * @param strings
	 */
	public void setFieldidkey(String[] strings) {
		fieldidkey = strings;
	}

}

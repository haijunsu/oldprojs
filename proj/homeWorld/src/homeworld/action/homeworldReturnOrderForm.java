/*
 * @(#)homewordOrderForm.java  2004-1-8
 *
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.action;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author ht
 */

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author ht
 */
public class homeworldReturnOrderForm  extends ActionForm {

	private String epoqdt  ="";
	
	private String eponum  ="";
	private String epostr  ="";
	private String epostn  ="";
	private String eposdt  ="";
	private String epordt  ="";
	private String epordtmid  ="";
	private String epordtm2id  ="";

	private String epordtmshow  ="";
	private String epordtm2show ="";
	
	private String epoflg  ="";
	
	private String operatorid  ="";
	private String operatorshow  ="";
	
	private String operdate  ="";
	private String opertime  ="";
	
	private String queryid  ="";
	private String selectwhere  ="";
	
	private String flag  ="";
	private String freeformstate  ="";
	
	private String nowdate="";
	
	private String message="";
		
	public homeworldReturnOrderForm() {
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
	public String getEpoflg() {
		return epoflg;
	}

	/**
	 * @return
	 */
	public String getEponum() {
		return eponum;
	}

	/**
	 * @return
	 */
	public String getEpordt() {
		return epordt;
	}

	/**
	 * @return
	 */
	public String getEposdt() {
		return eposdt;
	}

	/**
	 * @return
	 */
	public String getEpostn() {
		return epostn;
	}

	/**
	 * @return
	 */
	public String getEpostr() {
		return epostr;
	}


	/**
	 * @return
	 */
	public String getOperdate() {
		return operdate;
	}

	/**
	 * @return
	 */
	public String getOpertime() {
		return opertime;
	}

	/**
	 * @param string
	 */
	public void setEpoflg(String string) {
		epoflg = string;
	}

	/**
	 * @param string
	 */
	public void setEponum(String string) {
		eponum = string;
	}

	/**
	 * @param string
	 */
	public void setEpordt(String string) {
		epordt = string;
	}

	/**
	 * @param string
	 */
	public void setEposdt(String string) {
		eposdt = string;
	}

	/**
	 * @param string
	 */
	public void setEpostn(String string) {
		epostn = string;
	}

	/**
	 * @param string
	 */
	public void setEpostr(String string) {
		epostr = string;
	}


	/**
	 * @param string
	 */
	public void setOperdate(String string) {
		operdate = string;
	}

	/**
	 * @param string
	 */
	public void setOpertime(String string) {
		opertime = string;
	}

	/**
	 * @return
	 */
	public String getQueryid() {
		return queryid;
	}

	/**
	 * @return
	 */
	public String getSelectwhere() {
		return selectwhere;
	}

	/**
	 * @param string
	 */
	public void setQueryid(String string) {
		queryid = string;
	}

	/**
	 * @param string
	 */
	public void setSelectwhere(String string) {
		selectwhere = string;
	}

	/**
	 * @return
	 */
	public String getOperatorid() {
		return operatorid;
	}

	/**
	 * @return
	 */
	public String getOperatorshow() {
		return operatorshow;
	}

	/**
	 * @param string
	 */
	public void setOperatorid(String string) {
		operatorid = string;
	}

	/**
	 * @param string
	 */
	public void setOperatorshow(String string) {
		operatorshow = string;
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
	public String getFreeformstate() {
		return freeformstate;
	}

	/**
	 * @param string
	 */
	public void setFlag(String string) {
		flag = string;
	}

	/**
	 * @param string
	 */
	public void setFreeformstate(String string) {
		freeformstate = string;
	}

	/**
	 * @return
	 */
	public String getEpordtm2id() {
		return epordtm2id;
	}

	/**
	 * @return
	 */
	public String getEpordtm2show() {
		return epordtm2show;
	}

	/**
	 * @return
	 */
	public String getEpordtmid() {
		return epordtmid;
	}

	/**
	 * @return
	 */
	public String getEpordtmshow() {
		return epordtmshow;
	}

	/**
	 * @param string
	 */
	public void setEpordtm2id(String string) {
		epordtm2id = string;
	}

	/**
	 * @param string
	 */
	public void setEpordtm2show(String string) {
		epordtm2show = string;
	}

	/**
	 * @param string
	 */
	public void setEpordtmid(String string) {
		epordtmid = string;
	}

	/**
	 * @param string
	 */
	public void setEpordtmshow(String string) {
		epordtmshow = string;
	}

	/**
	 * @return
	 */
	public String getNowdate() {
		return nowdate;
	}

	/**
	 * @param string
	 */
	public void setNowdate(String string) {
		nowdate = string;
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param string
	 */
	public void setMessage(String string) {
		message = string;
	}

	/**
	 * @return
	 */
	public String getEpoqdt() {
		return epoqdt;
	}

	/**
	 * @param string
	 */
	public void setEpoqdt(String string) {
		epoqdt = string;
	}

}

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
 * @author lyc
 */

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;



/**
 * <P> </P>
 * 
 * @version 0.1
 * @author ¿Ó”¿≥ı
 */
public class homeworldActionLogForm  extends ActionForm {
				
	private String flag="";
	
	private String date="";
	private String title="";
	
	private String qtable="";
	
	private String hiddenshow="0";
	
	
	
	private String liketext="";
	private String datetime[]=null;
	private String user[]=null;
	private String memo[]=null;

	public homeworldActionLogForm() {
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
		public String getFlag() {
			return flag;
		}


		/**
		 * @param string
		 */
		public void setFlag(String string) {
			flag = string;
		}


	

	/**
	 * @return
	 */
	public String[] getDatetime() {
		return datetime;
	}

	/**
	 * @return
	 */
	public String[] getMemo() {
		return memo;
	}

	/**
	 * @return
	 */
	public String[] getUser() {
		return user;
	}

	/**
	 * @param strings
	 */
	public void setDatetime(String[] strings) {
		datetime = strings;
	}

	/**
	 * @param strings
	 */
	public void setMemo(String[] strings) {
		memo = strings;
	}

	/**
	 * @param strings
	 */
	public void setUser(String[] strings) {
		user = strings;
	}

	/**
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param string
	 */
	public void setDate(String string) {
		date = string;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param string
	 */
	public void setTitle(String string) {
		title = string;
	}

	/**
	 * @return
	 */
	public String getLiketext() {
		return liketext;
	}

	/**
	 * @param string
	 */
	public void setLiketext(String string) {
		liketext = string;
	}

	/**
	 * @return
	 */
	public String getHiddenshow() {
		return hiddenshow;
	}

	/**
	 * @param string
	 */
	public void setHiddenshow(String string) {
		hiddenshow = string;
	}

	/**
	 * @return
	 */
	public String getQtable() {
		return qtable;
	}

	/**
	 * @param string
	 */
	public void setQtable(String string) {
		qtable = string;
	}

}

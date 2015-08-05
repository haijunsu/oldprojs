/**
 * @(#)SalaryInputSaveForm.java  2003-5-12
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.action;

import javax.servlet.http.HttpServletRequest;

//import org.apache.struts.action.ActionError;
//import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * <P>用于口令修改Form</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class ChangePasswordForm extends ActionForm {

	/**
	* Constructor
	*/
	private String usernameid;          //用户ID
	private String usernamec;          //用户名
	private String passwordold;        //旧的口令
	private String passwordnew;        //新的口令
	private String passwordagain;      //再一次新的口令
	public ChangePasswordForm() {

		super();

	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

	}

	/**
	 * @return
	 */
	public String getPasswordagain() {
		return passwordagain;
	}

	/**
	 * @return
	 */
	public String getPasswordnew() {
		return passwordnew;
	}

	/**
	 * @return
	 */
	public String getPasswordold() {
		return passwordold;
	}

	/**
	 * @return
	 */
	public String getUsernamec() {
		return usernamec;
	}

	/**
	 * @param string
	 */
	public void setPasswordagain(String string) {
		passwordagain = string;
	}

	/**
	 * @param string
	 */
	public void setPasswordnew(String string) {
		passwordnew = string;
	}

	/**
	 * @param string
	 */
	public void setPasswordold(String string) {
		passwordold = string;
	}

	/**
	 * @param string
	 */
	public void setUsernamec(String string) {
		usernamec = string;
	}

	/**
	 * @return
	 */
	public String getUsernameid() {
		return usernameid;
	}

	/**
	 * @param string
	 */
	public void setUsernameid(String string) {
		usernameid = string;
	}

}

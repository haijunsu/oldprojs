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
 * <P>���ڿ����޸�Form</P>
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
	private String usernameid;          //�û�ID
	private String usernamec;          //�û���
	private String passwordold;        //�ɵĿ���
	private String passwordnew;        //�µĿ���
	private String passwordagain;      //��һ���µĿ���
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

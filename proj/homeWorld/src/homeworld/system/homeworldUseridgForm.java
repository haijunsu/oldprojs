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
 * @author ������
 */
public class homeworldUseridgForm extends ActionForm {
	
	private String flag = "";//�ύ�ķ�֧��־
	
	private String currrow = "-1";//��ǰ��
	private String currcolumn = "-1";//��ǰ��
	
	private String[] rowstate  = null;//��״̬

	private String[] userid= null;//�û�id
	private String[] usershow= null;//�û�����
	
	private String[] lifebeg = null;//����������
	private String[] lifeend = null;//��������ֹ
	
	private String[] useridgshow = null;//����������
	private String[] useridgid = null;//������id

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

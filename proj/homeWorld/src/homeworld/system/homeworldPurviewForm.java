/*
 * @(#)homewordPurviewForm.java  2004-2-12
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
public class homeworldPurviewForm extends ActionForm {
	
	private String flag = "";//�ύ�ķ�֧��־
	
	private String currrowshowold="-1";//��һ��ʾ��
	private String currrowshow="-1";//��ǰ��ʾ��
	private String currrow = "-1";//��ǰ��
	
	private String[] rowstate  = null;//��״̬
	
	private String[] mpurviewshow = null;//Ȩ����ʾ
	private String[] mpurviewid = null;//Ȩ��id
	
	private String[] mpurview = null;//Ȩ��id

	private String[] appsysshow = null;//ϵͳ��ʾ
	private String[] appsysid = null;//ϵͳid

	private String[] userid= null;//�û�id
	private String[] usershow= null;//�û�����
	
	private String[] lifebeg = null;//����������
	private String[] lifeend = null;//��������ֹ
	
	private String[] useridgshow = null;//����������
	private String[] useridgid = null;//������id

	/**
	 * 
	 */
	public homeworldPurviewForm() {
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
	public String[] getMpurviewid() {
		return mpurviewid;
	}

	/**
	 * @return
	 */
	public String[] getMpurviewshow() {
		return mpurviewshow;
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
	public void setMpurviewid(String[] strings) {
		mpurviewid = strings;
	}

	/**
	 * @param strings
	 */
	public void setMpurviewshow(String[] strings) {
		mpurviewshow = strings;
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
	 * @return
	 */
	public String[] getUsershow() {
		return usershow;
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
	public String[] getMpurview() {
		return mpurview;
	}

	/**
	 * @param strings
	 */
	public void setMpurview(String[] strings) {
		mpurview = strings;
	}

	/**
	 * @param strings
	 */
	public void setAppsysid(String[] strings) {
		appsysid = strings;
	}

	/**
	 * @param strings
	 */
	public void setAppsysshow(String[] strings) {
		appsysshow = strings;
	}

	/**
	 * @return
	 */
	public String[] getAppsysid() {
		return appsysid;
	}

	/**
	 * @return
	 */
	public String[] getAppsysshow() {
		return appsysshow;
	}

}

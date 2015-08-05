/*
 * @(#)homewordFatherForm.java  2004-2-19
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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author ������
 */
public class homeworldPurviewNewForm extends ActionForm {
	
	private String id="";	       //Ҫ��ѯ���ֶ���(Ӣ��)
	private String show="";	       //Ҫ��ѯ���ֶ���(����)

	private String page = "1";     //��ǰ��ʾҳ  1����ʾ����  2����ʾ����  
	private String liketext="";	   //����������� 

	private String currrow="-1";     //��ǰ��	
	
	private String flag = "";      //�ύ��־
	private String count="";	   //�����������
	private String where = "";      //����
	
	private String se_up="1";	   // 1¼�� 2��ѯ
	private String title="";	   // ����
	
	private String disabled="";	   //��֧��־
	
	private String[] rowstate = null;//��״̬

	
	/*
	 *����������ֶ��� 
	*/

	private String currrowshowold="-1";//��һ��ʾ��
	private String currrowshow="-1";//��ǰ��ʾ��
	
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

	
	
	public homeworldPurviewNewForm() {
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
	 * <P>�����������</P>
	 * @param string
	 */
	/**
	 * @return
	 */

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getLiketext() {
		return liketext;
	}

	/**
	 * @return
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @return
	 */
	public String getShow() {
		return show;
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
	public void setLiketext(String string) {
		liketext = string;
	}

	/**
	 * @param string
	 */
	public void setPage(String string) {
		page = string;
	}

	/**
	 * @param string
	 */
	public void setShow(String string) {
		show = string;
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
	public String[] getRowstate() {
		return rowstate;
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
	public void setRowstate(String[] strings) {
		rowstate = strings;
	}

	/**
	 * @return
	 */
	public String getSe_up() {
		return se_up;
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
	public void setSe_up(String string) {
		se_up = string;
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
	public String getWhere() {
		return where;
	}

	/**
	 * @param string
	 */
	public void setWhere(String string) {
		where = string;
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
	public String[] getMpurview() {
		return mpurview;
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
	public void setMpurview(String[] strings) {
		mpurview = strings;
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
	public String getDisabled() {
		return disabled;
	}

	/**
	 * @param string
	 */
	public void setDisabled(String string) {
		disabled = string;
	}

}

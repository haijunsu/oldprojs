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
public class homeworldFatherForm extends ActionForm {
	
	private String id="";	       //Ҫ��ѯ���ֶ���(Ӣ��)
	private String show="";	       //Ҫ��ѯ���ֶ���(����)

	private String page = "1";     //��ǰ��ʾҳ  1����ʾ����  2����ʾ����  
	private String liketext="";	   //����������� 

	private String currrow="";     //��ǰ��	

	private String flag = "";      //�ύ��־
	private String count="";	   //�����������
	private String where = "";      //����
	
	private String se_up="1";	   // 1¼�� 2��ѯ
	private String title="";	   // ����
	
	
	private String[] rowstate = null;//��״̬
	private String[] seq = null;     //��ѯ������к�
	
	/*
	 *����������ֶ��� 
	*/
	
	public homeworldFatherForm() {
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
	public String[] getSeq() {
		return seq;
	}

	/**
	 * @param strings
	 */
	public void setSeq(String[] strings) {
		seq = strings;
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

}

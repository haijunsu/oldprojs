/*
 * @(#)homeworldTopicForm.java  2004-2-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.bbs;

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
public class homeworldTopicForm   extends ActionForm {
					
	private String flag="";
	private String page="";
	private String freeformstate ="";

	private String nowdate  ="";  //����
	
	private String topid  ="";  //����
	private String seq="";  //����


	private String topicc  ="";  //����
	private String contentc="";  //����
	
	private String bbskind ="";  //ͨ������
	private String[] readioshow =null;//ͨ����ʾ
	
	private String effbeg  ="";  //��Чʼ����
	private String effend  ="";  //��Чֹ����

	private String[] bbsgroup=null;  //ͨ��������
	private String[] bbsgroupshow=null;  //ͨ��������

	private String operatorshow="";  //������
	private String operatorid =""; //������

	private String operdate =""; //��������
	

	private String nowpage =""; //��������
	private String nowoncepage =""; //��������
	
	public homeworldTopicForm () {
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
	public String getBbskind() {
		return bbskind;
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
	 * @return
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @return
	 */
	public String[] getReadioshow() {
		return readioshow;
	}

	/**
	 * @param string
	 */
	public void setBbskind(String string) {
		bbskind = string;
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
	 * @param string
	 */
	public void setPage(String string) {
		page = string;
	}

	/**
	 * @param strings
	 */
	public void setReadioshow(String[] strings) {
		readioshow = strings;
	}

	/**
	 * @return
	 */
	public String[] getBbsgroup() {
		return bbsgroup;
	}

	/**
	 * @return
	 */
	public String[] getBbsgroupshow() {
		return bbsgroupshow;
	}

	/**
	 * @return
	 */
	public String getContentc() {
		return contentc;
	}

	/**
	 * @return
	 */
	public String getEffbeg() {
		return effbeg;
	}

	/**
	 * @return
	 */
	public String getEffend() {
		return effend;
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
	 * @return
	 */
	public String getOperdate() {
		return operdate;
	}

	/**
	 * @return
	 */
	public String getTopicc() {
		return topicc;
	}

	/**
	 * @param strings
	 */
	public void setBbsgroup(String[] strings) {
		bbsgroup = strings;
	}

	/**
	 * @param strings
	 */
	public void setBbsgroupshow(String[] strings) {
		bbsgroupshow = strings;
	}

	/**
	 * @param string
	 */
	public void setContentc(String string) {
		contentc = string;
	}

	/**
	 * @param string
	 */
	public void setEffbeg(String string) {
		effbeg = string;
	}

	/**
	 * @param string
	 */
	public void setEffend(String string) {
		effend = string;
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
	 * @param string
	 */
	public void setOperdate(String string) {
		operdate = string;
	}

	/**
	 * @param string
	 */
	public void setTopicc(String string) {
		topicc = string;
	}

	/**
	 * @return
	 */
	public String getSeq() {
		return seq;
	}

	/**
	 * @return
	 */
	public String getTopid() {
		return topid;
	}

	/**
	 * @param string
	 */
	public void setSeq(String string) {
		seq = string;
	}

	/**
	 * @param string
	 */
	public void setTopid(String string) {
		topid = string;
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
	public String getNowoncepage() {
		return nowoncepage;
	}

	/**
	 * @return
	 */
	public String getNowpage() {
		return nowpage;
	}

	/**
	 * @param string
	 */
	public void setNowoncepage(String string) {
		nowoncepage = string;
	}

	/**
	 * @param string
	 */
	public void setNowpage(String string) {
		nowpage = string;
	}

}

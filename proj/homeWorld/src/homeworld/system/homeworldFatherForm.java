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
 * @author 李永初
 */
public class homeworldFatherForm extends ActionForm {
	
	private String id="";	       //要查询的字段名(英文)
	private String show="";	       //要查询的字段名(中文)

	private String page = "1";     //当前显示页  1无显示内容  2有显示内容  
	private String liketext="";	   //所输入的内容 

	private String currrow="";     //当前行	

	private String flag = "";      //提交标志
	private String count="";	   //结果集总行数
	private String where = "";      //条件
	
	private String se_up="1";	   // 1录入 2查询
	private String title="";	   // 标题
	
	
	private String[] rowstate = null;//行状态
	private String[] seq = null;     //查询结果的行号
	
	/*
	 *在这里添加字段名 
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
	 * <P>变量输出函数</P>
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

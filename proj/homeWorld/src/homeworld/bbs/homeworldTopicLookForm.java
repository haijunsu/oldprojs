/*
 * @(#)homeworldTopicLookForm.java  2004-2-26
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.bbs;

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
	public class homeworldTopicLookForm   extends ActionForm {

		private String topicc  ="";  //标题
		private String contentc="";  //内容
	
		private String bbskind ="";  //通告类型
	
		private String operatorshow="";  //操作人
		private String operatorid =""; //操作人

		private String operdate =""; //操作日期
		
		private String nowpage =""; //操作日期
		private String nowoncepage =""; //操作日期
		private String first="";
	
		public homeworldTopicLookForm () {
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
		public String getContentc() {
			return contentc;
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
		 * @param string
		 */
		public void setBbskind(String string) {
			bbskind = string;
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

		/**
		 * @return
		 */
		public String getFirst() {
			return first;
		}

		/**
		 * @param string
		 */
		public void setFirst(String string) {
			first = string;
		}

	}

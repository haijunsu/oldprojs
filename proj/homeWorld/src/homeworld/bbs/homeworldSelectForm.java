/*
 * @(#)homeworldTestForm.java  2004-3-18
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
		public class homeworldSelectForm  extends ActionForm {

			private String contentc="";  //内容
	
			private String[][] show = null;//显示列
			
			private String[] columnname = null;
			
			public homeworldSelectForm  () {
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
			public String getContentc() {
				return contentc;
			}


			/**
			 * @param string
			 */
			public void setContentc(String string) {
				contentc = string;
			}


			/**
			 * @return
			 */
			public String[][] getShow() {
				return show;
			}

			/**
			 * @param strings
			 */
			public void setShow(String[][] strings) {
				show = strings;
			}

			/**
			 * @return
			 */
			public String[] getColumnname() {
				return columnname;
			}

			/**
			 * @param strings
			 */
			public void setColumnname(String[] strings) {
				columnname = strings;
			}

		}

/*
 * @(#)homeworldEpydtlForm.java  2004-7-1
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.fk;

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
		public class homeworldEpydtlForm  extends ActionForm {

			private String selectwhere="";

			private String[] epdvdr=null;	//供货商号
			private String[] epdstr=null;	//商店号
			private String[] epdrdt=null;	//付日期
			
			private String[] epdtrk=null;	//交易类型
			private String[] epdivn=null;	//交易日期
			private String[] epdivd=null;	//金额
			
	
			public homeworldEpydtlForm () {
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
			public String[] getEpdivd() {
				return epdivd;
			}

			/**
			 * @return
			 */
			public String[] getEpdivn() {
				return epdivn;
			}

			/**
			 * @return
			 */
			public String[] getEpdrdt() {
				return epdrdt;
			}

			/**
			 * @return
			 */
			public String[] getEpdstr() {
				return epdstr;
			}

			/**
			 * @return
			 */
			public String[] getEpdtrk() {
				return epdtrk;
			}

			/**
			 * @return
			 */
			public String[] getEpdvdr() {
				return epdvdr;
			}

			/**
			 * @return
			 */
			public String getSelectwhere() {
				return selectwhere;
			}

			/**
			 * @param strings
			 */
			public void setEpdivd(String[] strings) {
				epdivd = strings;
			}

			/**
			 * @param strings
			 */
			public void setEpdivn(String[] strings) {
				epdivn = strings;
			}

			/**
			 * @param strings
			 */
			public void setEpdrdt(String[] strings) {
				epdrdt = strings;
			}

			/**
			 * @param strings
			 */
			public void setEpdstr(String[] strings) {
				epdstr = strings;
			}

			/**
			 * @param strings
			 */
			public void setEpdtrk(String[] strings) {
				epdtrk = strings;
			}

			/**
			 * @param strings
			 */
			public void setEpdvdr(String[] strings) {
				epdvdr = strings;
			}

			/**
			 * @param string
			 */
			public void setSelectwhere(String string) {
				selectwhere = string;
			}

		}

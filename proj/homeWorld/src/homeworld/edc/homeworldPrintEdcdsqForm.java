/*
 * @(#)homeworldPrintEdcdsqForm.java  2004-10-18
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.edc;

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
	public class homeworldPrintEdcdsqForm  extends ActionForm {

		private String selectwhere="";
		private String queryid="";
	
		private String endnew ="";  //尾是否为新页
		private String pagerow ="";  //尾是否为新页
		
		private String[] dqponm =null;       //订单号    
		private String[] dqcdat =null;       //生成日期  
		private String[] dqctim =null;       //生成时间  
		private String[] dqpdat =null;       //订单日期  
		private String[] dqpstr =null;       //订单地点  
		private String[] dqvend =null;       //供货商号  
		private String[] dqseq  =null;       //序号      
		private String[] dqdstr =null;       //商店      
		private String[] dqsku  =null;       //sku       
		private String[] dqskud =null;       //品名      
		private String[] dqvndn =null;       //货号      
		private String[] dqdqty =null;       //分配数量  
		private String[] dqdcas =null;       //箱数      
		
	
	
		public homeworldPrintEdcdsqForm () {
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
		public String[] getDqcdat() {
			return dqcdat;
		}

		/**
		 * @return
		 */
		public String[] getDqctim() {
			return dqctim;
		}

		/**
		 * @return
		 */
		public String[] getDqdcas() {
			return dqdcas;
		}

		/**
		 * @return
		 */
		public String[] getDqdqty() {
			return dqdqty;
		}

		/**
		 * @return
		 */
		public String[] getDqdstr() {
			return dqdstr;
		}

		/**
		 * @return
		 */
		public String[] getDqpdat() {
			return dqpdat;
		}

		/**
		 * @return
		 */
		public String[] getDqponm() {
			return dqponm;
		}

		/**
		 * @return
		 */
		public String[] getDqpstr() {
			return dqpstr;
		}

		/**
		 * @return
		 */
		public String[] getDqseq() {
			return dqseq;
		}

		/**
		 * @return
		 */
		public String[] getDqsku() {
			return dqsku;
		}

		/**
		 * @return
		 */
		public String[] getDqskud() {
			return dqskud;
		}

		/**
		 * @return
		 */
		public String[] getDqvend() {
			return dqvend;
		}

		/**
		 * @return
		 */
		public String[] getDqvndn() {
			return dqvndn;
		}

		/**
		 * @return
		 */
		public String getEndnew() {
			return endnew;
		}

		/**
		 * @return
		 */
		public String getPagerow() {
			return pagerow;
		}

		/**
		 * @return
		 */
		public String getQueryid() {
			return queryid;
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
		public void setDqcdat(String[] strings) {
			dqcdat = strings;
		}

		/**
		 * @param strings
		 */
		public void setDqctim(String[] strings) {
			dqctim = strings;
		}

		/**
		 * @param strings
		 */
		public void setDqdcas(String[] strings) {
			dqdcas = strings;
		}

		/**
		 * @param strings
		 */
		public void setDqdqty(String[] strings) {
			dqdqty = strings;
		}

		/**
		 * @param strings
		 */
		public void setDqdstr(String[] strings) {
			dqdstr = strings;
		}

		/**
		 * @param strings
		 */
		public void setDqpdat(String[] strings) {
			dqpdat = strings;
		}

		/**
		 * @param strings
		 */
		public void setDqponm(String[] strings) {
			dqponm = strings;
		}

		/**
		 * @param strings
		 */
		public void setDqpstr(String[] strings) {
			dqpstr = strings;
		}

		/**
		 * @param strings
		 */
		public void setDqseq(String[] strings) {
			dqseq = strings;
		}

		/**
		 * @param strings
		 */
		public void setDqsku(String[] strings) {
			dqsku = strings;
		}

		/**
		 * @param strings
		 */
		public void setDqskud(String[] strings) {
			dqskud = strings;
		}

		/**
		 * @param strings
		 */
		public void setDqvend(String[] strings) {
			dqvend = strings;
		}

		/**
		 * @param strings
		 */
		public void setDqvndn(String[] strings) {
			dqvndn = strings;
		}

		/**
		 * @param string
		 */
		public void setEndnew(String string) {
			endnew = string;
		}

		/**
		 * @param string
		 */
		public void setPagerow(String string) {
			pagerow = string;
		}

		/**
		 * @param string
		 */
		public void setQueryid(String string) {
			queryid = string;
		}

		/**
		 * @param string
		 */
		public void setSelectwhere(String string) {
			selectwhere = string;
		}

	}

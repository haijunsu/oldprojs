/*
 * @(#)homeworldPrintEipdtlForm.java  2004-6-30
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
			public class homeworldPrintEipdtlForm  extends ActionForm {
			

				private String selectwhere="";
				private String pagerow="";
				
				private String[] eivtrk=null; //	发票字轨
				private String[] eivivn=null; //	发票号
				private String[] eivamt=null; //	开票日期
				private String[] eivtax=null;	//应付日期
				
				private String[] eivdat=null;	//应付日期
				
				private String[] seq=null;	//序号	
		
				private String[] eipstr=null;	//商店号	
				private String[] eipnum=null;	//单号	
				private String[] eipamt=null;	//金额	
				private String[] eipdta=null;	//交易日期
				private String[] eiptyp=null;	//交易类型
				private String[] eipvdr=null;	//供货商号
				private String[] eipyta=null;	//应付日期
				

				private String[] seqmx=null;	
				private String[] eivtrkmx=null;
				private String[] eivivnmx=null;
				private String[] eivamtmx=null;
				private String[] eivtaxmx=null;
		
		
				public homeworldPrintEipdtlForm () {
					super();
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
				public String[] getEipamt() {
					return eipamt;
				}

				/**
				 * @return
				 */
				public String[] getEipdta() {
					return eipdta;
				}

				/**
				 * @return
				 */
				public String[] getEipnum() {
					return eipnum;
				}

				/**
				 * @return
				 */
				public String[] getEipstr() {
					return eipstr;
				}

				/**
				 * @return
				 */
				public String[] getEiptyp() {
					return eiptyp;
				}

				/**
				 * @return
				 */
				public String[] getEipvdr() {
					return eipvdr;
				}

				/**
				 * @return
				 */
				public String[] getEipyta() {
					return eipyta;
				}

				/**
				 * @return
				 */
				public String[] getEivamt() {
					return eivamt;
				}

				/**
				 * @return
				 */
				public String[] getEivivn() {
					return eivivn;
				}

				/**
				 * @return
				 */
				public String[] getEivtrk() {
					return eivtrk;
				}

				/**
				 * @return
				 */
				public String getSelectwhere() {
					return selectwhere;
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
				public void setEipamt(String[] strings) {
					eipamt = strings;
				}

				/**
				 * @param strings
				 */
				public void setEipdta(String[] strings) {
					eipdta = strings;
				}

				/**
				 * @param strings
				 */
				public void setEipnum(String[] strings) {
					eipnum = strings;
				}

				/**
				 * @param strings
				 */
				public void setEipstr(String[] strings) {
					eipstr = strings;
				}

				/**
				 * @param strings
				 */
				public void setEiptyp(String[] strings) {
					eiptyp = strings;
				}

				/**
				 * @param strings
				 */
				public void setEipvdr(String[] strings) {
					eipvdr = strings;
				}

				/**
				 * @param strings
				 */
				public void setEipyta(String[] strings) {
					eipyta = strings;
				}

				/**
				 * @param strings
				 */
				public void setEivamt(String[] strings) {
					eivamt = strings;
				}

				/**
				 * @param strings
				 */
				public void setEivivn(String[] strings) {
					eivivn = strings;
				}

				/**
				 * @param strings
				 */
				public void setEivtrk(String[] strings) {
					eivtrk = strings;
				}

				/**
				 * @param string
				 */
				public void setSelectwhere(String string) {
					selectwhere = string;
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
				public String getPagerow() {
					return pagerow;
				}

				/**
				 * @param string
				 */
				public void setPagerow(String string) {
					pagerow = string;
				}

				/**
				 * @return
				 */
				public String[] getEivtax() {
					return eivtax;
				}

				/**
				 * @param strings
				 */
				public void setEivtax(String[] strings) {
					eivtax = strings;
				}

				/**
				 * @return
				 */
				public String[] getEivamtmx() {
					return eivamtmx;
				}

				/**
				 * @return
				 */
				public String[] getEivivnmx() {
					return eivivnmx;
				}

				/**
				 * @return
				 */
				public String[] getEivtaxmx() {
					return eivtaxmx;
				}

				/**
				 * @return
				 */
				public String[] getEivtrkmx() {
					return eivtrkmx;
				}

				/**
				 * @return
				 */
				public String[] getSeqmx() {
					return seqmx;
				}

				/**
				 * @param strings
				 */
				public void setEivamtmx(String[] strings) {
					eivamtmx = strings;
				}

				/**
				 * @param strings
				 */
				public void setEivivnmx(String[] strings) {
					eivivnmx = strings;
				}

				/**
				 * @param strings
				 */
				public void setEivtaxmx(String[] strings) {
					eivtaxmx = strings;
				}

				/**
				 * @param strings
				 */
				public void setEivtrkmx(String[] strings) {
					eivtrkmx = strings;
				}

				/**
				 * @param strings
				 */
				public void setSeqmx(String[] strings) {
					seqmx = strings;
				}

				/**
				 * @return
				 */
				public String[] getEivdat() {
					return eivdat;
				}

				/**
				 * @param strings
				 */
				public void setEivdat(String[] strings) {
					eivdat = strings;
				}

			}

/*
 * @(#)homeworldPrintErjhdrForm.java  2004-5-26
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.rtv;

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
			public class homeworldPrintErjhdrForm    extends ActionForm {
		
				private String selectwhere="";
				private String queryid="";
	
				private String erjrnm="";	//收货单号
				private String erjnum="";	//收货调整单号
				private String erjcdt="";	//生成日期
				private String erjctm="";	//生成时间
				private String erjstr="";	//商店
				private String erjstn=""; 	//商店名
				private String erjvnd="";	//供货商
				private String erjvdn="";	//供货商名
				private String erjjdt="";	//调整日期    
		
		
				private String[] erjseq=null;	//序号
				private String[] erjstrmx=null;	//商店      
				private String[] erjvndmx=null;	//供货商    
				private String[] erjssq=null;	//sku序号   
				private String[] erjsku=null;	//sku       
				private String[] erjskd=null;	//sku描述   
				private String[] erjvds=null;	//供货商型号
				private String[] erjmgn=null;	//规格      
				private String[] erjrqy=null;	//数量      
				private String[] erjret=null;	//售价      
				private String[] erjcst=null;	//成本      
				private String[] erjtrt=null;	//售价金额  
				private String[] erjtct=null;	//成本金额  
			
			
				private String countrqy	="";  
				private String counttrt	="";  
				private String counttct	="";  


				private String endnew ="";  //尾是否为新页

				private String pagerow ="";  //尾是否为新页
				
				public homeworldPrintErjhdrForm   () {
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
				public String getCountrqy() {
					return countrqy;
				}

				/**
				 * @return
				 */
				public String getCounttct() {
					return counttct;
				}

				/**
				 * @return
				 */
				public String getCounttrt() {
					return counttrt;
				}

				/**
				 * @return
				 */
				public String getErjcdt() {
					return erjcdt;
				}

				/**
				 * @return
				 */
				public String[] getErjcst() {
					return erjcst;
				}

				/**
				 * @return
				 */
				public String getErjctm() {
					return erjctm;
				}

				/**
				 * @return
				 */
				public String getErjjdt() {
					return erjjdt;
				}

				/**
				 * @return
				 */
				public String[] getErjmgn() {
					return erjmgn;
				}

				/**
				 * @return
				 */
				public String getErjnum() {
					return erjnum;
				}

				/**
				 * @return
				 */
				public String[] getErjret() {
					return erjret;
				}

				/**
				 * @return
				 */
				public String getErjrnm() {
					return erjrnm;
				}

				/**
				 * @return
				 */
				public String[] getErjrqy() {
					return erjrqy;
				}

				/**
				 * @return
				 */
				public String[] getErjseq() {
					return erjseq;
				}

				/**
				 * @return
				 */
				public String[] getErjskd() {
					return erjskd;
				}

				/**
				 * @return
				 */
				public String[] getErjsku() {
					return erjsku;
				}

				/**
				 * @return
				 */
				public String[] getErjssq() {
					return erjssq;
				}

				/**
				 * @return
				 */
				public String getErjstn() {
					return erjstn;
				}

				/**
				 * @return
				 */
				public String getErjstr() {
					return erjstr;
				}

				/**
				 * @return
				 */
				public String[] getErjstrmx() {
					return erjstrmx;
				}

				/**
				 * @return
				 */
				public String[] getErjtct() {
					return erjtct;
				}

				/**
				 * @return
				 */
				public String[] getErjtrt() {
					return erjtrt;
				}

				/**
				 * @return
				 */
				public String getErjvdn() {
					return erjvdn;
				}

				/**
				 * @return
				 */
				public String[] getErjvds() {
					return erjvds;
				}

				/**
				 * @return
				 */
				public String getErjvnd() {
					return erjvnd;
				}

				/**
				 * @return
				 */
				public String[] getErjvndmx() {
					return erjvndmx;
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
				 * @param string
				 */
				public void setCountrqy(String string) {
					countrqy = string;
				}

				/**
				 * @param string
				 */
				public void setCounttct(String string) {
					counttct = string;
				}

				/**
				 * @param string
				 */
				public void setCounttrt(String string) {
					counttrt = string;
				}

				/**
				 * @param string
				 */
				public void setErjcdt(String string) {
					erjcdt = string;
				}

				/**
				 * @param strings
				 */
				public void setErjcst(String[] strings) {
					erjcst = strings;
				}

				/**
				 * @param string
				 */
				public void setErjctm(String string) {
					erjctm = string;
				}

				/**
				 * @param string
				 */
				public void setErjjdt(String string) {
					erjjdt = string;
				}

				/**
				 * @param strings
				 */
				public void setErjmgn(String[] strings) {
					erjmgn = strings;
				}

				/**
				 * @param string
				 */
				public void setErjnum(String string) {
					erjnum = string;
				}

				/**
				 * @param strings
				 */
				public void setErjret(String[] strings) {
					erjret = strings;
				}

				/**
				 * @param string
				 */
				public void setErjrnm(String string) {
					erjrnm = string;
				}

				/**
				 * @param strings
				 */
				public void setErjrqy(String[] strings) {
					erjrqy = strings;
				}

				/**
				 * @param strings
				 */
				public void setErjseq(String[] strings) {
					erjseq = strings;
				}

				/**
				 * @param strings
				 */
				public void setErjskd(String[] strings) {
					erjskd = strings;
				}

				/**
				 * @param strings
				 */
				public void setErjsku(String[] strings) {
					erjsku = strings;
				}

				/**
				 * @param strings
				 */
				public void setErjssq(String[] strings) {
					erjssq = strings;
				}

				/**
				 * @param string
				 */
				public void setErjstn(String string) {
					erjstn = string;
				}

				/**
				 * @param string
				 */
				public void setErjstr(String string) {
					erjstr = string;
				}

				/**
				 * @param strings
				 */
				public void setErjstrmx(String[] strings) {
					erjstrmx = strings;
				}

				/**
				 * @param strings
				 */
				public void setErjtct(String[] strings) {
					erjtct = strings;
				}

				/**
				 * @param strings
				 */
				public void setErjtrt(String[] strings) {
					erjtrt = strings;
				}

				/**
				 * @param string
				 */
				public void setErjvdn(String string) {
					erjvdn = string;
				}

				/**
				 * @param strings
				 */
				public void setErjvds(String[] strings) {
					erjvds = strings;
				}

				/**
				 * @param string
				 */
				public void setErjvnd(String string) {
					erjvnd = string;
				}

				/**
				 * @param strings
				 */
				public void setErjvndmx(String[] strings) {
					erjvndmx = strings;
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

			}

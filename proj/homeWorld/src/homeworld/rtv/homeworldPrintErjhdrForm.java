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
			 * @author ������
			 */
			public class homeworldPrintErjhdrForm    extends ActionForm {
		
				private String selectwhere="";
				private String queryid="";
	
				private String erjrnm="";	//�ջ�����
				private String erjnum="";	//�ջ���������
				private String erjcdt="";	//��������
				private String erjctm="";	//����ʱ��
				private String erjstr="";	//�̵�
				private String erjstn=""; 	//�̵���
				private String erjvnd="";	//������
				private String erjvdn="";	//��������
				private String erjjdt="";	//��������    
		
		
				private String[] erjseq=null;	//���
				private String[] erjstrmx=null;	//�̵�      
				private String[] erjvndmx=null;	//������    
				private String[] erjssq=null;	//sku���   
				private String[] erjsku=null;	//sku       
				private String[] erjskd=null;	//sku����   
				private String[] erjvds=null;	//�������ͺ�
				private String[] erjmgn=null;	//���      
				private String[] erjrqy=null;	//����      
				private String[] erjret=null;	//�ۼ�      
				private String[] erjcst=null;	//�ɱ�      
				private String[] erjtrt=null;	//�ۼ۽��  
				private String[] erjtct=null;	//�ɱ����  
			
			
				private String countrqy	="";  
				private String counttrt	="";  
				private String counttct	="";  


				private String endnew ="";  //β�Ƿ�Ϊ��ҳ

				private String pagerow ="";  //β�Ƿ�Ϊ��ҳ
				
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

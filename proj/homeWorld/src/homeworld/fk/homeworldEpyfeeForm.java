/*
 * @(#)homeworldEpyfeeForm.java  2004-7-1
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
			 * @author ������
			 */
			public class homeworldEpyfeeForm  extends ActionForm {

				private String selectwhere="";

				private String[] epjvdr=null;          //�����̺�
				private String[] epjstr=null;          //�̵��
				private String[] epjrtd=null;          //��������
				private String[] epjpst=null;          //Э���Ӧ���̵�
				private String[] epjspn=null;          //ϵͳЭ���
				private String[] epjhpn=null;          //��ǩЭ���
				private String[] epjpnm=null;          //��Ŀ����
				private String[] epjfee=null;          //���ý��
	
				public homeworldEpyfeeForm () {
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
				public String[] getEpjfee() {
					return epjfee;
				}

				/**
				 * @return
				 */
				public String[] getEpjhpn() {
					return epjhpn;
				}

				/**
				 * @return
				 */
				public String[] getEpjpnm() {
					return epjpnm;
				}

				/**
				 * @return
				 */
				public String[] getEpjpst() {
					return epjpst;
				}

				/**
				 * @return
				 */
				public String[] getEpjrtd() {
					return epjrtd;
				}

				/**
				 * @return
				 */
				public String[] getEpjspn() {
					return epjspn;
				}

				/**
				 * @return
				 */
				public String[] getEpjstr() {
					return epjstr;
				}

				/**
				 * @return
				 */
				public String[] getEpjvdr() {
					return epjvdr;
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
				public void setEpjfee(String[] strings) {
					epjfee = strings;
				}

				/**
				 * @param strings
				 */
				public void setEpjhpn(String[] strings) {
					epjhpn = strings;
				}

				/**
				 * @param strings
				 */
				public void setEpjpnm(String[] strings) {
					epjpnm = strings;
				}

				/**
				 * @param strings
				 */
				public void setEpjpst(String[] strings) {
					epjpst = strings;
				}

				/**
				 * @param strings
				 */
				public void setEpjrtd(String[] strings) {
					epjrtd = strings;
				}

				/**
				 * @param strings
				 */
				public void setEpjspn(String[] strings) {
					epjspn = strings;
				}

				/**
				 * @param strings
				 */
				public void setEpjstr(String[] strings) {
					epjstr = strings;
				}

				/**
				 * @param strings
				 */
				public void setEpjvdr(String[] strings) {
					epjvdr = strings;
				}

				/**
				 * @param string
				 */
				public void setSelectwhere(String string) {
					selectwhere = string;
				}


			}

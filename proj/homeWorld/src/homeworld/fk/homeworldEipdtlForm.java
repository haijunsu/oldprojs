/*
 * @(#)homeworldEipdtlForm.java  2004-6-29
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
	public class homeworldEipdtlForm extends ActionForm {

		private String selectwhere="";
		private String flag="";
		
		private String msgbox="";
		
		private String nowdate="";
		
		private String showhide="0"; //	��Ʊ��
		
		
		private String currrow=""; //	��Ʊ�ֹ�
		private String currcolumn=""; //	��Ʊ��
		
		
		private String[] eivtrk=null; //	��Ʊ�ֹ�
		private String[] eivivn=null; //	��Ʊ��
		private String[] eivamt=null; //	��Ʊ����
		private String[] eivtax=null; //	��Ʊ����
		private String[] eivdat=null; //	��Ʊ����
		
		
		private String[] rowstate=null;	//���	
		
		private String[] seq=null;	//���	
		private String[] show=null;	//���
		private String[] eipstr=null;	//�̵��	
		private String[] eipnum=null;	//����	
		private String[] eipamt=null;	//���	
		private String[] eipdta=null;	//��������
		private String[] eiptyp=null;	//��������
		private String[] eipvdr=null;	//�����̺�
		private String[] eipyta=null;	//Ӧ������
		private String[] eipflg=null;	//Ӧ������
		
		private String[] chacked=null;	//Ӧ������
		
	
		public homeworldEipdtlForm() {
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
		public String getSelectwhere() {
			return selectwhere;
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
		 * @param string
		 */
		public void setSelectwhere(String string) {
			selectwhere = string;
		}

		/**
		 * @return
		 */
		public String[] getChacked() {
			return chacked;
		}

		/**
		 * @return
		 */
		public String getFlag() {
			return flag;
		}

		/**
		 * @param strings
		 */
		public void setChacked(String[] strings) {
			chacked = strings;
		}

		/**
		 * @param string
		 */
		public void setFlag(String string) {
			flag = string;
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
		public String[] getShow() {
			return show;
		}

		/**
		 * @param strings
		 */
		public void setShow(String[] strings) {
			show = strings;
		}

		/**
		 * @return
		 */
		public String getCurrcolumn() {
			return currcolumn;
		}

		/**
		 * @return
		 */
		public String getCurrrow() {
			return currrow;
		}

		/**
		 * @param string
		 */
		public void setCurrcolumn(String string) {
			currcolumn = string;
		}

		/**
		 * @param string
		 */
		public void setCurrrow(String string) {
			currrow = string;
		}

		/**
		 * @return
		 */
		public String[] getRowstate() {
			return rowstate;
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
		public String[] getEivtax() {
			return eivtax;
		}

		/**
		 * @return
		 */
		public String[] getEivtrk() {
			return eivtrk;
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
		public void setEivtax(String[] strings) {
			eivtax = strings;
		}

		/**
		 * @param strings
		 */
		public void setEivtrk(String[] strings) {
			eivtrk = strings;
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
		public String getMsgbox() {
			return msgbox;
		}

		/**
		 * @param string
		 */
		public void setMsgbox(String string) {
			msgbox = string;
		}

		/**
		 * @return
		 */
		public String[] getEipflg() {
			return eipflg;
		}

		/**
		 * @param strings
		 */
		public void setEipflg(String[] strings) {
			eipflg = strings;
		}

		/**
		 * @return
		 */
		public String getShowhide() {
			return showhide;
		}

		/**
		 * @param string
		 */
		public void setShowhide(String string) {
			showhide = string;
		}

	}

/*
 * @(#)homeworldUsersForm.java  2004-9-2
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


			import org.apache.struts.action.ActionForm;

			/**
			 * <P> </P>
			 * 
			 * @version 0.1
			 * @author ������
			 */
			public class homeworldUsersForm  extends ActionForm {
	
				private String id="";	       //Ҫ��ѯ���ֶ���(Ӣ��)
				private String show="";	       //Ҫ��ѯ���ֶ���(����)

				private String page = "1";     //��ǰ��ʾҳ  1����ʾ����  2����ʾ����  
				private String liketext="";	   //����������� 

				private String currrow="-1";     //��ǰ��	
				private String currcolumn="1";	   // 1¼�� 2��ѯ
			
				private String flag = "";      //�ύ��־
				private String count="";	   //�����������
				private String where = "";      //����
	
				private String se_up="1";	   // 1¼�� 2��ѯ
				private String title="";	   // ����
	

				private String[] rowstate = null;//��״̬

	
				/*
				 *����������ֶ��� 
				*/

				private String currrowshowold="-1";//��һ��ʾ��
				private String currrowshow="-1";//��ǰ��ʾ��


				private String[] vndnum= null;//�û�id
				
				private String[] vndnam= null;//�û�����
			
				private String[] vndnt1= null;//�û�ȫ��1
				
				private String[] vndnt2= null;//�û�ȫ��2
			
				private String[] vndnt3 = null;//�û�ȫ��3
	
	
				public homeworldUsersForm () {
					super();
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
				 * @return
				 */
				public String getCurrrowshow() {
					return currrowshow;
				}

				/**
				 * @return
				 */
				public String getCurrrowshowold() {
					return currrowshowold;
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
				public String[] getRowstate() {
					return rowstate;
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
				public String getShow() {
					return show;
				}

				/**
				 * @return
				 */
				public String getTitle() {
					return title;
				}

				/**
				 * @return
				 */
				public String[] getVndnam() {
					return vndnam;
				}

				/**
				 * @return
				 */
				public String[] getVndnt1() {
					return vndnt1;
				}

				/**
				 * @return
				 */
				public String[] getVndnt2() {
					return vndnt2;
				}

				/**
				 * @return
				 */
				public String[] getVndnt3() {
					return vndnt3;
				}

				/**
				 * @return
				 */
				public String[] getVndnum() {
					return vndnum;
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
				public void setCount(String string) {
					count = string;
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
				 * @param string
				 */
				public void setCurrrowshow(String string) {
					currrowshow = string;
				}

				/**
				 * @param string
				 */
				public void setCurrrowshowold(String string) {
					currrowshowold = string;
				}

				/**
				 * @param string
				 */
				public void setFlag(String string) {
					flag = string;
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
				 * @param strings
				 */
				public void setRowstate(String[] strings) {
					rowstate = strings;
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
				public void setShow(String string) {
					show = string;
				}

				/**
				 * @param string
				 */
				public void setTitle(String string) {
					title = string;
				}

				/**
				 * @param strings
				 */
				public void setVndnam(String[] strings) {
					vndnam = strings;
				}

				/**
				 * @param strings
				 */
				public void setVndnt1(String[] strings) {
					vndnt1 = strings;
				}

				/**
				 * @param strings
				 */
				public void setVndnt2(String[] strings) {
					vndnt2 = strings;
				}

				/**
				 * @param strings
				 */
				public void setVndnt3(String[] strings) {
					vndnt3 = strings;
				}

				/**
				 * @param strings
				 */
				public void setVndnum(String[] strings) {
					vndnum = strings;
				}

				/**
				 * @param string
				 */
				public void setWhere(String string) {
					where = string;
				}

			}


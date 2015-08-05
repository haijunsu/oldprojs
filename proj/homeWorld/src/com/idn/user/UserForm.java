/*
 * @(#)UserForm.java  2003-10-29
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.user;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.idn.property.CodeNotFoundException;
import com.idn.property.CodesManager;
import com.idn.util.CommonTools;
import com.idn.util.FormatDate;

/**
 * <P>�û���Ϣ�������û�ע����޸�</P>
 * 
 * @version 0.1
 * @author navy
 */

/**
 * <p>�����ˣ� navy<br>
 * ����ʱ�䣺2004-1-15<br>
 * �������ݣ�<br>
 *  <UL>
 *  	<LI>���ڸ�����CodeManager���������´�FORM�е�getXXXCollection������Ҫ����CodeNotFoundException�쳣
 *  </UL>
 */
public class UserForm extends ActionForm {
	
	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(UserForm.class);

	/**
	 * �������ͣ���NEW���½�����MODIFY���༭����CREATE���������û�����UPDATE�������û���Ϣ
	 */
	private String action = "NEW";

	/**
	 * �û�ID�Ƿ���Դ�Сд
	 */
	private boolean ignoreCase = false;

	/**
	 * �û�ID
	 */
	private String userid = null;

	/**
	 * �û�����ʹ�õĲ���ϵͳ
	 */
	private String appsys = "98";

	/**
	 * �û���Ĭ��Ȩ��
	 */
	private String purview = "255";

	/**
	 * �û�����
	 */
	private String username = null;

	/**
	 * �Ա�
	 */
	private String sex = null;
	
	/**
	 * �Ա���������
	 */
	private String[] sexLabel = null;

	/**
	 * �Ա�ѡ��ֵ����
	 */
	private String[] sexValues = null;

	/**
	 * ��������
	 */
	private String birthday = null;
	
	/**
	 * ����������
	 */
	private String birthdayYear = null;
	
	/**
	 * ����������
	 */
	private String birthdayMonth = null;
	
	/**
	 * ����������
	 */
	private String birthdayDay = null;

	/**
	 * �绰
	 */
	private String phone = null;

	/**
	 * ����
	 */
	private String fax = null;

	/**
	 * ���֤��
	 */
	private String cardid = null;

	/**
	 * լ��
	 */
	private String phoneh = null;

	/**
	 * �ֻ�
	 */
	private String handset = null;

	/**
	 * BP��
	 */
	private String bp = null;

	/**
	 * �����ʼ�
	 */
	private String email = null;

	/**
	 * ��ַ
	 */
	private String address = null;

	/**
	 * �ʱ�
	 */
	private String postc = null;

	/**
	 * ְ�����
	 */
	private String dutyc = null;

	/**
	 * ְ��ѡ�񼯺�
	 */
	private Collection dutycCollection = null;

	/**
	 * �û����𣬽���Ա����Ч
	 */
	private String level = null;

	/**
	 * �û�����ѡ�񼯺�
	 */
	private Collection levelCollection = null;

	/**
	 * Ա��״̬��ֻ�й���Ա�����޸�
	 */
	private String state = null;

	/**
	 * Ա��״̬ѡ�񼯺�
	 */
	private Collection stateCollection = null;

	/**
	 * δ֪
	 */
	private String gread = null;

	/**
	 * �û�����
	 */
	private String userType = null;

	/**
	 * ����
	 */
	private String dept = null;

	/**
	 * ����״̬ѡ�񼯺�
	 */
	private Collection deptCollection = null;

	/**
	 * ��������
	 */
	private String operdate =
		FormatDate.format(
			System.currentTimeMillis(),
			FormatDate.SHORT_YYYY_MM_DD);

	/**
	 * ������
	 */
	private String operator = null;
	/**
	 * ���캯��
	 */
	public UserForm() {
		super();
	}

	/* ���� Javadoc��
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = "NEW";

		userid = null;

		username = null;

		sex = "1";

		sexLabel = null;

		sexValues = null;

		birthday = null;

		phone = null;

		fax = null;

		cardid = null;

		phoneh = null;

		handset = null;

		bp = null;

		email = null;

		address = null;

		postc = null;

		dept = null;

		deptCollection = null;

		dutyc = null;

		dutycCollection = null;

		level = null;

		levelCollection = null;

		state = null;

		stateCollection = null;

		ignoreCase = false;

		appsys = "98";

		purview = "255";

		operdate =
			FormatDate.format(
				System.currentTimeMillis(),
				FormatDate.SHORT_YYYY_MM_DD);

		operator = null;

		userType = null;

	}

	/**
	 * @return ��������
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @return ��ַ
	 */
	public String getAddress() {
		if (address == null)
			return "";
		return address;
	}

	/**
	 * @return ��������
	 */
	public String getBirthday() {
		if (birthdayYear == null || birthdayMonth == null || birthdayDay == null)
			return "";
		birthday = birthdayYear + "-" + birthdayMonth + "-" + birthdayDay;
		return birthday;
	}

	/**
	 * @return ����
	 */
	public String getBp() {
		if (bp == null)
			return "";
		return bp;
	}

	/**
	 * @return ���֤
	 */
	public String getCardid() {
		if (cardid == null)
			return "";
		return cardid;
	}

	/**
	 * @return ְ��
	 */
	public String getDutyc() {
		if (dutyc == null)
			return "";
		return dutyc;
	}

	/**
	 * @return ְ�񼯺�
	 */
	public Collection getDutycCollection() {
		if (dutycCollection == null) {
			try {
				dutycCollection =
					CodesManager.getCodeLabelValueBeanCollection("02");
			} catch (CodeNotFoundException e) {
				log.error(e.getMessage(), e);
			}
		}
		return dutycCollection;
	}

	/**
	 * @return ����
	 */
	public String getEmail() {
		if (email == null)
			return "";
		return email;
	}

	/**
	 * @return ����
	 */
	public String getFax() {
		if (fax == null)
			return "";
		return fax;
	}

	/**
	 * @return �ֻ�
	 */
	public String getHandset() {
		if (handset == null)
			return "";
		return handset;
	}

	/**
	 * @return ����
	 */
	public String getLevel() {
		if (level == null)
			return "";
		return level;
	}

	/**
	 * @return ���𼯺�
	 */
	public Collection getLevelCollection() {
		if (levelCollection == null){
			try {
				levelCollection =
					CodesManager.getCodeLabelValueBeanCollection("06");
			} catch (CodeNotFoundException e) {
				log.error(e.getMessage(), e);
			}
		}
		return levelCollection;
	}

	/**
	 * @return �绰
	 */
	public String getPhone() {
		if (phone == null)
			return "";
		return phone;
	}

	/**
	 * @return լ��
	 */
	public String getPhoneh() {
		if (phoneh == null)
			return "";
		return phoneh;
	}

	/**
	 * @return �ʱ�
	 */
	public String getPostc() {
		if (postc == null)
			return "";
		return postc;
	}

	/**
	 * @return �Ա�
	 */
	public String getSex() {
		if (sex == null)
			return "1";
		return sex;
	}

	/**
	 * @return �Ա�˵��
	 */
	public String[] getSexLabel() {
		if (sexLabel == null) {
			try {
				sexLabel = CodesManager.getCodeMembersValue("01");
			} catch (CodeNotFoundException e) {
				log.error(e.getMessage(), e);
			}
		}
		return sexLabel;
	}

	/**
	 * @return �Ա�ֵ
	 */
	public String[] getSexValues() {
		if (sexValues == null)
			try {
				sexValues = CodesManager.getCodeMembersKey("01");
			} catch (CodeNotFoundException e) {
				log.error(e.getMessage(), e);
			}
		return sexValues;
	}

	/**
	 * @return �û�״̬
	 */
	public String getState() {
		if (state == null)
			return "";
		return state;
	}

	/**
	 * @return ״̬����
	 */
	public Collection getStateCollection() {
		if (stateCollection == null)
			try {
				stateCollection =
					CodesManager.getCodeLabelValueBeanCollection("04");
			} catch (CodeNotFoundException e) {
log.error(e.getMessage(), e);
			}

		return stateCollection;
	}

	/**
	 * @return �û�ID
	 */
	public String getUserid() {
		if (ignoreCase)
			return userid.toUpperCase();
		return userid;
	}

	/**
	 * @return �û�����
	 */
	public String getUsername() {
		if (username == null)
			return "";
		return username;
	}

	/**
	 * @param string
	 */
	public void setAction(String string) {
		action = string.toUpperCase();
	}

	/**
	 * @param string
	 */
	public void setAddress(String string) {
		address = string;
	}

	/**
	 * @param string
	 */
	public void setBirthday(String string) {
		String[] strTemp = CommonTools.stringToArray(string, "-");
		birthdayYear = strTemp[0];
		birthdayMonth = strTemp[1];
		birthdayDay = strTemp[2];
	}

	/**
	 * @param string
	 */
	public void setBp(String string) {
		bp = string;
	}

	/**
	 * @param string
	 */
	public void setCardid(String string) {
		cardid = string;
	}

	/**
	 * @param string
	 */
	public void setDutyc(String string) {
		dutyc = string;
	}

	/**
	 * @param collection
	 */
	public void setDutycCollection(Collection collection) {
		dutycCollection = collection;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * @param string
	 */
	public void setFax(String string) {
		fax = string;
	}

	/**
	 * @param string
	 */
	public void setHandset(String string) {
		handset = string;
	}

	/**
	 * @param string
	 */
	public void setLevel(String string) {
		level = string;
	}

	/**
	 * @param collection
	 */
	public void setLevelCollection(Collection collection) {
		levelCollection = collection;
	}

	/**
	 * @param string
	 */
	public void setPhone(String string) {
		phone = string;
	}

	/**
	 * @param string
	 */
	public void setPhoneh(String string) {
		phoneh = string;
	}

	/**
	 * @param string
	 */
	public void setPostc(String string) {
		postc = string;
	}

	/**
	 * @param string
	 */
	public void setSex(String string) {
		sex = string;
	}

	/**
	 * @param strings
	 */
	public void setSexLabel(String[] strings) {
		sexLabel = strings;
	}

	/**
	 * @param strings
	 */
	public void setSexValues(String[] strings) {
		sexValues = strings;
	}

	/**
	 * @param string
	 */
	public void setState(String string) {
		state = string;
	}

	/**
	 * @param collection
	 */
	public void setStateCollection(Collection collection) {
		stateCollection = collection;
	}

	/**
	 * @param string
	 */
	public void setUserid(String string) {
		userid = string;
	}

	/**
	 * @param string
	 */
	public void setUsername(String string) {
		username = string;
	}

	/**
	 * @return Gread
	 */
	public String getGread() {
		if (gread == null)
			return "";
		return gread;
	}

	/**
	 * @return ������
	 */
	public String getOperator() {
		if (operator == null)
			return "";
		return operator;
	}

	/**
	 * @return ��������
	 */
	public String getOperdate() {
		if (operdate == null)
			return "";
		return operdate;
	}

	/**
	 * @param string
	 */
	public void setGread(String string) {
		gread = string;
	}

	/**
	 * @param string
	 */
	public void setOperator(String string) {
		operator = string;
	}

	/**
	 * @param string
	 */
	public void setOperdate(String string) {
		operdate = string;
	}

	/**
	 * @return �û�ID�Ƿ���Դ�Сд
	 */
	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	/**
	 * @param b
	 */
	public void setIgnoreCase(boolean b) {
		ignoreCase = b;
	}

	/**
	 * @return �û�Ӧ�õ�ϵͳ
	 */
	public String getAppsys() {
		if (appsys == null)
			return "";
		return appsys;
	}

	/**
	 * @return �û���Ȩ��
	 */
	public String getPurview() {
		if (purview == null)
			return "";
		return purview;
	}

	/**
	 * @return �û�����
	 */
	public String getUserType() {
		if (userType == null)
			return "";
		return userType;
	}

	/**
	 * @param string
	 */
	public void setAppsys(String string) {
		appsys = string;
	}

	/**
	 * @param string
	 */
	public void setPurview(String string) {
		purview = string;
	}

	/**
	 * @param string
	 */
	public void setUserType(String string) {
		userType = string;
	}

	/**
	 * @return ����
	 */
	public String getDept() {
		if (dept == null)
			return "";
		return dept;
	}

	/**
	 * @return ���ż���
	 */
	public Collection getDeptCollection() {
		if (deptCollection == null)
			try {
				deptCollection = CodesManager.getCodeLabelValueBeanCollection("03");
			} catch (CodeNotFoundException e) {
				log.error(e.getMessage(), e);
			}
		return deptCollection;
	}

	/**
	 * @param string
	 */
	public void setDept(String string) {
		dept = string;
	}

	/**
	 * @param collection
	 */
	public void setDeptCollection(Collection collection) {
		deptCollection = collection;
	}

	/**
	 * @return �������ڵ���
	 */
	public String getBirthdayDay() {
		return birthdayDay;
	}


	/**
	 * @return �������ڵ��·�
	 */
	public String getBirthdayMonth() {
		return birthdayMonth;
	}


	/**
	 * @return �������ڵ���
	 */
	public String getBirthdayYear() {
		return birthdayYear;
	}

	/**
	 * @param string
	 */
	public void setBirthdayDay(String string) {
		birthdayDay = string;
	}

	/**
	 * @param string
	 */
	public void setBirthdayMonth(String string) {
		birthdayMonth = string;
	}

	/**
	 * @param string
	 */
	public void setBirthdayYear(String string) {
		birthdayYear = string;
	}

}

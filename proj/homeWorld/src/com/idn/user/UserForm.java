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
 * <P>用户信息，用于用户注册和修改</P>
 * 
 * @version 0.1
 * @author navy
 */

/**
 * <p>更改人： navy<br>
 * 更改时间：2004-1-15<br>
 * 更改内容：<br>
 *  <UL>
 *  	<LI>由于更改了CodeManager方法，导致此FORM中的getXXXCollection方法需要捕获CodeNotFoundException异常
 *  </UL>
 */
public class UserForm extends ActionForm {
	
	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(UserForm.class);

	/**
	 * 操作类型，“NEW”新建、“MODIFY”编辑、“CREATE”生成新用户、“UPDATE”更新用户信息
	 */
	private String action = "NEW";

	/**
	 * 用户ID是否忽略大小写
	 */
	private boolean ignoreCase = false;

	/**
	 * 用户ID
	 */
	private String userid = null;

	/**
	 * 用户可以使用的操作系统
	 */
	private String appsys = "98";

	/**
	 * 用户的默认权限
	 */
	private String purview = "255";

	/**
	 * 用户姓名
	 */
	private String username = null;

	/**
	 * 性别
	 */
	private String sex = null;
	
	/**
	 * 性别描述集合
	 */
	private String[] sexLabel = null;

	/**
	 * 性别选择值集合
	 */
	private String[] sexValues = null;

	/**
	 * 出生日期
	 */
	private String birthday = null;
	
	/**
	 * 出日日期年
	 */
	private String birthdayYear = null;
	
	/**
	 * 出日日期月
	 */
	private String birthdayMonth = null;
	
	/**
	 * 出日日期日
	 */
	private String birthdayDay = null;

	/**
	 * 电话
	 */
	private String phone = null;

	/**
	 * 传真
	 */
	private String fax = null;

	/**
	 * 身份证号
	 */
	private String cardid = null;

	/**
	 * 宅电
	 */
	private String phoneh = null;

	/**
	 * 手机
	 */
	private String handset = null;

	/**
	 * BP机
	 */
	private String bp = null;

	/**
	 * 电子邮件
	 */
	private String email = null;

	/**
	 * 地址
	 */
	private String address = null;

	/**
	 * 邮编
	 */
	private String postc = null;

	/**
	 * 职务代码
	 */
	private String dutyc = null;

	/**
	 * 职务选择集合
	 */
	private Collection dutycCollection = null;

	/**
	 * 用户级别，仅对员工有效
	 */
	private String level = null;

	/**
	 * 用户级别选择集合
	 */
	private Collection levelCollection = null;

	/**
	 * 员工状态，只有管理员可以修改
	 */
	private String state = null;

	/**
	 * 员工状态选择集合
	 */
	private Collection stateCollection = null;

	/**
	 * 未知
	 */
	private String gread = null;

	/**
	 * 用户类型
	 */
	private String userType = null;

	/**
	 * 部门
	 */
	private String dept = null;

	/**
	 * 部门状态选择集合
	 */
	private Collection deptCollection = null;

	/**
	 * 操作日期
	 */
	private String operdate =
		FormatDate.format(
			System.currentTimeMillis(),
			FormatDate.SHORT_YYYY_MM_DD);

	/**
	 * 操作人
	 */
	private String operator = null;
	/**
	 * 构造函数
	 */
	public UserForm() {
		super();
	}

	/* （非 Javadoc）
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
	 * @return 操作类型
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @return 地址
	 */
	public String getAddress() {
		if (address == null)
			return "";
		return address;
	}

	/**
	 * @return 出生日期
	 */
	public String getBirthday() {
		if (birthdayYear == null || birthdayMonth == null || birthdayDay == null)
			return "";
		birthday = birthdayYear + "-" + birthdayMonth + "-" + birthdayDay;
		return birthday;
	}

	/**
	 * @return 呼机
	 */
	public String getBp() {
		if (bp == null)
			return "";
		return bp;
	}

	/**
	 * @return 身份证
	 */
	public String getCardid() {
		if (cardid == null)
			return "";
		return cardid;
	}

	/**
	 * @return 职务
	 */
	public String getDutyc() {
		if (dutyc == null)
			return "";
		return dutyc;
	}

	/**
	 * @return 职务集合
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
	 * @return 电邮
	 */
	public String getEmail() {
		if (email == null)
			return "";
		return email;
	}

	/**
	 * @return 传真
	 */
	public String getFax() {
		if (fax == null)
			return "";
		return fax;
	}

	/**
	 * @return 手机
	 */
	public String getHandset() {
		if (handset == null)
			return "";
		return handset;
	}

	/**
	 * @return 级别
	 */
	public String getLevel() {
		if (level == null)
			return "";
		return level;
	}

	/**
	 * @return 级别集合
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
	 * @return 电话
	 */
	public String getPhone() {
		if (phone == null)
			return "";
		return phone;
	}

	/**
	 * @return 宅电
	 */
	public String getPhoneh() {
		if (phoneh == null)
			return "";
		return phoneh;
	}

	/**
	 * @return 邮编
	 */
	public String getPostc() {
		if (postc == null)
			return "";
		return postc;
	}

	/**
	 * @return 性别
	 */
	public String getSex() {
		if (sex == null)
			return "1";
		return sex;
	}

	/**
	 * @return 性别说明
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
	 * @return 性别值
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
	 * @return 用户状态
	 */
	public String getState() {
		if (state == null)
			return "";
		return state;
	}

	/**
	 * @return 状态集合
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
	 * @return 用户ID
	 */
	public String getUserid() {
		if (ignoreCase)
			return userid.toUpperCase();
		return userid;
	}

	/**
	 * @return 用户名称
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
	 * @return 操作人
	 */
	public String getOperator() {
		if (operator == null)
			return "";
		return operator;
	}

	/**
	 * @return 操作日期
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
	 * @return 用户ID是否忽略大小写
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
	 * @return 用户应用的系统
	 */
	public String getAppsys() {
		if (appsys == null)
			return "";
		return appsys;
	}

	/**
	 * @return 用户的权限
	 */
	public String getPurview() {
		if (purview == null)
			return "";
		return purview;
	}

	/**
	 * @return 用户类型
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
	 * @return 部门
	 */
	public String getDept() {
		if (dept == null)
			return "";
		return dept;
	}

	/**
	 * @return 部门集合
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
	 * @return 出日日期的天
	 */
	public String getBirthdayDay() {
		return birthdayDay;
	}


	/**
	 * @return 出日日期的月份
	 */
	public String getBirthdayMonth() {
		return birthdayMonth;
	}


	/**
	 * @return 出日日期的年
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

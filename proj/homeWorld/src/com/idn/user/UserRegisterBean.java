/*
 * @(#)UserRegisterBean.java  2003-10-30
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.user;

import java.sql.SQLException;

import com.idn.sql.DataBean;
import com.idn.sql.DynaSqlBean;

/**
 * <P> 用户注册 </P>
 * 
 * @version 0.1
 * @author navy
 */
public class UserRegisterBean {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(UserRegisterBean.class);

	/**
	 * 向USERS表插入数据SQL语句
	 */
	private final String INSERT_USERS_SQL =
		"INSERT INTO USERS "
			+ "(USERID, UPWD, APPSYS, PURVIEW) VALUES (?, ?, ?, ?)";

	/**
	 * 查询USERS表SQL语句
	 */
	private final String SELECT_USERS_SQL =
		"SELECT USERID, UPWD, APPSYS, PURVIEW FROM USERS WHERE USERID = ?";

	/**
	 * 更新USERS表数据SQL语句，不更新用户的口令
	 */
	private final String UPDATE_USERS_SQL =
		"UPDATE USERS SET " + "APPSYS = ?, PURVIEW = ? WHERE USERID = ?";

	/**
	 * 向ASSOCIATOR表插入数据SQL语句
	 */
	private final String INSERT_ASSOCIATOR_SQL =
		"INSERT INTO ASSOCIATOR "
			+ "(USERID, NAME, SEX, BIRTHDAY, CARDID, PHONE, FAX, PHONEH, HANDSET, BP, "
			+ "EMAIL, ADDRESS, POSTC, DUTYC, GREAD, OPERDATE, OPERATOR) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	/**
	 * 从ASSOCIATOR表查询数据SQL语句
	 */
	private final String SELECT_ASSOCIATOR_SQL =
		"SELECT NAME, SEX, BIRTHDAY, CARDID, PHONE, FAX, PHONEH, HANDSET, "
			+ "BP, EMAIL, ADDRESS, POSTC, DUTYC, GREAD, OPERDATE, OPERATOR "
			+ "FROM ASSOCIATOR WHERE USERID = ?";
	/**
	 * 更新ASSOCIATOR表数据SQL语句
	 */
	private final String UPDATE_ASSOCIATOR_SQL =
		"UPDATE ASSOCIATOR SET "
			+ "NAME = ?, SEX = ?, BIRTHDAY = ?, CARDID = ?, PHONE = ?, "
			+ "FAX = ?, PHONEH = ?, HANDSET = ?, BP = ?, "
			+ "EMAIL = ?, ADDRESS = ?, POSTC = ?, DUTYC = ?, GREAD = ?, "
			+ "OPERDATE = ?, OPERATOR = ? "
			+ "WHERE USERID = ?";

	/**
	 * 向Employee表插入数据SQL语句
	 */
	private final String INSERT_EMPLOYEE_SQL =
		"INSERT INTO EMPLOYEE "
			+ "(USERID, NAMEC, GENDER, JOB, DEPT, BIRTHDAY, ADDRESS, TEL, BP, "
			+ "HANDSET, LEVEL, STATE, OPERDATE, OPERATOR) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	/**
	 * 向Employee表插入数据SQL语句
	 */
	private final String SELECT_EMPLOYEE_SQL =
		"SELECT USERID, NAMEC, GENDER, JOB, DEPT, BIRTHDAY, ADDRESS, TEL, BP, "
			+ "HANDSET, LEVEL, STATE, OPERDATE, OPERATOR "
			+ "FROM EMPLOYEE WHERE USERID = ?";

	/**
	 * 更新Employee表数据SQL语句
	 */
	private final String UPDATE_EMPLOYEE_SQL =
		"UPDATE EMPLOYEE SET "
			+ "NAMEC = ?, GENDER = ?, JOB = ?, DEPT = ?, BIRTHDAY = ?, "
			+ "ADDRESS = ?, TEL = ?, BP = ?, HANDSET = ?, "
			+ "LEVEL = ?, STATE = ?, "
			+ "OPERDATE = ?, OPERATOR = ? "
			+ "WHERE USERID = ?";

	/**
	 * 检查USERS表中USERID是否重复SQL语句
	 */
	private final String CHECK_DUPLICATE_USER_SQL =
		"SELECT USERID FROM USERS WHERE USERID = ?";

	/**
	 * 检查ASSOCIATOR表中USERID是否重复SQL语句
	 */
	private final String CHECK_DUPLICATE_ASSOCIATOR_SQL =
		"SELECT USERID FROM ASSOCIATOR WHERE USERID = ?";

	/**
	 * 检查ASSOCIATOR表中USERID是否重复SQL语句
	 */
	private final String CHECK_DUPLICATE_EMPLOYEE_SQL =
		"SELECT USERID FROM EMPLOYEE WHERE USERID = ?";

	/**
	 * 删除ASSOCIATOR表数据SQL语句
	 */
	private final String DELETE_DUPLICATE_ASSOCIATOR_SQL =
		"DELETE FROM ASSOCIATOR WHERE USERID = ?";

	/**
	 * 删除EMPLOYEE表数据SQL语句
	 */
	private final String DELETE_DUPLICATE_EMPLOYEE_SQL =
		"DELETE FROM EMPLOYEE WHERE USERID = ?";

	/**
	 * 用户信息FORM
	 */
	private UserForm form = null;

	/**
	 * 实例化
	 */
	public UserRegisterBean() {
		super();
	}

	/**
	 * @return UserForm
	 */
	public UserForm getForm() {
		return form;
	}

	/**
	 * @param form
	 */
	public void setForm(UserForm form) {
		this.form = form;
	}

	/**
	 * 插入用户信息
	 * @throws UserException
	 */
	public void insert() throws UserException {
		if (isUserExistInUsers())
			throw new UserException(UserException.USER_EXISTED, "用户已经存在！");
		DynaSqlBean dsb = new DynaSqlBean();
		String[] strSql = null;
		String[][] strSqlParam = null;
		if (form.getUserType().equals(UserType.EMPLOYEE)) {
			//内部员工
			if (isUserExistInEmployee()) {
				strSql =
					new String[] {
						INSERT_USERS_SQL,
						DELETE_DUPLICATE_EMPLOYEE_SQL,
						INSERT_EMPLOYEE_SQL };
				strSqlParam = new String[3][14];
				//user表参数
				strSqlParam[0][0] = form.getUserid();
				strSqlParam[0][1] = form.getUserid();
				strSqlParam[0][2] = form.getAppsys();
				strSqlParam[0][3] = form.getPurview();
				//删除语句参数
				strSqlParam[1][0] = form.getUserid();
				//插入语句参数
				strSqlParam[2][0] = form.getUserid();
				strSqlParam[2][1] = form.getUsername();
				strSqlParam[2][2] = form.getSex();
				strSqlParam[2][3] = form.getDutyc();
				strSqlParam[2][4] = form.getDept();
				strSqlParam[2][5] = form.getBirthday();
				strSqlParam[2][6] = form.getAddress();
				strSqlParam[2][7] = form.getPhone();
				strSqlParam[2][8] = form.getBp();
				strSqlParam[2][9] = form.getHandset();
				strSqlParam[2][10] = form.getLevel();
				strSqlParam[2][11] = form.getState();
				strSqlParam[2][12] = form.getOperdate();
				strSqlParam[2][13] = form.getOperator();

			} else {

				strSql = new String[] { INSERT_USERS_SQL, INSERT_EMPLOYEE_SQL };
				strSqlParam = new String[2][14];
				//user表参数
				strSqlParam[0][0] = form.getUserid();
				strSqlParam[0][1] = form.getUserid();
				strSqlParam[0][2] = form.getAppsys();
				strSqlParam[0][3] = form.getPurview();
				//插入语句参数
				strSqlParam[1][0] = form.getUserid();
				strSqlParam[1][1] = form.getUsername();
				strSqlParam[1][2] = form.getSex();
				strSqlParam[1][3] = form.getDutyc();
				strSqlParam[1][4] = form.getDept();
				strSqlParam[1][5] = form.getBirthday();
				strSqlParam[1][6] = form.getAddress();
				strSqlParam[1][7] = form.getPhone();
				strSqlParam[1][8] = form.getBp();
				strSqlParam[1][9] = form.getHandset();
				strSqlParam[1][10] = form.getLevel();
				strSqlParam[1][11] = form.getState();
				strSqlParam[1][12] = form.getOperdate();
				strSqlParam[1][13] = form.getOperator();
			}
		} else {
			//公司客户
			if (isUserExistInAssociator()) {
				strSql =
					new String[] {
						INSERT_USERS_SQL,
						DELETE_DUPLICATE_ASSOCIATOR_SQL,
						INSERT_ASSOCIATOR_SQL };
				strSqlParam = new String[3][17];
				//user表参数
				strSqlParam[0][0] = form.getUserid();
				strSqlParam[0][1] = form.getUserid();
				strSqlParam[0][2] = form.getAppsys();
				strSqlParam[0][3] = form.getPurview();
				//删除语句参数
				strSqlParam[1][0] = form.getUserid();
				//插入语句参数
				strSqlParam[2][0] = form.getUserid();
				strSqlParam[2][1] = form.getUsername();
				strSqlParam[2][2] = form.getSex();
				strSqlParam[2][3] = form.getBirthday();
				strSqlParam[2][4] = form.getCardid();
				strSqlParam[2][5] = form.getPhone();
				strSqlParam[2][6] = form.getFax();
				strSqlParam[2][7] = form.getPhoneh();
				strSqlParam[2][8] = form.getHandset();
				strSqlParam[2][9] = form.getBp();
				strSqlParam[2][10] = form.getEmail();
				strSqlParam[2][11] = form.getAddress();
				strSqlParam[2][12] = form.getPostc();
				strSqlParam[2][13] = form.getDutyc();
				strSqlParam[2][14] = form.getGread();
				strSqlParam[2][15] = form.getOperdate();
				strSqlParam[2][16] = form.getOperator();
			} else {
				strSql =
					new String[] { INSERT_USERS_SQL, INSERT_ASSOCIATOR_SQL };
				strSqlParam = new String[2][17];
				//user表参数
				strSqlParam[0][0] = form.getUserid();
				strSqlParam[0][1] = form.getUserid();
				strSqlParam[0][2] = form.getAppsys();
				strSqlParam[0][3] = form.getPurview();
				//插入语句参数
				strSqlParam[1][0] = form.getUserid();
				strSqlParam[1][1] = form.getUsername();
				strSqlParam[1][2] = form.getSex();
				strSqlParam[1][3] = form.getBirthday();
				strSqlParam[1][4] = form.getCardid();
				strSqlParam[1][5] = form.getPhone();
				strSqlParam[1][6] = form.getFax();
				strSqlParam[1][7] = form.getPhoneh();
				strSqlParam[1][8] = form.getHandset();
				strSqlParam[1][9] = form.getBp();
				strSqlParam[1][10] = form.getEmail();
				strSqlParam[1][11] = form.getAddress();
				strSqlParam[1][12] = form.getPostc();
				strSqlParam[1][13] = form.getDutyc();
				strSqlParam[1][14] = form.getGread();
				strSqlParam[1][15] = form.getOperdate();
				strSqlParam[1][16] = form.getOperator();
			}
		}
		dsb.setSql(strSql);
		dsb.setBatchParam(strSqlParam);
		try {
			dsb.executeArrayBatch();
		} catch (SQLException e) {
			log.error("创建用户失败", e);
			throw new UserException(
				UserException.INSERT_INFORMATION_FAIL,
				"创建用户失败");
		} catch (Exception e) {
			log.error("创建用户失败", e);
			throw new UserException(
				UserException.INSERT_INFORMATION_FAIL,
				"创建用户失败");
		}

	}

	/**
	 * 修改用户信息
	 * @throws UserException
	 */
	public void update() throws UserException {
		DynaSqlBean dsb = new DynaSqlBean();
		String[] strSql = null;
		String[][] strSqlParam = null;
		if (form.getUserType().equals(UserType.EMPLOYEE)) {
			//内部员工
			if (isUserExistInEmployee()) {
				strSql =
					new String[] {
						UPDATE_USERS_SQL,
						UPDATE_EMPLOYEE_SQL };
				strSqlParam = new String[2][14];
				//user表参数
				strSqlParam[0][0] = form.getAppsys();
				strSqlParam[0][1] = form.getPurview();
				strSqlParam[0][2] = form.getUserid();
				//插入语句参数
				strSqlParam[1][0] = form.getUsername();
				strSqlParam[1][1] = form.getSex();
				strSqlParam[1][2] = form.getDutyc();
				strSqlParam[1][3] = form.getDept();
				strSqlParam[1][4] = form.getBirthday();
				strSqlParam[1][5] = form.getAddress();
				strSqlParam[1][6] = form.getPhone();
				strSqlParam[1][7] = form.getBp();
				strSqlParam[1][8] = form.getHandset();
				strSqlParam[1][9] = form.getLevel();
				strSqlParam[1][10] = form.getState();
				strSqlParam[1][11] = form.getOperdate();
				strSqlParam[1][12] = form.getOperator();
				strSqlParam[1][13] = form.getUserid();

			} else {

				strSql = new String[] { UPDATE_USERS_SQL, INSERT_EMPLOYEE_SQL };
				strSqlParam = new String[2][14];
				//user表参数
				strSqlParam[0][0] = form.getAppsys();
				strSqlParam[0][1] = form.getPurview();
				strSqlParam[0][2] = form.getUserid();
				//插入语句参数
				strSqlParam[1][0] = form.getUserid();
				strSqlParam[1][1] = form.getUsername();
				strSqlParam[1][2] = form.getSex();
				strSqlParam[1][3] = form.getDutyc();
				strSqlParam[1][4] = form.getDept();
				strSqlParam[1][5] = form.getBirthday();
				strSqlParam[1][6] = form.getAddress();
				strSqlParam[1][7] = form.getPhone();
				strSqlParam[1][8] = form.getBp();
				strSqlParam[1][9] = form.getHandset();
				strSqlParam[1][10] = form.getLevel();
				strSqlParam[1][11] = form.getState();
				strSqlParam[1][12] = form.getOperdate();
				strSqlParam[1][13] = form.getOperator();
			}
		} else {
			//公司客户
			if (isUserExistInAssociator()) {
				strSql =
					new String[] {
						UPDATE_USERS_SQL,
						UPDATE_ASSOCIATOR_SQL };
				strSqlParam = new String[2][17];
				//user表参数
				strSqlParam[0][0] = form.getAppsys();
				strSqlParam[0][1] = form.getPurview();
				strSqlParam[0][2] = form.getUserid();
				//插入语句参数
				strSqlParam[1][0] = form.getUsername();
				strSqlParam[1][1] = form.getSex();
				strSqlParam[1][2] = form.getBirthday();
				strSqlParam[1][3] = form.getCardid();
				strSqlParam[1][4] = form.getPhone();
				strSqlParam[1][5] = form.getFax();
				strSqlParam[1][6] = form.getPhoneh();
				strSqlParam[1][7] = form.getHandset();
				strSqlParam[1][8] = form.getBp();
				strSqlParam[1][9] = form.getEmail();
				strSqlParam[1][10] = form.getAddress();
				strSqlParam[1][11] = form.getPostc();
				strSqlParam[1][12] = form.getDutyc();
				strSqlParam[1][13] = form.getGread();
				strSqlParam[1][14] = form.getOperdate();
				strSqlParam[1][15] = form.getOperator();
				strSqlParam[1][16] = form.getUserid();
			} else {
				strSql =
					new String[] { UPDATE_USERS_SQL, INSERT_ASSOCIATOR_SQL };
				strSqlParam = new String[2][17];
				//user表参数
				strSqlParam[0][0] = form.getAppsys();
				strSqlParam[0][1] = form.getPurview();
				strSqlParam[0][2] = form.getUserid();
				//插入语句参数
				strSqlParam[1][0] = form.getUserid();
				strSqlParam[1][1] = form.getUsername();
				strSqlParam[1][2] = form.getSex();
				strSqlParam[1][3] = form.getBirthday();
				strSqlParam[1][4] = form.getCardid();
				strSqlParam[1][5] = form.getPhone();
				strSqlParam[1][6] = form.getFax();
				strSqlParam[1][7] = form.getPhoneh();
				strSqlParam[1][8] = form.getHandset();
				strSqlParam[1][9] = form.getBp();
				strSqlParam[1][10] = form.getEmail();
				strSqlParam[1][11] = form.getAddress();
				strSqlParam[1][12] = form.getPostc();
				strSqlParam[1][13] = form.getDutyc();
				strSqlParam[1][14] = form.getGread();
				strSqlParam[1][15] = form.getOperdate();
				strSqlParam[1][16] = form.getOperator();
			}
		}
		dsb.setSql(strSql);
		dsb.setBatchParam(strSqlParam);
		try {
			dsb.executeArrayBatch();
		} catch (SQLException e) {
			log.error("更新用户失败", e);
			throw new UserException(
				UserException.UPDATE_INFORMATION_FAIL,
				"更新用户失败");
		} catch (Exception e) {
			log.error("更新用户失败", e);
			throw new UserException(
				UserException.UPDATE_INFORMATION_FAIL,
				"更新用户失败");
		}

	}

	public void query() throws UserException {
		DataBean db = new DataBean();
		DynaSqlBean dsb = new DynaSqlBean();
		dsb.setParam(form.getUserid());
		dsb.setSql(SELECT_USERS_SQL);
		try {
			db.setCrs(dsb.executeQuery());
			form.setAppsys(db.getElementValue(0, "APPSYS"));
			form.setPurview(db.getElementValue(0, "PURVIEW"));
			db.release();
		} catch (SQLException e1) {
			db.release();
			log.error("取用户基本信息时出错", e1);
			throw new UserException(
				UserException.QUERY_INFORMATION_FAIL,
				"取用户基本信息时出错");
		}

		dsb.setParam(form.getUserid());
		if (form.getUserType().equals(UserType.EMPLOYEE)) {
			//内部员工
			dsb.setSql(SELECT_EMPLOYEE_SQL);
			try {
				db.setCrs(dsb.executeQuery());
				form.setUsername(db.getElementValue(0, "NAMEC").trim());
				form.setSex(db.getElementValue(0, "GENDER").trim());
				form.setDutyc(db.getElementValue(0, "JOB").trim());
				form.setDept(db.getElementValue(0, "DEPT").trim());
				form.setBirthday(db.getElementValue(0, "BIRTHDAY").trim());
				form.setAddress(db.getElementValue(0, "ADDRESS").trim());
				form.setPhone(db.getElementValue(0, "TEL").trim());
				form.setBp(db.getElementValue(0, "BP").trim());
				form.setHandset(db.getElementValue(0, "HANDSET").trim());
				form.setLevel(db.getElementValue(0, "LEVEL").trim());
				form.setState(db.getElementValue(0, "STATE").trim());
				db.release();
			} catch (SQLException e) {
				log.error("取员工信息时出错", e);
				db.release();
				throw new UserException(
					UserException.QUERY_INFORMATION_FAIL,
					"取员工信息时出错");
			}
		} else {
			//合作伙伴
			dsb.setSql(SELECT_ASSOCIATOR_SQL);
			try {
				db.setCrs(dsb.executeQuery());
				form.setUsername(db.getElementValue(0, "NAME").trim());
				form.setSex(db.getElementValue(0, "SEX").trim());
				form.setBirthday(db.getElementValue(0, "BIRTHDAY").trim());
				form.setCardid(db.getElementValue(0, "CARDID").trim());
				form.setPhone(db.getElementValue(0, "PHONE").trim());
				form.setFax(db.getElementValue(0, "FAX").trim());
				form.setPhoneh(db.getElementValue(0, "PHONEH").trim());
				form.setHandset(db.getElementValue(0, "HANDSET").trim());
				form.setBp(db.getElementValue(0, "BP").trim());
				form.setEmail(db.getElementValue(0, "EMAIL").trim());
				form.setAddress(db.getElementValue(0, "ADDRESS").trim());
				form.setPostc(db.getElementValue(0, "POSTC").trim());
				form.setDutyc(db.getElementValue(0, "DUTYC").trim());
				form.setGread(db.getElementValue(0, "GREAD").trim());
				db.release();
			} catch (SQLException e) {
				log.error("取用户信息时出错", e);
				db.release();
				throw new UserException(
					UserException.QUERY_INFORMATION_FAIL,
					"取用户信息时出错");
			}
		}

	}

	/**
	 * 检查USERS表，是否存在已有的记录
	 * @return 存在为TRUE，不存在为FALSE
	 */
	private boolean isUserExistInUsers() {
		DataBean db = new DataBean();
		DynaSqlBean dsl = new DynaSqlBean();
		dsl.setSql(CHECK_DUPLICATE_USER_SQL);
		dsl.setParam(form.getUserid());
		try {
			db.setCrs(dsl.executeQuery());
		} catch (SQLException e) {
			log.error("在USERS表查重时出现错误！", e);
			db.release();
			return false;
		}
		return db.getRowCount() > 0;
	}

	/**
	 * 检查Employee表，是否存在已有的记录
	 * @return 存在为TRUE，不存在为FALSE
	 */
	private boolean isUserExistInEmployee() {
		DataBean db = new DataBean();
		DynaSqlBean dsl = new DynaSqlBean();
		dsl.setSql(CHECK_DUPLICATE_EMPLOYEE_SQL);
		dsl.setParam(form.getUserid());
		try {
			db.setCrs(dsl.executeQuery());
		} catch (SQLException e) {
			log.error("在Employee表查重时出现错误！", e);
			db.release();
			return false;
		}
		return db.getRowCount() > 0;
	}

	/**
	 * 检查Asociator表，是否存在已有的记录
	 * @return 存在为TRUE，不存在为FALSE
	 */
	private boolean isUserExistInAssociator() {
		DataBean db = new DataBean();
		DynaSqlBean dsl = new DynaSqlBean();
		dsl.setSql(CHECK_DUPLICATE_ASSOCIATOR_SQL);
		dsl.setParam(form.getUserid());
		try {
			db.setCrs(dsl.executeQuery());
		} catch (SQLException e) {
			log.error("在ASSOCIATOR表查重时出现错误！", e);
			db.release();
			return false;
		}
		return db.getRowCount() > 0;
	}

}

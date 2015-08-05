/*
 * @(#)LogonBean.java  2003-10-17
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.user;

import java.sql.SQLException;

import com.idn.property.PropertyManager;
import com.idn.sql.DataBean;
import com.idn.sql.DynaSqlBean;
import com.idn.util.DateUtil;
import com.idn.util.FormatDate;

/**
 * <P> 登录处理 </P>
 * 
 * @version 0.2
 * @author navy
 */
public class LogonBean {

	/**
	 * 启用 commons-logging API 来进行日志记录
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(LogonBean.class);

	/**
	 * 检查用户是否存在SQL语句
	 */
	private final String CHECK_USERID_SQL =
		"SELECT USERID FROM USERS " + "WHERE USERID = ? ";

	/**
	 * 检查用户口令是否正确语句
	 */
	private final String CHECK_PASSWORD_SQL =
		"SELECT APPSYS, PURVIEW, USERIDG, LIFEBEG, LIFEEND, BEGROUP FROM USERS "
			+ "WHERE (USERID = ? AND UPWD = ?)  ";

	/**
	 * 更新用户口令
	 */
	private final String UPDATE_PASSWORD_SQL =
		"UPDATE USERS SET UPWD = ? WHERE USERID = ?  ";

	/**
	 * 查询用户名称语句(服务商)
	 */
	private final String QUERY_VNDINFO_USERNAME =
		"SELECT VNDNAM FROM VNDINFO WHERE VNDNUM = ?";

	/**
	 * 查询用户名称语句(雇员)
	 */
	private final String QUERY_EMPLOYEE_USERNAME =
		"SELECT NAMEC FROM EMPLOYEE WHERE USERID = ?";

	/**
	 * 查询用户名称语句（客户）
	 */
	private final String QUERY_ASSOCIATOR_USERNAME =
		"SELECT NAME FROM ASSOCIATOR WHERE USERID = ?";

	/**
	 * 登录FORMBEAN
	 */
	private LogonForm form = null;

	/**
	 * 构造函数
	 *
	 */
	public LogonBean() {
		super();
	}

	/**
	 * 校验用户信息，成功后登录
	 * @throws UserException
	 */
	public void logon() throws UserException {

		if (log.isDebugEnabled()) {
			log.debug("校验基本信息...");
		}
		if (form.getUserid() == null)
			throw new UserException(UserException.USERID_EMPTY, "用户名为空");
		if (form.getPassword() == null)
			throw new UserException(UserException.PASSWORD_EMPTY, "口令为空");
		if (log.isDebugEnabled()) {
			log.debug("基本信息校验完成。");
		}

		String[] strParamPass =
			new String[] { form.getUserid(), form.getPassword()};
		DataBean db = new DataBean();
		DynaSqlBean dsb = new DynaSqlBean();

		try {

			//检查用户是否存在
			if (log.isDebugEnabled()) {
				log.debug("检查用户ID . . .");
			}
			dsb.setSql(CHECK_USERID_SQL);
			dsb.setParam(form.getUserid());
			db.setCrs(dsb.executeQuery());
			if (db.getRowCount() == 0)
				throw new UserException(
					UserException.USERID_NOT_EXISTED,
					"用户名不存在");
			if (log.isDebugEnabled()) {
				log.debug("用户ID校验成功，开始用户口令校验 . . .");
			}
			//检查用户口令是否正确
			dsb.setSql(CHECK_PASSWORD_SQL);
			dsb.setParam(strParamPass);
			db.setCrs(dsb.executeQuery());
			if (db.getRowCount() == 0)
				throw new UserException(UserException.WRONG_PASSWORD, "口令不正确");
			if (log.isDebugEnabled()) {
				log.debug("用户口令校验成功，开始确认用户名与组名是否相同 . . .");
			}
			String userLogonGroup = db.getElementValue(0, "USERIDG").trim();
			form.setUseridGroup(userLogonGroup);
			if (form.isIgnoreCase()) {
				if (!form.getUserid().trim().equalsIgnoreCase(userLogonGroup))
					throw new UserException(
						UserException.NEED_GROUP_USERID_LOGON,
						"Userid 与组ID不相同，需要使用组ID来登录！ Userid: "
							+ form.getUserid()
							+ "USERIDG: "
							+ userLogonGroup);
			} else {
				if (!form.getUserid().trim().equals(userLogonGroup))
					throw new UserException(
						UserException.NEED_GROUP_USERID_LOGON,
						"Userid 与组ID不相同，需要使用组ID来登录！ Userid: "
							+ form.getUserid()
							+ "USERIDG: "
							+ userLogonGroup);
			}
			if (log.isDebugEnabled()) {
				log.debug("用户名与登录组相同，开始确认生命周期 . . .");
			}

			long nLifeBegin =
				Long.parseLong(db.getElementValue(0, "LIFEBEG").trim());
			long nLifeEnd =
				Long.parseLong(db.getElementValue(0, "LIFEEND").trim());
			long nNow = System.currentTimeMillis();
			if (log.isDebugEnabled()) {
				log.debug(
					"生命周期起始时间："
						+ FormatDate.format(nLifeBegin, FormatDate.LONG_YYYY_M_D)
						+ "/结束时间："
						+ FormatDate.format(nLifeEnd, FormatDate.LONG_YYYY_M_D));
			}
			form.setLiftBegin(nLifeBegin);
			form.setLiftEnd(nLifeEnd);
			if (nLifeBegin > nLifeEnd)
				throw new UserException(
					UserException.LIFE_INACTIVE,
					"用户生命周期设置有误,起始时间小于结束时间!");
			if ((DateUtil
				.elapsedDays(DateUtil.getDate(nNow), DateUtil.getDate(nLifeEnd))
				< 0)
				|| (DateUtil
					.elapsedDays(
						DateUtil.getDate(nNow),
						DateUtil.getDate(nLifeBegin))
					> 0))
				throw new UserException(
					UserException.LIFE_INACTIVE,
					"超过用户生命周期,用户处于不可用状态！");
			if (log.isDebugEnabled()) {
				log.debug("生命周期校验成功！开始检查用户使用系统权限 . . .");
			}
			//设置用户所在的应用系统代码
			if (!PropertyManager
				.getProperty("appsys")
				.trim()
				.equals(db.getElementValue(0, "APPSYS").trim()))
				throw new UserException(UserException.NOT_PERMIT, "没有权限使用此系统");
			if (log.isDebugEnabled()) {
				log.debug("用户权限校验成功，开始设置用户相关信息 . . .");
			}

			//设置用户权限
			form.setPurview(db.getElementValue(0, "PURVIEW"));
			
			//设置用户组
			form.setUseridGroup(db.getElementValue(0, "BEGROUP"));

			try {
				if (log.isDebugEnabled()) {
					log.debug("从VNDINFO表中查用户名称");
				}
				dsb.setSql(QUERY_VNDINFO_USERNAME);
				dsb.setParam(form.getUserid());
				db.setCrs(dsb.executeQuery());
				form.setUserType(UserType.VENDOR);
				if (db.getRowCount() > 0) {
					form.setUsername(db.getElementValue(0, 0).trim());
					db.release();
					if (log.isDebugEnabled()) {
						log.debug("在VNDINFO发现用户名称");
					}
				}
			} catch (SQLException e) {
			}
			if (form.getUsername() == null) {
				try {
					if (log.isDebugEnabled()) {
						log.debug("从EMPLOYEE表中查用户名");
					}
					dsb.setSql(QUERY_EMPLOYEE_USERNAME);
					dsb.setParam(form.getUserid());
					db.setCrs(dsb.executeQuery());
					form.setUserType(UserType.EMPLOYEE);
					if (db.getRowCount() > 0) {
						form.setUsername(db.getElementValue(0, 0).trim());
						db.release();
						if (log.isDebugEnabled()) {
							log.debug("在EMPLOYEE表中发现用户名称");
						}
					}
				} catch (SQLException e) {
				}
			}
			if (form.getUsername() == null) {
				try {
					if (log.isDebugEnabled()) {
						log.debug("从ASSOCIATOR表中查用户名");
					}
					dsb.setSql(QUERY_ASSOCIATOR_USERNAME);
					dsb.setParam(form.getUserid());
					db.setCrs(dsb.executeQuery());
					form.setUserType(UserType.EMPLOYEE);
					if (db.getRowCount() > 0) {
						form.setUsername(db.getElementValue(0, 0).trim());
						db.release();
						if (log.isDebugEnabled()) {
							log.debug("在ASSOCIATOR表中发现用户名称");
						}
					}
				} catch (SQLException e) {
				}
			}
			if (form.getUsername() == null) {
				if (log.isWarnEnabled()) {
					log.warn("没有设置用户详细信息！");
				}
				form.setUsername(form.getUserid());
				form.setUserType(UserType.UNREGISTERED);
			}

			//用户口令修改
			if (form.getNewpass() != null && !form.getNewpass().equals("")) {
				if (log.isDebugEnabled()) {
					log.debug("开始更改口令 . . .");
				}
				if (form.getNewpass().equals(form.getRepass())) {
					strParamPass =
						new String[] { form.getNewpass(), form.getUserid()};
					dsb.setSql(UPDATE_PASSWORD_SQL);
					dsb.setParam(strParamPass);
					dsb.execute();
					//清除更新口令
					form.setNewpass(null);
					form.setRepass(null);
					if (log.isDebugEnabled()) {
						log.debug("口令更改成功！");
					}
				} else {
					throw new UserException(
						UserException.UPDATE_PASS_FAIL,
						"更改口令失败，新旧口令不匹配");
				}
			}

		} catch (SQLException sqle) {
			throw new UserException(UserException.SYSTEM_ERROR, "系统错误");
		} catch (NumberFormatException e) {
			throw new UserException(UserException.LIFE_INACTIVE, "生命周期格式设置错误");
		}
		finally {
			strParamPass = null;
			db.destory();
			db = null;
			dsb = null;
		}
	}
	/**
	 * @return LogonForm
	 */
	public LogonForm getForm() {
		return form;
	}

	/**
	 * @param form
	 */
	public void setForm(LogonForm form) {
		this.form = form;
	}

}

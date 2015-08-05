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
 * <P> ��¼���� </P>
 * 
 * @version 0.2
 * @author navy
 */
public class LogonBean {

	/**
	 * ���� commons-logging API ��������־��¼
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(LogonBean.class);

	/**
	 * ����û��Ƿ����SQL���
	 */
	private final String CHECK_USERID_SQL =
		"SELECT USERID FROM USERS " + "WHERE USERID = ? ";

	/**
	 * ����û������Ƿ���ȷ���
	 */
	private final String CHECK_PASSWORD_SQL =
		"SELECT APPSYS, PURVIEW, USERIDG, LIFEBEG, LIFEEND, BEGROUP FROM USERS "
			+ "WHERE (USERID = ? AND UPWD = ?)  ";

	/**
	 * �����û�����
	 */
	private final String UPDATE_PASSWORD_SQL =
		"UPDATE USERS SET UPWD = ? WHERE USERID = ?  ";

	/**
	 * ��ѯ�û��������(������)
	 */
	private final String QUERY_VNDINFO_USERNAME =
		"SELECT VNDNAM FROM VNDINFO WHERE VNDNUM = ?";

	/**
	 * ��ѯ�û��������(��Ա)
	 */
	private final String QUERY_EMPLOYEE_USERNAME =
		"SELECT NAMEC FROM EMPLOYEE WHERE USERID = ?";

	/**
	 * ��ѯ�û�������䣨�ͻ���
	 */
	private final String QUERY_ASSOCIATOR_USERNAME =
		"SELECT NAME FROM ASSOCIATOR WHERE USERID = ?";

	/**
	 * ��¼FORMBEAN
	 */
	private LogonForm form = null;

	/**
	 * ���캯��
	 *
	 */
	public LogonBean() {
		super();
	}

	/**
	 * У���û���Ϣ���ɹ����¼
	 * @throws UserException
	 */
	public void logon() throws UserException {

		if (log.isDebugEnabled()) {
			log.debug("У�������Ϣ...");
		}
		if (form.getUserid() == null)
			throw new UserException(UserException.USERID_EMPTY, "�û���Ϊ��");
		if (form.getPassword() == null)
			throw new UserException(UserException.PASSWORD_EMPTY, "����Ϊ��");
		if (log.isDebugEnabled()) {
			log.debug("������ϢУ����ɡ�");
		}

		String[] strParamPass =
			new String[] { form.getUserid(), form.getPassword()};
		DataBean db = new DataBean();
		DynaSqlBean dsb = new DynaSqlBean();

		try {

			//����û��Ƿ����
			if (log.isDebugEnabled()) {
				log.debug("����û�ID . . .");
			}
			dsb.setSql(CHECK_USERID_SQL);
			dsb.setParam(form.getUserid());
			db.setCrs(dsb.executeQuery());
			if (db.getRowCount() == 0)
				throw new UserException(
					UserException.USERID_NOT_EXISTED,
					"�û���������");
			if (log.isDebugEnabled()) {
				log.debug("�û�IDУ��ɹ�����ʼ�û�����У�� . . .");
			}
			//����û������Ƿ���ȷ
			dsb.setSql(CHECK_PASSWORD_SQL);
			dsb.setParam(strParamPass);
			db.setCrs(dsb.executeQuery());
			if (db.getRowCount() == 0)
				throw new UserException(UserException.WRONG_PASSWORD, "�����ȷ");
			if (log.isDebugEnabled()) {
				log.debug("�û�����У��ɹ�����ʼȷ���û����������Ƿ���ͬ . . .");
			}
			String userLogonGroup = db.getElementValue(0, "USERIDG").trim();
			form.setUseridGroup(userLogonGroup);
			if (form.isIgnoreCase()) {
				if (!form.getUserid().trim().equalsIgnoreCase(userLogonGroup))
					throw new UserException(
						UserException.NEED_GROUP_USERID_LOGON,
						"Userid ����ID����ͬ����Ҫʹ����ID����¼�� Userid: "
							+ form.getUserid()
							+ "USERIDG: "
							+ userLogonGroup);
			} else {
				if (!form.getUserid().trim().equals(userLogonGroup))
					throw new UserException(
						UserException.NEED_GROUP_USERID_LOGON,
						"Userid ����ID����ͬ����Ҫʹ����ID����¼�� Userid: "
							+ form.getUserid()
							+ "USERIDG: "
							+ userLogonGroup);
			}
			if (log.isDebugEnabled()) {
				log.debug("�û������¼����ͬ����ʼȷ���������� . . .");
			}

			long nLifeBegin =
				Long.parseLong(db.getElementValue(0, "LIFEBEG").trim());
			long nLifeEnd =
				Long.parseLong(db.getElementValue(0, "LIFEEND").trim());
			long nNow = System.currentTimeMillis();
			if (log.isDebugEnabled()) {
				log.debug(
					"����������ʼʱ�䣺"
						+ FormatDate.format(nLifeBegin, FormatDate.LONG_YYYY_M_D)
						+ "/����ʱ�䣺"
						+ FormatDate.format(nLifeEnd, FormatDate.LONG_YYYY_M_D));
			}
			form.setLiftBegin(nLifeBegin);
			form.setLiftEnd(nLifeEnd);
			if (nLifeBegin > nLifeEnd)
				throw new UserException(
					UserException.LIFE_INACTIVE,
					"�û�����������������,��ʼʱ��С�ڽ���ʱ��!");
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
					"�����û���������,�û����ڲ�����״̬��");
			if (log.isDebugEnabled()) {
				log.debug("��������У��ɹ�����ʼ����û�ʹ��ϵͳȨ�� . . .");
			}
			//�����û����ڵ�Ӧ��ϵͳ����
			if (!PropertyManager
				.getProperty("appsys")
				.trim()
				.equals(db.getElementValue(0, "APPSYS").trim()))
				throw new UserException(UserException.NOT_PERMIT, "û��Ȩ��ʹ�ô�ϵͳ");
			if (log.isDebugEnabled()) {
				log.debug("�û�Ȩ��У��ɹ�����ʼ�����û������Ϣ . . .");
			}

			//�����û�Ȩ��
			form.setPurview(db.getElementValue(0, "PURVIEW"));
			
			//�����û���
			form.setUseridGroup(db.getElementValue(0, "BEGROUP"));

			try {
				if (log.isDebugEnabled()) {
					log.debug("��VNDINFO���в��û�����");
				}
				dsb.setSql(QUERY_VNDINFO_USERNAME);
				dsb.setParam(form.getUserid());
				db.setCrs(dsb.executeQuery());
				form.setUserType(UserType.VENDOR);
				if (db.getRowCount() > 0) {
					form.setUsername(db.getElementValue(0, 0).trim());
					db.release();
					if (log.isDebugEnabled()) {
						log.debug("��VNDINFO�����û�����");
					}
				}
			} catch (SQLException e) {
			}
			if (form.getUsername() == null) {
				try {
					if (log.isDebugEnabled()) {
						log.debug("��EMPLOYEE���в��û���");
					}
					dsb.setSql(QUERY_EMPLOYEE_USERNAME);
					dsb.setParam(form.getUserid());
					db.setCrs(dsb.executeQuery());
					form.setUserType(UserType.EMPLOYEE);
					if (db.getRowCount() > 0) {
						form.setUsername(db.getElementValue(0, 0).trim());
						db.release();
						if (log.isDebugEnabled()) {
							log.debug("��EMPLOYEE���з����û�����");
						}
					}
				} catch (SQLException e) {
				}
			}
			if (form.getUsername() == null) {
				try {
					if (log.isDebugEnabled()) {
						log.debug("��ASSOCIATOR���в��û���");
					}
					dsb.setSql(QUERY_ASSOCIATOR_USERNAME);
					dsb.setParam(form.getUserid());
					db.setCrs(dsb.executeQuery());
					form.setUserType(UserType.EMPLOYEE);
					if (db.getRowCount() > 0) {
						form.setUsername(db.getElementValue(0, 0).trim());
						db.release();
						if (log.isDebugEnabled()) {
							log.debug("��ASSOCIATOR���з����û�����");
						}
					}
				} catch (SQLException e) {
				}
			}
			if (form.getUsername() == null) {
				if (log.isWarnEnabled()) {
					log.warn("û�������û���ϸ��Ϣ��");
				}
				form.setUsername(form.getUserid());
				form.setUserType(UserType.UNREGISTERED);
			}

			//�û������޸�
			if (form.getNewpass() != null && !form.getNewpass().equals("")) {
				if (log.isDebugEnabled()) {
					log.debug("��ʼ���Ŀ��� . . .");
				}
				if (form.getNewpass().equals(form.getRepass())) {
					strParamPass =
						new String[] { form.getNewpass(), form.getUserid()};
					dsb.setSql(UPDATE_PASSWORD_SQL);
					dsb.setParam(strParamPass);
					dsb.execute();
					//������¿���
					form.setNewpass(null);
					form.setRepass(null);
					if (log.isDebugEnabled()) {
						log.debug("������ĳɹ���");
					}
				} else {
					throw new UserException(
						UserException.UPDATE_PASS_FAIL,
						"���Ŀ���ʧ�ܣ��¾ɿ��ƥ��");
				}
			}

		} catch (SQLException sqle) {
			throw new UserException(UserException.SYSTEM_ERROR, "ϵͳ����");
		} catch (NumberFormatException e) {
			throw new UserException(UserException.LIFE_INACTIVE, "�������ڸ�ʽ���ô���");
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

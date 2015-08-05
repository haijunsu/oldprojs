/*
 * @(#)XPDropDownMenuBean.java  2004-2-16
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package com.idn.menu;

import java.util.Hashtable;

import com.idn.property.SystemProperties;

/**
 * <P>����XP�������˵�</P>
 * 
 * @version 0.1
 * @author navy
 */
public class XPDropDownMenuBean extends StandardMenuBean {

	/**
	 * ���� commons-logging API ��������־��¼
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(XPDropDownMenuBean.class);
		
	/**
	 * ���ɺ��HTML����
	 */
	private String m_MenuHtml = null;
	
	/**
	 * ���캯��
	 */
	public XPDropDownMenuBean() {
		super();
	}

	/**
	 * ������ʾ�˵���HTML����
	 * @return ���ɺ�Ĵ���
	 */
	public String renderMenuHtml() {
		StringBuffer sb = new StringBuffer();
		int k = 0;
		if (!getPurview().equals("0")) {
			Hashtable ht = getHtMenus();
			try {
				for (int i = 0; i < getSortedMenuNodeIds().length; i++) {
					MenuNode mn =
						(MenuNode) getHtMenus().get(getSortedMenuNodeIds()[i]);
					sb.append(renderNode(mn, i));
					k++;
				}
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(), e);
			}

		}
		sb.append(renderCommonNode(k + 1));
		return sb.toString();
	}

	/**
	 * ���ɽڵ�HTML����
	 * @param mn �˵��ڵ�
	 * @param index �˵�˳���
	 * @return �ڵ�HTML����
	 */
	private String renderNode(MenuNode mn, int index) {
		StringBuffer sb = new StringBuffer();
		sb.append("mpmenu").append(index + 1).append("=new mMenu('");
		sb
			.append(mn.getName())
			.append("','")
			.append(mn.getUrl())
			.append("','")
			.append(getUrlTarget())
			.append("','','','','');")
			.append(SystemProperties.LINE_SEPARATOR);

		for (int i = 0; i < mn.getSortedMenuIds().length; i++) {
			MenuItem mi =
				MenusUtil.getMenuItemByMenuId(
					mn.getSortedMenuIds()[i],
					mn.getHtMenu());
			sb
				.append("mpmenu")
				.append(index + 1)
				.append(".addItem(")
				.append(renderItem(mi))
				.append(");")
				.append(SystemProperties.LINE_SEPARATOR);
		}
		return sb.toString();
	}

	/**
	 * ���ɲ˵���
	 * @param mi �˵���
	 * @return ���ɲ˵�����HTML
	 */
	private String renderItem(MenuItem mi) {
		StringBuffer sb = new StringBuffer();
		sb
			.append("new mMenuItem('")
			.append(mi.getName())
			.append("','")
			.append(mi.getUrl())
			.append("','")
			.append(getUrlTarget())
			.append("',false,'")
			.append(mi.getName())
			.append("','','','','')");
		return sb.toString();
	}

	private String renderCommonNode(int i) {
		StringBuffer sb = new StringBuffer();
		sb.append("mpmenu").append(i).append("=new mMenu('");
		sb.append("ϵͳ','','self','','','','');").append(
			SystemProperties.LINE_SEPARATOR);
		sb
			.append("mpmenu")
			.append(i)
			.append(".addItem(new mMenuItem('��ҳ','")
			.append(getContextPath())
			.append("/index.jsp?urlname=��ҳ")
			.append("','self',false,'������ҳ','','','',''));")
			.append(SystemProperties.LINE_SEPARATOR);
		sb
			.append("mpmenu")
			.append(i)
			.append(".addItem(new mMenuItem('����','")
			.append(getContextPath())
			.append("/help/syshelp.html','self',false,'��ʾ������Ϣ','','','',''));")
			.append(SystemProperties.LINE_SEPARATOR);
		if (!getPurview().equals("0"))
			sb
				.append("mpmenu")
				.append(i)
				.append(".addItem(new mMenuItem('�˳�','")
				.append(getContextPath())
				.append("/logoff.do','self',false,'�˳�','','','',''));")
				.append(SystemProperties.LINE_SEPARATOR);

		return sb.toString();
	}
	
	/**
	 * @return �������ɲ˵����HTML
	 */
	public String getMenuHtml() {
		if (m_MenuHtml == null || isReload())
			m_MenuHtml = renderMenuHtml();
		return m_MenuHtml;
	}

	/**
	 * @param string
	 */
	public void setMenuHtml(String string) {
		m_MenuHtml = string;
	}

}

/*
 * @(#)CrisMenuBean.java  2004-3-24
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package com.idn.menu;

import org.apache.log4j.Logger;

import java.util.Hashtable;

import com.idn.property.SystemProperties;

/**
 * <P> ������˵� </P>
 * 
 * @version 0.1
 * @author navy
 */
public class CrisMenuBean extends StandardMenuBean {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CrisMenuBean.class);


	/**
	 * ���ɺ��HTML����
	 */
	private String m_MenuHtml = null;

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
					sb.append(renderNode(mn));
					k++;
				}
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}

		}
		return sb.toString();
	}

	/**
	 * ���ɽڵ�HTML����
	 * @param mn �˵��ڵ�
	 * @return �ڵ�HTML����
	 */
	private String renderNode(MenuNode mn) {
		StringBuffer sb = new StringBuffer();
		sb
			.append("                <table cellpadding=0 cellspacing=0 width=158 align=center>")
			.append(SystemProperties.LINE_SEPARATOR);
		sb.append("                    <tr>").append(
			SystemProperties.LINE_SEPARATOR);
		sb
			.append("                        <td height=25 class=menu_title ")
			.append("onmouseover=this.className='menu_title2';")
			.append(" onmouseout=this.className='menu_title'; ")
			.append("background=\"")
			.append(getContextPath())
			.append("/img/menu")
			.append(mn.getNodeId())
			.append(".gif\" id=menuTitle1 onclick=\" showsubmenu(")
			.append(Integer.parseInt(mn.getNodeId()))
			.append(") \"> ")
			.append(SystemProperties.LINE_SEPARATOR);
		sb.append("                            <span>").append(
			mn.getName()).append(
			"</span>");
		sb.append("                        </td>").append(
			SystemProperties.LINE_SEPARATOR);
		sb.append("                    </tr>").append(
			SystemProperties.LINE_SEPARATOR);
		sb.append("                    <tr>").append(
			SystemProperties.LINE_SEPARATOR);
		sb
			.append("                        <td style=\"display:\" id='submenu")
			.append(Integer.parseInt(mn.getNodeId()))
			.append("'>")
			.append(SystemProperties.LINE_SEPARATOR);
		sb
			.append("                            <div class=sec_menu style=\"width:158 \">")
			.append(SystemProperties.LINE_SEPARATOR);
		sb
			.append("                                <table cellpadding=0 cellspacing=0 align=center width=135>")
			.append(SystemProperties.LINE_SEPARATOR);

		for (int i = 0; i < mn.getSortedMenuIds().length; i++) {
			MenuItem mi =
				MenusUtil.getMenuItemByMenuId(
					mn.getSortedMenuIds()[i],
					mn.getHtMenu());
			sb.append(renderItem(mi)).append(SystemProperties.LINE_SEPARATOR);
		}
		sb.append("                                </table>").append(
			SystemProperties.LINE_SEPARATOR);
		sb.append("                            </div>").append(
			SystemProperties.LINE_SEPARATOR);
		sb.append(
			"                            <div  style=\"width:158\">").append(
			SystemProperties.LINE_SEPARATOR);
		sb
			.append("                                <table cellpadding=0 cellspacing=0 align=center width=135>")
			.append(SystemProperties.LINE_SEPARATOR);
		sb
			.append("                                    <tr><td height=20></td></tr>")
			.append(SystemProperties.LINE_SEPARATOR);
		sb.append("                                </table>").append(
			SystemProperties.LINE_SEPARATOR);
		sb.append("                            </div>").append(
			SystemProperties.LINE_SEPARATOR);
		sb.append("                        </td>").append(
			SystemProperties.LINE_SEPARATOR);
		sb.append("                    </tr>").append(
			SystemProperties.LINE_SEPARATOR);
		sb.append("                </table>").append(
			SystemProperties.LINE_SEPARATOR);
		return sb.toString();
	}

	/**
	 * ���ɲ˵���
	 * @param mi �˵���
	 * @return ���ɲ˵�����HTML
	 */
	private String renderItem(MenuItem mi) {
		StringBuffer sb = new StringBuffer();
		sb.append("                                    <tr> ").append(
			SystemProperties.LINE_SEPARATOR);
		sb
			.append("                                        <td height=20><a href=")
			.append(mi.getUrl())
			.append(" target=")
			.append(getUrlTarget())
			.append(">")
			.append(mi.getName())
			.append("</a></td>")
			.append(SystemProperties.LINE_SEPARATOR);
		sb.append("                                    </tr>").append(
			SystemProperties.LINE_SEPARATOR);
		return sb.toString();
	}

	/**
	 * @return �������ɲ˵����HTML
	 */
	public String getMenuHtml() {
		if (this.m_MenuHtml == null || isReload())
			this.m_MenuHtml = renderMenuHtml();
		return this.m_MenuHtml;
	}

	/**
	 * @param string
	 */
	public void setMenuHtml(String string) {
		this.m_MenuHtml = string;
	}

}

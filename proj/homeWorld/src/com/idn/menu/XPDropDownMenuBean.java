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
 * <P>类似XP的下拉菜单</P>
 * 
 * @version 0.1
 * @author navy
 */
public class XPDropDownMenuBean extends StandardMenuBean {

	/**
	 * 启用 commons-logging API 来进行日志记录
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(XPDropDownMenuBean.class);
		
	/**
	 * 生成后的HTML代码
	 */
	private String m_MenuHtml = null;
	
	/**
	 * 构造函数
	 */
	public XPDropDownMenuBean() {
		super();
	}

	/**
	 * 生成显示菜单的HTML代码
	 * @return 生成后的代码
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
	 * 生成节点HTML代码
	 * @param mn 菜单节点
	 * @param index 菜单顺序号
	 * @return 节点HTML代码
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
	 * 生成菜单项
	 * @param mi 菜单项
	 * @return 生成菜单项后的HTML
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
		sb.append("系统','','self','','','','');").append(
			SystemProperties.LINE_SEPARATOR);
		sb
			.append("mpmenu")
			.append(i)
			.append(".addItem(new mMenuItem('首页','")
			.append(getContextPath())
			.append("/index.jsp?urlname=首页")
			.append("','self',false,'返回首页','','','',''));")
			.append(SystemProperties.LINE_SEPARATOR);
		sb
			.append("mpmenu")
			.append(i)
			.append(".addItem(new mMenuItem('帮助','")
			.append(getContextPath())
			.append("/help/syshelp.html','self',false,'显示帮助信息','','','',''));")
			.append(SystemProperties.LINE_SEPARATOR);
		if (!getPurview().equals("0"))
			sb
				.append("mpmenu")
				.append(i)
				.append(".addItem(new mMenuItem('退出','")
				.append(getContextPath())
				.append("/logoff.do','self',false,'退出','','','',''));")
				.append(SystemProperties.LINE_SEPARATOR);

		return sb.toString();
	}
	
	/**
	 * @return 返回生成菜单后的HTML
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

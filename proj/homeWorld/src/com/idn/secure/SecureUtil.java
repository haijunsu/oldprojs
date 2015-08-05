/*
 * @(#)SecureUtil.java  2004-2-6
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package com.idn.secure;

import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import com.idn.menu.MenuItem;
import com.idn.menu.MenusIndexByUrl;
import com.idn.menu.MenusUtil;
import com.idn.property.PropertyManager;
import com.idn.util.CommonTools;
import com.idn.util.ServletTools;

/**
 * <P>校验访问权限</P>
 * 
 * @version 0.1
 * @author navy
 */
public class SecureUtil {
	
	/**
	 * 启用 commons-logging API 来进行日志记录
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(SecureUtil.class);

	/**
	 * 构造函数 
	 */
	public SecureUtil() {
		super();
	}

	/**
	 * 判断有没有权限执行指定的菜单
	 * @param mi 菜单项
	 * @param strPurview 用户权限
	 * @return 是否有权限
	 */
	public static boolean isPermited(MenuItem mi, String strPurview) {
			return CommonTools.isMenuShow(strPurview, mi.getPurview(), PropertyManager.getBooleanProperty("menu.encryption"));
	}
	
	/**
	 * 查找在menuall表定义的对应模块的权限
	 * @param request request请求
	 * @return 对应的模块
	 */
	public static MenuItem findMenuItemByRequest(HttpServletRequest request) {
		String strUrlPath = request.getRequestURI();
		if (log.isDebugEnabled()) {
			log.debug("requestUrl: " + strUrlPath);
		}
		int nFitIndex = 0;
		boolean isFinded = false;
		int nParamNumber = 0;
		
		try {
			Hashtable htReqParm =
				ServletTools.RequestParametersToHashtable(request);
			MenuItem[] mi = MenusIndexByUrl.getMenuItemsByUrl(strUrlPath);
			if (mi == null) {
				return null;
			}
			for (int i = 0; i < mi.length; i++) {
				//查找最合适菜单
				if (log.isDebugEnabled()) {
					log.debug("找到的第" + (i + 1) + "一个菜单：" + mi[i].getMenuId() + "/" + mi[i].getUrl());
				}
				Hashtable ht = MenusUtil.parseStringUrlParamters(mi[i].getUrl());
				if (ht == null) {
					if (!isFinded) {
						if (log.isDebugEnabled()) {
							log.debug("定义的模块没有带参数");
						}
						nFitIndex = i;
						isFinded = true;
					}
				} else {
					
					if (htReqParm == null)
						continue;
					
					Enumeration enumkeys = ht.keys();
					boolean isFit = true;
					while (enumkeys.hasMoreElements()) {
						String element = (String) enumkeys.nextElement();
						Object o = htReqParm.get(element);
						if (o==null) {
							isFit = false;
							break;
						}
						if (!((String)ht.get(element)).equals((String)o) && !((String)ht.get(element)).equals(URLEncoder.encode((String)o))) {
							isFit = false;
							break;
						}
					}
					if (isFit) {
						if (ht.size() > nParamNumber) {
							nParamNumber = ht.size();
							isFinded = true;
							nFitIndex = i;
						}
					} 
				}
			}
			if (!isFinded) {
				return null;
			} else {
				if (log.isDebugEnabled()) {
					log.debug("找到了访问的URL模块：" + mi[nFitIndex].getUrl());
				}
				return mi[nFitIndex];
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
		
	}

}

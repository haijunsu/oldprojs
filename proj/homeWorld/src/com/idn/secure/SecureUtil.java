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
 * <P>У�����Ȩ��</P>
 * 
 * @version 0.1
 * @author navy
 */
public class SecureUtil {
	
	/**
	 * ���� commons-logging API ��������־��¼
	 */
	private static org.apache.commons.logging.Log log =
		org.apache.commons.logging.LogFactory.getLog(SecureUtil.class);

	/**
	 * ���캯�� 
	 */
	public SecureUtil() {
		super();
	}

	/**
	 * �ж���û��Ȩ��ִ��ָ���Ĳ˵�
	 * @param mi �˵���
	 * @param strPurview �û�Ȩ��
	 * @return �Ƿ���Ȩ��
	 */
	public static boolean isPermited(MenuItem mi, String strPurview) {
			return CommonTools.isMenuShow(strPurview, mi.getPurview(), PropertyManager.getBooleanProperty("menu.encryption"));
	}
	
	/**
	 * ������menuall����Ķ�Ӧģ���Ȩ��
	 * @param request request����
	 * @return ��Ӧ��ģ��
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
				//��������ʲ˵�
				if (log.isDebugEnabled()) {
					log.debug("�ҵ��ĵ�" + (i + 1) + "һ���˵���" + mi[i].getMenuId() + "/" + mi[i].getUrl());
				}
				Hashtable ht = MenusUtil.parseStringUrlParamters(mi[i].getUrl());
				if (ht == null) {
					if (!isFinded) {
						if (log.isDebugEnabled()) {
							log.debug("�����ģ��û�д�����");
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
					log.debug("�ҵ��˷��ʵ�URLģ�飺" + mi[nFitIndex].getUrl());
				}
				return mi[nFitIndex];
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
		
	}

}

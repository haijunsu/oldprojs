/*
 * @(#)ServletTools.java  2003-4-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.util;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

/**
 * <P>Servlet��һЩ���� </P>
 * 
 * @version 0.1
 * @author �պ���
 */
public class ServletTools {

	/**
	 * ˽�й��캯�������಻���Ա�ʵ����
	 */
	private ServletTools() {
	}

	/**
	 * ��ȡHttpServletRequest����ֵ
	 * @param req  HttpServletRequest
	 * @param param Ҫ��ȡ����������
	 * @param isRequired �ǲ���Ϊ�������
	 * @param defaultValue �������Ϊ��ѡ����������Ҳ������򷵻ش�ֵ
	 * @return ����ֵ
	 * @throws MissingParameterException ������Ϊ���裬���Ҳ���ʱ���׳��˴���
	 */
	public static String getParameter(
		HttpServletRequest req,
		String param,
		boolean isRequired,
		String defaultValue)
		throws MissingParameterException {
		String strParam = req.getParameter(param);
		if (strParam == null && isRequired) {
			throw new MissingParameterException("������" + param + "��û���ҵ���");
		}
		if (strParam == null)
			return defaultValue;
		return strParam;
	}

	/**
	 * ��ȡHttpServletRequest����ֵ
	 * @param req  HttpServletRequest
	 * @param param Ҫ��ȡ����������
	 * @param isRequired �ǲ���Ϊ�������
	 * @param defaultValue �������Ϊ��ѡ����������Ҳ������򷵻ش�ֵ
	 * @return ����ֵ
	 * @throws MissingParameterException ������Ϊ���裬���Ҳ���ʱ���׳��˴���
	 */
	public static String[] getParameterValues(
		HttpServletRequest req,
		String param,
		boolean isRequired,
		String[] defaultValue)
		throws MissingParameterException {
		String[] strParam = req.getParameterValues(param);
		if (strParam == null && isRequired) {
			throw new MissingParameterException("������" + param + "��û���ҵ���");
		}
		if (strParam == null)
			return defaultValue;
		return strParam;
	}

	/**
	 * ��HttpServletRequest�еĲ����ŵ���ϣ����
	 * @param req HttpServletRequest
	 * @return ������ϣ��
	 * @throws MissingParameterException
	 */
	public static Hashtable RequestParametersToHashtable(HttpServletRequest req)
		throws MissingParameterException {
		Hashtable htParams = new Hashtable();
		Enumeration enumParamNames = req.getParameterNames();

		String strName = null;
		while (enumParamNames.hasMoreElements()) {
			strName = (String) enumParamNames.nextElement();
			htParams.put(
				strName,
				ServletTools.getParameter(req, strName, true, ""));
		}
		return htParams;
	}

}

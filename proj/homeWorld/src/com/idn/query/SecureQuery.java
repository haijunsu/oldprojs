/*
 * @(#)SecureQuery.java  2003-5-7
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.idn.secure.SecureAction;

/**
 * <P>ִ�а�ȫ��ѯ��ͨ����¼����ܲ�ѯ </P>
 * 
 * @version 0.1
 * @author navy
 */
public class SecureQuery extends SecureAction {

	/* (non-Javadoc)
	 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecurely(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {
		
		QueryAction query = new QueryAction();
		return query.execute(mapping, form, request, response);
		
	}

}

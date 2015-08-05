/**
 * @(#)CommRequest.java  2003-11-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.util;

import javax.servlet.http.*;

/**
 * <P>����ȡRequest�����Ϣ������</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommRequest {

	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommRequest.class);
	
	/**
	* Constructor
	*/
	public CommRequest() {
		super();

	}
	/**
	 * ��request��ȡ���������
	 * 
	 * @param request HttpServletRequest �����request
	 * @param s_arg string ����ID
	 * @return stirng[] ����������
	 */	
	public String[] getParameterValues(HttpServletRequest request,String s_arg){
		String s_return[];
		try {
			s_return = request.getParameterValues(s_arg);
		} catch (Exception e){
			e.printStackTrace();
			s_return = null;
			log.debug("CommRequest:From reuqest get parameter arrary is failure");
		}
		return s_return;
	}	
		
	/**
	 * ��request��ȡ����������
	 * 
	 * @param request HttpServletRequest �����request
	 * @param s_arg string ����ID
	 * @return stirng ����������
	 */	
	public String getParameter(HttpServletRequest request,String s_arg){
		String s_return;
		try {
			s_return = request.getParameter(s_arg);
			if (s_return == null) {
				s_return = "";
			}
		} catch (Exception e){
			e.printStackTrace();
			s_return = "";
			log.debug("CommRequest:From reuqest get parameter is failure");
		}
		return s_return;
	}	

	
}
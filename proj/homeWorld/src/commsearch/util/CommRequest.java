/**
 * @(#)CommRequest.java  2003-11-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.util;

import javax.servlet.http.*;

/**
 * <P>公用取Request相关信息工具类</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommRequest {

	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommRequest.class);
	
	/**
	* Constructor
	*/
	public CommRequest() {
		super();

	}
	/**
	 * 在request中取出数组参数
	 * 
	 * @param request HttpServletRequest 传入的request
	 * @param s_arg string 参数ID
	 * @return stirng[] 参数的内容
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
	 * 在request中取出单个参数
	 * 
	 * @param request HttpServletRequest 传入的request
	 * @param s_arg string 参数ID
	 * @return stirng 参数的内容
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
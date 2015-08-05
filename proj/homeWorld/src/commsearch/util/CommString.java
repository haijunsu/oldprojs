/**
 * @(#)CommString.java  2003-11-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.util;

import java.util.*;
//import javax.servlet.http.*;

//import com.idn.sql.DataBean;
//import javax.servlet.ServletException;
//import javax.servlet.http.*;

//import org.apache.struts.action.*;
//import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;
/**
 * <P>公用字符相关工具类</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommString {

	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommString.class);
	
	String divsign;         //字符串的分割符
	/**
	* Constructor
	*/
	public CommString() {
		super();
		setDivsign(",");
	}
	/**
	 * <P>在字符串中替换<P>
	 * @param String 要处理的字符串
	 * @param String 要被替换的原内容
	 * @param String 要替换的内容 
	 * @return String double型的字符串
	 */	
	public String replaceAll(String s_thing,String s_old,String s_new){
		int i_indexof;
		if (s_old.equals(s_new)){
			//替换与被替换的相同
			return s_thing;
		}
		i_indexof = s_thing.indexOf(s_old);
		while (i_indexof != -1) {
			s_thing = s_thing.substring(0, i_indexof) + s_new 
					+ s_thing.substring(i_indexof + s_old.length());
			i_indexof = s_thing.indexOf(s_old);
		}
		return s_thing;
	}
	/**
	 * 将字符串按指定的长度作左截
	 * 
	 * @param String 字符串
	 * @param int    左截的长度
	 * @return
	 */
	public String Left(String s_data,int i_left){
		String s_return = "";
		if (i_left>s_data.length()){
			i_left = s_data.length();
		}
		s_return = s_data.substring(0,i_left);
		return s_return;
	}
	/**
	 * 将字符串按指定的长度作右截
	 * 
	 * @param String 字符串
	 * @param int    右截的长度
	 * @return
	 */
	public String Right(String s_data,int i_right){
		String s_return = "";
		if (i_right>s_data.length()) {
			i_right = s_data.length();
		}
		s_return = s_data.substring(s_data.length() - i_right,s_data.length());
		return s_return;
	}

	/**
	 * 将字符串转换为HTML
	 * 
	 * @param str
	 * @return
	 */
	public String stringToHtml(String str) {
		String s_return = "";
		s_return = com.idn.util.HtmlFilter.stringToHtml(str);
		return s_return;
	}

	
	/**
	 * 按固定的分隔符,分隔字符串
	 * 
	 * @param string 原字符串
	 * @param string 分隔符
	 * @return stirng[][] 分隔的结果
	 */	
	public String[] DivString(String s_thing,String s_divsign){
		setDivsign(s_divsign);
		return DivString(s_thing);
	}
	/**
	 * 按固定的分隔符,分隔字符串(默认分隔符为",")
	 * 
	 * @param string 原字符串
	 * @return stirng[][] 分隔的结果
	 */	
	public String[] DivString(String s_thing){
		String[] s_return;
		String s_temp;
		int i_count;
		if (s_thing==null) return null;
		s_thing = s_thing.trim();
		if (s_thing.equals("")) return null;
		if (getDivsign().equals("")) {
			setDivsign(",");
		}
		StringTokenizer st = new StringTokenizer(s_thing,getDivsign());
		i_count = 0;

		i_count = st.countTokens();
		s_return = new String[i_count];
		i_count = 0;
		while (st.hasMoreTokens()){
			s_return[i_count] = st.nextToken();
//			log.debug(s_return[i_count]);
			i_count ++;
		}
		return s_return;
	}

	/**
	 * @return
	 */
	public String getDivsign() {
		return this.divsign;
	}

	/**
	 * @param string
	 */
	public void setDivsign(String string) {
		this.divsign = string;
	}
	
}
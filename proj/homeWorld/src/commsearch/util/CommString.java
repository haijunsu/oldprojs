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
 * <P>�����ַ���ع�����</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommString {

	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommString.class);
	
	String divsign;         //�ַ����ķָ��
	/**
	* Constructor
	*/
	public CommString() {
		super();
		setDivsign(",");
	}
	/**
	 * <P>���ַ������滻<P>
	 * @param String Ҫ������ַ���
	 * @param String Ҫ���滻��ԭ����
	 * @param String Ҫ�滻������ 
	 * @return String double�͵��ַ���
	 */	
	public String replaceAll(String s_thing,String s_old,String s_new){
		int i_indexof;
		if (s_old.equals(s_new)){
			//�滻�뱻�滻����ͬ
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
	 * ���ַ�����ָ���ĳ��������
	 * 
	 * @param String �ַ���
	 * @param int    ��صĳ���
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
	 * ���ַ�����ָ���ĳ������ҽ�
	 * 
	 * @param String �ַ���
	 * @param int    �ҽصĳ���
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
	 * ���ַ���ת��ΪHTML
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
	 * ���̶��ķָ���,�ָ��ַ���
	 * 
	 * @param string ԭ�ַ���
	 * @param string �ָ���
	 * @return stirng[][] �ָ��Ľ��
	 */	
	public String[] DivString(String s_thing,String s_divsign){
		setDivsign(s_divsign);
		return DivString(s_thing);
	}
	/**
	 * ���̶��ķָ���,�ָ��ַ���(Ĭ�Ϸָ���Ϊ",")
	 * 
	 * @param string ԭ�ַ���
	 * @return stirng[][] �ָ��Ľ��
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
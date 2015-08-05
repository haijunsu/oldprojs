/**
 * @(#)CommTools.java  2003-7-09
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch;

import java.util.*;
import javax.servlet.http.*;


//import com.idn.sql.DataBean;
//import javax.servlet.ServletException;
//import javax.servlet.http.*;

//import org.apache.struts.action.*;
//import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;
/**
 * <P>���ù�����</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommTools {

	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommTools.class);
	
	String divsign;
	String[] condition = null;        //������ID
			
	String[] conditionch = null;      //����������
	String[] relation = null;         //��ϵ��ID
	String[] relationch = null;       //��ϵ������			
	
	
	/**
	* Constructor
	*/
	public CommTools() {
		super();
		setDivsign("");
	}
	/**
	 * ȡ����ӦUSERID������
	 * 
	 * @param userid
	 * @return
	 */
	public String getUserName(String userid) {
		String s_return = "";
		commsearch.util.CommUsers cu = new commsearch.util.CommUsers();
		s_return = cu.getUserName(userid);
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
	 * ȡ����ǰʱ��
	 * 
	 * @param String ʱ��ĸ�ʽ
	 * @return String ���
	 */	
	public String getNow(String s_format){
		String s_now = "";
		commsearch.util.CommDate cd = new commsearch.util.CommDate();
		s_now = cd.getNow(s_format);
		return s_now;
	}
	/**
	 * �ж��ǲ���������
	 * 
	 * @param String �ַ�������
	 * @return int   1���ǣ�-1������
	 */	
	public int isNumber(String s_thing){
		int i_return = 1;
		double d_temp;
		try{
			d_temp = Double.parseDouble(s_thing);
			
		} catch (NumberFormatException e){
			i_return = -1;
		}
		return i_return;
	}
	/**
	 * �������в�ѯ���
	 * 
	 * @param String[] ��ѯ����
	 * @param String ��ѯ����
	 * @return int   ��ѯ�����������е����
	 */	
	public int searchInArray(String[] s_arrary,String s_search){
		int i_return = -1;
		int i_fori;
		for (i_fori=0;i_fori<s_arrary.length;i_fori++){
			if (s_arrary[i_fori].trim().equals(s_search.trim())){
				i_return = i_fori;
				break;
			}
		}
		
		return i_return;
	}
	/**
	 * ����ǰ��һ��
	 * 
	 * @param String[] ԭ����
	 * @param String �µ�Ԫ��
	 * @return stirng[] �µ�����
	 */	
	public String[] addArray(String[] s_add,String s_new){
		String[] s_return = null;
		int i_fori;
		try {
			s_return = new String[s_add.length + 1];
			s_return[0] = s_new;
			for(i_fori=0;i_fori<s_add.length;i_fori++){
				s_return[i_fori + 1] = s_add[i_fori];
			}
		} catch (Exception e) {
			log.error("addArray:ERROR");
			e.printStackTrace();
			
			return s_add;
		}
		
		return s_return;
	}
	/**
	 * ��request��ȡ���������
	 * 
	 * @param HttpServletRequest request
	 * @param string ����ID
	 * @return stirng[] ����������
	 */	
	public String[] getParameterValues(HttpServletRequest request,String s_arg){
		String s_return[];
		try {
			s_return = request.getParameterValues(s_arg);
		} catch (Exception e){
			e.printStackTrace();
			s_return = null;
			log.debug("CommTools:��reuqest��ȡ��������ʧ��");
		}
		return s_return;
	}	
		
	/**
	 * ��request��ȡ����������
	 * 
	 * @param HttpServletRequest request
	 * @param string ����ID
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
			log.debug("CommTools:��reuqest��ȡ����ʧ��");
		}
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

	/**
	 * @return
	 */
	public String[] getCondition() {
		if (this.condition == null){
			this.condition = new String[8];
			this.condition[0] = "��ѯ�����б�";
			this.condition[1] = "=";
			this.condition[2] = "<>";
			this.condition[3] = ">";
			this.condition[4] = "<";
			this.condition[5] = ">=";
			this.condition[6] = "<=";
			this.condition[7] = "LIKE";
		}
		return this.condition;
	}

	/**
	 * @return
	 */
	public String[] getConditionch() {
		if (this.conditionch == null){
			this.conditionch = new String[8];
			this.conditionch[0] = "";
			this.conditionch[1] = "����";
			this.conditionch[2] = "������";
			this.conditionch[3] = "����";
			this.conditionch[4] = "С��";
			this.conditionch[5] = "���ڵ���";
			this.conditionch[6] = "С�ڵ���";
			this.conditionch[7] = "����";
		}
		return this.conditionch;
	}

	/**
	 * @return
	 */
	public String[] getRelation() {
		if (this.relation ==null){
			this.relation = new String[3];
			this.relation[0] = "��ѯ��ϵ�б�";
			this.relation[1] = "AND";
			this.relation[2] = "OR";
		}
		return this.relation;
	}

	/**
	 * @return
	 */
	public String[] getRelationch() {
		if (this.relationch ==null){
			this.relationch = new String[3];
			this.relationch[0] = "";
			this.relationch[1] = "��";
			this.relationch[2] = "��";
		}
		return this.relationch;
	}

	/**
	 * @param strings
	 */
	public void setCondition(String[] strings) {
		this.condition = strings;
	}

	/**
	 * @param strings
	 */
	public void setConditionch(String[] strings) {
		this.conditionch = strings;
	}

	/**
	 * @param strings
	 */
	public void setRelation(String[] strings) {
		this.relation = strings;
	}

	/**
	 * @param strings
	 */
	public void setRelationch(String[] strings) {
		this.relationch = strings;
	}
	
	
}

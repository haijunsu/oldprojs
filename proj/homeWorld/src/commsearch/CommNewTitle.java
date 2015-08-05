/**
 * @(#)CommNewTitle.java  2003-9-14
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch;

//import java.util.*;
//import javax.servlet.ServletException;
import javax.servlet.http.*;
//import javax.servlet.http.*;
//import javax.servlet.ServletException;
//import javax.servlet.http.*;

//import org.apache.struts.action.*;
//import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;
/**
 * <P>�����µ���ͷ</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommNewTitle {

	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommNewTitle.class);

	/**
	* Constructor
	* @param String ��ѯID
	*/
	public CommNewTitle() {
		super();
	}


	/**
	 * ���������newTitle
	 * 
		 * @param HttpServletRequest request
		 * @return stirng
	 */	
	public String makeEndTitle(String s_queryid,String[][] s_data){
		
		StringBuffer s_return= new StringBuffer("");
		if (s_data==null) return "";
		if (s_data.length==0) return "";
		
		commsearch.util.CommArrary ca = new commsearch.util.CommArrary();
		String s_endreturn[][];


		if (s_queryid.equals("DS_ZJZBMONTHCOUNT")){
			s_endreturn = null;
			s_endreturn = ca.makeArrayCalculate(s_data,1,2,"allsum");
			if (s_endreturn!=null){
				s_return.append("�ϼ�װ��:" + s_endreturn[0][1] + "̨" + ";");				
			}
			s_endreturn = null;
			s_endreturn = ca.makeArrayCalculate(s_data,0,2,"sum");
			for (int i_fori=0;i_fori<s_endreturn.length;i_fori++){
				s_return .append( ";"+s_endreturn[i_fori][0] + "��:" +  s_endreturn[i_fori][1] + "̨");
			}
		}
		if (s_queryid.equals("DS_XGDJZBMONTHCOUNT")){
			s_endreturn = null;
			s_endreturn = ca.makeArrayCalculate(s_data,1,2,"allsum");
			s_return .append("�ϼ���:" +  s_endreturn[0][1] + "̨" + "<BR>");
			s_endreturn = null;
			s_endreturn = ca.makeArrayCalculate(s_data,1,2,"sum");
			for (int i_fori=0;i_fori<s_endreturn.length;i_fori++){
				s_return .append( "" + s_endreturn[i_fori][0] + "��:" +  s_endreturn[i_fori][1] + "̨" + "<BR>");
			}
		}

		return s_return.toString();
	}


	/**
	 * ���������newTitle
	 * 
		 * @param HttpServletRequest request
		 * @return stirng
	 */	
	public String makeBeginTitle(String s_queryid,HttpServletRequest request){
		
		
		CommTools ct = new CommTools();    //���ò�ѯ������
		StringBuffer bs_title = new StringBuffer();
		String s_fields1id = null;     //��ѯ�ֶ�1
		String s_condition1id = null;  //��ѯ����1
		String s_thing1 = null;      //��ѯ������1
	
		String s_fields2id = null;     //��ѯ�ֶ�2  
		String s_condition2id = null;  //��ѯ����2
		String s_thing2 = null;      //��ѯ������2
	
		String s_fields3id = null;     //��ѯ�ֶ�3  
		String s_condition3id = null;  //��ѯ����3  
		String s_thing3 = null;      //��ѯ������3
	
		String s_relation1id = null;    //��ѯ��ϵ1
		String s_relation2id = null;    //��ѯ��ϵ2
		
		//���������
		s_fields1id = ct.getParameter(request,"fields1show");
		s_condition1id = ct.getParameter(request,"condition1show");
		s_thing1 = ct.getParameter(request,"thing1");
		s_fields2id = ct.getParameter(request,"fields2show");
		s_condition2id = ct.getParameter(request,"condition2show");
		s_thing2 = ct.getParameter(request,"thing2");
		s_fields3id = ct.getParameter(request,"fields3show");
		s_condition3id = ct.getParameter(request,"condition3show");
		s_thing3 = ct.getParameter(request,"thing3");
			
		s_relation1id = ct.getParameter(request,"relation1show");
		s_relation2id = ct.getParameter(request,"relation2show");		
		bs_title = bs_title.append("");
		if (s_queryid.equals("DS_ZJZBMONTHCOUNT") || s_queryid.equals("DS_XGDJZBMONTHCOUNT")){
			if (!s_thing1.equals("")){
				bs_title.append(s_fields1id +":"+ s_thing1);
				if (!s_thing2.equals("") || !s_thing3.equals("") ){
					bs_title.append("��");
				}
			}
			if (!s_thing2.equals("") && !s_thing3.equals("") ){
				//��,ֹ����
				bs_title.append("" + s_fields2id + ":");
				bs_title.append(s_thing2);
				bs_title.append("��");
				bs_title.append(s_thing3);
			}
			if (!s_thing2.equals("") && s_thing3.equals("") ){
				//����
				bs_title.append("" + s_fields2id + ":");
				bs_title.append(s_thing2 + "����");
			}
			if (s_thing2.equals("") && !s_thing3.equals("") ){
				//ֹ��
				bs_title.append("" + s_fields3id + ":");
				bs_title.append(s_thing3 + "��ǰ");
			}
			
		}
		return bs_title.toString();
	}

}

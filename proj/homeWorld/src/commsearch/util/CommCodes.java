/**
 * @(#)CommCodes.java  2003-7-29
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.util;

import java.util.*;
//import javax.servlet.ServletException;
//import javax.servlet.http.*;

//import org.apache.struts.action.*;
//import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;
/**
 * <P>���ô���˵���������</P>
 * 
 * ����init()��������������������
 * 
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommCodes {

	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommCodes.class);
	
	  
	
	private String CODES_SELECT="SELECT CCLASS,CCODE,CSHOWC,CSHOWE FROM CODES";
	
	private String codeselect;             //������SQL���
	private String codelist;               //�����õ��Ĵ����б�(��","�ָ�)
	private Map codesmap = new TreeMap();  //����������(MAP)
	private String[][] codes = null;       //����������(����)
	

	/**
	* Constructor(�Զ������ʼ��)
	*/
	public CommCodes() {
		super();
		setCodeselect(this.CODES_SELECT);
		setCodelist("");
	}
	/**
	* Constructor(�Զ������ʼ��)
	* @param String �����õ��Ĵ����б�
	*/
	public CommCodes(String s_codelist) {
		super();
		setCodeselect(this.CODES_SELECT);
		init(s_codelist);
	}
	/**
	 * �����ݿ���ȡ��������
	 * 
	 * @param ����ȡ�õĴ����б�(�Զ���(,)�ָ�)
	 * @return 
	 */	
	public void init(String s_codelist){
		setCodelist(s_codelist);
		init();
	}	
	/**
	 * �����ݿ���ȡ��������
	 * 
	 * @param 
	 * @return 
	 */	
	public void init(){
		String[][] s_codes;
		String[] s_codewhere=null;
		String s_codeselect="";
		String s_where = "";
		int i_fori,i_forj;
		commsearch.CommSQL cs = new commsearch.CommSQL();    //���ݿ����
		if (getCodelist().trim().equals("")){
			//������,Ҫȫ��������
			s_codes = cs.executeSelect(getCodeselect());
		} else {
			//Ҫ���ֵ�����
			
			//CommTools ct = new CommTools();
			CommString ct = new CommString();
			s_codewhere=ct.DivString(getCodelist());
			s_where = " WHERE";
			for(i_fori=0;i_fori<s_codewhere.length;i_fori++){
				s_where = s_where + " CCLASS = '" + s_codewhere[i_fori] + "' OR";
			}
			s_where = s_where.substring(0,s_where.length() - 3);
			log.debug("������WHERE���:" + s_where);
			s_codes = cs.executeSelect(getCodeselect() + s_where);
		}
		if (s_codes!=null){
			Map m_temp = new TreeMap();
			for(i_fori=0;i_fori<s_codes.length;i_fori++){
				m_temp.put(s_codes[i_fori][0].trim() + s_codes[i_fori][1].trim() ,s_codes[i_fori][2]);
			}
			setCodes(s_codes);
			setCodesmap(m_temp);

		} else {
			setCodes(null);
			setCodesmap(null);
		}
	}	
	
	/**
	 * ��������/һ�������,ȫ��ȡ������˵��
	 * 
	 * @param String[] һ����Ĵ���
	 * @param String ����CCLASS 
	 * @return String ����˵��
	 */	
	public String[] getCodesShow(String[] s_data,String s_cclass){
		String[] s_return=null;
		int i_fori;
		if (s_data==null) return null;
		s_return = new String[s_data.length];
		for (i_fori=0;i_fori<s_data.length;i_fori++){
			s_return[i_fori] = getCodesShow(s_cclass,s_data[i_fori]);
		}
		
		return s_return;
	}

		
	/**
	 * ��������,ȡ������˵��
	 * 
	 * @param String ����CCLASS + ����CCODE
	 * @return String ����˵��
	 */	
	public String getCodesShow(String s_cclassccode){
		String s_return="";
		if (this.getCodesmap() ==null) return "";
		try {
			s_return = (String) getCodesmap().get(s_cclassccode);
			if (s_return==null) s_return = "";
		} catch (Exception e){
			s_return="";
			e.printStackTrace();
			log.debug("���ô���˵��������� :��������,ȡ������˵��ʧ��");
		}
		return s_return;
	}
	/**
	 * ��������,ȡ������˵��
	 * 
	 * @param String ����CCLASS
	 * @param String ����CCODE
	 * @return String ����˵��
	 */	
	public String getCodesShow(String s_cclass,String s_ccode){
		String s_return="";
		s_return = getCodesShow(s_cclass.trim()+s_ccode.trim());
		return s_return;
	}
	
	

	/**
	 * @return
	 */
	public String getCodelist() {
		return this.codelist;
	}

	/**
	 * @return
	 */
	public String[][] getCodes() {
		return this.codes;
	}

	/**
	 * @return
	 */
	public String getCodeselect() {
		return this.codeselect;
	}

	/**
	 * @return
	 */
	public Map getCodesmap() {
		return this.codesmap;
	}

	/**
	 * @param string
	 */
	public void setCodelist(String string) {
		this.codelist = string;
	}

	/**
	 * @param strings
	 */
	public void setCodes(String[][] strings) {
		this.codes = strings;
	}

	/**
	 * @param string
	 */
	public void setCodeselect(String string) {
		this.codeselect = string;
	}

	/**
	 * @param map
	 */
	public void setCodesmap(Map map) {
		this.codesmap = map;
	}

}

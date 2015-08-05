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
 * <P>公用代码说明表管理类</P>
 * 
 * 调用init()来处理本次所需代码的种类
 * 
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommCodes {

	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommCodes.class);
	
	  
	
	private String CODES_SELECT="SELECT CCLASS,CCODE,CSHOWC,CSHOWE FROM CODES";
	
	private String codeselect;             //代码表的SQL语句
	private String codelist;               //本次用到的代码列表(用","分隔)
	private Map codesmap = new TreeMap();  //代码表的内容(MAP)
	private String[][] codes = null;       //代码表的内容(数组)
	

	/**
	* Constructor(自动代码初始化)
	*/
	public CommCodes() {
		super();
		setCodeselect(this.CODES_SELECT);
		setCodelist("");
	}
	/**
	* Constructor(自动代码初始化)
	* @param String 本次用到的代码列表
	*/
	public CommCodes(String s_codelist) {
		super();
		setCodeselect(this.CODES_SELECT);
		init(s_codelist);
	}
	/**
	 * 从数据库中取出代码来
	 * 
	 * @param 本次取用的代码列表(以逗号(,)分割)
	 * @return 
	 */	
	public void init(String s_codelist){
		setCodelist(s_codelist);
		init();
	}	
	/**
	 * 从数据库中取出代码来
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
		commsearch.CommSQL cs = new commsearch.CommSQL();    //数据库操作
		if (getCodelist().trim().equals("")){
			//无条件,要全部的内容
			s_codes = cs.executeSelect(getCodeselect());
		} else {
			//要部分的内容
			
			//CommTools ct = new CommTools();
			CommString ct = new CommString();
			s_codewhere=ct.DivString(getCodelist());
			s_where = " WHERE";
			for(i_fori=0;i_fori<s_codewhere.length;i_fori++){
				s_where = s_where + " CCLASS = '" + s_codewhere[i_fori] + "' OR";
			}
			s_where = s_where.substring(0,s_where.length() - 3);
			log.debug("代码表的WHERE语句:" + s_where);
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
	 * 给出代码/一数组的数,全部取出代码说明
	 * 
	 * @param String[] 一数组的代码
	 * @param String 代码CCLASS 
	 * @return String 代码说明
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
	 * 给出代码,取出代码说明
	 * 
	 * @param String 代码CCLASS + 代码CCODE
	 * @return String 代码说明
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
			log.debug("公用代码说明表管理类 :给出代码,取出代码说明失败");
		}
		return s_return;
	}
	/**
	 * 给出代码,取出代码说明
	 * 
	 * @param String 代码CCLASS
	 * @param String 代码CCODE
	 * @return String 代码说明
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

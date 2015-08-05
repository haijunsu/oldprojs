/**
 * @(#)CommFields.java  2003-9-6
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch;

//import java.util.*;
/**
 * <P>字段的内容处理类</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommFields {

	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommFields.class);
	String[][] data;
	int rowcount,colcount;
	String searchfieldid="";
	String queryselect="";
	String[] fieldscol=null;
	/**
	* Constructor
	*/
	public CommFields() {
		super();
	}
	/**
	 * <P>三张表的列类处理，初始化<P>
	 * @param String 要查询的FIELDID的名字
	 * @return 1、正常；2、异常
	 */	
	public String init(String s_searchfieldid){
		setSearchfieldid(s_searchfieldid);
		return init();
	}
	/**
	 * <P>三张表的列类处理，初始化<P>
	 * @param 
	 * @return 1、正常；2、异常
	 */	
	public String init(){
		String[][] s_data;
		String[] s_fieldscol;
		String s_where="";
		StringBuffer sb_select = new StringBuffer("");
		String s_return="1";
		CommSQL cs = new CommSQL();

		if (!getSearchfieldid().trim().equals("")){
			s_where = " WHERE FIELDID='" + getSearchfieldid() + "' ";
		}
		setFieldscol();
		s_fieldscol = getFieldscol();
		sb_select = sb_select.append("SELECT ");
		sb_select = sb_select.append(s_fieldscol[0]);
		for (int i_fori=1;i_fori<s_fieldscol.length;i_fori++){
			sb_select = sb_select.append(" , " + s_fieldscol[i_fori]);
		}
		sb_select = sb_select.append(" FROM FIELDS ");
		sb_select = sb_select.append(s_where);
		sb_select = sb_select.append(" ORDER BY FIELDS.FIELDID");
		log.debug("CommFields.init:SQL语句是"+sb_select.toString());
		setQueryselect(sb_select.toString());
		s_data = cs.executeSelect(getQueryselect());
		if (s_data==null) return "-1";
		if (s_data.length==0) return "-1";
		log.debug(s_data.length);
		setData(s_data);	
		setRowcount(s_data.length);
		setColcount(s_data[0].length);
		return s_return;
	}
	/**
	 * <P>取指定行，列的FIELDS表的内容<P>
	 * @param int 指定的行
	 * @param String 指定的FIELDS表的列名
	 * @return FIELDS表的指定行、列的内容
	 */	
	public String getData(int i_row,String s_col){
		String[] s_fieldscol;
		s_fieldscol = getFieldscol();
		for (int i_fori=0;i_fori<s_fieldscol.length;i_fori++){
			if (s_fieldscol[i_fori].equals(s_col.trim())){
				return this.data[i_row][i_fori];
			}
		}
		return "";
	}
	/**
	 * <P>取指定列的FIELDS表的内容<P>
	 * @param String 指定的FIELDS表的列名
	 * @return FIELDS表的指定列的内容
	 */	
	public String[] getData(String s_col){
		String[] s_fieldscol;
		String[] s_return = null;
		s_fieldscol = getFieldscol();
		for (int i_fori=0;i_fori<s_fieldscol.length;i_fori++){
			if (s_fieldscol[i_fori].equals(s_col.trim())){
				s_return = new String[getRowcount()];
				for (int i_forj=0;i_forj<getRowcount();i_forj++){
					s_return[i_forj] = this.data[i_forj][i_fori];
				}
				return s_return;
			}
		}
		return null;
	}
	/**
	 * <P>设定SELECT语句</P>
	 * @param
	 * @return
	 */
	public void setFieldscol() {
		String[] s_a = new String[7];
		s_a[0]="FIELDID";
		s_a[1]="FTYPE";
		s_a[2]="FLENGTH";
		s_a[3]="FDIGITS";
		s_a[4]="FHEADERC";
		s_a[5]="FHEADERE";
		s_a[6]="CCLASS";
		setFieldscol(s_a);
	}

	/**
	 * @return
	 */
	public String[][] getData() {
		return this.data;
	}

	/**
	 * @param strings
	 */
	public void setData(String[][] strings) {
		this.data = strings;
	}

	/**
	 * @return
	 */
	public String getSearchfieldid() {
		return this.searchfieldid;
	}

	/**
	 * @param string
	 */
	public void setSearchfieldid(String string) {
		this.searchfieldid = string;
	}
	/**
	 * @return
	 */
	public String getQueryselect() {
		return this.queryselect;
	}

	/**
	 * @param string
	 */
	public void setQueryselect(String string) {
		this.queryselect = string;
	}
	/**
	 * @return
	 */
	public String[] getFieldscol() {
		return this.fieldscol;
	}

	/**
	 * @param strings
	 */
	public void setFieldscol(String[] strings) {
		this.fieldscol = strings;
	}

	/**
	 * @return
	 */
	public int getColcount() {
		return this.colcount;
	}

	/**
	 * @return
	 */
	public int getRowcount() {
		return this.rowcount;
	}

	/**
	 * @param i
	 */
	public void setColcount(int i) {
		this.colcount = i;
	}

	/**
	 * @param i
	 */
	public void setRowcount(int i) {
		this.rowcount = i;
	}

}

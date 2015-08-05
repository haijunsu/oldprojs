/**
 * @(#)CommQminutia.java  2003-9-6
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch;

//import java.util.*;
/**
 * <P>字段的属性处理类</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommQminutia {

	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommQminutia.class);

	String[][] data;
	int rowcount,colcount;
	String searchfieldid="";
	String queryselect="";
	String[] fieldscol=null;
	/**
	* Constructor
	*/
	public CommQminutia() {
		super();
	}
	/**
	 * <P>三张表的属性类处理，初始化<P>
	 * @param String 要查询的FIELDID的名字
	 * @return 1、正常；2、异常
	 */	
	public String init(String s_searchfieldid){
		setSearchfieldid(s_searchfieldid);
		return init();
	}
	/**
	 * <P>三张表的属性类处理，初始化<P>
	 * @param 
	 * @return 1、正常；2、异常
	 */	
	public String init(){

		CommSQL cs = new CommSQL();
		
		String[][] s_data;
		String[] s_fieldscol;
		String s_where="";
		StringBuffer sb_select = new StringBuffer("");
		String s_return="";
		if (getSearchfieldid().trim().equals("")){
			return "-1";
		}
		s_where = " WHERE QMINUTIA.FIELDID='" + getSearchfieldid() + "' ";
		setFieldscol();
		s_fieldscol = getFieldscol();
		sb_select = sb_select.append("SELECT ");
		sb_select = sb_select.append(s_fieldscol[0]);
		for (int i_fori=1;i_fori<s_fieldscol.length;i_fori++){
			sb_select = sb_select.append(" , " + s_fieldscol[i_fori]);
		}
		sb_select = sb_select.append(" FROM QUERYS,QMINUTIA,FIELDS ");
		sb_select = sb_select.append(s_where);
		sb_select = sb_select.append(" AND RTRIM(QWHERE)='TABLE' AND QUERYS.QUERYID = QMINUTIA.QUERYID AND QMINUTIA.FIELDID=FIELDS.FIELDID ");
		sb_select = sb_select.append(" ORDER BY QUERYS.QUERYID");
		log.debug("CommQminutia.init:SQL语句是"+sb_select.toString());
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
	 * <P>取指定行，列的三张表的内容<P>
	 * @param int 指定的行
	 * @param String 指定的三张表的列名
	 * @return 三张表的指定行、列的内容
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
	 * <P>取指定列的三张表的内容<P>
	 * @param String 指定的三张表的列名
	 * @return 三张表的指定行、列的内容
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
	 * 
	 */
	public void setFieldscol() {
		String[] s_a = new String[20];
		s_a[ 0]="QUERYS.QUERYID";
		s_a[ 1]="QNAMEC";
		s_a[ 2]="QNAMEE";
		s_a[ 3]="QTABLE";
		s_a[ 4]="QWHERE";
		s_a[ 5]="QGROUP";
		s_a[ 6]="QORDER";
		s_a[ 7]="QFILE";
		s_a[ 8]="SEQ";
		s_a[ 9]="QFIELDID";
		s_a[10]="QMINUTIA.FIELDID";
		s_a[11]="QHEADERC";
		s_a[12]="QHEADERE";
		s_a[13]="QDEFAULT";
		s_a[14]="FTYPE";
		s_a[15]="FLENGTH";
		s_a[16]="FDIGITS";
		s_a[17]="FHEADERC";
		s_a[18]="FHEADERE";
		s_a[19]="CCLASS";		
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

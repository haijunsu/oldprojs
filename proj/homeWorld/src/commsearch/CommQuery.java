/**
 * @(#)CommQuery.java  2003-7-25
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch;

import java.util.*;

public class CommQuery {

	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommQuery.class);
	
	private CommSQL cs = new CommSQL();     
	private String queryid;               
	private String queryselect;           
	private String[][] querys = null;     
	private int queryrow;                 
	private int querycol;                 
	private String[] fields=null;         
	private String[] fieldstype=null;     
	private String[] fieldsch=null;       
	private String[] fieldslen=null;      
	private String[] fieldskey=null;      
	private String fieldscodelist = "";   
	private Map sumtype = new TreeMap();  
	public CommQuery(String s_queryid) {
		super();
		if (s_queryid == null){
			s_queryid = "";
		}
		setQueryid(s_queryid);
		
	}

	/**
	 * @param string
	 */
	public void setFieldscode() {
		String s_fieldscodelist;
		String[] s_codelist;
		int i_fori;
		s_fieldscodelist = "";
		s_codelist = getFieldscol("CCLASS");
		for(i_fori=0;i_fori<s_codelist.length;i_fori++){
			if (s_codelist[i_fori].trim().equals("no") || s_codelist[i_fori].trim().equals("")){
			} else {
				if (s_codelist[i_fori].trim().length()>2){
					if (s_codelist[i_fori].trim().length()>=8 ){
						if (s_codelist[i_fori].trim().substring(0,8).equals("DO_CODES")){
							s_codelist[i_fori] = s_codelist[i_fori].substring(8); 
						}
					}
				}
				s_fieldscodelist = s_fieldscodelist + s_codelist[i_fori].trim() + ","; 
			}
		}
		if (!s_fieldscodelist.equals("")) {
			s_fieldscodelist = s_fieldscodelist.substring(0,s_fieldscodelist.length() - 1);
		}

		setFieldscodelist(s_fieldscodelist);
	}

	public String[] getFieldscol(String s_colname) {
		String[] s_fieldscol;
		int i_row;
		if (getQueryrow()>0){
			s_colname = s_colname.toUpperCase();
			s_fieldscol = new String[getQueryrow()];
			for(i_row=0;i_row<getQueryrow();i_row++){
				s_fieldscol[i_row] = this.cs.getFieldsValue(i_row,s_colname);
			}
		} else {
			s_fieldscol=null;
		}
		return s_fieldscol;		
	}

	public void setFieldstype() {
		setFieldstype(getFieldscol("FTYPE"));
	}
	public void setFieldslen() {
		setFieldslen(getFieldscol("FLENGTH"));
	}
	
	public void setFields() {
		setFields(getFieldscol("QFIELDID"));
		String[] s_fields = null;
		int i_fori,i_forj;
		s_fields = getFields();
		for(i_fori=0;i_fori<s_fields.length;i_fori++){
			if (s_fields[i_fori].equals("")){
				s_fields[i_fori] = this.cs.getFieldsValue(i_fori,"FIELDID");
			}
		}
	}

	public void setFieldsch() {
		setFieldsch(getFieldscol("QHEADERC"));
	}

	public void setFieldskey() {
		String[] s_fieldskey;
		int i_row,i_count = 0;
		if (getQueryrow()>0){

			for(i_row=0;i_row<getQueryrow();i_row++){
				if (this.cs.getFieldsValue(i_row,"QKATTRI").equals("1"))
					i_count++;
			}
			s_fieldskey = new String[i_count];
			i_count = 0;
			for(i_row=0;i_row<getQueryrow();i_row++){
				if (this.cs.getFieldsValue(i_row,"QKATTRI").equals("1")){
					s_fieldskey[i_count] = this.cs.getFieldsValue(i_row,"QFIELDID");
					if (s_fieldskey[i_count].equals("")){
						s_fieldskey[i_count] = this.cs.getFieldsValue(i_row,"FIELDID");
					}
					i_count++;
				}
			}
			setFieldskey(s_fieldskey);
		} else {
			setFieldskey(null);
		}

	}
	
	
	public String init() {
		String s_return;
		s_return = "-1";
		String[][] s_querys;
		setQueryselect("SELECT QUERYS.QUERYID,QNAMEC,QNAMEE,QTABLE,QWHERE,QGROUP,QORDER,QFILE,SEQ,QFIELDID,QMINUTIA.FIELDID,QFATTRI,QSATTRI,QKATTRI,QSTATE,QHEADERC,QHEADERE,QDEFAULT,FTYPE,FLENGTH,FDIGITS,FHEADERC,FHEADERE,CCLASS FROM QUERYS,QMINUTIA,FIELDS WHERE QUERYS.QUERYID = QMINUTIA.QUERYID AND QMINUTIA.FIELDID=FIELDS.FIELDID AND QUERYS.QUERYID=? ORDER BY QUERYS.QUERYID,SEQ");
		s_querys = this.cs.executeSelect(getQueryselect(),getQueryid());
		if (s_querys == null){
			setQuerys(s_querys);
			setQueryrow(0);
			setQuerycol(0);
		} else{
			setQuerys(s_querys);
			setQueryrow(this.cs.getRowcount());
			setQuerycol(this.cs.getColcount());
			
			setFields();
			setFieldstype();
			setFieldslen();
			setFieldsch();
			setFieldskey();
			setFieldscode();
			s_return = "1";
		}
		return s_return;
	}
	public String getQuerysValue(int i_row,String s_col){
		String s_return ;
		s_return = this.cs.getFieldsValue(i_row,s_col);
		return s_return;
	}	
	
	/**
	 * @return
	 */
	public String getQueryid() {
		return this.queryid;
	}

	/**
	 * @param string
	 */
	public void setQueryid(String string) {
		this.queryid = string;
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
	public int getQuerycol() {
		return this.querycol;
	}

	/**
	 * @return
	 */
	public int getQueryrow() {
		return this.queryrow;
	}

	/**
	 * @return
	 */
	public String[][] getQuerys() {
		return this.querys;
	}

	/**
	 * @param i
	 */
	public void setQuerycol(int i) {
		this.querycol = i;
	}

	/**
	 * @param i
	 */
	public void setQueryrow(int i) {
		this.queryrow = i;
	}

	/**
	 * @param strings
	 */
	public void setQuerys(String[][] strings) {
		this.querys = strings;
	}

	/**
	 * @return
	 */
	public String[] getFields() {
		return this.fields;
	}

	/**
	 * @return
	 */
	public String[] getFieldsch() {
		return this.fieldsch;
	}

	/**
	 * @return
	 */
	public String[] getFieldskey() {
		return this.fieldskey;
	}

	/**
	 * @param strings
	 */
	public void setFields(String[] strings) {
		this.fields = strings;
	}

	/**
	 * @param strings
	 */
	public void setFieldsch(String[] strings) {
		this.fieldsch = strings;
	}

	/**
	 * @param strings
	 */
	public void setFieldskey(String[] strings) {
		this.fieldskey = strings;
	}

	/**
	 * @return
	 */
	public String[] getFieldstype() {
		return this.fieldstype;
	}

	/**
	 * @param strings
	 */
	public void setFieldstype(String[] strings) {
		this.fieldstype = strings;
	}

	/**
	 * @return
	 */
	public String getFieldscodelist() {
		return this.fieldscodelist;
	}

	/**
	 * @param string
	 */
	public void setFieldscodelist(String string) {
		this.fieldscodelist = string;
	}


	/**
	 * @return
	 */
	public Map getSumtype() {
		if (this.sumtype.isEmpty()){
			this.sumtype.put("01","SUM");
			this.sumtype.put("02","COUNT");
			this.sumtype.put("03","MAX");
			this.sumtype.put("04","MIN");
			this.sumtype.put("05","AVG");
		}
		return this.sumtype;
	}

	/**
	 * @param map
	 */
	public void setSumtype(Map map) {
		this.sumtype = map;
	}

	/**
	 * @return
	 */
	public String[] getFieldslen() {
		return this.fieldslen;
	}

	/**
	 * @param strings
	 */
	public void setFieldslen(String[] strings) {
		this.fieldslen = strings;
	}
	public void setClear(){
		this.cs = null;  
		this.querys = null;  
		this.fields=null;    
		this.fieldstype=null;
		this.fieldsch=null;  
		this.fieldslen=null; 
		this.fieldskey=null; 
		this.sumtype.clear();

	}
}

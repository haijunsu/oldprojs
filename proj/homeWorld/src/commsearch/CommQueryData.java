/**
 * @(#)CommQueryData.java  2003-7-29
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch;

import java.util.*;

import commsearch.util.CommString;


public class CommQueryData {

	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommQueryData.class);
	
	private CommQuery cq = null;           
	private String[][] data = null;        
	private String[][] datashow = null;    
	private String[] datatitle = null;     
	private String[] datakey = null;       
	private String actiondo = null;        
	
	/**
	* Constructor
	*/
	public CommQueryData(CommQuery cq_cq) {
		super();
	
		setCq(cq_cq);
		setActiondo("");
	}

	public void setDatashow() {
		String s_codelist = "";
		String[][] s_view = null;
		Map s_viewmap = new TreeMap();
		String[][] s_datashow = null;
		String[] s_cclass=null,s_qfattri = null;
		String[] s_ftype=null,s_flength=null,s_fdigits=null;
		String s_temp="";
		CommSQL cs = new CommSQL();
		commsearch.util.CommNumber cn = new commsearch.util.CommNumber();
		commsearch.util.CommDate   cd = new commsearch.util.CommDate();
		int i_fori,i_forj,i_fork;
		if (getData()==null){
			setDatashow(null);
			return;
		}
		s_codelist = getCq().getFieldscodelist();
		s_datashow = new String[getData().length][getData()[0].length];
		
		for (i_fori=0;i_fori<getData().length;i_fori++){
			System.arraycopy(getData()[i_fori],0,s_datashow[i_fori],0,getData()[0].length);
		}
		commsearch.util.CommCodes cc = new commsearch.util.CommCodes(s_codelist);
		s_cclass = getCq().getFieldscol("CCLASS");
		s_qfattri = getCq().getFieldscol("QFATTRI");
		for (i_fori=0;i_fori<s_cclass.length;i_fori++){
			if (s_cclass[i_fori].equals("")) {
				s_cclass[i_fori] = "no";
			}
			if (!s_cclass[i_fori].equals("no") && s_qfattri[i_fori].equals("0")){
				if (s_cclass[i_fori].trim().length()>2){
					if (s_cclass[i_fori].trim().length()>=8 ){
						if (s_cclass[i_fori].trim().substring(0,8).equals("DO_CODES")){
							s_cclass[i_fori] = s_cclass[i_fori].substring(8); 
						}
					}
				}
				if (cn.isNumber(s_cclass[i_fori])==1){
					for (i_forj=0;i_forj<s_datashow.length;i_forj++){
						s_temp = cc.getCodesShow(s_cclass[i_fori],s_datashow[i_forj][i_fori]);
						if (!s_temp.equals("")) s_datashow[i_forj][i_fori] = s_temp;
					}
				} else {
					s_view = cs.executeSelect("SELECT * FROM " + s_cclass[i_fori]);
					if (s_view != null){
						for (i_fork=0;i_fork<s_view.length;i_fork++){
							s_viewmap.put(s_view[i_fork][0],s_view[i_fork][1]);
						}
						for (i_forj=0;i_forj<s_datashow.length;i_forj++){
							try{
								s_temp = (String ) s_viewmap.get(s_datashow[i_forj][i_fori]); 
							}catch (Exception e){
									s_temp="";
							}
							if (s_temp == null){
								s_temp = "";
							}
							if (!s_temp.equals("")) {
								s_datashow[i_forj][i_fori] = s_temp;
							} 
						}	
					}
				}
			}
		}
		
		s_ftype = getCq().getFieldscol("FTYPE");
		s_flength = getCq().getFieldscol("FLENGTH");
		s_fdigits = getCq().getFieldscol("FDIGITS");
		
		
		for (i_fori=0;i_fori<s_ftype.length;i_fori++){
			if (s_ftype[i_fori].toUpperCase().equals("DOUBLE") ||
			s_ftype[i_fori].toUpperCase().equals("DECIMAL") ){
				if (s_fdigits[i_fori].toUpperCase().equals("0") ||
				s_fdigits[i_fori].toUpperCase().equals("")){
				} else {
					for (i_forj=0;i_forj<s_datashow.length;i_forj++){
						if (!s_datashow[i_forj][i_fori].trim().equals("")){
							s_datashow[i_forj][i_fori] = cn.doubleFormat(s_datashow[i_forj][i_fori]);
						}
						
					}
				}
			}

			if (s_ftype[i_fori].toUpperCase().equals("DATE")){
				for (i_forj=0;i_forj<s_datashow.length;i_forj++){
					if (!s_datashow[i_forj][i_fori].trim().equals("")){
						s_datashow[i_forj][i_fori] = cd.dateFormat(s_datashow[i_forj][i_fori],"L");
					}
				}
			}
			if (s_ftype[i_fori].toUpperCase().equals("TIME")){
				for (i_forj=0;i_forj<s_datashow.length;i_forj++){
					if (!s_datashow[i_forj][i_fori].trim().equals("")){
						s_datashow[i_forj][i_fori] = cd.timeFormat(s_datashow[i_forj][i_fori],"L");
					}
				}
			}
			
		}
		setDatashow(s_datashow);
		
		s_viewmap.clear();
	}


	public int makeSelectCount(String s_where){
		
		
		CommSQL cs = new CommSQL();        

		String s_select = "";
		int i_fori,i_forj,i_count=0;
		String[] s_fields = null;        
		String[] s_fieldskey = null;     
		String[] s_qstate = null;        
		String s_oldwhere = "";          
		String s_oldgroupby = "";        
		String s_oldorderby = "";        
		String s_oldtablename="";        
		Map statemap = null;

		s_fields = getCq().getFields();
		s_fieldskey = getCq().getFieldskey();
		s_qstate = getCq().getFieldscol("QSTATE");
		if (s_fields==null || s_fieldskey==null ) return 0;
		
		s_oldgroupby = getCq().getQuerysValue(0,"QGROUP");
		s_oldorderby = getCq().getQuerysValue(0,"QORDER");
		s_oldtablename = getCq().getQuerysValue(0,"QTABLE");
		if (s_oldtablename.equals("")) {
			s_oldtablename = getCq().getQuerysValue(0,"QUERYID");
		}
		
		if (s_oldgroupby.equals("")){
			s_select = "SELECT " + " COUNT(*) " + " FROM " + s_oldtablename;
			s_oldwhere = getCq().getQuerysValue(0,"QWHERE");
			if (s_oldwhere.equals("TABLE")) s_oldwhere = "";
			if (!s_oldwhere.equals("")){
				s_select = s_select + " WHERE ";
				s_select = s_select + " (" + s_oldwhere + ")";
			}
			if (!s_where.equals("")){
				if (s_oldwhere.equals("")){
					s_select = s_select + " WHERE ";
					s_select = s_select + s_where;
				} else{
					s_select = s_select + " AND " + s_where + ""; 
				}
			}
			String[][] s_temp = null;
			s_temp = cs.executeSelect(s_select);
			if (s_temp== null) {
				i_count = 0;
			} else {
				i_count = Integer.parseInt(s_temp[0][0]);
//				i_count = s_temp.length;
			}
			
		} else {
			statemap = getCq().getSumtype();
			for (i_fori=0;i_fori<s_fields.length;i_fori++){
				if (s_qstate[i_fori].equals("") || s_qstate[i_fori].equals("00")){
					s_select = s_select + s_fields[i_fori] + ",";
				} else {
					s_select = s_select + (String)statemap.get(s_qstate[i_fori]) + "(" + s_fields[i_fori] + "),";
				}
			}
			s_select = s_select.substring(0,s_select.length() - 1);
			s_select = "SELECT " + s_select + " FROM " + s_oldtablename;
			s_oldwhere = getCq().getQuerysValue(0,"QWHERE");
			if (s_oldwhere.equals("TABLE")) s_oldwhere = "";
			if (!s_oldwhere.equals("")){
				s_select = s_select + " WHERE ";
				s_select = s_select + " (" + s_oldwhere + ")";
			}
			if (!s_where.equals("")){
				if (s_oldwhere.equals("")){
					s_select = s_select + " WHERE ";
					s_select = s_select + s_where;
				} else{
					s_select = s_select + " AND " + s_where + ""; 
				}
				
			}
			if (!s_oldgroupby.equals("")){
				s_select = s_select + " GROUP BY " + s_oldgroupby;
			}
			if (s_oldorderby.trim().equals("")){
				s_select = s_select + " ORDER BY "; 
				for (i_fori=0;i_fori<s_fieldskey.length;i_fori++){
					s_select = s_select + s_fieldskey[i_fori] + ",";
				}
				s_select = s_select.substring(0,s_select.length() - 1);
			} else {
				s_select = s_select + " ORDER BY " + s_oldorderby;
			}
			String[][] s_temp = null;
			s_temp = cs.executeSelect(s_select);
			if (s_temp== null) {
				i_count = 0;
			} else {
				i_count = s_temp.length;
			}
		}
		return i_count;
	}



	public String makeSelectPage(String s_where,String s_order,String s_pageination){
		
		String s_select = "";
		int i_fori,i_forj;
		String[] s_fields = null;        
		String[] s_fieldskey = null;     
		String[] s_qstate = null;        
		String s_oldwhere = "";          
		String s_oldgroupby = "";        
		String s_oldorderby = "";        
		String s_oldtablename="";        
		
		String s_tempselect = "";        
		
		Map statemap = null;

						
		s_fields = getCq().getFields();
		s_fieldskey = getCq().getFieldskey();
		s_qstate = getCq().getFieldscol("QSTATE");
		if (s_fields==null || s_fieldskey==null ) return "-1";
		
		s_oldgroupby = getCq().getQuerysValue(0,"QGROUP");
		s_oldorderby = getCq().getQuerysValue(0,"QORDER");
		s_oldtablename = getCq().getQuerysValue(0,"QTABLE");
		if (s_oldtablename.equals("")) {
			s_oldtablename = getCq().getQuerysValue(0,"QUERYID");
		}
		statemap = getCq().getSumtype();
		for (i_fori=0;i_fori<s_fields.length;i_fori++){
			if (s_qstate[i_fori].equals("") || s_qstate[i_fori].equals("00")){
				s_select = s_select + s_fields[i_fori] + ",";
			} else {
				s_select = s_select + (String)statemap.get(s_qstate[i_fori]) + "(" + s_fields[i_fori] + "),";
			}
		}
		CommString cstr = new CommString();
		s_tempselect = cstr.Left(s_select,s_select.length() - 1);
		s_select = "SELECT " + s_select + "rownumber() over ";
		s_select = s_select + "(";
		if (s_order.equals("")){
			if (s_oldorderby.trim().equals("")){
				s_select = s_select + " ORDER BY "; 
				for (i_fori=0;i_fori<s_fieldskey.length;i_fori++){
					s_select = s_select + s_fieldskey[i_fori] + ",";
				}
				s_select = s_select.substring(0,s_select.length() - 1);
			} else {
				s_select = s_select + " ORDER BY " + s_oldorderby;
			}
		} else {
			s_select = s_select + " ORDER BY " + s_order;
		}
		s_select = s_select + ") as rowid ";
		s_select = s_select  + " FROM " + s_oldtablename;
		s_oldwhere = getCq().getQuerysValue(0,"QWHERE");
		if (s_oldwhere.equals("TABLE")) s_oldwhere = "";
		if (!s_oldwhere.equals("")){
			s_select = s_select + " WHERE ";
			s_select = s_select + " (" + s_oldwhere + ")";
		}
		if (!s_where.equals("")){
			if (s_oldwhere.equals("")){
				s_select = s_select + " WHERE ";
				s_select = s_select + s_where;
			} else{
				s_select = s_select + " AND " + s_where + ""; 
			}
			
		}
		if (!s_oldgroupby.equals("")){
			s_select = s_select + " GROUP BY " + s_oldgroupby;
		}
		s_select = "select " + s_tempselect  + " from (" + s_select +") as page ";
		s_select = s_select + "WHERE rowid " + s_pageination;
		return s_select;
	}


	public String makeSelect(String s_where,String s_order){
		
		String s_select = "";
		int i_fori,i_forj;
		String[] s_fields = null;     
		String[] s_fieldskey = null;  
		String[] s_qstate = null;     
		String s_oldwhere = "";       
		String s_oldgroupby = "";     
		String s_oldorderby = "";     
		String s_oldtablename="";     
		Map statemap = null;

						
		s_fields = getCq().getFields();
		s_fieldskey = getCq().getFieldskey();
		s_qstate = getCq().getFieldscol("QSTATE");
		if (s_fields==null || s_fieldskey==null ) return "-1";
		
		s_oldgroupby = getCq().getQuerysValue(0,"QGROUP");
		s_oldorderby = getCq().getQuerysValue(0,"QORDER");
		s_oldtablename = getCq().getQuerysValue(0,"QTABLE");
		if (s_oldtablename.equals("")) {
			s_oldtablename = getCq().getQuerysValue(0,"QUERYID");
		}
		statemap = getCq().getSumtype();
		for (i_fori=0;i_fori<s_fields.length;i_fori++){
			if (s_qstate[i_fori].equals("") || s_qstate[i_fori].equals("00")){
				s_select = s_select + s_fields[i_fori] + ",";
			} else {
				s_select = s_select + (String)statemap.get(s_qstate[i_fori]) + "(" + s_fields[i_fori] + "),";
			}
		}
		s_select = s_select.substring(0,s_select.length() - 1);
		s_select = "SELECT " + s_select + " FROM " + s_oldtablename;
		s_oldwhere = getCq().getQuerysValue(0,"QWHERE");
		if (s_oldwhere.equals("TABLE")) s_oldwhere = "";
		if (!s_oldwhere.equals("")){
			s_select = s_select + " WHERE ";
			s_select = s_select + " (" + s_oldwhere + ")";
		}
		if (!s_where.equals("")){
			if (s_oldwhere.equals("")){
				s_select = s_select + " WHERE ";
				s_select = s_select + s_where;
			} else{
				s_select = s_select + " AND " + s_where + ""; 
			}
			
		}
		if (!s_oldgroupby.equals("")){
			s_select = s_select + " GROUP BY " + s_oldgroupby;
		}
		if (s_order.equals("")){
			if (s_oldorderby.trim().equals("")){
				s_select = s_select + " ORDER BY "; 
				for (i_fori=0;i_fori<s_fieldskey.length;i_fori++){
					s_select = s_select + s_fieldskey[i_fori] + ",";
				}
				s_select = s_select.substring(0,s_select.length() - 1);
			} else {
				s_select = s_select + " ORDER BY " + s_oldorderby;
			}
		} else {
			s_select = s_select + " ORDER BY " + s_order;
		}
		return s_select;
	}


	public String init(String s_where,String s_pageination){
		String s_order="";
		return init(s_where,s_order,s_pageination);
	}
	public String init(String s_where,String s_order,String s_pageination){
		
		CommSQL cs = new CommSQL();        
		String s_return="-1";
		String s_select = "";
		String s_key;                            
		String[] s_fields = null;          
		String[] s_datatitle=null;         
		String[] s_datakey = null;         
		String[] s_fieldskey = null;       
		String[] s_fieldstype = null;      
		int[] i_fieldskey = null;          
		String[][] s_data = null;          


		int i_fori,i_forj;
		
		if (s_where == null){
			setData(null);
			setDatatitle(null);
			setDatakey(null);
			setDatashow(null);
			
			return s_return;
		}
		if (s_where.trim().equals("")){
			setData(null);
			setDatatitle(null);
			setDatakey(null);
			setDatashow(null);
			
			return s_return;
		}
		
		s_fields = getCq().getFields();
		s_fieldstype = getCq().getFieldstype();
		s_fieldskey = getCq().getFieldskey();
		if (s_pageination.trim().equals("")){
			s_select = makeSelect(s_where,s_order);
		} else {
			s_select = makeSelectPage(s_where,s_order,s_pageination);
		}
		if (s_select.equals("-1")){
			setData(null);
			setDatatitle(null);
			setDatakey(null);
			setDatashow(null);
			return s_return;
		}


		s_data = cs.executeSelect(s_select);
		if (s_data == null){
			setData(null);
			setDatatitle(null);
			setDatakey(null);
			setDatashow(null);
			return s_return;
		}

		i_fieldskey = new int[s_fieldskey.length];
		for (i_fori=0;i_fori<s_fields.length;i_fori++){
			for (i_forj=0;i_forj<i_fieldskey.length;i_forj++){
				if (s_fields[i_fori].trim().equals(s_fieldskey[i_forj])){
					i_fieldskey[i_forj] = i_fori;
				}
			}
		}
		if (!getActiondo().equals("")) {
			s_datakey = new String[s_data.length];
		} 
		for (i_fori=0;i_fori<s_data.length;i_fori++){
			s_key = "";
			for (i_forj=0;i_forj<i_fieldskey.length;i_forj++){
				if (s_fieldstype[i_fieldskey[i_forj]].trim().toUpperCase().equals("CHAR") ||
				s_fieldstype[i_fieldskey[i_forj]].trim().toUpperCase().equals("VARCHAR") ||
				s_fieldstype[i_fieldskey[i_forj]].trim().toUpperCase().equals("VARCHAR2") ||
				s_fieldstype[i_fieldskey[i_forj]].trim().toUpperCase().equals("DATE") ){
					//×Ö·ûÐÍ´¦Àí
					s_key = s_key + s_fields[i_fieldskey[i_forj]] + "='" + s_data[i_fori][i_fieldskey[i_forj]] +"'";
				} else {
					s_key = s_key + s_fields[i_fieldskey[i_forj]] + "=" + s_data[i_fori][i_fieldskey[i_forj]];
				}
				s_key = s_key + " AND ";
			}
			s_key = s_key.substring(0,s_key.length() - 5);
			if (!getActiondo().equals(""))  s_datakey[i_fori] = getActiondo() + "?queryid="+ getCq().getQueryid() + "&" +"selectwhere=" + s_key;
		}
		s_datatitle = getCq().getFieldsch();
		setData(s_data);
		setDatatitle(s_datatitle);
		setDatakey(s_datakey);
		setDatashow();
		return s_return;
	}

	/**
	 * @return
	 */
	public CommQuery getCq() {
		return this.cq;
	}

	public void setCq(CommQuery query) {
		this.cq = query;
	}

	public String getActiondo() {
		return this.actiondo;
	}

	public String[][] getData() {
		return this.data;
	}

	public String[] getDatakey() {
		return this.datakey;
	}

	public String[] getDatatitle() {
		return this.datatitle;
	}

	public void setActiondo(String string) {
		this.actiondo = string;
	}

	public void setData(String[][] strings) {
		this.data = strings;
	}

	public void setDatakey(String[] strings) {
		this.datakey = strings;
	}

	public void setDatatitle(String[] strings) {
		this.datatitle = strings;
	}

	public String[][] getDatashow() {
		return this.datashow;
	}

	public void setDatashow(String[][] strings) {
		this.datashow = strings;
	}

}

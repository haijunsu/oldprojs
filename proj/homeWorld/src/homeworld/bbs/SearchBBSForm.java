/**
 * @(#)SearchOrderForm.java  2004-02-01
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package homeworld.bbs;

import org.apache.struts.action.ActionForm;


public class SearchBBSForm extends ActionForm {
	
	private String searchtitle1 = null;  
	private String searchtitle2 = null;  
	private String searchtitle3 = null; 
	private String searchkey1 = null;    
	private String searchkey2 = null;    
	private String searchkey3 = null;   
	private String searchthing1 = null;  
	private String searchthing2 = null;  
	private String searchthing3 = null;  
	
	
	private String page = null;       
	private String flag = null;       
	
	private String searchid = null;   
	private String queryid = null;    
	private String queryiddis = null; 
	private String selectwhere = null;
	private String actiondo = null;   
	
	private String nowpage = null;     
	private String nowoncepage = null; 
	private commsearch.page.PageList[] pagelist = null;  
	private String prewOncePage;     
	private String nextOncePage;     
	private String oncepage;          
	
	
	private String[][] data= null;           
	private String[] datatitle = null;      
	private String[] datakey = null;       	
	private String rowcount;                 
	private String title;                     
	private String endtitle;                 
	private String[] lookin = null;          
	
	public SearchBBSForm() {
		super();
	}


	/**
	 * @return
	 */
	public String getActiondo() {
		return this.actiondo;
	}

	/**
	 * @return
	 */
	public String[][] getData() {
		return this.data;
	}

	/**
	 * @return
	 */
	public String[] getDatakey() {
		return this.datakey;
	}

	/**
	 * @return
	 */
	public String[] getDatatitle() {
		return this.datatitle;
	}

	/**
	 * @return
	 */
	public String getEndtitle() {
		return this.endtitle;
	}

	/**
	 * @return
	 */
	public String getFlag() {
		return this.flag;
	}

	/**
	 * @return
	 */
	public String getNextOncePage() {
		return this.nextOncePage;
	}

	/**
	 * @return
	 */
	public String getNowoncepage() {
		return this.nowoncepage;
	}

	/**
	 * @return
	 */
	public String getNowpage() {
		return this.nowpage;
	}

	/**
	 * @return
	 */
	public String getPage() {
		return this.page;
	}

	/**
	 * @return
	 */
	public commsearch.page.PageList[] getPagelist() {
		return this.pagelist;
	}

	/**
	 * @return
	 */
	public String getPrewOncePage() {
		return this.prewOncePage;
	}

	/**
	 * @return
	 */
	public String getQueryid() {
		return this.queryid;
	}

	/**
	 * @return
	 */
	public String getQueryiddis() {
		return this.queryiddis;
	}

	/**
	 * @return
	 */
	public String getRowcount() {
		return this.rowcount;
	}

	/**
	 * @return
	 */
	public String getSearchid() {
		return this.searchid;
	}

	/**
	 * @return
	 */
	public String getSelectwhere() {
		return this.selectwhere;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param string
	 */
	public void setActiondo(String string) {
		this.actiondo = string;
	}

	/**
	 * @param strings
	 */
	public void setData(String[][] strings) {
		this.data = strings;
	}

	/**
	 * @param strings
	 */
	public void setDatakey(String[] strings) {
		this.datakey = strings;
	}

	/**
	 * @param strings
	 */
	public void setDatatitle(String[] strings) {
		this.datatitle = strings;
	}

	/**
	 * @param string
	 */
	public void setEndtitle(String string) {
		this.endtitle = string;
	}

	/**
	 * @param string
	 */
	public void setFlag(String string) {
		this.flag = string;
	}

	/**
	 * @param string
	 */
	public void setNextOncePage(String string) {
		this.nextOncePage = string;
	}

	/**
	 * @param string
	 */
	public void setNowoncepage(String string) {
		this.nowoncepage = string;
	}

	/**
	 * @param string
	 */
	public void setNowpage(String string) {
		this.nowpage = string;
	}

	/**
	 * @param string
	 */
	public void setPage(String string) {
		this.page = string;
	}

	/**
	 * @param lists
	 */
	public void setPagelist(commsearch.page.PageList[] lists) {
		this.pagelist = lists;
	}

	/**
	 * @param string
	 */
	public void setPrewOncePage(String string) {
		this.prewOncePage = string;
	}

	/**
	 * @param string
	 */
	public void setQueryid(String string) {
		this.queryid = string;
	}

	/**
	 * @param string
	 */
	public void setQueryiddis(String string) {
		this.queryiddis = string;
	}

	/**
	 * @param string
	 */
	public void setRowcount(String string) {
		this.rowcount = string;
	}

	/**
	 * @param string
	 */
	public void setSearchid(String string) {
		this.searchid = string;
	}

	/**
	 * @param string
	 */
	public void setSelectwhere(String string) {
		this.selectwhere = string;
	}

	/**
	 * @param string
	 */
	public void setTitle(String string) {
		this.title = string;
	}

	/**
	 * @return
	 */
	public String getSearchkey1() {
		return this.searchkey1;
	}

	/**
	 * @return
	 */
	public String getSearchkey2() {
		return this.searchkey2;
	}

	/**
	 * @return
	 */
	public String getSearchkey3() {
		return this.searchkey3;
	}

	/**
	 * @return
	 */
	public String getSearchthing1() {
		return this.searchthing1;
	}

	/**
	 * @return
	 */
	public String getSearchthing2() {
		return this.searchthing2;
	}

	/**
	 * @return
	 */
	public String getSearchthing3() {
		return this.searchthing3;
	}

	/**
	 * @param string
	 */
	public void setSearchkey1(String string) {
		this.searchkey1 = string;
	}

	/**
	 * @param string
	 */
	public void setSearchkey2(String string) {
		this.searchkey2 = string;
	}

	/**
	 * @param string
	 */
	public void setSearchkey3(String string) {
		this.searchkey3 = string;
	}

	/**
	 * @param string
	 */
	public void setSearchthing1(String string) {
		this.searchthing1 = string;
	}

	/**
	 * @param string
	 */
	public void setSearchthing2(String string) {
		this.searchthing2 = string;
	}

	/**
	 * @param string
	 */
	public void setSearchthing3(String string) {
		this.searchthing3 = string;
	}

	/**
	 * @return
	 */
	public String getSearchtitle1() {
		return this.searchtitle1;
	}

	/**
	 * @return
	 */
	public String getSearchtitle2() {
		return this.searchtitle2;
	}


	/**
	 * @param string
	 */
	public void setSearchtitle1(String string) {
		this.searchtitle1 = string;
	}

	/**
	 * @param string
	 */
	public void setSearchtitle2(String string) {
		this.searchtitle2 = string;
	}


	/**
	 * @return
	 */
	public String getSearchtitle3() {
		return this.searchtitle3;
	}

	/**
	 * @param string
	 */
	public void setSearchtitle3(String string) {
		this.searchtitle3 = string;
	}

	/**
	 * @return
	 */
	public String getOncepage() {
		return this.oncepage;
	}

	/**
	 * @param string
	 */
	public void setOncepage(String string) {
		this.oncepage = string;
	}

	/**
	 * @return
	 */
	public String[] getLookin() {
		return this.lookin;
	}

	/**
	 * @param strings
	 */
	public void setLookin(String[] strings) {
		this.lookin = strings;
	}

}

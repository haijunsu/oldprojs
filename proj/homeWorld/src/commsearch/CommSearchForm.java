/**
 * @(#)CommSearchForm.java  2003-7-09
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch;

//import javax.servlet.http.HttpServletRequest;

//import org.apache.struts.action.ActionError;
//import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionMapping;

/**
 * <P>用于公用查询条件的Form</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommSearchForm extends ActionForm {
	
	private String page = null;       //当前的画面
	private String flag = null;       //提交后的标志
	
	private String searchid = null;    //公用修改条件,公用查询
	private String queryid = null;    //三张表的查询ID
	private String queryiddis = null; //显示与查询条件不同时用
	private String selectwhere = null;//生成的WHERE语句
	private String actiondo = null;    //调用的下一级画面
	
	private String nowpage = null;     //当前页
	private String nowoncepage = null; //当前显示页
	private commsearch.page.PageList[] pagelist = null;  //分页的超文本链接,分页的页码
	private String prewOncePage;     //上显示页
	private String nextOncePage;      //下显示页
	
	
	private String[][] data= null;           //查询结果的列表
	private String[] datatitle = null;       //查询结果的表头
	private String[] datakey = null;       	 //查询结果的KEY条件
	private String rowcount;                  //标题头
	private String title;                     //查询名称
	private String endtitle;                  //标题尾
	private String[] fieldsid = null;           //字段的ID
	private String[] fieldsshow = null;         //字段的内容
	private String[] fieldstype = null;         //字段的类型
	private String[] fieldskey = null;          //字段的KEY
	private String[] conditionid = null;        //条件的ID
	private String[] conditionshow = null;      //条件的内容
	private String[] relationid = null;         //关系的ID
	private String[] relationshow = null;       //关系的内容
	
	
	private String fields1id = null;     //查询字段1
	private String fields1show = null;   //查询字段1中文
	private String condition1id = null;  //查询条件1
	private String condition1show = null;//查询条件1中文
	private String thing1 = null;      //查询的内容1
	
	private String fields2id = null;     //查询字段2  
	private String fields2show = null;   //查询字段2中文
	private String condition2id = null;  //查询条件2
	private String condition2show = null;//查询条件2中文
	private String thing2 = null;      //查询的内容2
	
	private String fields3id = null;     //查询字段3  
	private String fields3show = null;   //查询字段3中文
	private String condition3id = null;  //查询条件3  
	private String condition3show = null;//查询条件3中文
	private String thing3 = null;      //查询的内容3
	
	private String relation1id = null;    //查询关系1
	private String relation1show = null;  //查询关系1中文
	private String relation2id = null;    //查询关系2
	private String relation2show = null;  //查询关系2中文
	
	/**
	* Constructor
	*/
	public CommSearchForm() {
		super();
		setPage("");
		setFields1id("");
		setCondition1id("");
		setThing1("");
		setFields2id("");
		setCondition2id("");
		setThing2("");
		setFields3id("");
		setCondition3id("");
		setThing3("");
		setRelation1id("");
		setRelation2id("");
		setRowcount("0");
		setTitle("");
	}



	/**
	 * @return
	 */
	public String getCondition1id() {
		return this.condition1id;
	}

	/**
	 * @return
	 */
	public String getCondition1show() {
		return this.condition1show;
	}

	/**
	 * @return
	 */
	public String getCondition2id() {
		return this.condition2id;
	}

	/**
	 * @return
	 */
	public String getCondition2show() {
		return this.condition2show;
	}

	/**
	 * @return
	 */
	public String getCondition3id() {
		return this.condition3id;
	}

	/**
	 * @return
	 */
	public String getCondition3show() {
		return this.condition3show;
	}

	/**
	 * @return
	 */
	public String[] getConditionid() {
		return this.conditionid;
	}

	/**
	 * @return
	 */
	public String[] getConditionshow() {
		return this.conditionshow;
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
	public String getFields1id() {
		return this.fields1id;
	}

	/**
	 * @return
	 */
	public String getFields1show() {
		return this.fields1show;
	}

	/**
	 * @return
	 */
	public String getFields2id() {
		return this.fields2id;
	}

	/**
	 * @return
	 */
	public String getFields2show() {
		return this.fields2show;
	}

	/**
	 * @return
	 */
	public String getFields3id() {
		return this.fields3id;
	}

	/**
	 * @return
	 */
	public String getFields3show() {
		return this.fields3show;
	}

	/**
	 * @return
	 */
	public String[] getFieldsid() {
		return this.fieldsid;
	}

	/**
	 * @return
	 */
	public String[] getFieldskey() {
		return this.fieldskey;
	}

	/**
	 * @return
	 */
	public String[] getFieldsshow() {
		return this.fieldsshow;
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
	public String getPage() {
		return this.page;
	}

	/**
	 * @return
	 */
	public String getRelation1id() {
		return this.relation1id;
	}

	/**
	 * @return
	 */
	public String getRelation1show() {
		return this.relation1show;
	}

	/**
	 * @return
	 */
	public String getRelation2id() {
		return this.relation2id;
	}

	/**
	 * @return
	 */
	public String getRelation2show() {
		return this.relation2show;
	}

	/**
	 * @return
	 */
	public String[] getRelationid() {
		return this.relationid;
	}

	/**
	 * @return
	 */
	public String[] getRelationshow() {
		return this.relationshow;
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
	public String getThing1() {
		return this.thing1;
	}

	/**
	 * @return
	 */
	public String getThing2() {
		return this.thing2;
	}

	/**
	 * @return
	 */
	public String getThing3() {
		return this.thing3;
	}

	/**
	 * @param string
	 */
	public void setCondition1id(String string) {
		this.condition1id = string;
	}

	/**
	 * @param string
	 */
	public void setCondition1show(String string) {
		this.condition1show = string;
	}

	/**
	 * @param string
	 */
	public void setCondition2id(String string) {
		this.condition2id = string;
	}

	/**
	 * @param string
	 */
	public void setCondition2show(String string) {
		this.condition2show = string;
	}

	/**
	 * @param string
	 */
	public void setCondition3id(String string) {
		this.condition3id = string;
	}

	/**
	 * @param string
	 */
	public void setCondition3show(String string) {
		this.condition3show = string;
	}

	/**
	 * @param strings
	 */
	public void setConditionid(String[] strings) {
		this.conditionid = strings;
	}

	/**
	 * @param strings
	 */
	public void setConditionshow(String[] strings) {
		this.conditionshow = strings;
	}

	/**
	 * @param strings
	 */
	public void setData(String[][] strings) {
		this.data = strings;
	}

	/**
	 * @param string
	 */
	public void setFields1id(String string) {
		this.fields1id = string;
	}

	/**
	 * @param string
	 */
	public void setFields1show(String string) {
		this.fields1show = string;
	}

	/**
	 * @param string
	 */
	public void setFields2id(String string) {
		this.fields2id = string;
	}

	/**
	 * @param string
	 */
	public void setFields2show(String string) {
		this.fields2show = string;
	}

	/**
	 * @param string
	 */
	public void setFields3id(String string) {
		this.fields3id = string;
	}

	/**
	 * @param string
	 */
	public void setFields3show(String string) {
		this.fields3show = string;
	}

	/**
	 * @param strings
	 */
	public void setFieldsid(String[] strings) {
		this.fieldsid = strings;
	}

	/**
	 * @param strings
	 */
	public void setFieldskey(String[] strings) {
		this.fieldskey = strings;
	}

	/**
	 * @param strings
	 */
	public void setFieldsshow(String[] strings) {
		this.fieldsshow = strings;
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
	public void setPage(String string) {
		this.page = string;
	}

	/**
	 * @param string
	 */
	public void setRelation1id(String string) {
		this.relation1id = string;
	}

	/**
	 * @param string
	 */
	public void setRelation1show(String string) {
		this.relation1show = string;
	}

	/**
	 * @param string
	 */
	public void setRelation2id(String string) {
		this.relation2id = string;
	}

	/**
	 * @param string
	 */
	public void setRelation2show(String string) {
		this.relation2show = string;
	}

	/**
	 * @param strings
	 */
	public void setRelationid(String[] strings) {
		this.relationid = strings;
	}

	/**
	 * @param strings
	 */
	public void setRelationshow(String[] strings) {
		this.relationshow = strings;
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
	public void setThing1(String string) {
		this.thing1 = string;
	}

	/**
	 * @param string
	 */
	public void setThing2(String string) {
		this.thing2 = string;
	}

	/**
	 * @param string
	 */
	public void setThing3(String string) {
		this.thing3 = string;
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
	public String[] getDatatitle() {
		return this.datatitle;
	}

	/**
	 * @param strings
	 */
	public void setDatatitle(String[] strings) {
		this.datatitle = strings;
	}

	/**
	 * @return
	 */
	public String[] getDatakey() {
		return this.datakey;
	}

	/**
	 * @param strings
	 */
	public void setDatakey(String[] strings) {
		this.datakey = strings;
	}

	/**
	 * @return
	 */
	public String getActiondo() {
		return this.actiondo;
	}

	/**
	 * @param string
	 */
	public void setActiondo(String string) {
		this.actiondo = string;
	}

	/**
	 * @return
	 */
	public String getRowcount() {
		return this.rowcount;
	}

	/**
	 * @param i
	 */
	public void setRowcount(String string) {
		this.rowcount = string;
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
	public void setTitle(String string) {
		this.title = string;
	}

	/**
	 * @return
	 */
	public String getSearchid() {
		return this.searchid;
	}

	/**
	 * @param string
	 */
	public void setSearchid(String string) {
		this.searchid = string;
	}

	/**
	 * @return
	 */
	public String getQueryiddis() {
		return this.queryiddis;
	}

	/**
	 * @param string
	 */
	public void setQueryiddis(String string) {
		this.queryiddis = string;
	}

	/**
	 * @return
	 */
	public String getEndtitle() {
		return this.endtitle;
	}

	/**
	 * @param string
	 */
	public void setEndtitle(String string) {
		this.endtitle = string;
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
	 * @return
	 */
	public commsearch.page.PageList[] getPagelist() {
		return this.pagelist;
	}

	/**
	 * @param strings
	 */
	public void setPagelist(commsearch.page.PageList[] strings) {
		this.pagelist = strings;
	}

	/**
	 * @param strings
	 */

	/**
	 * @return
	 */
	public String getNextOncePage() {
		return this.nextOncePage;
	}

	/**
	 * @return
	 */
	public String getPrewOncePage() {
		return this.prewOncePage;
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
	public void setPrewOncePage(String string) {
		this.prewOncePage = string;
	}

}

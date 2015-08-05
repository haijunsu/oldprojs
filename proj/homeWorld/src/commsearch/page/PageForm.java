/**
 * @(#)BBSTopicForm.java  2003-11-7
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.page;

//import javax.servlet.http.HttpServletRequest;

//import org.apache.struts.action.ActionError;
//import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionMapping;

/**
 * <P>分页用的Form</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class PageForm extends ActionForm {
	
	/**
	* Constructor
	*/
	int onePage;             //一页所显示的行数
	int nowPage;             //当前的页号
	int nowOncePage;         //当前一次显示的页号
	int beginPage;           //本次显示的开始页数
	int endPage;             //本次显示的结束页数
	int oncePage;            //一次显示的页数
	int nowPageBegin;        //当前页开始记录
	int nowPageEnd;          //当前页结束记录
	
	String prewOncePage;     //上显示页
	String nextOncePage;      //下显示页
	
	String pageination;      //分页标志（0：分页；1：不分页）
	String stringAdd;        //SQL语句中的字符串连接符
	String firstSQL;         //前几条SQL语句的语法
	String[] listoncepage = null; //指定页的内容超文本链接
	String data[][] = null ; //全结果集
	String redata[][] = null;//显示的内容
	String select="";        //显示的SELECT语句
	public PageForm() {
		super();
		setOnePage(15);
		setNowPage(0);
		setOncePage(10);
		setPageination("0");
		setStringAdd("||");
	}

	/**
	 * @return 前几条SQL语句的语法
	 */
	public String getFirstSQL() {
		//String s_first = "fetch first " + (getOnePage() * getOncePage()) + " rows only";
		//firstSQL = s_first;
		return this.firstSQL;
	}

	/**
	 * @return 当前的页号
	 */
	public int getNowPage() {
		return this.nowPage;
	}

	/**
	 * @return 一次显示的页数
	 */
	public int getOncePage() {
		return this.oncePage;
	}

	/**
	 * @return 一页所显示的行数
	 */
	public int getOnePage() {
		return this.onePage;
	}

	/**
	 * @return 分页标志（0：分页；1：不分页）
	 */
	public String getPageination() {
		return this.pageination;
	}

	/**
	 * @return SQL语句中的字符串连接符
	 */
	public String getStringAdd() {
		return this.stringAdd;
	}

	/**
	 * @param string 前几条SQL语句的语法
	 */
	public void setFirstSQL(String string) {
		this.firstSQL = string;
	}

	/**
	 * @param i 当前的页号
	 */
	public void setNowPage(int i) {
		this.nowPage = i;
	}

	/**
	 * @param i 一页所显示的行数
	 */
	public void setOncePage(int i) {
		this.oncePage = i;
	}

	/**
	 * @param i 一页所显示的行数
	 */
	public void setOnePage(int i) {
		this.onePage = i;
	}

	/**
	 * @param string 分页标志（0：分页；1：不分页）
	 */
	public void setPageination(String string) {
		this.pageination = string;
	}

	/**
	 * @param string SQL语句中的字符串连接符
	 */
	public void setStringAdd(String string) {
		this.stringAdd = string;
	}

	/**
	 * @return 全结果集
	 */
	public String[][] getData() {
		return this.data;
	}

	/**
	 * @return 显示的内容
	 */
	public String[][] getRedata() {
		return this.redata;
	}

	/**
	 * @param strings 全结果集
	 */
	public void setData(String[][] strings) {
		this.data = strings;
	}

	/**
	 * @param strings 显示的内容
	 */
	public void setRedata(String[][] strings) {
		this.redata = strings;
	}

	/**
	 * @return 显示的SELECT语句
	 */
	public String getSelect() {
		return this.select;
	}

	/**
	 * @param string 显示的SELECT语句
	 */
	public void setSelect(String string) {
		this.select = string;
	}

	/**
	 * @return
	 */
	public int getNowOncePage() {
		return this.nowOncePage;
	}

	/**
	 * @param i
	 */
	public void setNowOncePage(int i) {
		this.nowOncePage = i;
	}

	/**
	 * @return
	 */
	public String[] getListoncepage() {
		return this.listoncepage;
	}

	/**
	 * @param strings
	 */
	public void setListoncepage(String[] strings) {
		this.listoncepage = strings;
	}

	/**
	 * @return
	 */
	public int getBeginPage() {
		return this.beginPage;
	}

	/**
	 * @return
	 */
	public int getEndPage() {
		return this.endPage;
	}

	/**
	 * @param i
	 */
	public void setBeginPage(int i) {
		this.beginPage = i;
	}

	/**
	 * @param i
	 */
	public void setEndPage(int i) {
		this.endPage = i;
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

	/**
	 * @return
	 */
	public int getNowPageBegin() {
		return this.nowPageBegin;
	}

	/**
	 * @return
	 */
	public int getNowPageEnd() {
		return this.nowPageEnd;
	}

	/**
	 * @param i
	 */
	public void setNowPageBegin(int i) {
		this.nowPageBegin = i;
	}

	/**
	 * @param i
	 */
	public void setNowPageEnd(int i) {
		this.nowPageEnd = i;
	}

}

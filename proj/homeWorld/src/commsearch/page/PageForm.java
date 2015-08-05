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
 * <P>��ҳ�õ�Form</P>
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
	int onePage;             //һҳ����ʾ������
	int nowPage;             //��ǰ��ҳ��
	int nowOncePage;         //��ǰһ����ʾ��ҳ��
	int beginPage;           //������ʾ�Ŀ�ʼҳ��
	int endPage;             //������ʾ�Ľ���ҳ��
	int oncePage;            //һ����ʾ��ҳ��
	int nowPageBegin;        //��ǰҳ��ʼ��¼
	int nowPageEnd;          //��ǰҳ������¼
	
	String prewOncePage;     //����ʾҳ
	String nextOncePage;      //����ʾҳ
	
	String pageination;      //��ҳ��־��0����ҳ��1������ҳ��
	String stringAdd;        //SQL����е��ַ������ӷ�
	String firstSQL;         //ǰ����SQL�����﷨
	String[] listoncepage = null; //ָ��ҳ�����ݳ��ı�����
	String data[][] = null ; //ȫ�����
	String redata[][] = null;//��ʾ������
	String select="";        //��ʾ��SELECT���
	public PageForm() {
		super();
		setOnePage(15);
		setNowPage(0);
		setOncePage(10);
		setPageination("0");
		setStringAdd("||");
	}

	/**
	 * @return ǰ����SQL�����﷨
	 */
	public String getFirstSQL() {
		//String s_first = "fetch first " + (getOnePage() * getOncePage()) + " rows only";
		//firstSQL = s_first;
		return this.firstSQL;
	}

	/**
	 * @return ��ǰ��ҳ��
	 */
	public int getNowPage() {
		return this.nowPage;
	}

	/**
	 * @return һ����ʾ��ҳ��
	 */
	public int getOncePage() {
		return this.oncePage;
	}

	/**
	 * @return һҳ����ʾ������
	 */
	public int getOnePage() {
		return this.onePage;
	}

	/**
	 * @return ��ҳ��־��0����ҳ��1������ҳ��
	 */
	public String getPageination() {
		return this.pageination;
	}

	/**
	 * @return SQL����е��ַ������ӷ�
	 */
	public String getStringAdd() {
		return this.stringAdd;
	}

	/**
	 * @param string ǰ����SQL�����﷨
	 */
	public void setFirstSQL(String string) {
		this.firstSQL = string;
	}

	/**
	 * @param i ��ǰ��ҳ��
	 */
	public void setNowPage(int i) {
		this.nowPage = i;
	}

	/**
	 * @param i һҳ����ʾ������
	 */
	public void setOncePage(int i) {
		this.oncePage = i;
	}

	/**
	 * @param i һҳ����ʾ������
	 */
	public void setOnePage(int i) {
		this.onePage = i;
	}

	/**
	 * @param string ��ҳ��־��0����ҳ��1������ҳ��
	 */
	public void setPageination(String string) {
		this.pageination = string;
	}

	/**
	 * @param string SQL����е��ַ������ӷ�
	 */
	public void setStringAdd(String string) {
		this.stringAdd = string;
	}

	/**
	 * @return ȫ�����
	 */
	public String[][] getData() {
		return this.data;
	}

	/**
	 * @return ��ʾ������
	 */
	public String[][] getRedata() {
		return this.redata;
	}

	/**
	 * @param strings ȫ�����
	 */
	public void setData(String[][] strings) {
		this.data = strings;
	}

	/**
	 * @param strings ��ʾ������
	 */
	public void setRedata(String[][] strings) {
		this.redata = strings;
	}

	/**
	 * @return ��ʾ��SELECT���
	 */
	public String getSelect() {
		return this.select;
	}

	/**
	 * @param string ��ʾ��SELECT���
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

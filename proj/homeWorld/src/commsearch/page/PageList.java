/**
 * @(#)BBSTopicForm.java  2003-11-7
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.page;

/**
 * <P>显示分页的超文本链接</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class PageList {
	public String list="";
	public String number="";
	public PageList() {
		super();
		setList("");
		setNumber("");
	}
	
	/**
	 * @return
	 */
	public String getList() {
		return this.list;
	}

	/**
	 * @return
	 */
	public String getNumber() {
		return this.number;
	}

	/**
	 * @param string
	 */
	public void setList(String string) {
		this.list = string;
	}

	/**
	 * @param string
	 */
	public void setNumber(String string) {
		this.number = string;
	}

}

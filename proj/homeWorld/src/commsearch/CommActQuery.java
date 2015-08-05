/**
 * @(#)CommActQuery.java  2003-7-29
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch;


/**
 * <P>公用查询类</P>
 * <ul>查询三张表的数据和查询的结果
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommActQuery {
	
	private String[][] data = null;                  //最后的数据
	private String[][] datatrue = null;                  //最后的数据
	private CommQuery cq =null;
	private String[] fields=null; 
	/**
	 * 公用查询 
	* @param String 查询的ID
	* @param String 显示数据的WHERE条件(***=***)
	*/
	public CommActQuery(String s_queryid,String s_where) {
		super();
		//用三张表的数据
		this.cq = new CommQuery(s_queryid.toUpperCase());
		//初始化三张表
		//设定数组
		this.cq.init();
		CommQueryData cqd = new CommQueryData(this.cq);
		cqd.init(s_where,"");
		setData(cqd.getDatashow());
		setDataTrue(cqd.getData());

		
//		String a;
//		a="erp.fun.Fun";
//		Class cl = Class.forName(a);
//		Class a1=cl.newInstance();
//		a1.Setimport();
//		c1.DeleteCompanyType();
	}
	/**
	 * @return KEY的数组
	 */
	public String[] getFieldskey() {
		return this.cq.getFieldskey();
	}
	/**
	 * @return 显示的表名
	 */
	public String getTablename() {
		String[] s_name=null;
		s_name = getFields("QNAMEC");
		return s_name[0].trim();
	}	
	/**
	 * @return 显示的列名的数组
	 */
	public String[] getFieldsch() {
		return this.cq.getFieldsch();
	}
	/**
	 * @return 显示的列类型的数组
	 */
	public String[] getFieldstype() {
		return this.cq.getFieldstype();
	}
	/**
	 * @return 显示的列长度的数组
	 */
	public String[] getFieldslen() {
		return this.cq.getFieldslen();
	}
	/**
	 * @return 显示的列小数点位数的数组
	 */
	public String[] getFieldsdigits() {
		return getFields("FDIGITS");
	}
	/**
		 * @return 显示的列顺序的数组
	*/
	public String[] getFieldsseq() {
		return getFields("SEQ");
	}
	/**
		 * @return 显示的列显示标志的数组
	*/
	public String[] getFieldsqsattri() {
		return 	getFields("QSATTRI");
	}
	
	/**
	 * 显示的全部数据
	 * @return 显示的数据
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
	 * 显示的全部数据
	 * @return 显示的数据
	 */
	public String[][] getDataTrue() {
		return this.datatrue;
	}

	/**
	 * @param strings
	 */
	public void setDataTrue(String[][] strings) {
		this.datatrue = strings;
	}

	/**
	 * 给出字段ID,取内容数组
	 * @param 三张表的列名ID
	 * @return 内容
	 */
	public String[] getFields(String s_fieldcol) {
		return this.cq.getFieldscol(s_fieldcol);
	}

	/**
	 * @param strings
	 */
	public void setFields(String[] strings) {
		this.fields = strings;
	}

}

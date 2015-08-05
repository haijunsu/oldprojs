/**
 * @(#)CommActQuery.java  2003-7-29
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch;


/**
 * <P>���ò�ѯ��</P>
 * <ul>��ѯ���ű�����ݺͲ�ѯ�Ľ��
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommActQuery {
	
	private String[][] data = null;                  //��������
	private String[][] datatrue = null;                  //��������
	private CommQuery cq =null;
	private String[] fields=null; 
	/**
	 * ���ò�ѯ 
	* @param String ��ѯ��ID
	* @param String ��ʾ���ݵ�WHERE����(***=***)
	*/
	public CommActQuery(String s_queryid,String s_where) {
		super();
		//�����ű������
		this.cq = new CommQuery(s_queryid.toUpperCase());
		//��ʼ�����ű�
		//�趨����
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
	 * @return KEY������
	 */
	public String[] getFieldskey() {
		return this.cq.getFieldskey();
	}
	/**
	 * @return ��ʾ�ı���
	 */
	public String getTablename() {
		String[] s_name=null;
		s_name = getFields("QNAMEC");
		return s_name[0].trim();
	}	
	/**
	 * @return ��ʾ������������
	 */
	public String[] getFieldsch() {
		return this.cq.getFieldsch();
	}
	/**
	 * @return ��ʾ�������͵�����
	 */
	public String[] getFieldstype() {
		return this.cq.getFieldstype();
	}
	/**
	 * @return ��ʾ���г��ȵ�����
	 */
	public String[] getFieldslen() {
		return this.cq.getFieldslen();
	}
	/**
	 * @return ��ʾ����С����λ��������
	 */
	public String[] getFieldsdigits() {
		return getFields("FDIGITS");
	}
	/**
		 * @return ��ʾ����˳�������
	*/
	public String[] getFieldsseq() {
		return getFields("SEQ");
	}
	/**
		 * @return ��ʾ������ʾ��־������
	*/
	public String[] getFieldsqsattri() {
		return 	getFields("QSATTRI");
	}
	
	/**
	 * ��ʾ��ȫ������
	 * @return ��ʾ������
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
	 * ��ʾ��ȫ������
	 * @return ��ʾ������
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
	 * �����ֶ�ID,ȡ��������
	 * @param ���ű������ID
	 * @return ����
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

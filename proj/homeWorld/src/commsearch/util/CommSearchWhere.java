/**
 * @(#)CommSearchWhere.java  2003-11-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch.util;

//import java.util.*;
//import javax.servlet.http.*;

//import com.idn.sql.DataBean;
//import javax.servlet.ServletException;
//import javax.servlet.http.*;

//import org.apache.struts.action.*;
//import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;
/**
 * <P>���ò�ѯ�ù�ϵ��ع�����</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class CommSearchWhere {

	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommSearchWhere.class);

	String[] condition = null;        //������ID
			
	String[] conditionch = null;      //����������
	String[] relation = null;         //��ϵ��ID
	String[] relationch = null;       //��ϵ������			
	String[][] wherereplace = null;   //��Ӧת��������
	/**
	* Constructor
	*/
	public CommSearchWhere() {
		super();
		
	}
	
	
	/**
	 * @return
	 */
	public String whereReplace(String s_where) {
		String s_return="";
		commsearch.util.CommString cs = new commsearch.util.CommString(); 
		s_return = s_where;
		if (getWherereplace()==null){
//			wherereplace = new String [2][2];
//			wherereplace[0][0]="DWBH";
//			wherereplace[0][1]="YB";
//			wherereplace[1][0]="DWBH";
//			wherereplace[1][1]="BY";
		}
		if (getWherereplace()!=null){
			for (int i_fori=0;i_fori<this.wherereplace.length;i_fori++){
				s_return = cs.replaceAll(s_return,this.wherereplace[i_fori][0],this.wherereplace[i_fori][1]); 
			}
		}
		return s_return;
	}
	
	
	
	/**
	 * @return
	 */
	public String[] getCondition() {
		if (this.condition == null){
			this.condition = new String[8];
			this.condition[0] = "��ѯ�����б�";
			this.condition[1] = "=";
			this.condition[2] = "<>";
			this.condition[3] = ">";
			this.condition[4] = "<";
			this.condition[5] = ">=";
			this.condition[6] = "<=";
			this.condition[7] = "LIKE";
		}
		return this.condition;
	}

	/**
	 * @return
	 */
	public String[] getConditionch() {
		if (this.conditionch == null){
			this.conditionch = new String[8];
			this.conditionch[0] = "";
			this.conditionch[1] = "����";
			this.conditionch[2] = "������";
			this.conditionch[3] = "����";
			this.conditionch[4] = "С��";
			this.conditionch[5] = "���ڵ���";
			this.conditionch[6] = "С�ڵ���";
			this.conditionch[7] = "����";
		}
		return this.conditionch;
	}

	/**
	 * @return
	 */
	public String[] getRelation() {
		if (this.relation ==null){
			this.relation = new String[3];
			this.relation[0] = "��ѯ��ϵ�б�";
			this.relation[1] = "AND";
			this.relation[2] = "OR";
		}
		return this.relation;
	}

	/**
	 * @return
	 */
	public String[] getRelationch() {
		if (this.relationch ==null){
			this.relationch = new String[3];
			this.relationch[0] = "";
			this.relationch[1] = "��";
			this.relationch[2] = "��";
		}
		return this.relationch;
	}

	/**
	 * @param strings
	 */
	public void setCondition(String[] strings) {
		this.condition = strings;
	}

	/**
	 * @param strings
	 */
	public void setConditionch(String[] strings) {
		this.conditionch = strings;
	}

	/**
	 * @param strings
	 */
	public void setRelation(String[] strings) {
		this.relation = strings;
	}

	/**
	 * @param strings
	 */
	public void setRelationch(String[] strings) {
		this.relationch = strings;
	}
	
	
	/**
	 * @return
	 */
	public String[][] getWherereplace() {
		return this.wherereplace;
	}

	/**
	 * @param strings
	 */
	public void setWherereplace(String[][] strings) {
		this.wherereplace = strings;
	}

}
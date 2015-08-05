/*
 * @(#)Columns.java  2003-4-24
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import com.idn.property.CodeNotFoundException;
import com.idn.property.CodesManager;
import com.idn.sql.DataBean;
import com.idn.util.CommonTools;

/**
 * <P>��ѯ�����Ա�Ӱ�䣬������е���ʾ���ԣ�URL�� </P>
 * 
 * @version 0.1
 * @author navy
 */
public class Column {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(Column.class);

	/**
	 * ���е���ʾ��˳��
	 */
	private String sequence = null;

	/**
	 * Ҫ��ʾ���У�����Ϊ���SQL��������������
	 */
	private String queryColumn = null;

	/**
	 * ����Ҫ��ʾ��ʵ�����ƣ�����ͷ���ǩ
	 */
	private String displayName = null;

	/**
	 * �����ڱ�����ʵ������
	 */
	private String realColumn = null;

	/**
	 * �����ڱ��β�ѯ���Ƿ���ʾ�ı�־����0��Ϊ����ʾ��������Ϊ��ʾ<br>
	 * ��ӦHTML�༭���ԣ�
	 * <pre>
	 * 		0 - HIDDEN
	 * 		1 - TEXT��TEXTAREA��PASSWORD
	 * 		2 - RADIO��CHECKBOX
	 * 		3 - CHECKBOX��MULTIBOX
	 * 		4 - OPTION��OPTIONCOLLECTION��SELECT
	 * 		5 - OPTION��OPTIONS��OPTIONCOLLECTION��SELECT
	 * </pre>
	 */
	private String showFlag = null;

	/**
	 * �����Ƿ����URL��URL�п��Դ���������������������request���ݽ�����
	 * Ҳ������SQL�е�ĳ�еĿɱ�ֵ�������á�`������������������ԡ�=����ͷ��
	 * ����Ϊ�ǵ�ǰ��ѯ��ĳ�еĿɱ�ֵ����=��ӦΪqueryColumn�е�һ��ֵ��
	 * ʾ�����£�<br>
	 * 		/test.jsp?param0=test&param1=`fromRequest`&param2=`=fromColumnValue`<BR>
	 * ��ʱ�����ѯ�������һ��Ϊ��fromColumnValue��,��ֵ�Ǵ�0��ʼ���е���������Ϊ1��
	 * ��HttpRequest��������һ����fromRequest���Ĳ�������ֵΪ��ABC������ʵ�ʽ��Ϊ��<BR>
	 * <code>
	 * 		/test.jsp?param0=test&param1=ABC&param2=0<BR>
	 * 		/test.jsp?param0=test&param1=ABC&param2=1<BR>
	 * 		/test.jsp?param0=test&param1=ABC&param2=2<BR>
	 * 	</code>		......<BR>
	 * ���URL���ԡ�/����ͷ�����������ʱ���Զ���SERVLET�����ļ��ڳ�����ǰ�棬<BR>
	 * ��֤���ܷ���Ӧ���ڵ����ӡ���������ӵ����Ӧ�û���������д������URL���磺<BR>
	 * 		http://www.idn-g.com/idnweb/��
	 * 
	 */
	private String url = null;

	/**
	 * ����VIEW�Ĵ����У��������е����е����ݴ����Hashtable���У�������
	 * �ƴ�CodesManager��ȡ����Ӧ�Ĵ���ֵ
	 */
	private Hashtable codeMap = null;

	/**
	 * �����־��λ�ڴ�����32ֵ
	 */
	private String computeFlag = null;

	/**
	 * ������ʾ�Ĵ�С
	 */
	private int displaySize = 0;

	/**
	 * ����Columns�������е���������Ϊnull;
	 */
	public Column() {
		computeFlag = null;
		codeMap = null;
		sequence = null;
		queryColumn = null;
		displayName = null;
		realColumn = null;
		showFlag = null;
		url = null;
	}

	/**
	 * ����Columns�������е���������Ϊ���Ӧ��ֵ
	 * @param strSequence ���е���ʾ��˳��
	 * @param strqueryColumn Ҫ��ʾ����
	 * @param strDisplayName Ҫ��ʾ������
	 * @param strRealColumn ʵ�ʱ��д��ڵ���������
	 * @param strIsShow �����Ƿ���ʾ
	 * @param strUrl ���е�URL
	 */
	public Column(
		String strSequence,
		String strqueryColumn,
		String strDisplayName,
		String strRealColumn,
		String strShowFlag,
		String strUrl) {
		sequence = strSequence;
		queryColumn = strqueryColumn;
		displayName = strDisplayName;
		realColumn = strRealColumn;
		showFlag = strShowFlag;
		url = strUrl;
		if (realColumn.trim().equals(""))
			realColumn = null;
	}

	/**
	 * �ж��Ƿ�ΪURL��
	 * @return trueΪURL�У�false����
	 */
	public boolean isUrlColumn() {
		if (url == null || url.trim().length() == 0)
			return false;
		return true;
	}

	/**
	 * �ж��Ƿ�ΪGROUP BY�У�
	 * @return trueΪGROUP BY�У�false����
	 */
	public boolean isGruopByColumn() {
		if (getComputeName() == null)
			return true;
		return false;
	}

	/**
	 * �жϸ����Ƿ���Ҫ��ʾ
	 * @return trueΪ��ʾ��falseΪ����ʾ
	 */
	public boolean isDisplay() {
		if (showFlag == null)
			return false;
		if (Integer.parseInt(showFlag) > 0)
			return true;
		return false;
	}

	/**
	 * ����SQL�����в�ѯ�еı������磺<br>
	 * 	count(*) AS mycount<br>
	 * 	�򷵻�"mycount"
	 * @return SQL�����в�ѯ�еı���
	 */
	public String getQueryColumnName() {
		String strComputeName = getComputeName();
		StringBuffer sbName = new StringBuffer();
		if (strComputeName == null) {
			StringTokenizer strTokens = new StringTokenizer(queryColumn, " ");
			int iCount = strTokens.countTokens();
			if (iCount < 3)
				return queryColumn.trim();
			String[] tokensElements = new String[iCount];
			for (int i = 0; i < tokensElements.length; i++) {
				tokensElements[i] = strTokens.nextToken();
			}
			if (tokensElements[iCount - 2].equalsIgnoreCase("AS"))
				return tokensElements[iCount - 1];
			return queryColumn.trim();
		}
		sbName.append("COMPUTE_").append(sequence.trim());
		return sbName.toString();

	}
	/**
	 * ���еı༭���ԣ��μ�QueryContents�����FORMԪ�ص�����ֵ
	 * @return ���Ե����ֱ�ʾֵ
	 * <pre>
	 * 		0 - HIDDEN
	 * 		1 - TEXT��TEXTAREA��PASSWORD
	 * 		2 - RADIO��CHECKBOX
	 * 		3 - CHECKBOX��MULTIBOX
	 * 		4 - OPTION��OPTIONCOLLECTION��SELECT
	 * 		5 - OPTION��OPTIONS��OPTIONCOLLECTION��SELECT
	 * </pre>
	 */
	public int getColumnEditType() {
		return Integer.parseInt(showFlag);
	}

	/**
	 * ���ؼ��������
	 * @return ��������
	 */
	public String getComputeName() {
		if (computeFlag.trim().equals(""))
			return null;
		try {
			String strTemp = CodesManager.getCodeValue("32", computeFlag.trim());
			return strTemp;
		} catch (CodeNotFoundException e) {
			return null;
		}
	}

	/**
	 * �����ǲ��Ǵ����У������Ϊ��no����Ϊ������
	 * 
	 * @return �Ƿ������
	 */
	public boolean isCodeColumn() {
		return !(getColumnCodeKey().equals("no"));
	}

	/**
	 * ���ظ��еĴ���ֵ�����Ǵ������򷵻ء�no��
	 * @return ���еĴ���ֵ
	 */
	public String getColumnCodeKey() {
		if (realColumn == null)
			return "no";
		return FieldsCollection.getField(realColumn).getCclass().trim();
	}

	/**
	 * ���е�����
	 * @return ����
	 */
	public String getColumnType() {
		if (realColumn == null) {
			if (queryColumn.indexOf("'") > 0)
				return "CHAR";
			else
				return "DECIMAL";
		}
		return FieldsCollection.getField(realColumn).getType().trim();
	}

	/**
	 * ���Ϊ�����ͣ�Ҫ�󷵻���С������λ��
	 * @return С��λ��
	 */
	public int getColumnDecimalDigits() {
		if (realColumn == null)
			return 0;
		return FieldsCollection.getField(realColumn).getDecimalDigits();
	}

	/**
	 * ���еĳ���ֵ
	 * @return ����ֵ
	 */
	public int getColumnLength() {
		if (realColumn == null)
			return 0;
		return FieldsCollection.getField(realColumn).getLength();
	}

	/**
	 * ����������ͣ�����ݳ��Ⱥ�С��λ������ʾ
	 * @return ��������ʾģ��
	 */
	public String getColumnNumberPattern() {
		if (realColumn == null)
			return null;
		return FieldsCollection.getField(realColumn).getNumberPattern();
	}

	/**
	 * �����е����г�Ա��KEY
	 * @return ���г�Ա��KEY
	 */
	public String[] getColumnCodeMembersKey() {
		String strCodeMemberKey = getColumnCodeKey();
		try {
			int nTemp = Integer.parseInt(strCodeMemberKey);
			return CodesManager.getCodeMembersKey(getColumnCodeKey());
		} catch (Exception e) {
			log.debug("����ΪView:" + strCodeMemberKey);
			return getCodeMapKeys();
		}
	}

	/**
	 * �����е����г�Ա��ֵ
	 * @return ���г�Ա��ֵ
	 */
	public String[] getColumnCodeMembersValue() {
		String strCodeMemberKey = getColumnCodeKey();
		try {
			int nTemp = Integer.parseInt(strCodeMemberKey);
			return CodesManager.getCodeMembersValue(getColumnCodeKey());
		} catch (Exception e) {
			return getCodeMapValues();
		}
	}

	/**
	 * ���ش����е�ָ����ֵ
	 * @param cclass
	 * @param ccode
	 * @return
	 */
	public String getColumnCodeValue(String cclass, String ccode) {
		try {
			int nTemp = Integer.parseInt(cclass);
			return CodesManager.getCodeValue(cclass, ccode);
		} catch (Exception e) {
			if (codeMap == null)
				initCodeMap();
			return (String) codeMap.get(ccode);
		}

	}
	/**
	 * ��ʼ��������в����ڵĴ���
	 */
	private void initCodeMap() {
		codeMap = new Hashtable();
		String strSql = "SELECT * FROM " + getColumnCodeKey();
		DataBean db = new DataBean();
		try {
			db.executeSelect(strSql);
			for (int i = 0; i < db.getRowCount(); i++) {
				codeMap.put(
					db.getElementValue(i, 0).trim(),
					db.getElementValue(i, 1).trim());
			}
			log.debug("init code " + getColumnCodeKey() + " success");
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * ���ظ��д�������е�KEY
	 * @return KEY
	 */
	private String[] getCodeMapKeys() {
		if (codeMap == null) {
			initCodeMap();
		}
		String[] keys = new String[codeMap.size()];
		Enumeration enum = codeMap.keys();
		int i = 0;
		while (enum.hasMoreElements()) {
			keys[i] = (String) enum.nextElement();
			i++;
		}
		Arrays.sort(keys);
		return keys;
	}

	/**
	 * ���ظ��д�������е�ֵ
	 * @return ���е�ֵ
	 */
	private String[] getCodeMapValues() {
		String[] keys = getCodeMapKeys();
		String[] values = new String[codeMap.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = (String) codeMap.get(keys[i]);
		}
		return values;
	}

	/**
	 * @return Ҫ��ʾ����
	 */
	public String getQueryColumn() {
		String strComputeName = getComputeName();
		StringBuffer sbName = new StringBuffer();
		if (strComputeName == null)
			return queryColumn.trim();
		sbName
			.append(strComputeName)
			.append("(")
			.append(queryColumn.trim())
			.append(") AS COMPUTE_")
			.append(sequence.trim());
		return sbName.toString();
	}

	/**
	 * @return �еı�ע
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return ��ʾ��־
	 */
	public String getShowFlag() {
		return showFlag;
	}

	/**
	 * @return ԭʼ������
	 */
	public String getRealColumn() {
		return realColumn;
	}

	/**
	 * @return �е���ʾ˳���
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * @return �е�URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param string
	 */
	public void setQueryColumn(String string) {
		queryColumn = string;
	}

	/**
	 * @param string
	 */
	public void setDisplayName(String string) {
		displayName = string;
	}

	/**
	 * @param string
	 */
	public void setShowFlag(String string) {
		showFlag = string;
	}

	/**
	 * @param string
	 */
	public void setRealColumn(String string) {
		if (string.trim().equals(""))
			realColumn = null;
		else
			realColumn = string;
	}

	/**
	 * @param string
	 */
	public void setSequence(String string) {
		sequence = string;
	}

	/**
	 * @param string
	 */
	public void setUrl(String string) {
		url = string;
	}

	/**
	 * @return ����ӳ������в�����CodesManagerҪ�󣬶��������ݿ��д���VIEW��
	 */
	public Hashtable getCodeMap() {
		return codeMap;
	}

	/**
	 * @param hashtable
	 */
	public void setCodeMap(Hashtable hashtable) {
		codeMap = hashtable;
	}

	/**
	 * @return �����־
	 */
	public String getComputeFlag() {
		return computeFlag;
	}

	/**
	 * @param string
	 */
	public void setComputeFlag(String string) {
		computeFlag = string;
	}

	/**
	 * @return ���ظ�����ʾ�Ĵ�С��������������ݿⶨ����С��4���򷵻�Ϊ4
	 */
	public int getDisplaySize() {
		if (displaySize > 0)
			return displaySize;
		if (realColumn == null) {
			log.debug(queryColumn + "û����ʵ��Ӧ�У����ȷ���4");
			displaySize = 4;
			return displaySize;
		}
		if (isCodeColumn()) {
			log.debug(queryColumn + "�����з�������������ַ��ĳ���");
			String strTemp[] = getColumnCodeMembersValue();
			int nTemp = 0;
			int nOffset = 0;
			for (int i = 0; i < strTemp.length; i++) {
				nTemp = strTemp[i].trim().length();
				if (displaySize < nTemp) {
					displaySize = nTemp;
					nOffset = i;
				}
			}
			//����ʵ�ʳ��ȣ����ְ������ֽ�������
			displaySize = CommonTools.stringLength(strTemp[nOffset]);
			if (displaySize < 4)
				displaySize = 4;
			return displaySize;
		}
		if (getColumnDecimalDigits() > 0) {
			displaySize = getColumnLength() + getColumnDecimalDigits() + 1;
		} else {
			displaySize = getColumnLength();
		}
		if (displaySize < 4)
			displaySize = 4;
		return displaySize;
	}

	/**
	 * ���ر�ͷdisplayName�ĳ���
	 * @return ��ͷdisplayName�ĳ���
	 */
	public int getLabelDisplaySize() {
		return CommonTools.stringLength(displayName);
	}

	/**
	 * @param i
	 */
	public void setDisplaySize(int i) {
		displaySize = i;
	}
	
	/**
	 * �ж��ǲ���Ϊ��������
	 * @return �ǲ���Ϊ��������
	 */
	public boolean isNumberColumn() {
		if (getColumnNumberPattern() != null)
			return true;
		return false;
	}
	

}

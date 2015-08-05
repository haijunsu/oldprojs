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
 * <P>查询列属性表影射，定义该列的显示属性，URL等 </P>
 * 
 * @version 0.1
 * @author navy
 */
public class Column {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(Column.class);

	/**
	 * 该列的显示的顺序
	 */
	private String sequence = null;

	/**
	 * 要显示的列，该列为组成SQL语名的真正的列
	 */
	private String queryColumn = null;

	/**
	 * 该列要显示的实际名称，即表头或标签
	 */
	private String displayName = null;

	/**
	 * 该列在表中真实的名字
	 */
	private String realColumn = null;

	/**
	 * 该列在本次查询中是否显示的标志，“0”为不显示，大于零为显示<br>
	 * 对应HTML编辑属性：
	 * <pre>
	 * 		0 - HIDDEN
	 * 		1 - TEXT、TEXTAREA、PASSWORD
	 * 		2 - RADIO、CHECKBOX
	 * 		3 - CHECKBOX、MULTIBOX
	 * 		4 - OPTION、OPTIONCOLLECTION、SELECT
	 * 		5 - OPTION、OPTIONS、OPTIONCOLLECTION、SELECT
	 * </pre>
	 */
	private String showFlag = null;

	/**
	 * 该列是否带有URL，URL中可以带参数，参数可以是来自request传递进来，
	 * 也可以是SQL中的某列的可变值，参数用“`”引起来，如果参数以“=”开头，
	 * 则认为是当前查询中某列的可变值，“=”应为queryColumn中的一个值。
	 * 示例如下：<br>
	 * 		/test.jsp?param0=test&param1=`fromRequest`&param2=`=fromColumnValue`<BR>
	 * 此时假设查询语句中有一列为“fromColumnValue”,其值是从0开始按行递增，步长为1，
	 * 在HttpRequest请求中有一个“fromRequest”的参数，其值为“ABC”，则实际结果为：<BR>
	 * <code>
	 * 		/test.jsp?param0=test&param1=ABC&param2=0<BR>
	 * 		/test.jsp?param0=test&param1=ABC&param2=1<BR>
	 * 		/test.jsp?param0=test&param1=ABC&param2=2<BR>
	 * 	</code>		......<BR>
	 * 如果URL是以“/”开头，则程序生成时会自动将SERVLET上下文加在超链接前面，<BR>
	 * 保证仅能访问应用内的链接。如果想连接到别的应用或主机，请写完整的URL，如：<BR>
	 * 		http://www.idn-g.com/idnweb/。
	 * 
	 */
	private String url = null;

	/**
	 * 对于VIEW的代码列，将代码列的所有的内容存放于Hashtable表中，可以类
	 * 似从CodesManager中取出相应的代码值
	 */
	private Hashtable codeMap = null;

	/**
	 * 计算标志，位于代码表的32值
	 */
	private String computeFlag = null;

	/**
	 * 该列显示的大小
	 */
	private int displaySize = 0;

	/**
	 * 构造Columns，将所有的属性设置为null;
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
	 * 构造Columns，将所有的属性设置为相对应的值
	 * @param strSequence 该列的显示的顺序
	 * @param strqueryColumn 要显示的列
	 * @param strDisplayName 要显示的名字
	 * @param strRealColumn 实际表中存在的真正的列
	 * @param strIsShow 该列是否显示
	 * @param strUrl 该列的URL
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
	 * 判断是否为URL列
	 * @return true为URL列，false则不是
	 */
	public boolean isUrlColumn() {
		if (url == null || url.trim().length() == 0)
			return false;
		return true;
	}

	/**
	 * 判断是否为GROUP BY列，
	 * @return true为GROUP BY列，false则不是
	 */
	public boolean isGruopByColumn() {
		if (getComputeName() == null)
			return true;
		return false;
	}

	/**
	 * 判断该列是否需要显示
	 * @return true为显示，false为不显示
	 */
	public boolean isDisplay() {
		if (showFlag == null)
			return false;
		if (Integer.parseInt(showFlag) > 0)
			return true;
		return false;
	}

	/**
	 * 返回SQL语名中查询列的别名。如：<br>
	 * 	count(*) AS mycount<br>
	 * 	则返回"mycount"
	 * @return SQL语名中查询列的别名
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
	 * 该列的编辑属性，参见QueryContents定义的FORM元素的属性值
	 * @return 属性的数字表示值
	 * <pre>
	 * 		0 - HIDDEN
	 * 		1 - TEXT、TEXTAREA、PASSWORD
	 * 		2 - RADIO、CHECKBOX
	 * 		3 - CHECKBOX、MULTIBOX
	 * 		4 - OPTION、OPTIONCOLLECTION、SELECT
	 * 		5 - OPTION、OPTIONS、OPTIONCOLLECTION、SELECT
	 * </pre>
	 */
	public int getColumnEditType() {
		return Integer.parseInt(showFlag);
	}

	/**
	 * 返回计算的名称
	 * @return 计算名称
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
	 * 该列是不是代码列，如果不为“no”则为代码列
	 * 
	 * @return 是否代码列
	 */
	public boolean isCodeColumn() {
		return !(getColumnCodeKey().equals("no"));
	}

	/**
	 * 返回该列的代码值，不是代码列则返回“no”
	 * @return 该列的代码值
	 */
	public String getColumnCodeKey() {
		if (realColumn == null)
			return "no";
		return FieldsCollection.getField(realColumn).getCclass().trim();
	}

	/**
	 * 该列的类型
	 * @return 类型
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
	 * 如果为数字型，要求返回其小数点后的位数
	 * @return 小数位数
	 */
	public int getColumnDecimalDigits() {
		if (realColumn == null)
			return 0;
		return FieldsCollection.getField(realColumn).getDecimalDigits();
	}

	/**
	 * 该列的长度值
	 * @return 长度值
	 */
	public int getColumnLength() {
		if (realColumn == null)
			return 0;
		return FieldsCollection.getField(realColumn).getLength();
	}

	/**
	 * 如果是数字型，则根据长度和小数位数来显示
	 * @return 数字型显示模板
	 */
	public String getColumnNumberPattern() {
		if (realColumn == null)
			return null;
		return FieldsCollection.getField(realColumn).getNumberPattern();
	}

	/**
	 * 代码列的所有成员的KEY
	 * @return 所有成员的KEY
	 */
	public String[] getColumnCodeMembersKey() {
		String strCodeMemberKey = getColumnCodeKey();
		try {
			int nTemp = Integer.parseInt(strCodeMemberKey);
			return CodesManager.getCodeMembersKey(getColumnCodeKey());
		} catch (Exception e) {
			log.debug("代码为View:" + strCodeMemberKey);
			return getCodeMapKeys();
		}
	}

	/**
	 * 代码列的所有成员的值
	 * @return 所有成员的值
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
	 * 返回代码列的指定的值
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
	 * 初始化代码表中不存在的代码
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
	 * 返回该列代码的所有的KEY
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
	 * 返回该列代码的所有的值
	 * @return 所有的值
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
	 * @return 要显示的列
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
	 * @return 列的标注
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return 显示标志
	 */
	public String getShowFlag() {
		return showFlag;
	}

	/**
	 * @return 原始列名称
	 */
	public String getRealColumn() {
		return realColumn;
	}

	/**
	 * @return 列的显示顺序号
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * @return 列的URL
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
	 * @return 代码映射表（此列不满足CodesManager要求，而是在数据库中存在VIEW）
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
	 * @return 计算标志
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
	 * @return 返回该列显示的大小，如果该列在数据库定义中小于4，则返回为4
	 */
	public int getDisplaySize() {
		if (displaySize > 0)
			return displaySize;
		if (realColumn == null) {
			log.debug(queryColumn + "没有真实对应列，长度返回4");
			displaySize = 4;
			return displaySize;
		}
		if (isCodeColumn()) {
			log.debug(queryColumn + "代码列返回数组中最长的字符的长度");
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
			//计算实际长度，汉字按两个字节来计算
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
	 * 返回表头displayName的长度
	 * @return 表头displayName的长度
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
	 * 判断是不是为数字型列
	 * @return 是不是为数字型列
	 */
	public boolean isNumberColumn() {
		if (getColumnNumberPattern() != null)
			return true;
		return false;
	}
	

}

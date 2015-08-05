/*
 * @(#)Contents.java  2003-4-24
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

import com.idn.property.InitServletProperty;
import com.idn.property.PropertyManager;
import com.idn.sql.DataBean;
import com.idn.sql.DynaSqlBean;
import com.idn.util.CommonTools;
import com.idn.util.MissingParameterException;

/**
 * <P>影射查询表内容 </P>
 * 
 * @version 0.1
 * @author navy
 */
public class QueryFormula {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(QueryFormula.class);

	/**
	 * 用哈希表来存贮列的属性
	 */
	private Hashtable columns = null;

	/**
	 * 用哈希表来存贮request参数
	 */
	private Hashtable requestParam = null;

	/**
	 * 查询的名字
	 */
	private String queryName = null;

	/**
	 * 查询页中显示的窗口标题
	 */
	private String title = null;

	/**
	 * 查询页中显示的页面标题
	 */
	private String header = null;

	/**
	 * 要查询的表
	 */
	private String sqlTables = null;

	/**
	 * 查询语句中的WHERE语句部分，其中对于参数部分，其值用"?"代替
	 */
	private String sqlWhere = null;

	/**
	 * 查询结果集的排序部分
	 */
	private String sqlOrderBy = null;

	/**
	 * 查询语名中的Group By部分 
	 */
	private String sqlGroupBy = null;

	/**
	 * 页面导向设置，当查询没有传递pageName时，则从此列取值
	 * 此列可为ActionMapping的forward名称，也可以是jsp页面，
	 * 如果是jsp页面，则必须以“/”开头。
	 */
	private String forwardPage = null;

	/**
	 * 从request中追加过来的WHERE条件
	 */
	private String paramWhere = null;
	
	/**
	 * 页面设置
	 */
	private String pageType = null;

	/**
	 * 构造查询操作内容，建立列哈希表，其它值为null值
	 */
	public QueryFormula() {
		columns = null;
		queryName = null;
		title = null;
		header = null;
		sqlTables = null;
		sqlWhere = null;
		sqlOrderBy = null;
		sqlGroupBy = null;
		pageType = null;
	}

	/**
	 * 构造函数，根据queryPrefix来初始化查询时所需要的内容。
	 * 构造过程中出现的错误将被捕捉记录，未做任何处理
	 * @param queryPrefix
	 */
	public QueryFormula(String queryPrefix) {
		try {
			initContent(queryPrefix);
		} catch (QueryException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 向哈希表添加一列，该列是以它在本次查询中的顺序号为KEY
	 * @param column 要添加的列对象
	 */
	public void addColumn(Column column) {
		columns.put(column.getSequence(), column);
	}

	/**
	 * 从哈希表中删除指定的顺序号的列
	 * @param strSequence 顺序号
	 */
	public void removeColumn(String strSequence) {
		columns.remove(strSequence);
	}

	/**
	 * 从哈希表中删除指定的顺序号的列
	 * @param iSequence 顺序号
	 */
	public void removeColumn(int iSequence) {
		columns.remove(String.valueOf(iSequence));
	}

	/**
	 * 从哈希表中获取指定的顺序号的列
	 * @param iSequence 顺序号
	 * @return 指定的列
	 */
	public Column getColumn(int iSequence) {
		//循环是从0开始
		iSequence = iSequence + 1;
		return (Column) columns.get(String.valueOf(iSequence));
	}

	/**
	 * 从哈希表中获取指定的顺序号的列
	 * @param strSequence 顺序号
	 * @return 指定的列
	 */
	public Column getColumn(String strSequence) {
		return (Column) columns.get(strSequence);
	}

	/**
	 * 返回查询中的列数
	 * @return 查询列的个数
	 */
	public int size() {
		if (columns == null)
			return 0;
		return columns.size();
	}
	
	/**
	 * 是否需要打印
	 * @return 如果需要打印，则返回真
	 */
	public boolean isNeedPrint() {
		return Integer.parseInt(pageType) > 0;
	}
	
	/**
	 * 可以显示的列数
	 * @return 可以显示的列数
	 */
	public int getDisplayColumnCount() {
		if (columns == null)
			return 0;
		int nCount = 0;
		for (int i = 0; i < size(); i++) {
			if (getColumn(i).isDisplay())
				nCount++;
		}
		return nCount;
	}

	/**
	 * 判断SQL语句是否需要进行分组计算
	 * @return 需要进行分组计算，则为true
	 */
	public boolean isGroupByCompute() {
		return (getGroupByComputeDepth() > 0);
	}

	/**
	 * 判断SQL语句是否需要进行分组计算
	 * @return 分组计算的层数
	 */
	public int getGroupByComputeDepth() {
		if (sqlGroupBy.equals(""))
			return 0;
		try {
			return Integer.parseInt(sqlGroupBy);
		} catch (Exception e) {
			if (sqlGroupBy.length() > 0)
				log.warn("SQL计算深度设置错误：" + sqlGroupBy);
			return 0;
		}
	}

	/**
	 * 查询SQL语句中的GROUP BY中的所有的列,去除With ROLLUP或With CUBE后生成的数组
	 * @return Group By语句中的所有的列组成的数组
	 */
	public String[] getSqlGroupByColumns() {
		ArrayList al = new ArrayList();
		for (int i = 0; i < size(); i++) {
			if (getColumn(i).isGruopByColumn())
				al.add(getColumn(i).getQueryColumn());
		}
		String[] groupByColumn = (String[]) al.toArray(new String[al.size()]);
		return groupByColumn;
	}

	/**
	 * 判断该列是不是Group By中的列
	 * @param sequence 要查询列的序号
	 * @return 如果是Group By中的列，则返回true
	 */
	public boolean isGroupByColumn(String sequence) {
		log.debug(sequence);
		return getColumn(sequence).isGruopByColumn();
	}

	/**
	 * 判断该列是不是Group By中的列
	 * @param sequence 要查询列的序号
	 * @return 如果是Group By中的列，则返回true
	 */
	public boolean isGroupByColumn(int sequence) {
		sequence = sequence + 1;
		return isGroupByColumn(Integer.toString(sequence));
	}

	/**
	 * 判断是否需要追加GROUP BY条件
	 * @return 追加GROUP BY 标志
	 */
	public boolean isNeedGroupBy() {
		for (int i = 0; i < size(); i++) {
			if (getColumn(i).getComputeName() != null)
				return true;
		}
		return false;
	}

	/**
	 * 组合SQL语句，在SQL语句中增加了URL列的值，这些列别名为<BR>
	 * “URL列名” + “_” + “URL”<BR>
	 * 所组成的若干列
	 * @return SQL语句
	 * @throws QueryException 处理SQL语句不合法时，如“SELECT”与“FROM”之间不存在实际列时的错误等
	 * @throws MissingParameterException 从HttpServletRequest中获取参数错误时抛出此错误
	 */
	public String getSql() throws QueryException, MissingParameterException {

		if (queryName == null)
			throw new QueryException("请先运行initContent(String)来初始化Content Bean!");
		if (columns == null)
			initContent(queryName);

		StringBuffer sbSql = new StringBuffer();
		sbSql.append("SELECT ");
		//获取查询列
		for (int i = 0; i < size(); i++) {
			//查询列的顺序在数据库中是以1开始
			log.debug("Sequence: " + i);
			if (getColumn(i) != null
				&& getColumn(i).getQueryColumn().trim().length() > 0) {
				sbSql.append(getColumn(i).getQueryColumn());
				if (getColumn(i).isUrlColumn()) {
					sbSql
						.append(", ")
						.append(parseColumnUrl(getColumn(i).getUrl().trim()))
						.append(" AS ")
						.append(getColumn(i).getQueryColumnName().trim())
						.append("_URL");
				}
				if (isNeedGroupBy()&&isGroupByCompute()&&isGroupByColumn(i)) {
					sbSql
						.append(", GROUPING(")
						.append(getColumn(i).getQueryColumnName().trim())
						.append(") AS GROUPING_")
						.append(getColumn(i).getQueryColumnName().trim());
				}
				if (i < size() - 1)
					sbSql.append(", ");
				else 
					sbSql.append(" ");
			} else {
				throw new QueryException("组合SQL语句时出现错误，不能获取第" + i + "查询列的名字");
			}
		}
		//查询表部分
		if (sqlTables == null || sqlTables.trim().equals(""))
			throw new QueryException("组合SQL语句时出现错误，不能获取要查询表的名字");
		sbSql.append("FROM ").append(sqlTables).append(" ");
		//WHERE语名部分
		paramWhere = (String) requestParam.get("queryWhere");
		if (paramWhere != null)
			sbSql.append("WHERE ").append(paramWhere).append(" ");

		if (sqlWhere != null && !sqlWhere.trim().equals("")) {
			if (paramWhere == null)
				sbSql.append("WHERE ").append(sqlWhere).append(" ");
			else
				sbSql.append("AND ").append(sqlWhere).append(" ");
		}

		//Group by 部分
		if (isNeedGroupBy()) {
			sbSql.append("GROUP BY ");
			String[] groupByColumns = getSqlGroupByColumns();
			for (int i = 0; i < groupByColumns.length; i++) {
				sbSql.append(groupByColumns[i]);
				if (i < groupByColumns.length - 1)
					sbSql.append(", ");
				else
					sbSql.append(" ");
			}
		}

		if (isGroupByCompute()) {
			if (isNeedGroupBy()) {
				sbSql.append("WITH ROLLUP ");
			} else {
				throw new QueryException("组合SQL语句时出现错误，没有GROUP BY的需求，但需要进行分组计算");
			}
		}

		//Order by 部分，对于有计算要求的列，优先按GRUOP BY排序列进行排序
		if (isGroupByCompute() && isNeedGroupBy()) {
			sbSql.append("ORDER BY ");
			String[] groupByColumns = getSqlGroupByColumns();
			for (int i = 0; i < groupByColumns.length; i++) {
				sbSql.append(groupByColumns[i]);
				if (i < groupByColumns.length - 1)
					sbSql.append(", ");
				else
					sbSql.append(" ");
			}
		} else {
			if (sqlOrderBy != null && !sqlOrderBy.trim().equals(""))
				sbSql.append("ORDER BY ").append(sqlOrderBy).append(" ");
		}
		log.debug(sbSql.toString());
		return sbSql.toString();
	}

	/**
	 * 初始化Content内容
	 * @param queryName
	 */
	public void initContent(String queryName) throws QueryException {
		//初始化查询名称
		this.queryName = queryName;
		DataBean db = new DataBean();
		DynaSqlBean dsb = new DynaSqlBean();
		String[] param = new String[] { queryName };
		try {
			//初始化Contant各个属性
			String strSql = QueryProperties.getProperty("content.sql");
			if (strSql == null)
				throw new QueryException("没有找到content.sql语名，请检查查询配置文件com.idn.query.query");
			dsb.setParam(param);
			dsb.setSql(strSql);
			db.setCrs(dsb.executeQuery());
			if (db.getRowCount() == 0)
				throw new QueryException("没有找到指定的查询：" + queryName);
			this.title = db.getElementValue(0, 0).trim();
			this.header = this.title;
			this.sqlTables = db.getElementValue(0, 1).trim();
			this.sqlWhere = db.getElementValue(0, 2).trim();
			this.sqlGroupBy = db.getElementValue(0, 3).trim();
			this.sqlOrderBy = db.getElementValue(0, 4).trim();
			this.forwardPage = db.getElementValue(0, 5).trim();
			this.pageType = db.getElementValue(0, 6).trim();
			db.release();

			//初始化查询的列
			strSql = QueryProperties.getProperty("columns.sql");
			if (strSql == null)
				throw new QueryException("没有找到columns.sql语名，请检查查询配置文件com.idn.query.query");
			dsb.setSql(strSql);
			dsb.setParam(param);
			db.setCrs(dsb.executeQuery());
			if (db.getRowCount() == 0)
				throw new QueryException("没有找到指定的查询" + queryName + "中的列！");
			this.columns = new Hashtable();
			for (int i = 0; i < db.getRowCount(); i++) {
				Column column = new Column();
				column.setSequence(db.getElementValue(i, 0).trim());
				column.setQueryColumn(db.getElementValue(i, 1).trim());
				column.setDisplayName(db.getElementValue(i, 2).trim());
				column.setRealColumn(db.getElementValue(i, 3).trim());
				column.setShowFlag(db.getElementValue(i, 4).trim());
				column.setUrl(db.getElementValue(i, 5).trim());
				column.setComputeFlag(db.getElementValue(i, 6).trim());
				addColumn(column);
			}
			db.release();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new QueryException("执行SQL语句时出错！");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new QueryException("执行SQL时，未能捕捉到的异常！");
		}

	}

	/**
	 * 解析URL，将其中的参数赋于实际值或列名
	 * @param req HttpServletRequest请求
	 * @param strUrl URL的值
	 * @return 返回处理过的URL
	 * @throws MissingParameterException 从HttpServletRequest中获取参数错误时抛出此错误 
	 */
	private String parseColumnUrl(String strUrl)
		throws MissingParameterException {
		StringBuffer sbUrl = new StringBuffer();
		String strAdditionSymbol =
			PropertyManager.getProperties().getProperty(
				"db.string.addition.symbol");
		sbUrl.append("'");
		strUrl = CommonTools.stringReplace(strUrl, "'", "''");
		//如果URL以根绝对路径开头，则在其前面自动加上下文
		if (strUrl.startsWith("/")) {
			sbUrl.append("/").append(InitServletProperty.getContextName());
		}
		//如果找不到参数的分割值，则返回URL
		if (strUrl.indexOf("`") == -1)
			return sbUrl.append(strUrl).append("'").toString();

		StringTokenizer strTokens = new StringTokenizer(strUrl, "`");
		int iParamCount = strTokens.countTokens();
		String[] strUrlElements = new String[iParamCount];
		for (int i = 0; i < strUrlElements.length; i++) {
			strUrlElements[i] = strTokens.nextToken().trim();
		}
		//处理URL中的参数
		sbUrl.append(strUrlElements[0]);
		for (int i = 1; i < strUrlElements.length; i++) {
			if (i % 2 != 0) {
				String strTemp = strUrlElements[i].trim();
				if (strTemp.startsWith("=")) {
					//列变量
					strTemp = strTemp.substring(1);
					strUrlElements[i] = "'" + strAdditionSymbol + strTemp;
					sbUrl.append(strUrlElements[i]);
					if (i < strUrlElements.length - 1)
						sbUrl.append(strAdditionSymbol).append("'");
				} else {
					//取参数
					strUrlElements[i] =
						(String) requestParam.get(strUrlElements[i]);
					if (strUrlElements[i] == null)
						throw new MissingParameterException("找不到参数：" + strTemp);
					sbUrl.append(strUrlElements[i].trim());
					if (i == strUrlElements.length - 1)
						sbUrl.append("'");
				}
			} else {
				sbUrl.append(strUrlElements[i]);
				if (i == strUrlElements.length - 1)
					sbUrl.append("'");
			}
		}
		return sbUrl.toString();
	}

	/**
	 * @return 列的哈希表
	 */
	public Hashtable getColumns() {
		return columns;
	}

	/**
	 * @return 页标题
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @return 查询名称
	 */
	public String getQueryName() {
		return queryName;
	}

	/**
	 * @return Group By条件
	 */
	public String getSqlGroupBy() {
		return sqlGroupBy;
	}

	/**
	 * @return Order By 条件
	 */
	public String getSqlOrderBy() {
		return sqlOrderBy;
	}

	/**
	 * @return 查询的表
	 */
	public String getSqlTables() {
		return sqlTables;
	}

	/**
	 * @return Where条件
	 */
	public String getSqlWhere() {
		return sqlWhere;
	}

	/**
	 * @return 页标题行
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param hashtable
	 */
	public void setColumns(Hashtable hashtable) {
		columns = hashtable;
	}

	/**
	 * @param string
	 */
	public void setHeader(String string) {
		header = string;
	}

	/**
	 * @param string
	 */
	public void setQueryName(String string) {
		queryName = string;
	}

	/**
	 * @param string
	 */
	public void setSqlGroupBy(String string) {
		sqlGroupBy = string;
	}

	/**
	 * @param string
	 */
	public void setSqlOrderBy(String string) {
		sqlOrderBy = string;
	}

	/**
	 * @param string
	 */
	public void setSqlTables(String string) {
		sqlTables = string;
	}

	/**
	 * @param string
	 */
	public void setSqlWhere(String string) {
		sqlWhere = string;
	}

	/**
	 * @param string
	 */
	public void setTitle(String string) {
		title = string;
	}

	/**
	 * @return 参数的哈希表
	 */
	public Hashtable getRequestParam() {
		return requestParam;
	}

	/**
	 * @param hashtable
	 */
	public void setRequestParam(Hashtable hashtable) {
		requestParam = hashtable;
	}

	/**
	 * @return 导向页面
	 */
	public String getForwardPage() {
		return forwardPage;
	}

	/**
	 * @param string
	 */
	public void setForwardPage(String string) {
		forwardPage = string;
	}

	/**
	 * @return request中的WHERE条件
	 */
	public String getParamWhere() {
		if (paramWhere == null)
			paramWhere = (String) requestParam.get("queryWhere");
		return paramWhere;
	}

	/**
	 * @param string
	 */
	public void setParamWhere(String string) {
		paramWhere = string;
	}

	/**
	 * @return 页面设置类型
	 */
	public String getPageType() {
		return pageType;
	}

	/**
	 * @param string
	 */
	public void setPageType(String string) {
		pageType = string;
	}

}

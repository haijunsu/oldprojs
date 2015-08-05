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
 * <P>Ӱ���ѯ������ </P>
 * 
 * @version 0.1
 * @author navy
 */
public class QueryFormula {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(QueryFormula.class);

	/**
	 * �ù�ϣ���������е�����
	 */
	private Hashtable columns = null;

	/**
	 * �ù�ϣ��������request����
	 */
	private Hashtable requestParam = null;

	/**
	 * ��ѯ������
	 */
	private String queryName = null;

	/**
	 * ��ѯҳ����ʾ�Ĵ��ڱ���
	 */
	private String title = null;

	/**
	 * ��ѯҳ����ʾ��ҳ�����
	 */
	private String header = null;

	/**
	 * Ҫ��ѯ�ı�
	 */
	private String sqlTables = null;

	/**
	 * ��ѯ����е�WHERE��䲿�֣����ж��ڲ������֣���ֵ��"?"����
	 */
	private String sqlWhere = null;

	/**
	 * ��ѯ����������򲿷�
	 */
	private String sqlOrderBy = null;

	/**
	 * ��ѯ�����е�Group By���� 
	 */
	private String sqlGroupBy = null;

	/**
	 * ҳ�浼�����ã�����ѯû�д���pageNameʱ����Ӵ���ȡֵ
	 * ���п�ΪActionMapping��forward���ƣ�Ҳ������jspҳ�棬
	 * �����jspҳ�棬������ԡ�/����ͷ��
	 */
	private String forwardPage = null;

	/**
	 * ��request��׷�ӹ�����WHERE����
	 */
	private String paramWhere = null;
	
	/**
	 * ҳ������
	 */
	private String pageType = null;

	/**
	 * �����ѯ�������ݣ������й�ϣ������ֵΪnullֵ
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
	 * ���캯��������queryPrefix����ʼ����ѯʱ����Ҫ�����ݡ�
	 * ��������г��ֵĴ��󽫱���׽��¼��δ���κδ���
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
	 * ���ϣ�����һ�У������������ڱ��β�ѯ�е�˳���ΪKEY
	 * @param column Ҫ��ӵ��ж���
	 */
	public void addColumn(Column column) {
		columns.put(column.getSequence(), column);
	}

	/**
	 * �ӹ�ϣ����ɾ��ָ����˳��ŵ���
	 * @param strSequence ˳���
	 */
	public void removeColumn(String strSequence) {
		columns.remove(strSequence);
	}

	/**
	 * �ӹ�ϣ����ɾ��ָ����˳��ŵ���
	 * @param iSequence ˳���
	 */
	public void removeColumn(int iSequence) {
		columns.remove(String.valueOf(iSequence));
	}

	/**
	 * �ӹ�ϣ���л�ȡָ����˳��ŵ���
	 * @param iSequence ˳���
	 * @return ָ������
	 */
	public Column getColumn(int iSequence) {
		//ѭ���Ǵ�0��ʼ
		iSequence = iSequence + 1;
		return (Column) columns.get(String.valueOf(iSequence));
	}

	/**
	 * �ӹ�ϣ���л�ȡָ����˳��ŵ���
	 * @param strSequence ˳���
	 * @return ָ������
	 */
	public Column getColumn(String strSequence) {
		return (Column) columns.get(strSequence);
	}

	/**
	 * ���ز�ѯ�е�����
	 * @return ��ѯ�еĸ���
	 */
	public int size() {
		if (columns == null)
			return 0;
		return columns.size();
	}
	
	/**
	 * �Ƿ���Ҫ��ӡ
	 * @return �����Ҫ��ӡ���򷵻���
	 */
	public boolean isNeedPrint() {
		return Integer.parseInt(pageType) > 0;
	}
	
	/**
	 * ������ʾ������
	 * @return ������ʾ������
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
	 * �ж�SQL����Ƿ���Ҫ���з������
	 * @return ��Ҫ���з�����㣬��Ϊtrue
	 */
	public boolean isGroupByCompute() {
		return (getGroupByComputeDepth() > 0);
	}

	/**
	 * �ж�SQL����Ƿ���Ҫ���з������
	 * @return �������Ĳ���
	 */
	public int getGroupByComputeDepth() {
		if (sqlGroupBy.equals(""))
			return 0;
		try {
			return Integer.parseInt(sqlGroupBy);
		} catch (Exception e) {
			if (sqlGroupBy.length() > 0)
				log.warn("SQL����������ô���" + sqlGroupBy);
			return 0;
		}
	}

	/**
	 * ��ѯSQL����е�GROUP BY�е����е���,ȥ��With ROLLUP��With CUBE�����ɵ�����
	 * @return Group By����е����е�����ɵ�����
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
	 * �жϸ����ǲ���Group By�е���
	 * @param sequence Ҫ��ѯ�е����
	 * @return �����Group By�е��У��򷵻�true
	 */
	public boolean isGroupByColumn(String sequence) {
		log.debug(sequence);
		return getColumn(sequence).isGruopByColumn();
	}

	/**
	 * �жϸ����ǲ���Group By�е���
	 * @param sequence Ҫ��ѯ�е����
	 * @return �����Group By�е��У��򷵻�true
	 */
	public boolean isGroupByColumn(int sequence) {
		sequence = sequence + 1;
		return isGroupByColumn(Integer.toString(sequence));
	}

	/**
	 * �ж��Ƿ���Ҫ׷��GROUP BY����
	 * @return ׷��GROUP BY ��־
	 */
	public boolean isNeedGroupBy() {
		for (int i = 0; i < size(); i++) {
			if (getColumn(i).getComputeName() != null)
				return true;
		}
		return false;
	}

	/**
	 * ���SQL��䣬��SQL�����������URL�е�ֵ����Щ�б���Ϊ<BR>
	 * ��URL������ + ��_�� + ��URL��<BR>
	 * ����ɵ�������
	 * @return SQL���
	 * @throws QueryException ����SQL��䲻�Ϸ�ʱ���硰SELECT���롰FROM��֮�䲻����ʵ����ʱ�Ĵ����
	 * @throws MissingParameterException ��HttpServletRequest�л�ȡ��������ʱ�׳��˴���
	 */
	public String getSql() throws QueryException, MissingParameterException {

		if (queryName == null)
			throw new QueryException("��������initContent(String)����ʼ��Content Bean!");
		if (columns == null)
			initContent(queryName);

		StringBuffer sbSql = new StringBuffer();
		sbSql.append("SELECT ");
		//��ȡ��ѯ��
		for (int i = 0; i < size(); i++) {
			//��ѯ�е�˳�������ݿ�������1��ʼ
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
				throw new QueryException("���SQL���ʱ���ִ��󣬲��ܻ�ȡ��" + i + "��ѯ�е�����");
			}
		}
		//��ѯ����
		if (sqlTables == null || sqlTables.trim().equals(""))
			throw new QueryException("���SQL���ʱ���ִ��󣬲��ܻ�ȡҪ��ѯ�������");
		sbSql.append("FROM ").append(sqlTables).append(" ");
		//WHERE��������
		paramWhere = (String) requestParam.get("queryWhere");
		if (paramWhere != null)
			sbSql.append("WHERE ").append(paramWhere).append(" ");

		if (sqlWhere != null && !sqlWhere.trim().equals("")) {
			if (paramWhere == null)
				sbSql.append("WHERE ").append(sqlWhere).append(" ");
			else
				sbSql.append("AND ").append(sqlWhere).append(" ");
		}

		//Group by ����
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
				throw new QueryException("���SQL���ʱ���ִ���û��GROUP BY�����󣬵���Ҫ���з������");
			}
		}

		//Order by ���֣������м���Ҫ����У����Ȱ�GRUOP BY�����н�������
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
	 * ��ʼ��Content����
	 * @param queryName
	 */
	public void initContent(String queryName) throws QueryException {
		//��ʼ����ѯ����
		this.queryName = queryName;
		DataBean db = new DataBean();
		DynaSqlBean dsb = new DynaSqlBean();
		String[] param = new String[] { queryName };
		try {
			//��ʼ��Contant��������
			String strSql = QueryProperties.getProperty("content.sql");
			if (strSql == null)
				throw new QueryException("û���ҵ�content.sql�����������ѯ�����ļ�com.idn.query.query");
			dsb.setParam(param);
			dsb.setSql(strSql);
			db.setCrs(dsb.executeQuery());
			if (db.getRowCount() == 0)
				throw new QueryException("û���ҵ�ָ���Ĳ�ѯ��" + queryName);
			this.title = db.getElementValue(0, 0).trim();
			this.header = this.title;
			this.sqlTables = db.getElementValue(0, 1).trim();
			this.sqlWhere = db.getElementValue(0, 2).trim();
			this.sqlGroupBy = db.getElementValue(0, 3).trim();
			this.sqlOrderBy = db.getElementValue(0, 4).trim();
			this.forwardPage = db.getElementValue(0, 5).trim();
			this.pageType = db.getElementValue(0, 6).trim();
			db.release();

			//��ʼ����ѯ����
			strSql = QueryProperties.getProperty("columns.sql");
			if (strSql == null)
				throw new QueryException("û���ҵ�columns.sql�����������ѯ�����ļ�com.idn.query.query");
			dsb.setSql(strSql);
			dsb.setParam(param);
			db.setCrs(dsb.executeQuery());
			if (db.getRowCount() == 0)
				throw new QueryException("û���ҵ�ָ���Ĳ�ѯ" + queryName + "�е��У�");
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
			throw new QueryException("ִ��SQL���ʱ����");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new QueryException("ִ��SQLʱ��δ�ܲ�׽�����쳣��");
		}

	}

	/**
	 * ����URL�������еĲ�������ʵ��ֵ������
	 * @param req HttpServletRequest����
	 * @param strUrl URL��ֵ
	 * @return ���ش������URL
	 * @throws MissingParameterException ��HttpServletRequest�л�ȡ��������ʱ�׳��˴��� 
	 */
	private String parseColumnUrl(String strUrl)
		throws MissingParameterException {
		StringBuffer sbUrl = new StringBuffer();
		String strAdditionSymbol =
			PropertyManager.getProperties().getProperty(
				"db.string.addition.symbol");
		sbUrl.append("'");
		strUrl = CommonTools.stringReplace(strUrl, "'", "''");
		//���URL�Ը�����·����ͷ��������ǰ���Զ���������
		if (strUrl.startsWith("/")) {
			sbUrl.append("/").append(InitServletProperty.getContextName());
		}
		//����Ҳ��������ķָ�ֵ���򷵻�URL
		if (strUrl.indexOf("`") == -1)
			return sbUrl.append(strUrl).append("'").toString();

		StringTokenizer strTokens = new StringTokenizer(strUrl, "`");
		int iParamCount = strTokens.countTokens();
		String[] strUrlElements = new String[iParamCount];
		for (int i = 0; i < strUrlElements.length; i++) {
			strUrlElements[i] = strTokens.nextToken().trim();
		}
		//����URL�еĲ���
		sbUrl.append(strUrlElements[0]);
		for (int i = 1; i < strUrlElements.length; i++) {
			if (i % 2 != 0) {
				String strTemp = strUrlElements[i].trim();
				if (strTemp.startsWith("=")) {
					//�б���
					strTemp = strTemp.substring(1);
					strUrlElements[i] = "'" + strAdditionSymbol + strTemp;
					sbUrl.append(strUrlElements[i]);
					if (i < strUrlElements.length - 1)
						sbUrl.append(strAdditionSymbol).append("'");
				} else {
					//ȡ����
					strUrlElements[i] =
						(String) requestParam.get(strUrlElements[i]);
					if (strUrlElements[i] == null)
						throw new MissingParameterException("�Ҳ���������" + strTemp);
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
	 * @return �еĹ�ϣ��
	 */
	public Hashtable getColumns() {
		return columns;
	}

	/**
	 * @return ҳ����
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @return ��ѯ����
	 */
	public String getQueryName() {
		return queryName;
	}

	/**
	 * @return Group By����
	 */
	public String getSqlGroupBy() {
		return sqlGroupBy;
	}

	/**
	 * @return Order By ����
	 */
	public String getSqlOrderBy() {
		return sqlOrderBy;
	}

	/**
	 * @return ��ѯ�ı�
	 */
	public String getSqlTables() {
		return sqlTables;
	}

	/**
	 * @return Where����
	 */
	public String getSqlWhere() {
		return sqlWhere;
	}

	/**
	 * @return ҳ������
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
	 * @return �����Ĺ�ϣ��
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
	 * @return ����ҳ��
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
	 * @return request�е�WHERE����
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
	 * @return ҳ����������
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

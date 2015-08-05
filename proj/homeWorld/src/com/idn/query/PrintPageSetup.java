/*
 * @(#)PrintPageSetup.java  2003-6-19
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.query;

import java.sql.SQLException;
import java.util.Hashtable;

import com.idn.sql.DataBean;
import com.idn.sql.DynaSqlBean;

/**
 * <P> ��ӡ��ѯ����Ĳ������� </P>
 * 
 * @version 0.1
 * @author navy
 */
public class PrintPageSetup {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(PrintPageSetup.class);

	/**
	 * Ĭ�ϵ������С
	 */
	private String defaluFontSize = "14";

	/**
	 * ����ҳ���ӡ���Լ��ϣ�keyΪfontSize
	 */
	private Hashtable hsPrintParameters = null;

	/**
	 * ҳ���ӡ���Լ��������������С����Ϣ
	 */
	private String[] fontSize = null;

	/**
	 * ��ѯѡ��Ĵ�ӡֽ����
	 */
	private String pageType = null;

	/**
	 * ���캯����û�ж�pageType����
	 */
	public PrintPageSetup() {
		this.hsPrintParameters = new Hashtable();
	}

	/**
	 * ���캯������pageType���ã�����ʼ����ӡҳ�����Լ���
	 * @param pageType ��ӡֽ����
	 */
	public PrintPageSetup(String pageType) {
		this.pageType = pageType;
		this.hsPrintParameters = new Hashtable();
		initPrintParameter();
	}

	/**
	 * ��ʼ����ӡ�Ĳ���
	 */
	private void initPrintParameter() {
		DataBean db = new DataBean();
		DynaSqlBean dsb = new DynaSqlBean();

		//SALAPAR: �Ѿ��������KEY
		//SALADIV: �Ѿ��������KEY
		//SALAVAL1: ֽ������
		//SALAVAL2: ����ֵ
		//SALADATE: ��� 0000�ֺ�01-ÿ������
		//				 0000�ֺ�02-��ҳ����
		//				 0000�ֺ�03-����ҳ����
		//				 0000�ֺ�04-�����С
		String strSql =
			"SELECT SALAVAL1, "
				+ "SALAVAL2, "
				+ "SALADATE "
				+ "FROM SALARYPA "
				+ "WHERE SALADATE<'00009000' AND SALAVAL1=? "
				+ "ORDER BY SALAVAL1, SALADATE";
		dsb.setSql(strSql);
		dsb.setParam(pageType);
		try {
			db.setCrs(dsb.executeQuery());
			log.debug("�ڲ�������ȡ��ֽ�Ų�����¼������" + db.getRowCount());
			//��������ֵ
			fontSize = new String[db.getRowCount() / 4];
			for (int i = 0; i < db.getRowCount(); i += 4) {
				PrintParameter printParameter = new PrintParameter();
				try {
					log.debug(
						pageType
							+ "��ֽ"
							+ db.getElementValue(i + 3, "SALAVAL2").trim()
							+ "����ÿ�пɴ�ӡ������"
							+ db.getElementValue(i, "SALAVAL2").trim());
					printParameter.setLineWordCount(
						Integer.parseInt(
							db.getElementValue(i, "SALAVAL2").trim()));
				} catch (NumberFormatException nfe) {
					printParameter.setLineWordCount(80);
					log.warn("ÿ�е�������������Ϊ80");
					log.warn(db.getElementValue(i, "SALAVAL2").trim(), nfe);
				}

				try {
					log.debug(
						pageType
							+ "��ֽ"
							+ db.getElementValue(i + 3, "SALAVAL2").trim()
							+ "������ҳ��������"
							+ db.getElementValue(i + 1, "SALAVAL2").trim());
					printParameter.setFirstPageRows(
						Integer.parseInt(
							db.getElementValue(i + 1, "SALAVAL2").trim()));
				} catch (NumberFormatException nfe) {
					printParameter.setFirstPageRows(30);
					log.warn("��ҳ��������������Ϊ30");
					log.warn(db.getElementValue(i + 1, "SALAVAL2").trim(), nfe);
				}

				try {
					log.debug(
						pageType
							+ "��ֽ"
							+ db.getElementValue(i + 3, "SALAVAL2").trim()
							+ "��������ҳ��������"
							+ db.getElementValue(i + 2, "SALAVAL2").trim());
					printParameter.setOtherPageRows(
						Integer.parseInt(
							db.getElementValue(i + 2, "SALAVAL2").trim()));
				} catch (NumberFormatException nfe) {
					printParameter.setOtherPageRows(30);
					log.warn("ÿҳ��������������Ϊ30");
					log.warn(db.getElementValue(i + 2, "SALAVAL2").trim(), nfe);
				}
				fontSize[i / 4] = db.getElementValue(i + 3, "SALAVAL2").trim();
				log.debug(fontSize[i / 4]);
				hsPrintParameters.put(
					db.getElementValue(i + 3, "SALAVAL2").trim(),
					printParameter);
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

	}

	/**
	 * ���ض���������ɹ�ѡ��
	 * @return �ɴ�ѡ�������
	 */
	public int size() {
		return hsPrintParameters.size();
	}

	/**
	 * ����Ĭ�����������Լ����е�λ��
	 */
	public int getCurrentPosition() {
		for (int i = 0; i < size(); i++) {
			if (fontSize[i].equals(defaluFontSize))
				return i;
		}
		return 0;
	}

	/**
	 * ����ָ���Ĵ�ӡ������
	 * @param i ָ����λ��
	 * @return ��ӡ������
	 */
	public PrintParameter getPrintParameter(int i) throws QueryException {
		PrintParameter pp = (PrintParameter) hsPrintParameters.get(fontSize[i]);
		if (pp == null) {
			throw new QueryException("ֽ�Ų�������Ϊ�գ����ܽ��д�ӡ");
		}
		return pp;
	}

	/**
	 * ����ָ���Ĵ�ӡ������
	 * @param strFontSize ָ���������С
	 * @return ��ӡ������
	 */
	public PrintParameter getPrintParameter(String strFontSize)
		throws QueryException {
		PrintParameter pp = (PrintParameter) hsPrintParameters.get(strFontSize);
		if (pp == null) {
			throw new QueryException("ֽ�Ų�������Ϊ�գ����ܽ��д�ӡ");
		}
		return pp;
	}

	/**
	 * @return Ĭ�������С
	 */
	public String getDefaluFontSize() {
		return defaluFontSize;
	}

	/**
	 * @return �����С�ļ���
	 */
	public String[] getFontSize() {
		return fontSize;
	}

	/**
	 * @return �����������
	 */
	public Hashtable getHsPrintParameters() {
		if (hsPrintParameters == null || hsPrintParameters.size() == 0)
			log.error("ֽ�Ų�������Ϊ�գ����ܽ��д�ӡ");
		return hsPrintParameters;
	}

	/**
	 * @return ��ӡҳ������
	 */
	public String getPageType() {
		return pageType;
	}

	/**
	 * @param string
	 */
	public void setDefaluFontSize(String string) {
		defaluFontSize = string;
	}

	/**
	 * @param strings
	 */
	public void setFontSize(String[] strings) {
		fontSize = strings;
	}

	/**
	 * @param hashtable
	 */
	public void setHsPrintParameters(Hashtable hashtable) {
		hsPrintParameters = hashtable;
	}

	/**
	 * @param string
	 */
	public void setPageType(String string) {
		pageType = string;
		initPrintParameter();
	}

}

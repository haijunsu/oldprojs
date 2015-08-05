/*
 * @(#)SaveToExcel.java  2003-8-28
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package com.idn.print;

import java.io.File;
import java.io.IOException;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.WritableWorkbook;

import com.idn.util.CommonTools;

/**
 * <P>������ɹ����浽EXCEL�ļ�</P>
 * 
 * @version 0.1
 * @author navy
 */
public class SaveToExcel {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(SaveToExcel.class);

	/**
	 * Ҫ����Excel������
	 */
	private Contents m_contents = null;

	/**
	 * �ļ�����
	 */
	private String m_fileName = null;

	/**
	 * ����ļ��Ѿ����ڣ��Ƿ�Ҫ����
	 */
	private boolean isOverWrite = false;

	/**
	 * ʵ����
	 * @param contents
	 * @param strFileName
	 */
	public SaveToExcel() {
	}

	/**
	 * ʵ����
	 * @param contents
	 * @param strFileName
	 */
	public SaveToExcel(Contents contents, String strFileName) {
		m_contents = contents;
		m_fileName = strFileName;
	}

	/**
	 * �����ݴ洢��EXCEL�ļ���
	 */
	public void save() throws PrintException {
		save(m_contents, m_fileName);
	}

	/**
	 * �����ݴ洢��EXCEL�ļ���
	 * @param contents ����
	 * @param strFileName �ļ�����
	 */
	public void save(Contents contents, String strFileName)
		throws PrintException {
		save(contents, strFileName, isOverWrite);
	}

	/**
	 * �����ݴ洢��EXCEL�ļ���
	 * @param contents
	 * @param strFileName
	 * @param isOverWrite
	 * @return �洢�Ƿ�ɹ�
	 */
	public void save(
		Contents contents,
		String strFileName,
		boolean isOverWrite)
		throws PrintException {
		if (contents == null) {
			log.error("û�����ݿ��Դ洢�����ȳ�ʼ��contents.");
			throw new PrintException("û�����ݿ��Դ洢��");
		}
		if (strFileName == null) {
			log.error("û��ָ���ļ����ƣ����ȳ�ʼ��strFileName.");
			throw new PrintException("û��ָ���ļ����ƣ�");
		}
		File file = new File(strFileName);
		if (!file.getParentFile().exists()) {
			log.error("�Ҳ���·����" + file.getParent());
			file = null;
			throw new PrintException("�Ҳ���·����");
		}

		if (file.exists() && !isOverWrite) {
			log.error(strFileName + "�ļ��Ѿ����ڣ�");
			throw new PrintException("�ļ��Ѿ����ڣ�");
		}

		WritableWorkbook wwb = null;

		try {
			wwb = jxl.Workbook.createWorkbook(file);
			jxl.write.WritableSheet ws =
				wwb.createSheet(contents.getTitle(), 0);
			int nCol = 0;
			int nRow = 0;
			jxl.write.Label labelC = null;
			jxl.write.Number labelN = null;
			jxl.write.NumberFormat[] nf =
				new jxl.write.NumberFormat[contents.getColumnCount()];
			jxl.write.WritableFont wf = null;
			jxl.write.WritableCellFormat wcf = null;
			String strTemp = "";
			double ddTemp = 0.0;

			wf =
				new jxl.write.WritableFont(
					jxl.write.WritableFont.ARIAL,
					18,
					jxl.write.WritableFont.BOLD,
					true);
			wcf = new jxl.write.WritableCellFormat(wf);
			wcf.setAlignment(jxl.format.Alignment.CENTRE);
			labelC = new jxl.write.Label(nCol, nRow, contents.getTitle(), wcf);
			ws.addCell(labelC);
			nRow++;

			wf =
				new jxl.write.WritableFont(
					jxl.write.WritableFont.ARIAL,
					12,
					jxl.write.WritableFont.NO_BOLD,
					false);
			wcf = new jxl.write.WritableCellFormat(wf);
			wcf.setBackground(jxl.format.Colour.GREY_25_PERCENT);
			wcf.setAlignment(jxl.format.Alignment.CENTRE);
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
			log.debug("��/�У� " + contents.getColumnCount() + "/" + contents.getRowCount());
			//д��ͷ
			for (int k = 0; k < contents.getColumnCount(); k++) {
				if (contents.getColumn(k).isShow()) {
					labelC =
						new jxl.write.Label(
							nCol,
							nRow,
							contents.getColumn(k).getLabel(),
							wcf);
					ws.addCell(labelC);
					nCol++;
				}
			}
			//�ϲ�������
			if (nCol > 1)
				ws.mergeCells(0, 0, nCol - 1, 0);

			//���еĸ�ʽ
			for (int i = 0; i < nf.length; i++) {
				strTemp = contents.getColumn(i).getNumberPattern();
				if (strTemp != null)
					nf[i] = new jxl.write.NumberFormat(strTemp);
			}
			//д����
			int nRealCol = 0;
			for (int i = 0; i < contents.getRowCount(); i++) {
				nRow++;
				nCol = 0;
				for (int j = 0; j < contents.getColumnCount(); j++) {
					if (contents.getColumn(j).isShow()) {
						nRealCol = contents.getColumn(j).getIndex();
						strTemp = contents.getElement(i, nRealCol);
						if (!contents.getColumn(j).isString()) {
							//����getSequenceColumnHtmlCode(j, i)
							try {
								strTemp =
									CommonTools.stringReplace(strTemp, ",", "");
								ddTemp = Double.parseDouble(strTemp);
							} catch (NumberFormatException e) {
								log.error(j + "/" + i + "���ִ���", e);
								ddTemp = 0.0;
							}
							wcf = new jxl.write.WritableCellFormat(wf, nf[j]);
							wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
							labelN =
								new jxl.write.Number(nCol, nRow, ddTemp, wcf);
							ws.addCell(labelN);
						} else {
							//�ַ�
							wcf = new jxl.write.WritableCellFormat(wf);
							wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
							labelC =
								new jxl.write.Label(nCol, nRow, strTemp, wcf);
							ws.addCell(labelC);
						}
						nCol++;
					}
				}
			}

			wwb.write();
			wwb.close();
			log.debug("����Excel�ɹ�");
			wwb = null;

		} catch (IOException e) {
			log.error("����Excel�ļ�����", e);
			throw new PrintException("����Excel�ļ�����");
		} catch (jxl.write.biff.RowsExceededException e) {
			log.error("����Excel�ļ�����", e);
			throw new PrintException("����Excel�ļ�����");
		} catch (jxl.write.WriteException e) {
			log.error("����Excel�ļ�����", e);
			throw new PrintException("����Excel�ļ�����");
		} finally {
			if (wwb != null)
				try {
					wwb.close();
				} catch (IOException ioe) {
					log.error("�ر�Excel�ļ�����", ioe);
				}
		}
	}

	/**
	 * @param contents ����Ҫ�洢������
	 */
	public void setContents(Contents contents) {
		m_contents = contents;
	}

	/**
	 * @return �Ƿ񸲸�
	 */
	public boolean isOverWrite() {
		return isOverWrite;
	}

	/**
	 * @return �洢���ļ���
	 */
	public String getFileName() {
		return m_fileName;
	}

	/**
	 * @param b
	 */
	public void setOverWrite(boolean b) {
		isOverWrite = b;
	}

	/**
	 * @param string
	 */
	public void setFileName(String string) {
		m_fileName = string;
	}

}

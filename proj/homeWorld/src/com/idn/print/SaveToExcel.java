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
 * <P>将数组成功保存到EXCEL文件</P>
 * 
 * @version 0.1
 * @author navy
 */
public class SaveToExcel {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(SaveToExcel.class);

	/**
	 * 要存入Excel的内容
	 */
	private Contents m_contents = null;

	/**
	 * 文件名称
	 */
	private String m_fileName = null;

	/**
	 * 如果文件已经存在，是否要覆盖
	 */
	private boolean isOverWrite = false;

	/**
	 * 实例化
	 * @param contents
	 * @param strFileName
	 */
	public SaveToExcel() {
	}

	/**
	 * 实例化
	 * @param contents
	 * @param strFileName
	 */
	public SaveToExcel(Contents contents, String strFileName) {
		m_contents = contents;
		m_fileName = strFileName;
	}

	/**
	 * 将内容存储到EXCEL文件里
	 */
	public void save() throws PrintException {
		save(m_contents, m_fileName);
	}

	/**
	 * 将内容存储到EXCEL文件里
	 * @param contents 内容
	 * @param strFileName 文件名称
	 */
	public void save(Contents contents, String strFileName)
		throws PrintException {
		save(contents, strFileName, isOverWrite);
	}

	/**
	 * 将内容存储到EXCEL文件里
	 * @param contents
	 * @param strFileName
	 * @param isOverWrite
	 * @return 存储是否成功
	 */
	public void save(
		Contents contents,
		String strFileName,
		boolean isOverWrite)
		throws PrintException {
		if (contents == null) {
			log.error("没有内容可以存储！请先初始化contents.");
			throw new PrintException("没有内容可以存储！");
		}
		if (strFileName == null) {
			log.error("没有指定文件名称！请先初始化strFileName.");
			throw new PrintException("没有指定文件名称！");
		}
		File file = new File(strFileName);
		if (!file.getParentFile().exists()) {
			log.error("找不到路径：" + file.getParent());
			file = null;
			throw new PrintException("找不到路径！");
		}

		if (file.exists() && !isOverWrite) {
			log.error(strFileName + "文件已经存在！");
			throw new PrintException("文件已经存在！");
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
			log.debug("列/行： " + contents.getColumnCount() + "/" + contents.getRowCount());
			//写表头
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
			//合并标题行
			if (nCol > 1)
				ws.mergeCells(0, 0, nCol - 1, 0);

			//各列的格式
			for (int i = 0; i < nf.length; i++) {
				strTemp = contents.getColumn(i).getNumberPattern();
				if (strTemp != null)
					nf[i] = new jxl.write.NumberFormat(strTemp);
			}
			//写表体
			int nRealCol = 0;
			for (int i = 0; i < contents.getRowCount(); i++) {
				nRow++;
				nCol = 0;
				for (int j = 0; j < contents.getColumnCount(); j++) {
					if (contents.getColumn(j).isShow()) {
						nRealCol = contents.getColumn(j).getIndex();
						strTemp = contents.getElement(i, nRealCol);
						if (!contents.getColumn(j).isString()) {
							//数字getSequenceColumnHtmlCode(j, i)
							try {
								strTemp =
									CommonTools.stringReplace(strTemp, ",", "");
								ddTemp = Double.parseDouble(strTemp);
							} catch (NumberFormatException e) {
								log.error(j + "/" + i + "数字错误", e);
								ddTemp = 0.0;
							}
							wcf = new jxl.write.WritableCellFormat(wf, nf[j]);
							wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
							labelN =
								new jxl.write.Number(nCol, nRow, ddTemp, wcf);
							ws.addCell(labelN);
						} else {
							//字符
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
			log.debug("生成Excel成功");
			wwb = null;

		} catch (IOException e) {
			log.error("生成Excel文件出错", e);
			throw new PrintException("生成Excel文件出错！");
		} catch (jxl.write.biff.RowsExceededException e) {
			log.error("生成Excel文件出错", e);
			throw new PrintException("生成Excel文件出错！");
		} catch (jxl.write.WriteException e) {
			log.error("生成Excel文件出错", e);
			throw new PrintException("生成Excel文件出错！");
		} finally {
			if (wwb != null)
				try {
					wwb.close();
				} catch (IOException ioe) {
					log.error("关闭Excel文件出错", ioe);
				}
		}
	}

	/**
	 * @param contents 设置要存储的内容
	 */
	public void setContents(Contents contents) {
		m_contents = contents;
	}

	/**
	 * @return 是否覆盖
	 */
	public boolean isOverWrite() {
		return isOverWrite;
	}

	/**
	 * @return 存储的文件名
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

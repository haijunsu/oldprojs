/*
 * @(#)Skzjd.java  2003-8-21
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package printsystem;

import java.sql.SQLException;

import com.idn.sql.DataBean;
import com.idn.sql.DynaSqlBean;

/**
 * <P>处理打印税控装置机单业务逻辑</P>
 * 
 * @version 0.1
 * @author navy
 */
public class Skzjd {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(Skzjd.class);

	/**
	 * 查询税控装机单SQL语句
	 */
	private final String QUERY_SQL =
		"SELECT ZJZB.TAXPAYER AS TAXPAYER, ZJZB.TAXNO AS TAXNO, COMPNO, " +
		"INSDATE, NAMEC, ZJZB.OPERDATE AS CREADATE, ZJZB.INSFDATE AS INSFDATE, " +
		"SEQ, PROMODEL, GOODSID, PASSID, PASSNO, ICNO, " +
		"DW.DZ AS DZ, DW.LXR AS LXR, DW.DH AS DH " +
		"FROM ZJMXB, ZJZB, CPSXB, EMPLOYEE, DW " +
		"WHERE CPSXB.PROID = ZJMXB.PROID " +
		"AND EMPLOYEE.USERID = ZJZB.OPERATOR " +
		"AND ZJMXB.INSTID = ZJZB.INSTID " +
		"AND ZJZB.INSTID = ? " +
		"AND DW.DWBH = ZJZB.COMPNO " +
		"ORDER BY ZJMXB.SEQ";
	
	/**
	 * 税控装机单FORMBEAN
	 */
	private SkzjdForm m_form = null;
	
	/**
	 * 实例化
	 */
	public Skzjd() {
		super();
	}
	
	/**
	 * 查询税控装置装机单内容
	 * @throws SQLException 查询时SQL语句出错
	 * @throws SkzjdException 查询无结果
	 */
	public void query() throws SQLException, SkzjdException {
		
		log.debug(m_form.getInstId());
			
		DynaSqlBean dsb = new DynaSqlBean();
		DataBean db = new DataBean();
		
		dsb.setSql(QUERY_SQL);
		dsb.setParam(m_form.getInstId());
		
		db.setCrs(dsb.executeQuery());
		
		if (db.getRowCount() == 0) {
			throw new SkzjdException("没有找到指定的出库单！");
		}

		m_form.setTaxpayerName(db.getElementValue(0, "TAXPAYER").trim());
		m_form.setTaxRegisterNumber(db.getElementValue(0, "TAXNO").trim());
		m_form.setComputerId(db.getElementValue(0, "COMPNO").trim());
		m_form.setPreInstallDate(db.getElementValue(0, "INSDATE"));
		m_form.setOrderOwner(db.getElementValue(0, "NAMEC").trim());
		m_form.setCreateDate(db.getElementValue(0, "CREADATE"));
		m_form.setAddress(db.getElementValue(0, "DZ").trim());
		m_form.setPhone(db.getElementValue(0, "DH").trim());
		m_form.setLinkman(db.getElementValue(0, "LXR").trim());
		m_form.setInstDate(db.getElementValue(0, "INSFDATE").trim());
		
		String[][] strOrderDetails = new String[6][db.getRowCount()];
		
		for (int i = 0; i < strOrderDetails.length; i++) {
			for (int j = 0; j < strOrderDetails[0].length; j++) {
				strOrderDetails[i][j] = db.getElementValue(j, i + 7).trim();
			}
		}
		
		m_form.setSequenceNumber(strOrderDetails[0]);
		m_form.setEquipmentType(strOrderDetails[1]);
		m_form.setEquipmentNumber(strOrderDetails[2]);
		m_form.setPasswordInitOrderNumber(strOrderDetails[3]);
		m_form.setPasswordNumber(strOrderDetails[4]);
		m_form.setIcCardNumber(strOrderDetails[5]); 
		
		db.release();
		
	}
	

	/**
	 * @return 初始化后的FORM
	 */
	public SkzjdForm getForm() {
		return m_form;
	}

	/**
	 * @param form
	 */
	public void setForm(SkzjdForm form) {
		m_form = form;
	}

}

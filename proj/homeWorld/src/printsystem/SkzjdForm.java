/*
 * @(#)SkzjdForm.java  2003-8-21
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package printsystem;

import org.apache.struts.action.ActionForm;

import com.idn.util.CommonTools;

/**
 * <P>税控装置装机单数据映射</P>
 * 
 * @version 0.1
 * @author navy
 */
public class SkzjdForm extends ActionForm {

	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(SkzjdForm.class);
		
	/**
	 * 税控装机单ID
	 */
	private String m_instId = null;
	
	/**
	 * 纳税人名称
	 */
	private String m_taxpayerName = null;
	
	/**
	 * 税务登记号
	 */
	private String m_taxRegisterNumber = null;
	
	/**
	 * 计算机代码
	 */
	private String m_computerId = null;
	
	/**
	 * 预约安装时间
	 */
	private String m_preInstallDate = null;
	
	/**
	 * 序号
	 */
	private String[] m_sequenceNumber = null;
	
	/**
	 * 设备型号
	 */
	private String[] m_equipmentType = null;
	
	/**
	 * 设备编号
	 */
	private String[] m_equipmentNumber = null;
	
	/**
	 * 密码器初始化单号
	 */
	private String[] m_passwordInitOrderNumber = null;
	
	/**
	 * 密码器编号
	 */
	private String[] m_passwordNumber = null;
	
	/**
	 * IC卡号
	 */
	private String[] m_icCardNumber = null;
	
	/**
	 * 生成表格日期
	 */
	private String m_createDate = null;
	
	/**
	 * 制表人
	 */
	private String m_orderOwner = null;
	
	/**
	 * 联系人
	 */
	private String m_linkman = null;
	
	/**
	 * 地址
	 */
	private String m_address = null;
	
	/**
	 * 联系电话
	 */
	private String m_phone = null;
	
	/**
	 * 实际安装日期
	 */
	private String m_instDate = null;
	
	/**
	 * 实例化
	 */
	public SkzjdForm() {
		super();
	}
	
	

	/**
	 * @return 计算机代码
	 */
	public String getComputerId() {
		return m_computerId;
	}

	/**
	 * @return 生成表格日期
	 */
	public String getCreateDate() {
		if (m_createDate == null) {
			return "年&nbsp;&nbsp;月&nbsp;&nbsp;日";
		}
		return m_createDate;
	}

	/**
	 * @return 设备编号
	 */
	public String[] getEquipmentNumber() {
		return m_equipmentNumber;
	}

	/**
	 * @return 设备类型
	 */
	public String[] getEquipmentType() {
		return m_equipmentType;
	}

	/**
	 * @return IC卡号
	 */
	public String[] getIcCardNumber() {
		return m_icCardNumber;
	}

	/**
	 * @return 制表人
	 */
	public String getOrderOwner() {
		return m_orderOwner;
	}

	/**
	 * @return 密码器初始化单号
	 */
	public String[] getPasswordInitOrderNumber() {
		return m_passwordInitOrderNumber;
	}

	/**
	 * @return 密码器编号
	 */
	public String[] getPasswordNumber() {
		return m_passwordNumber;
	}

	/**
	 * @return 预约安装时间
	 */
	public String getPreInstallDate() {
		return m_preInstallDate;
	}

	/**
	 * @return 序号
	 */
	public String[] getSequenceNumber() {
		return m_sequenceNumber;
	}

	/**
	 * @return 纳税人名称
	 */
	public String getTaxpayerName() {
		return m_taxpayerName;
	}

	/**
	 * @return 税务登记号
	 */
	public String getTaxRegisterNumber() {
		return m_taxRegisterNumber;
	}

	/**
	 * @param string
	 */
	public void setComputerId(String string) {
		m_computerId = string;
	}

	/**
	 * @param string
	 */
	public void setCreateDate(String string) {
		String[] strDate = CommonTools.stringToArray(string, "-");
		try {
			m_createDate = strDate[0] + "年" + strDate[1] + "月" + strDate[2] + "日";
		} catch (ArrayIndexOutOfBoundsException e) {
			m_createDate = string;
		}
	}

	/**
	 * @param strings
	 */
	public void setEquipmentNumber(String[] strings) {
		m_equipmentNumber = strings;
	}

	/**
	 * @param strings
	 */
	public void setEquipmentType(String[] strings) {
		m_equipmentType = strings;
	}

	/**
	 * @param strings
	 */
	public void setIcCardNumber(String[] strings) {
		m_icCardNumber = strings;
	}

	/**
	 * @param string
	 */
	public void setOrderOwner(String string) {
		m_orderOwner = string;
	}

	/**
	 * @param strings
	 */
	public void setPasswordInitOrderNumber(String[] strings) {
		m_passwordInitOrderNumber = strings;
	}

	/**
	 * @param strings
	 */
	public void setPasswordNumber(String[] strings) {
		m_passwordNumber = strings;
	}

	/**
	 * @param string
	 */
	public void setPreInstallDate(String string) {
		String[] strDate = CommonTools.stringToArray(string, "-");
		try {
			m_preInstallDate = strDate[0] + "年" + strDate[1] + "月" + strDate[2] + "日";
		} catch (ArrayIndexOutOfBoundsException e) {
			m_preInstallDate = string;
		}
	}

	/**
	 * @param strings
	 */
	public void setSequenceNumber(String[] strings) {
		m_sequenceNumber = strings;
	}

	/**
	 * @param string
	 */
	public void setTaxpayerName(String string) {
		m_taxpayerName = string;
	}

	/**
	 * @param string
	 */
	public void setTaxRegisterNumber(String string) {
		m_taxRegisterNumber = string;
	}

	/**
	 * @return 装机单编号
	 */
	public String getInstId() {
		return m_instId;
	}

	/**
	 * @param string
	 */
	public void setInstId(String string) {
		m_instId = string;
	}
	
	public boolean isHaveData() {
		return getSequenceNumber() != null;
	}

	/**
	 * @return 联系地址
	 */
	public String getAddress() {
		return m_address;
	}

	/**
	 * @return 联系人
	 */
	public String getLinkman() {
		return m_linkman;
	}

	/**
	 * @return 联系电话
	 */
	public String getPhone() {
		return m_phone;
	}

	/**
	 * @param string
	 */
	public void setAddress(String string) {
		m_address = string;
	}

	/**
	 * @param string
	 */
	public void setLinkman(String string) {
		m_linkman = string;
	}

	/**
	 * @param string
	 */
	public void setPhone(String string) {
		m_phone = string;
	}

	/**
	 * @return 实际安装日期
	 */
	public String getInstDate() {
		return m_instDate;
	}

	/**
	 * @param string
	 */
	public void setInstDate(String string) {
		String[] strDate = CommonTools.stringToArray(string, "-");
		try {
			m_instDate = strDate[0] + "年" + strDate[1] + "月" + strDate[2] + "日";
		} catch (ArrayIndexOutOfBoundsException e) {
			m_instDate = string;
		}
	}

}

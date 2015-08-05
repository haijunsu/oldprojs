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
 * <P>˰��װ��װ��������ӳ��</P>
 * 
 * @version 0.1
 * @author navy
 */
public class SkzjdForm extends ActionForm {

	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(SkzjdForm.class);
		
	/**
	 * ˰��װ����ID
	 */
	private String m_instId = null;
	
	/**
	 * ��˰������
	 */
	private String m_taxpayerName = null;
	
	/**
	 * ˰��ǼǺ�
	 */
	private String m_taxRegisterNumber = null;
	
	/**
	 * ���������
	 */
	private String m_computerId = null;
	
	/**
	 * ԤԼ��װʱ��
	 */
	private String m_preInstallDate = null;
	
	/**
	 * ���
	 */
	private String[] m_sequenceNumber = null;
	
	/**
	 * �豸�ͺ�
	 */
	private String[] m_equipmentType = null;
	
	/**
	 * �豸���
	 */
	private String[] m_equipmentNumber = null;
	
	/**
	 * ��������ʼ������
	 */
	private String[] m_passwordInitOrderNumber = null;
	
	/**
	 * ���������
	 */
	private String[] m_passwordNumber = null;
	
	/**
	 * IC����
	 */
	private String[] m_icCardNumber = null;
	
	/**
	 * ���ɱ������
	 */
	private String m_createDate = null;
	
	/**
	 * �Ʊ���
	 */
	private String m_orderOwner = null;
	
	/**
	 * ��ϵ��
	 */
	private String m_linkman = null;
	
	/**
	 * ��ַ
	 */
	private String m_address = null;
	
	/**
	 * ��ϵ�绰
	 */
	private String m_phone = null;
	
	/**
	 * ʵ�ʰ�װ����
	 */
	private String m_instDate = null;
	
	/**
	 * ʵ����
	 */
	public SkzjdForm() {
		super();
	}
	
	

	/**
	 * @return ���������
	 */
	public String getComputerId() {
		return m_computerId;
	}

	/**
	 * @return ���ɱ������
	 */
	public String getCreateDate() {
		if (m_createDate == null) {
			return "��&nbsp;&nbsp;��&nbsp;&nbsp;��";
		}
		return m_createDate;
	}

	/**
	 * @return �豸���
	 */
	public String[] getEquipmentNumber() {
		return m_equipmentNumber;
	}

	/**
	 * @return �豸����
	 */
	public String[] getEquipmentType() {
		return m_equipmentType;
	}

	/**
	 * @return IC����
	 */
	public String[] getIcCardNumber() {
		return m_icCardNumber;
	}

	/**
	 * @return �Ʊ���
	 */
	public String getOrderOwner() {
		return m_orderOwner;
	}

	/**
	 * @return ��������ʼ������
	 */
	public String[] getPasswordInitOrderNumber() {
		return m_passwordInitOrderNumber;
	}

	/**
	 * @return ���������
	 */
	public String[] getPasswordNumber() {
		return m_passwordNumber;
	}

	/**
	 * @return ԤԼ��װʱ��
	 */
	public String getPreInstallDate() {
		return m_preInstallDate;
	}

	/**
	 * @return ���
	 */
	public String[] getSequenceNumber() {
		return m_sequenceNumber;
	}

	/**
	 * @return ��˰������
	 */
	public String getTaxpayerName() {
		return m_taxpayerName;
	}

	/**
	 * @return ˰��ǼǺ�
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
			m_createDate = strDate[0] + "��" + strDate[1] + "��" + strDate[2] + "��";
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
			m_preInstallDate = strDate[0] + "��" + strDate[1] + "��" + strDate[2] + "��";
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
	 * @return װ�������
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
	 * @return ��ϵ��ַ
	 */
	public String getAddress() {
		return m_address;
	}

	/**
	 * @return ��ϵ��
	 */
	public String getLinkman() {
		return m_linkman;
	}

	/**
	 * @return ��ϵ�绰
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
	 * @return ʵ�ʰ�װ����
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
			m_instDate = strDate[0] + "��" + strDate[1] + "��" + strDate[2] + "��";
		} catch (ArrayIndexOutOfBoundsException e) {
			m_instDate = string;
		}
	}

}

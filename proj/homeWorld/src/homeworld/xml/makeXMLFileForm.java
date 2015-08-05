/**
 * @(#)SearchOrderForm.java  2004-02-01
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package homeworld.xml;

//import javax.servlet.http.HttpServletRequest;

//import org.apache.struts.action.ActionError;
//import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionMapping;

/**
 * <P>������ѯ��Form</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class makeXMLFileForm extends ActionForm {
	
	/**
	* Constructor
	*/	
	private String[] mainDataName = null;         //���������
	private String[] mainDataKey = null;          //�����KEY
	private String[] mainDataValue = null;        //���������
	private String[] detailDataName = null;       //��ϸ�������
	private String[] detailDataKey = null;        //��ϸ���KEY
	private String[][] detailDataValue = null;    //��ϸ�������
	
	
	
	private String xmlFilePath = "";              //XML�ļ���ȫ·����/Ϊֹ��
	private String xmlFileName = "";              //XML�ļ���ȫ��������.XML��
	private String nodeRoot = "";                 //���ڵ�����
	private String nodeMain = "";                 //����ڵ�����
	private String nodeDetail = "";               //��ϸ��ڵ�����
	private String nodeRow = "";                  //��ϸ�нڵ�����
	
	
	public makeXMLFileForm() {
		super();
	}



	/**
	 * @return ��ϸ���KEY
	 */
	public String[] getDetailDataKey() {
		return detailDataKey;
	}

	/**
	 * @return ��ϸ�������
	 */
	public String[] getDetailDataName() {
		return detailDataName;
	}

	/**
	 * @return ��ϸ�������
	 */
	public String[][] getDetailDataValue() {
		return detailDataValue;
	}

	/**
	 * @return �����KEY
	 */
	public String[] getMainDataKey() {
		return mainDataKey;
	}

	/**
	 * @return ���������
	 */
	public String[] getMainDataName() {
		return mainDataName;
	}

	/**
	 * @return ���������
	 */
	public String[] getMainDataValue() {
		return mainDataValue;
	}

	/**
	 * @return XML�ļ���ȫ��������.XML��
	 */
	public String getXmlFileName() {
		return xmlFileName;
	}

	/**
	 * @return XML�ļ���ȫ·����/Ϊֹ��
	 */
	public String getXmlFilePath() {
		return xmlFilePath;
	}

	/**
	 * @param strings ��ϸ���KEY
	 */
	public void setDetailDataKey(String[] strings) {
		detailDataKey = strings;
	}

	/**
	 * @param strings ��ϸ�������
	 */
	public void setDetailDataName(String[] strings) {
		detailDataName = strings;
	}

	/**
	 * @param strings ��ϸ�������
	 */
	public void setDetailDataValue(String[][] strings) {
		detailDataValue = strings;
	}

	/**
	 * @param strings �����KEY
	 */
	public void setMainDataKey(String[] strings) {
		mainDataKey = strings;
	}

	/**
	 * @param strings ���������
	 */
	public void setMainDataName(String[] strings) {
		mainDataName = strings;
	}

	/**
	 * @param strings ���������
	 */
	public void setMainDataValue(String[] strings) {
		mainDataValue = strings;
	}

	/**
	 * @param string XML�ļ���ȫ��������.XML��
	 */
	public void setXmlFileName(String string) {
		xmlFileName = string;
	}

	/**
	 * @param string XML�ļ���ȫ·����/Ϊֹ��
	 */
	public void setXmlFilePath(String string) {
		xmlFilePath = string;
	}

	/**
	 * @return ��ϸ��ڵ�����
	 */
	public String getNodeDetail() {
		return nodeDetail;
	}

	/**
	 * @return ����ڵ�����
	 */
	public String getNodeMain() {
		return nodeMain;
	}

	/**
	 * @return ���ڵ�����
	 */
	public String getNodeRoot() {
		return nodeRoot;
	}

	/**
	 * @param string ��ϸ��ڵ�����
	 */
	public void setNodeDetail(String string) {
		nodeDetail = string;
	}

	/**
	 * @param string ����ڵ�����
	 */
	public void setNodeMain(String string) {
		nodeMain = string;
	}

	/**
	 * @param string ���ڵ�����
	 */
	public void setNodeRoot(String string) {
		nodeRoot = string;
	}

	/**
	 * @return ��ϸ�нڵ�����
	 */
	public String getNodeRow() {
		return nodeRow;
	}

	/**
	 * @param string ��ϸ�нڵ�����
	 */
	public void setNodeRow(String string) {
		nodeRow = string;
	}

}

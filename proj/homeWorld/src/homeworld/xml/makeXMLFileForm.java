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
 * <P>定单查询的Form</P>
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
	private String[] mainDataName = null;         //主表的列名
	private String[] mainDataKey = null;          //主表的KEY
	private String[] mainDataValue = null;        //主表的内容
	private String[] detailDataName = null;       //明细表的列名
	private String[] detailDataKey = null;        //明细表的KEY
	private String[][] detailDataValue = null;    //明细表的内容
	
	
	
	private String xmlFilePath = "";              //XML文件的全路径（/为止）
	private String xmlFileName = "";              //XML文件的全名（包括.XML）
	private String nodeRoot = "";                 //根节点内容
	private String nodeMain = "";                 //主表节点内容
	private String nodeDetail = "";               //明细表节点内容
	private String nodeRow = "";                  //明细行节点内容
	
	
	public makeXMLFileForm() {
		super();
	}



	/**
	 * @return 明细表的KEY
	 */
	public String[] getDetailDataKey() {
		return detailDataKey;
	}

	/**
	 * @return 明细表的列名
	 */
	public String[] getDetailDataName() {
		return detailDataName;
	}

	/**
	 * @return 明细表的内容
	 */
	public String[][] getDetailDataValue() {
		return detailDataValue;
	}

	/**
	 * @return 主表的KEY
	 */
	public String[] getMainDataKey() {
		return mainDataKey;
	}

	/**
	 * @return 主表的列名
	 */
	public String[] getMainDataName() {
		return mainDataName;
	}

	/**
	 * @return 主表的内容
	 */
	public String[] getMainDataValue() {
		return mainDataValue;
	}

	/**
	 * @return XML文件的全名（包括.XML）
	 */
	public String getXmlFileName() {
		return xmlFileName;
	}

	/**
	 * @return XML文件的全路径（/为止）
	 */
	public String getXmlFilePath() {
		return xmlFilePath;
	}

	/**
	 * @param strings 明细表的KEY
	 */
	public void setDetailDataKey(String[] strings) {
		detailDataKey = strings;
	}

	/**
	 * @param strings 明细表的列名
	 */
	public void setDetailDataName(String[] strings) {
		detailDataName = strings;
	}

	/**
	 * @param strings 明细表的内容
	 */
	public void setDetailDataValue(String[][] strings) {
		detailDataValue = strings;
	}

	/**
	 * @param strings 主表的KEY
	 */
	public void setMainDataKey(String[] strings) {
		mainDataKey = strings;
	}

	/**
	 * @param strings 主表的列名
	 */
	public void setMainDataName(String[] strings) {
		mainDataName = strings;
	}

	/**
	 * @param strings 主表的内容
	 */
	public void setMainDataValue(String[] strings) {
		mainDataValue = strings;
	}

	/**
	 * @param string XML文件的全名（包括.XML）
	 */
	public void setXmlFileName(String string) {
		xmlFileName = string;
	}

	/**
	 * @param string XML文件的全路径（/为止）
	 */
	public void setXmlFilePath(String string) {
		xmlFilePath = string;
	}

	/**
	 * @return 明细表节点内容
	 */
	public String getNodeDetail() {
		return nodeDetail;
	}

	/**
	 * @return 主表节点内容
	 */
	public String getNodeMain() {
		return nodeMain;
	}

	/**
	 * @return 根节点内容
	 */
	public String getNodeRoot() {
		return nodeRoot;
	}

	/**
	 * @param string 明细表节点内容
	 */
	public void setNodeDetail(String string) {
		nodeDetail = string;
	}

	/**
	 * @param string 主表节点内容
	 */
	public void setNodeMain(String string) {
		nodeMain = string;
	}

	/**
	 * @param string 根节点内容
	 */
	public void setNodeRoot(String string) {
		nodeRoot = string;
	}

	/**
	 * @return 明细行节点内容
	 */
	public String getNodeRow() {
		return nodeRow;
	}

	/**
	 * @param string 明细行节点内容
	 */
	public void setNodeRow(String string) {
		nodeRow = string;
	}

}

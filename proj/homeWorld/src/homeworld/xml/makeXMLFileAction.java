/**
 * @(#)SearchOrder.java  2004-02-01
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package homeworld.xml;
//import java.io.IOException;
//import java.util.*;

//import javax.servlet.ServletException;
//import javax.servlet.http.*;

import com.idn.property.InitServletProperty;

//import system.action.ErrorValue;

import commsearch.*;

/**
 * <P>为生成XML准备数据</P>
 * <ul>
 * <li>
  * </ul>
 * 
 * @version 1.0
 * @author XIAOAI
 */

public class makeXMLFileAction   {
	
	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(makeXMLFileAction.class);
	private String username = null;            //登录用户名
	private commsearch.CommSQL cs = new commsearch.CommSQL();    //执行SQL语句 
	private CommQuery cqa = null;               //公用查询数据类
	private CommQuery cqb = null;               //公用查询数据类
	private CommQueryData cqda = null;
	private CommQueryData cqdb = null;
	private makeXMLFileForm xmlFileForm= null;
	public makeXMLFileAction() {
		super();
	}
	/**
	 * 为生成XML准备数据
	 * 
	 */			
	
	public void init(String s_queryid,String s_where){
		
		String[] s_mainDataName = null;         //主表的列名
		String[] s_mainDataKey = null;          //主表的KEY
		String[] s_mainDataValue = null;        //主表的内容
		String[] s_detailDataName = null;       //明细表的列名
		String[] s_detailDataKey = null;        //明细表的KEY
		String[][] s_detailDataValue = null;    //明细表的内容
		String s_queryida="";                      //主表
		String s_queryidb="";                      //明细表
		String s_xmlFilePath = "";              //XML文件的全路径（/为止）
		String s_xmlFileName = "";              //XML文件的全名（包括.XML）
		String s_nodeRoot = "";                 //根节点内容
		String s_nodeMain = "";                 //主表节点内容
		String s_nodeDetail = "";               //明细表节点内容
		String s_nodeRow = "";                  //明细行节点内容
	
		//TODO XML文件的内容	
		log.debug("QUERYID:" + s_queryid);
		if (s_where== null || s_queryid==null) {
			setXmlFileForm(null);
			return ;
		}
		if (s_where.equals("") || s_queryid.equals("")) {
			setXmlFileForm(null);
			return ;
		}

		if (s_queryid.equals("DQ_EPOHDR")){
			//订单
			s_queryida = "EPOHDR";
			s_queryidb = "EPODTL";
			s_xmlFilePath = InitServletProperty.getRealPath() + "/out/xml/";
			s_xmlFileName = "order.xml";             
			s_nodeRoot = "ORDER";                 
			s_nodeMain = "MAIN";                 
			s_nodeDetail = "DETAIL";               
			s_nodeRow = "EPOSKU";                  
		}
		if (s_queryid.equals("DQ_ERTHDR")){
			//返厂单
			s_queryida = "ERTHDR";
			s_queryidb = "ERTDTL";
			s_xmlFilePath = InitServletProperty.getRealPath() + "/out/xml/";
			s_xmlFileName = "ERTHDR.xml";             
			s_nodeRoot = "ERTHDR";                 
			s_nodeMain = "MAIN";                 
			s_nodeDetail = "DETAIL";               
			s_nodeRow = "ERTSKU";                  
		}
		if (s_queryid.equals("DQ_ERVHDR")){
			//收货单
			s_queryida = "ERVHDR";
			s_queryidb = "ERVDTL";
			s_xmlFilePath = InitServletProperty.getRealPath() + "/out/xml/";
			s_xmlFileName = "ERVHDR.xml";             
			s_nodeRoot = "ERVHDR";                 
			s_nodeMain = "MAIN";                 
			s_nodeDetail = "DETAIL";               
			s_nodeRow = "ERVSKU";                  
		}
		if (s_queryid.equals("DQ_ERJHDR")){
			//收货单
			s_queryida = "ERJHDR";
			s_queryidb = "ERJDTL";
			s_xmlFilePath = InitServletProperty.getRealPath() + "/out/xml/";
			s_xmlFileName = "ERJHDR.xml";             
			s_nodeRoot = "ERJHDR";                 
			s_nodeMain = "MAIN";                 
			s_nodeDetail = "DETAIL";               
			s_nodeRow = "ERJSSQ";                  
		}
		
		String[][] s_temp = null;
		String[] s_field = null;
		makeXMLFileForm l_xmlFileForm= new makeXMLFileForm();
		
		//主表		
		cqa = new CommQuery(s_queryida);
		cqa.init();
		s_mainDataName = cqa.getFields();
		s_mainDataKey = cqa.getFieldskey();
		cqda = new CommQueryData(cqa);
		cqda.init(s_where,"","");
		s_temp = cqda.getData();
		s_mainDataValue = new String[s_temp[0].length];
		for (int i_fori = 0;i_fori<s_temp[0].length;i_fori++){
			s_mainDataValue[i_fori] = s_temp[0][i_fori];
		}

		//明细表		
		cqb = new CommQuery(s_queryidb);
		cqb.init();
		s_detailDataName = cqb.getFields();
		s_detailDataKey = cqb.getFieldskey();
		cqdb = new CommQueryData(cqb);
		cqdb.init(s_where,"","");
		s_temp = null;
		s_temp = cqdb.getData();
		s_detailDataValue = new String[s_temp.length][s_temp[0].length];
		for (int i_fori = 0;i_fori<s_temp.length;i_fori++){
			for (int i_forj = 0;i_forj<s_temp[0].length;i_forj++){
				s_detailDataValue[i_fori][i_forj] = s_temp[i_fori][i_forj];
			}
		}
		//设定FROMBEAN
		l_xmlFileForm.setMainDataName(s_mainDataName);
		l_xmlFileForm.setMainDataKey(s_mainDataKey);
		l_xmlFileForm.setMainDataValue(s_mainDataValue);
		l_xmlFileForm.setDetailDataName(s_detailDataName);
		l_xmlFileForm.setDetailDataKey(s_detailDataKey);
		l_xmlFileForm.setDetailDataValue(s_detailDataValue);
		l_xmlFileForm.setXmlFileName(s_xmlFileName);
		l_xmlFileForm.setXmlFilePath(s_xmlFilePath);
		l_xmlFileForm.setNodeRoot(s_nodeRoot);
		l_xmlFileForm.setNodeMain(s_nodeMain);
		l_xmlFileForm.setNodeDetail(s_nodeDetail);
		l_xmlFileForm.setNodeRow(s_nodeRow);
		setXmlFileForm(l_xmlFileForm);
		
		
		log.debug("makeXMLFileAction:结束");
	}
	
	


	/**
	 * @return
	 */
	public makeXMLFileForm getXmlFileForm() {
		return xmlFileForm;
	}

	/**
	 * @param form
	 */
	public void setXmlFileForm(makeXMLFileForm form) {
		xmlFileForm = form;
	}

}

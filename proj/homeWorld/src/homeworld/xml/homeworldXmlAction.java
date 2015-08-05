/*
 * @(#)homeworldXmlAction.java  2004-3-29
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.xml;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author lyc
 */

//import java.util.Date;
import java.io.IOException;
//import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSession;

//import org.apache.commons.beanutils.PropertyUtils;
//import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.idn.secure.SecureAction;
import com.idn.upload.Download;

import org.jdom.*;
//import org.jdom.output.XMLOutputter;
//import java.io.*;

/**
 * <P>�˵�����</P>
 * 
 * @version 0.1
 * @author ������
 */
public class homeworldXmlAction extends SecureAction {
	//���÷�װBean-com.idn.log.LogWrapper��������־��¼
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(homeworldXmlAction.class);
	ActionErrors errors = new ActionErrors();
	makeXMLFileForm xff = new makeXMLFileForm();
	
	String key = "";
	/**
	 * ���캯��
	 */
	public homeworldXmlAction() {
		super();
	}

	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeme(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		errors.clear();
		errors.add("header", new ActionError("errors.header"));
		errors.add("footer", new ActionError("errors.footer"));

		log.debug("homeworldXmlAction:��ʼִ��");

		HttpSession hs = request.getSession();
		String uname=(String)hs.getAttribute("userid");

		//TODO ��ʱ���������
		long l_begin,l_end;
		l_begin = System.currentTimeMillis();		
		commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
		commsearch.util.CommActionLog.setTempLog(
			Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
			uname,hs.getId(),"XML","B","LYC0000000",0);
			
		//����
		String selectwhere =
			(String) request.getParameter("selectwhere").trim();
		String queryid = (String) request.getParameter("queryid").trim();


		if(selectwhere==null)
			selectwhere="";
		if(selectwhere.equals("")){
//			TODO ��ʱ���������
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"XML","E","LYC3333333",l_end - l_begin);
			return null;
		}
		
		try {
			homeworld.xml.makeXMLFileAction xf =
				new homeworld.xml.makeXMLFileAction();
			xf.init(queryid, selectwhere);
			xff = xf.getXmlFileForm();
			org.jdom.Document document = setFormbeen(
				xff.getMainDataKey(),
				xff.getMainDataName(),
				xff.getMainDataValue(),
				xff.getDetailDataKey(),
				xff.getDetailDataName(),
				xff.getDetailDataValue(),
				xff.getXmlFilePath(),
				xff.getXmlFileName(),
				xff.getNodeRoot(),
				xff.getNodeMain(),
				xff.getNodeDetail(),
				xff.getNodeRow());
			Download.downloadXML(response, document, xff.getXmlFileName());
			//д��־��XIAOAI����
//			commsearch.util.CommActionLog  cal= new commsearch.util.CommActionLog();
/*
			HttpSession hs = request.getSession();
			String uname=(String)hs.getAttribute("userid");
			cal.setAct_user(uname);
			cal.setAct_from("homeworldXml");
			cal.setAct_do("NEW");
			cal.setAct_key(getKey());
			cal.setAct_table("XML");
			cal.setAct_ip(request.getRemoteAddr());
			cal.setAct_memo("����XML���Ϊ" + getKey());
			cal.setAct_me("");
			cal.setActionLog();
*/
						log.debug("homeworldXmlAction:����");

//			TODO ��ʱ���������
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"XML","E","LYC8888888",l_end - l_begin);
				
		} catch (Exception e) {

			e.printStackTrace();
//			TODO ��ʱ���������
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"XML","C","LYC9999999",l_end - l_begin);
		}

//		return (mapping.findForward("success"));
		return null;

	}
	/**
	 * ��ָ�����ַ����滻�س���ת��Ϊ���飬�м���ָ�����ַ�delimiter�ָ�
	 * ���delimiterΪnull����delimiterΪ�ո�
	 *
	 * @param str ���ָ���ַ���
	 * @param delimiter �ָ���
	 *
	 * @return ���飬����Ԫ�ض��Ѿ�trim()
	 * @since JDK 1.4
	 */
	public org.jdom.Document setFormbeen(
		String[] mainkey,
		String[] mainname,
		String[] mainvalue,
		String[] detailkey,
		String[] detailname,
		String[][] detailvalue,
		String xmlfilepath,
		String xmlfilename,
		String noderoot,
		String nodemain,
		String nodedetail,
		String noderow)
		throws Exception {

		try {

			if (noderoot == null || noderoot == "")
				throw new Exception("noderoot is null");
			if (nodemain == null || nodemain == "")
				throw new Exception("nodemain is null");
			if (nodedetail == null || nodedetail == "")
				throw new Exception("nodedetail is null");
			if (noderow == null || noderow == "")
				throw new Exception("noderow is null");

			//������Ŀ¼
			Element root = new Element(noderoot);
			Document myDocument = new Document(root);

			//������������Ŀ¼
			Element main = new Element(nodemain);
			Element detail = new Element(nodedetail);

			if (mainname != null && mainvalue != null) {
				//Ϊ����Ŀ¼���main��
				for (int i = 0; i < mainname.length; i++) {
					main.addContent(
						new Element(mainname[i]).setText(mainvalue[i]));
					for (int j = 0; j < mainkey.length; j++) {
						if (mainname[i].trim().equals(mainkey[j].trim())) {
							main.setAttribute(mainname[i], mainvalue[i]);
							setKey(mainvalue[i]);
							break;
						}
					}
				}
				//��Ӷ���Ŀ¼main����Ŀ¼
				root.addContent(main);
			}
			log.debug("main�ڵ����ɳɹ�����");

			if (detailname != null && detailvalue != null) {
				//Ϊ����Ŀ¼���detail��
				Element row;
				for (int i = 0; i < detailvalue.length; i++) {
					row = new Element(noderow);
					for (int j = 0; j < detailname.length; j++) {
						row.addContent(
							new Element(detailname[j]).setText(
								detailvalue[i][j]));

						for (int k = 0; k < detailkey.length; k++) {
							if (detailname[j]
								.trim()
								.equals(detailkey[k].trim())) {
								row.setAttribute(
									detailname[j],
									detailvalue[i][j]);
								break;
							}
						}

					}
					detail.addContent(row);
				}
				//��Ӷ���Ŀ¼detail����Ŀ¼
				root.addContent(detail);
			}

			log.debug("detail�ڵ����ɳɹ�����");
			return myDocument;

			//�����ļ�
			//						log.debug("�ļ����ǣ�"+xmlfilepath.trim()+xmlfilename.trim());
			//						File f=new File(xmlfilepath.trim()+xmlfilename.trim());
			//						
			//						f.createNewFile();
			//						//���������
			//	
			//						XMLOutputter out=new XMLOutputter("",true);
			//						
			//						//���xml�ļ�
			//						out.setEncoding("GB2312");
			//						out.setIndent(true);
			//						out.output(myDocument,new FileOutputStream(f));
			//					
			//					
			//						log.debug("�ļ����ɳɹ�����");
			//					
			//						
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

//		return true;
	}
	/* (non-Javadoc)
	 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecurely(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {
		return executeme(mapping, form, request, response);
	}

	/**
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param string
	 */
	public void setKey(String string) {
		key = string;
	}

}

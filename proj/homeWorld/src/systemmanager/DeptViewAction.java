/**
 * @(#)LogonAction.java  2003-6-11
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package systemmanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

//import java.lang.reflect.InvocationTargetException;
//import java.util.*;

import com.idn.sql.*;

/**
 * <P>���ʿ������</P>
 * 
 * @version 0.1
 * @author ������
 */
public class DeptViewAction extends Action {
	//private static LogWrapper log= new LogWrapper(SalaryCardCtrl.class);
	private int intTemp = 0;
	private String strSql;
	private String[] strTemp = null;
	ActionErrors errors = new ActionErrors();
	ActionMessages messages = new ActionMessages();
	/**
	 * ���캯��
	 */
	public DeptViewAction() {
		super();
	}

	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,	HttpServletResponse response)throws Exception {
		String sqlwhere;
		String program;
		String strTitle;
		String rownow;
		String visib;
		String page;
		//��ʼ��error����		
		errors.clear();
		errors.add("header",new ActionError("errors.header"));
		errors.add("footer",new ActionError("errors.footer"));
			
		//ȡ�ô������arg0,��Ա���
		sqlwhere = (String)request.getParameter("arg0");
		if (sqlwhere == null) sqlwhere = "1";
		sqlwhere=sqlwhere.trim();
		//ȡ�ô������arg1,Ҫ���õ�URL		
		program = (String)request.getParameter("arg1");
		if (program == null) program = "salarycard.do";
		program=program.trim();
		//ȡ�ô������arg2,Ҫ�ý������		
		rownow = (String)request.getParameter("arg2");
		if (rownow == null) rownow = "";
		rownow=rownow.trim();
		//ȡ�ô������arg3,����Ϊ0�Ƿ���ʾ��ť  �� 0  ��  1		
		visib = (String)request.getParameter("arg3");
		if (visib == null) visib="0";
		PropertyUtils.setSimpleProperty(form, "visib",visib);
		//ȡ�ô������arg4,���Ӳ���		
		page = (String)request.getParameter("arg4");
		if (page == null) page ="";
		
		//����messages	
		messages.clear();
		strTitle =program.substring(0,program.indexOf(".do")).toUpperCase()+".TITLE."+sqlwhere;  //"DeptView.title";
		messages.add("title", new ActionMessage(strTitle));
		saveMessages(request, messages);

		program=program+"?arg4="+page+"&arg3=" + visib + "&arg0=" + sqlwhere;
		
		//��ʼ����Ա��Ϣ
		if(setFormbeenC(form,sqlwhere,program,rownow).equalsIgnoreCase("0"))
			saveErrors(request, errors);
		return(mapping.findForward("success"));	
	}
	
	/**
		 * ��ָ����������ݴ�����ʾFormBeen(����)
		 * 
		 * @param ActionForm ��ʾFormBeen(����)
		 * @param String ��Ա�б������ŷ��飩
		 * @return String 1���ɹ���0�����ɹ���
		 * @exception Exception
		 */
		public String setFormbeenC(ActionForm form,String strWhere,String program,String rownowid) throws Exception {
			
			DataBean dbBean = new DataBean();
			DataBean dbBeansmall = new DataBean();
			String strSql;
			int intMaxrow,inttemp;
			
			try{
				//��ѯ��������
				strSql="SELECT  ccode,cshowc FROM codes WHERE cclass='08' ORDER BY ccode ";	
				dbBean.executeSelect(strSql);
			}catch (Exception e){
				errors.add("errormessage",new ActionError("DeptView.code"));
				return "0";
			}
			
			intMaxrow = dbBean.getRowCount();
			try{
				String sqlWhere="";
				//sqlWhere = "('" + strWhere.replaceAll("-","','") + "')";
				
				sqlWhere = "('" + com.idn.util.CommonTools.stringReplace(strWhere,"-","','") + "')";
				
				
				
				//��ѯ��������
				//strSql=" SELECT  DEPT,cshowc ,Count(*) FROM SALARYU,codes WHERE(state in"+ sqlWhere +") and (cclass='08') and (ccode=dept) GROUP BY DEPT,cshowc ORDER BY DEPT ";
				//if(sqlWhere.equalsIgnoreCase("('all')"))
					strSql=" SELECT  DEPT,cshowc ,Count(*) FROM employee,codes WHERE (cclass='08') and (ccode=dept) GROUP BY DEPT,cshowc ORDER BY DEPT ";
				
				dbBeansmall.executeSelect(strSql);
			}catch (Exception e){
				errors.add("errormessage",new ActionError("DeptView.salaryu"));
				return "0";
			}
			
	

//			��¼�������еĲ�����
			if (intMaxrow <2) {
			  intMaxrow=2;
			}			

			String[] strname =  new String[intMaxrow];
			String[] strno = new String[intMaxrow];
			String[] strcount = new String[intMaxrow];
			int rownow=-1;
			
			for (int intRow = 0; intRow <dbBean.getRowCount();intRow++){
				strno[intRow]=dbBean.getElementValue(0,intRow).trim();
				strname[intRow]=dbBean.getElementValue(intRow,1).trim();
				strcount[intRow]="0";
				if(strno[intRow].equalsIgnoreCase(rownowid))
					rownow=intRow;
			}
			if(rownow==-1) rownow=0;
			
			//�Ƚ���������
			inttemp=0;
  			for (int intRow = 0; intRow < intMaxrow;intRow++){
	  			//����С�����±�
	  			if(inttemp>=dbBeansmall.getRowCount()){
					strcount[intRow]="0";
					continue;
  				}
	  			//���
	  			if(strno[intRow].trim().equalsIgnoreCase(dbBeansmall.getElementValue(inttemp,0).trim())){
	  				strcount[intRow]=dbBeansmall.getElementValue(inttemp,2);
	  				inttemp++;
	  			//����
	  			}else{
					strcount[intRow]="0";
					continue;
	  			}
  			}

  			try{
  				//��formbeen��������
				PropertyUtils.setSimpleProperty(form, "focusnow",String.valueOf(rownow));
				PropertyUtils.setSimpleProperty(form, "columnname",strname);
				PropertyUtils.setSimpleProperty(form, "rowno",strno);
				PropertyUtils.setSimpleProperty(form, "columncount",strcount);
				PropertyUtils.setSimpleProperty(form, "program",program);
				PropertyUtils.setSimpleProperty(form, "key",strWhere);
			}catch (Exception e){
				errors.add("errormessage",new ActionError("DeptView.formbean"));
				return "0";
			}
					
			return "1";
		}
	

}

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
 * <P>工资卡表管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class DeptViewAction extends Action {
	//private static LogWrapper log= new LogWrapper(SalaryCardCtrl.class);
	private int intTemp = 0;
	private String strSql;
	private String[] strTemp = null;
	ActionErrors errors = new ActionErrors();
	ActionMessages messages = new ActionMessages();
	/**
	 * 构造函数
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
		//初始化error对象		
		errors.clear();
		errors.add("header",new ActionError("errors.header"));
		errors.add("footer",new ActionError("errors.footer"));
			
		//取得传入参数arg0,人员类别
		sqlwhere = (String)request.getParameter("arg0");
		if (sqlwhere == null) sqlwhere = "1";
		sqlwhere=sqlwhere.trim();
		//取得传入参数arg1,要调用的URL		
		program = (String)request.getParameter("arg1");
		if (program == null) program = "salarycard.do";
		program=program.trim();
		//取得传入参数arg2,要置焦点的行		
		rownow = (String)request.getParameter("arg2");
		if (rownow == null) rownow = "";
		rownow=rownow.trim();
		//取得传入参数arg3,人数为0是否显示按钮  不 0  显  1		
		visib = (String)request.getParameter("arg3");
		if (visib == null) visib="0";
		PropertyUtils.setSimpleProperty(form, "visib",visib);
		//取得传入参数arg4,附加参数		
		page = (String)request.getParameter("arg4");
		if (page == null) page ="";
		
		//定义messages	
		messages.clear();
		strTitle =program.substring(0,program.indexOf(".do")).toUpperCase()+".TITLE."+sqlwhere;  //"DeptView.title";
		messages.add("title", new ActionMessage(strTitle));
		saveMessages(request, messages);

		program=program+"?arg4="+page+"&arg3=" + visib + "&arg0=" + sqlwhere;
		
		//初始化人员信息
		if(setFormbeenC(form,sqlwhere,program,rownow).equalsIgnoreCase("0"))
			saveErrors(request, errors);
		return(mapping.findForward("success"));	
	}
	
	/**
		 * 将指定类代码内容传入显示FormBeen(大表格)
		 * 
		 * @param ActionForm 显示FormBeen(大表格)
		 * @param String 人员列表（按部门分组）
		 * @return String 1、成功；0、不成功；
		 * @exception Exception
		 */
		public String setFormbeenC(ActionForm form,String strWhere,String program,String rownowid) throws Exception {
			
			DataBean dbBean = new DataBean();
			DataBean dbBeansmall = new DataBean();
			String strSql;
			int intMaxrow,inttemp;
			
			try{
				//查询代码内容
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
				
				
				
				//查询代码内容
				//strSql=" SELECT  DEPT,cshowc ,Count(*) FROM SALARYU,codes WHERE(state in"+ sqlWhere +") and (cclass='08') and (ccode=dept) GROUP BY DEPT,cshowc ORDER BY DEPT ";
				//if(sqlWhere.equalsIgnoreCase("('all')"))
					strSql=" SELECT  DEPT,cshowc ,Count(*) FROM employee,codes WHERE (cclass='08') and (ccode=dept) GROUP BY DEPT,cshowc ORDER BY DEPT ";
				
				dbBeansmall.executeSelect(strSql);
			}catch (Exception e){
				errors.add("errormessage",new ActionError("DeptView.salaryu"));
				return "0";
			}
			
	

//			记录不足两行的补空行
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
			
			//比较两个数组
			inttemp=0;
  			for (int intRow = 0; intRow < intMaxrow;intRow++){
	  			//超过小树组下标
	  			if(inttemp>=dbBeansmall.getRowCount()){
					strcount[intRow]="0";
					continue;
  				}
	  			//相等
	  			if(strno[intRow].trim().equalsIgnoreCase(dbBeansmall.getElementValue(inttemp,0).trim())){
	  				strcount[intRow]=dbBeansmall.getElementValue(inttemp,2);
	  				inttemp++;
	  			//不等
	  			}else{
					strcount[intRow]="0";
					continue;
	  			}
  			}

  			try{
  				//向formbeen中送数据
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

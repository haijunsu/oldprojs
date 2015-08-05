/**
 * @(#)LogonAction.java  2003-6-11
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package system.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.idn.sql.DataBean;


/**
 * <P>工资卡表管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class SelectOneAction extends Action {
	
	private int intTemp = 0;
	private String strSql;
	private String[] strTemp = null;
	/**
	 * 构造函数
	 */
	public SelectOneAction() {
		super();
	}

	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,	HttpServletResponse response)throws Exception {
			String sqlwhere;
			String strsql,cclass;
			DataBean dbBean = new DataBean();
			DataBean db = new DataBean();
			
			strsql="";
			//取得传入参数arg0
			sqlwhere = (String)request.getParameter("arg0");
			//sqlwhere = "appsyscode";
			String cshowc="";
			
			if(sqlwhere.length()<20){
				strsql="select * from "+sqlwhere;
				//若未表名时取中文名称
				cshowc = (String)request.getParameter("arg1");
			}else{
				strsql=sqlwhere;
				//若为sql时取中文名称
				cshowc = (String)request.getParameter("arg1");
			}
			
			if(sqlwhere.length()<=2){
				strsql="select ccode,cshowc from codes where cclass='"+sqlwhere+"'";
				cclass="select cshowc from codes where cclass='00' and ccode='"+sqlwhere+"'";
				db.executeSelect(cclass);
				cshowc=db.getElementValue(0,0).trim();
//				cshowc=cshowc.trim();
			}
			dbBean.executeSelect(strsql);
			
			int intMaxrow=dbBean.getRowCount();
			int intMaxcolumn=dbBean.getColumnCount()-2;
		
//			记录不足两行的补空行
			if (intMaxrow <2) {
			//  intMaxrow=2;
			}
					
			String[] rowid=new String[intMaxrow];
			String[] rowshow=new String[intMaxrow];
			String[][] row=new String[intMaxrow][intMaxcolumn];

			for (int intRow = 0; intRow < intMaxrow;intRow++){
				rowid[intRow]=dbBean.getElementValue(intRow,0).trim();
				rowshow[intRow]=dbBean.getElementValue(intRow,1).trim();
				
				for(int intColumn = 0; intColumn< intMaxcolumn;intColumn++){
					row[intRow][intColumn]=dbBean.getElementValue(intRow,intColumn+2).trim();
				}
				
			}
			

			PropertyUtils.setSimpleProperty(form, "row",row);
			PropertyUtils.setSimpleProperty(form, "cshowc",cshowc);
			PropertyUtils.setSimpleProperty(form, "rowid",rowid);
			PropertyUtils.setSimpleProperty(form, "rowshow",rowshow);	
			
			return(mapping.findForward("success"));
	}

}

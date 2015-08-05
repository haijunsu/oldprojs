/**
 * @(#)LogonAction.java  2003-6-11
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package systemmanager;

//import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.GregorianCalendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
//import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
//import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.idn.sql.DataBean;
import com.idn.sql.DynaSqlBean;
import com.idn.util.FormatDate;

/**
 * <P>工资卡表管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class ParametAction extends Action {
	//private static LogWrapper log= new LogWrapper(SalaryCardCtrl.class);
	private int intTemp = 0;
	private String strSql;
	private String[] strTemp = null;
	ActionErrors errors = new ActionErrors();
	ActionMessages messages = new ActionMessages();
	/**
	 * 构造函数
	 */
	public ParametAction() {
		super();
	}

	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,	HttpServletResponse response)throws Exception {
			
		String[] strTemp=null;
		String page;
		String flag;
		String nowdate;
		//errors对象初始化
		errors.clear();
		errors.add("header",new ActionError("errors.header"));
		errors.add("footer",new ActionError("errors.footer"));
		
		try{
			//nowdate=((String) PropertyUtils.getSimpleProperty(form, "nowdate")).trim();
			page=((String) PropertyUtils.getSimpleProperty(form, "page")).trim();
			flag=((String) PropertyUtils.getSimpleProperty(form, "flag")).trim();
		
			//String s_date,s_temp;
			//GregorianCalendar gc = new GregorianCalendar();
			//gc.add(GregorianCalendar.DATE, 1);
			
			
			Date today=new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//			nowdate = sdf.format(today);
			nowdate=FormatDate.format(today,"yyyyMMdd");
			
			PropertyUtils.setSimpleProperty(form, "nowdate",FormatDate.format(today,"yyyy-MM-dd"));
						
			if(flag.equalsIgnoreCase("")){
				if(setFormbeen(form,"1",nowdate).equalsIgnoreCase("0"))
					saveErrors(request, errors);
				PropertyUtils.setSimpleProperty(form, "page","1");
				return(mapping.findForward("success"));
			}
			
			if(flag.equalsIgnoreCase("changexx")){
				if(setFormbeen(form,page,nowdate).equalsIgnoreCase("0"))
					saveErrors(request, errors);
				return(mapping.findForward("success"));
			}
			
			//提交数据库
			Vector vecTemp = new Vector();
			String[] policyid=null;
			String[] subsect=null;
			String[] paramet=null;
			String[] effedate=null;
			String[] rowid=null;
			String[] rowstate=null;
			String[] policyshow=null;
			String[] rowlistold=null;
			String[] paramet2=null;
			DynaSqlBean dbBean = new DynaSqlBean(); 
				
			//数据处理
			policyid = (String[]) PropertyUtils.getSimpleProperty(form, "policyid");
			subsect = (String[]) PropertyUtils.getSimpleProperty(form, "subsect");
			paramet = (String[]) PropertyUtils.getSimpleProperty(form, "paramet");
			effedate = (String[]) PropertyUtils.getSimpleProperty(form, "effedate");
			rowid = (String[]) PropertyUtils.getSimpleProperty(form, "rowid");
			rowstate = (String[]) PropertyUtils.getSimpleProperty(form, "rowstate");
			policyshow = (String[]) PropertyUtils.getSimpleProperty(form, "policyshow");
			paramet2 = (String[]) PropertyUtils.getSimpleProperty(form, "paramet2");
			
			
			for (int intFor = 0;intFor<rowstate.length;intFor++){
				//删除
				if(rowstate[intFor].equals("2")||rowstate[intFor].equals("1")){
					if(!rowid[intFor].trim().equals("")){
						strSql="DELETE FROM salarypa where (SALAPAR||SALADIV||SALADATE='"+rowid[intFor]+"')"; 
						vecTemp.addElement(strSql);
						System.out.println(strSql);
					}
				}
				//添加
				if(rowstate[intFor].equals("4") || rowstate[intFor].equals("1")){
					//代码项为空不添加
					if (!policyshow[intFor].trim().equals("")){
						//effedate[intFor]=effedate[intFor].replaceAll("-","");
						effedate[intFor]=com.idn.util.CommonTools.stringReplace(effedate[intFor],"-","");
						
						
						
						strSql="INSERT INTO salarypa (SALAPAR,SALADIV,SALAVAL1,SALADATE,SALAVAL2) VALUES " +
							"('"+policyid[intFor]+"','"+subsect[intFor]+"','"+paramet[intFor]+"','"+effedate[intFor].trim()+"','"+paramet2[intFor]+"')";
						vecTemp.addElement(strSql);
						System.out.println(strSql);
					}
				}
			}
			
			if (vecTemp.size() > 0){
				strTemp = (String[]) vecTemp.toArray(new String[0]);
				dbBean.setSql(strTemp);
				try{
						dbBean.executeBatch();
					} catch (Exception e){
						System.out.println("error");
						errors.add("errormessage",new ActionError("Paramet.writedb"));
						saveErrors(request, errors);
					}
			}
			
			////////////
			
			if (setFormbeen(form,page,nowdate).equalsIgnoreCase("0"))
				saveErrors(request, errors);
			return(mapping.findForward("success"));
			
		} catch (Exception e){
		errors.add("errormessage",new ActionError("Paramet.FormBean"));
		saveErrors(request, errors);
		return(mapping.findForward("success"));
		}
			
	}
	/**
	 * 将指定类代码内容传入显示FormBeen(小表格)
	 * 
	 * @param ActionForm 显示FormBeen
	 * @param String 工资卡号
	 * @return String 1、成功；0、不成功；
	 * @exception Exception
	 */
	public String setFormbeen(ActionForm form,String page,String nowdate) throws Exception {
		
		String[] strtemp=new String[10];
		String strsql;
		DataBean dbBean = new DataBean();
		
		if (page.equalsIgnoreCase("2")){
			//未生效的				
			strsql="SELECT SALAPAR,SALADIV,SALAVAL1,SALADATE,cshowc,SALAVAL2 "+
				"FROM salarypa,codes "+ 
				"WHERE (SALADATE >'"+nowdate+"') and (SALAPAR=ccode) and (cclass='21') "+ 
				"ORDER BY SALAPAR,DOUBLE(SALADIV),SALADATE";
		}else{
			// 已生效的
			strsql="SELECT SALAPAR,SALADIV,SALAVAL1,SALADATE,cshowc,SALAVAL2 "+
					"FROM salarypa,codes "+ 
					"WHERE  (SALAPAR||SALADIV||SALADATE IN "+ 
					"(SELECT SALAPAR||SALADIV||max(SALADATE) FROM salarypa WHERE SALADATE <='"+nowdate+"' GROUP BY  SALAPAR,SALADIV) "+
					"and (SALAPAR=ccode) and (cclass='21')) "+
					"ORDER BY SALAPAR,DOUBLE(SALADIV),SALADATE ";
		}
		try{
			dbBean.executeSelect(strsql);
		} catch (Exception e){
			System.out.println("error");
			errors.add("errormessage",new ActionError("Paramet.FormBean"));
			return "0";
		}
		
		int intMaxrow;
		
		intMaxrow = dbBean.getRowCount();
		//记录不足两行的补空行
		if (intMaxrow < 2) {
			intMaxrow=2;
		}
		String[] paramet2 = new String[intMaxrow];
		String[] paramet = new String[intMaxrow];
		String[] subsect = new String[intMaxrow];
		String[] policyid = new String[intMaxrow];
		String[] effedate = new String[intMaxrow];
		String[] showname = new String[intMaxrow];	
		String[] rowlist = new String[intMaxrow];	
			
		for(int i=0;i<intMaxrow ;i++){
			policyid[i]=dbBean.getElementValue(0,i);
			subsect[i]=dbBean.getElementValue(i,1);
			paramet[i]=dbBean.getElementValue(i,2);
			effedate[i]=dbBean.getElementValue(i,3);
			//effedate[i]=effedate[i].substring(0,4)+"-"+effedate[i].substring(4,6)+"-"+effedate[i].substring(6);
			showname[i]=dbBean.getElementValue(i,4);
			paramet2[i]=dbBean.getElementValue(i,5);
			
			rowlist[i]=policyid[i]+subsect[i]+effedate[i];
			
			policyid[i]=policyid[i].trim();
			subsect[i]=subsect[i].trim();
			paramet[i]=paramet[i].trim();
			effedate[i]=effedate[i].trim();
			showname[i]=showname[i].trim();
			paramet2[i]=paramet2[i].trim();
			
			if(effedate[i].length()==8)
				effedate[i]=effedate[i].substring(0,4)+"-"+effedate[i].substring(4,6)+"-"+effedate[i].substring(6);
		}

		PropertyUtils.setSimpleProperty(form, "flag","");
		
		PropertyUtils.setSimpleProperty(form, "policyid",policyid);
		PropertyUtils.setSimpleProperty(form, "policyshow",showname);
		
		PropertyUtils.setSimpleProperty(form, "rowid",rowlist);
		
		PropertyUtils.setSimpleProperty(form, "subsect",subsect);
		PropertyUtils.setSimpleProperty(form, "paramet",paramet);
		PropertyUtils.setSimpleProperty(form, "effedate",effedate);
		PropertyUtils.setSimpleProperty(form, "paramet2",paramet2);		
		return "1";
	}

}

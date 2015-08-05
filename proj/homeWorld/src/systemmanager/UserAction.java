/**
 * @(#)LogonAction.java  2003-6-11
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package systemmanager;


import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.idn.property.CodesManager;
import system.fun.FunPurview;
import com.idn.log.LogWrapper;
import com.idn.sql.DataBean;
import com.idn.sql.DynaSqlBean;
import com.idn.util.CommonTools;

/**
 * <P>菜单管理</P>
 * 
 * @version 0.1
 * @author 李永初
 */
public class UserAction extends Action {
	//private static LogWrapper log= new LogWrapper(SalaryUserForm.class);
	private static LogWrapper log = new LogWrapper("UserForm");
	private int intTemp = 0;
	private String strSql;
	private String[] strTemp = null;
	ActionErrors errors = new ActionErrors();
	FunPurview funPurview=new FunPurview();
	/**
	 * 构造函数
	 */
	public UserAction() {
		super();
	}

	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,	HttpServletResponse response)throws Exception {

		errors.clear();
		errors.add("header",new ActionError("errors.header"));
		errors.add("footer",new ActionError("errors.footer"));
		
		DynaSqlBean dybBean = new DynaSqlBean();
		Vector vecTemp=new Vector();
		String strTemp,strFlag;
		String deptshow="";
		String deptid="";
		String salagmshow="";
		String salagmid="";
		String strPage;
		String returnurl;
		String salaryNo="";
		String temp="";

		String currrowshow,userid,appsysid;
		String[] strSql=null;
		
		strPage=((String) PropertyUtils.getSimpleProperty(form, "page"));
		deptid=((String) PropertyUtils.getSimpleProperty(form, "nowdeptid"));
		
		//取得传入参数arg1,要调用的URL		
		strTemp = (String)request.getParameter("arg4");
		if (strTemp != null){
			if (!(strTemp.trim()).equals("")){strPage=strTemp.trim();}
			returnurl="DeptView.do?arg4="+strTemp;
			
			//取得传入参数arg1,要调用的URL		
			strTemp = (String)request.getParameter("arg0");
			returnurl=returnurl+"&arg0="+strTemp;
			strTemp = (String)request.getParameter("arg1");
			returnurl=returnurl+"&arg1=User.do&arg2="+strTemp;
			deptid=strTemp.trim();
			deptshow=CodesManager.getCodeValue("08",strTemp);
			PropertyUtils.setSimpleProperty(form, "nowdeptshow",deptshow);
						
			strTemp = (String)request.getParameter("arg3");
			returnurl=returnurl+"&arg3="+strTemp;
			PropertyUtils.setSimpleProperty(form, "returnurl",returnurl);
		}
		
		if(strPage == null) strPage="1";	
		PropertyUtils.setSimpleProperty(form, "page",strPage);
		
		PropertyUtils.setSimpleProperty(form, "nowdeptid",deptid);
		
		strFlag=((String) PropertyUtils.getSimpleProperty(form, "flag"));
		
		//根据不同的系统显示不同的权限
		if (strFlag.equalsIgnoreCase("circle")){
			String temps;
			temps=((String) PropertyUtils.getSimpleProperty(form, "appsysid")).trim();
			if(funPurview.decodePurview(0,temps)){
				PropertyUtils.setSimpleProperty(form, "mpurviewshow",funPurview.getCshowc());
				PropertyUtils.setSimpleProperty(form, "mpurviewid",funPurview.getPurview());
			}	
			return(mapping.findForward("success"));
		}

		//取当前行
		currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrowshowold"));
		if (strFlag.equalsIgnoreCase("commit") || strFlag.equalsIgnoreCase("hidden") || strFlag.equalsIgnoreCase("exit"))
			currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrow"));
			
		//不需要提交数据库的时候直接赋值
		if (strFlag.equalsIgnoreCase("") || strFlag.substring(strFlag.length()-4).equalsIgnoreCase("exic")){
			strTemp=setFormbeen(form,deptid);
			if (strTemp.equals("0")){
				saveErrors(request, errors);
			}
			return(mapping.findForward("success"));
		}
		
		//取用户id
		userid=((String) PropertyUtils.getIndexedProperty(form, "userid",Integer.parseInt(currrowshow))).trim();
		
//			page为1
				  if (strPage.equals("1")){
					  String strRowstate,strInsert,strNamec,strUid;
					  String[] strPurview=null;
					  strTemp="";
					//strRowstate=((String) PropertyUtils.getSimpleProperty(form, "rowstate")).trim();
					
					strRowstate=(String) PropertyUtils.getIndexedProperty(form, "rowstate",Integer.parseInt(currrowshow));
					  if(strRowstate.equals("4")){
						temp=((String) PropertyUtils.getIndexedProperty(form, "namec",Integer.parseInt(currrowshow))).trim();
						String uid=nameToUserid(temp);
						if(uid.equals("-1")){
							saveErrors(request, errors);
							return(mapping.findForward("success"));
						}
						strInsert="insert into employee values('"+uid+"',";
						
						PropertyUtils.setIndexedProperty(form, "userid",Integer.parseInt(currrowshow),uid);
						strInsert=strInsert+"'"+temp+"',"; 
						temp=((String) PropertyUtils.getIndexedProperty(form, "genderid",Integer.parseInt(currrowshow))).trim();
						strInsert=strInsert+"'"+temp+"',"; 
						temp=((String) PropertyUtils.getIndexedProperty(form, "jobid",Integer.parseInt(currrowshow))).trim();
						strInsert=strInsert+"'"+temp+"',"; 
						temp=((String) PropertyUtils.getIndexedProperty(form, "deptid",Integer.parseInt(currrowshow))).trim();
						strInsert=strInsert+"'"+temp+"',"; 
						temp=((String) PropertyUtils.getIndexedProperty(form, "birthday",Integer.parseInt(currrowshow))).trim();
						strInsert=strInsert+"'"+temp+"',";  
						temp=((String) PropertyUtils.getIndexedProperty(form, "address",Integer.parseInt(currrowshow))).trim();
						strInsert=strInsert+"'"+temp+"',"; 
						temp=((String) PropertyUtils.getIndexedProperty(form, "tel",Integer.parseInt(currrowshow))).trim();
						strInsert=strInsert+"'"+temp+"',"; 
						temp=((String) PropertyUtils.getIndexedProperty(form, "bp",Integer.parseInt(currrowshow))).trim();
						strInsert=strInsert+"'"+temp+"',"; 
						temp=((String) PropertyUtils.getIndexedProperty(form, "handset",Integer.parseInt(currrowshow))).trim();
						strInsert=strInsert+"'"+temp+"',"; 
						temp=((String) PropertyUtils.getIndexedProperty(form, "levelid",Integer.parseInt(currrowshow))).trim();
						strInsert=strInsert+"'"+temp+"',"; 
						temp=((String) PropertyUtils.getIndexedProperty(form, "ustateid",Integer.parseInt(currrowshow))).trim();
						strInsert=strInsert+"'"+temp+"',";
						
						HttpSession session = request.getSession();
						strTemp=(String)session.getAttribute("userid");
						strInsert=strInsert+"'"+temp+"',";
						
						strInsert=strInsert+"(current date))";
						
						vecTemp.addElement(strInsert);
				
						//  strInsert="insert into salaryca values('"+salaryNo+"',1,'')";
						//  vecTemp.addElement(strInsert);
				
						  strInsert="insert into users values('"+uid+"','"+uid+"','98',"+String.valueOf(funPurview.codingPurview(strPurview))+")";
						  vecTemp.addElement(strInsert);	
					  }
					  
					  if(strRowstate.equals("1")){
						  strInsert="update employee set " ;
				
					  strTemp=((String) PropertyUtils.getIndexedProperty(form, "namec",Integer.parseInt(currrowshow))).trim();
					  strInsert=strInsert+"namec='"+strTemp+"',";
				
					  strTemp=((String) PropertyUtils.getIndexedProperty(form, "genderid",Integer.parseInt(currrowshow))).trim();
					  strInsert=strInsert+"gender='"+strTemp+"',";
					  strTemp=((String) PropertyUtils.getIndexedProperty(form, "jobid",Integer.parseInt(currrowshow))).trim();
					  strInsert=strInsert+"job='"+strTemp+"',";
					  strTemp=((String) PropertyUtils.getIndexedProperty(form, "deptid",Integer.parseInt(currrowshow))).trim();
					  strInsert=strInsert+"dept='"+strTemp+"',";
					  strTemp=((String) PropertyUtils.getIndexedProperty(form, "birthday",Integer.parseInt(currrowshow))).trim();
					  if(strTemp.equals("")){
					  	strInsert=strInsert+"birthday=null,";
					  }else{
						strInsert=strInsert+"birthday='"+strTemp+"',";
					  }
					  strTemp=((String) PropertyUtils.getIndexedProperty(form, "ustateid",Integer.parseInt(currrowshow))).trim();
					  strInsert=strInsert+"state='"+strTemp+"',";
					  strTemp=((String) PropertyUtils.getIndexedProperty(form, "address",Integer.parseInt(currrowshow))).trim();
					  strInsert=strInsert+"address='"+strTemp+"',";
					  strTemp=((String) PropertyUtils.getIndexedProperty(form, "tel",Integer.parseInt(currrowshow))).trim();
					  strInsert=strInsert+"tel='"+strTemp+"',";
					  strTemp=((String) PropertyUtils.getIndexedProperty(form, "bp",Integer.parseInt(currrowshow))).trim();
					  strInsert=strInsert+"bp='"+strTemp+"',";
					  strTemp=((String) PropertyUtils.getIndexedProperty(form, "handset",Integer.parseInt(currrowshow))).trim();
					  strInsert=strInsert+"handset='"+strTemp+"',";
					  strTemp=((String) PropertyUtils.getIndexedProperty(form, "levelid",Integer.parseInt(currrowshow))).trim();
					  strInsert=strInsert+"level='"+strTemp+"',";
				
					  strInsert=strInsert+"operdate=(current date),";
					  HttpSession session = request.getSession();
					  strTemp=(String)session.getAttribute("userid");
					  strInsert=strInsert+"operator='"+strTemp+"'";
				
					temp=((String) PropertyUtils.getIndexedProperty(form, "userid",Integer.parseInt(currrowshow))).trim();
					
					  strInsert=strInsert+" where userid='"+temp+"'";
					  vecTemp.addElement(strInsert);	
				  }		
			  }	
		
			  //page为2
			  if (strPage.equals("2")){
				  //取用户id
				  userid=((String) PropertyUtils.getIndexedProperty(form, "userid",Integer.parseInt(currrowshow))).trim();
				  String[] purviewid=null;
				  //取系统
				  appsysid=((String) PropertyUtils.getSimpleProperty(form, "appsysid"));
				  //取权限
				  purviewid = (String[])request.getParameterValues("mpurviewid");
				  strTemp=String.valueOf(funPurview.codingPurview(purviewid));
				  vecTemp.addElement("update users set appsys='"+appsysid+"',purview="+strTemp+" where userid='"+userid+"'");
			
			  }

		strSql = (String[]) vecTemp.toArray(new String[0]);		
		//执行sql语句
		try{
			dybBean.setSql(strSql);
			dybBean.executeBatch();
			//String ss =(String) PropertyUtils.getIndexedProperty(form,"salaryno",Integer.parseInt(currrowshow));
			
			//if(ss.equals(""))
			//	PropertyUtils.setIndexedProperty(form,"salaryno",Integer.parseInt(currrowshow),salaryNo);
				
		} catch (Exception e){
			errors.add("errormessage",new ActionError("SalaryMenuAll.writedb"));
			saveErrors(request, errors);
		}
		
		setFormbeen(form,deptid);
		return(mapping.findForward("success"));		
		
	}


	/**
	 * 将指定类代码内容传入显示FormBeen(小表格)
	 * 
	 * @param ActionForm 显示FormBeen
	 * @param String 工资卡号
	 * @return String 1、成功；0、不成功；
	 * @exception Exception
	 */
	public String setFormbeen(ActionForm form,String strDept) throws Exception{
		String strSql,key,currrowshow;
		DataBean dbBean = new DataBean();
		DataBean dbEx = new DataBean();
		Vector vecTemp=new Vector();
		
		strSql="select userid,namec,gender,job,dept,birthday,Address,tel,bp,handset,level,state " +
			"from employee " +
			"where dept='"+strDept+"' " +
			"order by userid";

		if(strDept.equals("All")){
			strSql="select userid,namec,gender,job,dept,birthday,Address,tel,bp,handset,level,state " +
				"from employee " +
				"order by userid";
		}
		
		
		String strFlag=((String) PropertyUtils.getSimpleProperty(form, "flag"));
		//取当前行
		currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrowshowold"));
		//if (strFlag.equalsIgnoreCase("commit") || strFlag.equalsIgnoreCase("hidden") || strFlag.equalsIgnoreCase("exit"))
			currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrowshow"));
			
		key="";
		if(!currrowshow.equals("-1"))
			key=((String) PropertyUtils.getIndexedProperty(form, "userid",Integer.parseInt(currrowshow))).trim();
		
		dbBean.executeSelect(strSql);
		int count;
		count=dbBean.getRowCount();
	
		
	String[] mpurviewshow = new String[count];//权限显示
	String[] mpurviewid = new String[count];//权限id

	String appsysshow = null;//系统显示
	String appsysid = null;//系统id

	String[] userid= new String[count];//用户id
	String[] namec= new String[count];//用户姓名
	
	String[] gendershow = new String[count];//性别中文
	String[] genderid = new String[count];//性别代码
	
	String[] jobshow = new String[count];//职务中文
	String[] jobid = new String[count];//职务代码
	
	String[] deptshow = new String[count];//单位中文
	String[] deptid = new String[count];//单位代码

	String[] birthday = new String[count];//单位中文
	String[] address = new String[count];//单位代码

	String[] tel = new String[count];//电话
	String[] bp = new String[count];//bp
	String[] handset = new String[count];//手机

	String[] levelshow = new String[count];//级别
	String[] levelid = new String[count];//级别
	
	String[] ustateshow = new String[count];//用户状态中文
	String[] ustateid = new String[count];//用户代码
				
		String[] rowstate = new String[count];
		
		int intcurrrow=-1;
		
		for(int i=0;i<count;i++){
		
			userid[i]=dbBean.getElementValue(0,i);
			namec[i]=dbBean.getElementValue(1,i);
			
			userid[i]=userid[i].trim();
			namec[i]=namec[i].trim();
						
			genderid[i]=dbBean.getElementValue(i,2);
			gendershow[i]=CodesManager.getCodeValue("01",genderid[i]);

			jobid[i]=dbBean.getElementValue(i,3);
			jobshow[i]=CodesManager.getCodeValue("02",jobid[i]);

			deptid[i]=dbBean.getElementValue(i,4);
			deptshow[i]=CodesManager.getCodeValue("08",deptid[i]);

			birthday[i]=dbBean.getElementValue(i,5);
			address[i]=dbBean.getElementValue(i,6);
			tel[i]=dbBean.getElementValue(i,7);
			bp[i]=dbBean.getElementValue(i,8);
			handset[i]=dbBean.getElementValue(i,9);

			levelid[i]=dbBean.getElementValue(i,10);
			levelshow[i]=CodesManager.getCodeValue("13",levelid[i]);
						
			ustateid[i]=dbBean.getElementValue(i,11);
			ustateshow[i]=CodesManager.getCodeValue("09",ustateid[i]);

			rowstate[i]="0";
			
			if((!key.equals("")) && (userid[i].equalsIgnoreCase(key)))
				intcurrrow=i;
		}
			
		String strPage;
		 
		strPage=((String) PropertyUtils.getSimpleProperty(form, "page"));
		
		if(strPage.equals("2") && !(currrowshow.equals("-1"))){
			strSql="SELECT APPSYS,PURVIEW FROM USERS WHERE  USERID='"+userid[Integer.parseInt(currrowshow)] +"'";
			dbEx.executeSelect(strSql);
			appsysid=dbEx.getElementValue(0,0).trim();
			appsysshow=CodesManager.getCodeValue("00",appsysid);
			//解析权限
			if(funPurview.decodePurview(Integer.parseInt(dbEx.getElementValue(0,1)),appsysid)){
				mpurviewid=funPurview.getPurview();
				mpurviewshow=funPurview.getCshowc();
			}
		}
		
		
		PropertyUtils.setSimpleProperty(form, "count",String.valueOf(count));
		PropertyUtils.setSimpleProperty(form,"currrowshow",String.valueOf(intcurrrow));
		if(intcurrrow!=-1)
			PropertyUtils.setIndexedProperty(form,"rowstate",intcurrrow,"0");
		
		PropertyUtils.setSimpleProperty(form, "mpurviewshow",mpurviewshow);
		PropertyUtils.setSimpleProperty(form, "mpurviewid",mpurviewid);
		PropertyUtils.setSimpleProperty(form, "appsysshow",appsysshow);
		PropertyUtils.setSimpleProperty(form, "appsysid",appsysid);
		
		PropertyUtils.setSimpleProperty(form, "userid",userid);
		PropertyUtils.setSimpleProperty(form, "namec",namec);
		
		PropertyUtils.setSimpleProperty(form, "gendershow",gendershow);
		PropertyUtils.setSimpleProperty(form, "genderid",genderid);
		
		PropertyUtils.setSimpleProperty(form, "jobshow",jobshow);
		PropertyUtils.setSimpleProperty(form, "jobid",jobid);
		
		PropertyUtils.setSimpleProperty(form, "deptshow",deptshow);
		PropertyUtils.setSimpleProperty(form, "deptid",deptid);
		

		PropertyUtils.setSimpleProperty(form, "birthday",birthday);
		PropertyUtils.setSimpleProperty(form, "address",address);
		PropertyUtils.setSimpleProperty(form, "tel",tel);
		PropertyUtils.setSimpleProperty(form, "bp",bp);
		PropertyUtils.setSimpleProperty(form, "handset",handset);

		PropertyUtils.setSimpleProperty(form, "levelid",levelid);
		PropertyUtils.setSimpleProperty(form, "levelshow",levelshow);
		
		PropertyUtils.setSimpleProperty(form, "ustateshow",ustateshow);
		PropertyUtils.setSimpleProperty(form, "ustateid",ustateid);
		
		
		return "1";
	}
	
	/**
	* 根把传入的名字，按拼音规则在数据库里生成唯一的ID
	* @param name 用户名字
	* @return 生成的新ID
	*/
   public String nameToUserid(String name) {

	   char[] chName = name.toCharArray();
	   String strUserid = "";
	   String strTemp = "";
	   String strSql = "SELECT CON FROM CHINESE WHERE CHINESE = ?";
	DynaSqlBean dsb = new DynaSqlBean();
	DataBean db = new DataBean();
	
	   //拼ID
	   for (int i = 0; i < chName.length; i++) {
		   //strTemp = Character.toString(chName[i]);
			strTemp = String.valueOf(chName[i]);
		   if (CommonTools.isChinese(strTemp)) {
			   dsb.setSql(strSql);
			   dsb.setParam(strTemp);
			   try {
				   db.setCrs(dsb.executeQuery());
			   } catch (Exception e) {
				   log.debug(e.getMessage(), e);
			   }
			   strTemp = db.getElementValue(0, 0).trim();
			   if (strTemp.length() > 1)
				   strTemp = strTemp.substring(0, 1);
		   }
		   strUserid = strUserid + strTemp.toUpperCase();
	   }
	   
	   //查重
	   strSql = "SELECT USERID FROM USERS WHERE USERID = ?";
	   strTemp = strUserid;
	   int nDuplation = 1;
	   int nIncrease = 1;
	   while (nDuplation > 0) {
		   dsb.setParam(strTemp);
		   dsb.setSql(strSql);
		   try {
			   db.setCrs(dsb.executeQuery());
			   nDuplation = db.getRowCount();
			   if (nDuplation > 0) {
				   strTemp = strUserid + nIncrease;
				   nIncrease++;
			   }
		   } catch (Exception e) {
			   log.debug(e.getMessage(), e);
			   return "-1";
		   }
	   }
	   strUserid = strTemp.toUpperCase();
	   if(strUserid.trim().equals("")){
		errors.add("errormessage",new ActionError("SalaryMenuAll.writedb"));
		//saveErrors(request, errors);
		return "-1";
	   }
	   return strUserid;
   }	
	
/*	
   public String generateSalaryno(String name) {
	   String strSalaryno = "";
	   String strSql = "SELECT MAX(SALARYNO) FROM SALARYU";
	   DynaSqlBean dsb = new DynaSqlBean();
	   DataBean db = new DataBean();
	   try {
		   dsb.setSql(strSql);
		   db.setCrs(dsb.executeQuery());
		   strSalaryno = db.getFieldValue(0, 0).trim();
		   if (strSalaryno.length() == 0) {
			   log.warn("数据库里可能没有数据，从0开始发号");
			   log.debug("自动发号列长度为: " + db.getColumnLength(0));
			   for (int i = 0; i < db.getColumnLength(0); i++) {
				   strSalaryno = strSalaryno + "0";
			   }
		   }
	   } catch (Exception e) {
		   log.error(e.getMessage(), e);
		   return "0";
	   }
	   log.debug(strSalaryno);
	   strSalaryno = CommonTools.autoNum(strSalaryno);
	   log.debug("发号成功：" + strSalaryno);
	   return strSalaryno;
   }*/
}

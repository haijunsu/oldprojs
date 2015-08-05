/**
 * @(#)LogonAction.java  2003-6-11
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */

package systemmanager;


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

import system.fun.FunPurview;
import java.util.*;
//import com.idn.log.LogWrapper;
import com.idn.sql.*;

/**
 * <P>�˵�����</P>
 * 
 * @version 0.1
 * @author ������
 */
public class MenuAllAction extends Action {
	//private static LogWrapper log= new LogWrapper(SalaryCardCtrl.class);
	private int intTemp = 0;
	private String strSql;
	private String[] strTemp = null;
	ActionErrors errors = new ActionErrors();
	FunPurview funPurview=new FunPurview();

	/**
	 * ���캯��
	 */
	public MenuAllAction() {
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
			
			String stemp,flag,page;
		
			int currrowshow;
			DynaSqlBean dybBean = new DynaSqlBean();
			Vector vecTemp=new Vector();
			String temp=null;

			HttpSession hs = request.getSession();
			String uname=(String)hs.getAttribute("userid");
			
			//TODO ��ʱ���������
			long l_begin,l_end;
			l_begin = System.currentTimeMillis();		
			commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"MenuAll","B","LYC0000000",0);
				
		try{
			//ȡ��־
			flag=(String) PropertyUtils.getSimpleProperty(form, "flag");
			page=(String) PropertyUtils.getSimpleProperty(form, "page");
			currrowshow=Integer.parseInt((String) PropertyUtils.getSimpleProperty(form, "currrowshow"));
			//���ݲ�ͬ��ϵͳ��ʾ��ͬ��Ȩ��
			if (flag.equalsIgnoreCase("cyl")){
				String temps;
				temps=((String) PropertyUtils.getIndexedProperty(form, "appsysid",currrowshow)).trim();
				if(funPurview.decodePurview(0,temps)){
					PropertyUtils.setSimpleProperty(form, "mpurviewshow",funPurview.getCshowc());
					PropertyUtils.setSimpleProperty(form, "mpurviewid",funPurview.getPurview());
				}	
						
//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"MenuAll","E","LYC8888881",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			//����Ҫ�ύ���ݿ��ʱ��ֱ�Ӹ�ֵ
			if (flag.equalsIgnoreCase("") || flag.substring(flag.length()-2).equalsIgnoreCase("xx")){
				temp=setFormbeen(form,page,currrowshow);
				if (temp.equals("0")){
					saveErrors(request, errors);
				}
						
//				TODO ��ʱ���������
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"MenuAll","E","LYC8888882",l_end - l_begin);
				return(mapping.findForward("success"));
			}
			String[] strsql=null;
			String id=null;
			id=(String) PropertyUtils.getSimpleProperty(form, "id");	
			
			int currrowshowold;
			String menuid="";
			String mnamec="";
			String mnamee="";
			String mfile="";
			String mfilee="";
			String appsysid="";
			String mpurview="";
			String rowstate="";
			
			
			//�ֲ�ͬ�����ȡ��ǰ�е�ֵ
			if(flag.equalsIgnoreCase("commit") || flag.equalsIgnoreCase("exit"))
				currrowshowold=Integer.parseInt((String) PropertyUtils.getSimpleProperty(form, "currrowshow"));
			else
				currrowshowold=Integer.parseInt((String) PropertyUtils.getSimpleProperty(form, "currrowshowold"));
			//����ǰ��ȡֵ
			menuid=((String) PropertyUtils.getIndexedProperty(form, "menuid",currrowshowold)).trim();
			mnamec=((String) PropertyUtils.getIndexedProperty(form, "mnamec",currrowshowold)).trim();
			mnamee=((String) PropertyUtils.getIndexedProperty(form, "mnamee",currrowshowold)).trim();
			mfile=((String) PropertyUtils.getIndexedProperty(form, "mfile",currrowshowold)).trim();
			mfilee=((String) PropertyUtils.getIndexedProperty(form, "mfilee",currrowshowold)).trim();
			appsysid=((String) PropertyUtils.getIndexedProperty(form, "appsysid",currrowshowold)).trim();

			//�༭Ȩ�޴���
			String[] purviewid=null;
			purviewid = (String[])request.getParameterValues("mpurviewid");
			mpurview=String.valueOf(funPurview.codingPurview(purviewid));
			//if(purviewid==null)
			//	mpurview="0";
			
			//ƴд�ύ���ݿ�Ĵ���
			rowstate=((String) PropertyUtils.getIndexedProperty(form, "rowstate",currrowshowold)).trim();
			if (flag.equalsIgnoreCase("del"))
				rowstate="2";
			if(rowstate.equalsIgnoreCase("1") || rowstate.equalsIgnoreCase("2"))
				vecTemp.addElement("DELETE FROM menuall where menuid='"+id+"'");
				
			if(rowstate.equalsIgnoreCase("1") || rowstate.equalsIgnoreCase("4"))
				vecTemp.addElement("insert into menuall values('"+menuid+"','"+mnamec+"','"+mnamee+"','"+mfile+"','"+mfilee+"','"+appsysid+"',"+mpurview+")");
				
			strsql = (String[]) vecTemp.toArray(new String[0]);
				
			//ִ��sql���
			try{
				dybBean.setSql(strsql);
				dybBean.executeBatch();
			} catch (Exception e){
				errors.add("errormessage",new ActionError("MenuAll.writedb"));
				saveErrors(request, errors);
			}
			
			//ˢ����ҳ
			temp=setFormbeen(form,page,currrowshow);
			if (temp.equals("0")){
				saveErrors(request, errors);
			}

//			TODO ��ʱ���������
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"MenuAll","E","LYC8888888",l_end - l_begin);
			return(mapping.findForward("success"));
		} catch (Exception e){
			errors.add("errormessage",new ActionError("MenuAll.formbean"));
			saveErrors(request, errors);
//			TODO ��ʱ���������
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				uname,hs.getId(),"MenuAll","C","LYC9999999",l_end - l_begin);
			return(mapping.findForward("success"));
		}
	}


	/**
	 * ��ָ����������ݴ�����ʾFormBeen(С���)
	 * 
	 * @param ActionForm ��ʾFormBeen
	 * @param String ���ʿ���
	 * @return String 1���ɹ���0�����ɹ���
	 * @exception Exception
	 */
	public String setFormbeen(ActionForm form,String page,int currrowshow) throws Exception{
	
		int count;
		String strsql,key;
		String[] menuid=null;
		String[] mnamec=null;
		String[] mnamee=null;
		String[] mfile=null;
		String[] mfilee=null;
		String[] appsysshow=null;
		String[] appsysid=null;
		String mpurview="";
		
		DataBean dbBean = new DataBean();
	
		String temp=null;
		//ȡ����		
		if (page.equalsIgnoreCase("1")){
			strsql="SELECT MENUID,MNAMEC,MNAMEE,MFILE,MFILEE,APPSYS,MPURVIEW,cshowc  " +
				"FROM menuall ,codes " +
				"WHERE  Substr(menuid,3,2) ='00' and ccode=appsys and cclass='00' " +
				"ORDER BY MENUID ASC";   
		}
		else{
			
			key=(String) PropertyUtils.getSimpleProperty(form, "key");
			//key=key.substring(0,2);
			strsql="SELECT MENUID,MNAMEC,MNAMEE,MFILE,MFILEE,APPSYS,MPURVIEW,cshowc  " +
				"FROM menuall ,codes " +
				"WHERE  Substr(menuid,1,2) = '"+key+"' AND Substr(menuid,3,2) <> '00' and ccode=appsys and cclass='00' " +
				"ORDER BY MENUID ASC";
		}
		try{
			dbBean.executeSelect(strsql);
		} catch (Exception e) {
			errors.add("errormessage",new ActionError("MenuAll.readdb"));
			return "0";
		}
		
		String menu="";
		int currrow=-1;
		if(currrowshow!=-1)
			menu=((String) PropertyUtils.getIndexedProperty(form, "menuid",currrowshow)).trim();
	
		//����������������	
		count=dbBean.getRowCount();
		//if (count<2)
		//	count=2;
			
		menuid= new String[count];
		mnamec=new String[count];
		mnamee=new String[count];
		mfile=new String[count];
		mfilee=new String[count];
		appsysshow=new String[count];
		appsysid=new String[count];
		
		//��ֵ���飬����ȥ�ո�
		for(int i=0;i<count;i++){
			menuid[i]=dbBean.getElementValue(i,0);
			mnamec[i]=dbBean.getElementValue(i,1);
			mnamee[i]=dbBean.getElementValue(i,2);
			mfile[i]=dbBean.getElementValue(i,3);
			mfilee[i]=dbBean.getElementValue(i,4);
			appsysid[i]=dbBean.getElementValue(i,5);
			
			appsysshow[i]=dbBean.getElementValue(i,7);
			
			menuid[i]=menuid[i].trim();
			mnamec[i]=mnamec[i].trim();
			mnamee[i]=mnamee[i].trim();
			mfile[i]=mfile[i].trim();
			mfilee[i]=mfilee[i].trim();
			appsysshow[i]=appsysshow[i].trim();
			
			if(menuid[i].equalsIgnoreCase(menu)&& (!menuid[i].equals(""))){
				mpurview=dbBean.getElementValue(i,6);
				
				if (mpurview.equals("")){
					mpurview="0";
				}
				
				currrow=i;
			}
		}
		
		String[] mpurviewshow=null;
		String[] mpurviewid=null;
		if(currrow!=-1){
			//����Ȩ��
			if(funPurview.decodePurview(Integer.parseInt(mpurview),appsysid[currrow])){
				mpurviewid=funPurview.getPurview();
				mpurviewshow=funPurview.getCshowc();
			}
			//״̬����
			PropertyUtils.setIndexedProperty(form,"rowstate",currrow,"0");	
		}
		
		//��formbean�и�ֵ
		PropertyUtils.setSimpleProperty(form, "currrowshow",String.valueOf(currrow));
		PropertyUtils.setSimpleProperty(form, "mpurviewshow",mpurviewshow);
		PropertyUtils.setSimpleProperty(form, "mpurviewid",mpurviewid);
		PropertyUtils.setSimpleProperty(form, "count",String.valueOf(count));	
		PropertyUtils.setSimpleProperty(form, "menuid",menuid);			
		PropertyUtils.setSimpleProperty(form, "mnamec",mnamec);
		PropertyUtils.setSimpleProperty(form, "mnamee",mnamee);
		PropertyUtils.setSimpleProperty(form, "mfile",mfile);
		PropertyUtils.setSimpleProperty(form, "mfilee",mfilee);
		PropertyUtils.setSimpleProperty(form, "appsysid",appsysid);
		PropertyUtils.setSimpleProperty(form, "appsysshow",appsysshow);
	
		return "1";
	}



}

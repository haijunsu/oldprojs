/*
 * @(#)FieldsAction.java  2003-8-11
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.idn.sql.DynaSqlBean;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author haizhou
 */
public class FieldsAction extends Action {
	
	/**
	 * ���÷�װBean-com.idn.log.LogWrapper��������־��¼
	 */
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(FieldsAction.class);
	private int intTemp = 0;
	private String strSql;
	private String[] strTemp = null;
	ActionErrors errors = new ActionErrors();

	/**
	 * 
	 */
	public FieldsAction() {
		super();
	}
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,	HttpServletResponse response)throws Exception {
				String stemp,flag,page,strpage;
				int currrowshow;
				String temp=null;
				try{
					//ȡ��־
					flag=(String) PropertyUtils.getSimpleProperty(form, "flag");
					currrowshow=Integer.parseInt((String) PropertyUtils.getSimpleProperty(form, "currrowshow"));
					log.debug("flag:" + flag);
					log.debug("currrow:" + currrowshow);
					//����Ҫ�ύ���ݿ��ʱ��ֱ�Ӹ�ֵ
					if (flag.equalsIgnoreCase("") || flag.substring(flag.length()-2).equalsIgnoreCase("xx")){
						temp=setFormbeen(form,currrowshow);
						if (temp.equals("0")){
					  		saveErrors(request, errors);
						}
							return(mapping.findForward("success"));
					}
					//����
					int strreint = fsave(form,flag);
					//ˢ����ҳ
					temp=setFormbeen(form,currrowshow);
					if (temp.equals("0")){
						saveErrors(request, errors);
					}
			
			
					return(mapping.findForward("success"));		
					
				}catch(Exception e){
					return(mapping.findForward("success"));
				}
				
				
			}
			
	
	
	/**
	 * ��ָ����������ݴ�����ʾFormBeen(С���)
	 * 
	 * @param ActionForm ��ʾFormBeen
	 * 
	 * @return String 1���ɹ���0�����ɹ���
	 * @exception Exception
	 */
	public String setFormbeen(ActionForm form,int currrowshow) throws Exception{
		int count;
		String strsql="",key;
		String fqueryid = "",ffieldid = "";
		String[][] s_data = null;
		int i_rowcount,i_colcount;
		try{
			strsql="SELECT FIELDID,FTYPE,FLENGTH,FDIGITS,FHEADERC,FHEADERE,CCLASS FROM FIELDS ORDER BY FIELDID";   
			log.debug("Strsql:" + strsql);		
			commsearch.CommSQL sis = new commsearch.CommSQL();
			s_data = sis.executeSelect(strsql);
		}catch(Exception e){
			
		}
		String menu="";
		int currrow=-1;
		if(currrowshow!=-1)
		{
			ffieldid = ((String) PropertyUtils.getIndexedProperty(form, "fieldid",currrowshow)).trim();
		}
		String[] ftypeeid = new String[8];
		String[] ftypeeshow = new String[8];
		ftypeeid[0] = "��������";
		ftypeeid[1] = "CHAR";
		ftypeeid[2] = "VARCHAR";
		ftypeeid[3] = "INTEGER";
		ftypeeid[4] = "SMALLINT";
		ftypeeid[5] = "DATE";
		ftypeeid[6] = "DECIMAL";
		ftypeeid[7] = "LONG";
		ftypeeshow[0] = "";
		ftypeeshow[1] = "�ַ���";
		ftypeeshow[2] = "�ɱ��ַ���";
		ftypeeshow[3] = "����";
		ftypeeshow[4] = "С����";
		ftypeeshow[5] = "������";
		ftypeeshow[6] = "������";
		ftypeeshow[7] = "������";
		PropertyUtils.setSimpleProperty(form, "ftypeeid",ftypeeid);
		PropertyUtils.setSimpleProperty(form, "ftypeeshow",ftypeeshow);	
		//s_data = null;
		if (s_data == null)
		{
			PropertyUtils.setSimpleProperty(form, "currrowshow","-1");
			PropertyUtils.setSimpleProperty(form, "currrowshowold","0");
			PropertyUtils.setSimpleProperty(form, "count","0");
			return "1";
		}
		i_rowcount = s_data.length;
		i_colcount = s_data[0].length;
		count = i_rowcount;
		PropertyUtils.setSimpleProperty(form, "count",Integer.toString(count));
		String[] fieldid = new String[count];
		String[] ftypeshow = new String[count];
		String[] flength = new String[count];
		String[] fdigits = new String[count];
		String[] fheaderc = new String[count];
		String[] fheadere = new String[count];
		String[] cclass = new String[count];
		String[] ftypeid = new String[count];
		log.debug("��λ�ļ�¼��Ϊ:" + Integer.toString(i_rowcount));
		try{
			for(int i_fori=0;i_fori<count;i_fori++){
				fieldid[i_fori] = s_data[i_fori][0];
				ftypeid[i_fori] = s_data[i_fori][1];
				if(ftypeid[i_fori].equalsIgnoreCase("CHAR")){
					ftypeshow[i_fori] = "�ַ���";
				}
				if(ftypeid[i_fori].equalsIgnoreCase("INTEGER")){
					ftypeshow[i_fori] = "����";
				}
				if(ftypeid[i_fori].equalsIgnoreCase("SMALLINT")){
					ftypeshow[i_fori] = "С����";
				}
				if(ftypeid[i_fori].equalsIgnoreCase("LONG")){
					ftypeshow[i_fori] = "������";
				}
				if(ftypeid[i_fori].equalsIgnoreCase("DATE")){
					ftypeshow[i_fori] = "������";
				}
				if(ftypeid[i_fori].equalsIgnoreCase("DECIMAL")){
					ftypeshow[i_fori] = "������";
				}
				if(ftypeid[i_fori].equalsIgnoreCase("VARCHAR")){
					ftypeshow[i_fori] = "�ɱ��ַ���";
				}
				//log.debug("test:" + ftypeshow[i_fori] + "/" + ftypeid[i_fori]);
				flength[i_fori] = s_data[i_fori][2];
				fdigits[i_fori] = s_data[i_fori][3];
				fheaderc[i_fori] = s_data[i_fori][4];
				fheadere[i_fori] = s_data[i_fori][5];
				cclass[i_fori] = s_data[i_fori][6];
				if(fieldid[i_fori].equalsIgnoreCase(ffieldid)&& (!fieldid[i_fori].equals(""))){
					currrow=i_fori;
				}
			}
			if(currrow!=-1){
			//״̬����
				PropertyUtils.setIndexedProperty(form,"rowstate",currrow,"0");	
			}
			PropertyUtils.setSimpleProperty(form, "currrowshow",String.valueOf(currrow));
			PropertyUtils.setSimpleProperty(form, "fieldid",fieldid);
			PropertyUtils.setSimpleProperty(form, "ftypeid",ftypeid);
			PropertyUtils.setSimpleProperty(form, "flength",flength);
			PropertyUtils.setSimpleProperty(form, "fdigits",fdigits);
			PropertyUtils.setSimpleProperty(form, "fheaderc",fheaderc);
			PropertyUtils.setSimpleProperty(form, "fheadere",fheadere);
			PropertyUtils.setSimpleProperty(form, "cclass",cclass);
			PropertyUtils.setSimpleProperty(form, "ftypeshow",ftypeshow);
		}catch(Exception e){
				
		}
		return "1";
	}		
				
	private int fsave(ActionForm form,String strflag){
		String fieldid = "";
		String ftype = "";
		String flength = "";
		String fdigits = "";
		String fheaderc = "";
		String fheadere = "";
		String cclass = "";
		String strfieldidkey = "";
		String[] strsql=null;
		Vector vecTemp=new Vector();
		DynaSqlBean dybBean = new DynaSqlBean();
		int currrowshowold;
		try{
			if(strflag.equalsIgnoreCase("commit") || strflag.equalsIgnoreCase("exit"))
				currrowshowold=Integer.parseInt((String) PropertyUtils.getSimpleProperty(form, "currrowshow"));
				
			else
			    currrowshowold=Integer.parseInt((String) PropertyUtils.getSimpleProperty(form, "currrowshowold"));
			String rowstate = "";
			//ƴд�ύ���ݿ�Ĵ���
			rowstate = ((String) PropertyUtils.getIndexedProperty(form, "rowstate",currrowshowold)).trim();
			log.debug("rowstate:" + rowstate);
			if (strflag.equalsIgnoreCase("del"))
				rowstate="2";
			fieldid = ((String) PropertyUtils.getIndexedProperty(form, "fieldid",currrowshowold)).trim();
			ftype = ((String) PropertyUtils.getIndexedProperty(form, "ftypeid",currrowshowold)).trim();
			flength = ((String) PropertyUtils.getIndexedProperty(form, "flength",currrowshowold)).trim();
			fdigits = ((String) PropertyUtils.getIndexedProperty(form, "fdigits",currrowshowold)).trim();
			fheaderc = ((String) PropertyUtils.getIndexedProperty(form, "fheaderc",currrowshowold)).trim();
			fheadere = ((String) PropertyUtils.getIndexedProperty(form, "fheadere",currrowshowold)).trim();
			cclass = ((String) PropertyUtils.getIndexedProperty(form, "cclass",currrowshowold)).trim();
			if(rowstate.equalsIgnoreCase("1"))
			{																	  
				strfieldidkey = ((String) PropertyUtils.getIndexedProperty(form, "fieldidkey",currrowshowold)).trim();
				log.debug("strfieldidsky:" + strfieldidkey);
				vecTemp.addElement("update fields set fieldid = '" + fieldid.trim().toUpperCase() + "',ftype = '" + ftype.toUpperCase() + "',flength = " + flength + ",fdigits = " + fdigits + ",fheaderc = '" + fheaderc + "',fheadere = '" + fheadere + "',cclass = '" + cclass.trim() + "' where fieldid = '" + strfieldidkey.trim().toUpperCase() + "'");
			}
			if(rowstate.equalsIgnoreCase("4"))
			{
				vecTemp.addElement("insert into fields(fieldid,ftype,flength,fdigits,fheaderc,fheadere,cclass) values ('" + fieldid.trim().toUpperCase() + "','" + ftype.trim().toUpperCase() + "'," + flength + "," + fdigits + ",'" + fheaderc + "','" + fheadere + "','" + cclass + "')");
			}
			if(rowstate.equalsIgnoreCase("2"))
			{
				strfieldidkey = ((String) PropertyUtils.getIndexedProperty(form, "fieldidkey",currrowshowold)).trim();
				vecTemp.addElement("delete from fields where fieldid = '" + strfieldidkey.trim().toUpperCase() + "'");
			}
			strsql = (String[]) vecTemp.toArray(new String[0]);
			//ִ��sql���
			try{
				  dybBean.setSql(strsql);
				  dybBean.executeBatch();
			} catch (Exception e){
				  
			}
				
		}catch(Exception e){
			return -1;
		}
		
		return 1;		
	}
				
				

}

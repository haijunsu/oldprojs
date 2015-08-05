/*
 * @(#)CreatetableAction.java  2003-8-1
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//import system.fun.FunPurview;
import java.util.*;
//import com.idn.log.LogWrapper;
import com.idn.sql.*;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author haizhou
 */
public class CreatetableAction extends Action {
	
	/**
	 * 利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	 */
	private static com.idn.log.LogWrapper log =
			new com.idn.log.LogWrapper(CreatetableAction.class);
	private int intTemp = 0;
	private String strSql;
	private String[] strTemp = null;
	ActionErrors errors = new ActionErrors();

	/**
	 * 
	 */
	public CreatetableAction() {
		super();
	}
	
	/** (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,
		HttpServletRequest request,	HttpServletResponse response)throws Exception {
		errors.clear();
	//	errors.add("header",new ActionError("errors.header"));
		//errors.add("footer",new ActionError("errors.footer"));
		String stemp,flag,page,strpage;
		int currrowshow;
		DynaSqlBean dybBean = new DynaSqlBean();
		DataBean dbselect = new DataBean();
		DynaSqlBean dcreate = new DynaSqlBean();
		Vector vecTemp=new Vector();
		String temp=null;
		try{
			//取标志
			flag=(String) PropertyUtils.getSimpleProperty(form, "flag");
			page=(String) PropertyUtils.getSimpleProperty(form, "page");
			currrowshow=Integer.parseInt((String) PropertyUtils.getSimpleProperty(form, "currrowshow"));
			strpage = page;
			//log.debug("page::" + page);
			//log.debug("flag:" + flag);
			//log.debug("currrow:" + currrowshow);
			//不需要提交数据库的时候直接赋值
			if (flag.equalsIgnoreCase("") || flag.substring(flag.length()-2).equalsIgnoreCase("xx")){
				temp=setFormbeen(form,strpage,currrowshow);
				if (temp.equals("0")){
				  saveErrors(request, errors);
				 }
				 return(mapping.findForward("success"));
			}
			String[] strsql=null;
			String id=null;
			id=(String) PropertyUtils.getSimpleProperty(form, "id");
			int currrowshowold;
			String queryid="";
			String qnamec="";
			String qnamee="";
			String qwhere="";
			String seq = "";
			String qkattri = "";
			String fieldid = "";
			String qfieldid = "";
			String ftype = "";
			String flength = "";
			String fdigits = "";
			String fheaderc = "";
			String fheadere = "";
			String qdefault = "";
			String seqhid = "";
			String key="",strtablename = "";
			String cclass = "";
//			分不同的情况取当前行的值
		    if(flag.equalsIgnoreCase("commit") || flag.equalsIgnoreCase("exit"))
				currrowshowold=Integer.parseInt((String) PropertyUtils.getSimpleProperty(form, "currrowshow"));
			else
			    currrowshowold=Integer.parseInt((String) PropertyUtils.getSimpleProperty(form, "currrowshowold"));
			if(currrowshowold == -1){
				currrowshowold = 0;		  
			}
			if(flag.equalsIgnoreCase("changenext"))
			{
				page = "1"; 
			}
			if(flag.equalsIgnoreCase("return"))
			{
				page = "2";
			}
			String rowstate = "";
//			拼写提交数据库的代码
			rowstate = ((String) PropertyUtils.getIndexedProperty(form, "rowstate",currrowshowold)).trim();
			log.debug("rowstate:" + rowstate);
		    if (flag.equalsIgnoreCase("del"))
			  rowstate="2";
			  
//			按当前行取值
			if(page.equalsIgnoreCase("1"))
			{
				queryid=((String) PropertyUtils.getIndexedProperty(form, "queryid",currrowshowold)).trim();
				qnamec=((String) PropertyUtils.getIndexedProperty(form, "qnamec",currrowshowold)).trim();
				qnamee=((String[]) PropertyUtils.getSimpleProperty(form, "qnamee"))[0].trim();
				qwhere=((String[]) PropertyUtils.getSimpleProperty(form, "qwhere"))[0].trim();
				if(rowstate.equalsIgnoreCase("1"))
					vecTemp.addElement("update querys set qnamec = '" + qnamec + "',qnamee = '" + qnamee + "',qwhere = '" + qwhere.trim() + "',qtable = '" + queryid.trim().toUpperCase() + "' where queryid = '" + queryid.trim().toUpperCase() + "'");
				if(rowstate.equalsIgnoreCase("4"))
					vecTemp.addElement("insert into querys(queryid,qnamec,qnamee,qwhere,qtable) values('"+queryid.toUpperCase()+"','"+qnamec+"','"+qnamee+"','"+qwhere+"','" + queryid.toUpperCase() + "')");
				if(rowstate.equalsIgnoreCase("2"))
					vecTemp.addElement("delete from querys where queryid = '" + queryid.trim().toUpperCase() + "'");
			}
			if(page.equalsIgnoreCase("2"))
			{
				key=(String) PropertyUtils.getSimpleProperty(form, "key");
				seq = ((String) PropertyUtils.getIndexedProperty(form, "seq",currrowshowold)).trim();
				qkattri = ((String) PropertyUtils.getIndexedProperty(form, "qkattri",currrowshowold)).trim();
				fieldid = ((String) PropertyUtils.getIndexedProperty(form, "fieldid",currrowshowold)).trim();
				fheaderc = ((String) PropertyUtils.getIndexedProperty(form, "fheaderc",currrowshowold)).trim();
				fheadere = ((String[]) PropertyUtils.getSimpleProperty(form, "fheadere"))[0].trim();
				qdefault = ((String) PropertyUtils.getIndexedProperty(form, "qdefault",currrowshowold)).trim();
				
				if(rowstate.equalsIgnoreCase("1"))
				{
					seqhid = ((String[]) PropertyUtils.getSimpleProperty(form, "seqhid"))[0].trim();
					vecTemp.addElement("update qminutia set fieldid = '" + fieldid.toUpperCase() + "',qfieldid = '" + fieldid.toUpperCase() + "',seq = " + seq + ",qkattri = '" + qkattri + "',qheaderc = '" + fheaderc + "',qheadere = '" + fheadere + "',qdefault = '" + qdefault + "' where queryid = '" + key.trim() + "' and seq = " + seqhid);
				}
				if(rowstate.equalsIgnoreCase("4"))
				{
					vecTemp.addElement("insert into qminutia(queryid,seq,qfieldid,fieldid,qfattri,qkattri,qsattri,qiattri,qmattri,qheaderc,qheadere,qdefault) values ('" + key.trim() + "'," + seq + ",'" + fieldid.toUpperCase() + "','" + fieldid.toUpperCase() + "','0','" + qkattri + "','1','0','0','" + fheaderc + "','" + fheadere + "','" + qdefault + "')");
				}
				if(rowstate.equalsIgnoreCase("2"))
					vecTemp.addElement("delete from qminutia where queryid = '" + key.trim().toUpperCase() + "' and seq = " + seq);
					
			}  
			
			 
			strsql = (String[]) vecTemp.toArray(new String[0]);
			
			 //执行sql语句
			 try{
				  dybBean.setSql(strsql);
				  dybBean.executeBatch();
			  } catch (Exception e){
				  errors.add("errormessage",new ActionError("SalaryMenuAll.writedb"));
				  saveErrors(request, errors);
			  }
			
//			建表
			if (flag.equalsIgnoreCase("crtable")){
			  	//key=((String) PropertyUtils.getIndexedProperty(form, "queryid",currrowshowold)).trim();
			 	 log.debug("key:::" + key);
				  Create(key);
			
			  }
			 //刷新网页
			  temp=setFormbeen(form,strpage,currrowshow);
			  if (temp.equals("0")){
					 // saveErrors(request, errors);
			  }
			
			
			
			
			
			return(mapping.findForward("success"));
		} catch (Exception e){
			//errors.add("errormessage",new ActionError("SalaryMenuAll.formbean"));
			//saveErrors(request, errors);
			return(mapping.findForward("success"));
		}
	}
	
	
	
	
	/**
		 * 将指定类代码内容传入显示FormBeen(小表格)
		 * 
		 * @param ActionForm 显示FormBeen
		 * 
		 * @return String 1、成功；0、不成功；
		 * @exception Exception
		 */
		public String setFormbeen(ActionForm form,String page,int currrowshow) throws Exception{
			int count;
			String strsql="",key;
			String fqueryid = "",fseq = "";
			
			String[][] s_data = null;
			int i_rowcount,i_colcount;
			try{
			
			if (page.equalsIgnoreCase("1")){
				strsql="SELECT QUERYID,QNAMEC,QNAMEE,QWHERE FROM QUERYS WHERE  QWHERE = 'TABLE' ORDER BY QUERYID ASC";   
			}
			if(page.equalsIgnoreCase("2"))
			{
				key=(String) PropertyUtils.getSimpleProperty(form, "key");
				strsql = "SELECT QMINUTIA.SEQ,QMINUTIA.QKATTRI,QMINUTIA.FIELDID,FIELDS.FTYPE,";   
         		strsql = strsql + "FIELDS.FLENGTH,FIELDS.FDIGITS,QMINUTIA.QHEADERC,QMINUTIA.QHEADERE,FIELDS.CCLASS,FIELDS.FIELDID,";   
         		strsql = strsql + "QMINUTIA.QUERYID,QMINUTIA.QFIELDID,QMINUTIA.QFATTRI,QMINUTIA.QSATTRI,";   
         		strsql = strsql + "QMINUTIA.QIATTRI,QMINUTIA.QMATTRI,QMINUTIA.QDEFAULT,QMINUTIA.QUERYID FROM QMINUTIA,FIELDS";  
   				strsql = strsql + " WHERE ((QMINUTIA.FIELDID = FIELDS.FIELDID) and (QMINUTIA.QUERYID = '" + key.trim() + "'))";   
				strsql = strsql + " ORDER BY QMINUTIA.SEQ ASC";
			}
			log.debug("Strsql:" + strsql);		
			commsearch.CommSQL sis = new commsearch.CommSQL();
			s_data = sis.executeSelect(strsql);
			}catch(Exception e){
				
			}
			String menu="";
			int currrow=-1;
			if(currrowshow!=-1)
			{
				if (page.equalsIgnoreCase("1")){
					fqueryid=((String) PropertyUtils.getIndexedProperty(form, "queryid",currrowshow)).trim();
				}
				if(page.equalsIgnoreCase("2")){
					fseq = ((String) PropertyUtils.getIndexedProperty(form, "seq",currrowshow)).trim();
				}
			}
			String[] ftypeeid = new String[7];
			String[] ftypeeshow = new String[7];
			ftypeeid[0] = "CHAR";
			ftypeeid[1] = "VARCHAR";
			ftypeeid[2] = "INTEGER";
			ftypeeid[3] = "SMALLINT";
			ftypeeid[4] = "DATE";
			ftypeeid[5] = "DECIMAL";
			ftypeeid[6] = "LONG";
			ftypeeshow[0] = "字符型";
			ftypeeshow[1] = "可变字符型";
			ftypeeshow[2] = "整型";
			ftypeeshow[3] = "小整型";
			ftypeeshow[4] = "日期型";
			ftypeeshow[5] = "精度型";
			ftypeeshow[6] = "长整型";
			PropertyUtils.setSimpleProperty(form, "ftypeeid",ftypeeid);
			PropertyUtils.setSimpleProperty(form, "ftypeeshow",ftypeeshow);	
			if (s_data == null)
			{
				
				PropertyUtils.setSimpleProperty(form, "queryid",null);
					
				if(page.equalsIgnoreCase("1"))
				{
					PropertyUtils.setSimpleProperty(form, "qnamec",null);
					PropertyUtils.setSimpleProperty(form, "qnamee",null);			
					PropertyUtils.setSimpleProperty(form, "qwhere",null);
				}
				if(page.equalsIgnoreCase("2")){
					PropertyUtils.setSimpleProperty(form, "seq",null);	
					PropertyUtils.setSimpleProperty(form, "qkattri",null);
					PropertyUtils.setSimpleProperty(form, "fieldid",null);
					PropertyUtils.setSimpleProperty(form, "ftypeid",null);
					PropertyUtils.setSimpleProperty(form, "flength",null);
					PropertyUtils.setSimpleProperty(form, "fdigits",null);
					PropertyUtils.setSimpleProperty(form, "fheaderc",null);
					PropertyUtils.setSimpleProperty(form, "fheadere",null);
					PropertyUtils.setSimpleProperty(form, "cclass",null);
					PropertyUtils.setSimpleProperty(form, "qdefault",null);
					PropertyUtils.setSimpleProperty(form, "ftypeshow",null);
				}
				PropertyUtils.setSimpleProperty(form, "currrowshow","-1");
				PropertyUtils.setSimpleProperty(form, "currrowshowold","0");
				PropertyUtils.setSimpleProperty(form, "count","0");
				PropertyUtils.setIndexedProperty(form,"rowstate",0,"-1");
				return "1";
			}
			i_rowcount = s_data.length;
			i_colcount = s_data[0].length;
			count = i_rowcount;
			PropertyUtils.setSimpleProperty(form, "count",Integer.toString(count));
			String[] queryid = new String[count];     //表名
			String[] qnamec = new String[count];     //单位名称
			String[] qnamee = new String[count];     //单位属性(03)
			String[] qwhere = new String[count];     //上级单位
			String[] seq = new String[count];
			String[] qkattri = new String[count];
			String[] fieldid = new String[count];
			String[] qfieldid = new String[count];
			String[] ftypeshow = new String[count];
			String[] flength = new String[count];
			String[] fdigits = new String[count];
			String[] fheaderc = new String[count];
			String[] fheadere = new String[count];
			String[] qfattri = new String[count];
			String[] qsattri = new String[count];
			String[] qiattri = new String[count];
			String[] qmattri = new String[count];
			String[] qheaderc = new String[count];
			String[] qheadere = new String[count];
			String[] qdefault = new String[count];
			String[] cclass = new String[count];
			String[] ftypeid = new String[count];
			
			
			log.debug("单位的记录数为:" + Integer.toString(i_rowcount));
			
			try{
				for(int i_fori=0;i_fori<count;i_fori++){
					if(page.equalsIgnoreCase("1"))
					{
						queryid[i_fori] = s_data[i_fori][0];
						qnamec[i_fori] = s_data[i_fori][1];
						qnamee[i_fori] = s_data[i_fori][2];
						qwhere[i_fori] = s_data[i_fori][3];
						if(queryid[i_fori].equalsIgnoreCase(fqueryid)&& (!queryid[i_fori].equals(""))){
								currrow=i_fori;
						}
					}
					if(page.equalsIgnoreCase("2")){
						seq[i_fori] = s_data[i_fori][0];
						qkattri[i_fori] = s_data[i_fori][1];
						fieldid[i_fori] = s_data[i_fori][2];
						ftypeid[i_fori] = s_data[i_fori][3];
						if(ftypeid[i_fori].equalsIgnoreCase("CHAR")){
							ftypeshow[i_fori] = "字符型";
						}
						if(ftypeid[i_fori].equalsIgnoreCase("INTEGER")){
							ftypeshow[i_fori] = "整型";
						}
						if(ftypeid[i_fori].equalsIgnoreCase("SMALLINT")){
							ftypeshow[i_fori] = "小整型";
						}
						if(ftypeid[i_fori].equalsIgnoreCase("LONG")){
							ftypeshow[i_fori] = "长整型";
						}
						if(ftypeid[i_fori].equalsIgnoreCase("DATE")){
								ftypeshow[i_fori] = "日期型";
						}
						if(ftypeid[i_fori].equalsIgnoreCase("DECIMAL")){
								ftypeshow[i_fori] = "精度型";
						}
						if(ftypeid[i_fori].equalsIgnoreCase("VARCHAR")){
								ftypeshow[i_fori] = "可变字符型";
						}
						//log.debug("test:" + ftypeshow[i_fori] + "/" + ftypeid[i_fori]);
						flength[i_fori] = s_data[i_fori][4];
						fdigits[i_fori] = s_data[i_fori][5];
						fheaderc[i_fori] = s_data[i_fori][6];
						fheadere[i_fori] = s_data[i_fori][7];
						cclass[i_fori] = s_data[i_fori][8];
						queryid[i_fori] = s_data[i_fori][17];
						qdefault[i_fori] = s_data[i_fori][16];
						if(seq[i_fori].equalsIgnoreCase(fseq)&& (!seq[i_fori].equals(""))){
								currrow=i_fori;
						}
					}
				}
				if(currrow!=-1){
					//状态清零
					PropertyUtils.setIndexedProperty(form,"rowstate",currrow,"0");	
				}
				PropertyUtils.setSimpleProperty(form, "currrowshow",String.valueOf(currrow));
				PropertyUtils.setSimpleProperty(form, "queryid",queryid);
				if(page.equalsIgnoreCase("1")){
					PropertyUtils.setSimpleProperty(form, "qnamec",qnamec);
					PropertyUtils.setSimpleProperty(form, "count",String.valueOf(count));	
					PropertyUtils.setSimpleProperty(form, "qnamee",qnamee);			
					PropertyUtils.setSimpleProperty(form, "qwhere",qwhere);
						//PropertyUtils.setSimpleProperty(form, "mnamee",mnamee);
				}
				if(page.equalsIgnoreCase("2")){
					PropertyUtils.setSimpleProperty(form, "seq",seq);	
					PropertyUtils.setSimpleProperty(form, "qkattri",qkattri);
					PropertyUtils.setSimpleProperty(form, "fieldid",fieldid);
					PropertyUtils.setSimpleProperty(form, "ftypeid",ftypeid);
					PropertyUtils.setSimpleProperty(form, "flength",flength);
					PropertyUtils.setSimpleProperty(form, "fdigits",fdigits);
					PropertyUtils.setSimpleProperty(form, "fheaderc",fheaderc);
					PropertyUtils.setSimpleProperty(form, "fheadere",fheadere);
					PropertyUtils.setSimpleProperty(form, "cclass",cclass);
					PropertyUtils.setSimpleProperty(form, "qdefault",qdefault);
					PropertyUtils.setSimpleProperty(form, "ftypeshow",ftypeshow);
							
				}
			}catch(Exception e){
				
			}
			return "1";
		}
		
		//建表函数
		private void Create(String ckey){
			String strcol = "SELECT QMINUTIA.SEQ,QMINUTIA.QKATTRI,QMINUTIA.FIELDID,FIELDS.FTYPE,";   
			strcol = strcol + "FIELDS.FLENGTH,FIELDS.FDIGITS,QMINUTIA.QHEADERC,QMINUTIA.QHEADERE,FIELDS.CCLASS,FIELDS.FIELDID,";   
			strcol = strcol + "QMINUTIA.QUERYID,QMINUTIA.QFIELDID,QMINUTIA.QFATTRI,QMINUTIA.QSATTRI,";   
			strcol = strcol + "QMINUTIA.QIATTRI,QMINUTIA.QMATTRI,QMINUTIA.QDEFAULT,QMINUTIA.QUERYID FROM QMINUTIA,FIELDS";  
			strcol = strcol + " WHERE ((QMINUTIA.FIELDID = FIELDS.FIELDID) and (QMINUTIA.QUERYID = '" + ckey.trim() + "'))";   
			strcol = strcol + " ORDER BY QMINUTIA.SEQ ASC";
			commsearch.CommSQL sis = new commsearch.CommSQL();
			String[][] s_re = null;
			String s_crta = "";
			String strtype = "";
			String strfieldid = "",strkatr = "",strdefault = "";
			String strlength = "0",strdigits = "0";
			s_re = sis.executeSelect(strcol);
			String strprimary = "";
			DynaSqlBean dcreatee = new DynaSqlBean();
			if(s_re != null){
			int i_fields = 0;
			i_fields = s_re.length;
			s_crta = "DROP TABLE " + ckey.trim();
			try{
				dcreatee.setSql(s_crta);
				dcreatee.execute();
			}catch(Exception e){
				log.debug("e:" + e.getMessage());	
			}
			//拼建表SQL语句
			if(i_fields > 0){
				s_crta = "CREATE TABLE "+ckey.trim().toUpperCase()+" (";
				for(int nf=0;nf<i_fields;nf++){
					strkatr = s_re[nf][1];
					strfieldid = s_re[nf][2];
					strtype = s_re[nf][3].toUpperCase();
					strlength = s_re[nf][4];
					strdigits = s_re[nf][5];
					strdefault = s_re[nf][16];
					if(strlength.trim().length() <= 0) strlength = "0";
					if(strdigits.trim().length() <= 0) strdigits = "0";
					if(strtype.equalsIgnoreCase("CHAR") || strtype.equalsIgnoreCase("VARCHAR"))
					{
						s_crta = s_crta +strfieldid+" "+strtype+"("+strlength+")";
						if(strdefault.length() > 0 && strdefault.length() < Integer.parseInt(strlength))
						{
							s_crta = s_crta + " WITH DEFAULT '" + strdefault + "'";
						}
								
					}
							
					if(strtype.equalsIgnoreCase("INTEGER") || strtype.equalsIgnoreCase("SMALLINT") || strtype.equalsIgnoreCase("LONG"))
					{
						s_crta = s_crta +strfieldid+" "+strtype+"";
						if(strdefault.length() > 0)
						{
							try{
								s_crta = s_crta + " WITH DEFAULT " + Integer.toString(Integer.parseInt(strdefault));
							}catch(Exception e){
								//s_crta = s_crta;
							}
									
									
						}
					}
					if(strtype.equalsIgnoreCase("DATE")){
						s_crta = s_crta +strfieldid+" "+strtype+"";
						/*if(strdefault.length() > 0 && strdefault.length() < 10)
						{
							try{
								s_crta = s_crta + "WITH DEFAULT '" + strdefault + "'";
							}catch(Exception e){
							}
						
						}*/
					}	
					if(strtype.equalsIgnoreCase("DECIMAL"))
					{
						s_crta = s_crta +strfieldid+" "+strtype+"("+strlength+","+strdigits+")";
						try{
							s_crta = s_crta + " WITH DEFAULT " + Double.toString(Double.parseDouble(strdefault));
						}catch(Exception e){
						}
					}
					if(strkatr.equalsIgnoreCase("1"))
					{
						s_crta = s_crta +" NOT NULL ";
						strprimary = strprimary + strfieldid + ",";
																
					}
					s_crta = s_crta + ",";		
								 
				}
				strprimary = strprimary.trim().substring(0,strprimary.trim().length() - 1);
				s_crta = s_crta.trim().substring(0,s_crta.trim().length() - 1);
				if(strprimary.trim().length() > 0) s_crta = s_crta + ",PRIMARY KEY ("+strprimary+"))";
					try{
						log.debug("s_cr:" +s_crta);
						dcreatee.setSql(s_crta);
						dcreatee.execute();
					}catch(Exception e){
							
					}
				}
			
			
			}
		}
	
	
}

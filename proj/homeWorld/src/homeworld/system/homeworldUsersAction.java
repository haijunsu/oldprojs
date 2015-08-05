/*
 * @(#)homeworldUsersAction.java  2004-9-2
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2004 
 * All rights reserved. 
 */
package homeworld.system;

/**
 * <P> </P>
 * 
 * @version 0.1
 * @author lyc
 */

			import java.io.IOException;
			import java.util.Date;

			import java.util.Vector;

			import javax.servlet.ServletException;
			import javax.servlet.http.HttpServletRequest;
			import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

			import org.apache.commons.beanutils.PropertyUtils;
			import org.apache.struts.action.ActionError;
			import org.apache.struts.action.ActionErrors;
			import org.apache.struts.action.ActionForm;
			import org.apache.struts.action.ActionForward;
			import org.apache.struts.action.ActionMapping;
			import com.idn.secure.SecureAction;
			import com.idn.sql.*;
//			import system.fun.FunPurview;


			/**
			 * <P>置装费表管理</P>
			 * 
			 * @version 0.1
			 * @author 李永初
			 */
			public class homeworldUsersAction  extends SecureAction {
				ActionErrors errors = new ActionErrors();
	
				
				/**
				 * 构造函数
				 */
				public homeworldUsersAction () {
					super();
				}

				/** (non-Javadoc)
				 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
				 */
				public ActionForward executeme(ActionMapping mapping,ActionForm form,
					HttpServletRequest request,	HttpServletResponse response)throws IOException, ServletException {
		
					//初始化error对象		
					errors.clear();
					errors.add("header",new ActionError("errors.header"));
					errors.add("footer",new ActionError("errors.footer"));

					HttpSession hs = request.getSession();
					String uname=(String)hs.getAttribute("userid");
			
					//TODO 临时用来监测用
					long l_begin,l_end;
					l_begin = System.currentTimeMillis();		
					commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Users","B","LYC0000000",0);

				try{

					//		添加标题		
					PropertyUtils.setSimpleProperty(form, "title","用户");
					PropertyUtils.setSimpleProperty(form, "id","vndnum");
					PropertyUtils.setSimpleProperty(form, "show","用户");
					//		添加标题	

					String temp="";
	
		
					//1 : 修改(默认)  2 ：查询
					String se_up=(String)request.getParameter("se_up");
					if(se_up==null){se_up=(String) PropertyUtils.getSimpleProperty(form, "se_up");}
					PropertyUtils.setSimpleProperty(form,"se_up",se_up);
		
					//取标志
					String strFlag=(String) PropertyUtils.getSimpleProperty(form, "flag");
					String page=(String) PropertyUtils.getSimpleProperty(form, "page");
					String where=(String) PropertyUtils.getSimpleProperty(form, "where");

		
					//首次直接返回
					if (strFlag.equals("")){
						
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Users","E","LYC8888886",l_end - l_begin);
						return mapping.findForward("success");}
		
					//在次返回
					if(strFlag.equals("selectexic")){
						temp=setFormbeen(form,request,where);
						if (temp.equals("0")){saveErrors(request, errors);}
						PropertyUtils.setSimpleProperty(form, "currrow","-1"); 
						PropertyUtils.setSimpleProperty(form, "currrowshowold","-1");
						PropertyUtils.setSimpleProperty(form, "currrowshow","-1");

//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Users","E","LYC8888881",l_end - l_begin);
						return mapping.findForward("success");
					}

					if(strFlag.equals("hiddenexic")){
						temp=setFormbeen(form,request,where);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Users","E","LYC8888882",l_end - l_begin);
						return mapping.findForward("success");
					}

					if(strFlag.equals("changeexic")){
						temp=setFormbeen(form,request,where);
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Users","E","LYC8888883",l_end - l_begin);
						return mapping.findForward("success");
					}
						
		
					/*
					 *添加代码
					 */
							DynaSqlBean dybBean = new DynaSqlBean();
							Vector vecTemp=new Vector();

							String currrowshow;
							String[] strSql=null;
		
							currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrowshow"));

							//取当前行
							if(strFlag.equals("select"))
								currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrowshowold"));
		

								String vndnam=((String) PropertyUtils.getIndexedProperty(form, "vndnam",Integer.parseInt(currrowshow)));
								String vndnt1=((String) PropertyUtils.getIndexedProperty(form, "vndnt1",Integer.parseInt(currrowshow)));
								String vndnt2=((String) PropertyUtils.getIndexedProperty(form, "vndnt2",Integer.parseInt(currrowshow)));
								String vndnt3=((String) PropertyUtils.getIndexedProperty(form, "vndnt3",Integer.parseInt(currrowshow)));
								String rowstate=((String) PropertyUtils.getIndexedProperty(form, "rowstate",Integer.parseInt(currrowshow)));
					
								//取ID
								String vndnum=((String) PropertyUtils.getIndexedProperty(form, "vndnum",Integer.parseInt(currrowshow)));
								vndnum = vndnum.toUpperCase();
								
								DataBean dbBean = new DataBean();
								dbBean.executeSelect("select * from VNDINFO where vndnum='"+ vndnum+"'" );
								if(dbBean.getRowCount()!= 0 && rowstate.equals("4") ){
									temp=setFormbeen(form,request,where);
									PropertyUtils.setSimpleProperty(form, "currrow","-1"); 
									PropertyUtils.setSimpleProperty(form, "currrowshowold","-1");
									PropertyUtils.setSimpleProperty(form, "currrowshow","-1");
									errors.add("errormessage",new ActionError("Database.homeworldusers"));
									saveErrors(request, errors);
//									TODO 临时用来监测用
									l_end = System.currentTimeMillis();
									commsearch.util.CommActionLog.setTempLog(
										Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
										uname,hs.getId(),"Users","E","LYC8888884",l_end - l_begin);
									return mapping.findForward("success");
								}
								
								
//								hiddenbeg=String.valueOf(com.idn.util.DateUtil.parseDateToLong(year,month,date));


								if(rowstate.equals("1")){									
									vecTemp.addElement("delete from vndinfo where vndnum='"+vndnum+"'");
									vecTemp.addElement("insert into vndinfo values('"+vndnum+"','"+vndnam+"','"+vndnt1+"','"+vndnt2+"','"+vndnt3+"')");
								}
								if(rowstate.equals("4")){
									vecTemp.addElement("insert into vndinfo values('"+vndnum+"','"+vndnam+"','"+vndnt1+"','"+vndnt2+"','"+vndnt3+"')");
									Date dd=new Date();
									
									String beg=String.valueOf(com.idn.util.DateUtil.parseDateToLong(dd));
									
									String end=String.valueOf(com.idn.util.DateUtil.parseDateToLong(2029,1,1));
									
									vecTemp.addElement("insert into users values('"+vndnum+"','"+vndnum+"','98',394,'"+beg+"','"+end+"','"+vndnum+"','00000000000000000000000000000001')");
								}
								//执行sql语句
								strSql = (String[]) vecTemp.toArray(new String[0]);		
								try{
									dybBean.setSql(strSql);
									dybBean.executeBatch();
								} catch (Exception e){
									e.printStackTrace();
									errors.add("errormessage",new ActionError("Database.writedb"));
									saveErrors(request, errors);
								}
		
					currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrow"));

					/*
					 *添加代码
					 */

					//在次返回
					if(strFlag.equals("select")){
						temp=setFormbeen(form,request,where);
						if (temp.equals("0")){saveErrors(request, errors);}
//						TODO 临时用来监测用
						l_end = System.currentTimeMillis();
						commsearch.util.CommActionLog.setTempLog(
							Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
							uname,hs.getId(),"Users","E","LYC8888885",l_end - l_begin);
						return mapping.findForward("success");
					}

//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Users","E","LYC8888888",l_end - l_begin);
					//向formbean中赋值
					return mapping.findForward("success");	
		
				} catch (Exception e){
					e.printStackTrace();
					errors.add("errormessage",new ActionError("Database.formbean"));
					saveErrors(request, errors);
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Users","C","LYC9999999",l_end - l_begin);
					return(mapping.findForward("success"));
				}

			}

					/**
					 * 将指定类代码内容传入显示FormBeen(page=3)
					 * 
					 * @param ActionForm 显示FormBeen
					 * @param String 人员ID
					 * @return String 1、成功；0、不成功；
					 * @exception Exception
					 */
					public String setFormbeen(ActionForm form,HttpServletRequest request,String where) throws Exception {
			

						/*
						 *添加代码
						 */
						String strSql,key,currrowshow;
		
						DataBean dbBean = new DataBean();
		
						strSql="select * from VNDINFO where "+ where +" order by vndnum";

						dbBean.executeSelect(strSql);
	
						int count;
						count=dbBean.getRowCount();
					if (count<2){
						 count=2;
					}

					String[] vndnum= new String[count];//用户id
					String[] vndnam= new String[count];//用户姓名
					String[] vndnt1= new String[count];//用户全称1
					String[] vndnt2= new String[count];//用户全称2
					String[] vndnt3 = new String[count];//用户全称3

						for(int i=0;i<count;i++){
				
							vndnum[i]=dbBean.getElementValue(i,"vndnum").trim()  ;
							vndnam[i]=dbBean.getElementValue(i,"vndnam").trim() ;
			
							vndnt1[i]=dbBean.getElementValue(i,"vndnt1").trim();
							vndnt2[i]=dbBean.getElementValue(i,"vndnt2").trim();
							vndnt3[i]=dbBean.getElementValue(i,"vndnt3").trim();
						}

						PropertyUtils.setSimpleProperty(form, "vndnum",vndnum);
						PropertyUtils.setSimpleProperty(form, "vndnam",vndnam);

						PropertyUtils.setSimpleProperty(form, "vndnt1",vndnt1);
						PropertyUtils.setSimpleProperty(form, "vndnt2",vndnt2);
					
						PropertyUtils.setSimpleProperty(form, "vndnt3",vndnt3);
			
			

						/*
						 *添加代码
						 */
						PropertyUtils.setSimpleProperty(form, "count",String.valueOf(count));
						PropertyUtils.setSimpleProperty(form, "page","2");
						return "1";
				}

				/* (non-Javadoc)
				 * @see com.idn.secure.SecureAction#executeSecurely(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
				 */
				public ActionForward executeSecurely(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
					// 
					return  executeme( mapping,form,request,response);
				}

			}


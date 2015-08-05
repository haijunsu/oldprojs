/*
 * @(#)homeworldUserLifeAction.java  2004-3-4
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
		//import system.fun.FunPurview;

		/**
		 * <P>置装费表管理</P>
		 * 
		 * @version 0.1
		 * @author 李永初
		 */
		public class homeworldUserLifeAction extends SecureAction {
			ActionErrors errors = new ActionErrors();
	
			//FunPurview funPurview=new FunPurview();
			/**
			 * 构造函数
			 */
			public homeworldUserLifeAction() {
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
					uname,hs.getId(),"UserLift","B","LYC0000000",0);

			try{

				//		添加标题		
				PropertyUtils.setSimpleProperty(form, "title","人员生命周期");
				PropertyUtils.setSimpleProperty(form, "id","userid");
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

//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"UserLift","E","LYC8888881",l_end - l_begin);
					return mapping.findForward("success");
				}
		
				//在次返回
				if(strFlag.equals("selectexic")){
					PropertyUtils.setSimpleProperty(form, "lifedate","01");
					temp=setFormbeen(form,request,where);
					if (temp.equals("0")){saveErrors(request, errors);}
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"UserLift","E","LYC8888882",l_end - l_begin);
					return mapping.findForward("success");
				}

				if(strFlag.equals("hiddenexic")){
					PropertyUtils.setSimpleProperty(form, "lifedate","01");
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"UserLift","E","LYC8888883",l_end - l_begin);
					return mapping.findForward("success");
				}

				if(strFlag.equals("changeexic")){
					PropertyUtils.setSimpleProperty(form, "lifedate","01");
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"UserLift","E","LYC8888884",l_end - l_begin);
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
							currrowshow=((String) PropertyUtils.getSimpleProperty(form, "currrow"));
		
		
							//取ID
							String userid=((String) PropertyUtils.getIndexedProperty(form, "userid",Integer.parseInt(currrowshow)));
							
							//取生命周期
							String life =((String) PropertyUtils.getSimpleProperty(form,"lifedate")).trim();
							
							String hiddenbeg=((String) PropertyUtils.getIndexedProperty(form, "hiddenbeg",Integer.parseInt(currrowshow)));
							String hiddenend=((String) PropertyUtils.getIndexedProperty(form, "hiddenend",Integer.parseInt(currrowshow)));
						
							String lifebeg=((String) PropertyUtils.getIndexedProperty(form, "lifebeg",Integer.parseInt(currrowshow)));
							String lifeend=((String) PropertyUtils.getIndexedProperty(form, "lifeend",Integer.parseInt(currrowshow)));
				
							String year=lifebeg.substring(0,4);
							String month=lifebeg.substring(5,7);
							String date= lifebeg.substring(8,10);
							
							hiddenbeg=String.valueOf(com.idn.util.DateUtil.parseDateToLong(year,month,date));

							year=lifeend.substring(0,4);
							month=lifeend.substring(5,7);
							date= lifeend.substring(8,10);
				
							hiddenend=String.valueOf(com.idn.util.DateUtil.parseDateToLong(year,month,date));
							
//							if(lifebeg.equals("") || lifeend.compareTo(temp)<0 ){
//								
//								PropertyUtils.setIndexedProperty(form,"lifebeg",Integer.parseInt(currrowshow),temp);
//								
//								
//								Date dd=com.idn.util.DateUtil.addMonths(now,Integer.parseInt(life));
//								
//								PropertyUtils.setIndexedProperty(form,"lifeend",Integer.parseInt(currrowshow),com.idn.util.FormatDate.format(dd,"yyyy-MM-dd"));
//								
//								temp=String.valueOf(com.idn.util.DateUtil.parseDateToLong(com.idn.util.DateUtil.addMonths(now,Integer.parseInt(life))));
//								hiddenend=String.valueOf(temp);
//								
//								temp=String.valueOf(com.idn.util.DateUtil.parseDateToLong(now));
//								hiddenbeg=String.valueOf(temp);
//								
//
//								PropertyUtils.setIndexedProperty(form,"hiddenbeg",Integer.parseInt(currrowshow),hiddenbeg);
//								PropertyUtils.setIndexedProperty(form,"hiddenend",Integer.parseInt(currrowshow),hiddenend);
//							}
//							
//							if(lifeend.compareTo(temp)>=0 ){
//								//PropertyUtils.setIndexedProperty(form,"lifebeg",Integer.parseInt(currrowshow),lifeend);
//								//PropertyUtils.setIndexedProperty(form,"hiddenbeg",Integer.parseInt(currrowshow),hiddenend);
//								
//								Date dd=com.idn.util.DateUtil.getDate(Long.parseLong(hiddenend));
//								dd=com.idn.util.DateUtil.addMonths(dd,Integer.parseInt(life));
//								
//								lifeend=com.idn.util.FormatDate.format(dd,"yyyy-MM-dd");
//								hiddenend=String.valueOf(com.idn.util.DateUtil.parseDateToLong(dd));
//
//								PropertyUtils.setIndexedProperty(form,"lifeend",Integer.parseInt(currrowshow),lifeend);
//								PropertyUtils.setIndexedProperty(form,"hiddenend",Integer.parseInt(currrowshow),hiddenend);
//								
//								
//							}
								
							vecTemp.addElement("update users set lifebeg='"+hiddenbeg+"' , lifeend='"+hiddenend+"' where userid='"+userid+"'");

							strSql = (String[]) vecTemp.toArray(new String[0]);		
							//执行sql语句
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
//				if(strFlag.equals("select")){
//					temp=setFormbeen(form,request,where);
//					if (temp.equals("0")){saveErrors(request, errors);}
//					return mapping.findForward("success");
//				}

		
				//向formbean中赋值
				PropertyUtils.setSimpleProperty(form, "lifedate","01");
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"UserLift","E","LYC8888888",l_end - l_begin);
				return mapping.findForward("success");	
		
			} catch (Exception e){
				e.printStackTrace();
				errors.add("errormessage",new ActionError("Database.formbean"));
				saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"UserLift","C","LYC9999999",l_end - l_begin);
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
		
					strSql="select userid,appsys,purview,lifebeg,lifeend,useridg,vndnam,begroup  " +
						"from users,VNDINFO where userid=vndnum and ("+ where +") order by userid";

					dbBean.executeSelect(strSql);
	
					int count;
					count=dbBean.getRowCount();
				if (count<2){
					 count=2;
				}
		
				//String[] mpurview = new String[count];//权限id

				String[] userid= new String[count];//用户id
				String[] usershow= new String[count];//用户姓名
	
				String[] hiddenbeg = new String[count];
				String[] hiddenend = new String[count];
				String[] lifebeg = new String[count];
				String[] lifeend = new String[count];
	
				String[] useridgshow = new String[count];
				String[] useridgid = new String[count];
	
					for(int i=0;i<count;i++){
				
						userid[i]=dbBean.getElementValue(i,0).trim()  ;
						usershow[i]=dbBean.getElementValue(i,"vndnam").trim() ;
			
						lifebeg[i]=dbBean.getElementValue(i,3).trim();
						lifeend[i]=dbBean.getElementValue(i,4).trim();
						hiddenbeg[i]=lifebeg[i];
						hiddenend[i]=lifeend[i];
						if (!lifeend[i].equals("")){
							lifebeg[i]=com.idn.util.FormatDate.format(Long.parseLong(lifebeg[i]),"yyyy-MM-dd");	
							lifeend[i]=com.idn.util.FormatDate.format(Long.parseLong(lifeend[i]),"yyyy-MM-dd");
						}
						useridgid[i]=dbBean.getElementValue(i,5).trim();
						useridgshow[i]=useridgid[i].trim();			
					}

					PropertyUtils.setSimpleProperty(form, "userid",userid);
					PropertyUtils.setSimpleProperty(form, "usershow",usershow);

					PropertyUtils.setSimpleProperty(form, "lifebeg",lifebeg);
					PropertyUtils.setSimpleProperty(form, "lifeend",lifeend);
					
					PropertyUtils.setSimpleProperty(form, "hiddenbeg",hiddenbeg);
					PropertyUtils.setSimpleProperty(form, "hiddenend",hiddenend);

					PropertyUtils.setSimpleProperty(form, "useridgid",useridgid);
					PropertyUtils.setSimpleProperty(form, "useridgshow",useridgshow);
		
			

					PropertyUtils.setSimpleProperty(form, "currrow","-1"); 
					PropertyUtils.setSimpleProperty(form, "currrowshowold","-1");
					PropertyUtils.setSimpleProperty(form, "currrowshow","-1");


					
					strSql="select * from codes where cclass='23' order by ccode";
					try{
						dbBean.executeSelect(strSql);
					} catch (Exception e) {
						e.printStackTrace();
						errors.add("errormessage",new ActionError("Database.readdb"));
					}
					count =dbBean.getRowCount();
					
					String[] mpurviewshow = new String[count];//权限显示
					String[] mpurviewid = new String[count];
					
					for(int i=0;i<count;i++){
						mpurviewshow[i]=dbBean.getElementValue(i,"cshowc").trim() ;
						mpurviewid[i]=dbBean.getElementValue(i,"ccode").trim() ;
					}
					PropertyUtils.setSimpleProperty(form, "mpurviewshow",mpurviewshow);
					PropertyUtils.setSimpleProperty(form, "mpurviewid",mpurviewid);
					
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


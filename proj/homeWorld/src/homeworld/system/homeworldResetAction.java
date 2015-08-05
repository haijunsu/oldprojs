/*
 * @(#)homeworldResetAction.java  2004-4-19
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
//		  import java.util.Date;

//		  import java.util.Vector;

		import javax.servlet.ServletException;
		import javax.servlet.http.HttpServletRequest;
		import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//		  import javax.servlet.http.HttpSession;

		import org.apache.commons.beanutils.PropertyUtils;
//		  import org.apache.struts.action.Action;
		import org.apache.struts.action.ActionError;
		import org.apache.struts.action.ActionErrors;
		import org.apache.struts.action.ActionForm;
		import org.apache.struts.action.ActionForward;
		import org.apache.struts.action.ActionMapping;
//		import org.apache.struts.action.ActionMessages;

//		  import com.idn.log.LogWrapper;
		import com.idn.secure.SecureAction;
	import com.idn.sql.DataBean;
	import com.idn.sql.DynaSqlBean;
//		  import com.idn.sql.*;
//		  import com.idn.util.FormatDate;

		/**
		 * <P>置装费表管理</P>
		 * 
		 * @version 0.1
		 * @author 李永初
		 */
		public class homeworldResetAction extends SecureAction {

			ActionErrors errors = new ActionErrors();

			/**
			 * 构造函数
			 */
			public homeworldResetAction() {
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
					uname,hs.getId(),"Reset","B","LYC0000000",0);
				
			
				System.out.println("程序开始");
			try{
				PropertyUtils.setSimpleProperty(form, "message" ,"0");
				
				//添加标题	
				PropertyUtils.setSimpleProperty(form, "title","人员密码重置");
				PropertyUtils.setSimpleProperty(form, "id","userid");
				PropertyUtils.setSimpleProperty(form, "show","人员名称");
				//添加标题
		
				String temp="";
	
				//1 : 修改(默认)  2 ：查询
				String se_up=(String)request.getParameter("se_up");
				if(se_up==null){se_up=(String) PropertyUtils.getSimpleProperty(form, "se_up");}
				PropertyUtils.setSimpleProperty(form,"se_up",se_up);
		
				//取标志
				String flag=(String) PropertyUtils.getSimpleProperty(form, "flag");
				String page=(String) PropertyUtils.getSimpleProperty(form, "page");
				String where=(String) PropertyUtils.getSimpleProperty(form, "where");
		 
				//String liketext=(String) PropertyUtils.getSimpleProperty(form, "liketext");
			
				//首次直接返回
				if (flag.equals("")){
					System.out.println(page);
					System.out.println("首次直接返回");
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Reset","E","LYC8888881",l_end - l_begin);
					return mapping.findForward("success");
				}
		
				//在次返回
				if(flag.equals("selectexic")){
					temp=setFormbeen(form,request,where);
					if (temp.equals("0")){saveErrors(request, errors);}
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Reset","E","LYC8888882",l_end - l_begin);
					return mapping.findForward("success");
				}
		
				/*
				 *添加代码
				 */
				String currrowshow;
				String[] strSql=null;
				Vector vecTemp=new Vector();
				DynaSqlBean dybBean = new DynaSqlBean();
			
					String strFlag=((String) PropertyUtils.getSimpleProperty(form, "flag"));
			
					String key=((String) PropertyUtils.getProperty(form, "key"));
			
					vecTemp.addElement("update users set upwd=userid where userid='"+key+"'");
	
					strSql = (String[]) vecTemp.toArray(new String[0]);		
					//执行sql语句
					try{
						dybBean.setSql(strSql);
						dybBean.executeBatch();
						PropertyUtils.setSimpleProperty(form, "message" ,"1");
			
					} catch (Exception e){
						e.printStackTrace();
						errors.add("errormessage",new ActionError("Database.writedb"));
						saveErrors(request, errors);
					}
		 

				/*
				 *添加代码
				 */
		 
				System.out.println("存盘结束");
				//在次返回
				if(flag.equals("select")){
					temp=setFormbeen(form,request,where);
					if (temp.equals("0")){saveErrors(request, errors);}
//					TODO 临时用来监测用
					l_end = System.currentTimeMillis();
					commsearch.util.CommActionLog.setTempLog(
						Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
						uname,hs.getId(),"Reset","E","LYC8888883",l_end - l_begin);
					return mapping.findForward("success");
				}
				//向formbean中赋值
				System.out.println("程序结束");
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Reset","E","LYC8888884",l_end - l_begin);
				return mapping.findForward("success");	
		
			} catch (Exception e){
				e.printStackTrace();
				errors.add("errormessage",new ActionError("Database.formbean"));
				saveErrors(request, errors);
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					uname,hs.getId(),"Reset","C","LYC9999999",l_end - l_begin);
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
					String[] seq=null; 
		
					/*
					 *添加代码
					 */

					String strSql,key;
					DataBean dbBean = new DataBean();
					strSql="select userid,lifebeg,lifeend,useridg,vndnam " +
						"from users,vndinfo where "+where+" and userid=vndnum order by userid";

					dbBean.executeSelect(strSql);
	
					int count;
					count=dbBean.getRowCount();
				if (count<2){
					 count=2;
				}

				String[] userid= new String[count];//用户id
				String[] usershow= new String[count];//用户姓名
	
				String[] lifebeg = new String[count];
				String[] lifeend = new String[count];
	
				String[] useridgshow = new String[count];
				String[] useridgid = new String[count];
				seq = new String[count];
	
					for(int i=0;i<count;i++){
						seq[i]=String.valueOf(i);
						userid[i]=dbBean.getElementValue(i,0).trim() ;
						usershow[i]=dbBean.getElementValue(i,"vndnam").trim() ;

						lifebeg[i]=dbBean.getElementValue(i,1).trim() ;
						lifeend[i]=dbBean.getElementValue(i,2).trim() ;

						if (!lifeend[i].equals("")){
							lifebeg[i]=com.idn.util.FormatDate.format(Long.parseLong(lifebeg[i]),"yyyy-MM-dd");	
							lifeend[i]=com.idn.util.FormatDate.format(Long.parseLong(lifeend[i]),"yyyy-MM-dd");
						}
			
						useridgid[i]=dbBean.getElementValue(i,3).trim() ;
						useridgshow[i]=dbBean.getElementValue(i,4).trim();			
					}

					PropertyUtils.setSimpleProperty(form, "seq",seq);
					PropertyUtils.setSimpleProperty(form, "userid",userid);
					PropertyUtils.setSimpleProperty(form, "usershow",usershow);
		
					PropertyUtils.setSimpleProperty(form, "lifebeg",lifebeg);
					PropertyUtils.setSimpleProperty(form, "lifeend",lifeend);

					PropertyUtils.setSimpleProperty(form, "useridgid",useridgid);
					PropertyUtils.setSimpleProperty(form, "useridgshow",useridgshow);
		
		 

					/*
					 *添加代码
					 */
		 			
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

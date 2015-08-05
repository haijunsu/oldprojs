/*
 * @(#)SalaryInputAllCtrl.java  2003-05-08
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package system.action;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;

//import com.idn.util.*;
//import com.idn.property.*;

//import salaryin.SalaryInputTools;
//import com.idn.property.PropertyManager;
/**
 * <P>工资录入查询管理</P>
 * 
 * @version 1.0
 * @author XIAOAI
 */
public class ChangePasswordSave extends Action {

	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(ChangePasswordCtrl.class);
	private Locale locale = null;   //取配置文件用
	private MessageResources messages = null; //取配置文件用
	private String username = null;  //登录用户名
	private ErrorValue siev =null;  //错误Bean
//	private SalaryInputTools sit= null;     //工具
	private commsearch.CommSQL sis = null;      //SQL语句
	/**
	 * Constructor for QueryAction.
	 */		
	public ChangePasswordSave() {
		super();
	}
	/**
	 * 用户口令更改EXECUTE程序
	 * 
	 * @param ActionMapping
	 * @param ActionForm
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ActionForward
	 * @exception IOException, ServletException 
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException 
		{
		int i_fori,i_forj,i_return;
		int i_count,i_rowcount,i_colcount;
		String passwordold;        //新的口令
		String passwordnew;        //新的口令
		String passwordagain;      //再一次新的口令

		siev = new ErrorValue(); //Error ValueBean
//		sit = new SalaryInputTools();    //工具Bean
		ActionForward forward = new ActionForward();

		log.info("用户口令更改保存:开始执行用户口令更改保存控制程序Execute");
		HttpSession session = request.getSession();
		username = (String)session.getAttribute("userid");
		username = username.trim();
		log.info("登录用户："+username);

		//TODO 临时用来监测用
		long l_begin,l_end;
		l_begin = System.currentTimeMillis();		
		commsearch.util.CommDate cdtemp = new commsearch.util.CommDate();
		commsearch.util.CommActionLog.setTempLog(
			Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
			username,session.getId(),"ChangePassworldSave","B","cpw0001",0);



		if (username==null){
			//session失效了
			siev.setError("SalaryInputSearch","execute","时间太长,session已经失效了;请重新登录!");					
			request.setAttribute("siev",siev);
			forward = mapping.findForward("failure");
			log.info("用户口令更改保存:用户口令更改保存控制程序Execute导向失败画面");

//			TODO 临时用来监测用
			commsearch.util.CommDate cd = new commsearch.util.CommDate();
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"ChangePassworldSave","E","cpw0997",l_end - l_begin);

			return forward;
		}
		locale = getLocale(request);
		messages = getResources(request);
		sis = new commsearch.CommSQL();
		try {
			passwordold = request.getParameter("passwordold");
			passwordnew = request.getParameter("passwordnew");
			passwordagain = request.getParameter("passwordagain");
			passwordold=passwordold.toUpperCase().trim();
			sis = new commsearch.CommSQL();
			String[][] passwordoldcount=null;
			passwordoldcount = sis.executeSelect("select * from users where userid='"+username.trim()+"' AND upwd='" + passwordold +"'");
			if (passwordoldcount == null){
			//失败转向页面
				  siev.setError("SalaryChangePasswordSave","execute","旧的口令输入错误");
				  siev.setFlag("0");					
				  request.setAttribute("siev",siev);
				  forward = mapping.findForward("failure");
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					username,session.getId(),"ChangePassworldSave","B","cpw0903",l_end - l_begin);
				  
				  return forward;
			}

			if (!passwordnew.equals(passwordagain)){
				//失败转向页面
				siev.setError("SalaryChangePasswordSave","execute","两次输入的口令不一致");
				siev.setFlag("0");					
				request.setAttribute("siev",siev);
				forward = mapping.findForward("failure");
				
//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					username,session.getId(),"ChangePassworldSave","B","cpw0904",l_end - l_begin);
				
				return forward;				
			}
			if (passwordnew.equals("")){
				//失败转向页面
				siev.setError("SalaryChangePasswordSave","execute","口令不能为空");
				siev.setFlag("0");					
				request.setAttribute("siev",siev);
				forward = mapping.findForward("failure");

//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					username,session.getId(),"ChangePassworldSave","B","cpw0905",l_end - l_begin);

				return forward;				
			}
			
			
			
			
			
			passwordnew = passwordnew.toUpperCase();
			if (sis.executeBatchSQL("UPDATE USERS SET UPWD='"+passwordnew+"' WHERE USERID='"+username+"'")==-1){
				//失败转向页面
				siev.setError("SalaryChangePasswordSave","execute","用户口令更改数据库发生错误");
				siev.setFlag("0");					
				request.setAttribute("siev",siev);
				forward = mapping.findForward("failure");

//				TODO 临时用来监测用
				l_end = System.currentTimeMillis();
				commsearch.util.CommActionLog.setTempLog(
					Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
					username,session.getId(),"ChangePassworldSave","C","cpw0906",l_end - l_begin);
				
				return forward;				
			}
			//成功转向页面
			siev.setError("","","用户口令修改成功");
			siev.setFlag("1");					
			request.setAttribute("siev",siev);
			forward = mapping.findForward("success");
			
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"ChangePassworldSave","E","cpw0999",l_end - l_begin);
			
			
			log.info("用户口令更改保存:用户口令更改保存控制程序Execute导向成功画面");
		} catch (Exception e) {
			log.error("用户口令更改保存:未知异常错误");
			e.printStackTrace();
			siev.setError("SalaryChangePasswordSave","execute","异常未知错误");					
			request.setAttribute("siev",siev);
			forward = mapping.findForward("failure");

//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"ChangePassworldSave","E","cpw0900",l_end - l_begin);
			
			
			log.info("用户口令更改保存:用户口令更改保存控制程序Execute导向失败画面");
		}
		return forward;
	}	
}
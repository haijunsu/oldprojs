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
import org.apache.commons.beanutils.PropertyUtils;

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
public class ChangePasswordCtrl extends Action {

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
	public ChangePasswordCtrl() {
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
		siev = new ErrorValue(); //Error ValueBean
//		sit = new SalaryInputTools();    //工具Bean
		ActionForward forward = new ActionForward();

		log.info("用户口令更改:开始执行用户口令更改控制程序Execute");
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
			username,session.getId(),"ChangePassworldCtrl","B","cpw0001",0);


		if (username==null){
			//session失效了
			siev.setError("ChangePasswordCtrl","execute","时间太长,session已经失效了;请重新登录!");					
			request.setAttribute("siev",siev);
			forward = mapping.findForward("failure");
			log.info("用户口令更改:用户口令更改控制程序Execute导向失败画面");

//			TODO 临时用来监测用
			commsearch.util.CommDate cd = new commsearch.util.CommDate();
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"ChangePassworldCtrl","E","cpw0997",l_end - l_begin);
			return forward;
		}



		locale = getLocale(request);
		messages = getResources(request);
		sis = new commsearch.CommSQL();
		try {
			String[][] s_temp;
			String s_namec = "";
			String a;
			a="select VNDNAM from VNDINFO WHERE VNDNUM='"+username.toUpperCase()+"'";
			s_temp= sis.executeSelect(a);
			if (!(s_temp==null)){
				s_namec = s_temp[0][0];
			}
			PropertyUtils.setSimpleProperty(form,"usernameid",username);
			PropertyUtils.setSimpleProperty(form,"usernamec",s_namec);
			//成功转向页面
			forward = mapping.findForward("success");
//			siev.setError("SalaryChangePasswrodCtrl","execute","测试用");					
//			request.setAttribute("siev",siev);
//			forward = mapping.findForward("failure");
//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"ChangePassworldCtrl","E","cpw0999",l_end - l_begin);

			log.info("用户口令更改:用户口令更改控制程序Execute导向成功画面");
		} catch (Exception e) {
			log.error("用户口令更改:未知异常错误");
			e.printStackTrace();
			siev.setError("SalaryChangePasswordCtrl","execute","异常未知错误");					
			request.setAttribute("siev",siev);
			forward = mapping.findForward("failure");

//			TODO 临时用来监测用
			l_end = System.currentTimeMillis();
			commsearch.util.CommActionLog.setTempLog(
				Long.parseLong(cdtemp.getNow(cdtemp.FORMAT_ALL_NOSIGN)),
				username,session.getId(),"ChangePassworldCtrl","E","cpw0900",l_end - l_begin);


			log.info("用户口令更改:用户口令更改控制程序Execute导向失败画面");
		}
		return forward;
	}	
}
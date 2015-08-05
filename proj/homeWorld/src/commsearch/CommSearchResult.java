/*
 * @(#)CommSearch.java  2003-07-10
 * 
 * Copyright (c) Beijing IDN Systems Integration Co., Ltd. 2003 
 * All rights reserved. 
 */
package commsearch;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.util.MessageResources;
//import org.apache.commons.beanutils.PropertyUtils;

//import com.idn.util.*;
//import com.idn.property.*;
//import com.idn.property.PropertyManager;
//import com.idn.sql.*;

/**
 * <P>公用查询管理</P>
 * 
 * @version 1.0
 * @author XIAOAI
 
 */
public class CommSearchResult extends Action {

	//利用封装Bean-com.idn.log.LogWrapper来进行日志记录
	private static com.idn.log.LogWrapper log =
		new com.idn.log.LogWrapper(CommSearchResult.class);
	
	/**
	 * Constructor for QueryAction.
	 */		
	public CommSearchResult() {
		super();
	}
	/**
	 * 公用查询EXECUTE程序
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
		Locale locale = null;             //取配置文件用
		MessageResources messages = null; //取配置文件用
		String username = null;            //登录用户名
		CommTools ct = new CommTools();    //公用查询工具类
		CommQuery cq = null;               //公用查询数据类

		int i_fori,i_forj,i_return;
		String s_statu = "",s_flag,s_page,s_queryid,s_actiondo;

		ActionForward forward = new ActionForward();

		log.info("公用查询:开始执行公用查询控制程序Execute");
		HttpSession session = request.getSession();
		username = (String)session.getAttribute("userid");

		locale = getLocale(request);
		messages = getResources(request);
		try {
			//取传入参数
			//1:查询画面;2:查询与列表页面
			s_page = ct.getParameter(request,"page");
			if (s_page ==null ) s_page = "1";
			if (s_page.equals("")) s_page = "1";
			s_flag = ct.getParameter(request,"flag");
			s_actiondo = ct.getParameter(request,"actiondo");
			if (s_flag.equals("search")) {
				s_page = "2";
			}
			//三张表的queryid			
			s_queryid = ct.getParameter(request,"queryid");

			//成功转向页面
			forward = mapping.findForward("success");
			log.info("公用查询:公用查询控制程序Execute导向成功画面");
		} catch (Exception e) {
			//异常错误
			log.error("公用查询:未知异常错误");
			e.printStackTrace();
			system.action.ErrorValue ev = new system.action.ErrorValue(); 
			ev.setError("CommSearchResult","execute","公用查询出现异常错误:" + e.getMessage());
			request.setAttribute("siev",ev);
			forward = mapping.findForward("failure");
			log.info("公用查询:公用查询控制程序Execute导向失败画面");
			return forward;
		}
		return forward;
	}

}
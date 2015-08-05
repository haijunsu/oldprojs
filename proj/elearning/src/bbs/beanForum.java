//////////////////////////////////////////////
//
// 论坛
//
//      2002.9
//
//////////////////////////////////////////////

package bbs;

import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.htyz.*;
import com.htyz.util.BeanDateFormat;

public class beanForum extends HttpServlet
{
	protected String Str_Sql = "";
	protected String Select_Sql = "SELECT * FROM t_bbs_info ";
	protected String Order_Sql = " ORDER BY last_time DESC";
	protected String Conditions_Sql = "";
	protected beanElearnTools ets = new beanElearnTools();
	
	//初始化Servlet
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
	}
	
	//执行Post方法
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
		doProcess(req, res);
	}

	//执行Get方法
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
		doProcess(req, res);
	}
	
	//获取参数值函数
	//参数说明:
	//HttpServletRequest req: ServletRequest对象
	//String parameterName: 参数名称
	//boolean checkRequestParameters: 是否从ServletRequest获取
	//boolean checkInitParameters：是否从Servlet初始化变量中获取
	//boolean isParameterRequired：该变量是不是必须项，如果是必须项且没有找到，则抛出异常
	//String defaultValue；没有找到变量时，赋默认值
	public java.lang.String getParameter(javax.servlet.http.HttpServletRequest request, java.lang.String parameterName, boolean checkRequestParameters, boolean checkInitParameters, boolean isParameterRequired, java.lang.String defaultValue) throws  java.lang.Exception
	{
		java.lang.String[] parameterValues = null;
		java.lang.String paramValue = null;
		java.lang.String paramValue_GBK = null;

		//从request中获取参数
		if (checkRequestParameters)
		{
			parameterValues = request.getParameterValues(parameterName);

			paramValue = request.getParameter(parameterName);
			if (parameterValues != null)
				paramValue = parameterValues[0];
		}

		//如果request中参数为null,从servlet初始化参数中获取
		if ( (checkInitParameters) && (paramValue == null) )
			paramValue = getServletConfig().getInitParameter(parameterName);

		//如果参数为必须，且没有获取，则抛出异常
		if ( (isParameterRequired) && (paramValue == null) )
			throw new Exception(parameterName + " 参数为必须，但没有找到！请检查！");

		//如果参数没有找到，则返回默认值
		if (paramValue == null)
			paramValue = defaultValue;
			
		//转换为GBK
		paramValue_GBK = ets.getGBKString(paramValue);
		if (paramValue_GBK == null)
		{
			throw new Exception(parameterName + " 不能转换为GBK，请检查！");
		}
		paramValue = paramValue_GBK;


		return paramValue;
	}
	
	//Servlet的执行内容	
	public void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
		//定义变量
		String s_action, s_CatId, s_BiId, s_Title, s_MsgContent, s_UserIcon;
		String s_msg, s_UserId, s_PassWord, s_FileName;
		String s_pageNo;
		int i_rtn;
		Conditions_Sql = "";
		s_msg = "";
		beanGetdata bgd = new beanGetdata();
		beanGetdata egd = new beanGetdata();
		BeanDateFormat dsf=new BeanDateFormat();
		req.setAttribute("ForumData", bgd);
		try
		{
			
			
			//执行主体内容
			boolean create = false; //决定是否创建session，只有在Login中才为true
			HttpSession session = req.getSession(create);

			//判断session是否存在
			if(session == null)
			{
				req.setAttribute("message", "您没有登录！");
				s_FileName = getParameter(req, "loginPage", false, true, true, "");
				getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);
				return;
			}
			
			
			s_UserId = (String)session.getAttribute("userid");
			
			//判断userid是否存在
			if(s_UserId == null)
			{
				req.setAttribute("message", "您没有登录！");
				s_FileName = getParameter(req, "loginPage", false, true, true, "");
				getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);
			}

			//取得参数
			s_pageNo = getParameter(req, "pageno", true, false, false, "1");
			s_UserId = session.getAttribute("userid").toString().trim();
			s_PassWord=getParameter(req, "Password", true, false, false, "");
			s_FileName = getParameter(req, "listPage", false, true, true, "");
			s_action = getParameter(req, "action", true, true, false, "list");
			s_CatId=getParameter(req, "catid", true, false, true, "");

			Conditions_Sql=" where Cat_id="+s_CatId;
			
			//添加if action==list 直接执行
			if(s_action.equalsIgnoreCase("newpost")||s_action.equalsIgnoreCase("Reply"))
			
			{
				
				s_FileName = getParameter(req, "postPage", false, true, true, "");
				if(s_action.equalsIgnoreCase("Reply")) {
					s_BiId=getParameter(req, "biid", true, false, true, "");
					Conditions_Sql=" where cat_id="+s_CatId+" and Bi_id="+s_BiId;
					req.setAttribute("biid", s_BiId);
				}
			
			}
			if(s_action.equalsIgnoreCase("show"))
			{
				s_FileName = getParameter(req, "readPage", false, true, true, "");
				s_BiId=getParameter(req, "biid", true, false, true, "");
				egd.execute("Update t_bbs_info set Bi_hits=Bi_hits+1 where Cat_id="+s_CatId+" and Bi_id="+s_BiId);
				req.setAttribute("Sql_topic",egd);
				egd.executeSelect("select * from t_bbs_reply where Bi_id="+s_BiId);
				Conditions_Sql=" where cat_id="+s_CatId+" and Bi_id="+s_BiId;
			
			}
			
			if(s_action.equalsIgnoreCase("save"))
			{ 
			    
				s_Title = getParameter(req, "Bi_title", true, false, false, "");
				s_MsgContent=getParameter(req, "Message", true, false, false, "");
				s_UserIcon=getParameter(req, "usericon", true, false, false, "");

				egd.executeSelect("select max(Bi_id) as maxno from t_bbs_info");
				String s_temp;
				if(egd.getRowCount()==0)
					s_temp="000000000000000000";
				else
					s_temp=egd.getFieldValue("maxno",0);
				s_BiId=ets.AutoNum4(s_temp);
				egd.execute("insert into t_bbs_info (Bi_id,Cat_id,user_id,Bi_title,Bi_content,Bi_date,Bi_emotion,Bi_hits,Bi_reply,Last_Poster,Last_time,status) values("+s_BiId+","+s_CatId +",'"+s_UserId+"','"+s_Title+"','"+s_MsgContent+"','"+egd.getDbDate()+"','"+s_UserIcon+"',0,0,'"+s_UserId+"','"+egd.getDbDate()+"','1')");	
			}
			if(s_action.equalsIgnoreCase("ReplySave"))
			{ 
			    
				s_BiId=getParameter(req, "biid", true, false, true, "");
				s_MsgContent=getParameter(req, "Message", true, false, false, "");
				s_UserIcon=getParameter(req, "usericon", true, false, false, "");

				egd.executeSelect("select max(Br_id) as maxno from t_bbs_reply");
				String s_temp;
				if(egd.getRowCount()==0)
					s_temp="000000000000000000";
				else
					s_temp=egd.getFieldValue("maxno",0);
				String s_BrId=ets.AutoNum4(s_temp);
				egd.execute("Insert Into t_bbs_reply (Br_id,Bi_id,user_id,Br_emotion,Br_content,Br_date) Values("+s_BrId+","+s_BiId +",'"+s_UserId+"','"+s_UserIcon+"','"+s_MsgContent+"','"+egd.getDbDate()+"')");	
				egd.execute("Update t_bbs_info set Bi_reply=Bi_reply+1,Last_time='"+egd.getDbDate()+"',Last_Poster='"+s_UserId+"' where Cat_id="+s_CatId+" and Bi_id="+s_BiId);
			}
			if(s_action.equalsIgnoreCase("quickReply"))
			{ 
			    
				s_BiId=getParameter(req, "biid", true, false, true, "");
				s_MsgContent=getParameter(req, "Message", true, false, false, "");
				egd.executeSelect("select max(Br_id) as maxno from t_bbs_reply");
				String s_temp;
				if(egd.getRowCount()==0)
					s_temp="000000000000000000";
				else
					s_temp=egd.getFieldValue("maxno",0);
				String s_BrId=ets.AutoNum4(s_temp);
				egd.execute("Insert Into t_bbs_reply (Br_id,Bi_id,user_id,Br_content,Br_date) Values("+s_BrId+","+s_BiId +",'"+s_UserId+"','"+s_MsgContent+"','"+egd.getDbDate()+"')");	
				egd.execute("Update t_bbs_info set Bi_reply=Bi_reply+1,Last_time='"+egd.getDbDate()+"',Last_Poster='"+s_UserId+"' where Cat_id="+s_CatId+" and Bi_id="+s_BiId);
			}
			
			
			if(s_action.equalsIgnoreCase("mod"))
			{
				s_FileName = getParameter(req, "readPage", false, true, true, "");
				s_BiId=getParameter(req, "biid", true, false, true, "");
				Conditions_Sql=" where cat_id="+s_CatId+" and Bi_id="+s_BiId;
			}
			if(s_action.equalsIgnoreCase("update"))
			{ 
			    s_BiId=getParameter(req, "biid", true, false, true, "");
				s_Title = getParameter(req, "Bi_title", true, false, false, "");
				s_MsgContent=getParameter(req, "Message", true, false, false, "");
				s_UserIcon=getParameter(req, "usericon", true, false, false, "");
				egd.execute("update t_bbs_info set Bi_title='"+s_Title +"',Bi_content='"+s_MsgContent+"' where Bi_id='"+s_BiId+"' and Cat_id='"+s_CatId+"'");	
			}
			
				if(s_action.equalsIgnoreCase("del"))
			{
				s_BiId=getParameter(req, "biid", true, false, true, "");
				egd.execute("delete from t_bbs_info where B_id="+s_BiId+" and Cat_id="+s_CatId);
				
			}
			req.setAttribute("action", s_action);
			req.setAttribute("catid", s_CatId);
		    req.setAttribute("message", s_msg);
			Str_Sql = Select_Sql + Conditions_Sql + Order_Sql;
			bgd.executeSelect(Str_Sql);
			getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);
			return;
		}
		catch(Exception e)
		{
			try
			{
				System.err.println("error: " + e);
				req.setAttribute("errmsg", e.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher("/error.jsp").forward(req, res);
				return;
			}
			catch(Exception ex)
			{
				//System.err.println("BeanMessager调用错误页面时出错！");
			}
			
		}
	}
	
	//设置Str_Sql
	public void setStr_Sql(String strSql)
	{
		Str_Sql = strSql;
	}
	
	//获取Str_Sql
	public String getStr_Sql()
	{
		return Str_Sql;
	}
	
	//设置Order_Sql
	public void setOrder_Sql(String OrderSql)
	{
		Order_Sql = OrderSql;
	}
	
	//获取Order_Sql
	public String getOrder_Sql()
	{
		return Order_Sql;
	}

	//设置Conditions_Sql
	public void setConditions_Sql(String ConditionsSql)
	{
		Conditions_Sql = ConditionsSql;
	}
	
	//获取Conditions_Sql
	public String getConditions_Sql()
	{
		return Conditions_Sql;
	}

	//设置Select_Sql
	public void setSelect_Sql(String SelectSql)
	{
		Select_Sql = SelectSql;
	}
	
	//获取Select_Sql
	public String getSelect_Sql()
	{
		return Select_Sql;
	}

}


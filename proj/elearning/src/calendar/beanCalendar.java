//////////////////////////////////////////////
//
// 日记日历
//
//      2002.8
//
//////////////////////////////////////////////

package calendar;

import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.htyz.*;
import com.htyz.util.BeanDateFormat;

public class beanCalendar extends HttpServlet
{
	protected String Str_Sql = "";
	protected String Select_Sql = "SELECT * FROM t_user_daily ";
	protected String Order_Sql = " ORDER BY user_day";
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
		String s_action, s_NewId, s_MsgTitle, s_MsgContent, s_SenderId, s_ReceiptId, s_MsgStatus;
		String s_msg, s_Year,s_Month,s_Date,s_UserId, s_FileName;
		String s_pageNo,s_DailyId,s_Time;
		int i_rtn;
		Conditions_Sql = "";
		s_msg = "";
		beanGetdata bgd = new beanGetdata();
		beanGetdata egd = new beanGetdata();
		BeanDateFormat dsf=new BeanDateFormat();
		req.setAttribute("beanGetdata", bgd);
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
			s_FileName = getParameter(req, "listPage", false, true, true, "");
			s_action = getParameter(req, "action", true, true, false, "list");
			s_Year = getParameter(req, "year", true,true, false, dsf.getYear(bgd.getDbDate()));
			s_Month= getParameter(req, "month", true, true, false, dsf.getMonth(bgd.getDbDate()));
			s_Date = getParameter(req, "date", true, true, false, dsf.getDay(bgd.getDbDate()));
			s_Time=dsf.parseDBDate(s_Year,s_Month,s_Date);
			Conditions_Sql=" where user_day='"+s_Time+"' and user_id='"+s_UserId+"'";
			
			//添加
			if(s_action.equalsIgnoreCase("add"))
			
			{
				
			s_FileName = getParameter(req, "readPage", false, true, true, "");
			
			}
			if(s_action.equalsIgnoreCase("show"))
			{
				s_FileName = getParameter(req, "readPage", false, true, true, "");
				s_DailyId=getParameter(req, "daily_id", true, false, true, "");
				Conditions_Sql=" where daily_id='"+s_DailyId+"'";
			
			}
			
			if(s_action.equalsIgnoreCase("save"))
			{ 
			    
				String s_Title = getParameter(req, "title", true, false, false, "");
				String s_Content=getParameter(req, "content", true, false, false, "");
				String s_Time1=getParameter(req,"date1", true, false, false, "");
				String s_Time2=getParameter(req,"date2", true, false, false, "");

				s_Time=dsf.parseDBDate(s_Year,s_Month,s_Date);
				s_Time1=s_Time1.equals("")?"":dsf.parseDBDate(s_Time1.substring(0,4),s_Time1.substring(5,7),s_Time1.substring(8,10));
				s_Time2=s_Time2.equals("")?"":dsf.parseDBDate(s_Time2.substring(0,4),s_Time2.substring(5,7),s_Time2.substring(8,10));
				egd.executeSelect("select max(Daily_id) as maxno from t_user_daily");
				String s_temp;
				if(egd.getRowCount()==0)
					s_temp="00000000000000";
				else
					s_temp=egd.getFieldValue("maxno",0);
				s_DailyId=ets.AutoNum(s_temp);
				egd.execute("insert into t_user_daily(daily_id,user_id,user_day,wake_date,end_date,title,notes)values('"+s_DailyId+"','"+s_UserId +"','"+s_Time+"','"+s_Time1+"','"+s_Time2+"','"+s_Title+"','"+s_Content+"')");	
			}
			
			
			if(s_action.equalsIgnoreCase("mod"))
			{
				s_FileName = getParameter(req, "readPage", false, true, true, "");
				s_DailyId=getParameter(req, "daily_id", true, false, true, "");
				Conditions_Sql=" where daily_id='"+s_DailyId+"'";
			}
			if(s_action.equalsIgnoreCase("update"))
			{ 
			    s_DailyId=getParameter(req, "daily_id", true, false, true, "");
				String s_Title = getParameter(req, "title", true, false, false, "");
				String s_Content=getParameter(req, "content", true, false, false, "");
				String s_Time1=getParameter(req,"date1", true, false, false, "");
				String s_Time2=getParameter(req,"date2", true, false, false, "");
				s_Time=dsf.parseDBDate(s_Year,s_Month,s_Date);
				s_Time1=s_Time1.equals("")?"":dsf.parseDBDate(s_Time1.substring(0,4),s_Time1.substring(5,7),s_Time1.substring(8,10));
				s_Time2=s_Time2.equals("")?"":dsf.parseDBDate(s_Time2.substring(0,4),s_Time2.substring(5,7),s_Time2.substring(8,10));
				egd.execute("update t_user_daily set daily_id='"+s_DailyId+"',user_id='"+s_UserId +"',user_day='"+s_Time+"',wake_date='"+s_Time1+"',end_date='"+s_Time2+"',title='"+s_Title+"',notes='"+s_Content+"' where daily_id='"+s_DailyId+"'");	
			}
			
				if(s_action.equalsIgnoreCase("del"))
			{
				s_DailyId=getParameter(req, "daily_id", true, false, true, "");
				egd.execute("delete from t_user_daily where daily_id='"+s_DailyId+"'");
				
			}
		    req.setAttribute("year", s_Year);
		    req.setAttribute("month", s_Month);
		 	req.setAttribute("date", s_Date);	
			req.setAttribute("action", s_action);
		    req.setAttribute("message", s_msg);
			Str_Sql = Select_Sql + Conditions_Sql + Order_Sql;
//		System.out.println("SQL="+Str_Sql);
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


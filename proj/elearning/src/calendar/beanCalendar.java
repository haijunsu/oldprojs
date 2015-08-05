//////////////////////////////////////////////
//
// �ռ�����
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
	
	//��ʼ��Servlet
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
	}
	
	//ִ��Post����
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
		doProcess(req, res);
	}

	//ִ��Get����
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
		doProcess(req, res);
	}
	
	//��ȡ����ֵ����
	//����˵��:
	//HttpServletRequest req: ServletRequest����
	//String parameterName: ��������
	//boolean checkRequestParameters: �Ƿ��ServletRequest��ȡ
	//boolean checkInitParameters���Ƿ��Servlet��ʼ�������л�ȡ
	//boolean isParameterRequired���ñ����ǲ��Ǳ��������Ǳ�������û���ҵ������׳��쳣
	//String defaultValue��û���ҵ�����ʱ����Ĭ��ֵ
	public java.lang.String getParameter(javax.servlet.http.HttpServletRequest request, java.lang.String parameterName, boolean checkRequestParameters, boolean checkInitParameters, boolean isParameterRequired, java.lang.String defaultValue) throws  java.lang.Exception
	{
		java.lang.String[] parameterValues = null;
		java.lang.String paramValue = null;
		java.lang.String paramValue_GBK = null;

		//��request�л�ȡ����
		if (checkRequestParameters)
		{
			parameterValues = request.getParameterValues(parameterName);

			paramValue = request.getParameter(parameterName);
			if (parameterValues != null)
				paramValue = parameterValues[0];
		}

		//���request�в���Ϊnull,��servlet��ʼ�������л�ȡ
		if ( (checkInitParameters) && (paramValue == null) )
			paramValue = getServletConfig().getInitParameter(parameterName);

		//�������Ϊ���룬��û�л�ȡ�����׳��쳣
		if ( (isParameterRequired) && (paramValue == null) )
			throw new Exception(parameterName + " ����Ϊ���룬��û���ҵ������飡");

		//�������û���ҵ����򷵻�Ĭ��ֵ
		if (paramValue == null)
			paramValue = defaultValue;
			
		//ת��ΪGBK
		paramValue_GBK = ets.getGBKString(paramValue);
		if (paramValue_GBK == null)
		{
			throw new Exception(parameterName + " ����ת��ΪGBK�����飡");
		}
		paramValue = paramValue_GBK;


		return paramValue;
	}
	
	//Servlet��ִ������	
	public void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
		//�������
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
			
			
			//ִ����������
			boolean create = false; //�����Ƿ񴴽�session��ֻ����Login�в�Ϊtrue
			HttpSession session = req.getSession(create);

			//�ж�session�Ƿ����
			if(session == null)
			{
				req.setAttribute("message", "��û�е�¼��");
				s_FileName = getParameter(req, "loginPage", false, true, true, "");
				getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);
				return;
			}
			
			
			s_UserId = (String)session.getAttribute("userid");
			
			//�ж�userid�Ƿ����
			if(s_UserId == null)
			{
				req.setAttribute("message", "��û�е�¼��");
				s_FileName = getParameter(req, "loginPage", false, true, true, "");
				getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);
			}

			//ȡ�ò���
			s_pageNo = getParameter(req, "pageno", true, false, false, "1");
			s_UserId = session.getAttribute("userid").toString().trim();
			s_FileName = getParameter(req, "listPage", false, true, true, "");
			s_action = getParameter(req, "action", true, true, false, "list");
			s_Year = getParameter(req, "year", true,true, false, dsf.getYear(bgd.getDbDate()));
			s_Month= getParameter(req, "month", true, true, false, dsf.getMonth(bgd.getDbDate()));
			s_Date = getParameter(req, "date", true, true, false, dsf.getDay(bgd.getDbDate()));
			s_Time=dsf.parseDBDate(s_Year,s_Month,s_Date);
			Conditions_Sql=" where user_day='"+s_Time+"' and user_id='"+s_UserId+"'";
			
			//���
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
				//System.err.println("BeanMessager���ô���ҳ��ʱ����");
			}
			
		}
	}
	
	//����Str_Sql
	public void setStr_Sql(String strSql)
	{
		Str_Sql = strSql;
	}
	
	//��ȡStr_Sql
	public String getStr_Sql()
	{
		return Str_Sql;
	}
	
	//����Order_Sql
	public void setOrder_Sql(String OrderSql)
	{
		Order_Sql = OrderSql;
	}
	
	//��ȡOrder_Sql
	public String getOrder_Sql()
	{
		return Order_Sql;
	}

	//����Conditions_Sql
	public void setConditions_Sql(String ConditionsSql)
	{
		Conditions_Sql = ConditionsSql;
	}
	
	//��ȡConditions_Sql
	public String getConditions_Sql()
	{
		return Conditions_Sql;
	}

	//����Select_Sql
	public void setSelect_Sql(String SelectSql)
	{
		Select_Sql = SelectSql;
	}
	
	//��ȡSelect_Sql
	public String getSelect_Sql()
	{
		return Select_Sql;
	}

}


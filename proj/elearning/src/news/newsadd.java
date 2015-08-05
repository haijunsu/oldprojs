package news;

import javax.servlet.*;
import javax.servlet.http.*;
import com.htyz.*;
public class newsadd extends HttpServlet
{
	
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
		String s_Action, s_Msg, s_Title,s_FileName;
		String s_UserId, newstype, newstitle, newskey, newscontent;
		String s_Sql,s_Newsid,newsman,ss_sql,newsimg,newshead;
		int i_rtn;
	
	    beanGetdata bgd = new beanGetdata();
	    
		req.setAttribute("beanGetdata", bgd);
		//bean_QueryCodes bqc = new bean_QueryCodes();
		//req.setAttribute("beanQueryCodes", bqc);
		//beanUserInfo userinfo = new beanUserInfo();
	//	req.setAttribute("beanUserInfo", userinfo);		
		req.setAttribute("beanElearnTools", ets);

		try
		{
			//ȡ�ò���
			s_Action = getParameter(req, "action", true, false, false,"");
		
		
//�������
			if (s_Action.equalsIgnoreCase("add"))
			{
			    req.setAttribute("action", s_Action);
			    ss_sql = "SELECT max(news_id) as maxid FROM t_news";
					 bgd.executeSelect(ss_sql);
				     s_Newsid= bgd.getFieldValue("maxid", 0);
				     if (s_Newsid.equals(""))
				     {
				     s_Newsid="00000000000000000000";
					
					 }	
					 else{
					 	 s_Newsid = ets.AutoNum(s_Newsid);
				}
			       	
			        s_FileName = getParameter(req, "addPage", false, true, true, "");
			        newstype = getParameter(req, "news_type", true, false, true, "");
				    newstitle = getParameter(req, "news_title", true, false, true, "");
				    newskey= getParameter(req, "news_key", true, false, true, "");
				   newscontent=getParameter(req, "news_content", true, false, true, "");
				    newsman=getParameter(req, "news_man", true, false, true, "");
		           newsimg=getParameter(req, "file1", true, false, false, "");
		           newshead=getParameter(req, "head", true, false, true, "");
				   s_Sql = "INSERT INTO t_news (news_id, news_type,news_title,news_key,news_content,news_date,news_count,news_man,news_status,news_img,news_head) values('"+s_Newsid+"','"+newstype+"','"+newstitle+"','"+newskey+"','"+newscontent+"','"+bgd.getDbDate()+"',0,'"+newsman+"','1', '"+newsimg+"','"+newshead+"')";
				 bgd.execute(s_Sql);
		         getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);	    
	             return;
	             
		}
		
		if (s_Action.equalsIgnoreCase("update"))
		{
			req.setAttribute("action", s_Action);
        	s_FileName = getParameter(req, "listPage", false, true, true, "");
	
			try{
	    		String title= getParameter(req, "title", true, false, true, "");
	    		String type= getParameter(req, "type", true, false, true, "");
	    		String key= getParameter(req, "key", true, false, false, "");
	    		String content=getParameter(req, "content", true, false, true, "");
	    		String man= getParameter(req, "man", true, false, true, "");
	    		String img= getParameter(req, "file1", true, false, false, "");
        		String head= getParameter(req, "head", true, false, true, "");

		        ss_sql="update t_news set news_title='"+title+"',news_type='"+type+"',news_key='"+key+"',news_content='"+content+"',news_date='"+bgd.getDbDate()+"',news_man='"+man+"',news_img='"+img+"',news_head='"+head+"' where news_id='"+req.getParameter("newsid")+"' ";
				bgd.execute(ss_sql);
        		getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);	    
		
			}
			catch(Exception E){
		 		System.out.print(E);
			}
	
			getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);	    
	     	return;
	  	}

				
	   if(s_Action.equalsIgnoreCase("del")) {
	   	req.setAttribute("action",s_Action);
        s_FileName = getParameter(req, "listPage", false, true, true, "");
	   
	   	String s_selectedItem;
	   	String s_countPara = getParameter(req, "countInPage", true, false, true, "");
	   	int i_count = Integer.parseInt(s_countPara);
	   	String s_MsgId = "";
	   	for (int i=0;i<i_count;i++)
	   	{
	   	s_selectedItem = getParameter(req, "select" + Integer.toString(i),true, false, false, "");
	   	
	   	
	   	if (s_selectedItem.equals(""))
						continue;
					else
						s_MsgId = s_MsgId + ",'" + s_selectedItem + "'";
				}
				if (!s_MsgId.equals(""))
				{
					s_MsgId = s_MsgId.substring(1, s_MsgId.length());
	System.out.println(s_MsgId);
	System.out.println("000000000000000000000000000000000000000");
	  
	  
	   	ss_sql="update t_news set news_status='0' where news_id in ("+s_MsgId+ ")";
	  
   System.out.println(ss_sql);
	        bgd.execute(ss_sql);
	   	 	 
      
    
	}    getServletConfig().getServletContext().getRequestDispatcher(s_FileName).forward(req, res);	    
	    return;
	   
	   }
		
		}
			
			
				catch(Exception ex){}

			

}
}
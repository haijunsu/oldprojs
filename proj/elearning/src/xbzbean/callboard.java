package xbzbean;
import com.htyz.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class callboard extends HttpServlet
{
	private beanGetdata bgd = new beanGetdata();
	
	
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
	
	public void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{ try{
	String s_sql;
	String title=req.getParameter("title");
	String content=req.getParameter("content");
    boolean create = false; //�����Ƿ񴴽�session��ֻ����Login�в�Ϊtrue
    HttpSession session = req.getSession(create);
	String userid = (String)session.getAttribute("userid");
     s_sql=("insert into t_borad(user_id,call_name,call_content)values('"+userid +"','"+title+"','"+content+"')");
     bgd.execute(s_sql);
    getServletConfig().getServletContext().getRequestDispatcher("/callboard.jsp").forward(req, res);
	 
	 return;
}
    catch(Exception E){
   	System.out.print(E);
   	}
	
	}
}

package xbzbean;
import com.htyz.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class callboard extends HttpServlet
{
	private beanGetdata bgd = new beanGetdata();
	
	
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
	
	public void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{ try{
	String s_sql;
	String title=req.getParameter("title");
	String content=req.getParameter("content");
    boolean create = false; //决定是否创建session，只有在Login中才为true
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

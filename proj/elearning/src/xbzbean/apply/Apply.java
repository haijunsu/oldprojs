/*
 *课程批准
 * 
 */
package xbzbean.apply;
import javax.servlet.*;
import javax.servlet.http.*;

import com.htyz.beanGetdata;

public class Apply extends HttpServlet
{
	private String Query_Sql = "";
	private String Select_Sql = "Select * from t_user_apple ";
	private String Order_Sql = "order by Apply_time";
	private String Conditions_Sql = "";
    private beanGetdata bgd = new beanGetdata();
    
   	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
	}
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
		doProcess(req, res);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
		doProcess(req, res);
	}
	public void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException
	{
//    String s_sql, ss_sql;
//    String userid=session.getAttribute("userid");
//    String classid=req.getParameter("chekbox");
//    Conditions_Sql="where user_id='"+userid+"'and class_id='+classid+'";
//    Query_Sql=Select_Sql+Conditions_Sql;
//    bgd.executeSelect(Query_Sql);
//    if(bdg.getRowCount()>=1)
//    {
//     out.print("您已添加了此门课程!");
//    }
//    else
//    {
//    ss_sql="UPDATE t_user_class set user_id='"+userid+"',class_id='"+classid+"'";
//    rtn1=bdg.execute(ss_sql);
//    s_sql = "UPDATE t_user_apple set apply_status='0' where user_id='"+user_id+"' and class_id='"+classid+"' ";
//	rtn2 = bgd.execute(s_Sql);
// }
//	}
}

}


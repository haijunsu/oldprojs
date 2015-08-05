//////////////////////////////////////////////
//
//  显示用户菜单模块jsp/bean模式
//
//     苏海军     2002.1.24
//
//////////////////////////////////////////////

package system;

import com.htyz.*;

public class beanMenuShow
{
	
	protected beanElearnTools ets = new beanElearnTools();
	protected beanGetdata bgd = new beanGetdata();
	protected beanGetdata bgdmenu = new beanGetdata();

	//初始化Bean
	public void beanMenuShow()
	{

	}
	
	//Servlet的执行内容	
	public void SelectMenus(String UserId) throws Exception
	{
		//定义变量
		String s_Sql, s_ConditionsSql, s_QuerySql, s_ExeSql;
		s_Sql = "SELECT * FROM t_user ";
		s_ConditionsSql = "";
		s_QuerySql = "";
		s_ExeSql = "";
		
		if((UserId != null)&&!(UserId.equals("")))
		{
			s_ConditionsSql = " WHERE user_id = '" + UserId + "'";
		}
		s_QuerySql = s_Sql + s_ConditionsSql;
		bgd.executeSelect(s_QuerySql);
			
		s_QuerySql = "SELECT * FROM t_menu";
		bgdmenu.executeSelect(s_QuerySql);
	}
	
	//取user表的内容
	public String getUser(String col, int row) throws Exception
	{
		return bgd.getFieldValue(col, row);
	}
	
	public String getUser(int col, int row) throws Exception
	{
		return bgd.getFieldValue(col, row);
	}
	
	//取Menu
	public String getMenu(String col, int row) throws Exception
	{
		return bgdmenu.getFieldValue(col, row);
	}
	
	public String getMenu(int col, int row) throws Exception
	{
		return bgdmenu.getFieldValue(col, row);
	}
	
	public int getMenuCount() throws Exception
	{
		return bgdmenu.getRowCount();
	}
	
	//校验是否显示
	public boolean isShow(String userRight, String menuRight)
	{
		return ets.isMenuShow(userRight, menuRight);
	}
}


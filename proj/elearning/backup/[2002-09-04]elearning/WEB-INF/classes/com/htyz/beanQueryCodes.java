///////////////////////////////////
//取数据库代码表中的对应值
//
//  苏海军
//
//     2002.1
//
//
///////////////////////////////////

package com.htyz;

public class beanQueryCodes
{
	private String Query_Sql = "";
	private String Select_Sql = "SELECT * FROM t_code ";
	private String Order_Sql = " ORDER BY Code_id, Code_value";
	private String Conditions_Sql = "";
	
	private beanGetdata bgd = new beanGetdata();
	
	public beanQueryCodes()
	{
		super();//加入实例化参数
	}
	
	public int QueryCode(String codeType)
	{
		try
		{
			Conditions_Sql = " WHERE Code_id = '" + codeType + "' ";
			Query_Sql = Select_Sql + Conditions_Sql + Order_Sql;
			bgd.executeSelect(Query_Sql);
			return 1;
		}
		catch(Exception ex)
		{
			System.err.println("bean_QueryCodes.QueryCode error:" + ex.getMessage());
			return -1;
		}
	}

	public int QueryCode(String codeType, String codeValue)
	{
		try
		{
			Conditions_Sql = " WHERE code_id = '" + codeType + "' AND code_value = '" + codeValue + "' ";
			Query_Sql = Select_Sql + Conditions_Sql + Order_Sql;
			bgd.executeSelect(Query_Sql);
			return 1;
		}
		catch(Exception ex)
		{
			System.err.println("bean_QueryCodes.QueryCode error:" + ex.getMessage());
			return -1;
		}
	}
	
	//返回代码总数
	public int getCodeCount() throws ArrayIndexOutOfBoundsException
	{
		try
		{
			return bgd.getRowCount();
		}
		catch (ArrayIndexOutOfBoundsException ex)
		{
			System.err.println("bean_QueryCodes.getCodeCount() error:" + ex.getMessage());
			return 0;
		}
	}
	
	//返回当前的代码的名称
	public String getCodeValue(int col, int row) throws ArrayIndexOutOfBoundsException
	{
		try
		{
			return bgd.getFieldValue(col, row).trim();
		}
		catch (ArrayIndexOutOfBoundsException ex)
		{
			System.err.println("bean_QueryCodes.getCodeValue(int, int) error:" + ex.getMessage());
			return "";
		} 
	}
	
	public String getCodeValue(String col, int row) throws ArrayIndexOutOfBoundsException
	{
		try
		{
			return bgd.getFieldValue(col, row).trim();
		}
		catch (ArrayIndexOutOfBoundsException ex)
		{
			System.err.println("bean_QueryCodes.getCodeValue(String, int) error:" + ex.getMessage());
			return "";
		} 
	}

}
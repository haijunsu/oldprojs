package xbzbean;
import java.sql.*;
import java.io.*;
import java.util.*;
import javax.sql.*;

public class SqlJdbc {

String strUser="sa",strPassword="";
String strDbdriver="com.microsoft.jdbc.sqlserver.SQLServerDriver";
String strConstring="jdbc:microsoft:sqlserver://localhost:1433";
//String strDbdriver = "org.gjt.mm.mysql.Driver";
//String strConstring = "jdbc:mysql://localhost:3306/ibbs";


Connection conn = null;
ResultSet rs = null;

public SqlJdbc() {
try {//--read db info from config file-------------
             
Class.forName(strDbdriver); 
}
catch(Exception e) {
System.err.println("mydb(): " + e.getMessage());
}
}

//----query------------------
public ResultSet executeQuery(String sql)throws Exception {
rs = null;
conn = DriverManager.getConnection(strConstring,strUser,strPassword); 
Statement stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
rs = stmt.executeQuery(sql);

while (rs.next())
{
	System.out.println(rs.getString(1));
}
if(rs==null){System.exit(1);}
return rs;
}
//---Update-------------------
public void executeUpdate(String sql) {
try {
conn = DriverManager.getConnection(strConstring,strUser,strPassword); 
Statement stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_SENSITIVE,java.sql.ResultSet.CONCUR_UPDATABLE);
stmt.executeUpdate(sql);
stmt.close();
conn.close();
} 
catch(SQLException ex) { 
System.err.println("aq.executeUpdate: " + ex.getMessage());
 }
}

public static void main(String avg[])
{
	SqlJdbc sj = new SqlJdbc();
	try
	{
	 sj.executeQuery("Select * from spt_values");
	}catch(Exception e){
		e.printStackTrace();
		}
}

}
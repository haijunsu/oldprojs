package com.htyz.notice;
import java.rmi.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import com.htyz.*;
import com.htyz.util.BeanDateFormat;
import com.htyz.system.SystemProperties;

public class noticeBean {
beanSQL bean_Sql = new beanSQL();

private Connection con;
public noticeBean() {
}


  //==========================================================================
  // METHOD    : connectdatabase
  // DESC      : 连接数据库
  // PARAMETERS:
  // CREATE    : 1.0, liu_ag, 2002-7-11
  // MODIFY    :
  //==========================================================================
  public void connectdatabase() throws RemoteException
  {
    try
    {
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      con=DriverManager.getConnection("jdbc:odbc:elearning");
      Statement stmt=con.createStatement();
     
    }
    catch(Throwable e)
    {
      e.printStackTrace();
    }

  }
  //==========================================================================
  // METHOD    : charchange
  // DESC      : 字符集转换
  // PARAMETERS:
  // CREATE    : 1.0, liu_ag, 2002-7-11
  // MODIFY    :
  //==========================================================================
   public String charchange(String str) throws RemoteException
  {
    String newstr=" ";
    if(SystemProperties.getProperty("processURLChinese").equalsIgnoreCase("true")){
    	try
    	{
      		byte[] c= str.getBytes("ISO8859_1");
      		newstr = new String(c,"GBK");
    	}
    	catch(Throwable e)
    	{
      		e.printStackTrace();
    	}
    }else {
    	newstr = str;
    }
    return newstr;
  }
  //==========================================================================
  // METHOD: getSysDate
  // DESC  : 获取当前系统日期
  // CREATE: 1.0, liu-ag, 2002-7-12
  // MODIFY:
  //==========================================================================
  public String getSysDate() throws Exception
  {
    Calendar rightNow = Calendar.getInstance();

    Integer year = new Integer(rightNow.get(Calendar.YEAR));
    Integer month = new Integer(rightNow.get(Calendar.MONTH)+1);
    Integer day = new Integer(rightNow.get(Calendar.DATE));

    String sYear = null;
    String sMonth = null;
    String sDay = null;

    sYear = year.toString();
    if(month.intValue()<10)
            sMonth = "0" + month.toString();
    else
            sMonth = month.toString();
    if(day.intValue()<10)
            sDay = "0" + day.toString();
    else
            sDay = day.toString();

    //return sYear +"-" +sMonth +"-" +sDay;
    BeanDateFormat beanDate=new BeanDateFormat();
    return beanDate.parseDBDate(sYear,sMonth,sDay);
  }
  //==========================================================================
  // METHOD    : getuserData
  // DESC      : 得到用户数据
  // PARAMETERS:
  // CREATE    : 1.0, liu_ag, 2002-7-12
  // MODIFY    :
  //==========================================================================
  public Vector getuserData() throws RemoteException, Exception
  {
       Vector vc = new Vector();
       String stmt = null;
       userDO urdo=null;
       Statement ps = null;
       ResultSet rs = null;

       try
       {
         con = bean_Sql.getCon();
         if(con == null)
         {
           throw new Exception ( "Connect to database error!" );
         }
         stmt = "select user_id,user_name from t_user";
          ps = con.createStatement();

          ps.executeQuery(stmt);
          rs = ps.getResultSet();
         while (rs.next())
         {
           urdo = new userDO();
           urdo.setUser_id(rs.getString(1));
           urdo.setUser_name(rs.getString(2));
           vc.addElement(urdo);
          }
      }

      catch (SQLException sqe)
      {
       throw new RemoteException(sqe.getMessage());
      }

  finally
  {
      try
      {
          if (rs != null) rs.close();
          if (ps != null) ps.close();
		  if (con != null) con.close();
      }
      catch (Exception e)
      {
          throw new RemoteException (e.getMessage());
      }
  }
          return  vc ;
  }
   //==========================================================================
  // METHOD    : getGroupData
  // DESC      : 得到组数据
  // PARAMETERS:
  // CREATE    : 1.0, liu_ag, 2002-7-23
  // MODIFY    :
  //==========================================================================
  public Vector getGroupData() throws RemoteException, Exception
  {
       Vector vc = new Vector();
       String stmt = null;
       groupDO grdo=null;
       Statement ps = null;
       ResultSet rs = null;

       try
       {
         con = bean_Sql.getCon();
         if(con == null)
         {
           throw new Exception ( "Connect to database error!" );
         }
		  stmt = "select code_value,code_namec from t_code where code_id='0002'";
          ps = con.createStatement();

          ps.executeQuery(stmt);
          rs = ps.getResultSet();
         while (rs.next())
         {
           grdo = new groupDO();
           grdo.setGroup_id(rs.getString(1));
           grdo.setGroup_name(rs.getString(2));
           vc.addElement(grdo);
          }
      }

      catch (SQLException sqe)
      {
       throw new RemoteException(sqe.getMessage());
      }

  finally
  {
      try
      {
          if (rs != null) rs.close();
          if (ps != null) ps.close();
		  if (con != null)con.close();
      }
      catch (Exception e)
      {
          throw new RemoteException (e.getMessage());
      }
  }
          return  vc ;
  }
  //==========================================================================
  // METHOD    : getMaxNotice_id
  // DESC      : 得到一个最大号加1
  // PARAMETERS:
  // CREATE    : 1.0, liu_ag, 2002-7-11
  // MODIFY    :
  //==========================================================================
  public String getMaxNotice_id() throws RemoteException, Exception
  {
      //boolean find = false ;
       String stmt = null,maxNo="1";
       int intMaxNo=0;
       Statement ps = null;
       ResultSet rs = null;
       try
       {

         con = bean_Sql.getCon();
         if(con == null)
         {
           throw new Exception ( "Connect to database error!" );
         }
		  stmt = "select max(notice_id) from t_notice";

          ps = con.createStatement();

          ps.executeQuery(stmt);
          rs = ps.getResultSet();
         while (rs.next())
         {
           intMaxNo=rs.getInt(1);
           if(intMaxNo==0)
            intMaxNo = 1;
           else
            intMaxNo += 1;
           maxNo = String.valueOf(intMaxNo);
          }

      }

      catch (SQLException sqe)
      {
       throw new RemoteException(sqe.getMessage());
      }

  finally
  {
      try
      {
          if (rs != null) rs.close();
          if (ps != null) ps.close();
		  if (con != null) con.close();
      }
      catch (Exception e)
      {
          throw new RemoteException (e.getMessage());
      }
  }
          return  maxNo ;
  }
  //==========================================================================
  // METHOD    : getInitData
  // DESC      : 取初始数据
  // PARAMETERS: 无
  // CREATE    : 1.0, liu_ag, 2002-7-11
  // MODIFY    :
  //==========================================================================
  public Vector getInitData() throws RemoteException, Exception
  {
      Vector vc = new Vector();
      String stmt = null;

       Statement ps = null;
       ResultSet rs = null;
       noticeDO ntcDO = null;

       try
       {

         con = bean_Sql.getCon();
         if(con == null)
         {
           throw new Exception ( "Connect to database error!" );
         }
		  stmt = "select notice_id,user_id,notice_name,notice_content,";
          stmt+="notice_time,valid_time,to_time,notice_class from t_notice order by notice_id  desc";

          ps = con.createStatement();
          ps.executeQuery(stmt);
          rs = ps.getResultSet();
         while (rs.next())
         {
           ntcDO = new noticeDO();
           ntcDO.setNotice_id(rs.getString(1));
           ntcDO.setUser_id(rs.getString(2));
           ntcDO.setNotice_name(rs.getString(3));
           ntcDO.setNotice_content(rs.getString(4));
           ntcDO.setNotice_time(rs.getString(5));
           ntcDO.setValid_time(rs.getString(6));
           ntcDO.setTo_time(rs.getString(7));
           ntcDO.setNotice_class(rs.getString(8));
           vc.addElement(ntcDO);

          }

      }

      catch (SQLException sqe)
      {
       throw new RemoteException(sqe.getMessage());
      }

  finally
  {
      try
      {
          if (rs != null) rs.close();
          if (ps != null) ps.close();
		  if (con != null) con.close();
      }
      catch (Exception e)
      {
          throw new RemoteException (e.getMessage());
      }
  }
          return  vc ;
  }
   //////////////
 //==========================================================================
  // METHOD    : getDataByNo
  // DESC      : 根据通知号取一条数据
  // PARAMETERS: String notice_id
  // CREATE    : 1.0, liu_ag, 2002-7-11
  // MODIFY    :
  //==========================================================================
  public noticeDO getDataByNo(String notice_id) throws RemoteException, Exception
  {
       String stmt = null;

       Statement ps = null;
       ResultSet rs = null;
       noticeDO ntcDO = null;
       try
       {
		  con = bean_Sql.getCon();
          if(con == null)
          {
            throw new Exception ( "Connect to database error!" );
          }
          stmt = "select notice_id,user_id,rtrim(notice_name),rtrim(notice_content),notice_time,valid_time,to_time from t_notice where notice_id = '"+notice_id+"' ";

          ps = con.createStatement();
          //ps.setString(1,notice_id);
          ps.executeQuery(stmt);
          rs = ps.getResultSet();
         while (rs.next())
         {
           ntcDO = new noticeDO();
           ntcDO.setNotice_id(rs.getString(1));
           ntcDO.setUser_id(rs.getString(2));
           ntcDO.setNotice_name(rs.getString(3));
           ntcDO.setNotice_content(rs.getString(4));
           ntcDO.setNotice_time(rs.getString(5));
           ntcDO.setValid_time(rs.getString(6));
           ntcDO.setTo_time(rs.getString(7));
          }

      }

      catch (SQLException sqe)
      {
       throw new RemoteException(sqe.getMessage());
      }

  finally
  {
      try
      {
          if (rs != null) rs.close();
          if (ps != null) ps.close();
		  if (con != null) con.close();
      }
      catch (Exception e)
      {
          throw new RemoteException (e.getMessage());
      }
  }
          return  ntcDO ;
  }

  //////////////
//==========================================================================
// METHOD    : getDataByUser
// DESC      : 根据用户号取数据
// PARAMETERS: String user_id
// CREATE    : 1.0, liu_ag, 2002-7-25
// MODIFY    :
//==========================================================================
public Vector getDataByUser(String user_id,String group_id) throws RemoteException, Exception
{
     String stmt = null;

     Statement ps = null;
     ResultSet rs = null;
     noticeDO ntcDO = null;
     Vector vec=new Vector();
     try
     {
       con = bean_Sql.getCon();
       if(con == null)
       {
         throw new Exception ( "Connect to database error!" );
       }
	   user_id= "%"+user_id+"%";
       group_id='%'+group_id+'%';
       stmt = "select notice_id,user_id,rtrim(notice_name),rtrim(notice_content),notice_time,valid_time,to_time from t_notice where rtrim(user_name) like '" + user_id + "' or rtrim(user_name) like '" + group_id + "'  ";
       stmt+=" order by notice_id desc ";
       ps = con.createStatement();
       //ps.setString(1,user_id);
      // ps.setString(2,group_id);
       ps.executeQuery(stmt);
       rs = ps.getResultSet();
       while (rs.next())
       {
         ntcDO = new noticeDO();
         ntcDO.setNotice_id(rs.getString(1));
         ntcDO.setUser_id(rs.getString(2));
         ntcDO.setNotice_name(rs.getString(3));
         ntcDO.setNotice_content(rs.getString(4));
         ntcDO.setNotice_time(rs.getString(5));
         ntcDO.setValid_time(rs.getString(6));
         ntcDO.setTo_time(rs.getString(7));
         vec.addElement(ntcDO);
        }

    }

    catch (SQLException sqe)
    {
     throw new RemoteException(sqe.getMessage());
    }

    finally
    {
      try
      {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
		if (con != null) con.close();
      }
      catch (Exception e)
      {
        throw new RemoteException (e.getMessage());
      }
    }
    return  vec ;
}
//==========================================================================
  // MOTHOD: getRowCount(String user_id,String group_id)
  // INPUT : String user_id,String group_id
  // RETURN: int
  // DESC  : 返回符合条件的记录数
  // CREATE: 1.0,liu-lag,2002-7-25
  // MODIFY:
  //==========================================================================
  public int getRowCount(String user_id,String group_id) throws RemoteException, Exception
  {
      Statement ps = null;
      ResultSet rs = null;
      int intcount = 0;
      String sysdate="";
      user_id="%"+user_id+"%";
      group_id="%"+group_id+"%";
      try
      {
        sysdate=getSysDate();
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }

     // boolean bflag = false  ;
      try
      {

         con = bean_Sql.getCon();
         if(con == null)
         {
           throw new Exception ( "Connect to database error!" );
         }
          ps = con.createStatement();
          //ps.setString(1,sysdate);
          ps.executeQuery("select count(*)" +
        " from t_notice  where (rtrim(user_name) like '" + user_id + "' or rtrim(user_name) like '" + group_id + "' )  and to_time >= '" + sysdate + "'  ");

          rs = ps.getResultSet();
          if (rs.next())
          {
             intcount = rs.getInt(1);

          }
      }

      catch (SQLException sqe)
      {
          throw new RemoteException(sqe.getMessage());
      }
      finally
      {
          try
          {
              if (rs != null) rs.close();
              if (ps != null) ps.close();
			  if (con != null) con.close();
          }
          catch (Exception e)
          {
              throw new RemoteException (e.getMessage());
          }
      }

      return intcount;
    }
//////////////
    //==========================================================================
	// METHOD: SaveNoticeData
	// DESC  : 保存通知数据
	// CREATE: 1.0, liu-ag, 2002-07-11
	// MODIFY:
	//==========================================================================
    public boolean SaveNoticeData(noticeDO ntcDO) throws RemoteException

    {
        //System.out.println("notice save begin!");
        int rowcount = 0;
        boolean flg = false;
        String notice_id = "" ;
       // PreparedStatement prepStmt = null;
       // ResultSet rs = null;

        try
        {
            notice_id = ntcDO.getNotice_id() ; //通知号
            flg = DeleteNoticeData(notice_id) ;			
			flg=InsertNoticeData(ntcDO) ;
			

            //con.commit();

        }
    	catch (Exception e)
    	{
            System.out.println("notice save exception1:!"+ e.getMessage() + "\n");
            throw new RemoteException (e.getMessage());
    	}
        finally
        {
            try
            {
                //if (rs != null) rs.close();
               // if (prepStmt != null) prepStmt.close();
				//if (con != null) con.close();
            }
            catch (Exception e)
            {
                System.out.println("notice save exception2:!"+ e.getMessage() + "\n");
                throw new RemoteException (e.getMessage());
            }
        }
        //System.out.println("notice save end!");
        return true ;
    }

    //==========================================================================
	// METHOD: DeleteNoticeData
	// DESC  : 删除数据
	// CREATE: 1.0, liu-ag, 2002-7-11
	// MODIFY:
	//==========================================================================
    public boolean DeleteNoticeData(String notice_id) throws RemoteException, Exception
    {
        Statement ps = null;
        ResultSet rs = null;

        try
        {

          con = bean_Sql.getCon();
		  if(con == null)
		  {
			 throw new Exception ( "Connect to database error!" );
		  }
		  // System.out.println("DeleteNoticeData begin!");
           ps = con.createStatement();
          // ps.setString(1,notice_id);

           if (ps.executeUpdate("delete from t_notice where notice_id = '"+notice_id+"' ") == 0)
           {
               System.out.println("DeleteNoticeData del 0 row!");
           }
           //System.out.println("DeleteNoticeData success!");
           //con.commit();

        }
        catch (Exception e)
        {
            System.out.println("DeleteNoticeData exception1:!"+ e.getMessage() + "\n");
            throw new RemoteException (e.getMessage());
        }
        finally
        {
            try
            {
                if(ps!=null)
                 ps.close();
				if(con!=null)
                 con.close();
            }
            catch (Exception e)
            {
                System.out.println("DeleteNoticeData exception:!"+ e.getMessage() + "\n");
                throw new RemoteException (e.getMessage());
            }
        }
        return true ;
    }
//==========================================================================
	// METHOD: InsertNoticeData
	// DESC  : 插入数据
	// CREATE: 1.0, liu-ag, 2002-07-11
	// MODIFY:
	//==========================================================================
    public boolean InsertNoticeData(noticeDO ntcDO) throws RemoteException, Exception
    {

        Statement ps = null;
        ResultSet rs = null;
		String sql="";
        try
        {
            con = bean_Sql.getCon();
            if(con == null)
		    {
			   throw new Exception ( "Connect to database error!" );
		    }
			sql="insert into t_notice values('"+ntcDO.getNotice_id()+"','"+ntcDO.getUser_id()+"','"+ntcDO.getNotice_name()+"','"+ntcDO.getNotice_content()+"','"+ntcDO.getNotice_time()+"','"+ntcDO.getValid_time()+"','"+ntcDO.getTo_time()+"','1','"+ntcDO.getUser_name()+"')";
			ps = con.createStatement() ;
			
           /* ps = con.createStatement("insert into t_notice values(?,?,?,?,?,?,?,?,?)") ;
            ps.setString(1, ntcDO.getNotice_id());           //通知号
            ps.setString(2, ntcDO.getUser_id());            //用户号
            ps.setString(3, ntcDO.getNotice_name());        //通知名
            ps.setString(4, ntcDO.getNotice_content());     //通知内容
            ps.setString(5, ntcDO.getNotice_time());        //通知时间
            ps.setString(6, ntcDO.getValid_time());         //生效时间
            ps.setString(7, ntcDO.getTo_time());           //截止时间
            ps.setString(8,"1");       //通知类别
            ps.setString(9, ntcDO.getUser_name());            //用户号*/

            if (ps.executeUpdate(sql) == 0)
            {
                //System.out.println("InsertNoticeData fail!");
				return false;

            }
            //System.out.println("InsertNoticeData success!");
            con.commit();
        }
        catch (Exception e)
        {
            System.out.println("InsertNoticeData exception: "+ e.getMessage() + "\n");
            throw new RemoteException (e.getMessage());
        }
        finally
        {
            try
            {
                if(ps!=null)
                    ps.close();
			    if (con != null) 
					con.close();
            }
            catch (Exception e)
            {
            	System.out.println("InsertNoticeData exception: "+ e.getMessage() + "\n");
                throw new RemoteException (e.getMessage());
            }
        }
		return true;

    }

	//////
	//==========================================================================
  // METHOD    : getcode
  // DESC      : 得到课程代码
  // PARAMETERS:
  // CREATE    : 1.0, liu_ag, 2002-7-12
  // MODIFY    :
  //==========================================================================
  public Vector getcode() throws RemoteException, Exception
  {
       Vector vc = new Vector();
       String stmt = null;
       userDO urdo=null;
       Statement ps = null;
       ResultSet rs = null;

       try
       {
         con = bean_Sql.getCon();
         if(con == null)
         {
           throw new Exception ( "Connect to database error!" );
         }
         stmt = "select distinct code_id"+ SystemProperties.getProperty("db.addsign")+"code_value,code_namec from t_code";
          ps = con.createStatement();
          ps.executeQuery(stmt);
          rs = ps.getResultSet();
         while (rs.next())
         {
           urdo = new userDO();
           urdo.setUser_id(rs.getString(1));
           urdo.setUser_name(rs.getString(2));
           vc.addElement(urdo);
          }
      }

      catch (SQLException sqe)
      {
       throw new RemoteException(sqe.getMessage());
      }

  finally
  {
      try
      {
          if (rs != null) rs.close();
          if (ps != null) ps.close();
		  if (con != null) con.close();
      }
      catch (Exception e)
      {
          throw new RemoteException (e.getMessage());
      }
  }
          return  vc ;
  }
  
  	//////
	//==========================================================================
  // METHOD    : getcode
  // DESC      : 得到课程代码
  // PARAMETERS:
  // CREATE    : 1.0, liu_ag, 2002-7-12
  // MODIFY    :
  //==========================================================================
  public Vector getclasscode(String codeid) throws RemoteException, Exception
  {
       Vector vc = new Vector();
       String stmt = null;
       userDO urdo=null;
       Statement ps = null;
       ResultSet rs = null;

       try
       {
         con = bean_Sql.getCon();
         if(con == null)
         {
           throw new Exception ( "Connect to database error!" );
         }
         stmt = "select  code_value,code_namec from t_code where code_id='"+codeid+"'";
          ps = con.createStatement();
          ps.executeQuery(stmt);
          rs = ps.getResultSet();
         while (rs.next())
         {
           urdo = new userDO();
           urdo.setUser_id(rs.getString(1));
           urdo.setUser_name(rs.getString(2));
           vc.addElement(urdo);
          }
      }

      catch (SQLException sqe)
      {
       throw new RemoteException(sqe.getMessage());
      }

  finally
  {
      try
      {
          if (rs != null) rs.close();
          if (ps != null) ps.close();
		  if (con != null) con.close();
      }
      catch (Exception e)
      {
          throw new RemoteException (e.getMessage());
      }
  }
          return  vc ;
  }
}
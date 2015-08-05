<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.*"%>
<%@ page import="com.htyz.*"%>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="lessonScheduleBgdclass" scope="request" class="com.htyz.beanGetdata"/>

<SCRIPT LANGUAGE=javascript>

function setflg()
{
  document.form2.flg.value="ok"
}

function confirmer()
{
  c=document.form2.flg.value
  if(c.length==0)
  {
    alert("请先选择一条记录!")
    return false ;
  }
 if(confirm("确定删除么?"))
 {
    return true ;

 }
 else
 {
  return false ;
 }

}

</SCRIPT>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</head>

<body >

<form method="post" name="form2" action="lessonDel.jsp">

  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
     <tr bgcolor="#CCCCCC" align="center"> 
      <td width="10%">单选钮</td>
      <td width="50%">&nbsp;&nbsp;课程名称</td>
      <td width="40%">&nbsp;组名称</td>
     
    </tr>
    <%
    int totalRowNum=0,pageRowNum=10,totalPageNum=1,curPageNum=1,nextPageNum=2;
    int startRow=0,endRow=1,nextstaRow=1;
	String cls_id="",cod_val="",s_sql="",s_insert="",s_select="";
	String lessonScheduleSql="",date="";
    if (request.getParameter("del") != null)
	{
	  boolean bz=false;

	  if (request.getParameter("radiobutton") == null)
	  {
		 out.println("选择一条记录后在删除");
		 return ;
	  }
	  else
	  {
		int count=0;
		cls_id= (request.getParameter("radiobutton")).substring(0,14);
		cod_val= (request.getParameter("radiobutton")).substring(15);
		
		if(cls_id!=null&&cod_val!=null)
		{
			s_select=" select a.user_id userid,class_id,start_time,end_time,status from t_user_class b,t_user a where b.user_id = a.group_id and b.user_id='"+cod_val+"' and class_id='"+cls_id+"' ";

           
			bgd.executeSelect(s_select);
			date=bgd.getDbDate();
			for (int i=0; i<bgd.getRowCount(); i++)
			{
				String user_id="",start_time="",end_time="",status="";
				user_id=bgd.getFieldValue("userid", i);
				start_time=bgd.getFieldValue("start_time", i);
				end_time=bgd.getFieldValue("end_time", i);
				status=bgd.getFieldValue("status", i);
				//计算学习进度
				String percent, remain;
				lessonScheduleSql = "SELECT count(lesson_id) FROM t_lesson WHERE class_id = '" + cls_id + "'";
				lessonScheduleBgdclass.executeSelect(lessonScheduleSql);
				String totalSection = lessonScheduleBgdclass.getFieldValue(1, 0);
				lessonScheduleSql = "SELECT count(lesson_id) FROM t_lesson_log WHERE class_id = '" + cls_id + "' AND user_id = '" +user_id + "'";
				lessonScheduleBgdclass.executeSelect(lessonScheduleSql);
				String userSection = lessonScheduleBgdclass.getFieldValue(1, 0);
				float a = Float.parseFloat(totalSection);
				float b = Float.parseFloat(userSection);
				if ((int)a == 0)
				{
					percent = "0";
					remain = "100";
				}
				else
				{
					a = b/a*100;
					percent = new java.text.DecimalFormat("0").format(a);
					a = 100 - a;
					remain = new java.text.DecimalFormat("0").format(a);
				}
				s_insert="insert  into t_userclass_del(user_id,class_id,start_time,end_time,status,cancel_time,reason,schedule) values('"+user_id+"','"+cls_id+"','"+start_time+"','"+end_time+"','"+status+"','"+date+"','2','"+percent+"')";
				count=bgd.execute(s_insert);
			}
            if(count>0)
			{
				
				s_sql="delete from t_user_class where class_id= '"+cls_id+"' and user_id= '"+cod_val+"' ";
				count=bgd.execute(s_sql);
				if(count<0)
				{
				  out.println("删除失败!");
				  return ;
				}
			}
			else
			{
				out.println("备份失败!");
			}
			
		 }
	  }

   }

    if(request.getParameter("page")!=null)
    {
      curPageNum=Integer.parseInt(request.getParameter("page"));
      nextPageNum=curPageNum+1 ;
    }
    %>
    <%
    try
    {
      String class_id = " ",class_name=" ",code_value = " ",code_namec = " ",sql="",str="";
      sql="select  c.class_id,c.class_name,t_code.code_value,t_code.code_namec ";
	  sql=sql+" from t_class c,t_user_class t,t_code ";
      sql=sql+"where c.class_id=t.class_id  and t.user_id=t_code.code_value and t_code.code_id='0002'";
	  sql=sql+" order by c.class_id";
     
	  bgd.executeSelect(sql);
	  totalRowNum=bgd.getRowCount();

       if(totalRowNum%pageRowNum==0)
       {
         totalPageNum=totalRowNum/pageRowNum ;
       }
       else
       {
          totalPageNum=(totalRowNum/pageRowNum) +1 ;
       }
      startRow=(curPageNum-1)*pageRowNum ;
      endRow=curPageNum*pageRowNum ;
      nextstaRow=(curPageNum)*pageRowNum ;
      if(endRow>totalRowNum)
        endRow=totalRowNum;
      for(int i=startRow;i<endRow;i++)
      {
         class_id=bgd.getFieldValue("class_id",i);
		 class_name=bgd.getFieldValue("class_name",i);
		 code_value=bgd.getFieldValue("code_value",i);
		 code_namec=bgd.getFieldValue("code_namec",i);
		 str=class_id+"/"+code_value;
    %>
    <tr >
      <td width="10%">
      <input onclick = "setflg()" type="radio" name="radiobutton" value="<%=str%>">
      </td>
      <td width="50%" ><%=class_name%></td>
      <td width="40%"><%=code_namec%></td>
      
    </tr>
    <% } }
    catch(Exception e)
    {
      e.printStackTrace();
    }%>

  </table>
  <table width="90%" border="0">
    <tr>
      
      <td width="30%" height="29">
        <div align="center">
          <%--<input type="submit"  value="删除" name="del" onclick = "return confirmer()">--%>
        <INPUT type="submit" id=btndel name="del" value="[ 删 除 ]" class="txtbox" LANGUAGE=javascript style="BACKGROUND-COLOR: white; BACKGROUND-REPEAT: no-repeat; CURSOR: hand;Border:0;font-size:9pt;color:black;" onclick="return confirmer()">
        </div>
      </td>
    </tr>
  </table>
  <table width="90%" border="0">
    <tr>
    <%
    if(curPageNum>1)
    {
    %>
      <td align='center'><a href="lessonDel.jsp?page=<%=curPageNum-1%>" >上页</a></td>
    <%
    }
    else
    {
    %>
      <td align='center'><font color="a9a9a9">上页</font></td>
    <%
    }
    %>

     <%
     if(nextstaRow<totalRowNum)
     {
     %>
      <td align='center'><a href="lessonDel.jsp?page=<%=curPageNum+1%>" >下页</a></td>
     <%
     }
     else
     {
     %>
      <td align='center'><font color="a9a9a9">下页</font></td>
     <%
     }
     %>

      <td align='center'> 当前页:<%=curPageNum%>/<%=totalPageNum%></td>
      <td align='center'> 共<%=totalPageNum%>页</td>
    </tr>
  </table>
  <input type="hidden" value="" name="flg">

</form>
<p>&nbsp; </p>
</body>
</html>


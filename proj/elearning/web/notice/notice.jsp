<%@ page contentType="text/html; charset=GB2312" %>
<jsp:useBean id="notbean" scope="session" class="com.htyz.notice.noticeBean" />
<%@ page import="com.htyz.notice.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.htyz.*"%>
<jsp:useBean id="beanDate" scope="session" class="com.htyz.util.BeanDateFormat" />
<SCRIPT LANGUAGE=javascript>

function mycheck()
{
  c=document.form2.flg.value
  if(c.length==0)
  {
    alert("����ѡ��һ����¼!")
    return false ;
  }
}
function setflg()
{
  document.form2.flg.value="ok"
}

function confirmer()
{
  c=document.form2.flg.value
  if(c.length==0)
  {
    alert("����ѡ��һ����¼!")
    return false ;
  }
 if(confirm("ȷ��ɾ��ô?"))
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
<p><div align="center"><font face="�����п�" size='5'>֪ͨ</font></div></p>

<form method="post" name="form2" action="noticeDetail.jsp">

  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
     <tr bgcolor="#CCCCCC" align="center"> 
      <td width="10%">��ѡť</td>
      <td width="10%">֪ͨ��</td>
      <td width="20%">&nbsp;&nbsp;֪ͨ����</td>
      <td width="20%">&nbsp;֪ͨʱ��</td>
      <td width="20%">&nbsp;��Чʱ��</td>
      <td width="20%">&nbsp;��ֹʱ��</td>
    </tr>
    <%
    int totalRowNum=0,pageRowNum=10,totalPageNum=1,curPageNum=1,nextPageNum=2;
    int startRow=0,endRow=1,nextstaRow=1;
    if(request.getParameter("page")!=null)
    {
      curPageNum=Integer.parseInt(request.getParameter("page"));
      nextPageNum=curPageNum+1 ;
    }
    %>
    <%
    try
    {
      String user_id = " ",notice_name=" ",notice_time = " ",valid_time = " ";
      String notice_id=" ",to_time=" ";
       Vector vect = new Vector();
      // notbean.connectdatabase();
       vect = notbean.getInitData();
       totalRowNum=vect.size();
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
      if(endRow>vect.size())
        endRow=vect.size();
      for(int i=startRow;i<endRow;i++)
      {
        noticeDO ntcDO = new noticeDO();
        ntcDO = (noticeDO)vect.elementAt(i);
        notice_id=ntcDO.getNotice_id();
        user_id= ntcDO.getUser_id();
        notice_name = ntcDO.getNotice_name();
        //notice_time = (ntcDO.getNotice_time()).substring(0,10);
        //valid_time =(ntcDO.getValid_time()).substring(0,10);
        //to_time =(ntcDO.getTo_time()).substring(0,10);
		notice_time=beanDate.format(ntcDO.getNotice_time(),14);
		valid_time=beanDate.format(ntcDO.getValid_time(),14);
		to_time=beanDate.format(ntcDO.getTo_time(),14);
    %>
    <tr >
      <td width="10%">
      <input onclick = "setflg()" type="radio" name="radiobutton" value="<%=notice_id%>">
      </td>
      <td width="10%" ><a href="noticeDetail.jsp?parm=<%=notice_id%>"><%=user_id%></a></td>
      <td width="20%"><%=notice_name%></td>
      <td width="20%"><%=notice_time%></td>
      <td width="20%"><%=valid_time%></td>
      <td width="20%"><%=to_time%></td>
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
          <%--<input type="submit" value="����" name="add" onClick = "">--%>
          <INPUT type="submit" id=btnadd name="add" value="[ ���� ]" class="txtbox" LANGUAGE=javascript style="BACKGROUND-COLOR: white; BACKGROUND-REPEAT: no-repeat; CURSOR: hand;Border:0;font-size:9pt;color:black;" onclick="">
        </div>
      </td>
      <td width="30%" height="29">
        <div align="center">
          <%--<input type="submit" value="�޸�" name="modify" onclick="return mycheck()" >--%>
          <INPUT type="submit" id=btnmodify name="modify" value="[ �޸� ]" class="txtbox" LANGUAGE=javascript style="BACKGROUND-COLOR: white; BACKGROUND-REPEAT: no-repeat; CURSOR: hand;Border:0;font-size:9pt;color:black;" onclick="return mycheck()">
        </div>
      </td>
      <td width="30%" height="29">
        <div align="center">
          <%--<input type="submit"  value="ɾ��" name="del" onclick = "return confirmer()">--%>
        <INPUT type="submit" id=btndel name="del" value="[ ɾ �� ]" class="txtbox" LANGUAGE=javascript style="BACKGROUND-COLOR: white; BACKGROUND-REPEAT: no-repeat; CURSOR: hand;Border:0;font-size:9pt;color:black;" onclick="return confirmer()">
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
      <td align='center'><a href="notice.jsp?page=<%=curPageNum-1%>" >��ҳ</a></td>
    <%
    }
    else
    {
    %>
      <td align='center'><font color="a9a9a9">��ҳ</font></td>
    <%
    }
    %>

     <%
     if(nextstaRow<totalRowNum)
     {
     %>
      <td align='center'><a href="notice.jsp?page=<%=curPageNum+1%>" >��ҳ</a></td>
     <%
     }
     else
     {
     %>
      <td align='center'><font color="a9a9a9">��ҳ</font></td>
     <%
     }
     %>

      <td align='center'> ��ǰҳ:<%=curPageNum%>/<%=totalPageNum%></td>
      <td align='center'> ��<%=totalPageNum%>ҳ</td>
    </tr>
  </table>
  <input type="hidden" value="" name="flg">

</form>
<p>&nbsp; </p>
</body>
</html>


<%@page contentType="text/html;charset=GB2312"%>
<%@ include file="open.jsp"%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="xbz.css" type="text/css">
</head>

<body bgcolor="#FFFFFF" text="#000000">
<table width="100%" border="0" cellspacing="0" cellpadding="1" bgcolor="#CCCCCC">
  <tr> 
    <td bgcolor="#ffffff"> 
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="xbz">
        <tr> 
          <td bgcolor="#eeeeee" class="xbz" height="20" width="18%">名字：sesson</td>
          <td bgcolor="#eeeeee" class="xbz" height="20" width="82%">&nbsp;</td>
        </tr>
        <tr> 
          <td bgcolor="#eeeeee" height="20" width="18%">日期：</td>
		  <%String sql="select left(convert(varchar,user_day,20),10) as jj,notes from tuser_daliy";
		  ResultSet rs=stmt.executeQuery(sql);
          if (rs.next()){
		  String vv=rs.getString("jj");
		  %>
          <td bgcolor="#eeeeee" height="20" width="82%"><%=vv%> 
          </td>
        </tr>
		<%}%>
        <tr> 
          <td bgcolor="#eeeeee" width="18%">内容： </td>
          <td bgcolor="#eeeeee" width="82%"> 
            <textarea name="textfield" cols="40" rows="4" readonly="true"><%=rs.getString("notes")%></textarea>
          </td>
        </tr>
        <tr> 
          <td bgcolor="#eeeeee" height="20" width="18%">&nbsp;</td>
          <td bgcolor="#eeeeee" height="20" width="82%">&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>

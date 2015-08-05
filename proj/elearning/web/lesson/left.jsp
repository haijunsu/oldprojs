<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../xbz.css" type="text/css">
</head>

<body bgcolor="#6699CC" text="#000000">

<table width="100%" border="0" cellspacing="1" cellpadding="1" class="xbz">
  <tr bgcolor="#6699CC"> 
    <td height="20" bgcolor="#FFFFFF"> 
      <div align="left"><img src="../images/pack_go.gif" width="16" height="14" ><a href="../class/tclass.jsp" target="mainFrame">课程的添加</a></div>
    </td>
  </tr>
  <tr bgcolor="#6699CC"> 
    <td height="20" align="center" bgcolor="#FFFFFF"> 
      <div align="left"><img src="../images/pack_go.gif" width="16" height="14"><a href="<%=request.getContextPath()%>/servlet/elearn.bean_ManagerRequireLession" target="mainFrame">必修课的指定</a></div>
    </td>
  </tr>
  <tr bgcolor="#6699CC"> 
    <td height="20" align="center" bgcolor="#FFFFFF"> 
      <div align="left"><img src="../images/pack_go.gif" width="16" height="14"><a href="lesson.jsp" target="mainFrame">章节的添加</a></div>
    </td>
  </tr>
  <tr bgcolor="#6699CC"> 
    <td height="20" align="center" bgcolor="#FFFFFF"> 
      <div align="left"><img src="../images/pack_go.gif" width="16" height="14"><a href="adminapply.jsp" target="mainFrame">用户申请课程批准</a></div>
    </td>
  </tr>
</table>
</body>
</html>

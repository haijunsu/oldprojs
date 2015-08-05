<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>系统管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../xbz.css" type="text/css">
</head>

<body bgcolor="#6699CC" text="#000000">
<table width="100%" border="0" cellspacing="0" cellpadding="1" height="20" bgcolor="#ffffff">
  <tr> 
    <td bgcolor="#6699CC"> 
      <table width="100%" border="0" cellspacing="1" cellpadding="1" class="xbz">
        <tr bgcolor="#6699CC"> 
          <td bgcolor="#FFFFFF" height="20"> 
            <div align="left"><img src="<%=request.getContextPath()%>/images/pack_go.gif" width="16" height="14"><a href="<%=request.getContextPath()%>/servlet/admin/codeManager?root=0013" target="mainFrame">课程分类管理</a></div>
          </td>
        </tr>
        <tr bgcolor="#6699CC"> 
          <td align="center" bgcolor="#FFFFFF" height="20"> 
            <div align="left"><img src="<%=request.getContextPath()%>/images/pack_go.gif" width="16" height="14"><a href="<%=request.getContextPath()%>/servlet/lessadmin/lessonManager" target="mainFrame">课程制作</a></div>
          </td>
        </tr>
        <tr bgcolor="#6699CC"> 
          <td align="center" bgcolor="#FFFFFF" height="20"> 
            <div align="left"><img src="<%=request.getContextPath()%>/images/pack_go.gif" width="16" height="14"><a href="<%=request.getContextPath()%>/servlet/lessadmin/lessonReqManager" target="mainFrame">必修课指定</a></div>
          </td>
        </tr>
		 <tr bgcolor="#6699CC"> 
          <td align="center" bgcolor="#FFFFFF" height="20"> 
            <div align="left"><img src="<%=request.getContextPath()%>/images/pack_go.gif" width="16" height="14"><a href="<%=request.getContextPath()%>/lessadmin/lessonDel.jsp" target="mainFrame">必修课管理</a></div>
          </td>
        </tr>
        <tr bgcolor="#6699CC"> 
          <td align="center" bgcolor="#FFFFFF" height="20"> 
            <div align="left"><img src="<%=request.getContextPath()%>/images/pack_go.gif" width="16" height="14"><a href="<%=request.getContextPath()%>/lessadmin/adminapply.jsp" target="mainFrame">申请课申请审批</a></div>
          </td>
        </tr>
        <tr bgcolor="#6699CC"> 
          <td align="center" bgcolor="#FFFFFF" height="20"> 
            <div align="left"><img src="<%=request.getContextPath()%>/images/pack_go.gif" width="16" height="14"><a href="<%=request.getContextPath()%>/servlet/exam/topicManager" target="mainFrame">题库管理</a></div>
          </td>
        </tr>
		<tr bgcolor="#6699CC"> 
          <td align="center" bgcolor="#FFFFFF" height="20"> 
            <div align="left"><img src="<%=request.getContextPath()%>/images/pack_go.gif" width="16" height="14"><a href="<%=request.getContextPath()%>/lessonmade/lessonmade.jsp" target="mainFrame">课件制作</a></div>
          </td>
        </tr>
		
      </table>
    </td>
  </tr>
</table>
</body>
</html>

<%@page contentType="text/html;charset=GB2312"%>
<%
//禁止Cache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<HTML>
<HEAD>
<TITLE>系统设置</TITLE>
<link href="../style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<%
java.util.Vector driverVector = (java.util.Vector)request.getAttribute("driverVector");
system.systemProfile profile = (system.systemProfile)request.getAttribute("profile");
String driver;
%>
<FORM id="setForm" name="setForm" method="post" action="systemSetup">
<INPUT type="hidden" name="action" id="action" value="update">
<TABLE  class="table003">
<TBODY>
  <TR>
  	<TD nowrap>数据库驱动程序:</TD>
  	<TD><SELECT id="driver" name="driver">
  		<%
  		for (int i=0; i<driverVector.size(); i++)
  		{
  			if (driverVector.elementAt(i).equals(profile.getDriver()))
  			{
  				out.println("<option selected value='" + driverVector.elementAt(i) + "'>" + driverVector.elementAt(i) +"</option>");
  			}
  			else
  			{
  				out.println("<option value='" + driverVector.elementAt(i) + "'>" + driverVector.elementAt(i) +"</option>");
  			}
  		}
  		%>
  	    </SELECT>
	</TD>
  </TR>
  <TR>
  	<TD nowrap>其它驱动程序:</TD>
  	<TD><INPUT type="text" name="customdriver" id="customdriver" value='<%=driverVector.contains(profile.getDriver())?"":profile.getDriver()%>'>
	</TD>
  </TR>
  <TR>
  	<TD nowrap>数据源名称:</TD>
  	<TD><INPUT type="text" name="datasource" id="datasource"  value="<%=profile.getDatasource()%>">
	</TD>
  </TR>
  <TR>
  	<TD nowrap>数据源URL前缀:</TD>
  	<TD><INPUT type="text" name="datasourceurl" id="datasourceurl"  value="<%=profile.getDatasourceurl()%>">
	</TD>
  </TR>
  <TR>
  	<TD nowrap>数据库名称:</TD>
  	<TD><INPUT type="text" name="database" id="database"  value="<%=profile.getDatabase()%>">
	</TD>
  </TR>
  <TR>
  	<TD nowrap>数据库模式名:</TD>
  	<TD><INPUT type="text" name="owner" id="owner"  value="<%=profile.getOwner()%>">
	</TD>
  </TR>
  <TR>
  	<TD nowrap>课程存档位置:</TD>
  	<TD><INPUT type="text" name="lessondir" id="lessondir"  value="<%=profile.getLessonDir()%>">
	</TD>
  </TR>
  <TR>
  	<TD nowrap>登录用户:</TD>
  	<TD><INPUT type="text" name="userid" id="userid"  value="<%=profile.getUserid()%>">
	</TD>
  </TR>
  <TR>
  	<TD nowrap>登录口令:</TD>
  	<TD><INPUT type="password" name="password" id="password"  value="<%=profile.getPassword()%>">
	</TD>
  </TR>
  <TR>
  	<TD nowrap>口令确认:</TD>
  	<TD><INPUT type="password" name="repassword" id="repassword"  value="<%=profile.getPassword()%>">
	</TD>
  </TR>
  <TR>
  	<TD nowrap><INPUT type="submit" name="submit" id="submit"  value="提交"></TD>
  	<TD><INPUT type="reset" name="reset" id="reset"  value="重置">
	</TD>
  </TR>
 </TBODY>
</TABLE>
</FORM>
</BODY>
</HTML>
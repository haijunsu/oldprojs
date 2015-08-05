<%@page contentType="text/html;charset=GB2312"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>&nbsp;登录到E-learning&nbsp;</title>
</head>
<style>
a:hover { color: #FF0000}
body { font-family: "宋体"; font-size: 9pt; text-decoration: none}
a { font-family: "宋体"; font-size: 9pt; text-decoration: none}
table { font-family: "宋体"; font-size: 9pt; text-decoration: none}
</style>
<script language="jscript" src="drag.js"></script>
<script>
function check_guest()
{
	var flag=ELS_login.is_guest.checked;
	if(flag==true)
	{
		ELS_login.userid.disabled=true;
		ELS_login.pass.disabled=true;
	}
	else
	{
		ELS_login.userid.disabled=false;
		ELS_login.pass.disabled=false;
	}
}
</script>
<body bgcolor=background onload=init() scroll=no>

<object id="closes" type="application/x-oleobject" classid="clsid:adb880a6-d8ff-11cf-9377-00aa003b7a11" width="0" height="0">
<param name="Command" value="Close">
</object>
<script>
function wqp()
{
    window.open(top.location,'RUNMIT_ELS','resizable=yes,scrollbars=auto,toolbar=no,location=0,menubar=no,status=yes,fullscreen=1');
    window.focus();
    document.closes.Click();
}
if(window.name != "RUNMIT_ELS")	wqp();
</script>
<%
String s_Msg;
s_Msg = (String)request.getAttribute("message");
if(s_Msg != null)
{
%>
<Font color="red"><%=s_Msg%></FONT> 
<%}%>
<form name="ELS_login" method=post action="/servlet/beanUserLogin">
    	<DIV id=login style="height: 223px;width: 400px;POSITION:absolute;z-index:0;" >
        <table border="0" width="400" height="223" cellspacing="0" cellpadding="0" style="border-left:2 solid #efefef;border-top:2 solid #efefef;border-right:2 solid #333333;border-bottom:2 solid #333333;background:menu">
          <tr>
            <td width="100%" height="20" bgcolor=activecaption style='cursor:hand' onmouseout=drag=0 onmouseover="dragObj=login; drag=1;"><font color=captiontext>&nbsp;登录E-learning&nbsp;</font></td><td bgcolor=activecaption><img src="close_app.gif" onclick="javascript:self.close();"></td>
          </tr>
          <tr>
            <td width="100%" height="70" valign="bottom" align="right" colspan=2><img src="logo6.gif" width=100% height=100%></td>
          </tr>
          <tr>
            <td width="100%" height="3" bgcolor=activecaption colspan=2></td>
          </tr>
          <tr>
            <td width="100%" height="130" colspan="2" valign="top" align="left">
              <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="100%" height="100%">
               	    <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0">
                      <tr>
                        <td nowrap height=40 align=left>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名：&nbsp</td>
                        <td width="100%" >&nbsp;<input type=text id="userid3" name="userid" style="width=80%">&nbsp;</td>
                      </tr>
                      <tr>
                        <td nowrap height=20 align=left>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;码：&nbsp;</td>
                        <td width="100%">&nbsp;<input type=password id="pass4" name="pass" style="width=80%">&nbsp;</td>
                      </tr>
                      <tr>
                        <td align=right height=25>&nbsp;</td>
                        <td width="100%">&nbsp;<input type=checkbox name=is_guest onclick="javascrip:check_guest();" value=1>&nbsp;以过客身份登录&nbsp;</td>
                      </tr>
                      <tr>
                        <td width="100%" height=45 colspan=2 align=right>
                          <input type=submit value="&nbsp;&nbsp;确定&nbsp;&nbsp;">&nbsp;&nbsp;&nbsp;&nbsp;
                          <input type=button value="&nbsp;&nbsp;注册&nbsp;&nbsp;" onClick=window.open("servlet/system.beanUserProfiles?action=apply","_blank","top=0,left=0,width=610,height=350,status=no")>&nbsp;&nbsp;&nbsp;
                          <input type=button value="&nbsp;&nbsp;退出&nbsp;&nbsp;" onclick="javascript:self.close();">&nbsp;&nbsp;&nbsp;
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        </div>
        
<input type=hidden name=back_url value="/elearning/login.jsp">
<input type=hidden name=is_ELS value="yes">
</form>
</body>
</html>
<script>
login.style.left=document.body.clientWidth/2-login.clientWidth/2
login.style.top=document.body.clientHeight/2-login.clientHeight/2-50
</script>

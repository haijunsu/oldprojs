<%@page contentType="text/html;charset=GB2312"%>
<%@page import="system.*"%>
<html>
<head>
<title>用户属性</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css" type="text/css">
</head>
<Script language="javascript">
<!--
function check(){
if (document.userForm.userid.value.length==0){
alert("用户id不能为空！！");
document.userForm.userid.focus();
return;
}

if (document.userForm.username.value.length==0){
alert("用户姓名不能为空！！");
document.userFourm.username.focus();
return;
}
c2 = document.userForm.birthday.value;
if (chkDate(c2)==0)
{
  alert("出生时间无效！")
  return 
}
document.userForm.submit();
}

function chkDate(str)
{
    var year, month, day;
    if(str.length!=10)
    {
      return 0;
    }
    year = str.substring(0,4)
    month=str.substring(5,7)
    day=str.substring(8)
    if(str.substring(4,5)!="-")
    {
      return 0;
    }
    if(str.substring(7,8)!="-")
    {
      return 0;
    }
    if (fucCheckInt(year+month+day)==0)
    {
        return 0;
    }

    if(year>3000)
    {
      return 0;

    }
    //检查月
    if(month>12||month==0)
    {
      return 0;
    }
    //检查日
    if(day>31||day==0)
    {
      return 0;
    }
    //小月时对日检查
    if (month == 4 || month == 6 || month == 9 || month == 11)
    {
        if (day > 30||day==0)
        {
            return 0;
        }
    }
    //闰年闰月检查
    if(month==2)
    {
      if ((year%4 == 0 && year%100 != 0)||(year%400 == 0))
      {
          //是闰年
          if (day > 29||day==0)
          {
              return 0;
          }
      }
      else
      {
          //不是闰年
          if (day > 28||day==0)
          {
              return 0;
          }
      }
    }
    return 1;
}

-->
</Script>
<body>

<jsp:useBean id="beanQueryCodes" scope="request" class="com.htyz.beanQueryCodes"/>
<jsp:useBean id="beanElearnTools" scope="request" class="com.htyz.beanElearnTools"/>
<%
beanUserInfo beanUserInfo = (beanUserInfo)request.getAttribute("beanUserInfo");
String s_msg, s_action, s_Title, s_checked1, s_checked2, s_btnSubmit;
s_msg = (String)request.getAttribute("message");
s_action = (String)request.getAttribute("action");
s_Title =  (String)request.getAttribute("title");
%>

<%
if(s_msg != null)
{
%>
<Font color="red"><%=s_msg%></Font>
<%}
if(s_action.equals("changepass")||s_action.equals("updatepass"))
{
%>
<FORM id="userForm" name="userForm" method="Get" action="<%=request.getContextPath()%>/servlet/admin/userProfiles">
<input type="hidden" id="action" name="action" value="<%=s_action%>">
   
  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="600">
    <tr>
		
      <td height="20" nowrap> 用户旧密码 :</td>
		
      <td height="20">
<input class="input1"  type='password' id='oldpass' name='oldpass' size='20' maxlength='20' value='<%=beanUserInfo.getPassword()%>'> </td>
	</tr>
	<tr>
		
      <td height="20" nowrap> 用户新密码: </td>
		
      <td height="20">
<input class="input1" type='password' id='pass' name='pass' size='20' maxlength='20' value='<%=beanUserInfo.getPassword()%>'> </td>
	</tr>
	<tr>
		
      <td height="20" nowrap> 再次确认密码 :</td>
		
      <td height="20">
<input class="input1" type='password' id='repass' name='repass' size='20' maxlength='20' value=''> </td>
	</tr>
	<tr bordercolor="#CCCCCC">
		<td height="20" colspan='2'>
		<input class="input2" type='submit' id='btnsubmit' name='btnsublit' value='提交'> </td>
	</tr>
</table>
</FORM>
<%
}
else
{
%>
<FORM id="userForm" name="userForm" method="post" action="<%=request.getContextPath()%>/servlet/admin/userProfiles">
<input type="hidden" id="action" name="action" value="<%=s_action%>">
  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" class="xbz" width="600">
    <tr>
		<td height="20" nowrap> 用户ID/登录号 </td>
		<%
		if(s_action.equals("add"))
		{
		%>
			<td height="20"><input class="input1" type='text' id='userid' name='userid' size='30' maxlength='20' value='<%=beanUserInfo.getUserid()%>'> </td>
		<%
		}
		else
		{
		%>
			<td><input name="userid" class="input1" value="<%=beanUserInfo.getUserid()%>" size="30"></td>
		<%}%>
	</tr>
	<tr>
		<td height="20" nowrap> 姓名 </td>
		<td height="20"><input class="input1" type='text' id='username' name='username' size='30' maxlength='30' value='<%=beanUserInfo.getUserName()%>'></td>
	</tr>
	<tr>
		<td height="20" nowrap> 出生日期 </td>
		<td height="20"><input class="input1" type='text' id='birthday' name='birthday' size='15' maxlength='10' value='<%=beanUserInfo.getBirthday()%>'>格式：YYYY-MM-DD</td>
	</tr>
	<%
	if(s_action.equals("apply")||s_action.equals("add"))
	{
	%>
	<tr>
		<td height="20" nowrap> 用户密码 </td>
		<td height="20"><input class="input1" type='password' id='pass' name='pass' size='20' maxlength='20' value='<%=beanUserInfo.getPassword()%>'> </td>
	</tr>
	<tr>
		<td height="20" nowrap> 确认密码 </td>
		<td height="20"><input class="input1" type='password' id='repass' name='repass' size='20' maxlength='20' value=''> </td>
	</tr>
	<%
	}
	else
	{
	%>
	<tr>
		<td height="20" colspan="2">
		<input class="input2" type='button' id='changepass' name='changepass' value='修改密码' onClick="window.location.href='system.beanUserProfiles?action=changepass'"> </td>
	</tr>
	<%
	}
	%>
	<tr>
		<td height="20" nowrap> 性别 </td>
		<%
		s_checked1 = "checked";
		s_checked2 = "";
		if(beanUserInfo.getSex().equals("2"))
		{
			s_checked1 = "";
			s_checked2 = "checked";
		}
		%>
		<td height="20"><input class="input1" type='radio' id='sex' name='sex' <%=s_checked1%> value='1'>男  <input type='radio' id='sex' name='sex' <%=s_checked2%> value='2'>女
		</td>
	</tr>
	<tr>
		<td height="21" nowrap> 邮件地址 </td>
		<td height="21"><input class="input1" type='text' id='email' name='email' size='50' maxlength='50' value='<%=beanUserInfo.getEmail()%>'></td>
	</tr>
	<tr>
		<td height="20" nowrap> 电话 </td>
		<td height="20"><input class="input1" type='text' id='phone' name='phone' size='50' maxlength='20' value='<%=beanUserInfo.getPhone()%>'></td>
	</tr>
	<tr>
		<td height="20" nowrap> 单位 </td>
		<td height="20"><input class="input1" type='text' id='company' name='company' size='50' maxlength='50' value='<%=beanUserInfo.getCompany()%>'></td>
	</tr>
	<tr>
		<td height="20" nowrap> 地区 </td>
		<td height="20"><input class="input1" type='text' id='area' name='area' size='50' maxlength='20' value='<%=beanUserInfo.getArea()%>'></td>
	</tr>
	<tr>
		<td height="20" nowrap> 签名 </td>
		<td height="20"><textarea class="testarea1" id='signature' name='signature' rows='5' cols='50'><%=beanUserInfo.getSignature()%></textarea></td>
	</tr>
	<tr>
		
      <td height="20" colspan="2" align="center"> 
        <%
			s_btnSubmit = "申请";
			if(s_action.equals("update"))
			{
				s_btnSubmit = "修改";
			}
			%>
        <input class="input2" type="submit" id='btnsubmit' name='btnsubmit' value="<%=s_btnSubmit%>" onclick="check()">
        <input class="input2" type="reset" id='btnreset' name='btnreset' value="重置">
		</td>
	</tr>
</table>
</FORM>
<%}%>
</body>
</html>
	
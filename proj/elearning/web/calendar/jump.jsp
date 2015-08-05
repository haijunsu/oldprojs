<%@ page contentType="text/html;charset=GB2312"%>
<jsp:useBean id="beanGetdata" scope="session" class="com.htyz.beanGetdata"/>
<%
String action=request.getParameter("action");
String year=request.getParameter("e_year");
String month=request.getParameter("e_month");
String date=request.getParameter("e_date");
//System.out.print("jsp="+syear);
//out.println("the year==="+s_year);
//out.println("the action==="+action);
String daily_id,title,content,date1,date2;
if(action==null||action.equals(""))
	action="save";

if(beanGetdata.getRowCount()==0 || action.equals("add"))
{
	daily_id="";
	title="";
	content="";
	date1="";
	date2="";
}else{
	daily_id=beanGetdata.getFieldValue("daily_id",0);
	title=beanGetdata.getFieldValue("title",0);
	content=beanGetdata.getFieldValue("notes",0);
	date1=beanGetdata.getFieldValue("wake_date",0);
	date2=beanGetdata.getFieldValue("end_date",0);
}
%>
<html>
<head>
<title>Untitled Document</title>
<link href="../style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<SCRIPT LANGUAGE=javascript>
 function mycheck()
{
 c1=document.form1.title.value;
    if(c1.length==0){
	alert("请输入提醒标题！");
	return false
	}
c2=document.form1.content.value;
    if(c2.length==0){
	alert("请输入提醒内容！");
	return false
	}
 c3 = document.form1.date1.value;
    if (chkDate(c3)==0)
    {
      alert("提醒开始日期无效！");
      return false
    }
c4 = document.form1.date2.value;
    if (chkDate(c4)==0)
    {
      alert("提醒结束日期无效！");
      return false
    }	
	
}
function fucCheckInt(NUM)
{
    var i,j,strTemp;

    strTemp="0123456789";
    if ( NUM.length== 0)
            return 1
    for (i=0;i<NUM.length;i++)
    {
            j=strTemp.indexOf(NUM.charAt(i));
            if (j==-1)
            {
            //说明有字符不是数字
              return 0;
            }
    }
    //说明是数字
    return 1;
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
    if(parseInt(month)>12||month==0)
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
	<%if(action.equals("add")){%>
	document.form1.action="save";
	<%}
	if(action.equals("mod")){%>
	document.form1.action="update";
	<%}%>
//-->
</script>
<body bgcolor="#FFFFFF" text="#000000">
<form name="form1" method="post" action="<%=request.getContextPath()%>/servlet/calendar.beanCalendar">
<input type="hidden" name="action" value="<%=action%>">

<input type="hidden" name="daily_id" value="<%=daily_id%>">

  <table border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
    <tr bgcolor="#cccccc"> 
      <td height="20" colspan="2"><%=year%>年<%=month%>月<%=date%>日 
        <input  type="hidden"  name="year"  value=<%=request.getParameter("year")%>>
        <input  type="hidden"  name="month" value=<%=request.getParameter("month")%>>
        <input  type="hidden"  name="date"  value=<%=request.getParameter("date")%>>
      </td>
    </tr>
    <tr> 
      <td height="20" width="22%">事件标题：</td>
      <td height="20" width="78%"> 

        <input class="input1" type="text" name="title" value="<%=title%>" >
      </td>
    </tr>
    <tr> 
      <td height="20" width="22%">事件内容：</td>
      <td height="20" width="78%"> 
        <textarea name="content" cols="30" rows="5"><%=content%></textarea>
      </td>
    </tr>
    <tr> 
      <td height="20">提醒开始日期：</td>
      <td height="20"> 
        <input class="input1" type="text" name="date1" value="<%=date1%>">
        格式：yyyy-mm-dd</td>
    </tr>
    <tr> 
      <td height="20">提醒结束日期：</td>
      <td height="20">
        <input class="input1" type="text" name="date2" value="<%=date2%>">
        格式：yyyy-mm-dd </td>
    </tr>
    <tr> 
      <td height="20" colspan="2"> 
        <input class="input2" type="submit" name="Submit" value="提交" onclick = "return mycheck()">
        <input class="input2" type="submit" name="Submit2" value="取消">
      </td>
    </tr>
  </table>

</form>
</body>
</html>

<%@ page contentType="text/html; charset=GB2312" %>
<jsp:useBean id="notbean" scope="session" class="com.htyz.notice.noticeBean" />
<%@ page import="com.htyz.notice.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.htyz.*"%>
<jsp:useBean id="beanDate" scope="session" class="com.htyz.util.BeanDateFormat" />
<SCRIPT LANGUAGE=javascript>
function mycheck()
{
    c = (document.form1.tzm.value);
    if (c.length == 0)
    {
      alert("必须输入通知名称！")
      return false
    }
    c1 = (document.form1.tznr.value);
    if (c1.length == 0)
    {
      alert("必须输入通知内容！")
      return false
    }

    c2 = document.form1.sxsj.value;
    if (chkDate(c2)==0)
    {
      alert("生效时间无效！")
      return false
    }
    c3 = document.form1.jzsj.value;
    if (chkDate(c3)==0)
    {
      alert("截止时间无效！")
      return false
    }
     return true
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


<!--

  function setva()
  {
	//var strj=document.form1.select1
    var str=document.form1.jsc.value

	with(document.form1.yhh)
	{
		for(loop_index=0;loop_index<length;loop_index++)
		{
			if (options[loop_index].selected)
			{
				//are.value=options[loop_index].text
                          if(str.length==0)
                            str=str+options[loop_index].text
                            //str=str+options[loop_index].value
                          else
                            str=str+","+options[loop_index].text
                            //str=str+","+options[loop_index].value
			}

		}
	}
        document.form1.jsc.value=str
  }

//-->

</SCRIPT>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="<%=request.getContextPath()%>/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<%
    String user_id = "",notice_name="",notice_time = "",valid_time = "";
    String notice_id="",notice_content="",to_time="";
    String sysdate="",flg="";
	int len=0;
    if (request.getParameter("add") != null)
    {
      notice_id=notbean.getMaxNotice_id();
	  len=notice_id.length();
	  for(int m=0;m<6-len;m++)
	  {
		notice_id="0"+notice_id;
	  }
      sysdate=notbean.getSysDate();
	  flg="add";
      //notice_time=sysdate;
      //valid_time=sysdate;
      //to_time=sysdate;

	  notice_time=beanDate.format(sysdate,14);
	  valid_time=notice_time;
	  to_time=notice_time;
    }
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
        String bh= request.getParameter("radiobutton");
        bz = notbean.DeleteNoticeData(bh);
        if(bz)
        {
          %>
          <jsp:forward page="notice.jsp" />
          <%
        }
        else
        {
          out.println("删除失败!通知号为: "+bh);
          return ;
        }
      }

    }
    else if(request.getParameter("modify") != null||request.getParameter("parm") != null)
    {
      if (request.getParameter("modify") != null&&request.getParameter("radiobutton") == null)
      {
        out.println("选择一条记录后再修改");
        return ;
      }
      else
      {
        noticeDO ntcdo = new noticeDO();
        String bh= request.getParameter("radiobutton");
        if(bh==null)
          bh= request.getParameter("parm");
        ntcdo=notbean.getDataByNo(bh);
        notice_id=ntcdo.getNotice_id();
        user_id = (ntcdo.getUser_id()).trim();
        notice_name = ntcdo.getNotice_name();
        notice_content = ntcdo.getNotice_content();
        //notice_time=(ntcdo.getNotice_time()).substring(0,10);
        //valid_time=(ntcdo.getValid_time()).substring(0,10);
        //to_time=(ntcdo.getTo_time()).substring(0,10);
		notice_time=beanDate.format(ntcdo.getNotice_time(),14);
		valid_time=beanDate.format(ntcdo.getValid_time(),14);
		to_time=beanDate.format(ntcdo.getTo_time(),14);
      }
    }
	
%>


<p><font face="华文行楷" size='6'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;通知</font></p>
<form method="post" name="form1" action="noticeUpdate.jsp">
  <table bgcolor ="#eeeeee" width="85%" border="0">
    <tr>
      <td color="#FFFFFF" width="15%"  >通知号</td>
      <td width="30%">
         <input readonly type="text" name="tzh" value="<%=notice_id%>">
      </td>
      <td width="15%">通知名</td>
      <td width="35%">
        <input maxlength=32 type="text" name="tzm" value="<%=notice_name%>">
      </td>
    </tr>
    <tr>
      <td width="15%" color="#0000FF" >通知时间</td>
      <td width="35%">
        <input readonly type="text" name="tzsj" value="<%=notice_time%>">
      </td>
      <td width="15%" color="#0000FF" >生效时间</td>
      <td width="35%">
        <input type="text" name="sxsj" value="<%=valid_time%>">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;yyyy-mm-dd
      </td>
    </tr>
    <tr>
      <td width="15%" color="#0000FF" >接收人</td>
      <td width="40%">
        <select name="yhh" multiple size='2' onblur="setva()">
          <%
          Vector vec=new Vector();
          Vector ve=new Vector();
          userDO urdo = null;
          groupDO grdo = null;
          vec = notbean.getuserData();
          ve = notbean.getGroupData();
          session.setAttribute("user_name",ve);
          for(int i=0;i<vec.size();i++)
          {
            urdo = new userDO();
            urdo = (userDO)vec.elementAt(i);
            out.println("<option  value="+urdo.getUser_id()+">"+urdo.getUser_name()+"</option>");
          }
          for(int m=0;m<ve.size();m++)
          {
            grdo = new groupDO();
            grdo = (groupDO)ve.elementAt(m);
            out.println("<option  value="+grdo.getGroup_id()+">"+grdo.getGroup_name()+"</option>");
          }
          %>
        </select>
      </td>
      <td width="15%" color="#0000FF" >截止时间</td>
      <td width="15%">
        <input type="text" name="jzsj" value="<%=to_time%>">
      </td>
    </tr>

  </table>
  <table bgcolor ="#eeeeee" width="85%" border="0">
    <tr>
      <td width="15%" color="#0000FF" ></td>
      <td width="85%">
         <input type="text" name="jsc" value="<%=user_id%>" size='62'>
      </td>
    </tr>
</table>
 <table bgcolor ="#eeeeee" width="85%" border="0">
    <tr>
      <td width="15%" color="#0000FF" >通知内容</td>
      <td width="85%">
        <textarea  rows="13" name="tznr" cols="60"  ><%=notice_content%></textarea>
      </td>
    </tr>
</table>
<%
  if(request.getParameter("parm")==null)
  {
%>
    <table bgcolor ="#ffffff" width="85%" border="0">
      <tr>
        <td>
          <input type="submit" name="save" value="保存" onclick = "return mycheck()">
        </td>
      </tr>
	  <input type="hidden" name="flg" value="<%=flg%>" >
    </table>

<%
  }
%>
</form>

<p>&nbsp; </p>
</body>
</html>


<%@ page contentType="text/html; charset=gb2312" %>
<jsp:useBean id="notbean" scope="session" class="com.htyz.notice.noticeBean" />
<%@ page import="com.htyz.notice.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.htyz.*"%>
<jsp:useBean id="beanTools" scope="session" class="com.htyz.beanElearnTools" />
<jsp:useBean id="beanDate" scope="session" class="com.htyz.util.BeanDateFormat" />
<html>
<head>
<title>
hello!
</title>
</head>
<body bgcolor="#fbf0c8">
<%
try
{
  if (request.getParameter("save") != null)
  {
    boolean bflg = false;
    String user_id="",user_name="",group_id="",group_name="";
	String notice_time="",valid_time="",to_time="";
    Vector ve=new Vector();
    groupDO grDO=null;
	user_name=notbean.charchange(request.getParameter("jsc"));
    user_id=request.getParameter("jsc");
    //user_name=user_id;
    //System.out.println("fist user_name is=="+user_name);
    ve=(Vector)session.getAttribute("user_name");
    for(int i=0;i<ve.size();i++)
    {
      grDO=(groupDO)ve.elementAt(i);
      group_id=grDO.getGroup_id();
      group_name=grDO.getGroup_name();
      //user_name=user_name.replaceAll(group_name,group_id);
	  user_name=beanTools.strReplace(user_name,group_name,group_id);
     
    }
   
    noticeDO ntcDO = new noticeDO();
    ntcDO.setNotice_id(request.getParameter("tzh"));
    ntcDO.setUser_id(user_id);
    ntcDO.setNotice_name(notbean.charchange(request.getParameter("tzm")));
    ntcDO.setNotice_content(notbean.charchange(request.getParameter("tznr")));
    //ntcDO.setNotice_name(request.getParameter("tzm"));
   // ntcDO.setNotice_content(request.getParameter("tznr"));
    notice_time=request.getParameter("tzsj");
	valid_time= request.getParameter("sxsj");
	to_time= request.getParameter("jzsj");
	
 	notice_time=beanDate.parseDBDate(notice_time.substring(0,4),notice_time.substring(5,7),notice_time.substring(8));
	valid_time=beanDate.parseDBDate(valid_time.substring(0,4),valid_time.substring(5,7),valid_time.substring(8));
	to_time=beanDate.parseDBDate(to_time.substring(0,4),to_time.substring(5,7),to_time.substring(8));
	
    ntcDO.setNotice_time(notice_time);
    ntcDO.setValid_time(valid_time);
    ntcDO.setTo_time(to_time);
    ntcDO.setUser_name(user_name);
	if(request.getParameter("flg").equals("add"))
		bflg = notbean.InsertNoticeData(ntcDO);
	else
		bflg = notbean.SaveNoticeData(ntcDO);
    if(bflg)
    {
    %>
     <jsp:forward page="notice.jsp" />
    <%
    }
    else
    {
      out.println("±£´æÊ§°Ü!");
    }
  }

}
catch(Exception e)
{
e.printStackTrace();
}
%>
<p></p>
<h1>
</h1>

</body>
</html>

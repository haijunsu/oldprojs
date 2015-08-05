<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.htyz.notice.*"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>
notice
</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../3.css" type="text/css">
<link href="../style.css" rel="stylesheet" type="text/css">
</head>
<jsp:useBean id="notbean" scope="session" class="com.htyz.notice.noticeBean" />
<jsp:setProperty name="noticeBeanId" property="*" />
<%
String user_id=(String)session.getAttribute("userid");
String group_id=(String)session.getAttribute("groupid");
if (user_id==null) user_id="invaliduser";
if (group_id==null) group_id="invalidgroupid";

%>
<body text="#000000" bgcolor="#FFFFFF">
<br>
<%
    try
    {
      String notice_name=" ",to_time = " ",valid_time = " ",notice_conent="";
       Vector vect = new Vector();
       //notbean.connectdatabase();
       vect = notbean.getDataByUser(user_id,group_id);
       //System.out.println("the size is ==="+vect.size());
	   if(vect.size()==0)
       {
         out.println("该用户无通知！");
         return ;

       }
      for(int i=0;i<vect.size();i++)
      {
        noticeDO ntcDO = new noticeDO();
        ntcDO = (noticeDO)vect.elementAt(i);
        notice_name=ntcDO.getNotice_name();
        valid_time =(ntcDO.getValid_time()).substring(0,10);
        to_time =(ntcDO.getTo_time()).substring(0,10);
        notice_conent=ntcDO.getNotice_content();
 %>
        <table width="98%" border="0" cellspacing="0" cellpadding="0" height="20" bgcolor="#ffffff" align="center" class="da">
          <tr>
           <td bgcolor="#666666">
             <table width="100%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                <td bgcolor="#CCCCCC" class="da" height="20">
                  <div align="center"><b><%=notice_name%></b></div>
                  有效日期:&nbsp;<%=valid_time%>&nbsp;至&nbsp;<%=to_time%>
                </td>
               </tr>
               <tr>
                <td bgcolor="#eeeeee"  height="20" class="xbz">
				 <textarea  rows="13" name="tznr" cols="80" style="border-style: solid; border-width: 0"><%=notice_conent%></textarea>
                </td>
               </tr>
             </table>
           </td>
         </tr>
      </table>
  <%
      }
    }
    catch(Exception e)
   {
      e.printStackTrace();
   }
  %>

</body>
</html>

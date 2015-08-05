<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.htyz.notice.*"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="beanDate" scope="session" class="com.htyz.util.BeanDateFormat" />

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

<body text="#000000" bgcolor="#FFFFFF">
<br>
<%
   String notice_name=" ",to_time = " ",valid_time = " ",notice_conent="";
	try
    {
      // notbean.connectdatabase();
       noticeDO ntcDO = new noticeDO();
       String  bh= request.getParameter("parm");
	   //out.println("bh==="+bh);
       ntcDO=notbean.getDataByNo(bh);
	   notice_name=ntcDO.getNotice_name();
       //valid_time =(ntcDO.getValid_time()).substring(0,10);
       //to_time =(ntcDO.getTo_time()).substring(0,10);

	   valid_time=beanDate.format(ntcDO.getValid_time(),14);
	   to_time=beanDate.format(ntcDO.getTo_time(),14);
       notice_conent=ntcDO.getNotice_content();
	 }
    catch(Exception e)
    {
      e.printStackTrace();
    }
      
       
 %>
        <table width="98%" border="0" cellspacing="0" cellpadding="0" height="50" bgcolor="#ffffff" align="center" class="da">
          <tr>
           <td bgcolor="#666666">
             <table width="100%" border="0" cellpadding="0" cellspacing="0">
               <tr>
                <td bgcolor="#CCCCCC" class="da" height="20">
                  <div align="center"><b><%=notice_name%></b></div>
                 
                </td>
               </tr>
			   <tr ><td  bgcolor="#eeeeee" height="20"> 有效日期:&nbsp;<%=valid_time%>&nbsp;至&nbsp;<%=to_time%> </td>
			   </tr>
			  
               <tr >
                <td bgcolor="#eeeeee"  height="200" class="xbz" valign="top">
				<%=notice_conent%>
                </td>
               </tr>
             </table>
           </td>
         </tr>
      </table>
	  <p><p>
	点击<a href='noticefront.jsp'><font color="red">这里</font></a>返回
</body>
</html>

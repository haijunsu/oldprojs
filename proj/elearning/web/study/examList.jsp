<%@page contentType="text/html;charset=GB2312"%>
<%@page session="true"%>
<jsp:useBean id="bgd" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="bgdclass" scope="request" class="com.htyz.beanGetdata"/>
<jsp:useBean id="eds" scope="request" class="com.htyz.util.BeanDateFormat"/>
<%
//��ֹCache.
   response.setHeader("Pragma","No-Cache");
   response.setHeader("Cache-Control","No-Cache");
   response.setDateHeader("Expires", 0);
%>
<HTML>
<HEAD>
<TITLE>�ɼ���</TITLE>
<link href="../style.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
  <%bgd.executeSelect("select c.class_id AS class_id,c.class_name AS class_name,c.pass_stander as pass_stander,u.test_date AS test_date,u.test_mark AS test_mark from t_result u,t_class c where u.user_id='"+session.getAttribute("userid")+"' and c.class_id=u.class_id order by class_name,test_date desc");
%>
<%@ include file="/study/lessonTest.jsp" %>
<table class=table003 border="0" width="100%" bgcolor="#FF9966">
  <TR> 
    <TD height="20"><B><font color="#FFFFFF">�ҵĿ��Կ�Ŀ</font></B><font color="#FFFFFF">================(�����μӹ�<%=bgd.getRowCount()%>�Ŵογ̿���)</font></TD>
  </TR>
</table>
<TABLE class=table002 border="1" cellspacing="0" cellpadding="0" align="center" bordercolorlight="#000000" bordercolordark="#FFFFFF" width="100%">
  <TR bgcolor="#CCCCCC"> 
    <TD height="20" nowrap><div align="center"><strong>���ԵĿγ�</strong></div></TD>
    <TD nowrap><div align="center"><strong>����ʱ��</strong></div></TD>
    <TD nowrap><div align="center"><strong>���Գɼ�</strong></div></TD>
    <TD nowrap><div align="center"><strong>ͨ����׼</strong></div></TD>
    <TD nowrap><div align="center"><strong>ͨ��</strong></div></TD>
    <TD nowrap><div align="center"><strong>���ڿ���</strong></div></TD>
  </TR>
  <%for(int i=0;i<bgd.getRowCount();i++){ %>
  <TR bgcolor="#FFFFFF"> 
    <TD height="20" nowrap><%=bgd.getFieldValue("class_name",i)%> </TD>
    <TD nowrap><%=eds.format(bgd.getFieldValue("test_date",i),3)%></TD>
    <TD nowrap><%=bgd.getFieldValue("test_mark",i).equals("-1")?"û���":bgd.getFieldValue("test_mark",i)%> </TD>
    <TD nowrap> <%=bgd.getFieldValue("pass_stander",i)%></TD>
    <TD nowrap> <%=Integer.parseInt(bgd.getFieldValue("test_mark",i))>=Integer.parseInt(bgd.getFieldValue("pass_stander",i))?"ͨ��":"δͨ��"%></TD>
    <TD nowrap><a href="../exam/lessonExam.jsp?classid=<%=bgd.getFieldValue("class_id",i)%>" target="test">���ھͿ���</a></TD>
  </TR>
  <%}%>
</TABLE>
</BODY>
</HTML>
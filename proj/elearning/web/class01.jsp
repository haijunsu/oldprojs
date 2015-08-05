<%@ page contentType="text/html;charset=GB2312" %>
<jsp:useBean id="list" scope="session" class="com.elearning.ListBean" />
  <jsp:setProperty name="list" property="jdbcDriver" value="sun.jdbc.odbc.JdbcOdbcDriver" />
  <jsp:setProperty name="list" property="jdbcURL" value="jdbc:odbc:elearning" />
  <jsp:setProperty name="list" property="jdbcUser" value="sa" />
  <jsp:setProperty name="list" property="jdbcPassword" value="" />

  <jsp:setProperty name="list" property="tableName" value="tclass" />
  <jsp:setProperty name="list" property="labelCols" value="Class_name,set_time,Class_id" />
  <jsp:setProperty name="list" property="urlCol" value="1" />
  <jsp:setProperty name="list" property="goUrl" value="/elearning/selectclass.jsp" />
  <jsp:setProperty name="list" property="orderStyle" value="Order BY set_time" />
  <jsp:setProperty name="list" property="whereClause" value=" where class_status='1'" />
  <jsp:setProperty name="list" property="tableStyle" value="da" />

  <jsp:setProperty name="list" property="listPage" value="selectclass.jsp" />
  <jsp:setProperty name="list" property="leafTarget" value="rhs" />
  <jsp:setProperty name="list" property="rec_per" value="5" />
  <jsp:setProperty name="list" property="topnum" value="5" />
  <jsp:setProperty name="list" property="toporder" value=" order by set_time " />
  <jsp:setProperty name="list" property="*" />
<%
list.setGopage_On("0");
list.setPages_On(0);
list.setNext_On("0");

if(request.getParameter("cur_page")!=null)
	{
		list.setcur_page(Integer.parseInt(request.getParameter("cur_page")));
}
list.setDateColNum(2);
out.print(list.ListTable());
%>
 

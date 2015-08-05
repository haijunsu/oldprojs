<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page 
language="java"
contentType="text/html; charset=GB2312"
pageEncoding="GB2312"
%>
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%>


<table width="442" height="100" border="0" align="center" cellpadding="0" cellspacing="0" id="table1">

<html:form action="/searchbbsFirst">
	<html:hidden property="flag" value="search"/>
	<html:hidden property="page" />
	<html:hidden property="searchid" />
	<html:hidden property="queryid" />
	<html:hidden property="queryiddis" />
	<html:hidden property="actiondo" />
	<html:hidden property="nowpage" />
	<html:hidden property="nowoncepage" />
	<%String s_link="";%>
	<%String pname="";%>
	<%String pname2="";%>
	<%String s_img="";%>
	<%String s_no="";%>
			<logic:equal name="SearchBBSForm" property="page" value="2">
			</logic:equal>
			<logic:notPresent name="SearchBBSForm" property="data">
				<TR><TD>
				<center>无通告</center>
				</TD></TR>
			</logic:notPresent>
			<logic:present name="SearchBBSForm" property="data">
				
					<tr> 
				    	<th width="85" rowspan="16"> 
				    		<font color="#FF0000">公</font>
				    		<p><font color="#FF0000">告</font></p>
				    	</th>
				    	<td></td>
					</tr>
					<logic:iterate id="row" name="SearchBBSForm" property="data" indexId="indexrow">
					<TR>
						<%pname = "data[" + indexrow + "]";%>
						<logic:iterate id="col" name="SearchBBSForm" property="<%=pname%>" indexId="indexcol">
						<%s_no = String.valueOf(indexcol);%>
						<% if (s_no.equals("0")) {%>
						<%} else {%>
							<% if (s_no.equals("1")) {%>
								<td valign="top"><font size="2">
									<%s_link="datakey[" + indexrow + "]";%>
									<A href='<%=request.getContextPath()%>/<bean:write name="SearchBBSForm" property='<%=s_link%>'/>&first=1' target="_blank">
										◆ <%=col%>
									</A>
								</font></TD>
							<%} else {%>
								<% if (s_no.equals("3")) {%>
								<%}%>		
							<%}%>
							<%}%>
						</logic:iterate>
						<TD>
							<logic:present name="SearchBBSForm" property="lookin">
								<logic:equal name="SearchBBSForm" property='<%="lookin[" + indexrow + "]"%>' value="0">
									&nbsp<img border="0" src="<%=request.getContextPath()%>/img/new.jpg" >
								</logic:equal>
							</logic:present>
						</TD>
					</TR>
					</logic:iterate>	
			</logic:present>
</html:form>
</table>

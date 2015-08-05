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
<html:html locale="true">

<HEAD>

<META HTTP-EQUIV="Cache-Control" CONTENT="No-cache">
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META http-equiv="Content-Type" content="text/html; charset=GB2312">
<META name="XIAOAI" content="Eclipse">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css">


<TITLE>订单查询</TITLE>
<script language="javascript" src="<%=request.getContextPath()%>/commjs/FormScript.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/commjs/ValidData.js"></script>			

<script language="Javascript1.2">

	function callfun(flaga){
		if (flaga=='search'){
			//CommSearchForm.submit();
		}
		SearchBBSForm.flag.value=flaga;
		SearchBBSForm.nowoncepage.value=flaga;
		SearchBBSForm.nowpage.value=flaga;
		SearchBBSForm.submit();
	}
	//选择代码函数
	function selectfield(thistext,fields){
		if(selectvalue(thistext,fields)){
		}
	}

	function callpagenext(s_nowpage){
		SearchBBSForm.nowpage.value=s_nowpage;
		SearchBBSForm.nowoncepage.value=SearchBBSForm.nowoncepage.value * 1 + 1;
		SearchBBSForm.submit();
	}
	function callpageprew(s_nowpage){
		SearchBBSForm.nowpage.value=s_nowpage;
		SearchBBSForm.nowoncepage.value=SearchBBSForm.nowoncepage.value * 1 - 1;
		SearchBBSForm.submit();
	}
	function callpagenow(s_nowpage){
		SearchBBSForm.nowpage.value=s_nowpage;
		SearchBBSForm.submit();
	}
	
</script>

</HEAD>
<BODY>
<html:form action="/searchbbs">

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
			
	<logic:equal name="SearchBBSForm" property="page" value="1">
		<center>通告一览</center>
	</logic:equal>
	<logic:equal name="SearchBBSForm" property="page" value="2">
		<center>通告维护</center>
	</logic:equal>
<HR>
			<logic:equal name="SearchBBSForm" property="page" value="2">
				<P align="left">
					<A href="<%=request.getContextPath()%>/homeworldTopic.do?queryid=DQ_TOPIC">
						新发表
					</A>
				</P>
			</logic:equal>
			<logic:notPresent name="SearchBBSForm" property="data">
				<center>无通告</center>
			</logic:notPresent>
			<logic:present name="SearchBBSForm" property="data">
				<logic:present name="SearchBBSForm" property="pagelist">
					<TABLE>
					<TR>
						<TD class="textright" NOWRAP>
							当前第<bean:write name="SearchBBSForm" property="nowpage"/>页 &nbsp;
						</TD>
						<logic:present name="SearchBBSForm" property="prewOncePage">
							<TD class="textright" NOWRAP>
								<A href=javascript:callpageprew('<bean:write name="SearchBBSForm" property="prewOncePage"/>')>
									上十页&nbsp;
								</A>	
							</TD>
						</logic:present>
						<logic:iterate id="row" name="SearchBBSForm" property="pagelist" indexId="index">
							<TD class="textright" NOWRAP>
								<A href=javascript:callpagenow('<bean:write name="row" property="list"/>')>
								<!--A href='<bean:write name="row" property="list"/>'-->
									第<bean:write name="row" property="number"/>页&nbsp;
								</A>	
							</TD>
						</logic:iterate>
						<logic:present name="SearchBBSForm" property="nextOncePage">
							<TD class="textright" NOWRAP>
								<A href=javascript:callpagenext('<bean:write name="SearchBBSForm" property="nextOncePage"/>')>
									下十页&nbsp;
								</A>	
							</TD>
						</logic:present>
						<TD>
							&nbsp;
						</TD>
					</TR>

					</TABLE>
				</logic:present>
				<BR>
				<TABLE borderColor=#000033 height=25 cellSpacing=0 cellPadding=0 width="97%" align=center bgColor=#8888b0 background="" border=1>
					<TR align="center">
						<logic:iterate id="row" name="SearchBBSForm" property="datatitle" indexId="index">
							<%s_no = String.valueOf(index);%>
							<%if (s_no.equals("0")) {%>
							<%}else{%>
								<TD class="hometitle" NOWRAP>
									<bean:write name="SearchBBSForm" property='<%="datatitle[" + index + "]"%>'/>
								</TD>
							<%}%>
						</logic:iterate>
						<TD class="hometitle" NOWRAP>
							标志
						</TD>
					</TR>
					<logic:iterate id="row" name="SearchBBSForm" property="data" indexId="indexrow">
						<TR align="center">
							<%pname = "data[" + indexrow + "]";%>
							<logic:iterate id="col" name="SearchBBSForm" property="<%=pname%>" indexId="indexcol">
								<%s_no = String.valueOf(indexcol);%>
								<% if (s_no.equals("0")) {%>
								<%} else {%>
									<% if (s_no.equals("1")) {%>
											<TD class="hometext" NOWRAP>
												<%s_link="datakey[" + indexrow + "]";%>
												<A href='<%=request.getContextPath()%>/<bean:write name="SearchBBSForm" property='<%=s_link%>'/>'>
													<%=col%>
												</A>
											</TD>
									<%} else {%>
										<% if (col.equals("")) {%>
											<TD class="hometext" NOWRAP>&nbsp</TD>
										<% } else {%>
											
												<% s_img = "" + indexcol + "";%>
												<% if (s_img.equals("4")) {%>
													<TD class="hometext" NOWRAP>
														<img src="<%=request.getContextPath()%>/img/BBS/mood<%=col%>.gif " ALT=''>
													</TD>
													
												<% } else {%>									
													<TD class="hometext" NOWRAP><%=col%></TD>
												<%}%>
		
										<%}%>
									<%}%>
								<%}%>
							</logic:iterate>
							<TD class="hometext" NOWRAP>
								<logic:present name="SearchBBSForm" property="lookin">
									<logic:equal name="SearchBBSForm" property='<%="lookin[" + indexrow + "]"%>' value="0">
										&nbsp<img border="0" src="<%=request.getContextPath()%>/img/new.jpg" >
									</logic:equal>
								</logic:present>
							</TD>
						</TR>
					</logic:iterate>
				</TABLE>
				<center><bean:write name="SearchBBSForm" property="endtitle"/></center>
			</logic:present>

</html:form>
</BODY>
</html:html>
